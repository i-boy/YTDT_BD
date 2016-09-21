/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author James Bond
 */
@Stateless
public class DtDmClsKhoaFacade implements DtDmClsKhoaFacadeLocal, DtDmClsKhoaFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(DtDmClsKhoa dtDmClsKhoa) {
        em.persist(dtDmClsKhoa);
    }

    public void edit(DtDmClsKhoa dtDmClsKhoa) {
        em.merge(dtDmClsKhoa);
    }

    public void remove(DtDmClsKhoa dtDmClsKhoa) {
        em.remove(em.merge(dtDmClsKhoa));
    }

    public DtDmClsKhoa find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmClsKhoa.class, id);
    }

    public List<DtDmClsKhoa> findAll() {
        return em.createQuery("select object(o) from DtDmClsKhoa as o").getResultList();
    }

    public List<DtDmClsKhoa> findByMaSoKhoa(Integer maSoKhoa) {
        List<DtDmClsKhoa> list = new ArrayList<DtDmClsKhoa>();
        try {
            System.out.println("-----Maso khoa: "+maSoKhoa);
            String sQuery = "select object(o) from DtDmClsKhoa as o";
            sQuery += " WHERE o.dmkhoaMaso.dmkhoaMaso = :maSoKhoa";
            //sQuery +=" group by o.dtdmclsbgMaso.dtdmclsbgMaso ";
            Query q;
            q = em.createQuery(sQuery);
            q.setParameter("maSoKhoa", maSoKhoa);
            list = q.getResultList();
            System.out.println("-----List.size: "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public DtDmClsKhoa findByMaClsBangGia(Integer maSoClsBg) {
        if (findAll().size() > 0) {
            List<DtDmClsKhoa> list = em.createQuery("select object(o) from DtDmClsKhoa as o WHERE o.dtdmclsMaso.dtdmclsbgMaso = :maSoClsBg").setParameter("maSoClsBg", maSoClsBg).getResultList();        
            if (list != null && list.size() > 0) {            
                return list.get(0);
            } else {
                return null;
            } 
        }else {
            return null;
        }        
        
    }
    
    public List<DtDmClsKhoa> findByMaSoCLS(Integer masoCLS) {
        List<DtDmClsKhoa> list = new ArrayList<DtDmClsKhoa>();
        try {
            System.out.println("-----Maso CLS: "+masoCLS);
            String sQuery = "select object(o) from DtDmClsKhoa as o";
            sQuery += " WHERE o.dtdmclsMaso.dtdmclsbgMaso = :masoCLS";
            //sQuery +=" group by o.dtdmclsbgMaso.dtdmclsbgMaso ";
            Query q;
            q = em.createQuery(sQuery);
            q.setParameter("masoCLS", masoCLS);
            list = q.getResultList();
            System.out.println("-----List.size: "+list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
