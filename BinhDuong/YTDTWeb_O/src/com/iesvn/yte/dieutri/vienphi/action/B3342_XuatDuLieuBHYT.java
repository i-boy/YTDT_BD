package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;
import com.iesvn.yte.util.ConvertUnicodeToTCVN3;


@Scope(CONVERSATION)
@Name("B3342_XuatDuLieuBHYT")
@Synchronized(timeout = 6000000)
public class B3342_XuatDuLieuBHYT implements Serializable {
	private static Logger log = Logger
			.getLogger(B3342_XuatDuLieuBHYT.class);

	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
	
	private String chonloaibc = "";
	private String fileExport =null;
	private boolean reportFinish = false;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Begin(join = true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		emtyData();
		log.info("***Finished init ***");

		SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
		ngayhientai = format.format(new Date());
		
		return "BaoCaoVienPhi_BaoCaoBHYT_XuatDuLieuBHYT";
	}
	
	@End
	public void endFunct(){
		
	}
	public void emtyData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		tungay = sdf.format(currentDate);
		denngay = sdf.format(currentDate);
		setChonloaibc("l1");
	}

	public void resetValue() {
		emtyData();
	}
	
	/**
	 * xuat du lieu BHYT
	 */
	int indexComm = 0;
	public void xuatDuLieuBHYT() {
		log.info("Vao Method XuatDuLieuBHYT");		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = sdf.parse(tungay);
			Date toDate = sdf.parse(denngay); 
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(IConstantsRes.PATH_BASE+"/templates/bc25abhyt.xls"));
			HSSFWorkbook wbIn = new HSSFWorkbook(fs);
			
			FileOutputStream fileOut = new FileOutputStream(IConstantsRes.PATH_BASE+"/templates/result/bc25abhyt.xls");
			HSSFWorkbook wbOut = new HSSFWorkbook();
			
			if(chonloaibc.equals("l1")){//Tat ca		
				if(!exportDataNgoaiTru(wbIn, wbOut, fromDate, toDate)){
					FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR);
					return;
				}
				if(!exportDataNoiTru(wbIn, wbOut, fromDate, toDate, false, indexComm)){
					FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR);
					return;
				}
			}else if(chonloaibc.equals("l2")){ //Ngoai tru
				if(!exportDataNgoaiTru(wbIn, wbOut, fromDate, toDate)){
					FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR);
					return;
				}
			}else{//Noi tru
				if(!exportDataNoiTru(wbIn, wbOut, fromDate, toDate, true, 0)){
					FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR);
					return;
				}
			}
			wbOut.write(fileOut);
			fileOut.close();
			fileExport = "/templates/result/bc25abhyt.xls";
		}catch (Exception e) {
		    log.info("ERROR Method XuatDuLieuBHYT!!!");
		    FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR+ e.getMessage());
			e.printStackTrace();
		}
		setReportFinish(true);
		FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_SUCCESS);
	    log.info("Thoat Method XuatDuLieuBHYT");
	}
	
	public boolean exportDataNgoaiTru(HSSFWorkbook wbIn, HSSFWorkbook wbOut, Date fromDate, Date toDate){
		log.info("Vao Method exportDataNgoaiTru");
		try{			
			TiepDonDelegate tiepdonDel = TiepDonDelegate.getInstance();
			List<TmpDataBhyt> lstTmpDataNgoaitru = tiepdonDel.exportDataNgoaiTru(fromDate, toDate);
			log.info("lstTmpDataNgoaitru.size: "+lstTmpDataNgoaitru.size());
			//Ghi data ngoai tru vao excel template			
			HSSFSheet worksheetNGT = wbOut.createSheet("Ngoai Tru");

			HSSFSheet sheetNGT = wbIn.getSheetAt(0);//Sheet Ngoai tru	
			HSSFRow rowheadIn = sheetNGT.getRow((short)0);
			HSSFCellStyle cellStyleIn = wbIn.getCellStyleAt((short)0);
			HSSFCellStyle cellStyleOut = wbOut.createCellStyle();
			cellStyleOut.setFillBackgroundColor(HSSFColor.RED.index); 
			int socot = rowheadIn.getPhysicalNumberOfCells();
			HSSFRow rowHeadOut = worksheetNGT.createRow((short)0);
			for(short i = 0; i < socot; i++){				
				HSSFCell cell = rowHeadOut.createCell(i);
				cell.setCellValue(rowheadIn.getCell(i).toString());
				cell.setCellStyle(cellStyleOut);
			}
			int index=1;
			exportData(index, wbOut, worksheetNGT, lstTmpDataNgoaitru);			
		}catch(Exception er){
			er.printStackTrace();
			return false;
		}
		log.info("Thoat Method exportDataNgoaiTru");
		return true;
	}
	
	public boolean exportDataNoiTru(HSSFWorkbook wbIn, HSSFWorkbook wbOut, Date fromDate, Date toDate, boolean xoabangtam, int index){
		log.info("Vao Method exportDataNoiTru");
		try{
			HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
			List<TmpDataBhyt> lstTmpDataNoitru = hsbaDel.exportDataNoiTru(fromDate, toDate, xoabangtam, IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI);
			log.info("lstTmpDataNoitru.size: "+lstTmpDataNoitru.size());
			//Ghi data ngoai tru vao excel template
			HSSFSheet worksheetInNT = wbIn.getSheetAt(0);//Sheet Ngoai tru	
			HSSFRow rowheadInNT = worksheetInNT.getRow((short)0);
			HSSFCellStyle cellStyleIn = wbIn.getCellStyleAt((short)0);
			HSSFCellStyle cellStyleOut = wbOut.createCellStyle();
			cellStyleOut.setFillBackgroundColor(cellStyleIn.getFillForegroundColor()); 
			int socot = rowheadInNT.getPhysicalNumberOfCells();	
			HSSFSheet worksheetOutNT = wbOut.createSheet("Noi Tru");
			HSSFRow rowHeadOutNT = worksheetOutNT.createRow((short)0);
			for(short i = 0; i < socot; i++){
				HSSFCell cell = rowHeadOutNT.createCell(i);
				cell.setCellValue(rowheadInNT.getCell(i).toString());
				cell.setCellStyle(cellStyleOut);
			}
			index = index+1;
			exportData(index,  wbOut, worksheetOutNT, lstTmpDataNoitru);
						
		}catch(Exception er){
			er.printStackTrace();
			return false;
		}
		log.info("Thoat Method exportDataNoiTru");
		return true;
	}
	
	public void exportData(int index, HSSFWorkbook wbOut, HSSFSheet worksheet, List<TmpDataBhyt> lstTmpData){
		log.info("Vao Method exportData");
		ConvertUnicodeToTCVN3 cvtUnicodeToTcvn3 = new ConvertUnicodeToTCVN3();
		for(TmpDataBhyt tmpData: lstTmpData){
			HSSFRow row = worksheet.createRow((short)index);
			row.createCell((short)0).setCellValue(index);//stt
			String strHoTen = tmpData.getHoTen().toLowerCase();
            strHoTen = printCapitalized(strHoTen);
            strHoTen = cvtUnicodeToTcvn3.convertCAP(strHoTen);
            
			row.createCell((short)1).setCellValue(strHoTen);//hoten
			row.createCell((short)2).setCellValue(tmpData.getNamSinh());//namsinh
			row.createCell((short)3).setCellValue(tmpData.getGioiTinh());//gioitinh
			row.createCell((short)4).setCellValue(tmpData.getMaThe());//mathe
			row.createCell((short)5).setCellValue(tmpData.getMaDkbd());//ma_dkbd
			row.createCell((short)6).setCellValue(tmpData.getMaBenh());//mabenh		
			//Format ngay vao, ngay ra			
			HSSFDataFormat format = wbOut.createDataFormat();
			HSSFCellStyle cellStyle = wbOut.createCellStyle();
	        cellStyle.setDataFormat(format.getFormat("MM/dd/yyyy"));
	        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
	        Date ngayvaoDate = new Date();
	        Date ngayraDate = new Date();
	        Date giatriTuDate = new Date();
	        Date giatriDenDate = new Date();
	        try{
		        ngayvaoDate = sf.parse(tmpData.getNgayVao());
		        ngayraDate = sf.parse(tmpData.getNgayRa());
		        giatriTuDate = sf.parse(tmpData.getGtTu());
		        giatriDenDate = sf.parse(tmpData.getGtDen());
		        
	        }catch(Exception er){
	        	System.out.println("Loi dinh dang ngay gio sai");
	        }
	        HSSFCell cell7 = row.createCell((short)7);
			cell7.setCellValue(ngayvaoDate);//ngay_vao
			cell7.setCellStyle(cellStyle);

			HSSFCell cell8 = row.createCell((short)8);
			cell8.setCellValue(ngayraDate);//ngay_ra
			cell8.setCellStyle(cellStyle);
			
			row.createCell((short)9).setCellValue(tmpData.getNgayDtr());//ngaydtr
			row.createCell((short)10).setCellValue(tmpData.getTienXetnghiem());//t_xn
			row.createCell((short)11).setCellValue(tmpData.getTienCdha());//t_cdha
			row.createCell((short)12).setCellValue(tmpData.getTienThuoc());//t_thuoc
			row.createCell((short)13).setCellValue(tmpData.getTienMau());//t_mau
			row.createCell((short)14).setCellValue(tmpData.getTienPttt());//t_pttt
			row.createCell((short)15).setCellValue(tmpData.getTienVtytth());//t_vtytth
			row.createCell((short)16).setCellValue(tmpData.getTienDvktc());//t_dvktc
			row.createCell((short)17).setCellValue(tmpData.getTienKtg());//t_ktg Tổng tiền giường (không sử dụng, luôn = 0)
			row.createCell((short)18).setCellValue(tmpData.getTienKham());//t_kham
			row.createCell((short)19).setCellValue(tmpData.getTienVanchuyen());//t_vchuyen
			row.createCell((short)20).setCellValue(tmpData.getTongChi());//t_tongchi
			row.createCell((short)21).setCellValue(tmpData.getTienBntra());//t_bnct
			row.createCell((short)22).setCellValue(tmpData.getTienBhxh());//t_bhtt
			row.createCell((short)23).setCellValue(tmpData.getTienNgoaids());//t_ngoaids  TCP - (Tiền BN + Tiền BH)
			row.createCell((short)24).setCellValue(tmpData.getLydoVv());//lydo_vv  Lý do vào viện (1 - đúng tuyến; 0 - trái tuyến)
			String strBenhKhac = tmpData.getBenhKhac();
			if(strBenhKhac != null){
				strBenhKhac = cvtUnicodeToTcvn3.convert(strBenhKhac);
			}
			row.createCell((short)25).setCellValue(strBenhKhac);//benhkhac
			String noiKCB = IConstantsRes.MASO_CO_SO_KCB;
			//cat bo "." ra chuoi noiKCB
			noiKCB = noiKCB.replace(".", "");			
			row.createCell((short)26).setCellValue(noiKCB);//noikcb
			row.createCell((short)27).setCellValue(tmpData.getThangQt());//thang_qt
			row.createCell((short)28).setCellValue(tmpData.getNamQt());//nam_qt
			
			HSSFCell cell29 = row.createCell((short)29);
			cell29.setCellValue(giatriTuDate);//gt_tu
			cell29.setCellStyle(cellStyle);

			HSSFCell cell30 = row.createCell((short)30);	
			cell30.setCellValue(giatriDenDate);//gt_den
			cell30.setCellStyle(cellStyle);	
						
			String strDiachi = tmpData.getDiaChi();
			if(strDiachi != null){
				strDiachi = strDiachi.toLowerCase();
				strDiachi = printCapitalized(strDiachi);
				strDiachi = cvtUnicodeToTcvn3.convertCAP(strDiachi);
			}
			row.createCell((short)31).setCellValue(strDiachi);//diachi
			row.createCell((short)32).setCellValue(tmpData.getGiamDinh());//giamdinh
			row.createCell((short)33).setCellValue(tmpData.getXuatToan());//t_xuattoan
			row.createCell((short)34).setCellValue(tmpData.getLydoXuattoan());//lydo_xt
			row.createCell((short)35).setCellValue(tmpData.getDaTuyen());//t_datuyen
			row.createCell((short)36).setCellValue(tmpData.getVuotTran());//t_vuottran
			row.createCell((short)37).setCellValue(tmpData.getLoaiKcb());//loaikcb
			row.createCell((short)38).setCellValue(tmpData.getNoiThanhtoan());//noi_ttoan
			//Tho add 30/8/2011 - se bo 4 ky tu dau.
			String sophieu = "";
			if(tmpData.getSoPhieu() != null && !tmpData.getSoPhieu().equals("")){
				sophieu = tmpData.getSoPhieu().substring(4);
			}			
			row.createCell((short)39).setCellValue(sophieu);//sophieu
			if(tmpData.getMaKhoa() == null){
				row.createCell((short)40).setCellValue(0);//ma_khoa
			}else{
				row.createCell((short)40).setCellValue(tmpData.getMaKhoa());//ma_khoa
			}
			
			row.createCell((short)41).setCellValue(tmpData.getTuyen());//tuyen
			row.createCell((short)42).setCellValue(tmpData.getTiepDon());// bao.ttc: tiepdon_ma, dua vao de kiem tra BN nao sai tien
			index++;
		}
		indexComm = index;
		log.info("Thoat Method exportDataNoiTru");
	}
	
	private String printCapitalized( String str )
    {
        // Print a copy of str to standard output, with the
        // first letter of each word in upper case.
        char ch;       // One of the characters in str.
        char prevCh;   // The character that comes before ch in the string.
        int i;         // A position in str, from 0 to str.length()-1.
        prevCh = '.';  // Prime the loop with any non-letter character.
        StringBuffer result = new StringBuffer();
        for ( i = 0;  i < str.length();  i++ ) {
            ch = str.charAt(i);
            if ( Character.isLetter(ch)  &&  ! Character.isLetter(prevCh) )
                result.append( Character.toUpperCase(ch) );
            else
                result.append( ch );
            prevCh = ch;  // prevCh for next iteration is ch.
        }
        return result.toString();
    }
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		B3342_XuatDuLieuBHYT.log = log;
	}

	public void nhaplai() throws Exception {
		ResetForm();
	}

	// Ham reset form
	private void ResetForm() {
		log.info("Begining ResetForm(): ");
		emtyData();
		log.info("End ResetForm(): ");
	}
	
	public String getThoigian_thang() {
		return thoigian_thang;
	}
	
	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}
	
	public String getThoigian_nam() {
		return thoigian_nam;
	}
	
	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}
	
	public String getTungay() {
		return tungay;
	}
	
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	
	public String getDenngay() {
		return denngay;
	}
	
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	
	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setChonloaibc(String chonloaibc) {
		this.chonloaibc = chonloaibc;
	}

	public String getChonloaibc() {
		return chonloaibc;
	}	
	
	public void setFileExport(String fileExport) {
		this.fileExport = fileExport;
	}

	public String getFileExport() {
		return fileExport;
	}
	
	public void setReportFinish(boolean reportFinish) {
		this.reportFinish = reportFinish;
	}

	public boolean isReportFinish() {
		return reportFinish;
	}
}


