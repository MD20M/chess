import java.io.*;
import java.net.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class stest extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private static List<Socket> sockets = new ArrayList<Socket>();
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Chat Room</title></head>");
    out.println("<body>");
    out.println("<h1>Chat Room</h1>");
    out.println("<form method=\"post\">");
    out.println("<input type=\"text\" name=\"message\">");
    out.println("<input type=\"submit\" value=\"Send\">");
    out.println("</form>");
    out.println("<ul>");
    for (Socket socket : sockets) {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      if (in.ready()) {
        out.println("<li>" + in.readLine() + "</li>");
      }
    }
    out.println("</ul>");
    out.println("</body></html>");
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String message = request.getParameter("message");
    for (Socket socket : sockets) {
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println(message);
    }
    doGet(request, response);
  }
  
  public static class ChatServer implements Runnable {
    public void run() {
      try {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
          Socket socket = serverSocket.accept();
          sockets.add(socket);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void init() throws ServletException {
    super.init();
    new Thread(new ChatServer()).start();
  }
}
