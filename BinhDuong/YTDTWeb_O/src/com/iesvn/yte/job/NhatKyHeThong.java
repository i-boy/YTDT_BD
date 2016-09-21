package com.iesvn.yte.job;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
import com.iesvn.yte.dieutri.delegate.YteLogDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("nhatkyHethong")
@Synchronized(timeout = 6000000)
public class NhatKyHeThong  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loaiBaoCao;
	private String tuNgay;
	private String denNgay;
	private String user;
	private String logString;
	private String logData;
	private String object;
	@DataModel
	private List<YteLog> listLogs;
	private List<SelectItem> listUser;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Begin(join = true)
	public String init(){
		resetForm();
		loadListUser();
		return "nhatkyhethong";
	}
	
	public List<SelectItem> loadListUser(){
		listUser = new ArrayList<SelectItem>();
		DtDmNhanVienDelegate nguoiDungDelegate = DtDmNhanVienDelegate.getInstance();
		List<DtDmNhanVien> lst = nguoiDungDelegate.findAllWithOrderBy();
		for (DtDmNhanVien nd : lst) {
			listUser.add(new SelectItem(nd.getNdMaso(true).getNdTendangnhap(),(nd.getNdMaso(true).getNdTendangnhap()== null ? "" : nd.getNdMaso(true).getNdTendangnhap()) + " - " + nd.getDtdmnhanvienTen()));
		}
		return listUser;
	}

	public void resetForm(){
		object = "";
		logData = "";
		logString= "";
		loaiBaoCao = "";
		tuNgay = dateFormat.format(Calendar.getInstance().getTime());
		denNgay = dateFormat.format(Calendar.getInstance().getTime());
		listLogs = new ArrayList<YteLog>();
	}
	
	public  void thuchienAction(){
		YteLogDelegate logDelegate = YteLogDelegate.getInstance();
		
		try {
			Date dTuNgay = null;
			Date dDenNgay = null;
			if (tuNgay!=null && !"".equals(tuNgay)){
				dTuNgay = dateFormat.parse(tuNgay);
			}
			if (denNgay!=null && !"".equals(denNgay)){
				dDenNgay = dateFormat.parse(denNgay);
			}
			if (user.equals("ALL")){
				user = "";
			}
			if (loaiBaoCao.equals("ALL")){
				loaiBaoCao = "";
			}
			
			listLogs = logDelegate.findByCondition(loaiBaoCao, user, object, logString, logData, dTuNgay, dDenNgay);
			
			if (listLogs.isEmpty()){
				FacesMessages.instance().add(IConstantsRes.no_found);
				return ;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLoaiBaoCao() {
		return loaiBaoCao;
	}

	public void setLoaiBaoCao(String loaiBaoCao) {
		this.loaiBaoCao = loaiBaoCao;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLogString() {
		return logString;
	}

	public void setLogString(String logString) {
		this.logString = logString;
	}

	public String getLogData() {
		return logData;
	}

	public void setLogData(String logData) {
		this.logData = logData;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<YteLog> getListLogs() {
		return listLogs;
	}

	public void setListLogs(List<YteLog> listLogs) {
		this.listLogs = listLogs;
	}

	public List<SelectItem> getListUser(){
		return listUser;
	}
	public void setListUser(List<SelectItem> listUser) {
		this.listUser = listUser;
	}

	
}
