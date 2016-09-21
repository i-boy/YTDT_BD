/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtXuatKho;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class CtXuatKhoFacade implements CtXuatKhoFacadeLocal, CtXuatKhoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtXuatKho ctXuatKho) {
        em.persist(ctXuatKho);
    }

    public void edit(CtXuatKho ctXuatKho) {
        em.merge(ctXuatKho);
    }

    public void remove(CtXuatKho ctXuatKho) {
        em.remove(em.merge(ctXuatKho));
    }

    public CtXuatKho find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtXuatKho.class, id);
    }

    public List<CtXuatKho> findAll() {
        return em.createQuery("select object(o) from CtXuatKho as o").getResultList();
    }
     public List<CtXuatKho> findByphieuxuatkhoMa(String phieuxuatMa) {
        Query q = em.createQuery("SELECT c FROM CtXuatKho c WHERE c.phieuxuatkhoMa.phieuxuatkhoMa = :phieuxuatMa order by c.dmthuocMaso.dmthuocTen");
        q.setParameter("phieuxuatMa", phieuxuatMa);
        return q.getResultList();
    }

}


