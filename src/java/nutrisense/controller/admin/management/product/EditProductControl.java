package nutrisense.controller.admin.management.product;

import nutrisense.dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditControl", urlPatterns = {"/editproduct"})
public class EditProductControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String pid_raw = request.getParameter("id");
        String pname = request.getParameter("name");
        String[] pimage = request.getParameterValues("image");

        String pprice_raw = request.getParameter("price");
        String pdescribe = request.getParameter("describe");
        String pquantity_raw = request.getParameter("quantity");
        String pdate = request.getParameter("date");
        String pdiscount_raw = request.getParameter("discount");
        String pquantityunit = request.getParameter("quantityperunit");

        String pcategory_raw = request.getParameter("category");
        String psupplier_raw = request.getParameter("supplier");
        ProductDAO dao = new ProductDAO();
        double pprice, pdiscount;
        int pid, pquantity, psupplier, pcategory;
        String image = "";
        try {
            pprice = Double.parseDouble(pprice_raw);
            pdiscount = Double.parseDouble(pdiscount_raw);
            pquantity = Integer.parseInt(pquantity_raw);
            pid = Integer.parseInt(pid_raw);
            psupplier = Integer.parseInt(psupplier_raw);
            pcategory = Integer.parseInt(pcategory_raw);
            if (pimage[0] != null && !pimage[0].equals("")) {
                for (int i = 0; i < pimage.length; i++) {
                    switch (pcategory) {
                        case 1:
                            image += "images/products/Men/" + pimage[i] + ",";
                            break;
                        case 2:
                            image = "images/products/Women/" + pimage[i] + ",";
                            break;
                        case 3:
                            image = "images/products/Kids/" + pimage[i] + ",";
                            break;
                        case 4:
                            image = "images/products/Unisex/" + pimage[i] + ",";
                            break;
                        case 5:
                            image = "images/products/Gift/" + pimage[i] + ",";
                            break;
                    }
                }
                if (image.endsWith(",")) {
                    image = image.substring(0, image.length() - 1);
                }
            }
            dao.editProduct(pname, image, pprice, pdescribe, pquantity, pquantityunit, pdate, pdiscount, psupplier, pcategory, pid);
            request.setAttribute("mess", "Edit successfully!");
            request.getRequestDispatcher("manager").forward(request, response);
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
    }// </editor-fold>

}
