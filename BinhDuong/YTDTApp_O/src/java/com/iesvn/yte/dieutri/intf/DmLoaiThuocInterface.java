/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.DmLoaiThuoc;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author user01
 */
public interface DmLoaiThuocInterface {
    public void create(DmLoaiThuoc dmLoaiThuoc);
    public void edit(DmLoaiThuoc dmLoaiThuoc);
    public void remove(DmLoaiThuoc dmLoaiThuoc);
    public DmLoaiThuoc find(Object id);
    public HashMap<String,DmLoaiThuoc> findAllDm();
    public List<DmLoaiThuoc> findAll();
}
