package com.iesvn.yte.identity.extension;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("quanlycumthuphi")
@Synchronized(timeout = 6000000)
public class QLCumThuPhiAction implements Serializable {

	private static final long serialVersionUID = -142440506671926180L;
	private static Logger log = Logger.getLogger(QLCumThuPhiAction.class);
	
	private String tungay;
	private String denngay;
	
	private Integer cumMaso;
	private String cumMa;
	private String cumTen;
	
	private Integer nhanvienMaso;
	private String nhanvienMa;
	private String nhanvienTen;
	private Integer quyen;
	
	private String update;
	
	@DataModel
	private List<PcCumThuPhi> listPCCTP;
	@DataModelSelection
	private PcCumThuPhi chitiet;
	
	private String nosuccess;

	@Create
	public void init() {
		resetValue();
	}
	
	public void resetValue(){
		log.info("---resetValue");
		listPCCTP = new ArrayList<PcCumThuPhi>();
		nosuccess=tungay=denngay=cumMa=nhanvienMa=nhanvienTen=cumTen="";
		update="false";
		nhanvienMaso=cumMaso=quyen=0;
	}
	
	public void displayCT(int index){
		log.info("---displayCT");
		chitiet = listPCCTP.get(index);
		tungay = formatDate(chitiet.getPcctpTungay());
		denngay = formatDate(chitiet.getPcctpDenngay());
		cumMa = chitiet.getDtdmcumMa().getDtdmcumMa();
		nhanvienMa = chitiet.getDtdmnhanvienMa().getDtdmnhanvienMa();
		quyen = Integer.valueOf(String.valueOf(chitiet.getQuyen()));
		update="true";
	}
	
	public void updateCT(){
		log.info("---updateCT");		
		DtDmNhanVien nv = new DtDmNhanVien();
		nv.setDtdmnhanvienMaso(nhanvienMaso);
		nv.setDtdmnhanvienMa(nhanvienMa);
		nv.setDtdmnhanvienTen(nhanvienTen);
		DtDmCum cum = new DtDmCum();
		cum.setDtdmcumMaso(cumMaso);
		cum.setDtdmcumMa(cumMa);
		cum.setDtdmcumTen(cumTen);		
		if (update.equals("true")){
			chitiet.setQuyen(Short.valueOf(String.valueOf(quyen)));
			chitiet.setDtdmnhanvienMa(nv);
			PcCumThuPhiDelegate.getInstance().edit(chitiet);
			update="false";
			nosuccess=tungay=denngay=cumMa=nhanvienMa="";
			nhanvienMaso=cumMaso=quyen=0;
		}else{
			PcCumThuPhi obj = new PcCumThuPhi();
			obj.setQuyen(Short.valueOf(String.valueOf(quyen)));
			obj.setDtdmnhanvienMa(nv);
			obj.setDtdmcumMa(cum);
			obj.setPcctpTungay(Utils.getDBDate("00:00", tungay).getTime());
			obj.setPcctpDenngay(Utils.getDBDate("00:00", denngay).getTime());
			PcCumThuPhiDelegate.getInstance().create(obj);
			listPCCTP.add(obj);
			nosuccess=tungay=denngay=cumMa=nhanvienMa="";
			nhanvienMaso=cumMaso=quyen=0;
		}
	}
	
	public void search(){
		log.info("---search");
		listPCCTP = new ArrayList<PcCumThuPhi>();
		Date tn=null,dn=null;
		String cum=null;
		if (!tungay.equals("")){
			try {
				tn = new SimpleDateFormat("dd/MM/yyyy").parse(tungay);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		if (!denngay.equals("")){
			try {
				dn = new SimpleDateFormat("dd/MM/yyyy").parse(denngay);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		if (!cumMa.equals("")){
			cum = cumMa;
		}
		List<PcCumThuPhi> list = PcCumThuPhiDelegate.getInstance().findPcCumThuPhi(tn, dn, cum);
		if (list!=null && list.size()>0){
			listPCCTP = list;
		}else{
			FacesMessages.instance().add(IConstantsRes.PCCTP_SEARCH_NULL);
		}
	}
	
	public void delete(int index){
		log.info("---delete");
		PcCumThuPhi del = listPCCTP.get(index);
		listPCCTP.remove(index);
		try{
			PcCumThuPhiDelegate.getInstance().remove(del);
			FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_SUC);
		}catch (Exception ex){
			FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_FAIL);
		}
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date); 
	}

	public Integer getNhanvienMaso() {
		return nhanvienMaso;
	}

	public void setNhanvienMaso(Integer nhanvienMaso) {
		this.nhanvienMaso = nhanvienMaso;
	}

	public Integer getQuyen() {
		return quyen;
	}

	public void setQuyen(Integer quyen) {
		this.quyen = quyen;
	}

	public String getNhanvienMa() {
		return nhanvienMa;
	}

	public void setNhanvienMa(String nhanvienMa) {
		this.nhanvienMa = nhanvienMa;
	}

	public PcCumThuPhi getChitiet() {
		return chitiet;
	}

	public void setChitiet(PcCumThuPhi chitiet) {
		this.chitiet = chitiet;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getNhanvienTen() {
		return nhanvienTen;
	}

	public void setNhanvienTen(String nhanvienTen) {
		this.nhanvienTen = nhanvienTen;
	}

	public Integer getCumMaso() {
		return cumMaso;
	}

	public void setCumMaso(Integer cumMaso) {
		this.cumMaso = cumMaso;
	}

	public String getCumTen() {
		return cumTen;
	}

	public void setCumTen(String cumTen) {
		this.cumTen = cumTen;
	}
	
	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public List<PcCumThuPhi> getListPCCTP() {
		return listPCCTP;
	}

	public void setListPCCTP(List<PcCumThuPhi> listPCCTP) {
		this.listPCCTP = listPCCTP;
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

	public String getCumMa() {
		return cumMa;
	}

	public void setCumMa(String cumMa) {
		this.cumMa = cumMa;
	}
	
}
