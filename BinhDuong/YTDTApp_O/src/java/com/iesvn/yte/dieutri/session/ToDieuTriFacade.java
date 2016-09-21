/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ToDieuTri;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.ArrayList;
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
 * @author halylam
 */
@Stateless
public class ToDieuTriFacade implements ToDieuTriFacadeLocal, ToDieuTriFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
     @EJB
    private HsbaFacadeLocal hsbaFacade;
     
    public void create(ToDieuTri toDieuTri) {
        em.persist(toDieuTri);
    }

    public void edit(ToDieuTri toDieuTri) {
        em.merge(toDieuTri);
    }

    public void remove(ToDieuTri toDieuTri) {
        em.remove(em.merge(toDieuTri));
    }

    public ToDieuTri find(Object id) {
        return em.find(ToDieuTri.class, id);
    }

    public List<ToDieuTri> findAll() {
        return em.createQuery("select object(o) from ToDieuTri as o").getResultList();
    }

//     public List<ToDieuTri> findByHsbaCM(Integer hsbacmmaso) {
//        System.out.println("call findByHsbaCM");
//
//       List<ToDieuTri> lst =  em.createQuery("select object(o) from ToDieuTri as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso ").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
//       System.out.println("lst:"+lst);
//       return lst;
//
//    }
    public List<ToDieuTri> findByHsba(String hsbaMaso) {
        System.out.println("call findByHsba");
        List<ToDieuTri> lst = new ArrayList<ToDieuTri>();
        if (findAll().size() > 0) {
            lst = em.createQuery("select object(o) from ToDieuTri as o where o.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien order by o.todieutriNgaygio ").setParameter("hsbaSovaovien", hsbaMaso).getResultList();
        }        
        return lst;

    }

    public String capNhatToDieuTri(List<ToDieuTri> listTDT, String hsbaMaso) {

        String result = "";
        String maPhieu = "";
        if (listTDT.size() > 0) {
            boolean needMaPhieu = true;
            for (int i = 0; i < listTDT.size(); i++) {

                ToDieuTri tdt = listTDT.get(i);
                if ((tdt.getTodieutriMa() != null && !tdt.getTodieutriMa().equals(""))) {
                    needMaPhieu = false;
                    break;
                }

            }
            if (needMaPhieu == true) {
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 1 :" + maPhieu);
                maPhieu = Utils.maPhieuToDieuTri(em);
                System.out.println("needMaPhieuneedMaPhieuneedMaPhieu 2 :" + maPhieu);
            }

        }

        System.out.println("maPhieu: " + maPhieu);
        try {

            String listtdtMa = "";
            for (int i = 0; i < listTDT.size(); i++) {

                ToDieuTri tdt = listTDT.get(i);

//                System.out.print("cls.getHsbaSovaovien():" + cls.getHsbaSovaovien());

                if (tdt.getTodieutriMa() != null && !tdt.getTodieutriMa().equals("")) {

                    if (listtdtMa.equals("")) {
                        listtdtMa += tdt.getTodieutriMaso();
                    } else {
                        listtdtMa += "," + tdt.getTodieutriMaso();
                    }
                }

            }
            System.out.print("listtdtMa:" + listtdtMa);

            this.removeItem(listtdtMa, hsbaMaso);
            // Get object hsba
            Hsba hsba = hsbaFacade.find(hsbaMaso);
            for (ToDieuTri tdt : listTDT) {


                if (tdt.getTodieutriMaso() != null) {


                    tdt.setTodieutriMa(maPhieu);


                    em.merge(tdt);
                    System.out.println("update thanh cong chi tiet tdt");
                } else {
//                    System.out.print("clsMo.getHsbaSovaovien:" + clsMo.getHsbaSovaovien());
                    tdt.setHsbaSovaovien(hsba);
                    tdt.setTodieutriMa(maPhieu);
                    em.merge(tdt);
                    System.out.println("insert them thanh cong chi tiet  " + maPhieu);

                }

            }
            result = "0";

        } catch (Exception ex) {
            result = "-1";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    public void removeItem(String listpcsMa, String hsbaMaso) {


        Query q;
        if (listpcsMa == null || listpcsMa.equals("")) {
            //q = em.createQuery("SELECT c FROM ToDieuTri c WHERE ( c.todieutriMa is null or c.todieutriMa = '' ) AND  c.hsbacmMa.hsbacmMa like :hsbacmMaso ");
            q = em.createQuery("Delete FROM ToDieuTri c WHERE ( c.todieutriMa is null or c.todieutriMa = '' ) AND  c.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien ");    
        } else {
            //q = em.createQuery("SELECT c FROM ToDieuTri c WHERE ( c.todieutriMa is null or c.todieutriMa = '' )  and c.todieutriMaso NOT IN (" + listpcsMa + ") AND  c.hsbacmMa.hsbacmMa like :hsbacmMaso ");
            q = em.createQuery("DELETE FROM ToDieuTri c WHERE ( c.todieutriMa is null or c.todieutriMa = '' )  and c.todieutriMaso NOT IN (" + listpcsMa + ") AND  c.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien ");
        }
        q.setParameter("hsbaSovaovien", hsbaMaso);

        System.out.println("hsbaSovaovien :" + hsbaMaso);
        int count = q.executeUpdate();
        
        System.out.println("delete " + count + " item(s)");
        
    }
    public ToDieuTri createToDieuTri(ToDieuTri tdt) {
        if (tdt.getTodieutriMaso() == null) {
            em.persist(tdt);
        } else {
            em.merge(tdt);
        }
        return tdt;
    }
}
