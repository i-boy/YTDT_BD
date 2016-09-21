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
import com.iesvn.yte.dieutri.delegate.TuvongTruocDelegate;
//import com.iesvn.yte.dieutri.delegate.CtTuvongTruocDelegate;
import com.iesvn.yte.dieutri.delegate.BenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.CtBenhAnNgoaiTruDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.entity.TuvongTruoc;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
//import com.iesvn.yte.dieutri.entity.CtTuvongTruoc;
import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B121_11_Batuvongtruockhivaovien")
@Synchronized(timeout = 6000000)
public class BATuVongTruocKhiVaoVienAction implements Serializable {

	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	private int stt;
	private String ngaySinh;
	private String thoiGian;
	private String gioThamKham;
	private String ngay;
	private String dienbienbenh;
	private String ylenh;
	private String bacSiMa;
	private String giuXac;
	private Integer bacSiMaSo_hidden;
	private static Logger log = Logger.getLogger(ThamKhamAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String maBanKhamOut;
	
	@In(required = false)
	@Out(required = false)
	private String maTiepDonOut;
	
	@In(required = false)
	@Out(required = false)
	private String goToLapBATuVongTruocKhiVaoVien;
	private SimpleDateFormat formatter;
	private BenhNhan benhNhan;
	

	private ThamKham thamkham;

	public String nguoiduadengioitinh;
	 public String nguoidungxingioitinh;

	    public String getNguoiduadengioitinh() {
	        return nguoiduadengioitinh;
	    }

	    public void setNguoiduadengioitinh(String nguoiduadengioitinh) {
	        this.nguoiduadengioitinh = nguoiduadengioitinh;
	    }

	    public String getNguoidungxingioitinh() {
	        return nguoidungxingioitinh;
	    }

	    public void setNguoidungxingioitinh(String nguoidungxingioitinh) {
	        this.nguoidungxingioitinh = nguoidungxingioitinh;
	    }
	 
	    public String ngaytuvong;
	     public String giotuvong;

	        public String getGiotuvong() {
	            return giotuvong;
	        }

	        public void setGiotuvong(String giotuvong) {
	            this.giotuvong = giotuvong;
	        }

	        public String getNgaytuvong() {
	            return ngaytuvong;
	        }

	        public void setNgaytuvong(String ngaytuvong) {
	            this.ngaytuvong = ngaytuvong;
	        }

	
	public void resetValue() {
		ngaytuvong = giotuvong ="";

	}
	private String resultHidden ="";
	
	private TuvongTruoc tuvongTruoc = null;

	
	
	@Begin(nested = true)
	@Factory("goToLapBATuVongTruocKhiVaoVien")
	public void init() throws Exception {
		log.info("***Starting init ***");		
		try{
		
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			thamkham = tkDelegate.findByBanKhamVaMaTiepDon(maBanKhamOut,maTiepDonOut);
			benhNhan = thamkham.getTiepdonMa().getBenhnhanMa();
			
			if(thamkham.getThamkhamBacsi() != null){
				bacSiMa = thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
//				bacSiMaSo_hidden = thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();
			}
			
			
			TuvongTruoc tuvongTruocTemp = null;
			try{
				tuvongTruocTemp = TuvongTruocDelegate.getInstance().getTuvongTruoc(thamkham.getThamkhamMa());
			}catch(Exception e){
				log.info("error:"+ e);
			}
			// bao.ttc: log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTruTemp:"+baNgoaiTruTemp);
			ngaytuvong=giotuvong="";
			if (tuvongTruocTemp!= null){
				tuvongTruoc= tuvongTruocTemp;
				giuXac = tuvongTruoc.getTvtGiuxac()?"0":"1";
				nguoiduadengioitinh = tuvongTruoc.getTvtGioitinhNguoiduaden()?"1":"0";
				nguoidungxingioitinh = tuvongTruoc.getTvtNguoixinxacGioitinh()?"1":"0";
				log.info("%%%%%%%%%%%%%%%%%%%%%tuvongTruoc.getTvtMa(): " + tuvongTruoc.getTvtMa());
				if(tuvongTruoc.getTvtTuvongluc()!=null)
				{
					setNgaytuvong(formatDate(tuvongTruoc.getTvtTuvongluc()));
					setGiotuvong(formatDateTime(tuvongTruoc.getTvtTuvongluc()));
				}
			}else{
				tuvongTruoc = new TuvongTruoc();
				tuvongTruoc.setTvtBenhnhan(benhNhan);
				tuvongTruoc.setTvtBankham(thamkham.getThamkhamBankham(true));
				tuvongTruoc.setTvtThamkham(thamkham);
				giuXac = "0";
				nguoiduadengioitinh = "0";
				nguoidungxingioitinh ="0";
			}
			setOtherValue();
			
			
			//quang: khong can dua thong tin thuoc vao vi khong co y lenh
//			get_thuoc_info();
			
			destroyService();
			
			goToLapBATuVongTruocKhiVaoVien = "";
		}catch(Exception e){
			log.info("***init Exception = " + e);
		}
		log.info("***Finished init ***");		
	}

	@End 
	public void end(){
		goToLapBATuVongTruocKhiVaoVien = null;
	}
		
	//***********************************************************************************
	
	   public TuvongTruoc getTuvongTruoc() {
	        return tuvongTruoc;
	    }

	    public void setTuvongTruoc(TuvongTruoc tuvongTruoc) {
	        this.tuvongTruoc = tuvongTruoc;
	    }
	  
	    public String formatDate(Date date) {
			return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy")
					.format(date);
		}

		public String formatDateTime(Date date) {
			System.out.println("qdate = "
					+ (date == null ? "isnull" : date.toString()));
			return date == null ? "" : Utils.getGioPhut(date);
		}
	//Ly
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		insertRow();
		reset_ctbant();
		log.info("*****End Enter() *****");
	}
	private void insertRow(){
		
	}
	
    public String getGiuXac() {
        return giuXac;
    }

    public void setGiuXac(String giuXac) {
        this.giuXac = giuXac;
    }
	
	public void delete(int index) throws Exception {
		log.info("*****Begin delete() *****");
		reset_ctbant();
		log.info("*****End delete() *****");
	}
	
	public void reset_ctbant(){
		
		dienbienbenh = "";
		ylenh = "";
		// bao.ttc: ko can set lai cac tham so duoi de tranh null exception
		// ngay = "";
		// bacSiMaSo_hidden = null;
		// bacSiMa = "";
	}
	
	// Ham ghi nhan
	//Xu ly cho nut ghi nhan 
	public String ghiNhanAjax() throws Exception {
		log.info("***Starting ghinhan **");
		
		//
//		if (tuNgay != null && !tuNgay.equals("")){
//			SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
//			Date dTN = df.parse(tuNgay);
////			tuvongTruoc.setBantDttungay(dTN);
//		}
//		if (denNgay != null && !denNgay.equals("")){
//			SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
//			Date dDN = df.parse(denNgay);
////			tuvongTruoc.setBantDtdenngay(dDN);
//		}
		log.info("ngaytuvong = "+ngaytuvong);
		log.info("giotuvong = "+giotuvong );
		
		
		if (!ngaytuvong.trim().equals("")) {
			if (giotuvong.trim().equals("")) {
				tuvongTruoc.setTvtTuvongluc(Utils.getDBDate("00:00", ngaytuvong)
						.getTime());
			} else {
				tuvongTruoc.setTvtTuvongluc(Utils.getDBDate(giotuvong  , ngaytuvong)
						.getTime());
			}
		}
		
		// 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
//		String maBATvt = "";
		
		//maBATvt = TuvongTruocDelegate.getInstance().capNhatTuvongTruoc(tuvongTruoc, tuvongTruoc.getBantMa(), listCtBANT);
//		if (maBANT != null && !maBANT.equals("")){
//			tuvongTruoc.setBantMa(maBANT);
//		}
		// END -- 20110321 bao.ttc: Luu lai Ma BA Ngoai tru de in phieu
		
		log.info("Giu xac = "+tuvongTruoc.getTvtGiuxac());
		log.info("Giu xac = "+getGiuXac());
		if((giuXac!=null)&&(giuXac.equals("0")))
		{
			tuvongTruoc.setTvtGiuxac(true);
			tuvongTruoc.setTvtXinxackhongkhieunai(false);
		}else
		{
			tuvongTruoc.setTvtGiuxac(false);
			tuvongTruoc.setTvtXinxackhongkhieunai(true);
		}
		
		if((nguoiduadengioitinh!=null)&&(nguoiduadengioitinh.equals("0")))
		{
			tuvongTruoc.setTvtGioitinhNguoiduaden(false);
		}else
		{
			tuvongTruoc.setTvtGioitinhNguoiduaden(true);
		}
		if((nguoidungxingioitinh!=null)&&(nguoidungxingioitinh.equals("0")))
		{
			tuvongTruoc.setTvtNguoixinxacGioitinh(false);
		}else
		{
			tuvongTruoc.setTvtNguoixinxacGioitinh(true);
		}
		
		if (tuvongTruoc.getTvtMa() == null){
			TuvongTruocDelegate.getInstance().create(tuvongTruoc);
			
		}else{
			TuvongTruocDelegate.getInstance().edit(tuvongTruoc);
		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
		return "/B1_Tiepdon/B121_11_Batuvongtruockhivaovien";
	}
	
	//Xu ly cho nut quay lai
	public String quayLai()  throws Exception {
		log.info("*****Begin quayLai() *****");
		goToLapBATuVongTruocKhiVaoVien = null;
		log.info("*****End quayLai() *****");
		return "ghinhan";
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
		reportTypeTD="benhantuvongtruockhivaovien";
		
		log.info("Vao Method XuatReport benhantuvongtruockhivaovien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhantuvongtruoc_main.jrxml";
			String sub0Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhantuvongtruoc_sub1.jrxml";
			String sub1Template = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"tiepdon/benhantuvongtruoc_sub2.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport sub0Report =JasperCompileManager.compileReport(sub0Template);
			JasperReport sub1Report =JasperCompileManager.compileReport(sub1Template);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("sub1", sub0Report);
			params.put("sub2", sub1Report);
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
			log.info("soyte = "+IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info("ten benh vien = "+ IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
			log.info("****NGAYSINH: "+benhNhan.getBenhnhanNgaysinh());
			params.put("sodt", thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
			params.put("khoakham", thamkham.getThamkhamBankham(true).getDmkhoaMaso(true).getDmkhoaTen());
			
			params.put("HoTen", benhNhan.getBenhnhanHoten());
			
			params.put("Tuoi", benhNhan.getBenhnhanTuoi());
			String diachistr = "";
			if (benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()
						+ ", ";
			if (benhNhan.getXaMa(true).getDmxaTen() != null)
				diachistr += benhNhan.getXaMa(true)
						.getDmxaTen()
						+ ", ";
			if (benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true)
						.getDmhuyenTen()
						+ ", ";
			if (benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true)
						.getDmtinhTen();
			params.put("DIACHI", diachistr);
			params.put("gioiTinh",  benhNhan.getDmgtMaso(true).getDmgtTen());
			params.put("DonViTuoi",  benhNhan.getBenhnhanDonvituoi()==null?new Integer(1):new Integer(benhNhan.getBenhnhanDonvituoi()) );
			TuvongTruoc tmp = new  TuvongTruoc();
			tmp = TuvongTruocDelegate.getInstance().getTuvongTruoc(thamkham.getThamkhamMa());
			params.put("MAGIAY",tmp !=  null ?  tmp.getTvtMa() : null);
			/*
			 *
			 * 
			 */
			
			params.put("gioiTinh", benhNhan.getDmgtMaso(true).getDmgtTen());
			
						
			
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
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintTD,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/", "pdf","benhanvaovien");
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
	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	private String tuNgay;
	private String denNgay;
	
	private void setOtherValue() {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null	&& !thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		else if(thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh()!=null)
			ngaySinh = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
			
		if (thamkham.getThamkhamNgaygio() != null
				&& !thamkham.getThamkhamNgaygio().equals("")) {
			thoiGian = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			ngay = formatter.format(thamkham.getThamkhamNgaygio().getTime());
			gioThamKham = Utils.getGioPhut(thamkham.getThamkhamNgaygio()) ;
		}
		
//		if (tuvongTruoc.getBantDttungay() != null
//				&& !tuvongTruoc.getBantDttungay().equals("")) {
//			tuNgay = formatter
//					.format(tuvongTruoc.getBantDttungay().getTime());
//		}
//		if (tuvongTruoc.getBantDtdenngay() != null
//				&& !tuvongTruoc.getBantDtdenngay().equals("")) {
//			denNgay = formatter
//					.format(tuvongTruoc.getBantDtdenngay().getTime());
//		}
	}

	private void setinfor(ClsKham nhapctSelected) {
		// SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();
		//dmkythuat.setDtdmkythuatDiengiai(diengiai);
		nhapctSelected.setClskhamMahang(dmkythuat);
//		if (thamkham.getTiepdonMa() != null) {
//			nhapctSelected.setTiepdonMa(thamkham.getTiepdonMa());
//		}
		if (thamkham.getThamkhamNgaygio() != null
				&& !"".equals(thamkham.getThamkhamNgaygio())) {
			nhapctSelected.setClskhamNgaygio(thamkham.getThamkhamNgaygio());
		}

//		if (thamkham.getThamkhamBankham() != null) {
//			nhapctSelected.setClskhamBankham(thamkham.getThamkhamBankham());
//		}
	}


	// 20110209 bao.ttc: lay thong tin ThuocPhongKham theo Ngay de dua vao Y lenh
	public void get_thuoc_info(){
		
		ylenh = "";
		if(ngay != null && !ngay.equals("")){
			
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
					ylenh += "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH;
				if(!thuocBK.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK;
				if(!thuocVe.equals(""))
					ylenh += "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe;
				
			} else {
				ylenh = IConstantsRes.KHONG_DUNG_THUOC;
			}
			
		}
		
		//log.info("### Y lenh: " + ylenh);
	}

///////////////////////	
///////////////////
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
		log.info("************************* destroy ThamKhamAction");
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

	
	public String getGioThamKham() {
		return gioThamKham;
	}

	public void setGioThamKham(String gioThamKham) {
		this.gioThamKham = gioThamKham;
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

	public TuvongTruoc getBaNgoaiTru() {
		return tuvongTruoc;
	}

	public void setBaNgoaiTru(TuvongTruoc tuvongTruoc) {
		this.tuvongTruoc = tuvongTruoc;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getDienbienbenh() {
		return dienbienbenh;
	}

	public void setDienbienbenh(String dienbienbenh) {
		this.dienbienbenh = dienbienbenh;
	}

	public String getYlenh() {
		return ylenh;
	}

	public void setYlenh(String ylenh) {
		this.ylenh = ylenh;
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


}


