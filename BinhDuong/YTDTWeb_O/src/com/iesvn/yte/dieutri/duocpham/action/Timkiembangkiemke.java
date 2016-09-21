/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;


import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.ServiceException;


import org.apache.commons.beanutils.BeanUtils;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("Timkiembangkiemke")
@Scope(CONVERSATION)
public class Timkiembangkiemke  implements Serializable {
	@Logger
	private Log log;
		
	@Out(required=false)
	@In(required=false)
	private String resetFormTimKiemKiemKe=null;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList<DmPhanLoaiThuoc>();
	@DataModel
	private List<KiemKe> listResultBangKiemKe = new ArrayList<KiemKe>();

	@DataModelSelection("listDmPLThuocKK")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelectKK;
	
	private int index=0;
	private String ngaykiemkeTu;
	private String ngaykiemkeDen;
	private Integer loaihang_maso=null;
	private Integer plthuoc_maso=null;
	private String plthuoc_ma="";
	private String loaihang_ma="";
	private String maKiemKe;
	private String tenChuongTrinh;
	private String trangthai;
	
	@In(required = false)
	@Out(required = false)
	private DmKhoa dmKhoaKhoOut;
	
	@In(required = false)
	@Out(required = false)
	private KiemKe kiemkeOut;
	
	@In(required = false)
	@Out(required = false)
	private String trangthaittOut;
	
	public String getMaKiemKe() {
		return maKiemKe;
	}
	public void setMaKiemKe(String maKiemKe) {
		this.maKiemKe = maKiemKe;
	}


	String dmKhoXuat = "";	
	
	public String getDmKhoXuat() {
		return dmKhoXuat;
	}
	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}
	@Factory("resetFormTimKiemKiemKe")
	public void initresetFormTimKiemKiemKe() {
		log.info("initresetFormTimKiemKiemKe");
		resetForm();
	}
	@Begin (join = true)
	public String init(String loaiKho) {
		reset();
		resetFormTimKiemKiemKe ="";
		String maLoaiKho="";
		if (loaiKho.equals("KC")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
			maLoaiKho = IConstantsRes.KHOA_KC_MA;
		}
		else if (loaiKho.equals("KhoNoiTru")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
			maLoaiKho = IConstantsRes.KHOA_NT_MA;
		}
		else if (loaiKho.equals("BHYT")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
			maLoaiKho = IConstantsRes.KHOA_BHYT_MA;
		}
		else if (loaiKho.equals("TE")){
			tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
			maLoaiKho = IConstantsRes.KHOA_TE_MA;
		}
		DieuTriUtilDelegate dtriUtil = DieuTriUtilDelegate.getInstance();
		dmKhoaKhoOut = (DmKhoa)dtriUtil.findByMa(maLoaiKho, "DmKhoa", "dmkhoaMa");
		return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
	}
	@End
	public void end(){
		
	}
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void reset(){
		log.info("=============reset==============");
		if(listDmPLThuocKK.size() > 0){
			DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
			dmplt = listDmPLThuocKK.get(0);
			log.info(dmplt);
			if(!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(loaihang_ma)){
				listDmPLThuocKK.clear();
				setPlthuoc_ma("");
			}
		}
		ngaykiemkeTu = Utils.getCurrentDate();
		ngaykiemkeDen = Utils.getCurrentDate();
		maKiemKe = "";
		loaihang_ma = "";
		loaihang_maso = null;
		trangthai = "";
		listResultBangKiemKe = new ArrayList<KiemKe>();
	}
	
	public void timkiemAction() {
		log.info("-----timkiem()-----");
		listResultBangKiemKe = new ArrayList<KiemKe>();		
		KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date ngayKiemKeTu = new Date();
		Date ngayKiemKeDen = new Date();
		
		try{
			if (!ngaykiemkeTu.equals(""))
				ngayKiemKeTu = df.parse(ngaykiemkeTu);
			else ngayKiemKeTu = null;
			if (!ngaykiemkeDen.equals(""))
				ngayKiemKeDen = df.parse(ngaykiemkeDen);
		}catch(ParseException e){
			FacesMessages.instance().add("Ngay khong dung", ngaykiemkeTu);
			e.printStackTrace();
		}	
		List<KiemKe> lstKiemKe = kiemkeDelegate.findByDieuKienTimKiem(maKiemKe, ngayKiemKeTu, ngayKiemKeDen, dmKhoaKhoOut.getDmkhoaMaso(), loaihang_maso, listDmPLThuocKK, trangthai);
		if (lstKiemKe == null){
			FacesMessages.instance().add("Kh\u00F4ng t\u00ECm th\u1EA5y ki\u1EC3m k\u00EA n\u00E0o.", maKiemKe);							
		}else{
			log.info("lstKiemKe = " +lstKiemKe.size());
			listResultBangKiemKe = lstKiemKe;
		}	
		log.info("-----End timkiem()-----");
	}
	
	public String taomoiKiemKe() throws ServiceException, RemoteException{
		resetFormTimKiemKiemKe = null;
		return "QuanLyKhoLe_KiemKeKhoChinh_TaoBangKiemKeDinhKy";
	}
	
	public String getListDmphanloaithuoc(String maPhieukiem){
		KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
		String lstPLThuoc = kiemkeDelegate.getListPhanLoaiThuocMa(maPhieukiem);
		return lstPLThuoc;
	}
	
		
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setPlthuoc_ma("");
		listDmPLThuocKK.clear();
		listResultBangKiemKe.clear();
		setLoaihang_ma("");
		resetFormTimKiemKiemKe = "";
		ngaykiemkeTu = Utils.getCurrentDate();
		ngaykiemkeDen = Utils.getCurrentDate();
		maKiemKe = "";
		timkiemAction();
	}
	
	/**
	 * ham them mot phan tu vao DS
	 */
	public void enter(){
		log.info("bat dau them du lieu vao luoi");
		DmPhanLoaiThuoc plThuoc;
		boolean test=false;
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		if(!plthuoc_ma.equals("")){
			if((listDmPLThuocKK.size()==0) && (plthuoc_ma !=null)){
				log.info("size list bang 0");
				Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
				plThuoc=(DmPhanLoaiThuoc)obj;
				listDmPLThuocKK.add(plThuoc);
				log.info("da add phan tu dau tien" + listDmPLThuocKK.size());
			}else if ((listDmPLThuocKK.size()>0) && (plthuoc_ma !=null)){
				log.info("size list lon hon 0");
				for(DmPhanLoaiThuoc item:listDmPLThuocKK){
					if(item.getDmphanloaithuocMa().equals(plthuoc_ma)){
						test=true;
					}
				}
				if(test == false){
					Object obj=dtutilDelegate.findByMa(plthuoc_ma , "DmPhanLoaiThuoc", "dmphanloaithuocMa");
					plThuoc=(DmPhanLoaiThuoc)obj;
					listDmPLThuocKK.add(plThuoc);
				}
				log.info("da add phan tu" + listDmPLThuocKK.size());
			}
		}
		setPlthuoc_ma("");
	}
	
	
	/**
	 * Ham xoa mot record
	 */
	public void deletedmPLThuoc(){
		log.info("bat dau xoa , size" + listDmPLThuocKK.size());
		listDmPLThuocKK.remove(dmPLThuocSelectKK);
		log.info("da xoa , size" + listDmPLThuocKK.size());
		log.info("ket thuc xoa");
	}
	
	public String selectBangKiemKe(Integer index) {
		log.info("-----selectBangKiemKe()-----");
		log.info("-----index: " + index);
		kiemkeOut = listResultBangKiemKe.get(index);
		trangthaittOut = kiemkeOut.getTrangthai();
		resetFormTimKiemKiemKe = null;
		return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
	}
	
	public String formatDateTime(Date ngaygio){
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String strDatetime = sf.format(ngaygio);
		return strDatetime;
	}
	
	public String getTenLoaiThuoc(String tenLoaiThuoc){
		if(tenLoaiThuoc.trim().equals("") || tenLoaiThuoc == null){
			return "ALL";			
		}else{
			return tenLoaiThuoc;
		}
	}
	
	public String viewGUI(String trangthai){
		if(trangthai.equals("OPEN")){
			return "\u0110ang ki\u1EC3m k\u00EA";
		}else{
			return "\u0110\u00E3 ho\u00E0n t\u1EA5t";
		}
	}
	
	public String getNgaykiemkeTu() {
		return ngaykiemkeTu;
	}

	public void setNgaykiemkeTu(String ngaykiemkeTu) {
		this.ngaykiemkeTu = ngaykiemkeTu;
	}
	
	public String getNgaykiemkeDen() {
		return ngaykiemkeDen;
	}

	public void setNgaykiemkeDen(String ngaykiemkeDen) {
		this.ngaykiemkeDen = ngaykiemkeDen;
	}

	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihang_maso) {
		this.loaihang_maso = loaihang_maso;
	}

	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuoc_maso) {
		this.plthuoc_maso = plthuoc_maso;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuoc_ma) {
		this.plthuoc_ma = plthuoc_ma;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihang_ma) {
		this.loaihang_ma = loaihang_ma;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	 
	public List<KiemKe> getListResultBangKiemKe() {
		return listResultBangKiemKe;
	}

	public void setListResultBangKiemKe(List<KiemKe> listResultBangKiemKe) {
		this.listResultBangKiemKe = listResultBangKiemKe;
	}
}
