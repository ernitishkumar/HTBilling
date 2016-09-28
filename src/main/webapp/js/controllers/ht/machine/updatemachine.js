angular.module("htBillingApp").controller('UpdateMachineController', ['$http', '$scope', '$location','$routeParams','$window','authService', function ($http, $scope, $location,$routeParams,$window,authService) {

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
			getMachine();
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
					url: 'backend/plants/developer/'+$scope.machine.developer.id
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
	
	
	function getMachine() {
		var machineId = $routeParams.id;
		$http(
				{
					method: 'GET',
					url: 'backend/machines/'+machineId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.machine = response.data;
						getPlantsByDeveloper();
						getInvestorsByPlant();
						var datestr1 = $scope.machine.commissionedDate;
						var splitDate1 = datestr1.split("-");
						var finalDate1 = splitDate1[2]+'-'+splitDate1[1]+'-'+splitDate1[0];
						var d1 = new Date(finalDate1);
						var month1 = d1.getMonth();
					    var day1 = d1.getDate();
					    var year1 =d1.getFullYear();
					    if (month1 < 10) {
							month1 = "0" + month1;
						}
						var day1 = d1.getDate();
						if (day1 < 10) {
							day1 = "0" + day1;
						}
						$scope.machine.commissionedDate = new Date(year1,month1,day1);
						var datestr2 = $scope.machine.ppaDate;
						var splitDate2 = datestr2.split("-");
						var finalDate2 = splitDate2[2]+'-'+splitDate2[1]+'-'+splitDate2[0];
						var d2 = new Date(finalDate2);
						var month2 = d2.getMonth();
					    var day2 = d2.getDate();
					    var year2 =d2.getFullYear();
					    if (month2 < 10) {
							month2 = "0" + month2;
						}
						var day2 = d2.getDate();
						if (day2 < 10) {
							day2 = "0" + day2;
						}
						$scope.machine.ppaDate = new Date(year2,month2,day2);
					}
				},
				function(error){
					console.log("Error while fetching Machine");
					console.log(error);
				}
		);
	}

	/*
	 * getInvestorsByPlant function to fetch different investors
	 * for a particular plant
	 */
	var getInvestorsByPlant = function () {
		$scope.investors = null;
		$http(
				{
					method: 'GET',
					url: 'backend/investors/plant/'+$scope.machine.plant.id
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
		var d1 = new Date($scope.machine.commissionedDate);
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
		$scope.machine.commissionedDate = commissionedDate;
		var d2 = new Date($scope.machine.ppaDate);
		var y2 = d2.getFullYear();
		var m2 = d2.getMonth() + 1;
		if (m2 < 10) {
			m2 = "0" + m2;
		}
		var day2 = d2.getDate();
		if (day2 < 10) {
			day2 = "0" + day2;
		}
		var ppaDate = day2 + "-" + m2 + "-" + y2;
		$scope.machine.ppaDate = ppaDate;
		$scope.machine.developerId = $scope.machine.developer.id;
		$scope.machine.plantId = $scope.machine.plant.id;
		$scope.machine.investorId = $scope.machine.investor.id;
		$http(
				{
					method: 'PUT',
					url: 'backend/machines/'+$scope.machine.id,
					data : $scope.machine
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						bootbox.alert("Machine Updated successfully.",function(answer){
							$scope.error = null;
							window.history.back();	
						});
					}
				},
				function(error){
					console.log("Unable to Update machine");
					console.log(error);
				}
		);
	};

	/*
	 * clearForm function to clear the form
	 */
	this.back = function () {
		window.history.back();
	};

}]);