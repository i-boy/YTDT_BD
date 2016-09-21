package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
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
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;


@Scope(CONVERSATION)
@Name("B235_Giaychuyenvien")
@Synchronized(timeout = 6000000)
public class GiayChuyenVienAction implements Serializable{

	private static final long serialVersionUID = 8711728630888663422L;
	private static Logger log = Logger.getLogger(GiayChuyenVienAction.class);
	
	private HsbaChuyenVien gcv;
	private String nnTru;
	private String ngayCap;
	private String maBATD;
	private String ngayYc;
	private HsbaChuyenMon cm;
	
	private boolean isUpdate;
	
	private String nosuccess;
	private String nofound;
	private String nofoundHSBA;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	
	private String ngayChuyenVien;
	private String giatrithe_tungay;
	private String giatrithe_denngay;
	private String dieutri1Tungay;
	private String dieutri1Denngay;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	
	@Begin (join = true)
	public String init() {
	
		resetValue();		
		return "DieuTri_LapVanBanTheoMau_GiayChuyenVien";
	}

	@End
	public void endFunc(){
		
	}
	public void resetValue() {
		log.info("---resetValue");
		nnTru="noi";
		gcv = new HsbaChuyenVien();
		setInfoIfNullForHsbaGiayChuyenVien(gcv);
		nnTru = "noi";
		ngayYc = ngayCap = dateFormat.format(new Date());
		maBATD = "";
		cm = new HsbaChuyenMon();
		setInfoIfNullForHsbaChuyenMon(cm);
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
		ngayChuyenVien = Utils.getCurrentDate();
		giatrithe_tungay ="";
		giatrithe_denngay="";
		dieutri1Tungay = "";
		dieutri1Denngay = "";
	}

	private void setInfoIfNullForHsbaChuyenMon(HsbaChuyenMon obj) {
		if (obj.getHsbacmBenhchinh()==null)
			obj.setHsbacmBenhchinh(new DmBenhIcd());
	}

	private void setInfoIfNullForHsbaGiayChuyenVien(HsbaChuyenVien obj) {
		if (obj.getHsbaSovaovien()==null){
			Hsba _hsba = new Hsba();			
			setInfoIfNullForHsba(_hsba);
			obj.setHsbaSovaovien(_hsba);
		}
		if (obj.getHsbacvLydochuyenv()==null){
			obj.setHsbacvLydochuyenv(new DtDmLyDoCv());
		}
		if (obj.getHsbacvBschuyen()==null){
			obj.setHsbacvBschuyen(new DtDmNhanVien());
		}
		if (obj.getHsbacvChvienden()==null){
			obj.setHsbacvChvienden(new DmBenhVien());
		}
		if (obj.getHsbacvBsdieutri()==null){
			obj.setHsbacvBsdieutri(new DtDmNhanVien());
		}
	}
	
	private void setInfoIfNullForHsba(Hsba obj){
		if (obj.getBenhnhanMa()==null){
			BenhNhan _benhnhan = new BenhNhan();
			setInfoIfNullForBenhNhan(_benhnhan);
			obj.setBenhnhanMa(_benhnhan);
		}
	}
	
	private void setInfoIfNullForBenhNhan(BenhNhan obj) {
		if (obj.getDmgtMaso()==null)
			obj.setDmgtMaso(new DmGioiTinh());
		if (obj.getDantocMa()==null)
			obj.setDantocMa(new DmDanToc());
		if (obj.getTinhMa()==null)
			obj.setTinhMa(new DmTinh());
		if (obj.getHuyenMa()==null)
			obj.setHuyenMa(new DmHuyen());
		if (obj.getXaMa()==null)
			obj.setXaMa(new DmXa());
		if (obj.getBenhnhanNghe()==null)
			obj.setBenhnhanNghe(new DmNgheNghiep());
	}
	
	public void displayHSBA(){
		log.info("---displayHSBA");
		Hsba hsba_tmp=null;
		gcv = new HsbaChuyenVien();
		if (!maBATD.trim().equals("")){
			if (nnTru.equals("noi")){
				hsba_tmp = HsbaDelegate.getInstance().find(maBATD.trim());
				if (hsba_tmp==null){
					nofoundHSBA = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maBATD);
				}else{
					if (hsba_tmp.getHsbaKhoarav()!=null){
						HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
						if (cm_tmp!=null){
							cm = cm_tmp;
							setInfoIfNullForHsbaChuyenMon(cm);
						}
					}
					
					String tmp_cls ="";
					List<ClsMo> clsmo_arr = ClsMoDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
					if (clsmo_arr != null && clsmo_arr.size() > 0){
						
						for (ClsMo c : clsmo_arr){
							if (c.getClsmoLoaicls() != null && ((c.getClsmoLoaicls().getDtdmclsMaso() == 4) || (c.getClsmoLoaicls().getDtdmclsMaso() == 5))){
								tmp_cls=tmp_cls + c.getClsmoMahang(true).getDtdmclsbgDiengiai()+"; ";
							}
						}
						
						if(tmp_cls.equals("")){
							tmp_cls = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
						} else if(tmp_cls.length() > 2040){
							tmp_cls = tmp_cls.substring(0, 2039) + " ...";
						}
						
					} else {
						tmp_cls = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
					}
					gcv.setHsbacvXetnghiem(tmp_cls);
					
					String tmp_thuoc = "";
					List<ThuocNoiTru> thuocnoitru = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
					if (thuocnoitru != null && thuocnoitru.size() > 0){
						
						for(ThuocNoiTru tnt : thuocnoitru){
							tmp_thuoc =tmp_thuoc+tnt.getThuocnoitruMathuoc(true).getDmthuocTen()+"; ";
						}
						
						if (tmp_thuoc.length() > 2040){
							tmp_thuoc = tmp_thuoc.substring(0,2039) + " ...";
						}
						
					} else {
						tmp_thuoc = "Kh\u00F4ng c\u00F3"; // chu "Khong co"
					}
					gcv.setHsbacvThuocdadung(tmp_thuoc);
					
//					duyen.lp lay thong tin the bao hiem
					HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
					HsbaBhyt hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienKhoadangdtLastHsbaBhyt(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoadangdt(true).getDmkhoaMa());
					if (hsbaBhyt!= null){
						if (hsbaBhyt.getHsbabhytGiatri0()!=  null){
							giatrithe_tungay = dateFormat.format(hsbaBhyt.getHsbabhytGiatri0());
							log.info("giatrithe_tungay "+ giatrithe_tungay);
						}
						
						if (hsbaBhyt.getHsbabhytGiatri1()!=  null){
							giatrithe_denngay = dateFormat.format(hsbaBhyt.getHsbabhytGiatri1());
							log.info("giatrithe_denngay "+ giatrithe_denngay);
						}
						
					}
					
					
//					duyen.lp them tim kiem ma phieu
					HsbaChuyenVien gcv_tmp = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
					if (gcv_tmp!= null){
						gcv = gcv_tmp;
					}

				}					
				
			}else if (nnTru.equals("ngoai")){
				hsba_tmp = HsbaDelegate.getInstance().findByTiepDonMa(maBATD.trim());
				if (hsba_tmp==null){
					nofoundHSBA = "true";
					hsba_tmp = new Hsba();
					FacesMessages.instance().add(IConstantsRes.HSBA_BY_MTD_NULL, maBATD);
				}else{
					if (hsba_tmp.getHsbaKhoarav()!=null){
						HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
						if (cm_tmp!=null){
							cm = cm_tmp;
							setInfoIfNullForHsbaChuyenMon(cm);
						}
					}
//					duyen.lp them tim kiem ma phieu
					HsbaChuyenVien gcv_tmp = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
					if (gcv_tmp!= null){
						gcv = gcv_tmp;
					}
				}
			}

			
			setInfoIfNullForHsba(hsba_tmp);
			gcv.setHsbaSovaovien(hsba_tmp);
			maBATD=hsba_tmp.getHsbaSovaovien();
			
			SimpleDateFormat sf = new SimpleDateFormat(Utils.FORMAT_DATE);
			Date dNgayChuyenVien = gcv.getHsbacvNgagiochvien();
			if (dNgayChuyenVien != null){
				ngayChuyenVien = sf.format(dNgayChuyenVien);
			}
			
			Date dDieutri1 = gcv.getDieutri1Tungay();
			if (dDieutri1 != null) {
				dieutri1Tungay = sf.format(dDieutri1);
			} else {
				dieutri1Tungay = "";
			}

			dDieutri1 = gcv.getDieutri1Denngay();
			if (dDieutri1 != null) {
				dieutri1Denngay = sf.format(dDieutri1);
			} else {
				dieutri1Denngay = "";
			}
			
		}else{
			nofoundHSBA = "true";
			hsba_tmp = new Hsba();
			setInfoIfNullForHsba(hsba_tmp);
			gcv.setHsbaSovaovien(hsba_tmp);
		}		
	}
	
	public void displayGiayChuyenVien(){
		log.info("---displayGiayRaVien");
		String maGcv = gcv.getHsbacvMa().trim();
		HsbaChuyenVien gcv_tmp = null;
		if (!maGcv.equals("")){
			gcv_tmp = HsbaChuyenVienDelegate.getInstance().findByHsbacvMa(maGcv);
			if (gcv_tmp==null){
				nofound = "true";
				gcv_tmp = new HsbaChuyenVien();
				FacesMessages.instance().add(IConstantsRes.GCV_NULL, maGcv);
			}else
			{
				
				HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(gcv_tmp.getHsbaSovaovien(true).getHsbaSovaovien(), gcv_tmp.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());
				if (cm_tmp!=null){
					cm = cm_tmp;
					setInfoIfNullForHsbaChuyenMon(cm);
				}
			}
			setInfoIfNullForHsbaGiayChuyenVien(gcv_tmp);
			gcv = gcv_tmp;
			
			maBATD = gcv_tmp.getHsbaSovaovien().getHsbaSovaovien();
			nnTru = "noi";
			isUpdate = true;
		}else{
			nofound = "true";
			gcv = new HsbaChuyenVien();
			setInfoIfNullForHsbaGiayChuyenVien(gcv);
		}
	}
	
	public void ghiNhan() throws ParseException{
		log.info("---ghiNhan");
		//log.info("Ghi nhan Giay chuyen vien, Nguoi chuyen: " + gcv.getHsbacvBschuyen(true).getDtdmnhanvienMaso() );
		//log.info("Ghi nhan Giay chuyen vien, Nguoi chuyen: " + gcv.getHsbacvBschuyen(true).getDtdmnhanvienMa() );
		//log.info("Ghi nhan Giay chuyen vien, Nguoi chuyen: " + gcv.getHsbacvBschuyen(true).getDtdmnhanvienTen() );
		//log.info("Ghi nhan Giay chuyen vien, BS Dieu tri: " + gcv.getHsbacvBsdieutri(true).getDtdmnhanvienMaso() );
		//log.info("Ghi nhan Giay chuyen vien, BS Dieu tri: " + gcv.getHsbacvBsdieutri(true).getDtdmnhanvienMa() );
		//log.info("Ghi nhan Giay chuyen vien, BS Dieu tri: " + gcv.getHsbacvBsdieutri(true).getDtdmnhanvienTen() );
		RemoveUtil.setAllNullForGiayChuyenVien(gcv);
		String result="";
		if (!ngayCap.trim().equals("")){
			gcv.setHsbacvNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		}
		if (!ngayYc.trim().equals("")){
			gcv.setHsbacvNgayyc(Utils.getDBDate("00:00", ngayYc).getTime());
		}
		gcv.setHsbacvNgaygiocn(new Date());
		SimpleDateFormat sf = new SimpleDateFormat(Utils.FORMAT_DATE);
		Date ngayChuyen = sf.parse(ngayChuyenVien);
		gcv.setHsbacvNgagiochvien(ngayChuyen);
		
		if (dieutri1Tungay != null && !dieutri1Tungay.equals("")) {
			Date dDieutri1Tungay = sf.parse(dieutri1Tungay);
			gcv.setDieutri1Tungay(dDieutri1Tungay);
		} else {
			gcv.setDieutri1Tungay(null);
		}
		
		if (dieutri1Denngay != null && !dieutri1Denngay.equals("")) {
			Date dDieutri1Denngay = sf.parse(dieutri1Denngay);
			gcv.setDieutri1Denngay(dDieutri1Denngay);
		} else {
			gcv.setDieutri1Denngay(null);
		}
		
		
		//String maGcv = gcv.getHsbacvMa().trim();
		if ( gcv.getHsbacvMa() != null && !gcv.getHsbacvMa().trim().equals("")){
			isUpdate = true;
		} else {
			isUpdate = false;
		}
		
		if (isUpdate){
			result = HsbaChuyenVienDelegate.getInstance().update(gcv);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCV_CN_THATBAI);
			}else{
				gcv.setHsbacvMa(result);	
				setInfoIfNullForHsbaGiayChuyenVien(gcv);
				FacesMessages.instance().add(IConstantsRes.GCV_CN_THANHCONG, result);
			}
		}else{
			result = HsbaChuyenVienDelegate.getInstance().insert(gcv);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GCV_LT_THATBAI);
			}else{
				gcv.setHsbacvMa(result);
				setInfoIfNullForHsbaGiayChuyenVien(gcv);
				FacesMessages.instance().add(IConstantsRes.GCV_LT_THANHCONG, result);
			}
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
		loaiBCDT="GiayChuyenVien";
		log.info("Vao Method XuatReport giay chuyen vien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/giaychuyenvienmoi.jrxml";
			log.info("Da thay file template: " + pathTemplate);
			
			HsbaChuyenVien gcv_tmp = HsbaChuyenVienDelegate.getInstance().findByHsbacvMa(gcv.getHsbacvMa());
			if (gcv_tmp != null) {
				gcv = gcv_tmp;
			}
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
			
			if(gcv.getHsbacvChvienden()!=null){
				if(IConstantsRes.TINH_DEFAULT.equals("511"))  // 20110127 bao.ttc: bo chu "Ban Giam doc theo yeu cau cua Khanh Hoa"
					params.put("KINHCHUYENDEN", gcv.getHsbacvChvienden().getDmbenhvienTen());
				else
					params.put("KINHCHUYENDEN", "Ban Gi\u00E1m \u0111\u1ED1c " + gcv.getHsbacvChvienden().getDmbenhvienTen()); // chu "Ban Giam doc "
			}
			BenhNhan benhNhan = gcv.getHsbaSovaovien().getBenhnhanMa();
			
			params.put("HOTENBN", benhNhan.getBenhnhanHoten());
			if (benhNhan.getBenhnhanNgaysinh() != null){
				params.put("NGAYSINH",  sdf.format(benhNhan.getBenhnhanNgaysinh()));
			}else{
				params.put("NGAYSINH",  benhNhan.getBenhnhanNamsinh());
			}
			params.put("GIOITINH",  benhNhan.getDmgtMaso(true).getDmgtTen());
			params.put("DANTOC", benhNhan.getDantocMa(true).getDmdantocTen());
			params.put("NGHENGHIEP", benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
			params.put("LIDOCHUYENVIEN",gcv.getHsbacvLydochuyenv(true).getDtdmlydocvTen());
			
			String bsDieutri = "";
			if(gcv.getHsbacvBsdieutri() != null) {
				if(gcv.getHsbacvBsdieutri(true).getDmhocviMaso() != null){
					bsDieutri += gcv.getHsbacvBsdieutri(true).getDmhocviMaso(true).getDmhocviMa() + ". ";
				}
				bsDieutri += gcv.getHsbacvBsdieutri(true).getDtdmnhanvienTen();
			}
			params.put("BSKHAM", bsDieutri);
			
			String bsChuyen = "";
			if(gcv.getHsbacvBschuyen() != null) {
				if(gcv.getHsbacvBschuyen(true).getDmhocviMaso() != null){
					bsChuyen += gcv.getHsbacvBschuyen(true).getDmhocviMaso(true).getDmhocviMa() + ". ";
				}
				bsChuyen += gcv.getHsbacvBschuyen(true).getDtdmnhanvienTen();
			}
			params.put("CANBO", bsChuyen);
			
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+", ";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+", ";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+", ";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("DIACHI", diachistr );
			
			HsbaBhyt hsbaBhyt = HsbaBhytDelegate.getInstance().findBySoVaoVienLastHsbaBhyt(gcv.getHsbaSovaovien().getHsbaSovaovien());
			String so_bhyt = "";
			if (hsbaBhyt != null && !hsbaBhyt.getHsbabhytSothebh().equals("")){
				so_bhyt = hsbaBhyt.getHsbabhytSothebh();
				params.put("COQUAN", hsbaBhyt.getHsbabhytCoquanbh()); // 20111013 bao.ttc: them thong tin Co quan 
				
				if(so_bhyt.length() >= 12){
					params.put("khoiBhytMa", so_bhyt.substring(0, 2));
					params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
					params.put("tinhBhytMa", so_bhyt.substring(3, 5));
					params.put("namBhyt", so_bhyt.substring(5, 7));
					params.put("maCoQuan", so_bhyt.substring(7, 10));
					if (hsbaBhyt.getHsbabhytMakcb() != null){
						params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa());
					}
				}
			
				Date giaTri1 = hsbaBhyt.getHsbabhytGiatri0();
				String sGiaTri1= "";
				if (giaTri1 != null){
					sGiaTri1 = sdf.format(giaTri1);
				}
				
				Date giaTri2 = hsbaBhyt.getHsbabhytGiatri1();
				String sGiaTri2= "";
				if (giaTri2 != null){
					sGiaTri2 = sdf.format(giaTri2);
				}
				
				params.put("GIATRISUDUNG",sGiaTri1 +" \u0111\u1EBFn "+sGiaTri2);
				params.put("NOICAP", hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
			}
			
			if(gcv.getHsbaSovaovien(true).getHsbaNgaygiovaov()!= null){
				params.put("NGAYVAOVIEN", sdf.format(gcv.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
				if(gcv.getHsbaSovaovien(true).getHsbaNgaygiorav() != null)
					params.put("NGAYRAVIEN", sdf.format(gcv.getHsbaSovaovien(true).getHsbaNgaygiorav()));
				else
					params.put("NGAYRAVIEN", sdf.format(new Date()));
				
				//log.info("===================== Tu ngay: " + params.get("NGAYVAOVIEN") + " - Den ngay: " + params.get("NGAYRAVIEN"));
			}
			params.put("NOIDIEUTRI", gcv.getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaTen());
			
			// bao.ttc: Dung de truyen Huong dieu tri, thay the bang Ly do Radio
			//if(gcv.getHsbacvLydochuyenv() != null)
			//	params.put("LIDOCHUYENVIEN", gcv.getHsbacvLydochuyenv().getDtdmlydocvTen());
			
			//bao.ttc: them cac truong moi
			if(gcv.getHsbacvDauhieulamsang() != null)
				params.put("DAUHIEULAMSANG",gcv.getHsbacvDauhieulamsang());
			else
				params.put("DAUHIEULAMSANG","");
			
			if(gcv.getHsbacvTinhtrangnguoibenh() != null)
				params.put("TINHTRANGNGUOIBENH", gcv.getHsbacvTinhtrangnguoibenh());
			else
				params.put("TINHTRANGNGUOIBENH", "");
			
			if(gcv.getHsbacvPhuongtienvanchuyen()!= null)
				params.put("PHUONGTIENVANCHUYEN", gcv.getHsbacvPhuongtienvanchuyen());
			else
				params.put("PHUONGTIENVANCHUYEN", "");
			
			if(gcv.getHsbacvXetnghiem() != null)
				params.put("CACXETNGHIEM", gcv.getHsbacvXetnghiem());
			else
				params.put("CACXETNGHIEM", "");
			
			if(gcv.getHsbacvThuocdadung()!= null)
				params.put("THUOCDADUNG", gcv.getHsbacvThuocdadung());
			else
				params.put("THUOCDADUNG", "");
			
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			String maChanDoan = "";
			String tenChanDoan = "";
			String chanDoan = "";
			DmBenhIcd benh = null;
			
			if (cm.getHsbacmBenhchinh() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhchinh(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			if (cm.getHsbacmBenhphu() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhphu(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += "; " + maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			if (cm.getHsbacmBenhphu2() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhphu2(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += "; " + maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			if (cm.getHsbacmBenhphu3() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhphu3(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += "; " + maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			if (cm.getHsbacmBenhphu4() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhphu4(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += "; " + maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			if (cm.getHsbacmBenhphu5() != null){
				benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(cm.getHsbacmBenhphu5(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
				if (benh != null){
					maChanDoan = benh.getDmbenhicdMa();
					tenChanDoan = benh.getDmbenhicdTen();
					chanDoan += "; " + maChanDoan + " - " +  tenChanDoan;
				}
			}
			
			// bao.ttc: them thamkham.ghichu
			if (cm.getHsbacmDiengiaibc() != null) {
				chanDoan += " , " + cm.getHsbacmDiengiaibc();
			}
			
			params.put("CDCNG", chanDoan );
			
			long iNgayVaoVien=0;
			if(gcv.getHsbaSovaovien().getHsbaNgaygiovaov()!=null&&gcv.getHsbaSovaovien().getHsbaNgaygiorav()!=null)
			iNgayVaoVien=daysBetween(gcv.getHsbaSovaovien().getHsbaNgaygiovaov(),gcv.getHsbaSovaovien().getHsbaNgaygiorav())+1;
			params.put("SONGAYDIEUTRI", iNgayVaoVien+"");
			
			params.put("DTNOITRU", "X");
			
			// 20110401 bao.ttc: cap nhat lai entity vi tren giao dien khong get duoc ten BS
			
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			
			// bao.ttc
			if(gcv.getHsbaSovaovien().getHsbaSovaovien() != null){
				params.put("MATIEPDON", gcv.getHsbaSovaovien().getHsbaSovaovien());
				//log.info("================= " + thamkham.getTiepdonMa(true).getTiepdonMa());
			}
			
			if (gcv.getDieutri1Donvi() != null && gcv.getDieutri1Donvi(true).getDmbenhvienMaso() != null) {
				params.put("DIEUTRI1DONVI", gcv.getDieutri1Donvi(true).getDmbenhvienTen() + " " + gcv.getDieutri1Donvi(true).getDmbenhvienMa());
			}
			
			if (dieutri1Tungay != null && !dieutri1Tungay.equals("")) {
				params.put("DIEUTRI1TUNGAY", dieutri1Tungay);
			}
			
			if (dieutri1Denngay != null && !dieutri1Denngay.equals("")) {
				params.put("DIEUTRI1DENNGAY", dieutri1Denngay);
			}
			
			if (gcv.getHsbacvLydoRadio() != null) {
				params.put("HUONGDIEUTRI", gcv.getHsbacvLydoRadio());
			}
			
			if (gcv.getHsbacvHuongdieutri() != null) {
				params.put("LIDOCHUYENVIEN", gcv.getHsbacvHuongdieutri());
			}
			
			//log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
		    
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,
					conn);
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index,
					IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "BienBanHoiChanPhauThuat");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
			index += 1;
			log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	private static long daysBetween(Date d1, Date d2)
	{
		if(d1==null) d1=new Date();
		if(d2==null) d2=new Date();
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}
	private static final long ONE_HOUR = 60 * 60 * 1000L;

	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
		
	public String formatDateTime(Date date){
		return date==null?"":Utils.getGioPhut(date);
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"true":"false";
	}

	public HsbaChuyenVien getGcv() {
		return gcv;
	}

	public void setGcv(HsbaChuyenVien gcv) {
		this.gcv = gcv;
	}

	public String getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(String ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNofoundHSBA() {
		return nofoundHSBA;
	}

	public void setNofoundHSBA(String nofoundHSBA) {
		this.nofoundHSBA = nofoundHSBA;
	}

	public String getNnTru() {
		return nnTru;
	}

	public void setNnTru(String nnTru) {
		this.nnTru = nnTru;
	}

	public String getMaBATD() {
		return maBATD;
	}

	public void setMaBATD(String maBATD) {
		this.maBATD = maBATD;
	}

	public String getNgayYc() {
		return ngayYc;
	}

	public void setNgayYc(String ngayYc) {
		this.ngayYc = ngayYc;
	}

	public HsbaChuyenMon getCm() {
		return cm;
	}

	public void setCm(HsbaChuyenMon cm) {
		this.cm = cm;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
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

	public String getNgayChuyenVien() {
		return ngayChuyenVien;
	}

	public void setNgayChuyenVien(String ngayChuyenVien) {
		this.ngayChuyenVien = ngayChuyenVien;
	}

	public String getDieutri1Tungay() {
		return dieutri1Tungay;
	}

	public void setDieutri1Tungay(String dieutri1Tungay) {
		this.dieutri1Tungay = dieutri1Tungay;
	}

	public String getDieutri1Denngay() {
		return dieutri1Denngay;
	}

	public void setDieutri1Denngay(String dieutri1Denngay) {
		this.dieutri1Denngay = dieutri1Denngay;
	}

	public String getGiatrithe_denngay() {
		return giatrithe_denngay;
	}

	public void setGiatrithe_denngay(String giatrithe_denngay) {
		this.giatrithe_denngay = giatrithe_denngay;
	}

	public String getGiatrithe_tungay() {
		return giatrithe_tungay;
	}

	public void setGiatrithe_tungay(String giatrithe_tungay) {
		this.giatrithe_tungay = giatrithe_tungay;
	}

	public String getDuongdanStrDT() {
		return duongdanStrDT;
	}

	public void setDuongdanStrDT(String duongdanStrDT) {
		this.duongdanStrDT = duongdanStrDT;
	}

}
