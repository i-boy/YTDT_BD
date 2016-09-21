package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDoiTuongList")
public class DmDoiTuongList extends EntityQuery<DmDoiTuong> {

	private static final String EJBQL = "select dmDoiTuong from DmDoiTuong dmDoiTuong";

	private static final String[] RESTRICTIONS = {
			"lower(dmDoiTuong.dmdoituongBenhan) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongBenhan}),'%')",
			"lower(dmDoiTuong.dmdoituongMa) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongMa}),'%')",
			"lower(dmDoiTuong.dmdoituongMagom) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongMagom}),'%')",
			"lower(dmDoiTuong.dmdoituongMauphieu) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongMauphieu}),'%')",
			"lower(dmDoiTuong.dmdoituongTen) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongTen}),'%')",
			"lower(dmDoiTuong.dmdoituongTenbc) like concat('%',lower(#{dmDoiTuongList.dmDoiTuong.dmdoituongTenbc}),'%')", };

	private DmDoiTuong dmDoiTuong = new DmDoiTuong();

	public DmDoiTuongList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDoiTuongList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmDoiTuong getDmDoiTuong() {
		return dmDoiTuong;
	}
}
