/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.session.DtDmNhanVienFacadeLocal;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.NguoiDungVaiTro;
import com.iesvn.yte.entity.VaiTro;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ThanhDo
 */
@Stateless
public class VaiTroFacade implements VaiTroFacadeLocal, VaiTroFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private NguoiDungVaiTroFacadeLocal nguoiDungVaiTroFacade;
    @EJB
    private DtDmNhanVienFacadeLocal nhaVienFacade;

    public void create(VaiTro vaiTro) {
        em.persist(vaiTro);
    }

    public void edit(VaiTro vaiTro) {
        em.merge(vaiTro);
    }

    public void remove(VaiTro vaiTro) {
        em.remove(em.merge(vaiTro));
    }

    public VaiTro find(Object id) {
        return em.find(com.iesvn.yte.entity.VaiTro.class, id);
    }

    public VaiTro findByMa(String maVT) {
        Object obj = em.createQuery("select object(o) from VaiTro as o where o.vaitroMa = :vaitroMa").setParameter("vaitroMa", maVT).getSingleResult();
        if (obj != null) {
            return (VaiTro) obj;
        }
        return null;
    }

    public List<VaiTro> findAll() {
        return em.createQuery("select object(o) from VaiTro as o").getResultList();
    }

    public String capNhatVaiTro(Integer maSoNhanVien, NguoiDung nguoiDung, List<VaiTro> lstVaiTro) {
        System.out.print("-----capNhatVaiTro()-----");
        DtDmNhanVien nv = nhaVienFacade.find(maSoNhanVien);

        if (nv == null) {
            return null;
        }

        //remove vai tro, nguoi dung dang ton tai va ko co' trong lstVaiTro
        removeVaiTroForNguoiDung(nguoiDung, lstVaiTro);

        for (VaiTro vaiTro : lstVaiTro) {
            System.out.println("-----vaiTro: " + vaiTro);
            NguoiDungVaiTro nd_vt = null;
            try {
                nd_vt = findByMaSoNguoiDungAndMaSoVaiTro(nguoiDung.getNdMaso(), vaiTro.getVaitroMaso());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (nd_vt == null) {
                System.out.println("-----nd_vt is null ");
                nd_vt = new NguoiDungVaiTro();
                nd_vt.setNdMaso(nguoiDung);
                nd_vt.setVaitroMaso(vaiTro);
                em.persist(nd_vt);
            }

        }
        return "sucessful";
    }

    private NguoiDungVaiTro findByMaSoNguoiDungAndMaSoVaiTro(Integer nguoiDungMaSo, Integer vaiTroMaSo) {

        try {
            Query q = em.createQuery("select object(o) from NguoiDungVaiTro as o where o.ndMaso.ndMaso = :ndMaso and o.vaitroMaso.vaitroMaso = :vaitroMaso").setParameter("ndMaso", nguoiDungMaSo).setParameter("vaitroMaso", vaiTroMaSo);

            List<NguoiDungVaiTro> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return (NguoiDungVaiTro) list.get(0);
            }
        } catch (Exception ex) {
            System.out.println("---findByMaSoNguoiDungAndMaSoVaiTro Err: " + ex);
            return null;
        }
        // 20111007 bao.ttc: doi cach search vi lay ket qua getSingleResult() truc tiep se co exception
        //Object obj = em.createQuery("select object(o) from NguoiDungVaiTro as o where o.ndMaso.ndMaso = :ndMaso and o.vaitroMaso.vaitroMaso = :vaitroMaso").setParameter("ndMaso", nguoiDungMaSo).setParameter("vaitroMaso", vaiTroMaSo).getSingleResult();
        //if (obj != null) {
        //    return (NguoiDungVaiTro) obj;
        //}
        return null;
    }

    private void removeVaiTroForNguoiDung(NguoiDung nguoiDung, List<VaiTro> lstVaiTro) {
        System.out.print("-----removeVaiTroForNguoiDung()-----");
        List<NguoiDungVaiTro> listNguoiDungVaiTro = nguoiDungVaiTroFacade.findByNguoiDung(nguoiDung.getNdMaso());
        System.out.print("-----listNguoiDungVaiTro: " + listNguoiDungVaiTro);
        for (NguoiDungVaiTro ndVaiTro : listNguoiDungVaiTro) {
            if (!contain(ndVaiTro.getVaitroMaso().getVaitroMaso(), lstVaiTro)) {
                em.remove(ndVaiTro);
            }

        }
    }

    private boolean contain(Integer vaiTroMaSo, List<VaiTro> lstVaiTro) {
        boolean bKetqua = false;
        for (VaiTro vt : lstVaiTro) {
            if (vaiTroMaSo.intValue() == vt.getVaitroMaso().intValue()) {
                bKetqua = true;
                break;
            }
        }
        return bKetqua;
    }
}
