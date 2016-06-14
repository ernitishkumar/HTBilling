angular.module("htBillingApp").controller('ViewBillController', ['$http', '$scope', '$location', '$routeParams','$window','authService', function ($http, $scope, $location, $routeParams,$window,authService) {

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};

	/*
	 * var sno a controller level variable to store Serial No.
	 */
	$scope.sno = 0;

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
		}else if(userRole.role === "developer" || userRole.role === "circle" || userRole.role === "admin"){
			$scope.user = user;
			$scope.userRole = userRole;
			loadBill();
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
	 * loadDeveloperHome function to navigate to developer home page
	 */
	this.loadHome = function () {
		if($scope.userRole.role === "developer"){
			this.loadDeveloperHome();
		}else if($scope.userRole.role === "circle"){
			this.loadCircleHome();
		}else if($scope.userRole.role === "admin"){
			this.loadHtHome();
		}else{
			this.logout();
			$location.path("/");
		}
	}

	/*
	 * loadDeveloperHome function to navigate to developer home page
	 */
	this.loadDeveloperHome = function () {
		$location.path("/developer/home");
	}

	/*
	 * loadDeveloperHome function to navigate to developer home page
	 */
	this.loadCircleHome = function () {
		$location.path("/circle/home");
	}

	/*
	 * loadDeveloperHome function to navigate to developer home page
	 */
	this.loadHtHome = function () {
		$location.path("/ht/home");
	}

	/*
	 * back function to go back to the last page.
	 */
	this.back = function () {
		window.history.back();
	};

	/*
	 * function to load bill from backend as per passed billId
	 */
	function loadBill(){

		var billId = $routeParams.billDetailsId;

		$http(
				{
					method: 'GET',
					url: 'backend/bill/'+billId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						var billDetails = response.data;
						if(billDetails.id == billId){
							$scope.billDetails = billDetails;
							console.log("printing bill details");
							console.log($scope.billDetails);
							loadMachines();
						}else{
							alert("Got incorrect bill please check");
						}
					}
				},
				function(error){
					console.log("error while getting bill for id");
					console.log(error);
					alert("Bill doesnot exists.");
					window.history.back();
				}
		);


	}

	/*
	 * function to load Machines for the particular bill
	 * to display particulars and other informations
	 */
	function loadMachines(){
		if($scope.billDetails !== undefined && $scope.billDetails !== null){
			//console.log("plant id is : "+$scope.billDetails.plant.id+" and investor Id is : "+$scope.billDetails.investor.id);
			$http(
					{
						method: 'GET',
						url: 'backend/machines/plant/'+$scope.billDetails.plant.id+'/investor/'+$scope.billDetails.investor.id
					}
			).then(
					function (response) {
						var status = response.status;
						if(status === 200){
							$scope.billDetails.machines = response.data;
						}
					},
					function(error){
						console.log("error while getting machines for bill id");
						console.log(error);
					}
			);
		}
	}
}]);