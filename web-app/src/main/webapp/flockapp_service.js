'use strict';

App.factory('FlockAppService', ['$http', '$q', function($http, $q){

	return {
			fetchUserId: function(token) {
					return $http.get('http://localhost:8084/rest/eventtoken?token='+token)
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
			fetchFlockJiraData: function(jsonData) {
					return $http.post('http://localhost:8084/rest/config/jiraurl',jsonReqData)
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
            fetchToken: function(jsonData) {
					return $http.post('http://localhost:8084/rest/config/token',jsonReqData)
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
