/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.PAGE;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.remove.RemoveUtil;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.IConstantsRes;

@Scope(PAGE)
@Name("B3217_CapnhatsotheBHYT")
@Synchronized(timeout = 6000000)
public class CapnhatsotheBHYT  implements Serializable {
	private static String FORMAT_DATE = "dd/MM/yyyy";
	private String msgFail = "";
	private String msgSuccess = "";
	private static Logger log = Logger.getLogger(CapnhatsotheBHYT.class);
	private static final long serialVersionUID = 10L;
		
	
	private String ngayhientai = "";
	
	private BenhNhan benhNhan;
	private Hsba hoSoBenhAn;
	private HsbaBhyt hsbaBHYT;
	private String ngaySinh=null;
	private String gioi="";
	private String ngayvaovien=null;
	private String giatri0=null;
	private String giatri1=null;
	private String giatri2=null;
	private String giatri3=null;
	private String moc1=null;
	private String moc2=null;
	private String moc3=null;
	private String listMaTinhBhyt;
	private Boolean coGiayChuyenVien;
	private boolean disabledGhinhan = false;
	@Create
	public void init() {	
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(FORMAT_DATE);
		ngayhientai=formatter.format(new Date());
		resetValue();	
		
	}
	
	
	public void resetValue(){
		benhNhan = new BenhNhan();
		hoSoBenhAn = new Hsba();
		hsbaBHYT = new HsbaBhyt();
		hoSoBenhAn.setBenhnhanMa(benhNhan);
		setGiatri0("");
		setGiatri1("");
		setGiatri2("");
		setGiatri3("");
		setMoc1("");
		setMoc2("");
		setMoc3("");
		setNgayvaovien("");
		setGioi(null);
		setNgaySinh("");
		disabledGhinhan = false;
		SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
		// Lay danh muc tinh de tao listMaTinhBhyt
		List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");		
		listMaTinhBhyt = "";
		for(DmTinh each : listDmTinh) {
			listMaTinhBhyt += each.getDmtinhBHYT() + ",";
		}
	}	

	public void displayInfor() throws Exception {
		log.info("begin displayInfo=======");
		
		hsbaBHYT=new HsbaBhyt();
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE); 
		try {
			disabledGhinhan = false;
			HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
			String sba = hoSoBenhAn.getHsbaSovaovien();
			if (sba != null && !sba.trim().equals("")) {
				Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
				if (hoSoBenhAn_temp != null) {
					setHoSoBenhAn(hoSoBenhAn_temp);
					SetInforUtil.setInforIfNullForHSBA(hoSoBenhAn);
					
					String doiTuong = hoSoBenhAn.getDoituongMa().getDmdoituongMa();
					if (doiTuong.equals("TP")) {
						FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI_CNBHYT);
						disabledGhinhan = true;
						return;
					}
					
					// 20101209 bao.ttc: kiem tra neu da thanh toan thi khong cho chinh sua
					HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();
					HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(hoSoBenhAn.getHsbaSovaovien());
					if (hsbaHsThtoan_temp != null) {
						if (hsbaHsThtoan_temp.getHsthtoanDatt() != null && hsbaHsThtoan_temp.getHsthtoanDatt()) {
							FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
							disabledGhinhan = true;
							return;
						}
					}
					
					
					benhNhan = hoSoBenhAn.getBenhnhanMa();
					formatter = new SimpleDateFormat(FORMAT_DATE);
					if (benhNhan.getBenhnhanNgaysinh() != null
							&& !benhNhan.getBenhnhanNgaysinh().equals("")) {
						ngaySinh=formatter.format(benhNhan
								.getBenhnhanNgaysinh().getTime());
					}
					SetInforUtil.setInforIfNullForBN(benhNhan);
					
					log.info("-------Set ngay vao vien--------");
					if (hoSoBenhAn.getHsbaNgaygiovaov() != null
							&& !hoSoBenhAn.getHsbaNgaygiovaov().equals("")) {
						ngayvaovien=formatter.format(hoSoBenhAn.getHsbaNgaygiovaov().getTime());
					}else{
			        	log.info("ngay vao vien null" );
			        }
			        
			        log.info("-------Set gioi tinh--------");
					if (benhNhan.getDmgtMaso() != null){
						log.info("bat dau vao ham set gioi tinh");	
						 if ("1".equals(benhNhan.getDmgtMaso().getDmgtMa())){
							setGioi("r1");  //1 : Name
						}else{
							setGioi("r2");
						}					
					}else{
						setGioi(null);
					}
					log.info("gioi tinh :" + gioi);
					
					log.info("-------Tim ho so bao hiem y te--------");
					HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
					HsbaBhyt hsbaBhytLast = hsbaBhytDelegate
								.findBySoVaoVienLastHsbaBhyt(hoSoBenhAn.getHsbaSovaovien());
					if (hsbaBhytLast != null) {
						hsbaBHYT = hsbaBhytLast;
						log.info("hs benh nhan BHYT "+hsbaBHYT);
						
						log.info("-------Set gia tri 0--------");
						if (hsbaBHYT.getHsbabhytGiatri0() != null
								&& !hsbaBHYT.getHsbabhytGiatri0().equals("")) {
							giatri0=formatter.format(hsbaBHYT.getHsbabhytGiatri0().getTime());
						}else{
				        	log.info("gia tri 0 null" );
				        }
						
						log.info("-------Set gia tri 1--------");
						if (hsbaBHYT.getHsbabhytGiatri1() != null
								&& !hsbaBHYT.getHsbabhytGiatri1().equals("")) {
							giatri1=formatter.format(hsbaBHYT.getHsbabhytGiatri1().getTime());
						}else{
				        	log.info("gia tri 1 null" );
				        }
						
						log.info("-------Set gia tri 2--------");
						if (hsbaBHYT.getHsbabhytGiatri2() != null
								&& !hsbaBHYT.getHsbabhytGiatri2().equals("")) {
							giatri2=formatter.format(hsbaBHYT.getHsbabhytGiatri2().getTime());
						}else{
				        	log.info("gia tri 2 null" );
				        }
						
						log.info("-------Set gia tri 3--------");
						if (hsbaBHYT.getHsbabhytGiatri3() != null
								&& !hsbaBHYT.getHsbabhytGiatri3().equals("")) {
							giatri3=formatter.format(hsbaBHYT.getHsbabhytGiatri3().getTime());
						}else{
				        	log.info("gia tri 3 null" );
				        }
						
						log.info("-------Set moc 1--------");
						if (hsbaBHYT.getHsbabhytMoc1() != null
								&& !hsbaBHYT.getHsbabhytMoc1().equals("")) {
							moc1=formatter.format(hsbaBHYT.getHsbabhytMoc1().getTime());
						}else{
				        	log.info("moc 1 null" );
				        }
						
						log.info("-------Set moc 2--------");
						if (hsbaBHYT.getHsbabhytMoc2() != null
								&& !hsbaBHYT.getHsbabhytMoc2().equals("")) {
							moc2=formatter.format(hsbaBHYT.getHsbabhytMoc2().getTime());
						}else{
				        	log.info("moc 2 null" );
				        }
						log.info("-------Set moc 3--------");
						if (hsbaBHYT.getHsbabhytMoc3() != null
								&& !hsbaBHYT.getHsbabhytMoc3().equals("")) {
							moc3=formatter.format(hsbaBHYT.getHsbabhytMoc3().getTime());
						}else{
				        	log.info("moc 2 null" );
				        }
					}
					
				}
				else {
					hoSoBenhAn.setHsbaSovaovien("");
					FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND);
					log.info("khong tim thay sobenhan");
				}
				log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
			}

		} catch (Exception e) {
			log.info("ERROR displayInfo=======" + e);
			e.printStackTrace();
		}
		log.info("End displayInfo=======");
	}

	
	/**
	 * 
	 * @throws Exception
	 */
	public void ghiNhan() throws Exception{
		log.info("--------------Bat dau ghi nhan---------------");
		try{			
			log.info("lay gia tri 0" + giatri0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(!giatri0.trim().equals("")){
				hsbaBHYT.setHsbabhytGiatri0(sdf.parse(giatri0));
			}
			log.info("lay gia tri 1" + giatri1);
			if(!giatri1.trim().equals("")){
				hsbaBHYT.setHsbabhytGiatri1(sdf.parse(giatri1));
			}
			log.info("lay gia tri 2" + giatri2);
			if(!giatri2.trim().equals("")){
				hsbaBHYT.setHsbabhytGiatri2(sdf.parse(giatri2));
			}
			log.info("lay gia tri 3" + giatri3);
			if(!giatri3.trim().equals("")){
				hsbaBHYT.setHsbabhytGiatri3(sdf.parse(giatri3));
			}
			log.info("Moc 1" + moc1);
			if(!moc1.trim().equals("")){
				hsbaBHYT.setHsbabhytMoc1(sdf.parse(moc1));
			}
			log.info("Moc 2" + moc2);
			if(!moc2.trim().equals("")){
				hsbaBHYT.setHsbabhytMoc2(sdf.parse(moc2));
			}
			log.info("Moc 3" + moc3);
			if(!moc3.trim().equals("")){
				hsbaBHYT.setHsbabhytMoc3(sdf.parse(moc3));
			}
			
			//hsbaBHYT.setHsbabhytCoGiayChuyenVien(tiepdonCoGiayChuyenVien)
			
			
			log.info("nam tham gia" + hsbaBHYT.getHsbabhytNamtg());
			log.info("thanh toan " + hoSoBenhAn.getHsbaThanhtoantn());
			log.info("ma so HSBA " + hsbaBHYT.getHsbabhytMa());
			
			HsbaBhytDelegate hsbcDel=HsbaBhytDelegate.getInstance();
			HsbaDelegate hsbaDelegate=HsbaDelegate.getInstance();
			// phuc.lc 26-01-2011 : Fix bug 186
			TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
			TiepDon td = tdDelegate.find(hsbaBHYT.getHsbaSovaovien().getTiepdonMa());
			//
			DieuTriUtilDelegate dieutriUtil = DieuTriUtilDelegate.getInstance();
			Object obj = dieutriUtil.findByMa("BH", "DmDoiTuong", "dmdoituongMa");
			if (obj != null ){
				DmDoiTuong doiTuong = (DmDoiTuong)obj;
				hoSoBenhAn.setDoituongMa(doiTuong);
				td.setDoituongMa(doiTuong);
			}
			
			if (!("".equals(hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT()))){
				DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
				DmTinh tinhbh = (DmTinh) ws.findByMa(
						hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT(), "DmTinh", "dmtinhBHYT");
			
//				if (tinhbh == null)
//					return null;
				hsbaBHYT.setHsbabhytTinhbh(tinhbh);
				td.setTinhbhytMa(tinhbh);
			}
			// phuc.lc 26-01-2011 : Fix bug 186 Cap nhat thong tin the bao hiem vao bang tiep don
			td.setTiepdonSothebh(hsbaBHYT.getHsbabhytSothebh());
			td.setKhoibhytMa(hsbaBHYT.getHsbabhytKhoibh());
			td.setTinhbhytMa(hsbaBHYT.getHsbabhytTinhbh());
			td.setKcbbhytMa(hsbaBHYT.getHsbabhytMakcb());
			td.setTiepdonGiatri1(sdf.parse(giatri0));
			td.setTiepdonGiatri2(sdf.parse(giatri1));
			td.setTiepdonGiatri3(sdf.parse(giatri2));
			td.setTiepdonGiatri4(sdf.parse(giatri3));
			td.setTiepdonMoc1(sdf.parse(moc1));
			td.setTiepdonMoc2(sdf.parse(moc2));
			td.setTiepdonMoc3(sdf.parse(moc3));
			td.setTiepdonMacoquan(hsbaBHYT.getHsbabhytCoquanbh());
			td.setTiepdonCoGiayGioiThieu(hsbaBHYT.getHsbabhytCoGiayChuyenVien());
			Short tuyenBH;
			
			DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
			if (hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso() != null){				
				DmTinh tinh =  (DmTinh) dele.findByMaSo(hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso(),"DmTinh","dmtinhMaso");
				if (tinh != null){
					hsbaBHYT.setHsbabhytTinhbh(tinh);
				}else{
					//hsbaBHYT.setHsbabhytTinhbh(null);
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.TINH_CAP_BHYT, hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT());					
					return;
				}
			}else{				
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.TINH_CAP_BHYT);
				return;
			}
			// Kiem tra noi DK Kham chua benh
			if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMaso() != null) {
				DmBenhVien noiKCB = (DmBenhVien) dele.findByMa(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
				if(noiKCB == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND, IConstantsRes.NOI_DK_KCB, hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());					
					return;
				} else {
					hsbaBHYT.setHsbabhytMakcb(noiKCB);
				}
			} else {				
				FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, IConstantsRes.NOI_DK_KCB);
				return;
			}
			// 20110521 bao.ttc: set TUYEN theo CAP_TRIEN_KHAI_PHAN_MEM
			if(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN)) {
				tuyenBH = new Short("1");
			
			} else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH")){

				if(hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().startsWith(IConstantsRes.MA_TINH_BHYT)) {
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
			
			// END -- 20110521 bao.ttc: set TUYEN theo CAP_TRIEN_KHAI_PHAN_MEM
			
			td.setTiepdonTuyen(tuyenBH);
			RemoveUtil.removeAllNullFromHSBA(hoSoBenhAn);
			RemoveUtil.removeAllNullFromHSBABHYT(hsbaBHYT);
			
			
			hsbaDelegate.edit(hoSoBenhAn);
			// phuc.lc 01/11/2011 : Chi cap nhat cac tiep don duoc tao ra tu noi tru (theo hsba, HSBA_TYPE = null)
			// ca tiep don chuyen tu ngoai tru vao noi tru thi khong cap nhat
			if(hoSoBenhAn.getHsbaType() == null) {
				tdDelegate.edit(td);
			}
			log.info("hsba dieu tri" + hoSoBenhAn.getHsbaDieutri());
			hsbaBHYT.setHsbabhytTuyen(tuyenBH);
			if(hsbaBHYT.getHsbabhytMa()==null){
				log.info("=========Truong hop them moi HS BHYT=========");
				hsbaBHYT.setHsbaSovaovien(hoSoBenhAn);
				hsbcDel.create(hsbaBHYT);
				FacesMessages.instance().add("H\u1ed3 s\u01a1 BHYT \u0111\u00e3 l\u01b0u th\u00e0nh c\u00f4ng !");
			}else{
				log.info("=========Truong hop cap nhat HS BHYT=========");
				hsbcDel.edit(hsbaBHYT);
				//hsbaDelegate.edit(hoSoBenhAn);
				FacesMessages.instance().add("H\u1ed3 s\u01a1 BHYT \u0111\u00e3 c\u1eadp nh\u1eadt th\u00e0nh c\u00f4ng !");
			}
			// Cap nhat don gia cho cac CLS (noi tru) da chi dinh truoc khi thay doi thong tin the bao hiem
			hsbcDel.capnhatGiaClsTheoThoiGianBaoHiem(hsbaBHYT);			
			// Cap nhat don gia cho cac CLS (ngoai tru) neu co chuyen so lieu tu ngoai tru vao noi tru
			if(ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(td.getTiepdonMa()) != null) {
				tdDelegate.capnhatGiaClsTheoThoiGianBaoHiem(td);			
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			//log.info("ERROR ghinhan()==========="+ ex);
		}
		log.info("--------------ket thuc ghi nhan---------------");
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
		log.debug("nhaplai()");
		resetValue();
	}	

	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
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
		CapnhatsotheBHYT.log = log;
	}


	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}


	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}


	public void setHsbaBHYT(HsbaBhyt hsbaBHYT) {
		this.hsbaBHYT = hsbaBHYT;
	}


	public HsbaBhyt getHsbaBHYT() {
		return hsbaBHYT;
	}


	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}


	public void setGioi(String gioi) {
		this.gioi = gioi;
	}


	public String getGioi() {
		return gioi;
	}


	public void setNgayvaovien(String ngayvaovien) {
		this.ngayvaovien = ngayvaovien;
	}


	public String getNgayvaovien() {
		return ngayvaovien;
	}


	

	public String getGiatri0() {
		return giatri0;
	}


	public void setGiatri0(String giatri0) {
		this.giatri0 = giatri0;
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


	public void setMoc1(String moc1) {
		this.moc1 = moc1;
	}


	public String getMoc1() {
		return moc1;
	}


	public void setMoc2(String moc2) {
		this.moc2 = moc2;
	}


	public String getMoc2() {
		return moc2;
	}


	public String getMoc3() {
		return moc3;
	}


	public void setMoc3(String moc3) {
		this.moc3 = moc3;
	}


	public Boolean getCoGiayChuyenVien()
	{
		return coGiayChuyenVien;
	}


	public void setCoGiayChuyenVien(Boolean coGiayChuyenVien)
	{
		this.coGiayChuyenVien = coGiayChuyenVien;
	}


	public boolean isDisabledGhinhan() {
		return disabledGhinhan;
	}


	public void setDisabledGhinhan(boolean disabledGhinhan) {
		this.disabledGhinhan = disabledGhinhan;
	}


	public String getListMaTinhBhyt() {
		return listMaTinhBhyt;
	}


	public void setListMaTinhBhyt(String listMaTinhBhyt) {
		this.listMaTinhBhyt = listMaTinhBhyt;
	}	
	
	/*private void setValueCoGiayChuyenVien(Boolean bool)
	{
		i
	}*/
	
	
}


