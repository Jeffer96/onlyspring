var visible = false;function toggleVisiblePass(){visible = !visible;document.getElementById("toggleVisibility").src = "supportSources/images/" + (visible ? "hide.png" : "show.png");document.getElementById("userPass").type = visible ? "text" : "password";}function changeInputText(){document.getElementById("errorMsg").style.display = "none";}function authenticate(){showLoading();var auth = {"userName":document.getElementById("userNick").value,"userPass":document.getElementById("userPass").value};fetch("/login/auth",{method : 'POST',headers : new Headers({'Content-Type': 'application/json'}),body : JSON.stringify(auth)}).then(function(response){return response.json();}).then(function (data){if (data.respCode == "202"){document.getElementById("errorMsg").style.display = "none";localStorage.setItem("token",data.token);localStorage.setItem("currentUser",data.userName);window.location.href = data.url;}else if (data.respCode == "401"){hideLoading();document.getElementById("errorMsg").style.display = "initial";}else{hideLoading();alert("Ocurrio un error, intentelo nuevamente");}}).catch(function (error){hideLoading();alert("no se pudo completar la operacion: "+error);});document.getElementById("userNick").value = "";document.getElementById("userPass").value = "";}function showLoading(){document.getElementById("loadingLayout").style.display = "block";}function hideLoading(){document.getElementById("loadingLayout").style.display = "none";}

function addUser(){
	const header = new Headers({'Content-Type': 'application/json'});
	if (localStorage.getItem("token")){
		header.append('Authorization','Bearer '+localStorage.getItem("token"));
	}
	var user = {};
	user["name"]= document.getElementById("userName").value;
	user["pass"] = document.getElementById("userPass").value;
	user["nickName"] = document.getElementById("userNick").value;
	user["id"] = document.getElementById("userId").value;
	user["roleName"]="ADMIN";
	fetch("/administrador/registro/add",{
		method: 'POST',
		headers: header,
		body: JSON.stringify(user)
	}).then(function(response){
		return response.text();
	}).then(function(text) {
		alert(text);
	  }).catch(error => console.error(error));
	document.getElementById("userName").value = "";
	document.getElementById("userPass").value = "";
}

function test(){
	var customHeader ={'Content-Type' : 'application/json'};
	if (localStorage.getItem("token")){
		customHeader['Authorization'] = 'Bearer '+ localStorage.getItem("token");
	}
	alert(customHeader['Authorization']);
	fetch("/administrador/test",{
		method : 'POST',
		headers : new Headers(customHeader)
	}).then(function (response){
		alert(response.status);
	});
}

