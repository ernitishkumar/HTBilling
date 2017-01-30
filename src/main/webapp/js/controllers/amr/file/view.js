angular.module("htBillingApp").controller('ViewAMRReadingsController', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
		}else if(userRole.role === "amr" || userRole.role === 'admin'){
			$scope.user = user;
			$scope.userRole = userRole;
			loadAMRReadings();
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
	 * function to navigate to the home page
	 */
	this.loadAMRHome = function () {
		$location.path("/amr/home");
	};
	
	this.loadHTHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function to load all AMR readings from backend and display them.
	 */
	function loadAMRReadings() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/amr/readings'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.readings = response.data;
						console.log($scope.readings);
						$scope.loading = false;
					}
				},
				function(error){
					console.log("Error while fetching AMR Readings");
					console.log(error);
				}
		);
	}
	
	this.remove = function(reading){
		bootbox.confirm("Are you sure to delete this Reading ?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'DELETE',
							url: 'backend/amr/reading/'+reading.id
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								var deletedReading = response.data;
								if(deletedReading !== null){
									$scope.readings.splice($scope.readings.indexOf(reading),1);
								}
							}
						},
						function(error){
							console.log("Error while deleting Reading");
							console.log(error);
						}
				);
			}
		});
	};
	
	this.approve = function(reading){
		bootbox.confirm("Are you sure want to Approve this Reading ?",function(answer){
			reading.status=1;
			if(answer === true){
				$http(
						{
							method: 'PUT',
							url: 'backend/amr/reading',
							data: reading
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								bootbox.dialog({
									  title: "Approve Status",
									  message: "Reading Approved Successfully !",
									  buttons: {
										    danger: {
										      label: "Close",
										      className: "btn-default",
										      callback: function() {
										        
										      }
										    },
										    main: {
										      label: "Ok",
										      className: "btn-primary",
										      callback: function() {
										        
										      }
										    }
									  }
									});
								$location.path("/amr/file/view");
							}
						},
						function(error){
							reading.status=0;
							console.log(error);
							bootbox.dialog({
								  title: "Approval Failed !!!",
								  message: error.data.errorMessage,
								  buttons: {
									    danger: {
									      label: "Close",
									      className: "btn-default",
									      callback: function() {
									        
									      }
									    },
									    main: {
									      label: "Ok",
									      className: "btn-primary",
									      callback: function() {
									        
									      }
									    }
								  }
								});
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