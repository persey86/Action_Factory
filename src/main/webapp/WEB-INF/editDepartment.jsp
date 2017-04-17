<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create department</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<div class="main container">
    <div class="row">
        <form method="post" action="<c:url value='/editDepartment' />">
            <input type="hidden" name="departmentId" value="${department.id}">
            <input type="text" placeholder="departmentName" name="departmentName" value="${department.name}"/>
            <input class="btn btn-success" type="submit" value="Add department"/>
        </form>

    </div>


    <div class="text-danger">${mapErr['name']}</div>

</div>
</body>
</html>
