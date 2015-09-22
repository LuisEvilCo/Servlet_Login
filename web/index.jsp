<%--
  Created by IntelliJ IDEA.
  User: Luis
  Date: 22/09/15
  Time: 3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <p>Defaul page of proyect</p>
  <%
      int a = 5;
      //out.println(a*a);
  %>
    <form method="post" action="login">
        <p>Usuario :
            <input type="text" name="User"/>
        </p>
        <br/>
        <p>Contraseña :
            <input type="text" name="Password"/>
        </p>
        <br/>
        <input type ="submit"/>
    </form>

  <p>Arturo come tierra</p>
  </body>
</html>
