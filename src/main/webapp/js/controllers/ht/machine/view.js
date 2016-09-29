angular.module("htBillingApp").controller('ViewMachineDetailsController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			getAllMachines();
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
	 * loadHome function to navigate to the home page
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	
	this.update = function (id) {
		$location.path("/machine/update/"+id);
	};
	
	/*
	 * function getAllMachines to fetch all the machines from backend to display 
	 * on the page.
	 */
	function getAllMachines() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/machines'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.machines = response.data;
						$scope.loading = false;
					}
				},
				function(error){
					console.log("Error while fetching all the machines");
					console.log(error);
				}
		);
	}

	/*
	 * function to delete selected meter
	 */
	this.remove = function(index){
		bootbox.confirm("Are you sure to delete this Machine?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'DELETE',
							url: 'backend/machines/'+$scope.machines[index].id
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								var deletedMachine = response.data;
								if(deletedMachine !== null){
									$scope.machines.splice(index,1);
								}
							}
						},
						function(error){
							console.log("Error while deleting Machine");
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