<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<!-- ${pageContext.request.contextPath}의 의미는 /mysite4와 같다. -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		
		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->
	
		<c:import url="/WEB-INF/views/includes/aside.jsp"></c:import>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
						<button id="btnTest" class="btn btn-primary">모달창</button>
						
					
					<!-- 리스트영역 -->
					<div id="listArea">
						
					</div>
					<!-- //리스트영역 -->
					
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

<!-- //////////////////////// modal /////////////////////// -->
<!-- 삭제 modal -->
<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
      
      비밀번호<input type="text" name="password" value=""><br>
      <input type="text" name="no" value="">
      
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
        <button type="button" id="btnModalDel" class="btn btn-danger">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->





</body>

<script type="text/javascript">

	 
	 
	//DOM생성(준비가 끝났을 때) (DOM생성되면 메소드 실행)
	$(document).ready(function() {
		//리스트 요청
		fetchList();
	});
	
	
	
	//button -> submit 이벤트
	//jquery 요청(jquery -> json)
	$("#btnSubmit").on("click", function() {
		console.log("저장버튼클릭");
		
		//데이터 가져오기 (value)
		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name='content']").val();

		var guestVo = {
			name: name,
			password: password,
			content: content
		};
		
		$.ajax({
			//url : "${pageContext.request.contextPath}/api/guestbook/add?name="+name+"&password="+password+"&content="+content,		
			url : "${pageContext.request.contextPath}/api/guestbook/add2",		
			type : "post",
			contentType : "application/json",	// 보낼때 json으로 보내려고 한다면 contentType : "application/json", 사용하면 된다.
			//기존 객체를 파라미터로 보내던것을 JSON.stringify 사용 시 객체를 JSON으로 바꿔준다.
			data : JSON.stringify(guestVo),	//파라미터 정리된다.
			
			dataType : "json",
			success : function(gVo){
				render(gVo, "up");
				
				//입력폼 초기화
				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	})
		
	
	
	
	
	
	/*
	//기존방식 jquery 파라미터
	//button -> submit 이벤트
	$("#btnSubmit").on("click", function() {
		console.log("저장버튼클릭");
		
		//데이터 가져오기 (value)
		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name='content']").val();
		console.log(name);
		var guestVo = {
			name: name,
			password: password,
			content: content
		};
		
		$.ajax({
			//url : "${pageContext.request.contextPath}/api/guestbook/add?name="+name+"&password="+password+"&content="+content,		
			url : "${pageContext.request.contextPath}/api/guestbook/add",		
			type : "post",
			//contentType : "application/json",
			
			// data 2가지 방법 필드값넣어주거나 객체를 하나 만들어서 넣어주는방법
			//data : {name: name, password : password, content : content},
			data : guestVo,	//파라미터 정리된다.
			
			dataType : "json",
			success : function(gVo){
				render(gVo, "up");
				
				//입력폼 초기화
				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	})
		 */
	
	// 리스트의 삭제버튼 눌렀을 때 이벤트
	$("#listArea").on("click", ".btnModal", function() {
		console.log("테스트버튼");
		//클릭한 버튼 변수에 할당
		var $this = $(this);
		console.log($this);
		
		//버튼의 데이터 꺼내서 다른변수에 할당
		var no = $this.data("no");
		
		//modal에 form 값 입력
		$("#delModal [name='password']").val("");
		$("#delModal [name='no']").val(no);
		
		
		//moal 띄우기(bootstrap)
		$("#delModal").modal("show");
		
	});
	
	// 리스트 > 삭제버튼 눌렀을 때 이벤트
	$("#btnModalDel").on("click", function(){
		console.log("체크체크");
		
		//데이터 모으기
		var no = $("#delModal [name='no']").val();
		var password = $("#delModal [name='password']").val();
		
		console.log(no);
		console.log(password);
		
		//데이터 객체에 담기
		var guestVo = {
				password : password,
				no : no
		};
		
	
		
		/*
		아래와 같은방법.(객체생성하는 방법)
		var guestVo = {};
		guestVo.password = password;
		guestVo.no = no;
		*/
		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : guestVo,

			dataType : "json",
			success : function(state){
				/*성공시 처리해야될 코드 작성*/
				console.log(state);
				
				//성공 시 삭제
				if(state === 'succeess') {
				//modal 닫기
					$("#t" + no).remove();
					$("#delModal").modal("hide");
				//실패 삭제x
				}else {
					
					alert("비밀번호를 다시 입력해주세요.");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	});
	
	
	
	//리스트 요청 메소드( + 그리기)
	function fetchList(){
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},
			
			dataType : "json",
			success : function(gList){
				/*성공시 처리해야될 코드 작성*/
				console.log(gList);
				//화면 data + html 그린다
				for(var i = 0; i < gList.length; i++) {
					render(gList[i], "down");	//vo --> 화면에 출력
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
		
		
	//리스트 1개씩 그리기
	function render(guestVo, opt) {
		
		var str = '';
		str += '<table id="t'+guestVo.no+'" class="guestRead">'; 
		str += '	<colgroup>'; 
		str += '		<col style="width: 10%;">'; 
		str += '		<col style="width: 40%;">'; 
		str += '		<col style="width: 40%;">'; 
		str += '		<col style="width: 10%;">'; 
		str += '	</colgroup>'; 
		str += '	<tr>'; 
		str += '		<td>' + guestVo.no + '</td>'; 
		str += '		<td>' + guestVo.name + '</td>'; 
		str += '		<td>' + guestVo.regDate + '</td>'; 
		//data를줄때엔 소문자만 인식한다. 예)personId -> data-personid
		str += '		<td><button class="btnModal" data-no="' + guestVo.no + '" >[삭제]</button></td>';
		str += '	</tr>'; 
		str += '	<tr>'; 
		str += '		<td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '	</tr>';	
		str += '</table>'; 
		
		
		if(opt === "down") {
			$("#listArea").append(str);	
		}else if(opt === "up") {
			$("#listArea").prepend(str);
		}else {
			console.log("opt오류");
		}
		
	}
		
		
	
</script>







</html>