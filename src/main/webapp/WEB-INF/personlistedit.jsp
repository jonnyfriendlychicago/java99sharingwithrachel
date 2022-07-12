<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


    
<!DOCTYPE html>
<html lang="en">
<head>
<title>java99sharingwithrachel</title>
<meta charset="UTF-8">
<!-- local css -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- local javascript -->
<script type ="text/javascript" src="javascript/app.js"></script>
<!--  Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> 
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<h1>java99sharingwithrachel Person List+</h1>

	<a href= "/">Home</a>
	
	<h2>Person List</h2>

	<table class="table table-striped">
	  <thead>
	    <tr>
			<th scope="col">id</th>
	      	<th scope="col">firstName</th>
	      	<th scope="col">lastName</th>
			<th scope="col">Actions</th> 
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var="record" items="${personList}">
	        <tr>
		      <td >
		      	<a href= "/person/${record.id}">${record.id}</a>
		      </td>
		      <td>${record.firstName}</td>
		      <td>${record.lastName}</td>
				<td>
		      		<a href= "/personpluscreate/${record.id}/edit">Edit</a>
		      	
			      	<form action="/person/${record.id}" method="post">
					    <input type="hidden" name="_method" value="delete">
					    <input type="submit" value="Delete">
					</form>
		      </td>
		    </tr>
    	</c:forEach>  
	  </tbody>
	</table>
    
    <h2> Edit Person Record</h2>
    
    <form:form action='/personpluscreate/${person.id}/edit' method='post' modelAttribute='person'>
	  
	  <div class="form-group">
	    <form:label path="firstName" for="firstName" >firstName</form:label>
	     <form:input type="text" class="form-control"  path="firstName" aria-describedby="firstNameHelp" placeholder="Enter firstName"/>
	     <p class="errorText"><form:errors path="firstName"/></p>
	  </div>
	  
	  <div class="form-group">
	    <form:label path="lastName" for="lastName">lastName</form:label>
	    <form:input type="text" class="form-control" path="lastName" placeholder="lastName here"/>
	    <p class="errorText"><form:errors path="lastName"/></p>
	  </div>

	  <button type="submit" class="btn btn-primary">Update</button>
	</form:form> 
	
	
	
</body>
</html>