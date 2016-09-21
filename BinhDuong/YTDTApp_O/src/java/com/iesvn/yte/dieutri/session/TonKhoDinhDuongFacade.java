/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.TonKhoDinhDuong;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class TonKhoDinhDuongFacade implements TonKhoDinhDuongFacadeLocal, TonKhoDinhDuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(TonKhoDinhDuong tonKhoDinhDuong) {
        em.persist(tonKhoDinhDuong);
    }

    public void edit(TonKhoDinhDuong tonKhoDinhDuong) {
        em.merge(tonKhoDinhDuong);
    }

    public void remove(TonKhoDinhDuong tonKhoDinhDuong) {
        em.remove(em.merge(tonKhoDinhDuong));
    }

    public TonKhoDinhDuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.TonKhoDinhDuong.class, id);
    }

    public List<TonKhoDinhDuong> findAll() {
        return em.createQuery("select object(o) from TonKhoDinhDuong as o").getResultList();
    }
    
    public TonKhoDinhDuong findLastTKByNhaSX(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso) {
        //return em.createQuery("select object(o) from TonKhoDinhDuong as o").getResultList();
        if (findAll().size() > 0) {
            String sql = "select object(o) from TonKhoDinhDuong as o " +      
                    "where o.dtdmlaMaso.dtdmlaMaso = :loaianMaso " +                            
                    "and o.dtdmla2Maso.dtdmla2Maso = :loaian2Maso "; 
            if (nhasxMaso != null) {
                sql += "and o.dtdmnsxMaso.dtdmnsxMaso = :nhasxMaso ";            
            }
            sql += "order by o.tkddMaso DESC ";
            
            Query q = em.createQuery(sql);
            q.setParameter("loaianMaso", loaianMaso);
            q.setParameter("loaian2Maso", loaian2Maso);        
            if (nhasxMaso != null) {
                q.setParameter("nhasxMaso", nhasxMaso);
            }             
            return ((q.getResultList().size() > 0) ? (TonKhoDinhDuong) q.getResultList().get(0) : null);
            }
        return null;
    }
    
    private Integer tinhSoNhap(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso, Date ngaynhap) {
        //return em.createQuery("select object(o) from TonKhoDinhDuong as o").getResultList();
        System.out.println("Begin tinhSoNhap, ngaynhap = " + ngaynhap);
        Integer sumSoNhap = new Integer(0);
        //if (findAll().size() > 0) {
            String sql = "select SUM(o.nkddSoluong) from NhapKhoDinhDuong as o " +      
                    "where o.dtdmlaMaso.dtdmlaMaso = :loaianMaso " +                            
                    "and o.dtdmla2Maso.dtdmla2Maso = :loaian2Maso " +                    
                    "and (o.nkddNgaynhap < :ngaynhap or o.nkddNgaynhap = :ngaynhap) "; 
            if (nhasxMaso != null) {
                sql += "and o.dtdmnsxMaso.dtdmnsxMaso = :nhasxMaso ";            
            }
            
            
            Query q = em.createQuery(sql);
            q.setParameter("loaianMaso", loaianMaso);
            q.setParameter("loaian2Maso", loaian2Maso);
            q.setParameter("ngaynhap", ngaynhap);
            if (nhasxMaso != null) {
                q.setParameter("nhasxMaso", nhasxMaso);
            }  
            System.out.println("In tinhSoNhap, truoc khi truy van, sumSoNhap = " + sumSoNhap);
            Object obj = q.getSingleResult();
            if (obj != null) {
                sumSoNhap = new Integer(((Double) obj).intValue()); 
            }
            System.out.println("After tinhSoNhap, truoc khi truy van, sumSoNhap = " + sumSoNhap);
            //return ((q.getResultList().size() > 0) ? (TonKhoDinhDuong) q.getResultList().get(0) : null);
          //  }
        return (sumSoNhap == null ? new Integer(0) : sumSoNhap);
    }
    private Integer tinhSoXuat(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso, Date ngayxuat) {
        //return em.createQuery("select object(o) from TonKhoDinhDuong as o").getResultList();
        Integer sumSoXuat = new Integer(0);
        //if (findAll().size() > 0) {
            String sql = "select SUM(o.xkddSoluong) from XuatKhoDinhDuong as o " +      
                    "where o.dtdmlaMaso.dtdmlaMaso = :loaianMaso " +                            
                    "and o.dtdmla2Maso.dtdmla2Maso = :loaian2Maso " +                    
                    "and (o.xkddNgayxuat < :ngayxuat or o.xkddNgayxuat = :ngayxuat) "; 
            if (nhasxMaso != null) {
                sql += "and o.dtdmnsxMaso.dtdmnsxMaso = :nhasxMaso ";            
            }
            
            
            Query q = em.createQuery(sql);
            q.setParameter("loaianMaso", loaianMaso);
            q.setParameter("loaian2Maso", loaian2Maso);
            q.setParameter("ngayxuat", ngayxuat);
            if (nhasxMaso != null) {
                q.setParameter("nhasxMaso", nhasxMaso);
            }
            Object obj = q.getSingleResult();
            if (obj != null) {
                sumSoXuat = new Integer(((Double)obj).intValue()); 
            }
            //return ((q.getResultList().size() > 0) ? (TonKhoDinhDuong) q.getResultList().get(0) : null);
         //   }
        return (sumSoXuat == null ? new Integer(0) : sumSoXuat);
    }
    
    public int tinhSoTon(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso, Date ngayxuat) {
        Integer sonhap = tinhSoNhap(loaianMaso, loaian2Maso, nhasxMaso, ngayxuat);
        System.out.println("So nhap = " + sonhap);
        Integer soxuat = tinhSoXuat(loaianMaso, loaian2Maso, nhasxMaso, ngayxuat);
        System.out.println("So xuat = " + soxuat);
        int soton = sonhap.intValue() - soxuat.intValue();
        System.out.println("So ton = " + soton);
        return soton;
        
    }
}
