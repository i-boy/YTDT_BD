/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TuvongTruoc;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quang
 */
@Stateless
public class TuvongTruocFacade implements TuvongTruocFacadeRemote, TuvongTruocFacadeLocal {
    
   @PersistenceContext
    private EntityManager em;

    public void create(TuvongTruoc tuvongTruoc) {

        String maBenhAn = tuvongTruoc.getTvtMa();
        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnTuvongTruoc(em);

        }
        System.out.println("maBenhAn:"+maBenhAn);
        tuvongTruoc.setTvtMa(maBenhAn);

        em.persist(tuvongTruoc);
    }

     public String create2(TuvongTruoc tuvongTruoc) {

        String maBenhAn = tuvongTruoc.getTvtMa();
        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnTuvongTruoc(em);

        }
        System.out.println("maBenhAn:"+maBenhAn);
        tuvongTruoc.setTvtMa(maBenhAn);

        em.persist(tuvongTruoc);
        return maBenhAn;
    }

    public void edit(TuvongTruoc tuvongTruoc) {
        em.merge(tuvongTruoc);
    }

    public void remove(TuvongTruoc tuvongTruoc) {
        em.remove(em.merge(tuvongTruoc));
    }

    public TuvongTruoc find(Object id) {
        return em.find(TuvongTruoc.class, id);
    }

    public List<TuvongTruoc> findAll() {
        return em.createQuery("select object(o) from TuvongTruoc as o").getResultList();
    }

    public TuvongTruoc getTuvongTruoc(Integer thamkham){
       System.out.println("call getTuvongTruoc");

//       TuvongTruoc tuvongTruoc = new TuvongTruoc();
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonNgaygio();//dua den luc
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonDonvigoi();//noigioithieu, neu khong se la tuden

//       tuvongTruoc.getTvtPhuongtienvanchuyen();
//       tuvongTruoc.getTvtPhuongtienvanchuyenSoxe();
//       tuvongTruoc.getTvtTenNguoiduaden();
//       tuvongTruoc.getTvtTuoiNguoiduaden();
//       tuvongTruoc.getTvtGioitinhNguoiduaden();
//       tuvongTruoc.getTvtDiachiNguoiduaden();
//       tuvongTruoc.getTvtLhbnNguoiduaden();
//       tuvongTruoc.getTvtBenhsu();
//
//       tuvongTruoc.getTvtTiensu();
//
//       tuvongTruoc.getTvtPpdtTruoc();
//       tuvongTruoc.getTvtTuvongluc();
//       tuvongTruoc.getTvtTinhtrangchung();
//       tuvongTruoc.getTvtTrigiac();
//
//       tuvongTruoc.getTvtDaniem();
//       tuvongTruoc.getTvtDongtu();
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonMach();
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonHamax();
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonHamin();
//       tuvongTruoc.getTvtThamkham().getTiepdonMa().getTiepdonNhietdo();
//       tuvongTruoc.getTvtTimmach();
//       tuvongTruoc.getTvtHohap();
//       tuvongTruoc.getTvtThuongtonbenhlychinh();
//       tuvongTruoc.getTvtChandoansobo();
//       tuvongTruoc.getTvtCapcuu();
//       tuvongTruoc.getTvtCanthiepkhac();
//       tuvongTruoc.getTvtGiuxac();
//       tuvongTruoc.getTvtXinxackhongkhieunai();
//       tuvongTruoc.getTvtNguoixinxac();
//       tuvongTruoc.getTvtNguoixinxacTuoi();
//       tuvongTruoc.getTvtNguoixinxacGioitinh();
//       tuvongTruoc.getTvtNguoixinxacLienhebn();
//       tuvongTruoc.getTvtTaisanbenhnhan();
//       tuvongTruoc.getTvtCobienbantaisan();

       List<TuvongTruoc> lstTuvongTruoc =  em.createQuery("select object(o) from TuvongTruoc as o where o.tvtThamkham.thamkhamMa = :thamkham ").setParameter("thamkham", thamkham).getResultList();
       System.out.println("lstgetTuvongTruoc:"+lstTuvongTruoc);


       if (lstTuvongTruoc != null && lstTuvongTruoc.size() > 0){
           return lstTuvongTruoc.get(0);
       }else{
           return null;
       }
     }

    
//    public String capNhatTuvongTruoc(TuvongTruoc obj, String sMaPhieu) {
//        String result = "";
//        TuvongTruoc baTmp = getTuvongTruoc(obj.getTvtThamkham(true).getThamkhamMa());
//        if (baTmp != null){
//            sMaPhieu = baTmp.getTvtMa();
//        }
//        if (sMaPhieu == null || sMaPhieu.equals("")) {
//            sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
//            result = sMaPhieu;
//            obj.setBantMa(sMaPhieu);
//            getEm().persist(obj);
//            System.out.println("******* INSERT SUCCESS TuvongTruoc ******");
//        } else {
//            result = sMaPhieu;
//            getEm().merge(obj);
//            System.out.println("******* UPDATE SUCCESS TuvongTruoc ******");
//        }
//        System.out.println("******* Ma TuvongTruoc: " + result);
//        ///////////////////////////////////////////////////////////////////////
//        try {
//            String listpcsMa = "";
//            for (int i = 0; i < lstCT.size(); i++) {
//                CtTuvongTruoc pcs = lstCT.get(i);
//                if (pcs.getCtbantMaso() != null) {
//                    if (listpcsMa.equals("")) {
//                        listpcsMa += pcs.getCtbantMaso();
//                    } else {
//                        listpcsMa += "," + pcs.getCtbantMaso();
//                    }
//                }
//            }
//            System.out.print("listpcsMa:" + listpcsMa);
//            this.removeItem(listpcsMa, obj.getBantThamkham().getThamkhamMa());
//            for (CtTuvongTruoc pcs : lstCT) {
//                pcs.setTuvongTruoc(obj);
//                if (pcs.getCtbantMaso() != null) {
//                    getEm().merge(pcs);
//                    System.out.println("update thanh cong chi tiet pcs");
//                } else {
//                    getEm().persist(pcs);
//                    System.out.println("insert them thanh cong chi tiet ");
//                }
//            }
//            //result = "";
//        } catch (Exception ex) {
//            //result = "";
//            System.out.println(ex.toString());
//            ex.printStackTrace();
//        }
//
//        return result;
//    }


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
