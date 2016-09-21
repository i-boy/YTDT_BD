package com.iesvn.yte.identity.extension;

import static org.jboss.seam.ScopeType.SESSION;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.util.IConstantsRes;

@Name("org.jboss.seam.security.identity")
@Scope(SESSION)
@Install(precedence = Install.APPLICATION)
@BypassInterceptors
@Startup
public class CustomIdentity extends Identity {

	@Logger
	private Log log;

	private static final long serialVersionUID = -9154737979944336061L;

	

	@Override
	public void logout() {

		System.out.println("into logout");
		super.logout();

	}
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	
	@Factory("identity")
	public void setmyidentity(){
		identity = Identity.instance();
	}

	private String clientDateHour; // dd/MM/yyyy HH
	
	
	public String getClientDateHour() {
		return clientDateHour;
	}


	public void setClientDateHour(String clientDateHour) {
		this.clientDateHour = clientDateHour;
	}
	
	
	@Override
	public String login() {
		String retVal = null;
		try {
	        
			// clear out the login message in case it was triggered
			// by an authenticate occurring outside the login
			loginErrorMessage = null;
			
			// 20111026 bao.ttc: Upper Case USER NAME de login voi Oracle DB
			if (identity.getUsername() != null){
				String upper = identity.getUsername().toUpperCase();
				identity.setUsername(upper);
			}
			
			System.out.println("into CustomIdentity (upper): " + identity.getUsername());
			retVal = super.login();
			System.out.println("into CustomIdentity: retVal : " + retVal);

		} finally {
			// check if any error messages were registered from
			// this logging., if they are write them out
			// if (StringUtils.isNotBlank(loginErrorMessage)) {
			//		FacesMessages.instance().add(loginErrorMessage);
			// }
		}

		System.out.println("loginErrorMessage : " + loginErrorMessage);
		
		
		if (retVal == null) {
			return retVal;
		} else {
			
			System.out.println("into login --------------Custom identity");
			System.out.println("clientDateHour__2:"+clientDateHour);
			Date d = new Date();
			SimpleDateFormat formatter;	    
	        formatter = new SimpleDateFormat("dd/MM/yyyy HH"); 
	        String temp = formatter.format(d);
	        
	        
	        Date clientDate = new Date();
	        try{
	        	clientDate = formatter.parse(clientDateHour); 
	        }catch(Exception e){
	        	
	        }
	        
	        String serverDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy").format(d);
	        String clientDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy").format(clientDate);
	       
	        long diffMinutes = d.getTime() - clientDate.getTime() / (60 * 1000);
	        
	        
	        if (		
	        		"YES".equals(IConstantsRes.CHECK_DATE_TIME)
	        		&&   clientDateHour != null && !clientDateHour.equals("") && 
	        		
	        		( !temp.equals(clientDateHour)
	        				// cung ngay, chenh lech 2 phut thi ok
	        				 || (serverDDMMYYYY.equals(clientDDMMYYYY) && Math.abs(diffMinutes) <= 2)
	        		)
	        		
	        	){
	        	
	        	formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	        	temp = formatter.format(d);
	        	
	        	System.out.println(" can chinh sua ngay gio he thong 2");
	        	
				FacesMessages.instance().add(IConstantsRes.CHINH_SUA_NGAY_HE_THONG, d.getHours(), temp );
				
	        	this.logout();
				return null;
				
	        } else {
	        	//return null;
	        	
	        	System.out.println("identity.hasRole('NV_TiepDon') : " + identity.hasRole("NV_TiepDon"));
	        	System.out.println("identity.hasRole('NV_PhongKham') : " + identity.hasRole("NV_PhongKham"));
	        	return "MyMainForm";
	        }
	        
			
		}

	}

	private String loginErrorMessage;


}

