/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HoanUngKham;
import com.iesvn.yte.dieutri.utils.Utils;
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
public class HoanUngKhamFacade implements HoanUngKhamFacadeLocal, HoanUngKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HoanUngKham hoanUngKham) {
        em.persist(hoanUngKham);
    }

    public void edit(HoanUngKham hoanUngKham) {
        em.merge(hoanUngKham);
    }

    public void remove(HoanUngKham hoanUngKham) {
        em.remove(em.merge(hoanUngKham));
    }

    public HoanUngKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HoanUngKham.class, id);
    }

    public List<HoanUngKham> findAll() {
        return em.createQuery("select object(o) from HoanUngKham as o").getResultList();
    }
    
     public Double getTongHoanUng(String maTiepdon){
        maTiepdon = Utils.formatMa(em, maTiepdon);
        
        Double ketqua = new Double(0);
        Query q = em.createQuery("Select c from HoanUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon");
        q.setParameter("maTiepdon", maTiepdon);
        List<HoanUngKham> lstHoanUngKham =  q.getResultList();
        if (lstHoanUngKham != null && lstHoanUngKham.size() > 0){
            for (HoanUngKham hoanUngKham: lstHoanUngKham){
                Double sotien = hoanUngKham.getHoanungkhamSotien();
                if (sotien == null){
                    sotien = new Double(0);
                }
                ketqua += sotien;
            }
        }
        
        return ketqua;
    }

}


