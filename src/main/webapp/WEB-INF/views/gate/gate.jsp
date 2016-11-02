<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="util"     uri="/WEB-INF/holiday.tld"  %>
<div class='container-fluid'>
    <div class='row'>
        <div class="col-xs-12" style='padding-top: 10px;'>
            <c:if test="${not empty holidayHolder.years}">
                <c:url var="formAction"  value="${contextPath}/" />
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <form:form id="form" method="POST" action="${formAction}" modelAttribute="holidayHolder">
                            Year:&nbsp;
                            <form:select items="${holidayHolder.years}" path="year" />
                            <form:hidden path="year"/>
                        </form:form>
                    </div>
                    <table class="table table-bordered table-hover table-condensed">
                        <tbody>
                            <tr>                                
                                <th>Holiday</th>
                                <th>Observed</th>
                                <th>Official</th>
                            <tr>
                            <c:forEach var="s" items="${util:retrieveHolidays(holidayHolder)}">
                                <tr>
                                    <td><c:out value="${s.description}" default=" "/></td>
                                    <td><fmt:formatDate value="${s.observedDate}" pattern="MMMMM dd, yyyy"/></td>
                                    <td><fmt:formatDate value="${s.officialDate}" pattern="MMMMM dd, yyyy"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
