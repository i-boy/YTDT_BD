/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.entity.DmKhoa;
import java.util.ArrayList;
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
public class DtDmClsFacade implements DtDmClsFacadeLocal, DtDmClsFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(DtDmCls dtDmCls) {
        em.persist(dtDmCls);
    }

    public void edit(DtDmCls dtDmCls) {
        em.merge(dtDmCls);
    }

    public void remove(DtDmCls dtDmCls) {
        em.remove(em.merge(dtDmCls));
    }

    public DtDmCls find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmCls.class, id);
    }

    public List<DtDmCls> findAll() {
        return em.createQuery("select object(o) from DtDmCls as o").getResultList();
    }

    public List<DmKhoa> getListDmKhoa(Integer clsMaso) {
        ArrayList<DmKhoa> listKhoa = new ArrayList<DmKhoa>(10000);

        try {
            DtDmCls cls = em.find(DtDmCls.class, clsMaso);
            if (cls != null) {
                Query q = em.createQuery("Select ck From DtDmClsKhoa ck Where ck.dtdmclsMaso = :cls");
                q.setParameter("cls", cls);
                List<DtDmClsKhoa> listClsKhoa = q.getResultList();
                if (listClsKhoa != null) {
                    for (DtDmClsKhoa ck : listClsKhoa) {
                        listKhoa.add(ck.getDmkhoaMaso());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhoa;
    }
}


