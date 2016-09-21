/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.NguoiDung;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ThanhDo
 */
@Stateless
public class NguoiDungFacade  implements  NguoiDungFacadeRemote, NguoiDungFacadeLocal  {
    
 @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(NguoiDung nguoiDung) {
        em.persist(nguoiDung);
    }

    public void edit(NguoiDung nguoiDung) {
        em.merge(nguoiDung);
    }

    public void remove(NguoiDung nguoiDung) {
        em.remove(em.merge(nguoiDung));
    }

    public NguoiDung find(Object id) {
        return em.find(com.iesvn.yte.entity.NguoiDung.class, id);
    }
    public NguoiDung findByMa(String  maND) { // ma : ten da nhap
        List<NguoiDung> obj =  em.createQuery("select object(o) from NguoiDung as o where upper(o.ndTendangnhap) like upper(:ndTendangnhap)").setParameter("ndTendangnhap", maND).getResultList();
        if (obj!=null && obj.size() > 0){
            return (NguoiDung)obj.get(0);
        }
        return null;
    }

    public List<NguoiDung> findAll() {
        return em.createQuery("select object(o) from NguoiDung as o").getResultList();
    }
    
    public Boolean createNguoiDung(NguoiDung nd, DtDmNhanVien nv) {
        Boolean result = false;
        try {
            em.persist(nd);
            nv.setDtdmnhanvienChon(true);
            nv.setNdMaso(nd);
            em.merge(nv);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
