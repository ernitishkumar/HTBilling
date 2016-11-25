angular.module("htBillingApp").controller('ViewInvestorDetailsController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			getAllInvestors();
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
		$location.path("/investor/update/"+id);
	};
	
	/*
	 * getAllInvestors function to fetch all the investors
	 * from backend to display on the page.
	 */
	function getAllInvestors() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/investors'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.investors = response.data;
						$scope.loading = false;
					}
				},
				function(error){
					console.log("Unable to fetch all the investors.");
					console.log(error);
				}
		);
	}
	
	/*
	 * function to delete selected meter
	 */
	this.remove = function(investor){
		bootbox.confirm("Are you sure to delete this Investor?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'DELETE',
							url: 'backend/investors/'+investor.id
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								var deletedInvestor = response.data;
								if(deletedInvestor !== null){
									$scope.investors.splice($scope.investors.indexOf(investor),1);
								}
							}
						},
						function(error){
							console.log("Error while deleting Investor");
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