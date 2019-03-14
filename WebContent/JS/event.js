function save() {
	var param = "occTime=" + $(".occTime").val() + "&locale=" + $(".didian").val() + $(".mozu").val() + "&department=" + $(".department").val() + "&level=" + $(".level").val() + "&discPerson=" + $(".discPerson").val() + "&discTime=" + $(".discTime").val() + "&handlePerson=" + $(".handlePerson").val() + "&eventDesc=" + $(".eventDesc").val() + "&effBus=" + $(".effBus").val() + "&incidence=" + $(".incidence").val() + "&effTime=" + $(".effTime").val() + "&eventHandle=" + $(".eventHandle").val() + "&eventReason=" + $(".eventReason").val() + "&eventResult=" + $(".eventResult").val();
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
		}
	})
}

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

function eventSearch(startTime, endTime) {
	var param = "startTime=" + startTime + "&endTime=" + endTime;
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