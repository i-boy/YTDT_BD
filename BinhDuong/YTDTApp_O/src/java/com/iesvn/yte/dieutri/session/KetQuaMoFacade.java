/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.KetQuaMo;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class KetQuaMoFacade implements KetQuaMoFacadeLocal, KetQuaMoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(KetQuaMo ketQuaMo) {
        em.persist(ketQuaMo);
    }

    public void edit(KetQuaMo ketQuaMo) {
        em.merge(ketQuaMo);
    }

    public void remove(KetQuaMo ketQuaMo) {
        em.remove(em.merge(ketQuaMo));
    }

    public KetQuaMo find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.KetQuaMo.class, id);
    }

    public List<KetQuaMo> findAll() {
        return em.createQuery("select object(o) from KetQuaMo as o").getResultList();
    }
    
}


