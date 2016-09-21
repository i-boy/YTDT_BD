/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;


import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ThuocDongYNoiTruInterface;
import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import java.util.List;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ThuocDongYNoiTruDelegate {

    private ThuocDongYNoiTruInterface thuocdongynoitruService;

    public static ThuocDongYNoiTruDelegate getInstance() {
        return new ThuocDongYNoiTruDelegate();
    }

    private ThuocDongYNoiTruInterface lookupService() {
        return (ThuocDongYNoiTruInterface) LookupServiceUtils.lookupService("ThuocDongYNoiTruFacade");
    }

    public void create(ThuocDongYNoiTru thuocDongYNoiTru) {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        thuocdongynoitruService.create(thuocDongYNoiTru);
    }

    public void edit(ThuocDongYNoiTru thuocDongYNoiTru) {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        thuocdongynoitruService.edit(thuocDongYNoiTru);
    }

    public void remove(ThuocDongYNoiTru thuocDongYNoiTru) {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        thuocdongynoitruService.remove(thuocDongYNoiTru);
    }

    public ThuocDongYNoiTru find(Object id) {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        return thuocdongynoitruService.find(id);
    }

    public List<ThuocDongYNoiTru> findAll() {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        return thuocdongynoitruService.findAll();
    }
    
    public List<ThuocDongYNoiTru> findBySoVaoVien(String hsbaSovaovien) {
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        return thuocdongynoitruService.findBySoVaoVien(hsbaSovaovien);
    }

    public List<ThuocDongYNoiTru> findBySoVaoVienandKhoaDTandNgayandLoai(String soVaoVien, String khoaMa, Date ngay, String loai){
        if (thuocdongynoitruService == null) {
            thuocdongynoitruService = lookupService();
        }
        return thuocdongynoitruService.findBySoVaoVienandKhoaDTandNgayandLoai(soVaoVien, khoaMa, ngay, loai);
    }
}


