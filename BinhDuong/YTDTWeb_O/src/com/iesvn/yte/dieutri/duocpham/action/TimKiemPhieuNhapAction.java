package com.iesvn.yte.dieutri.duocpham.action;

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
import java.util.List;

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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(ScopeType.CONVERSATION)
@Name("Timkiemphieunhap")
public class TimKiemPhieuNhapAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(TimKiemPhieuNhapAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private PhieuNhapKho pnk;
	
	private Integer loaithuocMaso=0;
	private String loaithuocMa;
	private String ngayHt;
	private String soChungTu="";
	private String soHD="";
	
	private Integer count;
	private String maPhieu;
	private Double thanhTienThue;
	private String ngayHoaDon;
	private String ngaygiocn;
	@DataModel
	private List<PhieuNhapKho> listResultPhieuNhap;
	@DataModel
	private ArrayList<CtNhapKho> listCtNhapKho;
	
	
	@Begin(join=true)
	public String init(String loaiKho) {
		logger.info("-----init()-----");
		reset();
		if (loaiKho.equals("KC"))
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
		else if (loaiKho.equals("KhoNoiTru"))
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
		else if (loaiKho.equals("BHYT"))
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
		else if (loaiKho.equals("TE"))
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
		return "QuanLyKhoChinh_NhapKhoChinh_TimKiemPhieuNhap";
	}
	
	public void reset() {
		pnk = new PhieuNhapKho();
		ngayHt = Utils.getCurrentDate();
		listResultPhieuNhap = new ArrayList<PhieuNhapKho>();
		listCtNhapKho = new ArrayList<CtNhapKho>();
		maPhieu = "";
		ngayHoaDon="";
		ngaygiocn="";
		thanhTienThue= new Double(0);
	}
	
	public void loadPhieuNhap() {
		logger.info("-----loadPhieuNhap()-----");
		if (!maPhieu.equals("")) {
			PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
			CtNhapKhoDelegate ctDelegate = CtNhapKhoDelegate.getInstance();
			try {
				PhieuNhapKho pnk_temp = pnkDelegate.findByPhieunhapkhoMa(maPhieu);
				if (pnk_temp != null) {
					maPhieu = pnk_temp.getPhieunhapkhoMa();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					ngayHt = df.format(pnk_temp.getPhieunhapkhoNgaygiocn());
					listCtNhapKho = (ArrayList<CtNhapKho>) ctDelegate.findByPhieuNhapKho(maPhieu);
					count = listCtNhapKho.size();					
					pnk = pnk_temp;
					ngayHoaDon = df.format(pnk.getPhieunhapkhoNgayhoadon());
					ngaygiocn = df.format(pnk.getPhieunhapkhoNgaygiocn());
					tinhTienThue();
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, maPhieu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selectPhieuNhap(Integer index) {
		logger.info("-----selectPhieuNhap()-----");
		logger.info("-----index: " + index);
		pnk = listResultPhieuNhap.get(index.intValue());
		maPhieu = pnk.getPhieunhapkhoMa();
		loadPhieuNhap();
	}

	public void timkiem() {
		logger.info("-----timkiem()-----");
		listCtNhapKho = new ArrayList<CtNhapKho>();
		pnk = new PhieuNhapKho();
		if (tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh))
		{
			PhieuNhapKhoDelegate pnkDelegate = new PhieuNhapKhoDelegate();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date ngayHD = new Date();
			try{
				if (!ngayHt.equals(""))
					ngayHD = df.parse(ngayHt);
				else ngayHD = null;
			}catch(ParseException e)
			{
				FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_FORMATDATE, ngayHt);
				e.printStackTrace();
			}
			
			if (loaithuocMaso == null || loaithuocMaso.equals(null))
				loaithuocMaso = 0;		
			List<PhieuNhapKho>  lstPhieuNhapKho = pnkDelegate.find(maPhieu, ngayHD, loaithuocMaso, soChungTu, soHD);
			if (lstPhieuNhapKho != null || !lstPhieuNhapKho.equals(null))
			{
				logger.info("lstPhieuNhapKho = " +lstPhieuNhapKho.size());
				listResultPhieuNhap = lstPhieuNhapKho;
				
			}else {
				FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, null);
			}
			
		}
	}
	
public void tinhTienThue() {
		
		if (pnk == null){
			return;
		}		
		
		logger.info("-----tinhTienThue()-----");
		Double mucThue = pnk.getPhieunhapkhoMucthue();
		if (mucThue == null) {
			mucThue = new Double("0");
		}
		Double thanhTien = pnk.getPhieunhapkhoThanhtien();
		Double thueGtgt = (thanhTien * mucThue) / 100;
		thanhTienThue = thanhTien + thueGtgt;
		logger.info("-----thueGtgt: " + thueGtgt);
		logger.info("-----thanhTienThue: " + thanhTienThue);
	}
	
	public PhieuNhapKho getPnk() {
		return pnk;
	}

	public void setPnk(PhieuNhapKho pnk) {
		this.pnk = pnk;
	}	
	
	public ArrayList<CtNhapKho> getListCtNhapKho() {
		return listCtNhapKho;
	}

	public void setListCtNhapKho(ArrayList<CtNhapKho> listCtNhapKho) {
		this.listCtNhapKho = listCtNhapKho;
	}

	public List<PhieuNhapKho> getListResultPhieuNhap() {
		return listResultPhieuNhap;
	}

	public void setListResultPhieuNhap(List<PhieuNhapKho> listResultPhieuNhap) {
		this.listResultPhieuNhap = listResultPhieuNhap;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}
	
	public String getSoChungTu() {
		return soChungTu;
	}

	public void setSoChungTu(String soChungTu) {
		this.soChungTu = soChungTu;
	}
	
	public String getSoHD() {
		return soChungTu;
	}

	public void setSoHD(String soHD) {
		this.soHD = soHD;
	}
	
	public Integer getLoaiThuocMaSo() {
		return loaithuocMaso;
	}

	public void setLoaiThuocMaSo(Integer loaithuocMaso) {
		this.loaithuocMaso = loaithuocMaso;
	}
	
	public String getLoaiThuocMa() {
		return loaithuocMa;
	}

	public void setLoaiThuocMa(String loaithuocMa) {
		this.loaithuocMa = loaithuocMa;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}	
	
	public String getNgayHoaDon() {
		return ngayHoaDon;
	}

	public void setNgayHoaDon(String ngayHoaDon) {
		this.ngayHoaDon = ngayHoaDon;
	}
	
	public String getNgaygiocn() {
		return ngaygiocn;
	}

	public void setNgaygiocn(String ngaygiocn) {
		this.ngaygiocn = ngaygiocn;
	}
	
	public Double getThanhTienThue() {
		return thanhTienThue;
	}

	public void setThanhTienThue(Double thanhTienThue) {
		this.thanhTienThue = thanhTienThue;
	}
}
