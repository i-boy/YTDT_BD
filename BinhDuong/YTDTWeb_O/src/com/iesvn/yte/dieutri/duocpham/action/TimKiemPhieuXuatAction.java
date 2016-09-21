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
import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Scope(ScopeType.CONVERSATION)
@Name("Timkiemphieuxuat")
public class TimKiemPhieuXuatAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger logger = Logger.getLogger(TimKiemPhieuXuatAction.class);
	
	@In(required = false)
	@Out(required = false)
	private String tenChuongTrinh;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private PhieuXuatKho pxk;
	
	private Integer loaithuocMaso=0;
	private String loaithuocMa;
	private String ngayHt;
	private String phieuDuTru="";
	
	private Integer count;
	private String maPhieu;
	private String ngayLap;
	private String ngaygiocn;
	private String nhanvienLapphieuMa;
	private Integer nhanvienLapphieuMaso;
	private String nhanvienNhapphieuMa;
	private Integer nhanvienNhapphieuMaso;
	private String khoaNhanMa;
	private Integer khoaNhanMaSo;
	private DmKhoa dmKhoaXuat = new DmKhoa();
	
	@DataModel
	private List<PhieuXuatKho> listResultPhieuXuat;
	@DataModel
	private ArrayList<CtXuatKho> listCtXuatKho;
	
	DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
	
	@Begin(join=true)
	public String init(String loaiKho) {
		logger.info("-----init()-----");
		reset();
		if (loaiKho.equals("KC")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;			
			dmKhoaXuat = (DmKhoa)dtDel.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
		}else if (loaiKho.equals("NT")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			dmKhoaXuat = (DmKhoa)dtDel.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
		}else if (loaiKho.equals("BHYT")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			dmKhoaXuat = (DmKhoa)dtDel.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
		}else if (loaiKho.equals("TE")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
			dmKhoaXuat = (DmKhoa)dtDel.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
		}
		
		return "QuanLyKhoChinh_NhapKhoChinh_TimKiemPhieuXuat";
	}
	
	public void reset() {
		pxk = new PhieuXuatKho();
		ngayHt = Utils.getCurrentDate();
		listResultPhieuXuat = new ArrayList<PhieuXuatKho>();
		listCtXuatKho = new ArrayList<CtXuatKho>();
		maPhieu = "";
		ngayLap="";
		ngaygiocn="";
	}
	
	public void loadPhieuXuat() {
		logger.info("-----loadPhieuXuat()-----");		
		if (!maPhieu.equals("")) {
			PhieuXuatKhoDelegate pxkDelegate = PhieuXuatKhoDelegate.getInstance();
			CtXuatKhoDelegate ctDelegate = CtXuatKhoDelegate.getInstance();
			try {
				PhieuXuatKho pxk_temp = pxkDelegate.findPhieuXuatKhoByKhoXuat(maPhieu, dmKhoaXuat.getDmkhoaMaso());
				if (pxk_temp != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					if(pxk_temp.getPhieuxuatkhoNgaygiocn() != null) {
						ngaygiocn = df.format(pxk_temp.getPhieuxuatkhoNgaygiocn());
					} else {
						ngaygiocn = "";
					}				
					listCtXuatKho = (ArrayList<CtXuatKho>) ctDelegate.findByphieuxuatkhoMa(maPhieu);
					count = listCtXuatKho.size();
					pxk = pxk_temp;
					ngayLap = df.format(pxk.getPhieuxuatkhoNgaylap());
					
				} else {
					FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, maPhieu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selectPhieuXuat(Integer index) {
		logger.info("-----selectPhieuXuat()-----");
		logger.info("-----index: " + index);
		pxk = listResultPhieuXuat.get(index.intValue());
		maPhieu = pxk.getPhieuxuatkhoMa();	
		loadPhieuXuat();
	}

	public void timkiem() {
		logger.info("-----timkiem()-----");
		listCtXuatKho = new ArrayList<CtXuatKho>();
		pxk = new PhieuXuatKho();
		PhieuXuatKhoDelegate pxkDelegate = new PhieuXuatKhoDelegate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayHD = new Date();
		try{
			if (!ngayHt.equals(""))
				ngayHD = df.parse(ngayHt);
			else ngayHD = null;
		}catch(ParseException e)
		{
			FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_FORMATDATE, ngayHt);
			e.printStackTrace();
		}
			
		if (loaithuocMaso == null || loaithuocMaso.equals(null))
			loaithuocMaso = 0;		
		if (nhanvienLapphieuMaso == null || nhanvienLapphieuMaso.equals(null))
			nhanvienLapphieuMaso = 0;
		if (nhanvienNhapphieuMaso == null || nhanvienNhapphieuMaso.equals(null))
			nhanvienNhapphieuMaso = 0;
		if (khoaNhanMaSo == null || khoaNhanMaSo.equals(null))
			khoaNhanMaSo = 0;
		
		List<PhieuXuatKho>  lstPhieuXuatKho = pxkDelegate.find(maPhieu, ngayHD, loaithuocMaso, nhanvienLapphieuMaso, nhanvienNhapphieuMaso, khoaNhanMaSo, dmKhoaXuat.getDmkhoaMaso());
		if (lstPhieuXuatKho != null)
		{
			logger.info("lstPhieuXuatKho = " +lstPhieuXuatKho.size());
			listResultPhieuXuat = lstPhieuXuatKho;				
		}
	}
	
	public PhieuXuatKho getPxk() {
		return pxk;
	}

	public void setPxk(PhieuXuatKho pxk) {
		this.pxk = pxk;
	}	
	
	public ArrayList<CtXuatKho> getListCtXuatKho() {
		return listCtXuatKho;
	}

	public void setListCtXuatKho(ArrayList<CtXuatKho> listCtXuatKho) {
		this.listCtXuatKho = listCtXuatKho;
	}

	public List<PhieuXuatKho> getListResultPhieuXuat() {
		return listResultPhieuXuat;
	}

	public void setListResultPhieuXuat(List<PhieuXuatKho> listResultPhieuXuat) {
		this.listResultPhieuXuat = listResultPhieuXuat;
	}

	public String getNgayHt() {
		return ngayHt;
	}

	public void setNgayHt(String ngayHt) {
		this.ngayHt = ngayHt;
	}
	
	public String getPhieuDuTru() {
		return phieuDuTru;
	}

	public void setPhieuDuTru(String phieuDuTru) {
		this.phieuDuTru = phieuDuTru;
	}
	
	public Integer getNhanvienNhapphieuMaso() {
		return nhanvienNhapphieuMaso;
	}

	public void setNhanvienNhapphieuMaso(Integer nhanvienNhapphieuMaso) {
		this.nhanvienNhapphieuMaso = nhanvienNhapphieuMaso;
	}
	
	public String getNhanvienNhapphieuMa() {
		return nhanvienNhapphieuMa;
	}

	public void setNhanvienNhapphieuMa(String nhanvienNhapphieuMa) {
		this.nhanvienNhapphieuMa = nhanvienNhapphieuMa;
	}
	
	public Integer getNhanvienLapphieuMaso() {
		return nhanvienLapphieuMaso;
	}

	public void setNhanvienLapphieuMaso(Integer nhanvienLapphieuMaso) {
		this.nhanvienLapphieuMaso = nhanvienLapphieuMaso;
	}
	
	public String getNhanvienLapphieuMa() {
		return nhanvienLapphieuMa;
	}

	public void setNhanvienLapphieuMa(String nhanvienLapphieuMa) {
		this.nhanvienLapphieuMa = nhanvienLapphieuMa;
	}
	
	public String getKhoaNhanMa() {
		return khoaNhanMa;
	}
	
	public void setKhoaNhanMa(String khoaNhanMa) {
		this.khoaNhanMa = khoaNhanMa;
	}
	
	public Integer getKhoaNhanMaSo() {
		return khoaNhanMaSo;
	}

	public void setKhoaNhanMaSo(Integer khoaNhanMaSo) {
		this.khoaNhanMaSo = khoaNhanMaSo;
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
	
	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	public String getNgaygiocn() {
		return ngaygiocn;
	}

	public void setNgaygiocn(String ngaygiocn) {
		this.ngaygiocn = ngaygiocn;
	}
}
