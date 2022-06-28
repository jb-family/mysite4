<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<!-- ${pageContext.request.contextPath}의 의미는 /mysite4와 같다. -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		
		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
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
					<%-- <form action="${pageContext.request.contextPath}/api/guestbook/add 	" method="get"> --%>
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
						
					<!-- </form> -->	
					
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

</body>

<script type="text/javascript">
	
	//DOM생성 (DOM생성되면 메소드 실행)
	$(document).ready(function() {
		//리스트 요청
		fetchList();
	});
		
	//button -> submit 이벤트
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
/* 			url : "${pageContext.request.contextPath}/api/guestbook/add?name="+name+"&password="+password+"&content="+content, */		
			url : "${pageContext.request.contextPath}/api/guestbook/add",		
			type : "post",
			//contentType : "application/json",
			
			// data 2가지 방법 필드값넣어주거나 객체를 하나 만들어서 넣어주는방법
			/* data : {name: name, password : password, content : content}, */
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
		console.log("render()");
		
		var str = '';
		str += '<table class="guestRead">'; 
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
		str += '		<td><a href="">[삭제]</a></td>'; 
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