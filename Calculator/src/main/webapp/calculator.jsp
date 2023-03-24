<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<style>
.number-input{
width:100%;
margin:20px;
}
p{
margin:20px;
}
</style>
</head>
<body>
	<div class="container">
      <form action="CalculatorServlet" method="post">
        <input type="text" name="input" placeholder="0" readonly id="myInput">
        <p>Result: <%=request.getAttribute("result") %></p>
        <br>
        <div class="number-input">
        <input type="button" value="7" onclick="buttonClick('7')">
        <input type="button" value="8" onclick="buttonClick('8')">
        <input type="button" value="9" onclick="buttonClick('9')">
        <input type="button" value="+" onclick="buttonClick('+')">
        <br>
        <input type="button" value="4" onclick="buttonClick('4')">
        <input type="button" value="5" onclick="buttonClick('5')">
        <input type="button" value="6" onclick="buttonClick('6')">
        <input type="button" value="-" onclick="buttonClick('-')">
        <br>
        <input type="button" value="1" onclick="buttonClick('1')">
        <input type="button" value="2" onclick="buttonClick('2')">
        <input type="button" value="3" onclick="buttonClick('3')">
        <input type="button" value="*" onclick="buttonClick('*')">
        <br>
        <input type="button" value="0" onclick="buttonClick('0')">
        <input type="button" value="/" onclick="buttonClick('/')">
        <input type="button" value="C" onclick="buttonClick('C')">
        <input type="submit" value="=">
        </div>
      </form>
    </div>
    <script src="script.js"></script>  
</body>
</html>