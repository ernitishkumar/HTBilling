angular.module("htBillingApp").controller('PlantInvestorMappingController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			//fetching all Plants
			getAllPlants();
			//fetching all Unused Meters
			getMetersNotInUse();
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
	 * formData controller to hold form data from the add plant page.
	 */
	$scope.formData = {};

	/*
	 * loadHome function to navigate to the home page
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function getAllPlants() to fetch all the plants from backend
	 */
	function getAllPlants() {

		$http(
				{
					method: 'GET',
					url: 'backend/plants'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.plants = response.data;
					}
				},
				function(error){
					console.log("Error while fetching all the plants");
					console.log(error);
				}
		);
	}
	
	/*
	 * getInvestorsByPlant function to fetch different investors
	 * for a particular plant
	 */
	function getMetersNotInUse() {
		$http(
				{
					method: 'GET',
					url: 'backend/meter/unmapped'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.meters = response.data;
					}
				},
				function(error){
					console.log("Unable to fetch Meters.");
					console.log(error);
				}
		);
	}
	
	/*
	 * processForm function to submit the formdata to the backend.
	 */
	this.processForm = function () {
		var formData = $scope.formData;
		$http(
				{
					method: 'POST',
					url: 'backend/plant-meter-mapping',
					data: formData
				}
		).then(
				function (response) {
					//$location.path("/saved/Plant Saved Successfully!");
					var status = response.status;
					if(status === 201){
						bootbox.alert("Mapping saved successfully!");
						$scope.formData = {};	
						$scope.error = null;
					}
				},
				function(error){
					console.log("Error while mapping investor with plant.");
					console.log(error);
					$scope.error = error.data.errorMessage;
				}
		);
	};

	/*
	 * clearForm function to clear the form
	 */
	this.clearForm = function () {
		$scope.formData = {};
		$scope.error = null;
	};

}]);