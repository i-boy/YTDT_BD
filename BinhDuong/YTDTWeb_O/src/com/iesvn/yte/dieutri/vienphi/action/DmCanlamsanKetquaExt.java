package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsKetQuaDelegate;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmCanlamsanKetqua")
@Scope(SESSION)
public class DmCanlamsanKetquaExt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@In(required = false)
	@Out(required = false)
	private DtDmClsKetQua dtDmClsKetQua;

	@DataModel
	private List<DtDmClsKetQua> listDtDmClsKetQua;
	
	private List<SelectItem> listKhoaCLS;
	
	private List<SelectItem> listClsBanggia;
	
	private Integer dmkhoaMaso;
	
	private Integer dtdmclsbgMaso;
	
	private List<String> dmSynchronize;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmCanlamsanKetquaExt....");
		
		resetValue();
		
		return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua";
	}
	
	public void displayCLS(){
		log.info("Init displayCLS....");
		listClsBanggia = new ArrayList<SelectItem>();
		DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();		
		List<DtDmClsBangGia> temp = (ArrayList<DtDmClsBangGia>) dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa((Integer)dmkhoaMaso);
		SelectItem itemTemp = new SelectItem(null,"---");
		listClsBanggia.add(itemTemp);		
		if(temp != null){
			for (DtDmClsBangGia dtDmClsBangGia : temp) {	
				if(dtDmClsBangGia.getDtdmclsbgXn() != null && dtDmClsBangGia.getDtdmclsbgXn() == true){
					SelectItem item = new SelectItem(dtDmClsBangGia.getDtdmclsbgMaso(),dtDmClsBangGia.getDtdmclsbgDiengiai());
					listClsBanggia.add(item);			
				}
			}
		}
	}
	
	public void synchronizeDataDmLabconn() throws ClassNotFoundException, SQLException{
		try{
		if(IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES")){
			log.info("Init synchronizeDataDmLabconn....");
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	    
			String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + ":" + IConstantsRes.LABCONN_PORT + ";useUnicode=true;characterEncoding=UTF-8;" + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
		    String username = IConstantsRes.LABCONN_USERNAME;
		    String password = IConstantsRes.LABCONN_PASSWORD;
		    Class.forName(driver); // load sqlserver driver
		    Connection conn = DriverManager.getConnection(url, username, password);	
		    String query1 = " DELETE FROM [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Location_HIS]; ";		
			query1 += " DELETE FROM [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Doctor_HIS]; ";	
			query1 += " DELETE FROM [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Object_HIS]; ";	
			query1 += " DELETE FROM [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_TestCode_HIS]; ";	
			PreparedStatement stmt1 = conn.prepareStatement(query1);
			stmt1.executeUpdate();
			conn.close();
			conn = DriverManager.getConnection(url, username, password);	
			for(String temp: dmSynchronize){
				log.info("dm " + temp);
				
				if(temp.equals("DmKhoa")){
					List<DmKhoa> list = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);				
					for(DmKhoa dmKhoa: list){
						if(dmKhoa.getDmkhoaCls() != null && dmKhoa.getDmkhoaCls() == true){
							String query = " INSERT INTO [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Location_HIS]([LocationID],[LocationName]) VALUES ";
							query += "('" + dmKhoa.getDmkhoaMa() + "',N'" + dmKhoa.getDmkhoaTen() + "')";
							PreparedStatement stmt = conn.prepareStatement(query);
							stmt.executeUpdate();
						}
					}
				}
				if(temp.equals("DtDmNhanVien")){
					List<DtDmNhanVien> list = DieuTriUtilDelegate.getInstance().findAll("DtDmNhanVien");
					for(DtDmNhanVien dtDmNhanVien: list){
						if( dtDmNhanVien.getDmhocviMaso() != null && (dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(2) ||  dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(3)
							|| dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(4))){
							String query = " INSERT INTO [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Doctor_HIS]([DoctorID],[DoctorName]) VALUES ";
							query += "('" + dtDmNhanVien.getDtdmnhanvienMa() + "',N'" + dtDmNhanVien.getDtdmnhanvienTen() + "')";
							PreparedStatement stmt = conn.prepareStatement(query);
							stmt.executeUpdate();
						}
					}				
				}
				if(temp.equals("DmDoiTuong")){
					List<DmDoiTuong> list = DieuTriUtilDelegate.getInstance().findAll("DmDoiTuong", "dmdoituongDt", true);
					for(DmDoiTuong dmDoiTuong: list){
						String query = " INSERT INTO [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Object_HIS]([ObjectID],[ObjectName]) VALUES ";
						query += "('" + dmDoiTuong.getDmdoituongMa() + "',N'" + dmDoiTuong.getDmdoituongTen() + "')";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.executeUpdate();
					}				
				}
				if(temp.equals("DtDmClsKetQua")){
					List<DtDmClsKetQua> list = DieuTriUtilDelegate.getInstance().findAll("DtDmClsKetQua");
					for(DtDmClsKetQua dtDmClsKetQua: list){
						if(dtDmClsKetQua.getDtdmclskqMa().equals("XN141_013")){
							log.info("dm XN141_013");
						}
						String query = " INSERT INTO [" +  IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_TestCode_HIS]([TestCodeHIS],[TestName]) VALUES ";
						query += "('" + dtDmClsKetQua.getDtdmclskqMa() + "',N'" + dtDmClsKetQua.getDtdmclskqTen() + "')";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.executeUpdate();						
					}
				}
			}
		}
	} catch (Exception e) {
		
	}
	}

	public void search() {
		log.info("Search DmCanlamsanKetquaExt....");
		
		this.listDtDmClsKetQua = DtDmClsKetQuaDelegate.getInstance().findByAllConditions(this.dtDmClsKetQua.getDtdmclskqMa(), this.dtDmClsKetQua.getDtdmclskqTen(), this.dtdmclsbgMaso);
		
		
		if (this.listDtDmClsKetQua.size() == 0) { 
			FacesMessages.instance().add(IConstantsRes.no_found);
		}
		
	}

	public void delete(int rowIndex) {
		log.info("Delete DmCanlamsanKetquaExt....");

		if (rowIndex != -1) {
			
			String ma = "";
			Date date = new Date();
			
			ma = this.listDtDmClsKetQua.get(rowIndex).getDtdmclskqMa();
		
			try {						
				DtDmClsKetQua be = this.listDtDmClsKetQua.get(rowIndex);
				
				DieuTriUtilDelegate.getInstance().remove(be);
				this.listDtDmClsKetQua.remove(rowIndex);

				FacesMessages.instance().add(IConstantsRes.dtdmclskq_de_su, ma);

			} catch (Exception e) {
				log.error(e.toString());
				FacesMessages.instance().add(IConstantsRes.dtdmclskq_de_fa, ma);
			}
		}
	}

	public void reset() {
		log.info("Reset DmCanlamsanKetquaExt....");

		resetValue();
	}
	
	public String goback() {
		log.info("Goback DmCanlamsanKetquaExt....");

		return "/MyMainForm";
	}

	private void resetValue() {
		this.dtDmClsKetQua = new DtDmClsKetQua();
		this.listDtDmClsKetQua = new ArrayList<DtDmClsKetQua>();
		this.listKhoaCLS = new ArrayList<SelectItem>();
		this.listClsBanggia = new ArrayList<SelectItem>();
		SelectItem itemTemp = new SelectItem(null,"---");
		listKhoaCLS.add(itemTemp);
		List<DmKhoa> listKhoaCLSTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);
		for (DmKhoa dmKhoa : listKhoaCLSTemp) {
			if(dmKhoa.getDmkhoaCls() != null && dmKhoa.getDmkhoaCls() == true){
				SelectItem item = new SelectItem(dmKhoa.getDmkhoaMaso(),dmKhoa.getDmkhoaTen());
				listKhoaCLS.add(item);
			}
		}
		this.dmkhoaMaso = null;
		this.dtdmclsbgMaso = null;
		this.dmSynchronize = new ArrayList<String>();
	}

	public DtDmClsKetQua getDtDmClsKetQua() {
		return dtDmClsKetQua;
	}

	public void setDtDmClsKetQua(DtDmClsKetQua dtDmClsKetQua) {
		this.dtDmClsKetQua = dtDmClsKetQua;
	}

	public List<DtDmClsKetQua> getListDtDmClsKetQua() {
		return listDtDmClsKetQua;
	}

	public void setListDtDmClsKetQua(List<DtDmClsKetQua> listDtDmClsKetQua) {
		this.listDtDmClsKetQua = listDtDmClsKetQua;
	}

	public List<SelectItem> getListKhoaCLS() {
		return listKhoaCLS;
	}

	public void setListKhoaCLS(List<SelectItem> listKhoaCLS) {
		this.listKhoaCLS = listKhoaCLS;
	}

	public List<SelectItem> getListClsBanggia() {
		return listClsBanggia;
	}

	public void setListClsBanggia(List<SelectItem> listClsBanggia) {
		this.listClsBanggia = listClsBanggia;
	}

	public Integer getDmkhoaMaso() {
		return dmkhoaMaso;
	}

	public void setDmkhoaMaso(Integer dmkhoaMaso) {
		this.dmkhoaMaso = dmkhoaMaso;
	}

	public Integer getDtdmclsbgMaso() {
		return dtdmclsbgMaso;
	}

	public void setDtdmclsbgMaso(Integer dtdmclsbgMaso) {
		this.dtdmclsbgMaso = dtdmclsbgMaso;
	}

	public List<String> getDmSynchronize() {
		return dmSynchronize;
	}

	public void setDmSynchronize(List<String> dmSynchronize) {
		this.dmSynchronize = dmSynchronize;
	}
	
	
	
}
