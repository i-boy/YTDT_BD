/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ClsMo;

import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.session.DmKhoaFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class ClsMoFacade implements ClsMoFacadeLocal, ClsMoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private DmKhoaFacadeLocal khoaDAO;
    @EJB
    private DieuTriUtilFacadeLocal dieuTriUtils;

    public void create(ClsMo clsMo) {
        em.persist(clsMo);
    }

    public void edit(ClsMo clsMo) {
        em.merge(clsMo);
    }

    public void remove(ClsMo clsMo) {
        em.remove(em.merge(clsMo));
    }

    public ClsMo find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ClsMo.class, id);
    }

    public List<ClsMo> findAll() {
        return em.createQuery("select object(o) from ClsMo as o").getResultList();
    }

    public List<ClsMo> findByMaPhieu(String maPhieu) {

        maPhieu = Utils.formatMaPhieu(maPhieu);

        Query q = em.createQuery("Select c from ClsMo c Where c.clsmoMaphieu = :maPhieu");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from ClsMo as o where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbaKhoa.khoaMa.dmkhoaMa like :khoaMa order by o.clsmoNgay ");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);

        return q.getResultList();
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer tangMaso) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from ClsMo as o where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien = :soVaoVien) and (o.hsbaKhoa.khoaMa.dmkhoaMa = :khoaMa) and (o.hsbaKhoa.dmtangMaso.dmtangMaso = :tangMaso) order by o.clsmoNgay ");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("tangMaso", tangMaso);

        return q.getResultList();
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay) {
        System.out.println("----- ClsMo findBySoVaoVienAndKhoaMaAndTangAndNgay() -----");
        System.out.println("----- soVaoVien : " + soVaoVien);
        System.out.println("----- khoaMa    : " + khoaMa);
        System.out.println("----- tangMaso  : " + tangMaso);
        System.out.println("----- ngay      : " + ngay);
        List<ClsMo> listClsMo = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from ClsMo as o where (o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien = :soVaoVien) and (o.hsbaKhoa.khoaMa.dmkhoaMa = :khoaMa) and (o.hsbaKhoa.dmtangMaso.dmtangMaso = :tangMaso) and (o.clsmoNgay between :ngay1 and :ngay2) order by o.clsmoMa ");

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            System.out.println("----- findBySoVaoVienAndKhoaMaAndTangAndNgay() ----- NGAY_1: " + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay_1" + e);
        }

        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            System.out.println("----- findBySoVaoVienAndKhoaMaAndTangAndNgay() ----- NGAY_2: " + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay_2" + e);
        }
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("tangMaso", tangMaso);
        try {
            listClsMo = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listClsMo;
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAnhLan(String soVaoVien, String khoaMa, Integer lan) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from ClsMo as o where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbaKhoa.khoaMa.dmkhoaMa like :khoaMa  and  o.hsbaKhoa.hsbakhoaLan = :lan ");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        q.setParameter("lan", lan);

        return q.getResultList();
    }
    
     public List<ClsMo> findBySoVaoVien(String soVaoVien) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from ClsMo as o where o.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien ");
        q.setParameter("soVaoVien", soVaoVien);
      
        return q.getResultList();

    }

    /**
     * 
     * @param listCLS
     * @param soVaoVien
     * @return
     */
    public String createClsMoForCLSPhauThuat(List<ClsMo> listCLS, String soVaoVien, String khoaMa, Integer dmtangMaso, String ngay) {

        if ( listCLS == null || soVaoVien == null || soVaoVien.equals("")
            || khoaMa == null || khoaMa.equals("") || dmtangMaso == null ) {
            return "error";
        }

        soVaoVien = Utils.formatMa(em, soVaoVien);

        HsbaKhoaFacade hsbaKhoaFacade = new HsbaKhoaFacade();
        hsbaKhoaFacade.setEm(em);
        // 20120418 bao.ttc : them Tang
        // HsbaKhoa hsbaKhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMa(soVaoVien, khoaMa);
        HsbaKhoa hsbaKhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMaAndTang(soVaoVien, khoaMa, dmtangMaso);
        if (hsbaKhoa == null) {
            return "error";
        }
        System.out.print("createClsMoForCLSPhauThuat -- hsbaKhoa: " + hsbaKhoa);

        String result = "";
        String maPhieu = "";
        if (listCLS.size() > 0) {
            boolean needMaPhieu = false;
            for (int i = 0; i < listCLS.size(); i++) {

                ClsMo cls = listCLS.get(i);
                if ( (cls.getClsmoMaphieu() == null ||cls.getClsmoMaphieu().equals("") ) && cls.getClsmoDatt() != null && cls.getClsmoDatt().booleanValue() == true) {
                    needMaPhieu = true;
                    break;
                }
            }
            if (needMaPhieu == true) {
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 1 :" + maPhieu );
                maPhieu = Utils.maPhieuThanhToan(em);
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 2 :" + maPhieu );
            }
        }

        if (dieuTriUtils == null) {
            dieuTriUtils = new DieuTriUtilFacade();
            dieuTriUtils.setEm(em);
        }

        try {

            String listclsMa = "";
            for (int i = 0; i < listCLS.size(); i++) {

                ClsMo cls = listCLS.get(i);

                if (cls.getClsmoMa() != null) {

                    if (listclsMa.equals("")) {
                        listclsMa += cls.getClsmoMa();
                    } else {
                        listclsMa += "," + cls.getClsmoMa();
                    }

                    //thanh add 24/11/2011
                    List<ClsKetQua> listClsKetQua = dieuTriUtils.findByAllConditions("ClsKetQua", "clsmoMaso", "clskqTen", cls.getClsmoMa() + "", "");
                    for (ClsKetQua clsKetQua: listClsKetQua) {
                        getEm().remove(clsKetQua);
                    }
                    //thanh add 24/11/2011
                }

            }
            
            System.out.print("listclsMa: " + listclsMa);
            //this.removeItem(listclsMa, soVaoVien,khoaMa);
            this.removeClsMoItemWithTangNgay(listclsMa, soVaoVien, khoaMa, dmtangMaso, ngay);

            int count = 0;
            for (ClsMo clsMo : listCLS) {
                if (clsMo.getClsmoMaphieu() != null && !clsMo.getClsmoMaphieu().equals("")) {
                    continue;
                }

                if (clsMo.getClsmoMa() != null) {
                    System.out.println("clsMo.getClsmoMa()  :" + clsMo.getClsmoMa() );
                    System.out.println("clsMo.getClsmoMaphieu() :" + clsMo.getClsmoMaphieu());
                    System.out.println("clsMo.getClsmoDatt() :" + clsMo.getClsmoDatt());
                    if (clsMo.getClsmoMaphieu() == null || clsMo.getClsmoMaphieu().equals("")) {
                        if (clsMo.getClsmoDatt() != null && clsMo.getClsmoDatt().booleanValue() == true) {
                            clsMo.setClsmoMaphieu(maPhieu);
                        }
                    }
                    em.merge(clsMo);
                    System.out.println("update thanh cong chi tiet CLS MO WITH TANG -- " + count);
                } else {
                    clsMo.setHsbaKhoa(hsbaKhoa);
                    em.persist(clsMo);
                    System.out.println("insert thanh cong chi tiet CLS MO WITH TANG -- " + count);
                }

                //thanh add 24/11/2011
                count++;
                DtDmClsBangGia dtDmClsBangGia = clsMo.getClsmoMahang();
                if (dtDmClsBangGia.getDtdmclsbgXn() != null) {
                    List<DtDmClsKetQua> listDtDmClsKetQua = dieuTriUtils.findByAllConditions("DtDmClsKetQua", "dtdmclsbgMaso", "dtdmclskqTen", dtDmClsBangGia.getDtdmclsbgMaso() + "", "");
                    for (int j = 0; j < listDtDmClsKetQua.size(); j++) {
                        System.out.println("j " + j);
                        ClsKetQua clsKetQua = new ClsKetQua();
                        clsKetQua.setClskqMa(listDtDmClsKetQua.get(j).getDtdmclskqMa());
                        clsKetQua.setClskqTen(listDtDmClsKetQua.get(j).getDtdmclskqTen());
                        clsKetQua.setClskqGhiChu(listDtDmClsKetQua.get(j).getDtdmclskqGhiChu());
                        clsKetQua.setClsmoMaso(clsMo);
                        getEm().persist(clsKetQua);
                    }
                }
                //thanh add 24/11/2011
            }
            result = "success";

        } catch (Exception ex) {
            result = "exception ERROR";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("###### createClsMoForCLSPhauThuat result: " + result);
        return result;
    }

    public void removeItem(String listclsMa, String soVaoVien, String khoaMa) {

        Query q;
        if (listclsMa == null || listclsMa.equals("")) {
            q = em.createQuery("SELECT c FROM ClsMo c WHERE ( c.clsmoMaphieu is null or c.clsmoMaphieu = '' ) AND  c.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND c.hsbaKhoa.khoaMa.dmkhoaMa like :khoaMa ");

        } else {
            q = em.createQuery("SELECT c FROM ClsMo c WHERE ( c.clsmoMaphieu is null or c.clsmoMaphieu = '' )  and c.clsmoMa NOT IN (" + listclsMa + ") AND  c.hsbaKhoa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND c.hsbaKhoa.khoaMa.dmkhoaMa like :khoaMa ");
        }

        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
       
        List<ClsMo> listCLS = q.getResultList();
        System.out.println("remove**********************");
        for (ClsMo cls : listCLS) {
            System.out.println("remove: " + cls.getClsmoMa());
		  //thanh add 24/11/2011
            if (dieuTriUtils == null) {
                dieuTriUtils = new DieuTriUtilFacade();
                dieuTriUtils.setEm(em);
            }
            List<ClsKetQua> listClsKetQua = dieuTriUtils.findByAllConditions("ClsKetQua", "clsmoMaso", "clskqTen", cls.getClsmoMa() + "", "");
             for (ClsKetQua clsKetQua: listClsKetQua) {
                 getEm().remove(clsKetQua);
             }
            //thanh add 21/12/2011
			em.remove(cls);
        }
        System.out.println("remove**********************");
    }

    public void removeClsMoItemWithTangNgay(String listclsMa, String soVaoVien, String khoaMa, Integer dmtangMaso, String ngay) {

        List<ClsMo> listCLS = null;
        String ngay_1 = ngay + " 00:00:00";
        String ngay_2 = ngay + " 23:59:59";

        Query q;
        if (listclsMa == null || listclsMa.equals("")) {
            q = em.createQuery("SELECT c FROM ClsMo c WHERE ( c.clsmoMaphieu is null or c.clsmoMaphieu = '' ) AND (c.hsbaKhoa.hsbaSovaovien.hsbaSovaovien = :soVaoVien) AND (upper(c.hsbaKhoa.khoaMa.dmkhoaMa) = :khoaMa) AND (c.hsbaKhoa.dmtangMaso.dmtangMaso = :dmtangMaso) AND (c.clsmoNgay between :ngay1 and :ngay2) ");
        } else {
            q = em.createQuery("SELECT c FROM ClsMo c WHERE ( c.clsmoMaphieu is null or c.clsmoMaphieu = '' ) AND c.clsmoMa NOT IN (" + listclsMa + ") AND (c.hsbaKhoa.hsbaSovaovien.hsbaSovaovien = :soVaoVien) AND (upper(c.hsbaKhoa.khoaMa.dmkhoaMa) = :khoaMa) AND (c.hsbaKhoa.dmtangMaso.dmtangMaso = :dmtangMaso) AND (c.clsmoNgay between :ngay1 and :ngay2) ");
        }

        try {
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // bao.ttc  input: "dd/MM/yyyy" ; in DB: 2010-05-18 14:53:18
            cal1.setTime(df1.parse(ngay_1));
            java.sql.Date jsqlD1 = new java.sql.Date( cal1.getTime().getTime() );
            System.out.println("----- removeClsMoItemWithTangNgay() ----- NGAY_1: " + jsqlD1);
            q.setParameter("ngay1", jsqlD1);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay_1" + e);
        }

        try {
            Calendar cal2 = Calendar.getInstance();
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            cal2.setTime(df2.parse(ngay_2));
            java.sql.Date jsqlD2 = new java.sql.Date( cal2.getTime().getTime() );
            System.out.println("----- removeClsMoItemWithTangNgay() ----- NGAY_2: " + jsqlD2);
            q.setParameter("ngay2", jsqlD2);
        } catch (Exception e) {
            System.out.println("Loi khi set gia tri ngay_2" + e);
        }

        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa.toUpperCase());
        q.setParameter("dmtangMaso", dmtangMaso);

        try {
            listCLS = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("  removeClsMoItemWithTangNgay CLS_KQ Begin **********************");
        for (ClsMo cls : listCLS) {
            System.out.println("remove: " + cls.getClsmoMa());
		  //thanh add 24/11/2011
            if (dieuTriUtils == null) {
                dieuTriUtils = new DieuTriUtilFacade();
                dieuTriUtils.setEm(em);
            }
            List<ClsKetQua> listClsKetQua = dieuTriUtils.findByAllConditions("ClsKetQua", "clsmoMaso", "clskqTen", cls.getClsmoMa() + "", "");
            for (ClsKetQua clsKetQua: listClsKetQua) {
                getEm().remove(clsKetQua);
            }
            //thanh add 21/12/2011
            em.remove(cls);
        }
        System.out.println("  removeClsMoItemWithTangNgay CLS_KQ End **********************");
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ClsMo> checkClsmoTruocNgayVaoVien(String sovaovien) {
        System.out.println("---checkClsmoTruocNgayVaoVien---");
        try {
        sovaovien = Utils.formatMa(em, sovaovien);
        String sSQL = "select clsmo.* from cls_mo clsmo, hsba_khoa hsbakhoa, hsba " +
            " where clsmo.hsbakhoa_maso = hsbakhoa.hsbakhoa_maso " +
            " AND hsbakhoa.hsba_sovaovien = hsba.hsba_sovaovien " +
            " AND hsba.hsba_sovaovien = :sovaovien " +
            " AND (TO_DATE(clsmo.CLSMO_NGAY) < TO_DATE(hsba.HSBA_NGAYGIOVAOV)) ";
        Query q = getEm().createNativeQuery(sSQL, ClsMo.class);
        q.setParameter("sovaovien", sovaovien);

        List<ClsMo> returnList = q.getResultList();
        if (returnList != null && returnList.size() > 0) {
            System.out.println("---End checkClsmoTruocNgayVaoVien()---");
            return returnList;
        }
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;


    }

    public List<ClsMo> checkClsmoSauNgayRaVien(String sovaovien, Date ngayravien) {
        System.out.println("---checkClsmoSauNgayRaVien---");
        try {
            sovaovien = Utils.formatMa(em, sovaovien);
            String sSQL = "select clsmo.* from cls_mo clsmo, hsba_khoa hsbakhoa, hsba " +
                " where clsmo.hsbakhoa_maso = hsbakhoa.hsbakhoa_maso " +
                " AND hsbakhoa.hsba_sovaovien = hsba.hsba_sovaovien " +
                " AND hsba.hsba_sovaovien = :sovaovien " +
                " AND (TO_DATE(clsmo.CLSMO_NGAY) > :ngayravien) ";
            Query q = getEm().createNativeQuery(sSQL, ClsMo.class);
            q.setParameter("sovaovien", sovaovien);
            q.setParameter("ngayravien", ngayravien);
            List<ClsMo> returnList = q.getResultList();
            if (returnList != null && returnList.size() > 0) {
                System.out.println("---End checkClsmoSauNgayRaVien()---");
                return returnList;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;


    }
}


