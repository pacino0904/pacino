//事件单保存
function save() {
	var nowTime = new Date();
	var tjsj = nowTime.getFullYear() + "-" + (nowTime.getMonth() + 1) + "-" + nowTime.getDate() + " " + nowTime.getHours() + ":" + nowTime.getMinutes() + ":" + nowTime.getSeconds();
	var param = "occTime=" + $(".occTime").val() + "&locale=" + $(".didian").val() + $(".mozu").val() + "&department=" + $(".department").val() + "&level=" + $(".level").val() + "&discPerson=" + $(".discPerson").val() + "&discTime=" + $(".discTime").val() + "&handlePerson=" + $(".handlePerson").val() + "&eventDesc=" + $(".eventDesc").val() + "&effBus=" + $(".effBus").val() + "&incidence=" + $(".incidence").val() + "&effTime=" + $(".effTime").val() + "&eventHandle=" + $(".eventHandle").val() + "&eventReason=" + $(".eventReason").val() + "&eventResult=" + $(".eventResult").val() + "&tjsj=" + tjsj + "&eventNumber=" + sjdbh;
	$.ajax({
		type: "POST",
		url: "/myproject/EventAddServlet",
		data: param,
		dataType: "json",
		success: function (data) {
			console.log(param);
		}
	})
}

//变更申请单保存
function bgsqSave() {
	var nowTime = new Date();
	var tjsj = nowTime.getFullYear() + "-" + (nowTime.getMonth() + 1) + "-" + nowTime.getDate() + " " + nowTime.getHours() + ":" + nowTime.getMinutes() + ":" + nowTime.getSeconds();
	var lxRadios = document.getElementsByName('bglx');
	var bglx;
	for (var i = 0, length = lxRadios.length; i < length; i++) {
		if (lxRadios[i].checked) {
			bglx = lxRadios[i].value;
			break;
		}
	}
	var djRadios = document.getElementsByName('bgdj');
	var bgdj;
	for (var i = 0, length = djRadios.length; i < length; i++) {
		if (djRadios[i].checked) {
			bgdj = djRadios[i].value;
			break;
		}
	}
	var param = "bglx=" + bglx + "&bgxm=" + $(".bgxm").val() + "&bgdj=" + bgdj + "&jhssr=" + $(".jhssr").val() + "&jhkssj=" + $(".jhkssj").val() + "&jhjssj=" + $(".jhjssj").val() + "&bgsqr=" + $(".bgsqr").val() + "&sqsj=" + $(".sqsj").val() + "&bgyy=" + $(".bgyy").val() + "&bgfa=" + $(".bgfa").val() + "&bghtfa=" + $(".bghtfa").val() + "&tjsj=" + tjsj + "&changeNumber=" + bgdbh;
	$.ajax({
		type: "POST",
		url: "/myproject/addChange",
		data: param,
		dataType: "json",
		success: function (data) {
			console.log(param);
		}
	})
}

//查看事件单详情
function detail(dataid) {
	var param = "id=" + dataid;
	$.ajax({
		type: "POST",
		url: "/myproject/detailServlet",
		data: param,
		dataType: "json",
		success: function (data) {
			$("#occTime").html(data.occTime);
			$("#locale").html(data.locale);
			$("#department").html(data.department);
			$("#level").html(data.level);
			$("#discPerson").html(data.discPerson);
			$("#discTime").html(data.discTime);
			$("#handlePerson").html(data.handlePerson);
			$("#eventDesc").html(data.eventDesc);
			$("#effBus").html(data.effBus);
			$("#incidence").html(data.incidence);
			$("#effTime").html(data.effTime);
			$("#eventHandle").html(data.eventHandle);
			$("#eventReason").html(data.eventReason);
			$("#eventResult").html(data.eventResult);
			$("#sjdbhTd").html(data.eventNumber);
		}
	})
}

//查看变更单详情
function changeDetail(dataid) {
	var param = "id=" + dataid;
	$.ajax({
		type: "POST",
		url: "/myproject/detailChange",
		data: param,
		dataType: "json",
		success: function (data) {
			$("#bglx").html(data.bglx);
			$("#bgxm").html(data.bgxm);
			$("#bgdj").html(data.bgdj);
			$("#jhssr").html(data.jhssr);
			$("#jhkssj").html(data.jhkssj);
			$("#jhjssj").html(data.jhjssj);
			$("#bgsqr").html(data.bgsqr);
			$("#sqsj").html(data.sqsj);
			$("#bgyy").html(data.bgyy);
			$("#bgfa").html(data.bgfa);
			$("#bghtfa").html(data.bghtfa);
			$("#changeNumber").html(data.changeNumber);
		}
	})
}

//事件单删除
function eventDelete(dataid) {
	var param = "id=" + dataid;
	$.ajax({
		type: "POST",
		url: "/myproject/deleteEvent",
		data: param,
		dataType: "json",
		success: function (data) {
			alert("成功!");
		}
	})
}

//变更单删除
function changeDelete(dataid) {
	var param = "id=" + dataid;
	$.ajax({
		type: "POST",
		url: "/myproject/deleteChange",
		data: param,
		dataType: "json",
		success: function (data) {
			alert("成功!");
		}
	})
}

//事件单搜索功能
function eventSearch() {
	var startTime;
	if ($("#start-time").val()) {
		startTime = $("#start-time").val();
	} else {
		startTime = null;
	}
	var endTime;
	if ($("#end-time").val()) {
		endTime = $("#end-time").val();
	} else {
		endTime = null;
	}
	var keyWord;
	if ($("#key-word").val()) {
		keyWord = $("#key-word").val();
	} else {
		keyWord = null;
	}

	var param = "startTime=" + startTime + "&endTime=" + endTime + "&keyWord=" + keyWord;
	$.ajax({
		type: "POST",
		url: "/myproject/searchEvent",
		data: param,
		dataType: "json",
		success: function (data) {
			console.log(data);
		}
	})
}