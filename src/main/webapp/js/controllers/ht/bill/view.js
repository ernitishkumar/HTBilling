angular.module("htBillingApp").controller('ListBillDetailsCtrl', ['$http', '$scope', '$location','authService', function ($http, $scope, $location,authService) {

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
			loadAllBillDetails();
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
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function to load all the meters from the backend and display them.
	 */
	function loadAllBillDetails() {

		$http(
				{
					method: 'GET',
					url: 'backend/bill'
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.billDetails = response.data;
						console.log($scope.billDetails);
					}
				},
				function(error){
					console.log("Error while fetching Bill Details");
					console.log(error);
				}
		);
	}

	this.viewBill = function (billDetailsId) {
		$location.path("/ht/bill/viewBill/"+billDetailsId);
	}
	
	
	/*this.getByDate = function () {
		console.log($scope.formData);
		var date= $scope.formData.month+"-"+$scope.formData.year;
		console.log(date);
		$http(
				{
					method: 'GET',
					url: 'backend/bill/byDate/'+date
				}
		).then(
				function (response) {
					var status = response.status;
					if(status === 200){
						$scope.billDetails = response.data;
						console.log($scope.billDetails);
					}
				},
				function(error){
					console.log("Error while fetching Bill Details");
					console.log(error);
				}
		);
	}*/
	
	
	
	this.applyFilter = function () {
		console.log($scope.formData);
			if($scope.formData.meterNo != null){
			$http(
					{
						method: 'GET',
						url: 'backend/bill/meterNo/'+$scope.formData.meterNo
					}
			).then(
					function (response) {
						var status = response.status;
						if(status === 200){
							$scope.billDetails = response.data;
							console.log($scope.billDetails);
						}
					},
					function(error){
						console.log("Error while fetching Bill Details");
						console.log(error);
					}
			);
		}
			else if($scope.formData.billNo != null){
				$http(
						{
							method: 'GET',
							url: 'backend/bill/billNo/'+$scope.formData.billNo
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								$scope.billDetails = response.data;
								console.log($scope.billDetails);
							}
						},
						function(error){
							console.log("Error while fetching Bill Details");
							console.log(error);
						}
				);
			}
			else if($scope.formData.invoiceNo != null){
					$http(
							{
								method: 'GET',
								url: 'backend/bill/invoiceNo/'+$scope.formData.invoiceNo
							}
					).then(
							function (response) {
								var status = response.status;
								if(status === 200){
									$scope.billDetails = response.data;
									console.log($scope.billDetails);
								}
							},
							function(error){
								console.log("Error while fetching Bill Details");
								console.log(error);
							}
					);
				}
			else if($scope.formData.month != null && $scope.formData.year !=null){
				var date= $scope.formData.month+"-"+$scope.formData.year;
				console.log(date);
				$http(
						{
							method: 'GET',
							url: 'backend/bill/byDate/'+date
						}
				).then(
						function (response) {
							var status = response.status;
							if(status === 200){
								$scope.billDetails = response.data;
								console.log($scope.billDetails);
							}
						},
						function(error){
							console.log("Error while fetching Bill Details");
							console.log(error);
						}
				);
			}
			else{
				$scope.error="Please provide atleast one input";
			}
	}
	
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