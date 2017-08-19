app.factory('UserService',function($http){
	var userService=this;
	
	var BASE_URL="http://localhost:1010/userrest1"
	userService.authenticate=function(user){
		return $http.post(BASE_URL + "/logincheck",user);
	};
	
	userService.registerUser=function(user){
		return $http.post(BASE_URL+"/register",user);
	};

	userService.logout=function(user){
		return $http.post(BASE_URL+"/logout");
	};
	
	
	return userService;
})