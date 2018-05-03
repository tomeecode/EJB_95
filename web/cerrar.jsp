<%
    //CERRAMOS LA SESION DESTRUIR PERSON
    //request.getSession().invalidate();
    session.invalidate();
    response.sendRedirect("inicio.jsp");
%>
