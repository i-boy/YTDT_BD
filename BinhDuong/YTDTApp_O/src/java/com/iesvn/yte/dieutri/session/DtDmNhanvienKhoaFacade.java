/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DtDmNhanvienKhoaFacade implements DtDmNhanvienKhoaFacadeLocal, DtDmNhanvienKhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmNhanvienKhoa dtDmNhanvienKhoa) {
        em.persist(dtDmNhanvienKhoa);
    }

    public void edit(DtDmNhanvienKhoa dtDmNhanvienKhoa) {
        em.merge(dtDmNhanvienKhoa);
    }

    public void remove(DtDmNhanvienKhoa dtDmNhanvienKhoa) {
        em.remove(em.merge(dtDmNhanvienKhoa));
    }

    public DtDmNhanvienKhoa find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa.class, id);
    }

    public List<DtDmNhanvienKhoa> findAll() {
        return em.createQuery("select object(o) from DtDmNhanvienKhoa as o").getResultList();
    }

  

    public DtDmNhanvienKhoa findByMaNguoiDung(DtDmNhanVien maND) {
       List<DtDmNhanvienKhoa> obj =  em.createQuery("select object(o) from DtDmNhanvienKhoa as o where o.dtdmnhanvienMaso =:ndTendangnhap").setParameter("ndTendangnhap", maND).getResultList();
        if (obj!=null && obj.size() > 0){
            return (DtDmNhanvienKhoa)obj.get(0);
        }
        return null;
    }

}


