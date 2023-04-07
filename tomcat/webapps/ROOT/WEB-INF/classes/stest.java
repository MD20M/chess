import java.io.*;
import java.util.*; 
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/sock")
public class stest extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Session session = null;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        this.session = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Random Number Servlet with WebSockets</title>");
    out.println("<script>");
    out.println("var websocket = new WebSocket('wss://' + window.location.host + '/randomNumber');");
    out.println("websocket.onmessage = function(event) { document.getElementById('randomNumber').innerHTML = event.data; }");
    out.println("</script>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>Click the button to generate a random number</h1>");
    out.println("<button onclick=\"websocket.send('generateRandomNumber');\">Generate Random Number</button>");
    out.println("<div id='randomNumber'></div>");

    out.println("</body>");
    out.println("</html>");

}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not used in this example
    }

    public static void generateRandomNumber() throws IOException {
        if (session != null) {
            session.getBasicRemote().sendText(new Random().nextInt(100) + "");
        }
    }
}
