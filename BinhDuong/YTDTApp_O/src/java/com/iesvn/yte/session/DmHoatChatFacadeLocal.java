/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHoatChat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmHoatChatFacadeLocal {

    void create(DmHoatChat dmHoatChat);

    void edit(DmHoatChat dmHoatChat);

    void remove(DmHoatChat dmHoatChat);

    DmHoatChat find(Object id);

    List<DmHoatChat> findAll();

}


