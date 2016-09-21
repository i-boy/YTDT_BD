/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class PhieuDuTruFacade implements PhieuDuTruFacadeLocal, PhieuDuTruFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private CtPhieuDtFacadeLocal ctPhieuDtFacade;

    public void create(PhieuDuTru phieuDuTru) {
        getEm().persist(phieuDuTru);
    }

    public void edit(PhieuDuTru phieuDuTru) {
        getEm().merge(phieuDuTru);
    }

    public void remove(PhieuDuTru phieuDuTru) {
        getEm().remove(getEm().merge(phieuDuTru));
    }

    public PhieuDuTru find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.PhieuDuTru.class, id);
    }

    public List<PhieuDuTru> findAll() {
        return getEm().createQuery("select object(o) from PhieuDuTru as o").getResultList();
    }

    public boolean removeAllPhieuDuTru(PhieuDuTru phieuDutru) {
        boolean result = false;
        Query q = null;
        int numRecordDeleted = 0;
        try {
            if (phieuDutru != null) {
                String sMaPhieuDt = phieuDutru.getPhieudtMa();
                ctPhieuDtFacade.removeAllCtPhieuDuTru(phieuDutru);
                q = getEm().createQuery("DELETE FROM PhieuDuTru WHERE phieudtMa = :sMaPhieuDt");
                q.setParameter("sMaPhieuDt", sMaPhieuDt);
                numRecordDeleted = q.executeUpdate();
                System.out.println("----- Number of records PhieuDuTru were deleted: " + numRecordDeleted);
                result = true;
            } else {
                System.out.println("----- PhieuDuTru is null");
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
            result = false;
        }
        return result;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public SessionContext getContext() {
        return context;
    }

    public void setContext(SessionContext context) {
        this.context = context;
    }
}


