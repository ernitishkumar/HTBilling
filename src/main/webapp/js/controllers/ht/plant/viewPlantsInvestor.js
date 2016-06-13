angular.module("htBillingApp").controller('ViewInvestorMappingController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			getAllMappings();
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
	function getAllMappings() {

		$http(
				{
					method: 'GET',
					url: 'backend/investor-plant-mapping'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.mappings = response.data;
					}
				},
				function(error){
					console.log("Error while fetching all the mappings");
					console.log(error);
				}
		);
	}

	this.remove = function(index){
		bootbox.confirm("Are you sure to delete Mapping?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'DELETE',
							url: 'backend/investor-plant-mapping/'+$scope.mappings[index].id
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								var deletedMapping = response.data;
								if(deletedMapping !== null){
									$scope.mappings.splice(index,1);
								}
							}
						},
						function(error){
							console.log("Error while deleting mappings");
							console.log(error);
						}
				);
			}
		});
	};
	
	/*
	 * variable currentPage to hold value for currentpage
	 * required for pagination
	 */
	$scope.currentPage = 1;
	
	/*
	 * variable pageSize to hold value for currentpage
	 * required for pagination
	 */
	$scope.pageSize = 10;
	
	/*
	 * function pageChangeHandler gets executed when user
	 * changes page from the pagination row
	 */
	$scope.pageChangeHandler = function(num) {
	      
	  };
}]);