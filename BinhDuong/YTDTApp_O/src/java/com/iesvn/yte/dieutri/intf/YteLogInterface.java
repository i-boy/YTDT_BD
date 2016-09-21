/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.YteLog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author i-boy
 */
public interface YteLogInterface {

    public void create(YteLog yteLog);

    public void edit(YteLog yteLog);

    public void remove(YteLog yteLog);

    public YteLog find(Object id);

    public List<YteLog> findAll();
    
    public List<YteLog> findByCondition(String form,String userID,String objectId,String logString,String listData,Date tuNgay,Date denNgay);
}
