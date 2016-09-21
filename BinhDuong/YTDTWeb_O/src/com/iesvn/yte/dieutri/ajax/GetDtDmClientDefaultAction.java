package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;


public class GetDtDmClientDefaultAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		

		buf.append("<list>");
		
		buf.append("</list>");
	
		return buf.toString();
	}

}


