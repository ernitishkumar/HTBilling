angular.module("htBillingApp").controller('CircleViewMeterReadingsController', ['$http', '$scope', '$location', '$routeParams','authService','utilService', function ($http, $scope, $location, $routeParams,authService,utilService) {

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
			$scope.userRole = userRole;
			loadCircleReadings();
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
	 * loadDeveloperHome function to navigate to developer's homepage.
	 */
	this.loadCircleHome = function () {
		$location.path("/circle/home");
	}

	/*
	 * loadDeveloperReadings function to load readings for all the corresponding plants 
	 * of logged in developer.
	 */
	function loadCircleReadings() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/readings/circle/'+$scope.userRole.circle
				}
		).then(
				function (response) {
					var status = response.status;
					//checking the status. if status is ok(200) assigning the values 
					//to display on front end.
					if (status === 200) {
						$scope.readingData = response.data;
						$scope.readingData.forEach(function (reading) {
							utilService.calculateReadingData(reading);
						});
						$scope.loading = false;
					}
				},
				function(error){
					console.log("unable to display readings.error code is below");
					console.log(error);
				}
		);
	}

	/*
	 * function validateReading to implement validate action event
	 * this function runs when user clicks validate reading button on page.
	 */
	//To be implemented for circle when required
	/*this.validateReading = function (reading) {
		$http(
				{
					method: 'PUT',
					url: 'backend/readings/validate',
					params: {
						role: $scope.userRole.role,
						readingId: reading.currentMeterReading.id
					}
				}
		).then(
				function (response) {
					var status = response.status;
					//checking status code for successful response from server
					if (status === 200) {
						var found = false;
						var consumptionData = {};
						$scope.readingData.forEach(
								function (item) {
									if (item.meterNo === reading.meterNo) {
										if ($scope.userRole.role === 'developer') {
											
											item.currentMeterReading.developerValidation = 1;
											//creating consumption data to be inserted into backend
											consumptionData.meterNo = item.meterNo;
											consumptionData.activeConsumption = parseFloat(item.activeEnergyConsumption);
											consumptionData.meterReadingId = item.currentMeterReading.id;
											consumptionData.plantId = item.plant.id;
											consumptionData.plantCode = item.plant.code;
											consumptionData.reactiveConsumption = parseFloat(item.quadrantOneConsumption)+parseFloat(item.quadrantTwoConsumption)
											                                      + parseFloat(item.quadrantThreeConsumption) + parseFloat(item.quadrantFourConsumption)
											found = true;
										}
									}
								}
						);
						if(found){
							$http(
									{
										method: 'POST',
										url: 'backend/consumptions',
										data : consumptionData
									}
							).then(
									function (response) {
										var status = response.status;
										if(status === 201){
                                           var insertedConsumption = response.data;
										}
									},
									function(error){
                                        console.log("error inserting consumption. below is error ");
                                        console.log(error);
									}
							);
						}
					}
				},
				function(error){
					console.log("error in validating readings. response is below");
					console.log(error);
				}
		);
	};*/

	/*
	 * viewInvestorData function to navigate to view bifurcated readings page
	 * where circle user can see investor's wise bifurcation page of particular plant.
	 */
	this.viewInvestorsData = function (reading) {
		$location.path("/circle/readings/viewsplited/" + reading.consumption.id);
	};

	/*
	 * variable currentPage to hold value for currentpage
	 * required for pagination
	 */
	$scope.currentPage = 1;
	
	/*
	 * variable pageSize to hold value for currentpage
	 * required for pagination
	 */
	$scope.pageSize = 5;
	
	/*
	 * function pageChangeHandler gets executed when user
	 * changes page from the pagination row
	 */
	$scope.pageChangeHandler = function(num) {
	      
	  };
}]);