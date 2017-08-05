var $ = require("jquery");
require("angular");

angular.module('createam', [])
.controller('HelloController', function($scope, $http) {
  $http({
    method: 'GET',
    url: 'https://createam-api.herokuapp.com/heartbeat'
  }).then(function successCallback(response) {
    $scope.hello = response.data;
  }, function errorCallback(response) {
    $scope.hello.message = 'no message available';
    $scope.hello.heartbeats = 'no heartbeats available';
    $scope.hello.uptime = 'no uptime available';
  });
});
