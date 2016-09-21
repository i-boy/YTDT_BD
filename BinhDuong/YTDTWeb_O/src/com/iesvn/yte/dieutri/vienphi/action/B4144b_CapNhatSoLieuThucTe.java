package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B4144b_CapNhatSoLieuThucTe")
@Synchronized(timeout = 6000000)
public class B4144b_CapNhatSoLieuThucTe implements Serializable {

	private static final long serialVersionUID = -340255448614143263L;
	private static Logger log = Logger.getLogger(B4144b_CapNhatSoLieuThucTe.class);
	private String khoa;
	private String thuoc;
	private String quocgia;
	private String nsx;	
	private String namnhap;	
	private String nct;	
	private String nkp;	
	private String ngayhd;
	private String ngaykiem;
	private String maKiemKe;
	private String soDangKy;
	private String donVi;
	private Integer loaihang_maso=null;	
	private Integer plthuoc_maso=null;
	private String plthuoc_ma="";	
	private List<KiemKeKho> listKiemKeKho = new ArrayList<KiemKeKho>();
	private int index = 0;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC = null;
	
	Map<String, Object> params = null;
	
	public Integer getPlthuoc_maso() {
		return plthuoc_maso;
	}

	public void setPlthuoc_maso(Integer plthuocMaso) {
		plthuoc_maso = plthuocMaso;
	}

	public String getPlthuoc_ma() {
		return plthuoc_ma;
	}

	public void setPlthuoc_ma(String plthuocMa) {
		plthuoc_ma = plthuocMa;
	}

	@DataModel
	private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList<DmPhanLoaiThuoc>();

	@DataModelSelection("listDmPLThuocKK")
	@Out(required = false)
	private DmPhanLoaiThuoc dmPLThuocSelectKK;
	
	public List<DmPhanLoaiThuoc> getListDmPLThuocKK() {
		return listDmPLThuocKK;
	}

	public void setListDmPLThuocKK(List<DmPhanLoaiThuoc> listDmPLThuocKK) {
		this.listDmPLThuocKK = listDmPLThuocKK;
	}

	public DmPhanLoaiThuoc getDmPLThuocSelectKK() {
		return dmPLThuocSelectKK;
	}

	public void setDmPLThuocSelectKK(DmPhanLoaiThuoc dmPLThuocSelectKK) {
		this.dmPLThuocSelectKK = dmPLThuocSelectKK;
	}

	public Integer getLoaihang_maso() {
		return loaihang_maso;
	}

	public void setLoaihang_maso(Integer loaihangMaso) {
		loaihang_maso = loaihangMaso;
	}

	public String getLoaihang_ma() {
		return loaihang_ma;
	}

	public void setLoaihang_ma(String loaihangMa) {
		loaihang_ma = loaihangMa;
	}

	private String loaihang_ma="";
	public String getMaKiemKe() {
		return maKiemKe;
	}

	public void setMaKiemKe(String maKiemKe) {
		this.maKiemKe = maKiemKe;
	}

	public String getSoDangKy() {
		return soDangKy;
	}

	public void setSoDangKy(String soDangKy) {
		this.soDangKy = soDangKy;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getDonGia() {
		return donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}

	private String donGia;
	
	@DataModel
	private List<KiemKeKho> listB4144b;	
	
	String dmKhoXuat = "";
	
	@Begin (join = true)
	public String init(String kho) throws Exception {
		resetvalue();
		dmKhoXuat = kho;
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe_old";
	}
	
	@End
	public void end(){
		
	}
	public void resetvalue(){
		log.info("---resetValue");
		thuoc=quocgia=nsx=namnhap=nct=nkp=ngayhd="";
		ngaykiem = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		listB4144b = new ArrayList<KiemKeKho>();
		maKiemKe = "";
		listDmPLThuocKK.clear();
		plthuoc_ma = "";
		listKiemKeKho = new ArrayList<KiemKeKho>();
	}
	
	public void search(){
		log.info("---search");
		listB4144b = new ArrayList<KiemKeKho>();
		String ngay="",thang="",nam="";
		if (!ngayhd.trim().equals("")){
			String[] ntn = ngayhd.split("/");
			ngay = ntn[0];thang = ntn[1];nam = ntn[2];
		}
//		List<KiemKeKho> list = KiemKeKhoDelegate.getInstance().findKiemKeKhoForCapNhatSL(
//				khoa.equals("")?null:khoa, 
//				thuoc.equals("")?null:thuoc, 
//				nct.equals("")?null:nct, 
//				nkp.equals("")?null:nkp,
//				nsx.equals("")?null:nsx, 
//				quocgia.equals("")?null:quocgia, 
//				namnhap.equals("")?null:namnhap, ngay, thang, nam);
		List<KiemKeKho> list = new ArrayList<KiemKeKho>();
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		boolean temp = true;
		for(int i = 0; i < listKiemKeKho.size(); i++){	
			temp = false;
			KiemKeKho kkk = listKiemKeKho.get(i);			
			DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso() , "DmThuoc", "dmthuocMaso");
			DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso() , "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
			DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
			
			if(!khoa.equals("") && !kkk.getDmkhoaMaso().getDmkhoaMa().equals(khoa)){
				continue;
			}
			
			if(!nct.equals("") && !kkk.getDmnctMaso().getDmnctMa().equals(nct)){
				continue;
			}
			if(!nkp.equals("") && !kkk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa().equals(nkp)){
				continue;
			}
			if(!nsx.equals("") && !kkk.getDmnhasanxuatMaso().getDmnhasanxuatMa().equals(nsx)){
				continue;
			}
			if(!quocgia.equals("") && !kkk.getDmquocgiaMaso().getDmquocgiaMa().equals(quocgia)){
				continue;
			}
			if(!namnhap.equals("") && !kkk.getKiemkeNamnhap().equals(namnhap)){
				continue;
			}
			if(!ngay.equals("") && !kkk.getKiemkeNgayhandung().equals(ngay)){
				continue;
			}
			
			if(!thang.equals("") && !kkk.getKiemkeThanghandung().equals(thang)){
				continue;
			}
			if(!nam.equals("") && !kkk.getKiemkeNamhandung().equals(nam)){
				continue;
			}
			if(!loaihang_ma.equals("") && !dmLoaiThuoc.getDmloaithuocMa().equals(loaihang_ma)){
				continue;
			}
			
			if(listDmPLThuocKK.size() > 0){
				for(DmPhanLoaiThuoc item:listDmPLThuocKK){
					if(item.getDmphanloaithuocMa().equals(dmPhanLoaiThuoc.getDmphanloaithuocMa())){
						list.add(kkk);				
					}
				}
			}else{
				list.add(kkk);
			}		
			
		}
		
		if (list!=null && list.size()>0){
			
			listB4144b = list;
		}else{
			
			FacesMessages.instance().add(IConstantsRes.KKK_NULL);
		}
	}
	
	public void ghinhan(){
		log.info("---ghinhan");
		Date now = new Date();
		KiemKeKhoDelegate delegate = KiemKeKhoDelegate.getInstance();
		boolean result=true;
		for (KiemKeKho obj : listB4144b){
			if (obj.getKiemkeTontt()!=null) {
				//obj.setKiemkeNgaykiem(Utils.getDBDate("00:00", ngaykiem).getTime());
				obj.setKiemkeNgaykiem(now);
				obj.setKiemkeNgaygiocn(now);
				//obj.setKiemkeMaphieukiem(null);
				String malk = delegate.updateAndEditTonKhoDau(obj);
				if (malk.equals("")){
					result=false;
					break;
				}
			}			
		}
		//delegate.updateKiemKeKho(listKiemKeKho);
		result=true;
		if (result){
			FacesMessages.instance().add(IConstantsRes.KKK_CN_THANHCONG);
		}else{
			FacesMessages.instance().add(IConstantsRes.KKK_CN_THATBAI);
		}		
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
	}
	
	/**
	 * Hien thi phieu kiem ke len giao dien
	 */
	public void displayPhieuKiemKe() {
		log.info(" ***** displayPhieuKiemKe() ***** ");
		listB4144b = new ArrayList<KiemKeKho>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (!maKiemKe.equals("")) {
			log.info(String.format(" ***** Phieu kiem ke ma: %s", this.maKiemKe));

			try {
				KiemKeKhoDelegate kkkDel=KiemKeKhoDelegate.getInstance();
				List<KiemKeKho> listKkk = kkkDel.findByMaPhieuKiem(maKiemKe);
				if(listKkk != null && listKkk.size() > 0){
					listB4144b = listKkk;
					for(int i = 0; i < listB4144b.size(); i++){
						listB4144b.get(i).setKiemkeTontt(listB4144b.get(i).getKiemkeTon());
					}
				
					KiemKeKho kkk = listKkk.get(0);
					DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
					DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso() , "DmThuoc", "dmthuocMaso");
					DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso() , "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
					DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
					loaihang_ma = dmLoaiThuoc.getDmloaithuocMa();
					ngaykiem = sdf.format(kkk.getKiemkeNgaygiocn());			
					plthuoc_ma = "";
					quocgia = "";
					nsx = "";
					nct = "";
					nkp = "";
					ngayhd = "";
					soDangKy = "";
					donVi = "";
					donGia = "";
					listDmPLThuocKK.clear();
					listKiemKeKho = listB4144b;
				}else{
					FacesMessages.instance().add("Kh\u00F4ng t\u00ECm th\u1EA5y phi\u1EBFu ki\u1EC3m k\u00EA ");
				}
			}catch(Exception e){
				FacesMessages.instance().add("X\u1EA3y ra l\u1ED7i ", maKiemKe);		
				log.error(String.format(" ***** Error: %s", e));
				
			}
		}
		
	}
	
	public void selectCt(Integer index) {		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		KiemKeKho kkk = listB4144b.get(index.intValue());		
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();
		DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso() , "DmThuoc", "dmthuocMaso");
		DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso() , "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
		DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
		loaihang_ma = dmLoaiThuoc.getDmloaithuocMa();
		listDmPLThuocKK.clear();
		listDmPLThuocKK.add(dmPhanLoaiThuoc);
		ngaykiem = sdf.format(kkk.getKiemkeNgaygiocn());	
		quocgia = kkk.getDmquocgiaMaso().getDmquocgiaMa();
		nsx = kkk.getDmnhasanxuatMaso().getDmnhasanxuatMa();
		nct = kkk.getDmnctMaso().getDmnctMa();
		nkp = kkk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa();
		ngayhd = (kkk.getKiemkeNgayhandung()==null?"":kkk.getKiemkeNgayhandung() + "/") + (kkk.getKiemkeThanghandung()==null?"":kkk.getKiemkeThanghandung() + "/") + (kkk.getKiemkeNamhandung()==null?"":kkk.getKiemkeNamhandung());
		soDangKy = kkk.getKiemkeSodangky();
		donVi = dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa();
		donGia = kkk.getKiemkeDongia()+"";
		plthuoc_ma = "";	
	}
	
	/**
	 * xuat report 
	 */
	public String XuatReport() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		params=new HashMap<String, Object>();
		params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
		params.put("NGAYKIEM", ngaykiem);
		params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
		params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
		params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
		try{
			Date dateTemp=sdf.parse(ngaykiem);
			Calendar calTemp=Calendar.getInstance();
			calTemp.setTime(dateTemp);
			calTemp.add(Calendar.DATE, -1);
			dateTemp = calTemp.getTime();
			params.put("TINHDENNGAY" , dateTemp);
		}catch(Exception ex){}
		String temp=null;
		String listPl="(";		
		DieuTriUtilDelegate dtutilDelegate=DieuTriUtilDelegate.getInstance();	
	
		if(khoa.trim().equals("")){
			setKhoa(null);
		}else{
			DmKhoa dmKhoa = (DmKhoa)dtutilDelegate.findByMa(khoa, "DmKhoa", "dmkhoaMa");
			params.put("Kho", dmKhoa.getDmkhoaTen());
			params.put("KHOAMASO", dmKhoa.getDmkhoaMaso());
		}
		
		params.put("MaPhieuKiem", maKiemKe);
		reportTypeKC="Capnhatbangkiemke";
		log.info("Vao Method XuatReport cap nhat bang kiem ke");
		String baocao1=null;
		String pathTemplate=null;
		try {
			log.info("bat dau method XuatReport ");
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";
			
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf","Capnhatbangkiemke");
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
	    log.info("Thoat Method XuatReport");
	    return "B4160_Chonmenuxuattaptin";
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

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getThuoc() {
		return thuoc;
	}

	public void setThuoc(String thuoc) {
		this.thuoc = thuoc;
	}

	public String getQuocgia() {
		return quocgia;
	}

	public void setQuocgia(String quocgia) {
		this.quocgia = quocgia;
	}

	public String getNsx() {
		return nsx;
	}

	public void setNsx(String nsx) {
		this.nsx = nsx;
	}

	public String getNamnhap() {
		return namnhap;
	}

	public void setNamnhap(String namnhap) {
		this.namnhap = namnhap;
	}

	public String getNct() {
		return nct;
	}

	public void setNct(String nct) {
		this.nct = nct;
	}

	public String getNkp() {
		return nkp;
	}

	public void setNkp(String nkp) {
		this.nkp = nkp;
	}

	public String getNgayhd() {
		return ngayhd;
	}

	public void setNgayhd(String ngayhd) {
		this.ngayhd = ngayhd;
	}

	public String getNgaykiem() {
		return ngaykiem;
	}

	public void setNgaykiem(String ngaykiem) {
		this.ngaykiem = ngaykiem;
	}

	public List<KiemKeKho> getListB4144b() {
		return listB4144b;
	}

	public void setListB4144b(List<KiemKeKho> listB4144b) {
		this.listB4144b = listB4144b;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		B4144b_CapNhatSoLieuThucTe.log = log;
	}

	public String getDmKhoXuat() {
		return dmKhoXuat;
	}

	public void setDmKhoXuat(String dmKhoXuat) {
		this.dmKhoXuat = dmKhoXuat;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
}
