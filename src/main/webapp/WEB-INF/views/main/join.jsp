<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<title>JSP 게시판</title>
<meta http-equiv="Conetent-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/js/bootstrap.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery.min.js" charset="UTF-8"></script>
<script src="${path}/resources/js/address.js" charset="UTF-8"></script>

<script src="${path}/resources/js/join.js" charset="UTF-8"></script>

</head>

<body>

<jsp:include page="../mainNav.jsp" />

<!-- 메인 컨텐츠 -->
<div class="container">
	<div class="col-lg-3"></div>
	
	<div class="col-lg-6">
		<div class="jumbotron">
			<form method="POST" action="./joinAction">
				<h3 style="text-align: center;">회원가입</h3>
				
				<!-- 아이디 입력에 대한 공간 할당 -->
				<!-- bs : row - 공간을 12개로 분할 -->
				<div class="row">
				
					<!-- 아이디 입력 -->
					<div class="form-group col-sm-9 col-md-9 col-lg-9">
						<input type="text" class="form-control" id="user_id" autocomplete="off" name="user_id" placeholder="아이디">
					</div>
						
					<div class="col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 15px;">
						<button type="button" class="btn btn-success btn-block" id="id_check">중복체크</button>
					</div>
				</div>
				
				<!-- 비밀번호 입력에 대한 공간 할당 -->
				<div class="row">
					<!-- 비밀번호 입력 -->
					<div class="form-group col-sm-12 col-md-12 col-lg-12">
						<input type="password" class="form-control" id="user_pw" name="user_pw" placeholder="비밀번호">
					</div>
				</div>
				
				<!-- 비밀번호 확인에 대한 공간 할당 -->
				<div class="row">
					<!-- 비밀번호 입력 -->
					<div class="form-group col-sm-12 col-md-12 col-lg-12">
						<input type="password" class="form-control" id="user_pw2" name="user_pw2" placeholder="비밀번호 확인">
					</div>
				</div>
				
				<!-- 비밀번호 체크에 대한 공간 할당 -->
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<h6 id="diff_pw" class="text-danger" style="display: none;">비밀번호가 서로 다릅니다.</h6>
						<h6 id="equal_pw" class="text-success" style="display: none;">확인되었습니다.</h6>
					</div>
				</div>
				
				<!-- 이름 입력에 대한 공간 할당 -->
				<div class="row">
					<!-- 이름 입력 -->
					<div class="form-group col-sm-12 col-md-12 col-lg-12">
						<input type="text" class="form-control" name="user_name" placeholder="이름">
					</div>
				</div>
				
				<!-- 성별 선택에 대한 공간 할당 -->
				<div class="row">
					<!-- 성별 선택 -->
					<div class="form-group" style="text-align: center;">
						<div class="btn-group" data-toggle="buttons">
							<!-- label : UI 요소에 라벨을 정의하는 HTML 태그 -->
							<label class="btn btn-primary active">
								<!--  autocomplete 속성 : 과거 기록을 dropdown 형식으로 보여줄지를 선택 - true : 보여줌 , false : 보여주지않음 -->
								<input type="radio" name="user_gender" autocomplete="off" value="m" checked>남자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="user_gender" autocomplete="off" value="w">여자
							</label>
						</div>
					</div>
				</div>
				
				<!-- 이메일 입력에 대한 공간 할당 -->
				<div class="row">
					<!-- 이메일 입력 -->
					<div class="form-group col-sm-9 col-md-9 col-lg-9">
						<input type="email" class="form-control" id="user_email" name="user_email" placeholder="이메일" readonly>
					</div>
						
					<div class="col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 15px;">
						<button type="button" class="btn btn-success btn-block" 
							data-toggle="modal" data-target="#emailAuthModal">인증</button>
					</div>
				</div>
				
				<!-- 주소 입력에대한 공간 할당 -->
				<div class="row">
					<!-- 우편번호 입력 -->
					<div class="form-group col-sm-3 col-md-3 col-lg-3">
						<input type="text" class="form-control" id="addr1" name="addr1" placeholder="우편번호" readonly>
					</div>
						
					<div class="col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 15px;">
						<button type="button" class="btn btn-success btn-block"
							data-toggle="modal" data-target="#addrModal">주소검색</button>
					</div>
				</div>
				<div class="row">
					<!-- 주소 입력 -->
					<div class="form-group col-sm-12 col-md-12 col-lg-12">
						<input type="text" class="form-control" id="addr2" name="addr2" placeholder="주소" readonly>
					</div>
				</div>
				
				<!-- 상세주소 입력에 대한 공간 할당 -->
				<div class="row">
					<!-- 상세주소 입력 -->
					<div class="form-group col-sm-12 col-md-12 col-lg-12">
						<input type="text" class="form-control" id="addr3" name="addr3" placeholder="상세주소">
					</div>
				</div>
				
				<!-- 제출 버튼 -->
				<div class="form-group">
					<input type="submit" class="btn btn-primary form-control" value="회원가입">
				</div>
				
			</form>
		</div>
	</div>
	
	<div class="col-lg-3"></div>
</div>
<!-- 메인 컨텐츠 종료 -->
<!-- 이메일 인증 모달 -->
<div id="emailAuthModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">이메일 인증</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-8">
						<input type="email" class="form-control" id="user_mail_modal" placeholder="이메일">
					</div>
					<div class="col-lg-4">
						<button type="button" class="btn btn-success btn-block" id="sendAuthBtn">인증번호 전송</button>
					</div>
				</div>
				<div class="row" id="authDiv" style="margin-top: 15px; display: none;">
					<div class="col-lg-4">
						<input type="text" class="form-control" id="auth_num" placeholder="인증번호">
					</div>
					<div class="col-lg-4">
						<button type="button" class="btn btn-primary btn-block" id="authMailBtn">인증</button>
					</div>
					<div class="col-lg-4"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 이메일 인증 모달 종료 -->
<!-- 주소 검색 모달 -->
<div id="addrModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">주소 검색</h4>
			</div>
			<div class="modal-body">
				<div class="row" id="postcodify"></div>
				
				<script>
					$('#postcodify').postcodify({
						insertPostcode5  : '#entry_postcode5',
						insertAddress    : '#entry_address',
						insertExtraInfo : '#entry_extra_info',
						userFullJibeon   : true,
						mapLinkProvider  : 'google',
						ready : function() {
							$('#postcodify div.postcode_search_status.empty').hide();
						},
						beforeSearch : function() {
							$('#entry_box').hide();
						},
						afterSearch : function(selectedEntry) {
							$('#postcodify div.postcode_search_result').remove();
							$('#postcodify div.postcode_search_status.summary').hide();
							$('#entry_box').show();
							$('#entry_details').focus();
						}
					});
				</script>
				
				<div>
					<p>
						<label for="entry_postcode5">우편번호</label>
						<input type="text" class="form-control" id="entry_postcode5" readonly>
					</p>
					<p>
						<label for="entry_address">도로명주소</label>
						<input type="text" class="form-control" id="entry_address" readonly>
					</p>
					<p>
						<label for="entry_extra_info">지명주소</label>
						<input type="text" class="form-control" id="entry_extra_info" readonly>
					</p>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="getAddrBtn" class="btn btn-primary" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>
<!-- 주소 검색 모달 종료 -->

<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);
});
</script>

</body>

</html>








