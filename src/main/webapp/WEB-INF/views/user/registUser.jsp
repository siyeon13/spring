<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	//주소검색 버튼이 클릭 되었을때 
	$("#addrBtn").on("click",function(){
		
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            console.log(data)
            
            $("#addr1").val(data.roadAddress);	//도로주소
            $("#zipcode").val(data.zonecode);		//우편번호
            
            //사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
            $("#addr2").focus();
        }
    }).open();
	});
});
</script>
</head>

<body>



	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JSP/SPRING</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="/user/allUser">전체 사용자</a></li>
					<li class="active"><a href="${cp}/user/emplist">직원</a></li>
					<li class="active"><a href="${cp}/user/pagingUser?page=1&pageSize=5">사용자 페이징 리스트</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<%UserVo vo= (UserVo)request.getAttribute("user"); %>

				contextPath el
				<form class="form-horizontal" role="form" action="/user/regist" method="post"
													enctype="multipart/form-data">
				
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
					<%-- 	<% String userid = request.getParameter("userid");
								userid = userid == null ? "" : userid; %> --%>
							<input type="text" class="form-control" id="userid" name="userid" value="${param.userid} "
								placeholder="사용자 아이디" />
								
								<form:errors path="userVo.userid" />
								
						<input type="file" class="form-control" name="profile"/>
						</div>
					</div>
					<div class="form-group">
					
					
					
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
				<%-- 		<% String usernm = request.getParameter("userid");
								usernm = usernm == null ? "" : usernm; %> --%>
						
							<input type="text" class="form-control" id="usernm" name="usernm" value="${param.usernm}"
								placeholder="사용자 이름">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
					<%-- 	<% String pass = request.getParameter("pass");
								pass = pass == null ? "" : pass; %> --%>
							<input type="password" class="form-control" id="pass" name="pass" value="${param.pass}"
								placeholder="비밀번호">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
						<%-- <% String regdt = request.getParameter("regdt");
								regdt = regdt == null ? "" : regdt; %> --%>
							<input type="text" class="form-control" id="regdt" name="reg_dt" value="${param.regdt}"
								  >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
					<%-- 	<% String alias = request.getParameter("alias");
								alias = alias == null ? "" : alias; %> --%>
							<input type="text" class="form-control" id="alias" name="alias" value="${param.alias}"
								placeholder="별명" >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
					<%-- 	<% String addr1 = request.getParameter("addr1");
								addr1 = addr1 == null ? "" : addr1; %> --%>
							<input type="text" class="form-control" id="addr1" name="addr1" value="${param.addr1}"
								placeholder="도로주소" readonly="readonly">
						</div>
						<div class="col-sm-2">
								<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
						<%-- <% String addr2 = request.getParameter("addr2");
								addr2 = addr2 == null ? "" : addr2; %> --%>
							<input type="text" class="form-control" id="addr2" name="addr2" value="${param.addr2}"
								placeholder="상세주소" >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호코드</label>
						<div class="col-sm-10">
						<%-- <% String zipcode = request.getParameter("zipcode");
								zipcode = zipcode == null ? "" : zipcode; %> --%>
							<input type="text" class="form-control" id="zipcode" name="zipcode" value="${param.zipcode}"
								placeholder="우편번호코드"  readonly="readonly">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
