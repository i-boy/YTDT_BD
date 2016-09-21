function init() {
	
    if (window.google && google.gears) {
    	
        try {
			//db = google.gears.factory.create('beta.database');
			setAttrForCombobox(prefix_component + "DT_DM_LY_DO_CV_MA","DT_DM_LY_DO_CV_span","DT_DM_LY_DO_CV","getDtDmLyDoCv()","","","");
			setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");
			setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");
			setAttrForCombobox(prefix_component + "DM_BENH_VIEN_MA","DM_BENH_VIEN_span","DM_BENH_VIEN","getDmBenhVien()","","","");
			setAttrForCombobox(prefix_component + "BENHVIEN_MA_2","DM_BENH_VIEN_span2", "DM_BENH_VIEN__2","getDmBenhVien()","","","");
			
			timer.setTimeout(function(){focusInit();},100);						
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById(prefix_component + "__magiay").focus(); 
}

function oncompleteOfTiepTucNhap(){
	document.getElementById("DT_DM_LY_DO_CV").value="";
	document.getElementById("DT_DM_NHAN_VIEN__1").value="";
	document.getElementById("DT_DM_NHAN_VIEN__2").value="";
	document.getElementById("DM_BENH_VIEN").value="";
	document.getElementById("DM_BENH_VIEN__2").value="";
	
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
}

function oncompleteOfHSBA(){
	if (document.getElementById(prefix_component + "nofoundHSBA").value == "true") {
		document.getElementById(prefix_component + "nofoundHSBA").value = "false";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById(prefix_component + "__sobenhan").focus();
		return;
	}
	myOnblurTextbox(prefix_component + "DT_DM_LY_DO_CV_MA", "DT_DM_LY_DO_CV");
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_2", "DT_DM_NHAN_VIEN__2");
	myOnblurTextbox(prefix_component + "DM_BENH_VIEN_MA", "DM_BENH_VIEN");
	myOnblurTextbox(prefix_component + "BENHVIEN_MA_2", "DM_BENH_VIEN__2");
	document.getElementById("__divInPhieu").style.display = "block";
}

function oncompleteOfGiayChuyenVien(){
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__magiay").value = "";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById("DT_DM_LY_DO_CV").value="";
		document.getElementById("DT_DM_NHAN_VIEN__1").value="";
		document.getElementById("DT_DM_NHAN_VIEN__2").value="";
		document.getElementById("DM_BENH_VIEN").value="";
		document.getElementById("DM_BENH_VIEN__2").value="";
		document.getElementById(prefix_component + "__magiay").focus();
		return;
	} 
	myOnblurTextbox(prefix_component + "DT_DM_LY_DO_CV_MA", "DT_DM_LY_DO_CV");
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_2", "DT_DM_NHAN_VIEN__2");
	myOnblurTextbox(prefix_component + "DM_BENH_VIEN_MA", "DM_BENH_VIEN");
	myOnblurTextbox(prefix_component + "BENHVIEN_MA_2", "DM_BENH_VIEN__2");
	document.getElementById("__divInPhieu").style.display = "block";
}

function oncompleteOfGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__magiay").focus();		
		return;
	}
	document.getElementById("__divGhiNhan").style.display = "none";
	document.getElementById("__divInPhieu").style.display = "block";
}