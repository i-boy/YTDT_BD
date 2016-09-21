/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuKbVaoVien;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author James
 */
@Stateless
public class PhieuKbVaoVienFacade implements PhieuKbVaoVienFacadeLocal, PhieuKbVaoVienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(PhieuKbVaoVien phieuKbVaoVien) {
        getEm().persist(phieuKbVaoVien);
    }

    public void edit(PhieuKbVaoVien phieuKbVaoVien) {
        getEm().merge(phieuKbVaoVien);
    }

    public void remove(PhieuKbVaoVien phieuKbVaoVien) {
        getEm().remove(getEm().merge(phieuKbVaoVien));
    }

    public PhieuKbVaoVien find(Object id) {
        return getEm().find(PhieuKbVaoVien.class, id);
    }

    public List<PhieuKbVaoVien> findAll() {
        return getEm().createQuery("select object(o) from PhieuKbVaoVien as o").getResultList();
    }

    public List<PhieuKbVaoVien> findByPhieuKbVaoVien(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM PhieuKbVaoVien c WHERE  c.pkbvvMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public PhieuKbVaoVien findByMaThamKham(Integer iMaThamKham) {
        Query q;
//        List<PhieuKbVaoVien> list=new ArrayList<PhieuKbVaoVien>();
        q = getEm().createQuery("SELECT object(c) FROM PhieuKbVaoVien c WHERE  c.pkbvvThamkham.thamkhamMa = :maPhieu ");
        q.setParameter("maPhieu", iMaThamKham);
        PhieuKbVaoVien obj=null;
        List<PhieuKbVaoVien> list=q.getResultList();
        if(list!=null && list.size()>0)
            obj=list.get(0);
        return obj;
    }

    public String capNhatPhieuKbVaoVien(PhieuKbVaoVien obj, String sMaPhieu)
    {
        String result="";
        if (sMaPhieu == null || sMaPhieu.equals("")) {
                sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
                result=sMaPhieu;
                obj.setPkbvvMa(sMaPhieu);
                getEm().persist(obj);
                System.out.println("******* INSERT SUCCESS PhieuKbVaoVien ******");
            }
        else
        {
            result=sMaPhieu;
            obj.setPkbvvMa(sMaPhieu);
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS PhieuKbVaoVien ******");
        }
        System.out.println("******* Ma PhieuKbVaoVien: "+result);

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
