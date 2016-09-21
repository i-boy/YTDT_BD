package com.iesvn.yte.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import org.apache.log4j.Logger;

public class UpdatingThuocJob implements Job{
	private static Logger log = Logger.getLogger(UpdatingThuocJob.class);
	private DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();  
	
	public UpdatingThuocJob() {
    }
	public void execute(JobExecutionContext context)throws JobExecutionException {
		log.info("Excute UpdatingThuocJob! - ");
		try {
            log.info("### CALL UpdatingThuocJob AT: ");
            dmThuocDel.updateFieldTonKho(); 
            log.info("### UpdatingThuocJob FINISHED");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("### ERROR UpdatingThuocJob!!!!");
        }
	}
}
