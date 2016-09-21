/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiBenhVien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiBenhVienFacadeLocal {

    void create(DmLoaiBenhVien dmLoaiBenhVien);

    void edit(DmLoaiBenhVien dmLoaiBenhVien);

    void remove(DmLoaiBenhVien dmLoaiBenhVien);

    DmLoaiBenhVien find(Object id);

    List<DmLoaiBenhVien> findAll();

}


