/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
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
public class KiemKeKhoFacade implements KiemKeKhoFacadeLocal, KiemKeKhoFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private TonKhoFacadeLocal tkFacade;

    public void create(KiemKeKho kiemKeKho) {
        em.persist(kiemKeKho);
    }

    public void edit(KiemKeKho kiemKeKho) {
        em.merge(kiemKeKho);
    }

    public void remove(KiemKeKho kiemKeKho) {
        em.remove(em.merge(kiemKeKho));
    }

    public KiemKeKho find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.KiemKeKho.class, id);
    }

    public Number getAllKiemKeKhoTotal(String maPhieuKiem){
        Query query = em.createQuery("select count(i) from KiemKeKho i where i.kiemkeMaphieukiem.maphieukiem = :maPhieuKiem");
        query.setParameter("maPhieuKiem", maPhieuKiem);
        return (Number) query.getSingleResult();
    }

    public List<KiemKeKho> getItemKiemKeKhos(String maPhieuKiem, int limit, int offset) {
        System.out.println("---find List<KiemKeKho> getItemKiemKeKhos");
        try {
            Query query = em.createQuery("select object(o) from KiemKeKho as o where o.kiemkeMaphieukiem.maphieukiem = :maPhieuKiem");
            query.setParameter("maPhieuKiem", maPhieuKiem);

            List<KiemKeKho> list = query.setFirstResult(offset).setMaxResults(limit).getResultList();
            System.out.println("---find List<KiemKeKho> findByMaPhieuKiem" + list.size());
            if (list != null && list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error KiemKeKho findByMaPhieuKiem find: " + e);
        }
        return null;
    }
    
    public String getMaPhieu() {
        String maPhieu = Utils.maPhieuKiemKe(em);
        return maPhieu;
    }

    public List<KiemKeKho> findAll() {
        return em.createQuery("select object(o) from KiemKeKho as o").getResultList();
    }
    
    public KiemKeKho findByKhoAndMaLienKet(String maKho, String maLk){
        System.out.println("---find KiemKeKho ByKhoAndMaLienKet"); 
         try {
            Query query = em.createQuery("select object(o) from KiemKeKho as o where o.dmkhoaMaso.dmkhoaMa = :maKho and o.kiemkeMalienket = :maLk");
            query.setParameter("maKho", maKho);
            query.setParameter("maLk", maLk);
            List<KiemKeKho> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (KiemKeKho) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error KiemKeKho find: " + e);
        }
        return null;
    }
    
    public List<KiemKeKho> findByMaPhieuKiem(String maPhieuKiem){
        System.out.println("---find List<KiemKeKho> findByMaPhieuKiem"); 
         try {             
            Query query = em.createQuery("select object(o) from KiemKeKho as o where o.kiemkeMaphieukiem.maphieukiem = :maPhieuKiem");
            System.out.println("---find List<KiemKeKho> findByMaPhieuKiem" + query.toString()); 
            query.setParameter("maPhieuKiem", maPhieuKiem);  
            System.out.println("---find List<KiemKeKho> findByMaPhieuKiem" + query.toString()); 
            List<KiemKeKho> list = query.getResultList();
            System.out.println("---find List<KiemKeKho> findByMaPhieuKiem" + list.size()); 
            if (list != null && list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error KiemKeKho findByMaPhieuKiem find: " + e);
        }
        return null;
    }
    
    public void updateKiemKeKho(List<KiemKeKho> listKiemKeKho){
        System.out.println("---update list KiemKeKho"); 
        try{
            for(KiemKeKho kkk: listKiemKeKho){
                em.merge(kkk);
                updateTonKho(kkk);
            }
            System.out.println("---end update list KiemKeKho"); 
        }catch (Exception e){
            e.printStackTrace();            
        }        
    }
    
    public String updateAndEditTonKhoDau(KiemKeKho obj){
        System.out.println("---update KiemKeKho"); 
        try{
            em.merge(obj);
            updateTonKho(obj);
            return obj.getKiemkeMalienket();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }        
    }
    
    private void updateTonKho(KiemKeKho obj){
        TonKho tk = tkFacade.find(obj.getTonkhoMa());
        if (tk!=null){
            tk.setTonkhoTondau(obj.getKiemkeTontt());
            tk.setTonkhoTon(obj.getKiemkeTontt());
            tk.setTonkhoMaphieukiem(null);
            tkFacade.edit(tk);
        }
    }
    
    public List<KiemKeKho> findKiemKeKhoForCapNhatSL(){
        System.out.println("---findKiemKeKhoForCapNhatSL"); 
        Query query = em.createQuery("select o from KiemKeKho o where (o.kiemkeMa in (select max(o1.kiemkeMa) from KiemKeKho o1 group by o1.kiemkeMalienket))");
        return query.getResultList();        
    }
    
    public List<KiemKeKho> findKiemKeKhoForCapNhatSL(String khoa, String thuoc, String nct, String nkp, String nsx, String quocgia, String namnhap, String ngay, String thang, String nam){
        System.out.println("---findKiemKeKhoForCapNhatSL(param)"); 
        Query query = em.createQuery("select o from KiemKeKho o where (o.kiemkeMa in (select max(o1.kiemkeMa) from KiemKeKho o1 group by o1.kiemkeMalienket)) " +
                "and (:khoa is null or :khoa = o.dmkhoaMaso.dmkhoaMa) " +
                "and (:thuoc is null or :thuoc = o.dmthuocMaso.dmthuocMa) " +
                "and (:nct is null or :nct = o.dmnctMaso.dmnctMa) " +
                "and (:nkp is null or :nkp = o.dmnguonkinhphiMaso.dmnguonkinhphiMa) " +
                "and (:nsx is null or :nsx = o.dmnhasanxuatMaso.dmnhasanxuatMa) " +
                "and (:quocgia is null or :quocgia = o.dmquocgiaMaso.dmquocgiaMa) " +
                "and (:namnhap is null or :namnhap = o.kiemkeNamnhap) " +
                "and (:ngay is null or :ngay = o.kiemkeNgayhandung) " +
                "and (:thang is null or :thang = o.kiemkeThanghandung) " +
                "and (:nam is null or :nam = o.kiemkeNamhandung)" +
                "and o.kiemkeMaphieukiem is not null");
        query.setParameter("khoa", khoa);
        query.setParameter("thuoc", thuoc);
        query.setParameter("nct", nct);
        query.setParameter("nkp", nkp);
        query.setParameter("nsx", nsx);
        query.setParameter("quocgia", quocgia);
        query.setParameter("namnhap", namnhap);
        query.setParameter("ngay", ngay);
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);
        return query.getResultList();        
    }

     public List<KiemKeKho> findKiemKeKhoForCapNhatSLGUI(String makiemke, Integer loaiThuocMaso, Integer phanloaiThuocMaso, Integer thuocMaso, Integer nctMaso, Integer nkpMaso){
        System.out.println("---findKiemKeKhoForCapNhatSLGUI(param)");
        String sSQL = "select o from KiemKeKho o where o.kiemkeMaphieukiem.maphieukiem = :maphieukiem "+
                "and (:dmloaithuocMaso is null or :dmloaithuocMaso = o.dmthuocMaso.dmphanloaithuocMaso.dmloaithuocMaso.dmloaithuocMaso) " +
                "and (:dmphanloaithuocMaso is null or :dmphanloaithuocMaso = o.dmthuocMaso.dmphanloaithuocMaso.dmphanloaithuocMaso) " +
                "and (:thuoc is null or :thuoc = o.dmthuocMaso.dmthuocMaso) " +
                "and (:nct is null or :nct = o.dmnctMaso.dmnctMaso) " +
                "and (:nkp is null or :nkp = o.dmnguonkinhphiMaso.dmnguonkinhphiMaso) ";
        System.out.println("makiemke: "+ makiemke);
        System.out.println("loaiThuocMaso: "+ loaiThuocMaso);
        System.out.println("phanloaiThuocMaso: "+ phanloaiThuocMaso);
        System.out.println("thuocMaso: "+ thuocMaso);
        System.out.println("nctMaso: "+ nctMaso);
        System.out.println("nkpMaso: "+ nkpMaso);
        System.out.println("sSQL: "+ sSQL);
        Query query = em.createQuery(sSQL);
        query.setParameter("maphieukiem", makiemke);
        query.setParameter("dmloaithuocMaso", loaiThuocMaso);
        query.setParameter("dmphanloaithuocMaso", phanloaiThuocMaso);
        query.setParameter("thuoc", thuocMaso);
        query.setParameter("nct", nctMaso);
        query.setParameter("nkp", nkpMaso);
        List<KiemKeKho> lstKiemKeKho = query.getResultList();
        System.out.println("lstKiemKeKho.size: "+ lstKiemKeKho.size());
        if(lstKiemKeKho != null || lstKiemKeKho.size() >0){
            return lstKiemKeKho;
        }

        return null;
    }
}


