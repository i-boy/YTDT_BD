package com.iesvn.yte.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name("com.iesvn.yte.converter.TimeConverter") 
@Converter 
@BypassInterceptors
public class TimeConverter implements javax.faces.convert.Converter, Serializable{
	public TimeConverter() {
	}

	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String param) {
		try {
			if (param == null || param.trim().length() == 0) return new Integer(0);
			String hr_mi_se[] = param.split(":");
			
			int seconds = Integer.parseInt(hr_mi_se[0]) * 60 * 60 + Integer.parseInt(hr_mi_se[1]) * 60;

			Integer sObject = new Integer(seconds);
			return sObject;
		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
		try {
			if (obj == null) return "";
			int total_seconds = (int) ((Integer) obj).intValue();
			int hours = (total_seconds) / (60 * 60);
			int rem = (total_seconds) % (60 * 60);
			int minutes = rem / 60;
			String str_hours = "" + hours;
			String str_minutes = "" + minutes;
			
			if (hours < 10) {
				str_hours = "0" + hours;
			}
			if (minutes < 10) {
				str_minutes = "0" + minutes;
			}
			return str_hours + ":" + str_minutes;

		} catch (Exception exception) {
			throw new ConverterException(exception);
		}
	}
}