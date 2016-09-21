/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class ThuocDongYNoiTruFacade implements ThuocDongYNoiTruFacadeLocal, ThuocDongYNoiTruFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ThuocDongYNoiTru thuocDongYNoiTru) {
        em.persist(thuocDongYNoiTru);
    }

    public void edit(ThuocDongYNoiTru thuocDongYNoiTru) {
        em.merge(thuocDongYNoiTru);
    }

    public void remove(ThuocDongYNoiTru thuocDongYNoiTru) {
        em.remove(em.merge(thuocDongYNoiTru));
    }

    public ThuocDongYNoiTru find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru.class, id);
    }

    public List<ThuocDongYNoiTru> findAll() {
        return em.createQuery("select object(o) from ThuocDongYNoiTru as o").getResultList();
    }
    
    public List<ThuocDongYNoiTru> findBySoVaoVien(String hsbaSovaovien) {
        if (findAll().size() > 0) {
            Query q = em.createQuery("select object(o) from ThuocDongYNoiTru as o where o.hsbaSovaovien.hsbaSovaovien like :hsbaSovaovien ");        
            q.setParameter("hsbaSovaovien", hsbaSovaovien);
            return q.getResultList();
        } else {
            return findAll();
        }
    }

    public List<ThuocDongYNoiTru> findBySoVaoVienandKhoaDTandNgayandLoai(String soVaoVien, String khoaMa, Date ngay, String loai) {
       System.out.println("So vao vien: "+ soVaoVien);
       System.out.println("Ngay: "+ ngay);
       System.out.println("khoaMa: "+ khoaMa);
       System.out.println("loai: "+ loai);
       Query q = em.createQuery("select object(o) from ThuocDongYNoiTru as o where o.hsbaSovaovien.hsbaSovaovien like :hsbaSovaovien and to_date(o.thuocdongyNgaygiocn) = :ngay and o.thuocdongyKhoa.dmkhoaMa like :khoaMa and o.thuocdongyLoai like :loai");
       q.setParameter("hsbaSovaovien", soVaoVien);
       q.setParameter("ngay", ngay);
       q.setParameter("khoaMa", khoaMa);
       q.setParameter("loai", loai);
       List<ThuocDongYNoiTru> list = q.getResultList();
       return list;
    }
}
