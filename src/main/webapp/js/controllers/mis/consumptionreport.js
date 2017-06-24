angular.module("htBillingApp").controller('ConsumptionReportCtrl', ['$http', '$scope', '$location','authService','localStorageService', function ($http, $scope, $location,authService,localStorageService) {

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};
	
	$scope.formData = {};
	
	$scope.encodedCredentials;

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
			$scope.encodedCredentials = localStorageService.get('credentials');
			$scope.userRole = userRole;
			makeCurrentBillMonth();
			fetchCurrentYear();
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
		$scope.loading = true;
		console.log("inside billDetails");
		$http(
				{
					method: 'GET',
					url: 'backend/bill/view/'+$scope.formData.billMonth
				}
		).then(
				function (response) {
					//console.log(response);
					var status = response.status;
					if(status === 200){
						$scope.billDetails = response.data;
						//console.log($scope.billDetails);
					}
					$scope.loading = false;
				},
				function(error){
					console.log("Error while fetching Bill Details");
					console.log(error);
				}
		);
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
	$scope.pageSize = 20;
	
	/*
	 * function pageChangeHandler gets executed when user
	 * changes page from the pagination row
	 */
	$scope.pageChangeHandler = function(num) {
	      
	};
	  
	  $scope.billMonthYearChanged = function(){
			if($scope.formData.year !== null && $scope.formData.year !== undefined && $scope.formData.month !== null && $scope.formData.month !== undefined){
				$scope.formData.billMonth = $scope.formData.month+"-"+$scope.formData.year;
				if($scope.userRole.role === 'admin'){
					loadAllBillDetails();
				}
	
			}
	  };

	$scope.months = [
	                 'JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'
	                 ];
		
	function fetchCurrentYear(){
		var date = new Date();
		//var year = date.getFullYear()-1;
		var year = 2016;
		$scope.years = [];
		while(year <= 2026){
			$scope.years.push(year++);
		}
	}
		
		function makeCurrentBillMonth(){
			var date = new Date();
			var dd = date.getDate();
			var mm = date.getMonth() + 1;
			var yyyy = date.getFullYear();
			var currDate = dd+"-"+mm+"-"+yyyy;
			console.log("Current Date is  "+currDate);
			var prevMonth='';
			switch (mm) {
			case 1:
				var prevMonth = 'JAN';
				break;
			case 2:
				prevMonth = 'FEB';
				break;
			case 3:
				prevMonth = 'MAR';
				break;
			case 4:
				prevMonth = 'APR';
				break;
			case 5:
				prevMonth = 'MAY';
				break;
			case 6:
				prevMonth = 'JUN';
				break;
			case 7:
				prevMonth = 'JUL';
				break;
			case 8:
				prevMonth = 'AUG';
				break;
			case 9:
				prevMonth = 'SEP';
				break;
			case 10:
				prevMonth = 'OCT';
				break;
			case 11:
				prevMonth = 'NOV';
				break;
			case 12:
				prevMonth = 'DEC';
				break;
			default:
				break;
			}
			$scope.formData.month = prevMonth;
			$scope.formData.year = ''+parseInt(yyyy);
			if($scope.formData.year !== null && $scope.formData.year !== undefined && $scope.formData.month !== null && $scope.formData.month !== undefined){
				$scope.formData.billMonth = $scope.formData.month+"-"+$scope.formData.year;
			}
			console.log("Current Bill Month is:"+$scope.formData.month+"-"+$scope.formData.year);
		};
		
		$scope.exportConsumptionReport = function () {
	        var params = {
	        		Authorization: 'Basic '+$scope.encodedCredentials,
	        		username: $scope.user.username
	        	};
	        var fileUrl;
			fileUrl = window.location.origin+"/htbilling/backend/export/bill/month/"+$scope.formData.billMonth;	
	    	var url = [fileUrl, $.param(params)].join('?');
	    	window.open(url);
	    };
}]);