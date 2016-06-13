angular.module("htBillingApp").controller('UpdateInvestorController', ['$http', '$scope', '$location','$routeParams','$window','authService', function ($http, $scope, $location,$routeParams,$window,authService) {

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
			getInvestor();
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
	 * processForm function to submit the formdata to the backend.
	 */
	this.processForm = function () {
		$http(
				{
					method: 'PUT',
					url: 'backend/investors/'+$scope.investor.id,
					data: $scope.investor
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						bootbox.alert("Investor updated successfully.",function(answer){
							window.history.back();
						});
					}
				},
				function(error){
					console.log("Error while Updating investor");
					console.log(error);
					$scope.error = error.data.errorMessage;
				}
		);
	};
	
	
	
	function getInvestor() {
		var investorId = $routeParams.id;
		$http(
				{
					method: 'GET',
					url: 'backend/investors/'+investorId
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.investor = response.data;
					}
				},
				function(error){
					console.log("Error while fetching Investor");
					console.log(error);
				}
		);
	}

	/*
	 * function to go back to view page
	 */
	this.back = function () {
		window.history.back();
	};

}]);