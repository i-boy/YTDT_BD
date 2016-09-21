/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;
import com.iesvn.yte.entity.DmLoaiPhieu;
import java.util.List;
/**
 *
 * @author user01
 */
public interface DmLoaiPhieuInterface {
    void create(DmLoaiPhieu dmLoaiPhieu);
    void edit(DmLoaiPhieu dmLoaiPhieu);
    void remove(DmLoaiPhieu dmLoaiPhieu);
    DmLoaiPhieu find(Object id);
    List<DmLoaiPhieu> findAll();
}
