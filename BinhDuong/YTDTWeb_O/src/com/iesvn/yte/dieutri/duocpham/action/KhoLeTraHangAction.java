package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.TonKhoUtil;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(CONVERSATION)
@Name("B4162_Kholetrahang")
@Synchronized(timeout = 6000000)
/**
 *	Triet code 
 */
public class KhoLeTraHangAction implements Serializable{

	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private static final long serialVersionUID = 10L;

	private static Logger log = Logger.getLogger(KhoLeTraHangAction.class);
	@DataModel
	private ArrayList<CtTraKhoExt> listCtKhoLeTraEx;
	@DataModelSelection
	private CtTraKhoExt selected;

	private PhieuTraKho phieuTra;
	private String maPhieu;
	private String ngayXuat;
	private String dmtMa;
	private Double tonkho;
	private Double xuat;
	private String tonkhoMa;
	private Integer updateItem = -1;
	private Integer count;
	private String ngayHienTai;
	private String nguonMa;
	private String kpMa;
	private String malk;
	private Double tongTien;
	private String isFound;
	private String isUpdate;
	
	String dmKhoXuat = "";
	String dmKhoNhan = "";
	
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog ;
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	

	@Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
	@Begin(join = true)
	public String init(String khoNhan, String khoXuat) {
		log.info("***** init KhoLeTraHangAction() *****");
		reset();
		tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		dmKhoXuat = khoXuat;
		dmKhoNhan = khoNhan;
		return "QuanLyKhoChinh_NhapKhoChinh_KhoLeTraHang";
	}
	
	@End
	public void endConversation(){
		
	}
	
	@Factory("listCtKhoLeTraEx")
	public void createTable() {
		log.info("-----createTable()-----");
		listCtKhoLeTraEx = new ArrayList<CtTraKhoExt>();
	}

	/**
	 * Xoa chi tiet xuat
	 */
	public void deleteCt() {
		log.info("-----deleteCt()-----");
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		listCtKhoLeTraEx.remove(selected);
		this.count = listCtKhoLeTraEx.size();
		tinhTien();
	}
	
	public String getDmKhoNhan() {
		return dmKhoNhan;
	}

	public void setDmKhoNhan(String dmKhoNhan) {
		this.dmKhoNhan = dmKhoNhan;
	}

	/**
	 * Hien thi chi tiet xuat
	 */
	public void selectCt(Integer index) {
		log.info("-----selectCt()-----");
		log.info(String.format("-----selected: %s", selected.getCtTraKho().getCttrakhoThutu()));
		//int index = selected.getCtXuatKho().getCtxuatkhoThutu().intValue() - 1;
		TonKho tk = selected.getTonKho();
		CtTraKho ctx = selected.getCtTraKho();
		updateItem = index;
		tonkhoMa = tk.getTonkhoMa().toString();
		log.info("-----ton kho: " + tonkho);
		
		//DecimalFormat df = new DecimalFormat("###.##");
		tonkho = tk.getTonkhoTon();
		dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
		xuat = ctx.getCttrakhoSoluong();
		
	}

	/**
	 * Cap nhat chi tiet phieu xuat
	 */
	public void tiepTucNhap() {
		log.info("-----tiepTucNhap()-----");
		log.info(String.format("-----index: %s", updateItem));
		
		log.info("tonkhoMa:"+tonkhoMa);
		log.info("xuat:"+xuat);
		log.info("dmtMa:"+dmtMa);
		log.info("updateItem:"+updateItem);
		log.info("tonkho:"+tonkho);

		if (xuat == null || xuat.equals("") || tonkho  == null || tonkho.equals("")){
			return;
		}
		      
		if ("".equals(tonkhoMa) && tonkhoMa == null) {
			log.info("-----tonkho ma is null.");
		} else {
			log.info(String.format("-----tonkho ma: %s", tonkhoMa));
			TonKho tk = null;
			
			TonKhoDelegate tkDelegate;
			try {
				tkDelegate = TonKhoDelegate.getInstance();
				
				tk = tkDelegate.find(Integer.valueOf(tonkhoMa));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CtTraKho ctx = new CtTraKho();
			
			Double slXuat = new Double("0");
			for (int i = 0; i < listCtKhoLeTraEx.size(); i++) {
				CtTraKho ctxk = listCtKhoLeTraEx.get(i).getCtTraKho();
				if (malk.equals(ctxk.getCttrakhoMalk())) {
					log.info("-----malk " + malk);
					slXuat += ctxk.getCttrakhoSoluong();
					updateItem = i;
				}
			}
			slXuat += Double.valueOf(xuat);
			ctx.setCttrakhoSoluong(slXuat);
			CtTraKhoExt ctxEx = createCtXuatKho(ctx, tk);
			log.info("-----xuat: " + slXuat);
			
			if (updateItem.equals(-1)) {
				log.info("-----them moi ct");
				listCtKhoLeTraEx.add(ctxEx);
			} else {
				log.info("-----Cap nhat ct-----");
				if (tk != null) {
					listCtKhoLeTraEx.set(updateItem, ctxEx);
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, dmtMa);
				}

				log.info(String.format("-----update ct: %s", ctx.getCttrakhoThutu()));
			}

			count = listCtKhoLeTraEx.size();
			log.info(String.format("-----listCtXuatKho: %s", listCtKhoLeTraEx.size()));
			tonkhoMa = "";
			dmtMa = "";
			tonkho = new Double(0);
			xuat = new Double(0);
			updateItem = -1;
		}
		tinhTien();
	}

	/**
	 * Ket thuc nhap phieu xuat
	 * @return
	 */
	public void end() {
		log.info("-----end()-----");
		TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
		KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
		if (listCtKhoLeTraEx.size() > 0) {
			yteLog = new YteLog();
			listDataLog = "";
			if (!ngayXuat.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dtNgayXuat = df.parse(ngayXuat);
					Calendar cal = Calendar.getInstance();
					cal.setTime(dtNgayXuat);
					phieuTra.setPhieutrakhoNgaygiocn(cal.getTime());
				} catch (ParseException e) {
					log.error(String.format("-----Error: %s", e.toString()));
				}
			}
			
			
			try {
				for (int i=0;i<listCtKhoLeTraEx.size();i++) {
					CtTraKho ct = listCtKhoLeTraEx.get(i).getCtTraKho();
					
					//28-4-2011 - kiem tra tai kho nhan, neu dang kiem ke thi khong duoc tra thuoc
					boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCttrakhoMalk(),phieuTra.getDmkhoaNhan().getDmkhoaMaso());
					if(tinhtrangKiemKe == true){
						FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, ct.getCttrakhoMalk());
						return;
					}
					//End kiem tra
					
					Double tonkhoHienTai = tkDelegate.findByTonkhoKhoaMalienketHienTai(ct.getCttrakhoMalk(), phieuTra.getDmkhoaTra().getDmkhoaMaso());
					if (tonkhoHienTai < ct.getCttrakhoSoluong())
					{
						//Neu so luong ton hien tai < so luong tra tren phieu du tru cua khoa tra thi se khong cho tra tren phieu du tru do
						//Yeu cau huy phieu du tru va lam lai
						FacesMessages.instance().add(IConstantsRes.PHIEUTRAKHO_SLKHONGDUTRA);
						return;
					}
				}
				double tt = Double.valueOf("0");
				ArrayList<CtTraKho> listCtx = new ArrayList<CtTraKho>();
				ArrayList<TonKho> listTkTra = new ArrayList<TonKho>();
				ArrayList<TonKho> listTkNhan = new ArrayList<TonKho>();
				TonKho tkTra;
				TonKho tkNhan;
				//CtTraKhoExt obj = new CtTraKhoExt();
				for (int i=0;i<listCtKhoLeTraEx.size();i++) {
					CtTraKho ct = listCtKhoLeTraEx.get(i).getCtTraKho();
					tt += ct.getCttrakhoSoluong() * ct.getCttrakhoDongia();
					ct.setCttrakhoThutu(Short.valueOf("" + (i + 1)));
					listCtx.add(ct);
					tkTra=createTonKhoTra(listCtKhoLeTraEx.get(i));
					//ctx.getTonKhoTra().setTonkhoMa(null);
					listTkTra.add(tkTra);
					
					tkNhan=createTonKhoNhan(listCtKhoLeTraEx.get(i));
					//ctx.getTonKhoNhan().setTonkhoMa(null);
					listTkNhan.add(tkNhan);
					
					//luu log thong tin thuoc
					listDataLog += "Ma LK:"+ ct.getCttrakhoMalk()+
							" Don gia: "+ ct.getCttrakhoDongia()+ " Don gia ban: "+ ct.getCttrakhoDongiaban() + 
							" So luong: "+ ct.getCttrakhoSoluong()+
							" So lo: "+ ct.getCttrakhoLo()+
							" Nam SX: " + ct.getCttrakhoNamnhap()+
							" Nam HD: " + ct.getCttrakhoNamhandung()+"\n";
				}
				phieuTra.setPhieutrakhoThanhtien(tt);
				phieuTra.setPhieutrakhoNgaygiocn(new Date());
				phieuTra.setPhieutrakhoNgaygiophat(new Date());
				//phieuTra.setPhieutrakhoNgay(dtNgayXuat);
				PhieuTraKhoDelegate pxDelegate = PhieuTraKhoDelegate.getInstance();
				clearInfor();
				maPhieu = pxDelegate.thucHienTraKho(phieuTra, listCtx, listTkNhan, listTkTra);
				if (maPhieu != "") {
					resetInfo();
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, maPhieu);
					//reset();
					isUpdate = "0";//@Trung fix bug 473: sau khi Ghi Nhan moi cho In phieu
					isFound="true";
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				}
				
//				Luu log he thong
		         yteLog.setForm("B4162_Kholetrahang");
		         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
		         yteLog.setObjectId(maPhieu);
		         yteLog.setLogString("Ngày xuất: "+ ngayXuat + 
		         					" Người nhập: "+ phieuTra.getDtdmnhanvienCn(true).getDtdmnhanvienMa()+
		         					" Người ký: "+ phieuTra.getDtdmnhanvienBacsi(true).getDtdmnhanvienMa()+
		         					" Kho nhận: "+ phieuTra.getDmkhoaNhan(true).getDmkhoaMa()+
		         					" Kho xuất: "+ phieuTra.getDmkhoaTra(true).getDmkhoaMa()+
		         					" Người lập: "+ phieuTra.getDtdmnhanvienPhat(true).getDtdmnhanvienMa());
		         yteLog.setDateTime(new Date());
		         yteLog.setListData(listDataLog);

		         yteLogDele.create(yteLog);
				
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI);
				log.error(String.format("-----Error: %s", e.toString()));
			}
		} else {
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL);
		}
	}
	
	@Destroy
	public void destroy() {
		log.info("-----destroy()-----");
	}
	
	/**
	 * Hien thi phieu xuat len giao dien
	 */
	public void displayPhieuXuatKho() {
		log.info("-----displayPhieuXuatKho()-----");
		if (!maPhieu.equals("")) {
			try {
				DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
				PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
				CtTraKhoDelegate ctxWS = CtTraKhoDelegate.getInstance();
				DmKhoa dmKhoaNhan = new DmKhoa();
				dmKhoaNhan = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
				phieuTra = pxkWS.findByPhieutrakhoByKhoNhan(maPhieu, dmKhoaNhan.getDmkhoaMaso());
				if (phieuTra != null) {
					maPhieu = phieuTra.getPhieutrakhoMa();
					resetInfo();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayXuat = df.format(phieuTra.getPhieutrakhoNgay());
					for (CtTraKho ct : ctxWS.findByphieutrakhoMa(phieuTra.getPhieutrakhoMa())) {
						CtTraKhoExt ctxEx = new CtTraKhoExt();
						ctxEx.setCtTraKho(ct);
						listCtKhoLeTraEx.add(ctxEx);
					}
					count = listCtKhoLeTraEx.size();
					isFound="true";
					// = null la chua luu ton kho -> cho ghi nhan
					// = 1 da luu to  kho -> khong cho ghi nhan
					if(phieuTra.getPhieutrakhoNgaygiophat()==null)
					isUpdate = "1";
					else
						isUpdate = "0";
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
					reset();
				}
				tinhTien();
			} catch (Exception e) {
				FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, maPhieu);
				reset();
				log.error(String.format("-----Error: %s", e));
			}
		}
		
	}

	/**
	 * Tao chi tiet xuat kho tu ton kho
	 * @param ctx
	 * @param tk
	 * @return
	 */
	private CtTraKhoExt createCtXuatKho(CtTraKho ctx, TonKho tk) {
		log.info(String.format("-----createCtXuatKho()-----"));
		
		TonKho tkTra = null;
		try {
			tkTra = (TonKho) BeanUtils.cloneBean(tk);
			tkTra.setTonkhoNgaygiocn(new Date());
			tkTra.setTonkhoMa(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tkTra != null) {
			tkTra.setTonkhoXuat(ctx.getCttrakhoSoluong());
			tkTra.setTonkhoTra(null);
			tkTra.setTonkhoNhap(null);
			tkTra.setTonkhoMa(tk.getTonkhoMa());
		}
		
		TonKho tkNhan = null;
		try {
			tkNhan = (TonKho) BeanUtils.cloneBean(tk);
			tkNhan.setTonkhoNgaygiocn(new Date());
			tkNhan.setTonkhoMa(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tkNhan != null) {
			tkNhan.setTonkhoNhap(ctx.getCttrakhoSoluong());
			tkNhan.setTonkhoTra(null);
			tkNhan.setTonkhoXuat(null);
		}
		
		ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
		ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
		ctx.setDmthuocMaso(tk.getDmthuocMaso());
		ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
		ctx.setDmnctMaso(tk.getDmnctMaso());
		ctx.setCttrakhoDongia(tk.getTonkhoDongia());
		ctx.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
		ctx.setCttrakhoLo(tk.getTonkhoLo());
		ctx.setCttrakhoMalk(tk.getTonkhoMalienket());
		ctx.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
		ctx.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
		ctx.setCttrakhoNgaygiocn(new Date());
		ctx.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
		ctx.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
		ctx.setPhieutrakhoMa(phieuTra);
		ctx.setCttrakhoNgaygiocn(new Date());
		ctx.setCtxuatkhoSodangky(tk.getTonkhoSodangky());
		
		CtTraKhoExt ctxEx = new CtTraKhoExt();
		ctxEx.setCtTraKho(ctx);
		ctxEx.setTonKhoTra(tkTra);
		ctxEx.setTonKhoNhan(tkNhan);
		return ctxEx;
	}
	public String thuchienAction(){
		XuatReport();
		return "B4160_Chonmenuxuattaptin";
	}
	int index= 0;
	/**
	 * xuat report 
	 */
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	public void XuatReport() {
		reportTypeKC="TraHangKhoChinh";
		log.info("Vao Method XuatReport TraHangKhoChinh");
		String baocao1=null;
		Date currentDate = new Date();

		if (!maPhieu.equals("")) {
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieutrakho_01.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
			PhieuTraKho px = pxkWS.find(maPhieu);
			// saveSum(tuNgay, denNgay, donViMa);
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			log.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
			Calendar cal = Calendar.getInstance();
			cal.setTime(px.getPhieutrakhoNgay());
			if (cal != null) {
				log.info(String.format("-----ngay lap: %s", cal.getTime()));
				params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
				params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
				params.put("namHt", "" + cal.get(Calendar.YEAR));
			} else {
				log.info("-----ngay lap is null");
				params.put("ngayHt", "");
				params.put("thangHt", "");
				params.put("namHt", "");
			}

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
			String ngayGioHt = df.format(Calendar.getInstance().getTime());
			log.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
			params.put("gioHt", ngayGioHt);

			params.put("pxMa", px.getPhieutrakhoMa());

			if (px.getDmkhoaNhan() != null) {
				params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
			} else {
				params.put("khoaNhan", "");
			}
			log.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

			if (px.getDmkhoaTra() != null) {
				params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
			} else {
				params.put("khoaXuat", "");
			}
			log.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

			params.put("tongTien", px.getPhieutrakhoThanhtien());
			log.info(String.format("-----tongTien: %s", params.get("tongTien")));
			///params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
			params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());
			
			log.info("================= ");
			Class.forName(IConstantsRes.ORACLE_DRIVER);
			   log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","xuathangdenkhokhac");
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    index+=1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		}
		log.info("Thoat Method XuatReport");
	}
	/**
	 * Hien thi trang in
	 * @param loaiFile
	 * @return
	 */
	/*
	public String inPhieu(String loaiFile) {
		logger.info(String.format("-----inPhieu()-----"));
		logger.info(String.format("-----loai file: %s", loaiFile));
		Date currentDate = new Date();

		if (!maPhieu.equals("")) {
			try {

				//PhieuXuatKhoWSService pxkService = new PhieuXuatKhoWSServiceLocator();
				PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
				PhieuTraKho px = pxkWS.find(maPhieu);

				String pathTemplate = "D03_phieuxuatkho";
				logger.info(String.format("-----pathTemplate: %s", pathTemplate));
				Map<String, Object> params = new HashMap<String, Object>();
				// saveSum(tuNgay, denNgay, donViMa);
				params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
				logger.info(String.format("-----tenSo: %s", params.get("tenSo")));
				params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
				logger.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
				params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
				logger.info(String.format("-----dienThoaiDonVi: %s", params.get("dienThoaiDonVi")));
				Calendar cal = Calendar.getInstance();
				cal.setTime(px.getPhieuxuatkhoNgaylap());
				if (cal != null) {
					logger.info(String.format("-----ngay lap: %s", cal.getTime()));
					params.put("ngayHt", "" + cal.get(Calendar.DAY_OF_MONTH));
					params.put("thangHt", "" + (cal.get(Calendar.MONTH) + 1));
					params.put("namHt", "" + cal.get(Calendar.YEAR));
				} else {
					logger.info("-----ngay lap is null");
					params.put("ngayHt", "");
					params.put("thangHt", "");
					params.put("namHt", "");
				}

				SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
				String ngayGioHt = df.format(Calendar.getInstance().getTime());
				logger.info(String.format("-----ngay gio hien tai: %s", ngayGioHt));
				params.put("gioHt", ngayGioHt);

				params.put("pxMa", px.getPhieuxuatkhoMa());

				if (px.getDmkhoaNhan() != null) {
					params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
				} else {
					params.put("khoaNhan", "");
				}
				logger.info(String.format("-----khoaNhan: %s", params.get("khoaNhan")));

				if (px.getDmkhoaXuat() != null) {
					params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
				} else {
					params.put("khoaXuat", "");
				}
				logger.info(String.format("-----khoaXuat: %s", params.get("khoaXuat")));

				params.put("tongTien", px.getPhieuxuatkhoThanhtien());
				logger.info(String.format("-----tongTien: %s", params.get("tongTien")));
				params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
				params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());

				String fileNameExt = String.valueOf(currentDate.getTime()) ;
				String fileName = com.iesvn.yte.ReportUtil.xuatReport(IConstantsRes.PATH_BASE,
															IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, 
															IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "duocpham/",
															pathTemplate , params, loaiFile, fileNameExt);
		        
		        if(loaiFile.equalsIgnoreCase("HTML")) {
	            	resultReportName = fileName;
	            } else {
	            	resultReportFileName = fileName;
	            }
	            setIsReport("true");
			} catch (Exception ex) {
				logger.error(String.format("-----Error: %s", ex.toString()));
			}
		}
		
		return "B4121_Chonmenuxuattaptin";
	}
	*/
	/**
	 * 
	 * @return
	 */
	public String troVe(){
		try {
			log.info("***** troVe()");
			return "B4444_Phieutrahangbhytchokhochinh";
		} 		
		catch (Exception e) {
			log.info("***** End exception = " + e);    	
		} 
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public void reset() {
		log.info("-----reset()-----");
		isFound="false";
		maPhieu = "";
		updateItem = -1;
		tonkhoMa = "";
		dmtMa = "";
		tonkho = new Double(0);
		xuat = new Double(0);
		nguonMa = "";
		kpMa = "";
		count = 0;
		phieuTra = new PhieuTraKho();
		listCtKhoLeTraEx = new ArrayList<CtTraKhoExt>();
		resetInfo();
		ngayXuat = Utils.getCurrentDate();
		ngayHienTai = Utils.getCurrentDate();
		isUpdate = "0";
		log.info("-----identity: " + identity.getUsername());
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
		if (nv != null) {
			phieuTra.setDtdmnhanvienCn(nv);
		}
		tongTien = new Double("0");
	}
	
	private void tinhTien() {
		tongTien = new Double("0");
		for (CtTraKhoExt ctExt : listCtKhoLeTraEx) {
			Double sl = ctExt.getCtTraKho().getCttrakhoSoluong();
			if (sl == null) {
				sl = new Double("0");
			}
			Double dg = ctExt.getCtTraKho().getCttrakhoDongia();
			if (dg == null) {
				dg = new Double("0");
			}
			tongTien += sl * dg;
		}
		log.info("-----tong tien: " + tongTien);
	}
	
	private void resetInfo() {
		log.info("-----resetInfo()-----");
		if (phieuTra.getDmkhoaNhan() == null) {
			phieuTra.setDmkhoaNhan(new DmKhoa());
		}
		if (phieuTra.getDmkhoaTra() == null) {
			phieuTra.setDmkhoaTra(new DmKhoa());
		}
		if (phieuTra.getDmdoituongMaso() == null) {
			phieuTra.setDmdoituongMaso(new DmDoiTuong());
		}
		
		/*if (phieuTra.getDmloaithuocMaso() == null) {
			phieuTra.setDmloaithuocMaso(new DmLoaiThuoc());
		}*/
		if (phieuTra.getDtdmnhanvienBacsi() == null) {
			phieuTra.setDtdmnhanvienBacsi(new DtDmNhanVien());
		}
		if (phieuTra.getDtdmnhanvienCn() == null) {
			phieuTra.setDtdmnhanvienCn(new DtDmNhanVien());
		}
		/*if (phieuTra.getDtdmnhanvienNhan() == null) {
			phieuTra.setDtdmnhanvienNhan(new DtDmNhanVien());
		}*/
		if (phieuTra.getDtdmnhanvienPhat() == null) {
			phieuTra.setDtdmnhanvienPhat(new DtDmNhanVien());
		}
	}
	private void clearInfor(){
		log.info("***** begin clearInfor() *****") ;
		if ("".equals(Utils.reFactorString(phieuTra.getDmkhoaNhan().getDmkhoaMaso()))) {
			phieuTra.setDmkhoaNhan(new DmKhoa());
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDmkhoaTra().getDmkhoaMaso()))) {
			phieuTra.setDmkhoaTra(new DmKhoa());
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDmdoituongMaso().getDmdoituongMaso() ))) {
			phieuTra.setDmdoituongMaso(null);
		}
		//if ("".equals(Utils.reFactorString(phieuTra.getDmloaithuocMaso().getDmloaithuocMaso() ))) {
			//phieuTra.setDmloaithuocMaso(null);
		//}
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienBacsi().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienBacsi(null);
		}
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienCn().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienCn(null);
		}
		/*if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienNhan().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienNhan(null);
		}*/
		if ("".equals(Utils.reFactorString(phieuTra.getDtdmnhanvienPhat().getDtdmnhanvienMaso() ))) {
			phieuTra.setDtdmnhanvienPhat(null);
		}
		log.info("***** end clearInfor() *****") ;
	}

	public void setPhieuTra(PhieuTraKho phieuXuat) {
		this.phieuTra = phieuXuat;
	}

	public PhieuTraKho getPhieuTra() {
		return phieuTra;
	}

	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public String getNgayXuat() {
		return ngayXuat;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setDmtMa(String dmtMa) {
		this.dmtMa = dmtMa;
	}

	public String getDmtMa() {
		return dmtMa;
	}

	public void setTonkho(Double tonkho) {
		this.tonkho = tonkho;
	}

	public Double getTonkho() {
		return tonkho;
	}

	public void setXuat(Double xuat) {
		this.xuat = xuat;
	}

	public Double getXuat() {
		return xuat;
	}

	public void setTonkhoMa(String tonkhoMa) {
		this.tonkhoMa = tonkhoMa;
	}

	public String getTonkhoMa() {
		return tonkhoMa;
	}

	public void setUpdateItem(Integer updateItem) {
		this.updateItem = updateItem;
	}

	public Integer getUpdateItem() {
		return updateItem;
	}

	public void setNgayHienTai(String ngayHienTai) {
		this.ngayHienTai = ngayHienTai;
	}

	public String getNgayHienTai() {
		return ngayHienTai;
	}

	public static Logger getLogger() {
		return log;
	}

	public static void setLogger(Logger logger) {
		KhoLeTraHangAction.log = logger;
	}

	public ArrayList<CtTraKhoExt> getListCtXuatKhoEx() {
		return listCtKhoLeTraEx;
	}

	public void setListCtXuatKhoEx(ArrayList<CtTraKhoExt> listCtTraKhoEx) {
		this.listCtKhoLeTraEx = listCtTraKhoEx;
	}

	public CtTraKhoExt getSelected() {
		return selected;
	}

	public void setSelected(CtTraKhoExt selected) {
		this.selected = selected;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setKpMa(String kpMa) {
		this.kpMa = kpMa;
	}

	public String getKpMa() {
		return kpMa;
	}

	public void setNguonMa(String nguonMa) {
		this.nguonMa = nguonMa;
	}

	public String getNguonMa() {
		return nguonMa;
	}

	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}

	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}

	public String getMalk() {
		return malk;
	}

	public void setMalk(String malk) {
		this.malk = malk;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	
	public String getIsFound() {
		return isFound;
	}

	public void setIsFound(String notFound) {
		this.isFound = notFound;
	}

	public TonKho createTonKhoTra(CtTraKhoExt ct) {
		TonKho tk = new TonKho();
		try {
		tk=TonKhoUtil.getTonKhoHienTai(ct.getCtTraKho().getCttrakhoMalk(), phieuTra.getDmkhoaTra().getDmkhoaMaso());
		tk.setDtdmnhanvienCn(phieuTra.getDtdmnhanvienCn());
		tk.setTonkhoNhap(null);
		tk.setTonkhoXuat(ct.getCtTraKho().getCttrakhoSoluong());
		tk.setTonkhoTra(null);
		tk.setTonkhoNgaygiocn(new Date());
		tk.setTonkhoMa(null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//}
		return tk;
	}
	
	public TonKho createTonKhoNhan(CtTraKhoExt ct) {
		TonKho tk = new TonKho();
		try {			
			tk=TonKhoUtil.getTonKhoHienTai(ct.getCtTraKho().getCttrakhoMalk(), phieuTra.getDmkhoaNhan().getDmkhoaMaso());
			if(tk != null){
				tk.setDtdmkhoMaso(null);
				tk.setDtdmnhanvienCn(phieuTra.getDtdmnhanvienCn());
				tk.setTonkhoHienthi(true);
				tk.setTonkhoNhap(null);
				tk.setTonkhoXuat(null);
				tk.setTonkhoTra(ct.getCtTraKho().getCttrakhoSoluong());
				Date dNgayCn = new Date();
				tk.setTonkhoMa(null);
				tk.setTonkhoNgaygiocn(dNgayCn);
			}
			
		} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		//}
		return tk;
	}
	
	
	
	public class CtTraKhoExt implements Serializable{
		private static final long serialVersionUID = 0L;
		private CtTraKho ctTraKho;
		private TonKho tonKho;
		private TonKho tonKhoTra;
		private TonKho tonKhoNhan;
		private Double thanhTien;
		public Double getThanhTien() {
			return thanhTien;
		}
		public void setThanhTien(Double thanhTien) {
			this.thanhTien = thanhTien;
		}
		public CtTraKhoExt(){
			ctTraKho = new CtTraKho();
			tonKho = new TonKho();
			SetInforUtil.setInforIfNullForTonKho(tonKho);
			if (tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
				tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
			}
			thanhTien = new Double(0);
			
		}
		public CtTraKho getCtTraKho() {
			if(ctTraKho==null) ctTraKho=new CtTraKho();
			return ctTraKho;
		}
		public void setCtTraKho(CtTraKho ctTraKho) {
			this.ctTraKho = ctTraKho;
		}
		public TonKho getTonKho() {
			if(tonKho==null) tonKho=new TonKho();
			return tonKho;
		}
		public void setTonKho(TonKho tonKho) {
			this.tonKho = tonKho;
		}
		public TonKho getTonKhoTra() {
			if(tonKhoTra==null) tonKhoTra=new TonKho();
			return tonKhoTra;
		}
		public void setTonKhoTra(TonKho tonKhoTra) {
			this.tonKhoTra = tonKhoTra;
		}
		public TonKho getTonKhoNhan() {
			if(tonKhoNhan==null) tonKhoNhan=new TonKho();
			return tonKhoNhan;
		}
		public void setTonKhoNhan(TonKho tonKhoNhan) {
			this.tonKhoNhan = tonKhoNhan;
		}
	}
	
}
