<%-- 
    Document   : edit
    Created on : 25-abr-2018, 19:31:49
    Author     : roman
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/header.jsp"/>

    <body class="grey">
        
        <c:import url="template/menu.jsp"/>
        
        <div class="container">
            <div class="card-panel z-depth-3">

                <form action="control.do" method="post">
                    <h3 class="hide-on-small-only"> 
                        Detalle del producto ${producto.nombre}
                    </h3>
                    <!-- FILA -->
                    <div class="row">
                        <!-- COLUMNA 1-->
                        <div class="col s12 m6 l6">
                            <input type="hidden" name="txt1" value="${producto.id}"/>

                            Nombre<br>
                            <input readonly type="text" name="txt2" value="${producto.nombre}"/>

                        </div>
                        <!-- COLUMNA 2-->    
                        <div class="col s12 m6 l6">
                            Precio<br>
                            <input type="text" name="txt3" value="${producto.precio}"/>

                        </div>
                        <!-- COLUMNA 3-->   
                        <div class="col s12">
                            <button class="btn red" name="bt" value="delete">
                                Eliminar
                                <i class="material-icons left">delete</i>
                            </button>
                            <button class="btn blue" name="bt" value="edit">
                                Editar
                                <i class="material-icons left">edit</i>
                            </button>
                        </div>

                    </div>
                </form>
            </div>

        </div>


        <c:import url="template/footer.jsp"/>
    </body>
</html>
