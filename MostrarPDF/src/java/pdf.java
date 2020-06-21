
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try{
            
            try{
                
                Connection cnn = null;
                Statement st = null;
                ResultSet rst = null;
                
                //Coneccion a la bd_inventario
                Class.forName("com.mysql.jdbc.Driver");
                cnn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_tareapdf?zeroDateTimeBehavior=convertToNull","root","");
                st = (Statement) cnn.createStatement();
                rst = st.executeQuery("SELECT * FROM datos_persona; ");
                
                if (cnn!=null){
                    try{
              Document documento = new Document();
              PdfWriter.getInstance(documento, out);
              
              //Abrimos documento
              documento.open();
              
              Paragraph p1 = new Paragraph();
              Font fonttitulo = new Font(Font.FontFamily.HELVETICA,16,Font.ITALIC,BaseColor.RED);
              p1.add(new Phrase("Informe bd", fonttitulo));
              p1.setAlignment(Element.ALIGN_CENTER);
              p1.add(new Phrase(Chunk.NEWLINE));
              p1.add(new Phrase(Chunk.NEWLINE));
              documento.add(p1);
              
              Paragraph p2 = new Paragraph();
              Font fontcontenido = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.ITALIC,BaseColor.BLUE);
              p2.add(new Phrase("Tabla datos de personas ", fontcontenido));
              p2.setAlignment(Element.ALIGN_CENTER);
              p2.add(new Phrase(Chunk.NEWLINE));
              p2.add(new Phrase(Chunk.NEWLINE));
              documento.add(p2);
              
              PdfPTable tabla = new PdfPTable(3);
              PdfPCell celda1 = new PdfPCell (new Paragraph("Nombre ", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));
              PdfPCell celda2 = new PdfPCell (new Paragraph("Apellido ", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));
              PdfPCell celda3 = new PdfPCell (new Paragraph("Celular ", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK)));
              
              tabla.addCell(celda1);
              tabla.addCell(celda2);
              tabla.addCell(celda3);
              
              //Recorrido
              
              while (rst.next()){
                  tabla.addCell(rst.getString(1));
                  tabla.addCell(rst.getString(2));
                  tabla.addCell(rst.getString(3));

              }
              documento.add(tabla);

              documento.close();
          }
            catch(Exception ex){
              ex.getMessage();
          }  
                }
                
            }catch(Exception ex){
                ex.getMessage();
            }
          
        }
        finally{
            out.close();
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
