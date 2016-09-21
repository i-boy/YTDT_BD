/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.DtDmBanKhamSequence;
import com.iesvn.yte.dieutri.intf.DtDmBanKhamSequenceInterface;
import com.iesvn.yte.locator.LookupServiceUtils;
import java.util.List;

/**
 *
 * @author root
 */
public class DtDmBanKhamSequenceDelegate {
    private DtDmBanKhamSequenceInterface dtdmbankhamsequenceService;

    public static DtDmBanKhamSequenceDelegate getInstance() {
        return new DtDmBanKhamSequenceDelegate();
    }

    private DtDmBanKhamSequenceInterface lookupService() {
        return (DtDmBanKhamSequenceInterface) LookupServiceUtils.lookupService("DtDmBanKhamSequenceFacade");
    }
    
    
    public void create(DtDmBanKhamSequence dtDmBanKhamSequence) {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        dtdmbankhamsequenceService.create(dtDmBanKhamSequence);
    }

    public void edit(DtDmBanKhamSequence dtDmBanKhamSequence) {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        dtdmbankhamsequenceService.edit(dtDmBanKhamSequence);
    }

    public void remove(DtDmBanKhamSequence dtDmBanKhamSequence) {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        dtdmbankhamsequenceService.remove(dtDmBanKhamSequence);
    }

    public DtDmBanKhamSequence find(Object id) {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        return dtdmbankhamsequenceService.find(id);
    }

    public List<DtDmBanKhamSequence> findAll() {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        return dtdmbankhamsequenceService.findAll();
    }
    
    public int reset() {
        if (dtdmbankhamsequenceService == null) {
            dtdmbankhamsequenceService = lookupService();
        }
        return dtdmbankhamsequenceService.reset();
    }
    
}
