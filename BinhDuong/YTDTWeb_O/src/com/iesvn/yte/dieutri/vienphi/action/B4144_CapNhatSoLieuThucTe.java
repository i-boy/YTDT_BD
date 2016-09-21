package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
import com.iesvn.yte.dieutri.entity.KiemKeKho;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B4144_capnhatsolieuthucte")
@Synchronized(timeout = 6000000)
public class B4144_CapNhatSoLieuThucTe implements Serializable {

	private static final long serialVersionUID = -2130703730903444169L;	
	private static Logger log = Logger.getLogger(B4144_CapNhatSoLieuThucTe.class);
	private KiemKeKho kiemkekho ;
	private String ngayKiem;
	private String hanDung;
	
	@Create
	public void init() throws Exception {
		resetValue();
	}
	
	public void resetValue(){
		log.info("---resetValue");
		ngayKiem = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		hanDung="";
		kiemkekho = new KiemKeKho();
	}
		
	public void ghiNhan(){	
		log.info("---ghiNhan");
		String[] ntnHanDung = hanDung.split("/");
		String maLk = kiemkekho.getDmthuocMaso().getDmthuocMa() 
					+ kiemkekho.getDmnctMaso().getDmnctMa()
					+ kiemkekho.getDmnguonkinhphiMaso().getDmnguonkinhphiMa()
					+ kiemkekho.getDmquocgiaMaso().getDmquocgiaMa()
					+ kiemkekho.getDmnhasanxuatMaso().getDmnhasanxuatMa()
					+ kiemkekho.getKiemkeNoiban().getDmnhacungcapMa()
					+ kiemkekho.getKiemkeNamnhap()
					+ ntnHanDung[0]
					+ ntnHanDung[1]
					+ ntnHanDung[2]
					+ kiemkekho.getKiemkeDongia();
		KiemKeKhoDelegate delegate = KiemKeKhoDelegate.getInstance();
		log.info("khoa: " + kiemkekho.getDmkhoaMaso().getDmkhoaMa());
		log.info("malienket " + maLk);
		KiemKeKho kkk_tmp = delegate.findByKhoAndMaLienKet(kiemkekho.getDmkhoaMaso().getDmkhoaMa(), maLk);
		if (kkk_tmp!=null){
			kiemkekho = kkk_tmp;
			kiemkekho.setKiemkeMalienket(maLk);
			kiemkekho.setKiemkeNgaygiocn(new Date());
			kiemkekho.setKiemkeNgaykiem(Utils.getDBDate("00:00", ngayKiem).getTime());
			String result = delegate.updateAndEditTonKhoDau(kiemkekho);
			if (result==null||result.equals("")){
				FacesMessages.instance().add(IConstantsRes.KKK_CN_THATBAI);
			}else{
				FacesMessages.instance().add(IConstantsRes.KKK_CN_THANHCONG, result);
			}
		}else{
			FacesMessages.instance().add(IConstantsRes.KKK_NULL);
		}		
	}
	
	public String getNgayKiem() {
		return ngayKiem;
	}

	public void setNgayKiem(String ngayKiem) {
		this.ngayKiem = ngayKiem;
	}
	
	public KiemKeKho getKiemkekho() {
		return kiemkekho;
	}

	public void setKiemkekho(KiemKeKho kiemkekho) {
		this.kiemkekho = kiemkekho;
	}	
	
	public String getHanDung() {
		return hanDung;
	}

	public void setHanDung(String hanDung) {
		this.hanDung = hanDung;
	}
}
