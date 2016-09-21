/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsThtoankFacade implements HsThtoankFacadeLocal, HsThtoankFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(HsThtoank hsThtoank) {
        if (hsThtoank.getHsthtoankMa() == null || hsThtoank.getHsthtoankMa().equals("")) {
            hsThtoank.setHsthtoankMa(Utils.createMaPhieu(em));
        }
        em.persist(hsThtoank);
    }

    public String capNhatTTHsttk(HsThtoank hsThtoank, List<ClsKham> clslist, List<ThuocPhongKham> lstTPK, boolean capnhattonkho) {
        String maPhieu = hsThtoank.getHsthtoankMa();
        System.out.println("In capNhatTTHsttk(), maPhieu = " + maPhieu);
        if (maPhieu == null || maPhieu.equals("")) {
            maPhieu = Utils.createMaPhieu(em);
            hsThtoank.setHsthtoankMa(maPhieu);
        }
        // phuc.lc 24-12-2010 : begin
        // Sua loi mot tiep don co 2 HsThToanK
        if(hsThtoank.getTiepdonMa() != null) {
            HsThtoank hsTmp = findBytiepdonMaLast(hsThtoank.getTiepdonMa().getTiepdonMa());
            if (hsTmp != null) {
                hsThtoank.setHsthtoankMa(hsTmp.getHsthtoankMa());
                maPhieu = hsTmp.getHsthtoankMa();
                em.merge(hsThtoank);
            } else {
                em.persist(hsThtoank);
            }
        } else {
            em.persist(hsThtoank);
        }
        // phuc.lc 24-12-2010 : end
        

        // luu thong tin dongiabntra va dongiabhyt -> da co' tu client

        // luu tiep thong tin
        //
        //
        //
        //

        //
        DieuTriUtilFacade dieuTriUtilFacade = new DieuTriUtilFacade();
        // phuc.lc comment 15-10-2010
        // Khong su dung dong code duoi day, vi neu su dung se xay ra loi : Transaction is not active
        // khi goi ham Utils.createMaPhieu(em) o dong code :  "clskham.setClskhamMaphieu(Utils.createMaPhieu(em))" ben duoi
        // loi tren xay ra khi nhan nut Ghi nhan o form "Thanh toan phong cap cuu", cac form khac chua kiem tra,
        // khong biet comment dong code nay co anh huong cac form khac khong
        
        //dieuTriUtilFacade.setEm(em);
        
        DtDmCum cumtiepdon = (DtDmCum) dieuTriUtilFacade.findByMa(hsThtoank.getTiepdonMa().getTiepdonCum(), "DtDmCum", "dtdmcumMa");

        DmKhoa khoaKham = (DmKhoa) dieuTriUtilFacade.findByMa("KHA", "DmKhoa", "dmkhoaMa");

        if (clslist != null && clslist.size() > 0) {
            for (ClsKham clskham : clslist) {
                if((clskham.getClskhamDatt() != null && clskham.getClskhamDatt().booleanValue() == true)
                  || clskham.getClskhamNgaygiott() != null){
                    continue;
                }
               // clskham.setClskhamNgaygio(new Date());
                clskham.setClskhamNgaygiocn(new Date());
                if(hsThtoank.getTiepdonMa().getTiepdonThungan() != null) {
                    clskham.setClskhamThungan(hsThtoank.getTiepdonMa().getTiepdonThungan());
                } else {
                    clskham.setClskhamThungan(hsThtoank.getHsthtoankThungan());
                }

                clskham.setClskhamCum(cumtiepdon);

                clskham.setClskhamKyHieu(hsThtoank.getHsthtoankKyhieu());
                clskham.setClskhamQuyen(hsThtoank.getHsthtoankQuyen());
                clskham.setClskhamBienLai(hsThtoank.getHsthtoankBienlai());

//                clskham.setClskhamMaphieu(Utils.createMaPhieu(em));
                // phuc.lc 11-01-2011 : Begin Fix bug 1997 : Truong hop benh nhan cap cuu khong luu ma phieu vao clskham
                if (clskham.getClskhamMaphieu() == null || clskham.getClskhamMaphieu().equals("")) {
                        //clskham.setClskhamMaphieu(Utils.createMaPhieu(em));
                        // phuc.lc : 07/01/2011 : ma phieu CLS kham su dung chung voi ma phieu HsThtoanK
                        // Muc dich de sau nay lay lai danh sach CSL theo ma phieu de in bao cao
                        clskham.setClskhamMaphieu(maPhieu);
                    }
                clskham.setClskhamDatt(true);
                /*
                if ("TP".equals(hsThtoank.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa()) && !"CCL".equals(hsThtoank.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())) {
                    // do nothing
                } else {
                    if (clskham.getClskhamMaphieu() == null || clskham.getClskhamMaphieu().equals("")) {
                        //clskham.setClskhamMaphieu(Utils.createMaPhieu(em));
                        // phuc.lc : 07/01/2011 : ma phieu CLS kham su dung chung voi ma phieu HsThtoanK
                        // Muc dich de sau nay lay lai danh sach CSL theo ma phieu de in bao cao
                        clskham.setClskhamMaphieu(maPhieu);
                    }
                    clskham.setClskhamDatt(true);
                }
                */
                // phuc.lc 11-01-2011 : end  Fix bug 1997 
                em.merge(clskham);
            }
        }
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(em);

        if (lstTPK != null && lstTPK.size() > 0) {
            for (ThuocPhongKham tpk : lstTPK) {
                if (tpk.getThuocphongkhamMaphieud() != null && !tpk.getThuocphongkhamMaphieud().equals("")) {
                    continue;
                }
                if (tpk.getThuocphongkhamMaphieuh() != null && !tpk.getThuocphongkhamMaphieuh().equals("")) {
                    continue;
                }

                //tpk.setThuocphongkhamMaphieud(Utils.createMaPhieu(em));
                tpk.setThuocphongkhamMaphieud(maPhieu);
                tpk.setThuocphongkhamDatt(true);
                tpk.setThuocphongkhamNgaygiott(new Date());
                // 20111207 bao.ttc: khong set lai ngay gio cap thuoc: tpk.setThuocphongkhamNgaygio(new Date());

                tpk.setThuocphongkhamKyhieu(hsThtoank.getHsthtoankKyhieu());
                tpk.setThuocphongkhamQuyen(hsThtoank.getHsthtoankKyhieu());
                tpk.setThuocphongkhamBienlai(hsThtoank.getHsthtoankKyhieu());

                tpk.setThuocphongkhamCum(cumtiepdon);
                tpk.setThuocphongkhamNgaygiocn(new Date());

                em.merge(tpk);
                // phuc.lc 30-11-2010:  fix bug 1559 : chi cap nhat ton kho khi xuat hang cho benh nhan 
//                if (capnhattonkho) {
//                    String malk = tpk.getThuocphongkhamMalk();
//                    TonKho tk_old = tkFacade.getTonKhoHienTai(malk, tpk.getThuocphongkhamKhoa(true).getDmkhoaMaso());
//
//                    TonKho newtkxuat;
//                    try {
//                        newtkxuat = (TonKho) BeanUtils.cloneBean(tk_old);
//                        newtkxuat.setTonkhoMa(null);
//                        newtkxuat.setTonkhoNhap(new Double(0));
//                        newtkxuat.setTonkhoTra(new Double(0));
//                        newtkxuat.setTonkhoXuat(tpk.getThuocphongkhamSoluong());
//                        tkFacade.insertTonKho(newtkxuat);
//                    } catch (Exception ex) {
//                    }
//                }//Tho remove vi doan nay khong su dung nua
            }
        }
        return maPhieu;
    }

    public Boolean edit(HsThtoank hsThtoank) {
        Boolean result = false;
        try {
//            Utils.setInforForHsThToan(hsThtoank);
            em.merge(hsThtoank);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void remove(HsThtoank hsThtoank) {
        em.remove(getEm().merge(hsThtoank));
    }

    public HsThtoank find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsThtoank.class, id);
    }

    public List<HsThtoank> findAll() {
        return getEm().createQuery("select object(o) from HsThtoank as o").getResultList();
    }

    public HsThtoank findBytiepdonMa(String tiepdonMa) {
        tiepdonMa = Utils.formatMa(em, tiepdonMa);
        HsThtoank hsttk = null;
        Query q = getEm().createQuery("Select h From HsThtoank h Where h.tiepdonMa.tiepdonMa = :tiepdonMa AND hsthtoankNgaygiott IS NOT NULL");
        q.setParameter("tiepdonMa", tiepdonMa);
        try {
            List<HsThtoank> listHsttk = q.getResultList();
            if (listHsttk != null && listHsttk.size() > 0) {
                //hsttk = listHsttk.get(0);
                // phuc.lc 01-11-2010 : Fix bug 580
                hsttk = listHsttk.get(listHsttk.size() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsttk;
    }

    public HsThtoank findAllBytiepdonMa(String tiepdonMa) {
        tiepdonMa = Utils.formatMa(em, tiepdonMa);
        HsThtoank hsttk = null;
        Query q = getEm().createQuery("Select h From HsThtoank h Where h.tiepdonMa.tiepdonMa = :tiepdonMa Order By h.hsthtoankMa DESC");
        q.setParameter("tiepdonMa", tiepdonMa);
        try {
            List<HsThtoank> listHsttk = q.getResultList();
            if (listHsttk != null && listHsttk.size() > 0) {
                hsttk = listHsttk.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsttk;
    }

    public HsThtoank findBytiepdonMaLast(String tiepdonMa) {
        tiepdonMa = Utils.formatMa(em, tiepdonMa);
        HsThtoank hsttk = null;
        Query q = getEm().createQuery("Select h From HsThtoank h Where h.tiepdonMa.tiepdonMa = :tiepdonMa");
        q.setParameter("tiepdonMa", tiepdonMa);
        try {
            List<HsThtoank> listHsttk = q.getResultList();
            if (listHsttk != null && listHsttk.size() > 0) {
                hsttk = listHsttk.get(listHsttk.size() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsttk;
    }

    public HsThtoank findByMaPhieu(String maPhieuTT) {
        //maPhieuTT = Utils.formatMa(em, maPhieuTT);
        HsThtoank hsttk = null;
        Query q = getEm().createQuery("Select h From HsThtoank h Where h.hsthtoankMa = :maPhieuTT");
        q.setParameter("maPhieuTT", maPhieuTT);
        try {
            List<HsThtoank> listHsttk = q.getResultList();
            if (listHsttk != null && listHsttk.size() > 0) {
                hsttk = listHsttk.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsttk;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /*
     * Thanh add 29/09/2011
     * Đã chỉnh sửa
     */
    public List<HsThtoank> findByNgayTiepdon(Date tungay, Date denngay) {                
        try {            
            String sSQL = "SELECT * FROM hs_thtoank h" +
            " where tiepdon_ma IN(" +
            " SELECT t.tiepdon_ma FROM tiep_don t where TO_DATE(t.tiepdon_ngaygio) >= :tungay AND TO_DATE(t.tiepdon_ngaygio) <= :denngay)";
            Query q = getEm().createNativeQuery(sSQL, HsThtoank.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);            
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }       
    }
    /*
     * Thanh add 29/09/2011
     * Đã fixed
     */
    // Set field hsthtoank_ngaygiott va hsthtoank_datt cua bang hs_thtoank  thanh null theo thoi gian tiep don (de thuc hien chuc nang thanh toan hang loat)
    public int updateNgayGioTTAndDaTT2Null(Date tungay, Date denngay) {
        try {            
            String sSQL = "UPDATE hs_thtoank hs, tiep_don td SET hs.HSTHTOANK_NGAYGIOTT = null, hs.HSTHTOANK_DATT = null" +
            " WHERE hs.TIEPDON_MA = td.TIEPDON_MA" +            
            " AND TO_DATE(td.TIEPDON_NGAYGIO) >= :tungay " +
            " AND TO_DATE(td.TIEPDON_NGAYGIO) <= :denngay ";
            Query q = getEm().createNativeQuery(sSQL, HsThtoank.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);            
            return q.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }           
    }

public List<HsThtoank> findByNgayTiepdonAndDaTT(Date tungay, Date denngay) {
        try {
            String sSQL = "SELECT * FROM hs_thtoank h" +
            " where hsthtoank_ngaygiott is not null AND tiepdon_ma IN(" +
            " SELECT t.tiepdon_ma FROM tiep_don t where to_date(t.tiepdon_ngaygio) >= :tungay AND to_date(t.tiepdon_ngaygio) <= :denngay)";
            Query q = getEm().createNativeQuery(sSQL, HsThtoank.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}