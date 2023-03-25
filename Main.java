import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Main extends HttpServlet {
  private int[] array = {1, 2, 3, 4, 5};

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h2>Array values:</h2>");

    // display the array
    for (int i = 0; i < array.length; i++) {
     out.println(array[i] + "<br>");
    }

    // check if a value is being updated
    String value = request.getParameter("value");
    String index = request.getParameter("index");
    
    if (value != null && index != null) {
      try {
       int newValue = Integer.parseInt(value);
       int i = Integer.parseInt(index);

        // check if index is within bounds
        if (i < 0 || i >= array.length) {
         out.println("<br>");
         out.println("Invalid index: " + i);
         return;
        }

        // update the value in the array
        array[i] = newValue;

       out.println("<br>");
       out.println("Value at index " + i + " has been updated to " + newValue);
      } catch (NumberFormatException e) {
        out.println("<br>");
        out.println("Invalid value: " + value);
      }
    }

    // display the form to update a value
    out.println("<h2>Update value:</h2>");
    out.println("<form method='get'>");
    out.println("Index: <input type='text' name='index'><br>");
    out.println("Value: <input type='text' name='value'><br>");
    out.println("<input type='submit' value='Update'>");
    out.println("</form>");
    out.println("</body></html>");
  }
}
