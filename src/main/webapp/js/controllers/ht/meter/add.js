angular.module("htBillingApp").controller('AddMeterController', ['$http', '$scope','$location','authService', function ($http, $scope, $location,authService) {

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
	 * controller level variable to hold the status of currently adding meter
	 */
	$scope.meterAlreadyAdded = false;

	/*
	 * function meterExists to check whether meter being added exists in
	 * the database or not.
	 */
	this.meterExists = function(){
		var documentResult = document.getElementById("meterno");
		var wrappedDocument = angular.element(documentResult);
		$http(
				{
					method:'GET',
					url:'backend/meter/'+$scope.formData.meterNo
				}		
		).then(
				function(response){
					var status = response.status;
					if(status === 200){
						var meter = response.data;
						if(meter.meterNo === $scope.formData.meterNo){
							$scope.meterAlreadyAdded = true;
							$scope.error = "Meter already added.Please provide a different meter no.";
							wrappedDocument.removeClass("validmeter");
							wrappedDocument.addClass("invalidMeter");
						}
					}
				},
				function(error){
					$scope.meterAlreadyAdded = false;
					if($scope.formData.meterNo !== undefined){
						wrappedDocument.removeClass("invalidMeter");
						wrappedDocument.addClass("validmeter");	
					}
				}
		);
	};

	/*
	 * function processForm which gets executed when user clicks add meter on the page.
	 * It passes the add meter form data to backend servers for inserting in databse;
	 */
	this.processForm = function () {
		if(!$scope.meterAlreadyAdded){
			$scope.error = null;
			$http(
					{
						method: 'POST',
						url: 'backend/meter',
						data: $scope.formData
					}
			).then(
					function (response) {
						var status = response.status;
						if(status === 201){
							var insertedMeter = response.data;
							bootbox.alert("Meter Saved Successfully!");
							$scope.clearForm();
						}
					},
					function(error){
						$scope.error = error.data.errorMessage;
						//console.log("Error while inserting meter details.");
						//console.log(error);
					}
			);	
		}
	};

	/*
	 * function clearForm
	 */
	$scope.clearForm = function () {
		$scope.error = null;
		$scope.formData = {};
		$scope.meterAlreadyAdded = false;
	};

}]);