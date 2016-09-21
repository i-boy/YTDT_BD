/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmGioiTinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmGioiTinhFacadeLocal {

    void create(DmGioiTinh dmGioiTinh);

    void edit(DmGioiTinh dmGioiTinh);

    void remove(DmGioiTinh dmGioiTinh);

    DmGioiTinh find(Object id);

}


