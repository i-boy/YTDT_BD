/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;


import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.util.IConstantsRes;

@Name("B4143_Taobangkiemke")
@Scope(CONVERSATION)
public class Taobangkiemke  implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String resetFormB4143=null;
	
	@In(required = false)
	@Out(required = false)
	private DmKhoa dmKhoaKhoOut;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	@In(required = false)
	@Out(required = false)
	private KiemKe kiemkeOut;
	
	@In(required = false)
	@Out(required = false)
	private String trangthaittOut;
	
	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList<DmPhanLoaiThuoc>();

	@DataModelSelection("listDmPLThuocKK")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelectKK;
	private int index=0;
	private String ngayhientai;
	private Integer loaihang_maso=null;
	private Integer plthuoc_maso=null;
	private Integer khoa_maso=null;
	private Boolean chon=false;
	private String plthuoc_ma="";
	private String loaihang_ma="";
	private String khoa_ma="";
	Map<String, Object> params = null;
	private String maKiemKe;
	
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
	@Factory("resetFormB4143")
	public void initresetFormB4143() {
		log.info("init()");			
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		resetForm();
	}
	@Begin (join = true)
	public String init() {		
		resetList();
		return "QuanLyKhoChinh_KiemKeKhoChinh_TaoBangKiemKeDinhKy";
	}
	@End
	public void end(){
		
	}
	
	public String quayve() throws Exception{
		resetForm();
		return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
	}
	
	
	
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetList(){
		log.info("=============reset listttttttttttt " + listDmPLThuocKK.size());
		if(listDmPLThuocKK.size() > 0){
			DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
			dmplt = listDmPLThuocKK.get(0);
			log.info(dmplt);
			if(!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(loaihang_ma)){
				listDmPLThuocKK.clear();
				setPlthuoc_ma("");
			}
		}
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		maKiemKe = "";
		loaihang_ma = "";
	}
		
	/**
	 * Method tra ve String truyen tham so vao file jrxml
	 */
	private String getListDVMaParamsHelp(List<String> inputs){
        log.info("Vao method getListDVMaParamsHelp ");
        log.info("Inputs: " + inputs.toString());
        String result = "";
        for(String each : inputs){
                if(each != "")
                        result += "'"+each + "',";
        }
        result = result.substring(0, result.length()-1);
        log.info("Thoat method getListDVMaParamsHelp ");
        log.info("Output: " + result);
        return result;
	}
	
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){
		setPlthuoc_ma("");
		listDmPLThuocKK.clear();
		setLoaihang_ma("");
		resetFormB4143 = "";
		ngayhientai = com.iesvn.yte.util.Utils.getCurrentDate();
		maKiemKe = "";
		loaihang_ma = "";
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
		
	/**
	 * Ham thuc hien cap nhat bang kiem ke
	 * @return
	 */
	public String thuchienAction(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
		try{
			DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
			DtDmNhanVien nv = nvDelegate.findByNd(identity.getUsername());
			if(nv == null){
				FacesMessages.instance().add(IConstantsRes.USER_MAP_NV, identity.getUsername());
				return null;
			}
			String listPl="(";
			String loaiMa = loaihang_ma;
			DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
			System.out.println("loaiMa: "+loaiMa);
			if(loaihang_ma.trim().equals("")){
				setLoaihang_ma(null);
				dmloaithuoc = null;
			}else{
				dmloaithuoc = (DmLoaiThuoc) dieutriUtilDel.findByMa(loaihang_ma, "DmLoaiThuoc", "dmloaithuocMa");
			}
			if(khoa_ma.trim().equals("")){
				setKhoa_ma(null);
			}
			if(listDmPLThuocKK.size()>0){
				setLoaihang_ma(null);
				List<String> listtemp=new ArrayList<String>();
				for(DmPhanLoaiThuoc item:listDmPLThuocKK){
					listtemp.add(item.getDmphanloaithuocMa());
				}				
				listPl=listPl+getListDVMaParamsHelp(listtemp)+")";
				log.info("list phan loai " + listPl);
			}else{
				listPl="('')";				
			}			
			
			khoa_ma = dmKhoaKhoOut.getDmkhoaMa();
			
			//Kiem tra, neu dang ton tai 1 kiem ke voi cung loai hoac bao ham loai do trong DB voi trang thai dang kiem ke thi khong cho kiem ke nua
			KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
			boolean isExistedKiemKe = kiemkeDel.isExistedKiemKe(loaihang_ma, dmKhoaKhoOut.getDmkhoaMaso());
			if(isExistedKiemKe){
				FacesMessages.instance().add(IConstantsRes.KIEMKE_DANG_KIEM_KE);
				return null;
			}
			//Kiem tra xem co ton thuoc khong
			System.out.println("khoa_ma: "+khoa_ma);
	        System.out.println("listPl: "+listPl);
	        System.out.println("loaiMa: "+loaiMa);
	        System.out.println("kecaTonBangKhong: "+chon.booleanValue());
			TonKhoDelegate tonkhoDAO = TonKhoDelegate.getInstance();
			long countTonKhoList =tonkhoDAO.getTotalListTonKhoForKiemKe(khoa_ma ,listPl, loaiMa, chon.booleanValue());
			if (countTonKhoList == 0){
				FacesMessages.instance().add(IConstantsRes.KIEMKE_KHONG_CO_THUOC);
				return null;
			}
			log.info("list ton kho " + countTonKhoList);
			
			KiemKe kiemke = new KiemKe();
			kiemke.setDmkhoaMaso(dmKhoaKhoOut);
			kiemke.setDmloaithuocMaso(dmloaithuoc);
			kiemke.setDtdmnhanvienCn(nv);
			kiemke.setDtdmnhanvienKiemke(nv);
			kiemke.setMaphieukiem(null);
			kiemke.setNgaygiocn(new Date());
			kiemke.setNgaykiemke(new Date());
			kiemke.setTrangthai("OPEN");
			
			String resultInsert = kiemkeDel.taoBangKiemKe(kiemke, listPl, listDmPLThuocKK, loaihang_ma, chon.booleanValue());
			if(resultInsert != null){
				maKiemKe = resultInsert;
				FacesMessages.instance().add("T\u1EA1o th\u00E0nh c\u00F4ng phi\u1EBFu ki\u1EC3m k\u00EA ");	
				kiemke.setMaphieukiem(maKiemKe);
				kiemkeOut = kiemke;
				trangthaittOut = "OPEN";
			}else{
				FacesMessages.instance().add(resultInsert);
				return null;
			}	
			resetFormB4143 =null;
		}catch(Exception ex){
			log.info("That bai ");
			ex.printStackTrace();
		}
		return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
	}
	
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
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

	public Integer getKhoa_maso() {
		return khoa_maso;
	}

	public void setKhoa_maso(Integer khoa_maso) {
		this.khoa_maso = khoa_maso;
	}

	public Boolean getChon() {
		return chon;
	}

	public void setChon(Boolean chon) {
		this.chon = chon;
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
	
	public String getKhoa_ma() {
		return khoa_ma;
	}

	public void setKhoa_ma(String khoa_ma) {
		this.khoa_ma = khoa_ma;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
