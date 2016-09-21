package com.iesvn.yte.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import com.iesvn.yte.util.Utils;

@Name("com.iesvn.yte.converter.DateConverter") 
@Converter 
@BypassInterceptors 
public class DateConverter implements javax.faces.convert.Converter, Serializable{
	public DateConverter(){}
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String param) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String dateString ="";
			if (param == null || param.trim().length() == 0){	
				dateString = df.format(Utils.getCurrentDate());
			}
			dateString = df.format(param);
			return dateString;
		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String dateString ="";
			if (obj == null){	
				dateString = df.format(Utils.getCurrentDate());
			}
			dateString = df.format(obj);
			return dateString;
		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}
}
