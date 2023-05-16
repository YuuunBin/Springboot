let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		//console.log(data);

		//에이젝스 호출시 디폴트가 비동기 호출
		//에이젝스가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줍니다.(데이터타입이 제이슨타입이라 안적혀있어도)
		$.ajax({//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),//http 바디 데이터
			contentType: "application/json; charset=utf-8",//바디데이터가 어떤 타입인지(mime)
			dataTtpe: "json"//요청을 서버로 해서 응답이 왔을땐,기본적으로 모든것이 문자열(생긴게 json이라면)
		}).done(function(resp) {
			alert("글쓰기 완료되었습니다.");
			Cconsole.LOG(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});//에이젝스 통신을 이용하여 3개의 파라미터를 제이슨으로 변경하여 인서트 요청
	},


	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataTtpe: "json"
		}).done(function(resp) {
			alert("삭제 완료되었습니다.");
			Cconsole.LOG(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataTtpe: "json"
		}).done(function(resp) {
			alert("글 수정 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}
index.init();