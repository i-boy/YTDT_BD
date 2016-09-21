package com.iesvn.yte.job;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.CronTrigger;
import org.quartz.ee.servlet.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import org.apache.log4j.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import com.iesvn.yte.util.IConstantsRes;

public class KiemKeAutoServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(KiemKeJob.class);
	private boolean performShutdown = true;
	private Scheduler scheduler = null;	
	public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Initializing Scheduler PlugIn for Kiem Ke Jobs!");
		super.init(config);
		ServletContext ctx = config.getServletContext();
			
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QuartzInitializerServlet.QUARTZ_FACTORY_KEY); 

		try { 
			String configFile = config.getInitParameter("config-file");
			String shutdownPref = config.getInitParameter("shutdown-on-unload");
			if (shutdownPref != null){
				performShutdown = Boolean.valueOf(shutdownPref).booleanValue();
			}
			// get Properties
			if (configFile != null) {
				factory = new StdSchedulerFactory(configFile);
			} else {
				factory = new StdSchedulerFactory();
			}

			// Should the Scheduler being started now or later
			String startOnLoad = config.getInitParameter("start-scheduler-on-load");
			if (startOnLoad == null || (Boolean.getBoolean(startOnLoad))){
				// Start now
				scheduler = factory.getScheduler();
				JobDetail job = newJob(KiemKeJob.class)
			    						.withIdentity("KiemKeJob", "KIEMKE_GROUP")
			    						.storeDurably()
			    						.requestRecovery()
			    						.build();
				
				String cron_HrsExpr = IConstantsRes.KIEMKE_HOUR;
				String cron_MinuteExpr = IConstantsRes.KIEMKE_MINUTE;
				String cron_SecExpr = IConstantsRes.KIEMKE_SEC;
				String cronExpr = cron_SecExpr + " " + cron_MinuteExpr +" " + cron_HrsExpr+ " * * ?";
				CronTrigger trigger = newTrigger()
	            						.withIdentity("KiemKeTrigger", "KIEMKE_GROUP")
	            						.withSchedule(cronSchedule(cronExpr))
	            						.build();
					            
				scheduler.scheduleJob(job, trigger);
				System.out.println("Kiem Ke Job scheduled now ..");
				scheduler.start();
				log.info("Scheduler has been started...");
			} else {
				log.info("Scheduler started. Use scheduler.start()");
			}
			System.out.println("Job scheduled now ..");
			String factoryKey = config.getInitParameter("servlet-context-factory-key");
			if (factoryKey == null) {
				factoryKey = QUARTZ_FACTORY_KEY;
			}
			log.info("Storing SchedulerFactory at servlet context key: "	+ factoryKey);

			config.getServletContext().setAttribute(factoryKey, factory);
		} catch (Exception e){
			e.printStackTrace();
		}
	} 
	@Override
	public void destroy() {
		if (!performShutdown) {
			return;
		}
		
		try {
        	if (scheduler != null) {
        		scheduler.shutdown();
        		Thread.sleep(1000);
        	}
		} catch (Exception e) {
			log.info("Quartz Scheduler failed to shutdown cleanly: " + e.toString());
			e.printStackTrace();
		}
		log("Quartz Scheduler successful shutdown.");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
}
