angular.module("htBillingApp").controller('UpdateMeterController', ['$http', '$scope', '$location','$routeParams','$window','authService', function ($http, $scope, $location,$routeParams,$window,authService) {

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
			getMeter();
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
	 * controller level variable formData to hold login form data
	 */
	$scope.formData = {};

	/*
	 * controller level variable showDetails
	 */
	$scope.showdetails = {
			show: false
	};

	/*
	 * function to route to homepage
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function processForm which gets executed when user clicks add meter on the page.
	 * It passes the add meter form data to backend servers for inserting in databse;
	 */
	this.processForm = function () {
		$http(
				{
					method: 'PUT',
					url: 'backend/meter/'+$scope.meter.meterNo,
					data: $scope.meter
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						var updatedMeter = response.data;
						bootbox.alert("Meter Updated Successfully!",function(answer){
							//navigating back to view page
							window.history.back();	
						});
					}
				},
				function(error){
					console.log("Error while updating meter details.");
					console.log(error);
				}
		);
	};
	
	
	/*
	 * function getMeter() to fetch all the plants from backend
	 */
	function getMeter() {
		var meterNo = $routeParams.meterNo;
		$http(
				{
					method: 'GET',
					url: 'backend/meter/'+meterNo
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.meter = response.data;
					}
				},
				function(error){
					console.log("Error while fetching Meter");
					console.log(error);
				}
		);
	}
	

	/*
	 * function back to Meter View Page
	 */
	$scope.navigateToView = function () {
		window.history.back();
	};

}]);