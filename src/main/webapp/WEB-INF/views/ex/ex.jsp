<div class="container-fluid">

    <div ng-controller="HolidayJsController" data-ng-init="init()">
        <div class="row" style="padding-bottom: 6px;">
            <div class="col-xs-12">
                <b>&nbsp;Year&nbsp;&nbsp;</b>
                <select id="yearCode" name="yearCode" ng-model="yearCode" ng-change="yearChange()">
                    <option ng-repeat="y in years">{{y}}</option>
                    <option value=''>(all years) </option>
                </select>
            </div>
        </div>


        <div class="row">
            <div class="col-xs-12">
                <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                        <tr>
                            <th>Holiday</th>
                            <th>Observed</th>
                            <th>Official</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr data-ng-repeat="h in holidays | filter:yearCode">
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
