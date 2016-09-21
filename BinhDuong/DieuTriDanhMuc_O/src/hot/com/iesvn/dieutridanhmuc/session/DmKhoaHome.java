package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmKhoaHome")
public class DmKhoaHome extends EntityHome<DmKhoa> {
	private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DmKhoaBytHome dmKhoaBytHome;
	@In(create = true)
	DmLoaiKhoaHome dmLoaiKhoaHome;
	@In(create = true)
	DmNhomKhoaHome dmNhomKhoaHome;

	public void setDmKhoaDmkhoaMaso(Integer id) {
		setId(id);
	}

	public Integer getDmKhoaDmkhoaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmKhoa createInstance() {
		DmKhoa dmKhoa = new DmKhoa();
		return dmKhoa;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmKhoa> temp = new DmKhoaList("").getResultList();
		for(DmKhoa each : temp){
			listTemp.add(each.getDmkhoaTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmkhoaTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmKhoaByt dmKhoaBytByDmkhoaMabyt = dmKhoaBytHome.getDefinedInstance();
		if (dmKhoaBytByDmkhoaMabyt != null) {
			getInstance().setDmKhoaBytByDmkhoaMabyt(dmKhoaBytByDmkhoaMabyt);
		}
		DmKhoaByt dmKhoaBytByDmkhoaMabyt3 = dmKhoaBytHome.getDefinedInstance();
		if (dmKhoaBytByDmkhoaMabyt3 != null) {
			getInstance().setDmKhoaBytByDmkhoaMabyt3(dmKhoaBytByDmkhoaMabyt3);
		}
		DmLoaiKhoa dmLoaiKhoa = dmLoaiKhoaHome.getDefinedInstance();
		if (dmLoaiKhoa != null) {
			getInstance().setDmLoaiKhoa(dmLoaiKhoa);
		}
		DmNhomKhoa dmNhomKhoa = dmNhomKhoaHome.getDefinedInstance();
		if (dmNhomKhoa != null) {
			getInstance().setDmNhomKhoa(dmNhomKhoa);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmKhoa getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmKhoa> getDmKhoas() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoas());
	}

	public List<DmKhoa> getDmKhoas_1() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoas_1());
	}

	public List<DonMien> getDonMiens() {
		return getInstance() == null ? null : new ArrayList<DonMien>(
				getInstance().getDonMiens());
	}

	public List<DtDmBanKham> getDtDmBanKhams() {
		return getInstance() == null ? null : new ArrayList<DtDmBanKham>(
				getInstance().getDtDmBanKhams());
	}

	public List<DtDmBuong> getDtDmBuongs() {
		return getInstance() == null ? null : new ArrayList<DtDmBuong>(
				getInstance().getDtDmBuongs());
	}

	public List<DtDmClsKhoa> getDtDmClsKhoas() {
		return getInstance() == null ? null : new ArrayList<DtDmClsKhoa>(
				getInstance().getDtDmClsKhoas());
	}

	public List<DtDmKhach> getDtDmKhachs() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs());
	}

	public List<DtDmKhach> getDtDmKhachs_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKhach>(
				getInstance().getDtDmKhachs_1());
	}

	public List<DtDmKho> getDtDmKhos() {
		return getInstance() == null ? null : new ArrayList<DtDmKho>(
				getInstance().getDtDmKhos());
	}

	public List<DtDmKho> getDtDmKhos_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKho>(
				getInstance().getDtDmKhos_1());
	}

	public List<DtDmNhanvienKhoa> getDtDmNhanvienKhoas() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanvienKhoa>(
				getInstance().getDtDmNhanvienKhoas());
	}

	public List<DtDmNhanvienKhoa> getDtDmNhanvienKhoas_1() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanvienKhoa>(
				getInstance().getDtDmNhanvienKhoas_1());
	}

	public List<DtDmPhongMo> getDtDmPhongMos() {
		return getInstance() == null ? null : new ArrayList<DtDmPhongMo>(
				getInstance().getDtDmPhongMos());
	}

	public List<DtDmPhongMo> getDtDmPhongMos_1() {
		return getInstance() == null ? null : new ArrayList<DtDmPhongMo>(
				getInstance().getDtDmPhongMos_1());
	}
public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmkhoaTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmkhoaTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmkhoaTen("");
					break;
				}
			}						
		}		
	}

}
