/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatBh;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class PhieuXuatBhFacade implements PhieuXuatBhFacadeLocal, PhieuXuatBhFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private ThuocPhongKhamFacadeLocal tpkFacade;
    @EJB
    private CtXuatBhFacadeLocal ctXuatBHFacade;
    @Resource
    private SessionContext context;

    public void create(PhieuXuatBh phieuXuatBh) {
        em.persist(phieuXuatBh);
    }

    public void edit(PhieuXuatBh phieuXuatBh) {
        em.merge(phieuXuatBh);
    }

    public void remove(PhieuXuatBh phieuXuatBh) {
        em.remove(em.merge(phieuXuatBh));
    }

    public PhieuXuatBh find(Object id) {
        String pxMa = (String) id;
        pxMa = Utils.formatMaPhieu(pxMa);
        System.out.println("---formatMaPhieu -ma phieu xuat obj" + pxMa);
        return em.find(com.iesvn.yte.dieutri.entity.PhieuXuatBh.class, pxMa);
    }

    public List<PhieuXuatBh> findAll() {
        return em.createQuery("select object(o) from PhieuXuatBh as o").getResultList();
    }

    public String create(PhieuXuatBh phieuxuatBh, List<CtXuatBh> listCtXuatBh, List<TonKho> listTk) {
        String result = "";
        try {
            DmKhoa khoa = phieuxuatBh.getDmkhoaMaso();
            if (khoa != null) {
                Integer khoaMaso = khoa.getDmkhoaMaso();
                System.out.println("KhoaMaSo: " + khoaMaso);
                if (khoaMaso != null) {
                    khoa = em.find(DmKhoa.class, khoaMaso);
                    phieuxuatBh.setDmkhoaMaso(khoa);
                }
            }
            DtDmNhanVien nvBacsi = phieuxuatBh.getDtdmnhanvienBacsi();
            if (nvBacsi != null) {
                String nvMa = nvBacsi.getDtdmnhanvienMa();
                System.out.println("nvbacsiMa: " + nvMa);
                if (nvMa != null && !nvMa.equals("")) {
                    nvBacsi = em.find(DtDmNhanVien.class, nvMa);
                    phieuxuatBh.setDtdmnhanvienBacsi(nvBacsi);
                }
            }
            DtDmNhanVien nvCn = phieuxuatBh.getDtdmnhanvienCn();
            if (nvCn != null) {
                String nvMa = nvCn.getDtdmnhanvienMa();
                System.out.println("nvcapnhatMa : " + nvMa);
                if (nvMa != null && !nvMa.equals("")) {
                    nvCn = em.find(DtDmNhanVien.class, nvMa);
                    phieuxuatBh.setDtdmnhanvienCn(nvCn);
                }
            }
            DtDmNhanVien nvPhat = phieuxuatBh.getDtdmnhanvienPhat();
            if (nvPhat != null) {
                String nvMa = nvPhat.getDtdmnhanvienMa();
                System.out.println("nvPhatMa: " + nvMa);
                if (nvMa != null && !nvMa.equals("")) {
                    nvPhat = em.find(DtDmNhanVien.class, nvMa);
                    phieuxuatBh.setDtdmnhanvienPhat(nvPhat);
                }
            }
            TiepDon tiepdon = phieuxuatBh.getTiepdonMa();
            if (tiepdon != null) {
                String tdMa = tiepdon.getNguyenhanMa();
                if (tdMa != null && !tdMa.equals("")) {
                    tiepdon = em.find(TiepDon.class, tdMa);
                    phieuxuatBh.setTiepdonMa(tiepdon);
                }
            }

            String pxMa = phieuxuatBh.getPhieuxuatbhMa();
            if (pxMa == null || pxMa.equals("")) {
                System.out.println("insert phieu xuat bao hiem");
                pxMa = Utils.maPhieuXuatBaoHiem(em);
                System.out.println("Utils.maPhieuXuatBaoHiem: " + pxMa);
                phieuxuatBh.setPhieuxuatbhMa(pxMa);
                em.persist(phieuxuatBh);
                pxMa = phieuxuatBh.getPhieuxuatbhMa();
                System.out.println("PhieuXuatBhMA: " + pxMa);
                System.out.println("insert thanh cong phieu xuat bao hiem ");
            } else {
                pxMa = Utils.formatMaPhieu(pxMa);
                System.out.println("cap nhat phieu xuat bao hiem");
                em.merge(phieuxuatBh);

                System.out.println("Cap nhat danh sach CtXuatBh");
                String listCtXuatBhMa = "";
                int temp = 0;
                for (CtXuatBh ctxbh : listCtXuatBh) {
                    if (ctxbh.getCtxuatbhMa() != null && ctxbh.getCtxuatbhMa() != 0) {
                        if (temp == 0) {
                            listCtXuatBhMa += ctxbh.getCtxuatbhMa();
                            temp++;
                        } else {
                            listCtXuatBhMa += "," + ctxbh.getCtxuatbhMa();
                        }
                    }
                }
                System.out.println("List ctnk ma: " + listCtXuatBhMa);

                if (!listCtXuatBhMa.equals("")) {
                    System.out.println("Xoa nhung CtXuatBh khong co trong danh sach CtXuatBh");
                    this.removeCtXuatBh(listCtXuatBhMa, pxMa);
                }
                System.out.println("update thanh cong phieu nhap");
            }

            for (int i = 0; i < listCtXuatBh.size(); i++) {
                CtXuatBh ctx = listCtXuatBh.get(i);
                TonKho tk = listTk.get(i);
                if (ctx.getCtxuatbhMa() != null && ctx.getCtxuatbhMa() != 0) {
                    System.out.println("-----Update ct xuat bh-----");
                    CtXuatBh ctxOld = em.find(CtXuatBh.class, ctx.getCtxuatbhMa());
                    Double slOld = ctxOld.getCtxuatbhSoluong();
                    Double slNew = ctx.getCtxuatbhSoluong();
                    if (tk.getTonkhoXuat() == null) {
                        tk.setTonkhoXuat(0.0);
                    }
                    Double tkXuat = tk.getTonkhoXuat() - slOld + slNew;
                    Double tkTon = tk.getTonkhoTon() + slOld - slNew;
                    tk.setTonkhoXuat(tkXuat);
                    tk.setTonkhoTon(tkTon);
                    ctx.setCtxuatbhThutu(i + 1);
                    em.merge(ctx);
                    System.out.println("Update ct phieu xuat: " + ctx.getCtxuatbhMa());
                } else {
                    System.out.println("-----Insert ct phieu xuat bh-----");
                    ctx.setCtxuatbhMa(null);
                    ctx.setPhieuxuatbhMa(phieuxuatBh);

                    tk.setTonkhoMa(null);
                    tk.setTonkhoXuat(ctx.getCtxuatbhSoluong());
                    tk.setTonkhoTra(Double.valueOf("0"));
                    tk.setTonkhoNhap(Double.valueOf("0"));
                    boolean rs = tkFacade.insertTonKho(tk);
                    System.out.println("insert TonKHo: " + tk.getTonkhoMa());
                    if (rs) {
                        ctx.setCtxuatbhThutu(i + 1);
                        ctx.setTonKhoMa(tk.getTonkhoMa());
                        em.persist(ctx);
                        System.out.println("Insert ct phieu xuat bh: " + ctx.getCtxuatbhMa());
                    } else {
                        System.out.println("INSERT TONKHO FAIL ");
                    }
                }
            }
            result += phieuxuatBh.getPhieuxuatbhMa();
            System.out.println("result: " + result);

        } catch (Exception ex) {
            context.setRollbackOnly();
            result = "";
            ex.printStackTrace();
        }
        return result;
    }

    private void removeCtXuatBh(String listCtxBhMa, String pxBhMa) {
        System.out.println("Begin remove  removeCtXuatBh(String listCtxBhMa, String pxBhM) method");
        Query q = em.createQuery("SELECT c FROM CtXuatKho c WHERE c.ctxuatkhoMa NOT IN (" + listCtxBhMa + ") AND c.phieuxuatkhoMa.phieuxuatkhoMa = :pxkMa");
        q.setParameter("pxkMa", pxBhMa);
        List<CtXuatBh> listCtXuat = q.getResultList();
        if (listCtXuat != null) {
            for (CtXuatBh ct : listCtXuat) {
                /*
                TonKho tk = ct.getTonkhoMa();
                Double sl = ct.getCtxuatbhSoluong();
                Double ton = tk.getTonkhoXuat() - sl;                
                tk.setTonkhoXuat(ton);
                tk.setTonkhoTon(tk.getTonkhoTon() + sl);
                em.remove(ct);
                em.merge(tk);
                System.out.println("Remove ct phieu xuat: " + ct.getCtxuatbhMa());
                 */
            }
        }
        System.out.println("End remove  removeCtXuatBh(String listCtxBhMa, String pxBhM) method");
    }

    public List<PhieuXuatBh> findByTiepDonMa(String tiepDonMa) {
        Query q = em.createQuery("select object(o) from PhieuXuatBh as o where o.tiepdonMa.tiepdonMa = :tiepDonMa");
        q.setParameter("tiepDonMa", tiepDonMa);
        return q.getResultList();
    }

    public List<PhieuXuatBh> findByTiepDonMa_Kho(String tiepDonMa, Integer khoMaso) {
        Query q = em.createQuery("select object(o) from PhieuXuatBh as o where o.tiepdonMa.tiepdonMa = :tiepDonMa and o.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso");
        q.setParameter("tiepDonMa", tiepDonMa);
        q.setParameter("dmkhoaMaso", khoMaso);
        return q.getResultList();
    }

    public String createByThuocPhongKham(Integer khoaMaSo, PhieuXuatBh phieuxuatBh, List<ThuocPhongKham> listTPK, List<CtXuatBh> listCtXuatBH, String priority) {
        if (listTPK == null) {
            return "";
        }
        try {
            //Kiem tra xem ton cua kho BHYT/TE co du de xuat cho BN hay khong
            int i = 0;
            for (CtXuatBh obj : listCtXuatBH) {
                CtXuatBh ctXuatBhTemp = new CtXuatBh();
                ctXuatBhTemp = (CtXuatBh)BeanUtils.cloneBean(obj);
                TonKho tk_old = tkFacade.getTonKhoHienTai(obj.getCtxuatbhMalk(), khoaMaSo);
                Double tonHT = tk_old.getTonkhoTon();
                if( tonHT < obj.getCtxuatbhSoluong() ){
                    boolean founded = false;
                    int vitri =-1;
                    TonKho tonkhoThayThe = new TonKho();
                    //Tho add - neu khong du voi lo nay thi lay lo khac, cung gia, du? so luong, theo do uu tien
                    List<TonKho> lstTonKhoHT = tkFacade.getTonKhoHienTai(obj.getDmthuocMaso().getDmthuocMaso(), khoaMaSo, priority);
                    if(lstTonKhoHT != null){
                        for(TonKho tonkhoHT:lstTonKhoHT){
                            if(tonkhoHT.getTonkhoTon() >= obj.getCtxuatbhSoluong()){
                                //thay the ma lien ket cua ma thuoc nay thanh ma lien ket khac va update lai listCtXuatBH
                                tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkhoHT);
                                vitri = i;
                                founded = true;
                                break;
                            }
                        }
                    }
                    if(founded){
                        ctXuatBhTemp.setCtxuatbhMalk(tonkhoThayThe.getTonkhoMalienket());
                        ctXuatBhTemp.setCtxuatbhDongia(tonkhoThayThe.getTonkhoDongia());
                        ctXuatBhTemp.setCtxuatbhLo(tonkhoThayThe.getTonkhoLo());
                        ctXuatBhTemp.setCtxuatbhNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                        ctXuatBhTemp.setCtxuatbhNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                        ctXuatBhTemp.setCtxuatbhThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                        ctXuatBhTemp.setCtxuatbhNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                        ctXuatBhTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                        ctXuatBhTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                        ctXuatBhTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                        ctXuatBhTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                        listCtXuatBH.set(vitri,ctXuatBhTemp);
                    }else{
                        return "SLTHET-"+obj.getDmthuocMaso().getDmthuocMa() + ":"+ (obj.getCtxuatbhSoluong() - tonHT);
                    }
                }
                i++;
            }

            String pxMa = phieuxuatBh.getPhieuxuatbhMa();
            if (pxMa == null || pxMa.equals(""))
            {
                pxMa = Utils.maPhieuXuatBaoHiem(em);
                phieuxuatBh.setPhieuxuatbhMa(pxMa);
                em.persist(phieuxuatBh);
                pxMa = phieuxuatBh.getPhieuxuatbhMa();
                for (ThuocPhongKham obj : listTPK) {
                    obj = tpkFacade.find(obj.getThuocphongkhamMa());
                    if (obj != null) {
                        obj.setThuocphongkhamMaphieud(pxMa);
                        //tpkFacade.edit(obj);
                        getEm().merge(obj);
                    }
                }
            }

            for (CtXuatBh obj : listCtXuatBH) {
                if (obj.getCtxuatbhMa() == null)
                {
                    String malk = obj.getCtxuatbhMalk();
                    TonKho tk_old = tkFacade.getTonKhoHienTai(malk, khoaMaSo);

                    TonKho newtkxuat = (TonKho) BeanUtils.cloneBean(tk_old);
                    newtkxuat.setTonkhoMa(null);
                    newtkxuat.setTonkhoNhap(new Double(0));
                    newtkxuat.setTonkhoTra(new Double(0));
                    newtkxuat.setTonkhoXuat(obj.getCtxuatbhSoluong());
                    tkFacade.insertTonKho(newtkxuat);

                    obj.setPhieuxuatbhMa(phieuxuatBh);
                    obj.setTonKhoMa(newtkxuat.getTonkhoMa());
                    getEm().persist(obj);
                }
            }

            return pxMa == null ? "" : pxMa;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }


    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


