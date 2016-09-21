/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmLoaiThucPhamInterface {

    public void create(DtDmLoaiThucPham dtDmLoaiThucPham);

    public void edit(DtDmLoaiThucPham dtDmLoaiThucPham);

    public void remove(DtDmLoaiThucPham dtDmLoaiThucPham);

    public DtDmLoaiThucPham find(Object id);

    public List<DtDmLoaiThucPham> findAll();
}
