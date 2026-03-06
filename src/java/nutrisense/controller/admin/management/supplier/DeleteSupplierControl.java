package nutrisense.controller.admin.management.supplier;

import nutrisense.dal.SupplierDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteSupplier", urlPatterns = {"/deletesupplier"})
public class DeleteSupplierControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idSupplier_raw = request.getParameter("sid");
        SupplierDAO dao = new SupplierDAO();
        String msg = "";
        int idSup;
        try {
            idSup = Integer.parseInt(idSupplier_raw);
            dao.deleteSupplier(idSup);
            msg = "Supplier " + idSup + " deleted successfully";
            request.setAttribute("mess", msg);
            request.getRequestDispatcher("managersupplier").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
