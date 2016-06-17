angular.module("htBillingApp").controller('UpdateMeterReadingController', ['$http', '$scope', '$location', '$routeParams','$window', 'authService',function ($http, $scope, $location, $routeParams,$window, authService) {

	/*
	 * var meternotvalid a controller level variable to store boolean value.
	 */
	$scope.meternotvalid = false;

	/*
	 * var metervalid a controller level variable to store boolean value.
	 */
	$scope.metervalid = false;

	/*
	 * var plainmeter a controller level variable to store booelan value.
	 */
	$scope.plainmeter = false;

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};

	/*
	 * var formData a controller level variable to store meter form data.
	 */
	$scope.formData = {};

	/*
	 * var meterData a controller level variable to store meter data.
	 */
	$scope.meterdata = {};

	/*
	 * var showdetails a controller level object variable
	 */
	$scope.showdetails = {
			show: false
	};

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
	 * loadHome() function to load home page as per logged in user's role property.
	 */
	this.loadHome = function () {
		var userRole = $scope.userRole;
		if (userRole.role === 'admin' || userRole.role === 'ht') {
			$location.path("/ht/home");
		} else if (userRole.role === 'operator') {
			$location.path("/operator/home");
		} else {
			$location.path("/");
		}

	};

	/*
	 * loadOperatorHome() function to load operator home page.
	 */
	this.loadOperatorHome = function () {
		$location.path("/operator/home");
	};
	
	/*
     * loadSRFRReadingForm() function to route to enter srfrreading page
     */
    this.loadSRFRReadingForm = function () {
        $location.path("/srfrenterreading");
    };
    
    /*
	 * function getReadings() to fetch reading from reading id
	 */
	function getReadings() {
		var readingId = $routeParams.readingId;
		//console.log("id is: "+plantId);
		$http(
				{
					method: 'GET',
					url: 'backend/readings/'+readingId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.reading = response.data;
						var datestr = $scope.reading.readingDate;
						var splitDate = datestr.split("-");
						var finalDate = splitDate[2]+'-'+splitDate[1]+'-'+splitDate[0];
						var d = new Date(finalDate);
						console.log(finalDate);
						console.log(d);
						var month = d.getMonth();
					    var day = d.getDate();
					    var year =d.getFullYear();
					    if (month < 10) {
							month = "0" + month;
						}
						var day = d.getDate();
						if (day < 10) {
							day = "0" + day;
						}
					
						$scope.reading.readingDate = new Date(year,month,day);
						console.log($scope.reading);
					}
				},
				function(error){
					console.log("Error while fetching Readings");
					console.log(error);
				}
		);
	}
	getReadings();
	
	/*
	 * function to route to view reading page
	 */
	this.back = function(){
		window.history.back();
	};
	
	
	/*
	 * isValidMeterno function to check validity of meterno provided by the user
	 
	this.isValidMeterno = function () {

		if ($scope.meternoForm.meterno.$dirty && $scope.meternoForm.meterno.$valid) {
			$scope.plainmeter = true;
			$scope.metervalid = false;
			$scope.meternotvalid = false;
			$http({
				method: 'GET',
				url: 'backend/plants/meterno/'+this.formData.meterno
			}).then(
					function(response) {
						var status = response.status;
						if (status === 200) {
							var meterData = response.data;
							$scope.formData.mf = meterData.meter.mf;
							$scope.formData.location = meterData.plant.name;
							$scope.plainmeter = false;
							$scope.metervalid = true;
							$scope.meternotvalid = false;
							$scope.lastReading = meterData.currentReading;
						} else {
							$scope.plainmeter = false;
							$scope.metervalid = false;
							$scope.meternotvalid = true;
							$scope.error="Given Meter is not installed on any plant. Provide a different number";
						}
					},
					function(error){
						$scope.plainmeter = false;
						$scope.metervalid = false;
						$scope.meternotvalid = true;
						$scope.error="Given Meter is not installed on any plant. Provide a different number";
					}
			);
		} else {
			$scope.plainmeter = false;
			$scope.metervalid = false;
			$scope.meternotvalid = true;
		}

	};*/

	this.processForm = function () {
		console.log($scope.reading.readingDate);
			var res = $scope.reading.activeTodOne + $scope.reading.activeTodTwo + $scope.reading.activeTodThree;
			if($scope.reading.activeEnergy.toFixed(2) !== res.toFixed(2)){
				$scope.error = "Active Energy should be equal to sum of Tod 1, Tod 2, Tod 3";
				return;
			}
			var d = new Date($scope.reading.readingDate);
			var year = d.getFullYear();
			var month = d.getMonth() + 1;

			if (month < 10) {
				month = "0" + month;
			}
			var day = d.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			var readingDate = day + "-" + month + "-" + year;
			console.log(readingDate);
			$scope.reading.readingDate = readingDate;
			$http({
				method: 'PUT',
				url: 'backend/readings/'+$scope.reading.id,
				headers:{
					contentType:"application/json"
				},
				data: $scope.reading
			}).then(
					function (response) {
						var status = response.status;
						//checking the status of the response with created status code(201)
						if(status === 200){
							var insertedReading = response.data;
							$location.path("/saved/"+"Reading updated successfully!");
						}else{
							$scope.error = "Unable to update readings.Please try Again!";
						}
						$scope.formData = {};
					},
					function (error){
						var status = error.status;
						
						//checking the status with expectation failed status code(417)
						// if status code is 417 assigning the provided error message from backend
						// to display on the page. Else assigning the generic error Message.
						if(status === 417){
							var errorMessage = error.data.errorMessage;
							$scope.error = errorMessage;
						}else{
							$scope.error = "Unable to update readings.Please try Again!";
						}
					}
			);

	};
	
	this.isReadingValid = function(input1,input2){
		if(input1 === null || input1 === undefined){
			return false;
		}else if(input2 === null || input2 === undefined){
			return true;
		}else if(input1 >= input2){
			return true;
		}else{
			return false;
		}
	};

	this.clearForm = function () {
		$scope.plainmeter = false;
		$scope.metervalid = false;
		$scope.meternotvalid = false;
		$scope.formData = {};
		$scope.error = null;
	};

}]);