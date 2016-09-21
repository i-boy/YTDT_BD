package com.iesvn.yte.util;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public class FacesUtils {	
	public static String getParameter(String param) {			
		Object oReq = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String paramValue = "";
		try {
			paramValue = ((HttpServletRequest)oReq).getParameter(param);			
		} catch(Exception e) {		
			paramValue = ((PortletRequest)oReq).getParameter(param);
		}
		
		return paramValue;
	}
	
	public static Object getValueOfExpr (String expression, Class expectedType) {
		javax.faces.context.FacesContext context =javax.faces.context.FacesContext.getCurrentInstance();
		javax.faces.application.Application app = context.getApplication();
		ValueExpression valueEx =app.getExpressionFactory().createValueExpression(context.getELContext(),expression, expectedType);
		return valueEx.getValue(context.getELContext());
	}
}


