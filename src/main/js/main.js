var $ = require("jquery");
require("angular");

angular.module('createam', [])
.controller('HeartbeatController', function($scope, $http) {
    setInterval(function() {
        $http({
            method: 'GET',
            url: 'http://createam-api.herokuapp.com/heartbeat'
        }).then(function successCallback(response) {
            $scope.heartbeat = response.data;
        }, function errorCallback(response) {
            $scope.heartbeat.message = 'no message available';
            $scope.heartbeat.heartbeats = 'no heartbeats available';
            $scope.heartbeat.uptime = 'no uptime available';
            });
            console.log("Request sent!")
        }, 1000
    );
});
