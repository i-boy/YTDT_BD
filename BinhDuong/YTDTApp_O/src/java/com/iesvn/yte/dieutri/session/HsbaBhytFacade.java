/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaBhytFacade implements HsbaBhytFacadeLocal, HsbaBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @EJB
    private ClsMoFacadeLocal clsMoFacade;
    public HsbaBhyt create(HsbaBhyt hsbaBhyt) {
        em.persist(hsbaBhyt);
        return hsbaBhyt;
    }

    public void edit(HsbaBhyt hsbaBhyt) {
        em.merge(hsbaBhyt);
    }

    public void remove(HsbaBhyt hsbaBhyt) {
        em.remove(em.merge(hsbaBhyt));
    }

    public HsbaBhyt find(Object id) {
        String  soVaoVien = Utils.formatMa(em, (String)id);
        return em.find(com.iesvn.yte.dieutri.entity.HsbaBhyt.class, soVaoVien);
    }

    public List<HsbaBhyt> findAll() {
        return em.createQuery("select object(o) from HsbaBhyt as o").getResultList();
    }
    
     public List<HsbaBhyt> findBySoVaoVien(String soVaoVien){
        soVaoVien = Utils.formatMa(em, soVaoVien); 
        return getEm().createQuery("select object(o) from HsbaBhyt as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien").setParameter("soVaoVien",soVaoVien).getResultList();
                 
    }
    public HsbaBhyt findBySoVaoVienLastHsbaBhyt(String soVaoVien){
         
        soVaoVien = Utils.formatMa(em, soVaoVien);
        List<HsbaBhyt> hsbaBhyt = getEm().createQuery("select object(o) from HsbaBhyt as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbabhytMa DESC").setParameter("soVaoVien",soVaoVien).getResultList();
        if (hsbaBhyt != null && hsbaBhyt.size() > 0 ){
            
            return hsbaBhyt.get(0);
        }else{
            return null;
        }         
    }
    public HsbaBhyt findBySoVaoVienKhoadangdtLastHsbaBhyt(String soVaoVien, String khoaDangdt){
    	soVaoVien = Utils.formatMa(em, soVaoVien);
        List<HsbaBhyt> hsbaBhyt = getEm().createQuery("select object(o) from HsbaBhyt as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien  and o.hsbaSovaovien.hsbaKhoadangdt.dmkhoaMa = :khoaDangdt order by o.hsbabhytMa DESC")
        .setParameter("soVaoVien",soVaoVien)
        .setParameter("khoaDangdt",khoaDangdt).getResultList();
        if (hsbaBhyt != null && hsbaBhyt.size() > 0 ){
            
            return hsbaBhyt.get(0);
        }else{
            return null;
        }         
    }
    
    public HsbaBhyt findByMaTiepDon(String tiepdonMa){
        if (findAll().size() > 0) {         
            List<HsbaBhyt> hsbaBhyt = getEm().createQuery("select object(o) from HsbaBhyt as o where o.hsbaSovaovien.tiepdonMa like :tiepdonMa").setParameter("tiepdonMa",tiepdonMa).getResultList();
            if (hsbaBhyt != null && hsbaBhyt.size() > 0 ){
                return hsbaBhyt.get(0);
            }else{
                return null;
            }       
        } else {
            return null;
        }
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }


    /*
     * Thanh add 05/10/2011
     * Date ok
     */
     // phuc.lc
    // Ham cap nhat gia CLS da chi dinh khi thay doi thong tin the bao hiem
    public void capnhatGiaClsTheoThoiGianBaoHiem(HsbaBhyt hsbaBhyt) {
        // phuc.lc : 27-05-2011
        
        try {
            if (hsbaBhyt != null) {
                if(hsbaBhyt.getHsbaSovaovien().getDoituongMa() != null) {
                    if (hsbaBhyt.getHsbaSovaovien().getDoituongMa().getDmdoituongMa().equals("BH")) {
                        List<ClsMo> listCLSMo = clsMoFacade.findBySoVaoVien(hsbaBhyt.getHsbaSovaovien().getHsbaSovaovien());
                        if (listCLSMo.size() > 0) {
                            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            Date ngayBatDauBh = (hsbaBhyt.getHsbabhytGiatri2() != null ? hsbaBhyt.getHsbabhytGiatri2() : hsbaBhyt.getHsbabhytGiatri0());
                            Date ngayHetHanBh = (hsbaBhyt.getHsbabhytGiatri3() != null ? hsbaBhyt.getHsbabhytGiatri3() : hsbaBhyt.getHsbabhytGiatri1());
                            ngayBatDauBh = (ngayBatDauBh != null ? df.parse(df.format(ngayBatDauBh)) : null);
                            ngayHetHanBh = (ngayHetHanBh != null ? df.parse(df.format(ngayHetHanBh)) : null);
                            Double dongia;
                            Double dongiaBH;
                            Date ngayCls;                            
                    
                            for (ClsMo eachClsMo : listCLSMo) {
                                // Neu CLS da thanh toan hoac thuoc loai Mien, hoac NDM hoac Yeu cau thi khong can cap nhat lai don gia
                                if ((eachClsMo.getClsmoMien() != null && eachClsMo.getClsmoMien().booleanValue() == true) 
                                        || (eachClsMo.getClsmoNDM() != null && eachClsMo.getClsmoNDM().booleanValue() == true) 
                                        || (eachClsMo.getClsmoYeucau() != null && eachClsMo.getClsmoYeucau().booleanValue() == true) 
                                        || (eachClsMo.getClsmoDatt() != null && eachClsMo.getClsmoDatt().booleanValue() == true)) {
                                    continue;

                                }
                                // Cap nhat lai don gia
                                dongia = eachClsMo.getClsmoMahang().getDtdmclsbgDongia();
                                dongiaBH = eachClsMo.getClsmoMahang().getDtdmclsbgDongiabh();
                                ngayCls = df.parse(df.format(eachClsMo.getClsmoNgay()));
                                if (ngayBatDauBh != null && ngayHetHanBh != null) {
                                    if (ngayCls.before(ngayBatDauBh) || ngayCls.after(ngayHetHanBh)) {
                                        eachClsMo.setClsmoDongia(dongia);
                                    } else {
                                        eachClsMo.setClsmoDongia(dongiaBH);
                                    }
                                } else { // Neu ngayBatDauBh = null hoac ngayHetHatBh = null thi coi nhu het han bao hiem

                                    eachClsMo.setClsmoDongia(dongia);
                                }
                                clsMoFacade.edit(eachClsMo);
                            }
                        }
                    }
                   
                }                                                
            }
        } catch (Exception ex) {
            System.out.println("ERROR in method capnhatGiaClsTheoThoiGianBaoHiem(...) : " + ex.toString());
            ex.printStackTrace();
        }
    }   
}


