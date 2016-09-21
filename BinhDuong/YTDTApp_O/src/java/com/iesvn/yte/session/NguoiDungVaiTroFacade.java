/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.session;

import com.iesvn.yte.entity.NguoiDungVaiTro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ThanhDo
 */
@Stateless
public class NguoiDungVaiTroFacade implements NguoiDungVaiTroFacadeLocal, NguoiDungVaiTroFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(NguoiDungVaiTro nguoiDungVaiTro) {
        em.persist(nguoiDungVaiTro);
    }

    public void edit(NguoiDungVaiTro nguoiDungVaiTro) {
        em.merge(nguoiDungVaiTro);
    }

    public void remove(NguoiDungVaiTro nguoiDungVaiTro) {
        em.remove(em.merge(nguoiDungVaiTro));
    }

    public NguoiDungVaiTro find(Object id) {
        return em.find(com.iesvn.yte.entity.NguoiDungVaiTro.class, id);
    }

    public List<NguoiDungVaiTro> findAll() {
        return em.createQuery("select object(o) from NguoiDungVaiTro as o").getResultList();
    }

    public NguoiDungVaiTro findByMaSoNguoiDungAndMaSoVaiTro(Integer nguoiDungMaSo, Integer vaiTroMaSo) {

        Object obj = em.createQuery("select object(o) from NguoiDungVaiTro as o where o.ndMaso.ndMaso = :ndMaso and o.vaitroMaso.vaitroMaso = :vaitroMaso").setParameter("ndMaso", nguoiDungMaSo).setParameter("vaitroMaso", vaiTroMaSo).getSingleResult();
        if (obj != null) {
            return (NguoiDungVaiTro) obj;
        }
        return null;

    }

    public List<NguoiDungVaiTro> findByNguoiDung(Integer nguoiDungMaSo) {
        System.out.println("-----nguoiDungMaSo: " + nguoiDungMaSo);
        
        return em.createQuery("select object(o) from NguoiDungVaiTro as o where o.ndMaso.ndMaso = :ndMaso ").setParameter("ndMaso", nguoiDungMaSo).getResultList();
    }
}
