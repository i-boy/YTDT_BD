/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import com.iesvn.yte.dieutri.entity.ToaLinhKham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class ToaLinhKhamFacade implements ToaLinhKhamFacadeLocal, ToaLinhKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ToaLinhKham toaLinhKham) {
        em.persist(toaLinhKham);
    }

    public void edit(ToaLinhKham toaLinhKham) {
        em.merge(toaLinhKham);
    }

    public void remove(ToaLinhKham toaLinhKham) {
        em.remove(em.merge(toaLinhKham));
    }

    public ToaLinhKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ToaLinhKham.class, id);
    }

    public List<ToaLinhKham> findAll() {
        return em.createQuery("select object(o) from ToaLinhKham as o").getResultList();
    }
    
     public List<CtToaLinhk> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham) {
        Query q = em.createQuery("select object(o) from CtToaLinhk as o where  o.toalinhkhamMa.toalinhkhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.toalinhkhamMa.toalinhkhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa");
        q.setParameter("dtdmbankhamMa", maBanKham);
        q.setParameter("tiepdonMa", maTiepDon);

        return q.getResultList();
    }
    
     public boolean createToaLinhKham(List<CtToaLinhk> listTpk, ToaLinhKham toaLinhK) {
//        System.out.println("-----createThuocPhongKham-----");
//        
//        boolean result = false;
//        if (listTpk != null) {
//            System.out.println("-----listTpk: " + listTpk);
//            StringBuffer sb = new StringBuffer();
//            int i = 0;
//            for (CtToaLinhk ctToaLinhK : listTpk) {
//                
//                Integer maTpk = tpk.getToalinhkhamMa();
//                 
//                if (maTpk != null) {
//                    System.out.println("-----ToaLinhKham: " + maTpk);
//                    em.merge(tpk);
//                    if (i == 0) {
//                        sb.append(maTpk.toString());
//                    } else {
//                        sb.append(", " + maTpk);
//                    }
//                } else {
//                    System.out.print("-----Insert ToaLinhKham.");
//                    em.persist(tpk);
//                    
//                }
//            }
//            
//            try {
//                System.out.print("-----Delete ToaLinhKham: " + sb.toString());
//                Query q = em.createQuery("Select t From ToaLinhKham t Where t.toalinhkhamMa Not In (" 
//                        + sb.toString() + ") And t.toalinhkhamThamkham = :toalinhkhamThamkham ");
//                q.setParameter("toalinhkhamThamkham", listTpk.get(0).getToalinhkhamThamkham());
//                List<ToaLinhKham> listThuoc = q.getResultList();
//                if (listThuoc != null) {
//                    for (ToaLinhKham tpk : listThuoc) {
//                        em.remove(tpk);
//                    }
//                }
//            } catch (Exception e) {
//                
//            }
//           
//            result = true;
//        }
        return false;
    }

    public boolean createToaLinhKham(List<ToaLinhKham> listTpk) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     

}


