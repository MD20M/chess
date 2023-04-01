import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class stest extends HttpServlet {

    private ConcurrentHashMap<String, Socket> connectedClients;

    @Override
    public void init() {
        connectedClients = new ConcurrentHashMap<String, Socket>();
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            new Thread(new ConnectionListener(serverSocket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Chat Room</h1>");
        out.println("<form method='post'>");
        out.println("<input type='text' name='message'>");
        out.println("<input type='submit' value='Send'>");
        out.println("</form>");
        out.println("<div id='messages'>");
        out.println("</div>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            for (Socket socket : connectedClients.values()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            }
        }
    }

    private class ConnectionListener implements Runnable {

        private ServerSocket serverSocket;

        public ConnectionListener(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    String clientName = clientSocket.getInetAddress().getHostName();
                    connectedClients.put(clientName, clientSocket);
                    new Thread(new ClientHandler(clientSocket, clientName)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private String clientName;

        public ClientHandler(Socket clientSocket, String clientName) {
            this.clientSocket = clientSocket;
            this.clientName = clientName;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    for (Socket socket : connectedClients.values()) {
                        if (socket != clientSocket) {
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            out.println(clientName + ": " + inputLine);
                        }
                    }
                }
                in.close();
                connectedClients.remove(clientName);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
