function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
        	/*
			var cbTmp = document.getElementById(prefix_component + "DM_DAN_TOC");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_TINH");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_HUYEN");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_XA");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_KHOA");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_NGHE_NGHIEP");
			cbTmp.dojoType="dijit.form.ComboBox";	
			cbTmp = document.getElementById(prefix_component + "DT_DM_PHAU_THUAT");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DT_DM_VO_CAM");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN");
			cbTmp.dojoType="dijit.form.ComboBox";			
			*/
            timer.setTimeout(function(){focusInit();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
onTuoi();
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById(prefix_component + "__magiay").focus(); 
}

function oncompleteOfHSBA(){
onTuoi();
	if (document.getElementById(prefix_component + "nofoundHSBA").value == "true") {
		document.getElementById(prefix_component + "nofoundHSBA").value = "false";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById(prefix_component + "__sobenhan").focus();
		return;
	}
}

function oncompleteOfGiayCNPT(){
onTuoi();
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__magiay").value = "";
		document.getElementById(prefix_component + "__magiay").focus();
		return;
	} 
	document.getElementById("__divInPhieu").style.display = "block";
}

function oncompleteOfTiepTucNhap(){
onTuoi();
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
}

function oncompleteOfGhiNhan(){
onTuoi();
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__magiay").focus();		
		return;
	}
	document.getElementById("__divInPhieu").style.display = "block";
	document.getElementById("__divGhiNhan").style.display = "none";
}

function onChangeTK(value){
onTuoi();
	var now = new Date();	
	var d = new Date(now.getTime() + (value * 24 * 60 * 60 * 1000));	
	var rs = "";
	var ndate = d.getDate();	
	if (ndate<10) {
		rs = rs + "0" + ndate + "/";
	}else{
		rs = rs + ndate + "/";
	}
	var nmonth = d.getMonth();
	nmonth++;
	
	if (nmonth<10) {
		rs = rs + "0" + nmonth + "/";
	}else{
		rs = rs + nmonth + "/";
	}	
	var nyear = d.getFullYear();
	var rs = rs + nyear;
	document.getElementById(prefix_component + "__ngay").value = rs;
}

function onChangeNTK(value){
onTuoi();
	var str = value.split("/");
	var crr = new Date();
	var d = new Date();
	d.setDate(str[0]);
	d.setMonth(str[1]-1);
	d.setFullYear(str[2]);
	var rs = (d.getTime() - crr.getTime())/(24 * 60 * 60 * 1000);
	document.getElementById(prefix_component + "__taikhamsau").value = rs;	
}
function onTuoi(){
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
		
}