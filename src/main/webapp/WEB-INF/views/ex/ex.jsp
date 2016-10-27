<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class='container-fluid'>
    <div ng-controller="HolidayJsController" data-ng-init="init()">
        <div class='row'>
            <div class='col-xs-12'>
                <input class="form-control" placeholder="Type to filter..." type="text" ng-model="searchFor" size="30">
                <br/>
            </div>
        </div>

        <div class='row'>
            <div class='col-xs-12'>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Holiday</th>
                            <th>Observed</th>
                            <th>Official</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr data-ng-repeat="h in holidays | filter:searchFor">
                            <td>{{h.description}}</td>
                            <td>{{h.observedDate}}</td>
                            <td>{{h.officialDate}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
