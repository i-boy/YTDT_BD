/*
 * author : Ly
 */
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
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
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNaophathaiCtDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChiTietNaophathaiDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
//import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathaiCt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
//import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B211_3_CapNhatThongTinNaoPhaThai")
@Synchronized(timeout = 600000)
public class CapNhatThongTinChiTietBANaoPhaThaiAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatThongTinChiTietBANaoPhaThaiAction.class);

	@Out(required = false)
	@In(required = false)
	private String duongdanStrDT = null;

	@Out(required = false)
	@In(required = false)
	private String loaiBCDT = null;

	@Out(required = false)
	@In(required = false)
	JasperPrint jasperPrintDT = null;

	@In(required = false)
	@Out(required = false)
	private String soBenhAn;

	@In(required = false)
	@Out(required = false)
	private String khoaMa;

	private String ghiNhanException;
	private String hoTen;
	private String ngay;
	private String dienbienbenh;
	private String thuoc;
	private String lieuluong;
	private String cachtiem;
	private String anuongholy;
	@DataModel
	private List<HsbaChiTietNaophathaiCt> listCtNaophathais;
	@DataModelSelection(value="listCtNaophathais")
	private HsbaChiTietNaophathaiCt ctNaophathaiSelection;
	private HsbaChiTietNaophathaiCtDelegate ctNaophathaiDAO;
	
	private HsbaChuyenMon hsbaChuyenMon;
	private HsbaChiTietNaophathai hsbaCTNaophathai;
	private Hsba hosobenhan;
	private TiepDon tiepDon;
	private BenhNhan benhnhan;
	
	private String kinhlancuoingay;
	private int index = 0;

	@Create
	@Begin(nested = true)
	public String init() throws Exception {
		log.info("***Starting init Chi tiet BA Nao pha thai ***");
		
		listCtNaophathais = new ArrayList<HsbaChiTietNaophathaiCt>();
		ctNaophathaiDAO = HsbaChiTietNaophathaiCtDelegate.getInstance();
		
		HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
		hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);

		if (hsbaChuyenMon != null) {
			
			if (hsbaChuyenMon.getHsbaSovaovien() != null){
				hosobenhan = hsbaChuyenMon.getHsbaSovaovien();
				
				if (hosobenhan.getTiepdonMa() != null && !hosobenhan.getTiepdonMa().equals("")){
					TiepDonDelegate tiepdonDel = TiepDonDelegate.getInstance();
					tiepDon = tiepdonDel.find(hosobenhan.getTiepdonMa());
					if (tiepDon == null){
						tiepDon = new TiepDon();
					}
					
				} else{
					tiepDon = new TiepDon();
				}
				
				if (hosobenhan.getBenhnhanMa() != null){
					benhnhan = hosobenhan.getBenhnhanMa();
				} else{
					benhnhan = new BenhNhan();
				}
				
				hoTen = benhnhan.getBenhnhanHoten();

				HsbaChiTietNaophathai hsbaCTNaophathaiTemp = null;
				try {
					hsbaCTNaophathaiTemp = HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
				} catch (Exception e) {
					log.info("error:" + e);
				}
				
				if (hsbaCTNaophathaiTemp != null) {
					hsbaCTNaophathai = hsbaCTNaophathaiTemp;
					
					SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
					if(hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi() != null)
						kinhlancuoingay = sdf.format(hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi());
					listCtNaophathais = ctNaophathaiDAO.findByHsbaChiTietNaophathaiMaso(hsbaCTNaophathaiTemp.getHsbactnaophathaiMa());
				} else {
					hsbaCTNaophathai = new HsbaChiTietNaophathai();
				}
		
			} else {
				hosobenhan = new Hsba();
				tiepDon = new TiepDon();
				benhnhan = new BenhNhan();
			}
			
		} else {
			// chua ghi nhan
			return "DieuTri_CapNhat_CapNhatThongTinChung";
		}

		log.info("***Finished init **");
		return "DieuTri_CapNhat_CapNhatThongTinChiTietBANaophathai";
	}

	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "DieuTri_CapNhat_CapNhatThongTinChung";
		// return "MyMainForm";
	}
	
	public void get_thuoc_info(){
        thuoc = "";
        if(ngay != null && !ngay.equals("")){
        	
        	if(Utils.getDBDate("00:00", ngay) == null){
        		log.info("### Cap nhat BA Nao pha thai -- get_thuoc_info(): ERROR Parse NGAY !! ");
				return;
			}
        	
            List<ThuocNoiTru> listThuocNoiTru;
            ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
            listThuocNoiTru = tntDelegate.findBySoVaoVienAndKhoaMaAndNgay(hsbaChuyenMon.getHsbaSovaovien().getHsbaSovaovien(), hsbaChuyenMon.getKhoaMa().getDmkhoaMa(),ngay);
            if(listThuocNoiTru != null && listThuocNoiTru.size() >0){
                for (ThuocNoiTru each : listThuocNoiTru){
                	thuoc += "- " + each.getThuocnoitruMathuoc(true).getDmthuocTen();
                    if(each.getThuocnoitruSoluong() != null)
                    	thuoc += ", " + each.getThuocnoitruSoluong().toString();
                    thuoc += " " + each.getThuocnoitruMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
                    thuoc += "\n";
                } // END FOR
            } else {
                thuoc = IConstantsRes.KHONG_DUNG_THUOC;
            }
        }
    }
	
	public void enter() throws Exception {
		log.info("*****Begin Enter() *****");
		insertRow();
		reset_ctbant();
		log.info("*****End Enter() *****");
	}
	private void insertRow(){
		HsbaChiTietNaophathaiCt each = new HsbaChiTietNaophathaiCt();
		each.setHsbactnaophathaictDienbien(dienbienbenh);
		each.setHsbactnaophathaictThuoc(thuoc);
		each.setHsbactnaophathaictAnuongholy(anuongholy);
		each.setHsbactnaophathaictCachtiem(cachtiem);
		each.setHsbactnaophathaictLieuluong(lieuluong);
		if(ngay != null && !ngay.equals("")){
			if(Utils.getDBDate("00:00", ngay) == null){
				each.setHsbactnaophathaictNgay(new Date());
			} else {
				each.setHsbactnaophathaictNgay(Utils.getDBDate("00:00", ngay).getTime());
			}
		}
		each.setHsbactnaophathaiMa(hsbaCTNaophathai);
		listCtNaophathais.add(each);
	}
	
	public void delete(int index) throws Exception {
		log.info("***** Begin delete() *****");
		listCtNaophathais.remove(index);
		reset_ctbant();
		log.info("***** End delete() *****");
	}
	
	public void reset_ctbant(){
		anuongholy = "";
		cachtiem = "";
		lieuluong = "";
		dienbienbenh = "";
		thuoc = "";
	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		ghiNhanException = null;
		hsbaCTNaophathai.setHsbacmMa(hsbaChuyenMon);
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
		try {
			hsbaCTNaophathai.setHsbactnaophathaiNgaydaukinhcuoi(sdf.parse(kinhlancuoingay));
		} catch (ParseException e) {}
		RemoveUtil.removeAllNullFromHSBACTNaophathai(hsbaCTNaophathai);
		if (hsbaCTNaophathai.getHsbactnaophathaiMa() == null) {
			hsbaCTNaophathai = HsbaChiTietNaophathaiDelegate.getInstance().create(hsbaCTNaophathai);
		} else {
			HsbaChiTietNaophathaiDelegate.getInstance().edit(hsbaCTNaophathai);
		}
		List<HsbaChiTietNaophathaiCt> listTempForRemove = ctNaophathaiDAO.findByHsbaChiTietNaophathaiMaso(hsbaCTNaophathai.getHsbactnaophathaiMa());
		
		List<Integer> listIndexTemp = new ArrayList<Integer>();
		for(HsbaChiTietNaophathaiCt each : listCtNaophathais){
			each.setHsbactnaophathaiMa(hsbaCTNaophathai);
			if(each.getHsbactnaophathaictMaso() == null)
				each = ctNaophathaiDAO.create(each);
			else 
				ctNaophathaiDAO.edit(each);
			
			listIndexTemp.add(each.getHsbactnaophathaictMaso());
		}
		log.info("***listIndexTemp: **" + listIndexTemp.toString());
		for(HsbaChiTietNaophathaiCt each2 : listTempForRemove){
			log.info("***each2: **" + each2.getHsbactnaophathaictMaso());
			if(!listIndexTemp.contains(each2.getHsbactnaophathaictMaso())){
				log.info("***VAO REMOVE **");
				ctNaophathaiDAO.remove(each2);
			}
		}
		FacesMessages.instance().add(IConstantsRes.SUCCESS);
		log.info("***Finished ghinhan **");
	}

	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			resetForm();
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}

	// Ham reset form
	private void resetForm() {
		log.info("Begining ResetForm(): ");
		ghiNhanException = null;
		log.info("End ResetForm(): ");
	}

	public String thuchienAction() {
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}

	public void XuatReport() {
		loaiBCDT = "CapNhatThongTinChiTietBANaophathai";
		log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an naophathai");
		String baocao1 = null;
		
		
		if (hsbaChuyenMon != null) {
			HsbaChiTietNaophathai hsbaCTNaophathaiTemp = null;
			try {
				hsbaCTNaophathaiTemp = HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM(hsbaChuyenMon.getHsbacmMa());
			} catch (Exception e) {
				log.info("error - XuatReport - HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM: " + e);
			}
			
			if (hsbaCTNaophathaiTemp != null) {
				hsbaCTNaophathai = hsbaCTNaophathaiTemp;
			}
		} else {
			// Khong load duoc HSBACM, return
			return;
		}
		
		
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N011_benhannaothai.jrxml";
		
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

			SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			
//			String sHoTenBN = "";
//			String sDonViGoi = "";
//			String sNgheNghiep = "";

			//HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();
			//HsbaChuyenMon objHSBAChuyenMon = hsbadel.findBySoVaoVien_MaKhoa(soBenhAn, khoaMa);

			String sHoTenBN = "";
			if (benhnhan.getBenhnhanHoten() != null)
				sHoTenBN = benhnhan.getBenhnhanHoten();
			
			params.put("HOTENBN", sHoTenBN);
			
			String diachistr = "";
			if (benhnhan.getBenhnhanDiachi() != null)
				diachistr += benhnhan.getBenhnhanDiachi();
			if (benhnhan.getXaMa(true).getDmxaTen() != null)
				diachistr += ", " + benhnhan.getXaMa(true).getDmxaTen();
			if (benhnhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += ", " + benhnhan.getHuyenMa(true).getDmhuyenTen();
			if (benhnhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += ", " + benhnhan.getTinhMa(true).getDmtinhTen();
			
			params.put("DIACHIBN", diachistr);
			
			String sNgheNghiep = "";
			if (benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null){
				sNgheNghiep = benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
				params.put("NGHENGHIEPBN", sNgheNghiep);
			}
			
			String sDonViGoi = "";
			if (hosobenhan.getHsbaDonvigoi() != null){
				sDonViGoi = hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
			}
			params.put("DONVIGOI", sDonViGoi);
			
			if (sDonViGoi != null && !sDonViGoi.trim().equals(""))
				params.put("NOIGIOITHIEU", sDonViGoi);
			else
				params.put("NOIGIOITHIEU", "");
			
			int iTuoi = benhnhan.getBenhnhanTuoi();
			int iDonviTuoi = benhnhan.getBenhnhanDonvituoi();
			String sDonViTuoi = "";
			if (iDonviTuoi == 1)
				sDonViTuoi = "";
			else if (iDonviTuoi == 2)
				sDonViTuoi = IConstantsRes.THANG;// "Tháng";
			else if (iDonviTuoi == 3)
				sDonViTuoi = IConstantsRes.NGAY; // "Ngày";

			params.put("TUOIBN", iTuoi + " " + sDonViTuoi);
			
			params.put("HOTENCHONG", hsbaCTNaophathai.getHsbactnaophathaiHotenchong());
			params.put("TUOICHONG", hsbaCTNaophathai.getHsbactnaophathaiTuoichong());
			params.put("NGHENGHIEPCHONG", hsbaCTNaophathai.getHsbactnaophathaiNghenghiepchong());
			params.put("DIACHICHONG", hsbaCTNaophathai.getHsbactnaophathaiDiachichong());
			params.put("LYDOVAOVIEN", hsbaCTNaophathai.getHsbactnaophathaiLydovaov());
			
			params.put("SOLANDE", hsbaCTNaophathai.getHsbactnaophathaiSolande());
			params.put("SOLANDEKHO", hsbaCTNaophathai.getHsbactnaophathaiSolandekho());
			params.put("SOLANSAY", hsbaCTNaophathai.getHsbactnaophathaiSolansay());
			params.put("SOLANNAO", hsbaCTNaophathai.getHsbactnaophathaiSolannao());
			params.put("LYDONAO", hsbaCTNaophathai.getHsbactnaophathaiLydonao());
			
			params.put("APDUNGPPHANCHESINHDE", hsbaCTNaophathai.getHsbactnaophathaiApdungpphanchesinhde());
			params.put("DACOBENHPHUKHOAGI", hsbaCTNaophathai.getHsbactnaophathaiDacobenhphukhoagi());
			params.put("DACHUALANH", hsbaCTNaophathai.getHsbactnaophathaiDachualanh());
			params.put("DANGCOBENHPHUKHOANAO", hsbaCTNaophathai.getHsbactnaophathaiDangcobenhphukhoagi());
			params.put("DANGCHUAHAYKHONG", hsbaCTNaophathai.getHsbactnaophathaiDangchuahaykhong());
			
			if(hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi() != null)
				params.put("NGAYDAUCUAKINHCUOI", sdf.format(hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi()));
			
			params.put("CHIEUCAOTC", hsbaCTNaophathai.getHsbactnaophathaiChieucaotucung());
			params.put("AMHO", hsbaCTNaophathai.getHsbactnaophathaiAmho());
			params.put("AMDAO", hsbaCTNaophathai.getHsbactnaophathaiAmdao());
			params.put("COTUCUNG", hsbaCTNaophathai.getHsbactnaophathaiCotucung());
			params.put("TUICUNG", hsbaCTNaophathai.getHsbactnaophathaiTuicung());
			params.put("TUCUNG", hsbaCTNaophathai.getHsbactnaophathaiTucung());
			params.put("PHANPHU", hsbaCTNaophathai.getHsbactnaophathaiPhanphu());
			
			params.put("MACH", tiepDon.getTiepdonMach());
			params.put("NHIETDO", tiepDon.getTiepdonNhietdo());
			params.put("HUYETAPMAX", tiepDon.getTiepdonHamax());
			params.put("HUYETAPMIN", tiepDon.getTiepdonHamin());
			params.put("NHIPTHO", tiepDon.getTiepdonNhiptho());
			params.put("CANNANG", tiepDon.getTiepdonTrluong());
			
			params.put("PHANUNGSINHVAT", hsbaCTNaophathai.getHsbactnaophathaiPhanungsinhvat());
			params.put("CONGTHUCBACHCAU", hsbaCTNaophathai.getHsbactnaophathaiCongthucbanhcau());
			params.put("SOLUONGHONGCAU", hsbaCTNaophathai.getHsbactnaophathaiSoluonghongcau());
			params.put("MAUDONG", hsbaCTNaophathai.getHsbactnaophathaiMaudong());
			params.put("MAUCHAY", hsbaCTNaophathai.getHsbactnaophathaiMauchay());
			params.put("DIENTIM", hsbaCTNaophathai.getHsbactnaophathaiDientim());
			params.put("CHUPPHOITIM", hsbaCTNaophathai.getHsbactnaophathaiChupphoitim());
			
			String sBenhKemTheo = "";
			if (hsbaChuyenMon.getHsbacmBenhphu() != null)
				sBenhKemTheo = hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
			params.put("BENHKEMTHEOCANLUUY", sBenhKemTheo);
			
			params.put("CHIDINHTHUTHUAT", hsbaCTNaophathai.getHsbactnaophathaiChidinhthuthuat());
			params.put("CHIEUSAUTUCUNG", hsbaCTNaophathai.getHsbactnaophathaiChieusautucung());
			params.put("NONGHEGAR", hsbaCTNaophathai.getHsbactnaophathaiNonghegar());
			params.put("THUOCTEME", hsbaCTNaophathai.getHsbactnaophathaiThuocteme());
			params.put("OCYTOCINE", hsbaCTNaophathai.getHsbactnaophathaiOcytocime());
			params.put("OCYTOCINE_DONVI", hsbaCTNaophathai.getHsbactnaophathaiOcytocimeDonvi());
			params.put("NAODUNGCU", hsbaCTNaophathai.getHsbactnaophathaiNaodungcu());
			params.put("GAP", hsbaCTNaophathai.getHsbactnaophathaiGap());
			params.put("DATTUINUOC", hsbaCTNaophathai.getHsbactnaophathaiDattuinuoc());
			params.put("THUTHUATKETHOPKHAC", hsbaCTNaophathai.getHsbactnaophathaiThuthuatkethopkhac());
			
			params.put("SL_CL_TRUNGTHAINHAU", hsbaCTNaophathai.getHsbactnaophathaiSlClTrungthainhaulayra());
			params.put("BIENCHUNGLUCLAYTHAI", hsbaCTNaophathai.getHsbactnaophathaiBienchungluclaythai());
			params.put("KETQUAXUATVIEN", hsbaCTNaophathai.getHsbactnaophathaiKetquakhixuatvien());
			params.put("HSBACTNAOPHATHAI_MA", hsbaCTNaophathai.getHsbactnaophathaiMa());
			
			if (hsbaCTNaophathai.getHsbactnaophathaiBslamba() != null)
				params.put("BACSINAOPHATHAI", hsbaCTNaophathai.getHsbactnaophathaiBslamba(true).getDtdmnhanvienTen());
			if (hsbaCTNaophathai.getHsbactnaophathaiBsdieutri() != null)
				params.put("BACSIDT", hsbaCTNaophathai.getHsbactnaophathaiBsdieutri(true).getDtdmnhanvienTen());

			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("da thay driver mysql");
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,
						IConstantsRes.DATABASE_USER,
						IConstantsRes.DATABASE_PASS);
				log.info("Da connect DATABASE");
				jasperPrintDT = JasperFillManager.fillReport(jasperReport, params,conn);
				log.info("fillReport THANHCONG");
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
			baocao1 = XuatReportUtil.ReportUtil(jasperPrintDT, index, IConstantsRes.PATH_BASE
							+ IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI
							+ "hsba/", "pdf", "capNhatThongTinChiTietBANaophathai");
			duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
			log.info("duong dan file xuat report :" + baocao1);
			log.info("duong dan -------------------- :" + duongdanStrDT);
			index += 1;
			log.info("Index :" + getIndex());
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		log.info("Thoat Method XuatReport");
	}

	public Integer getSeconds(java.util.Date d) {
		long seconds = DateUtils.getFragmentInHours(d, java.util.Calendar.DATE)
				* 60
				* 60
				+ DateUtils.getFragmentInMinutes(d,
						java.util.Calendar.HOUR_OF_DAY) * 60;
		return Integer.valueOf(seconds + "");
	}

	public String getGhiNhanException() {
		return ghiNhanException;
	}

	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}

	public String getReportPathTD() {
		return duongdanStrDT;
	}

	public void setReportPathTD(String reportPathTD) {
		this.duongdanStrDT = reportPathTD;
	}

	public String getReportTypeTD() {
		return loaiBCDT;
	}

	public void setReportTypeTD(String reportTypeTD) {
		this.loaiBCDT = reportTypeTD;
	}

	public JasperPrint getJasperPrintTD() {
		return jasperPrintDT;
	}

	public void setJasperPrintTD(JasperPrint jasperPrintTD) {
		this.jasperPrintDT = jasperPrintTD;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

//	private HsbaChuyenVien getHsbaChuyenVien() {
//		HsbaChuyenVienDelegate hsbaChuyenVienDel = HsbaChuyenVienDelegate
//				.getInstance();
//		HsbaChuyenVien objhsbaChuyenVien = hsbaChuyenVienDel
//				.findBySoVaoVien(soBenhAn);
//		return objhsbaChuyenVien;
//	}
//
//	private static final long ONE_HOUR = 60 * 60 * 1000L;
//
//	private static long daysBetween(Date d1, Date d2) {
//		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
//	}

	public HsbaChiTietNaophathai getHsbaCTNaophathai() {
		return hsbaCTNaophathai;
	}

	public void setHsbaCTNaophathai(HsbaChiTietNaophathai hsbaCTNaophathai) {
		this.hsbaCTNaophathai = hsbaCTNaophathai;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getKinhlancuoingay() {
		return kinhlancuoingay;
	}

	public void setKinhlancuoingay(String kinhlancuoingay) {
		this.kinhlancuoingay = kinhlancuoingay;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getDienbienbenh() {
		return dienbienbenh;
	}

	public void setDienbienbenh(String dienbienbenh) {
		this.dienbienbenh = dienbienbenh;
	}

	public String getThuoc() {
		return thuoc;
	}

	public void setThuoc(String thuoc) {
		this.thuoc = thuoc;
	}

	public String getLieuluong() {
		return lieuluong;
	}

	public void setLieuluong(String lieuluong) {
		this.lieuluong = lieuluong;
	}

	public String getCachtiem() {
		return cachtiem;
	}

	public void setCachtiem(String cachtiem) {
		this.cachtiem = cachtiem;
	}

	public String getAnuongholy() {
		return anuongholy;
	}

	public void setAnuongholy(String anuongholy) {
		this.anuongholy = anuongholy;
	}

	public List<HsbaChiTietNaophathaiCt> getListCtNaophathais() {
		return listCtNaophathais;
	}

	public void setListCtNaophathais(List<HsbaChiTietNaophathaiCt> listCtNaophathais) {
		this.listCtNaophathais = listCtNaophathais;
	}

	public HsbaChiTietNaophathaiCt getCtNaophathaiSelection() {
		return ctNaophathaiSelection;
	}

	public void setCtNaophathaiSelection(
			HsbaChiTietNaophathaiCt ctNaophathaiSelection) {
		this.ctNaophathaiSelection = ctNaophathaiSelection;
	}
	
}
