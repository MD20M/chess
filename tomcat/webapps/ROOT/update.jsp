<%
    String character = request.getParameter("character");
    int row = Integer.parseInt(request.getParameter("row"));
    int col = Integer.parseInt(request.getParameter("col"));
    int[][] chessBoard = (int[][]) session.getAttribute("chessBoard");
    if (character != null && row >= 0 && row <= 7 && col >= 0 && col <= 7) {
        try {
            int value = Integer.parseInt(character);
            chessBoard[row][col] = value;
        } catch (NumberFormatException e) {}
    }
%>
<jsp:forward page="index.jsp"/>