/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class BenhNhanFacade implements BenhNhanFacadeLocal, BenhNhanFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private TiepDonFacadeLocal tdFacade;

    public void create(BenhNhan benhNhan) {
        getEm().persist(benhNhan);
    }

    public void edit(BenhNhan benhNhan) {
        getEm().merge(benhNhan);
    }

    public void remove(BenhNhan benhNhan) {
        getEm().remove(getEm().merge(benhNhan));
    }

    public BenhNhan find(Object id) {
        String maBenhNhan = (String) id;
        //System.out.println("--Ma benh nhan1 ---:" + maBenhNhan);
        maBenhNhan = Utils.formatMa(getEm(), maBenhNhan);
        //System.out.println("--Ma benh nhan2 ---:" + maBenhNhan);
        //System.out.println("------------tim benhnhan obj");

        return getEm().find(com.iesvn.yte.dieutri.entity.BenhNhan.class, maBenhNhan);

    }
    
    
    public List<BenhNhan> findByHoTen(String hoTen){            
        Query q = em.createQuery("Select object(b) From BenhNhan b Where lower(b.benhnhanHoten) like :hoTen");
        q.setParameter("hoTen", "%" + hoTen.toLowerCase().trim() + "%");
        return q.getResultList();       
    }
     public List<BenhNhan> findByMaBN(String mabenhnhan){
        Query q = em.createQuery("Select object(b) From BenhNhan b Where b.benhnhanMa like :mabenhnhan");
        q.setParameter("benhnhanMa", mabenhnhan);
        return q.getResultList();
    }

    /**/
    public TiepDon findBySoTheBHYT(String sothebhyt){
        //Query q = em.createQuery("Select object(td) From BenhNhan b , " +
        //        " TiepDon td  Where td.benhnhanMa like b.benhnhanMa and td.tiepdonSothebh like :sothebhyt order by b.benhnhanMa DESC");

        // 20110329 bao.ttc: toi uu querry dang dung
        Query q = em.createQuery("Select object(td) From TiepDon td Where td.tiepdonSothebh like :sothebhyt order by td.benhnhanMa DESC");

        q.setParameter("sothebhyt",sothebhyt);

        List<TiepDon> lstBN = q.getResultList();
        
        if(  lstBN != null && lstBN.size() > 0){
            return lstBN.get(0);
        }else{
            return null;
        }
    }
    
    public Long getLanVao(String benhnhanMa) {
        Long lanvao = new Long("0");
        try {
            Query q = em.createQuery("Select count(t) From TiepDon t Where t.benhnhanMa.benhnhanMa = :benhnhanMa");
            q.setParameter("benhnhanMa", benhnhanMa);
            lanvao = (Long) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lanvao;
    }

    public List<BenhNhan> findAll() {
        return getEm().createQuery("select object(o) from BenhNhan as o").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


