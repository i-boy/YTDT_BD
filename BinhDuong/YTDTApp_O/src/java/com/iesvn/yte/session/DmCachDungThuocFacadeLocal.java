/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmCachDungThuoc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmCachDungThuocFacadeLocal {

    void create(DmCachDungThuoc dmCachDungThuoc);

    void edit(DmCachDungThuoc dmCachDungThuoc);

    void remove(DmCachDungThuoc dmCachDungThuoc);

    DmCachDungThuoc find(Object id);

    List<DmCachDungThuoc> findAll();

}


