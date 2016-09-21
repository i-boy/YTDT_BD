/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ThanhDo
 */
@Stateless
public class HsbaKhoaFacade implements HsbaKhoaFacadeLocal, HsbaKhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaKhoa hsbaKhoa) {
        getEm().persist(hsbaKhoa);
    }

    public void edit(HsbaKhoa hsbaKhoa) {
        getEm().merge(hsbaKhoa);
    }

    public void remove(HsbaKhoa hsbaKhoa) {
        getEm().remove(getEm().merge(hsbaKhoa));
    }

    public HsbaKhoa find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.HsbaKhoa.class, id);
    }

    public List<HsbaKhoa> findAll() {
        return getEm().createQuery("select object(o) from HsbaKhoa as o").getResultList();
    }
      // lay hsba khoa moi nhat (last)
    public List<HsbaKhoa> findBySoVaoVien(String soVaoVien){
         soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaKhoa as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbakhoaMaso");
        q.setParameter("soVaoVien", soVaoVien);


        return  q.getResultList();


    }
 
    //lay hsbakhoa ung voi so vao vien va khoa ma dong dau tien
    public HsbaKhoa findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {

        if ( soVaoVien == null || soVaoVien.equals("") ) {
            System.out.println("###### findBySoVaoVienAndKhoaMa ### SOVAOVIEN NULL OR '' !!!");
            return null;
        }
        if ( khoaMa == null || khoaMa.equals("") ) {
            System.out.println("###### findBySoVaoVienAndKhoaMa ### KHOAMA NULL OR '' !!!");
            return null;
        }

        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaKhoa as o where o.hsbaSovaovien.hsbaSovaovien = :soVaoVien and upper(o.khoaMa.dmkhoaMa) = :khoaMa order by o.hsbakhoaMaso ASC");

        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa.toUpperCase());

        List lst = q.getResultList();
        if (lst != null && lst.size() > 0) {
            return (HsbaKhoa) lst.get(0);
        }
        return null;
    }

    public HsbaKhoa findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer dmtangMaso) {
        System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### soVaoVien : " + soVaoVien);
        System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### khoaMa    : " + khoaMa);
        System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### dmtangMaso: " + dmtangMaso);

        if ( soVaoVien == null || soVaoVien.equals("") ) {
            System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### SOVAOVIEN NULL OR '' !!!");
            return null;
        }
        if ( khoaMa == null || khoaMa.equals("") ) {
            System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### KHOAMA NULL OR '' !!!");
            return null;
        }
        if ( dmtangMaso == null ) {
            System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### DMTANGMASO NULL !!!");
            return null;
        }

        soVaoVien = Utils.formatMa(em, soVaoVien);

        //Query q = em.createQuery("select object(o) from HsbaKhoa as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien and o.khoaMa.dmkhoaMa like :khoaMa and o.dmtangMaso.dmtangMaso = :dmtangMaso order by o.hsbakhoaMaso ASC");
        Query q = em.createQuery("select object(o) from HsbaKhoa as o where o.hsbaSovaovien.hsbaSovaovien = :soVaoVien and upper(o.khoaMa.dmkhoaMa) = :khoaMa and o.dmtangMaso.dmtangMaso = :dmtangMaso order by o.hsbakhoaMaso ASC");
        q.setParameter("soVaoVien", soVaoVien);
        q.setParameter("khoaMa", khoaMa.toUpperCase());
        q.setParameter("dmtangMaso", dmtangMaso);

        List lst = q.getResultList();
        if (lst != null && lst.size() > 0) {
            List<HsbaKhoa> listHsbaKhoaTemp = lst;
            int i = 1;
            for(HsbaKhoa hsbaKhoa : listHsbaKhoaTemp){
                System.out.println("###### findBySoVaoVienAndKhoaMaAndTang ### " + i + " : "
                        + hsbaKhoa.getHsbakhoaMaso() + " -- " + hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien()
                        + " -- " + hsbaKhoa.getKhoaMa().getDmkhoaMa() + " -- "+ hsbaKhoa.getDmtangMaso().getDmtangMaso());
                i++;
            }

            return (HsbaKhoa) lst.get(0);
        }
        return null;
    }
    
    public String benhNhanChuyenKhoa(HsbaKhoa hsbaKhoa, DmKhoa khoaChuyenDen, Date ngayGioChuyenKhoa, DmTang tangChuyenDen) {
        // tim xem khoa chuyen den co' chua
        //HsbaKhoa hsbakhoatemp = findBySoVaoVienAndKhoaMa(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien(), khoaChuyenDen.getDmkhoaMa());

        // 20120315 bao.ttc: tim hsbaKhoa theo: Sovaovien - Khoa - Tang de xem xet co tao moi hay khong
        HsbaKhoa hsbakhoatemp = findBySoVaoVienAndKhoaMaAndTang(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien(), khoaChuyenDen.getDmkhoaMa(), tangChuyenDen.getDmtangMaso());

        if (hsbakhoatemp == null) {
            HsbaKhoa hsbaKhoaNew = new HsbaKhoa();
            hsbaKhoaNew.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());
            hsbaKhoaNew.setKhoaMa(khoaChuyenDen);
            hsbaKhoaNew.setHsbakhoaLan(1);
            hsbaKhoaNew.setDmtangMaso(tangChuyenDen);
            System.out.println("###### benhNhanChuyenKhoa ### NEW HSBA KHOA ###");
            em.persist(hsbaKhoaNew);
        } else {
            Integer soLanKhoa = hsbakhoatemp.getHsbakhoaLan();
            if (soLanKhoa == null) {
                soLanKhoa = 1; // phai la` 1 vi hsbacm da ton tai roi
            }
            soLanKhoa++;
            hsbakhoatemp.setHsbakhoaLan(soLanKhoa); // so lan da duoc tang nhu tren
            // 20120416 bao.ttc: Ko can set vi da co san: hsbakhoatemp.setDmtangMaso(tangChuyenDen);
            System.out.println("###### benhNhanChuyenKhoa ### MERGE HSBA KHOA ###");
            em.merge(hsbakhoatemp);
        }

        Hsba hsba = hsbaKhoa.getHsbaSovaovien();
        hsba.setHsbaKhoadangdt(khoaChuyenDen);
        hsba.setTangDangdt(tangChuyenDen);
        em.merge(hsba);

        return hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien();
    }

    public HsbaKhoa findBySoVaoVienLastHsbaKhoa(String soVaoVien) {
        soVaoVien = Utils.formatMa(em, soVaoVien);

        Query q = em.createQuery("select object(o) from HsbaKhoa as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien  order by o.hsbakhoaMaso DESC");
        q.setParameter("soVaoVien", soVaoVien);


        List lst = q.getResultList();
        if (lst != null && lst.size() > 0) {
            return (HsbaKhoa) lst.get(0);
        }
        return null;

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
