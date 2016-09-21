/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTonGiao;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTonGiaoFacadeLocal {

    void create(DmTonGiao dmTonGiao);

    void edit(DmTonGiao dmTonGiao);

    void remove(DmTonGiao dmTonGiao);

    DmTonGiao find(Object id);

    List<DmTonGiao> findAll();

}


