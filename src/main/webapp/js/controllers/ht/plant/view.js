angular.module("htBillingApp").controller('ViewPlantDetailsController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {
	
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
			
			//fetching all the plants
			getPlants();
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
	function getPlants() {

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
	 * function to delete selected meter
	 */
	this.remove = function(index){
		bootbox.confirm("Are you sure to delete plant?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'DELETE',
							url: 'backend/plants/'+$scope.plants[index].id
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								var deletedPlant = response.data;
								if(deletedPlant !== null){
									$scope.plants.splice(index,1);
								}
							}
						},
						function(error){
							console.log("Error while fetching all meters");
							console.log(error);
						}
				);
			}
		});
	};
	
	/*
	 * function to route to update plant page
	 */
	this.updatePage = function(i){
		var plantId = $scope.plants[i].id;
		console.log(plantId);
		$location.path("/plant/updateplant/"+plantId);
	};
	
	
}]);