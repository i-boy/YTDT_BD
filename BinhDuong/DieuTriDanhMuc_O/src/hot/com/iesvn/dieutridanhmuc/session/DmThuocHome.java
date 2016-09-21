package com.iesvn.dieutridanhmuc.session;

import java.util.ArrayList;
import java.util.List;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmThuocHome")
public class DmThuocHome extends EntityHome<DmThuoc> {
private List<String> listTemp;
	private String myTen = "";
	private int countChange = 0;
	
	@In(create = true)
	DmQuocGiaHome dmQuocGiaHome;
	@In(create = true)
	DmBaoQuanThuocHome dmBaoQuanThuocHome;
	@In(create = true)
	DmCachDungThuocHome dmCachDungThuocHome;
	@In(create = true)
	DmDonViTinhHome dmDonViTinhHome;
	@In(create = true)
	DmHoatChatHome dmHoatChatHome;
	@In(create = true)
	DmNhaSanXuatHome dmNhaSanXuatHome;
	@In(create = true)
	DmPhanLoaiThuocHome dmPhanLoaiThuocHome;
	@In(create = true)
	DmPhanNhomThuocHome dmPhanNhomThuocHome;

	public void setDmThuocDmthuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmThuocDmthuocMaso() {
		return (Integer) getId();
	}

	@Override
	public String persist() {

		/* Edit Ma Part 2 */

		String thuocMaTemp = this.instance.getDmthuocMa();
		int count1 = 0;
		DmThuocList dmThuocList = new DmThuocList("");

		for (DmThuoc each : dmThuocList.getResultList()) {			
			if (thuocMaTemp.equals((each.getDmthuocMa()).substring(0, 3))) {
				int count1_temp = Integer.parseInt(each.getDmthuocMa()
						.substring(3, 7));				
				if (count1 <= count1_temp) {
					count1 = count1_temp;
				}
			}
		}
		count1 = count1 + 1;
		if ((count1 + "").length() == 1) {
			if (count1 == 0) {
				thuocMaTemp = thuocMaTemp + "0001";
			} else {
				thuocMaTemp = thuocMaTemp + "000" + count1 + "";
			}
		} else if ((count1 + "").length() == 2) {
			thuocMaTemp = thuocMaTemp + "00" + count1 + "";
		} else if ((count1 + "").length() == 3) {
			thuocMaTemp = thuocMaTemp + "0" + count1 + "";
		} else if ((count1 + "").length() == 4) {
			thuocMaTemp = thuocMaTemp + count1 + "";
		} else {
			thuocMaTemp = thuocMaTemp + "9999";
		}
		this.instance.setDmthuocMa(thuocMaTemp);

		/* End edit Ma Part 2 */

		return super.persist();
	}

	@Override
	protected DmThuoc createInstance() {
		DmThuoc dmThuoc = new DmThuoc();
		return dmThuoc;
	}

	@Override
	public void create() {
		listTemp = new ArrayList<String>();
		List<DmThuoc> temp = new DmThuocList("").getResultList();
		for(DmThuoc each : temp){
			listTemp.add(each.getDmthuocTen());
		}
		super.create();		
	}
	
	public void focusName(){
		if(countChange == 0 && this.isManaged() == true){
			myTen = this.instance.getDmthuocTen();
			countChange = 1;
		}		
	}

	public void wire() {
		getInstance();
		DmBaoQuanThuoc dmBaoQuanThuoc = dmBaoQuanThuocHome.getDefinedInstance();
		if (dmBaoQuanThuoc != null) {
			getInstance().setDmBaoQuanThuoc(dmBaoQuanThuoc);
		}
		DmCachDungThuoc dmCachDungThuoc = dmCachDungThuocHome
				.getDefinedInstance();
		if (dmCachDungThuoc != null) {
			getInstance().setDmCachDungThuoc(dmCachDungThuoc);
		}
		DmDonViTinh dmDonViTinh = dmDonViTinhHome.getDefinedInstance();
		if (dmDonViTinh != null) {
			getInstance().setDmDonViTinh(dmDonViTinh);
		}
		DmHoatChat dmHoatChat = dmHoatChatHome.getDefinedInstance();
		if (dmHoatChat != null) {
			getInstance().setDmHoatChat(dmHoatChat);
		}
		DmNhaSanXuat dmNhaSanXuat = dmNhaSanXuatHome.getDefinedInstance();
		if (dmNhaSanXuat != null) {
			getInstance().setDmNhaSanXuat(dmNhaSanXuat);
		}
		DmPhanLoaiThuoc dmPhanLoaiThuocByDmphanloaithuocMaso = dmPhanLoaiThuocHome
				.getDefinedInstance();
		if (dmPhanLoaiThuocByDmphanloaithuocMaso != null) {
			getInstance().setDmPhanLoaiThuocByDmphanloaithuocMaso(
					dmPhanLoaiThuocByDmphanloaithuocMaso);
		}
		DmPhanLoaiThuoc dmPhanLoaiThuocByDmphanloaithuocMaso2 = dmPhanLoaiThuocHome
				.getDefinedInstance();
		if (dmPhanLoaiThuocByDmphanloaithuocMaso2 != null) {
			getInstance().setDmPhanLoaiThuocByDmphanloaithuocMaso2(
					dmPhanLoaiThuocByDmphanloaithuocMaso2);
		}
		DmPhanNhomThuoc dmPhanNhomThuocByDmphannhomthuocMaso = dmPhanNhomThuocHome
				.getDefinedInstance();
		if (dmPhanNhomThuocByDmphannhomthuocMaso != null) {
			getInstance().setDmPhanNhomThuocByDmphannhomthuocMaso(
					dmPhanNhomThuocByDmphannhomthuocMaso);
		}
		DmPhanNhomThuoc dmPhanNhomThuocByDmphannhomthuocMaso2 = dmPhanNhomThuocHome
				.getDefinedInstance();
		if (dmPhanNhomThuocByDmphannhomthuocMaso2 != null) {
			getInstance().setDmPhanNhomThuocByDmphannhomthuocMaso2(
					dmPhanNhomThuocByDmphannhomthuocMaso2);
		}
		DmQuocGia dmQuocGia = dmQuocGiaHome.getDefinedInstance();
		if (dmQuocGia != null) {
			getInstance().setDmQuocGia(dmQuocGia);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void checkName(){
		System.out.println("myTen:" + myTen);
		String instantTen = this.instance.getDmthuocTen();
		for (String each : listTemp){
			if(this.isManaged() == true){
				if((instantTen.toUpperCase()).equals(each.toUpperCase()) && 

!instantTen.toUpperCase().equals(myTen.toUpperCase())){
					this.instance.setDmthuocTen("");
					break;
				}
			}else{
				if((instantTen.toUpperCase()).equals(each.toUpperCase())){
					this.instance.setDmthuocTen("");
					break;
				}
			}						
		}		
	}

}
