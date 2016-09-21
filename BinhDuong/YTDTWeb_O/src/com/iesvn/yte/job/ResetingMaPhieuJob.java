package com.iesvn.yte.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;
import com.iesvn.yte.dieutri.delegate.YteSequenceDelegate;
import org.apache.log4j.Logger;

public class ResetingMaPhieuJob implements Job{
	private static Logger log = Logger.getLogger(ResetingMaPhieuJob.class);
	YteSequenceDelegate ytesequenceDel = YteSequenceDelegate.getInstance();
	
	public ResetingMaPhieuJob() {
    }
	public void execute(JobExecutionContext context)throws JobExecutionException {
		log.info("Excute ResetingMaPhieuJob! - ");
		try {
            log.info("### CALL ResetingMaPhieuJob AT: " + new Date().toString() );
            //call reset ma phieu
            String result = ytesequenceDel.resetMaPhieu();
            if(result.equals("-1")){
            	log.info("Loi reset ma phieu");
            }else{
            	log.info("Reset ma phieu thanh cong");
            }
            log.info("### ResetingMaPhieuJob FINISHED");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("### ERROR ResetingMaPhieuJob!!!!");
        }
	}
}
