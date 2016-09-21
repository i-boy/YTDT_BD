/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn;
import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class BenhNhanPhieuBaoAnFacade implements BenhNhanPhieuBaoAnFacadeLocal, BenhNhanPhieuBaoAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    public void create(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn) {
        em.persist(benhNhanPhieuBaoAn);
    }

    public void edit(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn) {
        em.merge(benhNhanPhieuBaoAn);
    }

    public void remove(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn) {
        em.remove(em.merge(benhNhanPhieuBaoAn));
    }

    public BenhNhanPhieuBaoAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn.class, id);
    }

    public List<BenhNhanPhieuBaoAn> findAll() {
        return em.createQuery("select object(o) from BenhNhanPhieuBaoAn as o").getResultList();
    }
    
    public void myCreate(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn, List<BenhNhanCheDoAn> listBnCda, List<BenhNhanGioAn> listBnGa, boolean isUpdate) {
        try {            
            if(isUpdate) {
                // Remove che do an va gio an cua benh nhan
                removeCheDoAnGioAn(benhNhanPhieuBaoAn.getBnpbaMaso());
            }
            if (benhNhanPhieuBaoAn.getBnpbaMaso() != null) {
                em.merge(benhNhanPhieuBaoAn);
            } else {
                em.persist(benhNhanPhieuBaoAn);
            }
            // Luu che do an cua benh nhan
            for (BenhNhanCheDoAn eachCda : listBnCda) {
                eachCda.setBncdaMaso(null);
                eachCda.setBnpbaMaso(benhNhanPhieuBaoAn);
                em.persist(eachCda);
            }
            // Luu gio an cua benh nhan
            for (BenhNhanGioAn eachGa : listBnGa) {
                eachGa.setBngaMaso(null);
                eachGa.setBnpbaMaso(benhNhanPhieuBaoAn);
                em.persist(eachGa);
            }
        } catch (Exception ex) {
            context.setRollbackOnly();            
            ex.printStackTrace();                        
        }      
    }
    
    private void removeCheDoAnGioAn(Integer bnpbaMaso) {
        try { 
            Query q = em.createQuery("Delete FROM BenhNhanCheDoAn c WHERE c.bnpbaMaso.bnpbaMaso = :bnpbaMaso");
            q.setParameter("bnpbaMaso", bnpbaMaso);
            System.out.println("begin remove BenhNhanCheDoAn" );
            int i = q.executeUpdate(); 
            
            q = em.createQuery("Delete FROM BenhNhanGioAn c WHERE c.bnpbaMaso.bnpbaMaso = :bnpbaMaso");
            q.setParameter("bnpbaMaso", bnpbaMaso);
            System.out.println("begin remove BenhNhanGioAn" );
            i = q.executeUpdate(); 
           
        } catch (Exception ex) {
            context.setRollbackOnly();            
            ex.printStackTrace();                        
        }   
        
    }
    
    public List<BenhNhanPhieuBaoAn> findByPbaMaso(Integer pbaMaso) {        
        Query q = em.createQuery("select object(o) from BenhNhanPhieuBaoAn as o where o.phieubaoanMaso.phieubaoanMaso = :pbaMaso ");        
        q.setParameter("pbaMaso", pbaMaso);
        return q.getResultList();
    }
    public int removeByPbaMaso(Integer pbaMaso) {
        int i = 0;
        try {
            List<BenhNhanPhieuBaoAn> listBnPba = findByPbaMaso(pbaMaso);
            for(BenhNhanPhieuBaoAn eachBnPba : listBnPba) {
                removeCheDoAnGioAn(eachBnPba.getBnpbaMaso()) ;
            }
            Query q = em.createQuery("Delete FROM BenhNhanPhieuBaoAn c WHERE c.phieubaoanMaso.phieubaoanMaso = :pbaMaso");
            q.setParameter("pbaMaso", pbaMaso);
            i = q.executeUpdate();            
        } catch (Exception ex) {
            i = -1;
            context.setRollbackOnly();
            ex.printStackTrace();
        }
        return i;
    }
}
