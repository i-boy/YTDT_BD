/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
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
public class CtNhapKhoFacade implements CtNhapKhoFacadeLocal, CtNhapKhoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtNhapKho ctNhapKho) {
        em.persist(ctNhapKho);
    }

    public void edit(CtNhapKho ctNhapKho) {
        em.merge(ctNhapKho);
    }

    public void remove(CtNhapKho ctNhapKho) {
        em.remove(em.merge(ctNhapKho));
    }

    public CtNhapKho find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtNhapKho.class, id);
    }

    public List<CtNhapKho> findAll() {
        return em.createQuery("select object(o) from CtNhapKho as o").getResultList();
    }
    
      public List<CtNhapKho> findByPhieuNhapKho(String pnkMa) {
        List<CtNhapKho> listCTNK = null;
        try {
            Query q = em.createQuery("select o from CtNhapKho o Where o.phieunhapkhoMa.phieunhapkhoMa = :phieunhapkhoMa");
            q.setParameter("phieunhapkhoMa", pnkMa);
            listCTNK = q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return listCTNK;
    }

}


