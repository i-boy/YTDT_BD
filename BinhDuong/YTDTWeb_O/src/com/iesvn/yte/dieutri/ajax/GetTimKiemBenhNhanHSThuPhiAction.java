package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;
import org.richfaces.json.XML;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.HsThtoan;

public class GetTimKiemBenhNhanHSThuPhiAction extends Action {
	
	private static Logger log = Logger.getLogger(GetTimKiemBenhNhanHSThuPhiAction.class);

	public String performAction(String request) throws Exception {
		log.info("---load ajax GetTimKiemBenhNhanHSThuPhiAction");
		log.info("---request: " + request);
		String[] para = request.split(";");
		String maSovaovien = para[0].trim();
		String hoten = para[1].trim();
		List<HsThtoan> list = HsThtoanDelegate.getInstance().findHsttCoThuPhi(maSovaovien, hoten);		
		if (list==null){
			list = new ArrayList<HsThtoan>();
		}
		StringBuffer buf = new StringBuffer();
		JSONArray arrBn = new JSONArray();
		for (HsThtoan obj : list) {
			BenhNhan bn=null;
			if (obj.getHsbaSovaovien()!=null){
				bn = obj.getHsbaSovaovien().getBenhnhanMa();
			}
			if (bn==null) continue;
			JSONObject bnJson = new JSONObject();			
			bnJson.put("mtd", obj.getHsbaSovaovien().getTiepdonMa()==null?" ":obj.getHsbaSovaovien().getTiepdonMa());
			bnJson.put("hstt",obj.getHsthtoanMa());
			bnJson.put("bnMa",bn.getBenhnhanMa());
			bnJson.put("ten",bn.getBenhnhanHoten());
			if (bn.getDmgtMaso()!=null) {
				bnJson.put("gtinh",bn.getDmgtMaso().getDmgtTen());
			}else{
				bnJson.put("gtinh"," ");
			}
			bnJson.put("tuoi",bn.getBenhnhanTuoi()==null?" ":String.valueOf(bn.getBenhnhanTuoi()));
			if (bn.getBenhnhanDonvituoi()==null) {
				bnJson.put("dvt", " ");
			}else if (bn.getBenhnhanDonvituoi() == 1){
				bnJson.put("dvt", "1");
			}
			else if (bn.getBenhnhanDonvituoi() == 2){
				bnJson.put("dvt", "2");
			}
			else if (bn.getBenhnhanDonvituoi() == 3){
				bnJson.put("dvt", "3");
			}			
			bnJson.put("nsinh", bn.getBenhnhanNgaysinh()==null?" ":new SimpleDateFormat("dd/MM/yyyy").format(bn.getBenhnhanNgaysinh()));
			if (bn.getDantocMa()!=null){ 
				bnJson.put("dtoc",bn.getDantocMa().getDmdantocTen());
			}else{
				bnJson.put("dtoc", " ");
			}
			arrBn.put(bnJson);
		}
		JSONObject result = new JSONObject();
		result.put("listBn", arrBn);
		log.info("---list find: " + result.toString());
		buf.append(XML.toString(result));
		return buf.toString();
	}
}
