package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(ScopeType.SESSION)
@Name("B3121_CanLamSang_DieuTri_KQAction")
@Synchronized(timeout = 6000000)
public class B3121_CanLamSang_DieuTri_KQAction implements Serializable {

	//private static String FORMAT_DATE = "dd/MM/yyyy";
	//private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	
	private String ngaySinh;

	@Logger
	private Log log;

	
	
	private String maKhoaKham;
	
	
	private String soBenhAn;
	
	private String tenBenhNhan;
	private Short donviTuoi;
	private String maBenhNhan;
	private Integer tuoi;	
	
	
	private Integer clsmaOut;
	
	private String maCLS;
	private String tenCLS; 
	private Boolean daThucHien;
	private String kqCLS;
	private String maKhoa;
	private String ghichu;
	private Integer loaiCLS = 0; // 0: binh thuong; 1: xet nghiem; 2: CDHA 	

	public String getKqCLS() {
		return kqCLS;
	}


	public void setKqCLS(String kqCLS) {
		this.kqCLS = kqCLS;
	}
	

	private Boolean thatSuThucHien ;
	
	
	//@Create
	public void init(){
		log.info("begin comeFromclsphauuthuat");
		
		ngaySinh= "";
		
		setDefaultValue();
		
		log.info("ma khoa " + maKhoa);
		log.info("file anh " + fileanh);
		log.info("end comeFromclsphauuthuat");
	}
		
	public String getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}



	public Boolean hasImage ;


	public void setDefaultValue() {
		loaiCLS = 0;
		log.info("maKhoaKham:"+maKhoaKham);
		log.info("soBenhAn:"+soBenhAn);
		log.info("clsmaOut:"+clsmaOut);
		
		
		if (maKhoaKham == null || maKhoaKham.equals("") || soBenhAn == null || soBenhAn.equals("")){
			resetValue();
			return;
		}
		if (clsmaOut ==null){
			resetValue();
			return;
		}
		ClsMoDelegate clsmoDel = ClsMoDelegate.getInstance();
		ClsMo clsMo = clsmoDel.find(clsmaOut);
		if (clsMo == null){
			log.info("ko tim thay cls:"+clsmaOut);
			resetValue();
			return;
		}
		
		maCLS = clsMo.getClsmoMahang(true).getDtdmclsbgMa();
		tenCLS = clsMo.getClsmoMahang(true).getDtdmclsbgDiengiai();
		daThucHien = clsMo.getClsmoTh();
		kqCLS = clsMo.getClsmoKetqua();
		
		
		thatSuThucHien = daThucHien;
		// 20110516 bao.ttc: tranh NULL cho nhung CLS khong co khoa, vd: cac CLS loai kham.
		//maKhoa = clsKham.getClskhamKhoa().getDmkhoaMa();
		maKhoa = clsMo.getClsmoKhoa(true).getDmkhoaMa();
		ghichu = clsMo.getClsmoGhichu();
		
		// 20111118: kiem tra loai CLS la xet nghiem hay CDHA
		if(clsMo.getClsmoMahang(true).getDtdmclsbgXn() != null && clsMo.getClsmoMahang(true).getDtdmclsbgXn()){
			loaiCLS = 1;
		}else{
			if(clsMo.getClsmoMahang(true).getDtdmclsbgCdha() != null && !clsMo.getClsmoMahang(true).getDtdmclsbgCdha().equals("")){
				loaiCLS = 2;
			}
		}
		
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
		Hsba hsba = hsbaDelegate.find(soBenhAn);
		if (hsba == null){
			resetValue();
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
			return ;
		}
		try {
			if (daThucHien != null && daThucHien == true){
//				byteImagSource = clsMo.getClsmoImg();
				viewImg();
				hasImage = true;				
			}else{
//				List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen",clsKham.getClskhamMa()+"","");
//				if(listClsKetQua.size() > 0){
//					connectLabconn();
//				}
				hasImage = false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		setOtherValue(hsba);
	}
	

	
	
	public void resetValue() {

	}
	
	public String ghinhan() throws Exception {
		if (clsmaOut == null || clsmaOut.equals("")){
			log.info("clsKham"+clsmaOut);
			return "";
		}
		ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
		ClsMo clsMo = clsMoDel.find(clsmaOut);
		if (clsMo == null){
			log.info("clsKham"+clsMo);
			return "";
		}
		if(ghichu != null && !ghichu.trim().equals("") || kqCLS != null && !kqCLS.trim().equals("") || byteImag != null && byteImag.length > 0){
			daThucHien = true;
		}
		clsMo.setClsmoTh(daThucHien);
		clsMo.setClsmoKetqua(kqCLS);			
		clsMo.setClsmoGhichu(ghichu);
		if (daThucHien == true && byteImag != null && byteImag.length > 0 ){
//			clsMo.setClsmoImg(byteImag);	
		}else{
//			clsMo.setClsmoImg(null);
		}
		
		
		clsMoDel.edit(clsMo);
		thatSuThucHien = daThucHien;
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		return "";
	}
	public String quaylai()  throws Exception {
		resetValue();		
		
		return "clsthuthat";
	}


	private void setOtherValue(Hsba hsba) {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (hsba.getBenhnhanMa()
				.getBenhnhanNgaysinh() != null
				&& !hsba.getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(hsba
					.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		tenBenhNhan = hsba.getBenhnhanMa(true).getBenhnhanHoten();
		maBenhNhan = hsba.getBenhnhanMa(true).getBenhnhanMa();
		donviTuoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
		
		tuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();
		
	}

	private String fileanh;
	
    public String getFileanh() {
		return fileanh;
	}


	public void setFileanh(String fileanh) {
		this.fileanh = fileanh;
	}


	public void viewImg () throws Exception{
		//byteImagSource
		if (byteImagSource ==null || byteImagSource.length == 0){
			log.info("length ==0");
			fileanh = null;
			return;
		}
		String ext = "jpg";
		if(maKhoa.equals(IConstantsRes.KHOA_VISINH_MA)){
			ext = IConstantsRes.VISINH_UPLOAD;
		}
		 String fileName = IConstantsRes.PATH_BASE + "/" + IConstantsRes.VI_TRI_THU_MUC + "/" + maKhoaKham + "_"+ soBenhAn + "_" + maCLS;
	        File file = new File(fileName + "." + ext);
	        FileOutputStream fOS = new FileOutputStream(file);
	        fOS.write(byteImagSource);
	        fOS.close();
	       
	        fileanh = maKhoaKham + "_"+ soBenhAn + "_" + maCLS + "." + ext;
	
	}
	private  byte[] byteImagSource ;

	
	private  byte[] byteImag ;
	private int chieuDaiByte = 5*1024*1024;
	public void listener(UploadEvent event) throws IOException{		
	    UploadItem item = event.getUploadItem();
	    File file = item.getFile();
	    log.info("duong dan cua file " + file.getPath());
	    try {
	      
//	        BufferedImage src = ImageIO.read(file);
//	        BufferedImage image = toBufferedImage(src);
//	        save(image, "jpg");  // png okay, j2se 1.4+
//	        //save(image, "bmp");  // j2se 1.5+
//            					   // gif okay in j2se 1.6+

	    	FileInputStream fIS = new  FileInputStream(file);
	    	
	    	byteImag = new byte[chieuDaiByte];
	    	
	    	byte[] imgData = new byte[1024];
	    	int endOfFile =  fIS.read(imgData);
	    	int viTri = 0;
	    	while (endOfFile != -1 && viTri < chieuDaiByte){
	    		for (int i = 0 ; i < endOfFile;i++){
	    			byteImag[viTri++] = imgData[i];
	    		}
	    		endOfFile =  fIS.read(imgData);
	    	}
	       
	    	// lay chieu dai chinh thuc
	    	
	        //do da`i byte[] thuc te viTri - 1
	        ///int chieudaithucte = viTri - 1;
	    	int chieudaithucte = viTri;
	        byte[] imageThucTe = new byte[chieudaithucte];
	        for (int i = 0 ; i < chieudaithucte; i++){
	        	imageThucTe[i] = byteImag[i];
	        }
	        byteImag = imageThucTe;
	        //------------------------------
	        
	        
	    } catch(Exception ioe) {
	        ioe.printStackTrace();
	    }

	    file.delete();
	}


	public String getMaKhoaKham() {
		return maKhoaKham;
	}


	public void setMaKhoaKham(String maKhoaKham) {
		this.maKhoaKham = maKhoaKham;
	}


	public String getSoBenhAn() {
		return soBenhAn;
	}


	public void setSoBenhAn(String soBenhAn) {
		this.soBenhAn = soBenhAn;
	}


	public Integer getClsmaOut() {
		return clsmaOut;
	}


	public void setClsmaOut(Integer clsmaOut) {
		this.clsmaOut = clsmaOut;
	}


	public String getTenBenhNhan() {
		return tenBenhNhan;
	}


	public void setTenBenhNhan(String tenBenhNhan) {
		this.tenBenhNhan = tenBenhNhan;
	}


	public Short getDonviTuoi() {
		return donviTuoi;
	}


	public void setDonviTuoi(Short donviTuoi) {
		this.donviTuoi = donviTuoi;
	}


	public String getMaBenhNhan() {
		return maBenhNhan;
	}


	public void setMaBenhNhan(String maBenhNhan) {
		this.maBenhNhan = maBenhNhan;
	}


	public Integer getTuoi() {
		return tuoi;
	}


	public void setTuoi(Integer tuoi) {
		this.tuoi = tuoi;
	}


	public String getMaCLS() {
		return maCLS;
	}


	public void setMaCLS(String maCLS) {
		this.maCLS = maCLS;
	}


	public String getTenCLS() {
		return tenCLS;
	}


	public void setTenCLS(String tenCLS) {
		this.tenCLS = tenCLS;
	}


	public Boolean getDaThucHien() {
		return daThucHien;
	}


	public void setDaThucHien(Boolean daThucHien) {
		this.daThucHien = daThucHien;
	}


	public Boolean getHasImage() {
		return hasImage;
	}


	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}


	public Boolean getThatSuThucHien() {
		return thatSuThucHien;
	}


	public void setThatSuThucHien(Boolean thatSuThucHien) {
		this.thatSuThucHien = thatSuThucHien;
	}


	public String getMaKhoa() {
		return maKhoa;
	}


	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}


	public String getGhichu() {
		return ghichu;
	}


	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}


	public Integer getLoaiCLS() {
		return loaiCLS;
	}


	public void setLoaiCLS(Integer loaiCLS) {
		this.loaiCLS = loaiCLS;
	}

	

}


