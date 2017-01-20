'use strict';

App.controller('FlockAppController', ['$scope','$location','FlockAppService', function($scope,$location,FlockAppService) {
    var self = this;
   $scope.userId='';
   $scope.showMain = true;
   $scope.showSteps = false;
   $scope.finalMsg = false;
   $scope.showKeys = false;
 $scope.baseUrl = $location.host();
   //$scope.target = $location.search()['target'];
   $scope.token = $location.search()['flockEventToken'];
   $scope.applicationName='flock-jira';
    $scope.publicKey='';
    $scope.consumerKey='';
    $scope.webhook='http://'+$scope.baseUrl+'/rest/jira';
     $scope.hostname='http://'+$scope.baseUrl;
    $scope.callBackUrl='http://'+$scope.baseUrl+'/rest/config/verify';
   $scope.jiraUrl='';
   $scope.ShowHide = function (jiraUrl) {
   $scope.jiraUrl=jiraUrl;
		console.log($scope.jiraUrl);
		console.log('Console token is:'+$scope.token);
       $scope.showSteps = $scope.showSteps ? false : true;
	   $scope.showMain = false;
	   FlockAppService.fetchUserId($scope.token)
                  .then(
      					       function(d) {
      						       $scope.userId = d.userId;
								   self.fetchFlockJiraData(d.userId);
      					       },
            					function(errResponse){
            						console.error('Error while fetching Devices');
            					}
      			       );
    };
	
	self.fetchFlockJiraData = function(userId){
	var jsonReqData = { 'userId':userId, 'jiraUrl': $scope.jiraUrl}
              FlockAppService.fetchFlockJiraData(jsonReqData)
		              .then(
                                    function(d) {
                  						       $scope.publicKey=d.publicKey;
                  						       $scope.consumerKey=d.consumerKey;
                  						       $scope.showKeys = true;
                  					       },
				              function(errResponse){
					               console.error('Error while creating Device.');
				              }	
                  );
          };
	self.fetchToken = function(){
	var jsonReqData = { 'userId':$scope.userId, 'jiraUrl': $scope.jiraUrl}
              FlockAppService.fetchToken(jsonReqData)
		              .then(
		              function(d) {
                                   window.open(d.url, '_blank');
                                        					       },
				              function(errResponse){
					               console.error('Error while creating Device.');
				              }	
                  );
          };	  
 $scope.callToken = function () {

   $scope.showMain = false;
    $scope.showSteps = false;
    $scope.finalMsg = true;
    self.fetchToken();
    $scope.showKeys = false;
 };

}]);

    
