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

<h5 class="top-name center-block text-center text-danger">${unsuccessful}</h5>
${errors}

<form method="POST" action="${pageContext.request.contextPath}/edit">

<h5 class="top-name center-block text-center">${userAccount.id}</h5>
 <input type="hidden" required="" name= "id" value="${userAccount.id}" class="form-control" id="id">
  <div class="form-group row">
    <label for="userName" class="col-sm-2 col-form-label">Username</label>
    <div class="col-sm-4">
      <input type="text" required="" pattern="[A-Za-z]{3,16}" name= "userName" value="${userAccount.userName}" class="form-control" id="userName" oninvalid="setCustomValidity('Please use latin characters only. Username length should be 3-16')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
  </div>
  <div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label">First name</label>
    <div class="col-sm-4">
      <input type="text" required="" pattern="[A-Za-z]{1,16}" name= "firstName" value="${userAccount.firstName}" class="form-control" id="firstName" oninvalid="setCustomValidity('Please use latin characters only. First name length should be no longer than 16')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
  </div>
  <div class="form-group row">
    <label for="firstName" class="col-sm-2 col-form-label">Last name</label>
    <div class="col-sm-4">
      <input type="text" required="" pattern="[A-Za-z]{1,16}" name="lastName" value="${userAccount.lastName}" class="form-control" id="lastName" oninvalid="setCustomValidity('Please use latin characters only. Last name length should be no longer than 16')" onchange="try{setCustomValidity('')}catch(e){}">
    </div>
  </div>
 <div class="form-group">
 <div class="col-sm-4">
     <select class="form-control" id="userRoleType" name="userRoleType">
     <c:forEach var="role" items="${allRoles}">
       <option value = "${role.roleType}">${role.roleType}</option>
     </c:forEach>
     </select>
     </div>
   </div>
   <div class="form-group">
   <div class="col-sm-4">
        <select class="form-control" id="userStatusType" name="userStatusType">
        <c:forEach var="status" items="${allStatuses}">
          <option value="${status.statusType}">${status.statusType}</option>
        </c:forEach>
        </select>
        </div>
      </div>


  <div class="form-group">
    <button type="submit" class="btn btn-dark">Submit</button>
  <div>




</form>


</body>
</html>