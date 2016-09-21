package com.iesvn.yte;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.delegate.DtDmBanKhamSequenceDelegate;

@Scope(ScopeType.SESSION)
@Name("CauHinhStt")
@Synchronized(timeout = 6000000)
public class CauHinhSttAction implements Serializable {
	private static Logger logger = Logger.getLogger(CauHinhSttAction.class);
	private static final long serialVersionUID = 10L;
	
	@Create
	public void init() {
		logger.debug("-----init()-----");
	}
	
	public void reset() {
		DtDmBanKhamSequenceDelegate bkSeq = DtDmBanKhamSequenceDelegate.getInstance();
		bkSeq.reset();
	}
}
