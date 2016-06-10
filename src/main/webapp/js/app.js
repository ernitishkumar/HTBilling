/*
 * Creating IIFE(Immediately Invoked Function Expression to launch our Anuglar App and 
 * create default services and values and configuration.
 */
(function () {
	/*
	 * Getting core app object in app variable to be used through out this script in current's functions
	 * scope.
	 */
	var app = angular.module('htBillingApp', ['ngRoute','ngAnimate','LocalStorageModule','angularUtils.directives.dirPagination']);

	/*
	 * Defining complete routing for the application and wiring them with appropriate pages.
	 */
	app.config(function ($routeProvider) {
		$routeProvider.when('/', {
			templateUrl: 'templates/pages/login/login.html',
			controller: 'FormController',
			controllerAs: 'fc'
		})
		.when('/ht/home', {
			templateUrl: 'templates/pages/ht/home.html',
			controller: 'HomeController',
			controllerAs: 'homeCtrl'
		}).when('/operator/home', {
			templateUrl: 'templates/pages/operator/operatorhome.html',
			controller: 'OperatorHomeController',
			controllerAs: 'homeCtrl'
		}).when('/circle/home', {
			templateUrl: 'templates/pages/circle/circlehome.html',
			controller: 'CircleHomeController',
			controllerAs: 'homeCtrl'
		}).when('/developer/home', {
			templateUrl: 'templates/pages/developer/developerhome.html',
			controller: 'DeveloperController',
			controllerAs: 'developerCtrl'
		})
		.when('/enterreading', {
			templateUrl: 'templates/pages/ht/reading/meterreading.html',
			controller: 'MeterReadingController',
			controllerAs: 'readingCtrl'
		}).when('/srfrenterreading', {
			templateUrl: 'templates/pages/ht/reading/srfrmeterreading.html',
			controller: 'SRFRMeterReadingController',
			controllerAs: 'srfrreadingCtrl'
		}).when('/saved/:message', {
			templateUrl: 'templates/pages/ht/reading/metersaved.html',
			controller: 'SaveController',
			controllerAs: 'saveCtrl'
		}).when('/ht/readings', {
			templateUrl: 'templates/pages/ht/reading/viewmeterreading2.html',
			controller: 'ViewMeterReadingsController',
			controllerAs: 'viewMeterReadingsCtrl'
		}).when('/meter/add', {
			templateUrl: 'templates/pages/ht/meter/addmeter.html',
			controller: 'AddMeterController',
			controllerAs: 'addMeterCtrl'
		}).when('/meter/update/:meterNo', {
			templateUrl: 'templates/pages/ht/meter/updatemeter.html',
			controller: 'UpdateMeterController',
			controllerAs: 'updateMeterCtrl'
		})
		.when('/meter/view', {
			templateUrl: 'templates/pages/ht/meter/viewmeterdetails.html',
			controller: 'ViewMeterDetailsController',
			controllerAs: 'viewMeterDetailsCtrl'
		})
		.when('/plant/add', {
			templateUrl: 'templates/pages/ht/plant/addplant.html',
			controller: 'AddPlantController',
			controllerAs: 'addPlantCtrl'
		}).when('/plant/updateplant/:plantId', {
			templateUrl: 'templates/pages/ht/plant/updateplant.html',
			controller: 'UpdatePlantController',
			controllerAs: 'updatePlantCtrl'
		})
		.when('/plant/view', {
			templateUrl: 'templates/pages/ht/plant/viewplantdetails.html',
			controller: 'ViewPlantDetailsController',
			controllerAs: 'viewPlantDetailsCtrl'
		})
		.when('/plant/investor/add', {
			templateUrl: 'templates/pages/ht/plant/mapinvestor.html',
			controller: 'AddInvestorMappingController',
			controllerAs: 'addInvestorMappingCtrl'
		})
		.when('/plant/investor/view', {
			templateUrl: 'templates/pages/ht/plant/viewinvestormapping.html',
			controller: 'ViewInvestorMappingController',
			controllerAs: 'viewInvestorMappingCtrl'
		})
		.when('/developer/add', {
			templateUrl: 'templates/pages/developer/adddeveloper.html',
			controller: 'AddDeveloperController',
			controllerAs: 'addDeveloperCtrl'
		}).when('/developer/update/:id', {
			templateUrl: 'templates/pages/developer/updatedeveloper.html',
			controller: 'UpdateDeveloperController',
			controllerAs: 'updateDeveloperCtrl'
		})
		.when('/developer/view', {
			templateUrl: 'templates/pages/developer/viewdeveloperdetails.html',
			controller: 'ViewDeveloperDetailsController',
			controllerAs: 'viewDeveloperDetailsCtrl'
		})
		.when('/investor/add', {
			templateUrl: 'templates/pages/ht/investor/addinvestor.html',
			controller: 'AddInvestorController',
			controllerAs: 'addInvestorCtrl'
		}).when('/investor/update/:id', {
			templateUrl: 'templates/pages/ht/investor/updateinvestor.html',
			controller: 'UpdateInvestorController',
			controllerAs: 'updateInvestorCtrl'
		})
		.when('/investor/view', {
			templateUrl: 'templates/pages/ht/investor/viewinvestordetails.html',
			controller: 'ViewInvestorDetailsController',
			controllerAs: 'viewInvestorDetailsCtrl'
		}).when('/user/add', {
			templateUrl: 'templates/pages/ht/user/adduser.html',
			controller: 'AddUserController',
			controllerAs: 'addUserCtrl'
		})
		.when('/machine/add', {
			templateUrl: 'templates/pages/ht/machine/addmachine.html',
			controller: 'AddMachineController',
			controllerAs: 'addMachineCtrl'
		}).when('/machine/update/:id', {
			templateUrl: 'templates/pages/ht/machine/updatemachine.html',
			controller: 'UpdateMachineController',
			controllerAs: 'updateMachineCtrl'
		})
		.when('/machine/view', {
			templateUrl: 'templates/pages/ht/machine/viewmachinedetails.html',
			controller: 'ViewMachineDetailsController',
			controllerAs: 'viewMachineDetailsCtrl'
		})
		.when('/developer/readings/view', {
			templateUrl: 'templates/pages/developer/viewdeveloperreading.html',
			controller: 'DeveloperViewMeterReadingsController',
			controllerAs: 'viewMeterReadingsCtrl'
		})
		.when('/developer/readings/split/:plantId/:consumptionId', {
			templateUrl: 'templates/pages/developer/readingsbifircationpage.html',
			controller: 'BifircateReadingsController',
			controllerAs: 'viewMeterReadingsCtrl'
		})
		.when('/developer/readings/viewsplited/:consumptionId', {
			templateUrl: 'templates/pages/developer/viewbifircationpage.html',
			controller: 'ViewBifircateReadingsForDeveloperController',
			controllerAs: 'viewMeterReadingsCtrl'
		})
		.when('/developer/investor/bill/view/:billDetailsId', {
			templateUrl: 'templates/pages/developer/viewbill.html',
			controller: 'ViewBillController',
			controllerAs: 'viewBillCtrl'
		})
		.when('/circle/readings', {
			templateUrl: 'templates/pages/circle/viewcirclereading.html',
			controller: 'CircleViewMeterReadingsController',
			controllerAs: 'viewMeterReadingsCtrl'
		})
		.when('/circle/readings/viewsplited/:consumptionId', {
			templateUrl: 'templates/pages/circle/viewbifircationpage.html',
			controller: 'ViewBifircateReadingsForCircleController',
			controllerAs: 'viewMeterReadingsCtrl'
		})
		.when('/circle/readings/consumptions', {
			templateUrl: 'templates/pages/circle/circle_consumption_validation_page.html',
			controller: 'CircleConsumptionValidationController',
			controllerAs: 'circleConsumptionValidationController'
		});
	});

	/*
	 * Introducing AuthService as 'authService' for complete app
	 * to perform Authorization related services and operations.
	 * This service has dependency on $http,$q and localStorageService
	 */
	app.factory('authService',['$http','$q','$location','localStorageService',function($http,$q,$location,localStorageService){

		/* 
		 * object of this serice which will be returned to the caller of this service.
		 * var authService;
		 */
		var authService = {};

		/*
		 * declaring user key to be used while storing and fetching data from local storage
		 */
		const USER_KEY = 'user';
		authService.USER_KEY = USER_KEY;

		/*
		 * declaring user_role key to be used while storing and fetching data from local storage
		 */
		const USER_ROLE_KEY = 'user_role';
		authService.USER_ROLE_KEY = USER_ROLE_KEY;

		/*
		 * declaring user_role key to be used while storing and fetching data from local storage
		 */
		const LOGIN_FORM_DATA_KEY = 'login_form';
		authService.LOGIN_FORM_DATA_KEY = LOGIN_FORM_DATA_KEY;

		/* 
		 * login function for login at the backend. returns promises using $q deferred services.
		 */
		var login = function(formData){
			var deferred = $q.defer();
			storeData(LOGIN_FORM_DATA_KEY,formData);
			$http(
					{
						method: 'GET',
						url: 'backend/authentication/login',
						params: {
							username: formData.username,
							password: formData.password
						}
					}
			).then(
					function(response){
						deferred.resolve(response);
					},
					function(error){
						deferred.reject(error);
					});
			return deferred.promise;
		};

		/*
		 * logout() method to logout user from application.
		 * function clears all the local storage for the app 
		 */
		var logout = function(){
			removeData();
			$location.path("/");
		}

		/*
		 * storeData() function to store any data in browsers localStorage as per key provided
		 */
		var storeData = function(key,data){
			if(key === USER_KEY){
				localStorageService.set(USER_KEY,data);
			}else if(key === USER_ROLE_KEY){
				localStorageService.set(USER_ROLE_KEY,data);
			}else if(key === LOGIN_FORM_DATA_KEY){
				localStorageService.set(LOGIN_FORM_DATA_KEY,data);
			}
		}

		/*
		 * fetchData() function to fetch any data in browsers localStorage as per key provided
		 */
		var fetchData = function(key){
			if(key === USER_KEY){
				return localStorageService.get(USER_KEY);
			}else if(key === USER_ROLE_KEY){
				return localStorageService.get(USER_ROLE_KEY);
			}else if(key === LOGIN_FORM_DATA_KEY){
				return localStorageService.set(LOGIN_FORM_DATA_KEY);
			}
		}

		/*
		 * removeData() function to remove any data in browsers localStorage as per key provided 
		 */
		var removeData = function(key){
			if(key === USER_KEY){
				return localStorageService.remove(USER_KEY);
			}else if(key === USER_ROLE_KEY){
				return localStorageService.remove(USER_ROLE_KEY);
			}else if(key === LOGIN_FORM_DATA_KEY){
				return localStorageService.remove(LOGIN_FORM_DATA_KEY);
			}else{
				return localStorageService.clearAll();
			}
		}

		/*
		 * wiring login() function to authService object's variable,which will be returned to the callee
		 */
		authService.login = login;

		/*
		 * wiring logout() function to authService object's variable,which will be returned to the callee
		 */
		authService.logout = logout;

		/*
		 * wiring storeData() function to authService object's variable,which will be returned to the callee
		 */
		authService.storeData = storeData;

		/*
		 * wiring fetchData() function to authService object's variable,which will be returned to the callee
		 */
		authService.fetchData = fetchData;

		/*
		 * wiring removeData() function to authService object's variable,which will be returned to the callee
		 */
		authService.removeData = removeData;

		/*
		 * returning authService object to the required callee.
		 */
		return authService;
	}]);

	/*
	 * Introducing Utility service for our app which contains various utility functions to be
	 * used across the apps
	 */
	app.factory('utilService',['$http','$q','$location',function($http,$q,$location){

		/* 
		 * object of this serice which will be returned to the caller of this service.
		 * var utilService;
		 */
		var utilService = {};

		/*
		 * function calculateData(reading) to calculate the difference between the 
		 * previous month's readings and current month's readings using JavaScript floating subtraction 
		 * methods so that values are rounded to upto 2 decimal places long
		 */
		var calculateReadingData = function(reading) {
			reading.activeEnergyDifference = (reading.currentMeterReading.activeEnergy - reading.previousMeterReading.activeEnergy).toFixed(2);

			reading.activeEnergyConsumption = (reading.activeEnergyDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.todOneDifference = (reading.currentMeterReading.activeTodOne - reading.previousMeterReading.activeTodOne).toFixed(2);

			reading.todOneConsumption = (reading.todOneDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.todTwoDifference = (reading.currentMeterReading.activeTodTwo - reading.previousMeterReading.activeTodTwo).toFixed(2);

			reading.todTwoConsumption = (reading.todTwoDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.todThreeDifference = (reading.currentMeterReading.activeTodThree - reading.previousMeterReading.activeTodThree).toFixed(2);

			reading.todThreeConsumption = (reading.todThreeDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.quadrantOneDifference = (reading.currentMeterReading.reactiveQuadrantOne - reading.previousMeterReading.reactiveQuadrantOne).toFixed(2);

			reading.quadrantOneConsumption = (reading.quadrantOneDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.quadrantTwoDifference = (reading.currentMeterReading.reactiveQuadrantTwo - reading.previousMeterReading.reactiveQuadrantTwo).toFixed(2);

			reading.quadrantTwoConsumption = (reading.quadrantTwoDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.quadrantThreeDifference = (reading.currentMeterReading.reactiveQuadrantThree - reading.previousMeterReading.reactiveQuadrantThree).toFixed(2);

			reading.quadrantThreeConsumption = (reading.quadrantThreeDifference * reading.currentMeterReading.mf).toFixed(2);

			reading.quadrantFourDifference = (reading.currentMeterReading.reactiveQuadrantFour - reading.previousMeterReading.reactiveQuadrantFour).toFixed(2);

			reading.quadrantFourConsumption = (reading.quadrantFourDifference * reading.currentMeterReading.mf).toFixed(2);
		};

		/*
		 * wiring removeData() function to utilService object's variable,which will be returned to the callee
		 */
		utilService.calculateReadingData = calculateReadingData;

		/*
		 * declaring constant HT_PATH variable & wiring with utilService object's.
		 */
		const HT_PATH = '/ht';
		utilService.HT_PATH = HT_PATH;

		/*
		 * declaring constant DEVELOPER_PATH variable & wiring with utilService object's.
		 */
		const DEVELOPER_PATH = '/developer';
		utilService.DEVELOPER_PATH = DEVELOPER_PATH;

		/*
		 * declaring constant CIRLCE_PATH variable & wiring with utilService object's.
		 */
		const CIRCLE_PATH = '/circle';
		utilService.CIRCLE_PATH = CIRCLE_PATH;

		/*
		 * declaring constant OPERATOR_PATH variable & wiring with utilService object's.
		 */
		const OPERATOR_PATH = '/operator';
		utilService.OPERATOR_PATH = OPERATOR_PATH;

		/*
		 * returning utilService object to the required callee.
		 */
		return utilService;
	}]);

	/*
	 * Injector service to inject username & password to every request made to the 
	 * backedn api service
	 */
	app.factory('credentialsInjector',['localStorageService',function(localStorageService){
		var credentialsInjector = {
				request: function(config){
					var formData = localStorageService.get('login_form');
					if(formData!=null){
						config.headers['Authorization'] = "Basic "+window.btoa(formData.username+":"+formData.password);	
					}
					return config;
				}
		};
		return credentialsInjector;
	}]);

	/*
	 * binding injector service to injector provider
	 */
	app.config(['$httpProvider',function($httpProvider){
		$httpProvider.interceptors.push('credentialsInjector');
	}]);

	/*
	 * configuring localStorageService for different configuration
	 */
	app.config(function (localStorageServiceProvider) {
		localStorageServiceProvider
		.setPrefix('ht_billing')
		.setStorageType('sessionStorage')
		.setNotify(true, true)
	});
})();