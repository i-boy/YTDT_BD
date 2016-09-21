package com.iesvn.yte.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;

import org.apache.log4j.Logger;

public class KiemKeJob implements Job{
	private static Logger log = Logger.getLogger(KiemKeJob.class);
	private KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();  
	private List<KiemKe> lstKiemKe = new ArrayList<KiemKe>();
	
	public KiemKeJob() {
    }
	public void execute(JobExecutionContext context)throws JobExecutionException {
		log.info("Excute KiemKeJob! - ");
		try {
            System.out.println("### CALL KiemKeJob AT: " + new Date().toGMTString());
            int thoihandongKiemKe = Integer.parseInt(IConstantsRes.THOI_HAN_DONG_KIEM_KE);
            lstKiemKe = kiemkeDel.findByKiemKeJob(thoihandongKiemKe);
            if(lstKiemKe !=null && lstKiemKe.size() > 0){
            	for(KiemKe kiemke:lstKiemKe){
            		kiemke.setTrangthai("CLOSE");
            		kiemke.setDtdmnhanvienCn(new DtDmNhanVien(804));
            		kiemke.setNgaygiocn(new Date());
                	String result = kiemkeDel.hoantatKiemKe(kiemke);
                	if(result.indexOf("ERROR") >0){
                		log.info(result + " at "+ kiemke.getMaphieukiem());
                	}else{
                		log.info("Dong kiem ke "+ kiemke.getMaphieukiem() + " thanh cong");
                	}
                }            	
            	lstKiemKe.clear();
            }                
            System.out.println("### KiemKeJob FINISHED");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("### ERROR KiemKeJob!!!!");
        }
	}
}
