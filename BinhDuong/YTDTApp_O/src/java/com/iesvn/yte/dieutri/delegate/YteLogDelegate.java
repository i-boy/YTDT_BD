/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.YteLogInterface;

import com.iesvn.yte.dieutri.entity.YteLog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author i-boy
 */
public class YteLogDelegate {
    
    private YteLogInterface yteLogService;
    
    public static YteLogDelegate getInstance() {
        return new YteLogDelegate();
    }
    
    private YteLogInterface lookupService() {
        return (YteLogInterface) LookupServiceUtils.lookupService("YteLogFacade");
    }
    
    public void create(YteLog yteLog) {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        yteLogService.create(yteLog);
    }
    
    public void edit(YteLog yteLog) {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        yteLogService.edit(yteLog);
    }
    
    public void remove(YteLog yteLog) {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        yteLogService.remove(yteLog);
    }
    
    public YteLog find(Object id) {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        return yteLogService.find(id);
    }
    
    public List<YteLog> findAll() {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        return yteLogService.findAll();
    }
    
    public List<YteLog> findByCondition(String form, String userID, String objectId, String logString, String listData, Date tuNgay, Date denNgay) {
        if (yteLogService == null) {
            yteLogService = lookupService();
        }
        return yteLogService.findByCondition(form, userID, objectId, logString, listData, tuNgay, denNgay);
    }
}
