import java.io.*;
import java.util.*;  
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class evidenca extends HttpServlet {
    public static void vpis(Dijak[]dijakV, int i) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("<form method='get'>");
        out.println("<p class='cm'>Control Menu</p><br>");
        out.println("<label>Vpis: </label> <input type='text' name='vpisIme'><br>");
        out.println("<input type='text' name='vpisPriimek'><br>");
        out.println("<input type='text' name='vpisDatum'><br>");
        out.println("<input type='text' name='vpisRazred'><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");

        out.println("_________________________");
        out.println("|Vpis|");
        out.println("-------------------------");
        out.print("Ime: ");
        String ime = vh.readLine();
        out.println(" ");

        out.print("Priimek: ");
        String priimek = vh.readLine();
        out.println(" ");

        out.print("Datum: ");
        String datum = vh.readLine();
        out.println(" ");

        out.print("Razred: ");
        String razred = vh.readLine();
        out.println(" ");

        dijakV[i] = new Dijak(ime, priimek, datum, razred);
        dijakV[i].izpis(i);
        out.println("-------------------------");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><link href='/style1.css' rel='stylesheet'/></head><body>");
        Integer count = (Integer) context.getAttribute("count");
            if (count == null) {
                count = 0;
            }

        BufferedReader vh = new BufferedReader(new InputStreamReader(System.in));
        Dijak[] dijaki = new Dijak[100];
        int v = 0;

        if (request.getParameter("ime") != null && request.getParameter("priimek") != null && request.getParameter("datum") != null && request.getParameter("razred") != null) {
            String ime = request.getParameter("ime");
            String priimek = request.getParameter("priimek");
            String datum = request.getParameter("datum");
            String razred = request.getParameter("razred");
            dijaki[count] = new Dijak(ime, priimek, datum, razred);
            count++;
        }
        
        izbrano = "";
        while (!izbrano.equals("5")){
            meni();
            izbrano = vh.readLine();

            if (izbrano.equals("1")){
                vpis(dijaki, v);
                v++;
            }

            if (izbrano.equals("2")){
                vsiDijaki(dijaki);
                System.out.print("Izberite dijaka: ");
                int iDijak = Integer.parseInt(vh.readLine());
                briši(dijaki, iDijak);
            }

            if (izbrano.equals("3")){
                vsiDijaki(dijaki);
            }

            if (izbrano.equals("4")){
                vsiDijaki(dijaki);
                System.out.print("Izberite dijaka: ");
                int iDijak = Integer.parseInt(vh.readLine());
                System.out.println("");
                int x = 0;
                while (x!=5){
                    spremeni(dijaki, x, iDijak);
                    x = Integer.parseInt(vh.readLine());
                }
            }
        }
        out.println("<div class='controllMenu'>");
        out.println("<form method='get'>");
        out.println("<p class='cm'>Control Menu</p><br>");
        out.println("<label>Vpišite: </label> <input type='text' name='vpisi'><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }    
}