package Backend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Luis on 22/09/15.
 */
@WebServlet(name = "Login",urlPatterns = {"/login"})
public class Servlet extends HttpServlet {
    String fileName = "database.txt";
    String Usuario = null, Pass = null, Directory = null, coded = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Directory = System.getProperty("user.dir");
        Usuario = request.getParameter("User");
        Pass = request.getParameter("Password");
        coded = Usuario + "%&++" + Pass;
        toAdd(Usuario, Pass);
        if(toFind(coded)){
            Yes(request,response);
        }else{
            No(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void toAdd(String usr, String pass)  {
        PrintWriter printWriter = null;
        File file = new File(fileName);
        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(System.lineSeparator() + usr + "%&++" + pass + System.lineSeparator());
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public boolean toFind(String coded){
        double count = 0,countBuffer=0,countLine=0;
        String lineNumber = "";
        String filePath = fileName;
        BufferedReader br;
        String inputSearch = coded;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                while((line = br.readLine()) != null)
                {
                    countLine++;
                    //System.out.println(line);
                    String[] words = line.split(" ");

                    for (String word : words) {
                        if (word.equals(inputSearch)) {
                            count++;
                            countBuffer++;
                        }
                    }

                    if(countBuffer > 0)
                    {
                        countBuffer = 0;
                        lineNumber += countLine + ",";
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Times found at--"+count);
        System.out.println("Word found at--"+lineNumber);
        if(count>=1){
            return true;
        }
        return false;
    }

    private void Yes(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BackendServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BackendServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>Usuario : " + Usuario + "</p>");
            out.println("Pass : " + Pass + "</p>");
            out.println("<p>Ruta de archvio = "+ Directory + "\\" + fileName + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void No(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>405</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<p>Usuario : " + Usuario + "</p>");
            //out.println("Pass : " + Pass + "</p>");
            out.println("<h1>Acceso restringido</h1>");
            out.println("<p>" + coded +"</p>");
            out.println("<p>Ruta de archvio = "+ Directory + "\\" + fileName + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
