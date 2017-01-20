'use strict';

App.controller('FlockAppController', ['$scope','$location','FlockAppService', function($scope,$location,FlockAppService) {
    var self = this;
   $scope.userId='';
   $scope.showMain = true;
   $scope.showSteps = false;
   //$scope.target = $location.search()['target'];
   $scope.token = $location.search()['token'];
   $scope.jiraUrl='';
   var jsonData = { 'name':$scope.name, 'employees': $scope.employees, 'headoffice':$scope.headoffice }
   $scope.ShowHide = function (jiraUrl) {
   $scope.jiraUrl=jiraUrl;
		console.log($scope.jiraUrl);
       $scope.showSteps = $scope.showSteps ? false : true;
	   $scope.showMain = false;
	   FlockAppService.fetchUserId($scope.token)
                  .then(
      					       function(d) {
      						       $scope.userId = d.userid;
								   self.fetchFlockJiraData()
      					       },
            					function(errResponse){
            						console.error('Error while fetching Devices');
            					}
      			       );
    };
	
	self.fetchFlockJiraData = function(){
	var jsonReqData = { 'userid':$scope.userId, 'jiraurl': $scope.jiraUrl}
              FlockAppService.fetchFlockJiraData(jsonReqData)
		              .then(
            
				              function(errResponse){
					               console.error('Error while creating Device.');
				              }	
                  );
          };
	self.fetchToken = function(){
	var jsonReqData = { 'userid':$scope.userId, 'jiraurl': $scope.jiraUrl}
              FlockAppService.fetchFlockJiraData(jsonReqData)
		              .then(
                      
				              function(errResponse){
					               console.error('Error while creating Device.');
				              }	
                  );
          };	  
 $scope.callToken = function () {
 };

}]);

    
