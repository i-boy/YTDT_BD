/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author QuynhNhu
 */
@Stateless
public class PhieuKhamChuyenKhoaFacade implements PhieuKhamChuyenKhoaFacadeLocal, PhieuKhamChuyenKhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa) {
        getEm().persist(phieuKhamChuyenKhoa);
    }

    public void edit(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa) {
        getEm().merge(phieuKhamChuyenKhoa);
    }

    public void remove(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa) {
        getEm().remove(getEm().merge(phieuKhamChuyenKhoa));
    }

    public PhieuKhamChuyenKhoa find(Object id) {
        return getEm().find(PhieuKhamChuyenKhoa.class, id);
    }

    public List<PhieuKhamChuyenKhoa> findAll() {
        return getEm().createQuery("select object(o) from PhieuKhamChuyenKhoa as o").getResultList();
    }

    public List<PhieuKhamChuyenKhoa> findByPhieuKhamChuyenKhoa(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM PhieuKhamChuyenKhoa c WHERE  c.pkckMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

     public PhieuKhamChuyenKhoa findByMaThamKham(Integer iMaThamKham) {
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM PhieuKhamChuyenKhoa c WHERE  c.pkckThamkham.thamkhamMa = :maPhieu ");
        q.setParameter("maPhieu", iMaThamKham);
        PhieuKhamChuyenKhoa obj=null;
        List<PhieuKhamChuyenKhoa> list=q.getResultList();
        if(list!=null && list.size()>0)
            obj=list.get(0);
        return obj;
    }


    public String capNhatPhieuKhamChuyenKhoa(PhieuKhamChuyenKhoa obj, String sMaPhieu)
    {
        String result="";
        if (sMaPhieu == null || sMaPhieu.equals("")) {
                sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
                result=sMaPhieu;
                obj.setPkckMa(sMaPhieu);
                getEm().persist(obj);
                System.out.println("******* INSERT SUCCESS PhieuKhamChuyenKhoa ******");
            }
        else
        {
            result=sMaPhieu;
            obj.setPkckMa(sMaPhieu);
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS PhieuKhamChuyenKhoa ******");
        }
        System.out.println("******* Ma PhieuKhamChuyenKhoa: "+result);

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
