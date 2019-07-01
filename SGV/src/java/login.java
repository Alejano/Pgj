/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DB.Prueba;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author alejandro_barragán
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Entraste</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PrintWriter out=response.getWriter();
       out.println("<html>");
       out.println("<body>");
       out.println("Método doGetno soportado en el Servlet");
       out.println("</body>");
       out.println("</html>");
       out.close();
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String usuario = request.getParameter("usuario_pgj");
            String password = request.getParameter("password");
            
            EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("SGVPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Prueba> consultaAlumnos= em.createNamedQuery("Prueba.findByUsuario", Prueba.class);
            consultaAlumnos.setParameter("usuario", usuario);
            Prueba Us = consultaAlumnos.getSingleResult();
            
            try {
            
        
                    String DBusuario = Us.getUsuario();
                    String DBpassword = Us.getContrasena();
                    


                    System.out.println("usuario:"+DBusuario);
                    System.out.println("password:"+DBpassword);

                    PrintWriter out=response.getWriter();
                    out.println("<html>");
                    out.println("<body>");
                    out.println("El parametro de la Base de datos: usuario es: "+ DBusuario);
                    out.println("<br>");
                    out.println("El parametro de la Base de datos: passwordes: "+DBpassword);
                    out.println("</body>");out.println("</html>");
                    out.close();
        
        } catch (Exception e) {
            PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Error");        
        out.println("</body>");
        out.println("</html>");
        out.close();
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
