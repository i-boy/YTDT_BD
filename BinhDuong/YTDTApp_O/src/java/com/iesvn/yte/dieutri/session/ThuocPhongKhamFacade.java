/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.CtXuatBhThuocbk;
import com.iesvn.yte.dieutri.utils.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class ThuocPhongKhamFacade implements ThuocPhongKhamFacadeLocal, ThuocPhongKhamFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private ThuocDongYNgoaiTruFacadeLocal thuocDYFacade;

    public void create(ThuocPhongKham thuocPhongKham) {
        getEm().persist(thuocPhongKham);
    }

    public void edit(ThuocPhongKham thuocPhongKham) {
        getEm().merge(thuocPhongKham);
    }

    public void remove(ThuocPhongKham thuocPhongKham) {
        getEm().remove(getEm().merge(thuocPhongKham));
    }

    public ThuocPhongKham find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.ThuocPhongKham.class, id);
    }

    public List<ThuocPhongKham> findAll() {
        return getEm().createQuery("select object(o) from ThuocPhongKham as o").getResultList();
    }

    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai) {
        maTiepDon = Utils.formatMa(getEm(), maTiepDon);
        Query q = getEm().createQuery("select object(o) from ThuocPhongKham as o where o.thuocphongkhamLoai like :loai and o.thuocphongkhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa");
        q.setParameter("loai", loai);
        q.setParameter("dtdmbankhamMa", maBanKham);
        q.setParameter("tiepdonMa", maTiepDon);
        return q.getResultList();
    }

    public List<ThuocPhongKham> find2LoaiByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai1, String loai2) {
        maTiepDon = Utils.formatMa(getEm(), maTiepDon);
        Query q = getEm().createQuery("select object(o) from ThuocPhongKham as o where (o.thuocphongkhamLoai like :loai1 or o.thuocphongkhamLoai like :loai2) and o.thuocphongkhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa");
        q.setParameter("loai1", loai1);
        q.setParameter("loai2", loai2);
        q.setParameter("dtdmbankhamMa", maBanKham);
        q.setParameter("tiepdonMa", maTiepDon);
        return q.getResultList();
    }

    public List<ThuocPhongKham> findByMaTiepDon(String maTiepDon, String loai) {
        Query q = getEm().createQuery("select object(o) from ThuocPhongKham as o where o.thuocphongkhamLoai like :loai  and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND (o.thuocphongkhamStatus = '0' OR o.thuocphongkhamStatus Is Null)");
        q.setParameter("loai", loai);

        q.setParameter("tiepdonMa", maTiepDon);

        return q.getResultList();
    }

    public List<ThuocPhongKham> find2LoaiByMaTiepDon(String maTiepDon, String loai1, String loai2) {
        Query q = getEm().createQuery("select object(o) from ThuocPhongKham as o where (o.thuocphongkhamLoai like :loai1 or o.thuocphongkhamLoai like :loai2)  and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND (o.thuocphongkhamStatus = '0' OR o.thuocphongkhamStatus Is Null)");
        q.setParameter("loai1", loai1);
        q.setParameter("loai2", loai2);

        q.setParameter("tiepdonMa", maTiepDon);

        return q.getResultList();
    }

    public List<ThuocPhongKham> findByKhoaKham(String maTiepDon, String loai, int khoaMaSo) {
        String sQuery=" select object(o) from ThuocPhongKham as o ";
        sQuery+=" where o.thuocphongkhamLoai like :loai  and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa ";
        sQuery+=" and o.thuocphongkhamKhoa.dmkhoaMaso = :khoaMaSo ";
        Query q = getEm().createQuery(sQuery);
        q.setParameter("loai", loai);
        q.setParameter("tiepdonMa", maTiepDon);
        q.setParameter("khoaMaSo", khoaMaSo);
        return q.getResultList();
    }

    public List<ThuocPhongKham> findByThamKham(Integer thamkhamMa, String loai) {
        List<ThuocPhongKham> listTpk = null;

        // 20110125 bao.ttc: remove phan "t.thuocphongkhamMaphieud is null" de dung search List Thuoc theo Thamkham
        //Query q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamThamkham.thamkhamMa = :thamkhamMa And t.thuocphongkhamMaphieud is null And t.thuocphongkhamLoai = :loai");
        Query q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamThamkham.thamkhamMa = :thamkhamMa And t.thuocphongkhamLoai = :loai");
        q.setParameter("thamkhamMa", thamkhamMa);
        q.setParameter("loai", loai);
        try {
            listTpk = q.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listTpk;
    }

    public List<ThuocPhongKham> findByThamKhamVaNgay(Integer thamkhamMa, Date tuNgay, Date denNgay) {
        List<ThuocPhongKham> listTpk = null;

        Query q = getEm().createQuery("Select t From ThuocPhongKham t Where (t.thuocphongkhamThamkham.thamkhamMa = :thamkhamMa) And (t.thuocphongkhamNgaygio between :ngay1 and :ngay2) ");
        q.setParameter("thamkhamMa", thamkhamMa);
        q.setParameter("ngay1", tuNgay);
        q.setParameter("ngay2", denNgay);

        try {
            listTpk = q.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }

        return listTpk;
    }

    public List<ThuocPhongKham> findByThamKhamHoanThu(Integer thamkhamMa) {
        List<ThuocPhongKham> listTpk = null;
        Query q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamThamkham.thamkhamMa = :thamkhamMa And t.thuocphongkhamMaphieuh is null");
        q.setParameter("thamkhamMa", thamkhamMa);
        try {
            listTpk = q.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listTpk;
    }

    public List<ThuocPhongKham> findByMaPhieu(String maPhieu) {
        System.out.println("-----findByMaPhieu()-----");
        List<ThuocPhongKham> listTpk = null;
        maPhieu = Utils.formatMaPhieu(maPhieu);
        System.out.println("-----ma phieu (format): " + maPhieu);
        Query q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamMaphieud = :maPhieu");
        q.setParameter("maPhieu", maPhieu);
        try {
            listTpk = q.getResultList();
        } catch (Exception e) {
        }
        return listTpk;
    }

    //Tho add tim theo loai thuoc, doi tuong, khoa//chua chinh sua cau query
    public List<ThuocPhongKham> findDanhSachTNGTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMaso) {
        System.out.println("---Begin findDanhSachTNGTDuTruLinh()---");
        String sSQL = "select object(tpk) from ThuocPhongKham tpk, DmThuoc t, DmPhanNhomThuoc pnt, DmPhanLoaiThuoc plt, DmLoaiThuoc lt " +
                      "where tpk.thuocphongkhamMathuoc.dmthuocMaso = t.dmthuocMaso " +
                      "and t.dmphannhomthuocMaso.dmphannhomthuocMaso = pnt.dmphannhomthuocMaso " +
                      "and t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso " +
                      "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso " +
                      "and (:doituongMaso = 0 or tpk.thuocphongkhamChedott.dmdoituongMaso = :doituongMaso) " +
                      "and tpk.thuocphongkhamKhoa.dmkhoaMaso = :dmKhoaMaso " +
                      "and tpk.thuocphongkhamLoai = :thuocphongkhamLoai " +
                      "and tpk.thuocphongkhamMaPhieuDT IS NULL " +
                      "and ( tpk.thuocphongkhamNgaygio between :tuNgay and :denNgay ) ";
        String loaithuocMa = "";
        String phannhomthuocMa = "";
        String phanloaithuocMa = "";
        String filterByDVT ="";
        String  thuocphongkhamLoai = "1";//Xu tri thuoc ban kham
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
            System.out.println("dmphannhomthuocMa: " + phannhomthuocMa);
            System.out.println("dmloaithuocMa: " + loaithuocMa);
            System.out.println("thuocphongkhamLoai: " + thuocphongkhamLoai);
            sSQL +="order by tpk.thuocphongkhamMathuoc.dmthuocTen";
            System.out.println("sSQL = "+ sSQL);
            Query q = em.createQuery(sSQL);
            q.setParameter("doituongMaso", doituongMaso);
            q.setParameter("dmKhoaMaso", dmKhoaMaso);
            q.setParameter("tuNgay", tuNgay);
            q.setParameter("denNgay", denNgay);
            q.setParameter("thuocphongkhamLoai", thuocphongkhamLoai);
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
             List<ThuocPhongKham> returnList = q.getResultList();

            // System.out.println("End get list Thuoc Phong Kham:" + returnList);
            if (returnList != null && returnList.size() > 0) {
                System.out.println("Returnlist size = " + returnList.size());
                return returnList;
            }
            System.out.println("---End findDanhSachTNGTDuTruLinh()---");
        } 
        return null;
    }

    public Boolean createHoanThu(List<ThuocPhongKham> listTpk, HsThtoank hsttk) {
        System.out.println("-----createHoanThu-----");
        boolean result = false;


        boolean canMaPhieuHoan = false;
        if (listTpk != null && listTpk.size() > 0) {
            for (ThuocPhongKham tpk : listTpk) {
                if (tpk.getThuocphongkhamMaphieuh() == null || tpk.getThuocphongkhamMaphieuh().equals("")) {
                    canMaPhieuHoan = true;
                    break;
                }
            }
        }
        String maPhieuHoan = "";
        if (canMaPhieuHoan == true) {
            maPhieuHoan = Utils.createMaPhieu(getEm());
        } else {
            return false;
        }

        if (listTpk != null && listTpk.size() > 0) {
            System.out.println("-----listTpk: " + listTpk);
//            if (hsttk != null) {
//                System.out.println("-----cap nhat hsttk: " + hsttk.getHsthtoankMa());
//                em.merge(hsttk);
//            }
            for (ThuocPhongKham tpk : listTpk) {
                if (tpk.getThuocphongkhamMaphieuh() != null && !tpk.getThuocphongkhamMaphieuh().equals("")) {
                    return false;
                }

                System.out.println("-----tpk: " + tpk.getThuocphongkhamMa());
                System.out.println("-----ma phieu: " + tpk.getThuocphongkhamMaphieud());

                Integer maTpk = tpk.getThuocphongkhamMa();

                tpk.setThuocphongkhamMaphieuh(maPhieuHoan);
                if (maTpk != null) {
                    System.out.println("-----Update thuoc phong kham: " + maTpk);
                    getEm().merge(tpk);

                    /**
                     * Cap nhat so luong xuat trong kho
                     */
                    TonKho tk = tkFacade.getTonKhoHienTai(tpk.getThuocphongkhamMalk(),
                            tpk.getThuocphongkhamKhoa().getDmkhoaMaso());
                    TonKho tkNew;
                    try {
                        tkNew = (TonKho) BeanUtils.cloneBean(tk);
                        tkNew.setTonkhoMa(null);
                        //tkNew.setTonkhoTondau(null);
                        tkNew.setTonkhoNhap(null);
                        tkNew.setTonkhoXuat(null);
                        tkNew.setTonkhoTra(tpk.getThuocphongkhamTra());
                        tkNew.setTonkhoTon(null);
                        tkNew.setTonkhoNgaygiocn(new Date());
                        tkFacade.insertTonKho(tkNew);



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
            result = true;
        }
        return result;
    }

    public String createThuocPhongKham(List<ThuocPhongKham> listTpk, HsThtoank hsttk, String loai) {
        System.out.println("-----createThuocPhongKham-----");
        boolean result = false;
        String maPhieu = "";
        if (listTpk != null) {
            System.out.println("-----listTpk: " + listTpk);
            StringBuffer sb = new StringBuffer();
            int i = 0;
            String temp = Utils.maPhieuThuocPk(getEm());

            for (ThuocPhongKham tpk : listTpk) {
                System.out.println("-----tpk: " + tpk.getThuocphongkhamMa());
                System.out.println("-----ma phieu: " + tpk.getThuocphongkhamMaphieud());
                if (tpk.getThuocphongkhamMaphieud() != null) {
                    continue;
                }

                if (tpk.getThuocphongkhamNgaygio() == null) {
                    tpk.setThuocphongkhamNgaygio(new Date());
                }


                /**
                 * Cap nhat so luong xuat trong kho
                 */
                TonKho tk = tkFacade.getTonKhoHienTai(tpk.getThuocphongkhamMalk(),
                        tpk.getThuocphongkhamKhoa().getDmkhoaMaso());
                TonKho tkNew;
                try {
                    tkNew = (TonKho) BeanUtils.cloneBean(tk);
                    tkNew.setTonkhoMa(null);
                    tkNew.setTonkhoTondau(null);
                    tkNew.setTonkhoNhap(null);
                    tkNew.setTonkhoXuat(tpk.getThuocphongkhamSoluong());
                    tkNew.setTonkhoTra(null);
                    tkNew.setTonkhoTon(null);
                    tkNew.setTonkhoNgaygiocn(new Date());
                    tkFacade.insertTonKho(tkNew);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Integer maTpk = tpk.getThuocphongkhamMa();
                // cap ma phieu

                tpk.setThuocphongkhamMaphieud(temp);


                System.out.println("-----ma phieu: " + tpk.getThuocphongkhamMaphieud());
                if (maTpk != null) {
                    System.out.println("-----Update thuoc phong kham: " + maTpk);
                    getEm().merge(tpk);
                    if (i == 0) {
                        sb.append(maTpk.toString());
                        i++;
                    } else {
                        sb.append(", " + maTpk);
                    }
                } else {
                    System.out.print("-----Insert thuoc phong kham.");
                    System.out.print("-----tham kham: " + tpk.getThuocphongkhamThamkham());
                    getEm().persist(tpk);
                }
                maPhieu = tpk.getThuocphongkhamMaphieud();
            }

            try {
                System.out.print("-----Do not delete thuoc phong kham: " + sb.toString());
                Query q = null;
                if (!sb.toString().equals("")) {

                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamMa Not In (" + sb.toString() + ") " + " And t.thuocphongkhamThamkham = :thuocphongkhamThamkham" + " And t.thuocphongkhamMaphieud is null" + " And t.thuocphongkhamLoai = :loai");
                    q.setParameter("thuocphongkhamThamkham", listTpk.get(0).getThuocphongkhamThamkham());
                    q.setParameter("loai", loai);

                } else {
                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamThamkham = :thuocphongkhamThamkham" + " And t.thuocphongkhamMaphieud is null" + " And t.thuocphongkhamLoai = :loai");
                    q.setParameter("thuocphongkhamThamkham", listTpk.get(0).getThuocphongkhamThamkham());
                    q.setParameter("loai", loai);
                }

                List<ThuocPhongKham> listThuoc = q.getResultList();
                if (listThuoc != null) {
                    for (ThuocPhongKham tpk : listThuoc) {
                        getEm().remove(tpk);
                    }
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-----ma phieu1: " + maPhieu);
            if (!result) {
                System.out.println("-----ma phieu2: " + maPhieu);
                maPhieu = "";
            }

        }
        System.out.println("-----ma phieu3: " + maPhieu);
        return maPhieu;
    }

    public String createToaThuocPhongKham(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru) {
        System.out.println("-----createThuocPhongKham-----");
        System.out.println("-----Loai thuoc phong kham-----" + loaiThuocPhongKham);

        if(hsmListThuocDYNgoaiTru != null && hsmListThuocDYNgoaiTru.size() > 0){
            java.util.Set set = hsmListThuocDYNgoaiTru.entrySet();
            Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	List listThuocDY = (List)me.getValue();
	    	ThuocDongYNgoaiTru thuocDY = (ThuocDongYNgoaiTru)listThuocDY.get(0);
		Boolean isSavedDB = (Boolean)listThuocDY.get(1);
                if(isSavedDB.booleanValue()){
                    //kiem tra neu thuocDY.getThuocdongyMaso() co ton tai trong thuoc phong kham cua list listTpk thi cho update, khong thi delete
                    boolean founded = false;
                    System.out.println("Thuoc DY: "+ thuocDY.getThuocdongyMaso());
                    if(listTpk != null){
                        for(ThuocPhongKham tpk : listTpk){
                            if(tpk.getDmbaithuocMaso() != null && tpk.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                                founded = true;
                                break;
                            }
                        }
                    }

                    System.out.println("Ton tai thuoc dong y trong tpk: " + founded);
                    if(founded){
                        //true: update lai Thuoc Dong Y ngoai tru
                        getEm().merge(thuocDY);
                    }else{
                        //Xoa thuoc dong y ngoai tru ra khoi DB
                        thuocDY = thuocDYFacade.find(thuocDY.getThuocdongyMaso());
                        getEm().remove(thuocDY);
                    }

                }else{
                    //false: insert vao Thuoc dong y ngoai tru
                    getEm().persist(thuocDY);
                    //update lai thuoc phong kham bang thuocDY.getThuocdongyMaso() where voi thuocDYMaso la bai thuoc
                    for(int j = 0; j<listTpk.size(); j++){
                        ThuocPhongKham tpk = listTpk.get(j);
                        if(tpk.getDmbaithuocMaso() != null && tpk.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                            tpk.setThuocdongyNgoaiTru(thuocDY);
                            listTpk.set(j,tpk);
                        }
                    }
                }
            }
        }
        if (listTpk != null) {

            // 20101227 bao.ttc: set thamkham_captoa cho loai KE_TOA_BENH_NHAN_TU_MUA: "2"
            if(loaiThuocPhongKham.equalsIgnoreCase("2")){
                if(thamKham != null)
                    thamKham.setThamkhamCapToa(true);
            }
            getEm().merge(thamKham); // in this case we save benh chinh            
             
            //System.out.println("-----listTpk: " + listTpk);
            StringBuffer sb = new StringBuffer();
            int i = 0;

            for (ThuocPhongKham tpk : listTpk) {
                //System.out.println("-----tpk: " + tpk.getThuocphongkhamMa());
                //System.out.println("-----ma phieu: " + tpk.getThuocphongkhamMaphieud());
                if (tpk.getThuocphongkhamMaphieud() != null) {
                    continue;
                }

                if (tpk.getThuocphongkhamNgaygio() == null) {
                    tpk.setThuocphongkhamNgaygio(new Date());
                }


                Integer maTpk = tpk.getThuocphongkhamMa();

                //System.out.println("-----getThuocphongkhamMa: " + maTpk);
                if (maTpk != null) {

                    // 20110211 bao.ttc: ke toa benh nhan tu mua, update va thoat
                    if ("2".equals(loaiThuocPhongKham)) {

                        // 20110117 bao.ttc: set null vi khong co thong tin
                        tpk.setThuocphongkhamQuocgia(null);
                        tpk.setThuocphongkhamHangsx(null);

                        getEm().merge(tpk);

                        if (i == 0) {
                            sb.append(tpk.getThuocphongkhamMa().toString());
                            i++;
                        } else {
                            sb.append(", " + tpk.getThuocphongkhamMa().toString());
                        }

                        continue;
                    }

                    // 20110211 bao.ttc: TH ke toa ban kham, neu doi loai thuoc, doi SL sau do merge se sai !!!!!!!!
                    getEm().merge(tpk);

                } else {
                    //System.out.print("-----Insert thuoc phong kham.");
                    //System.out.print("-----tham kham: " + thamKham);

                    if ("2".equals(loaiThuocPhongKham)) { // ke toa benh nhan tu mua, luu moi va` tra ve

                        // 20110117 bao.ttc: set null vi khong co thong tin
                        tpk.setThuocphongkhamQuocgia(null);
                        tpk.setThuocphongkhamHangsx(null);

                        getEm().persist(tpk);

                        if (i == 0) {
                            sb.append(tpk.getThuocphongkhamMa().toString());
                            i++;
                        } else {
                            sb.append(", " + tpk.getThuocphongkhamMa().toString());
                        }

                        continue;
                    }

                    //System.out.println("---tim kiem hien tai - tru thuoc o kho---");
                    TonKhoFacade tonKhoFacade = new TonKhoFacade();
                    tonKhoFacade.setEm(getEm());


                    TonKho tk = tonKhoFacade.getTonKhoHienTai(tpk.getThuocphongkhamMalk(), tpk.getThuocphongkhamKhoa(true).getDmkhoaMaso());
                    //System.out.println("-----TonKho:" + tk);
                    if (tk == null || tk.getTonkhoTon() <= 0 ||
                            tk.getTonkhoTon() < ((tpk.getThuocphongkhamSoluong() == null ? 0 : tpk.getThuocphongkhamSoluong()) - (tpk.getThuocphongkhamTra() == null ? 0 : tpk.getThuocphongkhamTra()))) {

                        System.out.println("***** TK null or <0 or < SL tpk *****");
                        continue;
                    }
                    //System.out.println("*********************************2");


                    TonKho newtkxuat = null;
                    try {
                        // du so luong thuoc can thiet
                        //tk.getTonkhoTon().doubleValue()  >=  soluong.doubleValue()
                        newtkxuat = (TonKho) BeanUtils.cloneBean(tk);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    newtkxuat.setTonkhoMa(null);
                    newtkxuat.setTonkhoNhap(new Double(0));
                    newtkxuat.setTonkhoTra(new Double(0));

                    Double soluong =
                            (tpk.getThuocphongkhamSoluong() == null ? 0 : tpk.getThuocphongkhamSoluong()) - (tpk.getThuocphongkhamTra() == null ? 0 : tpk.getThuocphongkhamTra());


                    newtkxuat.setTonkhoXuat(soluong);

                    TonKhoFacade facadeTK = new TonKhoFacade();
                    facadeTK.setEm(getEm());
                    //newtknhap.setTonkhoMa(null);
                    newtkxuat.setTonkhoMa(null);

                    //facadeTK.insertTonKho(newtknhap);
                    facadeTK.insertTonKho(newtkxuat);
                    ///////////////////// end tim kiem hien tai - tru thuoc o kho
                    tpk.setThuocphongkhamQuocgia( tk.getDmquocgiaMaso());
                    tpk.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
                    getEm().persist(tpk);
                    //Tho add vao bang tam de luu TonkhoMa cung voi ma tiep don de phuc vu cho viec
                    //show so luong xuat khi xu ly thuoc tai ban kham trong in the kho cua kho chinh
                    //System.out.println("Insert vao ct_xuat_bh_thuocbk de ho tro bao cao");
                    //facadeTK.insertXuatKhoXuLyThuocBK(thamKham.getTiepdonMa().getTiepdonMa(), newtkxuat.getTonkhoMa(), newtkxuat.getTonkhoMalienket(), newtkxuat.getDmthuocMaso(), tpk.getThuocphongkhamMa());
                    //20-05-2011: Tho remove dong nay vi thuoc xu ly ban kham lay tai tu truc cua khoa kham benh.
                }

                if (i == 0) {
                    sb.append(tpk.getThuocphongkhamMa().toString());
                    i++;
                } else {
                    sb.append(", " + tpk.getThuocphongkhamMa());
                }

            }

            try {
                //if (!sb.toString().equals("")) {
                //System.out.print("-----Delete thuoc phong kham KO CO': " + sb.toString());
                Query q = null;
                if ("".equals(sb.toString())) {
                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamLoai like :loaiThuocPhongKham and t.thuocphongkhamThamkham = :thuocphongkhamThamkham And t.thuocphongkhamMaphieud is null");

                } else {
                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamLoai like :loaiThuocPhongKham and  t.thuocphongkhamMa Not In (" + sb.toString() + ") And t.thuocphongkhamThamkham = :thuocphongkhamThamkham And t.thuocphongkhamMaphieud is null");

                }

                q.setParameter("loaiThuocPhongKham", loaiThuocPhongKham);
                q.setParameter("thuocphongkhamThamkham", thamKham);

                List<ThuocPhongKham> listThuoc = q.getResultList();

                //System.out.print("-----DANH DACH CAN XOA: " + listThuoc);

                //System.out.print("---listThuoc: " + listThuoc.size());
                if (listThuoc != null) {
                    for (ThuocPhongKham tpk : listThuoc) {

                        if ("2".equals(loaiThuocPhongKham)) { // ke toa benh nhan tu mua, luu moi va` tra ve

                            getEm().remove(tpk);
                            continue;
                        }

                        // tra thuoc ve kho
                        // them thuoc o kho

                        TonKhoFacade tonKhoFacade = new TonKhoFacade();
                        tonKhoFacade.setEm(getEm());
                        TonKho tk = tonKhoFacade.getTonKhoHienTai(tpk.getThuocphongkhamMalk(), tpk.getThuocphongkhamKhoa(true).getDmkhoaMaso());

                        if (tk == null || tk.getTonkhoTon() <= 0) {

                            //System.out.println("*********************************3");
                            //System.out.println("*********************************3");
                            continue;
                        }
                        //System.out.println("*********************************4");


                        TonKho newtknhap = null;
                        try {
                            // du so luong thuoc can thiet
                            //tk.getTonkhoTon().doubleValue()  >=  soluong.doubleValue()
                            newtknhap = (TonKho) BeanUtils.cloneBean(tk);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(ThuocPhongKhamFacade.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        newtknhap.setTonkhoMa(null);

                        Double soluong =
                                (tpk.getThuocphongkhamSoluong() == null ? 0 : tpk.getThuocphongkhamSoluong()) - (tpk.getThuocphongkhamTra() == null ? 0 : tpk.getThuocphongkhamTra());

                        newtknhap.setTonkhoNhap(new Double(0));
                        newtknhap.setTonkhoTra(soluong);
                        newtknhap.setTonkhoXuat(new Double(0));

                        TonKhoFacade facadeTK = new TonKhoFacade();
                        facadeTK.setEm(getEm());

                        newtknhap.setTonkhoMa(null);

                        facadeTK.insertTonKho(newtknhap);

///////////////////// them thuoc o kho
                        getEm().remove(tpk);
                        //remove thuoc kham trong bang ct_xuat_bh_thuocbk
                        String sSQL = "select object(t) from CtXuatBhThuocbk t where t.thuocphongkhamMa = :thuocphongkhamMa";
                        q = getEm().createQuery(sSQL);
                        q.setParameter("thuocphongkhamMa", tpk.getThuocphongkhamMa());
                        List<CtXuatBhThuocbk> list = q.getResultList();
                        if(list != null && list.size()>0)
                        {
                            for(CtXuatBhThuocbk ctTPK :list){
                                getEm().remove(ctTPK);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    //**** Luu ke toa quay benh vien
    public String createKeToaQuayBenhVien(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru) {
        //System.out.println("-----createKeToaQuayBenhVien-----");
        //System.out.println("-----Loai thuoc phong kham-----" + loaiThuocPhongKham);
        if(hsmListThuocDYNgoaiTru != null && hsmListThuocDYNgoaiTru.size() > 0){
            java.util.Set set = hsmListThuocDYNgoaiTru.entrySet();
            Iterator i = set.iterator();
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	List listThuocDY = (List)me.getValue();
	    	ThuocDongYNgoaiTru thuocDY = (ThuocDongYNgoaiTru)listThuocDY.get(0);
		Boolean isSavedDB = (Boolean)listThuocDY.get(1);
                if(isSavedDB.booleanValue()){
                    //kiem tra neu thuocDY.getThuocdongyMaso() co ton tai trong thuoc phong kham cua list listTpk thi cho update, khong thi delete
                    boolean founded = false;
                    System.out.println("Thuoc DY: "+ thuocDY.getThuocdongyMaso());
                    if(listTpk != null){
                        for(ThuocPhongKham tpk : listTpk){
                            if(tpk.getDmbaithuocMaso() != null && tpk.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                                founded = true;
                                break;
                            }
                        }
                    }

                    System.out.println("Ton tai thuoc dong y trong tpk: " + founded);
                    if(founded){
                        //true: update lai Thuoc Dong Y ngoai tru
                        getEm().merge(thuocDY);
                    }else{
                        //Xoa thuoc dong y ngoai tru ra khoi DB
                        thuocDY = thuocDYFacade.find(thuocDY.getThuocdongyMaso());
                        getEm().remove(thuocDY);
                    }

                }else{
                    //false: insert vao Thuoc dong y ngoai tru
                    getEm().persist(thuocDY);
                    //update lai thuoc phong kham bang thuocDY.getThuocdongyMaso() where voi thuocDYMaso la bai thuoc
                    for(int j = 0; j<listTpk.size(); j++){
                        ThuocPhongKham tpk = listTpk.get(j);
                        if(tpk.getDmbaithuocMaso() != null && tpk.getDmbaithuocMaso().equals(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso())){
                            tpk.setThuocdongyNgoaiTru(thuocDY);
                            listTpk.set(j,tpk);
                        }
                    }
                }
            }
        }
        if (listTpk != null) {            
            // 20101227 bao.ttc: set thamkham_captoa
            if(thamKham != null)
                thamKham.setThamkhamCapToa(true);
            getEm().merge(thamKham); // in this case we save benh chinh           

            //System.out.println("-----listTpk: " + listTpk);
            StringBuffer sb = new StringBuffer();
            int i = 0;

            for (ThuocPhongKham tpk : listTpk) {
                //System.out.println("-----tpk: " + tpk.getThuocphongkhamMa());
                //System.out.println("-----ma phieu: " + tpk.getThuocphongkhamMaphieud());
                if (tpk.getThuocphongkhamMaphieud() != null) {
                    continue;
                }

                if (tpk.getThuocphongkhamNgaygio() == null) {
                    tpk.setThuocphongkhamNgaygio(new Date());
                }
                Integer maTpk = tpk.getThuocphongkhamMa();

                //System.out.println("-----maTpk: " + maTpk);
                if (maTpk != null) {
                    //System.out.println("-----Update thuoc phong kham: " + maTpk);
                    getEm().merge(tpk);

                } else {
                    //System.out.print("-----Insert thuoc phong kham.");
                    //System.out.print("-----tham kham: " + thamKham);
                    String malk = tpk.getThuocphongkhamMalk();
                    Integer khoaMaso = tpk.getThuocphongkhamKhoa().getDmkhoaMaso();
                    System.out.println("ma lk = "+malk);
                    System.out.println("khoaMaso = "+khoaMaso);
                    TonKhoFacade tonKhoFacade = new TonKhoFacade();
                    tonKhoFacade.setEm(getEm());
                    TonKho tk = tonKhoFacade.getTonKhoHienTai(malk, khoaMaso);
                    tpk.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
                    tpk.setThuocphongkhamQuocgia(tk.getDmquocgiaMaso());
                    getEm().persist(tpk);
                }

                if (i == 0) {
                    sb.append(tpk.getThuocphongkhamMa().toString());
                    i++;
                } else {
                    sb.append(", " + tpk.getThuocphongkhamMa());
                }
            }

            try {
                //if (!sb.toString().equals("")) {
                //System.out.print("-----Delete thuoc phong kham KO CO': " + sb.toString());
                Query q = null;
                if ("".equals(sb.toString())) {
                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamLoai like :loaiThuocPhongKham and t.thuocphongkhamThamkham = :thuocphongkhamThamkham And t.thuocphongkhamMaphieud is null");

                } else {
                    q = getEm().createQuery("Select t From ThuocPhongKham t Where t.thuocphongkhamLoai like :loaiThuocPhongKham and  t.thuocphongkhamMa Not In (" + sb.toString() + ") And t.thuocphongkhamThamkham = :thuocphongkhamThamkham And t.thuocphongkhamMaphieud is null");

                }

                q.setParameter("loaiThuocPhongKham", loaiThuocPhongKham);
                q.setParameter("thuocphongkhamThamkham", thamKham);

                List<ThuocPhongKham> listThuoc = q.getResultList();

                //System.out.print("-----DANH DACH CAN XOA: " + listThuoc);

                //System.out.print("---listThuoc: " + listThuoc.size());
                if (listThuoc != null) {
                    for (ThuocPhongKham tpk : listThuoc) {

                        if ("2".equals(loaiThuocPhongKham)) { // ke toa benh nhan tu mua, luu moi va` tra ve

                            getEm().remove(tpk);
                            continue;
                        }
                        getEm().remove(tpk);
                    }
                }
                //}
                //result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    //them dk THUOCPHONGKHAM_MAPHIEUD=null
    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKhamForCapNhatPhieuXuatBH(String maTiepDon, String maBanKham, String loai) {
        Query q = getEm().createQuery("select object(o) from ThuocPhongKham as o where o.thuocphongkhamLoai like :loai and o.thuocphongkhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa and o.thuocphongkhamMaphieud = null");
        q.setParameter("loai", loai);
        q.setParameter("dtdmbankhamMa", maBanKham);
        q.setParameter("tiepdonMa", maTiepDon);

        return q.getResultList();
    }
    /*
     * Thanh add 29/09/2011
     * Đã fixed
     */
    // Set field DaTT thanh null theo thoi gian tiep don (de thuc hien chuc nang thanh toan hang loat)
    public int updateDaTT2Null(Date tungay, Date denngay) {
        try {            
            String sSQL = "UPDATE thuoc_phong_kham tpk, tham_kham tk, tiep_don td SET tpk.THUOCPHONGKHAM_DATT = null" +
            " WHERE tpk.THUOCPHONGKHAM_THAMKHAM = tk.THAMKHAM_MA" +
            " AND tk.TIEPDON_MA = td.TIEPDON_MA" +        
            " AND TO_DATE(td.TIEPDON_NGAYGIO) >= :tungay " +
            " AND TO_DATE(td.TIEPDON_NGAYGIO) <= :denngay ";
            Query q = getEm().createNativeQuery(sSQL, ThuocPhongKham.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);            
            return q.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }           
    }
    
    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}






