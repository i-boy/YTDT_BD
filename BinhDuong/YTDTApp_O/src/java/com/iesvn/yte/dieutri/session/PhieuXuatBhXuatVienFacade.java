/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;

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
public class PhieuXuatBhXuatVienFacade implements PhieuXuatBhXuatVienFacadeLocal, PhieuXuatBhXuatVienFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private ThuocNoiTruFacadeLocal tntFacade;
    @EJB
    private CtXuatBhXuatVienFacadeLocal ctXuatBHXVFacade;
    @Resource
    private SessionContext context;

    public void create(PhieuXuatBhXuatVien phieuXuatBh) {
        em.persist(phieuXuatBh);
    }

    public void edit(PhieuXuatBhXuatVien phieuXuatBh) {
        em.merge(phieuXuatBh);
    }

    public void remove(PhieuXuatBhXuatVien phieuXuatBh) {
        em.remove(em.merge(phieuXuatBh));
    }

    public PhieuXuatBhXuatVien find(Object id) {
        String pxMa = (String) id;
        pxMa = Utils.formatMaPhieu(pxMa);
        System.out.println("---formatMaPhieu -ma phieu xuat obj" + pxMa);
        return em.find(com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien.class, pxMa);
    }

    public List<PhieuXuatBhXuatVien> findAll() {
        return em.createQuery("select object(o) from PhieuXuatBhXuatVien as o").getResultList();
    }

    public String create(PhieuXuatBhXuatVien phieuxuatBh, List<CtXuatBhXuatVien> listCtXuatBh, List<TonKho> listTk) {
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
            Hsba hsba = phieuxuatBh.getHsba();
            if (hsba != null) {
                String sovaovien = hsba.getHsbaSovaovien();
                if (sovaovien != null && !sovaovien.equals("")) {
                    hsba = em.find(Hsba.class, sovaovien);
                    phieuxuatBh.setHsba(hsba);
                }
            }

            String pxMa = phieuxuatBh.getPhieuxuatbhxvMa();
            if (pxMa == null || pxMa.equals("")) {
                System.out.println("insert phieu xuat bao hiem");
                pxMa = Utils.maPhieuXuatBaoHiem(em);
                System.out.println("Utils.maPhieuXuatBaoHiem: " + pxMa);
                phieuxuatBh.setPhieuxuatbhxvMa(pxMa);
                em.persist(phieuxuatBh);
                pxMa = phieuxuatBh.getPhieuxuatbhxvMa();
                System.out.println("PhieuXuatBhMA: " + pxMa);
                System.out.println("insert thanh cong phieu xuat bao hiem ");
            } else {
                pxMa = Utils.formatMaPhieu(pxMa);
                System.out.println("cap nhat phieu xuat bao hiem");
                em.merge(phieuxuatBh);

                System.out.println("Cap nhat danh sach CtXuatBh");
                String listCtXuatBhMa = "";
                int temp = 0;
                for (CtXuatBhXuatVien ctxbh : listCtXuatBh) {
                    if (ctxbh.getCtxuatbhxvMa() != null && ctxbh.getCtxuatbhxvMa() != 0) {
                        if (temp == 0) {
                            listCtXuatBhMa += ctxbh.getCtxuatbhxvMa();
                            temp++;
                        } else {
                            listCtXuatBhMa += "," + ctxbh.getCtxuatbhxvMa();
                        }
                    }
                }
                System.out.println("List ctnk ma: " + listCtXuatBhMa);

                if (!listCtXuatBhMa.equals("")) {
                    System.out.println("Xoa nhung CtXuatBhXuatVien khong co trong danh sach CtXuatBhXuatVien");
                    this.removeCtXuatBh(listCtXuatBhMa, pxMa);
                }
                System.out.println("update thanh cong phieu nhap");
            }

            for (int i = 0; i < listCtXuatBh.size(); i++) {
                CtXuatBhXuatVien ctx = listCtXuatBh.get(i);
                TonKho tk = listTk.get(i);
                if (ctx.getCtxuatbhxvMa() != null && ctx.getCtxuatbhxvMa() != 0) {
                    System.out.println("-----Update ct xuat bh xuatvien-----");
                    CtXuatBhXuatVien ctxOld = em.find(CtXuatBhXuatVien.class, ctx.getCtxuatbhxvMa());
                    Double slOld = ctxOld.getCtxuatbhxvSoluong();
                    Double slNew = ctx.getCtxuatbhxvSoluong();
                    if (tk.getTonkhoXuat() == null) {
                        tk.setTonkhoXuat(0.0);
                    }
                    Double tkXuat = tk.getTonkhoXuat() - slOld + slNew;
                    Double tkTon = tk.getTonkhoTon() + slOld - slNew;
                    tk.setTonkhoXuat(tkXuat);
                    tk.setTonkhoTon(tkTon);
                    em.merge(ctx);
                    System.out.println("Update ct phieu xuat vien: " + ctx.getCtxuatbhxvMa());
                } else {
                    System.out.println("-----Insert ct phieu xuat bh xuat vien-----");
                    ctx.setCtxuatbhxvMa(null);
                    ctx.setPhieuxuatbhxvMa(phieuxuatBh);

                    tk.setTonkhoMa(null);
                    tk.setTonkhoXuat(ctx.getCtxuatbhxvSoluong());
                    tk.setTonkhoTra(Double.valueOf("0"));
                    tk.setTonkhoNhap(Double.valueOf("0"));
                    boolean rs = tkFacade.insertTonKho(tk);
                    System.out.println("insert TonKHo: " + tk.getTonkhoMa());
                    if (rs) {
                        ctx.setTonkhoMa(tk.getTonkhoMa());
                        em.persist(ctx);
                        System.out.println("Insert ct phieu xuat bh xuat vien: " + ctx.getCtxuatbhxvMa());
                    } else {
                        System.out.println("INSERT TONKHO FAIL ");
                    }
                }
            }
            result += phieuxuatBh.getPhieuxuatbhxvMa();
            System.out.println("result: " + result);

        } catch (Exception ex) {
            context.setRollbackOnly();
            result = "";
            ex.printStackTrace();
        }
        return result;
    }

    private void removeCtXuatBh(String listCtxBhMa, String pxBhMa) {
        System.out.println("Begin remove  removeCtXuatBhXuatVien(String listCtxBhxvMa, String pxBhM) method");
        Query q = em.createQuery("SELECT c FROM CtXuatKho c WHERE c.ctxuatkhoMa NOT IN (" + listCtxBhMa + ") AND c.phieuxuatkhoMa.phieuxuatkhoMa = :pxkMa");
        q.setParameter("pxkMa", pxBhMa);
        List<CtXuatBhXuatVien> listCtXuat = q.getResultList();
        if (listCtXuat != null) {
            for (CtXuatBhXuatVien ct : listCtXuat) {
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

    public List<PhieuXuatBhXuatVien> findBySoVaoVien(String soVaovien) {
        Query q = em.createQuery("select object(o) from PhieuXuatBhXuatVien as o where o.hsba.hsbaSovaovien = :sovaovien");
        q.setParameter("sovaovien", soVaovien);
        return q.getResultList();
    }

    public List<PhieuXuatBhXuatVien> findBySovaovien_Kho(String soVaovien, Integer khoMaso) {
        Query q = em.createQuery("select object(o) from PhieuXuatBhXuatVien as o where o.hsba.hsbaSovaovien = :sovaovien and o.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso");
        q.setParameter("sovaovien", soVaovien);
        q.setParameter("dmkhoaMaso", khoMaso);
        return q.getResultList();
    }

    public String createByThuocNoiTru(int KhoaMaSo, PhieuXuatBhXuatVien phieuxuatBh, List<ThuocNoiTru> listTNT, List<CtXuatBhXuatVien> listCtXuatBH) {
        if (listTNT == null) {
            return "";
        }
        try {
            //Kiem tra xem ton cua kho Chinh/Noitru co du de xuat cho BN hay khong
            for (CtXuatBhXuatVien obj : listCtXuatBH) {
                TonKho tk_old = tkFacade.getTonKhoHienTai(obj.getCtxuatbhxvMalk(), KhoaMaSo);
                if(tk_old != null){
                    Double tonHT = tk_old.getTonkhoTon();
                    if( tonHT < obj.getCtxuatbhxvSoluong() ){
                        return "SLTHET";
                    }
                }else{
                    return "SLTHET";
                }                
            }
            DmKhoa khoa = phieuxuatBh.getDmkhoaMaso();
            if (khoa != null) {
                Integer khoaMaso = khoa.getDmkhoaMaso();
                if (khoaMaso != null) {
                    khoa = em.find(DmKhoa.class, khoaMaso);
                    phieuxuatBh.setDmkhoaMaso(khoa);
                }
            }

            DtDmNhanVien nvCn = phieuxuatBh.getDtdmnhanvienCn();
            if (nvCn != null) {
                String nvMa = nvCn.getDtdmnhanvienMa();
                if (nvMa != null && !nvMa.equals("")) {
                    nvCn = em.find(DtDmNhanVien.class, nvCn.getDtdmnhanvienMaso());
                    phieuxuatBh.setDtdmnhanvienCn(nvCn);
                }
            }
            DtDmNhanVien nvPhat = phieuxuatBh.getDtdmnhanvienPhat();
            if (nvPhat != null) {
                String nvMa = nvPhat.getDtdmnhanvienMa();
                if (nvMa != null && !nvMa.equals("")) {
                    nvPhat = em.find(DtDmNhanVien.class, nvPhat.getDtdmnhanvienMaso());
                    phieuxuatBh.setDtdmnhanvienPhat(nvPhat);
                }
            }
            Hsba hsba = phieuxuatBh.getHsba();
            if (hsba != null) {
                String soVaovien = hsba.getHsbaSovaovien();
                if (soVaovien != null && !soVaovien.equals("")) {
                    hsba = em.find(Hsba.class, soVaovien);
                    phieuxuatBh.setHsba(hsba);
                }
            }

            String pxMa = phieuxuatBh.getPhieuxuatbhxvMa();
            if (pxMa == null || pxMa.equals(""))
            {
                pxMa = Utils.maPhieuXuatBaoHiemXuatVien(em);
                phieuxuatBh.setPhieuxuatbhxvMa(pxMa);
                em.persist(phieuxuatBh);
                pxMa = phieuxuatBh.getPhieuxuatbhxvMa();
                for (ThuocNoiTru obj : listTNT) {
                    obj = tntFacade.find(obj.getThuocnoitruMa());
                    if (obj != null) {
                        obj.setThuocnoitruMaphieu(pxMa);
                        obj.setThuocnoitruStatus("2");
                        getEm().merge(obj);
                    }
                }
            } 

            // tru vao kho BHYT            
            TonKhoFacade tkFacade = new TonKhoFacade();
            tkFacade.setEm(em);
            
            for (CtXuatBhXuatVien obj : listCtXuatBH) {                
                if (obj.getCtxuatbhxvMa() == null)
                {
                    String malk = obj.getCtxuatbhxvMalk();
                    TonKho tk_old = tkFacade.getTonKhoHienTai(malk, KhoaMaSo);

                    TonKho newtkxuat = (TonKho) BeanUtils.cloneBean(tk_old);
                    newtkxuat.setTonkhoMa(null);
                    newtkxuat.setTonkhoNhap(new Double(0));
                    newtkxuat.setTonkhoTra(new Double(0));
                    newtkxuat.setTonkhoXuat(obj.getCtxuatbhxvSoluong());
                    tkFacade.insertTonKho(newtkxuat);

                    obj.setPhieuxuatbhxvMa(phieuxuatBh);
                    obj.setTonkhoMa(newtkxuat.getTonkhoMa());
                    getEm().persist(obj);
                }
            }


            return pxMa == null ? "" : pxMa;
        } catch (Exception ex) {
            ex.printStackTrace();
            getContext().setRollbackOnly();
            return "";
        }


    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public SessionContext getContext() {
        return context;
    }

    public void setContext(SessionContext context) {
        this.context = context;
    }
}

