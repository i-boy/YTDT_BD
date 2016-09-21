/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaPhieuGuiXac;
import java.util.List;

/**
 *
 * @author quang
 */
public interface HsbaPhieuGuiXacInterface {
public     void create(HsbaPhieuGuiXac hsbaPhieuGuiXac);

public     void edit(HsbaPhieuGuiXac hsbaPhieuGuiXac);

public     void remove(HsbaPhieuGuiXac hsbaPhieuGuiXac);

public     HsbaPhieuGuiXac find(Object id);

public     List<HsbaPhieuGuiXac> findAll();
public String insert(HsbaPhieuGuiXac obj);
public String update(HsbaPhieuGuiXac obj);
public HsbaPhieuGuiXac findByHsbapgxMa(String ma);
}
