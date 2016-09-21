/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;
import com.iesvn.yte.dieutri.entity.HsbaPhieuGuiXac;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author quang
 */
@Stateless
public class HsbaPhieuGuiXacFacade implements HsbaPhieuGuiXacFacadeRemote, HsbaPhieuGuiXacFacadeLocal {

   @PersistenceContext
    private EntityManager em;

   
    public void create(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
        em.persist(hsbaPhieuGuiXac);
    }

    public void edit(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
        em.merge(hsbaPhieuGuiXac);
    }

    public void remove(HsbaPhieuGuiXac hsbaPhieuGuiXac) {
        em.remove(em.merge(hsbaPhieuGuiXac));
    }

    public HsbaPhieuGuiXac find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaPhieuGuiXac.class, id);
    }

    public List<HsbaPhieuGuiXac> findAll() {
        return em.createQuery("select object(o) from HsbaPhieuGuiXac as o").getResultList();
    }

    public String insert(HsbaPhieuGuiXac obj){
        System.out.println("---insert phieu gui xac");
        try{
            String ma = Utils.maPhieuGuiXac(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbapgxMa(ma);
            em.persist(obj);
            System.out.println("---insert phieu gui xac: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert phieu gui xac: err");
            return "";
        }
    }

    public String update(HsbaPhieuGuiXac obj){
        System.out.println("---update phieu gui xac");
        try{
            System.out.println("---ma update: " + obj.getHsbapgxMa());
            em.merge(obj);
            System.out.println("---update phieu gui xac: success");
            return obj.getHsbapgxMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update phieu gui xac: err");
            return "";
        }
    }

    public HsbaPhieuGuiXac findByHsbapgxMa(String ma){
        System.out.println("---find HsbaPhieuGuiXac by HsbapgxMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaPhieuGuiXac  as o where o.hsbapgxMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaPhieuGuiXac> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaPhieuGuiXac) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}
