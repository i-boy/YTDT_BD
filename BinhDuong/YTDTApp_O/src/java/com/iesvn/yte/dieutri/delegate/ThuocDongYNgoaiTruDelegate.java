/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;


import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ThuocDongYNgoaiTruInterface;
import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ThuocDongYNgoaiTruDelegate {

    private ThuocDongYNgoaiTruInterface thuocdongyngoaitruService;

    public static ThuocDongYNgoaiTruDelegate getInstance() {
        return new ThuocDongYNgoaiTruDelegate();
    }

    private ThuocDongYNgoaiTruInterface lookupService() {
        return (ThuocDongYNgoaiTruInterface) LookupServiceUtils.lookupService("ThuocDongYNgoaiTruFacade");
    }

    public void create(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        thuocdongyngoaitruService.create(thuocDongYNgoaiTru);
    }

    public void edit(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        thuocdongyngoaitruService.edit(thuocDongYNgoaiTru);
    }

    public void remove(ThuocDongYNgoaiTru thuocDongYNgoaiTru) {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        thuocdongyngoaitruService.remove(thuocDongYNgoaiTru);
    }

    public ThuocDongYNgoaiTru find(Object id) {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        return thuocdongyngoaitruService.find(id);
    }

    public List<ThuocDongYNgoaiTru> findAll() {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        return thuocdongyngoaitruService.findAll();
    }
    
    public List<ThuocDongYNgoaiTru> findByMaTiepDon(String maTiepDon) {
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        return thuocdongyngoaitruService.findByMaTiepDon(maTiepDon);
    }

    public List<ThuocDongYNgoaiTru> findByMaTiepDonAndMaBanKham(String maTiepDon, String bankhamMa, String loaiToaThuoc){
        if (thuocdongyngoaitruService == null) {
            thuocdongyngoaitruService = lookupService();
        }
        return thuocdongyngoaitruService.findByMaTiepDonAndMaBanKham(maTiepDon, bankhamMa, loaiToaThuoc);
    }
}


