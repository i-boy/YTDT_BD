/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.intf.NguoiDungVaiTroInterface;
import com.iesvn.yte.entity.NguoiDungVaiTro;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author Thanh
 */
public class NguoiDungVaiTroDelegate {
  private NguoiDungVaiTroInterface nguoiDungVTService;

    public static NguoiDungVaiTroDelegate getInstance() {
        return new NguoiDungVaiTroDelegate();
    }

    private NguoiDungVaiTroInterface lookupService() {
        return (NguoiDungVaiTroInterface) LookupServiceUtils.lookupService("NguoiDungVaiTroFacade");
    }

    public void create(NguoiDungVaiTro nguoiDungVT) {
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        nguoiDungVTService.create(nguoiDungVT);
    }
    
   public  void edit(NguoiDungVaiTro nguoiDungVaiTro){
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        nguoiDungVTService.edit(nguoiDungVaiTro);
    }

   public  void remove(NguoiDungVaiTro nguoiDungVaiTro){
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        nguoiDungVTService.remove(nguoiDungVaiTro);
    }

    public NguoiDungVaiTro find(Object id){
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
       return nguoiDungVTService.find(id);
    }
    
    public  NguoiDungVaiTro findByMaSoNguoiDungAndMaSoVaiTro( Integer nguoiDungMaSo, Integer nguoiDungVaiTro){
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        return nguoiDungVTService.findByMaSoNguoiDungAndMaSoVaiTro(nguoiDungMaSo,nguoiDungVaiTro );
    }

    List<NguoiDungVaiTro> findAll(){
        if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        return nguoiDungVTService.findAll();
    }
     public List<NguoiDungVaiTro> findByNguoiDung(Integer nguoiDungMaSo){
         if (nguoiDungVTService == null) {
            nguoiDungVTService = lookupService();
        }
        return nguoiDungVTService.findByNguoiDung(nguoiDungMaSo);
     }
     

}
