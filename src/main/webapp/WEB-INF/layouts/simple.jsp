<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en" ng-app="holidayApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>University of Hawaii | Holidays</title>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/styles/app-main-001.css" />"   type="text/css" media="screen" />
    <script type="text/javascript" src="<c:url value="/resources/angular-1.5.8/angular.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/holiday.app.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/holiday.service.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/holiday.controller.js" />"></script>           
</head>
<body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    <script type="text/javascript" src="<c:url value="/resources/javascript/jquery/jquery-2.1.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/holiday-util.js" />"></script>
</body>
</html>
