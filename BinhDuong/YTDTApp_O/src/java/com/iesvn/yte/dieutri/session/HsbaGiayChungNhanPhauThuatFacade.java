/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhanPhauThuat;
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
public class HsbaGiayChungNhanPhauThuatFacade implements HsbaGiayChungNhanPhauThuatFacadeLocal, HsbaGiayChungNhanPhauThuatFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    
    public void create(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
        em.persist(HsbaGiayChungNhanPhauThuat);
    }

    public void edit(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
        em.merge(HsbaGiayChungNhanPhauThuat);
    }

    public void remove(HsbaGiayChungNhanPhauThuat HsbaGiayChungNhanPhauThuat) {
        em.remove(em.merge(HsbaGiayChungNhanPhauThuat));
    }

    public HsbaGiayChungNhanPhauThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayChungNhanPhauThuat.class, id);
    }

    public List<HsbaGiayChungNhanPhauThuat> findAll() {
        return em.createQuery("select object(o) from HsbaGiayChungNhanPhauThuat as o").getResultList();
    }
    
    public String insert(HsbaGiayChungNhanPhauThuat gct){
        System.out.println("---insert giay chung nhan phau thuat");        
        try{
            String ma = Utils.maGiayChungNhanPhauThuat(em);
            System.out.println("---ma sinh ra: " + ma);        
            gct.setHsbagcnptMa(ma);
            em.persist(gct);
            System.out.println("---insert giay chung nhan phau thuat: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert giay chung nhan phau thuat: err");        
            return "";
        }
    }
    
    public String update(HsbaGiayChungNhanPhauThuat gct){
        System.out.println("---update giay chung nhan phau thuat");        
        try{
            System.out.println("---ma update: " + gct.getHsbagcnptMa());                   
            em.merge(gct);
            System.out.println("---update giay chung nhan phau thuat: success");
            return gct.getHsbagcnptMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update giay chung nhan phau thuat: err");        
            return "";
        }
    } 
    
    public HsbaGiayChungNhanPhauThuat findByHsbagcnptMa(String ma){
        System.out.println("---find Hsbagcnpt by findByHsbagcnptMa"); 
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaGiayChungNhanPhauThuat  as o where o.hsbagcnptMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaGiayChungNhanPhauThuat> list = query.getResultList();
            if (list != null && list.size()>0) {
                return (HsbaGiayChungNhanPhauThuat) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}


