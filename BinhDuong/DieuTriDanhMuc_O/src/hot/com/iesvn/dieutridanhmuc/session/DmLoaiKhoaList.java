package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmLoaiKhoaList")
public class DmLoaiKhoaList extends EntityQuery<DmLoaiKhoa> {

	private static final String EJBQL = "select dmLoaiKhoa from DmLoaiKhoa dmLoaiKhoa";

	private static final String[] RESTRICTIONS = {
			"lower(dmLoaiKhoa.dmloaikhoaMa) like concat('%',lower(#{dmLoaiKhoaList.dmLoaiKhoa.dmloaikhoaMa}),'%')",
			"lower(dmLoaiKhoa.dmloaikhoaTen) like concat('%',lower(#{dmLoaiKhoaList.dmLoaiKhoa.dmloaikhoaTen}),'%')", };

	private DmLoaiKhoa dmLoaiKhoa = new DmLoaiKhoa();

	public DmLoaiKhoaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmLoaiKhoa getDmLoaiKhoa() {
		return dmLoaiKhoa;
	}
}
