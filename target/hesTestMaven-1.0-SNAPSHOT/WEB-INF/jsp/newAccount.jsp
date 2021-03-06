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
      <input type="text" required="" pattern="[A-Za-z]{3,16}" name= "userName" value="kkkk" class="form-control" id="userName" oninvalid="setCustomValidity('Please use latin characters only. Username length should be 3-16')" onchange="try{setCustomValidity('')}catch(e){}" />
    </div>
  </div>
  <div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-4">
      <input type="password" required="" pattern="[A-Za-z0-9]{3,16}" name= "password" value="kkkk" class="form-control" id="password" oninvalid="setCustomValidity('Please use latin characters and numbers only. Password length should be 3-16')" onchange="try{setCustomValidity('')}catch(e){}" />
    </div>
  </div>
  <div class="form-group row">
    <label for="firstName" class="col-sm-2 col-form-label">First name</label>
    <div class="col-sm-4">
      <input type="text" required="" pattern="[A-Za-z]{1,16}" name="firstName" value="firstNameTest" class="form-control" id="firstName" oninvalid="setCustomValidity('Please use latin characters only. First name length should be no longer than 16')" onchange="try{setCustomValidity('')}catch(e){}" />
    </div>
  </div>
  <div class="form-group row">
      <label for="pwd_conf" class="col-sm-2 col-form-label">Last name</label>
      <div class="col-sm-4">
        <input type="text" required="" pattern="[A-Za-z]{1,16}" name="lastName" value="lastNameTest" class="form-control" id="lastName" oninvalid="setCustomValidity('Please use latin characters only. Last name length should be no longer than 16')" onchange="try{setCustomValidity('')}catch(e){}" />
      </div>
    </div>

  <div class="form-group">
    <button type="submit" class="btn btn-dark">Submit</button>
  <div>


</form>


</body>
</html>