/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_NOITRU_YHCT")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findAll", query = "SELECT h FROM HsbaChiTietNoitruYhct h"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMa", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMa = :hsbactnoitruYhctMa"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctKhoakhambenh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctKhoakhambenh = :hsbactnoitruYhctKhoakhambenh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChandoannguyennhan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChandoannguyennhan = :hsbactnoitruYhctChandoannguyennhan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChandoanbatchuong", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChandoanbatchuong = :hsbactnoitruYhctChandoanbatchuong"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBenhdanh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBenhdanh = :hsbactnoitruYhctBenhdanh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLydovaov", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLydovaov = :hsbactnoitruYhctLydovaov"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctQtbenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctQtbenhly = :hsbactnoitruYhctQtbenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTiensubenhbt", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTiensubenhbt = :hsbactnoitruYhctTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTiensubenhgd", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTiensubenhgd = :hsbactnoitruYhctTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThuocla", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThuocla = :hsbactnoitruYhctThuocla"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctRuou", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctRuou = :hsbactnoitruYhctRuou"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMatuy", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMatuy = :hsbactnoitruYhctMatuy"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDiung", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDiung = :hsbactnoitruYhctDiung"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctKhac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctKhac = :hsbactnoitruYhctKhac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNoitiet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNoitiet = :hsbactnoitruYhctNoitiet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDinhduong", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDinhduong = :hsbactnoitruYhctDinhduong"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThankinh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThankinh = :hsbactnoitruYhctThankinh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMat", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMat = :hsbactnoitruYhctMat"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTmh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTmh = :hsbactnoitruYhctTmh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctRhm", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctRhm = :hsbactnoitruYhctRhm"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTuanhoan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTuanhoan = :hsbactnoitruYhctTuanhoan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHohap", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHohap = :hsbactnoitruYhctHohap"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTieuhoa", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTieuhoa = :hsbactnoitruYhctTieuhoa"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDavamo", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDavamo = :hsbactnoitruYhctDavamo"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctCoxuongkhop", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctCoxuongkhop = :hsbactnoitruYhctCoxuongkhop"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTietnieu", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTietnieu = :hsbactnoitruYhctTietnieu"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSinhduc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSinhduc = :hsbactnoitruYhctSinhduc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctCoquankhac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctCoquankhac = :hsbactnoitruYhctCoquankhac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChitietbenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChitietbenhly = :hsbactnoitruYhctChitietbenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHuyethoc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHuyethoc = :hsbactnoitruYhctHuyethoc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoasinh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoasinh = :hsbactnoitruYhctHoasinh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctVisinh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctVisinh = :hsbactnoitruYhctVisinh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXquang", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXquang = :hsbactnoitruYhctXquang"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSieuam", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSieuam = :hsbactnoitruYhctSieuam"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDientim", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDientim = :hsbactnoitruYhctDientim"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNoisoi", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNoisoi = :hsbactnoitruYhctNoisoi"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctGiaiphaubenh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctGiaiphaubenh = :hsbactnoitruYhctGiaiphaubenh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXetnghiemkhac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXetnghiemkhac = :hsbactnoitruYhctXetnghiemkhac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHuyethockq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHuyethockq = :hsbactnoitruYhctHuyethockq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoasinhkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoasinhkq = :hsbactnoitruYhctHoasinhkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctVisinhkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctVisinhkq = :hsbactnoitruYhctVisinhkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXquangkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXquangkq = :hsbactnoitruYhctXquangkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSieuamkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSieuamkq = :hsbactnoitruYhctSieuamkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDientimkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDientimkq = :hsbactnoitruYhctDientimkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNoisoikq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNoisoikq = :hsbactnoitruYhctNoisoikq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctGiaiphaubenhkq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctGiaiphaubenhkq = :hsbactnoitruYhctGiaiphaubenhkq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXetnghiemkhackq", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXetnghiemkhackq = :hsbactnoitruYhctXetnghiemkhackq"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTomtattrieuchung", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTomtattrieuchung = :hsbactnoitruYhctTomtattrieuchung"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChandoanphanbiet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChandoanphanbiet = :hsbactnoitruYhctChandoanphanbiet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctVongchanHinhthai", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctVongchanHinhthai = :hsbactnoitruYhctVongchanHinhthai"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctVongchanMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctVongchanMota = :hsbactnoitruYhctVongchanMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThansacTinhtao", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThansacTinhtao = :hsbactnoitruYhctThansacTinhtao"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThansacSac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThansacSac = :hsbactnoitruYhctThansacSac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThansacTrach", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThansacTrach = :hsbactnoitruYhctThansacTrach"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctThansacMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctThansacMota = :hsbactnoitruYhctThansacMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLuoiChatluoi", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLuoiChatluoi = :hsbactnoitruYhctLuoiChatluoi"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLuoiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLuoiMota = :hsbactnoitruYhctLuoiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLuoiSac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLuoiSac = :hsbactnoitruYhctLuoiSac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSacluoiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSacluoiMota = :hsbactnoitruYhctSacluoiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctReuluoi", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctReuluoi = :hsbactnoitruYhctReuluoi"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctReuluoiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctReuluoiMota = :hsbactnoitruYhctReuluoiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBophanbibenh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBophanbibenh = :hsbactnoitruYhctBophanbibenh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTiengnoi", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTiengnoi = :hsbactnoitruYhctTiengnoi"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTiengnoiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTiengnoiMota = :hsbactnoitruYhctTiengnoiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoitho", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoitho = :hsbactnoitruYhctHoitho"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoithoMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoithoMota = :hsbactnoitruYhctHoithoMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHo", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHo = :hsbactnoitruYhctHo"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoTinhchat", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoTinhchat = :hsbactnoitruYhctHoTinhchat"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoMota = :hsbactnoitruYhctHoMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctOnac", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctOnac = :hsbactnoitruYhctOnac"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctOnacMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctOnacMota = :hsbactnoitruYhctOnacMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMui", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMui = :hsbactnoitruYhctMui"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMuiTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMuiTc = :hsbactnoitruYhctMuiTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMuiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMuiMota = :hsbactnoitruYhctMuiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoithocomui", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoithocomui = :hsbactnoitruYhctHoithocomui"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoithocomuiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoithocomuiMota = :hsbactnoitruYhctHoithocomuiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHoithocomuiTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHoithocomuiTc = :hsbactnoitruYhctHoithocomuiTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctCohannhiet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctCohannhiet = :hsbactnoitruYhctCohannhiet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHannhiet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHannhiet = :hsbactnoitruYhctHannhiet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHannhietMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHannhietMota = :hsbactnoitruYhctHannhietMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctCobenhthaydoitheomua", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctCobenhthaydoitheomua = :hsbactnoitruYhctCobenhthaydoitheomua"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBenhthaydoitheomuaMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBenhthaydoitheomuaMota = :hsbactnoitruYhctBenhthaydoitheomuaMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMohoi", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMohoi = :hsbactnoitruYhctMohoi"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMohoiMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMohoiMota = :hsbactnoitruYhctMohoiMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaumatcobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaumatcobenhly = :hsbactnoitruYhctDaumatcobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaumatbenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaumatbenhly = :hsbactnoitruYhctDaumatbenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaumatMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaumatMota = :hsbactnoitruYhctDaumatMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLungcobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLungcobenhly = :hsbactnoitruYhctLungcobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctLungMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctLungMota = :hsbactnoitruYhctLungMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBungnguccobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBungnguccobenhly = :hsbactnoitruYhctBungnguccobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBungnguc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBungnguc = :hsbactnoitruYhctBungnguc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBungngucMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBungngucMota = :hsbactnoitruYhctBungngucMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChantaycobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChantaycobenhly = :hsbactnoitruYhctChantaycobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChantayMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChantayMota = :hsbactnoitruYhctChantayMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctAncobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctAncobenhly = :hsbactnoitruYhctAncobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctAnTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctAnTc = :hsbactnoitruYhctAnTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctAnMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctAnMota = :hsbactnoitruYhctAnMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctUongcobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctUongcobenhly = :hsbactnoitruYhctUongcobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctUongTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctUongTc = :hsbactnoitruYhctUongTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctUongMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctUongMota = :hsbactnoitruYhctUongMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaitieutiencobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaitieutiencobenhly = :hsbactnoitruYhctDaitieutiencobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTieutienTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTieutienTc = :hsbactnoitruYhctTieutienTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaitienTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaitienTc = :hsbactnoitruYhctDaitienTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaitieutienMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaitieutienMota = :hsbactnoitruYhctDaitieutienMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNgucobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNgucobenhly = :hsbactnoitruYhctNgucobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNguTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNguTc = :hsbactnoitruYhctNguTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctNguMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctNguMota = :hsbactnoitruYhctNguMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctKinhnguyetcobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctKinhnguyetcobenhly = :hsbactnoitruYhctKinhnguyetcobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctRoiloankinhnguyet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctRoiloankinhnguyet = :hsbactnoitruYhctRoiloankinhnguyet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDaubungkinh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDaubungkinh = :hsbactnoitruYhctDaubungkinh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDoihacobenhly", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDoihacobenhly = :hsbactnoitruYhctDoihacobenhly"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDoihaTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDoihaTc = :hsbactnoitruYhctDoihaTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDoihaMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDoihaMota = :hsbactnoitruYhctDoihaMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSinhduccoroiloan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSinhduccoroiloan = :hsbactnoitruYhctSinhduccoroiloan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSinhducTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSinhducTc = :hsbactnoitruYhctSinhducTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctSinhducMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctSinhducMota = :hsbactnoitruYhctSinhducMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieukienxuathien", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieukienxuathien = :hsbactnoitruYhctDieukienxuathien"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieukienxuathienMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieukienxuathienMota = :hsbactnoitruYhctDieukienxuathienMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctConhucTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctConhucTc = :hsbactnoitruYhctConhucTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXucchanTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXucchanTc = :hsbactnoitruYhctXucchanTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBungTc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBungTc = :hsbactnoitruYhctBungTc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXucchanMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXucchanMota = :hsbactnoitruYhctXucchanMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtaytraiThon", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtaytraiThon = :hsbactnoitruYhctMachtaytraiThon"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtayphaiThon", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtayphaiThon = :hsbactnoitruYhctMachtayphaiThon"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtaytraiQuan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtaytraiQuan = :hsbactnoitruYhctMachtaytraiQuan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtaytraiXich", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtaytraiXich = :hsbactnoitruYhctMachtaytraiXich"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtayphaiQuan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtayphaiQuan = :hsbactnoitruYhctMachtayphaiQuan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachtayphaiXich", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachtayphaiXich = :hsbactnoitruYhctMachtayphaiXich"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTongkhambenphai", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTongkhambenphai = :hsbactnoitruYhctTongkhambenphai"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTongkhambentrai", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTongkhambentrai = :hsbactnoitruYhctTongkhambentrai"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctMachchanMota", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctMachchanMota = :hsbactnoitruYhctMachchanMota"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBienchungluantri", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBienchungluantri = :hsbactnoitruYhctBienchungluantri"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctBatcuong", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctBatcuong = :hsbactnoitruYhctBatcuong"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTangphu", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTangphu = :hsbactnoitruYhctTangphu"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieutridonthuanyhct", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieutridonthuanyhct = :hsbactnoitruYhctDieutridonthuanyhct"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieutrikethopyhhd", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieutrikethopyhhd = :hsbactnoitruYhctDieutrikethopyhhd"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctPhepchua", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctPhepchua = :hsbactnoitruYhctPhepchua"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctPhuongthuoc", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctPhuongthuoc = :hsbactnoitruYhctPhuongthuoc"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctPhuonghuyet", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctPhuonghuyet = :hsbactnoitruYhctPhuonghuyet"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctXoabop", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctXoabop = :hsbactnoitruYhctXoabop"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChedoan", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChedoan = :hsbactnoitruYhctChedoan"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChedoholy", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChedoholy = :hsbactnoitruYhctChedoholy"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTienluong", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTienluong = :hsbactnoitruYhctTienluong"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctQtbldbcls", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctQtbldbcls = :hsbactnoitruYhctQtbldbcls"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctKetquaclschinh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctKetquaclschinh = :hsbactnoitruYhctKetquaclschinh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctKetquagiaiphaubenh", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctKetquagiaiphaubenh = :hsbactnoitruYhctKetquagiaiphaubenh"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieutriYhhd", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieutriYhhd = :hsbactnoitruYhctDieutriYhhd"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctDieutriYhct", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctDieutriYhct = :hsbactnoitruYhctDieutriYhct"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChandoanravienYhhd", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChandoanravienYhhd = :hsbactnoitruYhctChandoanravienYhhd"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctChandoanravienYhct", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctChandoanravienYhct = :hsbactnoitruYhctChandoanravienYhct"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctTinhtrangnbravien", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctTinhtrangnbravien = :hsbactnoitruYhctTinhtrangnbravien"),
    @NamedQuery(name = "HsbaChiTietNoitruYhct.findByHsbactnoitruYhctHdtccdtt", query = "SELECT h FROM HsbaChiTietNoitruYhct h WHERE h.hsbactnoitruYhctHdtccdtt = :hsbactnoitruYhctHdtccdtt")})
public class HsbaChiTietNoitruYhct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_NOITRU_YHCT")
    @SequenceGenerator(name = "HSBA_CHI_TIET_NOITRU_YHCT", sequenceName = "HSBA_CHI_TIET_NOITRU_YHCT_HSBA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTNOITRU_YHCT_MA")
    private Integer hsbactnoitruYhctMa;
    @Column(name = "HSBACTNOITRU_YHCT_KHOAKHAMBENH")
    private String hsbactnoitruYhctKhoakhambenh;
    @Column(name = "HSBACTNOITRU_YHCT_CDNGUYENNHAN")
    private String hsbactnoitruYhctChandoannguyennhan;
    @Column(name = "HSBACTNOITRU_YHCT_CDBATCHUONG")
    private String hsbactnoitruYhctChandoanbatchuong;
    @Column(name = "HSBACTNOITRU_YHCT_BENHDANH")
    private String hsbactnoitruYhctBenhdanh;
    @Column(name = "HSBACTNOITRU_YHCT_LYDOVAOV")
    private String hsbactnoitruYhctLydovaov;
    @Column(name = "HSBACTNOITRU_YHCT_QTBENHLY")
    private String hsbactnoitruYhctQtbenhly;
    @Column(name = "HSBACTNOITRU_YHCT_TIENSUBENHBT")
    private String hsbactnoitruYhctTiensubenhbt;
    @Column(name = "HSBACTNOITRU_YHCT_TIENSUBENHGD")
    private String hsbactnoitruYhctTiensubenhgd;
    @Column(name = "HSBACTNOITRU_YHCT_THUOCLA")
    private Boolean hsbactnoitruYhctThuocla;
    @Column(name = "HSBACTNOITRU_YHCT_RUOU")
    private Boolean hsbactnoitruYhctRuou;
    @Column(name = "HSBACTNOITRU_YHCT_MATUY")
    private Boolean hsbactnoitruYhctMatuy;
    @Column(name = "HSBACTNOITRU_YHCT_DIUNG")
    private Boolean hsbactnoitruYhctDiung;
    @Column(name = "HSBACTNOITRU_YHCT_KHAC")
    private Boolean hsbactnoitruYhctKhac;
    @Column(name = "HSBACTNOITRU_YHCT_NOITIET")
    private String hsbactnoitruYhctNoitiet;
    @Column(name = "HSBACTNOITRU_YHCT_DINHDUONG")
    private String hsbactnoitruYhctDinhduong;
    @Column(name = "HSBACTNOITRU_YHCT_THANKINH")
    private String hsbactnoitruYhctThankinh;
    @Column(name = "HSBACTNOITRU_YHCT_MAT")
    private String hsbactnoitruYhctMat;
    @Column(name = "HSBACTNOITRU_YHCT_TMH")
    private String hsbactnoitruYhctTmh;
    @Column(name = "HSBACTNOITRU_YHCT_RHM")
    private String hsbactnoitruYhctRhm;
    @Column(name = "HSBACTNOITRU_YHCT_TUANHOAN")
    private String hsbactnoitruYhctTuanhoan;
    @Column(name = "HSBACTNOITRU_YHCT_HOHAP")
    private String hsbactnoitruYhctHohap;
    @Column(name = "HSBACTNOITRU_YHCT_TIEUHOA")
    private String hsbactnoitruYhctTieuhoa;
    @Column(name = "HSBACTNOITRU_YHCT_DAVAMO")
    private String hsbactnoitruYhctDavamo;
    @Column(name = "HSBACTNOITRU_YHCT_COXUONGKHOP")
    private String hsbactnoitruYhctCoxuongkhop;
    @Column(name = "HSBACTNOITRU_YHCT_TIETNIEU")
    private String hsbactnoitruYhctTietnieu;
    @Column(name = "HSBACTNOITRU_YHCT_SINHDUC")
    private String hsbactnoitruYhctSinhduc;
    @Column(name = "HSBACTNOITRU_YHCT_COQUANKHAC")
    private String hsbactnoitruYhctCoquankhac;
    @Column(name = "HSBACTNOITRU_YHCT_CTIETBENHLY")
    private String hsbactnoitruYhctChitietbenhly;
    @Column(name = "HSBACTNOITRU_YHCT_HUYETHOC")
    private String hsbactnoitruYhctHuyethoc;
    @Column(name = "HSBACTNOITRU_YHCT_HOASINH")
    private String hsbactnoitruYhctHoasinh;
    @Column(name = "HSBACTNOITRU_YHCT_VISINH")
    private String hsbactnoitruYhctVisinh;
    @Column(name = "HSBACTNOITRU_YHCT_XQUANG")
    private String hsbactnoitruYhctXquang;
    @Column(name = "HSBACTNOITRU_YHCT_SIEUAM")
    private String hsbactnoitruYhctSieuam;
    @Column(name = "HSBACTNOITRU_YHCT_DIENTIM")
    private String hsbactnoitruYhctDientim;
    @Column(name = "HSBACTNOITRU_YHCT_NOISOI")
    private String hsbactnoitruYhctNoisoi;
    @Column(name = "HSBACTNOITRU_YHCT_GIAIPHAUBENH")
    private String hsbactnoitruYhctGiaiphaubenh;
    @Column(name = "HSBACTNOITRU_YHCT_XNGHIEMKHAC")
    private String hsbactnoitruYhctXetnghiemkhac;
    @Column(name = "HSBACTNOITRU_YHCT_HUYETHOCKQ")
    private String hsbactnoitruYhctHuyethockq;
    @Column(name = "HSBACTNOITRU_YHCT_HOASINHKQ")
    private String hsbactnoitruYhctHoasinhkq;
    @Column(name = "HSBACTNOITRU_YHCT_VISINHKQ")
    private String hsbactnoitruYhctVisinhkq;
    @Column(name = "HSBACTNOITRU_YHCT_XQUANGKQ")
    private String hsbactnoitruYhctXquangkq;
    @Column(name = "HSBACTNOITRU_YHCT_SIEUAMKQ")
    private String hsbactnoitruYhctSieuamkq;
    @Column(name = "HSBACTNOITRU_YHCT_DIENTIMKQ")
    private String hsbactnoitruYhctDientimkq;
    @Column(name = "HSBACTNOITRU_YHCT_NOISOIKQ")
    private String hsbactnoitruYhctNoisoikq;
    @Column(name = "HSBACTNOITRU_YHCT_GPHAUBENHKQ")
    private String hsbactnoitruYhctGiaiphaubenhkq;
    @Column(name = "HSBACTNOITRU_YHCT_XNKHACKQ")
    private String hsbactnoitruYhctXetnghiemkhackq;
    @Column(name = "HSBACTNOITRU_YHCT_TTTRIEUCHUNG")
    private String hsbactnoitruYhctTomtattrieuchung;
    @Column(name = "HSBACTNOITRU_YHCT_CDPHANBIET")
    private String hsbactnoitruYhctChandoanphanbiet;
    @Column(name = "HSBACTNOITRU_YHCT_VCHAN_HTHAI")
    private String hsbactnoitruYhctVongchanHinhthai;
    @Column(name = "HSBACTNOITRU_YHCT_VCHAN_MOTA")
    private String hsbactnoitruYhctVongchanMota;
    @Column(name = "HSBACTNOITRU_YHCT_THANSACTTAO")
    private Boolean hsbactnoitruYhctThansacTinhtao;
    @Column(name = "HSBACTNOITRU_YHCT_THANSAC_SAC")
    private String hsbactnoitruYhctThansacSac;
    @Column(name = "HSBACTNOITRU_YHCT_THANSACTRACH")
    private String hsbactnoitruYhctThansacTrach;
    @Column(name = "HSBACTNOITRU_YHCT_THANSAC_MOTA")
    private String hsbactnoitruYhctThansacMota;
    @Column(name = "HSBACTNOITRU_YHCT_LUOI_CHLUOI")
    private String hsbactnoitruYhctLuoiChatluoi;
    @Column(name = "HSBACTNOITRU_YHCT_LUOI_MOTA")
    private String hsbactnoitruYhctLuoiMota;
    @Column(name = "HSBACTNOITRU_YHCT_LUOI_SAC")
    private String hsbactnoitruYhctLuoiSac;
    @Column(name = "HSBACTNOITRU_YHCT_SACLUOI_MOTA")
    private String hsbactnoitruYhctSacluoiMota;
    @Column(name = "HSBACTNOITRU_YHCT_REULUOI")
    private String hsbactnoitruYhctReuluoi;
    @Column(name = "HSBACTNOITRU_YHCT_REULUOI_MOTA")
    private String hsbactnoitruYhctReuluoiMota;
    @Column(name = "HSBACTNOITRU_YHCT_BOPHANBIBENH")
    private String hsbactnoitruYhctBophanbibenh;
    @Column(name = "HSBACTNOITRU_YHCT_TIENGNOI")
    private String hsbactnoitruYhctTiengnoi;
    @Column(name = "HSBACTNOITRU_YHCT_TIENGNOI_MTA")
    private String hsbactnoitruYhctTiengnoiMota;
    @Column(name = "HSBACTNOITRU_YHCT_HOITHO")
    private String hsbactnoitruYhctHoitho;
    @Column(name = "HSBACTNOITRU_YHCT_HOITHO_MOTA")
    private String hsbactnoitruYhctHoithoMota;
    @Column(name = "HSBACTNOITRU_YHCT_HO")
    private Boolean hsbactnoitruYhctHo;
    @Column(name = "HSBACTNOITRU_YHCT_HO_TINHCHAT")
    private String hsbactnoitruYhctHoTinhchat;
    @Column(name = "HSBACTNOITRU_YHCT_HO_MOTA")
    private String hsbactnoitruYhctHoMota;
    @Column(name = "HSBACTNOITRU_YHCT_ONAC")
    private Boolean hsbactnoitruYhctOnac;
    @Column(name = "HSBACTNOITRU_YHCT_ONAC_MOTA")
    private String hsbactnoitruYhctOnacMota;
    @Column(name = "HSBACTNOITRU_YHCT_MUI")
    private Boolean hsbactnoitruYhctMui;
    @Column(name = "HSBACTNOITRU_YHCT_MUI_TC")
    private String hsbactnoitruYhctMuiTc;
    @Column(name = "HSBACTNOITRU_YHCT_MUI_MOTA")
    private String hsbactnoitruYhctMuiMota;
    @Column(name = "HSBACTNOITRU_YHCT_HOITHOCOMUI")
    private Boolean hsbactnoitruYhctHoithocomui;
    @Column(name = "HSBACTNOITRU_YHCT_HTHOCOMUI_MT")
    private String hsbactnoitruYhctHoithocomuiMota;
    @Column(name = "HSBACTNOITRU_YHCT_HTHOCOMUI_TC")
    private String hsbactnoitruYhctHoithocomuiTc;
    @Column(name = "HSBACTNOITRU_YHCT_COHANNHIET")
    private Boolean hsbactnoitruYhctCohannhiet;
    @Column(name = "HSBACTNOITRU_YHCT_HANNHIET")
    private String hsbactnoitruYhctHannhiet;
    @Column(name = "HSBACTNOITRU_YHCT_HANNHIET_MTA")
    private String hsbactnoitruYhctHannhietMota;
    @Column(name = "HSBACTNOITRU_YHCT_COBENHTDTMUA")
    private Boolean hsbactnoitruYhctCobenhthaydoitheomua;
    @Column(name = "HSBACTNOITRU_YHCT_BTDTMUA_MOTA")
    private String hsbactnoitruYhctBenhthaydoitheomuaMota;
    @Column(name = "HSBACTNOITRU_YHCT_MOHOI")
    private String hsbactnoitruYhctMohoi;
    @Column(name = "HSBACTNOITRU_YHCT_MOHOI_MOTA")
    private String hsbactnoitruYhctMohoiMota;
    @Column(name = "HSBACTNOITRU_YHCT_DAUMATCOBLY")
    private Boolean hsbactnoitruYhctDaumatcobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_DAUMATBENHLY")
    private String hsbactnoitruYhctDaumatbenhly;
    @Column(name = "HSBACTNOITRU_YHCT_DAUMAT_MOTA")
    private String hsbactnoitruYhctDaumatMota;
    @Column(name = "HSBACTNOITRU_YHCT_LUNGCOBENHLY")
    private Boolean hsbactnoitruYhctLungcobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_LUNG_MOTA")
    private String hsbactnoitruYhctLungMota;
    @Column(name = "HSBACTNOITRU_YHCT_BNGNGUCCOBLY")
    private Boolean hsbactnoitruYhctBungnguccobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_BUNGNGUC")
    private String hsbactnoitruYhctBungnguc;
    @Column(name = "HSBACTNOITRU_YHCT_BNGNGUC_MOTA")
    private String hsbactnoitruYhctBungngucMota;
    @Column(name = "HSBACTNOITRU_YHCT_CHANTAYCOBLY")
    private Boolean hsbactnoitruYhctChantaycobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_CHANTAY_MOTA")
    private String hsbactnoitruYhctChantayMota;
    @Column(name = "HSBACTNOITRU_YHCT_ANCOBENHLY")
    private Boolean hsbactnoitruYhctAncobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_AN_TC")
    private String hsbactnoitruYhctAnTc;
    @Column(name = "HSBACTNOITRU_YHCT_AN_MOTA")
    private String hsbactnoitruYhctAnMota;
    @Column(name = "HSBACTNOITRU_YHCT_UONGCOBENHLY")
    private Boolean hsbactnoitruYhctUongcobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_UONG_TC")
    private String hsbactnoitruYhctUongTc;
    @Column(name = "HSBACTNOITRU_YHCT_UONG_MOTA")
    private String hsbactnoitruYhctUongMota;
    @Column(name = "HSBACTNOITRU_YHCT_DTTIENCOBLY")
    private Boolean hsbactnoitruYhctDaitieutiencobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_TIEUTIEN_TC")
    private String hsbactnoitruYhctTieutienTc;
    @Column(name = "HSBACTNOITRU_YHCT_DAITIEN_TC")
    private String hsbactnoitruYhctDaitienTc;
    @Column(name = "HSBACTNOITRU_YHCT_DTTIEN_MOTA")
    private String hsbactnoitruYhctDaitieutienMota;
    @Column(name = "HSBACTNOITRU_YHCT_NGUCOBENHLY")
    private Boolean hsbactnoitruYhctNgucobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_NGU_TC")
    private String hsbactnoitruYhctNguTc;
    @Column(name = "HSBACTNOITRU_YHCT_NGU_MOTA")
    private String hsbactnoitruYhctNguMota;
    @Column(name = "HSBACTNOITRU_YHCT_KNGUYETCOBLY")
    private Boolean hsbactnoitruYhctKinhnguyetcobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_RLKINHNGUYET")
    private String hsbactnoitruYhctRoiloankinhnguyet;
    @Column(name = "HSBACTNOITRU_YHCT_DAUBUNGKINH")
    private String hsbactnoitruYhctDaubungkinh;
    @Column(name = "HSBACTNOITRU_YHCT_DOIHACOBLY")
    private Boolean hsbactnoitruYhctDoihacobenhly;
    @Column(name = "HSBACTNOITRU_YHCT_DOIHA_TC")
    private String hsbactnoitruYhctDoihaTc;
    @Column(name = "HSBACTNOITRU_YHCT_DOIHA_MOTA")
    private String hsbactnoitruYhctDoihaMota;
    @Column(name = "HSBACTNOITRU_YHCT_SDCOROILOAN")
    private Boolean hsbactnoitruYhctSinhduccoroiloan;
    @Column(name = "HSBACTNOITRU_YHCT_SINHDUC_TC")
    private String hsbactnoitruYhctSinhducTc;
    @Column(name = "HSBACTNOITRU_YHCT_SINHDUC_MOTA")
    private String hsbactnoitruYhctSinhducMota;
    @Column(name = "HSBACTNOITRU_YHCT_DKXUATHIEN")
    private String hsbactnoitruYhctDieukienxuathien;
    @Column(name = "HSBACTNOITRU_YHCT_DKXUATHIENMT")
    private String hsbactnoitruYhctDieukienxuathienMota;
    @Column(name = "HSBACTNOITRU_YHCT_CONHUC_TC")
    private String hsbactnoitruYhctConhucTc;
    @Column(name = "HSBACTNOITRU_YHCT_XUCCHAN_TC")
    private String hsbactnoitruYhctXucchanTc;
    @Column(name = "HSBACTNOITRU_YHCT_BUNG_TC")
    private String hsbactnoitruYhctBungTc;
    @Column(name = "HSBACTNOITRU_YHCT_XUCCHAN_MOTA")
    private String hsbactnoitruYhctXucchanMota;
    @Column(name = "HSBACTNOITRU_YHCT_MTTRAI_THON")
    private String hsbactnoitruYhctMachtaytraiThon;
    @Column(name = "HSBACTNOITRU_YHCT_MTPHAI_THON")
    private String hsbactnoitruYhctMachtayphaiThon;
    @Column(name = "HSBACTNOITRU_YHCT_MTTRAI_QUAN")
    private String hsbactnoitruYhctMachtaytraiQuan;
    @Column(name = "HSBACTNOITRU_YHCT_MTTRAI_XICH")
    private String hsbactnoitruYhctMachtaytraiXich;
    @Column(name = "HSBACTNOITRU_YHCT_MTPHAI_QUAN")
    private String hsbactnoitruYhctMachtayphaiQuan;
    @Column(name = "HSBACTNOITRU_YHCT_MTPHAI_XICH")
    private String hsbactnoitruYhctMachtayphaiXich;
    @Column(name = "HSBACTNOITRU_YHCT_TKHAMBENPHAI")
    private String hsbactnoitruYhctTongkhambenphai;
    @Column(name = "HSBACTNOITRU_YHCT_TKHAMBENTRAI")
    private String hsbactnoitruYhctTongkhambentrai;
    @Column(name = "HSBACTNOITRU_YHCT_MACHCHAN_MTA")
    private String hsbactnoitruYhctMachchanMota;
    @Column(name = "HSBACTNOITRU_YHCT_BCLUANTRI")
    private String hsbactnoitruYhctBienchungluantri;
    @Column(name = "HSBACTNOITRU_YHCT_BATCUONG")
    private String hsbactnoitruYhctBatcuong;
    @Column(name = "HSBACTNOITRU_YHCT_TANGPHU")
    private String hsbactnoitruYhctTangphu;
    @Column(name = "HSBACTNOITRU_YHCT_NGUYENNHAN")
    private String hsbactnoitruYhctNguyennhan;
    @Column(name = "HSBACTNOITRU_YHCT_DTDTHUANYHCT")
    private Boolean hsbactnoitruYhctDieutridonthuanyhct;
    @Column(name = "HSBACTNOITRU_YHCT_DTKETHOPYHHD")
    private Boolean hsbactnoitruYhctDieutrikethopyhhd;
    @Column(name = "HSBACTNOITRU_YHCT_PHEPCHUA")
    private String hsbactnoitruYhctPhepchua;
    @Column(name = "HSBACTNOITRU_YHCT_PHUONGTHUOC")
    private String hsbactnoitruYhctPhuongthuoc;
    @Column(name = "HSBACTNOITRU_YHCT_PHUONGHUYET")
    private String hsbactnoitruYhctPhuonghuyet;
    @Column(name = "HSBACTNOITRU_YHCT_XOABOP")
    private String hsbactnoitruYhctXoabop;
    @Column(name = "HSBACTNOITRU_YHCT_CHEDOAN")
    private String hsbactnoitruYhctChedoan;
    @Column(name = "HSBACTNOITRU_YHCT_CHEDOHOLY")
    private String hsbactnoitruYhctChedoholy;
    @Column(name = "HSBACTNOITRU_YHCT_TIENLUONG")
    private String hsbactnoitruYhctTienluong;
    @Column(name = "HSBACTNOITRU_YHCT_QTBLDBCLS")
    private String hsbactnoitruYhctQtbldbcls;
    @Column(name = "HSBACTNOITRU_YHCT_KQUACLSCHINH")
    private String hsbactnoitruYhctKetquaclschinh;
    @Column(name = "HSBACTNOITRU_YHCT_KQUAGPBENH")
    private String hsbactnoitruYhctKetquagiaiphaubenh;
    @Column(name = "HSBACTNOITRU_YHCT_DIEUTRI_YHHD")
    private String hsbactnoitruYhctDieutriYhhd;
    @Column(name = "HSBACTNOITRU_YHCT_DIEUTRI_YHCT")
    private String hsbactnoitruYhctDieutriYhct;
    @Column(name = "HSBACTNOITRU_YHCT_CDRVIEN_YHHD")
    private String hsbactnoitruYhctChandoanravienYhhd;
    @Column(name = "HSBACTNOITRU_YHCT_CDRVIEN_YHCT")
    private String hsbactnoitruYhctChandoanravienYhct;
    @Column(name = "HSBACTNOITRU_YHCT_TTNBRAVIEN")
    private String hsbactnoitruYhctTinhtrangnbravien;
    @Column(name = "HSBACTNOITRU_YHCT_HDTCCDTT")
    private String hsbactnoitruYhctHdtccdtt;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTNOITRU_YHCT_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactnoitruYhctBsdieutri;

    public HsbaChiTietNoitruYhct() {
    }

    public HsbaChiTietNoitruYhct(Integer hsbactnoitruYhctMa) {
        this.hsbactnoitruYhctMa = hsbactnoitruYhctMa;
    }

    public Integer getHsbactnoitruYhctMa() {
        return hsbactnoitruYhctMa;
    }

    public void setHsbactnoitruYhctMa(Integer hsbactnoitruYhctMa) {
        this.hsbactnoitruYhctMa = hsbactnoitruYhctMa;
    }

    public String getHsbactnoitruYhctKhoakhambenh() {
        return hsbactnoitruYhctKhoakhambenh;
    }

    public void setHsbactnoitruYhctKhoakhambenh(String hsbactnoitruYhctKhoakhambenh) {
        this.hsbactnoitruYhctKhoakhambenh = hsbactnoitruYhctKhoakhambenh;
    }

    public String getHsbactnoitruYhctChandoannguyennhan() {
        return hsbactnoitruYhctChandoannguyennhan;
    }

    public void setHsbactnoitruYhctChandoannguyennhan(String hsbactnoitruYhctChandoannguyennhan) {
        this.hsbactnoitruYhctChandoannguyennhan = hsbactnoitruYhctChandoannguyennhan;
    }

    public String getHsbactnoitruYhctChandoanbatchuong() {
        return hsbactnoitruYhctChandoanbatchuong;
    }

    public void setHsbactnoitruYhctChandoanbatchuong(String hsbactnoitruYhctChandoanbatchuong) {
        this.hsbactnoitruYhctChandoanbatchuong = hsbactnoitruYhctChandoanbatchuong;
    }

    public String getHsbactnoitruYhctBenhdanh() {
        return hsbactnoitruYhctBenhdanh;
    }

    public void setHsbactnoitruYhctBenhdanh(String hsbactnoitruYhctBenhdanh) {
        this.hsbactnoitruYhctBenhdanh = hsbactnoitruYhctBenhdanh;
    }

    public String getHsbactnoitruYhctLydovaov() {
        return hsbactnoitruYhctLydovaov;
    }

    public void setHsbactnoitruYhctLydovaov(String hsbactnoitruYhctLydovaov) {
        this.hsbactnoitruYhctLydovaov = hsbactnoitruYhctLydovaov;
    }

    public String getHsbactnoitruYhctQtbenhly() {
        return hsbactnoitruYhctQtbenhly;
    }

    public void setHsbactnoitruYhctQtbenhly(String hsbactnoitruYhctQtbenhly) {
        this.hsbactnoitruYhctQtbenhly = hsbactnoitruYhctQtbenhly;
    }

    public String getHsbactnoitruYhctTiensubenhbt() {
        return hsbactnoitruYhctTiensubenhbt;
    }

    public void setHsbactnoitruYhctTiensubenhbt(String hsbactnoitruYhctTiensubenhbt) {
        this.hsbactnoitruYhctTiensubenhbt = hsbactnoitruYhctTiensubenhbt;
    }

    public String getHsbactnoitruYhctTiensubenhgd() {
        return hsbactnoitruYhctTiensubenhgd;
    }

    public void setHsbactnoitruYhctTiensubenhgd(String hsbactnoitruYhctTiensubenhgd) {
        this.hsbactnoitruYhctTiensubenhgd = hsbactnoitruYhctTiensubenhgd;
    }

    public Boolean getHsbactnoitruYhctThuocla() {
        return hsbactnoitruYhctThuocla;
    }

    public void setHsbactnoitruYhctThuocla(Boolean hsbactnoitruYhctThuocla) {
        this.hsbactnoitruYhctThuocla = hsbactnoitruYhctThuocla;
    }

    public Boolean getHsbactnoitruYhctRuou() {
        return hsbactnoitruYhctRuou;
    }

    public void setHsbactnoitruYhctRuou(Boolean hsbactnoitruYhctRuou) {
        this.hsbactnoitruYhctRuou = hsbactnoitruYhctRuou;
    }

    public Boolean getHsbactnoitruYhctMatuy() {
        return hsbactnoitruYhctMatuy;
    }

    public void setHsbactnoitruYhctMatuy(Boolean hsbactnoitruYhctMatuy) {
        this.hsbactnoitruYhctMatuy = hsbactnoitruYhctMatuy;
    }

    public Boolean getHsbactnoitruYhctDiung() {
        return hsbactnoitruYhctDiung;
    }

    public void setHsbactnoitruYhctDiung(Boolean hsbactnoitruYhctDiung) {
        this.hsbactnoitruYhctDiung = hsbactnoitruYhctDiung;
    }

    public Boolean getHsbactnoitruYhctKhac() {
        return hsbactnoitruYhctKhac;
    }

    public void setHsbactnoitruYhctKhac(Boolean hsbactnoitruYhctKhac) {
        this.hsbactnoitruYhctKhac = hsbactnoitruYhctKhac;
    }

    public String getHsbactnoitruYhctNoitiet() {
        return hsbactnoitruYhctNoitiet;
    }

    public void setHsbactnoitruYhctNoitiet(String hsbactnoitruYhctNoitiet) {
        this.hsbactnoitruYhctNoitiet = hsbactnoitruYhctNoitiet;
    }

    public String getHsbactnoitruYhctDinhduong() {
        return hsbactnoitruYhctDinhduong;
    }

    public void setHsbactnoitruYhctDinhduong(String hsbactnoitruYhctDinhduong) {
        this.hsbactnoitruYhctDinhduong = hsbactnoitruYhctDinhduong;
    }

    public String getHsbactnoitruYhctThankinh() {
        return hsbactnoitruYhctThankinh;
    }

    public void setHsbactnoitruYhctThankinh(String hsbactnoitruYhctThankinh) {
        this.hsbactnoitruYhctThankinh = hsbactnoitruYhctThankinh;
    }

    public String getHsbactnoitruYhctMat() {
        return hsbactnoitruYhctMat;
    }

    public void setHsbactnoitruYhctMat(String hsbactnoitruYhctMat) {
        this.hsbactnoitruYhctMat = hsbactnoitruYhctMat;
    }

    public String getHsbactnoitruYhctTmh() {
        return hsbactnoitruYhctTmh;
    }

    public void setHsbactnoitruYhctTmh(String hsbactnoitruYhctTmh) {
        this.hsbactnoitruYhctTmh = hsbactnoitruYhctTmh;
    }

    public String getHsbactnoitruYhctRhm() {
        return hsbactnoitruYhctRhm;
    }

    public void setHsbactnoitruYhctRhm(String hsbactnoitruYhctRhm) {
        this.hsbactnoitruYhctRhm = hsbactnoitruYhctRhm;
    }

    public String getHsbactnoitruYhctTuanhoan() {
        return hsbactnoitruYhctTuanhoan;
    }

    public void setHsbactnoitruYhctTuanhoan(String hsbactnoitruYhctTuanhoan) {
        this.hsbactnoitruYhctTuanhoan = hsbactnoitruYhctTuanhoan;
    }

    public String getHsbactnoitruYhctHohap() {
        return hsbactnoitruYhctHohap;
    }

    public void setHsbactnoitruYhctHohap(String hsbactnoitruYhctHohap) {
        this.hsbactnoitruYhctHohap = hsbactnoitruYhctHohap;
    }

    public String getHsbactnoitruYhctTieuhoa() {
        return hsbactnoitruYhctTieuhoa;
    }

    public void setHsbactnoitruYhctTieuhoa(String hsbactnoitruYhctTieuhoa) {
        this.hsbactnoitruYhctTieuhoa = hsbactnoitruYhctTieuhoa;
    }

    public String getHsbactnoitruYhctDavamo() {
        return hsbactnoitruYhctDavamo;
    }

    public void setHsbactnoitruYhctDavamo(String hsbactnoitruYhctDavamo) {
        this.hsbactnoitruYhctDavamo = hsbactnoitruYhctDavamo;
    }

    public String getHsbactnoitruYhctCoxuongkhop() {
        return hsbactnoitruYhctCoxuongkhop;
    }

    public void setHsbactnoitruYhctCoxuongkhop(String hsbactnoitruYhctCoxuongkhop) {
        this.hsbactnoitruYhctCoxuongkhop = hsbactnoitruYhctCoxuongkhop;
    }

    public String getHsbactnoitruYhctTietnieu() {
        return hsbactnoitruYhctTietnieu;
    }

    public void setHsbactnoitruYhctTietnieu(String hsbactnoitruYhctTietnieu) {
        this.hsbactnoitruYhctTietnieu = hsbactnoitruYhctTietnieu;
    }

    public String getHsbactnoitruYhctSinhduc() {
        return hsbactnoitruYhctSinhduc;
    }

    public void setHsbactnoitruYhctSinhduc(String hsbactnoitruYhctSinhduc) {
        this.hsbactnoitruYhctSinhduc = hsbactnoitruYhctSinhduc;
    }

    public String getHsbactnoitruYhctCoquankhac() {
        return hsbactnoitruYhctCoquankhac;
    }

    public void setHsbactnoitruYhctCoquankhac(String hsbactnoitruYhctCoquankhac) {
        this.hsbactnoitruYhctCoquankhac = hsbactnoitruYhctCoquankhac;
    }

    public String getHsbactnoitruYhctChitietbenhly() {
        return hsbactnoitruYhctChitietbenhly;
    }

    public void setHsbactnoitruYhctChitietbenhly(String hsbactnoitruYhctChitietbenhly) {
        this.hsbactnoitruYhctChitietbenhly = hsbactnoitruYhctChitietbenhly;
    }

    public String getHsbactnoitruYhctHuyethoc() {
        return hsbactnoitruYhctHuyethoc;
    }

    public void setHsbactnoitruYhctHuyethoc(String hsbactnoitruYhctHuyethoc) {
        this.hsbactnoitruYhctHuyethoc = hsbactnoitruYhctHuyethoc;
    }

    public String getHsbactnoitruYhctHoasinh() {
        return hsbactnoitruYhctHoasinh;
    }

    public void setHsbactnoitruYhctHoasinh(String hsbactnoitruYhctHoasinh) {
        this.hsbactnoitruYhctHoasinh = hsbactnoitruYhctHoasinh;
    }

    public String getHsbactnoitruYhctVisinh() {
        return hsbactnoitruYhctVisinh;
    }

    public void setHsbactnoitruYhctVisinh(String hsbactnoitruYhctVisinh) {
        this.hsbactnoitruYhctVisinh = hsbactnoitruYhctVisinh;
    }

    public String getHsbactnoitruYhctXquang() {
        return hsbactnoitruYhctXquang;
    }

    public void setHsbactnoitruYhctXquang(String hsbactnoitruYhctXquang) {
        this.hsbactnoitruYhctXquang = hsbactnoitruYhctXquang;
    }

    public String getHsbactnoitruYhctSieuam() {
        return hsbactnoitruYhctSieuam;
    }

    public void setHsbactnoitruYhctSieuam(String hsbactnoitruYhctSieuam) {
        this.hsbactnoitruYhctSieuam = hsbactnoitruYhctSieuam;
    }

    public String getHsbactnoitruYhctDientim() {
        return hsbactnoitruYhctDientim;
    }

    public void setHsbactnoitruYhctDientim(String hsbactnoitruYhctDientim) {
        this.hsbactnoitruYhctDientim = hsbactnoitruYhctDientim;
    }

    public String getHsbactnoitruYhctNoisoi() {
        return hsbactnoitruYhctNoisoi;
    }

    public void setHsbactnoitruYhctNoisoi(String hsbactnoitruYhctNoisoi) {
        this.hsbactnoitruYhctNoisoi = hsbactnoitruYhctNoisoi;
    }

    public String getHsbactnoitruYhctGiaiphaubenh() {
        return hsbactnoitruYhctGiaiphaubenh;
    }

    public void setHsbactnoitruYhctGiaiphaubenh(String hsbactnoitruYhctGiaiphaubenh) {
        this.hsbactnoitruYhctGiaiphaubenh = hsbactnoitruYhctGiaiphaubenh;
    }

    public String getHsbactnoitruYhctXetnghiemkhac() {
        return hsbactnoitruYhctXetnghiemkhac;
    }

    public void setHsbactnoitruYhctXetnghiemkhac(String hsbactnoitruYhctXetnghiemkhac) {
        this.hsbactnoitruYhctXetnghiemkhac = hsbactnoitruYhctXetnghiemkhac;
    }

    public String getHsbactnoitruYhctHuyethockq() {
        return hsbactnoitruYhctHuyethockq;
    }

    public void setHsbactnoitruYhctHuyethockq(String hsbactnoitruYhctHuyethockq) {
        this.hsbactnoitruYhctHuyethockq = hsbactnoitruYhctHuyethockq;
    }

    public String getHsbactnoitruYhctHoasinhkq() {
        return hsbactnoitruYhctHoasinhkq;
    }

    public void setHsbactnoitruYhctHoasinhkq(String hsbactnoitruYhctHoasinhkq) {
        this.hsbactnoitruYhctHoasinhkq = hsbactnoitruYhctHoasinhkq;
    }

    public String getHsbactnoitruYhctVisinhkq() {
        return hsbactnoitruYhctVisinhkq;
    }

    public void setHsbactnoitruYhctVisinhkq(String hsbactnoitruYhctVisinhkq) {
        this.hsbactnoitruYhctVisinhkq = hsbactnoitruYhctVisinhkq;
    }

    public String getHsbactnoitruYhctXquangkq() {
        return hsbactnoitruYhctXquangkq;
    }

    public void setHsbactnoitruYhctXquangkq(String hsbactnoitruYhctXquangkq) {
        this.hsbactnoitruYhctXquangkq = hsbactnoitruYhctXquangkq;
    }

    public String getHsbactnoitruYhctSieuamkq() {
        return hsbactnoitruYhctSieuamkq;
    }

    public void setHsbactnoitruYhctSieuamkq(String hsbactnoitruYhctSieuamkq) {
        this.hsbactnoitruYhctSieuamkq = hsbactnoitruYhctSieuamkq;
    }

    public String getHsbactnoitruYhctDientimkq() {
        return hsbactnoitruYhctDientimkq;
    }

    public void setHsbactnoitruYhctDientimkq(String hsbactnoitruYhctDientimkq) {
        this.hsbactnoitruYhctDientimkq = hsbactnoitruYhctDientimkq;
    }

    public String getHsbactnoitruYhctNoisoikq() {
        return hsbactnoitruYhctNoisoikq;
    }

    public void setHsbactnoitruYhctNoisoikq(String hsbactnoitruYhctNoisoikq) {
        this.hsbactnoitruYhctNoisoikq = hsbactnoitruYhctNoisoikq;
    }

    public String getHsbactnoitruYhctGiaiphaubenhkq() {
        return hsbactnoitruYhctGiaiphaubenhkq;
    }

    public void setHsbactnoitruYhctGiaiphaubenhkq(String hsbactnoitruYhctGiaiphaubenhkq) {
        this.hsbactnoitruYhctGiaiphaubenhkq = hsbactnoitruYhctGiaiphaubenhkq;
    }

    public String getHsbactnoitruYhctXetnghiemkhackq() {
        return hsbactnoitruYhctXetnghiemkhackq;
    }

    public void setHsbactnoitruYhctXetnghiemkhackq(String hsbactnoitruYhctXetnghiemkhackq) {
        this.hsbactnoitruYhctXetnghiemkhackq = hsbactnoitruYhctXetnghiemkhackq;
    }

    public String getHsbactnoitruYhctTomtattrieuchung() {
        return hsbactnoitruYhctTomtattrieuchung;
    }

    public void setHsbactnoitruYhctTomtattrieuchung(String hsbactnoitruYhctTomtattrieuchung) {
        this.hsbactnoitruYhctTomtattrieuchung = hsbactnoitruYhctTomtattrieuchung;
    }

    public String getHsbactnoitruYhctChandoanphanbiet() {
        return hsbactnoitruYhctChandoanphanbiet;
    }

    public void setHsbactnoitruYhctChandoanphanbiet(String hsbactnoitruYhctChandoanphanbiet) {
        this.hsbactnoitruYhctChandoanphanbiet = hsbactnoitruYhctChandoanphanbiet;
    }

    public String getHsbactnoitruYhctVongchanHinhthai() {
        return hsbactnoitruYhctVongchanHinhthai;
    }

    public void setHsbactnoitruYhctVongchanHinhthai(String hsbactnoitruYhctVongchanHinhthai) {
        this.hsbactnoitruYhctVongchanHinhthai = hsbactnoitruYhctVongchanHinhthai;
    }

    public String getHsbactnoitruYhctVongchanMota() {
        return hsbactnoitruYhctVongchanMota;
    }

    public void setHsbactnoitruYhctVongchanMota(String hsbactnoitruYhctVongchanMota) {
        this.hsbactnoitruYhctVongchanMota = hsbactnoitruYhctVongchanMota;
    }

    public Boolean getHsbactnoitruYhctThansacTinhtao() {
        return hsbactnoitruYhctThansacTinhtao;
    }

    public void setHsbactnoitruYhctThansacTinhtao(Boolean hsbactnoitruYhctThansacTinhtao) {
        this.hsbactnoitruYhctThansacTinhtao = hsbactnoitruYhctThansacTinhtao;
    }

    public String getHsbactnoitruYhctThansacSac() {
        return hsbactnoitruYhctThansacSac;
    }

    public void setHsbactnoitruYhctThansacSac(String hsbactnoitruYhctThansacSac) {
        this.hsbactnoitruYhctThansacSac = hsbactnoitruYhctThansacSac;
    }

    public String getHsbactnoitruYhctThansacTrach() {
        return hsbactnoitruYhctThansacTrach;
    }

    public void setHsbactnoitruYhctThansacTrach(String hsbactnoitruYhctThansacTrach) {
        this.hsbactnoitruYhctThansacTrach = hsbactnoitruYhctThansacTrach;
    }

    public String getHsbactnoitruYhctThansacMota() {
        return hsbactnoitruYhctThansacMota;
    }

    public void setHsbactnoitruYhctThansacMota(String hsbactnoitruYhctThansacMota) {
        this.hsbactnoitruYhctThansacMota = hsbactnoitruYhctThansacMota;
    }

    public String getHsbactnoitruYhctLuoiChatluoi() {
        return hsbactnoitruYhctLuoiChatluoi;
    }

    public void setHsbactnoitruYhctLuoiChatluoi(String hsbactnoitruYhctLuoiChatluoi) {
        this.hsbactnoitruYhctLuoiChatluoi = hsbactnoitruYhctLuoiChatluoi;
    }

    public String getHsbactnoitruYhctLuoiMota() {
        return hsbactnoitruYhctLuoiMota;
    }

    public void setHsbactnoitruYhctLuoiMota(String hsbactnoitruYhctLuoiMota) {
        this.hsbactnoitruYhctLuoiMota = hsbactnoitruYhctLuoiMota;
    }

    public String getHsbactnoitruYhctLuoiSac() {
        return hsbactnoitruYhctLuoiSac;
    }

    public void setHsbactnoitruYhctLuoiSac(String hsbactnoitruYhctLuoiSac) {
        this.hsbactnoitruYhctLuoiSac = hsbactnoitruYhctLuoiSac;
    }

    public String getHsbactnoitruYhctSacluoiMota() {
        return hsbactnoitruYhctSacluoiMota;
    }

    public void setHsbactnoitruYhctSacluoiMota(String hsbactnoitruYhctSacluoiMota) {
        this.hsbactnoitruYhctSacluoiMota = hsbactnoitruYhctSacluoiMota;
    }

    public String getHsbactnoitruYhctReuluoi() {
        return hsbactnoitruYhctReuluoi;
    }

    public void setHsbactnoitruYhctReuluoi(String hsbactnoitruYhctReuluoi) {
        this.hsbactnoitruYhctReuluoi = hsbactnoitruYhctReuluoi;
    }

    public String getHsbactnoitruYhctReuluoiMota() {
        return hsbactnoitruYhctReuluoiMota;
    }

    public void setHsbactnoitruYhctReuluoiMota(String hsbactnoitruYhctReuluoiMota) {
        this.hsbactnoitruYhctReuluoiMota = hsbactnoitruYhctReuluoiMota;
    }

    public String getHsbactnoitruYhctBophanbibenh() {
        return hsbactnoitruYhctBophanbibenh;
    }

    public void setHsbactnoitruYhctBophanbibenh(String hsbactnoitruYhctBophanbibenh) {
        this.hsbactnoitruYhctBophanbibenh = hsbactnoitruYhctBophanbibenh;
    }

    public String getHsbactnoitruYhctTiengnoi() {
        return hsbactnoitruYhctTiengnoi;
    }

    public void setHsbactnoitruYhctTiengnoi(String hsbactnoitruYhctTiengnoi) {
        this.hsbactnoitruYhctTiengnoi = hsbactnoitruYhctTiengnoi;
    }

    public String getHsbactnoitruYhctTiengnoiMota() {
        return hsbactnoitruYhctTiengnoiMota;
    }

    public void setHsbactnoitruYhctTiengnoiMota(String hsbactnoitruYhctTiengnoiMota) {
        this.hsbactnoitruYhctTiengnoiMota = hsbactnoitruYhctTiengnoiMota;
    }

    public String getHsbactnoitruYhctHoitho() {
        return hsbactnoitruYhctHoitho;
    }

    public void setHsbactnoitruYhctHoitho(String hsbactnoitruYhctHoitho) {
        this.hsbactnoitruYhctHoitho = hsbactnoitruYhctHoitho;
    }

    public String getHsbactnoitruYhctHoithoMota() {
        return hsbactnoitruYhctHoithoMota;
    }

    public void setHsbactnoitruYhctHoithoMota(String hsbactnoitruYhctHoithoMota) {
        this.hsbactnoitruYhctHoithoMota = hsbactnoitruYhctHoithoMota;
    }

    public Boolean getHsbactnoitruYhctHo() {
        return hsbactnoitruYhctHo;
    }

    public void setHsbactnoitruYhctHo(Boolean hsbactnoitruYhctHo) {
        this.hsbactnoitruYhctHo = hsbactnoitruYhctHo;
    }

    public String getHsbactnoitruYhctHoTinhchat() {
        return hsbactnoitruYhctHoTinhchat;
    }

    public void setHsbactnoitruYhctHoTinhchat(String hsbactnoitruYhctHoTinhchat) {
        this.hsbactnoitruYhctHoTinhchat = hsbactnoitruYhctHoTinhchat;
    }

    public String getHsbactnoitruYhctHoMota() {
        return hsbactnoitruYhctHoMota;
    }

    public void setHsbactnoitruYhctHoMota(String hsbactnoitruYhctHoMota) {
        this.hsbactnoitruYhctHoMota = hsbactnoitruYhctHoMota;
    }

    public Boolean getHsbactnoitruYhctOnac() {
        return hsbactnoitruYhctOnac;
    }

    public void setHsbactnoitruYhctOnac(Boolean hsbactnoitruYhctOnac) {
        this.hsbactnoitruYhctOnac = hsbactnoitruYhctOnac;
    }

    public String getHsbactnoitruYhctOnacMota() {
        return hsbactnoitruYhctOnacMota;
    }

    public void setHsbactnoitruYhctOnacMota(String hsbactnoitruYhctOnacMota) {
        this.hsbactnoitruYhctOnacMota = hsbactnoitruYhctOnacMota;
    }

    public Boolean getHsbactnoitruYhctMui() {
        return hsbactnoitruYhctMui;
    }

    public void setHsbactnoitruYhctMui(Boolean hsbactnoitruYhctMui) {
        this.hsbactnoitruYhctMui = hsbactnoitruYhctMui;
    }

    public String getHsbactnoitruYhctMuiTc() {
        return hsbactnoitruYhctMuiTc;
    }

    public void setHsbactnoitruYhctMuiTc(String hsbactnoitruYhctMuiTc) {
        this.hsbactnoitruYhctMuiTc = hsbactnoitruYhctMuiTc;
    }

    public String getHsbactnoitruYhctMuiMota() {
        return hsbactnoitruYhctMuiMota;
    }

    public void setHsbactnoitruYhctMuiMota(String hsbactnoitruYhctMuiMota) {
        this.hsbactnoitruYhctMuiMota = hsbactnoitruYhctMuiMota;
    }

    public Boolean getHsbactnoitruYhctHoithocomui() {
        return hsbactnoitruYhctHoithocomui;
    }

    public void setHsbactnoitruYhctHoithocomui(Boolean hsbactnoitruYhctHoithocomui) {
        this.hsbactnoitruYhctHoithocomui = hsbactnoitruYhctHoithocomui;
    }

    public String getHsbactnoitruYhctHoithocomuiMota() {
        return hsbactnoitruYhctHoithocomuiMota;
    }

    public void setHsbactnoitruYhctHoithocomuiMota(String hsbactnoitruYhctHoithocomuiMota) {
        this.hsbactnoitruYhctHoithocomuiMota = hsbactnoitruYhctHoithocomuiMota;
    }

    public String getHsbactnoitruYhctHoithocomuiTc() {
        return hsbactnoitruYhctHoithocomuiTc;
    }

    public void setHsbactnoitruYhctHoithocomuiTc(String hsbactnoitruYhctHoithocomuiTc) {
        this.hsbactnoitruYhctHoithocomuiTc = hsbactnoitruYhctHoithocomuiTc;
    }

    public Boolean getHsbactnoitruYhctCohannhiet() {
        return hsbactnoitruYhctCohannhiet;
    }

    public void setHsbactnoitruYhctCohannhiet(Boolean hsbactnoitruYhctCohannhiet) {
        this.hsbactnoitruYhctCohannhiet = hsbactnoitruYhctCohannhiet;
    }

    public String getHsbactnoitruYhctHannhiet() {
        return hsbactnoitruYhctHannhiet;
    }

    public void setHsbactnoitruYhctHannhiet(String hsbactnoitruYhctHannhiet) {
        this.hsbactnoitruYhctHannhiet = hsbactnoitruYhctHannhiet;
    }

    public String getHsbactnoitruYhctHannhietMota() {
        return hsbactnoitruYhctHannhietMota;
    }

    public void setHsbactnoitruYhctHannhietMota(String hsbactnoitruYhctHannhietMota) {
        this.hsbactnoitruYhctHannhietMota = hsbactnoitruYhctHannhietMota;
    }

    public Boolean getHsbactnoitruYhctCobenhthaydoitheomua() {
        return hsbactnoitruYhctCobenhthaydoitheomua;
    }

    public void setHsbactnoitruYhctCobenhthaydoitheomua(Boolean hsbactnoitruYhctCobenhthaydoitheomua) {
        this.hsbactnoitruYhctCobenhthaydoitheomua = hsbactnoitruYhctCobenhthaydoitheomua;
    }

    public String getHsbactnoitruYhctBenhthaydoitheomuaMota() {
        return hsbactnoitruYhctBenhthaydoitheomuaMota;
    }

    public void setHsbactnoitruYhctBenhthaydoitheomuaMota(String hsbactnoitruYhctBenhthaydoitheomuaMota) {
        this.hsbactnoitruYhctBenhthaydoitheomuaMota = hsbactnoitruYhctBenhthaydoitheomuaMota;
    }

    public String getHsbactnoitruYhctMohoi() {
        return hsbactnoitruYhctMohoi;
    }

    public void setHsbactnoitruYhctMohoi(String hsbactnoitruYhctMohoi) {
        this.hsbactnoitruYhctMohoi = hsbactnoitruYhctMohoi;
    }

    public String getHsbactnoitruYhctMohoiMota() {
        return hsbactnoitruYhctMohoiMota;
    }

    public void setHsbactnoitruYhctMohoiMota(String hsbactnoitruYhctMohoiMota) {
        this.hsbactnoitruYhctMohoiMota = hsbactnoitruYhctMohoiMota;
    }

    public Boolean getHsbactnoitruYhctDaumatcobenhly() {
        return hsbactnoitruYhctDaumatcobenhly;
    }

    public void setHsbactnoitruYhctDaumatcobenhly(Boolean hsbactnoitruYhctDaumatcobenhly) {
        this.hsbactnoitruYhctDaumatcobenhly = hsbactnoitruYhctDaumatcobenhly;
    }

    public String getHsbactnoitruYhctDaumatbenhly() {
        return hsbactnoitruYhctDaumatbenhly;
    }

    public void setHsbactnoitruYhctDaumatbenhly(String hsbactnoitruYhctDaumatbenhly) {
        this.hsbactnoitruYhctDaumatbenhly = hsbactnoitruYhctDaumatbenhly;
    }

    public String getHsbactnoitruYhctDaumatMota() {
        return hsbactnoitruYhctDaumatMota;
    }

    public void setHsbactnoitruYhctDaumatMota(String hsbactnoitruYhctDaumatMota) {
        this.hsbactnoitruYhctDaumatMota = hsbactnoitruYhctDaumatMota;
    }

    public Boolean getHsbactnoitruYhctLungcobenhly() {
        return hsbactnoitruYhctLungcobenhly;
    }

    public void setHsbactnoitruYhctLungcobenhly(Boolean hsbactnoitruYhctLungcobenhly) {
        this.hsbactnoitruYhctLungcobenhly = hsbactnoitruYhctLungcobenhly;
    }

    public String getHsbactnoitruYhctLungMota() {
        return hsbactnoitruYhctLungMota;
    }

    public void setHsbactnoitruYhctLungMota(String hsbactnoitruYhctLungMota) {
        this.hsbactnoitruYhctLungMota = hsbactnoitruYhctLungMota;
    }

    public Boolean getHsbactnoitruYhctBungnguccobenhly() {
        return hsbactnoitruYhctBungnguccobenhly;
    }

    public void setHsbactnoitruYhctBungnguccobenhly(Boolean hsbactnoitruYhctBungnguccobenhly) {
        this.hsbactnoitruYhctBungnguccobenhly = hsbactnoitruYhctBungnguccobenhly;
    }

    public String getHsbactnoitruYhctBungnguc() {
        return hsbactnoitruYhctBungnguc;
    }

    public void setHsbactnoitruYhctBungnguc(String hsbactnoitruYhctBungnguc) {
        this.hsbactnoitruYhctBungnguc = hsbactnoitruYhctBungnguc;
    }

    public String getHsbactnoitruYhctBungngucMota() {
        return hsbactnoitruYhctBungngucMota;
    }

    public void setHsbactnoitruYhctBungngucMota(String hsbactnoitruYhctBungngucMota) {
        this.hsbactnoitruYhctBungngucMota = hsbactnoitruYhctBungngucMota;
    }

    public Boolean getHsbactnoitruYhctChantaycobenhly() {
        return hsbactnoitruYhctChantaycobenhly;
    }

    public void setHsbactnoitruYhctChantaycobenhly(Boolean hsbactnoitruYhctChantaycobenhly) {
        this.hsbactnoitruYhctChantaycobenhly = hsbactnoitruYhctChantaycobenhly;
    }

    public String getHsbactnoitruYhctChantayMota() {
        return hsbactnoitruYhctChantayMota;
    }

    public void setHsbactnoitruYhctChantayMota(String hsbactnoitruYhctChantayMota) {
        this.hsbactnoitruYhctChantayMota = hsbactnoitruYhctChantayMota;
    }

    public Boolean getHsbactnoitruYhctAncobenhly() {
        return hsbactnoitruYhctAncobenhly;
    }

    public void setHsbactnoitruYhctAncobenhly(Boolean hsbactnoitruYhctAncobenhly) {
        this.hsbactnoitruYhctAncobenhly = hsbactnoitruYhctAncobenhly;
    }

    public String getHsbactnoitruYhctAnTc() {
        return hsbactnoitruYhctAnTc;
    }

    public void setHsbactnoitruYhctAnTc(String hsbactnoitruYhctAnTc) {
        this.hsbactnoitruYhctAnTc = hsbactnoitruYhctAnTc;
    }

    public String getHsbactnoitruYhctAnMota() {
        return hsbactnoitruYhctAnMota;
    }

    public void setHsbactnoitruYhctAnMota(String hsbactnoitruYhctAnMota) {
        this.hsbactnoitruYhctAnMota = hsbactnoitruYhctAnMota;
    }

    public Boolean getHsbactnoitruYhctUongcobenhly() {
        return hsbactnoitruYhctUongcobenhly;
    }

    public void setHsbactnoitruYhctUongcobenhly(Boolean hsbactnoitruYhctUongcobenhly) {
        this.hsbactnoitruYhctUongcobenhly = hsbactnoitruYhctUongcobenhly;
    }

    public String getHsbactnoitruYhctUongTc() {
        return hsbactnoitruYhctUongTc;
    }

    public void setHsbactnoitruYhctUongTc(String hsbactnoitruYhctUongTc) {
        this.hsbactnoitruYhctUongTc = hsbactnoitruYhctUongTc;
    }

    public String getHsbactnoitruYhctUongMota() {
        return hsbactnoitruYhctUongMota;
    }

    public void setHsbactnoitruYhctUongMota(String hsbactnoitruYhctUongMota) {
        this.hsbactnoitruYhctUongMota = hsbactnoitruYhctUongMota;
    }

    public Boolean getHsbactnoitruYhctDaitieutiencobenhly() {
        return hsbactnoitruYhctDaitieutiencobenhly;
    }

    public void setHsbactnoitruYhctDaitieutiencobenhly(Boolean hsbactnoitruYhctDaitieutiencobenhly) {
        this.hsbactnoitruYhctDaitieutiencobenhly = hsbactnoitruYhctDaitieutiencobenhly;
    }

    public String getHsbactnoitruYhctTieutienTc() {
        return hsbactnoitruYhctTieutienTc;
    }

    public void setHsbactnoitruYhctTieutienTc(String hsbactnoitruYhctTieutienTc) {
        this.hsbactnoitruYhctTieutienTc = hsbactnoitruYhctTieutienTc;
    }

    public String getHsbactnoitruYhctDaitienTc() {
        return hsbactnoitruYhctDaitienTc;
    }

    public void setHsbactnoitruYhctDaitienTc(String hsbactnoitruYhctDaitienTc) {
        this.hsbactnoitruYhctDaitienTc = hsbactnoitruYhctDaitienTc;
    }

    public String getHsbactnoitruYhctDaitieutienMota() {
        return hsbactnoitruYhctDaitieutienMota;
    }

    public void setHsbactnoitruYhctDaitieutienMota(String hsbactnoitruYhctDaitieutienMota) {
        this.hsbactnoitruYhctDaitieutienMota = hsbactnoitruYhctDaitieutienMota;
    }

    public Boolean getHsbactnoitruYhctNgucobenhly() {
        return hsbactnoitruYhctNgucobenhly;
    }

    public void setHsbactnoitruYhctNgucobenhly(Boolean hsbactnoitruYhctNgucobenhly) {
        this.hsbactnoitruYhctNgucobenhly = hsbactnoitruYhctNgucobenhly;
    }

    public String getHsbactnoitruYhctNguTc() {
        return hsbactnoitruYhctNguTc;
    }

    public void setHsbactnoitruYhctNguTc(String hsbactnoitruYhctNguTc) {
        this.hsbactnoitruYhctNguTc = hsbactnoitruYhctNguTc;
    }

    public String getHsbactnoitruYhctNguMota() {
        return hsbactnoitruYhctNguMota;
    }

    public void setHsbactnoitruYhctNguMota(String hsbactnoitruYhctNguMota) {
        this.hsbactnoitruYhctNguMota = hsbactnoitruYhctNguMota;
    }

    public Boolean getHsbactnoitruYhctKinhnguyetcobenhly() {
        return hsbactnoitruYhctKinhnguyetcobenhly;
    }

    public void setHsbactnoitruYhctKinhnguyetcobenhly(Boolean hsbactnoitruYhctKinhnguyetcobenhly) {
        this.hsbactnoitruYhctKinhnguyetcobenhly = hsbactnoitruYhctKinhnguyetcobenhly;
    }

    public String getHsbactnoitruYhctRoiloankinhnguyet() {
        return hsbactnoitruYhctRoiloankinhnguyet;
    }

    public void setHsbactnoitruYhctRoiloankinhnguyet(String hsbactnoitruYhctRoiloankinhnguyet) {
        this.hsbactnoitruYhctRoiloankinhnguyet = hsbactnoitruYhctRoiloankinhnguyet;
    }

    public String getHsbactnoitruYhctDaubungkinh() {
        return hsbactnoitruYhctDaubungkinh;
    }

    public void setHsbactnoitruYhctDaubungkinh(String hsbactnoitruYhctDaubungkinh) {
        this.hsbactnoitruYhctDaubungkinh = hsbactnoitruYhctDaubungkinh;
    }

    public Boolean getHsbactnoitruYhctDoihacobenhly() {
        return hsbactnoitruYhctDoihacobenhly;
    }

    public void setHsbactnoitruYhctDoihacobenhly(Boolean hsbactnoitruYhctDoihacobenhly) {
        this.hsbactnoitruYhctDoihacobenhly = hsbactnoitruYhctDoihacobenhly;
    }

    public String getHsbactnoitruYhctDoihaTc() {
        return hsbactnoitruYhctDoihaTc;
    }

    public void setHsbactnoitruYhctDoihaTc(String hsbactnoitruYhctDoihaTc) {
        this.hsbactnoitruYhctDoihaTc = hsbactnoitruYhctDoihaTc;
    }

    public String getHsbactnoitruYhctDoihaMota() {
        return hsbactnoitruYhctDoihaMota;
    }

    public void setHsbactnoitruYhctDoihaMota(String hsbactnoitruYhctDoihaMota) {
        this.hsbactnoitruYhctDoihaMota = hsbactnoitruYhctDoihaMota;
    }

    public Boolean getHsbactnoitruYhctSinhduccoroiloan() {
        return hsbactnoitruYhctSinhduccoroiloan;
    }

    public void setHsbactnoitruYhctSinhduccoroiloan(Boolean hsbactnoitruYhctSinhduccoroiloan) {
        this.hsbactnoitruYhctSinhduccoroiloan = hsbactnoitruYhctSinhduccoroiloan;
    }

    public String getHsbactnoitruYhctSinhducTc() {
        return hsbactnoitruYhctSinhducTc;
    }

    public void setHsbactnoitruYhctSinhducTc(String hsbactnoitruYhctSinhducTc) {
        this.hsbactnoitruYhctSinhducTc = hsbactnoitruYhctSinhducTc;
    }

    public String getHsbactnoitruYhctSinhducMota() {
        return hsbactnoitruYhctSinhducMota;
    }

    public void setHsbactnoitruYhctSinhducMota(String hsbactnoitruYhctSinhducMota) {
        this.hsbactnoitruYhctSinhducMota = hsbactnoitruYhctSinhducMota;
    }

    public String getHsbactnoitruYhctDieukienxuathien() {
        return hsbactnoitruYhctDieukienxuathien;
    }

    public void setHsbactnoitruYhctDieukienxuathien(String hsbactnoitruYhctDieukienxuathien) {
        this.hsbactnoitruYhctDieukienxuathien = hsbactnoitruYhctDieukienxuathien;
    }

    public String getHsbactnoitruYhctDieukienxuathienMota() {
        return hsbactnoitruYhctDieukienxuathienMota;
    }

    public void setHsbactnoitruYhctDieukienxuathienMota(String hsbactnoitruYhctDieukienxuathienMota) {
        this.hsbactnoitruYhctDieukienxuathienMota = hsbactnoitruYhctDieukienxuathienMota;
    }

    public String getHsbactnoitruYhctConhucTc() {
        return hsbactnoitruYhctConhucTc;
    }

    public void setHsbactnoitruYhctConhucTc(String hsbactnoitruYhctConhucTc) {
        this.hsbactnoitruYhctConhucTc = hsbactnoitruYhctConhucTc;
    }

    public String getHsbactnoitruYhctXucchanTc() {
        return hsbactnoitruYhctXucchanTc;
    }

    public void setHsbactnoitruYhctXucchanTc(String hsbactnoitruYhctXucchanTc) {
        this.hsbactnoitruYhctXucchanTc = hsbactnoitruYhctXucchanTc;
    }

    public String getHsbactnoitruYhctBungTc() {
        return hsbactnoitruYhctBungTc;
    }

    public void setHsbactnoitruYhctBungTc(String hsbactnoitruYhctBungTc) {
        this.hsbactnoitruYhctBungTc = hsbactnoitruYhctBungTc;
    }

    public String getHsbactnoitruYhctXucchanMota() {
        return hsbactnoitruYhctXucchanMota;
    }

    public void setHsbactnoitruYhctXucchanMota(String hsbactnoitruYhctXucchanMota) {
        this.hsbactnoitruYhctXucchanMota = hsbactnoitruYhctXucchanMota;
    }

    public String getHsbactnoitruYhctMachtaytraiThon() {
        return hsbactnoitruYhctMachtaytraiThon;
    }

    public void setHsbactnoitruYhctMachtaytraiThon(String hsbactnoitruYhctMachtaytraiThon) {
        this.hsbactnoitruYhctMachtaytraiThon = hsbactnoitruYhctMachtaytraiThon;
    }

    public String getHsbactnoitruYhctMachtayphaiThon() {
        return hsbactnoitruYhctMachtayphaiThon;
    }

    public void setHsbactnoitruYhctMachtayphaiThon(String hsbactnoitruYhctMachtayphaiThon) {
        this.hsbactnoitruYhctMachtayphaiThon = hsbactnoitruYhctMachtayphaiThon;
    }

    public String getHsbactnoitruYhctMachtaytraiQuan() {
        return hsbactnoitruYhctMachtaytraiQuan;
    }

    public void setHsbactnoitruYhctMachtaytraiQuan(String hsbactnoitruYhctMachtaytraiQuan) {
        this.hsbactnoitruYhctMachtaytraiQuan = hsbactnoitruYhctMachtaytraiQuan;
    }

    public String getHsbactnoitruYhctMachtaytraiXich() {
        return hsbactnoitruYhctMachtaytraiXich;
    }

    public void setHsbactnoitruYhctMachtaytraiXich(String hsbactnoitruYhctMachtaytraiXich) {
        this.hsbactnoitruYhctMachtaytraiXich = hsbactnoitruYhctMachtaytraiXich;
    }

    public String getHsbactnoitruYhctMachtayphaiQuan() {
        return hsbactnoitruYhctMachtayphaiQuan;
    }

    public void setHsbactnoitruYhctMachtayphaiQuan(String hsbactnoitruYhctMachtayphaiQuan) {
        this.hsbactnoitruYhctMachtayphaiQuan = hsbactnoitruYhctMachtayphaiQuan;
    }

    public String getHsbactnoitruYhctMachtayphaiXich() {
        return hsbactnoitruYhctMachtayphaiXich;
    }

    public void setHsbactnoitruYhctMachtayphaiXich(String hsbactnoitruYhctMachtayphaiXich) {
        this.hsbactnoitruYhctMachtayphaiXich = hsbactnoitruYhctMachtayphaiXich;
    }

    public String getHsbactnoitruYhctTongkhambenphai() {
        return hsbactnoitruYhctTongkhambenphai;
    }

    public void setHsbactnoitruYhctTongkhambenphai(String hsbactnoitruYhctTongkhambenphai) {
        this.hsbactnoitruYhctTongkhambenphai = hsbactnoitruYhctTongkhambenphai;
    }

    public String getHsbactnoitruYhctTongkhambentrai() {
        return hsbactnoitruYhctTongkhambentrai;
    }

    public void setHsbactnoitruYhctTongkhambentrai(String hsbactnoitruYhctTongkhambentrai) {
        this.hsbactnoitruYhctTongkhambentrai = hsbactnoitruYhctTongkhambentrai;
    }

    public String getHsbactnoitruYhctMachchanMota() {
        return hsbactnoitruYhctMachchanMota;
    }

    public void setHsbactnoitruYhctMachchanMota(String hsbactnoitruYhctMachchanMota) {
        this.hsbactnoitruYhctMachchanMota = hsbactnoitruYhctMachchanMota;
    }

    public String getHsbactnoitruYhctBienchungluantri() {
        return hsbactnoitruYhctBienchungluantri;
    }

    public void setHsbactnoitruYhctBienchungluantri(String hsbactnoitruYhctBienchungluantri) {
        this.hsbactnoitruYhctBienchungluantri = hsbactnoitruYhctBienchungluantri;
    }

    public String getHsbactnoitruYhctBatcuong() {
        return hsbactnoitruYhctBatcuong;
    }

    public void setHsbactnoitruYhctBatcuong(String hsbactnoitruYhctBatcuong) {
        this.hsbactnoitruYhctBatcuong = hsbactnoitruYhctBatcuong;
    }

    public String getHsbactnoitruYhctTangphu() {
        return hsbactnoitruYhctTangphu;
    }

    public void setHsbactnoitruYhctTangphu(String hsbactnoitruYhctTangphu) {
        this.hsbactnoitruYhctTangphu = hsbactnoitruYhctTangphu;
    }

    public Boolean getHsbactnoitruYhctDieutridonthuanyhct() {
        return hsbactnoitruYhctDieutridonthuanyhct;
    }

    public void setHsbactnoitruYhctDieutridonthuanyhct(Boolean hsbactnoitruYhctDieutridonthuanyhct) {
        this.hsbactnoitruYhctDieutridonthuanyhct = hsbactnoitruYhctDieutridonthuanyhct;
    }

    public Boolean getHsbactnoitruYhctDieutrikethopyhhd() {
        return hsbactnoitruYhctDieutrikethopyhhd;
    }

    public void setHsbactnoitruYhctDieutrikethopyhhd(Boolean hsbactnoitruYhctDieutrikethopyhhd) {
        this.hsbactnoitruYhctDieutrikethopyhhd = hsbactnoitruYhctDieutrikethopyhhd;
    }

    public String getHsbactnoitruYhctPhepchua() {
        return hsbactnoitruYhctPhepchua;
    }

    public void setHsbactnoitruYhctPhepchua(String hsbactnoitruYhctPhepchua) {
        this.hsbactnoitruYhctPhepchua = hsbactnoitruYhctPhepchua;
    }

    public String getHsbactnoitruYhctPhuongthuoc() {
        return hsbactnoitruYhctPhuongthuoc;
    }

    public void setHsbactnoitruYhctPhuongthuoc(String hsbactnoitruYhctPhuongthuoc) {
        this.hsbactnoitruYhctPhuongthuoc = hsbactnoitruYhctPhuongthuoc;
    }

    public String getHsbactnoitruYhctPhuonghuyet() {
        return hsbactnoitruYhctPhuonghuyet;
    }

    public void setHsbactnoitruYhctPhuonghuyet(String hsbactnoitruYhctPhuonghuyet) {
        this.hsbactnoitruYhctPhuonghuyet = hsbactnoitruYhctPhuonghuyet;
    }

    public String getHsbactnoitruYhctXoabop() {
        return hsbactnoitruYhctXoabop;
    }

    public void setHsbactnoitruYhctXoabop(String hsbactnoitruYhctXoabop) {
        this.hsbactnoitruYhctXoabop = hsbactnoitruYhctXoabop;
    }

    public String getHsbactnoitruYhctChedoan() {
        return hsbactnoitruYhctChedoan;
    }

    public void setHsbactnoitruYhctChedoan(String hsbactnoitruYhctChedoan) {
        this.hsbactnoitruYhctChedoan = hsbactnoitruYhctChedoan;
    }

    public String getHsbactnoitruYhctChedoholy() {
        return hsbactnoitruYhctChedoholy;
    }

    public void setHsbactnoitruYhctChedoholy(String hsbactnoitruYhctChedoholy) {
        this.hsbactnoitruYhctChedoholy = hsbactnoitruYhctChedoholy;
    }

    public String getHsbactnoitruYhctTienluong() {
        return hsbactnoitruYhctTienluong;
    }

    public void setHsbactnoitruYhctTienluong(String hsbactnoitruYhctTienluong) {
        this.hsbactnoitruYhctTienluong = hsbactnoitruYhctTienluong;
    }

    public String getHsbactnoitruYhctQtbldbcls() {
        return hsbactnoitruYhctQtbldbcls;
    }

    public void setHsbactnoitruYhctQtbldbcls(String hsbactnoitruYhctQtbldbcls) {
        this.hsbactnoitruYhctQtbldbcls = hsbactnoitruYhctQtbldbcls;
    }

    public String getHsbactnoitruYhctKetquaclschinh() {
        return hsbactnoitruYhctKetquaclschinh;
    }

    public void setHsbactnoitruYhctKetquaclschinh(String hsbactnoitruYhctKetquaclschinh) {
        this.hsbactnoitruYhctKetquaclschinh = hsbactnoitruYhctKetquaclschinh;
    }

    public String getHsbactnoitruYhctKetquagiaiphaubenh() {
        return hsbactnoitruYhctKetquagiaiphaubenh;
    }

    public void setHsbactnoitruYhctKetquagiaiphaubenh(String hsbactnoitruYhctKetquagiaiphaubenh) {
        this.hsbactnoitruYhctKetquagiaiphaubenh = hsbactnoitruYhctKetquagiaiphaubenh;
    }

    public String getHsbactnoitruYhctDieutriYhhd() {
        return hsbactnoitruYhctDieutriYhhd;
    }

    public void setHsbactnoitruYhctDieutriYhhd(String hsbactnoitruYhctDieutriYhhd) {
        this.hsbactnoitruYhctDieutriYhhd = hsbactnoitruYhctDieutriYhhd;
    }

    public String getHsbactnoitruYhctDieutriYhct() {
        return hsbactnoitruYhctDieutriYhct;
    }

    public void setHsbactnoitruYhctDieutriYhct(String hsbactnoitruYhctDieutriYhct) {
        this.hsbactnoitruYhctDieutriYhct = hsbactnoitruYhctDieutriYhct;
    }

    public String getHsbactnoitruYhctChandoanravienYhhd() {
        return hsbactnoitruYhctChandoanravienYhhd;
    }

    public void setHsbactnoitruYhctChandoanravienYhhd(String hsbactnoitruYhctChandoanravienYhhd) {
        this.hsbactnoitruYhctChandoanravienYhhd = hsbactnoitruYhctChandoanravienYhhd;
    }

    public String getHsbactnoitruYhctChandoanravienYhct() {
        return hsbactnoitruYhctChandoanravienYhct;
    }

    public void setHsbactnoitruYhctChandoanravienYhct(String hsbactnoitruYhctChandoanravienYhct) {
        this.hsbactnoitruYhctChandoanravienYhct = hsbactnoitruYhctChandoanravienYhct;
    }

    public String getHsbactnoitruYhctTinhtrangnbravien() {
        return hsbactnoitruYhctTinhtrangnbravien;
    }

    public void setHsbactnoitruYhctTinhtrangnbravien(String hsbactnoitruYhctTinhtrangnbravien) {
        this.hsbactnoitruYhctTinhtrangnbravien = hsbactnoitruYhctTinhtrangnbravien;
    }

    public String getHsbactnoitruYhctHdtccdtt() {
        return hsbactnoitruYhctHdtccdtt;
    }

    public void setHsbactnoitruYhctHdtccdtt(String hsbactnoitruYhctHdtccdtt) {
        this.hsbactnoitruYhctHdtccdtt = hsbactnoitruYhctHdtccdtt;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public String getHsbactnoitruYhctNguyennhan() {
        return hsbactnoitruYhctNguyennhan;
    }

    public void setHsbactnoitruYhctNguyennhan(String hsbactnoitruYhctNguyennhan) {
        this.hsbactnoitruYhctNguyennhan = hsbactnoitruYhctNguyennhan;
    }

    

    public DtDmNhanVien getHsbactnoitruYhctBsdieutri() {
        return hsbactnoitruYhctBsdieutri;
    }

    public void setHsbactnoitruYhctBsdieutri(DtDmNhanVien hsbactnoitruYhctBsdieutri) {
        this.hsbactnoitruYhctBsdieutri = hsbactnoitruYhctBsdieutri;
    }

    public DtDmNhanVien getHsbactnoitruYhctBsdieutri(boolean create) {
        if (create && hsbactnoitruYhctBsdieutri == null) {
            hsbactnoitruYhctBsdieutri = new DtDmNhanVien();
        }
        return hsbactnoitruYhctBsdieutri;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && getHsbacmMa() == null) {
            setHsbacmMa(new HsbaChuyenMon());
        }
        return getHsbacmMa();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbactnoitruYhctMa != null ? hsbactnoitruYhctMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietNoitruYhct)) {
            return false;
        }
        HsbaChiTietNoitruYhct other = (HsbaChiTietNoitruYhct) object;
        if ((this.hsbactnoitruYhctMa == null && other.hsbactnoitruYhctMa != null) || (this.hsbactnoitruYhctMa != null && !this.hsbactnoitruYhctMa.equals(other.hsbactnoitruYhctMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietNoitruYhct[hsbactnoitruYhctMa=" + hsbactnoitruYhctMa + "]";
    }

}
