/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import javax.ejb.Stateless;


/**
 *
 * @author thanh
 */
@Stateless
public class TimerFacade implements TimerSessionLocal, TimerSessionRemote{

   

//    @Resource
//    private SessionContext ctx;
 
    public  String startTimer(long interval) {
//        System.out.println(" startTimer:" + interval );
//         System.out.println(" ctx:" + ctx );
//        TimerService timerService = ctx.getTimerService();
//        Timer timer = timerService.createTimer(0, interval , null);
//
        return "success";
    }

    //@Timeout
    //public void handleTimeout(Timer timer) {
    //    System.out.println(" handleTimeout called.");
    //}
}
