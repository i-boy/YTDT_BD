/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class PhieuTheoDoiTruyenDichFacade implements PhieuTheoDoiTruyenDichFacadeLocal, PhieuTheoDoiTruyenDichFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
        em.persist(phieuTheoDoiTruyenDich);
    }

    public void edit(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
        em.merge(phieuTheoDoiTruyenDich);
    }

    public void remove(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich) {
        em.remove(em.merge(phieuTheoDoiTruyenDich));
    }

    public PhieuTheoDoiTruyenDich find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich.class, id);
    }

    public List<PhieuTheoDoiTruyenDich> findAll() {
        return em.createQuery("select object(o) from PhieuTheoDoiTruyenDich as o").getResultList();
    }
    
    public List<PhieuTheoDoiTruyenDich> findByHsba(String hsbaMaso) {        
        List<PhieuTheoDoiTruyenDich> lst = new ArrayList<PhieuTheoDoiTruyenDich>();
        if (findAll().size() > 0) {
            lst =  em.createQuery("select object(o) from PhieuTheoDoiTruyenDich as o where o.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien order by o.ptdtdBatdau ").setParameter("hsbaSovaovien", hsbaMaso).getResultList();
        }       
       return lst;
    }
    public PhieuTheoDoiTruyenDich createPhieuTheoDoiTruyenDich(PhieuTheoDoiTruyenDich ptdtd) {
        if (ptdtd.getPtdtdMaso() == null) {
            em.persist(ptdtd);
        } else {
            em.merge(ptdtd);
        }
        return ptdtd;
    }
    

}
