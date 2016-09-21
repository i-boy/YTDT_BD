/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Calendar;
import java.util.Date;
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
public class HsbaNopFacade implements HsbaNopFacadeLocal, HsbaNopFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaNop hsbaNop) {
        em.persist(hsbaNop);
    }

    public void edit(HsbaNop hsbaNop) {
        em.merge(hsbaNop);
    }

    public void remove(HsbaNop hsbaNop) {
        em.remove(em.merge(hsbaNop));
    }

    public HsbaNop find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaNop.class, id);
    }

    public List<HsbaNop> findAll() {
        return em.createQuery("select object(o) from HsbaNop as o").getResultList();
    }
     public HsbaNop findBySoVaoVien(String soVaoVien) {

        soVaoVien = Utils.formatMa(em, soVaoVien);
        // System.out.println(soVaoVien);


        List<HsbaNop> hsbaNop= em.createQuery("select object(o) from HsbaNop as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien").setParameter("soVaoVien", soVaoVien).getResultList();
        //System.out.println(hsbaCV);
        if (hsbaNop != null && hsbaNop.size() > 0) {
            //System.out.println("-------------------------");
            return hsbaNop.get(0);
        } else {
            return null;
        }
    }
     public HsbaNop findBySoLuuTru(String soLuuTru) {

        soLuuTru = Utils.formatMa(em, soLuuTru);
        // System.out.println(soVaoVien);


        List<HsbaNop> hsbaNop= em.createQuery("select object(o) from HsbaNop as o where o.hsbanopSoluutru like :soLuuTru").setParameter("soLuuTru", soLuuTru).getResultList();
        //System.out.println(hsbaCV);
        if (hsbaNop != null && hsbaNop.size() > 0) {
            //System.out.println("-------------------------");
            return hsbaNop.get(0);
        } else {
            return null;
        }
    }

    public int soBAtrongngay(Date ngay) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(ngay);

        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date dDauNgay = cal.getTime();
        //System.out.println(dDauNgay);

        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        Date dCuoiNgay = cal.getTime();
        //System.out.println(dCuoiNgay);

        Number soLuongBA = 0;

        Query q = em.createQuery("select COUNT(o.hsbanopMa) from HsbaNop o where  o.hsbanopNgaygioluutru >= :dDauNgay and o.hsbanopNgaygioluutru <= :dCuoiNgay");
        q.setParameter("dDauNgay", dDauNgay);
        q.setParameter("dCuoiNgay", dCuoiNgay);

        soLuongBA = (Number)q.getSingleResult();

        return soLuongBA.intValue();
    }

}
