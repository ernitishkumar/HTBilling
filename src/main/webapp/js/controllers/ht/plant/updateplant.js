angular.module("htBillingApp").controller('UpdatePlantController', ['$http', '$scope', '$location','$routeParams','$window','authService', function ($http, $scope, $location,$routeParams,$window,authService) {
	
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
			//fetching the plant by Id
			getPlant();
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
	 * function getPlants() to fetch all the plants from backend
	 */
	function getPlant() {
		var plantId = $routeParams.plantId;
		//console.log("id is: "+plantId);
		$http(
				{
					method: 'GET',
					url: 'backend/plants/plantId/'+plantId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.plant = response.data;
						var datestr = $scope.plant.commissionedDate;
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
					
						$scope.plant.commissionedDate = new Date(year,month,day);
					}
				},
				function(error){
					console.log("Error while fetching plant");
					console.log(error);
				}
		);
	}

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
	 * function to route to view plant page
	 */
	this.back = function(){
		window.history.back();
	};
	
	
	
	
	this.processForm = function () {
		var d = new Date($scope.plant.commissionedDate);
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
		var formData = $scope.plant;
		formData.commissionedDate = readingDate;
		formData.developerId = formData.developer.id;
		$http(
				{
					method: 'PUT',
					url: 'backend/plants/'+formData.id,
					data : formData
				}
		).then(
				function (response) {
					//$location.path("/saved/Plant Saved Successfully!");
					var status = response.status;
					if(status === 200){
						bootbox.alert("Plant updated successfully.",function(answer){
							//navigating back to view page
							window.history.back();
						});
					}
				},
				function(error){
					console.log("Error while updating the plant.");
					console.log(error);
					$scope.error = error.data.errorMessage;
				}
		);
	};

	
}]);