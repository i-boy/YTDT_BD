/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class PhieuNhapKhoFacade implements PhieuNhapKhoFacadeLocal, PhieuNhapKhoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private TonKhoFacadeLocal tkFacade;

    public void create(PhieuNhapKho phieuNhapKho) {
        em.persist(phieuNhapKho);
    }

    public void edit(PhieuNhapKho phieuNhapKho) {
        em.merge(phieuNhapKho);
    }

    public void remove(PhieuNhapKho phieuNhapKho) {
        em.remove(em.merge(phieuNhapKho));
    }

    public PhieuNhapKho find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.PhieuNhapKho.class, id);
    }

    public List<PhieuNhapKho> findAll() {
        return em.createQuery("select object(o) from PhieuNhapKho as o").getResultList();
    }

    public boolean checkExisted(String soHoaDon, Date ngayHoaDon){
        boolean result = false;

        List lst = em.createQuery("select object(o) from PhieuNhapKho as o where phieunhapkhoSohoadon like :soHoaDon and phieunhapkhoNgayhoadon = :ngayHoaDon").setParameter("soHoaDon", soHoaDon). setParameter("ngayHoaDon", ngayHoaDon).getResultList();
        if (lst != null && lst.size() > 0){
            result = true;
        }
        return result;
    }

    /**
     * Tim phieu nhap theo ma phieu
     * @param id
     * @return
     */
    public PhieuNhapKho findByPhieunhapkhoMa(String id) {
        id = Utils.formatMaPhieu(id);
        return em.find(com.iesvn.yte.dieutri.entity.PhieuNhapKho.class, id);
    }
    /**
     * Tim phieu nhap theo ma phieu, ngay nhap, loai phieu, chung tu, so hoa don
     * @param pnkMa
     * @param ngayNhap
     * @param loaiPhieuMa
     * @param soChungTu
     * @param soHD
     * @return ArrayList
     */
    public List<PhieuNhapKho> find(String pnkMa, Date ngayNhap, Integer loaiPhieuMaSo, String soChungTu, String soHD) {
        pnkMa = pnkMa.trim();
        soChungTu = soChungTu.trim();
        soHD = soHD.trim();
        String sql = "";
        sql = "SELECT c FROM PhieuNhapKho c WHERE 1 = 1 ";
        if(!pnkMa.equals("") )
            sql += "AND c.phieunhapkhoMa like :phieunhapkhoMa ";
        if(ngayNhap !=null)
            sql += "AND c.phieunhapkhoNgayhoadon = :phieunhapkhoNgayhoadon ";
        if(loaiPhieuMaSo>0)
            sql += "AND c.dmloaithuocMaso.dmloaithuocMaso = :dmloaithuocMaso ";
        if(!soChungTu.equals(""))
            sql += "AND c.phieunhapkhoChungtu like :phieunhapkhoChungtu ";
        if(!soHD.equals(""))
            sql += "AND c.phieunhapkhoSohoadon like :phieunhapkhoSohoadon ";
        
        sql += " order by c.phieunhapkhoNgaygiocn desc";
        try {
            Query q = em.createQuery(sql);
            if(!pnkMa.equals("") )
                q.setParameter("phieunhapkhoMa", pnkMa);
            if(ngayNhap !=null)
                q.setParameter("phieunhapkhoNgayhoadon", ngayNhap);
            if(loaiPhieuMaSo>0)
                q.setParameter("dmloaithuocMaso", loaiPhieuMaSo);
            if(!soChungTu.equals(""))
                q.setParameter("phieunhapkhoChungtu", soChungTu);
            if(!soHD.equals(""))
                q.setParameter("phieunhapkhoSohoadon", soHD);

            return (ArrayList)q.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public String getMaPhieu() {
        return Utils.maPhieuNhap(em);
    }

    /**
     * Cap nhat va them moi phieu nhap va chi tiet phieu nhap
     * @param pn
     * @param listCNK
     * @return
     */
    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, List<TonKho> listTk) {
        System.out.println("-----createPhieuNhap-----");
        System.out.println("phieu nhap kho: " + pn.getPhieunhapkhoMa());
        System.out.println("chi tiet nhap kho: " + listCNK.size());
        String result = "";
        try {
            pn.setPhieunhapkhoMa(Utils.maPhieuNhap(em));
            em.persist(pn);
            for (int i = 0; i < listCNK.size(); i++) {
                CtNhapKho ctnk = listCNK.get(i);
                TonKho tk = listTk.get(i);
                tk.setPhieunhapkhoMa(pn);
                if (tkFacade.insertTonKho(tk)) {
                    ctnk.setPhieunhapkhoMa(pn);
                    ctnk.setCtnhapkhoMalk(tk.getTonkhoMalienket());
                    ctnk.setTonKhoMa(tk.getTonkhoMa());
                    em.persist(ctnk);
                    System.out.println("them moi thanh cong ct nhap: " + ctnk.getCtnhapkhoMa());
                } else {
                    throw new Exception("them moi ton kho bi loi.");
                }
            }

            result += pn.getPhieunhapkhoMa();
            System.out.println("result = " + result);
        } catch (Exception ex) {
            context.setRollbackOnly();
            result = "";
            System.out.println("Error: " + ex.toString());
        }
        return result;
    }

    public String createPhieuNhap(PhieuNhapKho pn, List<CtNhapKho> listCNK, Integer khoaMaso) {
        System.out.println("-----createPhieuNhap-----");
        System.out.println("phieu nhap kho: " + pn.getPhieunhapkhoMa());
        System.out.println("chi tiet nhap kho: " + listCNK.size());
        String result = "";
        try {
            pn.setPhieunhapkhoMa(Utils.maPhieuNhap(em));
            em.persist(pn);
            for (int i = 0; i < listCNK.size(); i++) {
                CtNhapKho ctnk = listCNK.get(i);
                TonKho tonkhoHT = tkFacade.getTonKhoHienTai(ctnk.getDmthuocMaso().getDmthuocMaso(), ctnk.getCtnhapkhoLo(), ctnk.getCtnhapkhoDongiaban(), khoaMaso);
                //neu co ton kho hien tai, get malk va ton kho ton, nguoc lai, insert voi malk moi, ton moi
                if(tonkhoHT != null){
                    Double soluongTon = tonkhoHT.getTonkhoTon();
                    TonKho tk = (TonKho)BeanUtils.cloneBean(tonkhoHT);
                    tk.setTonkhoMa(null);
                    tk.setPhieunhapkhoMa(pn);
                    tk.setTonkhoTondau(soluongTon);
                    tk.setTonkhoNhap(ctnk.getCtnhapkhoSoluong());
                    tk.setTonkhoXuat(0.0);
                    tk.setTonkhoTra(0.0);
                    tk.setTonkhoTon(ctnk.getCtnhapkhoSoluong()+soluongTon);
                    tk.setDtdmnhanvienCn(pn.getDtdmnhanvienCn());
                    tk.setTonkhoNgaygiocn(new Date());
                    em.persist(tk);

                    ctnk.setPhieunhapkhoMa(pn);
                    ctnk.setCtnhapkhoMalk(tk.getTonkhoMalienket());
                    ctnk.setTonKhoMa(tk.getTonkhoMa());
                    em.persist(ctnk);

                    System.out.println("them moi thanh cong ct nhap1: " + ctnk.getCtnhapkhoMa());
                }else{
                    TonKho tk = new TonKho();
                    tk.setDmnctMaso(pn.getDmnctMaso());
                    tk.setDmnguonkinhphiMaso(pn.getDmnguonkinhphiMaso());
                    tk.setDmNhaCungCap(pn.getDtdmnoibanMa());
                    tk.setDmnhasanxuatMaso(ctnk.getDmnhasanxuatMaso());
                    tk.setDmquocgiaMaso(ctnk.getDmquocgiaMaso());
                    tk.setDmthuocMaso(ctnk.getDmthuocMaso());
                    tk.setDtdmkhoMaso(pn.getDtdmkhoMaso());
                    tk.setDtdmnhanvienCn(pn.getDtdmnhanvienCn());
                    tk.setTonkhoNgaygiocn(new Date());
                    tk.setTonkhoDongia(ctnk.getCtnhapkhoDongia());
                    tk.setTonkhoDongiaban(ctnk.getCtnhapkhoDongiaban());
                    tk.setTonkhoHienthi(true);
                    tk.setTonkhoLo(ctnk.getCtnhapkhoLo());
                    tk.setTonkhoMucthue(Float.valueOf(pn.getPhieunhapkhoMucthue().toString()));
                    tk.setTonkhoNhap(ctnk.getCtnhapkhoSoluong());
                    tk.setTonkhoXuat(null);
                    tk.setTonkhoTra(null);
                    tk.setTonkhoNgayhandung(ctnk.getCtnhapkhoNgayhandung());
                    tk.setTonkhoThanghandung(ctnk.getCtnhapkhoThanghandung());
                    tk.setTonkhoNamhandung(ctnk.getCtnhapkhoNamhandung());
                    tk.setTonkhoNamnhap(ctnk.getCtnhapkhoNamnhap());
                    tk.setTonkhoSodangky(ctnk.getCtnhapkhoSodangky());
                    tk.setDmkhoaMaso(new DmKhoa(khoaMaso));

                    tk.setPhieunhapkhoMa(pn);
                    if (tkFacade.insertTonKho(tk)) {
                        ctnk.setPhieunhapkhoMa(pn);
                        ctnk.setCtnhapkhoMalk(tk.getTonkhoMalienket());
                        ctnk.setTonKhoMa(tk.getTonkhoMa());
                        em.persist(ctnk);
                        System.out.println("them moi thanh cong ct nhap2: " + ctnk.getCtnhapkhoMa());
                    }
                }
            }

            result += pn.getPhieunhapkhoMa();
            System.out.println("result = " + result);
        } catch (Exception ex) {
            context.setRollbackOnly();
            result = "";
            System.out.println("Error: " + ex.toString());
        }
        return result;
    }
    /*
     * Thanh add 29/09/2011
     * Hàm ko sử dụng, không cần sửa
     */
    private void removeAll(String listCtnkMa, String pnkMa) throws Exception {
        System.out.println("-----removeAll()-----");
        System.out.println("-----listCtnkMa: " + listCtnkMa);
        System.out.println("-----pnkMa: " + pnkMa);
        String sql = "";
        if (listCtnkMa.equals("")) {
            sql = "SELECT c FROM CtNhapKho c WHERE c.phieunhapkhoMa.phieunhapkhoMa = :pnkMa";
        } else {
            sql = "SELECT c FROM CtNhapKho c WHERE c.ctnhapkhoMa NOT IN (" +
                    listCtnkMa + ") AND c.phieunhapkhoMa.phieunhapkhoMa = :pnkMa";
        }
        System.out.println("-----sql: " + sql);
        Query q = em.createQuery(sql);
        q.setParameter("pnkMa", pnkMa);
        List<CtNhapKho> listCTNK = q.getResultList();
        if (listCTNK != null) {
            for (CtNhapKho ctnk : listCTNK) {
                //TonKho tk = ctnk.getTonkhoMa();
                //em.remove(tk);
                //em.remove(ctnk);
                q = em.createNativeQuery("SELECT FUN_DEL_CT_NHAP_KHO(?)");
                q.setParameter(1, ctnk.getCtnhapkhoMa());
                Vector result = (Vector) q.getSingleResult();
                System.out.println("-----result remove: " + result);
                String temp = result.get(0).toString();
                if (temp.equals("0")) {
                    throw new Exception("Khong the xoa chi tiet nhap kho: " + ctnk.getCtnhapkhoMa());
                } else {
                    em.remove(ctnk);
                //em.remove(ctnk.getTonkhoMa());
                }
            }
        }
    }
}


