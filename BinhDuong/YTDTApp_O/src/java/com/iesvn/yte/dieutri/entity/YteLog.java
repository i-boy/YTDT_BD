/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author i-boy
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "YTE_LOG")
@NamedQueries({})
public class YteLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YTE_LOG")
    @SequenceGenerator(name = "YTE_LOG", sequenceName = "YTE_LOG_SEQ", allocationSize = 1)
    @Column(name = "LOG_ID", nullable = false)
    private Long logId;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Column(name = "FORM")
    private String form;
    @Column(name = "OBJECT_ID")
    private String objectId;
    @Column(name = "LOG_STRING")
    private String logString;
    @Column(name = "LIST_DATA")
    private String listData;

    public YteLog() {
    }

    public YteLog(Long logId) {
        this.logId = logId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId must < 10 bytes
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getForm() {
        return form;
    }

    /**
     *
     * @param form name must < 100 bytes
     */
    public void setForm(String form) {
        this.form = form;
    }

    public String getObjectId() {
        return objectId;
    }

    /**
     *
     * @param objectId must < 20 bytes
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getLogString() {
        return logString;
    }

    public void setLogString(String logString) {
        if (logString != null && logString.length() > 505) {
            logString = logString.substring(0, 505) + "#...";
        }
        this.logString = logString;
    }

    public String getListData() {
        return listData;
    }

    public void setListData(String listData) {
        if (listData != null && listData.length() > 2040) {
            listData = listData.substring(0, 2040) + "#...";
        }
        this.listData = listData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YteLog)) {
            return false;
        }
        YteLog other = (YteLog) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.YteLog[logId=" + logId + "]";
    }

}
