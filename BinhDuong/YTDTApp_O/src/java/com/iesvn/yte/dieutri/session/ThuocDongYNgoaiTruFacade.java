/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
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
public class ThuocDongYNgoaiTruFacade implements ThuocDongYNgoaiTruFacadeLocal, ThuocDongYNgoaiTruFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        em.persist(thuocDongYNgoaiTru);
    }

    public void edit(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        em.merge(thuocDongYNgoaiTru);
    }

    public void remove(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        em.remove(em.merge(thuocDongYNgoaiTru));
    }

    public ThuocDongYNgoaiTru find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru.class, id);
    }

    public List<ThuocDongYNgoaiTru> findAll() {
        return em.createQuery("select object(o) from ThuocDongYNgoaiTru as o").getResultList();
    }
    
    public List<ThuocDongYNgoaiTru> findByMaTiepDon(String maTiepDon) {
        if (findAll().size() > 0) {
            Query q = em.createQuery("select object(o) from ThuocDongYNgoaiTru as o where o.tiepdonMa.tiepdonMa like :tiepdonMa ");        
            q.setParameter("tiepdonMa", maTiepDon);
            return q.getResultList();
        } else {
            return findAll();
        }
    }
    public List<ThuocDongYNgoaiTru> findByMaTiepDonAndMaBanKham(String maTiepDon, String bankhamMa, String loaiToaThuoc) {
        Query q = em.createQuery("select object(o) from ThuocDongYNgoaiTru as o where o.thuocdongyLoai like :loai and o.thuocdongyThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.tiepdonMa.tiepdonMa like :tiepdonMa ");
        q.setParameter("tiepdonMa", maTiepDon);
        q.setParameter("dtdmbankhamMa", bankhamMa);
        q.setParameter("loai", loaiToaThuoc);
        List<ThuocDongYNgoaiTru> list = q.getResultList();
        return list;
    }
}
