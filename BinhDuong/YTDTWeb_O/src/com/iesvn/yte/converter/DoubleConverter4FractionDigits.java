package com.iesvn.yte.converter;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import com.sun.faces.util.MessageFactory;


@Name("com.iesvn.yte.converter.DoubleConverter4FractionDigits") 
@Converter 
@BypassInterceptors  
public class DoubleConverter4FractionDigits extends javax.faces.convert.DoubleConverter implements Serializable{
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if (context == null || component == null) {
          throw new NullPointerException();
      }
	     
	  // If the specified value is null or zero-length, return null
	  if (value == null) {
	      return (null);
	  }
	  value = value.trim();
	  if (value.length() < 1) {
	      return (null);
	  }
	  //value = value.replaceAll("[.]", "");	     
	  //value = value.replaceAll(",", ".");
	  value = value.replaceAll(",", "");
	  try {
	      return (Double.valueOf(value));
	  } catch (NumberFormatException nfe) {
	    throw new ConverterException(MessageFactory.getMessage(context, DOUBLE_ID, value, "1999999",
	                  MessageFactory.getLabel(context, component)));
	  } catch (Exception e) {
	      throw new ConverterException(e);
	  }
   }

	static NumberFormat formatter = null;
	private NumberFormat getNumberFormat() {
		if (formatter == null) {
			formatter = NumberFormat.getNumberInstance();
			formatter.setGroupingUsed(true);
			formatter.setMaximumFractionDigits(4);			
		}
		return formatter;
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (context == null || component == null) {
			throw new NullPointerException();
		}

		// If the specified value is null, return a zero-length String
		if (value == null) {
			return "";
		}

		// If the incoming value is still a string, play nice
		// and return the value unmodified
		if (value instanceof String) {
			return (String) value;
		}

		try {
			//NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRENCH);
			//NumberFormat formatter = NumberFormat.getNumberInstance();
			//formatter.setGroupingUsed(true);
			//formatter.setMaximumFractionDigits(4);			
			
			String sValue = (getNumberFormat().format(((Number) value).doubleValue()));
			
			return sValue;
		} catch (Exception e) {
			throw new ConverterException(MessageFactory.getMessage(context,
					STRING_ID, value, MessageFactory.getLabel(context,
							component)), e);
		}
	}

	
}
