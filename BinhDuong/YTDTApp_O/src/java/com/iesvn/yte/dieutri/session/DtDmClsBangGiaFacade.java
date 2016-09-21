/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.entity.DmKhoa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmClsBangGiaFacade implements DtDmClsBangGiaFacadeLocal, DtDmClsBangGiaFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private DtDmClsKhoaFacadeLocal objDtDmClsKhoaFacade;

    public void create(DtDmClsBangGia dtDmClsBangGia) {
        getEm().persist(dtDmClsBangGia);
    }

    public void edit(DtDmClsBangGia dtDmClsBangGia) {
        getEm().merge(dtDmClsBangGia);
    }

    public void remove(DtDmClsBangGia dtDmClsBangGia) {
        getEm().remove(getEm().merge(dtDmClsBangGia));
    }

    public DtDmClsBangGia find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.DtDmClsBangGia.class, id);
    }

    public List<DtDmClsBangGia> findAll() {
        return getEm().createQuery("select object(o) from DtDmClsBangGia as o").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<DtDmClsBangGia> getDtDmClsBangGiaByMaSoKhoa(Integer maSoKhoa) {

        List<DtDmClsKhoa> list = objDtDmClsKhoaFacade.findByMaSoKhoa(maSoKhoa);
        List listMa = new ArrayList();
        if (list == null || list.size() == 0) {
            System.out.println("------ Ma Khoa null");
            return null;
        } else {
            //StringBuffer sList = new StringBuffer("");
            try {
                for (int i = 0; i < list.size(); i++) {
                    listMa.add(list.get(i).getDtdmclsMaso().getDtdmclsbgMaso().intValue());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                System.out.println("-----ma so CLS bang gia:" + listMa.toString());
                StringBuffer sQuery = new StringBuffer("");
                sQuery.append(" select object(o) from DtDmClsBangGia as o ");
                sQuery.append(" WHERE o.dtdmclsbgMaso IN (");
                sQuery.append(listMa.toString().replace("[", "").replace("]", "") + " )");
                Query q;
                q = em.createQuery(sQuery.toString());
                List<DtDmClsBangGia> listDtDmClsBangGia = q.getResultList();
                return listDtDmClsBangGia;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<DtDmClsBangGia> findByAllConditions(String ma, String diengiai, Integer phanloai, boolean searchDaxoa) {
        String sql = "";

        sql = "select object(o) from  DtDmClsBangGia as o ";
        boolean bWhere = true;
        if (ma.trim().length() > 0) {
            sql += "where o.dtdmclsbgMa like ('%' || :ma || '%') ";
            bWhere = false;
        }
        if (diengiai.trim().length() > 0) {
            sql += (bWhere ? "where o.dtdmclsbgDiengiai like ('%' || :diengiai || '%') " : "and o.dtdmclsbgDiengiai like ('%' || :diengiai|| '%') ");
            bWhere = false;
        }
        if (phanloai != null) {
            if (phanloai.intValue() != 0) {
                sql += (bWhere ? "where o.dtdmclsbgPhanloai.dtdmclsMaso = :phanloai " : "and o.dtdmclsbgPhanloai.dtdmclsMaso = :phanloai ");
                bWhere = false;
            }
        }
        //phuc.lc 18-07-2011 : Fix bug 3363
        //sql += (bWhere ? "where o.dtdmclsbgChon = 1 " : "and o.dtdmclsbgChon = 1");
        //phuc.lc 05-08-2011 : Fix bug 3763 : 
        if (searchDaxoa) {
            sql += (bWhere ? "where o.dtdmclsbgChon = 0 " : "and o.dtdmclsbgChon = 0");
        } else {
            sql += (bWhere ? "where o.dtdmclsbgChon = 1 " : "and o.dtdmclsbgChon = 1");
        }
        sql += " order by o.dtdmclsbgMa, o.dtdmclsbgDiengiai ASC ";
        Query qry = em.createQuery(sql);
        if (ma.trim().length() > 0) {
            qry.setParameter("ma", ma);
        }
        if (diengiai.trim().length() > 0) {
            qry.setParameter("diengiai", diengiai);
        }
        if (phanloai != null) {
            if (phanloai.intValue() != 0) {
                qry.setParameter("phanloai", phanloai);
            }
        }
        return qry.getResultList();

    }
    // phuc.lc 11-01-2011 : Fix bug 2006

    public void create(DtDmClsBangGia dtDmClsBangGia, DmKhoa dmkhoa) {
        try {
            em.persist(dtDmClsBangGia);
            System.out.print(dtDmClsBangGia);
            // Luu thong tin cls khoa
            DtDmClsKhoa clsKhoa = new DtDmClsKhoa();
            clsKhoa.setDtdmclsMaso(dtDmClsBangGia);
            clsKhoa.setDmkhoaMaso(dmkhoa);
            clsKhoa.setDtdmclsKhoaChon(true);
            Date ngayCN = new Date();
            clsKhoa.setDtdmclsKhoaNgaygiocn(new Double(ngayCN.getTime()));
            em.persist(clsKhoa);
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }
    // phuc.lc 11-01-2011 : Fix bug 2006

    public void edit(DtDmClsBangGia dtDmClsBangGia, DtDmClsKhoa clsKhoa) {
        try {
            em.merge(dtDmClsBangGia);
            System.out.print(dtDmClsBangGia);
            // Luu thong tin cls khoa                        
            Date ngayCN = new Date();
            clsKhoa.setDtdmclsKhoaNgaygiocn(new Double(ngayCN.getTime()));
            em.merge(clsKhoa);
        } catch (Exception ex) {
            context.setRollbackOnly();
            ex.printStackTrace();
        }
    }

    public DtDmClsBangGia findByMa(String ma) {
        String sql = "select object(o) from  DtDmClsBangGia as o where o.dtdmclsbgMa = :ma  ";
        Query qry = em.createQuery(sql);
        qry.setParameter("ma", ma);
        List<DtDmClsBangGia> lstTemp = qry.getResultList();
        if (lstTemp.size() > 0) {
            return lstTemp.get(0);
        } else {
            return null;
        }
    }
}


