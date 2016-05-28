angular.module("htBillingApp").controller('AddMachineController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			//fetching all the developers for creating dropdown
			getAllDevelopers();
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

	function getAllDevelopers(){
		$http(
				{
					method: 'GET',
					url: 'backend/developers'
				}
		).then(
				function(response) {
					var status = response.status;
					if(status === 200){
						$scope.developers = response.data;
					}
				},
				function(error){
					console.log("Unable to fetch all developers");
					console.log(error);
				}
		);
	}

	/*
	 * getPlantsByDeveloper function to fetch different plants
	 * for given developer
	 */
	var getPlantsByDeveloper = function() {
		$scope.investors = null;
		$http(
				{
					method: 'GET',
					url: 'backend/plants/developer/'+$scope.formData.developerId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.plants = response.data;
					}
				},
				function(error){
					console.log("Unable to fetch Plants");
					console.log(error);
				}
		);
	};

	//Assigning the function to the controller variable
	this.getPlantsByDeveloper = getPlantsByDeveloper;

	/*
	 * getInvestorsByPlant function to fetch different investors
	 * for a particular plant
	 */
	var getInvestorsByPlant = function () {
		$scope.investors = null;
		$http(
				{
					method: 'GET',
					url: 'backend/investors/plant/'+$scope.formData.plantId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.investors = response.data;
					}
				},
				function(error){
					console.log("Unable to fetch investors.");
					console.log(error);
				}
		);
	};

	//assigning the object's variable
	this.getInvestorsByPlant = getInvestorsByPlant;

	/*
	 * processForm function to submit the formdata to the backend.
	 */
	this.processForm = function () {
		var d1 = new Date($scope.formData.commissionedDate);
		var y1 = d1.getFullYear();
		var m1 = d1.getMonth() + 1;
		if (m1 < 10) {
			m1 = "0" + m1;
		}
		var day1 = d1.getDate();
		if (day1 < 10) {
			day1 = "0" + day1;
		}
		var commissionedDate = day1 + "-" + m1 + "-" + y1;
		console.log(commissionedDate);
		$scope.formData.commissionedDate = commissionedDate;
		var d2 = new Date($scope.formData.ppaDate);
		var y2 = d1.getFullYear();
		var m2 = d1.getMonth() + 1;
		if (m2 < 10) {
			m2 = "0" + m2;
		}
		var day2 = d2.getDate();
		if (day2 < 10) {
			day2 = "0" + day2;
		}
		var ppaDate = day2 + "-" + m2 + "-" + y2;
		console.log(ppaDate);
		$scope.formData.ppaDate = ppaDate;

		$http(
				{
					method: 'POST',
					url: 'backend/machines',
					data : $scope.formData
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 201){
						bootbox.alert("Machine saved successfully.");
						$scope.formData = {};
						$scope.error = null;
					}
				},
				function(error){
					console.log("Unable to add machine");
					console.log(error);
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