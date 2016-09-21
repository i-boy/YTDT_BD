/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;
import com.iesvn.yte.dieutri.entity.HsbaToDieuTri;
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
public class HsbaToDieuTriFacade implements HsbaToDieuTriFacadeLocal, HsbaToDieuTriFacadeRemote {
    @PersistenceContext
    private EntityManager em;

     @Resource
    private SessionContext context;
   

    public void create(HsbaToDieuTri hsbaToDieuTri) {
        em.persist(hsbaToDieuTri);
    }

    public void edit(HsbaToDieuTri hsbaToDieuTri) {
        em.merge(hsbaToDieuTri);
    }

    public void remove(HsbaToDieuTri hsbaToDieuTri) {
        em.remove(em.merge(hsbaToDieuTri));
    }

    public HsbaToDieuTri find(Object id) {
        return em.find(HsbaToDieuTri.class, id);
    }

    public List<HsbaToDieuTri> findAll() {
        return em.createQuery("select object(o) from HsbaToDieuTri as o").getResultList();
    }

      public List<HsbaToDieuTri> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaToDieuTri as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien and  o.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa);
        return q.getResultList();

    }
  

     public List<HsbaToDieuTri> findBySoVaoVien(String soVaoVien) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaToDieuTri as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien ");
        q.setParameter("soVaoVien", soVaoVien);

        return q.getResultList();

    }

    /**
     *
     * @param listCLS
     * @param soVaoVien
     * @return
     */
    public String createToDieuTri(List<HsbaToDieuTri> listToDieuTri, String soVaoVien, String khoaMa) {

        soVaoVien = Utils.formatMa(em, soVaoVien);

        String result = "";
       try {

            String listtdtMa = "";
            for (int i = 0; i < listToDieuTri.size(); i++) {

                HsbaToDieuTri tdt = listToDieuTri.get(i);

//                System.out.print("cls.getHsbaSovaovien():" + cls.getHsbaSovaovien());

                if (tdt.getHsbatdtMa() != null) {

                    if (listtdtMa.equals("")) {
                        listtdtMa += tdt.getHsbatdtMa();
                    } else {
                        listtdtMa += "," + tdt.getHsbatdtMa();
                    }
                }

            }
            System.out.print("listtdtMa:" + listtdtMa);
            //if (!listclsMa.equals("")) {
            System.out.print("soVaoVien:" + soVaoVien);
            this.removeItem(listtdtMa, soVaoVien,khoaMa);
            //}
            for (HsbaToDieuTri tdt : listToDieuTri) {
              
                if (tdt.getHsbatdtMa() != null) {

                    System.out.println("tdt.getHsbatdtMa()  :" + tdt.getHsbatdtMa() );
                    em.merge(tdt);
                    System.out.println("update thanh cong chi tiet tdt");

                } else {
                 
                    em.persist(tdt);
                    System.out.println("insert them thanh cong chi tiet tdt " + tdt.getHsbatdtMa());

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

    public void removeItem(String listtdtMa, String soVaoVien, String khoaMa) {


        Query q;
        if (listtdtMa == null || listtdtMa.equals("")) {
            q = em.createQuery("SELECT c FROM HsbaToDieuTri c WHERE c.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND  c.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa ");

        } else {
            q = em.createQuery("SELECT c FROM HsbaToDieuTri c WHERE  c.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien AND  c.hsbacmMa.khoaMa.dmkhoaMa like :khoaMa  and c.hsbatdtMa NOT IN (" + listtdtMa + ") ");
        }
        q.setParameter("soVaoVien", soVaoVien);
         q.setParameter("khoaMa", khoaMa);

        System.out.println("soVaoVien:"+soVaoVien);

        List<HsbaToDieuTri> listTDT = q.getResultList();

        System.out.println("listTDT:"+listTDT);

        for (HsbaToDieuTri tdt : listTDT) {
            em.remove(tdt);
        }
    }
}
