/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thanh
 */
@Stateless
public class BaithuocThuocFacade implements BaithuocThuocFacadeLocal, BaithuocThuocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BaithuocThuoc baithuocThuoc) {
        em.persist(baithuocThuoc);
    }

    public void edit(BaithuocThuoc baithuocThuoc) {
        em.merge(baithuocThuoc);
    }

    public void remove(BaithuocThuoc baithuocThuoc) {
        baithuocThuoc = find(baithuocThuoc.getBaithuocthuocMaso());
        if(baithuocThuoc != null){
            em.remove(baithuocThuoc);
        }
    }

    public BaithuocThuoc find(Object id) {
        return em.find(BaithuocThuoc.class, id);
    }

    public void deleteAllBaithuocThuocsByMasoThuoc(Integer thuocMaso) {
        System.out.println("Begin deleteAllBaithuocThuocsByMasoThuoc");
        Query q = getEm().createQuery("Select object(b) from BaithuocThuoc b Where b.dmthuocMaso.dmthuocMaso = :dmthuocMaso");
        q.setParameter("dmthuocMaso", thuocMaso);
        List<BaithuocThuoc> baithuocThuocs = q.getResultList();
        for(int i = 0; i< baithuocThuocs.size(); i++){
            remove(baithuocThuocs.get(i));
        }
        System.out.println("End deleteAllBaithuocThuocsByMasoThuoc");
    }

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
