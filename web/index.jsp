

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.model.ServicioLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! private ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/EJB_95/Servicio!cl.model.ServicioLocal");
%>

<c:set scope="page"
       var="lista" 
value="<%= servicio.getLista()%>"/>


<c:import url="template/header.jsp"/>
   
    <body class="grey">
        <c:if test="${not empty sessionScope.person}">
            
            <c:import url="template/menu.jsp"/>
            
            <div class="container">

                <form action="control.do" method="post">
                    <div class="card-panel z-depth-3">
                        <h4>EJB 95. Bienvenido ${person.nombre}</h4>
                        Nombre<br>
                        <input type="text" name="txt1"/>
                        <bR>
                        Precio
                        <input type="text" name="txt2"/>
                        <bR>
                        <button class="btn blue-grey lighten-2" name="bt" value="add">
                            Guardar
                            <i class="material-icons left">save</i>
                        </button>
                    </div>

                    <br><br>
                    <div class="card-panel z-depth-3">
                        <table class="bordered highlight white">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th></th>
                            </tr>
                            <c:forEach var="p" items="${lista}">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.nombre}</td>
                                    <td>${p.precio}</td>
                                    <td>
                                        <button class="btn-floating blue" name="bt" value="${p.id}">
                                            <i class="material-icons">edit</i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </form>

            </div>
        </c:if>
        <c:if test="${empty sessionScope.person}">
            <div class="container">
                <p class="red white-text">
                    Debes iniciar sesion
                    para estar aqui.
                    <br>
                    <a href="inicio.jsp">Home</a>
                </p>
            </div>
        </c:if>



        <c:import url="template/footer.jsp"/>
    </body>
</html>
