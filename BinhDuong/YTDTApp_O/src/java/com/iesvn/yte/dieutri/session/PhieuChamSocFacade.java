/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuChamSoc;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author halylam
 */
@Stateless
public class PhieuChamSocFacade implements PhieuChamSocFacadeLocal, PhieuChamSocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

     @Resource
    private SessionContext context;

    public void create(PhieuChamSoc phieuChamSoc) {
        em.persist(phieuChamSoc);
    }

    public void edit(PhieuChamSoc phieuChamSoc) {
        em.merge(phieuChamSoc);
    }

    public void remove(PhieuChamSoc phieuChamSoc) {
        em.remove(em.merge(phieuChamSoc));
    }

    public PhieuChamSoc find(Object id) {
        return em.find(PhieuChamSoc.class, id);
    }

    public List<PhieuChamSoc> findAll() {
        return em.createQuery("select object(o) from PhieuChamSoc as o").getResultList();
    }
//    public List<PhieuChamSoc> findByHsbaCM(Integer hsbacmmaso) {
//        System.out.println("call findByHsbaCM");
//
//       List<PhieuChamSoc> lst =  em.createQuery("select object(o) from PhieuChamSoc as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso ").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
//       System.out.println("lst:"+lst);
//       return lst;
//
//    }
    
    public List<PhieuChamSoc> findByHsba(String hsbaMaso) {
        System.out.println("call findByHsba");
        List<PhieuChamSoc> lst = new ArrayList<PhieuChamSoc>();
        if (findAll().size() > 0) {
            lst =  em.createQuery("select object(o) from PhieuChamSoc as o where o.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien order by o.phieuchamsocNgaygio ").setParameter("hsbaSovaovien", hsbaMaso).getResultList();
        }       
       return lst;
    }

    public String capNhatPhieuChamSoc(List<PhieuChamSoc> listPCS, Integer hsbacmMaso) {
        String result = "";
        String maPhieu = "";
        if (listPCS.size() > 0) {
            boolean needMaPhieu = true;
            for (int i = 0; i < listPCS.size(); i++) {
                PhieuChamSoc pcs = listPCS.get(i);
                if ( (pcs.getPhieuchamsocMa() != null && !pcs.getPhieuchamsocMa().equals("") ) ) {
                    needMaPhieu = false;
                    break;
                }
            }
            if (needMaPhieu == true) {
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 1 :" + maPhieu );
                maPhieu = Utils.maPhieuChamSoc(em);
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 2 :" + maPhieu );
            }
        }
        System.out.println("maPhieu: " + maPhieu);
        try {
            String listpcsMa = "";
            for (int i = 0; i < listPCS.size(); i++) {
               PhieuChamSoc pcs = listPCS.get(i);
                if (pcs.getPhieuchamsocMa() != null && !pcs.getPhieuchamsocMa().equals("")) {
                    if (listpcsMa.equals("")) {
                        listpcsMa += pcs.getPhieuchamsocMaso();
                    } else {
                        listpcsMa += "," + pcs.getPhieuchamsocMaso();
                    }
                }
            }
            System.out.print("listpcsMa:" + listpcsMa);
            this.removeItem(listpcsMa,hsbacmMaso);
            for (PhieuChamSoc pcs : listPCS) {
                if (pcs.getPhieuchamsocMaso() != null) {
                    em.merge(pcs);
                    System.out.println("update thanh cong chi tiet pcs");
                } else {
                    pcs.setPhieuchamsocMa(maPhieu);
                    em.persist(pcs);
                    System.out.println("insert them thanh cong chi tiet thuoc " + maPhieu);
                }
            }
            result = "";

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    public void removeItem(String listpcsMa, Integer hsbacmMaso) {


        Query q;
        if (listpcsMa == null || listpcsMa.equals("")) {
            q = em.createQuery("SELECT c FROM PhieuChamSoc c WHERE ( c.phieuchamsocMa is null or c.phieuchamsocMa = '' ) AND  c.hsbacmMa.hsbacmMa like :hsbacmMaso ");

        } else {
            q = em.createQuery("SELECT c FROM PhieuChamSoc c WHERE ( c.phieuchamsocMa is null or c.phieuchamsocMa = '' )  and c.phieuchamsocMaso NOT IN (" + listpcsMa + ") AND  c.hsbacmMa.hsbacmMa like :hsbacmMaso ");
        }
        q.setParameter("hsbacmMaso", hsbacmMaso);

        System.out.println("hsbacmMaso:"+hsbacmMaso);

        List<PhieuChamSoc> listCLS = q.getResultList();

        System.out.println("listCLS:"+listCLS);

        for (PhieuChamSoc pcs : listCLS) {
            em.remove(pcs);
        }
    }
    
    public PhieuChamSoc createPhieuChamSoc(PhieuChamSoc pcs) {
        if (pcs.getPhieuchamsocMaso() == null) {
            em.persist(pcs);
        } else {
            em.merge(pcs);
        }
        return pcs;
    }
}
