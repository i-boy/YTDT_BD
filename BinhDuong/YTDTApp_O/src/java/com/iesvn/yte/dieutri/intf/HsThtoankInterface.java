/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import java.util.List;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsThtoankInterface {

    public void create(HsThtoank hsThtoank);

    public Boolean edit(HsThtoank hsThtoank);

    public void remove(HsThtoank hsThtoank);

    public HsThtoank find(Object id);

    public List<HsThtoank> findAll();

    public HsThtoank findBytiepdonMa(String tiepdonMa);

    public HsThtoank findAllBytiepdonMa(String tiepdonMa);

    public HsThtoank findBytiepdonMaLast(String tiepdonMa);

    public HsThtoank findByMaPhieu(String maPhieuTT);

    public String capNhatTTHsttk(HsThtoank hsThtoank, List<ClsKham> clslist, List<ThuocPhongKham> lstTNT, boolean capnhattonkho);
    
    public List<HsThtoank> findByNgayTiepdon(Date tungay, Date denngay) ;
    
    public int updateNgayGioTTAndDaTT2Null(Date tungay, Date denngay) ;

    public List<HsThtoank> findByNgayTiepdonAndDaTT(Date tungay, Date denngay) ;

}


