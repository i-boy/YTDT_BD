package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.CauHinhDelegate;

public class GetMaTiepDonAction extends Action {
	public String performAction(String request) throws Exception {
		System.out.println("-----Begin GetMaTiepDonAction() -----");
		String maTiepDonAndMaBenhNhan = null;
		String maTiepDon = null;
		if (request.indexOf("___") >=0){
			try {
				CauHinhDelegate chDelegate = CauHinhDelegate.getInstance();
				maTiepDon = chDelegate.getMaTiepDon();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			String[] arr = request.split("___");
			
			return String.format("<result>%s</result>", arr[0]  + "___" + maTiepDon + "___" + arr[1]); //ma tiep don
		}else{
			try {
				CauHinhDelegate chDelegate = CauHinhDelegate.getInstance();
				maTiepDonAndMaBenhNhan = chDelegate.getMaTiepDonAndMaBenhNhan();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			return String.format("<result>%s</result>", request + "___" + maTiepDonAndMaBenhNhan); //ma tiep don and ma benh nhan
		}
	}
}


