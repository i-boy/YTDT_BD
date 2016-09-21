package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.CtPhieuKhamDtNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuKhamDtNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B118_Thamkhamdieutringoaitru")
@Synchronized(timeout = 6000000)
public class ThamKhamDieuTriNgoaiTru implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String sMaPhieuKhamDT="";
	private String ngaySinh="";
	private String gioi="";
	private PhieuKhamDtNgoaiTru objPhieuKhamDTNgoaiTru=new PhieuKhamDtNgoaiTru(); 
	private String sShowDel="";
	private String sShowPrint="";
	private String ngay;
	private String chandoan;
	private String dieutri;
	private String bacSiMa;
	private Integer bacSiMaSo_hidden;
	@DataModel
	private List<CtPhieuKhamDtNgoaiTru> listCtPKDTNT;
	@DataModelSelection(value="listCtPKDTNT")
	private CtPhieuKhamDtNgoaiTru ctpkdtntSelection;
	private CtPhieuKhamDtNgoaiTruDelegate ctpkdtntDAO;
	
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)
	private String goToThamKhamDieuTriNgoaiTru;
	
	@Out(required = false)
	private String bacSiKCB;

	private BenhNhan benhNhan;

	private ThamKham thamkham;

	public void resetValue() {

	}
	private String resultHidden ="";
	
	@Begin(nested = true)
	@Factory("goToThamKhamDieuTriNgoaiTru")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ctpkdtntDAO = CtPhieuKhamDtNgoaiTruDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			if(thamkham != null){
				log.info("*** MABANKHAM: "+thamkham.getThamkhamMa());
				//objPhieuKhamDTNgoaiTru = PhieuKhamDtNgoaiTruDelegate.getInstance().findByMaThamKham(thamkham.getThamkhamMa());
				if(thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
					objPhieuKhamDTNgoaiTru = PhieuKhamDtNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(thamkham.getTiepdonMa().getTiepdonSothebh(),
																													  thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(),
																							                          thamkham.getThamkhamBankham().getDtdmbankhamMaso());
				} else {
					objPhieuKhamDTNgoaiTru = PhieuKhamDtNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(null,
																													  thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(),
																							                          thamkham.getThamkhamBankham().getDtdmbankhamMaso());
				}
				if(objPhieuKhamDTNgoaiTru == null){
					log.info("*****KO TIM THAY");
					objPhieuKhamDTNgoaiTru=new PhieuKhamDtNgoaiTru();
					listCtPKDTNT = new ArrayList<CtPhieuKhamDtNgoaiTru>();
					
					// 20111107 bao.ttc: them thong tin Chan doan
					String chandoanbenh = "";
					if (thamkham.getBenhicd10() != null){
						chandoanbenh = thamkham.getBenhicd10().getDmbenhicdTen();
					}
					if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")){
						chandoanbenh += " - " + thamkham.getThamkhamGhichu();
					}
					objPhieuKhamDTNgoaiTru.setPkdtntChandoan(chandoanbenh);
					// END -- 20111107 bao.ttc: them thong tin Chan doan
					
					sShowDel="false";
					sShowPrint="false";
					sMaPhieuKhamDT="";
				}
				else{
					log.info("***** TIM THAY");
					listCtPKDTNT = ctpkdtntDAO.findByPKDTNTMa(objPhieuKhamDTNgoaiTru.getPkdtntMa());
					sMaPhieuKhamDT=objPhieuKhamDTNgoaiTru.getPkdtntMa();
					
					// 20111107 bao.ttc: them thong tin Chan doan
					if (objPhieuKhamDTNgoaiTru.getPkdtntChandoan() != null && !objPhieuKhamDTNgoaiTru.getPkdtntChandoan().equals("")){
						String chandoanbenh = "";
						if (thamkham.getBenhicd10() != null){
							chandoanbenh = thamkham.getBenhicd10().getDmbenhicdTen();
						}
						if (thamkham.getThamkhamGhichu() != null && !thamkham.getThamkhamGhichu().equals("")){
							chandoanbenh += " - " + thamkham.getThamkhamGhichu();
						}
						objPhieuKhamDTNgoaiTru.setPkdtntChandoan(chandoanbenh);
						// END -- 20111107 bao.ttc: them thong tin Chan doan
					}
					
					sShowDel="true";
					sShowPrint="true";
					
				}
			}
			log.info("*** LIST SIZE: "+listCtPKDTNT.size());
			
			
			
			if (benhNhan.getDmgtMaso() != null){
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
					gioi = "r1";  //1 : Name
				}else{
					gioi = "r2";
				}					
			}else{
				gioi = null;
			}
			
			setOtherValue();
			destroyService();
			goToThamKhamDieuTriNgoaiTru = "";
			
		}catch(Exception e){
			log.info("***init Exception = " + e.toString());
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		
	}
		
	//***********************************************************************************
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhan() throws Exception {
		log.info("*****Begin ghiNhan() *****");
		
		try {
			PhieuKhamDtNgoaiTruDelegate phieuKhamDtNgoaiTruDel=PhieuKhamDtNgoaiTruDelegate.getInstance();
			
			objPhieuKhamDTNgoaiTru.setPkdtntThamkham(thamkham);
			objPhieuKhamDTNgoaiTru.setPkdtntBenhnhan(thamkham.getTiepdonMa().getBenhnhanMa());
			objPhieuKhamDTNgoaiTru.setPkdtntBankham(thamkham.getThamkhamBankham());
			if (thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
				objPhieuKhamDTNgoaiTru.setPkdtntSothebh(thamkham.getTiepdonMa().getTiepdonSothebh());
			}
			String sMaPhieu="";
			sMaPhieu=phieuKhamDtNgoaiTruDel.capNhatPhieuKhamDtNgoaiTru(objPhieuKhamDTNgoaiTru, sMaPhieuKhamDT, listCtPKDTNT);
			//setMaPhieuKhamDtNgoaiTru(sMaPhieu);
			log.info("*******************Ma Phieu: "+sMaPhieu);
			sMaPhieuKhamDT = sMaPhieu; // 20111220 bao.ttc: set lai de In phieu ngay sau khi ghi nhan.
			FacesMessages.instance().add(IConstantsRes.RPPKDTNT_INSERT_SUCCESS,sMaPhieu);
			log.info("*********************CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
			sShowDel="true";
			sShowPrint="true";
			init();
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			log.error("*************loi***********" + e.toString());
			return null;
		}
		log.info("*****End ghiNhan() *****");
		return null;
	}
	
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		insertRow();
		reset_ctpkdtnt();
		log.info("*****End Enter() *****");
	}
	public void insertRow(){
		if (ngay.equals("") || chandoan.equals("") || dieutri.equals("") || bacSiMa.equals("")) {
			FacesMessages.instance().add(IConstantsRes.THONGTIN_CHANDOAN);
		} else {
			CtPhieuKhamDtNgoaiTru each = new CtPhieuKhamDtNgoaiTru();
			each.setChandoan(chandoan);
			each.setCtpkdtntDieutri(dieutri);
			if(ngay != null && !ngay.equals(""))
				each.setCtpkdtntNgay(Utils.getDBDate("00:00", ngay).getTime());
			DtDmNhanVien bacsi = new DtDmNhanVien();
			bacsi.setDtdmnhanvienMaso(bacSiMaSo_hidden);
			log.info("***bacsimaso: " + bacSiMaSo_hidden);
			bacsi.setDtdmnhanvienMa(bacSiMa);
			each.setBacsi(bacsi);
			each.setPhieuKcbNgoaiTru(objPhieuKhamDTNgoaiTru);
			listCtPKDTNT.add(each);
		}
	}
	
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		listCtPKDTNT.remove(index);
		reset_ctpkdtnt();
		log.info("*****End delete() *****");
	}
	
	public void reset_ctpkdtnt(){
		ngay = "";
		dieutri = "";
		chandoan = "";
		
		// 20110427 bao.ttc: giu lai thong tin BS de co the nhap cho lan sau
		//bacSiMaSo_hidden = null;
		//bacSiMa = "";
	}
	public void huyPhieu()
	{
		log.info("***** start  huyPhieu() *****");
		if(sMaPhieuKhamDT==null||sMaPhieuKhamDT.equals(""))
		{
			return;
		}
		PhieuKhamDtNgoaiTruDelegate PhieuKhamDtNgoaiTruDel=PhieuKhamDtNgoaiTruDelegate.getInstance();
		PhieuKhamDtNgoaiTru obj=PhieuKhamDtNgoaiTruDel.find(sMaPhieuKhamDT);
		if(obj==null)
			return;
		
		PhieuKhamDtNgoaiTruDel.removeAllPhieuKhamDtNgoaiTru(obj);
		FacesMessages.instance().add(IConstantsRes.RPPKDTNT_DELETE_SUCCESS,sMaPhieuKhamDT);
		log.info("***** XOA THANH CONG *****");
		objPhieuKhamDTNgoaiTru=new PhieuKhamDtNgoaiTru();
		sMaPhieuKhamDT="";
		sShowDel="false";
		sShowPrint="false";
		listCtPKDTNT=new ArrayList<CtPhieuKhamDtNgoaiTru>();
		reset_ctpkdtnt();
		log.info("***** end  huyPhieu() *****");
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToThamKhamDieuTriNgoaiTru = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
	}
	
	public void lapBAmoi() {
		objPhieuKhamDTNgoaiTru = new PhieuKhamDtNgoaiTru();
		listCtPKDTNT = new ArrayList<CtPhieuKhamDtNgoaiTru>();	
		sMaPhieuKhamDT = "";
		sShowDel="false";
		sShowPrint="false";
	}
	
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	public String thuchienAction(){
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}
	
	public void XuatReport() {
		reportTypeTD="thamkhamdieutringoaitru";
		log.info("Vao Method XuatReport benhanvaovien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieukhamdieutringoaitru.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuKhamDtNgoaiTru phieuKhamDTNgoaiTruTemp = PhieuKhamDtNgoaiTruDelegate.getInstance().find(sMaPhieuKhamDT);
			if (phieuKhamDTNgoaiTruTemp != null){
				params.put("SOVAOVIEN", phieuKhamDTNgoaiTruTemp.getPkdtntSovaovien());
			}
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("HOTENBN", benhNhan.getBenhnhanHoten());
			params.put("GIOITINH", benhNhan.getDmgtMaso(true).getDmgtTen());
			Date nsDate = benhNhan.getBenhnhanNgaysinh();
			if (nsDate != null){
				params.put("NGAYSINH", sdf.format(nsDate));
			}else{
				params.put("NGAYSINH", benhNhan.getBenhnhanNamsinh());
			}
			params.put("DANTOC", benhNhan.getDantocMa(true).getDmdantocTen());
			params.put("NGHENGHIEP", benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
			params.put("DIACHI", (benhNhan.getBenhnhanDiachi()== null ? "" :  benhNhan.getBenhnhanDiachi() + ", " )+ (benhNhan.getXaMa() == null ? " " : benhNhan.getXaMa(true).getDmxaTen() + ", ") 
					+ (benhNhan.getHuyenMa() == null ? " " : benhNhan.getHuyenMa(true).getDmhuyenTen() + ", ") + (benhNhan.getTinhMa() == null ? "" :benhNhan.getTinhMa(true).getDmtinhTen()));
			params.put("NOILAMVIEC", thamkham.getTiepdonMa(true).getTiepdonMacoquan()); // 20111020 bao.ttc: them Co quan
			params.put("MATHEBHYT", thamkham.getTiepdonMa(true).getTiepdonSothebh());
			params.put("DOITUONG", thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongTen());
			// 20101102 bao.ttc: them ten Bac si
			if(thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen() != null){
				params.put("BACSI", thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen());
				//log.info("##### BAC SI ##### : " + thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen());
			}
			if(thamkham.getTiepdonMa(true).getTiepdonGiatri1()!=null && thamkham.getTiepdonMa(true).getTiepdonGiatri2()!=null)
			{
				params.put("GTSUDUNG1", sdf.format(thamkham.getTiepdonMa(true).getTiepdonGiatri1()));
				params.put("GTSUDUNG2",sdf.format(thamkham.getTiepdonMa(true).getTiepdonGiatri2()));
			}
			params.put("BAOTIN", thamkham.getTiepdonMa(true).getTiepdonBaotin());
			if(thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen()!=null)
				params.put("CHANDOANBD", thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen());
			if(thamkham.getThamkhamNgaygio()!=null)
				params.put("DENKBLUC", Utils.getGioPhutNgay(thamkham.getThamkhamNgaygio()));
			
			params.put("CHAYMAULAU", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTschaymaulau()));
			params.put("PHANUNGTHUOC", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsphanungthuoc()));
			params.put("DIUNG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbdiung()));
			params.put("CAOHA", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbcaoha()));
			params.put("TIMMACH", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbtm()));
			params.put("TIEUDUONG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbtieuduong()));
			params.put("DADAY", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbdadayth()));
			params.put("PHOI", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbphoi()));
			params.put("TRUYENNHIEM", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntTsbtruyennhiem()));
			params.put("PTRONGMIENG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntPtrongmieng()));
			params.put("PNGOAIMIENG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntPngoaimat()));
			params.put("CTMAU", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntXncongthucmau()));
			params.put("TBHOC", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntXntebaohoc())); // 20110307 bao.ttc: them Phan Te Bao Hoc
			params.put("CAYVK", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntXncayvk()));
			params.put("NHACHU", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhnhachu()));
			params.put("CHUARANG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhchuarang()));
			params.put("NHORANG", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhnhorang()));
			params.put("CANKHOP", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhcankhop()));
			params.put("PHCODINH", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhphuchinhcd()));
			params.put("PHTHAOLAP", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhphuhinhtl()));
			params.put("CHINHHINHRM", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhchinhhinhrm()));
			params.put("RANGTREEM", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhrangtreem()));
			params.put("PHONGNGUASR", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhphongnguasr()));
			params.put("PTHAMMAT", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntKhphauthuathm()));
			
			params.put("NHANXET", objPhieuKhamDTNgoaiTru.getPkdtntNhanxet());
			params.put("CHANDOAN", objPhieuKhamDTNgoaiTru.getPkdtntChandoan());
			
			params.put("PHTL", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntPhtl()));
			params.put("PHCD", getSymbol(objPhieuKhamDTNgoaiTru.getPkdtntPhcd()));
			
			//Tienpt modified 07/10/2010
			params.put("MA_PKDT_NGOAITRU", this.sMaPhieuKhamDT);
			
			String sDonViTuoi="";
			if(benhNhan.getBenhnhanDonvituoi()==1)
				sDonViTuoi = ""; //sDonViTuoi=IConstantsRes.NAM;
			else if(benhNhan.getBenhnhanDonvituoi()==2)
				sDonViTuoi = IConstantsRes.THANG;
			else if(benhNhan.getBenhnhanDonvituoi()==3)
				sDonViTuoi = IConstantsRes.NGAY;
			params.put("TUOI", benhNhan.getBenhnhanTuoi() + " " + sDonViTuoi);
															
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","thamkhamdieutringoaitru");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	private int index = 0;
	//***********************************************************************************

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa()
				.getBenhnhanNgaysinh() != null
				&& !thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa()
					.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
	}

	public void displayInfor() throws Exception {
		try {
			
		if(sMaPhieuKhamDT==null|| sMaPhieuKhamDT.equals(""))
			return;
		
		PhieuKhamDtNgoaiTruDelegate PhieuKhamDtNgoaiTruDel=PhieuKhamDtNgoaiTruDelegate.getInstance();
		objPhieuKhamDTNgoaiTru=new PhieuKhamDtNgoaiTru();
		List<PhieuKhamDtNgoaiTru> ls=PhieuKhamDtNgoaiTruDel.findByPhieuKhamDtNgoaiTru(getMaPhieuKhamDtNgoaiTru());
		
		if(ls==null||ls.size()==0)
		{
			FacesMessages.instance().add(IConstantsRes.RPPKDTNT_NOT_EXIST);
			sMaPhieuKhamDT="";
			sShowDel="false";
			sShowPrint="false";
			return;
		}
		sShowDel="true";
		sShowPrint="true";
		objPhieuKhamDTNgoaiTru=ls.get(0);
		sMaPhieuKhamDT=objPhieuKhamDTNgoaiTru.getPkdtntMa();
		//setDaKCT(giayChuyenVien.getGcvbhytKct());
		//setDtNgoaiTru(giayChuyenVien.getGcvbhytDadtngoaitru());
		//setDtNoiTru(giayChuyenVien.getGcvbhytDadtnoitru());
		log.info("*****sMaPhieuKhamDT: "+sMaPhieuKhamDT)		;
		
		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}
	}

	// 20110921 bao.ttc: lay thong tin ThuocPhongKham theo Ngay de dua vao Dieu tri
	public void get_thuoc_info(){
		
		dieutri = "";
		if(ngay != null && !ngay.equals("")){
			
			if(Utils.getDBDateWithHour(0, ngay, true) == null){
				log.info("### BA RHM Ngoai tru -- get_thuoc_info(): ERROR Parse NGAY !! ");
				return;
			}
			
			Date tuNgay = Utils.getDBDateWithHour(0, ngay, true).getTime();
			Date denNgay = Utils.getDBDateWithHour(23, ngay, false).getTime();
			String thuocBK = "";
			String thuocBH = "";
			String thuocVe = "";
			
			List<ThuocPhongKham> listThuocPhongKham;
			ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
			listThuocPhongKham = tpkDelegate.findByThamKhamVaNgay(thamkham.getThamkhamMa(), tuNgay, denNgay);
			
			if(listThuocPhongKham != null && listThuocPhongKham.size() >0){
				
				for (ThuocPhongKham thuoc : listThuocPhongKham){
					if(thuoc.getThuocphongkhamLoai().equals("3")){ // Thuoc Quay BV
						thuocBH += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBH += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBH += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocBH += ", " + thuoc.getThuocphongkhamChidan();
						thuocBH += "\n";
					} else if(thuoc.getThuocphongkhamLoai().equals("1")){ // Thuoc Ban kham
						thuocBK += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocBK += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocBK += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocBK += ", " + thuoc.getThuocphongkhamChidan();
						thuocBK += "\n";
					} else if(thuoc.getThuocphongkhamLoai().equals("2")){ // Thuoc Toa ve
						thuocVe += "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
						if(thuoc.getThuocphongkhamSoluong() != null)
							thuocVe += ", " + thuoc.getThuocphongkhamSoluong().toString();
						thuocVe += " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
						if(thuoc.getThuocphongkhamChidan() != null && !thuoc.getThuocphongkhamChidan().equals(""))
							thuocVe += ", " + thuoc.getThuocphongkhamChidan();
						thuocVe += "\n";
					}
				} // END FOR
				
				if(!thuocBH.equals(""))
					dieutri += "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH;
				if(!thuocBK.equals(""))
					dieutri += "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK;
				if(!thuocVe.equals(""))
					dieutri += "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe;
				
			} else {
				dieutri = IConstantsRes.KHONG_DUNG_THUOC;
			}
			
		}
		
		//log.info("### Y lenh: " + ylenh);
	}
	
	//Ham huy cac service da khoi tao
	public void destroyService() {
		try {
			log.debug("===== begin destroyService() method");			
//			thamKhamWS = null;
			log.debug("***** End destroyService() method");
		} catch (Exception ex) {
			log.debug("*****destroyService Exception: " + ex);
		}
	}	
	
	
	//Ham  se duoc goi khi het session (session timeout cau hinh trong file web.xml)
	@Destroy 
	public void destroy() {
		log.info("************************* destroy PhieuKhamDtNgoaiTru");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}
	
	public String getMaBanKhamOut() {
		return maBanKhamOut;
	}

	public void setMaBanKhamOut(String maBanKhamOut) {
		this.maBanKhamOut = maBanKhamOut;
	}

	public String getMaTiepDonOut() {
		return maTiepDonOut;
	}

	public void setMaTiepDonOut(String maTiepDonOut) {
		this.maTiepDonOut = maTiepDonOut;
	}

	public String getGioi()
	{
		return gioi;
	}

	public void setGioi(String gioi)
	{
		this.gioi = gioi;
	}


	public String getBacSiKCB() {
		return bacSiKCB;
	}

	public void setBacSiKCB(String bacSiKCB) {
		this.bacSiKCB = bacSiKCB;
	}

	public String getMaPhieuKhamDtNgoaiTru()
	{
		return sMaPhieuKhamDT;
	}

	public void setMaPhieuKhamDtNgoaiTru(String maGiayChuyenVien)
	{
		sMaPhieuKhamDT = maGiayChuyenVien;
	}
	
	public String getShowDel()
	{
		return sShowDel;
	}

	public void setShowDel(String showDel)
	{
		sShowDel = showDel;
	}

	public String getShowPrint()
	{
		return sShowPrint;
	}

	public void setShowPrint(String showPrint)
	{
		sShowPrint = showPrint;
	}

	public PhieuKhamDtNgoaiTru getPhieuKhamDTNgoaiTru()
	{
		return objPhieuKhamDTNgoaiTru;
	}

	public void setPhieuKhamDTNgoaiTru(PhieuKhamDtNgoaiTru objPhieuKhamDTNgoaiTru)
	{
		this.objPhieuKhamDTNgoaiTru = objPhieuKhamDTNgoaiTru;
	}
	
	private String getSymbol(Boolean boo)
	{
		if(boo) return "X";
		else return "";
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getChandoan() {
		return chandoan;
	}

	public void setChandoan(String chandoan) {
		this.chandoan = chandoan;
	}

	public String getBacSiMa() {
		return bacSiMa;
	}

	public void setBacSiMa(String bacSiMa) {
		this.bacSiMa = bacSiMa;
	}

	public Integer getBacSiMaSo_hidden() {
		return bacSiMaSo_hidden;
	}

	public void setBacSiMaSo_hidden(Integer bacSiMaSo_hidden) {
		this.bacSiMaSo_hidden = bacSiMaSo_hidden;
	}

	public List<CtPhieuKhamDtNgoaiTru> getListCtPKDTNT() {
		return listCtPKDTNT;
	}

	public void setListCtPKDTNT(List<CtPhieuKhamDtNgoaiTru> listCtPKDTNT) {
		this.listCtPKDTNT = listCtPKDTNT;
	}

	public CtPhieuKhamDtNgoaiTru getCtpkdtntSelection() {
		return ctpkdtntSelection;
	}

	public void setCtpkdtntSelection(CtPhieuKhamDtNgoaiTru ctpkdtntSelection) {
		this.ctpkdtntSelection = ctpkdtntSelection;
	}

	public String getDieutri() {
		return dieutri;
	}

	public void setDieutri(String dieutri) {
		this.dieutri = dieutri;
	}
	
	
	
}


