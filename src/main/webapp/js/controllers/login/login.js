angular.module("htBillingApp").controller('FormController', ['$http', '$scope', '$location','authService','localStorageService','utilService',function ($http, $scope, $location,authService,utilService,localStorageService) {
	/*
	 * controller level variable formData to hold login form data
	 */
	$scope.formData = {};
	
	/*
	 * function processForm which gets executed when user clicks login on the page.
	 * It passes the login form data to backend servers for validation,once validate
	 * route the current page to respective page as per user's role.
	 */
	this.processForm = function () {
        authService.login($scope.formData).then(
        		function (response) {
					authService.storeData(authService.USER_KEY,response.data.user);
					authService.storeData(authService.USER_ROLE_KEY,response.data.userRoles);
					var userRoles = response.data.userRoles;
					if (userRoles.role === "admin") {
						$location.path("/ht/home");
					} else if (userRoles.role === "operator") {
						$location.path("/operator/home");
					} else if (userRoles.role === "circle") {
						$location.path("/circle/home");
					} else if (userRoles.role === "developer") {
						$location.path("/developer/home");
					}else if (userRoles.role === "amr") {
						$location.path("/amr/home");
					}
				},
				function(error){
					var loginResult = error.data.loginResult;
					if(!(loginResult == null || loginResult == undefined)){
						$scope.error = "Invalid username/password. Try again!";
					}else{
						$scope.error = "Some error occurred. Please try again!";
					}
				}
		);
	};
}]);