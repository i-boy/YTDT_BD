/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHinhThucXuLy;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmHinhThucXuLyFacadeLocal {

    void create(DmHinhThucXuLy dmHinhThucXuLy);

    void edit(DmHinhThucXuLy dmHinhThucXuLy);

    void remove(DmHinhThucXuLy dmHinhThucXuLy);

    DmHinhThucXuLy find(Object id);

    List<DmHinhThucXuLy> findAll();

}


