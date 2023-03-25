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

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><link href='/style.css' rel='stylesheet'/></head><body>");
    out.println("<h2>Array values1:</h2>");

    // display the array
    out.println("<table>");
    out.println("<tr>");
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++){
        out.println("<td>"+array[i][j] + "</td>");
      }
      out.println("</tr>");
    }
    out.println("</table>");

    // check if a value is being updated
    String value = request.getParameter("value");
    String index = request.getParameter("index");
    
    if (value != null && index != null) {
      try {
       String newValue = value;
       String[]location = index.split("");
       int i = Integer.parseInt(location[0]);
       int j = Integer.parseInt(location[1]);

        // check if index is within bounds
        if (i < 0 || i >= array.length) {
         out.println("<br>");
         out.println("Invalid index: " + i);
         return;
        }

        // update the value in the array
        array[i][j] = newValue;

       out.println("<br>");
       out.println("Value at index " + i + " has been updated to " + newValue);
      } catch (NumberFormatException e) {
        out.println("<br>");
        out.println("Invalid value: " + value);
      }
    }

    // display the form to update a value
    out.println("<h2>Premikanje:</h2>");
    out.println("<form method='get'>");
    out.println("<label>Vpis:</label>");
    out.println("<label class='zraven'>Figura</label>: <input type='text' name='value'><br>");
    out.println("<label>Mesto</label>: <input type='text' name='index'><br>");
    out.println("<input type='submit' value='Update'>");
    out.println("</form>");
    out.println("</body></html>");
  }
}
