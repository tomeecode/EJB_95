<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="template/header.jsp"/>

<body class="green">
    <div class="row">
        <div class="col s4 offset-s4">
            <div class="card-panel z-depth-4">
                <h3>Iniciar Sesion!</h3>
                <form action="control.do" method="post">
                    Rut
                    <input type="text" name="x1" requied/>
                    Clave
                    <input type="password" name="x2" required/>
                    <button class="btn" name="bt" value="inicio">
                        ok
                    </button>
                </form>

                <p class="red-text">
                    ${msg}
                </p>
            </div>
        </div>
    </div>




    <c:import url="template/footer.jsp"/>


</body>
</html>
