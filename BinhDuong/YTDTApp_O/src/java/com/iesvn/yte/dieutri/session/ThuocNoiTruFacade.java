/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
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
public class ThuocNoiTruFacade implements ThuocNoiTruFacadeLocal, ThuocNoiTruFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private TonKhoFacadeLocal tkDAO;
    @EJB
    private DieuTriUtilFacadeLocal dieuTriUtils;
    @EJB
    private ThuocDongYNoiTruFacadeLocal thuocDYFacade;

    public void create(ThuocNoiTru thuocNoiTru) {
        em.persist(thuocNoiTru);
    }

    public void edit(ThuocNoiTru thuocNoiTru) {
        em.merge(thuocNoiTru);
    }

    public void remove(ThuocNoiTru thuocNoiTru) {
        em.remove(em.merge(thuocNoiTru));
    }

    public ThuocNoiTru find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ThuocNoiTru.class, id);
    }

    public List<ThuocNoiTru> findAll() {
        return em.createQuery("select object(o) from ThuocNoiTru as o").getResultList();
    }

       public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLan(String soVaoVien, String khoaMa, Integer lan) {
        //System.out.println("-----findBySoVaoVienAndKhoaMa()-----");
        //System.out.println("-----soVaoVien: " + soVaoVien);
        //System.out.println("-----khoaMa: " + khoaMa);
        List<ThuocNoiTru> listTnt = null;
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa and  o.hsbaKhoa.hsbakhoaLan = :lan ");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
         q.setParameter("lan", lan);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }

       public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(String soVaoVien, String khoaMa, Integer lan, Date ngayNhanThuoc) {
        //System.out.println("-----findBySoVaoVienAndKhoaMaAndLanAndNgayNhan()-----");
        //System.out.println("-----soVaoVien: " + soVaoVien);
        //System.out.println("-----khoaMa: " + khoaMa);
        //System.out.println("-----ngayNhanThuoc: " + ngayNhanThuoc);
        //System.out.println("-----lan: " + lan);
        List<ThuocNoiTru> listTnt = null;
        soVaoVien = Utils.formatMa(em, soVaoVien);
        String sSQL = "Select o From ThuocNoiTru o Where ( o.thuocnoitruTutrucPdt = 1 OR ( o.thuocnoitruTutrucPdt = 0 and (o.thuocnoitruStatus = '2' OR o.thuocnoitruStatus = '3' OR o.thuocnoitruStatus = '5') ) ) and o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa ";
        if(ngayNhanThuoc != null)
            sSQL = sSQL + "and to_date(o.thuocnoitruNgaygio) = :ngayNhanThuoc ";
        if(lan != null){
            sSQL = sSQL + "and  o.hsbaKhoa.hsbakhoaLan = :lan ";
        }
        Query q = em.createQuery(sSQL);
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        if(lan != null){
            q.setParameter("lan", lan);
        }
        if(ngayNhanThuoc != null)
            q.setParameter("ngayNhanThuoc", ngayNhanThuoc);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    /*
     * Thanh add 05/10/2011
     * Date ok
     */
    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay) {
        //System.out.println("----- findBySoVaoVienAndKhoaMaAndLanAndNgay() -----");
        //System.out.println("----- soVaoVien : " + soVaoVien);
        //System.out.println("----- khoaMa    : " + khoaMa);
        //System.out.println("----- lan       : " + lan);
        //System.out.println("----- ngay      : " + ngay);
        List<ThuocNoiTru> listTnt = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien) and  (o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa) and  (o.hsbaKhoa.hsbakhoaLan = :lan) and  (o.thuocnoitruNgaygio between :ngay1 and :ngay2) ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndLanAndNgay() -----" + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay" + e);
        }
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndLanAndNgay() -----" + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay 2" + e);
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("lan", lan);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    
    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay) {
        //System.out.println("----- findBySoVaoVienAndKhoaMaAndTangAndNgay() -----");
        //System.out.println("----- soVaoVien : " + soVaoVien);
        //System.out.println("----- khoaMa    : " + khoaMa);
        //System.out.println("----- tangMaso  : " + tangMaso);
        //System.out.println("----- ngay      : " + ngay);
        List<ThuocNoiTru> listTnt = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien) and (o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa) and  (o.hsbaKhoa.dmtangMaso.dmtangMaso = :tangMaso) and  (o.thuocnoitruNgaygio between :ngay1 and :ngay2) ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndTangAndNgay() -----" + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay" + e);
        }
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndTangAndNgay() -----" + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay 2" + e);
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("tangMaso", tangMaso);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    
        /*
         * Thanh add 05/10/2011
         * Date ok
         */
       public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay) {
        //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay() -----");
        //System.out.println("----- soVaoVien : " + soVaoVien);
        //System.out.println("----- khoaMa    : " + khoaMa);
        //System.out.println("----- lan       : " + lan);
        //System.out.println("----- ngay      : " + ngay);
        List<ThuocNoiTruXuatVien> listTnt = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTruXuatVien o Where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien) and  (o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa) and  (o.hsbaKhoa.hsbakhoaLan = :lan) and  (o.thuocnoitruxvNgaygio between :ngay1 and :ngay2) ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay() -----" + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay" + e);
        }
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay() -----" + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay 2" + e);
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("lan", lan);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    
    public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer dmTangMaso, String ngay) {
        //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay() -----");
        //System.out.println("----- soVaoVien : " + soVaoVien);
        //System.out.println("----- khoaMa    : " + khoaMa);
        //System.out.println("----- lan       : " + lan);
        //System.out.println("----- ngay      : " + ngay);
        List<ThuocNoiTruXuatVien> listTnt = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTruXuatVien o Where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien) and  (o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa) and  (o.hsbaKhoa.dmtangMaso.dmtangMaso = :dmTangMaso) and  (o.thuocnoitruxvNgaygio between :ngay1 and :ngay2) ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay() -----" + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay" + e);
        }
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            //System.out.println("----- findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay() -----" + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay 2" + e);
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("dmTangMaso", dmTangMaso);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    
       /*
        * Thanh add 05/10/2011
        * Date ok
        */
   //Ly them code phuc vu cho hsba nao pha thai
    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndNgay(String soVaoVien, String khoaMa, String ngay) {
        List<ThuocNoiTru> listTnt = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien) and  (o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa) and  (o.thuocnoitruNgaygio between :ngay1 and :ngay2) ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndNgay() -----" + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay" + e);
            return null;
        }
        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            //System.out.println("----- findBySoVaoVienAndKhoaMaAndNgay() -----" + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay 2" + e);
            return null;
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
       //=============END===============

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        //System.out.println("-----findBySoVaoVienAndKhoaMa()-----");
        //System.out.println("-----soVaoVien: " + soVaoVien);
        //System.out.println("-----khoaMa: " + khoaMa);
        List<ThuocNoiTru> listTnt = null;
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbaKhoa.khoaMa.dmkhoaMa  like :khoaMa");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }

    public List<ThuocNoiTru> findBySoVaoVien(String soVaoVien) {
        List<ThuocNoiTru> listTnt = null;
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien ");
        q.setParameter("soVaoVien", soVaoVien);

        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }
    
    public List<ThuocNoiTru> findTNTXuatVienBySoVaoVien(String soVaoVien) {
        List<ThuocNoiTru> listTnt = null;
        soVaoVien = Utils.formatMa(em, soVaoVien);
        Query q = em.createQuery("Select o From ThuocNoiTru o Where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien = :soVaoVien And o.thuocnoitruTutrucPdt = 2 ");
        q.setParameter("soVaoVien", soVaoVien);

        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }

    public List<ThuocNoiTru> findByMaPhieu(String thuocnoitruMaphieu) {
        if (thuocnoitruMaphieu.length() < 6) {
            thuocnoitruMaphieu = Utils.formatMaPhieu(thuocnoitruMaphieu);
        }
        Query q = em.createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruMaphieu = :thuocnoitruMaphieu");
        q.setParameter("thuocnoitruMaphieu", thuocnoitruMaphieu);
        return q.getResultList();
    }

    public List<ThuocNoiTru> findByHsbaAndKhoaAndTuTruc(String soVaoVien, String khoaMa) {
        soVaoVien = Utils.formatMa(em, soVaoVien);
        List<ThuocNoiTru> listTnt = null;
        Query q = em.createQuery("Select t From ThuocNoiTru t Where t.hsbaSovaovien.hsbaSovaovien = :soVaoVien And t.thuocnoitruKhoa.dmkhoaMa = :khoaMa And t.thuocnoitruTutrucPdt = 1");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }

    public List<ThuocNoiTru> findByHsbaKhoa(Integer hsbaKhoaMaso) {
        List<ThuocNoiTru> listTnt = null;
        Query q = em.createQuery("Select t From ThuocNoiTru t Where t.hsbaKhoa.hsbakhoaMaso = :hsbaKhoaMaso And t.thuocnoitruTutrucPdt = 2");
        q.setParameter("hsbaKhoaMaso", hsbaKhoaMaso);
        try {
            listTnt = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTnt;
    }

    public Boolean benhNhanTraThuoc(List<ThuocNoiTru> listTnt) {
        //System.out.println("-----benhNhanTraThuoc()-----");
        Boolean result = false;
        for (ThuocNoiTru tnt : listTnt) {
            if (tnt.getThuocnoitruTra() != null) {
                int tutrucPdt = tnt.getThuocnoitruTutrucPdt();
                if (tutrucPdt == 1) {
                    try {
                        //System.out.println("-----tra ve tu truc");
                        TonKho tkHt = tkDAO.getTonKhoHienTai(tnt.getThuocnoitruMalk(), tnt.getThuocnoitruKhoa().getDmkhoaMaso());
                        TonKho tkNew = (TonKho) BeanUtils.cloneBean(tkHt);
                        tkNew.setTonkhoMa(null);
                        tkNew.setTonkhoNhap(null);
                        tkNew.setTonkhoXuat(null);
                        tkNew.setTonkhoTra(tnt.getThuocnoitruTra());
                        tkNew.setTonkhoNgaygiocn(new Date());
                        tkDAO.insertTonKho(tkNew);
                        Double soluong = tnt.getThuocnoitruSoluong();
                        Double tra = tnt.getThuocnoitruTra();
                        tnt.setThuocnoitruSoluong(soluong - tra);
                        tnt.setThuocnoitruTra(new Double("0"));
                        tnt.setThuocnoitruThanhtien(Utils.roundDoubleNumber(tnt.getThuocnoitruSoluong() * tnt.getThuocnoitruDongiatt()));
                        tnt.setThuocnoitruStatus("5");//Da tra thuoc vao tu truc
                        em.merge(tnt);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (tutrucPdt == 0){
                    //System.out.println("-----tra theo phieu du tru");
                    tnt.setThuocnoitruStatus("3");//Trang thai tra thuoc cua BN - Tho add
                    tnt.setThuocnoitruThanhtien(Utils.roundDoubleNumber((tnt.getThuocnoitruSoluong()-tnt.getThuocnoitruTra()) * tnt.getThuocnoitruDongiatt()));
                    em.merge(tnt);
                }
            }

        }
        result = true;
        return result;
    }

    public String delHuyTNT(ThuocNoiTru tnt) {
        String result = "";
        List<ThuocNoiTru> listthuocnt = this.findByMaPhieu(tnt.getThuocnoitruMaphieu());
        if (listthuocnt != null) {
            for (ThuocNoiTru thuocnt : listthuocnt) {
                //tnt.setThuocnoitruStt(stt);
                //tnt.setThuocnoitruMaphieu(tntMa);
                //System.out.println("starting cacul");
//                this.Sub(thuocnt);
                //System.out.println("finish cacul");
                em.remove(thuocnt);

            }
        }
        result = tnt.getThuocnoitruMaphieu();
        return result;
    }

    public String createThuocNoiTru(List<ThuocNoiTru> listThuoc, List<ThuocNoiTru> listThuocDel, HashMap<Integer,List> hsmListThuocDYNoiTru) {
        //System.out.println("-----createThuocNoiTru()-----");
        String result = "";
        if(hsmListThuocDYNoiTru != null && hsmListThuocDYNoiTru.size() > 0){
            java.util.Set set = hsmListThuocDYNoiTru.entrySet();
            Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	List listThuocDY = (List)me.getValue();
	    	ThuocDongYNoiTru thuocDY = (ThuocDongYNoiTru)listThuocDY.get(0);
		Boolean isSavedDB = (Boolean)listThuocDY.get(1);
                if(isSavedDB.booleanValue()){
                    //kiem tra neu thuocDY.getThuocdongyMaso() co ton tai trong thuoc phong kham cua list listTpk thi cho update, khong thi delete
                    boolean founded = false;
                    if(listThuoc != null){
                        for(ThuocNoiTru tnt : listThuoc){
                            if(tnt.getDmbaithuocMaso() != null && tnt.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                                founded = true;
                                break;
                            }
                        }
                    }

                    if(founded){
                        //true: update lai Thuoc Dong Y ngoai tru
                        getEm().merge(thuocDY);
                    }else{
                        //Xoa thuoc dong y noi tru ra khoi DB
                        thuocDY = thuocDYFacade.find(thuocDY.getThuocdongyMaso());
                        getEm().remove(thuocDY);
                    }

                }else{
                    //false: insert vao Thuoc dong y noi tru
                    getEm().persist(thuocDY);
                    //update lai thuoc noi tru bang thuocDY.getThuocdongyMaso() where voi thuocDYMaso la bai thuoc
                    for(int j = 0; j<listThuoc.size(); j++){
                        ThuocNoiTru tnt = listThuoc.get(j);
                        if(tnt.getDmbaithuocMaso() != null && tnt.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                            tnt.setThuocdongyNoiTru(thuocDY);
                            listThuoc.set(j,tnt);
                        }
                    }
                }
            }
        }
        try {
            Integer thuocDYMasoDel = 0;
            if(listThuocDel.size()>0){
                int i = 0;
                for (ThuocNoiTru tnt : listThuocDel) {
                    if (tnt.getThuocnoitruMa() != null) {
                        ThuocNoiTru tntDelete = em.find(ThuocNoiTru.class, tnt.getThuocnoitruMa());
                        if (tntDelete != null){
                            em.remove(tntDelete);
                            /*luu ma thuoc dong y cua cac tnt bi xoa: 1 bai thuoc chi thuoc 1 toa nen chi can lay 1 lan thuoc dong y ma so
                              neu kiem tra tren cac thuoc noi tru con lai ma k co ma thuoc dong y noi tru
                              thi xoa ma nay trong bang thuoc noi tru dong y
                              */
                            if(tntDelete.getThuocdongyNoiTru() != null){
                                thuocDYMasoDel = tntDelete.getThuocdongyNoiTru().getThuocdongyMaso();
                                String sSQL = "Select t From ThuocNoiTru t Where t.thuocdongyNoiTru.thuocdongyMaso = :thuocdongyMaso";
                                Query q = getEm().createQuery(sSQL);
                                q.setParameter("thuocdongyMaso", thuocDYMasoDel);
                                if(q.getResultList().size()== 0){
                                    ThuocDongYNoiTru thuocDYNT = thuocDYFacade.find(thuocDYMasoDel);
                                    if(thuocDYNT != null){
                                        em.remove(thuocDYNT);
                                    }
                                }
                            }
                        }
                    }
                    i++;
                }
            }
            if(listThuoc.size()>0){
                for (ThuocNoiTru tnt: listThuoc) {
                    if (tnt.getThuocnoitruMa() != null) {
                        if(tnt.getThuocnoitruMalk() == null || tnt.getThuocnoitruMathuoc() == null
                           || !tnt.getThuocnoitruMalk().startsWith(tnt.getThuocnoitruMathuoc(true).getDmthuocMa()) ){
                            //System.out.println(" ##### Ma LK va Ma thuoc khong trung nhau ");
                            continue;
                        }
                        em.merge(tnt);
                    
                    } else {
                        
                        if(tnt.getThuocnoitruMalk() == null || tnt.getThuocnoitruMathuoc() == null
                           || !tnt.getThuocnoitruMalk().startsWith(tnt.getThuocnoitruMathuoc(true).getDmthuocMa()) ){
                            //System.out.println(" ##### Ma LK va Ma thuoc khong trung nhau ");
                            continue;
                        }
                        em.persist(tnt);
                        
                        if (tnt.getThuocnoitruTutrucPdt() == 1) {
                            TonKho tk = tkDAO.getTonKhoHienTai(tnt.getThuocnoitruMalk(),
                                    tnt.getHsbaKhoa().getKhoaMa().getDmkhoaMaso());
                            TonKho tkNew;
                            tkNew = (TonKho) BeanUtils.cloneBean(tk);
                            tkNew.setTonkhoMa(null);
                            tkNew.setTonkhoNhap(null);
                            tkNew.setTonkhoXuat(tnt.getThuocnoitruSoluong());
                            tkNew.setTonkhoTra(null);
                            tkNew.setTonkhoNgaygiocn(new Date());
                            if (!tkDAO.insertTonKho(tkNew)) {
                                context.setRollbackOnly();
                            }
                        }
                        //System.out.println("insert them thanh cong chi tiet thuoc " + tnt.getThuocnoitruMa());
                    }
                }
            }
            /*kiem tra tren cac thuoc noi tru con lai ma k co ma thuoc dong y noi tru
              thi xoa ma nay trong bang thuoc noi tru dong y
            */
            Query q = null;
            if (thuocDYMasoDel != 0) {
                q = getEm().createQuery("Select t From ThuocNoiTru t Where t.thuocdongyNoiTru.thuocdongyMaso = :thuocDYMaso");
                q.setParameter("thuocDYMaso", thuocDYMasoDel);
                List<ThuocNoiTru> listThuocDY = q.getResultList();
                if (listThuocDY == null || listThuocDY.size() == 0) {
                    ThuocDongYNoiTru thuocDYDelete = em.find(ThuocDongYNoiTru.class, thuocDYMasoDel);
                    if(thuocDYDelete != null){
                        getEm().remove(thuocDYDelete);
                    }                    
                }
            }
            result = "1";

        } catch (Exception ex) {
            result = "";
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        //System.out.println("result " + result);
        return result;

    }

    public String createThuocNoiTruXuatVien(List<ThuocNoiTruXuatVien> listThuoc, List<ThuocNoiTruXuatVien> listThuocDel) {
        //System.out.println("-----createThuocNoiTruXuatVien()-----");
        String result = "";
        try {
            if(listThuocDel.size()>0){
                for (ThuocNoiTruXuatVien tnt : listThuocDel) {
                    if (tnt.getThuocnoitruxvMa() != null) {                        
                        ThuocNoiTruXuatVien tntDelete = em.find(ThuocNoiTruXuatVien.class, tnt.getThuocnoitruxvMa());
                        //System.out.println("-----Delete thuoc noi tru xuat vien: " + tnt.getThuocnoitruxvMa() + tntDelete);
                        if (tntDelete != null){
                             em.remove(tntDelete);
                        }
                    }
                }
            }
            if(listThuoc.size()>0){
                for (ThuocNoiTruXuatVien tnt : listThuoc) {
                    if (tnt.getThuocnoitruxvMa() != null) {
                        em.merge(tnt);
                    } else {
                        em.persist(tnt);
                    }

                }
            }
            result = "1";

        } catch (Exception ex) {
            result = "";
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        //System.out.println("result " + result);
        return result;
    }

    public List<ThuocNoiTru> findTNTByKhoaMaSoAndMaLK(Integer khoaMaSo, String maLK) {
        Query q = em.createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruKhoa.dmkhoaMaso =  :khoaMaSo and o.thuocnoitruMalk like :maLK  ");
        q.setParameter("khoaMaSo", khoaMaSo);
        q.setParameter("maLK", maLK);
        return q.getResultList();
    }

    public List<ThuocNoiTru> findTNTByMaPDT(Integer khoaMaSo, String maphieuDT) {
        Query q = em.createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruKhoa.dmkhoaMaso =  :khoaMaSo and o.thuocnoitruMaPhieuDT =  :thuocnoitruMaPhieuDT");
        q.setParameter("khoaMaSo", khoaMaSo);
        q.setParameter("thuocnoitruMaPhieuDT", maphieuDT);
        return q.getResultList();
    }    
	
    //Tho add tim theo loai thuoc
//    public List<ThuocNoiTru> findDanhSachTNTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso) {
//        System.out.println("---Begin findDanhSachTNTDuTruLinh()---");
//        String sSQL = "select object(tnt) from ThuocNoiTru tnt, DmThuoc t, DmPhanNhomThuoc pnt, DmPhanLoaiThuoc plt, DmLoaiThuoc lt, HsbaKhoa k " +
//                      "where ( tnt.thuocnoitruNgaygio between :tuNgay and :denNgay ) " +
//                      "and tnt.thuocnoitruMathuoc.dmthuocMaso = t.dmthuocMaso " +
//                      "and t.dmphannhomthuocMaso.dmphannhomthuocMaso = pnt.dmphannhomthuocMaso " +
//                      "and t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso " +
//                      "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso " +
//                      "and (:doituongMaso = 0 or tnt.dmdoituongMaso.dmdoituongMaso = :doituongMaso) " +
//                      "and tnt.thuocnoitruKhoa.dmkhoaMaso = :dmKhoaMaso " +
//                      "and tnt.thuocnoitruKho.dmkhoaMaso = :dmKhoMaso " +
//                      "and tnt.hsbaKhoa.hsbakhoaMaso = k.hsbakhoaMaso " +
//                      "and (:dmtangMaso = 0 or k.dmtangMaso.dmtangMaso = :dmtangMaso) " +
//                      "and ( tnt.thuocnoitruMaPhieuDT IS NULL or trim(tnt.thuocnoitruMaPhieuDT) IS NULL) " +
//                      "and ( tnt.thuocnoitruTutrucPdt IS NULL OR tnt.thuocnoitruTutrucPdt = 0 ) ";
//        String loaithuocMa = "";
//        String phannhomthuocMa = "";
//        String phanloaithuocMa ="";
//        String filterByDVT ="";
//        if (!loaiMa.equals("")){
//            /*Format cua loaiMa: loaiMa chac chan khac ""
//                Tan duoc: thuoc thuong -> Format la: TD-TT
//                Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
//                Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
//                Tan duoc: huong than   -> Format la: TD-HT
//                Tan duoc: gay nghien   -> Format la: TD-GN
//                Y dung cu:             -> Format la YC
//                Hoa chat               -> Format la HC
//                Dong Y: Thuoc thanh pham -> Format la DY-DD
//                Dong Y: Thuoc vi         -> Format la DY-DD2
//                Thuoc chuong trinh     -> Format la CT
//            */
//            if(loaiMa.indexOf("-")>0){
//                loaithuocMa = loaiMa.substring(0,loaiMa.indexOf("-")).trim();//TD
//                String phannhomMaDVT = loaiMa.substring(loaiMa.indexOf("-")+1).trim();//TT;IN (35, 36, 37, 44)
//                if(loaithuocMa.equals("TD")){
//                    if(phannhomMaDVT.indexOf(";") > 0){
//                        phannhomthuocMa = phannhomMaDVT.substring(0,phannhomMaDVT.indexOf(";"));//TT
//                        filterByDVT = phannhomMaDVT.substring(phannhomMaDVT.indexOf(";")+1);//IN (35, 36, 37, 44)
//                        sSQL += "and t.dmdonvitinhMaso.dmdonvitinhMaso " + filterByDVT + " ";
//                    }else{
//                        phannhomthuocMa = phannhomMaDVT;
//                    }
//                    sSQL += "and pnt.dmphannhomthuocMa = :dmphannhomthuocMa ";
//                }else if(loaithuocMa.equals("DY")){
//                    phanloaithuocMa = phannhomMaDVT;//DD, DD2
//                    sSQL += "and plt.dmphanloaithuocMa = :dmphanloaithuocMa ";
//                }
//                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
//            }else{
//                loaithuocMa =  loaiMa;
//                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
//            }
//             sSQL +="order by tnt.thuocnoitruMathuoc.dmthuocTen";
//            System.out.println("sSQL = "+ sSQL);
//            Query q = em.createQuery(sSQL);
//            q.setParameter("doituongMaso", doituongMaso);
//            q.setParameter("dmKhoaMaso", dmKhoaMaso);
//            q.setParameter("dmKhoMaso", dmKhoMaso);
//            q.setParameter("dmtangMaso", dmTangMaso);
//            q.setParameter("tuNgay", tuNgay);
//            q.setParameter("denNgay", denNgay);
//
//            if(loaiMa.indexOf("-")>0){
//                if(loaithuocMa.equals("TD")){
//                    q.setParameter("dmphannhomthuocMa", phannhomthuocMa);
//                }else if(loaithuocMa.equals("DY")){
//                    q.setParameter("dmphanloaithuocMa", phanloaithuocMa);
//                }
//                q.setParameter("dmloaithuocMa", loaithuocMa);
//            }else{
//                q.setParameter("dmloaithuocMa", loaithuocMa);
//            }
//
//            List<ThuocNoiTru> returnList = q.getResultList();
//            // System.out.println("End get list Thuoc Noi Tru:" + returnList);
//            if (returnList != null && returnList.size() > 0) {
//                System.out.println("---End findDanhSachTNTDuTruLinh()---");
//                return returnList;
//            }
//        }
//        return null;
//    }
    
    // 20120607 bao.ttc: viet lai ham dung Native Query
    public List<ThuocNoiTru> findDanhSachTNTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso) {
        //System.out.println("---Begin findDanhSachTNTDuTruLinh()--- Native Query");
        String sSQL = "select tnt.* from thuoc_noi_tru tnt, dm_thuoc t, dm_phan_nhom_thuoc pnt, dm_phan_loai_thuoc plt, dm_loai_thuoc lt, hsba_khoa k " +
                      "where ( tnt.thuocnoitru_ngaygio between to_date(:sTuNgay, 'dd/MM/yyyy hh24:mi:ss') and to_date(:sDenNgay, 'dd/MM/yyyy hh24:mi:ss') ) " +
                      "and tnt.thuocnoitru_mathuoc = t.dmthuoc_maso " +
                      "and t.dmphannhomthuoc_maso = pnt.dmphannhomthuoc_maso " +
                      "and t.dmphanloaithuoc_maso = plt.dmphanloaithuoc_maso " +
                      "and plt.dmloaithuoc_maso = lt.dmloaithuoc_maso " +
                      "and (:doituongMaso = 0 or tnt.dmdoituong_maso = :doituongMaso) " +
                      "and tnt.thuocnoitru_khoa = :dmKhoaMaso " +
                      "and tnt.thuocnoitru_kho = :dmKhoMaso " +
                      "and tnt.hsba_khoa = k.hsbakhoa_maso " +
                      "and (:dmtangMaso = 0 or k.dmtang_maso = :dmtangMaso) " +
                      "and tnt.thuocnoitru_maphieudt IS NULL " +
                      "and ( tnt.thuocnoitru_tutruc_pdt IS NULL OR tnt.thuocnoitru_tutruc_pdt = 0 ) ";
                       
        String loaithuocMa;
        String phannhomthuocMa = "";
        String phanloaithuocMa ="";
        String filterByDVT;
        if (!loaiMa.equals("")){
            /*Format cua loaiMa: loaiMa chac chan khac ""
                Tan duoc: thuoc thuong -> Format la: TD-TT
                Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
                Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
                Tan duoc: huong than   -> Format la: TD-HT
                Tan duoc: gay nghien   -> Format la: TD-GN
                Y dung cu:             -> Format la YC
                Hoa chat               -> Format la HC
                Dong Y: Thuoc thanh pham -> Format la DY-DD
                Dong Y: Thuoc vi         -> Format la DY-DD2
                Thuoc chuong trinh     -> Format la CT
            */
            if(loaiMa.indexOf("-")>0){
                loaithuocMa = loaiMa.substring(0,loaiMa.indexOf("-")).trim();//TD
                String phannhomMaDVT = loaiMa.substring(loaiMa.indexOf("-")+1).trim();//TT;IN (35, 36, 37, 44)
                if(loaithuocMa.equals("TD")){
                    if(phannhomMaDVT.indexOf(";") > 0){
                        phannhomthuocMa = phannhomMaDVT.substring(0,phannhomMaDVT.indexOf(";"));//TT
                        filterByDVT = phannhomMaDVT.substring(phannhomMaDVT.indexOf(";")+1);//IN (35, 36, 37, 44)
                        sSQL += "and t.dmdonvitinh_maso " + filterByDVT + " ";
                    }else{
                        phannhomthuocMa = phannhomMaDVT;
                    }
                    sSQL += "and pnt.dmphannhomthuoc_ma = :dmphannhomthuocMa ";
                }else if(loaithuocMa.equals("DY")){
                    phanloaithuocMa = phannhomMaDVT;//DD, DD2
                    sSQL += "and plt.dmphanloaithuoc_ma = :dmphanloaithuocMa ";
                }
                sSQL += "and lt.dmloaithuoc_ma = :dmloaithuocMa ";
            }else{
                loaithuocMa =  loaiMa;
                sSQL += "and lt.dmloaithuoc_ma = :dmloaithuocMa ";
            }
             sSQL +="order by t.dmthuoc_ten ";
             
            System.out.println("sSQL = "+ sSQL);
            Query q = em.createNativeQuery(sSQL, ThuocNoiTru.class);
            
            q.setParameter("doituongMaso", doituongMaso);
            q.setParameter("dmKhoaMaso", dmKhoaMaso);
            q.setParameter("dmKhoMaso", dmKhoMaso);
            q.setParameter("dmtangMaso", dmTangMaso);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String sTuNgay;
            String sDenNgay;
            sTuNgay = sdf.format(tuNgay);
            sDenNgay = sdf.format(denNgay);
            
            q.setParameter("sTuNgay", sTuNgay);
            q.setParameter("sDenNgay", sDenNgay);

            if(loaiMa.indexOf("-")>0){
                if(loaithuocMa.equals("TD")){
                    q.setParameter("dmphannhomthuocMa", phannhomthuocMa);
                }else if(loaithuocMa.equals("DY")){
                    q.setParameter("dmphanloaithuocMa", phanloaithuocMa);
                }
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }else{
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }
            
            //System.out.println("---111 findDanhSachTNTDuTruLinh()--- Native Query");
            List<ThuocNoiTru> returnList = q.getResultList();
            //System.out.println("---222 findDanhSachTNTDuTruLinh()--- Native Query");
            
            if (returnList != null && returnList.size() > 0) {
                System.out.println("---End findDanhSachTNTDuTruLinh()--- Native Query");
                return returnList;
            }
        }
        return null;
    }
    
    // chua chuyen syntact
    public List<ThuocNoiTru> findDanhSachTNTForLapPhieuDuTruTra(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso) {
        //System.out.println("---Begin findDanhSachTNTForLapPhieuDuTruTra()---");
        String sSQL = "select object(tnt) from ThuocNoiTru tnt, DmThuoc t, DmPhanNhomThuoc pnt, DmPhanLoaiThuoc plt, DmLoaiThuoc lt, HsbaKhoa k " +
                      "where ( tnt.thuocnoitruNgaygio between :tuNgay and :denNgay ) " +
                      "and tnt.thuocnoitruKhoa.dmkhoaMaso = :dmKhoaMaso " +
                      "and tnt.thuocnoitruKho.dmkhoaMaso = :dmKhoMaso " +
                      "and ( tnt.thuocnoitruTra >0 ) " +
                      "and ( tnt.thuocnoitruTutrucPdt = 0 ) " +
                      "and ( tnt.thuocnoitruMaphieupdttra is null or trim(tnt.thuocnoitruMaphieupdttra) is null ) " +
                      "and ( tnt.thuocnoitruMaPhieuDT is not null and trim(tnt.thuocnoitruMaPhieuDT) is not null ) " +
                      "and (:doituongMaso = 0 or tnt.dmdoituongMaso.dmdoituongMaso = :doituongMaso) " +
                      "and tnt.thuocnoitruMathuoc.dmthuocMaso = t.dmthuocMaso " +
                      "and t.dmphannhomthuocMaso.dmphannhomthuocMaso = pnt.dmphannhomthuocMaso " +
                      "and t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso " +
                      "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso " +
                      "and tnt.hsbaKhoa.hsbakhoaMaso = k.hsbakhoaMaso " +
                      "and (:dmtangMaso = 0 or k.dmtangMaso.dmtangMaso = :dmtangMaso) ";

        String loaithuocMa = "";
        String phannhomthuocMa = "";
		String phanloaithuocMa = "";
        String filterByDVT ="";
        if (!loaiMa.equals("")){
            /*Format cua loaiMa: loaiMa chac chan khac ""
                Tan duoc: thuoc thuong -> Format la: TD-TT
                Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
                Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
                Tan duoc: huong than   -> Format la: TD-HT
                Tan duoc: gay nghien   -> Format la: TD-GN
                Y dung cu:             -> Format la YC
                Hoa chat               -> Format la HC
                Dong Y - Thanh pham    -> Format la DY-DD
             *  Dong Y - Thuoc vi      -> Format la DY-DD2
                Thuoc chuong trinh     -> Format la CT
            */
            if(loaiMa.indexOf("-")>0){
                loaithuocMa = loaiMa.substring(0,loaiMa.indexOf("-")).trim();//TD
                String phannhomMaDVT = loaiMa.substring(loaiMa.indexOf("-")+1).trim();//TT;IN (35, 36, 37, 44)
                if(loaithuocMa.equals("TD")){
                    if(phannhomMaDVT.indexOf(";") > 0){
                        phannhomthuocMa = phannhomMaDVT.substring(0,phannhomMaDVT.indexOf(";"));//TT
                        filterByDVT = phannhomMaDVT.substring(phannhomMaDVT.indexOf(";")+1);//IN (35, 36, 37, 44)
                        sSQL += "and t.dmdonvitinhMaso.dmdonvitinhMaso " + filterByDVT + " ";
                    }else{

                        phannhomthuocMa = phannhomMaDVT;
                    }
                    sSQL += "and pnt.dmphannhomthuocMa = :dmphannhomthuocMa ";
                }else if(loaithuocMa.equals("DY")){
                    phanloaithuocMa = phannhomMaDVT;//DD, DD2
                    sSQL += "and plt.dmphanloaithuocMa = :dmphanloaithuocMa ";
                }
                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
            }else{
                loaithuocMa =  loaiMa;
                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
            }
            //System.out.println("dmphannhomthuocMa: " + phannhomthuocMa);
            //System.out.println("dmloaithuocMa: " + loaithuocMa);
            sSQL +="order by tnt.thuocnoitruMathuoc.dmthuocTen";
            //System.out.println("sSQL = "+ sSQL);
            Query q = em.createQuery(sSQL);
            q.setParameter("doituongMaso", doituongMaso);
            q.setParameter("dmKhoaMaso", dmKhoaMaso);
            q.setParameter("dmKhoMaso", dmKhoMaso);
            q.setParameter("dmtangMaso", dmTangMaso);
            q.setParameter("tuNgay", tuNgay);
            q.setParameter("denNgay", denNgay);

            if(loaiMa.indexOf("-")>0){
                if(loaithuocMa.equals("TD")){
                    q.setParameter("dmphannhomthuocMa", phannhomthuocMa);
                }else if(loaithuocMa.equals("DY")){
                    q.setParameter("dmphanloaithuocMa", phanloaithuocMa);
                }
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }else{
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }
            List<ThuocNoiTru> returnList = q.getResultList();

            // System.out.println("End get list Thuoc Noi Tru:" + returnList);
            if (returnList != null && returnList.size() > 0) {
                //System.out.println("Returnlist size = " + returnList.size());
                return returnList;
            }
        }
        return null;
    }

    public int getStt() {
        int stt = 0;
        try {
            Query q = em.createQuery("Select MAX(tnt.thuocnoitruStt) from ThuocNoiTru as tnt");
            stt = ((Number) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }

        return stt;
    }

    public void removeAll(String tntMa) {

        Query q = em.createQuery("select object(o) from ThuocNoiTru as o Where o.thuocnoitruMaphieu = :thuocnoitruMaphieu");
        q.setParameter("thuocnoitruMaphieu", tntMa);
        List<ThuocNoiTru> list = q.getResultList();
        for (ThuocNoiTru tnt : list) {
            em.remove(tnt);
        }

    }

    public String findMaPhieu(List<ThuocNoiTru> listThuoc) {
        String result = "";
        for (ThuocNoiTru tnt : listThuoc) {
            if (tnt.getThuocnoitruMaphieu() != null) {
                result = tnt.getThuocnoitruMaphieu();
            }
        }
        return result;
    }

    public int findStt(List<ThuocNoiTru> listThuoc) {
        int result = 0;
        for (ThuocNoiTru tnt : listThuoc) {
            if (tnt.getThuocnoitruStt() != null) {
                result = tnt.getThuocnoitruStt();
            }
        }
        return result;
    }

    public void removeItem(String listtntMa, String tntMa) {
        //System.out.println("-----removeItem(" + listtntMa + ", " + tntMa + ")-----");
        Query q = em.createQuery("SELECT c FROM ThuocNoiTru c WHERE c.thuocnoitruMa NOT IN (" + listtntMa + ") AND c.thuocnoitruMaphieu like :thuocnoitruMaphieu");
        q.setParameter("thuocnoitruMaphieu", tntMa);
        List<ThuocNoiTru> listTNT = q.getResultList();
        for (ThuocNoiTru tnt : listTNT) {
            //System.out.println("-----Remove thuoc: " + tnt.getThuocnoitruMa());
            em.remove(tnt);
        }
    }
    // chua syntact
    //Tho add tim theo loai thuoc, khoa, doi tuong benh nhan
    public List<ThuocNoiTru> findDanhSachTNTDuTruLinhTuTruc(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMaso, Integer dmTangMaso) {
        //System.out.println("---Begin findDanhSachTNTDuTruLinhTuTruc()---");
        //System.out.println("tuNgay: " + tuNgay);
        //System.out.println("denNgay: " + denNgay);
        //System.out.println("doituongMaso: " + doituongMaso);
        //System.out.println("dmKhoaMaso: " + dmKhoaMaso);
        //System.out.println("loaiMa: " + loaiMa);
        String sSQL = "select object(tnt) from ThuocNoiTru tnt, DmThuoc t, DmPhanNhomThuoc pnt, DmPhanLoaiThuoc plt, DmLoaiThuoc lt, HsbaKhoa k " +
                      "where tnt.thuocnoitruMathuoc.dmthuocMaso = t.dmthuocMaso " +
                      "and t.dmphannhomthuocMaso.dmphannhomthuocMaso = pnt.dmphannhomthuocMaso " +
                      "and t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso " +
                      "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso " +
                      "and (:doituongMaso = 0 or tnt.dmdoituongMaso.dmdoituongMaso = :doituongMaso) " +
                      "and tnt.thuocnoitruKhoa.dmkhoaMaso = :dmKhoaMaso " +
                      "and tnt.hsbaKhoa.hsbakhoaMaso = k.hsbakhoaMaso " +
                      "and (:dmtangMaso = 0 or k.dmtangMaso.dmtangMaso = :dmtangMaso) " +
                      "and tnt.thuocnoitruTutrucPdt = 1 " +
                      "and tnt.thuocnoitruMaPhieuDT IS NULL " +
                      "and ( tnt.thuocnoitruNgaygio between :tuNgay and :denNgay ) ";
        String loaithuocMa = "";
        String phannhomthuocMa = "";
	String phanloaithuocMa = "";
        String filterByDVT ="";
        /*Format cua loaiMa: loaiMa chac chan khac ""
                Tan duoc: thuoc thuong -> Format la: TD-TT
                Tan duoc: thuoc vien   -> Format la: TD-TT;IN (35, 36, 37, 44)
                Tan duoc: thuoc khac   -> Format la: TD-TT;NOT IN (35, 36, 37, 44)
                Tan duoc: huong than   -> Format la: TD-HT
                Tan duoc: gay nghien   -> Format la: TD-GN
                Y dung cu:             -> Format la YC
                Hoa chat               -> Format la HC
                Dong Y                 -> Format la DY
                Thuoc chuong trinh     -> Format la CT
        */
        if (!loaiMa.equals("")){
            if(loaiMa.indexOf("-")>0){
                loaithuocMa = loaiMa.substring(0,loaiMa.indexOf("-")).trim();//TD
                String phannhomMaDVT = loaiMa.substring(loaiMa.indexOf("-")+1).trim();//TT;IN (35, 36, 37, 44)
                 if(loaithuocMa.equals("TD")){
                    if(phannhomMaDVT.indexOf(";") > 0){
                        phannhomthuocMa = phannhomMaDVT.substring(0,phannhomMaDVT.indexOf(";"));//TT
                        filterByDVT = phannhomMaDVT.substring(phannhomMaDVT.indexOf(";")+1);//IN (35, 36, 37, 44)
                        sSQL += "and t.dmdonvitinhMaso.dmdonvitinhMaso " + filterByDVT + " ";
                    }else{
                        phannhomthuocMa = phannhomMaDVT;
                    }
                    sSQL += "and pnt.dmphannhomthuocMa = :dmphannhomthuocMa ";
                }else if(loaithuocMa.equals("DY")){
                    phanloaithuocMa = phannhomMaDVT;//DD, DD2
                    sSQL += "and plt.dmphanloaithuocMa = :dmphanloaithuocMa ";
                }
                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
            }else{
                loaithuocMa =  loaiMa;
                sSQL += "and lt.dmloaithuocMa = :dmloaithuocMa ";
            }
            //System.out.println("dmphannhomthuocMa: " + phannhomthuocMa);
            //System.out.println("dmloaithuocMa: " + loaithuocMa);
            sSQL +="order by tnt.thuocnoitruMathuoc.dmthuocTen";
            //System.out.println("sSQL = "+ sSQL);
            Query q = em.createQuery(sSQL);
            q.setParameter("doituongMaso", doituongMaso);
            q.setParameter("dmKhoaMaso", dmKhoaMaso);
            q.setParameter("dmtangMaso", dmTangMaso);
            q.setParameter("tuNgay", tuNgay);
            q.setParameter("denNgay", denNgay);

            if(loaiMa.indexOf("-")>0){
                if(loaithuocMa.equals("TD")){
                    q.setParameter("dmphannhomthuocMa", phannhomthuocMa);
                }else if(loaithuocMa.equals("DY")){
                    q.setParameter("dmphanloaithuocMa", phanloaithuocMa);
                }
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }else{
                q.setParameter("dmloaithuocMa", loaithuocMa);
            }
            List<ThuocNoiTru> returnList = q.getResultList();

            // System.out.println("End get list Thuoc Noi Tru:" + returnList);
            if (returnList != null && returnList.size() > 0) {
                //System.out.println("Returnlist size = " + returnList.size());
                return returnList;
            }
            //System.out.println("---End findDanhSachTNTDuTruLinhTuTruc()---");
        }
        return null;
    }
    
    boolean findDmPhanLoai(String dmphanloaiMa, String dtdmloaiMa, DmPhanLoaiThuoc dmpl) {
        //System.out.println("Begin findDmphanloai");
        boolean result = false;

        if (dmpl.getDmphanloaithuocMa().equals(dmphanloaiMa)) {
            result = true;
        }

        //System.out.println("End findDmphanloai : " + result);
        return result;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ThuocNoiTru> checkThuocNoiTruTruocNgayVaoVien(String sovaovien) {
        //System.out.println("---checkThuocNoiTruTruocNgayVaoVien---");
        try {
            sovaovien = Utils.formatMa(em, sovaovien);
            String sSQL = "select tnt.* from thuoc_noi_tru tnt, hsba_khoa hsbakhoa, hsba " +
                " where tnt.hsba_khoa = hsbakhoa.hsbakhoa_maso " +
                " AND hsbakhoa.hsba_sovaovien = hsba.hsba_sovaovien " +
                " AND hsba.hsba_sovaovien = :sovaovien " +
                " AND (TO_DATE(tnt.THUOCNOITRU_NGAYGIO) < TO_DATE(hsba.HSBA_NGAYGIOVAOV)) ";
            Query q = getEm().createNativeQuery(sSQL, ThuocNoiTru.class);
            q.setParameter("sovaovien", sovaovien);

            List<ThuocNoiTru> returnList = q.getResultList();
            if (returnList != null && returnList.size() > 0) {
                //System.out.println("---End checkThuocNoiTruTruocNgayVaoVien()---");
                return returnList;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
        
    }

    public List<ThuocNoiTru> checkThuocNoiTruSauNgayRaVien(String sovaovien, Date ngayravien) {
        //System.out.println("---checkThuocNoiTruSauNgayRaVien---");
        try {
            sovaovien = Utils.formatMa(em, sovaovien);
            String sSQL = "select tnt.* from thuoc_noi_tru tnt, hsba_khoa hsbakhoa, hsba " +
                " where tnt.hsba_khoa = hsbakhoa.hsbakhoa_maso " +
                " AND hsbakhoa.hsba_sovaovien = hsba.hsba_sovaovien " +
                " AND hsba.hsba_sovaovien = :sovaovien " +
                " AND (TO_DATE(tnt.THUOCNOITRU_NGAYGIO) > :ngayravien) ";
            Query q = getEm().createNativeQuery(sSQL, ThuocNoiTru.class);
            q.setParameter("sovaovien", sovaovien);
            q.setParameter("ngayravien", ngayravien);

            List<ThuocNoiTru> returnList = q.getResultList();
            if (returnList != null && returnList.size() > 0) {
                //System.out.println("---End checkThuocNoiSauNgayRaVien()---");
                return returnList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
        
    }
}


