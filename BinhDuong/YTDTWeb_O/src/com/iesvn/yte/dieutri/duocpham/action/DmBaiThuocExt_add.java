package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.BaithuocThuocDelegate;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
import com.iesvn.yte.dieutri.entity.CtNhapKho;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.util.IConstantsRes;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Name("DmBaiThuoc_add")
@Scope(SESSION)
public class DmBaiThuocExt_add implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@DataModel
	private ArrayList<BaithuocThuoc> listbaithuocThuoc;
	@DataModelSelection
	private BaithuocThuoc selected;
	
	private BaithuocThuoc baithuocThuoc;
	
	@In(required = false)
	@Out(required = false)
	private DmBaiThuoc dmBaiThuoc;
	
	private Boolean isUpdate = false;
	private String soluong;
	
	private DmThuocDelegate dmThuocDelegate;
	private String dmtMa;
	private String dmtTen="";
	HashMap<String, DmThuoc> hmDmThuoc = new HashMap<String, DmThuoc>();
	private List<SelectItem> listDmThuocs = new ArrayList<SelectItem>();
	private ArrayList<BaithuocThuoc> listbaithuocThuocDel;//luu cac vi thuoc trong bai thuoc xoa ra khoi bai thuoc
	
	public String getSoluong() {
		return soluong;
	}

	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public ArrayList<BaithuocThuoc> getListbaithuocThuoc() {
		return listbaithuocThuoc;
	}

	public void setListbaithuocThuoc(ArrayList<BaithuocThuoc> listbaithuocThuoc) {
		this.listbaithuocThuoc = listbaithuocThuoc;
	}

	public BaithuocThuoc getBaithuocThuoc() {
		return baithuocThuoc;
	}

	public void setBaithuocThuoc(BaithuocThuoc baithuocThuoc) {
		this.baithuocThuoc = baithuocThuoc;
	}

	public BaithuocThuoc getSelected() {
		return selected;
	}

	public void setSelected(BaithuocThuoc selected) {
		this.selected = selected;
	}

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBaiThuocExt_add....");
		
		resetValue();
	
		return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy_add";
	}
	
	
	public String update() {
		log.info("Init DmBaiThuocExt_update....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmBaiThuoc");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.dmBaiThuoc = ((List<DmBaiThuoc>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}
		
		if( this.dmBaiThuoc != null)	
			listbaithuocThuoc = (ArrayList<BaithuocThuoc>)DieuTriUtilDelegate.getInstance().findByAllConditions("BaithuocThuoc", "dmbaithuocMaso", "dmthuocMaso", this.dmBaiThuoc.getDmbaithuocMaso()+"","");
	
		return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy_add";
	}

	public void save() {
		log.info("Save DmBaiThuocExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.dmBaiThuoc.getDmbaithuocMa();
		this.dmBaiThuoc.setDmbaithuocNgaygiocn((double)date.getTime());
		this.dmBaiThuoc.setDmbaithuocDt(true);
		setMaFromTen();
		
		try {
			DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
			BaithuocThuocDelegate baithuocThuocDel = BaithuocThuocDelegate.getInstance();
			System.out.println("listbaithuocThuocDel.size(): "+ listbaithuocThuocDel.size());
			if(listbaithuocThuocDel != null && listbaithuocThuocDel.size() >0){
				for(int i = 0; i < listbaithuocThuocDel.size(); i++){
					BaithuocThuoc temp = (BaithuocThuoc)listbaithuocThuocDel.get(i);
					if(temp.getBaithuocthuocMaso() != null){
						baithuocThuocDel.remove(temp);
					}
				}
			}
			if(this.dmBaiThuoc.getDmbaithuocMaso() == null){
				dtUtil.create(this.dmBaiThuoc);
			}else{				
				dtUtil.edit(this.dmBaiThuoc);
			}
			this.dmBaiThuoc = (DmBaiThuoc) dtUtil.findByMa(this.dmBaiThuoc.getDmbaithuocMa(), "DmBaiThuoc", "dmbaithuocMa");
			System.out.println("listbaithuocThuoc.size(): "+ listbaithuocThuoc.size());
			for(int i = 0; i < listbaithuocThuoc.size(); i++){
				BaithuocThuoc temp = (BaithuocThuoc)listbaithuocThuoc.get(i);
				temp.setDmbaithuocMaso(this.dmBaiThuoc);
				temp.setBaithuocthuocNgaygiocn((double)date.getTime());
				if(temp.getBaithuocthuocMaso() == null){
					dtUtil.create(temp);
				}else{					
					dtUtil.edit(temp);
				}
			}			
			
			FacesMessages.instance().add(IConstantsRes.baithuoc_cr_su, this.dmBaiThuoc.getDmbaithuocMa());
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.baithuoc_cr_fa, this.dmBaiThuoc.getDmbaithuocMa());
		}
		
	}
	
	public void setDmtMa(String dmtMa) {
		this.dmtMa = dmtMa;
	}

	public String getDmtMa() {
		return dmtMa;
	}
	
	public String getDmtTen() {
		return dmtTen;
	}

	public void setDmtTen(String dmtTen) {
		this.dmtTen = dmtTen;
	}

	public List<SelectItem> getListDmThuocs() {
		return listDmThuocs;
	}

	public void setListDmThuocs(List<SelectItem> listDmThuocs) {
		this.listDmThuocs = listDmThuocs;
	}
	
	public void onblurMaThuocAction(){
		log.info("-----BEGIN onblurMaThuocAction()-----");
		if (dmtMa != null) {
			DmThuoc dmThuoc = hmDmThuoc.get(dmtMa.toUpperCase());
			if(dmThuoc != null) {
				setDmtTen(dmThuoc.getDmthuocTen());
				baithuocThuoc.setDmthuocMaso(dmThuoc);
				log.info("-----DA THAY DMTHUOC-----");
			}
			else {
				setDmtTen("");
				baithuocThuoc.setDmthuocMaso(new DmThuoc());
			}
		}
		log.info("-----END onblurMaThuocAction()-----");
	}
	
	public void onblurTenThuocAction(){
		log.info("-----BEGIN onblurTenThuocAction()-----");
		Boolean hasTenThuoc = false;
		String maThuoc = "";
		DmThuoc dmThuocFinded = new DmThuoc();
		if( hmDmThuoc != null ){
			java.util.Set set = hmDmThuoc.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext()){
				Map.Entry me = (Map.Entry)i.next();
			   	DmThuoc dmThuoc = (DmThuoc)me.getValue();
			   	if(dmThuoc.getDmthuocTen().trim() == dmtTen || dmThuoc.getDmthuocTen().trim().equals(dmtTen)){
			   		hasTenThuoc = true;
			   		maThuoc = dmThuoc.getDmthuocMa();
			   		dmThuocFinded = dmThuoc;
			   		break;
			   	}	    		
			}
		}
	    if(hasTenThuoc){
	    	setDmtMa(maThuoc);
	    	baithuocThuoc.setDmthuocMaso(dmThuocFinded);
			log.info("-----DA THAY DMTHUOC-----");
	    }else{
	    	setDmtMa("");
	    	baithuocThuoc.setDmthuocMaso(new DmThuoc());
	    }
		log.info("-----END onblurTenThuocAction()-----");
	}
	
	public void refreshDmThuoc(){
		listDmThuocs.clear();
		hmDmThuoc.clear();
		dmThuocDelegate = DmThuocDelegate.getInstance();
		
		List<DmThuoc> lstDmThuoc = new ArrayList<DmThuoc>();
		//lstDmThuoc = dmThuocDelegate.findDongYAll();
		lstDmThuoc = dmThuocDelegate.findAll(); // 20120504 bao.ttc: cho hien tat ca DmThuoc de co the tao Bai Thuoc tuy y
		
		if(lstDmThuoc !=null && lstDmThuoc.size()>0)
		{
			for(DmThuoc o: lstDmThuoc){
				listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
				hmDmThuoc.put(o.getDmthuocMa(), o);
			}
		}		
	}

	public String goBack() {
		log.info("GoBack DmBaiThuocExt_add....");
		
		return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy";
	}
	
	private void setMaFromTen() {
		log.info("setMaFromTen DmBaiThuocExt_add....");
		
		int count = 0;
		String maTemp = this.dmBaiThuoc.getDmbaithuocMa();
		List<DmBaiThuoc> ls = new ArrayList<DmBaiThuoc>();
				
		ls = DieuTriUtilDelegate.getInstance().findMaLike("DmBaiThuoc", "dmbaithuocMa", "dmbaithuocDt", this.dmBaiThuoc.getDmbaithuocMa(), true);
	
		for (DmBaiThuoc dmBaiThuocTemp : ls) {
			if(this.dmBaiThuoc.getDmbaithuocMaso() != null){
				if(this.dmBaiThuoc.getDmbaithuocMa().equals(dmBaiThuocTemp.getDmbaithuocMa())){
					return;
				}
			}
			String temp = dmBaiThuocTemp.getDmbaithuocMa().substring(6);
			int counTemp = Integer.parseInt(temp);
			
			if (count < counTemp) {
				count = counTemp;
			}
		}
		
		count++;
		
		if ((count+"").length()==1) {
			
			if (count == 0) {
				maTemp += "00001";
			} else {
				maTemp += "0000" + count;
			}
			
		} else if ((count+"").length()==2) {
			
			maTemp += "000" + count;
			
		} else if ((count+"").length()==3) {
			
			maTemp += "00" + count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "0" + count;
			
		} else if ((count+"").length()==5) {
			
			maTemp += count;
			
		} else if ((count+"").length()==4) {
			
			maTemp += "99999" + count;
		}
		
		this.dmBaiThuoc.setDmbaithuocMa(maTemp);
	}
	
	public void reset() {
		log.info("Reset DmBaiThuocExt_add....");

		resetValue();
	}
	
	public void themCt() {
		log.info("-----themCt()-----");
		if (!isUpdate) {			
			try {				
				BaithuocThuoc baithuocThuocNew = (BaithuocThuoc) BeanUtils.cloneBean(baithuocThuoc);
				boolean update = false;
				for(int i = 0; i < listbaithuocThuoc.size(); i++){
					System.out.println("Ma thuoc grid: "+listbaithuocThuoc.get(i).getDmthuocMaso().getDmthuocMaso());
					System.out.println("Ma thuoc add moi: "+baithuocThuoc.getDmthuocMaso().getDmthuocMaso());					
					if(listbaithuocThuoc.get(i).getDmthuocMaso().getDmthuocMaso().equals(baithuocThuoc.getDmthuocMaso().getDmthuocMaso())){
						baithuocThuocNew.setBaithuocthuocMaso(listbaithuocThuoc.get(i).getBaithuocthuocMaso());
						listbaithuocThuoc.set(i, baithuocThuocNew);
						update = true;
						break;
					}
				}
				if(!update){
					DmThuoc dmThuoc = (DmThuoc) DieuTriUtilDelegate.getInstance().findByMa(baithuocThuoc.getDmthuocMaso().getDmthuocMa(), "DmThuoc", "dmthuocMa");
					if(dmThuoc != null){
						baithuocThuocNew.setDmthuocMaso(dmThuoc);				
						listbaithuocThuoc.add(baithuocThuocNew);
					}
				}
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
		}
		setNewValueForBaithuocThuoc();
		dmtMa ="";
		dmtTen ="";
	}
	
	public void deleteCt(Integer index) {
		log.info("-----deleteCt()-----");
		log.info("-----index: " + index);
		listbaithuocThuocDel.add(listbaithuocThuoc.get(index));
		listbaithuocThuoc.remove(index.intValue());		
		log.info("-----listCtNhapKho.size(): " + listbaithuocThuoc.size());		
	}
	
	public void selectCt(Integer index) {
		log.info("-----selectCt()-----");
		log.info("-----index: " + index);
		baithuocThuoc = listbaithuocThuoc.get(index.intValue());	
	}

	private void resetValue() {
		this.dmBaiThuoc = new DmBaiThuoc();
		listbaithuocThuoc = new ArrayList<BaithuocThuoc>();
		listbaithuocThuocDel = new ArrayList<BaithuocThuoc>();
		dmtMa ="";
		dmtTen ="";
		refreshDmThuoc();
		setNewValueForBaithuocThuoc();	
	}
	
	private void setNewValueForBaithuocThuoc() {		
		baithuocThuoc = new BaithuocThuoc();
		baithuocThuoc.setDmbaithuocMaso(new DmBaiThuoc());
		baithuocThuoc.setDmthuocMaso(new DmThuoc());
	}

	public DmBaiThuoc getDmBaiThuoc() {
		return dmBaiThuoc;
	}

	public void setDmBaiThuoc(DmBaiThuoc dmBaiThuoc) {
		this.dmBaiThuoc = dmBaiThuoc;
	}

	
}
