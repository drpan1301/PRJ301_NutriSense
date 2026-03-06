package nutrisense.controller.admin.management.supplier;

import nutrisense.dal.CategoryDAO;
import nutrisense.dal.SupplierDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nutrisense.model.Supplier;

@WebServlet(name = "AddSupplierControl", urlPatterns = {"/addsupplier"})
public class AddSupplierControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SupplierDAO sd = new SupplierDAO();

        String CompanyName = request.getParameter("CompanyName");
        String ContactName = request.getParameter("ContactName");
        String Country = request.getParameter("Country");
        String Phone = request.getParameter("Phone");
        String HomePage = request.getParameter("HomePage");
        Supplier supplier = new Supplier(0, CompanyName, ContactName, Country, Phone, HomePage);
        String msg = "";
        sd.insert(supplier);
        msg = "Supplier " + supplier.getCompanyName() + " add successfully!";
        request.setAttribute("mess", msg);
        response.sendRedirect("managersupplier");
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
