'use strict';

App.factory('FlockAppService', ['$http', '$q', function($http, $q){

	return {
			fetchUserId: function(token) {
					return $http.get('/rest/config/eventtoken?token='+token)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching Can Data');
										return $q.reject(errResponse);
									}
							);
			},
			fetchFlockJiraData: function(jsonReqData) {
					return $http.post('/rest/config/jiraurl',jsonReqData)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching Can Data');
										return $q.reject(errResponse);
									}
							);
			},
            fetchToken: function(jsonReqData) {
					return $http.post('/rest/config/token',jsonReqData)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching Can Data');
										return $q.reject(errResponse);
									}
							);
			}			
		
	};

}]);
