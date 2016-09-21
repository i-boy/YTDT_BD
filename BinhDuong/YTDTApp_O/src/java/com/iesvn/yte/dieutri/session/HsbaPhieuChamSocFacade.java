/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;


import com.iesvn.yte.dieutri.entity.HsbaPhieuChamSoc;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thanh
 */
@Stateless
public class HsbaPhieuChamSocFacade implements HsbaPhieuChamSocFacadeLocal, HsbaPhieuChamSocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

      @Resource
    private SessionContext context;

      
    public void create(HsbaPhieuChamSoc hsbaPhieuChamSoc) {
        em.persist(hsbaPhieuChamSoc);
    }

    public void edit(HsbaPhieuChamSoc hsbaPhieuChamSoc) {
        em.merge(hsbaPhieuChamSoc);
    }

    public void remove(HsbaPhieuChamSoc hsbaPhieuChamSoc) {
        em.remove(em.merge(hsbaPhieuChamSoc));
    }

    public HsbaPhieuChamSoc find(Object id) {
        return em.find(HsbaPhieuChamSoc.class, id);
    }

    public List<HsbaPhieuChamSoc> findAll() {
        return em.createQuery("select object(o) from HsbaPhieuChamSoc as o").getResultList();
    }

      public List<HsbaPhieuChamSoc> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaPhieuChamSoc as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        return q.getResultList();

    }


     public List<HsbaPhieuChamSoc> findBySoVaoVien(String soVaoVien) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaPhieuChamSoc as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien ");
        q.setParameter("soVaoVien", soVaoVien);

        return q.getResultList();

    }

    /**
     *
     * @param listCLS
     * @param soVaoVien
     * @return
     */
    public String createPhieuChamSoc(List<HsbaPhieuChamSoc> listPhieuChamSocDieuTri, String soVaoVien, String khoaMa) {

        soVaoVien = Utils.formatMa(em, soVaoVien);

        String result = "";
       try {

            String listpcsMa = "";
            for (int i = 0; i < listPhieuChamSocDieuTri.size(); i++) {

                HsbaPhieuChamSoc pcs = listPhieuChamSocDieuTri.get(i);

//                System.out.print("cls.getHsbaSovaovien():" + cls.getHsbaSovaovien());

                if (pcs.getHsbapcsMa() != null) {

                    if (listpcsMa.equals("")) {
                        listpcsMa +=pcs.getHsbapcsMa() ;
                    } else {
                        listpcsMa += "," + pcs.getHsbapcsMa() ;
                    }
                }

            }
            System.out.print("listpcsMa:" + listpcsMa);
            //if (!listclsMa.equals("")) {
            System.out.print("soVaoVien:" + soVaoVien);
            this.removeItem(listpcsMa, soVaoVien,khoaMa);
            //}
            for (HsbaPhieuChamSoc pcs : listPhieuChamSocDieuTri) {

                if (pcs.getHsbapcsMa() != null) {

                    System.out.println("pcs.getHsbapcsMa()  :" + pcs.getHsbapcsMa() );
                    em.merge(pcs);
                    System.out.println("update thanh cong chi tiet pcs");

                } else {

                    em.persist(pcs);
                    System.out.println("insert them thanh cong chi tiet pcs " + pcs.getHsbapcsMa());

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

    public void removeItem(String listpcsMa, String soVaoVien, String khoaMa) {


        Query q;
        if (listpcsMa == null || listpcsMa.equals("")) {
            q = em.createQuery("SELECT c FROM HsbaPhieuChamSoc c WHERE c.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND  c.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa ");

        } else {
            q = em.createQuery("SELECT c FROM HsbaPhieuChamSoc c WHERE  c.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND  c.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa  and c.hsbatdtMa NOT IN (" + listpcsMa + ") ");
        }
        q.setParameter("soVaoVien", soVaoVien);
         q.setParameter("khoaMa", khoaMa);

        System.out.println("soVaoVien:"+soVaoVien);

        List<HsbaPhieuChamSoc> listPCS = q.getResultList();

        System.out.println("listPCS:"+listPCS);

        for (HsbaPhieuChamSoc pcs : listPCS) {
            em.remove(pcs);
        }
    }
    

}
