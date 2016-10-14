(function() {

    function HolidayJsController($scope, dataProvider) {
        var url = '/holiday/api/holidays';
        $scope.init = function() {
            dataProvider.loadData(function(data) {
                $scope.holidays = data;
            }, url);
        };
    }
    holidayApp.controller("HolidayJsController", HolidayJsController);
    
})();
