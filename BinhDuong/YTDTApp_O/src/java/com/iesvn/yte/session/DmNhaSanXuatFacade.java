/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmThuoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DmNhaSanXuatFacade implements DmNhaSanXuatFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNhaSanXuat dmNhaSanXuat) {
        em.persist(dmNhaSanXuat);
    }

    public void edit(DmNhaSanXuat dmNhaSanXuat) {
        em.merge(dmNhaSanXuat);
    }

    public void remove(DmNhaSanXuat dmNhaSanXuat) {
        em.remove(em.merge(dmNhaSanXuat));
    }

    public DmNhaSanXuat find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNhaSanXuat.class, id);
    }

    public List<DmNhaSanXuat> findAll() {
        return em.createQuery("select object(o) from DmNhaSanXuat as o").getResultList();
    }
    
    public List<DmNhaSanXuat> findByDmThuocMaAndDmQuocGiaMa(String dmtMa, Integer dmqgMaso) {
        System.out.println("Begin findByDmThuocMaAndDmQuocGiaMa(String dmtMa, Integer dmqgMaso) method");
        List<DmNhaSanXuat> result = null;
        try {
            DmThuoc dmt = (DmThuoc) em.createQuery("Select d from DmThuoc d Where d.dmthuocMa = :dmthuocMa").setParameter("dmthuocMa", dmtMa).getSingleResult();
            Query q = em.createQuery("Select Distinct tk.dtdmhangsxMa from TonKho tk Where tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso " +
                    "and tk.dmquocgiaMaso.dmquocgiaMaso =: dmquocgiaMaso");
            q.setParameter("dmthuocMaso", dmt.getDmthuocMaso());                        
            q.setParameter("dmquocgiaMaso", dmqgMaso);
         result = q.getResultList();
        } catch (Exception ex) {
            System.out.println(":( Error: " + ex.toString());
        }
        System.out.println("End findByDmThuocMaAndDmQuocGiaMa(String dmtMa, Integer dmqgMaso) method");
        return result;
    }

}


