/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;

import com.iesvn.yte.dieutri.utils.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class KiemKeFacade implements KiemKeFacadeLocal, KiemKeFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private KiemKeKhoFacadeLocal kiemkeKhoFacade;

    @Resource
    private SessionContext context;

    public void create(KiemKe kiemKe) {
        em.persist(kiemKe);
    }

    public void edit(KiemKe kiemKe) {
        em.merge(kiemKe);
    }

    public void remove(KiemKe kiemKe) {
        em.remove(em.merge(kiemKe));
    }

    public KiemKe find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.KiemKe.class, id);
    }

    public String getMaPhieu() {
        String maPhieu = Utils.maPhieuKiemKe(em);
        return maPhieu;
    }

    public List<KiemKe> findAll() {
        return em.createQuery("select object(o) from KiemKe as o").getResultList();
    }
    
    public KiemKe findByKho(String maKho){
        System.out.println("---find KiemKe ByKho");
         try {
            Query query = em.createQuery("select object(o) from KiemKe as o where o.dmkhoaMaso.dmkhoaMa = :maKho");
            query.setParameter("maKho", maKho);
            List<KiemKe> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (KiemKe) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error KiemKe find: " + e);
        }
        return null;
    }
    
    public List<KiemKe> findByMaPhieuKiem(String maPhieuKiem){
        System.out.println("---find List<KiemKe> findByMaPhieuKiem");
         try {             
            Query query = em.createQuery("select object(o) from KiemKe o where o.maphieukiem = :maPhieuKiem");
            System.out.println("---find List<KiemKe> findByMaPhieuKiem" + query.toString()); 
            query.setParameter("maPhieuKiem", maPhieuKiem);  
            System.out.println("---find List<KiemKe> findByMaPhieuKiem" + query.toString()); 
            List<KiemKe> list = query.getResultList();
            System.out.println("---find List<KiemKe> findByMaPhieuKiem" + list.size()); 
            if (list != null && list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error KiemKe findByMaPhieuKiem find: " + e);
        }
        return null;
    }

    /*
     * Thanh add 12/10/2011
     * Fixed group by
     */
    public List<KiemKe> findByDieuKienTimKiem(String maPhieuKiem, Date ngayKiemKeTu, Date ngayKiemKeDen, Integer dmKhoaMaso, Integer dmLoaiThuocMaso, List<DmPhanLoaiThuoc> lstDmPLThuoc, String trangthai){
         System.out.println("---find List<KiemKe> findByDieuKienTimKiem");
         try {
             String sSQL = "select o.* from Kiem_Ke o, (select DISTINCT maphieukiem from Kiem_Ke_Phan_Loai_Thuoc where 1 = 1 ";             
             if(lstDmPLThuoc.size() > 0){
                 String sParam = "(";
                 for(int i = 0; i<lstDmPLThuoc.size();i++){
                    DmPhanLoaiThuoc dmPLThuoc = lstDmPLThuoc.get(i);
                    Integer dmPhanLoaiThuocMaso = dmPLThuoc.getDmphanloaithuocMaso();
                    if(i == 0){
                        sParam = sParam + dmPhanLoaiThuocMaso;
                    }else{
                        sParam = sParam + "," + dmPhanLoaiThuocMaso;
                    }
                 }
                 sParam += ") ";
                 sSQL = sSQL + "and dmphanloaithuoc_Maso IN "+ sParam;
             }
             sSQL += " ) plt where o.dmkhoa_Maso = :dmKhoaMaso and o.maphieukiem = plt.maphieukiem ";
             if(!maPhieuKiem.equals("")){
                sSQL = sSQL + "and o.maphieukiem = :maPhieuKiem ";
             }
             if(ngayKiemKeTu != null){
                sSQL = sSQL + "and to_date(o.ngaykiemke) between :ngayKiemKeTu and :ngayKiemKeDen ";
             }
             if(dmLoaiThuocMaso != null){
                sSQL = sSQL + "and o.dmloaithuoc_Maso = :dmLoaiThuocMaso ";
             }
             if(!trangthai.equals("")){
                sSQL = sSQL + "and o.trangthai = :trangthai ";
             }
             sSQL = sSQL + "order by o.maphieukiem";
             System.out.println(sSQL);
             Query query = em.createNativeQuery(sSQL, KiemKe.class);
            if(!maPhieuKiem.equals("")){
                query.setParameter("maPhieuKiem", maPhieuKiem);
             }
             if(ngayKiemKeTu != null){
                query.setParameter("ngayKiemKeTu", ngayKiemKeTu);
                query.setParameter("ngayKiemKeDen", ngayKiemKeDen);
             }
             query.setParameter("dmKhoaMaso", dmKhoaMaso);
             if(dmLoaiThuocMaso != null){
                query.setParameter("dmLoaiThuocMaso", dmLoaiThuocMaso);
             }
             if(!trangthai.equals("")){
                query.setParameter("trangthai", trangthai);
             }
            List<KiemKe> list = query.getResultList();
            if (list != null && list.size() > 0) {
                System.out.println("---find List<KiemKe> findByDieuKienTimKiem " + list.size());
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error KiemKe findByMaPhieuKiem find: " + e);
        }
        return null;
    }
    /*
     * Thanh add 29/09/2011
     * Đã fixed, date ok
     */
    public List<KiemKe> findByKiemKeJob(int thoihandongKiemKe){
        System.out.println("---find List<KiemKe> findByKiemKeJob");
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, -thoihandongKiemKe);
            System.out.println(dateFormat.format(cal.getTime()));
            String kiemkeDate = dateFormat.format(cal.getTime());
            String sSQL = "select * from Kiem_Ke o where o.ngaykiemke <= '" + kiemkeDate + "' and o.trangthai = 'OPEN'";
            Query query = em.createNativeQuery(sSQL,KiemKe.class);
            List<KiemKe> list = query.getResultList();
            if (list != null && list.size() > 0) {
                System.out.println("---find List<KiemKe> findByKiemKeJob " + list.size());
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error findByKiemKeJob: " + e);
        }
        return null;
    }

    public String getListPhanLoaiThuocMa(String maphieukiem){
        Query query = em.createQuery("select object(o) from KiemKePhanLoaiThuoc o where o.maphieukiem.maphieukiem = :maphieukiem");
        query.setParameter("maphieukiem", maphieukiem);
        List<KiemKePhanLoaiThuoc> listKKPLThuoc = query.getResultList();
        String maPLThuoc ="";
        if(listKKPLThuoc != null && listKKPLThuoc.size() > 0){
            for(KiemKePhanLoaiThuoc plthuoc:listKKPLThuoc){
                if(plthuoc.getDmphanloaithuocMaso() != null){
                    maPLThuoc = maPLThuoc + plthuoc.getDmphanloaithuocMaso().getDmphanloaithuocMa() +";";
                }else{
                    maPLThuoc = "ALL";
                }
            }
        }
        return maPLThuoc;
    }

    public List<KiemKePhanLoaiThuoc> getListKiemKePhanLoaiThuoc(String maphieukiem){
        Query query = em.createQuery("select object(o) from KiemKePhanLoaiThuoc o where o.maphieukiem.maphieukiem = :maphieukiem");
        query.setParameter("maphieukiem", maphieukiem);
        List<KiemKePhanLoaiThuoc> listKKPLThuoc = query.getResultList();
        if(listKKPLThuoc != null && listKKPLThuoc.size() > 0){
            return listKKPLThuoc;
        }
        return null;
    }

    public String taoBangKiemKe(KiemKe kiemke, String listPL, List<DmPhanLoaiThuoc> listDmPLThuocKK, String lthuoc, boolean chon) throws Exception{
        System.out.println("Begin taoBangKiemKe");
        String maPhieuKiem ="";
        try{
            maPhieuKiem = getMaPhieu();
            List<TonKho> listTK = tkFacade.findListTonKhoForKiemKe(kiemke.getDmkhoaMaso().getDmkhoaMaso(), listPL, lthuoc, chon);
            System.out.println("Danh sach ton kho: "+ listTK.size());
            //Luu du lieu trong bang kiem ke
            kiemke.setMaphieukiem(maPhieuKiem);
            getEm().persist(kiemke);

            //Luu du lieu trong bang KiemKePhanLoaiThuoc
            if(listDmPLThuocKK.size()>0){
                for(DmPhanLoaiThuoc dmPLThuoc:listDmPLThuocKK){
                    KiemKePhanLoaiThuoc kkPLT = new KiemKePhanLoaiThuoc();
                    kkPLT.setDmphanloaithuocMaso(dmPLThuoc);
                    kkPLT.setMaphieukiem(kiemke);
                    getEm().persist(kkPLT);
                }
            }else{
                KiemKePhanLoaiThuoc kkPLT = new KiemKePhanLoaiThuoc();
                kkPLT.setDmphanloaithuocMaso(null);
                kkPLT.setMaphieukiem(kiemke);
                getEm().persist(kkPLT);
            }

            for(TonKho tk:listTK){
                if(tk.getTonkhoMaphieukiem()==null){
                    TonKho tkTemp = (TonKho) BeanUtils.cloneBean(tk);
                    tkTemp.setTonkhoMaphieukiem(maPhieuKiem);
                    tkTemp.setTonkhoTondau(new Double(0));
                    tkTemp.setTonkhoNhap(new Double(0));
                    tkTemp.setTonkhoTra(new Double(0));
                    tkTemp.setTonkhoXuat(new Double(0));
                    tkTemp.setTonkhoTon(new Double(0));
                    tkTemp.setTonkhoNgaygiocn(new Date());
                    tkTemp.setTonkhoMa(null);
                    Integer tonkho_ma =  tkFacade.createTonKho(tkTemp);

                    KiemKeKho kiemkeKho = new KiemKeKho();
                    kiemkeKho.setKiemkeMalienket(tk.getTonkhoMalienket());
                    kiemkeKho.setKiemkeMaphieukiem(kiemke);
                    kiemkeKho.setDmthuocMaso(tk.getDmthuocMaso());
                    kiemkeKho.setDmnctMaso(tk.getDmnctMaso());
                    kiemkeKho.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
                    kiemkeKho.setDmquocgiaMaso(tk.getDmquocgiaMaso());
                    kiemkeKho.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
                    kiemkeKho.setKiemkeNamnhap(tk.getTonkhoNamnhap());
                    kiemkeKho.setKiemkeNgayhandung(tk.getTonkhoNgayhandung());
                    kiemkeKho.setKiemkeNamhandung(tk.getTonkhoNamhandung());
                    kiemkeKho.setKiemkeThanghandung(tk.getTonkhoThanghandung());
                    kiemkeKho.setKiemkeDongia(tk.getTonkhoDongia());
                    kiemkeKho.setKiemkeDongiaban(tk.getTonkhoDongiaban());
                    kiemkeKho.setKiemkeMucthue(tk.getTonkhoMucthue());
                    kiemkeKho.setKiemkeLo(tk.getTonkhoLo());
                    kiemkeKho.setKiemkeSodangky(tk.getTonkhoSodangky());
                    kiemkeKho.setKiemkeNgaykiem(new Date());
                    kiemkeKho.setKiemkeTon(tk.getTonkhoTon());
                    kiemkeKho.setKiemkeTontt(tk.getTonkhoTon());
                    kiemkeKho.setKiemkePhanbiet(tk.getTonkhoPhanbiet());
                    kiemkeKho.setDmkhoaMaso(kiemke.getDmkhoaMaso());
                    kiemkeKho.setDtdmnhanvienCn(kiemke.getDtdmnhanvienCn());
                    kiemkeKho.setKiemkeNgaygiocn(new Date());
                    kiemkeKho.setTonkhoMa(tonkho_ma);
                    getEm().persist(kiemkeKho);
                }
            }
            System.out.println("End taoBangKiemKe");
            return maPhieuKiem;
        } catch (Exception ex) {
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong taoBangKiemKe(): " + ex);
            throw ex;
        }        
    }

    public String hoantatKiemKe(KiemKe kiemke){
        System.out.println("Begin hoantatKiemKe");
        String maKiemKe = kiemke.getMaphieukiem();
        List<KiemKeKho> lstKiemKeKho = kiemkeKhoFacade.findByMaPhieuKiem(maKiemKe);
        DtDmNhanVien nhanvienCN = kiemke.getDtdmnhanvienCn();
        Date ngaygioCN = kiemke.getNgaygiocn();
        em = getEm();
        try{
            System.out.println("Cap nhat danh sach kiem ke kho");
            if(lstKiemKeKho != null){
                for (KiemKeKho obj : lstKiemKeKho){
                    TonKho tk = tkFacade.find(obj.getTonkhoMa());
                    if (tk!=null){
                        tk.setTonkhoTondau(obj.getKiemkeTontt());
                        tk.setTonkhoTon(obj.getKiemkeTontt());
                        tk.setTonkhoMaphieukiem(null);
                        em.merge(tk);
                    }
                    //cap nhat lai kiem ke kho voi nguoi cap nhat kiem ke
                    obj.setKiemkeNgaygiocn(ngaygioCN);
                    obj.setDtdmnhanvienCn(nhanvienCN);
                    em.merge(obj);
                }
            }
            System.out.println("Cap nhat kiem ke");
            //update lai kiem ke voi trang thai 'CLOSE', nguoi cap nhat kiem ke, ngay cn kiem ke
            em.merge(kiemke);
            System.out.println("End hoantatKiemKe");
            return "SUCCESS";
        }catch(Exception er){
            er.printStackTrace();
            context.setRollbackOnly();
            return "ERROR - "+ er.getMessage();
        }
    }

    public String huyKiemKe(KiemKe kiemke){
        System.out.println("Begin huyKiemKe");
        String maKiemKe = kiemke.getMaphieukiem();
        List<KiemKeKho> lstKiemKeKho = kiemkeKhoFacade.findByMaPhieuKiem(maKiemKe);
        em = getEm();
        try{
            if(lstKiemKeKho != null){
                for (KiemKeKho obj : lstKiemKeKho){
                    if (obj.getKiemkeTontt()!=null) {
                        //Xoa KiemKeKho
                        em.remove(em.merge(obj));
                        TonKho tk = tkFacade.find(obj.getTonkhoMa());
                        if (tk!=null){
                            //xoa ton kho ve nhu cu
                            em.remove(tk);
                        }
                    }
                }
            }
            //xoa kiem ke phan loai thuoc
            List<KiemKePhanLoaiThuoc> lstKiemKkePLT = getListKiemKePhanLoaiThuoc(maKiemKe);
            if(lstKiemKkePLT != null && lstKiemKkePLT.size() > 0){
                System.out.println("KKPLT.size: "+ lstKiemKkePLT.size());
                for(KiemKePhanLoaiThuoc kiemkiePLT:lstKiemKkePLT){
                    em.remove(em.merge(kiemkiePLT));
                }
            }
            System.out.println("Xoa kiem ke");
            //Xoa kiem ke
            em.remove(em.merge(kiemke));
            System.out.println("End huyKiemKe");
            return "SUCCESS";
        }catch(Exception er){
            return "ERROR - " + er.getMessage();
        }
    }
    
    public String capnhatSoLieuKiemKe(KiemKe kiemke, List<KiemKeKho> lstKiemKeKho){
        System.out.println("Begin capnhatSoLieuKiemKe");
        em = getEm();
        try{
            if(lstKiemKeKho != null){
                for (KiemKeKho obj : lstKiemKeKho){
                    KiemKeKho kiemkeKho = kiemkeKhoFacade.find(obj.getKiemkeMa());
                    if(kiemkeKho != null){
                        kiemkeKho.setDtdmnhanvienCn(obj.getDtdmnhanvienCn());
                        kiemkeKho.setKiemkeNgaygiocn(obj.getKiemkeNgaygiocn());
                        kiemkeKho.setKiemkeTontt(obj.getKiemkeTontt());
                        if(obj.getKiemkeLydo() != null){
                            kiemkeKho.setKiemkeLydo(obj.getKiemkeLydo());
                        }
                        em.merge(kiemkeKho);
                        TonKho tk = tkFacade.find(kiemkeKho.getTonkhoMa());                        
                        if (tk!=null){
                            tk.setTonkhoTondau(obj.getKiemkeTontt());
                            tk.setTonkhoTon(obj.getKiemkeTontt());
                            tk.setTonkhoMaphieukiem(null);
                            em.merge(tk);
                        }
                    }
                }
            }
            //update lai kiem ke voi trang thai 'CLOSE', nguoi cap nhat kiem ke, ngay cn kiem ke
            em.merge(kiemke);
            System.out.println("End capnhatSoLieuKiemKe");
            return "SUCCESS";
        }catch(Exception er){
            context.setRollbackOnly();
            return "ERROR - "+ er.getMessage();
        }
    }

    public boolean dangKiemKe(String malienket, Integer khoaMaso){
        boolean dangKiemKe = false;
        String sSQL = "select count(tk) from TonKho tk where tk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso "+
                                                        "and tk.tonkhoMalienket = :tonkhoMalienket " +
                                                        "and tk.tonkhoMaphieukiem is not null";
        Query query = em.createQuery(sSQL);
        query.setParameter("tonkhoMalienket", malienket);
        query.setParameter("dmkhoaMaso", khoaMaso);
        long recordNum = ((Long) query.getSingleResult()).longValue();
        if(recordNum > 0){
            dangKiemKe = true;
        }
        return dangKiemKe;
    }

    public boolean isExistedKiemKe(String loaiThuocMa, Integer khoaMaso){
        boolean dangKiemKe = false;
        String sSQL = "select count(kk) from KiemKe kk where kk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso ";
        
        if(loaiThuocMa != null){
            sSQL += "and (kk.dmloaithuocMaso.dmloaithuocMa = :dmloaithuocMa OR kk.dmloaithuocMaso IS NULL) ";
        }
        sSQL += "and kk.trangthai like 'OPEN' ";
        Query query = em.createQuery(sSQL);
        if(loaiThuocMa != null){
            query.setParameter("dmloaithuocMa", loaiThuocMa);
        }
        query.setParameter("dmkhoaMaso", khoaMaso);
        long recordNum = ((Long) query.getSingleResult()).longValue();
        if(recordNum > 0){
            dangKiemKe = true;
        }
        return dangKiemKe;
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the context
     */
    public SessionContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(SessionContext context) {
        this.context = context;
    }
}


