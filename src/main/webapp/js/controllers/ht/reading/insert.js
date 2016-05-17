angular.module("htBillingApp").controller('MeterReadingController', ['$http', '$scope', '$location', 'authService',function ($http, $scope, $location, authService) {

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
			$location.path("/home");
		} else if (userRole.role === 'operator') {
			$location.path("/operatorhome");
		} else {
			$location.path("/");
		}

	};

	/*
	 * loadOperatorHome() function to load operator home page.
	 */
	this.loadOperatorHome = function () {
		$location.path("/operatorhome");
	};

	/*
	 * isValidMeterno function to check validity of meterno provided by the user
	 */
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

	};

	this.processForm = function (ae,td1,td2,td3,q1,q2,q3,q4) {

		if(ae && td1 && td2 && td3 && q1 && q2 && q3 && q4){
			var d = new Date(this.formData.date);
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
			this.formData.readingDate = readingDate;
			this.formData.mf = $scope.formData.mf;
			$http({
				method: 'POST',
				url: 'backend/readings',
				headers:{
					contentType:"application/json"
				},
				data: this.formData
			}).then(
					function (response) {
						$scope.plainmeter = false;
						$scope.metervalid = false;
						$scope.meternotvalid = false;
						var status = response.status;
						//checking the status of the response with created status code(201)
						if(status === 201){
							var insertedReading = response.data;
							$location.path("/saved/"+"Reading saved successfully!");
						}else{
							$scope.error = "Unable to save readings.Please try Again!";
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
							$scope.error = "Unable to save readings.Please try Again!";
						}
					}
			);
		}else{
			$scope.error = "Current month's readings should be greater than previous month's readings!"
		}

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