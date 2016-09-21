package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
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
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaGiayTomTatDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaGiayTomTat;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B233_Tomtatbenhan")
@Synchronized(timeout = 6000000)
public class TomTatBenhAnAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(TomTatBenhAnAction.class);
	
	private String soVaoVien;
	private String ngayHt;
	private HsbaGiayTomTat hsbaTt;
	private Hsba hsba;
	private String maGiay;
	private String ngaySinh;
	private String ngayYc;
	private String gioVaoVien;
	private String ngayVaoVien;
	private String gioRaVien;
	private String ngayRaVien;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT = null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null; 
	
	private int index=0;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	private Boolean hienThiInPhieu = false;	
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strTomtatBA;
	//@Factory("strTomtatBA")
	@Begin (join = true)
	public String init() {
	
		logger.info("-----init()-----");
		reset();
		return "DieuTri_LapVanBanTheoMau_TomTatBenhAn";
	}
	@End
	public void endFunc(){
		
	}
	public void reset() {
		logger.info("-----reset()-----");
		ngayHt = Utils.getCurrentDate();
		hsbaTt = new HsbaGiayTomTat();
		hsba = new Hsba();
		ngaySinh = "";
		ngayYc = "";
		gioVaoVien = "";
		ngayVaoVien = "";
		ngayRaVien = "";
		gioRaVien = "";
		soVaoVien = "";
		maGiay = "";
		hienThiInPhieu = false;
		strTomtatBA = "";
	}
	
	public void loadHsba() {
		logger.info("-----loadHsba()-----");
		if (!soVaoVien.equals("")) {
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			try {
				hsba = hsbaDelegate.find(soVaoVien);
				if (hsba != null) {
					soVaoVien = hsba.getHsbaSovaovien();
					Date dNgaySinh = hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
					if (dNgaySinh != null) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						ngaySinh = df.format(dNgaySinh);
					}
					Date dNgayVv = hsba.getHsbaNgaygiovaov();
					if (dNgayVv != null) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						ngayVaoVien = df.format(dNgayVv);
						gioVaoVien = Utils.getGioPhut(dNgayVv);
					}
					Date dNgayRv = hsba.getHsbaNgaygiorav();
					if (dNgayRv != null) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						ngayRaVien = df.format(dNgayRv);
						gioRaVien = Utils.getGioPhut(dNgayRv);
					}
					//hsbaTt = HsbaGiayTomTatDelegate.getInstance().findBySovaovien(soVaoVien);
					//if(hsbaTt == null) {
					//	hienThiInPhieu = false;
					//	hsbaTt = new HsbaGiayTomTat();
						// Khoi tao gia tri Xet nghiem can lam sang va Qua trinh dieu tri 
						// Lay danh sach ClsMo theo so vao vien
						List<ClsMo> listClsMo = ClsMoDelegate.getInstance().findBySoVaoVien(soVaoVien);
						//logger.info("listClsMo size = " + listClsMo.size());
						// Lay danh sach thuoc noi tru
						List<ThuocNoiTru> listTnt = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(soVaoVien);
						//logger.info("listTnt size = " + listTnt.size());
						String strXnCls = "";
						String maCls;
						if (listClsMo != null) {
							for(ClsMo eachClsMo : listClsMo) {
								maCls = eachClsMo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
								if(maCls.equals("XN") || maCls.equals("CD") || maCls.equals("TT") || maCls.equals("PT")) {
									strXnCls += (strXnCls.trim().length() > 0 ? ", " + eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai() : eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai());
								}
							}
						}
						//logger.info("strXnCls = " + strXnCls);
						String strQtdt = "";
						if (listTnt != null) {
							String tenDvt = "";							
							for(ThuocNoiTru eachTnt : listTnt) {
								tenDvt = eachTnt.getThuocnoitruMathuoc().getDmdonvitinhMaso() == null ? "" : eachTnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
								strQtdt += (strQtdt.trim().length() > 0) ? ", " + eachTnt.getThuocnoitruMathuoc().getDmthuocTen() + "(" + eachTnt.getThuocnoitruSoluong() + " " + tenDvt + ")" :
									eachTnt.getThuocnoitruMathuoc().getDmthuocTen() + "(" + eachTnt.getThuocnoitruSoluong() + " " + tenDvt + ")" ;
							}
						}
						//logger.info("strQtdt 1 = " + strQtdt);
						// Hien thi cac CLS thuoc nhom mau trong truong Qua trinh dieu tri
						if (listClsMo != null) {
							for(ClsMo eachClsMo : listClsMo) {
								maCls = eachClsMo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
								if(maCls.equals("MA")) {
									strQtdt += (strQtdt.trim().length() > 0 ? ", " + eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai() : eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai());
								}
							}
						}
						
						HsbaGiayTomTat gtt_tmp = HsbaGiayTomTatDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
						if (gtt_tmp!= null){
							hsbaTt = gtt_tmp;
							
							Date dNgayYc = hsbaTt.getHsbagttNgayyc();
							if (dNgayYc != null) {
								SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								ngayYc = df.format(dNgayYc);
							}
						}
						
						if (strXnCls.length() > 2039) {
							strXnCls = strXnCls.substring(0, 2038);
						}
						
						if (strQtdt.length() > 2039) {
							strQtdt = strQtdt.substring(0, 2038);
						}
						
						hsbaTt.setHsbagttXncls(strXnCls);
						hsbaTt.setHsbagttQtdt(strQtdt);
					//} else {
					//	hienThiInPhieu = true;
						maGiay = hsbaTt.getHsbagiaytomtatMa();
					//}
				} else {
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
					ngaySinh = "";
					ngayYc = "";
					gioVaoVien = "";
					ngayVaoVien = "";
					ngayRaVien = "";
					gioRaVien = "";
					hsbaTt = new HsbaGiayTomTat();
				}
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, soVaoVien);
				e.printStackTrace();
				ngaySinh = "";
				ngayYc = "";
				gioVaoVien = "";
				ngayVaoVien = "";
				ngayRaVien = "";
				gioRaVien = "";
				hsbaTt = new HsbaGiayTomTat();
			}
		}
		
	}
	
	public void loadHsbaTomTat() {
		HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();
		try {
			hsbaTt = hsbattDelegate.findByMa(maGiay);
			if (hsbaTt == null) {
				FacesMessages.instance().add(IConstantsRes.no_found, maGiay);
				reset();
			} else {
				maGiay = hsbaTt.getHsbagiaytomtatMa();
				hsba = hsbaTt.getHsbaSovaovien(true);
				soVaoVien = hsba.getHsbaSovaovien();
				Date dNgaySinh = hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
				if (dNgaySinh != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngaySinh = df.format(dNgaySinh);
				}
				Date dNgayVv = hsba.getHsbaNgaygiovaov();
				if (dNgayVv != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayVaoVien = df.format(dNgayVv);
					gioVaoVien = Utils.getGioPhut(dNgayVv);
				}
				Date dNgayRv = hsba.getHsbaNgaygiorav();
				if (dNgayRv != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayRaVien = df.format(dNgayRv);
					gioRaVien = Utils.getGioPhut(dNgayRv);
				}
				Date dNgayYc = hsbaTt.getHsbagttNgayyc();
				if (dNgayYc != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayYc = df.format(dNgayYc);
				}
				hienThiInPhieu = true;
			}
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maGiay);
			reset();
			e.printStackTrace();
		}
	}
	
	public void end() {
		logger.info("-----end()-----");
		HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();
		/*HsbaGiayTomTat hsbaTtTmp;
		if(maGiay.trim().length() > 0) {
			hsbaTtTmp = hsbattDelegate.findByMa(maGiay);
			if (hsbaTtTmp == null) {
				FacesMessages.instance().add(IConstantsRes.no_found, maGiay);
				reset();
				return;
			} else {
				logger.info("tt sovaovien = " + hsbaTtTmp.getHsbaSovaovien().getHsbaSovaovien() + "; UI so vao vien = " + soVaoVien);
				if (!hsbaTtTmp.getHsbaSovaovien().getHsbaSovaovien().equals(soVaoVien)) {
					return;
				}
			}
		}
		
		hsbaTtTmp = hsbattDelegate.findBySovaovien(soVaoVien);
		if (hsbaTtTmp != null) {
			hsbaTt.setHsbagiaytomtatMa(hsbaTtTmp.getHsbagiaytomtatMa());
			maGiay = hsbaTtTmp.getHsbagiaytomtatMa();
		}
		*/
		hsbaTt.setHsbaSovaovien(hsba);
		try {
			hsbaTt.setHsbagttNgaygiocn(new Date());
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (!ngayYc.equals("")) {
				hsbaTt.setHsbagttNgayyc(df.parse(ngayYc));
			} else {
				hsbaTt.setHsbagttNgayyc(new Date());
			}
			if (maGiay.equals("")) {
				maGiay = hsbattDelegate.create(hsbaTt);
			} else {
				hsbattDelegate.edit(hsbaTt);
				maGiay = hsbaTt.getHsbagiaytomtatMa();
			}
			
			FacesMessages.instance().add(IConstantsRes.SUCCESS);
			hienThiInPhieu = true;
		} catch (Exception e) {
			FacesMessages.instance().add(IConstantsRes.FAIL);
			e.printStackTrace();
		}
	}
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		
		// 20110831 bao.ttc: load lai theo ma giay vi hsbaTT tren form vua ghi nhan xuong van chua co Ma so
		HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();
		hsbaTt = hsbattDelegate.findByMa(maGiay);
		if (hsbaTt == null) {
			FacesMessages.instance().add(IConstantsRes.no_found, maGiay);
			reset();
			return;
		}
		
		loaiBCDT="Giaytomtatbenhan";
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Giaytomtatbenhan.jrxml";
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			String khoa = hsbaTt.getHsbaSovaovien().getHsbaKhoadangdt(true).getDmkhoaTen();
			String hoTen = hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanHoten();
			//String tuoi = hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanTuoi().toString();
			
			String iTuoi = "";
			if(hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanTuoi() != null){
				iTuoi = hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanTuoi().toString();
			}
			int iDonviTuoi = 1;
			if(hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanDonvituoi() != null){
				iDonviTuoi = hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanDonvituoi();
			}
			String sDonViTuoi = "";
			if (iDonviTuoi == 1) {
				sDonViTuoi = "";
				iTuoi = iTuoi + "";
			} else if (iDonviTuoi == 2) {
				sDonViTuoi = IConstantsRes.THANG;
				iTuoi = iTuoi + "  " + sDonViTuoi;
			
			} else if (iDonviTuoi == 3) {
				sDonViTuoi = IConstantsRes.NGAY;
				iTuoi = iTuoi + "  " +  sDonViTuoi;
			}
			
			String diaChi = "";
			if(hsbaTt.getHsbaSovaovien().getBenhnhanMa() != null) {
				diaChi = (hsbaTt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDiachi() == null ? "" : hsbaTt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDiachi());
				if(hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa() != null) {
					diaChi += (hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa().getDmxaTen() == null ? "" : ", " + hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa().getDmxaTen());
				}
				if(hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa() != null) {
					diaChi += (hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa().getDmhuyenTen() == null ? "" : ", " + hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa().getDmhuyenTen());
				}
				if(hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa() != null) {
					diaChi += (hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa().getDmtinhTen() == null ? "" : ", " + hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa().getDmtinhTen());
				}
			}
			
			String gioiTinh = hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen();
			String chuanDoan = hsbaTt.getHsbaSovaovien().getHsbaMachdoanbd(true).getDmbenhicdTen();
			String soVaoVien = hsbaTt.getHsbaSovaovien().getHsbaSovaovien();
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("khoa", khoa);
			params.put("hoTen", hoTen);
			
			params.put("tuoi",iTuoi);
			params.put("diaChi", diaChi);
			params.put("gioiTinh", gioiTinh);
			params.put("chuanDoan", chuanDoan);
			params.put("soVaoVien", soVaoVien);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			params.put("hsbatt_maso", hsbaTt.getHsbagiaytomtatMaso());
			
			HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
			HsbaChuyenMon hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(hsbaTt.getHsbaSovaovien(true).getHsbaSovaovien(), hsbaTt.getHsbaSovaovien(true).getHsbaKhoadangdtCm(true).getDmkhoaMa());
			if (hoSoBenhAnCM_temp != null){
				params.put("SOGIUONG", hoSoBenhAnCM_temp.getHsbacmSogiuong());
				params.put("SOPHONG", hoSoBenhAnCM_temp.getHsbacmSobuong());
			}
			
			
			logger.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    logger.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	logger.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","Giaytomtatbenhan");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    logger.info("duong dan file xuat report :" + baocao1);
			    logger.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    logger.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    logger.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    logger.info("Thoat Method XuatReport");
	}
	
	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}

	public HsbaGiayTomTat getHsbaTt() {
		return hsbaTt;
	}

	public void setHsbaTt(HsbaGiayTomTat hsbaTt) {
		this.hsbaTt = hsbaTt;
	}

	public Hsba getHsba() {
		return hsba;
	}

	public void setHsba(Hsba hsba) {
		this.hsba = hsba;
	}

	public String getMaGiay() {
		return maGiay;
	}

	public void setMaGiay(String maGiay) {
		this.maGiay = maGiay;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioVaoVien() {
		return gioVaoVien;
	}

	public void setGioVaoVien(String gioVaoVien) {
		this.gioVaoVien = gioVaoVien;
	}

	public String getNgayVaoVien() {
		return ngayVaoVien;
	}

	public void setNgayVaoVien(String ngayVaoVien) {
		this.ngayVaoVien = ngayVaoVien;
	}

	public String getNgayRaVien() {
		return ngayRaVien;
	}

	public void setNgayRaVien(String ngayRaVien) {
		this.ngayRaVien = ngayRaVien;
	}

	public String getGioRaVien() {
		return gioRaVien;
	}

	public void setGioRaVien(String gioRaVien) {
		this.gioRaVien = gioRaVien;
	}

	public String getNgayYc() {
		return ngayYc;
	}

	public void setNgayYc(String ngayYc) {
		this.ngayYc = ngayYc;
	}

	public String getSoVaoVien() {
		return soVaoVien;
	}

	public void setSoVaoVien(String soVaoVien) {
		this.soVaoVien = soVaoVien;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public Boolean getHienThiInPhieu() {
		return hienThiInPhieu;
	}
	public void setHienThiInPhieu(Boolean hienThiInPhieu) {
		this.hienThiInPhieu = hienThiInPhieu;
	}
}
