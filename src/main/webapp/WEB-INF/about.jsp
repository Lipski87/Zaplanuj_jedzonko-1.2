<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>O aplikacji</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>
<c:choose>
    <c:when test="${isEnable == 1}">
        <%@include file="fragments/dashboardHeader.jsp"%>
    </c:when>
    <c:otherwise>
        <%@include file="fragments/header.jsp"%>
    </c:otherwise>
</c:choose>
<div class="col">
    <br>
    <h3 class="mb-4">Lorem ipsum dolor</h3>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis porta dui, vel pretium sem. Mauris suscipit imperdiet mi vel faucibus.<br>
        Etiam mi ipsum, commodo non pharetra eu, volutpat vel massa. Nulla a ipsum ipsum. Maecenas dignissim mi nec magna efficitur, in egestas felis cursus.<br>
        Quisque risus justo, ultricies vitae ipsum vitae, maximus posuere arcu. Integer varius quis felis ut convallis.<br>
        Ut iaculis pulvinar ex, sit amet volutpat arcu elementum vitae. Nullam quis dolor auctor, bibendum eros non, placerat nunc.</p><br>

</div>
<%@include file="fragments/footer.jsp"%>
</body>
</html>
