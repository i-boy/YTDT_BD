package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.KiemKe;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("CapNhatSoLieuKiemKeThucTeAction")
public class CapNhatSoLieuKiemKeThucTeAction implements Serializable {

	@Logger
	private Log log;
	@In(required = false)
	@Out(required = false)
	private KiemKe kiemkeOut;
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	String dmKhoCapNhat ="";
	DmKhoa dmKho = new DmKhoa();
	
	private KiemKe kiemke;
	private DtDmNhanVien nv;
	DtDmNhanVienDelegate nvDelegate;
	String resetFormCapNhatKiemKeFile ="";
	KiemKeDelegate kiemkeDel;
	
	File file;
	
	private String message;
	public boolean isNumberic(String str) {
		try {
			str = str.trim();
			Integer foo = Integer.parseInt(str);
			return (true);
		} catch (Exception ex) {
			return (false);
		}
	}
	
	@Factory("resetFormCapNhatKiemKeFile")
	public void resetFormCapNhatKiemKeFile() {
		log.info("resetFormCapNhatKiemKeFile()");	
		kiemke = kiemkeOut;
		dmKhoCapNhat = kiemke.getDmkhoaMaso().getDmkhoaMa();
		dmKho = kiemke.getDmkhoaMaso();
		kiemkeDel = KiemKeDelegate.getInstance();
		nvDelegate = DtDmNhanVienDelegate.getInstance();
		nv = nvDelegate.findByNd(identity.getUsername());		
		message ="";
		resetFormCapNhatKiemKeFile="";
	}

	@Begin(join = true)
	public String init(String kho) throws Exception {
		System.out.println("-------Begin Init()------");
		dmKhoCapNhat = kho;
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		if(dmKhoCapNhat.equals("KC")){
			dmKho = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
		}else if(dmKhoCapNhat.equals("TE")){
			dmKho = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
		}else if(dmKhoCapNhat.equals("BHYT")){
			dmKho = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
		}else{
			dmKho = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
		}
		nvDelegate = DtDmNhanVienDelegate.getInstance();
		kiemkeDel = KiemKeDelegate.getInstance();
		nv = nvDelegate.findByNd(identity.getUsername());
		message ="";
		System.out.println("-------End Init()------");
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe";
	}

	@End
	public void end() {

	}

	public void listener(UploadEvent event) throws IOException {
		System.out.println("---------Called listener()--------");
		UploadItem item = event.getUploadItem();
		file = item.getFile();
		log.info("duong dan cua file " + file.getPath());	
		
	}
	public void ghinhan(){
		log.info("Ghi nhan so lieu vao database"+file);
		List<KiemKeKho> lstKiemKeKho = new ArrayList<KiemKeKho>();
		if(nv == null){
			return;
		}
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			log.info("Tong so rows " + rows);

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			log.info(" so Columm " + cols);
			boolean end = false;
			double tonTT = 0.0;
			int maKiemKe = 0;
			Date now = new Date();
			for (int i = 20; i < rows; i++) {
				row = sheet.getRow(i);
				tonTT = 0.0;
				maKiemKe = 0;
				String lydo = "";
				if (row != null) {
					for (int j = 0; j < cols; j++) {
						cell = row.getCell(j);
						if (cell != null) {
							CellReference cellRef = new CellReference(row
									.getRowNum(), cell.getCellNum(), false,
									false);
							
							if (cellRef.formatAsString().startsWith("M")) {
								if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									tonTT = cell.getNumericCellValue();
								}
							}
							
							if (cellRef.formatAsString().startsWith("S")) {
								if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									lydo = cell.getRichStringCellValue().getString();
								}
							}
							
							if (cellRef.formatAsString().startsWith("T")) {
								if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									maKiemKe = (int) cell.getNumericCellValue();
								} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									if (cell.getRichStringCellValue()
											.getString().toLowerCase().equals(
													"E".toLowerCase()))
										end = true;
								}
							}
						}
					}
				}
				if (maKiemKe != 0) {
					KiemKeKho kiemkeKho = new KiemKeKho();
					kiemkeKho.setKiemkeMa(maKiemKe);
					kiemkeKho.setKiemkeNgaygiocn(now);
					kiemkeKho.setDtdmnhanvienCn(nv);
					kiemkeKho.setKiemkeTontt(tonTT);
					if(lydo !=""){
						kiemkeKho.setKiemkeLydo(lydo);
					}
					lstKiemKeKho.add(kiemkeKho);
				}
				if (end)
					break;
			}
			kiemke.setDtdmnhanvienCn(nv);
			kiemke.setNgaygiocn(now);
			kiemke.setTrangthai("CLOSE");
			
			String result = kiemkeDel.capnhatSoLieuKiemKe(kiemke, lstKiemKeKho);
			if(result.equals("SUCCESS")){
				FacesMessages.instance().add("B\u1EA1n \u0111\u00E3 c\u1EADp nh\u1EADt s\u1ED1 li\u1EC7u th\u00E0nh c\u00F4ng.");
			}else{
				FacesMessages.instance().add("B\u1EA1n kh\u00F4ng c\u1EADp nh\u1EADt s\u1ED1 li\u1EC7u th\u00E0nh c\u00F4ng b\u1EA3ng ki\u1EC3m k\u00EA n\u00E0y." + result);
			}
			file.delete();
		} catch (Exception ioe) {
			ioe.printStackTrace();		
		}
	}
	
	public String getMessage(){
		return this.message;
	}	
	
	public void setMessage(String message){
		this.message = message;
	}

	public String refreshPage() {
		return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe";
	}
	
	public String quayve(){
		resetFormCapNhatKiemKeFile = null;
		return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
	}
}
