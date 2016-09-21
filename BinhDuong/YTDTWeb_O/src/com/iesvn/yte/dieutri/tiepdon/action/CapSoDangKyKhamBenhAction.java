package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B112_Capsodangkykhambenh")
@Synchronized(timeout = 6000000)
public class CapSoDangKyKhamBenhAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(CapSoDangKyKhamBenhAction.class);
	
	private String ngayHt;
	private String gioHt;
	private String maBn;
	private BenhNhan bn;
	private DmDoiTuong dt;
//	private DtDmTienKham tk;
	private DtDmBanKham banKham;
	private String stt;
	private String bnTra;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@Create
	public void init() {
		logger.info("-----init()-----");
		reset();
	}
	
	public void reset() {
		logger.info("-----reset()-----");
		ngayHt = Utils.getCurrentDate();
		gioHt = Utils.getGioPhut(new Date());
		bn = new BenhNhan();
		dt = new DmDoiTuong();
//		tk = new DtDmTienKham();
		banKham = new DtDmBanKham();
		maBn = "";
		bnTra = "";
	}
	
	public void loadBenhNhan() {
		logger.info("-----loadBenhNhan()-----");
		if (!maBn.equals("") || !banKham.getDtdmbankhamMa().equals("")) {
			BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
			bn = bnDelegate.find(maBn);
			if (bn == null) {
				maBn = "";
			} else {
				maBn = bn.getBenhnhanMa();
//				DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
//				stt = dtDelegate.getSoKhamBenh(banKham.getDtdmbankhamMa()).toString();
			}
		}
	}
	
	public String inPhieu(String loaiFile) {
		logger.info(String.format("-----inPhieu()-----"));
		logger.info(String.format("-----loai file: %s", loaiFile));
		String pathTemplate = "B112_Capsodangkykhambenh";
		
		if (bn != null || !stt.equals("")) {
			try {
				
				logger.info(String.format("-----pathTemplate: %s", pathTemplate));
				Map<String, Object> params = new HashMap<String, Object>();
				
				DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
//				tk = (DtDmTienKham) dtDelegate.findByMa(tk.getDtdmtienkhamMa(), 
//						"DtDmTienKham", "dtdmtienkhamMa");
				banKham = (DtDmBanKham) dtDelegate.findByMa(banKham.getDtdmbankhamMa(), 
						"DtDmBanKham", "dtdmbankhamMa");
				
				params.put("doiTuong", dt.getDmdoituongMa());
				params.put("stt", stt);
				params.put("gioHt", gioHt);
				params.put("ngayHt", ngayHt);
//				params.put("loaiKham", tk.getDtdmtienkhamTen());
//				params.put("tienKham", tk.getDtdmtienkhamDongia().toString());
				params.put("maBn", maBn);
				params.put("tenBn", bn.getBenhnhanHoten());
				params.put("banKham", banKham.getDtdmbankhamTen());
				
				Date now = new Date();
				String fileNameExt = String.valueOf(now.getTime());
				String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
															IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
															IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "tiepdon/",
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
		
		return "/B1_Tiepdon/B112_Chonmenuxuattaptin.xhtml";
	}
	
	public String troVe(){
		try {
			logger.info("***** troVe()");
			return "/B1_Tiepdon/B112_Capsodangkykhambenh.xhtml";
		} 		
		catch (Exception e) {
			logger.info("***** End exception = " + e);    	
		} 
		return null;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setGioHt(String gioHt) {
		this.gioHt = gioHt;
	}

	public String getGioHt() {
		return gioHt;
	}

	public void setBn(BenhNhan bn) {
		this.bn = bn;
	}

	public BenhNhan getBn() {
		return bn;
	}

	public void setMaBn(String maBn) {
		this.maBn = maBn;
	}

	public String getMaBn() {
		return maBn;
	}

	public void setDt(DmDoiTuong dt) {
		this.dt = dt;
	}

	public DmDoiTuong getDt() {
		return dt;
	}

//	public void setTk(DtDmTienKham tk) {
//		this.tk = tk;
//	}
//
//	public DtDmTienKham getTk() {
//		return tk;
//	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getStt() {
		return stt;
	}

	public void setBanKham(DtDmBanKham banKham) {
		this.banKham = banKham;
	}

	public DtDmBanKham getBanKham() {
		return banKham;
	}

	public String getBnTra() {
		return bnTra;
	}

	public void setBnTra(String bnTra) {
		this.bnTra = bnTra;
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
}