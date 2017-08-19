var app=angular.module("myApp",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	console.log('entering configuration')		
		$routeProvider	
	.when('/',{ // $scope.user, $scope.register() -- local for this page
		controller:'UserController',
		templateUrl:'login.html'			
	})
	.when('/login',{ // $scope.user, $scope.register() -- local for this page
		controller:'UserController',
		templateUrl:'login.html'			
	})
	.when('/home',{ // $scope.user, $scope.register() -- local for this page
		controller:'UserController',
		templateUrl:'home.html'			
	})
	
})



app.run(function($cookieStore,$rootScope,$location,UserService){  //entry point
	
	if($rootScope.presentUser==undefined)
		$rootScope.presentUser=$cookieStore.get('presentUser')
		
	$rootScope.logoff=function(){
		console.log('logout function')
		delete $rootScope.presentUser;
		$cookieStore.remove('presentUser')
		UserService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$rootScope.message="Logged out Successfully";
			$location.path('/login')
		},
		function(response){
			console.log(response.status);
		})
		
	}	
})









/*

.when('/logout',{		
		controller:'UserController',
		templateUrl:'_user/login.html'
	})	*/