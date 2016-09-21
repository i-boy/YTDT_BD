/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DonMien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DonMienFacade implements DonMienFacadeLocal, DonMienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DonMien donMien) {
        em.persist(donMien);
    }

    public void edit(DonMien donMien) {
        em.merge(donMien);
    }

    public void remove(DonMien donMien) {
        em.remove(em.merge(donMien));
    }

    public DonMien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DonMien.class, id);
    }

    public List<DonMien> findAll() {
        return em.createQuery("select object(o) from DonMien as o").getResultList();
    }

}


