function setNgayGioCapNhat() {
	for (i = 0; i < document.forms[0].elements.length; i++) {
		var idString = document.forms[0].elements[i].id;
		if (document.forms[0].elements[i].type == "text" && idString.length > 9) {
			var testStr = idString.substring(idString.length - 9,
					idString.length);
			if (testStr == "Ngaygiocn") {
				var curDate = new Date;
				document.forms[0].elements[i].value = curDate.getTime();
			}
		}
	}
}

function trim(str) {
	while (str.substring(0, 1) == ' ') {
		str = str.substring(1, str.length);
	}
	while (str.substring(str.length - 1, str.length) == ' ') {
		str = str.substring(0, str.length - 1);
	}
	return str;
}

function getMaNVformFullName(myFullName) {
	var s1 = "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ";
	var s2 = "AAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";
	var s2Arr = s2.split("");
	var hoTen = myFullName;
	hoTen = trim(hoTen);
	var hoTenArr = hoTen.split(" ");
	var ten = hoTenArr[(hoTenArr.length) - 1];
	var ma_part1 = "";

	if (hoTen != "") {
		if (ten.length > 2) {
			ma_part1 = ten.substr(0, 3);
		} else if (ten.length == 2) {
			ma_part1 = ten + "-";
		} else if (ten.length == 1) {
			ma_part1 = ten + "--";
		}
	}

	ma_part1 = ma_part1.toUpperCase();

	var ma_part1_arr = ma_part1.split("");

	var ma_part1_ok = "";
	for ( var i = 0; i < ma_part1_arr.length; i++) {
		if (s1.indexOf(ma_part1_arr[i]) >= 0) {
			ma_part1_ok += s2Arr[s1.indexOf(ma_part1_arr[i])];
		} else {
			ma_part1_ok += ma_part1_arr[i];
		}
	}

	return ma_part1_ok;
}

function getMaThuocformTenThuoc(myTenThuoc) {
	var s1 = "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ";
	var s2 = "AAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";
	var s2Arr = s2.split("");
	var TenThuoc = myTenThuoc;
	TenThuoc = trim(TenThuoc);
	var TenThuocArr = TenThuoc.split(" ");
	var ten = TenThuocArr[0];
	var ma_part1 = "";

	if (TenThuoc != "") {
		if (ten.length > 2) {
			ma_part1 = ten.substr(0, 3);
		} else if (ten.length == 2) {
			ma_part1 = ten + "-";
		} else if (ten.length == 1) {
			ma_part1 = ten + "--";
		}
	}

	ma_part1 = ma_part1.toUpperCase();

	var ma_part1_arr = ma_part1.split("");

	var ma_part1_ok = "";
	for ( var i = 0; i < ma_part1_arr.length; i++) {
		if (s1.indexOf(ma_part1_arr[i]) >= 0) {
			ma_part1_ok += s2Arr[s1.indexOf(ma_part1_arr[i])];
		} else {
			ma_part1_ok += ma_part1_arr[i];
		}
	}

	return ma_part1_ok;
}


function getPath(){
	var url_temp = window.location + "";
	var arr = url_temp.split(":");
	if(arr.length > 1){
		javascript:location = arr[0] +":"+ arr[1]+ ":8088/YTDTWeb/loginND.seam";
	}
}
