package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.Utils;


public class TestBHYTTrungTrongNgayAction extends Action {
	
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		
		StringTokenizer sTokenizer  = new StringTokenizer(request,",");
		String sothebhyt = "";
		String ngaytiepdon = "";
		int i = 0;
		while (sTokenizer.hasMoreTokens()){
			if (i == 0){
				sothebhyt = sTokenizer.nextToken();
			}else if (i == 1){
				ngaytiepdon = sTokenizer.nextToken();
			}
			i++;
		}
		System.out.println("soHoaDon:"+sothebhyt);
		System.out.println("ngayHoaDon:"+ngaytiepdon);
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
		Date dNgaytiepdon = sdf.parse(ngaytiepdon);
		
		TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
		TiepDon tiepDon = null;
		try {
			
			tiepDon = tdDelegate.testBHYTTrungTrongNgay(sothebhyt, dNgaytiepdon);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		sdf = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
		buf.append("<list>");
		if (tiepDon != null){
			buf.append("<record " +
					" matiepdon='" + tiepDon.getTiepdonMa() + "'" +
					" ngaygio='" + sdf.format( tiepDon.getTiepdonNgaygio()) + "'" +
					" bankham='" + tiepDon.getTiepdonBankham(true).getDtdmbankhamTen()  + "'" +
					" ngaytaikham='" + "" + "'" +					
					" loaicanhbao='" + 0  +
					"' />");
		}else{
			// truong hop nay xet tiep den ngay tai kham
			tiepDon = tdDelegate.getTiepDonWithSoTheBHYTLast(sothebhyt);
			if (tiepDon != null){
				ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
				List<ThamKham> lstTK =  tkDele.findAllByMaTiepDon(tiepDon.getTiepdonMa());
				String ngayTaiKham = "";
				Date dNgayTaiKham = null;
				if (lstTK != null){
					for (ThamKham tk:lstTK){
						dNgayTaiKham = tk.getThamkhamNgaytaikham();
						if (dNgayTaiKham != null){
							ngayTaiKham = sdf.format(dNgayTaiKham);
							break;
						}
					}
				}
				
				// kiem tra ngay tai kham chua den han
				if (dNgayTaiKham != null){
					//test
					if (dNgayTaiKham.after(dNgaytiepdon)){
						// bn chua den han tai kham
						buf.append("<record " +
								" matiepdon='" + tiepDon.getTiepdonMa() + "'" +
								" ngaygio='" + sdf.format( tiepDon.getTiepdonNgaygio()) + "'" +
								" bankham='" + tiepDon.getTiepdonBankham(true).getDtdmbankhamTen()  + "'" +
								" ngaytaikham='" + ngayTaiKham  + "'" +
								" loaicanhbao='" + 1  +
								"' />");
					}
				}
			}
		}
		
		buf.append("</list>");
	
		return buf.toString();
	}
}


