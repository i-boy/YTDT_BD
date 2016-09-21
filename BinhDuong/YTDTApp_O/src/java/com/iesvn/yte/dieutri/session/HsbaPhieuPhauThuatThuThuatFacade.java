/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuatBacsi;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuatBacsigm;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author i-boy
 */
@Stateless
public class HsbaPhieuPhauThuatThuThuatFacade implements HsbaPhieuPhauThuatThuThuatFacadeRemote, HsbaPhieuPhauThuatThuThuatFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void create(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        em.persist(hsbaPhieuPhauThuatThuThuat);
    }

    public void edit(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        em.merge(hsbaPhieuPhauThuatThuThuat);
    }

    public void remove(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat) {
        em.remove(em.merge(hsbaPhieuPhauThuatThuThuat));
    }

    public HsbaPhieuPhauThuatThuThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat.class, id);
    }

    public List<HsbaPhieuPhauThuatThuThuat> findAll() {
        return em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuat as o").getResultList();
    }

    public String insert(HsbaPhieuPhauThuatThuThuat obj) {
        System.out.println("---insert phieu phau thuat thu thuat");
        try {
            String ma = Utils.maPhieuPhauThuatThuThuat(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbapptttMa(ma);
            em.persist(obj);
            System.out.println("---insert phieu phau thuat thu thuat: success");
            return ma;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("---insert phieu phau thuat thu thuat: err");
            return "";
        }
    }

    public String update(HsbaPhieuPhauThuatThuThuat obj) {
        System.out.println("---update phieu phau thuat thu thuat");
        try {
            System.out.println("---ma update: " + obj.getHsbapptttMa());
            em.merge(obj);
            System.out.println("---update phieu phau thuat thu thuat: success");
            return obj.getHsbapptttMa();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("---update phieu phau thuat thu thuat: err");
            return "";
        }
    }

    public HsbaPhieuPhauThuatThuThuat findByHsbapptttMa(String ma) {
        System.out.println("---find HsbaPhieuPhauThuatThuThuat by HsbapptttMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuat  as o where o.hsbapptttMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaPhieuPhauThuatThuThuat> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaPhieuPhauThuatThuThuat) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<DtDmNhanVien> findBacsiByHsbapptttMa(String ma) {
        //ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaPhieuPhauThuatThuThuatBacsi as o inner join o.dtdmnhanvienMaso as nv where o.hsbapptttMaso.hsbapptttMa =?1 order by o.hsbapptttbsMaso desc").setParameter(1, ma).getResultList();
    }

    public List<DtDmNhanVien> findBacsigmByHsbapptttMa(String ma) {
        //ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaPhieuPhauThuatThuThuatBacsigm as o inner join o.dtdmnhanvienMaso as nv where o.hsbapptttMaso.hsbapptttMa =?1 order by o.hsbapptttbsgmMaso desc").setParameter(1, ma).getResultList();
    }

    public HsbaPhieuPhauThuatThuThuat findByHsba(String hsbaSovaovien) {
        try {
            Query query = em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuat  as o where o.hsbaSovaovien.hsbaSovaovien like :ma order by o.hsbapptttMaso desc");
            query.setParameter("ma", hsbaSovaovien);
            List<HsbaPhieuPhauThuatThuThuat> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaPhieuPhauThuatThuThuat) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<HsbaPhieuPhauThuatThuThuat> findAllByHsba(String hsbaSovaovien) {
        try {
            Query query = em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuat  as o where o.hsbaSovaovien.hsbaSovaovien = :ma order by o.hsbapptttNgaypttt asc");
            query.setParameter("ma", hsbaSovaovien);
            List<HsbaPhieuPhauThuatThuThuat> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (List<HsbaPhieuPhauThuatThuThuat>) list;
            }
        } catch (Exception e) {
            System.out.println("Error - HsbaPhieuPhauThuatThuThuat - findAllByHsba " + e);
        }
        return null;
    }

    public String getPtvByHsba(String hsbaSovaovien) {
        HsbaPhieuPhauThuatThuThuat hsbaptttt = findByHsba(hsbaSovaovien);
        String tmp = "";
        if (hsbaptttt != null) {
            List<DtDmNhanVien> bacsi = findBacsiByHsbapptttMa(hsbaptttt.getHsbapptttMa());
            if (bacsi != null && bacsi.size() > 0) {
                String tail = "";
                for (DtDmNhanVien nv : bacsi) {
                    tmp += tail + nv.getDtdmnhanvienTen();
                    tail = ", ";
                }
                if (!tail.equals("")) {
                    tmp += ".";
                }
            }
        }
        return tmp;
    }

    public String getPtvByHsbappttt(HsbaPhieuPhauThuatThuThuat ppttt) {
        return getPtvByHsbappttt(ppttt.getHsbapptttMa());
    }

    public String getPtvByHsbappttt(String ma) {
        String tmp = "";
        List<DtDmNhanVien> bacsi = findBacsiByHsbapptttMa(ma);
        if (bacsi != null && bacsi.size() > 0) {
            String tail = "";
            for (DtDmNhanVien nv : bacsi) {
                tmp += tail + nv.getDtdmnhanvienTen();
                tail = ", ";
            }
            if (!tail.equals("")) {
                tmp += ".";
            }
        }
        return tmp;
    }

    //public HsbaPhieuPhauThuatThuThuat createHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList) {
    public String createHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        try {
            String ma = Utils.maPhieuPhauThuatThuThuat(em);
            System.out.println("---ma sinh ra: " + ma);
            hsbaPhieuPhauThuatThuThuat.setHsbapptttMa(ma);
            em.persist(hsbaPhieuPhauThuatThuThuat);
            System.out.println("---insert phieu phau thuat thu thuat: success");
            updateHsbaPhieuPhauThuatThuThuatBacsi(hsbaPhieuPhauThuatThuThuat, bacsiList, bacsigmList);
//            return hsbaPhieuPhauThuatThuThuat;
            return hsbaPhieuPhauThuatThuThuat.getHsbapptttMa();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("---insert phieu phau thuat thu thuat: err");
            return "";
        }
    }
//    public HsbaPhieuPhauThuatThuThuat editHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList) {
//    public String editHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList) {

    public String editHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        em.merge(hsbaPhieuPhauThuatThuThuat);
        updateHsbaPhieuPhauThuatThuThuatBacsi(hsbaPhieuPhauThuatThuThuat, bacsiList, bacsigmList);
        return hsbaPhieuPhauThuatThuThuat.getHsbapptttMa();
    }

    private void updateHsbaPhieuPhauThuatThuThuatBacsi(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        //System.out.println("thanhVienList=" + thanhVienList);
        if (bacsiList != null) {
            //System.out.println("thanhVienList.size()=" + thanhVienList.size());
            List<DtDmNhanVien> oldList = findBacsiByHsbapptttMa(hsbaPhieuPhauThuatThuThuat.getHsbapptttMa());
            //System.out.println("oldList=" + oldList.size());

            if (oldList.size() > 0) {
                for (int i = oldList.size() - 1; i >= 0; i--) {
                    if (!bacsiList.contains(oldList.get(i))) {
                        em.remove(findHsbaPhieuPhauThuatThuThuatBacsiByHsbapptttMasoAndDtdmnhanvienMaso(hsbaPhieuPhauThuatThuThuat.getHsbapptttMaso(), oldList.get(i).getDtdmnhanvienMaso()));
                    }

                }
            }
            //System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
            //System.out.println("newList.size()=" + thanhVienList.size());
            if (bacsiList.size() > 0) {
                HsbaPhieuPhauThuatThuThuatBacsi hsbaPhieuPhauThuatThuThuatBacsi = null;

                for (int i = bacsiList.size() - 1; i >= 0; i--) {
                    //System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
                    if (!oldList.contains(bacsiList.get(i))) {
                        hsbaPhieuPhauThuatThuThuatBacsi = new HsbaPhieuPhauThuatThuThuatBacsi();
                        hsbaPhieuPhauThuatThuThuatBacsi.setDtdmnhanvienMaso(bacsiList.get(i));
                        hsbaPhieuPhauThuatThuThuatBacsi.setHsbapptttMaso(hsbaPhieuPhauThuatThuThuat);
                        //System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
                        em.persist(hsbaPhieuPhauThuatThuThuatBacsi);
                        //System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
                    }
                }
            }

        }

        if (bacsigmList != null) {
            //System.out.println("thanhVienList.size()=" + thanhVienList.size());
            List<DtDmNhanVien> oldList = findBacsigmByHsbapptttMa(hsbaPhieuPhauThuatThuThuat.getHsbapptttMa());
            //System.out.println("oldList=" + oldList.size());

            if (oldList.size() > 0) {
                for (int i = oldList.size() - 1; i >= 0; i--) {
                    if (!bacsigmList.contains(oldList.get(i))) {
                        em.remove(findHsbaPhieuPhauThuatThuThuatBacsigmByHsbapptttMasoAndDtdmnhanvienMaso(hsbaPhieuPhauThuatThuThuat.getHsbapptttMaso(), oldList.get(i).getDtdmnhanvienMaso()));
                    }

                }
            }
            //System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
            //System.out.println("newList.size()=" + thanhVienList.size());
            if (bacsigmList.size() > 0) {
                HsbaPhieuPhauThuatThuThuatBacsigm hsbaPhieuPhauThuatThuThuatBacsigm = null;

                for (int i = bacsigmList.size() - 1; i >= 0; i--) {
                    //System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
                    if (!oldList.contains(bacsigmList.get(i))) {
                        hsbaPhieuPhauThuatThuThuatBacsigm = new HsbaPhieuPhauThuatThuThuatBacsigm();
                        hsbaPhieuPhauThuatThuThuatBacsigm.setDtdmnhanvienMaso(bacsigmList.get(i));
                        hsbaPhieuPhauThuatThuThuatBacsigm.setHsbapptttMaso(hsbaPhieuPhauThuatThuThuat);
                        //System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
                        em.persist(hsbaPhieuPhauThuatThuThuatBacsigm);
                        //System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
                    }
                }
            }

        }
    }

    public Object findHsbaPhieuPhauThuatThuThuatBacsiByHsbapptttMasoAndDtdmnhanvienMaso(Integer hsbapptttMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuatBacsi as o where o.hsbapptttMaso.hsbapptttMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2").setParameter(1, hsbapptttMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }

    public Object findHsbaPhieuPhauThuatThuThuatBacsigmByHsbapptttMasoAndDtdmnhanvienMaso(Integer hsbapptttMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaPhieuPhauThuatThuThuatBacsigm as o where o.hsbapptttMaso.hsbapptttMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2").setParameter(1, hsbapptttMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
}
