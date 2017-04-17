<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Departments</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value='/' />">Home page</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="main container">

    <div class="row">
        <h1>Form to create a new user </h1>
    </div>

<div class="row">
    <form method="post" action="<c:url value='/createUser' />">

        <label>Name</label>
          <input type="text" placeholder="name" name="userName" value="${invalidUser.name}"/>
        <div class="text-danger">${mapErr['name']}</div>

<label>Surname</label>
          <input type="text" placeholder="surname" name="userSurname" value="${invalidUser.surname}"/>
        <div class="text-danger">${mapErr['surname']}</div>

<label>E-mail  </label>
        <input type="text" placeholder="e-mail" name="userEmail" value="${invalidUser.email}"/>
        <div class="text-danger">${mapErr['email']}</div>

<label>User age</label>
        <input required type="number" placeholder="age of user" name="userAge" value="${invalidUser.age}"/>
        <div class="text-danger">${mapErr['age']}</div>


        <label>Department</label>
        <select name="departmentId">
            <c:forEach var="department" items="${allDepartments}">
                <c:if test="${department.id==invalidUser.departmentId}">
                    <option value="${department.id}" selected>${department.name}</option>
                </c:if>
                <c:if test="${department.id!=invalidUser.departmentId}">
                    <option value="${department.id}">${department.name}</option>
                </c:if>
            </c:forEach>
        </select>

       <input class="btn btn-success" type="submit" value="Add new user"/>
    </form>



</div>
</div>
</body>
</html>
