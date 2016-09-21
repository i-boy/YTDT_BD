package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import org.apache.log4j.Logger;
//import org.hibernate.validator.InvalidValue;
import org.jboss.seam.annotations.Create;
//import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
//import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
//import org.jboss.seam.annotations.security.Restrict;
//import org.jboss.seam.faces.FacesMessages;
//import org.jboss.seam.security.Identity;

// MYSQL DB - QUERY
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.iesvn.yte.util.IConstantsRes;
//import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B111_Dangkykhambenh")
@Synchronized(timeout = 6000000)
public class DangKyKhamBenhOnlineAction implements Serializable {

	
	private static final long serialVersionUID = 4201303779918089532L;
	private static Logger log = Logger.getLogger(DangKyKhamBenhOnlineAction.class);

	//private static final String urlMySQL = "jdbc:mysql://10.0.99.99:3306/healthonline_db";
	private static final String urlMySQL = IConstantsRes.DK_ONLINE_DB_URL;
	private static final String userName = IConstantsRes.DATABASE_USER;
	private static final String password = IConstantsRes.DATABASE_PASS;
	Connection conn;
	
	private String maTD_online = "";
	private String tenTD_online = "";
	private String gioi = "";
	private String tuoi_online = "";
	private String donvituoi = "";
	private String diachi_online = "";
	private String maBH_online = "";
	private String ngayTDDK = "";
	private String gioTDDK = "";
	
	private String ngaygioDK = "";
	private String doituong = "";

	
	@Create
	public void init() {
		log.info("### init() B111_Dangkykhambenh ###");
		//resetValue();
	}

	public void search_maTDonline() {
		
		if(!urlMySQL.startsWith("jdbc")){
			log.info("### Khong co server Dang ky online");
			return;
		}
		
		if(maTD_online.length() < 5){
			int add_zero = 5 - maTD_online.length();
			for(int i =0; i < add_zero; i++ ){
				maTD_online = "0" + maTD_online;
			}
		}
			
		String query = "SELECT * FROM healthonline_db.regonline r WHERE r.regno LIKE " + "'%" + maTD_online + "'" + " order by r.regid desc";
		//log.info("### Query ### " + query);
		
		try{
			
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			conn = DriverManager.getConnection(urlMySQL, userName, password);
		
			ResultSet rs;
			
			try{
	            PreparedStatement stmt = conn.prepareStatement(query);
	            rs =  stmt.executeQuery();
	            
	            if(rs.first()){
	            	tenTD_online = rs.getString("pname");
	            	maTD_online = rs.getString("regno");
	            	tuoi_online = rs.getString("page");
	            	donvituoi = rs.getString("agetype");
	            	diachi_online = rs.getString("paddress");
	            	maBH_online = rs.getString("pcardno");
	            	gioi = rs.getString("psex");
	            	ngayTDDK = rs.getString("regdate");
	            	gioTDDK = rs.getString("regtime");
	            	
	            	log.info("### SQL Query OK !! ### Ma: " + maTD_online
	            			+ " <> Tuoi: " + tuoi_online + " <> DV Tuoi: " + donvituoi + " <> Dia chi: " + diachi_online
	            			+ " <> Ma BH: " + maBH_online + " <> Ngay DK: " + ngayTDDK + " <> Gio DK: " + gioTDDK
	            			+ " <> Gioi tinh: " + gioi );
	            	
	            } else{
	            	FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND);
	            	log.info("### SQL Query OK !! ### No result !! ");
	            }
	            
	            
			} catch(SQLException er){
	            log.info("### SQL Querry Error !! ### " + er.getMessage());
	        }
		
		} catch(SQLException er){
			log.info("### SQL Connection Error !! ### " + er.getMessage());
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				log.info("### SQL Connection Close Error !! ### " + e.getMessage());
			}
		}
		
		
		// Xu ly cho Don vi tuoi, Gioi tinh
		if(donvituoi.equals("DAY")){
			donvituoi = "3";
		} else if(donvituoi.equals("MONTH")){
			donvituoi = "2";
		} else 
			donvituoi = "1";
		
		if(gioi == null || gioi.equals("")){
			gioi = "1";
		}
		
		// Xu ly cho ma BHYT
		if ( (maBH_online != null) && !("".equals(maBH_online)) ) {
			doituong = "BH";
		} else
			doituong = "TP";
		
		
		// Xu ly cho ngay gio DK
		if ( (ngayTDDK != null) && !("".equals(ngayTDDK)) ) {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df_view = new SimpleDateFormat("dd/MM/yyyy");
			String today = df.format(new Date());
			
			try {
				Date ngayDK = df.parse(ngayTDDK);
				Date ngayHT = df.parse(today);
				ngaygioDK = df_view.format(ngayDK);
				
				if(ngayDK.before(ngayHT)){
					FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENTRE, ngaygioDK);
				}
				if(ngayDK.after(ngayHT)){
					FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENSOM, ngaygioDK);
				}
				
				if( gioTDDK != null && !("".equals(gioTDDK)) ){
					ngaygioDK = gioTDDK + ":00 " + IConstantsRes.NGAY + " " + ngaygioDK;
				}
				

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	// Xu ly cho ngay gio DK -- END 
		
		
    }
 
	
	public void resetValue() {
		
		log.info("### resetValue() ###");
		FacesMessages.instance().clear();
		tenTD_online = "";
    	maTD_online = "";
    	tuoi_online = "";
    	donvituoi = "1";
    	diachi_online = "";
    	maBH_online = "";
    	gioi = "1";
    	ngayTDDK = "";
    	gioTDDK = "";
    	ngaygioDK = "";
    	doituong = "";
		
	}
	
	
	public String getDonvituoi() {
		return donvituoi;
	}

	public void setDonvituoi(String donvituoi) {
		this.donvituoi = donvituoi;
	}
	

	public String getGioi() {
		return gioi;
	}

	public void setGioi(String gioi) {
		this.gioi = gioi;
	}
	
	public String getMaTD_online() {
		return maTD_online;
	}

	public void setMaTD_online(String maTDOnline) {
		maTD_online = maTDOnline;
	}

	public String getTenTD_online() {
		return tenTD_online;
	}

	public void setTenTD_online(String tenTDOnline) {
		tenTD_online = tenTDOnline;
	}
	
	public String getTuoi_online() {
		return tuoi_online;
	}

	public void setTuoi_online(String tuoiOnline) {
		tuoi_online = tuoiOnline;
	}
	
	public String getDiachi_online() {
		return diachi_online;
	}

	public void setDiachi_online(String diachiOnline) {
		diachi_online = diachiOnline;
	}
	
	public String getMaBH_online() {
		return maBH_online;
	}

	public void setMaBH_online(String maBHOnline) {
		maBH_online = maBHOnline;
	}
	
	public String getNgayTDDK() {
		return ngayTDDK;
	}

	public void setNgayTDDK(String ngayTDDK) {
		this.ngayTDDK = ngayTDDK;
	}

	public String getGioTDDK() {
		return gioTDDK;
	}

	public void setGioTDDK(String gioTDDK) {
		this.gioTDDK = gioTDDK;
	}

	public String getNgaygioDK() {
		return ngaygioDK;
	}

	public void setNgaygioDK(String ngaygioDK) {
		this.ngaygioDK = ngaygioDK;
	}

	public String getDoituong() {
		return doituong;
	}

	public void setDoituong(String doituong) {
		this.doituong = doituong;
	}
	
	
	
}


