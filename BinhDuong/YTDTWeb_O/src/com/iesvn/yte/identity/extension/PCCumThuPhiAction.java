package com.iesvn.yte.identity.extension;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("phancongcumthuphi")
@Synchronized(timeout = 6000000)
public class PCCumThuPhiAction  implements Serializable {

	private static final long serialVersionUID = -460623918265282727L;
	private static Logger log = Logger.getLogger(PCCumThuPhiAction.class);
	
	private String tungay;
	private String denngay;
	private Integer nhanvienMaso;
	private String nhanvienMa;
	private String nhanvienTen;
	private Integer cumMaso;
	private String cumMa;
	private String cumTen;
	private Integer quyen;
	
	@DataModel
	private List<PcCumThuPhi> listPCCTP;	
	
	@Create
	public void init() {
		resetValue();
	}
	
	private String nosuccess;
	
	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public void resetValue(){
		log.info("---resetValue");
		listPCCTP = new ArrayList<PcCumThuPhi>();
		tungay = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 5);
		denngay = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
		nhanvienMa=nhanvienTen=cumMa=cumTen="";
		quyen=nhanvienMaso=cumMaso=0;
		nosuccess="false";
	}
	
	public void enter(){
		log.info("---enter");		
		DtDmCum cum = new DtDmCum();
		cum.setDtdmcumMaso(cumMaso);
		cum.setDtdmcumMa(cumMa);
		cum.setDtdmcumTen(cumTen);
		DtDmNhanVien nv = new DtDmNhanVien();
		nv.setDtdmnhanvienMaso(nhanvienMaso);
		nv.setDtdmnhanvienMa(nhanvienMa);
		nv.setDtdmnhanvienTen(nhanvienTen);
		PcCumThuPhi chitiet = new PcCumThuPhi();
		chitiet.setDtdmnhanvienMa(nv);
		chitiet.setDtdmcumMa(cum);
		chitiet.setQuyen(Short.valueOf(String.valueOf(quyen)));
		chitiet.setPcctpTungay(Utils.getDBDate("00:00", tungay).getTime());
		chitiet.setPcctpDenngay(Utils.getDBDate("00:00", denngay).getTime());
		listPCCTP.add(chitiet);
	}
	
	public void delete(int index){
		log.info("---delete");
		listPCCTP.remove(index);
	}
	
	public void ghinhan(){
		log.info("---ghinhan");
		if (listPCCTP.size()>0){
			int rs = PcCumThuPhiDelegate.getInstance().ghinhan(listPCCTP);
			if (rs==1) {
				FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS);
			}else if (rs==0){
				nosuccess="true";				
				FacesMessages.instance().add(IConstantsRes.INSERT_FAIL);
			}else if (rs==-1){
				nosuccess="true";				
				FacesMessages.instance().add(IConstantsRes.PCCTP_INS_FAIL);
			}
		}else{
			nosuccess="true";
			FacesMessages.instance().add(IConstantsRes.PCCTP_NULL);
		}		
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

	public List<PcCumThuPhi> getListPCCTP() {
		return listPCCTP;
	}

	public void setListPCCTP(List<PcCumThuPhi> listPCCTP) {
		this.listPCCTP = listPCCTP;
	}

	public Integer getNhanvienMaso() {
		return nhanvienMaso;
	}

	public void setNhanvienMaso(Integer nhanvienMaso) {
		this.nhanvienMaso = nhanvienMaso;
	}

	public String getNhanvienMa() {
		return nhanvienMa;
	}

	public void setNhanvienMa(String nhanvienMa) {
		this.nhanvienMa = nhanvienMa;
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

	public String getCumMa() {
		return cumMa;
	}

	public void setCumMa(String cumMa) {
		this.cumMa = cumMa;
	}

	public String getCumTen() {
		return cumTen;
	}

	public void setCumTen(String cumTen) {
		this.cumTen = cumTen;
	}

	public Integer getQuyen() {
		return quyen;
	}

	public void setQuyen(Integer quyen) {
		this.quyen = quyen;
	}
}
