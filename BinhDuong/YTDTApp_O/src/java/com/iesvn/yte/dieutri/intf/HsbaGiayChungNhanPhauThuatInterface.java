/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhanPhauThuat;
import java.util.List;

/**
 *
 * @author thao
 */
public interface HsbaGiayChungNhanPhauThuatInterface {
    
    public     void create(HsbaGiayChungNhanPhauThuat hsbaGiayChungNhanPhauThuat);

    public     void edit(HsbaGiayChungNhanPhauThuat hsbaGiayChungNhanPhauThuat);

    public     void remove(HsbaGiayChungNhanPhauThuat hsbaGiayChungNhanPhauThuat);

    public     HsbaGiayChungNhanPhauThuat find(Object id);

    public     List<HsbaGiayChungNhanPhauThuat> findAll();

    public String insert(HsbaGiayChungNhanPhauThuat gct);

    public String update(HsbaGiayChungNhanPhauThuat gct);

    public HsbaGiayChungNhanPhauThuat findByHsbagcnptMa(String ma);

}
