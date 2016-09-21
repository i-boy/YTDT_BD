/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmLoaiAn2Facade implements DtDmLoaiAn2FacadeLocal, DtDmLoaiAn2FacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiAn2 dtDmLoaiAn2) {
        em.persist(dtDmLoaiAn2);
    }

    public void edit(DtDmLoaiAn2 dtDmLoaiAn2) {
        em.merge(dtDmLoaiAn2);
    }

    public void remove(DtDmLoaiAn2 dtDmLoaiAn2) {
        em.remove(em.merge(dtDmLoaiAn2));
    }

    public DtDmLoaiAn2 find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiAn2.class, id);
    }

    public List<DtDmLoaiAn2> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiAn2 as o").getResultList();
    }
    
    public List<DtDmLoaiAn2> findByLoai(int loaian) {
        return em.createQuery("select object(o) from DtDmLoaiAn2 as o where o.dtdmlaMaso.dtdmlaMaso = :loaian").setParameter("loaian", loaian).getResultList();
    }
        
}
