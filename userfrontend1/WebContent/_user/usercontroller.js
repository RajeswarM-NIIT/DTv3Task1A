app.controller('UserController',function($scope,$rootScope,$location,$cookieStore,UserService){
	$scope.user={userid:'',username:'',password:'',enabled:'',userrole:''};	
	$scope.message;
	$scope.submit=function(){
		console.log('submit function in usercontroller');
		UserService.authenticate($scope.user)
		.then(function(response){			
				console.log('login success');
				console.log($scope.user.userrole);
				$scope.user=response.data;
				$rootScope.presentUser=response.data;
				
				//console.log($rootScope.presentUser.userid);
				//$scope.message="Successfully Registered";
				$cookieStore.put('presentUser',$rootScope.presentUser)
				$location.path("/home");							
			},
			function(response){
				console.log('login failed');
				$scope.message="Invalid Username or Password";
				$location.path("/login");	
		})
	}

	$scope.registerUser=function(){
		console.log('entering register user')
        UserService.registerUser($scope.user)		
		.then(function(response){
			$scope.message="Successfully Registered";
			console.log("registration success" + response.status)
			alert('Registration success. User id : ' + response.data.userid);
			$location.path("/login");
			
		}, function(response){
			console.log("registration failed" +  response.status)
			$scope.errmsg="Registration failed";
			$location.path("/register");
			
		})
	}
		

	
	
	
	
	$rootScope.hasRole=function(role){
		if($rootScope.presentUser.role==undefined)
			return false;
		return $rootScope.role==role;			
	}
	
	
	
	$scope.logout=function(){
		$cookieStore.remove('presentUser')
		$scope.message="Loged out successfully";
		$location.path('/login')		
	}
	
})













