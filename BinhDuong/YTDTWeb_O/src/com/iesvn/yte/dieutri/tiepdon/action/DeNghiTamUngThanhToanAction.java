package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B135_Denghitamungthanhtoan")
@Synchronized(timeout = 6000000)
public class DeNghiTamUngThanhToanAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(DeNghiTamUngThanhToanAction.class);
	
	private String ngayHt;
	private String gioHt;
	private String soTien;
	private String maBanKham;
	private String maTiepDon;
	private String tamUngThToan;
	private TiepDon td;
	private ThamKham thamKham;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@Begin (join = true)
	public String init() {
		logger.info("-----init()-----");
		reset();
		return "TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan";
	}
	@End
	public void end(){
		
	}
	@Factory("resetFormB135")
	public void initresetFormB145() {
		
		reset();
	}
	
	public void reset() {
		logger.info("-----reset()-----");
		ngayHt = Utils.getCurrentDate();
		gioHt = Utils.getGioPhut(new Date());
		maBanKham = IConstantsRes.PHONGKHAMCC_DEFAULT;
		maTiepDon = "";
		tamUngThToan = "0";
		soTien = "0";
		td = new TiepDon();
		thamKham = new ThamKham();
	}
	
	public void loadTiepDon() {
		logger.info("-----loadTiepDon()-----");
		
		if ("".equals(maTiepDon) || "".equals(maBanKham)) {
			
		} else {
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			try {
				thamKham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKham, maTiepDon);
				if (thamKham != null) {
					td = thamKham.getTiepdonMa();
					maTiepDon = td.getTiepdonMa();
				} else {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, 
							"b\u1EC7nh nh\u00E2n", "b\u00E0n kh\u00E1m " + maBanKham); //bệnh nhân  //bàn khám //
				}
			} catch (Exception e) {
				logger.error(e);
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, 
						"b\u1EC7nh nh\u00E2n", "b\u00E0n kh\u00E1m " + maBanKham);
				reset();
			}
		}
	}
	
	public String thuchienAction(){
		XuatReport();
		resetFormB135=null;
		return "B160_Chonmenuxuattaptin";
	}
	/*
	public String inPhieu(String loaiFile) {
		logger.info(String.format("-----inPhieu()-----"));
		logger.info(String.format("-----loai file: %s", loaiFile));
		Date currentDate = new Date();
		String pathTemplate = "B135_Denghitamungthanhtoan";
		
		if (!maTiepDon.equals("") || !soTien.equals("")) {
			try {
				
				logger.info(String.format("-----pathTemplate: %s", pathTemplate));
				Map<String, Object> params = new HashMap<String, Object>();
				// saveSum(tuNgay, denNgay, donViMa);
				params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
				params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
				params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
				logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
				
				params.put("ngayDeNghi", ngayHt);
				params.put("banKham", thamKham.getThamkhamChbankham().getDtdmbankhamTen());
				params.put("maTiepDon", maTiepDon);
				params.put("hoTenBenhNhan", td.getBenhnhanMa().getBenhnhanTuoi());
				params.put("diaChi", td.getBenhnhanMa().getBenhnhanDiachi());
				params.put("danToc", td.getBenhnhanMa().getDantocMa().getDmdantocTen());
				params.put("kcb", td.getKcbbhytMa(true).getDtdmkcbbhytTen());
				params.put("tuyenBH", td.getTiepdonTuyen());
				params.put("bnTra", hsttk.getHsthtoankBntra());
				params.put("soTien", soTien);
				
				String fileNameExt = String.valueOf(currentDate.getTime());
				String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
															IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
															IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "vienphi/",
															pathTemplate , params, loaiFile, fileNameExt);
				
		        if(loaiFile.equalsIgnoreCase("HTML")) {
	            	setResultReportName(fileName);
	            } else {
	            	setResultReportFileName(fileName);
	            }
	            setIsReport("true");
			} catch (Exception ex) {
				logger.error(String.format("-----Error: %s", ex.toString()));
			}
		}

		return "B135_Chonmenuxuattaptin";
	}
	*/
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD = null;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB135=null;
	
	int index = 0;
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		reportTypeTD="B135_Denghitamungthanhtoan";
		logger.info("Vao Method XuatReport B135_Denghitamungthanhtoan");
		String baocao1=null;
		String pathTemplate = null;
		try {
			//logger.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/B135_Denghitamungthanhtoan.jrxml";
			logger.info("da thay file templete" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			//logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			//logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			//logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("loaidenghi", tamUngThToan);
			params.put("ngayDeNghi", ngayHt);
			
			params.put("maTiepDon", maTiepDon);
			params.put("TEN", td.getBenhnhanMa(true).getBenhnhanHoten());
			
			String diachistr = "";
			if(td.getBenhnhanMa(true).getBenhnhanDiachi() != null && !td.getBenhnhanMa(true).getBenhnhanDiachi().equals("") )
				diachistr += td.getBenhnhanMa(true).getBenhnhanDiachi()+",";
			if(td.getBenhnhanMa(true).getXaMa(true).getDmxaTen() !=null && !td.getBenhnhanMa(true).getXaMa(true).getDmxaTen().equals(""))
				diachistr += td.getBenhnhanMa(true).getXaMa(true).getDmxaTen()+",";
			if(td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null && !td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen().equals(""))
				diachistr += td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen()+",";
			if(td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null && !td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen().equals(""))
				diachistr += td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			params.put("DANTOC", td.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen());
			params.put("KCB", td.getKcbbhytMa(true).getDmbenhvienTen());
			if (td.getTiepdonTuyen() == null) {
				params.put("TUYEN", "");
			} else {
				params.put("TUYEN", String.valueOf( td.getTiepdonTuyen()));
			}
			params.put("GIOITINH", td.getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());
			
			Double soTienTmp = null;
			try{
				soTienTmp = new Double(soTien);
			}catch(Exception e){
				soTienTmp = new Double(0);
			}
			params.put("SOTIEN", soTienTmp);
			params.put("TIENBANGCHU", Utils.NumberToString(soTienTmp));
			
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintTD =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","B135_Denghitamungthanhtoan");
			    reportPathTD=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    //logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+reportPathTD);
			    index+=1;
			    //logger.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    logger.info("Thoat Method XuatReport");
	}
	
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "B135_Denghitamungthanhtoan";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getGioHt() {
		return gioHt;
	}

	public void setGioHt(String gioHt) {
		this.gioHt = gioHt;
	}

	public String getMaBanKham() {
		return maBanKham;
	}

	public void setMaBanKham(String maBanKham) {
		this.maBanKham = maBanKham;
	}

	public String getMaTiepDon() {
		return maTiepDon;
	}

	public void setMaTiepDon(String maTiepDon) {
		this.maTiepDon = maTiepDon;
	}

	public TiepDon getTd() {
		return td;
	}

	public void setTd(TiepDon td) {
		this.td = td;
	}

	public String getSoTien() {
		return soTien;
	}

	public void setSoTien(String soTien) {
		this.soTien = soTien;
	}

	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public ThamKham getThamKham() {
		return thamKham;
	}

	public void setThamKham(ThamKham thamKham) {
		this.thamKham = thamKham;
	}

	public String getTamUngThToan() {
		return tamUngThToan;
	}

	public void setTamUngThToan(String tamUngThToan) {
		this.tamUngThToan = tamUngThToan;
	}

}
