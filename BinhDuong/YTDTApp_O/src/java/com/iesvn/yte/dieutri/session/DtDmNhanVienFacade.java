/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmBacSiBanKham;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import com.iesvn.yte.entity.DmKhoa;
import java.util.ArrayList;
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
public class DtDmNhanVienFacade implements DtDmNhanVienFacadeLocal, DtDmNhanVienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmNhanVien dtDmNhanVien) {
        em.persist(dtDmNhanVien);
    }

    public void edit(DtDmNhanVien dtDmNhanVien) {
        em.merge(dtDmNhanVien);
    }

    public void remove(DtDmNhanVien dtDmNhanVien) {
        em.remove(em.merge(dtDmNhanVien));
    }

    public DtDmNhanVien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmNhanVien.class, id);
    }

    public List<DtDmNhanVien> findAll() {
        return em.createQuery("select object(o) from DtDmNhanVien as o where o.dtdmnhanvienChon = true").getResultList();
    }
    
    public DtDmNhanVien findByNd(String tenNd) {
        DtDmNhanVien nv = null;
        Query q = em.createQuery("Select n From DtDmNhanVien n Where upper(n.ndMaso.ndTendangnhap) = upper(:tenNd) and n.dtdmnhanvienChon = true");
        q.setParameter("tenNd", tenNd);
        try {
            List<DtDmNhanVien> listNv = q.getResultList();
            if (listNv != null) {
                nv = listNv.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return nv;
    }
    
    public List<DtDmBanKham> getListBanKham(Integer nvMaso) {
        List<DtDmBanKham> listBk = new ArrayList<DtDmBanKham>();
        
        try {
            DtDmNhanVien nv = em.find(DtDmNhanVien.class, nvMaso);
            if (nv != null) {
                Query q = em.createQuery("Select b From DtDmBacSiBanKham b Where b.DTDMNHANVIEN_MASO = :nv");
                q.setParameter("nv", nv);
                List<DtDmBacSiBanKham> listBsBk = q.getResultList();
                if (listBsBk != null) {
                    for (DtDmBacSiBanKham bsbk : listBsBk) {
                        listBk.add(bsbk.getDtdmbankhamMaso());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBk;
    }
    
    public List<DmKhoa> getListKhoa(Integer nvMaso) {
        List<DmKhoa> listK = new ArrayList<DmKhoa>();
        
        try {
            DtDmNhanVien nv = em.find(DtDmNhanVien.class, nvMaso);
            if (nv != null) {
                Query q = em.createQuery("Select b From DtDmNhanvienKhoa b Where b.dtdmnhanvienMaso = :nv");
                q.setParameter("nv", nv);
                List<DtDmNhanvienKhoa> listNvK = q.getResultList();
                if (listNvK != null) {
                    for (DtDmNhanvienKhoa nvk : listNvK) {
                        listK.add(nvk.getDmkhoaMaso());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listK;
    }

    public DtDmNhanVien findByMaNhanVien(String dtdmnhanvienMa) {
        Query q = em.createQuery("select object(o) from DtDmNhanVien as o where upper(o.dtdmnhanvienMa) = upper(:dtdmnhanvienMa) and  o.dtdmnhanvienChon = true");
        q.setParameter("dtdmnhanvienMa", dtdmnhanvienMa);
        List<DtDmNhanVien> list  = q.getResultList();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public DtDmNhanVien findByTenNhanVien(String dtdmnhanvienTen) {
        Query q = em.createQuery("select object(o) from DtDmNhanVien as o where lower(o.dtdmnhanvienTen) = lower(:dtdmnhanvienTen) and o.dtdmnhanvienChon = true");
        q.setParameter("dtdmnhanvienTen", dtdmnhanvienTen);
        List<DtDmNhanVien> list  = q.getResultList();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
    
    public List<DtDmNhanVien> findAllWithOrderBy(){
        return em.createQuery("select object(o) from DtDmNhanVien as o where o.dtdmnhanvienChon = true order by o.dtdmnhanvienMa").getResultList();
    }

}
