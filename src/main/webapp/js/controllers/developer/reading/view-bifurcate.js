angular.module("htBillingApp").controller('ViewBifircateReadingsForDeveloperController', ['$http', '$scope', '$location', '$routeParams','authService', function ($http, $scope, $location, $routeParams,authService) {

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
		$location.path("/developer/home");
	}

	/*
	 * function loadConsumptionData to load the consumption data for 
	 * provided plant and meter
	 */
	function loadConsumptionData() {
		// Getting the consumptionId from the path params and assigning it to a local variable.
		var consumptionId = $routeParams.consumptionId;

		$http(
				{
					method: 'GET',
					url: 'backend/investors/consumption/'+consumptionId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.investorConsumptions = response.data;
						$scope.investorConsumptions.forEach(
								function(item){
									item.generating = false;
								}		
						);
						console.log($scope.investorConsumptions);
					}
				},
				function(error){
					console.log("error while fetching investors consumption from backend");
					console.log(error);
				}
		);
	};

	/*
	 * back function to go back to the last page.
	 */
	this.back = function () {
		window.history.back();
	};

	/*
	 * generateBill function to generate the bills for corresponding investor
	 */
	this.generateBill = function (investorData) {
		investorData.generating = true;
		$http(
				{
					method: 'POST',
					url: 'backend/bill/'+investorData.id
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 201){
						console.log(response);
						var generatedBill = response.data;
						investorData.billDetailsId= generatedBill.id;
						investorData.generating = false;
						investorData.billGenerated = true;
					}
				},
				function(error){
					console.log("error while generating bill.");
					console.log(error);
				}
		);
	};

	/*
	 * viewBill function to navigate to view bill page for corresponding investor's bill
	 */
	this.viewBill = function (investorData) {
		$location.path("/developer/investor/bill/view/"+investorData.billDetailsId);
	};

}]);