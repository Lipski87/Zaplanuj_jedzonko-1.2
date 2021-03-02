<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 02.03.2021
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Kontakt</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>
</head>
<body>
<%@include file="fragments/header.jsp"%>
<div class="container w-25">
<form class="padding-small text-center">
    <h1 class="text-color-darker">Kontakt</h1>
    <div class="form-group">
        <input type="text" class="form-control" id="email" name="email" placeholder="podaj adres email">
    </div>
    <div class="form-group">
        <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię i nazwisko">
    </div>
    <div class="form-group">
        <input type="text" class="form-control" id="message" name="message" placeholder="napisz swoją wiadomość">
    </div>
    <button class="btn btn-color rounded-0" type="submit">Wyślij</button>
</form>
</div>
<%@include file="fragments/footer.jsp"%>
</body>
</html>
