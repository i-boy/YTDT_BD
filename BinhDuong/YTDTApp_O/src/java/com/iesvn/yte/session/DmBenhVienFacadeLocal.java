/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhVien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmBenhVienFacadeLocal {

    void create(DmBenhVien dmBenhVien);

    void edit(DmBenhVien dmBenhVien);

    void remove(DmBenhVien dmBenhVien);

    DmBenhVien find(Object id);

    List<DmBenhVien> findAll();

}


