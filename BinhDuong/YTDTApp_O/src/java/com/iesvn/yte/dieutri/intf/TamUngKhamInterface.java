/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TamUngKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface TamUngKhamInterface {

public     TamUngKham create(TamUngKham tamUngKham);

public     void edit(TamUngKham tamUngKham);

public     void remove(TamUngKham tamUngKham);

public     TamUngKham find(Object id);

public     List<TamUngKham> findAll();

public Double getTongTamUng(String maTiepdon);

public List<TamUngKham> getListTamUngChuaTT(String maTiepdon);

public List<TamUngKham> getListTamUngDaTT(String maTiepdon);

public int countSolanTamUngByTiepdon(String maTiepdon);
}


