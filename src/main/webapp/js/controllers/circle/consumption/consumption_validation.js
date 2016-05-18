angular.module("htBillingApp").controller('CircleConsumptionValidationController', ['$http', '$scope', '$location', '$routeParams','authService', function ($http, $scope, $location, $routeParams,authService) {
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
    		loadCircleConsumptions();
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
     * function loadCircleHome to navigate to the home page of circle's user.
     */
    this.loadCircleHome = function () {
        $location.path("/circlehome");
    };

    /*
	 * function loadCircleConsumptions to load the consumption data for 
	 * different plant under logged in circle's user for validation
	 */
    function loadCircleConsumptions() {
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'getinvestorconsumption',
                circle: $scope.user.circle
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.consumptions = response.data.MeterConsumptionData;
                console.log($scope.consumptions);
            }
        });
    };

    this.validateReading = function (consumptionData) {
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'validate_consumption',
                consumptionId: consumptionData.consumption.id
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'VALIDATED') {
                $scope.consumptions.forEach(
                    function (item) {
                    	
                        if (item.consumption.id === consumptionData.consumption.id) {
                            item.investorBifurcations.forEach(
                            function(investorConsumption){
                            	investorConsumption.circleValidation = 1;
                            }		
                            );
                        }
                    }
                );
            }
            console.log("Data after validation : "+$scope.consumptions);
        });
    };

}]);