/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.YteSequenceInterface;

import com.iesvn.yte.dieutri.entity.YteSequence;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class YteSequenceDelegate {

    private YteSequenceInterface ytesequenceService;

    public static YteSequenceDelegate getInstance() {
        return new YteSequenceDelegate();
    }

    private YteSequenceInterface lookupService() {
        return (YteSequenceInterface) LookupServiceUtils.lookupService("YteSequenceFacade");
    }

    public void create(YteSequence yteSequence) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        ytesequenceService.create(yteSequence);
    }

    public void edit(YteSequence yteSequence) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        ytesequenceService.edit(yteSequence);
    }

    public void remove(YteSequence yteSequence) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        ytesequenceService.remove(yteSequence);
    }

    public YteSequence find(Object id) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.find(id);
    }

    public List<YteSequence> findAll() {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.findAll();
    }

    public java.lang.String formatMaPhieu(javax.persistence.EntityManager em, java.lang.String ma) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.formatMaPhieu(em, ma);
    }

    public java.lang.String formatMa(javax.persistence.EntityManager em, java.lang.String ma) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.formatMa(em, ma);
    }

    public java.lang.String getMa(javax.persistence.EntityManager em, int loaiMa) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.getMa(em, loaiMa);
    }

    public java.lang.String getMaPhieu(javax.persistence.EntityManager em, int loaiMa) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.getMaPhieu(em, loaiMa);
    }

    public String getMaDkKhamBenh() {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.getMaDkKhamBenh();
    }

    public String startMyTimer(long interval) {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.startMyTimer(interval);
    }

    public String resetMaPhieu() {
        if (ytesequenceService == null) {
            ytesequenceService = lookupService();
        }
        return ytesequenceService.resetMaPhieu();
    }
}


