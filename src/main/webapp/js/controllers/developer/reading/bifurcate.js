angular.module("htBillingApp").controller('BifircateReadingsController', ['$http', '$scope', '$location', '$routeParams','authService', function ($http, $scope, $location, $routeParams,authService) {

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

		var plantId = $routeParams.plantId;

		var consumptionId = $routeParams.consumptionId;
		//Getting Investors for bifurcation of readings
		$http(
				{
					method: 'GET',
					url: 'backend/investors/bifurcation/'+plantId
				}
		).then(
				function (response) {
					var status = response.status;
					if (status === 200) {
						$scope.investorsData = response.data;
						console.log($scope.investorsData);
					}
				},
				function(error){
					console.log("error while fetching investors for bifurcation.below is error");
					console.log(error);
				}
		);

		//Getting overall consumption data
		$http(
				{
					method: 'GET',
					url: 'backend/consumptions/'+consumptionId
				}
		).then(
				function (response) {
					var status = response.status;
					if (status === 200) {
						$scope.consumption = response.data;
					}
				},
				function(error){
					console.log("unable to fetch consumption. error below");
					console.log(error);
				}
		);
	};

	/*
	 * function to save bifurcated investors wise readings
	 * to the backend server.
	 */
	this.saveDistribution = function () {
		var investors = $scope.investorsData;
		var consumption = $scope.consumption;
		var totalActive = 0;
		var totalReactive = 0;
		investors.forEach(function (item) {
			totalActive += item.activeConsumption;
			totalReactive += item.reactiveConsumption;
			item.consumptionId = consumption.id;
			item.investorId = item.investor.id;
		});
		if (totalActive === consumption.activeConsumption && totalReactive === consumption.reactiveConsumption) {
			$http(
					{
						method: 'POST',
						url: 'backend/investors/consumption',
						data: investors
					}
			).then(
					function (response) {
						var status = response.status;
						if(status === 201){
							var insertedInvestorsConsumption = response.data;
							updateBifurcationFlag(consumption);
						}
					},
					function(error){
						console.log("error while saving investor wise consumptions");
						console.log(error);
					}
			);
		} else {
			alert("Readings sum is not equal to total Consumption. Please check !");
		}
	};

	/*
	 * updateBifercationFlag(id) function to update the bifurcation flag of consumption data
	 * in the backend when developer successfully bifurcates the consumption data
	 */
	function updateBifurcationFlag(consumption) {
		//Updating the consumptionBifurcated property of the controller's local variable and passing
		//it to the backend to update in database.
		consumption.consumptionBifurcated = 1;
		
		$http(
				{
					method: 'PUT',
					url: 'backend/consumptions/'+consumption.id,
					data: consumption
				}
		).then(
				function (response) {
					var status = response.status;
					if (status === 200) {
						alert("Bifurcated Successfully.")
						$location.path("/viewdeveloperreading");
					}
				},
				function(error){
					console.log("error while updating flag.error below : ");
					console.log(error);
				}
		);
	}
}]);