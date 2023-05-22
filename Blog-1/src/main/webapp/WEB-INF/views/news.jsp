<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>News Search Results</title>
</head>
<body>
    <h1>News Search Results</h1>
    <table>
        <tr> 
            <th>Title</th>
            <th>Description</th>
            <th>Link</th>
            <th>Published Date</th>
        </tr>
        <c:forEach items="${newsItems}" var="newsItem">
            <tr>
                <td>${newsItem.title}</td>
                <td>${newsItem.description}</td>
                <td><a href="${newsItem.link}" target="_blank">Link</a></td>
                <td>${newsItem.pubDate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
