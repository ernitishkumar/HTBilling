var htBillingApp = angular.module('htBillingApp');

htBillingApp.controller('SaveController', ['$http', '$scope', '$location', '$routeParams','authService', function ($http, $scope, $location, $routeParams, authService) {
	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};
	
	/* 
	 * checkUser function. checks whether any user is logged into the system
	 * and if he is authorized to view this page according to his role
	 *  if not then he is redirected to login page 
	 */

	var checkUser = function () {
		var user = authService.fetchData(authService.USER_KEY);
		var userRole = authService.fetchData(authService.USER_ROLE_KEY);
		if(user === null || user === undefined || user.username === null || user.username == undefined || userRole === null || userRole === undefined){
			$location.path("/");
		}else if(userRole.role === "admin" || userRole.role === "operator"){
			$scope.user = user;
			$scope.userRole = userRole;
		}else{
			$location.path("/");
		}
	};

	/* 
	 * calling checkUser() function on page load 
	 */
	checkUser();
	
    /*
     * controller level variable to hold message to be displayed on the page.
     */
	$scope.message = $routeParams.message;
    
	/* 
	 * logout function. Removes all local storage data
	 * and routes to login page
	 */
	this.logout = function () {
		$scope.user = {};
		$scope.userRole = {};
		authService.logout();
	};

	/*
	 * function to navigate to respective home page
	 * as per the users role
	 */
    this.loadHome = function () {
        var userRole = $scope.userRole;
        var path = '/';
        if (userRole.role === 'admin' || userRole.role === 'ht') {
            path = '/ht/home';
        }else if (userRole.role === 'operator') {
            path = '/operator/home';
        }
        $location.path(path);
    };

}]);