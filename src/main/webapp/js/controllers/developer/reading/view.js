angular.module("htBillingApp").controller('DeveloperViewMeterReadingsController', ['$http', '$scope', '$location', '$routeParams','authService','utilService', function ($http, $scope, $location, $routeParams,authService,utilService) {

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
			loadDeveloperReadings();
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
	this.loadDeveloperHome = function () {
		$location.path("/developer/home");
	}

	/*
	 * loadDeveloperReadings function to load readings for all the corresponding plants 
	 * of logged in developer.
	 */
	function loadDeveloperReadings() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/readings/developer/'+$scope.user.username
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
	this.validateReading = function (reading,index) {
		$http(
				{
					method: 'PUT',
					url: 'backend/readings/validate',
					params: {
						role: $scope.userRole.role,
						currentReadingId: reading.currentMeterReading.id,
						previousReadingId: reading.previousMeterReading.id
					}
				}
		).then(
				function (response) {
					var status = response.status;
					//checking status code for successful response from server
					if (status === 200) {
						var found = false;
						var consumptionData = {};
						/*$scope.readingData.forEach(
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
						);*/
						//Implementing the above logic with index passed from the page, No need to loop over complete collection for
						//every validation.
						var item = $scope.readingData[index];
						console.log("reading data.....  "+item);
						if(item.meterNo === reading.meterNo){
							console.log(item);
							if ($scope.userRole.role === 'developer') {
								item.currentMeterReading.developerValidation = 1;
								//creating consumption data to be inserted into backend
								consumptionData.meterNo = item.meterNo;
								consumptionData.activeConsumption = item.activeEnergyConsumption;
								consumptionData.meterReadingId = item.currentMeterReading.id;
								consumptionData.plantId = item.plant.id;
								consumptionData.plantCode = item.plant.code;
								/*consumptionData.reactiveConsumption = parseFloat(item.quadrantOneConsumption)+parseFloat(item.quadrantTwoConsumption)
								+ parseFloat(item.quadrantThreeConsumption) + parseFloat(item.quadrantFourConsumption);*/
								
								//As per new guidelines adding only Q2 and Q4 for bill generation. Will change after new orders
								consumptionData.reactiveConsumption = parseFloat(item.quadrantTwoConsumption) + parseFloat(item.quadrantFourConsumption);
								consumptionData.adjustment = item.currentMeterReading.adjustment;

								//making http request to save the consumption data at the backend
								$http(
										{
											method: 'POST',
											url: 'backend/consumptions',
											data : consumptionData
										}
								).then(
										function (response) {
											var status = response.status;
											//console.log(response);
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
					}
				},
				function(error){
					console.log("error in validating readings. response is below");
					console.log(error);
				}
		);
	};

	/*
	 * function discardReading to implement discard action event
	 * this function runs when user clicks Discard reading button on page.
	 */
	this.discardReading = function (index) {
		bootbox.confirm("Are you sure to Discard reading ?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'PUT',
							url: 'backend/readings/discard',
							params: {
								readingId: $scope.readingData[index].currentMeterReading.id
							},
							data: $scope.userRole
						}
				).then(
						function (response) {
							var status = response.status;
							//checking status code for successful response from server
							if (status === 200) {
								if ($scope.userRole.role === 'developer') {
									$scope.readingData.splice(index,1);
								}
							}
						},
						function(error){
							bootbox.prompt("Unable to discard.Please try after some time!");
							console.log("error in discarding readings. response is below");
							console.log(error);
						}
				);
			}
		});
	};

	/*
	 * getInvestorData function to navigate to bifurcation page,
	 * where investor wise bifurcation of readings is done.
	 */
	this.getInvestorsData = function (reading) {
		$location.path("/developer/readings/split/" + reading.plant.id + "/" + reading.consumption.id);
	};

	/*
	 * viewInvestorData function to navigate to view bifurcated readings page
	 * where developer can see investor's wise bifurcation page of particular plant.
	 */
	this.viewInvestorsData = function (reading) {
		$location.path("/developer/readings/viewsplited/" + reading.consumption.id);
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
		console.log('page changed to ' + num);
	};
}]);