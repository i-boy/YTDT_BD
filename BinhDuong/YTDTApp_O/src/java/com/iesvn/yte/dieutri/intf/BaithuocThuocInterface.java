/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import java.util.List;
/**
 *
 * @author thanh
 */
public interface BaithuocThuocInterface {
    void create(BaithuocThuoc baithuocThuoc);

    void edit(BaithuocThuoc baithuocThuoc);

    void remove(BaithuocThuoc baithuocThuoc);

    BaithuocThuoc find(Object id);
    public void deleteAllBaithuocThuocsByMasoThuoc(Integer thuocMaso);
}
