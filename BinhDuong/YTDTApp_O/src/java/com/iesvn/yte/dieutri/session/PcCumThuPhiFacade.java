/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import java.util.Date;
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
public class PcCumThuPhiFacade implements PcCumThuPhiFacadeLocal, PcCumThuPhiFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(PcCumThuPhi pcCumThuPhi) {
        em.persist(pcCumThuPhi);
    }

    public void edit(PcCumThuPhi pcCumThuPhi) {
        em.merge(pcCumThuPhi);
    }

    public void remove(PcCumThuPhi pcCumThuPhi) {
        em.remove(em.merge(pcCumThuPhi));
    }

    public PcCumThuPhi find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.PcCumThuPhi.class, id);
    }

    public List<PcCumThuPhi> findAll() {
        return em.createQuery("select object(o) from PcCumThuPhi as o").getResultList();
    }
    
    /*
     * 
     */
    public int ghinhan(List<PcCumThuPhi> list){
        System.out.println("---ghinhan list PcCumThuPhi");
        Date tn = list.get(0).getPcctpTungay();
        Date dn = list.get(0).getPcctpDenngay();
        //SELECT count(*) FROM PC_CUM_THU_PHI P where ('2009-03-20' not between P.PCCTP_TUNGAY and P.PCCTP_DENNGAY);
        Query query = em.createQuery("select object(o) from PcCumThuPhi as o where ( :tn not between o.pcctpTungay and o.pcctpDenngay ) and ( :dn not between o.pcctpTungay and o.pcctpDenngay )");
        query.setParameter("tn", tn);
        query.setParameter("dn", dn);
        List<PcCumThuPhi> chk = query.getResultList();
        System.out.println("--- list check PcCumThuPhi: " + chk==null?0:chk.size());
        if (chk!=null && chk.size()>0){
            return -1;
        }
        try{
            for (PcCumThuPhi o : list) {
		em.persist(o);	
            }
            return 1;
        }catch (Exception ee){
            System.out.println("Err PcCumThuPhiFacade ghinhan: " + ee);
        }        
        return 0;
    }
    
    public PcCumThuPhi findPcCumThuPhiByNgayAndNhanVien(Date date, String nvMa){
        System.out.println("---findPcCumThuPhiByNgayAndNhanVien"); 
        try {
            Query query = em.createQuery("select object(o) from PcCumThuPhi as o where ( :date between o.pcctpTungay and o.pcctpDenngay ) and o.quyen = 1 and o.dtdmnhanvienMa.dtdmnhanvienMa = :nvMa");
            query.setParameter("date", date);
            query.setParameter("nvMa", nvMa);
            List<PcCumThuPhi> list = query.getResultList();
            if (list != null && list.size()>0){
                int cumMaso = list.get(0).getDtdmcumMa().getDtdmcumMaso();
                query = em.createQuery("select object(o) from PcCumThuPhi as o where ( :date between o.pcctpTungay and o.pcctpDenngay ) and o.quyen = 2 and o.dtdmcumMa.dtdmcumMaso = :cumMaso");
                query.setParameter("cumMaso", cumMaso); 
                query.setParameter("date", date);
                List<PcCumThuPhi> list1 = query.getResultList();
                if (list1 != null && list1.size()>0){
                    return (PcCumThuPhi) list1.get(0);
                }     
            }            
        } catch (Exception e) {
            System.out.println("Error PcCumThuPhi find: " + e);
        }
        return null;
    }
    
    public List<PcCumThuPhi> findPcCumThuPhi(Date tungay, Date denngay, String cum){
        System.out.println("---findPcCumThuPhiByNgayAndNhanVien"); 
        try{
            Query q = em.createQuery("select object(o) from PcCumThuPhi as o where " +
                    "(:tungay is null or :tungay = o.pcctpTungay) " +
                    "and (:denngay is null or :denngay = o.pcctpDenngay) " +
                    "and (:cum is null or :cum = o.dtdmcumMa.dtdmcumMa)");
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);
            q.setParameter("cum", cum);
            List<PcCumThuPhi> list = q.getResultList();
            return list;
        }catch (Exception ex){
            System.out.println("Error PcCumThuPhi find: " + ex);
        }
        
        return null;
    }

}
