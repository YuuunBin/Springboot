<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<!--  뉴스검색 -->
	</c:forEach>
	  <h1>News Search</h1>
    <form action="/search" method="get">
        <input type="text" name="query" placeholder="검색어를 입력하세요">
        <input type="submit" value="검색">
    </form>
    	<!--  뉴스검색 끝-->
	<ul class="pagination justify-content-center">
	<c:choose>
	<c:when test="${boards.first}">
			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	</c:when>
	<c:otherwise>		
		<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	</c:otherwise>
	</c:choose>

	<c:choose>
	<c:when test="${boards.last}">
		<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	</c:when>
	<c:otherwise>		
		<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	</c:otherwise>
	</c:choose>
	</ul>
</div>
<%@ include file="layout/footer.jsp"%>