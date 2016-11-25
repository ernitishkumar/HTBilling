angular.module("htBillingApp").controller('ViewMeterReadingsController', ['$http', '$scope', '$location', '$routeParams','authService','utilService', function ($http, $scope, $location, $routeParams, authService,utilService) {

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
		}else if(userRole.role === "admin" || userRole.role === "operator"){
			$scope.user = user;
			$scope.userRole = userRole;
			loadReadings();
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
	 * function to navigate to home page
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function to navigate to operator home page
	 */
	this.loadOperatorHome = function () {
		$location.path("/operator/home");
	};
	
	/*
	 * function to navigate to operator home page
	 */
	this.updateReading = function(readingId){
		$location.path("/ht/updatereading/"+readingId);
	};
	/*
	 * function to load all the readings for all the meters from backend
	 * and assign it to the view to display on front end
	 */
	function loadReadings() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/readings'
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
		console.log(reading.currentMeterReading.id);
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
						//Implementing the above logic with index passed from the page, No need to loop over complete collection for
						//every validation.
						//var item = $scope.readingData[index];
						//if(item.meterNo === reading.meterNo){
							if ($scope.userRole.role === 'admin' || $scope.userRole.role === 'htcell') {
								$scope.readingData[$scope.readingData.indexOf(reading)].currentMeterReading.htCellValidation = 1;
							}
						//}
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
	this.discardReading = function (reading) {
		bootbox.confirm("Are you sure to Discard reading ?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'PUT',
							url: 'backend/readings/discard',
							params: {
								readingId: reading.currentMeterReading.id
							},
							data: $scope.userRole
						}
				).then(
						function (response) {
							var status = response.status;
							//checking status code for successful response from server
							if (status === 200) {
								if ($scope.userRole.role === 'admin' || $scope.userRole.role === 'htcell') {
									$scope.readingData.splice($scope.readingData.indexOf(reading),1);
								}
							}
						},
						function(error){
							console.log("error in discarding readings. response is below");
							console.log(error);
						}
				);
			}
		});
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