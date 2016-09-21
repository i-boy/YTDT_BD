package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("donMienList")
public class DonMienList extends EntityQuery<DonMien> {

	private static final String EJBQL = "select donMien from DonMien donMien";

	private static final String[] RESTRICTIONS = {
			"lower(donMien.donmienMa) like concat('%',lower(#{donMienList.donMien.donmienMa}),'%')",
			"lower(donMien.donmienLydo) like concat('%',lower(#{donMienList.donMien.donmienLydo}),'%')",
			"lower(donMien.donmienMienkhac) like concat('%',lower(#{donMienList.donMien.donmienMienkhac}),'%')",
			"lower(donMien.donmienStatus) like concat('%',lower(#{donMienList.donMien.donmienStatus}),'%')",
			"lower(donMien.hsbaSovaovien) like concat('%',lower(#{donMienList.donMien.hsbaSovaovien}),'%')", };

	private DonMien donMien = new DonMien();

	public DonMienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DonMien getDonMien() {
		return donMien;
	}
}
