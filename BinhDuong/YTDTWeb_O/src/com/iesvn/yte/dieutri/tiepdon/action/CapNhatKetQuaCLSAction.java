package com.iesvn.yte.dieutri.tiepdon.action;

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
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(ScopeType.SESSION)
@Name("B121_2_1_CapNhatKetQuaCLS")
@Synchronized(timeout = 6000000)
public class CapNhatKetQuaCLSAction implements Serializable {

	//private static String FORMAT_DATE = "dd/MM/yyyy";
	//private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;
	
	private String ngaySinh;

	@Logger
	private Log log;

	
	
	private String maBanKham;
	
	
	private String maTiepDon;
	
	private String tenBenhNhan;
	private Short donviTuoi;
	private String maBenhNhan;
	private Integer tuoi;	
	
	
	private Integer clsmaOut;
	
	private String maCLS;
	private String tenCLS; 
	private Boolean daThucHien;
	private String kqCLS;
	private String dulieuhinhanh;
	private String maKhoa;
	private String ghichu;
	private Integer loaiCLS = 0; // 0: binh thuong; 1: xet nghiem; 2: CDHA 
	
	
	public String getDulieuhinhanh() {
		return dulieuhinhanh;
	}


	public void setDulieuhinhanh(String dulieuhinhanh) {
		this.dulieuhinhanh = dulieuhinhanh;
	}


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
	
	public void connectLabconn(){
		try {
		if(IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")){
			log.info("ConnectLabconn");
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		    //String url = "jdbc:sqlserver://127.0.0.1\\test:1433;databaseName=test";
			//String username = "sa";
		    //String password = "123456";
			ClsKhamDelegate clskhamDel = ClsKhamDelegate.getInstance();
			ClsKham clsKham = clskhamDel.find(clsmaOut);			
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(maBanKham, maTiepDon);
			String temp = "";
			String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + "\\" + IConstantsRes.LABCONN_DATABASE + ":" + IConstantsRes.LABCONN_PORT + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
		    String username = IConstantsRes.LABCONN_USERNAME;
		    String password = IConstantsRes.LABCONN_PASSWORD;
		    try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url, username, password);
				List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen",clsKham.getClskhamMa()+"","");
				for (int j = 0; j < listClsKetQua.size(); j++) {	
					String query = " Select * from [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload]";
					query += " where 1 = 1 ";
					query += " and OrderID = '" + thamkham_temp.getTiepdonMa().getTiepdonMa() + "' ";
					query += " and TestCodeHIS = '" + listClsKetQua.get(j).getClskqMa() + "' ";
					PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = stmt.executeQuery();  
					if(rs!=null){ 							
		                while(rs.next()){ 
		                	daThucHien = true;
		                	ResultSetMetaData rsMetaData = rs.getMetaData();
		                	temp += listClsKetQua.get(j).getClskqTen() + ": " + rs.getString("Result") + "\n";
		                	listClsKetQua.get(j).setResult(rs.getString("Result"));
		                	DieuTriUtilDelegate.getInstance().edit(listClsKetQua.get(j));
	//	                    int columnCount = rsMetaData.getColumnCount();
	//	                    
	//	                    for (int k = 1; k <= columnCount; k++) {
	//	                      String key = rs.getString(k);
	//	                      log.info("key " + rsMetaData.getColumnName(k) + " is " + key);
	//	                      temp +=  rsMetaData.getColumnName(k) + ": " + key + "\n";
	//	                    }
		                }
					}
				}
				ghichu = temp;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // load sqlserver driver
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		} catch (Exception e) {
			
		}
	    
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
		log.info("maBanKham:"+maBanKham);
		log.info("maTiepDon:"+maTiepDon);
		log.info("clsmaOut:"+clsmaOut);
		
		
		if (maBanKham == null || maBanKham.equals("") || maTiepDon == null || maTiepDon.equals("")){
			resetValue();
			return;
		}
		if (clsmaOut ==null){
			resetValue();
			return;
		}
		ClsKhamDelegate clskhamDel = ClsKhamDelegate.getInstance();
		ClsKham clsKham = clskhamDel.find(clsmaOut);
		if (clsKham == null){
			log.info("ko tim thay cls:"+clsmaOut);
			resetValue();
			return;
		}
		
		maCLS = clsKham.getClskhamMahang(true).getDtdmclsbgMa();
		tenCLS = clsKham.getClskhamMahang(true).getDtdmclsbgDiengiai();
		daThucHien = clsKham.getClskhamTh();
		kqCLS = clsKham.getClskhamKetqua();
		dulieuhinhanh = clsKham.getClskhamDulieuhinhanh();
		
		thatSuThucHien = daThucHien;
		// 20110516 bao.ttc: tranh NULL cho nhung CLS khong co khoa, vd: cac CLS loai kham.
		//maKhoa = clsKham.getClskhamKhoa().getDmkhoaMa();
		maKhoa = clsKham.getClskhamKhoa(true).getDmkhoaMa();
		ghichu = clsKham.getClskhamGhichu();
		
		// 20111118: kiem tra loai CLS la xet nghiem hay CDHA
		if(clsKham.getClskhamMahang(true).getDtdmclsbgXn() != null && clsKham.getClskhamMahang(true).getDtdmclsbgXn()){
			loaiCLS = 1;
		}else{
			if(clsKham.getClskhamMahang(true).getDtdmclsbgCdha() != null && !clsKham.getClskhamMahang(true).getDtdmclsbgCdha().equals("")){
				loaiCLS = 2;
			}
		}
		
		ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
		ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(maBanKham, maTiepDon);
		if (thamkham_temp == null){
			resetValue();
			FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND);
			return ;
		}
		try {
			if (daThucHien != null && daThucHien == true){
				byteImagSource = clsKham.getClskhamImg();
				viewImg();
				hasImage = true;				
			}else{
				List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen",clsKham.getClskhamMa()+"","");
				if(listClsKetQua.size() > 0){
					connectLabconn();
				}
				hasImage = false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		setOtherValue(thamkham_temp);
	}
	

	
	
	public void resetValue() {

	}
	
	public String ghinhan() throws Exception {
		if (clsmaOut == null || clsmaOut.equals("")){
			log.info("clsKham"+clsmaOut);
			return "";
		}
		ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
		ClsKham clsKham = clsKhamDel.find(clsmaOut);
		if (clsKham == null){
			log.info("clsKham"+clsKham);
			return "";
		}
		if(ghichu != null && !ghichu.trim().equals("") || kqCLS != null && !kqCLS.trim().equals("") || byteImag != null && byteImag.length > 0 || dulieuhinhanh != null && !dulieuhinhanh.trim().equals("")){
			daThucHien = true;
		}
		clsKham.setClskhamTh(daThucHien);
		clsKham.setClskhamKetqua(kqCLS);	
		clsKham.setClskhamDulieuhinhanh(dulieuhinhanh);
		clsKham.setClskhamGhichu(ghichu);
		if (daThucHien == true && byteImag != null && byteImag.length > 0 ){
			clsKham.setClskhamImg(byteImag);	
		}else{
			clsKham.setClskhamImg(null);
		}
		
		
		clsKhamDel.edit(clsKham);
		thatSuThucHien = daThucHien;
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		return "";
	}
	public String quaylai()  throws Exception {
		resetValue();		
		
		return "clsthuthat";
	}


	private void setOtherValue(ThamKham thamkham) {
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
		if (thamkham.getTiepdonMa().getBenhnhanMa()
				.getBenhnhanNgaysinh() != null
				&& !thamkham.getTiepdonMa().getBenhnhanMa()
						.getBenhnhanNgaysinh().equals("")) {
			ngaySinh = formatter.format(thamkham.getTiepdonMa()
					.getBenhnhanMa().getBenhnhanNgaysinh().getTime());
		}
		tenBenhNhan = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanHoten();
		maBenhNhan = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanMa();
		donviTuoi = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanDonvituoi();
		
		tuoi = thamkham.getTiepdonMa(true).getBenhnhanMa(true).getBenhnhanTuoi();
		
	}
//    private static void save(BufferedImage image, String ext) {
//        String fileName = IConstantsRes.PATH_BASE + "/savingAnImage";
//        File file = new File(fileName + "." + ext);
//        try {
//            ImageIO.write(image, ext, file);  // ignore returned boolean
//        } catch(IOException e) {
//            System.out.println("Write error for " + file.getPath() +
//                               ": " + e.getMessage());
//        }
//    }

//    private static BufferedImage toBufferedImage(Image src) {
//        int w = src.getWidth(null);
//        int h = src.getHeight(null);
//        int type = BufferedImage.TYPE_INT_RGB;  // other options
//        BufferedImage dest = new BufferedImage(w, h, type);
//        Graphics2D g2 = dest.createGraphics();
//        g2.drawImage(src, 0, 0, null);
//        g2.dispose();
//        return dest;
//    }
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
		 String fileName = IConstantsRes.PATH_BASE + "/" + IConstantsRes.VI_TRI_THU_MUC + "/" + maBanKham + "_"+ maTiepDon + "_" + maCLS;
	        File file = new File(fileName + "." + ext);
	        FileOutputStream fOS = new FileOutputStream(file);
	        fOS.write(byteImagSource);
	        fOS.close();
	       
	        fileanh = maBanKham + "_"+ maTiepDon + "_" + maCLS + "." + ext;
	        
//    	 int w = 100, h = 100;
//        
//         ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//         int[] nBits = {8};
//         ColorModel cm = new ComponentColorModel(cs, nBits, false, true,
//                                                 Transparency.OPAQUE,
//                                                 DataBuffer.TYPE_BYTE);
//         SampleModel sm = cm.createCompatibleSampleModel(w, h);
//         DataBufferByte db = new DataBufferByte(byteImagSource, w*h);
//         WritableRaster raster = Raster.createWritableRaster(sm, db, null);
//         BufferedImage bm = new BufferedImage(cm, raster, false, null);
//         System.out.println("bm="+bm);
//
//       
//        save(bm, "jpg");  // png okay, j2se 1.4+
//        //save(image, "bmp");  // j2se 1.5+
//        					   // gif okay in j2se 1.6+
		
		
	}
	private  byte[] byteImagSource ;
//	public void paint(Graphics2D g2d, Object obj) {
//		log.info("call: paintpaintpaint");
//		if (byteImagSource.length == 0){
//			log.info("length ==0");
//			return;
//		}
//		int w = 100, h = 100;
//	        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//	        int[] nBits = {8};
//	        ColorModel cm = new ComponentColorModel(cs, nBits, false, true,
//	                                                Transparency.OPAQUE,
//	                                                DataBuffer.TYPE_BYTE);
//	        SampleModel sm = cm.createCompatibleSampleModel(w, h);
//	        DataBufferByte db = new DataBufferByte(byteImagSource, w*h);
//	        WritableRaster raster = Raster.createWritableRaster(sm, db, null);
//	        BufferedImage bm = new BufferedImage(cm, raster, false, null);
//
//	        float[] brightKernel = { 1 };
//	        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//	        BufferedImageOp bright = new ConvolveOp(new Kernel(1, 1, brightKernel), ConvolveOp.EDGE_NO_OP, hints);
//	      
//	        
//	       g2d.drawImage(bm, bright, w, h);
//	       
//	}
	
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


	public String getMaBanKham() {
		return maBanKham;
	}


	public void setMaBanKham(String maBanKham) {
		this.maBanKham = maBanKham;
	}


	public String getMaTiepDon() {
		return maTiepDon;
	}


	public void setMaTiepDon(String maTiepDon) {
		this.maTiepDon = maTiepDon;
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


