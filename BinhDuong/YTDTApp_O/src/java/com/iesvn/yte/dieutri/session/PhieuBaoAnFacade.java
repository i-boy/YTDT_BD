/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author james
 */
@Stateless
public class PhieuBaoAnFacade implements PhieuBaoAnFacadeLocal, PhieuBaoAnFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(PhieuBaoAn phieuBaoAn) {
        em.persist(phieuBaoAn);
    }

    public void edit(PhieuBaoAn phieuBaoAn) {
        em.merge(phieuBaoAn);
    }

    public void remove(PhieuBaoAn phieuBaoAn) {
        em.remove(em.merge(phieuBaoAn));
    }

    public PhieuBaoAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.PhieuBaoAn.class, id);
    }

    public List<PhieuBaoAn> findAll() {
        return em.createQuery("select object(o) from PhieuBaoAn as o").getResultList();
    }

    public List<PhieuBaoAn> findByPhieuBaoAn(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.phieubaoanMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<PhieuBaoAn> findByKhoaLoaiAn(String maKhoa, String maLoaiAn) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.khoaMaso.dmkhoaMa like :maKhoa AND c.loaianMaso.dtdmlaMa like :maLoaiAn");
        q.setParameter("maKhoa", maKhoa);
        q.setParameter("maLoaiAn", maLoaiAn);
        return q.getResultList();
    }

    public String capnhatphieubaoan(List<PhieuBaoAn> listPhieuBaoAn,
            String maphieuBaoAn) {


        String result = "";
       

        if (listPhieuBaoAn.size() > 0) {


            if (maphieuBaoAn == null || maphieuBaoAn.equals("")) {

                maphieuBaoAn = Utils.maPhieuBaoAn(em);
  
            }

            for (int i = 0; i < listPhieuBaoAn.size(); i++) {
                PhieuBaoAn pba = listPhieuBaoAn.get(i);
                pba.setPhieubaoanMa(maphieuBaoAn);
            }

        }

        System.out.println("maPhieu: " + maphieuBaoAn);
        try {

            String listpbaMaSo = "";
            for (int i = 0; i < listPhieuBaoAn.size(); i++) {

                PhieuBaoAn pba = listPhieuBaoAn.get(i);


                if (pba.getPhieubaoanMaso() != null) {

                    if (listpbaMaSo.equals("")) {
                        listpbaMaSo += pba.getPhieubaoanMaso();
                    } else {
                        listpbaMaSo += "," + pba.getPhieubaoanMaso();
                    }
                }

            }


            this.removeItem(listpbaMaSo, maphieuBaoAn);

            for (PhieuBaoAn pba : listPhieuBaoAn) {

                if (pba.getPhieubaoanMaso() != null) {

                    em.merge(pba);
                    System.out.println("update thanh cong pba");
                } else {


                    em.persist(pba);
                    System.out.println("insert them thanh cong phieu bao an: " + pba.getPhieubaoanMaso());

                }

            }
            result = maphieuBaoAn;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    public void removeItem(String listpbaMaSo, String maphieuBaoAn) {


        Query q;
        if (listpbaMaSo == null || listpbaMaSo.equals("")) {
            q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.phieubaoanMa like :maphieuBaoAn ");

        } else {
            q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.phieubaoanMa like :maphieuBaoAn  and c.phieubaoanMaso NOT IN (" + listpbaMaSo + ")  ");
        }
        q.setParameter("maphieuBaoAn", maphieuBaoAn);



        List<PhieuBaoAn> listPBA = q.getResultList();

        System.out.println("listPBA:" + listPBA);

        for (PhieuBaoAn pba : listPBA) {
            em.remove(pba);
        }
    }
    //ham lay phieu bao an theo ngay an
    public PhieuBaoAn findByKhoaNgayAn(String maKhoa, Date ngayAn) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.khoaMaso.dmkhoaMa = :maKhoa AND c.phieubaoanNgayan =  :ngayAn");
        q.setParameter("maKhoa", maKhoa);
        q.setParameter("ngayAn", ngayAn);
        if (q.getResultList().size() > 0) {
            return (PhieuBaoAn)q.getResultList().get(0);
        } else {
            return null;
        }
    }
    
    public PhieuBaoAn myCreate(PhieuBaoAn phieuBaoAn, boolean isUpdate) {
         if (isUpdate) {
             em.merge(phieuBaoAn);
         } else {
             em.persist(phieuBaoAn);
         }
         return phieuBaoAn;
    }
    // ham lay phieu bao an ngay truoc ngay an
    public PhieuBaoAn findByKhoaNgayAn2(String maKhoa, Date ngayAn) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuBaoAn c WHERE  c.khoaMaso.dmkhoaMa = :maKhoa AND c.phieubaoanNgayan <=  :ngayAn ORDER BY c.phieubaoanNgayan DESC");
        q.setParameter("maKhoa", maKhoa);
        q.setParameter("ngayAn", ngayAn);
        if (q.getResultList().size() > 0) {
            return (PhieuBaoAn)q.getResultList().get(0);
        } else {
            return null;
        }
    }
}
