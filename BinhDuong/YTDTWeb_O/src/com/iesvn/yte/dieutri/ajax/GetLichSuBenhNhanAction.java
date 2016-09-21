package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;
import org.richfaces.json.XML;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class GetLichSuBenhNhanAction extends Action {
	private static Logger log = Logger.getLogger(GetLichSuBenhNhanAction.class);

	public String performAction(String request) throws Exception {
		log.info("-----Begin performAction()-----");
		StringBuffer buf = new StringBuffer();
		log.info("---request: " + request);
		String[] para = request.split(";");
		String maBenhNhan = para[0].trim();
		log.info("---maBenhNhan: " + maBenhNhan);
		String maTiepDon = para[1].trim();
		log.info("---maTiepDon: " + maTiepDon);
		//String hoTenBN = para[2].trim();
		//log.info("---hoTenBN: " + hoTenBN);
		String hoBN = para[2].trim();
		log.info("---hoBN: " + hoBN);
		String tenBN = para[3].trim();
		log.info("---tenBN: " + tenBN);
		
//		TiepDonDelegate tdDelegate =  TiepDonDelegate.getInstance();
//		BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
//		
//		List<BenhNhan> listSearch = new ArrayList<BenhNhan>();
//		if (maBenhNhan != null && !maBenhNhan.equals("")){
//			log.info("tim kiem theo ma benh nhan");
//			BenhNhan bn_tmp = bnDelegate.find(maBenhNhan);
//			if (bn_tmp != null){
//				listSearch.add(bn_tmp);
//			}
//		}
//		boolean timByTD = false;
//		if (maTiepDon != null && !maTiepDon.trim().equals("") && !(listSearch.size()>0)){
//			log.info("tim kiem theo ma tiep don");
//			TiepDon td = tdDelegate.find(maTiepDon);
//			if (td != null){
//				if (td.getBenhnhanMa()!=null)listSearch.add(td.getBenhnhanMa());{
//					maTiepDon = td.getTiepdonMa();
//					timByTD = true;
//				}
//			}
//		}
//		if (hoTenBN != null && !hoTenBN.trim().equals("") && !(listSearch.size()>0)){
//			log.info("tim kiem theo ho ten benh nhan");
//			List<BenhNhan> lstBN = bnDelegate.findByHoTen(hoTenBN);
//			if (lstBN != null){
//				for (BenhNhan obj : lstBN) {
//					listSearch.add(obj);
//				}
//			}
//		}
//		List<TiepDon> tmp = null;
//		JSONArray arrBn = new JSONArray();
//		for (BenhNhan bn : listSearch) {			
//			JSONObject bnJson = new JSONObject();
//			if (timByTD){
//				bnJson.put("mtd", maTiepDon);
//			}else{
//				log.info("---find Tiep don Ma gan nhat cua bn : " + bn.getBenhnhanMa());
//				tmp = tdDelegate.findFinalByBenhNhan(bn.getBenhnhanMa());
//				log.info("so ket qua : " + tmp.size());
//				bnJson.put("mtd", tmp==null&&tmp.size()>0?" ":tmp.get(0).getTiepdonMa());
//			}
//			
//			bnJson.put("bnMa",bn.getBenhnhanMa());
//			bnJson.put("ten",bn.getBenhnhanHoten());
//			if (bn.getDmgtMaso()!=null) {
//				bnJson.put("gtinh",bn.getDmgtMaso().getDmgtTen());
//			}else{
//				bnJson.put("gtinh"," ");
//			}
//			bnJson.put("tuoi",bn.getBenhnhanTuoi()==null?" ":String.valueOf(bn.getBenhnhanTuoi()));
//			if (bn.getBenhnhanDonvituoi()==null) {
//				bnJson.put("dvt", " ");
//			}else if (bn.getBenhnhanDonvituoi() == 1){
//				bnJson.put("dvt", "1");
//			}
//			else if (bn.getBenhnhanDonvituoi() == 2){
//				bnJson.put("dvt", "2");
//			}
//			else if (bn.getBenhnhanDonvituoi() == 3){
//				bnJson.put("dvt", "3");
//			}			
//			bnJson.put("nsinh", bn.getBenhnhanNgaysinh()==null?" ":new SimpleDateFormat("dd/MM/yyyy").format(bn.getBenhnhanNgaysinh()));
//			if (bn.getDantocMa()!=null){ 
//				bnJson.put("dtoc",bn.getDantocMa().getDmdantocTen());
//			}else{
//				bnJson.put("dtoc", " ");
//			}
//			arrBn.put(bnJson);
//		}
//		JSONObject result = new JSONObject();
//		result.put("listBn", arrBn);
//		log.info("---list find: " + result.toString());
//		buf.append(XML.toString(result));
		
		
		if (maBenhNhan.equals("")) maBenhNhan=null;
		if (maTiepDon.equals("")) maTiepDon=null;
		//if (hoTenBN.equals("")) hoTenBN=null;
		
		// 20101207 bao.ttc: xu ly chuoi search BN truoc khi truyen vao ham, them space de gioi han KQ search: LIKE 'hoBN' + ' % ' + tenBN
		String searchBN = "";
		if ( hoBN.equals("") ){
			
			if ( tenBN.equals("") ){
				searchBN = null;
			}else{
				searchBN = "% " + tenBN;
			}
			
		}else{
			
			if ( tenBN.equals("") ){
				searchBN = hoBN + "%";
			}else{
				searchBN = hoBN + "% " + tenBN;
			}
			
		}
		
		
		List<TiepDon> listSearch = TiepDonDelegate.getInstance().findTiepDonForTimKiemBN(maBenhNhan, searchBN, maTiepDon);
		BenhNhan bn = null;
		JSONArray arrBn = new JSONArray();
		for (TiepDon td : listSearch) {
			JSONObject bnJson = new JSONObject();
			bnJson.put("mtd", td.getTiepdonMa());
			bn = td.getBenhnhanMa();
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
			Date dSinh = bn.getBenhnhanNgaysinh();
			String nsinh = null;
			if (dSinh!=null){
				nsinh = new SimpleDateFormat("dd/MM/yyyy").format(dSinh);
			}
			if (nsinh == null){
				nsinh = bn.getBenhnhanNamsinh();
			}
			bnJson.put("nsinh", nsinh==null?" ":nsinh);
			
			// 20101207 bao.ttc: edit phan tren neu khong co ngay sinh thi lay nam sinh
			// bnJson.put("nsinh", bn.getBenhnhanNgaysinh()==null?" ":new SimpleDateFormat("dd/MM/yyyy").format(bn.getBenhnhanNgaysinh()));
			if (bn.getDantocMa()!=null){ 
				bnJson.put("dtoc",bn.getDantocMa().getDmdantocTen());
			}else{
				bnJson.put("dtoc", " ");
			}
			arrBn.put(bnJson);
		}
		JSONObject result = new JSONObject();
		result.put("listBn", arrBn);
		// 20101207 bao.ttc: log.info("---list find: " + result.toString());
		buf.append(XML.toString(result));		
		return buf.toString();
	}
	
	
}
