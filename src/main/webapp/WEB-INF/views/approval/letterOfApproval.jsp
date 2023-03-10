<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/head.jsp"%>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${path}/css/approvalStyle.css">
	<link rel="stylesheet" href="${path}/css/appAutocomplete.css">
	<script type="text/javascript" src="${path}/js/approval.js"></script>
</head>

<body>
	<!-- header -->
	<%@ include file="../includes/header.jsp"%>

	<!-- contents start -->
	<div class="tjcontainer">
		<!-- menu list -->
		<%@ include file="../includes/menu_bar.jsp"%>
		<div class="con_middle">
			<div class="nav">
				<ul>
					<li><a href="${path}/approval/approvalMain"><img
							src="../images/home.png" alt="home" width="18px"></a>&#62;</li>
					<li><a href="${path}/approval/approvalMain">전자결재</a>&#62;</li>
					<li><a href="${path}/approval/letterOfApproval">품의서 작성</a></li>
				</ul>
			</div>

			<form action="${path}/approval/letterOfApproval_insert" method="post" id = "loaWriteForm"
				name="loaWriteForm" onsubmit="return check_onclick()"
				enctype="multipart/form-data">
				<div class="cash-form-section">
					<div class="cash-disbursement">
						<table border="2"
							style="width: 100%; font-size: 20px; border-collapse: collapse;">
							<tr>
								<td rowspan="2" colspan="4"
									style="width: 300px; height: 120px; font-size: 40px; font-weight: 600;">품
									의 서</td>
								<td rowspan="2"
									style="width: 20px; padding-top: 30px; font-size: 25px;">
									<span style="border: none; width: 80px;">결 재</span>
								</td>
								<td style="height: 30px; width: 100px;">최초승인자</td>
								<td style="width: 100px;">중간승인자</td>
								<td style="width: 100px;">최종승인자</td>
							</tr>
							<tr>
								<td style="">
									<input type="hidden" value="" id="firstApprover" name="firstApprover" readonly="readonly" class="nameView">
									<input type="text" value="" id="firstApproverName" name="firstApproverName" readonly="readonly" class="nameView">
									<input type="button" value="검색" class="searchMember" id="firstBtn" name="firstApprover" onclick="showEmployeeSearchForm(this)">
								</td>
								<td>
									<input type="hidden" value="" id="interimApprover" name="interimApprover" readonly="readonly" class="nameView">
									<input type="text" value="" id="interimApproverName" name="interimApproverName" readonly="readonly" class="nameView">
									<input type="button" value="검색" class="searchMember" id="secondBtn" name="interimApprover" onclick="showEmployeeSearchForm(this)">
								</td>
								<td>
									<input type="hidden" value="" id="finalApprover" name="finalApprover" readonly="readonly" class="nameView">
									<input type="text" value="" id="finalApproverName" name="finalApproverName" readonly="readonly" class="nameView">
									<input type="button" value="검색" class="searchMember" id="thirdBtn" name="finalApprover" onclick="showEmployeeSearchForm(this)">
								</td>
							</tr>
							<tr>
								<td colspan="2" style="height: 70px;">
									<button class="send-open" type="button">수신참조자 +</button>
								</td>
								<td colspan="6" style="height: 70px;"><textArea readonly
										name="referList" id="referList"
										style="border: none; margin-bottom: -12px; font-size: 19px; width: 600px; height: 60px; text-align: center; resize: none;"></textArea>
								</td>
							</tr>
							<tr>
								<td style="height: 70px; width: 80px;">성 명</td>
								<td><input type="text" name="writeName"
									value="${EmpVO.name}" readonly></td>
								<td style="width: 80px;">부 서</td>
								<td><input type="text" value="${deptname}"
									readonly></td>
								<td style="width: 80px;">직 급</td>
								<td colspan="3"><input type="text"
									value="${EmpVO.position}" readonly></td>
							</tr>
							<tr>
								<td style="height: 70px; width: 80px;">제 목</td>
								<td colspan="8"><input type="text" name="loaTitle"
									id="loaTitle"></td>
							</tr>
							<tr>
								<td colspan="8" style="height: 90px;">

									<input type="file" id="inputFile" name="appLoaFileUpload" />
								</td>
							</tr>
							<tr>
								<td colspan="8" style="height: 70px; width: 80px;">품의사유 및
									상세내용</td>
							</tr>
							<tr>
								<td colspan="8"><textarea name="loaContent" id="loaContent"
										cols="151px" rows="11px"
										style="width: 100%; height: 100%; border: none; resize: none; overflow: hidden; font-size: 25px;"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="8"
									style="text-align: center; height: 100px; border-bottom: none;">위와
									같은 품의사유로, 검토 후 결재 바랍니다.</td>
							</tr>
							<tr style="border: white;">
								<td>
									<input type="text" style="text-align: center; font-size: 30px;"
									readonly value="${serverTime}">
								</td>
							</tr>
							<tr>
			                    <td colspan="8" style="text-align: right; height: 100px; padding-right: 50px;">
			                        <input type="button" name="proposer" id="proposer" style="font-size:15px; width:70px; height:30px; border: none; text-align: center; border-radius:20px; margin-right:10px" value="서명" />
			                        신청자 : 
			                        <textArea name="proposerText" id="proposerText" style="width:130px; border: none; text-align: center; resize: none; font-size:24px; margin-bottom:-42px"></textArea>
			                        (인)
			                    </td>
							</tr>
						</table>
					</div>
					<div id="button">
						<input type="hidden" name="appKinds" value="품의서">
						<button type="submit" class="goToLeave" >등록</button>
						<input type="text" style="border: none; width: 40px;" disabled>
						<button type="reset" class="resetLeave" onclick="">취소</button>
					</div>
				</div>
			</form>
		</div>
		
	<script>
		function check_onclick() {

			if ($('#loaContent').val() == "" || $('#loaTitle').val() == "") {
				alert('상세내용 또는 제목란이 비어있습니다. 확인 후 등록하세요.');
				return false;

			} if ($('#proposer').val() == "") {
				alert('결재서명 후 \n결재를 진행해주세요.');
				return false;
			}
			return true;
		}
	</script>	
	
	<!-- 서명 클릭 스크립트  -->
	<script>
       $("#proposer").one("click",function(){
           var proposerValue = $("input[name='writeName']").val();
        
           $("#proposerText").append(proposerValue);
       });
    </script>
    
		<!-- right -->
		<%@ include file="../includes/con_right.jsp"%>
	</div>
	
	<!-- 수신참조자 modal/script/ajax -->
	<%@ include file="../approval/selectReferList.jsp"%> 

	<!-- 사원 검색  -->
	<%@ include file="../approval/searchEmployee.jsp"%>

	<!-- footer -->
	<%@ include file="../includes/footer.jsp"%>

	<!-- 일정 등록 Modal -->
	<%@ include file="../includes/todoModal.jsp"%>
</html>