/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import net.sf.jasperreports.engine.JasperPrint;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;

@Name("ChonXuatFileBaoCaoB225")
@Scope(SESSION)
public class ChonXuatFileBaoCao_B225 implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPath;
	
	@Out(required=false)
	@In(required=false)
	private String reportPath5b;
	
	@Out(required=false)
	@In(required=false)
	private String reportType;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jPrint;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jPrint1;
	
	private String chonFileXuat=null;
	
	private boolean reportFinish=false;
	private String duongdanFileXuat=null;
	private String duongdanFileXuat5b=null;
	
	@Create
	public void init(){
		setChonFileXuat("DOC");
	} 

	public void xuatFileAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String ketquaPath=null;
		String tenfile=null;
		if(reportType.equals("DSBNPhauthuatThuThuat")){
			log.info("-------Bao cao tinh hinh benh tat tu vong-----------");
			ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/";
		}
		
		int index=0;
		int index5b=0;
		String tempStr=null;
		String tempStr5b=null;
		tempStr=XuatReportUtil.ReportUtil(jPrint,index,ketquaPath,chonFileXuat.trim(),"Danhsachbenhnhanphauthuat");
		tempStr5b=XuatReportUtil.ReportUtil(jPrint1,index,ketquaPath,chonFileXuat.trim(),"Danhsachbenhnhanphauthuat5b");
		setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE,""));
		setDuongdanFileXuat5b(tempStr5b.replaceFirst(IConstantsRes.PATH_BASE,""));
		log.info("duong dan----" + getDuongdanFileXuat());
		log.info("duong dan----5b" + getDuongdanFileXuat5b());
		setReportFinish(true);
	}
	
	public String TroveAction(){
		return "DieuTri_BaoCaoHoatDongDieuTri_DanhSachPhauThuatThuThuat";
	}
	
	
	public void setChonFileXuat(String chonFileXuat) {
		this.chonFileXuat = chonFileXuat;
	}
	
	public String getChonFileXuat() {
		return chonFileXuat;
	}

	public void setDuongdanFileXuat(String duongdanFileXuat) {
		this.duongdanFileXuat = duongdanFileXuat;
	}

	public String getDuongdanFileXuat() {
		return duongdanFileXuat;
	}

	public void setReportFinish(boolean reportFinish) {
		this.reportFinish = reportFinish;
	}

	public boolean isReportFinish() {
		return reportFinish;
	}

	public String getDuongdanFileXuat5b() {
		return duongdanFileXuat5b;
	}

	public void setDuongdanFileXuat5b(String duongdanFileXuat5b) {
		this.duongdanFileXuat5b = duongdanFileXuat5b;
	}
}
