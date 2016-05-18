angular.module("htBillingApp").controller('HomeController', ['$http', '$scope', '$location','localStorageService','authService',function ($http, $scope, $location,localStorageService,authService) {
    
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
    	}else if(userRole.role === "admin"){
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
     * loadReadingForm() function to route to enter reading page
     */
    this.loadReadingForm = function () {
        $location.path("/enterreading");
    };

    /*
     * loadMeterReadingViewPage() function to route to view meter readings page
     */
    this.loadMeterReadingViewPage = function () {
        $location.path("/ht/readings");
    };

    /*
     * function to route to add meter page
     */
    this.loadAddMeterPage = function () {
        $location.path("/addmeter");
    };
    
    /*
     * function to route to add plant page
     */
    this.loadAddPlantPage = function () {
        $location.path("/addplant");
    };

    /*
     * function to route to homepage
     */
    this.loadHome = function () {
        $location.path("/ht/home");
    };
    
    /*
     * function to load operator home page
     */
    this.loadOperatorHome = function () {
        $location.path("/operator/home");
    };
    
    /*
     * function to load circle home page
     */
    this.loadCircleHome = function () {
        $location.path("/circle/home");
    };
}]);