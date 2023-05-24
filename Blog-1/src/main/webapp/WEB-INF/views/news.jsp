<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>News Articles</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table-primary > tbody > tr:hover {
            background-color: #b3d7ff;
        }
        .table-secondary > tbody > tr:hover {
            background-color: #e9ecef;
        }
        .table-success > tbody > tr:hover {
            background-color: #c3e6cb;
        }
        .table-danger > tbody > tr:hover {
            background-color: #f5c6cb;
        }
        .table-warning > tbody > tr:hover {
            background-color: #ffeeba;
        }
        .table-info > tbody > tr:hover {
            background-color: #bee5eb;
        }
        .table-light > tbody > tr:hover {
            background-color: #f8f9fa;
        }
        .table-dark > tbody > tr:hover {
            background-color: #c6c8ca;
        }
        .title-column {
            width: 40%;
            font-weight: bold;
            text-align: center;
        }
        .link-column {
            width: 5%;
            text-align: center;
        }
        .description-column {
            width: 55%;
            text-align: center;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>News Articles</h1>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th class="title-column">Title</th>
                    <th class="link-column">Original Link</th>
                    <th class="description-column">Description</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${newsList}" var="newsArticle" varStatus="loop">
                    <tr class="table-${loop.index % 8 + 1}">
                        <td><c:out value="${newsArticle.title}" /></td>
                        <td><a href="<c:out value="${newsArticle.originallink}" />"><c:out value="Link" /></a></td>
                        <td><c:out value="${newsArticle.description}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
