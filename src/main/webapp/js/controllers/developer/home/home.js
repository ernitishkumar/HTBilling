angular.module('htBillingApp').controller('DeveloperController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location, authService) {

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
    	}else if(userRole.role !== "developer"){
    		$location.path("/");
    	}
    	$scope.user = user;
    	$scope.userRole = userRole;
    };
    
    /* 
     * calling checkUser() function on page load 
     */
    checkUser();

    /* 
     * logout function. Removes all local storage data
     * and routes to login page
     */
    this.logout = function () {
    	$scope.user = {};
    	authService.logout();
    };

    /*
     * developerHome function to load developer homepage when invoked.
     */
    this.developerHome = function () {
        $location.path("/developer/home");
    };

    /*
     * loadViewReadingForm function to load view readings for all the plants 
     * developed by logged in developer
     */
    this.loadViewReadingForm = function () {
        $location.path("/developer/readings/view");
    }

}]);