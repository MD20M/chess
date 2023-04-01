import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Main extends HttpServlet {
  private String[][] array = {
    {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
    {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
    {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
};

    public static int cToNum(String cPos) {
      if (cPos.equals("A")) {
       return 0;
     }
      if (cPos.equals("B")) {
        return 1;
      }
      if (cPos.equals("C")) {
        return 2;
     }
      if (cPos.equals("D")) {
       return 3;
      }
      if (cPos.equals("E")) {
       return 4;
      }
      if (cPos.equals("F")) {
        return 5;
      }
      if (cPos.equals("G")) {
        return 6;
      }
      if (cPos.equals("H")) {
        return 7;
      }
      return 0;
}

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><link href='/style.css' rel='stylesheet'/></head><body onload='startAnimation()'>");

    ServletContext context = getServletContext();
    Integer count = (Integer) context.getAttribute("count");
    if (count == null) {
      count = 0;
    } else {
      count++;
    }
    context.setAttribute("count", count);
    if (count%2 !=0){
      out.println("<script src='script.js'></script>");
    }
    

    // check if a value is being updated
    String From = request.getParameter("From");
    String To = request.getParameter("To");
    
    if (From != null && To != null) {
      try {
       String newValue = " ";
       String[]location = From.split("");
       int i = Integer.parseInt(location[1])-1;
       int j = cToNum(location[0]);


        // check if index is within bounds
        if (i < 0 || i >= array.length) {
         out.println("<br>");
         out.println("Invalid index: " + i);
         return;
        }

        // update the value in the array
        String oldValue = array[i][j];
        array[i][j] = newValue;

        location = To.split("");
        i = Integer.parseInt(location[1])-1;
        j = cToNum(location[0]);
        array[i][j] = oldValue;

       out.println("<br>");
       out.println("Value at index " + i + " has been updated to " + newValue);
      } catch (NumberFormatException e) {
        out.println("<br>");
        out.println("Invalid value: " + From);
      }
    }
    // display the array
    if (count%2 == 0){
      out.println("<div class='board'>");
      out.println("<table>");
      for (int i = 0; i < array.length; i++) {
        out.println("<tr><td style='background-color: none; border: none; background: none;'>" + (i+1) + "</td>");
        for (int j = 0; j < array[i].length; j++){
          out.println("<td>"+array[i][j] + "</td>");
        }
        out.println("</tr>");
      }
      out.println("<tr>");
      out.println("<td style='background-color: none; border: none; background: none;'>" +" "+ "</td>");
      for(char i = 'A'; i <= 'H'; i++){
        out.println("<td style='background-color: none; border: none; background: none;'>" + (i) + "</td>");
      }
      out.println("</tr>");
      out.println("</table>");
      out.println("</div>");
    }
    else{
      out.println("<div class='board'>");
        out.println("<table>");
        for (int i = 7; i >= 0; i--) {
          out.println("<tr><td style='background-color: none; border: none; background: none;'>" + (i+1) + "</td>");
          for (int j = 0; j < 8; j++){
            out.println("<td>"+array[i][j] + "</td>");
          }
          out.println("</tr>");
        }
        out.println("<tr>");
        out.println("<td style='background-color: none; border: none; background: none;'>" +" "+ "</td>");
        for(char i = 'H'; i >= 'A'; i--){
          out.println("<td style='background-color: none; border: none; background: none;'>" + (i) + "</td>");
        }
        out.println("</tr>");
        out.println("</table>");
        out.println("</div>");
     }  

    

    // display the form to update a value
    out.println("<div class='controllMenu'>");
    out.println("<form method='get'>");
    out.println("<p class='cm'>Control Menu</p><br>");
    out.println("<label>From:</label> <input type='text' name='From'><br>");
    out.println("<label>To:</label> <input type='text' name='To'><br>");
    out.println("<input type='submit' value='Update'>");
    out.println("</form>");
    out.println("</div>");
    out.println("</body></html>");
  }
}
