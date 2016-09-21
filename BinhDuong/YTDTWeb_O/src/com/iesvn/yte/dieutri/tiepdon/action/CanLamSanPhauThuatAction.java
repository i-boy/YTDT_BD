package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.HsttKhamThreadUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_2_Canlamsangphauthuat")
@Synchronized(timeout = 6000000)
public class CanLamSanPhauThuatAction implements Serializable {

	//private static String FORMAT_DATE = "dd/MM/yyyy";
	//private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	
	private String strNgayBatDauBh;
	private String strNgayHetHanBh;
	private Date ngayBatDauBh;
	private Date ngayHetHanBh;
	
	// bao.ttc: index cua CLS can edit
	private int cls_index = 1;
	// 20110322 bao.ttc: check Thanh toan hay chua de Disable nut Ghi nhan
	private boolean daThanhtoan;
	
	private String diengiai;
	@Logger
	private Log log;
	
	@DataModel
	private java.util.List<ClsKham> listCtkq_B121_2; // bao.ttc:  ko dung ArrayList, dung List 
	@DataModel
	private java.util.List<ClsKhamExt> listCLSBanKhamTruoc;//Tho add hien thi danh sach CLS ban kham truoc neu co	
	
	private java.util.ArrayList<ClsKham> listCLSChoose_B121_1 = new ArrayList<ClsKham>();	
	
	private java.util.ArrayList<ClsKham> listCLSChoose_B121_2 = new ArrayList<ClsKham>();
	
	private Integer bacSiChiDinhMaSo;
	private String bacSiChiDinhMa;	
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@In(required = false)
	@Out(required = false)	
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
		
	@In(scope=ScopeType.PAGE,required = false)
	@Out(scope=ScopeType.PAGE,required = false)	
	private String goToCLSThuThuat;
		
	private BenhNhan benhNhan;
		
	private ThamKham thamkham;

	@DataModelSelection("listCtkq_B121_2")
	@Out(required=false)
	private ClsKham nhapctSelected;
	
	@Out(required=false)	
	private String reportPathTD=null;
	
	@Out(required=false)	
	private String reportTypeTD=null;
	
	@Out(required=false)	
	JasperPrint jasperPrintTD = null;
	
	@Out(required=false)
	@In(required=false)
	String initclsCapNhatTTCLS = null;
	
	@Out(required=false)	
	Integer clsmaOut = null;
	
	@Out(required=false)
	@In(required=false)
	private String khongQuaylaiTuCapNhatTTCLS ;
	
	private int index=0;
	
	@Out(required=false)
	@In(required=false)
	String fromMenu ;
	
	boolean dangGoiGhiNhan = false;
	
	private String khoaCDHA;
	
	private String dsIn;
	
	private Map<ClsKham,Boolean> mapSelect = new HashMap<ClsKham,Boolean>();
	
	@Factory("khongQuaylaiTuCapNhatTTCLS")
	@Begin(nested = true)
	public void quaylaitucapnhatketquacls(){
		log.info("begin quaylaitucapnhatketquacls");
		if (maBanKhamOut== null || maBanKhamOut.equals("") || maTiepDonOut == null || maTiepDonOut.equals("")){
			resetValueFromMenu();
			return;
		}
		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
		
		// bao.ttc:  ko dung ArrayList, dung List 
		java.util.List<ClsKham> listCtkq_B121_2_temp = (java.util.List<ClsKham>)clsKhamDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
		log.info("------------------------");
		if (listCtkq_B121_2_temp != null && listCtkq_B121_2_temp.size() > 0) {
			listCtkq_B121_2 = listCtkq_B121_2_temp;
			for (ClsKham cls : listCtkq_B121_2) {
				com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);		
				log.info(cls.getClskhamTh());
			}			
		}
		log.info("------------------------");
		khongQuaylaiTuCapNhatTTCLS ="yes";
		log.info("end quaylaitucapnhatketquacls");
	}
	
	@Begin(join = true)
	public String initFromMenu(String fromMenu){
		log.info("begin initFromMenu");
		
		khongQuaylaiTuCapNhatTTCLS="yes";
		goToCLSThuThuat="no";
		
		maBanKhamOut = null;
		maTiepDonOut = null;
		this.fromMenu = fromMenu;
		
		resetValueFromMenu();
		log.info("end initFromMenu");
		return "clsthuthat";
	}
	public void resetValueFromMenu(){
		maBanKhamOut = "";
		maTiepDonOut = "";
		thamkham = new ThamKham();		
		benhNhan = new BenhNhan();
		ngaySinh= "";
		thoiGian = "";
		loai = "";
		maSo = null;
		ma="";
		tenCLS="";
		diengiai = "";
		maKhoa = "";
		listCLSChoose = "";
		kyThuatCao = new Boolean(false);
		ndm = new Boolean(false);
		mien = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		donGiaBH = new Double(0);
		phanDV = new Double(0);
		listCtkq_B121_2 = new ArrayList<ClsKham>(); // bao.ttc: dung List 
		listCLSBanKhamTruoc = new ArrayList<ClsKhamExt>();
		listCLSChoose_B121_1 = new java.util.ArrayList<ClsKham>();
		listCLSChoose_B121_2 = new java.util.ArrayList<ClsKham>();
		ghiChu = "";
		daThanhtoan = false;
		strNgayBatDauBh = strNgayHetHanBh = "";
		ngayBatDauBh = ngayHetHanBh = null;
		khoaCDHA = "";
		mapSelect = new HashMap<ClsKham,Boolean>();
	}
	public String getListCLSChoose() {
		return listCLSChoose;
	}

	public void setListCLSChoose(String listCLSChoose) {
		this.listCLSChoose = listCLSChoose;
	}

	public void resetValueFromTK(){

		thamkham = new ThamKham();
		benhNhan = new BenhNhan();ngaySinh= "";
		thoiGian = "";
		loai = "";
		maSo = null;
		ma="";
		tenCLS="";
		diengiai = "";
		maKhoa = "";
		listCLSChoose = "";
		masoKhoa = 0;
		kyThuatCao = new Boolean(false);
		ndm = new Boolean(false);
		mien = new Boolean(false);
		soLuong = 0;
		donGia = new Double(0);
		donGiaBH = new Double(0);
		phanDV = new Double(0);
		listCtkq_B121_2 = new ArrayList<ClsKham>(); // bao.ttc: dung List 
		//listCLSChoose_B121_1 = new java.util.ArrayList<ClsKham>();
		//listCLSChoose_B121_2 = new java.util.ArrayList<ClsKham>();
		ghiChu = "";
		daThanhtoan = false;
		strNgayBatDauBh = strNgayHetHanBh = "";
		ngayBatDauBh = ngayHetHanBh = null;
		khoaCDHA = "";
		mapSelect = new HashMap<ClsKham,Boolean>();
	}
	
	

	@Factory("goToCLSThuThuat")
	@Begin(nested = true)
	public void comeFromThamKham(){
		log.info("begin comeFromThamKham");
		goToCLSThuThuat = "gone";
		
		if (dangGoiGhiNhan == true){
			log.info("dangGoiGhiNhan do nothing return");
			return;
		}
		if ("yes".equals(fromMenu)){
			log.info("den tu menu, maybe return");
//			if (maBanKhamOut == null || maBanKhamOut.equals("") || maTiepDonOut == null || maTiepDonOut.equals("")){
//				
//				log.info("den tu menu, return");
//				return;
//			}else{
//				fromMenu = null;
//				log.info("fromMenu: null");
//			}
			return;
		}
		
		if(maKhoa.equals("")){
			listCLSChoose_B121_1 = new java.util.ArrayList<ClsKham>();
			listCLSChoose_B121_2 = new java.util.ArrayList<ClsKham>();
		}
		if (listCtkq_B121_2 != null && listCtkq_B121_2.size() > 0 ){
			log.info("---------------------------dang thuc hien------------");
			return;
		}
		
		resetValueFromTK();
		thamkham.getThamkhamBankham(true).setDtdmbankhamMa(maBanKhamOut);
		thamkham.getTiepdonMa(true).setTiepdonMa(maTiepDonOut);
		
		khongQuaylaiTuCapNhatTTCLS="yes";		
		
		listCtkq_B121_2 = new ArrayList<ClsKham>(); // bao.ttc: dung List 
		setValueForListMo();
		
		// 20110322 bao.ttc: Kiem tra xem da Thanh toan hay chua de disable nut Ghi nhan doi voi BN BHYT
		if ( thamkham.getTiepdonMa(true).getTiepdonMa() != null && !thamkham.getTiepdonMa(true).getTiepdonMa().equals("") ){
			
			if ( !thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP") ) {
				
				HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
				HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
				
				if (hsttk != null){
					daThanhtoan = true;
				} else {
					daThanhtoan = false;
				}
			}
		}
		
		// 20110520 bao.ttc: check BN neu da chuyen vao noi tru thi khong cho chinh sua (dung chung bien "daThtoankham" de khong cho nhan Ghi nhan tren giao dien)
		if (thamkham.getTiepdonMa(true).getTiepdonChkhoa() != null && !thamkham.getTiepdonMa(true).getTiepdonChkhoa().equals("")){
			FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
			daThanhtoan = true;
		}
		
		log.info("end comeFromThamKham");
	}
	
	public void displayInforFromMenu() throws Exception {
//		maBanKhamOut = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
//		maTiepDonOut = thamkham.getTiepdonMa(true).getTiepdonMa();
		setValueForListMo();
	}
	
	public void setValueForListMo() {
		
		log.info("thamkham.getThamkhamBankham(true).getDtdmbankhamMa():"+thamkham.getThamkhamBankham(true).getDtdmbankhamMa());
		log.info("thamkham.getTiepdonMa(true).getTiepdonMa():"+thamkham.getTiepdonMa(true).getTiepdonMa());
		mapSelect = new HashMap<ClsKham, Boolean>();
		if (thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null || thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("") || thamkham.getTiepdonMa(true).getTiepdonMa() == null || thamkham.getTiepdonMa(true).getTiepdonMa().equals("")){
			resetValueFromMenu();
			return;
		}
		ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
		
		// bao.ttc: dung List
		List<ClsKham> listCtkq_B121_2_temp = (List<ClsKham>)clsKhamDelegate.findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),thamkham.getTiepdonMa(true).getTiepdonMa());
		
		if (listCtkq_B121_2_temp != null && listCtkq_B121_2_temp.size() > 0) {
			listCtkq_B121_2 = listCtkq_B121_2_temp;
			dsIn = "";
			for (ClsKham cls : listCtkq_B121_2) {
				
				mapSelect.put(cls, true);
				dsIn += "," + cls.getClskhamMahang(true).getDtdmclsbgMaso();

				if(cls.getClskhamTh() == null || cls.getClskhamTh() == false && cls.getClskhamMahang() != null){
					if(cls.getClskhamMahang().getDtdmclsbgXn() != null && cls.getClskhamMahang().getDtdmclsbgXn() == true){
						cls = connectLabconn(cls);				
						//log.info("ket qua: " + cls.getClskhamGhichu());
						//log.info("ket luan: " + cls.getClskhamKetqua());
						//log.info("thuc hien: " + cls.getClskhamTh());
						ClsKhamDelegate.getInstance().edit(cls);
					}
					if(cls.getClskhamKhoa() != null && cls.getClskhamKhoa().getDmkhoaMa().equals("CDH")){
						cls = connectPACS(cls);				
						//log.info("ket qua: " + cls.getClskhamGhichu());
						//log.info("ket luan: " + cls.getClskhamKetqua());
						//log.info("thuc hien: " + cls.getClskhamTh());
						ClsKhamDelegate.getInstance().edit(cls);
					}
				}
				com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
				//log.info("da thuc hien hay chua:" + cls.getClskhamTh());
			}			
		}
		
		
		
		
		ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
		ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), thamkham.getTiepdonMa(true).getTiepdonMa());
		if (thamkham_temp == null){
			resetValueFromMenu();
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
			return ;
		}
		thamkham = thamkham_temp;
		
		benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
		
		if (thamkham.getTiepdonMa().getKhoibhytMa() != null){
			khoiBHYT = thamkham.getTiepdonMa().getKhoibhytMa().getDtdmkhoibhytMa();
		}
		
		if (thamkham.getTiepdonMa().getTiepdonMoc1() != null && thamkham.getThamkhamNgaygiocn() != null){
			 if (thamkham.getTiepdonMa().getTiepdonMoc1().equals(thamkham.getThamkhamNgaygiocn())){
				 vuotMoc1 = "true";
			 }  else if ( thamkham.getThamkhamNgaygiocn().before(thamkham.getTiepdonMa().getTiepdonMoc1())){
				 vuotMoc1 = "true";				 
			 }			     
		}
		
		setOtherValue();		
	}
	
	public ClsKham connectPACS(ClsKham clsKham){
		try {
			if(IConstantsRes.KET_NOI_SERVER_PACS.equals("YES")){
				log.info("ConnectPACS");
				ThamKham thamkham_temp = thamkham;
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://" + IConstantsRes.PACS_SERVER + ":" + IConstantsRes.PACS_PORT + "/" + IConstantsRes.PACS_DATABASE;
				String username = IConstantsRes.PACS_USERNAME;
			    String password = IConstantsRes.PACS_PASSWORD;
			    boolean daThucHien = false;
			    //String ghichu = "";
			    String kqCLS = "";
			    String ketluan = "";
			    try {
					Class.forName(driver);
					Connection conn = DriverManager.getConnection(url, username, password);
					String query = " SELECT patient.*, study.*, series.* FROM patient, study, series ";
					query += " where 1 = 1 ";
					query += " and patient.pat_id = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
					query += " and study.ma_cls = '" + clsKham.getClskhamMahang(true).getDtdmclsbgMa()+ "' ";
					query += " AND patient.pk = study.patient_fk AND study.pk = series.study_fk";
					PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = stmt.executeQuery();  
					if(rs.first()){
						daThucHien = true;
						ketluan = rs.getString("ket_qua");
					}					
			    }catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if(daThucHien){
					clsKham.setClskhamTh(daThucHien);
					clsKham.setClskhamKetqua(kqCLS);					
					clsKham.setClskhamKetqua(ketluan);
				}
			}
		}catch (Exception e) {			
		}
		return clsKham;
	}
	
	public ClsKham connectLabconn(ClsKham clsKham){
		try {
		if(IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")){
			log.info("ConnectLabconn");
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		    //String url = "jdbc:sqlserver://127.0.0.1\\test:1433;databaseName=test";
			//String username = "sa";
		    //String password = "123456";					
			ThamKham thamkham_temp = thamkham;
			String temp = "";
			String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + "\\" + IConstantsRes.LABCONN_DATABASE + ":" + IConstantsRes.LABCONN_PORT + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
		    String username = IConstantsRes.LABCONN_USERNAME;
		    String password = IConstantsRes.LABCONN_PASSWORD;
		    boolean daThucHien = false;
		    String ghichu = "";
		    String kqCLS = "";
		    String ketluan = "";
		    try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url, username, password);
				List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen",clsKham.getClskhamMa()+"","");
				for (int j = 0; j < listClsKetQua.size(); j++) {	
					String query = " Select * from [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload]";
					query += " where 1 = 1 ";
					query += " and OrderID = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
					query += " and TestCodeHIS = '" + listClsKetQua.get(j).getClskqMa() + "' ";
					PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = stmt.executeQuery();  
					if(rs!=null){ 							
		                while(rs.next()){ 
		                	daThucHien = true;
		                	ResultSetMetaData rsMetaData = rs.getMetaData();
		                	temp += listClsKetQua.get(j).getClskqTen() + ": " + rs.getString("Result") + "\n";
		                	ketluan = rs.getString("Comment");
		                	listClsKetQua.get(j).setResult(rs.getString("Result"));
		                	DieuTriUtilDelegate.getInstance().edit(listClsKetQua.get(j));	
		                }
		                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");					
						String today = formatter.format(Calendar.getInstance().getTime());
						query = "UPDATE [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload] SET TimeOut = '" + today + "' ";
						query += " where 1 = 1 ";
						query += " and OrderID = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
						query += " and TestCodeHIS = '" + listClsKetQua.get(j).getClskqMa() + "' ";					
						stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						int num = stmt.executeUpdate();  
					     if (num == 1) {  
					         rs = stmt.getGeneratedKeys();  
					         log.info("ResultSet: "+rs.toString());  
					         if(rs!=null){  
					             while(rs.next()){  
					             	ResultSetMetaData rsMetaData = rs.getMetaData();
					                 int columnCount = rsMetaData.getColumnCount();
					
					                 for (int k = 1; k <= columnCount; k++) {
					                   String key = rs.getString(k);
					                   log.info("key " + k + " is " + key);
					                 }
					             }  
					         }  
					     }
					}
					
				}
				ghichu = temp;
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // load sqlserver driver
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(daThucHien){
				clsKham.setClskhamTh(daThucHien);
				clsKham.setClskhamKetqua(kqCLS);					
				clsKham.setClskhamGhichu(ghichu);
				clsKham.setClskhamKetqua(ketluan);
			}
			
		}	
		} catch (Exception e) {			
		}
		return clsKham;
	}
	
	@End 
	public void end(){
		
	}
	private String khoiBHYT = "";
	private String  vuotMoc1 = "";
	
	private boolean updateNhapct = false;

	private String loai = "";
	private String ma = "";
	private Integer maSo ;
	private String maKhoa = "";
	private String listCLSChoose = "";
	public Integer getMasoKhoa() {
		return masoKhoa;
	}

	public void setMasoKhoa(Integer masoKhoa) {
		this.masoKhoa = masoKhoa;
	}
	private Integer masoKhoa = 0;
	private Boolean mien = new Boolean(false);
	//private Boolean ngoaiDanhMuc = new Boolean(false);
	private Boolean yeuCau = new Boolean(false);
	private Boolean kyThuatCao = new Boolean(false);
	private Boolean dichVu = new Boolean(false);
	private Boolean ndm = new Boolean(false);
	private Boolean khongthu = new Boolean(false);
	
	private String tenCLS = "";
	private String ghiChu = "";
	private short soLuong = 0;
	private Double donGia = new Double(0);
	private Double donGiaBH = new Double(0); 
	private Double phanDV = new Double(0);

	public void resetValue() {

		loai = "";
		ma = "";
		//maKhoa = "";
		mien = new Boolean(false);
		//ngoaiDanhMuc = new Boolean(false);
		yeuCau = new Boolean(false);
		khongthu = new Boolean(false);
		kyThuatCao = new Boolean(false);
		dichVu = new Boolean(false);
		ndm = new Boolean(false);
		
		soLuong = 0;
		donGia = new Double(0);
		donGiaBH = new Double(0);
		phanDV = new Double(0);
		tenCLS = "";
		listCLSChoose = "";
		updateNhapct = false;
		ghiChu = "";
		listCLSBanKhamTruoc = new ArrayList<ClsKhamExt>();
	}
	private String resultHidden ="";
	
	public void CopyFrom(ClsKham cls) {
		
		loai = cls.getClskhamLoai();
		ma = cls.getClskhamMahang().getDtdmclsbgMa();
		maSo = cls.getClskhamMahang().getDtdmclsbgMaso();	
		log.info("In CopyFrom , ma = " + ma + ", Maso = " + maSo);
		maKhoa = (cls.getClskhamKhoa() != null ? cls.getClskhamKhoa().getDmkhoaMa() : "");		
		mien = cls.getClskhamMien();
		//ngoaiDanhMuc = cls.getClskhamNdm();
		yeuCau = cls.getClskhamYeucau();
		khongthu = cls.getClskhamKhongthu();
		kyThuatCao = cls.getClskhamKtcao();
		soLuong = cls.getClskhamLan();
		donGia = cls.getClskhamDongia();				
		dichVu = cls.getClskhamDichvu();
		ndm=cls.getClskhamNdm();
		// phuc.lc 
		tenCLS = cls.getClskhamMahang().getDtdmclsbgDiengiai();
		clsmaOut = cls.getClskhamMa();
		donGiaBH = cls.getClskhamDongiabh();
		phanDV = cls.getClskhamPhandv();
		ghiChu = cls.getClskhamGhichu();
		log.info("End CopyFrom , tenCLS = " + tenCLS + "clskham Maso = " + clsmaOut);
	}

	public void CopyTo(ClsKham cls) {
		log.info("Begin CopyTo(), Cls Bgia ma so = " + maSo);
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		
		//cls.setClskhamLoai(loai);
		
		DtDmClsBangGia dmClsBg = (DtDmClsBangGia)dieuTriUtilDelegate.findByMaSo(maSo, "DtDmClsBangGia", "dtdmclsbgMaso");
		log.info("dmClsBg = " + dmClsBg);
		cls.setClskhamMahang(dmClsBg);
		//cls.getClskhamMahang().setDtdmclsbgMa(ma);
		//cls.getClskhamMahang().setDtdmclsbgMaso(maSo);
		//phuc.lc
		cls.setClskhamMaloai(dmClsBg.getDtdmclsbgPhanloai());
		cls.setClskhamLoai(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMa());
		
		System.out.println("maSomaSomaSomaSomaSo:"+maSo);
		List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(dmClsBg.getDtdmclsbgMaso());
		if(dtDmClsKhoaList.size() > 0){
			maKhoa = (dtDmClsKhoaList.get(0).getDmkhoaMaso() != null ? dtDmClsKhoaList.get(0).getDmkhoaMaso().getDmkhoaMa() : "");
		}
		if(maKhoa.equals("")) {
			cls.setClskhamKhoa(null);
		} else {
			cls.getClskhamKhoa().setDmkhoaMa(maKhoa);
		}
		cls.setClskhamMien(dmClsBg.getDtdmclsbgMien());
		cls.setClskhamNdm(dmClsBg.getDtdmclsbgNDM());
		cls.setClskhamYeucau(yeuCau);
		cls.setClskhamKhongthu(khongthu);
		cls.setClskhamKtcao(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMaso() == 9); // 9 la ma so cua CLS Dich vu ky thuat cao
		cls.setClskhamLan(soLuong);
		cls.setClskhamLoai2(dmClsBg.getDtdmclsbgLoai());
		cls.setClskhamSoml(dmClsBg.getDmclsbgSoml());
		// phuc.lc comment
		// Truong CLSKHAM_DONGIA luu mot trong 2 gia tri nhu sau:
		// neu co chon yeu cau(dich vu) thi lay don gia yeu cau tu bang gia CSL, neu khong cho yeu cau(dich vu) thi la don gia tu bang gia CLS 
		// 2 gia tri nay da duoc tinh toan tren giao dien va luu vao bien donGia
		cls.setClskhamDongia(donGia);	
		cls.setClskhamDichvu(dichVu);
		//cls.setClskhamNdm(ndm);
		
		
		System.out.println("tenCLS:"+tenCLS);
		System.out.println("dongia:"+donGia);		
		
		//cls.getClskhamMahang().setDtdmclsbgDiengiai(tenCLS);
		//phuc.lc
		cls.setClskhamDongiabh(donGiaBH);
		cls.setClskhamPhandv(phanDV);
		cls.setClskhamGhichu(ghiChu);
		if (updateNhapct == true) {
			log.info("In CopyTo , clskham Maso = " + clsmaOut);
			cls.setClskhamMa(clsmaOut);
		}
	}
	// 20120702 bao.ttc: remove vi khong su dung
	// private List<ThuocPhongKham> listCtTPK_temp = null;
	// private List<ClsKham> clslist = null;
	
//	private void tinhToanChoHSTKKham(HsThtoank hsttk){
//		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(thamkham.getTiepdonMa());
//		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
//		Utils.setInforForHsThToan(hsttk);
//		// ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
//		// listCtTPK_temp = thuocDel.find2LoaiByMaTiepDon(thamkham.getTiepdonMa().getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
//		// clslist = hsthtoankUtilTmp.getListCtkq_temp();
//	}
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="CanLamSanPhauThuat";
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/phieuchidinhcls.jrxml";
			log.info("da thay file templete " + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("HOTEN", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten()  );
			params.put("BACSIDIEUTRI", thamkham.getThamkhamBacsi() != null ? thamkham.getThamkhamBacsi().getDtdmnhanvienTen(): "");
			String diachistr="";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi()+", ";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() !=null && !thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen()+", ";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen()+", ";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))
				diachistr += thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			// 20110510 bao.ttc: them thong tin Doi tuong, so the BHYT - Binh Duong
			if(thamkham.getTiepdonMa(true).getDoituongMa() != null && !thamkham.getTiepdonMa(true).getDoituongMa().equals("")){
				params.put("DOITUONG", thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongTen() );
			}
			params.put("SOTHEBHYT", thamkham.getTiepdonMa(true).getTiepdonSothebh() );
			
			params.put("GIOI", thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
			params.put("KHOA", thamkham.getTiepdonMa(true).getTiepdonBankham().getDtdmbankhamTen());
			params.put("MATIEPDON", thamkham.getTiepdonMa(true).getTiepdonMa());
			params.put("THAMKHAM", thamkham.getThamkhamMa());
			// phuc.lc 22-08-2011 : Fix bug 3853
			if(maKhoa.trim().length() > 0) {
				DmKhoa khoa = (DmKhoa)DieuTriUtilDelegate.getInstance().findByMa(maKhoa, "DmKhoa", "dmkhoaMa");				
				params.put("TENKHOACLS", khoa.getDmkhoaTen());
				//params.put("KHOACLS", " = " + maKhoa.toUpperCase());
				params.put("KHOACLS", " = " + khoa.getDmkhoaMaso());
			} else {
				params.put("TENKHOACLS", "");
				params.put("KHOACLS", " IS NULL ");
			}
			if(maKhoa.toUpperCase().equals("CDH")){
				params.put("KHOACDHA", khoaCDHA != "" ? khoaCDHA : null);
			}
			
			String sDonViTuoi="";
			if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi()==2)
				sDonViTuoi=IConstantsRes.THANG;
			else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi()==3)
				sDonViTuoi=IConstantsRes.NGAY;
			params.put("TUOI", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() + " " + sDonViTuoi);
			
			if (dsIn!="" && dsIn!= null){
				params.put("dsIn", " in ("+dsIn.replaceFirst(",", "")+")");
			}else{
				params.put("dsIn", " IS  NULL");
			}
			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan ="";
			
			//log.info(thamkham.getBenhicd10());
			
			if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
				}
				
			}
			
			String chanDoan = maChanDoan + " " +  tenChanDoan;
			//tiep tuc them benh phu.
			if (thamkham.getBenhicd10phu1() != null && thamkham.getBenhicd10phu1().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu2() != null && thamkham.getBenhicd10phu2().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu3() != null && thamkham.getBenhicd10phu3().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu4() != null && thamkham.getBenhicd10phu4().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			if (thamkham.getBenhicd10phu5() != null && thamkham.getBenhicd10phu5().getDmbenhicdMaso() !=null){
				DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += " , "+ maChanDoan + " " +  tenChanDoan;
				}
				
			}
			params.put("CHANDOAN", chanDoan );
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","CanLamSanPhauThuat");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathTD);
			    index+=1;
			    if(conn != null) {
			    	conn.close();
			    	conn = null;
			    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public String thuchienAction(){
		XuatReport();
		return "B160_Chonmenuxuattaptin";
	}


	/**
	 * 
	 * @throws Exception
	 * bao.ttc: them Index cua cls vua chon, dung khi edit cls
	 */
	public void nhapctAjax(int index) throws Exception {

		// 20110330 bao.ttc: comment vi da xet thanh toan hay chua khi load form
//		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
//		HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
//		if (hsttk_tmp != null){
//			FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
//			return ;
//		}
		
		log.info("*****Begin nhapctAjax() *****");
		//log.info("*****nhapctSelectednhapctSelectednhapctSelected:"+nhapctSelected.getClskhamMahang().getDtdmclsbgMa());
		//log.info("***** ma phieu : "+ nhapctSelected.getClskhamMaphieu());
		
		if (nhapctSelected.getClskhamMaphieu() != null && !nhapctSelected.getClskhamMaphieu().equals("")){
			return;
		}
		
		CopyFrom(nhapctSelected);
		updateNhapct = true;
		
		// bao.ttc: gan bien Index vua nhan duoc
		cls_index = index;
		
		log.info("***********end nhapctAjax***********");

	}
	
	/**
	 * press SuaLai button
	 * @throws Exception
	 */
	public void sualai()throws Exception {
		log.info("*****Begin sualai() *****");
//		init() ;
		resetValue();
		resultHidden="";
	}
	// Ham chuyen chi tiet nhap xuong duoi
	public void enter() throws Exception {

		// 20110330 bao.ttc: comment vi da xet thanh toan hay chua khi load form
//		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
//		HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
//		if (hsttk_tmp != null){
//			FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
//			return ;
//		}
		
		if (this.ma.equals("")){
			resetValue();
			return;
		}
		
		if (this.soLuong == 0){
			resetValue();
			return;
		}
		
		//log.info("*****Begin Enter() *****");
		if (updateNhapct) {
			 updateRow();
		} else {
			insertRow();
		}
		resetValue();
		log.info("*****End Enter() *****");
	}
	
	public void selectCls(ClsKham clsKham){
		dsIn = "";
		try {
			clsKham = (ClsKham)BeanUtils.cloneBean(clsKham);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean check = mapSelect.get(clsKham);
		mapSelect.put(clsKham, check);
		Set<ClsKham> setCls = mapSelect.keySet(); 
		for (Iterator iterator = setCls.iterator(); iterator.hasNext();) {
			ClsKham clsKhamTmp = (ClsKham) iterator.next();
			log.info("clsKhamTmp.getClskhamMahang(true).getDtdmclsbgMaso() "+ clsKhamTmp.getClskhamMahang(true).getDtdmclsbgMaso());
			if (mapSelect.get(clsKhamTmp) == true){
				dsIn += "," + clsKhamTmp.getClskhamMahang(true).getDtdmclsbgMaso();
			}
		}
	}
	
	private void updateRow(){
		log.info("*****updateNhapct");
		
		// bao.ttc: CopyTo(nhapctSelected);
		ClsKham cls = new ClsKham();
		com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
		CopyTo(cls);
		
		/* bao.ttc: ko can kiem tra Index vi da truyen vao cls_index khi chon edit
		int i = listCtkq_B121_2.indexOf(cls);
		
		if (i < 0 || i >= listCtkq_B121_2.size()){
			insertRow();
		}else{
			log.info("****** Index i= " + i + " ******");
			
			listCtkq_B121_2.set(cls_index, cls);
		}
		*/
		
		//log.info("====== Index i= " + cls_index + " ======");
		//listCtkq_B121_2.remove(cls_index);
		//listCtkq_B121_2.add(cls_index, cls);
		mapSelect.remove(listCtkq_B121_2.get(cls_index));
		listCtkq_B121_2.set(cls_index, cls);
		mapSelect.put(cls, true);
		//log.info("====== listCtkq_B121_2: " + listCtkq_B121_2.size() + " ======");
		
		updateNhapct = false;
	}
	
	private void insertRow(){
		log.info("begin cache chi tiet ket qua");
		
		if ( thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa() != null && !thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP") ) {
			HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
			if (hsttk_tmp != null){
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				return;
			}
		}
		
		// 20110520 bao.ttc: check BN neu da chuyen vao noi tru thi khong cho chinh sua (dung chung bien "daThtoankham" de khong cho nhan Ghi nhan tren giao dien)
		if (thamkham.getTiepdonMa(true).getTiepdonChkhoa() != null && !thamkham.getTiepdonMa(true).getTiepdonChkhoa().equals("")){
			FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU);
			return;
		}
		
		ClsKham cls = new ClsKham();
		com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
		CopyTo(cls);
		

		listCtkq_B121_2.add(cls);
		mapSelect.put(cls, true);
		//log.info("listCtkq_B121_2:" + listCtkq_B121_2);
		//log.info("listCtkq_B121_2:" + listCtkq_B121_2.size());
	}

	//
//	public String capNhatKetQuaCLS(int index) throws ServiceException, RemoteException {
//		log.info("*****Begin capNhatKetQuaCLS()");
//		if (benhNhan.getBenhnhanHoten() == null || benhNhan.getBenhnhanHoten().equals("")){
//			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
//		
//			return "";
//		}
//		
//		if (thamkham.getThamkhamBankham() == null ||thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null|| thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")){
//			FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND);
//			
//			return "";
//		}
//
//		
//		maTiepDonOutToCapNhatKQCLS = thamkham.getTiepdonMa(true).getTiepdonMa();
//		maBanKhamOutToCapNhatKQCLS = thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
//		
//		
//		
//		initclsCapNhatTTCLS = null;
//		
//		ClsKham clsKham = listCtkq_B121_2.get(index);
//		
//		log.info("***** ma phieu : "+ clsKham.getClskhamMaphieu());
//		
//				
//		clsmaOut=clsKham.getClskhamMa();
//		khongQuaylaiTuCapNhatTTCLS = null;
//		
//		
//		ClsKham cls = new ClsKham();
//		com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
//		cls.setClskhamMa(0);
//		CopyTo(cls);		
//
//		listCtkq_B121_2.add(0,cls);
//		
//		log.info("*****End capNhatKetQuaCLS()");
//		return "capNhatKetQuaCLS";
//	}
	
	// Ham delete chi tiet
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		
		// 20110330 bao.ttc: comment vi da xet thanh toan hay chua khi load form
//		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
//		HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
//		if (hsttk_tmp != null){
//			FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
//			return ;
//		}
		
		ClsKham clsKham = listCtkq_B121_2.get(index);
		
		log.info("***** ma phieu : "+ clsKham.getClskhamMaphieu());
		
		
		if (clsKham.getClskhamMaphieu() != null && !clsKham.getClskhamMaphieu().equals("")){
			return;
		}
		
		mapSelect.remove(listCtkq_B121_2.get(index));
		listCtkq_B121_2.remove(index);
		updateNhapct = false;
		resetValue();
		log.info("*****End delete() *****");
		//System.out.println(listCLSChoose_B121_1.size());
		//System.out.println(listCLSChoose_B121_2.size());
	}

	// Ham ghi nhan
	public String ghinhan() throws Exception {
		
		dangGoiGhiNhan = true;
		
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		if ( thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa() != null && !thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP") ) {
			HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(thamkham.getTiepdonMa(true).getTiepdonMa());
			if (hsttk_tmp != null){
				if (hsttk_tmp.getHsthtoankDatt() != null && hsttk_tmp.getHsthtoankDatt()) {
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
					return null;
				}
			}
		}

		log.info("***** Begin Ghi nhan() CLS_Kham *****");
		
		// 20120510 bao.ttc: save user action log to database
		YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
		YteLog yteLog = new YteLog();
		String yteLogString = "";
		String yteListData = "";
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE_TIME);

		yteLog.setForm("B121_2_Canlamsangphauthuat");
		yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());

		if (thamkham != null) {
			yteLog.setObjectId(thamkham.getTiepdonMa() == null ? "NULL" : thamkham.getTiepdonMa(true).getTiepdonMa());
			yteLogString += "BK:" + (thamkham.getThamkhamBankham() == null ? "NULL" : thamkham.getThamkhamBankham(true).getDtdmbankhamMa())
						+ " - TK:" + (thamkham.getThamkhamMa());
		}
		
		try {
			
			ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
			
			for (int i = 0; i < listCtkq_B121_2.size(); i++) {
				RemoveUtil.setAllNullForClsKham(listCtkq_B121_2.get(i));
				//thanhdo add here
				listCtkq_B121_2.get(i).setClskhamNgaygio(Calendar.getInstance().getTime());
				listCtkq_B121_2.get(i).setClskhamThamkham(thamkham);
			}

			clsKhamDelegate.createClsKhamForCLSPhauThuat(listCtkq_B121_2, thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamBankham().getDtdmbankhamMa());
			
			
			// 20111220 bao.ttc: thay doi cach search vi DB Oracle xem chuoi rong "" la NULL, khong phu hop dung ham findByAllConditions
			// List<ClsKham> listClsKham = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKham", "clskhamThamkham", "clskhamMaphieu",thamkham.getThamkhamMa()+"","");
			List<ClsKham> listClsKham = (List<ClsKham>)clsKhamDelegate.findByBanKhamVaMaTiepDon(thamkham.getThamkhamBankham(true).getDtdmbankhamMa(),thamkham.getTiepdonMa(true).getTiepdonMa());
			// thanhpham add 20/12/2010				
			try {
			if(IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")){
				System.out.println("KET_NOI_MAY_XET_NGHIEM");
				String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			    //String url = "jdbc:sqlserver://127.0.0.1\\test:1433;databaseName=test";
				//String username = "sa";
			    //String password = "123456";
				String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + ":" + IConstantsRes.LABCONN_PORT + ";useUnicode=true;characterEncoding=UTF-8;" + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
			    String username = IConstantsRes.LABCONN_USERNAME;
			    String password = IConstantsRes.LABCONN_PASSWORD;
			    Class.forName(driver); // load sqlserver driver
			    Connection conn = DriverManager.getConnection(url, username, password);		    
			    //System.out.println("conn");
			    
				
				if (listClsKham != null && listClsKham.size() > 0) {
					
					for (int i = 0; i < listClsKham.size(); i++) {
						if(listClsKham.get(i).getClskhamTh() == null || listClsKham.get(i).getClskhamTh() == false){
							List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen",listClsKham.get(i).getClskhamMa()+"","");
							for (int j = 0; j < listClsKetQua.size(); j++) {	
								String OrderID = thamkham.getTiepdonMa().getTiepdonMa();
								DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");						
								String DateOrder = formatter.format(listClsKham.get(i).getClskhamNgaygio());					
								String Invoice = thamkham.getTiepdonMa().getTiepdonSothebh();
								String ObjectID = thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa();					
								String TestCodeHIS = listClsKetQua.get(j).getClskqMa();					
								String PID = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa();
								String PName = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten();	
								String Address="";
								if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))
									Address += thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi()+",";
								if(thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() !=null && !thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))
									Address += thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen()+",";
								if(thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))
									Address += thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen()+",";
								if(thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null && !thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))
									Address += thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();							
								String Age = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
								String Sex = "F";
								if(thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen().toLowerCase().equals("nam")){
									Sex = "M";
								}			
								String DoctorID = "a";
								if(thamkham.getThamkhamBacsi() != null){
									DoctorID = thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
								}
								String Diagnostic = "";
								if(thamkham.getBenhicd10() != null){
									Diagnostic = thamkham.getBenhicd10().getDmbenhicdTen();
								}
								String LocationID = "";
								if(listClsKham.get(i).getClskhamKhoa() != null){
									LocationID = listClsKham.get(i).getClskhamKhoa().getDmkhoaMa();
								}
								String query1 = " Select * from [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Order_HIS]";
								query1 += " where 1 = 1 ";
								query1 += " and OrderID = '" + OrderID + "' ";
								query1 += " and TestCodeHIS = '" + TestCodeHIS + "' ";
								PreparedStatement stmt1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
								ResultSet rs1 = stmt1.executeQuery(); 
								if(rs1 != null){
									if(rs1.next()){ 
										continue;
									}
									
								}
								String query = " INSERT INTO [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Order_HIS] ([OrderID],[DateOrder],[Invoice],[ObjectID],[TestCodeHIS],[PID],[PName],[Address],[Age],[Sex], [DoctorID],[Diagnostic], [LocationID]) " +
									           " VALUES "  +
									           " ('" + OrderID + "','" + DateOrder + "','" + Invoice + "','" + ObjectID + "','" + TestCodeHIS + "','" + PID + "',N'" + PName + "',N'" + Address + "','" + Age + "','" + Sex +"','" + DoctorID + "',N'" + Diagnostic + "','" + LocationID + "') ";
									           //" ('1a','2010-12-31','TP','XN032', 'Phạm đức thành', '1985', 'M') ";
								PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
								int num = stmt.executeUpdate();  
					            if (num == 1) {  
					                ResultSet rs = stmt.getGeneratedKeys();  
					                log.info("ResultSet: "+rs.toString());  
					                if(rs!=null){  
					                    while(rs.next()){  
					                    	ResultSetMetaData rsMetaData = rs.getMetaData();
					                        int columnCount = rsMetaData.getColumnCount();
			
					                        for (int k = 1; k <= columnCount; k++) {
					                          String key = rs.getString(k);
					                          log.info("key " + k + " is " + key);
					                        }
					                    }  
					                }  
					            }
					            //stmt.close();
							}
						}
					}
				}
					//conn.close();
				}
			
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("*************loi***********" + e.toString());
				
			}			
			
			// ================================================================
//			HsThtoank hsttk = new HsThtoank();
//			hsttk.setTiepdonMa(thamkham.getTiepdonMa());			
//			// tinhToanChoHSTKKham(hsttk);
//			HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(thamkham.getTiepdonMa());
//			hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
//			Utils.setInforForHsThToan(hsttk);
			// ================================================================
			
			// 20120702 bao.ttc: THREAD RUN de tang toc do xu ly
			HsttKhamThreadUtil capnhatHsttKham = new HsttKhamThreadUtil();
			capnhatHsttKham.setTiepDon(thamkham.getTiepdonMa());
			capnhatHsttKham.start();
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			resultHidden="success";
			
			yteListData = "CLS: ";
			// 20120510 bao.ttc: load lai CLS de co the In DS, luu thong tin vao NHAT KY HE THONG
			mapSelect = new HashMap<ClsKham, Boolean>();
			dsIn = "";
			if (listClsKham != null && listClsKham.size() > 0) {
				listCtkq_B121_2 = listClsKham;
			}
			// data for following action
			for (int i = 0; i < listCtkq_B121_2.size(); i++) {
				com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(listCtkq_B121_2.get(i));
				mapSelect.put(listCtkq_B121_2.get(i), true);
				dsIn += "," + listCtkq_B121_2.get(i).getClskhamMahang(true).getDtdmclsbgMaso();
				
				yteListData += (i+1) + "/ " + (listCtkq_B121_2.get(i).getClskhamMahang() == null ? "NULL" : listCtkq_B121_2.get(i).getClskhamMahang(true).getDtdmclsbgMa())
							+ "  " + sdf.format(listCtkq_B121_2.get(i).getClskhamNgaygio())
							+ "  SL:" + listCtkq_B121_2.get(i).getClskhamLan()
							+ "  Gia:" + listCtkq_B121_2.get(i).getClskhamDongia()
							+ " # ";
			}
			
			// 20120510 bao.ttc: save user action log to database
			yteLog.setLogString(yteLogString);
			yteLog.setListData(yteListData);
			yteLog.setDateTime(new Date());
			yteLogDele.create(yteLog);
			
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden="fail";
			// TODO: handle exception
			e.printStackTrace();
			log.error("*************loi***********" + e.toString());
		}
		
		log.info("***** End Ghi nhan() CLS_Kham *****");
		// backward = "return";
		//return "";
		
		// 20110225 bao.ttc: fix sau khi ghi nhan khong tro ve Tham kham va Xu tri de tiep tuc In phieu CLS
//		if (fromMenu == null || fromMenu.equals("")){
//			dangGoiGhiNhan = false;
//			return quaylai();
//		}
		
		dangGoiGhiNhan = false;
		return null;
	}
	
	public String quaylai()  throws Exception {
//		init();
		resetValue();
		resultHidden="";
		listCtkq_B121_2 = null;
		listCLSChoose_B121_1 = new java.util.ArrayList<ClsKham>();
		listCLSChoose_B121_2 = new java.util.ArrayList<ClsKham>();
		
		return "ghinhan";
	}

	public String getThoiGian() {
		return thoiGian;
	}

	

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa()
				.getBenhnhanNgaysinh() != null
				&& !thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa()
					.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		
		
		
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter
					.format(thamkham.getThamkhamNgaygio().getTime());
		}
				
		ngayBatDauBh = thamkham.getTiepdonMa().getTiepdonGiatri3(); // Ngay bat dau bao hiem tinh theo gia tri the tai benh vien (luu trong truong TIEPDON_GIATRI3 cua bang tiep_don)
		// Neu ngay bat dau theo gia tri tai benh vien bang null, thi lay theo ngay bat dau cua the
		if (ngayBatDauBh == null) {			
			ngayBatDauBh = thamkham.getTiepdonMa().getTiepdonGiatri1();
		}		
		// lay ngay het han cua the bao hiem
		ngayHetHanBh = thamkham.getTiepdonMa().getTiepdonGiatri4(); // Ngay het han bao hiem tinh theo gia tri the tai benh vien (luu trong truong TIEPDON_GIATRI4 cua bang tiep_don)
		// Neu ngay het han theo gia tri tai benh vien bang null, thi lay theo ngay het han cua the
		if (ngayHetHanBh == null) {
			ngayHetHanBh = thamkham.getTiepdonMa().getTiepdonGiatri2();
		}
		strNgayBatDauBh = (ngayBatDauBh == null ? "" : formatter.format(ngayBatDauBh));
		strNgayHetHanBh = (ngayHetHanBh == null ? "" : formatter.format(ngayHetHanBh));
	}


	public void setFieldIesVnIfNull(Object obj) throws Exception {
		java.lang.reflect.Field[] fList = obj.getClass().getDeclaredFields();
		for (java.lang.reflect.Field field : fList) {
			Class c = field.getType();
			// System.out.println(c.getCanonicalName());
			if (c.getCanonicalName().startsWith("com.iesvn")) {
				Constructor ccc = Class.forName(c.getCanonicalName())
						.getConstructors()[0];
				// System.out.println(ccc);
				field.set(obj, ccc.newInstance());
			}
		}
	}

	public void displayInfor() throws Exception {
		try {

		} catch (Exception e) {
			System.out.println("error on function displayInfor" + e);
		}

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

//	public static String getFORMAT_DATE() {
//		return FORMAT_DATE;
//	}
//
//	public static void setFORMAT_DATE(String format_date) {
//		FORMAT_DATE = format_date;
//	}
//
//	public static String getFORMAT_DATE_TIME() {
//		return FORMAT_DATE_TIME;
//	}
//
//	public static void setFORMAT_DATE_TIME(String format_date_time) {
//		FORMAT_DATE_TIME = format_date_time;
//	}

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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public java.util.List<ClsKham> getListCtkq_B121_2() {
		return listCtkq_B121_2;
	}

	public void setListCtkq_B121_2(java.util.List<ClsKham> listCtkq_B121_2) {
		this.listCtkq_B121_2 = listCtkq_B121_2;
	}
	
	public java.util.List<ClsKhamExt> getListCLSBanKhamTruoc() {
		return listCLSBanKhamTruoc;
	}

	public void setListCLSBanKhamTruoc(java.util.ArrayList<ClsKhamExt> listCLSBanKhamTruoc) {
		this.listCLSBanKhamTruoc = listCLSBanKhamTruoc;
	}
	
	public java.util.ArrayList<ClsKham> getListCLSChoose_B121_1() {
		return listCLSChoose_B121_1;
	}

	public void setListCLSChoose_B121_1(
			java.util.ArrayList<ClsKham> listCLSChooseB121_1) {
		listCLSChoose_B121_1 = listCLSChooseB121_1;
	}

	public java.util.ArrayList<ClsKham> getListCLSChoose_B121_2() {
		return listCLSChoose_B121_2;
	}

	public void setListCLSChoose_B121_2(
			java.util.ArrayList<ClsKham> listCLSChooseB121_2) {
		listCLSChoose_B121_2 = listCLSChooseB121_2;
	}

	public ClsKham getNhapctSelected() {
		return nhapctSelected;
	}

	public void setNhapctSelected(ClsKham nhapctSelected) {
		this.nhapctSelected = nhapctSelected;
	}

	public  boolean isUpdateNhapct() {
		return updateNhapct;
	}

	public  void setUpdateNhapct(boolean updateNhapct) {
		this.updateNhapct = updateNhapct;
	}

	public ThamKham getThamkham() {
		return thamkham;
	}

	public void setThamkham(ThamKham thamkham) {
		this.thamkham = thamkham;
	}

	public String getDiengiai() {
		return diengiai;
	}

	public void setDiengiai(String diengiai) {
		this.diengiai = diengiai;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	public Boolean getMien() {
		return mien;
	}

	public void setMien(Boolean mien) {
		this.mien = mien;
	}

	/*public Boolean getNgoaiDanhMuc() {
		return ngoaiDanhMuc;
	}

	public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc) {
		this.ngoaiDanhMuc = ngoaiDanhMuc;
	}*/

	public Boolean getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(Boolean yeuCau) {
		this.yeuCau = yeuCau;
	}
	
	public Boolean getKhongthu() {
		return khongthu;
	}

	public void setKhongthu(Boolean khongthu) {
		this.khongthu = khongthu;
	}

	public Boolean getKyThuatCao() {
		return kyThuatCao;
	}

	public void setKyThuatCao(Boolean kyThuatCao) {
		this.kyThuatCao = kyThuatCao;
	}

	public short getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(short soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public String getTenCLS() {
		return tenCLS;
	}

	public void setTenCLS(String tenCLS) {
		this.tenCLS = tenCLS;
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

	public String getGoToCLSThuThuat() {
		return goToCLSThuThuat;
	}

	public void setGoToCLSThuThuat(String goToCLSThuThuat) {
		this.goToCLSThuThuat = goToCLSThuThuat;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public Integer getMaSo() {
		return maSo;
	}

	public void setMaSo(Integer maSo) {
		this.maSo = maSo;
	}

	public String getKhoiBHYT() {
		return khoiBHYT;
	}

	public void setKhoiBHYT(String khoiBHYT) {
		this.khoiBHYT = khoiBHYT;
	}

	public String getVuotMoc1() {
		return vuotMoc1;
	}

	public void setVuotMoc1(String vuotMoc1) {
		this.vuotMoc1 = vuotMoc1;
	}

	public Boolean getDichVu() {
		return dichVu;
	}

	public void setDichVu(Boolean dichVu) {
		this.dichVu = dichVu;
	}

	public Boolean getNdm() {
		return ndm;
	}

	public void setNdm(Boolean ndm) {
		this.ndm = ndm;
	}

	public Integer getBacSiChiDinhMaSo() {
		return bacSiChiDinhMaSo;
	}

	public void setBacSiChiDinhMaSo(Integer bacSiChiDinhMaSo) {
		this.bacSiChiDinhMaSo = bacSiChiDinhMaSo;
	}

	public String getBacSiChiDinhMa() {
		return bacSiChiDinhMa;
	}

	public void setBacSiChiDinhMa(String bacSiChiDinhMa) {
		this.bacSiChiDinhMa = bacSiChiDinhMa;
	}

	
	public String getFromMenu() {
		return fromMenu;
	}
	public void setFromMenu(String fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	/* Thanh Add
	 * Ham hien thi 2 danh sach can lam san 
	 * 
	 * 
	 * */ 	
	public void displayCLS() throws Exception {
		//System.out.println("masokhoa  " + masoKhoa);
		DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();		
		ArrayList<DtDmClsBangGia> temp = (ArrayList<DtDmClsBangGia>) dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa((Integer)masoKhoa);
		
		listCLSChoose_B121_1 = new java.util.ArrayList<ClsKham>();
		listCLSChoose_B121_2 = new java.util.ArrayList<ClsKham>();
		
		if ( temp == null ) {
			temp = new ArrayList<DtDmClsBangGia>();
		}
		Collections.sort(temp, new Comparator<DtDmClsBangGia>() {
			public int compare(DtDmClsBangGia o1, DtDmClsBangGia o2) {
				String o1Ma2 = "";
				String o2Ma2 = "";
				if(o1.getDtdmclsbgMa2() == null){
					o1Ma2 = "0";
					return 1;
				}else{
					o1Ma2 = o1.getDtdmclsbgMa2();
				}
				if(o2.getDtdmclsbgMa2() == null){
					o2Ma2 = "0";
					return -1;
				}else{
					o2Ma2 = o2.getDtdmclsbgMa2();
				}
				if(Integer.parseInt(o1Ma2) < Integer.parseInt(o2Ma2) ){
					return -1;
				}else{
					return 1;
				}
				
			}
		});
		for(int i = 0; i < temp.size(); i++){									
			ClsKham cls = new ClsKham();		
			com.iesvn.yte.dieutri.setinfor.SetInforUtil.setInforIfNullForClsKham(cls);
			cls.setClskhamMahang(temp.get(i));
			
			//cls.getClskhamMahang().setDtdmclsbgMa(temp.get(i).getDtdmclsbgMa());
			//cls.getClskhamMahang().setDtdmclsbgMaso(temp.get(i).getDtdmclsbgMaso());
			//cls.getClskhamMahang().setDtdmclsbgDiengiai(temp.get(i).getDtdmclsbgDiengiai());
			cls.setClskhamDongia(temp.get(i).getDtdmclsbgDongia());
			cls.setClskhamLan(Short.parseShort("1"));
			cls.getClskhamKhoa().setDmkhoaMa(maKhoa);
			cls.setClskhamDongiabh(temp.get(i).getDtdmclsbgDongiabh());
			cls.setClskhamPhandv(temp.get(i).getDtdmclsbgPhandv());
			//System.out.println("maso " + temp.get(i).getDtdmclsbgMaso());
			//System.out.println("don gia " + temp.get(i).getDtdmclsbgDongia());
			//System.out.println("so luong" + cls.getClskhamLan());
			if ( i < temp.size()/2 ) {
				listCLSChoose_B121_1.add(cls);
			}else{
				listCLSChoose_B121_2.add(cls);
			}
		}
		
		//System.out.println("listCLSChoose_B121_1 size " + listCLSChoose_B121_1.size());
			
		
	}	
	
	/* Thanh Add
	 * Ham hien thi 2 danh sach can lam san 
	 * 
	 * 
	 * */ 	
	public void addListCLSChoose() throws Exception {
		log.info("Begin addListCLSChoose, maDT = " + thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa());
		System.out.println("addListCLSChoose " + listCLSChoose);		
		if(listCLSChoose != ""){	
			String maDT = thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
		String[] result = listCLSChoose.split(",");
		for (int i=0; i<result.length; i++){
			ClsKham cls = new ClsKham();
			System.out.println(listCLSChoose_B121_1.size());
			System.out.println(listCLSChoose_B121_2.size());
			for(ClsKham clsItem: listCLSChoose_B121_1){
				if(clsItem.getClskhamMahang().getDtdmclsbgMaso() == Integer.parseInt(result[i])){
					cls = clsItem;
					break;
				}
			}
			for(ClsKham clsItem: listCLSChoose_B121_2){
				if(clsItem.getClskhamMahang().getDtdmclsbgMaso() == Integer.parseInt(result[i])){
					cls = clsItem;
					break;
				}
			}
			cls.setClskhamLoai(cls.getClskhamMahang().getDtdmclsbgPhanloai().getDtdmclsMa());
			cls.setClskhamMien(cls.getClskhamMahang().getDtdmclsbgMien());
			cls.setClskhamNdm(cls.getClskhamMahang().getDtdmclsbgNDM());
			cls.setClskhamYeucau(yeuCau);
			cls.setClskhamKhongthu(khongthu);
			cls.setClskhamKtcao(cls.getClskhamMahang().getDtdmclsbgPhanloai().getDtdmclsMaso().intValue() == 9);  // 9 la ma so cua CLS Dich vu ky thuat cao
			cls.setClskhamDichvu(dichVu);
			cls.setClskhamMaloai(cls.getClskhamMahang().getDtdmclsbgPhanloai());
			cls.setClskhamLoai2(cls.getClskhamMahang().getDtdmclsbgLoai());
			cls.setClskhamSoml(cls.getClskhamMahang().getDmclsbgSoml());
			/* phuc.lc 30-12-2010 : begin fix bug 1911
			* Cach tinh don gia khi thuc hien CLS nhu sau (don gia nay se duoc luu vao truong don gia cua bang ClsKham)
			* + Doi voi doi tuong BH
			*		- Neu thuc hien CLS mien, thi don gia = don gia mien cua CLS 
			*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
			*		- Neu thuc hien CLS (trong danh muc), thi don gia = don gia bao hiem cua CLS
			*		- Neu thuc hien CLS (ngoai danh muc), thi don gia = don gia thuong cua CLS
			* + Doi voi doi tuong MP		 
			*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
			*		- Neu thuc hien CLS (khong yeu cau), thi don gia = don gia mien cua CLS
			* + Doi voi doi tuong TP
			*		- Neu thuc hien CLS thuong, thi don gia = don gia thuong cua CLS 
			*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
			*		- Neu thuc hien CLS mien phi, thi don gia = don gia mien cua CLS	
			*/
			if (khongthu) {
				cls.setClskhamDongia(new Double(0));
			} else if(yeuCau) {
			// Neu co chon yeu cau thi lay don gia yeu cau
			
				cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiayc());
			} else {
				// Neu khong yeu cau thi xet theo tung doi tuong		
				if (maDT.equalsIgnoreCase("BH")){
					SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
					Date curDate = formatter.parse(formatter.format(new Date()));
					if (cls.getClskhamMahang().getDtdmclsbgMien()){
						// CLS mien phi ==> lay gia mien phi
						cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
					} else if ((cls.getClskhamMahang().getDtdmclsbgNDM() != null && cls.getClskhamMahang().getDtdmclsbgNDM().booleanValue() == true)
							|| ngayBatDauBh == null || ngayHetHanBh == null 
							|| curDate.before(ngayBatDauBh) || curDate.after(ngayHetHanBh)){
						// CLS ngoai danh muc hoac het han bao hiem ==> lay gia thuong
						cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
					} else {
						// Cac truong hop con lai ==> lay gia bao hiem
						cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiabh());
					}
				} else if (maDT.equalsIgnoreCase("MP")) {
					// Doi tuong mien phi neu khong yeu cau thi luon luon lay gia mien phi
					cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
				} else if (maDT.equalsIgnoreCase("TP")) {
					if (cls.getClskhamMahang().getDtdmclsbgMien()){
						// CLS mien phi ==> lay gia mien phi
						cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
					} else {
						cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
					}
				}
			}
			/* phuc.lc 30-12-2010 : end fix bug 1911 */
			if(listCtkq_B121_2 == null) 
				listCtkq_B121_2 = new ArrayList<ClsKham>();
			ClsKham clsTmp = (ClsKham)BeanUtils.cloneBean(cls);
			listCtkq_B121_2.add(clsTmp);
		}
		System.out.println("test " + listCtkq_B121_2.size());
		}
	}
	
	public void showCLSBanKhamTruoc(){
		log.info("Begin showCLSBanKhamTruoc");
		listCLSBanKhamTruoc = new ArrayList<ClsKhamExt>();
		ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
		if(thamkham != null){
			List<ClsKham> clsKhams = thamkhamDel.getListClsBanKhamTruoc(thamkham.getTiepdonMa().getTiepdonMa(), thamkham.getThamkhamMa());
			for(ClsKham cls:clsKhams){
				ClsKhamExt clsExt = new ClsKhamExt();
				clsExt.setCls(cls);
				clsExt.setBankhamTen(cls.getClskhamThamkham().getThamkhamBankham(true).getDtdmbankhamTen());
				listCLSBanKhamTruoc.add(clsExt);
			}
		}		
		log.info("End showCLSBanKhamTruoc");
	}

	public Double getDonGiaBH() {
		return donGiaBH;
	}

	public void setDonGiaBH(Double donGiaBH) {
		this.donGiaBH = donGiaBH;
	}

	public Double getPhanDV() {
		return phanDV;
	}

	public void setPhanDV(Double phanDV) {
		this.phanDV = phanDV;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public boolean isDaThanhtoan() {
		return daThanhtoan;
	}

	public void setDaThanhtoan(boolean daThanhtoan) {
		this.daThanhtoan = daThanhtoan;
	}

	public String getStrNgayBatDauBh() {
		return strNgayBatDauBh;
	}

	public void setStrNgayBatDauBh(String strNgayBatDauBh) {
		this.strNgayBatDauBh = strNgayBatDauBh;
	}

	public String getStrNgayHetHanBh() {
		return strNgayHetHanBh;
	}

	public void setStrNgayHetHanBh(String strNgayHetHanBh) {
		this.strNgayHetHanBh = strNgayHetHanBh;
	}

	public String getKhoaCDHA() {
		return khoaCDHA;
	}

	public void setKhoaCDHA(String khoaCDHA) {
		this.khoaCDHA = khoaCDHA;
	}
	
	public class ClsKhamExt{
		ClsKham cls;
		String bankhamTen;
		public ClsKhamExt(){}
		public ClsKhamExt(ClsKham cls, String bankhamTen){
			this.cls = cls;
			this.bankhamTen = bankhamTen;
		}
		public ClsKham getCls(boolean created){
			if(created){
				cls = new ClsKham();
			}
			return cls;
		}
		public ClsKham getCls(){
			return cls;
		}
		public void setCls(ClsKham cls){
			this.cls = cls;
		}
		public String getBankhamTen(){
			return bankhamTen;
		}
		public void setBankhamTen(String bankhamTen){
			this.bankhamTen = bankhamTen;
		}
	}

	public Map<ClsKham, Boolean> getMapSelect() {
		return mapSelect;
	}

	public void setMapSelect(Map<ClsKham, Boolean> mapSelect) {
		this.mapSelect = mapSelect;
	}
	
	
}


