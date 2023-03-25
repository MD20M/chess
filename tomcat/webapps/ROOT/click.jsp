<%@ page import="java.util.Random" %>
<%
    Random rand = new Random();
    String[][] chessBoard = { { "R", "K", "B", "Q", "K", "B", "K", "B" }, { "P", "P", "P", "P", "P", "P", "P", "P" },
        { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" },
        { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" }, { "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]" },
        { "P", "P", "P", "P", "P", "P", "P", "P" }, { "R", "K", "B", "Q", "K", "B", "K", "R" } };
%>
<html>
	<head>
		<title>Chessboard</title>
		<link href="style.css" rel="stylesheet"/>
		<script>
			function updateBoard(row, col) {
				var character = document.getElementById("character").value;
				if (character) {
					document.getElementById("cell" + row + col).innerHTML = character;
				}
			}
		</script>
	</head>
	<body>
		<table border="1">
			<% for (int i = 0; i < 8; i++) { %>
				<tr>
					<% for (int j = 0; j < 8; j++) { %>
						<td id="cell<%=i%><%=j%>" onclick="updateBoard(<%=i%>, <%=j%>)"><%=chessBoard[i][j]%></td>
					<% } %>
				</tr>
			<% } %>
		</table><br/>
		Character: <input type="text" id="character"/>
	</body>
	</html>