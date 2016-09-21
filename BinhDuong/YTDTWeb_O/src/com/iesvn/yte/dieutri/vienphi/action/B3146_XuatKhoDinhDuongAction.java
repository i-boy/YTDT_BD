package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.NhapKhoDinhDuongDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDinhDuongDelegate;
import com.iesvn.yte.dieutri.delegate.XuatKhoDinhDuongDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
import com.iesvn.yte.dieutri.entity.TonKhoDinhDuong;
import com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3146_XuatKhoDinhDuong")
public class B3146_XuatKhoDinhDuongAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String ngayxuat;
	
	private String soluong;	
	private XuatKhoDinhDuong xuatKDD;
	private String ngayhientai;
	private boolean lockedSaveButton;
	private List<XuatKhoDinhDuong> listXuatKho;
	private int selectedIndex;
	
	private XuatKhoDinhDuongDelegate xuatKhoDinhDuongDelegate = XuatKhoDinhDuongDelegate.getInstance();
	private TonKhoDinhDuongDelegate tonKhoDinhDuongDelegate = TonKhoDinhDuongDelegate.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strXuatKDD;
	@Logger
	private Log log;
	
	@Factory("strXuatKDD")
	public void init() {
		
		reset();
		strXuatKDD = "";
	}
	public void emptyData() {
		xuatKDD = new XuatKhoDinhDuong();
		xuatKDD.setDtdmlaMaso(new DtDmLoaiAn());
		xuatKDD.setDtdmla2Maso(new DtDmLoaiAn2());
		xuatKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
		xuatKDD.setDmkhoaMaso(new DmKhoa());
		xuatKDD.getDtdmlaMaso().setDtdmlaMaso(new Integer(2)); // Set default loai xuat la San pham dinh duong
		soluong = "";
		lockedSaveButton = false;
		selectedIndex = -1;
	}

	
	public void reset() {
		listXuatKho = new ArrayList<XuatKhoDinhDuong>();
		ngayxuat = sdf.format(new Date());
		ngayhientai = sdf.format(new Date());
		emptyData();
		FacesMessages.instance().clear();
	}
	
	public void seachXuatkho(){
		try {
			xuatKDD = new XuatKhoDinhDuong();
			listXuatKho  = xuatKhoDinhDuongDelegate.findByDate(sdf.parse(ngayxuat));
			if (listXuatKho != null) {								
				FacesMessages.instance().clear();
			} else {
				
				listXuatKho = new ArrayList<XuatKhoDinhDuong>();				
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.no_found);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedIndex = -1;
	}
	
	public void edit(int index) {
		log.info("*****Begin edit(),index = " + index);
		
		XuatKhoDinhDuong xuatKDD_tmp = listXuatKho.get(index);
		try {
			xuatKDD = (XuatKhoDinhDuong) BeanUtils.cloneBean(xuatKDD_tmp);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		soluong = xuatKDD_tmp.getXkddSoluong().toString();	
		ngayxuat = sdf.format(xuatKDD_tmp.getXkddNgayxuat());
		
		selectedIndex = index;
		log.info("*****End edit()" );
		
	}
	
	// Ham delete chi tiet
	public void delete(int index) throws Exception
	{
		log.info("*****Begin delete() *****, index = " + index + ", xuatKDD = " + listXuatKho.get(index));
		XuatKhoDinhDuong khoDinhDuong = (XuatKhoDinhDuong) BeanUtils.cloneBean(listXuatKho.get(index));
		// Xoa du lieu trong DB
		xuatKhoDinhDuongDelegate.remove(khoDinhDuong);
		
		TonKhoDinhDuong tonKhoDinhDuong = tonKhoDinhDuongDelegate.findLastTKByNhaSX(khoDinhDuong.getDtdmlaMaso().getDtdmlaMaso(), khoDinhDuong.getDtdmla2Maso().getDtdmla2Maso(), khoDinhDuong.getDtdmnsxMaso().getDtdmnsxMaso());
		
		if (tonKhoDinhDuong!= null){
			TonKhoDinhDuong tonKDD = new TonKhoDinhDuong();
			
			tonKDD.setDtdmlaMaso(tonKhoDinhDuong.getDtdmlaMaso());
			tonKDD.setDtdmla2Maso(tonKhoDinhDuong.getDtdmla2Maso());
			tonKDD.setDtdmnsxMaso(tonKhoDinhDuong.getDtdmnsxMaso());
			float soluongTon = tonKhoDinhDuong.getTkddSoluong() + khoDinhDuong.getXkddSoluong();
			tonKDD.setTkddSoluong(soluongTon);
			tonKDD.setTkddNgayton(new Date());
			tonKhoDinhDuongDelegate.create(tonKDD);
		}
		
		// Xoa item trong list
		listXuatKho.remove(index); 
		xuatKDD = new XuatKhoDinhDuong();		
		reset();	
		log.info("*****End delete() *****, listXuatKho.size = " + listXuatKho.size());
	}
	
	
	public void saveXuatKDD() {
		log.info("begin save, loai xuat ma so = " + xuatKDD.getDtdmlaMaso().getDtdmlaMaso());
		
		try {

			int soluong_tmp = xuatKDD.getXkddSoluong()== null ? 0 : xuatKDD.getXkddSoluong().intValue();
			xuatKDD.setXkddNgayxuat(sdf.parse(ngayxuat));
			xuatKDD.setXkddSoluong(new Float(soluong));
			
			log.info("Ma so loai an = " + xuatKDD.getDtdmlaMaso().getDtdmlaMaso());

			TonKhoDinhDuong tonKDDTmp = new TonKhoDinhDuong();
			tonKDDTmp = tonKhoDinhDuongDelegate.findLastTKByNhaSX(xuatKDD.getDtdmlaMaso().getDtdmlaMaso(), 
					                                                            xuatKDD.getDtdmla2Maso().getDtdmla2Maso(), 
					                                                           (xuatKDD.getDtdmnsxMaso() != null ? xuatKDD.getDtdmnsxMaso().getDtdmnsxMaso(): null));
			
			TonKhoDinhDuong tonKDD = new TonKhoDinhDuong();
			
			tonKDD.setDtdmlaMaso(xuatKDD.getDtdmlaMaso());
			tonKDD.setDtdmla2Maso(xuatKDD.getDtdmla2Maso());
			tonKDD.setTkddNgayton(xuatKDD.getXkddNgayxuat());
			tonKDD.setDtdmnsxMaso(xuatKDD.getDtdmnsxMaso());
			
			if (tonKDDTmp != null) {
				soluong_tmp = new Integer(soluong).intValue() - soluong_tmp;
				log.info("soluong_tmp "+ soluong_tmp);
				if (tonKDDTmp.getTkddSoluong() < soluong_tmp) {
					// So luong xuat lon hon so luong ton kho
					log.info("So luong xuat lon hon so luong ton kho");
					FacesMessages.instance().add(IConstantsRes.SL_XUAT_KHONG_HOP_LE, soluong_tmp,tonKDDTmp.getTkddSoluong());
					return;
				}
				tonKDD.setTkddSoluong(tonKDDTmp.getTkddSoluong() - soluong_tmp);
			} else {
				FacesMessages.instance().add(IConstantsRes.SL_XUAT_KHONG_HOP_LE, soluong, 0);
				return;
			}
		
			// Thiet lap thong tin xuat kho
			if (xuatKDD.getDtdmlaMaso().getDtdmlaMaso() == 3) { // Sua duong nhi
				xuatKDD.setDtdmnsxMaso(null);
			}
			
		
			xuatKhoDinhDuongDelegate.myCreate(xuatKDD);
			
			
			if(selectedIndex < 0) {
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
			} else {
				FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
			}
			
			seachXuatkho();
			emptyData();
		
			// Luu thong tin ton kho
			TonKhoDinhDuongDelegate.getInstance().create(tonKDD);
			
//			lockedSaveButton = true;
		} catch (Exception e) {
			log.info("ERROR : " + e.toString());
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.INSERT_FAIL);
			lockedSaveButton = false;
		}
		log.info("end save");
	}
	public String getNgayxuat() {
		return ngayxuat;
	}
	public void setNgayxuat(String ngayxuat) {
		this.ngayxuat = ngayxuat;
	}
	public String getSoluong() {
		return soluong;
	}
	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}
	public XuatKhoDinhDuong getXuatKDD() {
		return xuatKDD;
	}
	public void setXuatKDD(XuatKhoDinhDuong xuatKDD) {
		this.xuatKDD = xuatKDD;
	}
	public boolean isLockedSaveButton() {
		return lockedSaveButton;
	}
	public void setLockedSaveButton(boolean lockedSaveButton) {
		this.lockedSaveButton = lockedSaveButton;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public List<XuatKhoDinhDuong> getListXuatKho() {
		return listXuatKho;
	}
	public void setListXuatKho(List<XuatKhoDinhDuong> listXuatKho) {
		this.listXuatKho = listXuatKho;
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
}