/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
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
public class CtXuatBhXuatVienFacade implements CtXuatBhXuatVienFacadeLocal, CtXuatBhXuatVienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtXuatBhXuatVien ctXuatBh) {
        em.persist(ctXuatBh);
    }

    public void edit(CtXuatBhXuatVien ctXuatBh) {
        em.merge(ctXuatBh);
    }

    public void remove(CtXuatBhXuatVien ctXuatBh) {
        em.remove(em.merge(ctXuatBh));
    }

    public CtXuatBhXuatVien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien.class, id);
    }

    public List<CtXuatBhXuatVien> findAll() {
        return em.createQuery("select object(o) from CtXuatBhXuatVien as o").getResultList();
    }
    public List<CtXuatBhXuatVien> findByPhieuxuatBhXuatVienMa(String phieuxuatBhMa) {
        Query q = em.createQuery("SELECT c FROM CtXuatBhXuatVien c WHERE c.phieuxuatbhxvMa.phieuxuatbhxvMa = :phieuxuatbhMa");
        q.setParameter("phieuxuatbhMa", phieuxuatBhMa);
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


