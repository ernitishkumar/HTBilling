angular.module("htBillingApp").controller('UploadAMRFileController', ['$http', '$scope','$location','authService','Upload','$timeout', function ($http, $scope, $location,authService,Upload,$timeout) {

	/*
	 * var user a controller level variable to store user object.
	 */
	$scope.user = {};

	/*
	 * var userRole a controller level variable to store userRole object.
	 */
	$scope.userRole = {};

	/*
	 * var formData a controller level variable to store meter form data.
	 */
	$scope.fileToUpload = {};

	/*
	 * var uploadStarted variable to show about the uploading process.
	 */
	$scope.uploadStarted = false;
	
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
		}else if(userRole.role === "amr"){
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
	 * function to route to homepage
	 */
	this.loadHome = function () {
		$location.path("/amr/home");
	};

	

	$scope.processUpload = function () {
		$scope.uploadStarted = true;
		$scope.fileToUpload.upload = Upload.upload({
			url: 'backend/amr/upload',
			data: { file: $scope.fileToUpload}
		});

		var processesingModal;
		
		$scope.fileToUpload.upload.then(function (response) {
			$timeout(function () {
				$scope.fileToUpload.result = response.data;
			});
			var status = response.status;
			if(status === 201){
				bootbox.hideAll();
				var uploadResponse = response.data;
				bootbox.dialog({
					  title: "Upload Result",
					  message: "AMR File Uploaded Successfully !",
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
				$scope.clearForm();
			}
			
		}, function (error) {
			console.log("Error While Uploading AMR File");
			console.log(error);
			bootbox.hideAll();
			var data = error.data;
			var status = error.status;
			if(status === 417){
				var errorMessageToDisplay = "Some error occured while uploading AMR file.Try Again !";
				bootbox.dialog({
					  title: "Upload Failed !!!",
					  message: errorMessageToDisplay,
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
		}, function (evt) {
			// Math.min is to fix IE which reports 200% sometimes
			$scope.fileToUpload.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			if($scope.fileToUpload.progress === 100){
			 bootbox.dialog({
					  title: "Processing Records.. &nbsp;&nbsp;<span class='fa fa-refresh fa-spin'></span>",
					  message: "<div class='container'>"+
							  "<div class='form-group row'>" +
						  				"<label for='example-text-input' class='col-xs-4 col-form-label'></label>" +
						  					"<div class='col-xs-8'>"+
						  						"<p class='form-control-static' style='font-size:50px'></p>"+
						  					"</div>"+
						  			"</div>"+
				  		 "</div>"
					});
			}
		});

	};
	
	$scope.clearForm = function () {
		$scope.formData = null;
		$scope.error = null;
		$scope.fileToUpload = null;
		$scope.fileToUpload.progress = 0;
	};

	$scope.cancelUpload = function(){
		$scope.fileToUpload.upload.abort();
		$scope.fileToUpload = null;
	};
}]);