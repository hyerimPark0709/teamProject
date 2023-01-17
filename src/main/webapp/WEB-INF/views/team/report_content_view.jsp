<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../includes/head.jsp" %>
</head>

<body>

	<!-- haeder -->
	<%@ include file="../includes/header.jsp" %>

	<!-- contents start -->
	<div class="tjcontainer">
	
		<!-- menu list -->	
		<%@ include file="../includes/menu_bar.jsp" %>
		
		<!-- main -->
		<div class="con_middle">
			<div class="nav">
				<ul>
					<li><a href="${path}/main"><img src="${path}/images/home.png" alt="home" width="18px"></a>&#62;</li>
					<li><a href="#">팀 커뮤니티</a>&#62;</li>
					<li><a href="#">업무 보고</a></li>
				</ul>
			</div>
			<!-- =================================contents================================================= -->
			<fmt:requestEncoding value="UTF-8" />
			<c:set var="vo" value="${ReportVO}"></c:set>

			<c:set var="content" value="${fn:replace(vo.content, '<', '&lt;') }"></c:set>
			<c:set var="content" value="${fn:replace(content, '>', '&gt;') }"></c:set>
			<c:set var="content" value="${fn:replace(content, enter, '<br/>') }"></c:set>

			<div class="content_view">

				<h1>${vo.title}</h1>
				<h3>${vo.type}</h3>
				<div style="display: flex; line-height: 20px">
					<h5 style="width: 95%"> <fmt:formatDate value="${vo.writedate}" pattern="yyyy-MM-dd(E), aa h시 mm분" /> </h5>
					<div style="display: flex ">
						<img src="${path}/images/thums.png" alt="thums" width="14px" height="14px" style="padding: 3px">
						<h5>${vo.hit}</h5>
					</div>
				</div>
				<hr/>
				<div style="height: 35%"><p>${content}</p></div>
				
				<c:if test="${vo.attachedfile != null}">
					<div class="attach">
						<img alt="" src="${path}/images/clip.png" width="15x" style="margin: 4px 1px">
						<a href="${path}/Download?filename=${vo.realfilename}">${vo.attachedfile}</a>
					</div>					
				</c:if>
					
				
				<div style="height: 290px">
					<tr>
						<c:if test="${vo.name == EmpVO.name}">
						<div align="right">
							<td>
								<input type="button" value="수정" onclick="location.href='report_service?mode=2&idx=${vo.idx}&currentPage=${currentPage}'" />
								<input type="button" value="삭제" onclick="location.href='report_service?mode=3&idx=${vo.idx}'" />
							</td>
						</div>
						</c:if>
					</tr>
				<hr/>
				</div>
				<div align="right">
					<input type="button" value="돌아가기" onclick="location.href='report_list?currentPage=${currentPage}&'"/>
                </div>
			</div>
			<!-- =================================contents================================================= -->
		
		</div>
		<!-- main -->
		
		<!-- right -->
		<%@ include file="../includes/con_right.jsp" %>
		
	</div>
	<!-- contents end -->
	
	<!-- footer -->
	<%@ include file="../includes/footer.jsp" %>

	<!-- 일정 등록 Modal -->
	<%@ include file="../includes/insertTodoModal.jsp" %>

</body>

</html>