'use strict';

var App = angular.module('flockApp',[]);



App.config(['$locationProvider', function($locationProvider){  
$locationProvider.html5Mode({
  enabled: true,
  requireBase: false
});	
}]);