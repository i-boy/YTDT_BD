/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.intf.NguoiDungInterface;
import com.iesvn.yte.dieutri.intf.VaiTroInterface;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.VaiTro;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author ThanhDo
 */
public class VaiTroDelegate {

    private VaiTroInterface VaiTroService;

    public static VaiTroDelegate getInstance() {
        return new VaiTroDelegate();
    }

    private VaiTroInterface lookupService() {
        return (VaiTroInterface) LookupServiceUtils.lookupService("VaiTroFacade");
    }

    public void create(VaiTro VaiTro) {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        VaiTroService.create(VaiTro);
    }

    public void edit(VaiTro vaiTro) {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        VaiTroService.edit(vaiTro);

    }

    public void remove(VaiTro vaiTro) {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        VaiTroService.remove(vaiTro);
    }

    public VaiTro find(Object id) {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        return VaiTroService.find(id);
    }
  public VaiTro findByMa(String  maVT) {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        return VaiTroService.findByMa(maVT);
  }
    public List<VaiTro> findAll() {
        if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        return VaiTroService.findAll();
    }
     public String capNhatVaiTro(Integer maSoNhanVien, NguoiDung nguoiDung, List<VaiTro> lstVaiTro){
          if (VaiTroService == null) {
            VaiTroService = lookupService();
        }
        return VaiTroService.capNhatVaiTro(maSoNhanVien,nguoiDung,lstVaiTro);
     }
}
