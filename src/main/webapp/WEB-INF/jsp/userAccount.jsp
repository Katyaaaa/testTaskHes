<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>HES</title>
</head>
<body class="container-fluid wrapper">


<table class="table">
  <c:forEach var="userAccount" items="${userAccounts}">
  <tr>
      <td><a href="${pageContext.request.contextPath}/${userAccount.id}" class="link">${userAccount.userName}</a></td>
      <td>${userAccount.lastName}</td>
      <td>${userAccount.userRole.roleType}</td>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td><a href="${pageContext.request.contextPath}/${userAccount.id}/edit" class="link">change</a></td>
      </sec:authorize>
  </tr>
  </c:forEach>
</table>

<table class="table">
  <tr>
      <td><a href="${pageContext.request.contextPath}/${userAccount.id}" class="link">${userAccount.userName}</a></td>
      <td>${userAccount.firstName}</td>
      <td>${userAccount.lastName}</td>
      <td>${userAccount.userStatus.statusType}</td>
      <td>${userAccount.userRole.roleType}</td>
      <td>${userAccount.createdAt}</td>
  </tr>
</table>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="${pageContext.request.contextPath}/newAccount" class="link">Create new user</a>
</sec:authorize>
<a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
<a href="${pageContext.request.contextPath}/userAccount/sortByUserName" class="link">Sort by username</a>
<hr>
<a href="${pageContext.request.contextPath}/userAccount/sortByRole" class="link">Sort by role</a>

</body>
</html>