<%-- 
    Document   : index
    Created on : 06-20-2020, 12:53:52 AM
    Author     : alex
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="gray">
    <center>  <div style=" border: double black 5px; width: 300px; height: 300px;background-color: aqua">
            <center> <h1 style="font-family: Algerian">Datos persona</h1></center> 

        <%
        Connection cnn;
        Statement st ;
        ResultSet rst;

       
        Class.forName("com.mysql.jdbc.Driver");
        cnn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_tareapdf?zeroDateTimeBehavior=convertToNull","root","");
        st = (Statement) cnn.createStatement();
        rst = st.executeQuery("SELECT * FROM datos_persona; ");
        
        %>
    <center>
        <div>
        <table border="10">
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Celular<th/>  
            
            </tr>
             <%
                while(rst.next()){
                
             %>
             <tr>
                 <td><%= rst.getString("nombre")%></td>
                 <td><%= rst.getString("apellido")%></td>
                 <td><%= rst.getInt("celular")%></td>
             </tr>
             <%}%>
        </table>
        </div>
        <br>
         <form name="reporte" action="/MostrarPDF/pdf" method="POST">
            <input type="submit" value="Crear PDF" name="btnver" />
        </form></center>
        </div>
    </center> 
    </body>
</html>
