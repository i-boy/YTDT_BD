package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmTang;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B212_Capnhatthongtinnhapvien")
@Synchronized(timeout = 6000000)
public class CapNhatTTNhapVien  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
	private String msgFail = "";
	private String msgSuccess = "";
	
	private YteLogDelegate yteLogDele = YteLogDelegate.getInstance();
    private YteLog yteLog;
    private String listDataLog;

	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private String giatri1;
	private String giatri2;
	private String giatri3;
	private String giatri4;
	private String moc1;
	private String moc2;
	private String moc3;
	private String listMaTinhBhyt;
	private boolean disabledGhinhan = false;
	private boolean dangGhinhan = false; // 20110804 bao.ttc: neu dangGhi nhan == true thi khong cho chay Ghinhan() nua de tranh nguoi dung nhan nhieu lan 
	private List<BenhNhanTrungTheBhytDTO> listBN; 
	private boolean showListBn;
	private String maDoituong_Old = "";
	private static Logger log = Logger.getLogger(CapNhatTTNhapVien.class);
	
	private static final long serialVersionUID = 10L;
	
	private String resultHidden ="";	
	
	private String ngayhientai = "";		
	private String showMenu;
	
	private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
	private DmTang tangChuyenDen;
	private List<SelectItem> listDmTangs = new ArrayList<SelectItem>();
	private List<SelectItem> listDmKhoaNTs = new ArrayList<SelectItem>();
	private List<DmKhoa> listDmKhoaNTAll = new ArrayList<DmKhoa>();
	private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap<String, DmKhoa>();
	private DmKhoa khoaDangDt;
	private boolean lockDoituong = false;
	private boolean daCheckTrungBN = false;
	private boolean trungBN = false;
	private String strMsgTrungBN = "";
	//@Create
	//@Begin (join = true)
	public String init(String typeMenu) {	
		resetValue();
		refreshDmKhoaNT();
		showMenu = typeMenu;
		return "DieuTri_CapNhat_CapNhatThongTinNhapVien";
	}
	

	@End
	public void end(){
		
	}
	
	private void setOtherInfor (){
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE); 
        
        Date ngayGioVaoVien = hoSoBenhAn.getHsbaNgaygiovaov();
        if (ngayGioVaoVien != null){
	        Calendar dngayGioVaoVien = Calendar.getInstance();
	        dngayGioVaoVien.setTime(ngayGioVaoVien);
	        if (ngayGioVaoVien!=null){
	        	 gioVaoVien = Utils.getGioPhut(ngayGioVaoVien);
			     ngayVaoVien = formatter.format(ngayGioVaoVien);					        
	        }
        }
        Date ngayGioRaVien = hoSoBenhAn.getHsbaNgaygiorav();
        if (ngayGioRaVien != null){
	        Calendar dngayGioRaVien = Calendar.getInstance();
	        dngayGioRaVien.setTime(ngayGioRaVien);
	        if (ngayGioRaVien!=null){
	        	 gioRaVien = Utils.getGioPhut(ngayGioRaVien);
			     ngayRaVien = formatter.format(ngayGioRaVien.getTime());					        
	        }
        }	
        
        if (hsbaBHYT != null){
        	Date dGiaTri1 = hsbaBHYT.getHsbabhytGiatri0();
            if (dGiaTri1 != null){
    	        giatri1 = formatter.format(dGiaTri1);
    	    }else{
    	    	giatri1 = "";
    	    }
            
            Date dGiaTri2 = hsbaBHYT.getHsbabhytGiatri1();
            if (dGiaTri2 != null){
    	        giatri2 = formatter.format(dGiaTri2);
    	    }else{
    	    	giatri2 = "";
    	    }
            
            Date dGiaTri3 = hsbaBHYT.getHsbabhytGiatri2();
            if (dGiaTri3 != null){
    	        giatri3 = formatter.format(dGiaTri3);
    	    }else{
    	    	giatri3 = "";
    	    }
            
            Date dGiaTri4 = hsbaBHYT.getHsbabhytGiatri3();
            if (dGiaTri4 != null){
            	giatri4 = formatter.format(dGiaTri4);
    	    }else{
    	    	giatri4 = "";
    	    }
            
            Date dmoc1 = hsbaBHYT.getHsbabhytMoc1();
            if (dmoc1 != null){
            	moc1 = formatter.format(dmoc1);
    	    }else{
    	    	moc1 = "";
    	    }
            
            Date dmoc2 = hsbaBHYT.getHsbabhytMoc2();
            if (dmoc2 != null){
            	moc2 = formatter.format(dmoc2);
    	    }else{
    	    	moc2 = "";
    	    }
            
            Date dmoc3 = hsbaBHYT.getHsbabhytMoc3();
            if (dmoc3 != null){
            	moc3 = formatter.format(dmoc3);
    	    }else{
    	    	moc3 = "";
    	    }
        }else{
        	giatri1 = "";
        	giatri2 = "";
        	giatri3 = "";
        	giatri4 = "";
        	
        	moc1="";
        	moc2="";
        	moc3="";
        }
	}
	
	public void resetValue(){
		log.info("init() ");
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		
		hoSoBenhAn = new Hsba();
		hoSoBenhAn.setBenhnhanMa(benhNhan);

		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
	
		hsbaBHYT = new HsbaBhyt();
		SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
		
		ngaySinh = "";		
		gioVaoVien=Utils.getGioPhut(new Date());
		ngayVaoVien= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		gioRaVien = "";
		ngayRaVien = "";		
		resultHidden="";		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE);         
		ngayhientai = formatter.format(new Date());
		
		giatri1 = "";
		giatri2 = "";
		giatri3 = "";
		giatri4 = "";
		moc1 = "";
		moc2 = "";
		moc3="";
		lockChuyenKhoa = "";
		disabledGhinhan = false;
		dangGhinhan = false;
		listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
		showListBn = false;
		// Lay danh muc tinh de tao listMaTinhBhyt
		List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");		
		listMaTinhBhyt = "";
		for(DmTinh each : listDmTinh) {
			listMaTinhBhyt += each.getDmtinhBHYT() + ",";
		}	
		tangChuyenDen = new DmTang();
		khoaDangDt = new DmKhoa();
		lockDoituong = false;
		daCheckTrungBN = false;
		trungBN = false;
		strMsgTrungBN = "";
	}	

	private BenhNhan benhNhan;
	//private String tuoi;
	private String ngaySinh;
	private Hsba hoSoBenhAn;	
	private String gioVaoVien;
	private String ngayVaoVien;
	private String gioRaVien;
	private String ngayRaVien;
	private HsbaBhyt hsbaBHYT;
	private String gioi = "";
	private String lockChuyenKhoa;

	public HsbaBhyt getHsbaBHYT() {
		return hsbaBHYT;
	}

	public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
		this.hsbaBHYT = hsbaBHYT;
	}

	public void displayInfor() throws Exception {
		log.info("begin displayInfo=======");
		try {

//			HsbaWSService hsbaService = new HsbaWSServiceLocator();
//			HsbaWS hsbaWS = hsbaService.getHsbaWSPort();
			
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			
			String sba = hoSoBenhAn.getHsbaSovaovien();
			if (sba != null && !sba.trim().equals("")) {
				resetValue();
				Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
				if (hoSoBenhAn_temp != null) {

					hoSoBenhAn = hoSoBenhAn_temp;
					SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);

					benhNhan = hoSoBenhAn.getBenhnhanMa();

					SimpleDateFormat formatter= new SimpleDateFormat(FORMAT_DATE);

					if (benhNhan.getBenhnhanNgaysinh() != null
							&& !benhNhan.getBenhnhanNgaysinh().equals("")) {
						ngaySinh = formatter.format(benhNhan
								.getBenhnhanNgaysinh().getTime());
					}

					SetInforUtil.setInforIfNullForBN(benhNhan);
					
					// Luu ma doi tuong cu
					maDoituong_Old = hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
					//gioVaoVien=Utils.getGioPhut(new Date());
					//ngayVaoVien= new SimpleDateFormat("dd/MM/yyyy").format(new Date());

					 if (benhNhan.getDmgtMaso() != null){
							if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
								gioi = "r1";  //1 : Name
							}else{
								gioi = "r2";
							}					
						}else{
							gioi = null;
						}

//					HsbaBhytWSService hsbaBhytService = new HsbaBhytWSServiceLocator();
//					HsbaBhytWS hsbaBhytWS = hsbaBhytService.gethsbaBhytWSPort();

					HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
					HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());

					if (hsbaBhytLast != null) {
						hsbaBHYT = hsbaBhytLast;
						SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
					}
					setOtherInfor();
					// phuc.lc 03/08/2011: kiem tra neu da thanh toan thi khong cho chinh sua
					HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();
					HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(hoSoBenhAn.getHsbaSovaovien());
					if (hsbaHsThtoan_temp != null) {
						if (hsbaHsThtoan_temp.getHsthtoanDatt() != null && hsbaHsThtoan_temp.getHsthtoanDatt()) {
							FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
							disabledGhinhan = true;							
						}
					}
					//phuc.lc 26/10/2011 : Kiem tra co thuoc xuat vien hay khong
					// neu co thuoc xuat vien thi khong cho thay doi doi tuong (danh cho doi tuong thu phi)
					List<ThuocNoiTruXuatVien> lstTntXv = hsbaDelegate.findTntXuatVienBySoBenhAn(hoSoBenhAn.getHsbaSovaovien());
					// doi voi doi tuong bao hiem thi xet thuoc xuat vien trong bang thuoc_noi_tru co thuocnoitru_tutruc_pdt = 2
					List<HsbaKhoa> listHsbaKhoa = new ArrayList<HsbaKhoa>(); 
					listHsbaKhoa = HsbaKhoaDelegate.getInstance().findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
					List<ThuocNoiTru> listTnt_Xuatvien = new ArrayList<ThuocNoiTru>();
					ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
					for(HsbaKhoa eachHsbaKhoa : listHsbaKhoa) {
						listTnt_Xuatvien = tntDel.findByHsbaKhoa(eachHsbaKhoa.getHsbakhoaMaso());
						if (listTnt_Xuatvien != null && listTnt_Xuatvien.size() > 0) {
							break;
						}
					}
					if((lstTntXv != null && lstTntXv.size() > 0)
						|| (listTnt_Xuatvien != null && listTnt_Xuatvien.size() > 0)){
						lockDoituong = true;						
					} else {
						lockDoituong = false;
					}
					
					khoaDangDt = hoSoBenhAn.getHsbaKhoadangdt();
					//phuc.lc add code below
					// kiem tra thong tin chuyen khoa, 
					// neu da co thong tin chuyen khoa roi thi khong cho thay doi nua.
					
					List<HsbaChuyenMon> listHsbaCM = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien(hoSoBenhAn.getHsbaSovaovien());
					//Tho add 12/10/2011: de lay tangChuyenDen trong hsbaKhoa
					for(HsbaKhoa hsbaKhoa:listHsbaKhoa){
						if(hsbaKhoa.getKhoaMa().getDmkhoaMa().equals(hoSoBenhAn.getHsbaKhoadangdt().getDmkhoaMa())){
							// 20120308 bao.ttc: NOTES Truong hop o 1 khoa dang DT co nhieu Tang, phai xet dang DT o Tang nao ?
							if(hsbaKhoa.getDmtangMaso() != null){
								tangChuyenDen = hsbaKhoa.getDmtangMaso();
								// 20120315 bao.ttc: kiem tra HSBA Tang dang DT neu co thi load len, chua co thi cap nhat vao
								if (hoSoBenhAn.getTangDangdt() != null) {
									if (hoSoBenhAn.getTangDangdt().getDmtangMaso().equals(hsbaKhoa.getDmtangMaso().getDmtangMaso())){
										tangChuyenDen = hsbaKhoa.getDmtangMaso();
										break;
									}
								} else {
									hoSoBenhAn.setTangDangdt(tangChuyenDen);
									break;
								}
							}
						}
					}				
					//End Tho add
					lockChuyenKhoa = ((listHsbaKhoa.size() >= 1 || (listHsbaCM != null && listHsbaCM.size() >= 1))  ? "lock" : "");
					
				}
				else {
					// 20110224 bao.ttc: reset lai thong tin tren form
					resetValue();
					//hoSoBenhAn.setHsbaSovaovien("");
					FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
					log.info("khong tim thay sobenhan");
				}
				
				log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
			} else {
				resetValue();
			}

		} catch (Exception e) {
			log.info("ERROR displayInfo=======" + e);
			e.printStackTrace();
		}
		log.info("End displayInfo=======");
	}
	
	public void onblurMaKhoaAction(){
		log.info("-----BEGIN onblurMaKhoaAction()-----");
		if(khoaDangDt != null && khoaDangDt.getDmkhoaMa() != null){
			String maKhoa = khoaDangDt.getDmkhoaMa();
			if(hmDmKhoaNTAll != null){
				DmKhoa dmKhoa = (DmKhoa)hmDmKhoaNTAll.get(maKhoa.toUpperCase());
				if(dmKhoa != null) {
					khoaDangDt.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
			    	khoaDangDt.setDmkhoaMa(dmKhoa.getDmkhoaMa());
			    	khoaDangDt.setDmkhoaTen(dmKhoa.getDmkhoaTen());
					log.info("Tim ma khoa: Da thay khoa "+ khoaDangDt.getDmkhoaTen());
				}
				else {
					khoaDangDt = new DmKhoa();
					return;
				}
			}
			tangChuyenDen = new DmTang();
			refreshDmTang();
		}
		log.info("-----END onblurMaKhoaAction()-----");
	}
	
	public void onblurTenKhoaAction(){
		log.info("-----BEGIN onblurTenKhoaAction()-----");
		if(khoaDangDt != null && khoaDangDt.getDmkhoaTen() != null){
			String tenKhoa = khoaDangDt.getDmkhoaTen();
			Boolean hasTenKhoa = false;
			java.util.Set set = hmDmKhoaNTAll.entrySet();
		    Iterator i = set.iterator();
		    DmKhoa dmKhoa_Finded = new DmKhoa();
		    while(i.hasNext()){
		    	Map.Entry me = (Map.Entry)i.next();
		    	DmKhoa dmKhoa = (DmKhoa)me.getValue();
		    	if(dmKhoa.getDmkhoaTen() == tenKhoa || dmKhoa.getDmkhoaTen().equals(tenKhoa)){
		    		hasTenKhoa = true;
		    		dmKhoa_Finded = dmKhoa;
		    		break;
		    	}	    		
		    }
		    if(hasTenKhoa){
		    	khoaDangDt.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
		    	khoaDangDt.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
		    	khoaDangDt.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
		    }else{
		    	khoaDangDt = new DmKhoa();
		    	return;
		    }
		    tangChuyenDen = new DmTang();
		    refreshDmTang();
		}
		log.info("-----END onblurTenKhoaAction()-----");
	}
	
	public void refreshDmKhoaNT(){
		dmKhoaDel = DmKhoaDelegate.getInstance();
		listDmKhoaNTAll.clear();
		listDmKhoaNTs.clear();
		listDmKhoaNTAll = dmKhoaDel.getKhoaNT();
		hmDmKhoaNTAll.clear();
		for(DmKhoa o: listDmKhoaNTAll){
			hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
		}
		for(DmKhoa each : listDmKhoaNTAll){
			listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
		}
	}
	
	public void onblurTenTangAction(){
		if(tangChuyenDen != null && tangChuyenDen.getDmtangTen() != null){
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", tangChuyenDen.getDmtangTen());
			if(lstTangs != null && lstTangs.size()>0){
				tangChuyenDen = lstTangs.get(0);
			}else{
				tangChuyenDen = new DmTang();
			}
		}
	}
	
	public void refreshDmTang(){
		listDmTangs = new ArrayList<SelectItem>();
		if(khoaDangDt != null && khoaDangDt.getDmkhoaMaso() != null){
			String khoaMa = khoaDangDt.getDmkhoaMa();
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			//Get tat ca cac tang cua khoa chuyen den, show gia tri default truoc
			List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
			if(lstDmTangs != null && lstDmTangs.size()>0){
				for(DmTang dmTang:lstDmTangs){
					listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
				}
				for(DmTang dmTang:lstDmTangs){
					if(dmTang.getDmtangDefault().booleanValue() == true){
						tangChuyenDen = dmTang;
						break;
					}
				}
				// 20120315 bao.ttc: Truong hop khong co tang dafault thi chon tang dau tien trong list
				tangChuyenDen = lstDmTangs.get(0);
			}
		}
	}

	
	/**
	 * 
	 * @throws Exception
	 */
	public void ghiNhan() {
			
		log.info("ghiNhan()");		
		
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
		// Neu nhap benh nhan moi thi kiem tra van de trung thong tin benh nhan
		// neu cap nhat (da co so vao vien ) thi khong can kiem tra
		
		if ((!daCheckTrungBN) && ((hoSoBenhAn.getHsbaSovaovien() == null) || (hoSoBenhAn.getHsbaSovaovien().trim().equals("")))) { // Neu chua kiem tra thong tin trung thi thuc hien			
			// Kiem tra thong tin benh nhan
			// Neu benh nhan la doi tuong bao hiem thi kiem tra theo so the bao hiem
			// Neu benh nhan khong phai la doi tuong bao hiem thi kiem tra theo thong tin ho ten, gioi tinh, nam sinh, ngay gio vao vien
			String hotenBN_Tmp = "";
			String khoaVaoVien_Tmp = "";
			String khoaDangDT_Tmp = "";
			String ngayGioVaoVien_Tmp = "";
			String soVaoVien_Tmp = "";
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			Hsba hsbaTmp = null;
			if(hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")
					&& IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")) { // Doi tuong bao hiem
				List<Hsba> listHsba = hsbaDelegate.findBySoTheBHYT(hsbaBHYT.getHsbabhytSothebh());
				if(listHsba != null && listHsba.size() > 0) {
					for (Hsba eachHsba : listHsba) {
						if(eachHsba.getHsbaNgaygiorav() == null) {
							hsbaTmp = eachHsba;
							break;
						}
					}
				}
			} else { // Doi tuong khong phai bao hiem
				// Tim kiem  theo thong tin ho ten, gioi tinh, nam sinh, ngay gio vao vien				
				hsbaTmp = hsbaDelegate.findByThongTinBenhNhan(benhNhan.getBenhnhanHoten(),(gioi.equals("r1") ? new Integer("2") : new Integer("1")), benhNhan.getBenhnhanNamsinh(), gioVaoVien, ngayVaoVien);
				log.info("after findByThongTinBenhNhan, hsbaTmp = " + hsbaTmp);
			}
			if (hsbaTmp != null) {			
			
				trungBN = true;
				try {
					hotenBN_Tmp = hsbaTmp.getBenhnhanMa().getBenhnhanHoten();
				} catch(Exception e) {}
				try {
					khoaVaoVien_Tmp = hsbaTmp.getHsbaKhoavaov().getDmkhoaTen();
				} catch(Exception e) {}
				try {
					khoaDangDT_Tmp = hsbaTmp.getHsbaKhoadangdt().getDmkhoaTen();
				} catch(Exception e) {}
				try {
					ngayGioVaoVien_Tmp = sdf.format(hsbaTmp.getHsbaNgaygiovaov());
				} catch(Exception e) {}
				try {
					soVaoVien_Tmp = hsbaTmp.getHsbaSovaovien();
				} catch(Exception e) {}
			}
								
			daCheckTrungBN = true;
			strMsgTrungBN = "";
			if (trungBN) {
				if(hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")
						&& IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")) {
					strMsgTrungBN = "B\u1EC7nh nh\u00E2n c\u00F3 s\u1ED1 th\u1EBB BHYT " // Benh nhan co so the BHYT
						+ hsbaBHYT.getHsbabhytSothebh() + "(" + hotenBN_Tmp + ")"+ " \u0111\u00E3 nh\u1EADp vi\u1EC7n l\u00FAc " // da nhap vien luc
						+ ngayGioVaoVien_Tmp + " (S\u1ED1 b\u1EC7nh \u00E1n" + ": " + soVaoVien_Tmp  // So benh an:
						+ "), v\u00E0o khoa "	// vao khoa 
						+ khoaVaoVien_Tmp + ", hi\u1EC7n \u0111ang n\u1EB1m \u1EDF khoa " // hien dang nam o khoa 
						+ khoaDangDT_Tmp + ". C\u00F3 ti\u1EBFp t\u1EE5c nh\u1EADp kh\u00F4ng?" ; // Co tiep tuc khong?
				} else {
					
					strMsgTrungBN = "B\u1EC7nh nh\u00E2n " // Benh nhan
						+ hotenBN_Tmp + " \u0111\u00E3 nh\u1EADp vi\u1EC7n l\u00FAc " // da nhap vien luc 
						+ ngayGioVaoVien_Tmp + " (S\u1ED1 b\u1EC7nh \u00E1n" + ": " + soVaoVien_Tmp  // So benh an:
						+ "), v\u00E0o khoa " // vao khoa 
						+ khoaVaoVien_Tmp  + ", hi\u1EC7n \u0111ang n\u1EB1m \u1EDF khoa " // hien dang nam o khoa 
						+ khoaDangDT_Tmp + ". C\u00F3 ti\u1EBFp t\u1EE5c nh\u1EADp kh\u00F4ng?" ; // Co tiep tuc khong?
				}
				
				return;
			}
			
		}
		//20110804 bao.ttc: neu he thong dang Ghi nhan thi khong xu ly ham ghiNhan() de tranh user nhan nhieu lan
		if (dangGhinhan == true){
			log.info("He thong dang Ghi nhan thong tin !!");
			return;
		}
		dangGhinhan = true;
		log.info("########################### He thong dang Ghi nhan thong tin !!" + dangGhinhan);
		
		try {
			yteLog = new YteLog();
			listDataLog="";
			String strLog = "";
			daCheckTrungBN = false;
			trungBN = false;
			strMsgTrungBN = "";
			String sba = hoSoBenhAn.getHsbaSovaovien();
			String maDoituong_New = hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
			log.info("sobenhan: " + hoSoBenhAn);
			if (gioVaoVien != null && !gioVaoVien.equals("")  && ngayVaoVien != null && !ngayVaoVien.equals("")) {
				Calendar hsbaNgaygiovaov = Utils.getDBDate( gioVaoVien , ngayVaoVien);
				if (hsbaNgaygiovaov != null){
					hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
				}else{
					log.info(" hsbaNgaygiovaov : NULL . LAY NGAY GIO HE THONG");
					hoSoBenhAn.setHsbaNgaygiovaov(new Date());
				}
			}else{
				hoSoBenhAn.setHsbaNgaygiovaov(new Date());
			}

			if (ngaySinh != null && !ngaySinh.equals("")) {
				benhNhan.setBenhnhanNgaysinh(Utils.getDBDate("00:00", ngaySinh).getTime());
			}else{
				benhNhan.setBenhnhanNgaysinh(null);
			}
			
			log.info("gioi : " + gioi);
			if (gioi == null || gioi.equals("")) {
				benhNhan.setDmgtMaso(null);
			} else if ("r1".equals(gioi)) { //Nam
				DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
				DmGioiTinh gioiTinh = (DmGioiTinh)dele.findByMa("1", "DmGioiTinh", "dmgtMa");
				benhNhan.setDmgtMaso(gioiTinh);
			} else { //Nu
				DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
				DmGioiTinh gioiTinh = (DmGioiTinh)dele.findByMa("0", "DmGioiTinh", "dmgtMa");
				benhNhan.setDmgtMaso(gioiTinh);
			}

			SimpleDateFormat formatter;	    
	        formatter = new SimpleDateFormat(FORMAT_DATE);
	        ////////////
			if (giatri1 != null && !giatri1.equals("")){
				Date dGiaTri1 = formatter.parse(giatri1);
				hsbaBHYT.setHsbabhytGiatri0(dGiaTri1);
			}else{
				hsbaBHYT.setHsbabhytGiatri0(null);
			}
			if (giatri2 != null && !giatri2.equals("")){
				Date dGiaTri2 = formatter.parse(giatri2);
				hsbaBHYT.setHsbabhytGiatri1(dGiaTri2);
			}else{
				hsbaBHYT.setHsbabhytGiatri1(null);
			}
			if (giatri3 != null && !giatri3.equals("")){
				Date dGiaTri3 = formatter.parse(giatri3);
				hsbaBHYT.setHsbabhytGiatri2(dGiaTri3);
			}else{
				hsbaBHYT.setHsbabhytGiatri2(null);
			}
			if (giatri4 != null && !giatri4.equals("")){
				Date dGiaTri4 = formatter.parse(giatri4);
				hsbaBHYT.setHsbabhytGiatri3(dGiaTri4);
			}else{
				hsbaBHYT.setHsbabhytGiatri3(null);
			}			
			
			if (moc1 != null && !moc1.equals("")){
				Date dmoc1 = formatter.parse(moc1);
				hsbaBHYT.setHsbabhytMoc1(dmoc1);
			}else{
				hsbaBHYT.setHsbabhytMoc1(null);
			}
			
			if (moc2 != null && !moc2.equals("")){
				Date dmoc2 = formatter.parse(moc2);
				hsbaBHYT.setHsbabhytMoc2(dmoc2);
			}else{
				hsbaBHYT.setHsbabhytMoc2(null);
			}
			
			if (moc3 != null && !moc3.equals("")){
				Date dmoc3 = formatter.parse(moc3);
				hsbaBHYT.setHsbabhytMoc3(dmoc3);
			}else{
				hsbaBHYT.setHsbabhytMoc3(null);
			}
			DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
			// Kiem tra noi DK Kham chua benh
			if(maDoituong_New.equalsIgnoreCase("BH")) {
				if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMaso() != null) {
					DmBenhVien noiKCB = (DmBenhVien) dele.findByMa(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
					if(noiKCB == null) {
						FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.NOI_DK_KCB, hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());
						dangGhinhan = false;
						return;
					} else {
						hsbaBHYT.setHsbabhytMakcb(noiKCB);
					}
				} else {
					dangGhinhan = false;
					FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.NOI_DK_KCB);
					return;
				}
			}
			// 20110521 bao.ttc: set TUYEN theo CAP_TRIEN_KHAI_PHAN_MEM
			
			Short tuyenBH = new Short("1");
			// 20110224 bao.ttc: neu co giay chuyen vien thi set Tuyen = 1, khong thi giu nguyen Tuyen set o giao dien
			if (hsbaBHYT.getHsbabhytCoGiayChuyenVien() != null && hsbaBHYT.getHsbabhytCoGiayChuyenVien()){
				tuyenBH = new Short("1");
				
			} else if(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN)) {
				tuyenBH = new Short("1");
			
			} else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH")){

				if(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().startsWith(IConstantsRes.TINH_BHYT_DEFAULT)) {
					tuyenBH = new Short("2");
				} else {
					tuyenBH = new Short("3");				
				}
				
			} else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN")){
				if (hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso() != null 
						&& hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI)){
					tuyenBH = new Short("2");
				} else {
					tuyenBH = new Short("3");
				}
			} else {
				tuyenBH = new Short("3");
			}
			
			// 20110726 bao.ttc: Truong hop Cap cuu thi luon xem nhu BN dung Tuyen
			if (hoSoBenhAn.getHsbaCapcuu() != null && hoSoBenhAn.getHsbaCapcuu()){
				tuyenBH = new Short("1");
			}
			
			hsbaBHYT.setHsbabhytTuyen(tuyenBH);
			// END -- 20110521 bao.ttc: set TUYEN theo CAP_TRIEN_KHAI_PHAN_MEM
			
			hoSoBenhAn.setHsbaNgaygiocn(Calendar.getInstance().getTime());
			if(maDoituong_New.equalsIgnoreCase("BH")) {
				if (hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso() != null){				
					DmTinh tinh =  (DmTinh) dele.findByMaSo(hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso(),"DmTinh","dmtinhMaso");
					if (tinh != null){
						hsbaBHYT.setHsbabhytTinhbh(tinh);
					}else{
						//hsbaBHYT.setHsbabhytTinhbh(null);
						FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.TINH_CAP_BHYT, hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT());
						dangGhinhan = false;
						return;
					}
				}else{
					dangGhinhan = false;
					FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.TINH_CAP_BHYT);
					return;
				}
			}
			hoSoBenhAn.setHsbaKhoadangdt(khoaDangDt);
			
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromBN(benhNhan);
			RemoveUtil.removeAllNullFromHSBABHYT(hsbaBHYT);
			
			String sKetQua = hsbaDelegate.capNhatThongTinNhapVien(hoSoBenhAn, hsbaBHYT, benhNhan, tangChuyenDen);
			if(sKetQua.equals("error")){
				FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
				return;
			}	
			
			if(sba != null && sba.trim().equals("")){
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS + ": " + sKetQua);
			}
			else{
				FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS + ": " + sKetQua);
			}
			hoSoBenhAn.setHsbaSovaovien(sKetQua); // set lai so vao vien sau khi ghi nhan
			
			resultHidden="success";
			
			HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
			HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
			
			if (hsbaBhytLast != null) {
				hsbaBHYT = hsbaBhytLast;
				SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
			}else{
				hsbaBHYT = new HsbaBhyt();
				SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
			}
			/*
			// Cap nhat don gia cho cac CLS (noi tru) da chi dinh truoc khi thay doi thong tin the bao hiem
			if(hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")){
				hsbaBhytDelegate.capnhatGiaClsTheoThoiGianBaoHiem(hsbaBHYT);			
				// Cap nhat don gia cho cac CLS (ngoai tru) neu co chuyen so lieu tu ngoai tru vao noi tru
				ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(sKetQua);
				if(cvnt != null) {
					TiepDonDelegate.getInstance().capnhatGiaClsTheoThoiGianBaoHiem(cvnt.getTiepDon());			
				}
			}
			*/
			//phuc.lc : 18-08-2011 Fix bug 3523 
			// Truong hop thay doi doi tuong kham benh, cap nhat lai don gia cac CLS da chi dinh
			// chi cap nhat gia CSL, khong can cap nhat thuoc vi gia thuoc khong phu thuoc vao doi tuong kham benh
			if (sba != null && !sba.trim().equals("")){
				log.info("maDoituong_Old = " + maDoituong_Old + ", maDoituong_New = " + maDoituong_New);
				if(! maDoituong_Old.equals(maDoituong_New)) {
					strLog += "Chuyen Doi Tuong " + maDoituong_Old + " --> "+ maDoituong_New;
					maDoituong_Old = maDoituong_New;
					Date ngayBatDauBh = null;
					Date ngayHetHanBh = null;
					
					if (giatri3 != null && !giatri3.equals("")){
						ngayBatDauBh = formatter.parse(giatri3);
					} else if (giatri1 != null && !giatri1.equals("")){
						ngayBatDauBh = formatter.parse(giatri1);	
					}
					
					if (giatri4 != null && !giatri4.equals("")){
						ngayHetHanBh = formatter.parse(giatri4);
					} else if (giatri2 != null && !giatri2.equals("")){
						ngayHetHanBh = formatter.parse(giatri2);	
					}
					// Cap nhat clsMo					
					ClsMoDelegate clsMoDelegate  = ClsMoDelegate.getInstance();												
					List<ClsMo> listClsMo = clsMoDelegate.findBySoVaoVien(sba);
					for(ClsMo cls : listClsMo) {
						if(cls.getClsmoKhongthu() != null && cls.getClsmoKhongthu().booleanValue() == true) {
							// Neu CLS khong thu thi lay don gia = 0 (khong can cap nhat lai)
							//cls.setClsmoDongia(new Double(0));
							continue;
						}else if(cls.getClsmoYeucau() != null && cls.getClsmoYeucau().booleanValue() == true) {
							// Neu CLS yeu cau thi lay don gia yeu cau (khong can cap nhat lai)
							//cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiayc());
							continue;
						}else if(cls.getClsmoMien() != null && cls.getClsmoMien().booleanValue() == true) {
							// Neu CLS mien thi lay don gia mien (khong can cap nhat lai)
							//cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiamp());
							continue;
						} else if(maDoituong_New.equalsIgnoreCase("BH")) {
							Date ngayChidinhCLS = formatter.parse(formatter.format(cls.getClsmoNgay()));							
							// Cls ngoai danh muc hoac het han bao hiem thi lay don gia
							if((cls.getClsmoNDM() != null && cls.getClsmoNDM().booleanValue() == true) 
									|| ngayBatDauBh == null || ngayHetHanBh == null 
									|| ngayChidinhCLS.before(ngayBatDauBh) || ngayChidinhCLS.after(ngayHetHanBh)) {
								cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
							} else {
								// Lay gia bao hiem
								cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiabh());
							}						
						}else if(maDoituong_New.equalsIgnoreCase("MP")) {
							cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiamp());
						}else {
							cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
						}
						cls.setClsmoDongiabh(cls.getClsmoMahang().getDtdmclsbgDongiabh());
						clsMoDelegate.edit(cls);
					}
					// Cap nhat thuoc noi tru
					TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
					List<ThuocNoiTru> listTnt = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(sba);
					if(listTnt != null && listTnt.size() > 0) {
						ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();						
						for(ThuocNoiTru eachTnt : listTnt) {
							TonKho tonKho = tkDel.findTonkhoByMalienket(eachTnt.getThuocnoitruMalk());
							if(maDoituong_New.equalsIgnoreCase("MP")) {
								if(eachTnt.getThuocnoitruYeucau() != null && eachTnt.getThuocnoitruYeucau().booleanValue() == true) {
									eachTnt.setThuocnoitruDongia(tonKho.getTonkhoDongia());
								} else {
									eachTnt.setThuocnoitruDongia(new Double(0.0));
								}
							} else {	// Benh nhan BH va Thu phi
								if (eachTnt.getThuocnoitruKhongthu() != null && eachTnt.getThuocnoitruKhongthu().booleanValue() == true) {
									eachTnt.setThuocnoitruDongia(new Double(0.0));
								} else {
									eachTnt.setThuocnoitruDongia(tonKho.getTonkhoDongia());
								}
							}
							if (eachTnt.getThuocnoitruMaPhieuDT() == null) {
								eachTnt.setDmdoituongMaso(hoSoBenhAn.getDoituongMa());
							}
							tntDel.edit(eachTnt);
						}
					}
					//phuc.lc 04/11/2011 : Nghiep vu thay doi
					// Khi thay doi doi tuong o noi tru thi khong anh huong gi den ngoai tru
					// khong can cap nhat du lieu ngoai tru doi voi cac truong hop chuyen so lieu tu ngoai tru vao noi tru
					/*
					ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(sKetQua);
					if(cvnt != null) {
						// Cap nhat clsKham
						ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();						
						List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());
						for (ClsKham cls : listClsKham) {
							if(cls.getClskhamKhongthu() != null && cls.getClskhamKhongthu().booleanValue() == true) {
								//cls.setClskhamDongia(new Double(0));
								continue;
							} else if(cls.getClskhamYeucau() != null && cls.getClskhamYeucau().booleanValue() == true) {
								// Neu co chon yeu cau thi lay don gia yeu cau							
								//cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiayc());
								continue;
							} else {
								// Neu khong yeu cau thi xet theo tung doi tuong		
								if (maDoituong_New.equalsIgnoreCase("BH")){									
									Date ngayChidinhCLS = formatter.parse(formatter.format(cls.getClskhamNgaygio()));
									if (cls.getClskhamMien() != null && cls.getClskhamMien().booleanValue() == true){
										// CLS mien phi ==> lay gia mien phi
										cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
									} else if ((cls.getClskhamNdm() != null && cls.getClskhamNdm().booleanValue() == true)
											|| ngayBatDauBh == null || ngayHetHanBh == null 
											|| ngayChidinhCLS.before(ngayBatDauBh) || ngayChidinhCLS.after(ngayHetHanBh)){
										// CLS ngoai danh muc hoac het han bao hiem ==> lay gia thuong
										cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
									} else {
										// Cac truong hop con lai ==> lay gia bao hiem
										cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiabh());
									}
								} else if (maDoituong_New.equalsIgnoreCase("MP")) {
									// Doi tuong mien phi neu khong yeu cau thi luon luon lay gia mien phi
									cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
								} else if (maDoituong_New.equalsIgnoreCase("TP")) {
									if (cls.getClskhamMahang().getDtdmclsbgMien()){
										// CLS mien phi ==> lay gia mien phi
										cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp()); 
									} else {
										cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
									}
								}
							}
							clsKhamDelegate.edit(cls);
						}
						ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
						List<ThuocPhongKham> listTpk = tpkDel.find2LoaiByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(),Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
						if(listTpk != null && listTpk.size() > 0) {													
							for(ThuocPhongKham eachTpk : listTpk) {
								TonKho tonKho = tkDel.findTonkhoByMalienket(eachTpk.getThuocphongkhamMalk());
								if(maDoituong_New.equalsIgnoreCase("MP")) {
									if(eachTpk.getThuocphongkhamYeucau() != null && eachTpk.getThuocphongkhamYeucau().booleanValue() == true) {
										eachTpk.setThuocphongkhamDongia(tonKho.getTonkhoDongia());
									} else {
										eachTpk.setThuocphongkhamDongia(new Double(0.0));
									}
								} else {	// Benh nhan BH va Thu phi
									if (eachTpk.getThuocphongkhamKhongthu() != null && eachTpk.getThuocphongkhamKhongthu().booleanValue() == true) {
										eachTpk.setThuocphongkhamDongia(new Double(0.0));
									} else {
										eachTpk.setThuocphongkhamDongia(tonKho.getTonkhoDongia());
									}
								}
								tpkDel.edit(eachTpk);
							}
						}
					}
					*/
					// phuc.lc : 04/11/2011 END
				}
				
			}

//			Luu log he thong
	         yteLog.setForm("B212_Capnhatthongtinnhapvien");
	         yteLog.setUserId(identity == null ? "NULL" : identity.getUsername());
	         yteLog.setObjectId(hoSoBenhAn.getHsbaSovaovien());
	         String dtBH = "";
	         if (hsbaBHYT!= null && maDoituong_New.equals("BH")){
	        	 dtBH += " BHYT So the: "+ hsbaBHYT.getHsbabhytSothebh()
	        	 		+" DT BHYT: "+ (hsbaBHYT.getHsbabhytKhoibh() == null ? "NULL" : hsbaBHYT.getHsbabhytKhoibh(true).getDtdmkhoibhytMa())+
	        	 		" Tuyen BH: "+ hsbaBHYT.getHsbabhytTuyen()+
	        	 		" Gia tri tu ngay: "+ giatri1 + " den ngay: "+ giatri2+
	        	 		" Gia tri benh vien: "+ giatri3 + " den ngay: "+ giatri4+
	        	 		" Moc 1: "+ moc1+ 
	        	 		" Moc 2: "+ moc2+
	        	 		" Moc 3: "+ moc3;
	         }
	         yteLog.setLogString(//" Ma benh nhan: "+ benhNhan.getBenhnhanMa() +
	        		 			strLog + 
	        		 			" Ho ten BN: " + benhNhan.getBenhnhanHoten()+
	        		 			" Vao vien luc: "+ gioVaoVien + " " + ngayVaoVien +
	        		 			" CD Tuyen truoc: "+ hoSoBenhAn.getHsbaMachdoantuyent(true).getDmbenhicdMa()+
	        		 			" CD Ban dau: "+ hoSoBenhAn.getHsbaMachdoanbd(true).getDmbenhicdMa()+
	        		 			" Doi tuong: "+ hoSoBenhAn.getDoituongMa(true).getDmdoituongMa()+
	        		 			dtBH+
	        		 			" Hinh thuc DT: "+ hoSoBenhAn.getHsbaDieutri(true).getDmdieutriMa()+
	        		 			" Khoa DT: "+ (khoaDangDt == null ? " NULL":khoaDangDt.getDmkhoaMa())+
	        		 			" Tang DT" + (tangChuyenDen == null ? "NULL" : tangChuyenDen.getDmtangMa())
	         					);
	         yteLog.setDateTime(new Date());
	         yteLog.setListData(listDataLog);
	         
	         yteLogDele.create(yteLog);
			//phuc.lc : 18-08-2011 End
			resetValue(); // 20110804 bao.ttc: ket thuc Ghi nhan: dangGhinhan = false
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.FAIL);
			resultHidden="fail";
		}
	}
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT;
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	int index =1;
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="giaykhambenhvaovien";
		log.info("Vao Method XuatReport giaykhambenhvaovien");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/giaykhambenhvaovien.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("HoTen", benhNhan.getBenhnhanHoten());
			
			if (benhNhan.getBenhnhanNgaysinh() != null) {
				params.put("NgaySinh", sdf.format(benhNhan.getBenhnhanNgaysinh()));
			} else {
				params.put("NgaySinh", benhNhan.getBenhnhanNamsinh());
			}
			
			params.put("Tuoi", benhNhan.getBenhnhanTuoi());
			
			params.put("DonViTuoi",  benhNhan.getBenhnhanDonvituoi()==null?new Integer(1):new Integer(benhNhan.getBenhnhanDonvituoi()) );
			
			/*
			 * 
			 * 
			 */
			
			params.put("Gioi", benhNhan.getDmgtMaso(true).getDmgtTen());
			
			log.info("================= benhNhan.getDmgtMaso(true).getDmgtTen():" + benhNhan.getDmgtMaso(true).getDmgtTen());
			
			DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
			
			DmDanToc danToc = (DmDanToc)dele.findByMa(benhNhan.getDantocMa(true).getDmdantocMa(), "DmDanToc", "dmdantocMa");
		
			if (danToc != null ){
				params.put("DanToc", danToc.getDmdantocTen());
				params.put("MaDanToc", danToc.getDmdantocMa());
			}else{
				params.put("DanToc", "");
				params.put("MaDanToc", "");
			}
			
			
			
			params.put("DiaChi", benhNhan.getBenhnhanDiachi());
			
			DmHuyen huyen = (DmHuyen)dele.findByMa(benhNhan.getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
			if (huyen != null){
				params.put("Huyen", huyen.getDmhuyenTen());
				params.put("MaHuyen", huyen.getDmhuyenMa());
			}else{
				params.put("Huyen", "");
				params.put("MaHuyen", "");
			}
			
			DmTinh tinh = (DmTinh)dele.findByMa(benhNhan.getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
			if (tinh != null){
				params.put("Tinh", tinh.getDmtinhTen());
				params.put("MaTinh", tinh.getDmtinhMa());
			}else{
				params.put("Tinh", "");
				params.put("MaTinh", "");
			}
			
					
			
			
			DmQuocGia quocGia = (DmQuocGia)dele.findByMa(benhNhan.getQuocgiaMa(true).getDmquocgiaMa(), "DmQuocGia", "dmquocgiaMa");
			
			
			if (quocGia!= null && !"VNA".equals(quocGia.getDmquocgiaMa())){
				params.put("NgoaiKieu", quocGia.getDmquocgiaTen());
				params.put("MaNgoaiKieu", quocGia.getDmquocgiaMa());
			}else{
				params.put("NgoaiKieu", "");
				params.put("MaNgoaiKieu","");
			}
			
			
			
			DmNgheNghiep ngheNghiep = (DmNgheNghiep)dele.findByMa(benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa(), "DmNgheNghiep", "dmnghenghiepMa");
			
			if (ngheNghiep != null){
				params.put("NgheNghiep", ngheNghiep.getDmnghenghiepTen());															
				params.put("MaNgheNghiep", ngheNghiep.getDmnghenghiepMa());
			}else{
				params.put("NgheNghiep", "");															
				params.put("MaNgheNghiep", "");
				
			}
			log.info("nghe nghiep :"+ngheNghiep);
			
			String doiTuongMa = hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
			if (doiTuongMa != null && doiTuongMa.equals("BH")){
				params.put("DoiTuong_BHYT", "X");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "");
			}else if (doiTuongMa != null && doiTuongMa.equals("TP")){
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi", "X");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "");
			}else if (doiTuongMa != null && doiTuongMa.equals("MP")){
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", "X"); 
				params.put("DoiTuong_Khac",  "");
			}else {
				params.put("DoiTuong_BHYT", "");
				params.put("DoiTuong_ThuPhi", "");
				params.put("DoiTuong_Mien", ""); 
				params.put("DoiTuong_Khac",  "X");
			}
			
			if (hsbaBHYT.getHsbabhytCoquanbh() != null && !hsbaBHYT.getHsbabhytCoquanbh().equals("")){
				params.put("NoiLamViec",  hsbaBHYT.getHsbabhytCoquanbh());
			}else{
				params.put("NoiLamViec",  "");
			}
		
			
			
			params.put("ThoiHanBaoHiem",hsbaBHYT.getHsbabhytGiatri1());
			
			if (hsbaBHYT.getHsbabhytSothebh() != null && !hsbaBHYT.getHsbabhytSothebh().equals("")){
				if (hsbaBHYT.getHsbabhytKhoibh() != null ){
					params.put("SoTheBHYT", hsbaBHYT.getHsbabhytSothebh() + " - " + hsbaBHYT.getHsbabhytKhoibh().getDtdmkhoibhytMa());
				}else{
					params.put("SoTheBHYT", hsbaBHYT.getHsbabhytSothebh());
				}
				
			}else{
				params.put("SoTheBHYT", "");
			}
			
			params.put("NguoiBaoTin", ""); 
			
			params.put("NgayGioVaoVien", hoSoBenhAn.getHsbaNgaygiovaov());
			
			String diengiaitt  = hoSoBenhAn.getHsbaDiengiaituyent();
			if (diengiaitt == null){
				diengiaitt = "";
			}
			DmBenhIcd benhtuyentruoc = (DmBenhIcd)dele.findByMa(hoSoBenhAn.getHsbaMachdoantuyent(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
			if (benhtuyentruoc != null){
				
				
				params.put("ChanDoanTuyenTruoc", benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt );
			}else{
				if (diengiaitt.equals("")){
					diengiaitt = "";
				}
				params.put("ChanDoanTuyenTruoc", diengiaitt);
			}
			
			params.put("SoVaoVien", hoSoBenhAn.getHsbaSovaovien());
			
			params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
															
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","giaykhambenhvaovien");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	private boolean checkTrungMaBnInListBn(String maBN, List<BenhNhanTrungTheBhytDTO> listBN) {		
		if (listBN == null) return false;
		if (listBN.size() < 1 ) return false;
		for(BenhNhanTrungTheBhytDTO eachBN : listBN) {			
			if(eachBN.getBnMa().equals(maBN)) {
				return true;
			}
		}
		return false;
	}
	public void checkSoTheBHYT() {
		String sothe = hsbaBHYT.getHsbabhytSothebh();
		if (sothe.equals("")){
			return;
		}
		
		// 20111221 bao.ttc: truong hop doi Doi tuong (tu thu phi -> BHYT) thi ko search so the BHYT
		String svv = hoSoBenhAn.getHsbaSovaovien();
		if (svv != null && !svv.trim().equals("")) {
			return;
		}
		
		//phuc.lc 09/08/2011
		// Kiem tra so the bao hiem da co hay chua
		// Neu so the bao hiem da co va thong so cau hinh CHO_PHEP_TRUNG_SO_THE_BHYT = NO thi hien thi thong tin tim duoc theo so the len giao dien
		// Neu so the bao hiem da co va thong so cau hinh CHO_PHEP_TRUNG_SO_THE_BHYT = YES thi hien thi danh sach cac benh nhan trung so the bao hiem
		// Nguoi dung neu chon nhap moi thi cho phep nhan them benh nhan moi co cung so the bao hiem
		// Neu nguoi dung chon 1 benh nhan trong danh sach thi load lai thong tin cua benh nhan do de tao hsba moi
		showListBn = false;
		//Khong can kien tra hsba noi tru, chi can lay danh sach benh trung the bao hiem theo tiep don
		List<TiepDon> listTD = TiepDonDelegate.getInstance().findBySoTheBHYT(sothe);
		
		if (listTD != null && listTD.size() > 0) {
			if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")) {
				// Neu he thong khong cho phep trung so the, thi hien thi thong tin benh nhan cu va cho phep nhap benh an moi								
				Hsba hoSoBenhAn_temp = HsbaDelegate.getInstance().findByTiepDonMa(listTD.get(0).getTiepdonMa());
				
				
				if (hoSoBenhAn_temp != null) {	
					hoSoBenhAn = hoSoBenhAn_temp;
					SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
					benhNhan = hoSoBenhAn.getBenhnhanMa();								
					SimpleDateFormat formatter= new SimpleDateFormat(FORMAT_DATE);
	
					if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")) {
						ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
					}
					SetInforUtil.setInforIfNullForBN(benhNhan);
					if (benhNhan.getDmgtMaso() != null){
						if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
							gioi = "r1";  //1 : Nam
						}else{
							gioi = "r2";
						}					
					}else{
						gioi = null;
					}
					HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
					HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
	
					if (hsbaBhytLast != null) {
						hsbaBHYT = hsbaBhytLast;
						SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
					}
					
					// Set sovaovien = null de nhap moi
					hoSoBenhAn.setHsbaSovaovien(null);
					
					// 20111115 bao.ttc: set lai Cac thong tin vao vien truoc khong can LOAD lai de tao HSBA moi
					gioVaoVien=Utils.getGioPhut(new Date());
					ngayVaoVien= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
					gioRaVien = "";
					ngayRaVien = "";
					
					hoSoBenhAn.setHsbaNgaygiovaov(null);
					hoSoBenhAn.setHsbaNgaygiorav(null);
					hoSoBenhAn.setHsbaType(null);
					hoSoBenhAn.setHsbaIsNoitru(true);
					hoSoBenhAn.setHsbaNgaygiotv(null);
					hoSoBenhAn.setHsbaNgaygiocn(new Date());
					
					hoSoBenhAn.setHsbaCapcuu(null);
					hoSoBenhAn.setHsbaRuoubia(null);
					hoSoBenhAn.setHsbaKetqua(null);
					hoSoBenhAn.setHsbaTuvong(null);
					hoSoBenhAn.setHsbaTuvvong24g(null);
					
					hoSoBenhAn.setTainanMa(null);
					hoSoBenhAn.setDmptgtnMaso(null);
					hoSoBenhAn.setHsbaNguyennhan(null);
					hoSoBenhAn.setDiadiemMa(null);
					
					hoSoBenhAn.setHsbaDonvigoi(null);
					hoSoBenhAn.setHsbaMachdoantuyent(null);
					hoSoBenhAn.setHsbaDiengiaituyent(null);
					hoSoBenhAn.setHsbaMachdoanbd(null);
					hoSoBenhAn.setHsbaDiengiaibd(null);
					
					if (hoSoBenhAn.getHsbaKhoadangdt() != null){
						hoSoBenhAn.setHsbaKhoadangdtCm(hoSoBenhAn.getHsbaKhoadangdt());
						hoSoBenhAn.setHsbaKhoavaov(hoSoBenhAn.getHsbaKhoadangdt());
					} else {
						hoSoBenhAn.setHsbaKhoadangdtCm(null);
						hoSoBenhAn.setHsbaKhoavaov(null);
					}
					hoSoBenhAn.setHsbaKhoarav(null);
					hoSoBenhAn.setHsbaMachdravien(null);
					hoSoBenhAn.setHsbaYeuCau(null);
					// END -- 20111115 bao.ttc: set lai Cac thong tin vao vien truoc khong can LOAD lai de tao HSBA moi
					
					setOtherInfor();
					
				}
			} else {				
				// Neu cho phep trung so the, thi hien thi danh sach benh nhan trung so the de lay thong tin benh nhan cu hoac nhap benh nhan moi
				if(listBN == null) {
					listBN = new ArrayList<BenhNhanTrungTheBhytDTO>();
				} else {
					listBN.clear();
				}
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");				
				for(TiepDon eachTD : listTD) {
					if (eachTD.getBenhnhanMa() != null) {
						// Kiem tra ma benh nhan da co trong listBN hay chua?
						// Neu chua co thi add benh nhan vao list, neu co roi thi khong add nua
						if (!checkTrungMaBnInListBn(eachTD.getBenhnhanMa().getBenhnhanMa(), listBN)) {
							BenhNhanTrungTheBhytDTO bn = new BenhNhanTrungTheBhytDTO();
							bn.setTiepdonMa(eachTD.getTiepdonMa());							
							bn.setBnMa(eachTD.getBenhnhanMa().getBenhnhanMa());
							bn.setBnHoTen(eachTD.getBenhnhanMa().getBenhnhanHoten());
							bn.setBnGioiTinh(eachTD.getBenhnhanMa().getDmgtMaso().getDmgtTen());
							bn.setBnNamSinh(eachTD.getBenhnhanMa().getBenhnhanNamsinh());
							bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());
							bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : formatter.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" :formatter.format(eachTD.getTiepdonGiatri2())));
							listBN.add(bn);
						}
					}
				}
				if(listBN.size() > 0) {
					showListBn = true;
				}
				
			}
			FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, sothe);
		}
	}
	public void nhapBnMoi() {				
		FacesMessages.instance().clear();
		//hoSoBenhAn.setHsbaSovaovien("");
		//benhNhan.setBenhnhanMa("");
		showListBn = false;
	}
	public void layThongTinBnCu(int index) {
		log.info("Begin layThongTinBnCu, index = " + index + "hoSoBenhAn.getHsbaSovaovien() = " + hoSoBenhAn.getHsbaSovaovien());
		SimpleDateFormat formatter= new SimpleDateFormat(FORMAT_DATE);
		Hsba hoSoBenhAn_temp = HsbaDelegate.getInstance().findByTiepDonMa(listBN.get(index).getTiepdonMa());	
		log.info("hoSoBenhAn_temp = " + hoSoBenhAn_temp);
		if (hoSoBenhAn_temp != null) { // Truong hop benh nhan da co ho so benh an
			String sba = hoSoBenhAn.getHsbaSovaovien();
			hoSoBenhAn = hoSoBenhAn_temp;
			log.info("hoSoBenhAn = " + hoSoBenhAn);
			HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
			HsbaBhyt hsbaBhytLast_Old = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
			log.info("hsbaBhytLast_Old = " + hsbaBhytLast_Old);
			hoSoBenhAn.setHsbaSovaovien(sba);
			SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
			benhNhan = hoSoBenhAn.getBenhnhanMa();											

			if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")) {
				ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
			}
			SetInforUtil.setInforIfNullForBN(benhNhan);
			if (benhNhan.getDmgtMaso() != null){
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
					gioi = "r1";  //1 : Nam
				}else{
					gioi = "r2";
				}					
			}else{
				gioi = null;
			}
			
			HsbaBhyt hsbaBhytLast_New = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
			log.info("hsbaBhytLast_New = " + hsbaBhytLast_New);

			if (hsbaBhytLast_New != null) {
				hsbaBHYT = hsbaBhytLast_New;
				SetInforUtil.setInforIfNullForHSBABHYT(hsbaBHYT);
			} else if (hsbaBhytLast_Old != null){
				hsbaBHYT.setHsbabhytGiatri0(hsbaBhytLast_Old.getHsbabhytGiatri0());
				hsbaBHYT.setHsbabhytGiatri1(hsbaBhytLast_Old.getHsbabhytGiatri1());
				hsbaBHYT.setHsbabhytGiatri2(hsbaBhytLast_Old.getHsbabhytGiatri2());
				hsbaBHYT.setHsbabhytGiatri3(hsbaBhytLast_Old.getHsbabhytGiatri3());
				hsbaBHYT.setHsbabhytMoc1(hsbaBhytLast_Old.getHsbabhytMoc1());
				hsbaBHYT.setHsbabhytMoc2(hsbaBhytLast_Old.getHsbabhytMoc2());
				hsbaBHYT.setHsbabhytMoc3(hsbaBhytLast_Old.getHsbabhytMoc3());
			}
			
			// Set sovaovien = null de nhap moi
			hoSoBenhAn.setHsbaSovaovien(null);
			
			// 20111115 bao.ttc: set lai Cac thong tin vao vien truoc khong can LOAD lai de tao HSBA moi
			gioVaoVien=Utils.getGioPhut(new Date());
			ngayVaoVien= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			gioRaVien = "";
			ngayRaVien = "";
			
			hoSoBenhAn.setHsbaNgaygiovaov(null);
			hoSoBenhAn.setHsbaNgaygiorav(null);
			hoSoBenhAn.setHsbaType(null);
			hoSoBenhAn.setHsbaIsNoitru(true);
			hoSoBenhAn.setHsbaNgaygiotv(null);
			hoSoBenhAn.setHsbaNgaygiocn(new Date());
			
			hoSoBenhAn.setHsbaCapcuu(null);
			hoSoBenhAn.setHsbaRuoubia(null);
			hoSoBenhAn.setHsbaKetqua(null);
			hoSoBenhAn.setHsbaTuvong(null);
			hoSoBenhAn.setHsbaTuvvong24g(null);
			
			hoSoBenhAn.setTainanMa(null);
			hoSoBenhAn.setDmptgtnMaso(null);
			hoSoBenhAn.setHsbaNguyennhan(null);
			hoSoBenhAn.setDiadiemMa(null);
			
			hoSoBenhAn.setHsbaDonvigoi(null);
			hoSoBenhAn.setHsbaMachdoantuyent(null);
			hoSoBenhAn.setHsbaDiengiaituyent(null);
			hoSoBenhAn.setHsbaMachdoanbd(null);
			hoSoBenhAn.setHsbaDiengiaibd(null);
			
			if (hoSoBenhAn.getHsbaKhoadangdt() != null){
				hoSoBenhAn.setHsbaKhoadangdtCm(hoSoBenhAn.getHsbaKhoadangdt());
				hoSoBenhAn.setHsbaKhoavaov(hoSoBenhAn.getHsbaKhoadangdt());
			} else {
				hoSoBenhAn.setHsbaKhoadangdtCm(null);
				hoSoBenhAn.setHsbaKhoavaov(null);
			}
			hoSoBenhAn.setHsbaKhoarav(null);
			hoSoBenhAn.setHsbaMachdravien(null);
			hoSoBenhAn.setHsbaYeuCau(null);
			// END -- 20111115 bao.ttc: set lai Cac thong tin vao vien truoc khong can LOAD lai de tao HSBA moi
			
			setOtherInfor();
			
		} else {
			// Truong hop chi trung so the bao hiem voi cac benh nhan ngoai tru (khong co ho so benh an)
			// thi chi lay thong tin hanh chinh cua benh nhan
			TiepDon tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(listBN.get(index).getTiepdonMa());
			benhNhan = tiepdon.getBenhnhanMa();
			if (benhNhan.getBenhnhanNgaysinh() != null && !benhNhan.getBenhnhanNgaysinh().equals("")) {
				ngaySinh = formatter.format(benhNhan.getBenhnhanNgaysinh().getTime());
			}
			SetInforUtil.setInforIfNullForBN(benhNhan);
			if (benhNhan.getDmgtMaso() != null){
				if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
					gioi = "r1";  //1 : Nam
				}else{
					gioi = "r2";
				}					
			}else{
				gioi = null;
			}
									
			hsbaBHYT.setHsbabhytGiatri0(tiepdon.getTiepdonGiatri1());
			hsbaBHYT.setHsbabhytGiatri1(tiepdon.getTiepdonGiatri2());
			hsbaBHYT.setHsbabhytGiatri2(tiepdon.getTiepdonGiatri3());
			hsbaBHYT.setHsbabhytGiatri3(tiepdon.getTiepdonGiatri4());
			hsbaBHYT.setHsbabhytMoc1(tiepdon.getTiepdonMoc1());
			hsbaBHYT.setHsbabhytMoc2(tiepdon.getTiepdonMoc2());
			hsbaBHYT.setHsbabhytMoc3(tiepdon.getTiepdonMoc3());
			setOtherInfor();
		}
		FacesMessages.instance().clear();
		showListBn = false;
	}	
	public String getMsgFail() {
		return msgFail;
	}

	public void setMsgFail(String msgFail) {
		this.msgFail = msgFail;
	}

	public String getMsgSuccess() {
		return msgSuccess;
	}

	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}

	public void nhaplai() throws Exception {
		log.info("nhaplai()");
		resetValue();
	}	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

//	public String getTuoi() {
//		return tuoi;
//	}
//
//	public void setTuoi(String tuoi) {
//		this.tuoi = tuoi;
//	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}

	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}

	public String getGioVaoVien() {
		return gioVaoVien;
	}

	public void setGioVaoVien(String gioVaoVien) {
		this.gioVaoVien = gioVaoVien;
	}

	public String getNgayVaoVien() {
		return ngayVaoVien;
	}

	public void setNgayVaoVien(String ngayVaoVien) {
		this.ngayVaoVien = ngayVaoVien;
	}

	public String getGioRaVien() {
		return gioRaVien;
	}

	public void setGioRaVien(String gioRaVien) {
		this.gioRaVien = gioRaVien;
	}

	public String getNgayRaVien() {
		return ngayRaVien;
	}

	public void setNgayRaVien(String ngayRaVien) {
		this.ngayRaVien = ngayRaVien;
	}

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}

	public static String getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	public static void setFORMAT_DATE(String format_date) {
		FORMAT_DATE = format_date;
	}

	public static String getFORMAT_DATE_TIME() {
		return FORMAT_DATE_TIME;
	}

	public static void setFORMAT_DATE_TIME(String format_date_time) {
		FORMAT_DATE_TIME = format_date_time;
	}

	public String getResultHidden() {
		return resultHidden;
	}

	public void setResultHidden(String resultHidden) {
		this.resultHidden = resultHidden;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNgayhientai() {
		return ngayhientai;
	}

	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		CapNhatTTNhapVien.log = log;
	}

	public String getGiatri1() {
		return giatri1;
	}

	public void setGiatri1(String giatri1) {
		this.giatri1 = giatri1;
	}

	public String getGiatri2() {
		return giatri2;
	}

	public void setGiatri2(String giatri2) {
		this.giatri2 = giatri2;
	}

	public String getGiatri3() {
		return giatri3;
	}

	public void setGiatri3(String giatri3) {
		this.giatri3 = giatri3;
	}

	public String getGiatri4() {
		return giatri4;
	}

	public void setGiatri4(String giatri4) {
		this.giatri4 = giatri4;
	}

	public String getMoc1() {
		return moc1;
	}

	public void setMoc1(String moc1) {
		this.moc1 = moc1;
	}

	public String getMoc2() {
		return moc2;
	}

	public void setMoc2(String moc2) {
		this.moc2 = moc2;
	}

	public String getMoc3() {
		return moc3;
	}

	public void setMoc3(String moc3) {
		this.moc3 = moc3;
	}

	public String getLockChuyenKhoa() {
		return lockChuyenKhoa;
	}

	public void setLockChuyenKhoa(String lockChuyenKhoa) {
		this.lockChuyenKhoa = lockChuyenKhoa;
	}

	public boolean isDisabledGhinhan() {
		return disabledGhinhan;
	}

	public void setDisabledGhinhan(boolean disabledGhinhan) {
		this.disabledGhinhan = disabledGhinhan;
	}

	public List<BenhNhanTrungTheBhytDTO> getListBN() {
		return listBN;
	}

	public void setListBN(List<BenhNhanTrungTheBhytDTO> listBN) {
		this.listBN = listBN;
	}

	public boolean isShowListBn() {
		return showListBn;
	}

	public void setShowListBn(boolean showListBn) {
		this.showListBn = showListBn;
	}

	public String getShowMenu() {
		return showMenu;
	}

	public void setShowMenu(String showMenu) {
		this.showMenu = showMenu;
	}

	public String getListMaTinhBhyt() {
		return listMaTinhBhyt;
	}

	public void setListMaTinhBhyt(String listMaTinhBhyt) {
		this.listMaTinhBhyt = listMaTinhBhyt;
	}
	
	public List<SelectItem> getListDmKhoaNTs() {
		return listDmKhoaNTs;
	}

	public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs) {
		this.listDmKhoaNTs = listDmKhoaNTs;
	}
	
	public DmTang getTangChuyenDen() {
		return tangChuyenDen;
	}
	public void setTangChuyenDen(DmTang tangChuyenDen) {		
		this.tangChuyenDen = tangChuyenDen;
	}
	
	public List<SelectItem> getListDmTangs() {
		return listDmTangs;
	}

	public void setListDmTangs(List<SelectItem> listDmTangs) {
		this.listDmTangs = listDmTangs;
	}
	
	public DmKhoa getKhoaDangDt() {
		return khoaDangDt;
	}
	public void setKhoaDangDt(DmKhoa khoaDangDt) {		
		this.khoaDangDt = khoaDangDt;
	}


	public boolean isLockDoituong() {
		return lockDoituong;
	}


	public void setLockDoituong(boolean lockDoituong) {
		this.lockDoituong = lockDoituong;
	}


	public boolean isDaCheckTrungBN() {
		return daCheckTrungBN;
	}


	public void setDaCheckTrungBN(boolean daCheckTrungBN) {
		this.daCheckTrungBN = daCheckTrungBN;
	}


	public boolean isTrungBN() {
		return trungBN;
	}


	public void setTrungBN(boolean trungBN) {
		this.trungBN = trungBN;
	}


	public String getStrMsgTrungBN() {
		return strMsgTrungBN;
	}


	public void setStrMsgTrungBN(String strMsgTrungBN) {
		this.strMsgTrungBN = strMsgTrungBN;
	}
	
}


