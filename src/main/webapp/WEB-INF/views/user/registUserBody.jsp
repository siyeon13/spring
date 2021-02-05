<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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


		
				<form class="form-horizontal" role="form" action="/user/regist" method="post"
													enctype="multipart/form-data">
				
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERID" /></label>
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
					
					
					
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERNM" /></label>
						<div class="col-sm-10">
				<%-- 		<% String usernm = request.getParameter("userid");
								usernm = usernm == null ? "" : usernm; %> --%>
						
							<input type="text" class="form-control" id="usernm" name="usernm" value="${param.usernm}"
								placeholder="사용자 이름">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="PASS" /></label>
						<div class="col-sm-10">
					<%-- 	<% String pass = request.getParameter("pass");
								pass = pass == null ? "" : pass; %> --%>
							<input type="password" class="form-control" id="pass" name="pass" value="${param.pass}"
								placeholder="비밀번호">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="REG_DT" /></label>
						<div class="col-sm-10">
						<%-- <% String regdt = request.getParameter("regdt");
								regdt = regdt == null ? "" : regdt; %> --%>
							<input type="text" class="form-control" id="regdt" name="reg_dt" value="${param.regdt}"
								  >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ALIAS" /></label>
						<div class="col-sm-10">
					<%-- 	<% String alias = request.getParameter("alias");
								alias = alias == null ? "" : alias; %> --%>
							<input type="text" class="form-control" id="alias" name="alias" value="${param.alias}"
								placeholder="별명" >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR1" /></label>
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
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR2" /></label>
						<div class="col-sm-10">
						<%-- <% String addr2 = request.getParameter("addr2");
								addr2 = addr2 == null ? "" : addr2; %> --%>
							<input type="text" class="form-control" id="addr2" name="addr2" value="${param.addr2}"
								placeholder="상세주소" >
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ZIPCODE" /></label>
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
		
							<select name="lang">
								<option value="" >언어설정</option>
								<option value="ko" >한국어</option>
								<option value="en">영어</option>
							</select>
<script>
$(function(){
	$("select[name=lang]").val("${param.lang}");
	$("select[name=lang]").on("change", function(){
		document.location="/user/regist?lang=" + $(this).val();
		
	});
})

</script>
</body>
</html>
