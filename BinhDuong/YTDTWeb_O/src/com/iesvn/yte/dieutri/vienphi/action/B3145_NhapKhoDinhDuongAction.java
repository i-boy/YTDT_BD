package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

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

import com.iesvn.yte.dieutri.delegate.DtDmLoaiAn2Delegate;
import com.iesvn.yte.dieutri.delegate.NhapKhoDinhDuongDelegate;
import com.iesvn.yte.dieutri.delegate.TonKhoDinhDuongDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
import com.iesvn.yte.dieutri.entity.NhapNuoc;
import com.iesvn.yte.dieutri.entity.TonKhoDinhDuong;
import com.iesvn.yte.util.IConstantsRes;

@Scope(SESSION)
@Name("B3145_NhapKhoDinhDuong")
public class B3145_NhapKhoDinhDuongAction implements Serializable
{

	private static final long serialVersionUID = 10L;					
	
	private String ngaynhap;
	
	private String soluong;	
	private NhapKhoDinhDuong nhapKDD;
	private String ngayhientai;
	private boolean lockedSaveButton;
	private List<NhapKhoDinhDuong> listNhapKho;
	private int selectedIndex;
//	private int loaiAn;
//	private DtDmLoaiAn2 loaiSanPham;
//	private List<DtDmLoaiAn2> lstSanPham;
//	private List<SelectItem> lstItemSanPham ;
	NhapKhoDinhDuongDelegate dinhDuongDelegate  = NhapKhoDinhDuongDelegate.getInstance();
	TonKhoDinhDuongDelegate tonKhoDinhDuongDelegate = TonKhoDinhDuongDelegate.getInstance();

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@In(scope=ScopeType.PAGE,required=false)
	@Out(scope=ScopeType.PAGE,required=false)
	private String strNhapKDD;
	@Logger
	private Log log;
	
	@Factory("strNhapKDD")
	public void init() {
		reset();
	}
	
	public void reset() {
		listNhapKho = new ArrayList<NhapKhoDinhDuong>();
		strNhapKDD = "";
		emptyNhapKho();
		FacesMessages.instance().clear();
	}
	public void emptyNhapKho() {
		ngaynhap = sdf.format(new Date());
		nhapKDD = new NhapKhoDinhDuong();
		nhapKDD.setDtdmlaMaso(new DtDmLoaiAn());
		nhapKDD.setDtdmla2Maso(new DtDmLoaiAn2());
		nhapKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
		nhapKDD.getDtdmlaMaso().setDtdmlaMaso(new Integer(2)); // Set default loai nhap la San pham dinh duong
		soluong = "";
		ngayhientai = sdf.format(new Date());
		lockedSaveButton = false;
		selectedIndex = -1;
//		FacesMessages.instance().clear();
	}
	public void saveNhapKDD() {
		log.info("begin save, loai nhap ma so = " + nhapKDD.getDtdmlaMaso().getDtdmlaMaso());
		try {
			NhapKhoDinhDuong nhapKDD_Tmp = new NhapKhoDinhDuong();
			if (nhapKDD.getDtdmlaMaso().getDtdmlaMaso() == 3) { // Sua duong nhi
				nhapKDD.setDtdmnsxMaso(null);
			}
			float soluong_tmp = nhapKDD.getNkddSoluong()== null ? 0 : nhapKDD.getNkddSoluong();
			nhapKDD.setNkddNgaynhap(sdf.parse(ngaynhap));
			nhapKDD.setNkddSoluong(new Float(soluong));
			// Cap nhat ton kho
			TonKhoDinhDuong tonKDDTmp = new TonKhoDinhDuong();
			tonKDDTmp = tonKhoDinhDuongDelegate.findLastTKByNhaSX(nhapKDD.getDtdmlaMaso().getDtdmlaMaso(), 
					                                                            nhapKDD.getDtdmla2Maso().getDtdmla2Maso(), 
					                                                           (nhapKDD.getDtdmnsxMaso() != null ? nhapKDD.getDtdmnsxMaso().getDtdmnsxMaso(): null));
			
			TonKhoDinhDuong tonKDD = new TonKhoDinhDuong();
			
			tonKDD.setDtdmlaMaso(nhapKDD.getDtdmlaMaso());
			tonKDD.setDtdmla2Maso(nhapKDD.getDtdmla2Maso());
			tonKDD.setTkddNgayton(nhapKDD.getNkddNgaynhap());
			tonKDD.setDtdmnsxMaso(nhapKDD.getDtdmnsxMaso());
//			tonKDD.setTkddSoluong(nhapKDD.getNkddSoluong());
//			tonKDD.setTkddLoai(new Boolean(false));		// Loai nhap kho
			
			if (tonKDDTmp != null) {
				tonKDD.setTkddSoluong(nhapKDD.getNkddSoluong() + tonKDDTmp.getTkddSoluong());
			} else {
				tonKDD.setTkddSoluong(nhapKDD.getNkddSoluong());
			}
		
			// Luu thong tin nhap kho
			nhapKDD_Tmp = dinhDuongDelegate.myCreate(nhapKDD);
			
			if(selectedIndex < 0) {
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
			} else {
				if ((tonKDD.getTkddSoluong() - soluong_tmp) > 0) {
					tonKDD.setTkddSoluong(tonKDD.getTkddSoluong() - soluong_tmp);
					FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
				}else{
					FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
				}
			}
			// Luu thong tin ton kho
			TonKhoDinhDuongDelegate.getInstance().create(tonKDD);
			
			seachNhapkho();
			emptyNhapKho();
			
//			lockedSaveButton = true;
		} catch (Exception e) {
			log.info("ERROR : " + e.toString());
			e.printStackTrace();
			FacesMessages.instance().add(IConstantsRes.INSERT_FAIL);
			lockedSaveButton = false;
		}
		log.info("end save");
	}
	
	public void edit(int index) {
		log.info("*****Begin edit(),index = " + index);
		
		NhapKhoDinhDuong nhapKDD_tmp = listNhapKho.get(index);
		
		try {
			nhapKDD = (NhapKhoDinhDuong) BeanUtils.cloneBean(nhapKDD_tmp);
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
		
		soluong = nhapKDD_tmp.getNkddSoluong().toString();	
		ngaynhap = sdf.format(nhapKDD_tmp.getNkddNgaynhap());
		
		selectedIndex = index;
		log.info("*****End edit()" );
		
	}
	
	// Ham delete chi tiet
	public void delete(int index) throws Exception
	{
		log.info("*****Begin delete() *****, index = " + index + ", nhapKDD = " + listNhapKho.get(index));
		NhapKhoDinhDuong khoDinhDuong =(NhapKhoDinhDuong) BeanUtils.cloneBean(listNhapKho.get(index));
		// Xoa du lieu trong DB
		dinhDuongDelegate.remove(khoDinhDuong);
		
		TonKhoDinhDuong tonKhoDinhDuong = tonKhoDinhDuongDelegate.findLastTKByNhaSX(khoDinhDuong.getDtdmlaMaso().getDtdmlaMaso(), khoDinhDuong.getDtdmla2Maso().getDtdmla2Maso(), khoDinhDuong.getDtdmnsxMaso().getDtdmnsxMaso());
		
		if (tonKhoDinhDuong!= null){
			TonKhoDinhDuong tonKDD = new TonKhoDinhDuong();
			
			tonKDD.setDtdmlaMaso(tonKhoDinhDuong.getDtdmlaMaso());
			tonKDD.setDtdmla2Maso(tonKhoDinhDuong.getDtdmla2Maso());
			tonKDD.setDtdmnsxMaso(tonKhoDinhDuong.getDtdmnsxMaso());
			float soluongTon = tonKhoDinhDuong.getTkddSoluong() - khoDinhDuong.getNkddSoluong();
			tonKDD.setTkddSoluong(soluongTon);
			tonKDD.setTkddNgayton(new Date());
			tonKhoDinhDuongDelegate.create(tonKDD);
		}
		
		// Xoa item trong list
		listNhapKho.remove(index); 
		
		nhapKDD = new NhapKhoDinhDuong();
		
		log.info("*****End delete() *****, listNhapKho.size = " + listNhapKho.size());
	}
	
	public void seachNhapkho(){
		try {
			listNhapKho  = dinhDuongDelegate.findByDate(sdf.parse(ngaynhap));
			if (listNhapKho != null) {								
				FacesMessages.instance().clear();
			} else {
				
				listNhapKho = new ArrayList<NhapKhoDinhDuong>();				
				// Hien thi thong bao khong tim thay
				FacesMessages.instance().add(IConstantsRes.no_found);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedIndex = -1;
		log.info("--exit seachNhapkho()---"+ listNhapKho.size());
	}
	
	public void setNullForNhapKho(NhapKhoDinhDuong dinhDuong){
		if(dinhDuong.getDtdmla2Maso()== null){
			dinhDuong.setDtdmla2Maso(new DtDmLoaiAn2());
		}
		if(dinhDuong.getDtdmlaMaso()== null){
			dinhDuong.setDtdmlaMaso(new DtDmLoaiAn());
		}
		if(dinhDuong.getDtdmnsxMaso()== null){
			dinhDuong.setDtdmnsxMaso(new DtDmNhaSxSpdd());
		}
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
	public NhapKhoDinhDuong getNhapKDD() {
		return nhapKDD;
	}
	public void setNhapKDD(NhapKhoDinhDuong nhapKDD) {
		this.nhapKDD = nhapKDD;
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
	public List<NhapKhoDinhDuong> getListNhapKho() {
		return listNhapKho;
	}
	public void setListNhapKho(List<NhapKhoDinhDuong> listNhapKho) {
		this.listNhapKho = listNhapKho;
	}
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
}