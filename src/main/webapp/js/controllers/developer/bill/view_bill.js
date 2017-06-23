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
							makeBillMonth();
							/*console.log("printing bill details");
							console.log($scope.billDetails);*/
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
	
	function makeBillMonth(){
		console.log("inside make bill Month");
		var date = $scope.billDetails.readingDate;
		var dateArray = date.split("-");
		var month;
		var year = dateArray[2];
		switch(dateArray[1]) {
		    case "01":
		        month = "JAN";
		        break;
		    case "02":
		        month = "FEB";
		        break;
		    case "03":
		        month = "MAR";
		        break;
		    case "04":
		        month = "APR";
		        break;
		    case "05":
		        month = "MAY";
		        break;
		    case "06":
		        month = "JUN";
		        break;
		    case "07":
		        month = "JUL";
		        break;
		    case "08":
		        month = "AUG";
		        break;
		    case "09":
		        month = "SEP";
		        break;
		    case "10":
		        month = "OCT";
		        break;
		    case "11":
		        month = "NOV";
		        break;
		    case "12":
		        month = "DEC";
		        break;
		    default:
		    	month = null;
		    
		}
		if(month != null && year != null){
	    	console.log("inside if of bill Month");
	    	$scope.billDetails.billMonth = month+"-"+year;
	    }
	}

}]);