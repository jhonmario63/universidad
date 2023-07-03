/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UniversidadDAO;
import Model.Curso;
import Model.Estudiantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Mario
 */
public class ControllerUniversidad extends HttpServlet {

    String addEstudiante = "vistas/agregarEstudiante.jsp";
    String addEstCurso = "vistas/agregarEstCurso.jsp";
    String promedio = "vistas/promedio.jsp";
    String index = "index.jsp";
    Estudiantes estudiante = new Estudiantes();
    UniversidadDAO dao = new UniversidadDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerUniversidads</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerUniversidads at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equals("index")) {
            acceso = index;
        } else if (action.equals("addEstudiante")) {
            acceso = addEstudiante;
        } else if (action.equals("addEstCurso")) {
            acceso = addEstCurso;
        } else if (action.equals("promedio")) {
            acceso = promedio;
        } else if (action.equals("Agregar")) {
            try {
                estudiante.setNombre(request.getParameter("txtNombre"));
                estudiante.setCodigo(Integer.parseInt(request.getParameter("txtCodigo")));
                estudiante.setEdad(Integer.parseInt(request.getParameter("txtEdad")));
                estudiante.setFechaNacimiento(request.getParameter("txtFechaNacimiento"));
                estudiante.setGenero(request.getParameter("txtGenero"));
                estudiante.setPrograma(Integer.parseInt(request.getParameter("txtPrograma")));
                if (dao.agregarEstudiante(estudiante)) {
                    acceso = index;
                }
            } catch (Exception ex) {
                Logger.getLogger(ControllerUniversidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("AgregarEstCurso")) {
            try {
                int codigoCurso = (Integer.parseInt(request.getParameter("txtCurso")));
                int codigoEstudiante = (Integer.parseInt(request.getParameter("txtEstudiante")));
                if (dao.agregarEstudianteCurso(codigoCurso, codigoEstudiante)) {
                    acceso = index;
                }
            } catch (Exception ex) {
                Logger.getLogger(ControllerUniversidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//       
        String action = request.getParameter("accion");
        if (action.equals("loadCursos")) {
            try {
                String selectedEstudiante = request.getParameter("txtEstudiante");
                List<Curso> listaCursos = dao.listarCursoPrograma(Integer.parseInt(selectedEstudiante));
                StringBuilder opcionesCursos = new StringBuilder();
                for (Curso curso : listaCursos) {
                    opcionesCursos.append("<option value=\"" + curso.getCodigo() + "\">" + curso.getNombre() + "</option>");
                }
                response.setContentType("text/html");
                response.getWriter().write(opcionesCursos.toString());
            } catch (Exception ex) {
                Logger.getLogger(ControllerUniversidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("promedio")) {
            int sum = 0, contador = 0;
            double promedioUni = 0;
            try {
                List<Estudiantes> listaEstudianteUniversidad = dao.listarEstudiante();
                for (Estudiantes e : listaEstudianteUniversidad) {
                    sum = sum + e.getEdad();
                    contador = contador + 1;
                }

                if (sum > 0 && listaEstudianteUniversidad.size() > 0) {
                    promedioUni = sum / listaEstudianteUniversidad.size();
                    request.setAttribute("promedioUni", promedioUni);
                    RequestDispatcher vista = request.getRequestDispatcher(promedio);
                    vista.forward(request, response);
                }

            } catch (Exception ex) {
                Logger.getLogger(ControllerUniversidad.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
