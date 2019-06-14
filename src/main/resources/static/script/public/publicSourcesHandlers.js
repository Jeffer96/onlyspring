var visible = false;
function toggleVisiblePass(){
	visible = !visible;
	document.getElementById("toggleVisibility").src = "supportSources/images/" + (visible ? "hide.png" : "show.png");
	document.getElementById("userPass").type = visible ? "text" : "password";
}
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
	fetch("/registro/add",{
		method: 'POST',
		headers: header,
		body: JSON.stringify(user),
	}).then(function(response){
		return response.text();
	}).then(function(text) {
		alert(text);
	  }).catch(error => console.error(error));
	document.getElementById("userName").value = "";
	document.getElementById("userPass").value = "";
}
function authenticate(){
	showLoading();
	var auth = {"userName":document.getElementById("userNick").value,
				"userPass":document.getElementById("userPass").value};
	fetch("/login/auth",{
		method : 'POST',
		headers : header,
		body : JSON.stringify(auth)
	}).then(function(response){
		return response.text();
	}).then(function (data){
		if(data.length>1){
			var resp = data.split("<<>>");
			alertResponse(true,"Bienvenido: \n"+resp[1]);
			localStorage.setItem("token",resp[0]);
		}else{
			alertResponse(false,"El usuario no se ha autenticado correctamente");
		}
		
	}).catch(error => alertResponse(false,"La operacion no se pudo completar: "+error));
	document.getElementById("userNick").value = "";
	document.getElementById("userPass").value = "";
}
function showLoading(resp, msg){
	
}
