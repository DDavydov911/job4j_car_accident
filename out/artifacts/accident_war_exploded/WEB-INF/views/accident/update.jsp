<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="option" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Редактирование события.
            </div>
            <div class="card-body">
                <form action="<c:url value='/update'/>" method='POST'>
                    <input type="hidden" name="id" value="${accident.id}"/>
                    <div class="form-group">
                        <label for="name">Название:</label>
                        <input type="text" class="form-control" value="${accident.name}" name="name" id="name" >
                    </div>

                    <div class="form-group">
                        <label for="text">Текст:</label>
                        <input type="text" class="form-control" value="${accident.text}" name="text" id="text">
                    </div>

                    <div class="form-group">
                        <label for="address">Адрес:</label>
                        <input type="text" class="form-control" value="${accident.address}" name="address" id="address">
                    </div>

                    <div>
                        <label for="type">Тип проишествия:</label>
                        <select name="type.id" id="type">
                            <c:forEach var="type" items="${types}" >
                                <option value="${type.id}" ${type.id == accident.type.id ? 'selected' : ''}>${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="rules">Статьи:</label>
                        <select class="form-control" name="rIds" id="rules" multiple>
                            <c:forEach var="rule" items="${rules}">
                                <option value="${rule.id}" ${fn:contains(accident.rules, rule) ? 'selected' : ''}>${rule.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>