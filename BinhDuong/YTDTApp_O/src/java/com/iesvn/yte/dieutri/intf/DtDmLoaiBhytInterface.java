/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmLoaiBhytInterface {

public     void create(DtDmLoaiBhyt dtDmLoaiBhyt);

public     void edit(DtDmLoaiBhyt dtDmLoaiBhyt);

public     void remove(DtDmLoaiBhyt dtDmLoaiBhyt);

public     DtDmLoaiBhyt find(Object id);

public     List<DtDmLoaiBhyt> findAll();

}


