/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author root
 */
@Embeddable
public class DmTuyenDonViPK implements Serializable {
    @Column(name = "DMTUYEN_MASO", nullable = false)
    private int dmtuyenMaso;
    @Column(name = "DMDONVI_MASO", nullable = false)
    private int dmdonviMaso;

    public DmTuyenDonViPK() {
    }

    public DmTuyenDonViPK(int dmtuyenMaso, int dmdonviMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
        this.dmdonviMaso = dmdonviMaso;
    }

    public int getDmtuyenMaso() {
        return dmtuyenMaso;
    }

    public void setDmtuyenMaso(int dmtuyenMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
    }

    public int getDmdonviMaso() {
        return dmdonviMaso;
    }

    public void setDmdonviMaso(int dmdonviMaso) {
        this.dmdonviMaso = dmdonviMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) dmtuyenMaso;
        hash += (int) dmdonviMaso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTuyenDonViPK)) {
            return false;
        }
        DmTuyenDonViPK other = (DmTuyenDonViPK) object;
        if (this.dmtuyenMaso != other.dmtuyenMaso) {
            return false;
        }
        if (this.dmdonviMaso != other.dmdonviMaso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTuyenDonViPK[dmtuyenMaso=" + dmtuyenMaso + ", dmdonviMaso=" + dmdonviMaso + "]";
    }

}


