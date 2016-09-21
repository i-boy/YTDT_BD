/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thanh
 */
@Stateless
public class ThongKeTramYTeBhytFacade implements ThongKeTramYTeBhytRemote, ThongKeTramYTeBhytLocal {
    
    @PersistenceContext
    private EntityManager em;
    public void create(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
       em.persist(thongKeTramYTeBhyt);
    }

    public void edit(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
        em.merge(thongKeTramYTeBhyt);
    }

    public void remove(ThongKeTramYTeBhyt thongKeTramYTeBhyt) {
       em.remove(em.merge(thongKeTramYTeBhyt));
    }

    public ThongKeTramYTeBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt.class, id);
    }

    public List<ThongKeTramYTeBhyt> findAll() {
         return em.createQuery("select object(o) from ThongKeTramYTeBhyt as o").getResultList();
    }
    
    public String luuTruTTThongKeTramYTeBHYT (List<ThongKeTramYTeBhyt> lstTKTram){
        if (lstTKTram == null){
            return "";
        }
        for (ThongKeTramYTeBhyt tk: lstTKTram){
            if (tk.getTktramytebhytMaso() == null){
                em.persist(tk);
            }else{                
                em.merge(tk);
            }
        }
        return "";
    }
    public List<ThongKeTramYTeBhyt> findByThangNamTramYTe(String thang, String nam, String matramyte, Boolean noitru) {
        if (matramyte == null || matramyte.equals("")){
            matramyte = "%";
        }
        if (thang == null || thang.equals("")){
            thang = "%";
        }
        if (nam == null || nam.equals("")){
            nam = "%";
        }
         return em.createQuery("select object(o) from ThongKeTramYTeBhyt as o" +
                 " where o.tktramytebhytThang like :thang" +
                 " and o.tktramytebhytNam like :nam" +
                 " and o.dtdmtramytebhytMaso.dtdmtramytebhytMa like :matramyte" +
                 " and o.dtdmtramytebhytNoiTru = :noitru" +
                 "").setParameter("thang", thang)
                 .setParameter("nam", nam)
                 .setParameter("matramyte", matramyte)  
                 .setParameter("noitru", noitru)
                 .getResultList();
    }
    
   
}
