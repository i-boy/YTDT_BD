/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomKhoa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNhomKhoaFacadeLocal {

    void create(DmNhomKhoa dmNhomKhoa);

    void edit(DmNhomKhoa dmNhomKhoa);

    void remove(DmNhomKhoa dmNhomKhoa);

    DmNhomKhoa find(Object id);

    List<DmNhomKhoa> findAll();

}


