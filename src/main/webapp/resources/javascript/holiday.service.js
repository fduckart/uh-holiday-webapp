(function() {

    holidayApp.factory('dataProvider', function($http) {
        return {
            loadData: function(callback, url) {
                $http.get(encodeURI(url))
                     .success(callback)
                     .error(function(data, status) {
                         console.log('Error in dataProvider; status: ', status);
                     });
            },
            saveData: function(callback, url, data) {
                $http.post(url, data)
                    .success(function (data, status, headers, config) {
                        callback(data);
                    })
                    .error(function (data, status, header, config) {
                        console.log("Data: " + data +
                            "<hr />status: " + status +
                            "<hr />headers: " + header +
                            "<hr />config: " + config);
                    });
            }
        }
    });

})();
