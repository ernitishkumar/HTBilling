angular.module("htBillingApp").controller('CircleHomeController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {
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
    	}else if(userRole.role === "circle"){
    		$scope.user = user;
    	}else{
    		$location.path("/");
    	}
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
     * loadCircleReadingViewPage() function to route to view meter readings page
     */
    this.loadCircleReadingViewPage = function () {
        $location.path("/circle/readings");
    	//$location.path("/circle/readings/consumptions");
    };
    
    this.loadCircleReadingListPage = function () {
        $location.path("/circle/readings/list");
    	//$location.path("/circle/readings/consumptions");
    };

    /*
     * loadCircleHome function to route to homepage
     */
    this.loadCircleHome = function () {
        $location.path("/circle/home");
    };
}]);