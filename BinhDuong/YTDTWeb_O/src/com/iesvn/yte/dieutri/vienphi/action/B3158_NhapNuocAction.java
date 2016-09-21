package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperPrint;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.NhapNuocDelegate;
import com.iesvn.yte.dieutri.entity.NhapNuoc;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3158_NhapNuoc")
@Synchronized(timeout = 6000000)
public class B3158_NhapNuocAction implements Serializable
{

	// private static String FORMAT_DATE = "dd/MM/yyyy";
	// private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";

	private static final long serialVersionUID = 10L;		
		
	private String khoaMaso;  
	private String buongMaso;
	private String khoaMa;
	private String buongMa;
	private String ngaynhap;	
	private String ngayhientai;
	private String soluong;
	private NhapNuoc nhapnuoc;
	private int selectedIndex;
	
	private List<SelectItem> listDmKhoaNTAll;
	private List<SelectItem> listDmKhoaAll;
	private Integer ikhoaMaso;
	private Integer ibuongMaso;
	private List<DmTang> listDmTangAll;
	private List<SelectItem> listDmTangByKhoa;
	
	private NhapNuocDelegate nnDel;
	
	private List<NhapNuoc> listNhapNuoc;
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strNhapNuoc;
	@Logger
	private Log log;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Factory("strNhapNuoc")
	public void init() {
		log.info("begin init, nnDel = " + nnDel);
		ngayhientai = sdf.format(new Date());		
		ngaynhap = sdf.format(new Date());
		listNhapNuoc = new ArrayList<NhapNuoc>();
		nhapnuoc = new NhapNuoc();
			
		strNhapNuoc = "";		
		
		reset();
		if (nnDel == null) {
			nnDel = NhapNuocDelegate.getInstance();
		}
		listDmKhoaNTAll = new ArrayList<SelectItem>();
		listDmKhoaAll = new ArrayList<SelectItem>();
		listDmTangAll = new ArrayList<DmTang>();
		listDmTangByKhoa = new ArrayList<SelectItem>();
		
		DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
		DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
		
		listDmKhoaAll.add(new SelectItem(new Integer(0) ,""));
		for(DmKhoa eachKhoa :	dmKhoaDel.findAll() ) {			
			listDmKhoaAll.add(new SelectItem(eachKhoa.getDmkhoaMaso() ,eachKhoa.getDmkhoaTen()));
		}
		
		
		listDmKhoaNTAll.add(new SelectItem(new Integer(0) ,""));
		for(DmKhoa eachKhoa :	dmKhoaDel.getKhoaNT() ) {			
			listDmKhoaNTAll.add(new SelectItem(eachKhoa.getDmkhoaMaso() ,eachKhoa.getDmkhoaTen()));
		}
		List<Object> listTangTmp =  utilDel.findAll("DmTang");
		for(Object myObj : listTangTmp) {
			listDmTangAll.add((DmTang) myObj);
		}
		
		log.info("end init, nnDel = " + nnDel);	
	}
	private void reset() {
		khoaMaso = khoaMa = buongMaso = buongMa = "";
		soluong = "1";
		selectedIndex = -1;
		
		ikhoaMaso = new Integer("0");
		ibuongMaso = new Integer("0");
	}
	
	public void searchNhapnuoc() {
		log.info("--enter searchNhapnuoc()---");
		
		try {
			NhapNuocDelegate nnDel = NhapNuocDelegate.getInstance();
			
			listNhapNuoc = nnDel.findByNgayNhap(sdf.parse(ngaynhap));
			if (listNhapNuoc != null) {								
				FacesMessages.instance().clear();
			} else {
				
				listNhapNuoc = new ArrayList<NhapNuoc>();				
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.no_found);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		selectedIndex = -1;
		log.info("--exit searchNhapnuoc()---");
	}
		
		
	public void edit(int index) {
		log.info("*****Begin edit(),index = " + index);
		nhapnuoc = listNhapNuoc.get(index);
		//khoaMaso = nhapnuoc.getDmkhoaMaso().getDmkhoaMaso().toString();		
		//khoaMa = nhapnuoc.getDmkhoaMaso().getDmkhoaMa();		
		//buongMaso = nhapnuoc.getDtdmbMaso().getDtdmbMaso().toString();
		//buongMa = nhapnuoc.getDtdmbMaso().getDtdmbMa();
		soluong = nhapnuoc.getNhapnuocSoluong().toString();	
		ngaynhap = sdf.format(nhapnuoc.getNhapnuocNgay());
		
		ikhoaMaso = (nhapnuoc.getDmkhoaMaso() == null ? new Integer("0") : nhapnuoc.getDmkhoaMaso().getDmkhoaMaso());
		layTangTheoKhoa();
		ibuongMaso = (nhapnuoc.getDmtangMaso() == null ? new Integer("0") : nhapnuoc.getDmtangMaso().getDmtangMaso());
		selectedIndex = index;
		log.info("*****End edit(),nhapnuoc = " + nhapnuoc);
		
	}
	// Ham delete chi tiet
	public void delete(int index) throws Exception
	{
		log.info("*****Begin delete() *****, index = " + index + ", nhapnuoc = " + listNhapNuoc.get(index));
		// Xoa du lieu trong DB
		nnDel.remove(listNhapNuoc.get(index));
		// Xoa item trong list
		listNhapNuoc.remove(index); 
		
		nhapnuoc = new NhapNuoc();		
		reset();	
		log.info("*****End delete() *****, listNhapNuoc.size = " + listNhapNuoc.size());
	}
	
	
	// Ham ghi nhan
	public String ghinhan() throws Exception
	{
		log.info("*****Begin Ghi nhan() *****, buongMaso = " + ibuongMaso + ", ngaynhap = " + ngaynhap + ", nhapnuoc = " + nhapnuoc);
		// Kiem tra su ton tai cua thong tin theo buong va ngay nhap
		NhapNuoc nnTmp = nnDel.findByBuongNgayNhap(ibuongMaso, sdf.parse(ngaynhap));
		// Neu thong tin da ton tai vao khong phai dang cap nhat tho thong bao trung du lieu		
		if (nnTmp != null && (nhapnuoc.getNhapnuocMaso() == null || 
				((nhapnuoc.getNhapnuocMaso() != null) && (nhapnuoc.getNhapnuocMaso().intValue() != nnTmp.getNhapnuocMaso().intValue()))) ){			
			// Hien thi thong bao trung du lieu
			FacesMessages.instance().add(IConstantsRes.NHAPNUOC_EXISTS, ngaynhap, nnTmp.getDmtangMaso().getDmtangTen());
		} else {
			
			if (nhapnuoc.getNhapnuocMaso() == null) {				
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
			} else {
				FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
			}
			
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			// Set Dmuc khoa 		
			//if (khoaMaso.trim().length() > 0) {			
			if (ikhoaMaso.intValue() != 0) {
				DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(ikhoaMaso, "DmKhoa", "dmkhoaMaso");
				nhapnuoc.setDmkhoaMaso(dmKhoa);
			} else {
				nhapnuoc.setDmkhoaMaso(null);
			}
			// Set Dmuc Buong		
			/*if (buongMaso.trim().length() > 0) {
				nhapnuoc.setDtdmbMaso(DtDmBuongDelegate.getInstance().find(new Integer(buongMaso)));
			} else {
				nhapnuoc.setDtdmbMaso(null);
			}
			*/
			if (ibuongMaso.intValue() != 0) {				
				for (DmTang eachTang : listDmTangAll) {
					if (eachTang.getDmtangMaso().intValue() == ibuongMaso.intValue()) {
						nhapnuoc.setDmtangMaso(eachTang);
						break;
					}
				}				
			} else {
				nhapnuoc.setDmtangMaso(null);
			}
			if (soluong.trim().length() > 0) {
				nhapnuoc.setNhapnuocSoluong(new Integer(soluong));
			} else {
				nhapnuoc.setNhapnuocSoluong(null);
			}
			nhapnuoc.setNhapnuocNgay(sdf.parse(ngaynhap));			
			nnTmp = nnDel.myCreate(nhapnuoc);
			if(selectedIndex < 0) {
				listNhapNuoc.add(nnTmp);
			} else {
				listNhapNuoc.set(selectedIndex, nnTmp);
			}
			nhapnuoc = new NhapNuoc();
			reset();			
		}		
		log.info("*****End Ghi nhan() *****");
		return null;
	}
	public String thuchienAction()
	{
		XuatReport();
		return "B3360_Chonmenuxuattaptin";
	}
	/**
	 * xuat report
	 */
	public void XuatReport()
	{ /*
		reportTypeVP = "lapphieubaoanhangngay_form";
		log.info("Vao Method XuatReport phieu bao an ngay");
		// Lay danh muc khoa
		DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
		DmKhoa dmKhoa = (DmKhoa) dtUtil.findByMaSo(new Integer(khoaMaso), "DmKhoa", "dmkhoaMaso");
		PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
		try {
		PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), sdf.parse(ngayan));
		//if (pbaTmp != null) {
			String baocao1 = null;
			
				log.info("bat dau method XuatReport ");
				String pathTemplate = IConstantsRes.PATH_BASE
						+ IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI
						+ "vienphi/B3140_Phieubaoan_form.jrxml";
			
	
				JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
			
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("khoa", pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaTen() : "");
		        params.put("khoamaso",pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaMaso() : new Integer(0));
				params.put("ngayan", pbaTmp != null ? pbaTmp.getPhieubaoanNgayan() : sdf.parse(ngayan));				
				
				log.info("================= ");
				   Class.forName("oracle.jdbc.OracleDriver");
				    log.info("da thay driver mysql");
				    Connection conn = null;
				    try{
				    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
				    }catch(Exception e){
				    	log.info(e.getMessage());
				    }
				    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
				    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,new Long(new Date().getTime()).intValue(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","Xeminlientucphieucongkhai");
				    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
				    log.info("duong dan file xuat report :" + baocao1);
				    log.info("duong dan -------------------- :"+reportPathVP);
				    //index+= 1;
				    //log.info("Index :" + getIndex());
				    if(conn != null) conn.close();
			
			//} 
		} catch (Exception e){
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		*/
		log.info("Thoat Method XuatReport");
	}
	// End Phuc add
	
	public void layTangTheoKhoa() {
		log.info("Begin layTangTheoKhoa, khoaMaso = " + ikhoaMaso);
		listDmTangByKhoa.clear();
		log.info("After clear , listDmTangByKhoa.size = " + listDmTangByKhoa.size());
		listDmTangByKhoa.add(new SelectItem(new Integer(0) ,""));
		if (ikhoaMaso.intValue() != 0) {
			for(DmTang eachDmTang : listDmTangAll) {
				if (eachDmTang.getDmkhoaMaso() != null) {
					if(eachDmTang.getDmkhoaMaso().getDmkhoaMaso() != null) {
						if(eachDmTang.getDmkhoaMaso().getDmkhoaMaso().intValue() == ikhoaMaso.intValue()) {
							listDmTangByKhoa.add(new SelectItem(eachDmTang.getDmtangMaso() ,eachDmTang.getDmtangTen()));
						}
					}
				}
			}
		}
		log.info("End layTangTheoKhoa, listDmTangByKhoa.size = " + listDmTangByKhoa.size());
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public String getKhoaMaso() {
		return khoaMaso;
	}
	public void setKhoaMaso(String khoaMaso) {
		this.khoaMaso = khoaMaso;
	}
	
	public String getReportTypeVP(){
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP){
		this.reportTypeVP = reportTypeVP;
	}

	public String getBuongMaso() {
		return buongMaso;
	}

	public void setBuongMaso(String buongMaso) {
		this.buongMaso = buongMaso;
	}

	public String getNgaynhap() {
		return ngaynhap;
	}

	public void setNgaynhap(String ngaynhap) {
		this.ngaynhap = ngaynhap;
	}

	public String getSoluong() {
		return soluong;
	}

	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}

	public List<NhapNuoc> getListNhapNuoc() {
		return listNhapNuoc;
	}

	public void setListNhapNuoc(List<NhapNuoc> listNhapNuoc) {
		this.listNhapNuoc = listNhapNuoc;
	}

	public String getKhoaMa() {
		return khoaMa;
	}

	public void setKhoaMa(String khoaMa) {
		this.khoaMa = khoaMa;
	}

	public String getBuongMa() {
		return buongMa;
	}

	public void setBuongMa(String buongMa) {
		this.buongMa = buongMa;
	}
	public List<SelectItem> getListDmKhoaNTAll() {
		return listDmKhoaNTAll;
	}
	public void setListDmKhoaNTAll(List<SelectItem> listDmKhoaNTAll) {
		this.listDmKhoaNTAll = listDmKhoaNTAll;
	}
	public Integer getIkhoaMaso() {
		return ikhoaMaso;
	}
	public void setIkhoaMaso(Integer ikhoaMaso) {
		this.ikhoaMaso = ikhoaMaso;
	}
	public List<SelectItem> getListDmTangByKhoa() {
		return listDmTangByKhoa;
	}
	public void setListDmTangByKhoa(List<SelectItem> listDmTangByKhoa) {
		this.listDmTangByKhoa = listDmTangByKhoa;
	}
	public Integer getIbuongMaso() {
		return ibuongMaso;
	}
	public void setIbuongMaso(Integer ibuongMaso) {
		this.ibuongMaso = ibuongMaso;
	}
	public List<SelectItem> getListDmKhoaAll() {
		return listDmKhoaAll;
	}
	public void setListDmKhoaAll(List<SelectItem> listDmKhoaAll) {
		this.listDmKhoaAll = listDmKhoaAll;
	}
	
    
}
