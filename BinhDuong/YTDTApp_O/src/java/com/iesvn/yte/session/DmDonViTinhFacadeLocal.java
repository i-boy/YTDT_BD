/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDonViTinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDonViTinhFacadeLocal {

    void create(DmDonViTinh dmDonViTinh);

    void edit(DmDonViTinh dmDonViTinh);

    void remove(DmDonViTinh dmDonViTinh);

    DmDonViTinh find(Object id);

    List<DmDonViTinh> findAll();

}


