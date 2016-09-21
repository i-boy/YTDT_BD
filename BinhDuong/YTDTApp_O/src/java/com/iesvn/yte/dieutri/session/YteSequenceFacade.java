/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CauHinh;
import com.iesvn.yte.dieutri.entity.YteSequence;
import com.iesvn.yte.dieutri.utils.CauHinhFacade;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class YteSequenceFacade implements YteSequenceFacadeLocal, YteSequenceFacadeRemote {

   
    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext ctx;

    public  String startMyTimer(long interval) {
        System.out.println(" startTimer:" + interval );
        System.out.println(" ctx:" + ctx );
        TimerService timerService = ctx.getTimerService();
        System.out.println(" timerService:" + timerService.getTimers().size());
        Iterator i =  timerService.getTimers().iterator();
        while (i.hasNext()){
            Timer timer = (Timer)i.next();
            timer.cancel();
        }
        timerService.getTimers().clear();
        System.out.println(" timerService:" + timerService.getTimers().size());

        Timer timer = timerService.createTimer(123, interval , null);

        System.out.println(" timerService:" + timerService.getTimers().size());


        return "success";
    }

    @Timeout
    public void handleTimeout(Timer timer) {
       // System.out.println(" handleTimeout called.");
        //System.out.println(timer);
        //
        //taoBaoCaoTinhHinhMienGiamThatThuVienPhi();

    }

    /*
     * Thanh add 29/09/2011
     * Hàm không sử dụng, không cần fixed
     */
    private void taoBaoCaoTinhHinhMienGiamThatThuVienPhi(){
        // kiem tra neu co thang, nam thi ko lam gi, tra ve
        

        // kiem tra neu dau thang ma` thang truoc chua co du lieu trong database thi them vao

        Query q = em.createNativeQuery("CALL STO_BAO_CAO_TINH_HINH_MIEN_GIAM_THAT_THU_VIEN_PHI(1,2010)");
        q.executeUpdate();
    }

 
    public void create(YteSequence yteSequence) {
        em.persist(yteSequence);
       
    }

    public void edit(YteSequence yteSequence) {
        em.merge(yteSequence);
    }

    public void remove(YteSequence yteSequence) {
        em.remove(em.merge(yteSequence));
    }

    public YteSequence find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.YteSequence.class, id);
    }

    public List<YteSequence> findAll() {
        return em.createQuery("select object(o) from YteSequence as o").getResultList();
    }

    public String formatMaPhieu(EntityManager em, String ma) {
        String currentValue = null;
        if (ma != null && ma.length() < Utils.LENGTH_MA) {
            String ketQua = "";

            Calendar cal = new GregorianCalendar();
            String sYear = String.valueOf(cal.get(Calendar.YEAR));

            ketQua += sYear;

            String sMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
            if (sMonth.length() == 1) {
                sMonth = "0" + sMonth;
            }

            ketQua += sMonth;

            String sCurrentValue = String.valueOf(currentValue);
            while (sCurrentValue.length() < Utils.LENGTH_MA - 6) {
                sCurrentValue = "0" + sCurrentValue;
            }
            ketQua += sCurrentValue;
            return ketQua;

        } else {
            return ma;
        }
    }

    /**
     * @author thanhdo
     * @param em
     * @param loaiMa
     * @return
     */
    public String formatMa(EntityManager em, String ma) {
        if (em == null){
            em = this.em;                    
        }

        // 20120103 bao.ttc: ho tro user search chu HOA, thuong deu duoc
        if (ma != null){
            ma = ma.toUpperCase();
        }

        if (ma != null && ma.length() < Utils.LENGTH_MA) {
            CauHinhFacade facade = new CauHinhFacade();
            facade.setEm(em);
            
            CauHinh cauHinhMaBALUU = facade.findByMa("BA_LUU_PREFIX");
            CauHinh cauHinhMaBANGT = facade.findByMa("BA_DTNGT_PREFIX");
            String prefixBALUU = cauHinhMaBALUU.getChbvGiatri();
            String prefixBADTNGT = cauHinhMaBANGT.getChbvGiatri();
            //Kiem tra neu nguoi dung nhap ky tu CL thi se format theo ho so cap cuu luu, nguoc lai la khong thay doi
            if(ma.toUpperCase().indexOf(prefixBALUU.trim().toUpperCase()) > -1){
                /*neu trong chuoi ma co ton tai CL thi cat bo CL*/
                ma = ma.substring(ma.toUpperCase().indexOf(prefixBALUU)+2, ma.length());
                String ketQua = cauHinhMaBALUU.getChbvGiatri();

                Calendar cal = new GregorianCalendar();
                String sYear = String.valueOf(cal.get(Calendar.YEAR));
                if (sYear.length() == 4) {
                    sYear = sYear.substring(2);
                }
                ketQua += sYear;
                while (ma.length() < Utils.LENGTH_MA - (ketQua.length()) ) {
                    ma = "0" + ma;
                }
                ketQua += ma;
                return ketQua;
            }
            //Kiem tra neu nguoi dung nhap ky tu NG thi se format theo ho so dieu tri ngoai tru, nguoc lai la khong thay doi
            if(ma.toUpperCase().indexOf(prefixBADTNGT.trim().toUpperCase()) > -1){
                /*neu trong chuoi ma co ton tai NGT thi cat bo NG*/
                ma = ma.substring(ma.toUpperCase().indexOf(prefixBADTNGT)+2, ma.length());
                String ketQua = cauHinhMaBANGT.getChbvGiatri();

                Calendar cal = new GregorianCalendar();
                String sYear = String.valueOf(cal.get(Calendar.YEAR));
                if (sYear.length() == 4) {
                    sYear = sYear.substring(2);
                }
                ketQua += sYear;
                while (ma.length() < Utils.LENGTH_MA - (ketQua.length()) ) {
                    ma = "0" + ma;
                }
                ketQua += ma;
                return ketQua;
            }

            CauHinh cauHinhMaDonVi = facade.findByMa("PREFIX");
            String ketQua = cauHinhMaDonVi.getChbvGiatri();

            Calendar cal = new GregorianCalendar();
            String sYear = String.valueOf(cal.get(Calendar.YEAR));
            if (sYear.length() == 4) {
                sYear = sYear.substring(2);
            }
            ketQua += sYear;


            while (ma.length() < Utils.LENGTH_MA - (ketQua.length()) ) {
                ma = "0" + ma;
            }
            ketQua += ma;
            return ketQua;
            
        } else {
            return ma;
        }

    }

    /**
     * 
     *   loaiMa = 0 : ma benh nhan
     *     loaiMa = 1 : ma tiep don
     *     loaiMa = 2 : so vao vien (so benh an)
     *     loaiMa = 3 : so luu tru benh an
     *     loaiMa = 4 : so ho so cap cuu luu
     *     loaiMa = 5 : so ho so dieu tri ngoai tru
     * */
    /*
     * Thanh add 29/09/2011
     * oracle: syntax ok
     */
    public String getMa(EntityManager em, int loaiMa) {

        String currentValue = null;
        try {

            String giaTri = "";
            if (loaiMa == 0) {
                giaTri = "BENHNHAN_MA";
            } else if (loaiMa == 1) {
                giaTri = "TIEPDON_MA";
            } else if (loaiMa == 2) {
                giaTri = "SOVAOVIEN";
            } else if (loaiMa == 3) {
                giaTri = "SOLUUTRU";
            } else if (loaiMa == 4) {
                giaTri = "BA_CAPCUULUU";
            } else if (loaiMa == 5) {
                giaTri = "BA_DTNGOAITRU";
            }

            Query q = em.createNativeQuery("select SEQUENCE_CURRENT_VALUE from YTE_SEQUENCE where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            currentValue = String.valueOf(q.getSingleResult());
            //currentValue = currentValue.substring(1, currentValue.length() - 1);
            q = em.createNativeQuery("update YTE_SEQUENCE set  SEQUENCE_CURRENT_VALUE = SEQUENCE_CURRENT_NEXT, SEQUENCE_CURRENT_NEXT = SEQUENCE_CURRENT_NEXT + SEQUENCE_STEP where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            q.executeUpdate();
        } catch (Exception e) {
            System.out.println("thanhdo001:" + e);
            return "-1";
        }

        //CauHinh cauHinhMaDonVi = em.find(CauHinh.class, "PREFIX");
        CauHinhFacade facade = new CauHinhFacade();
        facade.setEm(em);
        //Tho add
        if(loaiMa == 4){//cap cuu luu se tao format ung voi prefix rieng
            CauHinh cauHinhMaDonVi = facade.findByMa("BA_LUU_PREFIX");
            String ketQua = cauHinhMaDonVi.getChbvGiatri();

            Calendar cal = new GregorianCalendar();
            String sYear = String.valueOf(cal.get(Calendar.YEAR));
            if (sYear.length() == 4) {
                sYear = sYear.substring(2);
            }
            ketQua += sYear;

            String sCurrentValue = String.valueOf(currentValue);
            while (sCurrentValue.length() < Utils.LENGTH_MA - ketQua.length()) {
                sCurrentValue = "0" + sCurrentValue;
            }
            ketQua += sCurrentValue;

            return ketQua;
        }else if(loaiMa == 5){//Dieu tri ngoai tru voi prefix rieng
            CauHinh cauHinhMaDonVi = facade.findByMa("BA_DTNGT_PREFIX");
            String ketQua = cauHinhMaDonVi.getChbvGiatri();

            Calendar cal = new GregorianCalendar();
            String sYear = String.valueOf(cal.get(Calendar.YEAR));
            if (sYear.length() == 4) {
                sYear = sYear.substring(2);
            }
            ketQua += sYear;

            String sCurrentValue = String.valueOf(currentValue);
            while (sCurrentValue.length() < Utils.LENGTH_MA - ketQua.length()) {
                sCurrentValue = "0" + sCurrentValue;
            }
            ketQua += sCurrentValue;

            return ketQua;
        }

        //End Tho add
        CauHinh cauHinhMaDonVi = facade.findByMa("PREFIX");

        String ketQua = cauHinhMaDonVi.getChbvGiatri();

        Calendar cal = new GregorianCalendar();
        String sYear = String.valueOf(cal.get(Calendar.YEAR));
        if (sYear.length() == 4) {
            sYear = sYear.substring(2);
        }
        ketQua += sYear;

        String sCurrentValue = String.valueOf(currentValue);
        while (sCurrentValue.length() < Utils.LENGTH_MA - ketQua.length()) {
            sCurrentValue = "0" + sCurrentValue;
        }
        ketQua += sCurrentValue;

        return ketQua;
    }

    /**
     * Thanh add 29/09/2011
     * Oracle: syntax ok
     */
    public String getMaPhieu(EntityManager em, int loaiMa) {
        String currentValue = null;
        try {

            String giaTri = "";
            if (loaiMa == 0) {
                giaTri = "MAPHIEU_NHAP";
            } else if (loaiMa == 1) {
                giaTri = "MAPHIEU_XUAT";
            } else if (loaiMa == 2) {
                giaTri = "MAPHIEU_TRA";
            } else if (loaiMa == 3) {
                giaTri = "MAPHIEU_THANH_TOAN";
            } else if (loaiMa == 4) {
                giaTri = "MAPHIEU_THUOC";
            } else if (loaiMa == 5) {
                giaTri = "MAPHIEU_DU_TRU";
            } else if (loaiMa == 6) {
                giaTri = "MAPHIEU_XUAT_BAO_HIEM";
            } else if (loaiMa == 7) {
                giaTri = "MAPHIEU_THUOC_PHONG_KHAM";
            } else if (loaiMa == 8) {
                giaTri = "MAPHIEU_TAM_UNG";
            } else if (loaiMa == 9) {
                giaTri = "MAPHIEU_HSTHTOANK";
            } else if (loaiMa == 10) {
                giaTri = "MAPHIEU_BIENBANHOICHAN";
            } else if (loaiMa == 11) {
                giaTri = "MAGIAY_CHUNG_THUONG";
            } else if (loaiMa == 12) {
                giaTri = "MAGIAY_CHUNG_NHAN_PHAU_THUAT";
            } else if (loaiMa == 13) {
                giaTri = "MAGIAY_RA_VIEN";
            } else if (loaiMa == 14) {
                giaTri = "MAHSBA_CHUYEN_VIEN";
            } else if (loaiMa == 15) {
                giaTri = "MAPHIEU_MIEN_GIAM";
            } else if (loaiMa == 16) {
                giaTri = "MAGIAY_TOM_TAT_HSBA";
            }else if (loaiMa == 17) {
                giaTri = "MAPHIEU_KIEMKE";
            }else if (loaiMa == 18) {
                giaTri = "MAPHIEU_BAOAN";
            }else if (loaiMa == 19) {
                giaTri = "MAPHIEU_GIAOBAN";
            }else if (loaiMa == 20) {
                giaTri = "BENH_AN_NGOAI";
            }else if (loaiMa == 21) {
                giaTri = "MAGIAY_CHUYENVIEN_BHYT";
            }else if (loaiMa == 22) {
                giaTri = "MAPHIEU_KHAM_DT_NGOAITRU";
            }else if (loaiMa == 23) {
                giaTri = "MAPHIEU_CHAMSOC";
            }else if (loaiMa == 24) {
                giaTri = "MAPHIEU_TODIEUTRI";
            }else if (loaiMa == 25) {
                giaTri = "MAPHIEU_KHAM_CHUYEN_KHOA";
            }else if (loaiMa == 26) {
                giaTri = "MAPHIEU_KB_VAOVIEN";
            }else if (loaiMa == 27) {
                giaTri = "MAGIAY_CN_SUCKHOE";
            }else if (loaiMa == 28) {
                giaTri = "MAPHIEU_TRA_NHACUNGCAP";
            }else if (loaiMa == 31) {
                giaTri = "MAGIAY_CHUYEN_XAC";
            }else if (loaiMa == 32) {
                giaTri = "MAPHIEU_GUI_XAC";
            }else if (loaiMa == 33) {
                giaTri = "MAPHIEU_PHAU_THUAT_THU_THUAT";
            }else if (loaiMa == 34) {
                giaTri = "MALAP_TRICH_BIEN_BAN_HOI_CHAN";
            }else if (loaiMa == 35) {
                giaTri = "MABIEN_BAN_HOI_CHAN_PHAU_THUAT";
            }else if (loaiMa == 36) {
                giaTri = "MAPHIEU_XUATVIEN";
            }else if (loaiMa == 37) {
                giaTri = "MAPHIEU_BA_TUVONG_TRUOC";
            }else if (loaiMa == 38) {
                giaTri = "MAPHIEU_XUAT_VIEN";
            }else if (loaiMa == 39) {
                giaTri = "MAPHIEU_CHUNG_NHAN";
            }else if (loaiMa == 40) {
                giaTri = "MAPHIEU_TOM_TAT_BENH_AN";
            }


            System.out.println("Gia tri = "+giaTri);
            Query q = em.createNativeQuery("select SEQUENCE_CURRENT_VALUE from YTE_SEQUENCE where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            System.out.println("select SEQUENCE_CURRENT_VALUE from YTE_SEQUENCE where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            currentValue = String.valueOf(q.getSingleResult());
            System.out.println("-----currentValue: " + currentValue);
            //currentValue = currentValue.substring(1, currentValue.length() - 1);
            q = em.createNativeQuery("update YTE_SEQUENCE set  SEQUENCE_CURRENT_VALUE = SEQUENCE_CURRENT_NEXT, SEQUENCE_CURRENT_NEXT = SEQUENCE_CURRENT_NEXT + SEQUENCE_STEP where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            
            
            
           q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("thanhdo001:" + e);
            return "-1";
        }

        String ketQua = "";
        
        Calendar cal = new GregorianCalendar();
        String sYear = String.valueOf(cal.get(Calendar.YEAR));

        ketQua += sYear;

        String sMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (sMonth.length() == 1) {
            sMonth = "0" + sMonth;
        }

        ketQua += sMonth;

        String sCurrentValue = String.valueOf(currentValue);
        while (sCurrentValue.length() < Utils.LENGTH_MA_PHIEU - 6) {
            sCurrentValue = "0" + sCurrentValue;
        }
        ketQua += sCurrentValue;

        return ketQua;
    }

    /*
     * Thanh add 29/09/2011
     * Oracle: syntax ok
     */
    public String getMaDkKhamBenh() {
        String currentValue = null;
        try {
            String giaTri = "MA_DK_KB";
            Query q = em.createNativeQuery("select SEQUENCE_CURRENT_VALUE from YTE_SEQUENCE where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            currentValue = String.valueOf(q.getSingleResult());
            System.out.println("-----currentValue: " + currentValue);
            //currentValue = currentValue.substring(1, currentValue.length() - 1);
            q = em.createNativeQuery("update YTE_SEQUENCE set  SEQUENCE_CURRENT_VALUE = SEQUENCE_CURRENT_NEXT, SEQUENCE_CURRENT_NEXT = SEQUENCE_CURRENT_NEXT + SEQUENCE_STEP where SEQUENCE_NAME_MA like " + "'" + giaTri + "'");
            q.executeUpdate();
        } catch (Exception e) {
            System.out.println("thanhdo001:" + e);
            return "-1";
        }

        return currentValue;
    }

//    public String resetMaPhieu(){
//        System.out.println("--------Begin: resetMaPhieu -----------------");
//        try{
//            Calendar origDay = new GregorianCalendar();
//            String sCurrentMonth = String.valueOf(origDay.get(Calendar.MONTH) + 1);
//            String sCurrentYear = String.valueOf(origDay.get(Calendar.YEAR));
//            int currentMonth = Integer.parseInt(sCurrentMonth);
//            int currentYear = Integer.parseInt(sCurrentYear);
//
//            Calendar nextDay = (Calendar) origDay.clone();
//            nextDay.add (Calendar.DAY_OF_YEAR, 1);
//            String sNextMonth = String.valueOf(nextDay.get(Calendar.MONTH) + 1);
//            int nextMonth = Integer.parseInt(sNextMonth);
//            String sNextYear = String.valueOf(nextDay.get(Calendar.YEAR));
//            int nextYear =  Integer.parseInt(sNextYear);
//
//            //Neu current date la cuoi thang thi se set cac phieu sau
//            System.out.println("Current Month: "+ currentMonth);
//            System.out.println("Next Month: "+ nextMonth);
//            if(currentMonth < nextMonth){
//                String sSQL = "update yte_sequence set sequence_current_value = 1, sequence_current_next = 2 where SEQUENCE_RESET_YEAR is null or SEQUENCE_RESET_YEAR = 0";
//                Query q = em.createNativeQuery(sSQL);
//                q.executeUpdate();
//            }
//            //neu current date la cuoi nam thi se set cac phieu sau
//            System.out.println("Current Year: "+ currentYear);
//            System.out.println("Next Year: "+ nextYear);
//            if(currentYear < nextYear){
//                String sSQL = "update yte_sequence set sequence_current_value = 1, sequence_current_next = 2 where SEQUENCE_RESET_YEAR = 1";
//                Query q = em.createNativeQuery(sSQL);
//                q.executeUpdate();
//            }
//        } catch (Exception e) {
//            System.out.println("ERROR resetMaPhieu: " + e.toString());
//            return "-1";
//        }
//        System.out.println("--------End: resetMaPhieu -----------------");
//        return "+1";
//    }
    
    public String resetMaPhieu(){
        System.out.println("-------- Begin: NEW resetMaPhieu -----------------");
        try{
            Calendar origDay = Calendar.getInstance() ;
            System.out.println("### origDay     : " + origDay);
            int iDayOfMonth = origDay.get(Calendar.DAY_OF_MONTH);
            int iDayOfYear = origDay.get(Calendar.DAY_OF_YEAR);

            //Neu iDayOfMonth la ngay dau thang thi se set cac phieu sau
            System.out.println("### iDayOfMonth : "+ iDayOfMonth);
            if (iDayOfMonth == 1) {
                String sSQL = "update yte_sequence set sequence_current_value = 1, sequence_current_next = 2 where SEQUENCE_RESET_YEAR is null or SEQUENCE_RESET_YEAR = 0";
                Query q = em.createNativeQuery(sSQL);
                q.executeUpdate();
                System.out.println("###---### Reset cac ma phieu theo THANG !");
            }
            //neu iDayOfYear la ngay dau nam thi se set cac phieu sau
            System.out.println("### iDayOfYear  : "+ iDayOfYear);
            if (iDayOfYear == 1) {
                String sSQL = "update yte_sequence set sequence_current_value = 1, sequence_current_next = 2 where SEQUENCE_RESET_YEAR = 1";
                Query q = em.createNativeQuery(sSQL);
                q.executeUpdate();
                System.out.println("###---### Reset cac ma phieu theo NAM !");
            }
        } catch (Exception e) {
            System.out.println("ERROR resetMaPhieu: " + e.toString());
            return "-1";
        }
        System.out.println("--------End: resetMaPhieu -----------------");
        return "+1";
    }
    
}


