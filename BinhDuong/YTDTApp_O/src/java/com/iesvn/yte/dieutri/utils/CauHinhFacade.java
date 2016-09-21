/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.utils;

import com.iesvn.yte.dieutri.entity.CauHinh;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class CauHinhFacade implements CauHinhFacadeLocal, CauHinhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CauHinh cauHinh) {
        getEm().persist(cauHinh);
    }

    public void edit(CauHinh cauHinh) {
        getEm().merge(cauHinh);
    }

    public void remove(CauHinh cauHinh) {
        getEm().remove(getEm().merge(cauHinh));
    }

    public CauHinh find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.CauHinh.class, id);
    }

    public List<CauHinh> findAll() {
        return getEm().createQuery("select object(o) from CauHinh as o").getResultList();
    }
    public CauHinh findByMa(String tencauhinh) {
        List<CauHinh> lstCauHinh =  getEm().createQuery("select object(o) from CauHinh as o where o.chbvName like :tencauhinh").setParameter("tencauhinh", tencauhinh). getResultList();
        if (lstCauHinh != null && lstCauHinh.size() > 0){
            return lstCauHinh.get(0);
        }
        return null;
    }
    public String getMaTiepDonAndMaBenhNhan(){
        return Utils.getMaTiepDon(getEm()) +"___"+Utils.getMaBenhNhan(getEm());
    }
    
    public String getMaTiepDon(){
        return Utils.getMaTiepDon(getEm()) ;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

}


