package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

@Name("B121_7_Giaychuyenviensotxuathuyet")
@Scope(CONVERSATION)
@Synchronized(timeout = 6000000)
public class GiayChuyenVienSotXuatHuyetAction implements Serializable {

	private static Logger log = Logger.getLogger(GiayChuyenVienSotXuatHuyetAction.class);

	@In(required = false)
	@Out(required = false)
	private String goToGiayChuyenVienSotXuatHuyet;

	@Begin(nested = true)
	@Factory("goToGiayChuyenVienSotXuatHuyet")
	public void init() throws Exception {
		log.info("***Starting init ***");

		log.info("***Finished init ***");
	}

	@End
	public void end() {

	}

}
