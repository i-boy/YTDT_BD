/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3135_Tonghopthuocydungcutheophong")
@Scope(CONVERSATION)
public class Tonghopthuocydungcutheophong implements Serializable{
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB3135=null;
	
	@DataModel
	private List<Hsba> listBN = new ArrayList<Hsba>();

	@DataModelSelection("listBN")
	@Out(required = false)
	private Hsba hsbaSelect;

	private int index=0;
	private String ngayhientai="";
//	private String khoaMa="";
	private String ngaydungtu="";
	private String ngaydungden="";
	private String sobenhanStr="";
	private String hotenBNStr="";
	private Hsba hsba=null;
	
	private DmKhoa dmKhoa;
	private DmTang dmTang;
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	
	@Factory("resetFormB3135")
	public void initresetFormB3135() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
	}
	
	@In(required = false)
	@Out(required = false)
	private String ten3135_3138;
	
	@Begin (join = true)
	public String init(String ten3135_3138) { // 3135 hoac 3138
		log.info("init()");
		this.ten3135_3138 = ten3135_3138;
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		refreshDmKhoaNT();
		return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong";
	}
	
	@End
	public void end(){
		
	}
	
	public void onblurMaKhoaAction() {
		log.info("-----BEGIN onblurMaKhoaAction()-----");
		if (dmKhoa != null && dmKhoa.getDmkhoaMa() != null) {
			String maKhoa = dmKhoa.getDmkhoaMa();
			if (hmDmKhoaNTAll != null) {
				DmKhoa dmKhoa = (DmKhoa) hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if (dmKhoa != null) {
					this.dmKhoa.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
					this.dmKhoa.setDmkhoaMa(dmKhoa.getDmkhoaMa());
					this.dmKhoa.setDmkhoaTen(dmKhoa.getDmkhoaTen());
					log.info("Tim ma khoa: Da thay khoa "
							+ dmKhoa.getDmkhoaTen());
				} else {
					this.dmKhoa = new DmKhoa();
					return;
				}
			}
			dmTang = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurMaKhoaAction()-----");
	}

	public void refreshDmTang() {
		listDmTangs = new ArrayList<SelectItem>();
		if (dmKhoa != null && dmKhoa.getDmkhoaMaso() != null) {
			String khoaMa = dmKhoa.getDmkhoaMa();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			// Get tat ca cac tang cua khoa chuyen den, show gia tri default
			// truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang","dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if (lstDmTangs != null && lstDmTangs.size() > 0) {
				for (DmTang dmTang : lstDmTangs) {
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
//				for (DmTang dmTang : lstDmTangs) {
//					if (dmTang.getDmtangDefault().booleanValue() == true) {
//						this.dmTang = dmTang;
//						break;
//					}
//				}
			}
		}
	}

	public void refreshDmKhoaNT() {
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();
		for (DmKhoa o : listDmKhoaNTAll) {
			hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
		}
		for (DmKhoa each : listDmKhoaNTAll) {
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}
	
	public void onblurTenKhoaAction(){
		log.info("-----BEGIN onblurTenKhoaAction()-----");
		if(dmKhoa != null && dmKhoa.getDmkhoaTen() != null){
			String tenKhoa = dmKhoa.getDmkhoaTen();
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == tenKhoa || dmKhoa.getDmkhoaTen().equals(tenKhoa)){
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	dmKhoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	dmKhoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
		    	dmKhoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	dmKhoa = new DmKhoa();
		    	return;
		    }
		    dmTang = new DmTang();
		    refreshDmTang();
		}
		log.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void onblurTenTangAction(){
		if(dmTang != null && dmTang.getDmtangTen() != null && dmTang.getDmtangTen() != "Tất cả"){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", dmTang.getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				dmTang = lstTangs.get(0);
			}else{
				dmTang = new DmTang();
			}
		}else{
			dmTang = new DmTang();
		}
	}
	
	
	
	public String thuchienAction(){
		XuatReport();
		resetFormB3135=null;
		return "B3360_Chonmenuxuattaptin";
	}
	
	/**
	 * Lay ten benh nhan dua vao so vao vien
	 */
	public void loadHsba() {
		log.info("-----So vao vien-----" + sobenhanStr);
		hsba = new Hsba();
		if (!sobenhanStr.equals("")) {
			try {
				HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
				hsba = hsbaDAO.find(sobenhanStr);
				if (hsba == null) {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sobenhanStr);
					return;
				}else{
					setSobenhanStr(hsba.getHsbaSovaovien());
					setHotenBNStr(hsba.getBenhnhanMa().getBenhnhanHoten());
				}	
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, sobenhanStr);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        String result = "";
        for(String each : inputs){
                if(each != "")
                        result += "'"+each + "',";
        }
        result = result.substring(0, result.length()-1);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
	}
	
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		boolean test=false;
		if(hsba != null){
			if(listBN.size()==0){
				log.info("size list bang 0");
				listBN.add(hsba);
				log.info("da add phan tu dau tien" + listBN.size());
			}else if (listBN.size()>0){
				log.info("size list lon hon 0");
				for(Hsba item:listBN){
					if(item.getHsbaSovaovien().equals(sobenhanStr)){
						test=true;
					}
				}
				if(test == false){
					listBN.add(hsba);
				}
				log.info("da add phan tu" + listBN.size());
			}
		}
		setSobenhanStr("");
		setHotenBNStr("");
		setHsba(null);
		log.info("ketthuc them du lieu vao luoi");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deleteBN(){
		log.info("bat dau xoa , size" + listBN.size());
		listBN.remove(hsbaSelect);
		log.info("da xoa , size" + listBN.size());
		log.info("ket thuc xoa");
	}
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
//		khoaMa="";
		ngaydungtu="";
		ngaydungden="";
		sobenhanStr="";
		hotenBNStr="";
		listBN.clear();
		FacesMessages.instance().clear();
		dmTang = new DmTang();
		dmKhoa = new DmKhoa();
	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeVP="Tonghopthuocydungcutheophong";
		log.info("Vao Method XuatReport Tong hop thuoc y dung cu theo phong");
		String baocao1=null;
		String pathTemplate=null;
		try {
			if ("3135".equals(ten3135_3138)){
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/tonghopthuocydungcutheophong.jrxml";
			}else{ //3138
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/phieulinhthuochangngay.jrxml";
				
			}
			
			
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
//			DmKhoa dmkhoa = new DmKhoa();
			DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
//			Object obj = dtUtilDAO.findByMa( khoaMa , "DmKhoa" , "dmkhoaMa");
//			dmkhoa = (DmKhoa)obj;
			params.put("KHOA", dmKhoa.getDmkhoaTen());
			params.put("KHOAMASO", dmKhoa.getDmkhoaMaso());
			params.put("NGAYLAPPHIEU", sdf.parse(ngayhientai));
			params.put("NGAYDUNGTU", sdf.parse(ngaydungtu));
			params.put("NGAYDUNGDEN", sdf.parse(ngaydungden));
			if  (dmTang!= null && dmTang.getDmtangMaso()!= null){
				params.put("TANGMASO", dmTang.getDmtangMaso());
				params.put("TANGTEN", dmTang.getDmtangTen());
			}else {
				params.put("TANGMASO", null);
				params.put("TANGTEN", null);
			}
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			
			if(listBN.size()>0){
				List<String> listtemp=new ArrayList<String>();
				for(Hsba item:listBN){
					listtemp.add(item.getHsbaSovaovien());
				}
				params.put("SOVAOVIEN", getListDVMaParamsHelp(listtemp));
				log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
			}
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Tonghopthuocydungcutheophong");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getNgaydungtu() {
		return ngaydungtu;
	}

	public void setNgaydungtu(String ngaydungtu) {
		this.ngaydungtu = ngaydungtu;
	}

	public String getNgaydungden() {
		return ngaydungden;
	}

	public void setNgaydungden(String ngaydungden) {
		this.ngaydungden = ngaydungden;
	}

	public String getSobenhanStr() {
		return sobenhanStr;
	}

	public void setSobenhanStr(String sobenhanStr) {
		this.sobenhanStr = sobenhanStr;
	}

	public String getHotenBNStr() {
		return hotenBNStr;
	}

	public void setHotenBNStr(String hotenBNStr) {
		this.hotenBNStr = hotenBNStr;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public DmKhoa getDmKhoa() {
		return dmKhoa;
	}

	public void setDmKhoa(DmKhoa dmKhoa) {
		this.dmKhoa = dmKhoa;
	}

	public DmTang getDmTang() {
		return dmTang;
	}

	public void setDmTang(DmTang dmTang) {
		this.dmTang = dmTang;
	}

	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}

	public HashMap<String, DmKhoa> getHmDmKhoaNTAll() {
		return hmDmKhoaNTAll;
	}

	public void setHmDmKhoaNTAll(HashMap<String, DmKhoa> hmDmKhoaNTAll) {
		this.hmDmKhoaNTAll = hmDmKhoaNTAll;
	}

	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}

	public DmKhoaDelegate getDmKhoaDel() {
		return dmKhoaDel;
	}

	public void setDmKhoaDel(DmKhoaDelegate dmKhoaDel) {
		this.dmKhoaDel = dmKhoaDel;
	}

	public List<DmKhoa> getListDmKhoaNTAll() {
		return listDmKhoaNTAll;
	}

	public void setListDmKhoaNTAll(List<DmKhoa> listDmKhoaNTAll) {
		this.listDmKhoaNTAll = listDmKhoaNTAll;
	}
	
}

