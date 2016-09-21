/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.ThamKham;
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
public class DtDmBanKhamFacade implements DtDmBanKhamFacadeLocal, DtDmBanKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmBanKham dtDmBanKham) {
        em.persist(dtDmBanKham);
    }

    public void edit(DtDmBanKham dtDmBanKham) {
        em.merge(dtDmBanKham);
    }

    public void remove(DtDmBanKham dtDmBanKham) {
        em.remove(em.merge(dtDmBanKham));
    }

    public DtDmBanKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmBanKham.class, id);
    }

    public List<DtDmBanKham> findAll() {
        return em.createQuery("select object(o) from DtDmBanKham as o").getResultList();
    }

    public List<DtDmBanKham> findByMaTiepDon(String maTiepDon){
       

        List<ThamKham> lstThamKham = em.createQuery("select object(o) from ThamKham as o where o.tiepdonMa.tiepdonMa like :tiepdonMa order by o.thamkhamMa DESC").setParameter("tiepdonMa", maTiepDon).getResultList();
        //System.out.println(hsbaCV);
        if (lstThamKham != null && lstThamKham.size() > 0) {
            List<DtDmBanKham> lstBanKham = new ArrayList<DtDmBanKham>();
            for (ThamKham tk: lstThamKham){
                lstBanKham.add(tk.getThamkhamBankham());
            }
            return lstBanKham;
        } else {
            return null;
        }
    }

}


