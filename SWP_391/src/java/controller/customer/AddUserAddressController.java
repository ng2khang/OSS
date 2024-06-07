/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.ProvinceDAO;
import DAO.Common.UserAddressDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Common.Province;
import model.Common.User;
import model.Common.UserAddress;

/**
 *
 * @author ifyou
 */
public class AddUserAddressController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUserAddressController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserAddressController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else {
            ProvinceDAO pdao = new ProvinceDAO();
            String provinceCode = request.getParameter("province");
            String districtCode = request.getParameter("district");
            String wardCode = request.getParameter("ward");
            String address = request.getParameter("address");
            String isDefault = request.getParameter("defaultAddress");
            int isDefaultNum;
            if (isDefault != null) {
                isDefaultNum = 1;
                UserAddress userAddress = new UserAddress(user.getId(), provinceCode, districtCode, wardCode, address, isDefaultNum);
                UserAddressDAO userAddressDAO = new UserAddressDAO();
                userAddressDAO.setDefaultAddressToZero(String.valueOf(user.getId()));
                userAddressDAO.addNewUserAddress(userAddress);
            } else {
                isDefaultNum = 0;
                UserAddress userAddress = new UserAddress(user.getId(), provinceCode, districtCode, wardCode, address, isDefaultNum);
                UserAddressDAO userAddressDAO = new UserAddressDAO();
                userAddressDAO.addNewUserAddress(userAddress);
            }
            response.sendRedirect("profile");
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
