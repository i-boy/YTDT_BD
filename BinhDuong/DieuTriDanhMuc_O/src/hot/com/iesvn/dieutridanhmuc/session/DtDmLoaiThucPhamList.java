package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLoaiThucPhamList")
public class DtDmLoaiThucPhamList extends EntityQuery<DtDmLoaiThucPham> {

	private static final String EJBQL = "select dtDmLoaiThucPham from DtDmLoaiThucPham dtDmLoaiThucPham";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLoaiThucPham.dtdmltpMa) like concat('%',lower(#{dtDmLoaiThucPhamList.dtDmLoaiThucPham.dtdmltpMa}),'%')",
			"lower(dtDmLoaiThucPham.dtdmltpTen) like concat('%',lower(#{dtDmLoaiThucPhamList.dtDmLoaiThucPham.dtdmltpTen}),'%')", };

	private DtDmLoaiThucPham dtDmLoaiThucPham = new DtDmLoaiThucPham();

	public DtDmLoaiThucPhamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLoaiThucPham getDtDmLoaiThucPham() {
		return dtDmLoaiThucPham;
	}
}
