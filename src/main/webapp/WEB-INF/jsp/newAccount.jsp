<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>HES</title>
</head>
<body class="container-fluid wrapper">

<h3 class="top-name center-block text-center">Registration</h4>
<h5 class="top-name center-block text-center text-danger">${unsuccessful}</h5>

${errors}

<form method="POST" action="${pageContext.request.contextPath}/newAccount">

  <div class="form-group row">
    <label for="userName" class="col-sm-2 col-form-label">User name</label>
    <div class="col-sm-4">
      <input type="text" required="" name= "userName" value="kkkk" class="form-control" id="userName">
    </div>
  </div>
  <div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-4">
      <input type="password" required="" name= "password" value="kkkk" class="form-control" id="password">
    </div>
  </div>
  <div class="form-group row">
    <label for="firstName" class="col-sm-2 col-form-label">First name</label>
    <div class="col-sm-4">
      <input type="text" required="" name="firstName" value="firstNameTest" class="form-control" id="firstName">
    </div>
  </div>
  <div class="form-group row">
      <label for="pwd_conf" class="col-sm-2 col-form-label">Last name</label>
      <div class="col-sm-4">
        <input type="text" required="" name="lastName" value="lastNameTest" class="form-control" id="lastName">
      </div>
    </div>

  <div class="form-group">
    <button type="submit" class="btn btn-dark">Submit</button>
  <div>


</form>


</body>
</html>