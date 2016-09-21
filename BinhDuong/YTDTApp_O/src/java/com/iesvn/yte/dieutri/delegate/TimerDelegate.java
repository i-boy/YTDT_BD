/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TimerFacadeInterface;

/**
 *
 * @author LENOVO 3000 Y410
     */
public class TimerDelegate {

    private TimerFacadeInterface timerInterface;

    public static TimerDelegate getInstance() {
        return new TimerDelegate();
    }

    private TimerFacadeInterface lookupService() {
        return (TimerFacadeInterface) LookupServiceUtils.lookupService("TimerFacade");
    }

    public String startTimer(long interval) {
        if (timerInterface == null) {
            timerInterface = lookupService();
        }
        return timerInterface.startTimer(interval);
    }
}






