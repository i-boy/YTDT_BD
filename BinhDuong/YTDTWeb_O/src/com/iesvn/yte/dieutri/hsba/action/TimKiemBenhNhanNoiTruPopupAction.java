package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("danhsachbenhnhannoitru")
@Synchronized(timeout = 6000000)
public class TimKiemBenhNhanNoiTruPopupAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493331384169670628L;
	private static Logger log = Logger.getLogger(TimKiemBenhNhanNoiTruPopupAction.class);	
	private String result;

	@DataModel
	private List<ItemForSearch> listForDanhsachbenhnhan;
	
	
	public List<ItemForSearch> getListForDanhsachbenhnhan() {
		return listForDanhsachbenhnhan;
	}

	public void setListForDanhsachbenhnhan(
			List<ItemForSearch> listForDanhsachbenhnhan) {
		this.listForDanhsachbenhnhan = listForDanhsachbenhnhan;
	}

	@Create
	public void init() {
		log.info("---init");
		listForDanhsachbenhnhan = new ArrayList<ItemForSearch>();		
	}
	
	public void fillList(){
		listForDanhsachbenhnhan = new ArrayList<ItemForSearch>();
		log.info(result);
		if (result!=null & !result.equals("")){
			JSONArray arr;
			try{
				arr = new JSONObject(result).getJSONArray("listBn");
			}catch (JSONException ee){				
				arr = new JSONArray();
				try {
					arr.put(new JSONObject(result).getJSONObject("listBn"));
				} catch (JSONException e) {	}
			}
			
			try {
				ItemForSearch item = null;
				for (int i = 0; i < arr.length(); i++){
					item = new ItemForSearch();
					JSONObject obj = arr.getJSONObject(i);
					item.setBnMa(obj.getString("bnMa"));
					item.setBnHoten(obj.getString("ten"));
					item.setBnGtinh(obj.getString("gtinh"));
					item.setBnTuoi(obj.getString("tuoi"));
					if (obj.getString("dvt").equals("1")){
						item.setBnDvtuoi(IConstantsRes.NAM);
					}else if (obj.getString("dvt").equals("2")){
						item.setBnDvtuoi(IConstantsRes.THANG);
					}else if (obj.getString("dvt").equals("3")){
						item.setBnDvtuoi(IConstantsRes.NGAY);
					}else{
						item.setBnDvtuoi("");
					}
					
					item.setBnNgaysinh(obj.getString("nsinh"));
					item.setBnDantoc(obj.getString("dtoc"));
					item.setTdMa(obj.getString("mtd"));
					listForDanhsachbenhnhan.add(item);
				}
			} catch (Exception e) {} 
		}	
	}
		
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	public class ItemForSearch{
		private String bnMa;
		private String bnHoten;
		private String bnGtinh;
		private String bnTuoi;
		private String bnDvtuoi;
		private String bnNgaysinh;
		private String bnDantoc;
		private String tdMa;
		
		public ItemForSearch(){
			
		}

		public String getBnMa() {
			return bnMa;
		}

		public void setBnMa(String bnMa) {
			this.bnMa = bnMa;
		}

		public String getBnHoten() {
			return bnHoten;
		}

		public void setBnHoten(String bnHoten) {
			this.bnHoten = bnHoten;
		}

		public String getBnGtinh() {
			return bnGtinh;
		}

		public void setBnGtinh(String bnGtinh) {
			this.bnGtinh = bnGtinh;
		}

		public String getBnTuoi() {
			return bnTuoi;
		}

		public void setBnTuoi(String bnTuoi) {
			this.bnTuoi = bnTuoi;
		}

		public String getBnDvtuoi() {
			return bnDvtuoi;
		}

		public void setBnDvtuoi(String bnDvtuoi) {
			this.bnDvtuoi = bnDvtuoi;
		}

		public String getBnNgaysinh() {
			return bnNgaysinh;
		}

		public void setBnNgaysinh(String bnNgaysinh) {
			this.bnNgaysinh = bnNgaysinh;
		}

		public String getBnDantoc() {
			return bnDantoc;
		}

		public void setBnDantoc(String bnDantoc) {
			this.bnDantoc = bnDantoc;
		}

		public String getTdMa() {
			return tdMa;
		}

		public void setTdMa(String tdMa) {
			this.tdMa = tdMa;
		}
		
		
	}

}
