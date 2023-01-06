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
                    <li><a href="board_list?category=자유 게시판">게시판</a>&#62;</li>
                    <li><a href="board_list?category=QNA">Q&A</a></li>
                </ul>
            </div>
            <!-- =================================contents================================================= -->
            <fmt:requestEncoding value="UTF-8"/>
            <c:set var="vo" value="${BoardVO}"></c:set>
            
        	<c:set var="content" value="${fn:replace(vo.content, '<', '&lt;') }"></c:set>
			<c:set var="content" value="${fn:replace(vo.content, '>', '&gt;') }"></c:set>
			<c:set var="content" value="${fn:replace(vo.content, enter, '<br/>') }"></c:set>	
			
			<c:if test="${vo.deptno == 500}"><c:set var="dname" value="경영지원부"></c:set></c:if>
			<c:if test="${vo.deptno == 400}"><c:set var="dname" value="IT부"></c:set></c:if>
			<c:if test="${vo.deptno == 300}"><c:set var="dname" value="상품개발부"></c:set></c:if>
			<c:if test="${vo.deptno == 200}"><c:set var="dname" value="마케팅부"></c:set></c:if>
			<c:if test="${vo.deptno == 100}"><c:set var="dname" value="영업부"></c:set></c:if>		
			
            <div class="content_view">
            	<!-- 질문 -->
				<div style="height: 46%">						
		         	<h1>Q. ${vo.title}</h1>
					<h5><fmt:formatDate value="${vo.writedate}" pattern = "yyyy-MM-dd aa h시 mm분"/></h5>
					<h5>${vo.name}&lpar;${dname}&rpar;</h5>
					<hr/>	
					<div style="padding: 2px 10px; height: 55%">	
						<p>${content}</p>
					</div>
					<br/>
					<div align="right">
		               	<c:if test="${vo.name == EmpVO.name}">
		                	<input type="button" value="삭제" onclick="location.href='board_delete?idx=${vo.idx}&currentPage=${currentPage}&category=QNA'"/>
		                </c:if>
		            </div>
		        </div>
		        <!-- 질문 -->
                <hr/>
                <!-- 관리자 답변 달기 -->
                <c:if test="${EmpVO.permission == 'YES' && commentList.list.size() == 0}">
              		<div style="height: 45%">
	                	<table style="width: 100%">
		                
		                	<form action="answer_insert" method="post">
		                	
		                		<input type="hidden" id="category" name="category" value="QNA"> 
	
								<tr>
									<th>답변등록</th>
									<td></td>
								</tr>
	
								<tr> 
									<th>제목</th>
									<td><input id="title" type="text" name="title"/></td>
								</tr>
								<tr>
									<th>내용</th>
									<td><textarea id="content" name="content" rows="9"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" align="right">
										<input type="reset" value="다시쓰기"/>
										<input type="submit" value="저장하기"/>
									</td>					
								</tr>
	
								<input id="name" type="hidden" name="name" value="${EmpVO.name}"/>
								<input id="empno" type="hidden" name="empno" value="${EmpVO.empno}"/>
								<input id="deptno" type="hidden" name="deptno" value="${EmpVO.deptno}"/>
								<input id="deptno" type="hidden" name="gup" value="${vo.idx}"/>
								
							</form>
		                </table>
		            </div>
					<hr/>
                </c:if>
				<!-- 관리자 답변 달기 -->
				
				<!-- 답변 -->
	 			<div style="height: 48%">
					<c:set var="co" value="${commentList.list}"></c:set>			
					<c:forEach var="co" items="${co}">
					
						<c:set var="cocontent" value="${fn:replace(co.content, '<', '&lt;') }"></c:set>
						<c:set var="cocontent" value="${fn:replace(co.content, '>', '&gt;') }"></c:set>
						<c:set var="cocontent" value="${fn:replace(co.content, enter, '<br/>') }"></c:set>
								
		            	<h1>A. ${co.title}</h1>
						<h5><fmt:formatDate value="${co.writedate}" pattern = "yyyy-MM-dd(E), aa h시 mm분"/></h5>
						<h5>${co.name}&lpar;관리자&rpar;</h5>
						<hr/>	
						<div style="padding: 2px 10px; height: 55%">	
							<p>${cocontent}</p>
						</div>
						<div align="right">
			               	<c:if test="${co.name == EmpVO.name}">
			                	<input type="button" value="삭제" onclick="location.href='board_service?mode=3&idx=${co.gup}&seq=${co.seq}&category=QNA'"/>
			                </c:if>
			            </div>
	                	<hr/>
			        </c:forEach>
					
					<div align="right">
						<input type="button" value="목록" onclick="location.href='board_list?currentPage=${currentPage}&category=QNA'"/>
	                </div>
				</div>
				<!-- 답변 -->
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
	<%@ include file="../includes/todoModal.jsp" %>

</body>

</html>