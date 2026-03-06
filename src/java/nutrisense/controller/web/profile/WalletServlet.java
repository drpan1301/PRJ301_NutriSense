package nutrisense.controller.web.profile;

import nutrisense.dal.WalletDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nutrisense.model.Wallet;

@WebServlet(name="WalletServlet", urlPatterns={"/wallet"})
public class WalletServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WalletServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WalletServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userN");
        String valueStr =  request.getParameter("add");
        double value = 0;
        try {
            value = Double.parseDouble(valueStr);
        } catch (Exception e) {
        }
        WalletDAO wd = new WalletDAO();
        // udate amount
        wd.inputMoney(userName, value);
        Wallet wallet = wd.getWalletByUserName(userName);
        session.setAttribute("wallet", wallet);
        
        request.getRequestDispatcher("profile").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
