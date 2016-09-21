/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DmThuocFacade implements DmThuocFacadeLocal , DmThuocFacadeRemote{
    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(DmThuoc dmThuoc) {
        em.persist(dmThuoc);
    }

    public void edit(DmThuoc dmThuoc) {
        em.merge(dmThuoc);
    }

    public void remove(DmThuoc dmThuoc) {
        em.remove(em.merge(dmThuoc));
    }

    public DmThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmThuoc.class, id);
    }

    public List<DmThuoc> findAll() {
        return em.createQuery("select object(o) from DmThuoc o where o.dmthuocDt = 1 order by dmthuocTen").getResultList();
    }

    // oracle ok
    public List<DmThuoc> findDongYAll() {
        String sSQL = "select t.* from Dm_Thuoc t, dm_phan_loai_thuoc plt, dm_loai_thuoc lt "+
                       "where t.dmthuoc_Dt = 1 and t.dmphanloaithuoc_Maso = plt.dmphanloaithuoc_Maso "+
                       "and plt.dmloaithuoc_Maso = lt.dmloaithuoc_Maso and lt.dmloaithuoc_Ma = 'DY' order by dmthuoc_Ten";
        return em.createNativeQuery(sSQL,DmThuoc.class).getResultList();
    }

    //oracle ok
    public List<DmThuoc> findAll(Integer dmKhoaMaso) {
        String sSQL = "select t.* from dm_thuoc t, (select tk.dmthuoc_maso from ton_kho tk, (select max(tk1.tonkho_ma) tonkho_ma from ton_kho tk1 where tk1.dmkhoa_maso = :dmkhoa_maso group by tk1.tonkho_malienket) tkht where tk.tonkho_ma = tkht.tonkho_ma and tk.tonkho_ton > 0 group by tk.dmthuoc_maso) tk2 where tk2.dmthuoc_maso = t.dmthuoc_maso and t.dmthuoc_Dt = 1 order by t.dmthuoc_ten";
        Query query = em.createNativeQuery(sSQL,DmThuoc.class);
        query.setParameter("dmkhoa_maso", dmKhoaMaso);
        return query.getResultList();
    }

    public List<DmThuoc> findAll(String dmKhoaMa) {
        String sSQL = "select t.* from dm_thuoc t where t.dmthuoc_Dt = 1 ";
        if(dmKhoaMa.equals("KCH")){
            sSQL += "and t.dmthuoc_istonkhokc = 1 ";
        }else if(dmKhoaMa.equals("KBH")){
            sSQL += "and t.dmthuoc_istonkhobh = 1 ";
        }else if(dmKhoaMa.equals("KNT")){
            sSQL += "and t.dmthuoc_istonkhont = 1 ";
        }else if(dmKhoaMa.equals("KTE")){
            sSQL += "and t.dmthuoc_istonkhote = 1 ";
        }
        sSQL += "order by t.dmthuoc_ten";
        Query query = em.createNativeQuery(sSQL,DmThuoc.class);
        return query.getResultList();
    }
    //oracle ok
    public List<DmThuoc> findAll(Integer dmKhoaMaso1, Integer dmKhoaMaso2) {
        String sSQL = "select t.* from " +
                    "(select t.* from dm_thuoc t, "+
                    "(select tk.dmthuoc_maso from ton_kho tk, (select max(tk1.tonkho_ma) tonkho_ma from ton_kho tk1 where tk1.dmkhoa_maso = :dmkhoa_maso1 group by tk1.tonkho_malienket) tkht where tk.tonkho_ma = tkht.tonkho_ma and tk.tonkho_ton > 0 group by tk.dmthuoc_maso) tkKC " +
                    "where tkKC.dmthuoc_maso = t.dmthuoc_maso and t.dmthuoc_Dt = 1 "+
                    "UNION " +
                    "select t.* from dm_thuoc t, " +
                    "(select tk.dmthuoc_maso from ton_kho tk, (select max(tk1.tonkho_ma) tonkho_ma from ton_kho tk1 where tk1.dmkhoa_maso = :dmkhoa_maso2 group by tk1.tonkho_malienket) tkht where tk.tonkho_ma = tkht.tonkho_ma and tk.tonkho_ton > 0 group by tk.dmthuoc_maso) tkKNT "+
                    "where t.dmthuoc_maso = tkKNT.dmthuoc_maso and t.dmthuoc_Dt = 1 ) t " +
                    "order by t.dmthuoc_ten";
        Query query = em.createNativeQuery(sSQL,DmThuoc.class);
        query.setParameter("dmkhoa_maso1", dmKhoaMaso1);
        query.setParameter("dmkhoa_maso2", dmKhoaMaso2);
        return query.getResultList();
    }

    public DmThuoc findByMaThuoc(String dmthuocMa) {
        Query q = em.createQuery("select object(o) from DmThuoc as o where o.dmthuocMa = :dmthuocMa and o.dmthuocDt = 1");
        q.setParameter("dmthuocMa", dmthuocMa);
        List<DmThuoc> list  = q.getResultList();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public DmThuoc findByTenThuoc(String dmthuocTen) {
        Query q = em.createQuery("select object(o) from DmThuoc as o where o.dmthuocTen = :dmthuocTen and o.dmthuocDt = 1");
        q.setParameter("dmthuocTen", dmthuocTen);
        List<DmThuoc> list  = q.getResultList();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
    //Tho add
    public List<DmThuoc> findByLoaiPhanLoaiThuoc(String loaiThuocMa, String phanloaiThuocMa) {
        //System.out.println("Begin: findByLoaiPhanLoaiThuoc()");
        String sSQL = "select object(t) from DmThuoc t, DmLoaiThuoc lt, DmPhanLoaiThuoc plt " +
                    "where t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso "+
                    "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso and t.dmthuocDt = 1 ";
        loaiThuocMa = loaiThuocMa.trim();
        phanloaiThuocMa = phanloaiThuocMa.trim();
        if( loaiThuocMa.equals("") && phanloaiThuocMa.equals("") )
        {
            List<DmThuoc> list  = findAll();
            if( list != null && list.size() > 0 ){
                return list;
            }
        }else if( !loaiThuocMa.equals("") ){
            sSQL =sSQL+ "and lt.dmloaithuocMa = :dmloaithuocMa ";
            if ( !phanloaiThuocMa.equals("") )
                sSQL = sSQL + "and plt.dmphanloaithuocMa = :dmphanloaithuocMa";
            sSQL = sSQL + " order by t.dmthuocTen";
            Query q = em.createQuery(sSQL);
            q.setParameter("dmloaithuocMa", loaiThuocMa);
            if ( !phanloaiThuocMa.equals("") )
                q.setParameter("dmphanloaithuocMa", phanloaiThuocMa);

            List<DmThuoc> list  = q.getResultList();
            if(list != null && list.size() > 0){
               return list;
            }
        }
        return null;
    }

    //oracle ok
    //Tho add: get danh muc thuoc han che theo kho va ton kho > 0 cua kho do
    public List<DmThuoc> findByLoaiPhanLoaiThuocNhomThuocDVTKho(String loaiMa, String phanloaiThuocMa, Integer khoMaso) {
        //System.out.println("Begin: findByLoaiPhanLoaiThuocNhomThuocDVTKho");
        String sSQL = "select t.* from Dm_Thuoc t, Dm_Loai_Thuoc lt, Dm_Phan_Loai_Thuoc plt, Dm_Phan_Nhom_Thuoc pnt, " +
                      "(select tk.dmthuoc_maso from ton_kho tk, (select max(tk1.tonkho_ma) tonkho_ma from ton_kho tk1 where tk1.dmkhoa_maso = :dmkhoa_maso group by tk1.tonkho_malienket) tkht where tk.tonkho_ma = tkht.tonkho_ma and tk.tonkho_ton > 0 group by tk.dmthuoc_maso) tkht " +
                      "where t.dmphanloaithuoc_Maso = plt.dmphanloaithuoc_Maso " +
                      "and plt.dmloaithuoc_Maso = lt.dmloaithuoc_Maso " +
                      "and t.dmphannhomthuoc_Maso = pnt.dmphannhomthuoc_Maso " +
                      "and tkht.dmthuoc_maso = t.dmthuoc_maso and t.dmthuoc_Dt = 1 ";
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
                        sSQL += "and t.dmdonvitinh_Maso " + filterByDVT + " ";
                    }else{
                        phannhomthuocMa = phannhomMaDVT;
                    }
                    sSQL += "and pnt.dmphannhomthuoc_Ma = :dmphannhomthuocMa ";
                }else if(loaithuocMa.equals("DY")){
                    phanloaithuocMa = phannhomMaDVT;//DD, DD2
                    sSQL += "and plt.dmphanloaithuocMa = :dmphanloaithuocMa ";
                }
                sSQL += "and lt.dmloaithuoc_Ma = :dmloaithuocMa ";
            }else{
                loaithuocMa =  loaiMa;
                sSQL += "and lt.dmloaithuoc_Ma = :dmloaithuocMa ";
            }

            if ( !phanloaiThuocMa.equals("") ){
                sSQL = sSQL + "and plt.dmphanloaithuoc_Ma = :dmphanloaithuocMa ";
            }
            sSQL = sSQL + " order by t.dmthuoc_Ten";
            //System.out.println("sSQL: " + sSQL);
            Query q = em.createNativeQuery(sSQL, DmThuoc.class);
            q.setParameter("dmkhoa_maso", khoMaso);
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

            if ( !phanloaiThuocMa.equals("") ){
                q.setParameter("dmphanloaithuocMa", phanloaiThuocMa);
            }
            List<DmThuoc> list  = q.getResultList();
            if(list != null && list.size() > 0){
               return list;
            }
        }
        //System.out.println("End: findByLoaiPhanLoaiThuocNhomThuocDVTKho");
        return null;
    }

    //Tho add
    public List<DmThuoc> findByLoai_ListPhanLoaiThuoc(String loaiThuocMa, List<DmPhanLoaiThuoc> listPhanloaiThuocMa) {
        //System.out.println("Begin: findByLoai_ListPhanLoaiThuoc()");
        String sSQL = "select object(t) from DmThuoc t, DmLoaiThuoc lt, DmPhanLoaiThuoc plt " +
                    "where t.dmphanloaithuocMaso.dmphanloaithuocMaso = plt.dmphanloaithuocMaso "+
                    "and plt.dmloaithuocMaso.dmloaithuocMaso = lt.dmloaithuocMaso and t.dmthuocDt = 1 ";
        loaiThuocMa = loaiThuocMa.trim();
        if( loaiThuocMa.equals(""))
        {
            List<DmThuoc> list  = findAll();
            if( list != null && list.size() > 0 ){
                return list;
            }
        }else if( !loaiThuocMa.equals("") ){
            sSQL =sSQL+ "and lt.dmloaithuocMa = :dmloaithuocMa ";
            if(listPhanloaiThuocMa != null && listPhanloaiThuocMa.size()>0){
                String setListPhanLoaiThuocMa = "IN ('";
                for(DmPhanLoaiThuoc o:listPhanloaiThuocMa){
                    setListPhanLoaiThuocMa =  setListPhanLoaiThuocMa + o.getDmphanloaithuocMa() + "','";
                }
                setListPhanLoaiThuocMa = setListPhanLoaiThuocMa + "')";
                sSQL = sSQL + "and plt.dmphanloaithuocMa " + setListPhanLoaiThuocMa;
            }
            sSQL = sSQL + " order by t.dmthuocTen";
            Query q = em.createQuery(sSQL);
            q.setParameter("dmloaithuocMa", loaiThuocMa);

            List<DmThuoc> list  = q.getResultList();
            if(list != null && list.size() > 0){
               return list;
            }
        }
        return null;
    }

    //Tho add
    public boolean hasThuoInPhanLoaiThuoc(Integer phanloaiThuocMaso){
        boolean hasFoundThuoc = false;
        String sSQL = "select object(t) from DmThuoc t where t.dmphanloaithuocMaso.dmphanloaithuocMaso = :dmphanloaithuocMaso and t.dmthuocDt = 1";
        Query q = em.createQuery(sSQL);
        q.setParameter("dmphanloaithuocMaso", phanloaiThuocMaso);
        List<DmThuoc> list  = q.getResultList();
        if(list != null && list.size() > 0){
          hasFoundThuoc = true;
        }
        return hasFoundThuoc;
    }

    public List<DmThuoc> findDmThuocBHYT() {
        //System.out.println("Begin List<DmThuoc> findDmThuocBHYT() method");
        List<DmThuoc> result = null;
        try {
            String sql = "select distinct tk.dmthuocMaso from TonKho tk where tk.dmKhoa.nhomMa like 'BHYT'";
            Query q = em.createQuery(sql);
            result = q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        //System.out.println("End List<DmThuoc> findDmThuocBHYT() method");
        return result;
    }

    //Tho addd
    public boolean updateFieldTonKho(){
        //System.out.println("Begin updateFieldTonKho method");
        try{
            String updateAll = "update dm_thuoc set dmthuoc_istonkhokc = 0, dmthuoc_istonkhont = 0, dmthuoc_istonkhobh = 0,dmthuoc_istonkhote = 0";
            String updateDMTKCH = "update dm_thuoc set dmthuoc_istonkhokc = 1 where dmthuoc_maso in (select distinct dmthuoc_maso from v_tonkho_khoa_hientai tkht, dm_khoa k where tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KCH' and tkht.tonkho_ton > 0)";
            String updateDMTKNT = "update dm_thuoc set dmthuoc_istonkhont = 1 where dmthuoc_maso in (select distinct dmthuoc_maso from v_tonkho_khoa_hientai tkht, dm_khoa k where tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KNT' and tkht.tonkho_ton > 0)";
            String updateDMTKBH = "update dm_thuoc set dmthuoc_istonkhobh = 1 where dmthuoc_maso in (select distinct dmthuoc_maso from v_tonkho_khoa_hientai tkht, dm_khoa k where tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KBH' and tkht.tonkho_ton > 0)";
            String updateDMTKTE = "update dm_thuoc set dmthuoc_istonkhote = 1 where dmthuoc_maso in (select distinct dmthuoc_maso from v_tonkho_khoa_hientai tkht, dm_khoa k where tkht.dmkhoa_maso = k.dmkhoa_maso and k.dmkhoa_ma = 'KTE' and tkht.tonkho_ton > 0)";
            Query q1 = em.createNativeQuery(updateAll);
            int result = q1.executeUpdate();
            if(result != -1){
                q1 = em.createNativeQuery(updateDMTKCH);
                q1.executeUpdate();
                q1 = em.createNativeQuery(updateDMTKNT);
                q1.executeUpdate();
                q1 = em.createNativeQuery(updateDMTKBH);
                q1.executeUpdate();
                q1 = em.createNativeQuery(updateDMTKTE);
                q1.executeUpdate();
            }
            //System.out.println("End updateFieldTonKho method");
            return true;
        }catch(Exception er){
            er.printStackTrace();
            getContext().setRollbackOnly();
            return false;
        }
    }

    public SessionContext getContext() {
        return context;
    }

    public void setContext(SessionContext context) {
        this.context = context;
    }
}

