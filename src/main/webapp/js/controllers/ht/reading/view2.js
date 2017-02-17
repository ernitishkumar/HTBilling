angular.module("htBillingApp").controller('ViewReadingsController', ['$http', '$scope', '$location', '$routeParams','authService','utilService', function ($http, $scope, $location, $routeParams, authService,utilService) {

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};
	
	$scope.formData = {};

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
		}else if(userRole.role === "admin" || userRole.role === "operator"){
			$scope.user = user;
			$scope.userRole = userRole;
			fetchCurrentYear();
			makeCurrentBillMonth();
			loadReadings();
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
	 * function to navigate to home page
	 */
	this.loadHome = function () {
		$location.path("/ht/home");
	};

	/*
	 * function to navigate to operator home page
	 */
	this.loadOperatorHome = function () {
		$location.path("/operator/home");
	};
	
	/*
	 * function to navigate to operator home page
	 */
	this.updateReading = function(readingId){
		$location.path("/ht/updatereading/"+readingId);
	};
	/*
	 * function to load all the readings for all the meters from backend
	 * and assign it to the view to display on front end
	 */
	function loadReadings() {
		$scope.loading = true;
		$http(
				{
					method: 'GET',
					url: 'backend/readings/month/'+$scope.formData.billMonth
				}
		).then(
				function (response) {
					var status = response.status;
					//checking the status. if status is ok(200) assigning the values 
					//to display on front end.
					if (status === 200) {
						$scope.readingData = response.data;
						$scope.readingData.forEach(function (reading) {
							utilService.calculateReadingData(reading);
						});
						$scope.loading = false;
					}
				},
				function(error){
					console.log("unable to display readings.error code is below");
					console.log(error);
				}
		);
	}

	/*
	 * function validateReading to implement validate action event
	 * this function runs when user clicks validate reading button on page.
	 */
	this.validateReading = function (reading,index) {
		console.log(reading.currentMeterReading.id);
		$http(
				{
					method: 'PUT',
					url: 'backend/readings/validate',
					params: {
						role: $scope.userRole.role,
						currentReadingId: reading.currentMeterReading.id,
						previousReadingId: reading.previousMeterReading.id
					}
				}
		).then(
				function (response) {
					var status = response.status;
					//checking status code for successful response from server
					if (status === 200) {
						//Implementing the above logic with index passed from the page, No need to loop over complete collection for
						//every validation.
						//var item = $scope.readingData[index];
						//if(item.meterNo === reading.meterNo){
							if ($scope.userRole.role === 'admin' || $scope.userRole.role === 'htcell') {
								$scope.readingData[$scope.readingData.indexOf(reading)].currentMeterReading.htCellValidation = 1;
							}
						//}
					}
				},
				function(error){
					console.log("error in validating readings. response is below");
					console.log(error);
				}
		);
	};
	
	
	/*
	 * function discardReading to implement discard action event
	 * this function runs when user clicks Discard reading button on page.
	 */
	this.discardReading = function (reading) {
		bootbox.confirm("Are you sure to Discard reading ?",function(answer){
			if(answer === true){
				$http(
						{
							method: 'PUT',
							url: 'backend/readings/discard',
							params: {
								readingId: reading.currentMeterReading.id
							},
							data: $scope.userRole
						}
				).then(
						function (response) {
							var status = response.status;
							//checking status code for successful response from server
							if (status === 200) {
								if ($scope.userRole.role === 'admin' || $scope.userRole.role === 'htcell') {
									$scope.readingData.splice($scope.readingData.indexOf(reading),1);
								}
							}
						},
						function(error){
							console.log("error in discarding readings. response is below");
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
	
	$scope.pageSize2 = 15;
	
	/*
	 * function pageChangeHandler gets executed when user
	 * changes page from the pagination row
	 */
	$scope.pageChangeHandler = function(num) {
	      
	  };
	  
	  $scope.billMonthYearChanged = function(){
			if($scope.formData.year !== null && $scope.formData.year !== undefined && $scope.formData.month !== null && $scope.formData.month !== undefined){
				$scope.formData.billMonth = $scope.formData.month+"-"+$scope.formData.year;
				if($scope.userRole.role === 'admin' || $scope.userRole.role === 'operator'){
					loadReadings();
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
}]);