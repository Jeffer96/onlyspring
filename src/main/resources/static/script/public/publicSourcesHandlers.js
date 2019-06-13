var visible = false;
function toggleVisiblePass(){
	visible = !visible;
	document.getElementById("toggleVisibility").src = "supportSources/images/" + (visible ? "hide.png" : "show.png");
	document.getElementById("userPass").type = visible ? "text" : "password";
}
function addUser(){
	var user = {};
	user["name"]= document.getElementById("userName").value;
	user["pass"] = document.getElementById("userPass").value;
	user["nickName"] = document.getElementById("userNick").value;
	user["id"] = document.getElementById("userId").value;
	fetch("/registro/add",{
		method: 'POST',
		headers: new Headers({
		  'Content-Type': 'application/json'
		}),
		body: JSON.stringify(user),
	}).then(function(response){
		return response.text();
	})
	  .then(function(text) {
		alert(text);
	  }).catch(error => console.error(error));
	//fetch('/registro/add?name=Jefferson David Castañeda Carreño&nickName=jedacer&pass=123456&id=1010226725');
}
