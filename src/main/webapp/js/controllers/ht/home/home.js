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
     * logout function. Removes all local storage data
     * and routes to login page
     */
    this.logout = function () {
    	$scope.user = {};
    	$scope.userRole = {};
    	authService.logout();
    };

    /*
     * loadReadingForm() function to route to enter reading page
     */
    this.loadReadingForm = function () {
        $location.path("/enterreading");
    };

    /*
     * loadSRFRReadingForm() function to route to enter srfrreading page
     */
    this.loadSRFRReadingForm = function () {
        $location.path("/srfrenterreading");
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
        $location.path("/meter/add");
    };
    
    /*
     * function to route to view meter details page
     */
    this.loadViewMeterPage = function(){
    	$location.path("/meter/view");
    }
    
    /*
     * function to route to add plant page
     */
    this.loadAddPlantPage = function () {
        $location.path("/plant/add");
    };

    /*
     * function to route to view plants page
     */
    this.loadViewPlantPage = function(){
    	$location.path("/plant/view")
    }
    
    /*
     * function to route to add developer page
     */
    this.loadAddDeveloperPage = function(){
    	$location.path("/developer/add");
    }
    
    /*
     * function to route to view developers page
     */
    this.loadViewDeveloperPage = function(){
    	$location.path("/developer/view");
    }
    
    /*
     * function to route to add investor page
     */
    this.loadAddInvestorPage = function(){
    	$location.path("/investor/add");
    }

    /*
     * function to route to view investors page
     */
    this.loadViewInvestorPage = function(){
    	$location.path("/investor/view");
    }
    
    /*
     * function to route to add machine page
     */
    this.loadAddMachinePage = function(){
    	$location.path("/machine/add");
    }
    
    /*
     * function to route to view machines page
     */
    this.loadViewMachinePage = function(){
    	$location.path("/machine/view");
    }
    
    /*
     * function to route to add user page
     */
    this.loadAddUserPage = function(){
    	$location.path("/user/add");
    }
    
    /*
     * function to route to add investor to plants page.
     */
    this.loadInvestorPlantMapping = function(){
    	$location.path("/plant/investor/add");
    }
    
    /*
     * function to route to view investors of a plant
     */
    this.loadViewInvestorPlantMapping = function(){
    	$location.path("/plant/investor/view");
    }
    
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