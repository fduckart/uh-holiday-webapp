<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="util"     uri="/WEB-INF/holiday.tld"  %>

<div class='container-fluid'>

    <div class='row'>
        <div class="col-xs-12">
            <c:if test="${not empty holidayHolder.years}">
                <c:url var="formAction"  value="${contextPath}/" />
                <div class="panel panel-success">
                    <div class="panel-body">
                        <table class="table table-bordered table-condensed">
                            <tbody>
                                <tr>
                                    <td colspan="3" valign="bottom" class="holiday-year">
                                        <form:form id="form" method="POST" action="${formAction}" modelAttribute="holidayHolder">
                                            Year:&nbsp;
                                            <form:select items="${holidayHolder.years}" path="year" />
                                        </form:form>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Holiday</th>
                                    <th>Observed</th>
                                    <th>Official</th>
                                <tr>                                
                                <c:forEach var="s" items="${util:retrieveHolidays(holidayHolder)}">
                                    <tr onMouseOver="this.bgColor='#E5F9C5';" onMouseOut="this.bgColor='#FFFFFF';">
                                        <td><c:out value="${s.description}" default=" "/></td>
                                        <td><fmt:formatDate value="${s.observedDate}" pattern="MMMMM dd, yyyy"/></td>
                                        <td><fmt:formatDate value="${s.officialDate}" pattern="MMMMM dd, yyyy"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

</div>
