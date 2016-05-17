angular.module("htBillingApp").controller('ViewBifircateReadingsController', ['$http', '$scope', '$location', '$routeParams','authService', function ($http, $scope, $location, $routeParams,authService) {

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};

	/*
	 * var sno a controller level variable to store Serial No.
	 */
    $scope.sno = 0;

    /*
     * var generating a controller level variable to store boolean value.
     */
    $scope.generating = false;
    
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
		}else if(userRole.role === "developer"){
			$scope.user = user;
			$scope.userRole = userRole;
			loadConsumptionData();
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
	 * loadDeveloperHome function to navigate to developer home page
	 */
    this.loadDeveloperHome = function () {
        $location.path("/developerhome");
    }

    /*
	 * function loadConsumptionData to load the consumption data for 
	 * provided plant and meter
	 */
    function loadConsumptionData() {
    	
        var plantId = $routeParams.plantId;
        
        var meterNo = $routeParams.meterNo;
        
        var consumptionId = $routeParams.consumptionId;
        
        $http({
            method: 'GET',
            url: 'InvestorConsumptionController',
            params: {
                action: 'getinvestorconsumption',
                plantId: plantId,
                meterNo: meterNo
            }
        }).then(function (response) {
            var result = response.data.Result;
            if (result === 'OK') {
                $scope.investorConsumptions = response.data.InvestorConsumptionData;
                $scope.investorConsumptions.forEach(
                function(item){
                	item.generating = false;
                }		
                );
                console.log($scope.investorConsumptions);
            }
        });
    };

    this.back = function () {
        $location.path("/viewdeveloperreading");
    };
    
    this.generateBill = function (investorData) {
    	investorData.generating = true;
    	$http({
            method: 'GET',
            url: 'BillingController',
            params: {
                action: 'generate',
                consumptionId: investorData.consumption.id,
                investorId: investorData.investor.id,
                bifurcationId:investorData.id
            }
        }).then(function (response) {
        	var result = response.data.Result;
        	if(result === "OK"){
        		var lastInsertedId = response.data.BillId;
        		investorData.billDetailsId= lastInsertedId;
        		investorData.generating = false;
        		investorData.billGenerated = true;
        		console.log("Object after generating bill : ");
        	}
        });
    };
    
    this.viewBill = function (investorData) {
    	$location.path("/viewbill/"+investorData.billDetailsId);
    };

}]);