/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author serio
 */
public class ShowSession extends HttpServlet {

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
        String heading;
        Integer accessCount;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            synchronized (session) {
                //Definir un mensaje
                accessCount = (Integer) session.getAttribute("accessCount");
                if (accessCount == null) {
                    accessCount = 0;
                    heading = "Welcome, Newcomer";
                } else {
                    heading = "Welcome Back";
                    accessCount = accessCount + 1;
                }
                //Actualizar el atributo en el onjeto session
                session.setAttribute("accessCount", accessCount);
            }
            out.println("<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n"
                    + "<!DOCTYPE html>"
                    + "<HTML>\n"
                    + "<HEAD><TITLE>" + "Acces Count" + "</TITLE></HEAD>\n"
                    + "<BODY BGCOLOR=\"#FDF5E6\">\n"
                    + "<CENTER>\n"
                    + "<H1>" + heading + "</H1>\n"
                    + "<H2>Information on Your Session:</H2>\n"
                    + "<TABLE BORDER=1>\n"
                    + "<TR BGCOLOR=\"#FFAD00\">\n"
                    + " <TH>Info Type<TH>Value\n"
                    + " <TR>" + accessCount + "\n"
                    + " <TD>Creation Time<TD>\n"
                    + " <TR>" + session.getCreationTime() + "\n"
                    + " <TD>Time of Last Access<TD>\n"
                    + " <TR>" + session.getLastAccessedTime() + "\n"
                    + " <TD>Number of Previous Accesses<TD>\n"
                    + " <TR>" + session.getLastAccessedTime() + "\n"
                    + "</TABLE>\n"
                    + "</CENTER></BODY></HTML>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
