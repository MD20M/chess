<%@ page import="java.util.Random" %>
<html>
<head>
<title>Random Number Generator</title>
</head>
<body>
<h1>Random Number Generator</h1>
<form method="post">
<input type="submit" value="Generate Random Number" name="submit">
</form>

<%
if(request.getParameter("submit")!=null)
{
Random rand = new Random();
int randomNum = rand.nextInt(1000);
%>

<p>The random number generated is <%=randomNum%>.</p>

<%
}
%>

</body>
</html>