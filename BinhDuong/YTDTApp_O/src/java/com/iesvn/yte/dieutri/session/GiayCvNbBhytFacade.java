/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author james
 */
@Stateless
public class GiayCvNbBhytFacade implements GiayCvNbBhytFacadeLocal, GiayCvNbBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(GiayCvNbBhyt giayCvNbBhyt) {
        getEm().persist(giayCvNbBhyt);
    }

    public void edit(GiayCvNbBhyt giayCvNbBhyt) {
        getEm().merge(giayCvNbBhyt);
    }

    public void remove(GiayCvNbBhyt giayCvNbBhyt) {
        getEm().remove(getEm().merge(giayCvNbBhyt));
    }

    public GiayCvNbBhyt find(Object id) {
        return getEm().find(GiayCvNbBhyt.class, id);
    }

    public List<GiayCvNbBhyt> findAll() {
        return getEm().createQuery("select object(o) from GiayCvNbBhyt as o").getResultList();
    }

    public List<GiayCvNbBhyt> findByGiayCvNbBhyt(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM GiayCvNbBhyt c WHERE  c.gcvbhytMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public GiayCvNbBhyt findByMaThamKham(Integer iMaThamKham) {
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM GiayCvNbBhyt c WHERE  c.gcvbhytThamkham.thamkhamMa = :maPhieu ");
        q.setParameter("maPhieu", iMaThamKham);
        GiayCvNbBhyt obj=null;//=new GiayCvNbBhyt();
        List<GiayCvNbBhyt> list=q.getResultList();
        if(list!=null && list.size()>0)
        {
            System.out.println("****List<GiayCvNbBhyt> :"+list.size());
            obj=list.get(0);
            System.out.println("****List<GiayCvNbBhyt> :"+list.size());
        }
        return obj;
    }

    public String capNhatGiayCvNbBhyt(GiayCvNbBhyt obj, String sMaPhieu)
    {
        String result="";
        if (sMaPhieu == null || sMaPhieu.equals("")) {
                sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
                result=sMaPhieu;
                obj.setGcvbhytMa(sMaPhieu);
                getEm().persist(obj);
                System.out.println("******* INSERT SUCCESS GiayCvNbBhyt ******");
            }
        else
        {
            result=sMaPhieu;
            obj.setGcvbhytMa(sMaPhieu);
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS GiayCvNbBhyt ******");
        }
        System.out.println("******* Ma GiayCvNbBhyt: "+result);

        return result;
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
