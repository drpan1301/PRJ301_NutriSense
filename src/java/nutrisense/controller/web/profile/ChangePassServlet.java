package nutrisense.controller.web.profile;

import nutrisense.dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nutrisense.model.User;

@WebServlet(name = "ChangePassServlet", urlPatterns = {"/changepass"})
public class ChangePassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String cfnewpass = request.getParameter("cfnewpass");
        String username = request.getParameter("username");
        String msg = "";
        UserDAO dao = new UserDAO();
        User a = dao.check(username, oldpass);
        if (a == null) {
            msg = "Old password is incorrect";
            request.setAttribute("error", msg);
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else {
            if (cfnewpass.equals(newpass)) {
                User newUser = new User(a.getUserName(), a.getFullName(), newpass, a.getAddress(), a.getPhone(), a.getEmail(), a.getImage(), a.getBirthdate(), a.getRoleID());
                dao.changePassword(newUser);
                msg = "Change password successfully!";
                HttpSession session = request.getSession();
                session.setAttribute("account", newUser);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else {
                msg = "New password does not match.";
                request.setAttribute("error", msg);
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
