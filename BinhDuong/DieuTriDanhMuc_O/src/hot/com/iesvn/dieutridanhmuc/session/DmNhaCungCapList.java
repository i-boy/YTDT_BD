package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhaCungCapList")
public class DmNhaCungCapList extends EntityQuery<DmNhaCungCap> {

	private static final String EJBQL = "select dmNhaCungCap from DmNhaCungCap dmNhaCungCap";

	private static final String[] RESTRICTIONS = {
			"lower(dmNhaCungCap.dmnhacungcapDiachi) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapDiachi}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapDienthoai) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapDienthoai}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapFax) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapFax}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapMa) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapMa}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapMasothue) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapMasothue}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapPhanbiet) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapPhanbiet}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapPhanloai) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapPhanloai}),'%')",
			"lower(dmNhaCungCap.dmnhacungcapTen) like concat('%',lower(#{dmNhaCungCapList.dmNhaCungCap.dmnhacungcapTen}),'%')", };

	private DmNhaCungCap dmNhaCungCap = new DmNhaCungCap();

	public DmNhaCungCapList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhaCungCapList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmNhaCungCap getDmNhaCungCap() {
		return dmNhaCungCap;
	}
}
