<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
        rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<%@include file="fragments/dashboardHeader.jsp"%>

    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
                <ul class="nav flex-column long-bg">
                        <li class="nav-item">
                            <a class="nav-link" href='<c:url value="/app/dashboard"/>'>
                                <span>Pulpit</span>
                                <i class="fas fa-angle-right"></i>
                            </a>
                        </li>
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/app/recipe/list"/>'>
                            <span>Przepisy</span>
                            <i class="fas fa-angle-right"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/app/plan/list"/>'>
                                <span>Plany</span>
                                <i class="fas fa-angle-right"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href='<c:url value="/app/edit-user"/>'>
                                <span>Edytuj dane</span>
                                <i class="fas fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>

            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">

                        </div>
                    </div>

                    <div class="schedules-content">
                        <form action="/app/recipe/plan/add" method="post">
                            <div class="form-group row">
                                <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                    Wybierz plan
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="choosePlan" name="plan">
                                        <c:forEach items="${planList}" var="plan" varStatus="LoopStatus">
                                        <option value="${plan.id}">${plan.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="name" class="col-sm-2 label-size col-form-label">
                                    Nazwa posi??ku
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="" id="name" placeholder="Nazwa posi??ku" name="name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="number" class="col-sm-2 label-size col-form-label">
                                    Numer posi??ku
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" value="" id="number" placeholder="Numer posi??ki" name="number">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="recipie" class="col-sm-2 label-size col-form-label">
                                    Przepis
                                </label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="recipie" name="recipie">
                                        <c:forEach items="${recipeList}" var="recipe" varStatus="LoopStatus">
                                            <option value=${recipe.id}>${recipe.name}</option>
                                        </c:forEach>
                                    </select> 
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="day" class="col-sm-2 label-size col-form-label">
                                    Dzie??
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="day", name="day">
                                        <c:forEach items="${dayNameList}" var="dayName" varStatus="LoopStatus">
                                            <option value="${dayName.id}">${dayName.name}</option>
                                        </c:forEach>
                                    </select>   
                                </div>
                            </div>
                            <input type="submit" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4"></input>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
    <%@include file="fragments/footer.jsp"%>
</body>
</html>