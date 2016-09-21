package com.iesvn.dieutridanhmuc.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.iesvn.dieutridanhmuc.entity.*;

@Name("authenticator")
public class Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;

    public boolean authenticate()
    {
    	String pass = "";
        String username = "";
        String usernameUI = credentials.getUsername();
        String passUI = credentials.getPassword();
        log.info("authenticating {0}", usernameUI);
        
        NguoiDungList listOfNguoidung = new NguoiDungList("");        
        Integer nguoidungMaso = new Integer(0);
        for(NguoiDung each : listOfNguoidung.getResultList()){
        	if(each.getNdTendangnhap().equalsIgnoreCase(usernameUI)){
        		pass = each.getNdMadangnhap();
        		username = each.getNdTendangnhap().toUpperCase();
        		nguoidungMaso = each.getNdMaso();
        		System.out.println("Nguoi dung ma so: " + nguoidungMaso);
        		System.out.println("Ten dang nhap: " + username);        		
        		break;
        	}        	
        }
        if(username != ""){
        	if (username.equals(usernameUI) && pass.equals(passUI)){
	        	NguoiDungVaiTroList nguoiDungVaiTroList = new NguoiDungVaiTroList("");
	        	return nguoiDungVaiTroList.getRoleByNguoidungMaso(nguoidungMaso);
	        }
        }
        return false;
    }
}