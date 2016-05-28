angular.module("htBillingApp").controller('AddPlantController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			//populating all the developers
			getDevelopers();
			//populating all the available meters
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
	 * getDevelopers function to fetch all the developers from the backend
	 * to show in drop down
	 */
	function getDevelopers(){

		$http(
				{
					method: 'GET',
					url: 'backend/developers'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.developers = response.data;	
					}
				},
				function(error){
					console.log("Error while getting all developers");
					console.log(error);
				}
		);
	}

	/*
	 * getMetersNotInUse fucntion to fetch all the available meters 
	 * to be installed on a plant
	 */
	function getMetersNotInUse(){

		$http(
				{
					method: 'GET',
					url: 'backend/meter/unused'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.metersNotInUse = response.data;
					}
				},
				function(error){
					console.log("unable to load all unused meters");
					console.log(error);
				}
		);
	}

	/*
	 * loadHome function to navigate to the home page
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * processForm function to submit the formdata to the backend.
	 */
	this.processForm = function () {
		var d = new Date($scope.formData.commissionedDate);
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
		var formData = $scope.formData;
		formData.commissionedDate = readingDate;
		console.log("Adding plant as");
		console.log(formData);
		$http(
				{
					method: 'POST',
					url: 'backend/plants',
					data : formData
				}
		).then(
				function (response) {
					//$location.path("/saved/Plant Saved Successfully!");
					var status = response.status;
					if(status === 201){
						bootbox.alert("Plant saved successfully.")
						$scope.formData = {};	
					}
				},
				function(error){
					console.log("Error while creating plant.");
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