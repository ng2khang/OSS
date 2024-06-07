/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.AccountActiveTokenDAO;
import DAO.Common.DistrictDAO;
import DAO.Common.ProvinceDAO;
import DAO.Common.UserAddressDAO;
import DAO.Common.UserDAO;
import DAO.Common.WardDAO;
import Service.SendMailService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import model.Common.AccountActiveToken;
import model.Common.District;
import model.Common.Province;
import model.Common.User;
import model.Common.UserAddress;
import model.Common.Ward;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ifyou
 */
public class RegisterController extends HttpServlet {

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
            ProvinceDAO pdao = new ProvinceDAO();
            List<Province> provinces = pdao.selectAllProvince();
            request.setAttribute("provinces", provinces);
            request.getRequestDispatcher("view\\customer\\register.jsp").forward(request, response);
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
        //get info from form
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phone = request.getParameter("phone");
        String provinceCode = request.getParameter("province");
        String districtCode = request.getParameter("district");
        String wardCode = request.getParameter("ward");
        String address = request.getParameter("address");
        // validate information
        // validate if the email is already exist
        UserDAO userDAO = new UserDAO();
        User checkUser = userDAO.searchUserByEmail(email);
        // if an user have used entered email
        if (checkUser != null) {
            String errorEmailMessage = "Địa chỉ email này đã tồn tại!";
            // check if the password and confirm password are not match
            if (!password.equals(confirmPassword)) {
                String errorConfirmPasswordMessage = "Mật khẩu và xác nhận mật khẩu không khớp!";
                request.setAttribute("errorConfirmPasswordMessage", errorConfirmPasswordMessage);
                // return the entered information for user
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("confirmPassword", confirmPassword);
                request.setAttribute("phone", phone);

                request.setAttribute("provinceCode", provinceCode);
                request.setAttribute("wardCode", wardCode);
                request.setAttribute("districtCode", districtCode);
                request.setAttribute("address", address);
                ProvinceDAO pdao = new ProvinceDAO();
                List<Province> provinces = pdao.selectAllProvince();
                request.setAttribute("provinces", provinces);
                Province selectedProvince = pdao.findProvinceByCode(provinceCode);
                request.setAttribute("selectedProvince", selectedProvince);
                DistrictDAO districtDAO = new DistrictDAO();
                District seletedDistrict = districtDAO.findDistrictByCode(districtCode);
                request.setAttribute("selectedDistrict", seletedDistrict);
                WardDAO wardDAO = new WardDAO();
                Ward selectedWard = wardDAO.findWardByCode(wardCode);
                request.setAttribute("selectedWard", selectedWard);
                request.setAttribute("errorEmailMessage", errorEmailMessage);
                request.getRequestDispatcher("view\\customer\\register.jsp").forward(request, response);
            } else {
                // the email is exist and the password is the same with confirm password
                // return the entered information for user
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("confirmPassword", confirmPassword);
                request.setAttribute("phone", phone);

                request.setAttribute("provinceCode", provinceCode);
                request.setAttribute("wardCode", wardCode);
                request.setAttribute("districtCode", districtCode);
                ProvinceDAO pdao = new ProvinceDAO();
                List<Province> provinces = pdao.selectAllProvince();
                request.setAttribute("provinces", provinces);
                Province selectedProvince = pdao.findProvinceByCode(provinceCode);
                request.setAttribute("selectedProvince", selectedProvince);
                DistrictDAO districtDAO = new DistrictDAO();
                District seletedDistrict = districtDAO.findDistrictByCode(districtCode);
                request.setAttribute("selectedDistrict", seletedDistrict);
                WardDAO wardDAO = new WardDAO();
                Ward selectedWard = wardDAO.findWardByCode(wardCode);
                request.setAttribute("selectedWard", selectedWard);
                request.setAttribute("errorEmailMessage", errorEmailMessage);
                request.setAttribute("address", address);
                request.getRequestDispatcher("view\\customer\\register.jsp").forward(request, response);
            }

        } else {
            // the info is valid
            // encode the password of user
            String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
            // save the user information
            User registerUser = new User(name, email, encodedPassword, phone, 0, 4, 0);
            userDAO.addNewUser(registerUser);
            // get information of the new user
            User newUser = userDAO.searchUserByEmail(email);
            // create user address of new user
            UserAddress userAddress = new UserAddress(newUser.getId(), provinceCode, districtCode, wardCode, address, 1);
            UserAddressDAO userAddressDAO = new UserAddressDAO();
            userAddressDAO.addNewUserAddress(userAddress);
            // add account access token of new user
            String activeToken = UUID.randomUUID().toString();
            AccountActiveToken accountAtiveToken = new AccountActiveToken(newUser.getEmail(), activeToken, null);
            AccountActiveTokenDAO accountActiveTokenDAO = new AccountActiveTokenDAO();
            accountActiveTokenDAO.createAccountActiveToken(accountAtiveToken);
            // send the account active url to the via emailuser
            SendMailService mailService = new SendMailService();
            mailService.sendVerifyAccount(newUser.getEmail(), activeToken);
            // redirect to thank page
            response.sendRedirect("view\\customer\\thankpage.jsp");
        }

    }

    /**
     * Returns a short descriptio n of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
