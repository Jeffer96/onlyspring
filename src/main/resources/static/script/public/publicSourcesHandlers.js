function loadReady(){
	showLoading();
	fetch("/login/getUrl",{
		headers : new Headers({'Authorization' : localStorage.getItem(ap) ? tp + localStorage.getItem(ap) : ""})
	})
	.then(function(response){
		return response.text();
	}).then(function(data){
		loadWith(data);
	}).catch(function(error){
		loadWith("/login");
	});
}
function lo(){
	fetch("/logout").catch(function(error){
		alert("Algo salio mal, se redigira a la pagina de inicio");
	});
	localStorage.removeItem(ap);
	localStorage.removeItem("currentUser");
	loadWith("/login");
}
function loadWith(goToUrl){
	showLoading();
	$.ajax({url: goToUrl,
		type: "GET",
		headers:{ 'Authorization' : localStorage.getItem(ap) ? tp + localStorage.getItem(ap) : ""}
	}).done(function(newUrl){
		current = goToUrl;
		$("#rootContent").html(newUrl);
		setTimeout(function(){hideLoading();},100);
	}).fail(function(errorRequest) {
		if (errorRequest.status =="401"){
			alert("Wow! parece que no te has authenticado para acceder al portal");
			current = "/login";
			loadWith("/login");
		}
	});
}
function showLoading(){document.getElementById("rootContent").style.display="none";document.getElementById("loadingLayout").style.display = "block";}function hideLoading(){document.getElementById("rootContent").style.display="initial";document.getElementById("loadingLayout").style.display = "none";}const tp = 'Bearer '; const ap = "token"; var identifier={"/login":function(){sll();},"/administrador":function(){sla();}};var current="";var visible = false;var currentDrop = "";function dropDown(id){if (currentDrop == ""){currentDrop = id+"DropDown";document.getElementById(currentDrop).style.display = "initial";}else if(currentDrop == id+"DropDown"){document.getElementById(id+"DropDown").style.display = "none";currentDrop = "";}else{document.getElementById(currentDrop).style.display = "none";currentDrop = id+"DropDown";document.getElementById(currentDrop).style.display = "initial";}}function toggleVisiblePass(){visible = !visible;document.getElementById("toggleVisibility").src = "supportSources/images/" + (visible ? "hide.png" : "show.png");document.getElementById("userPass").type = visible ? "text" : "password";}function changeInputText(){document.getElementById("errorMsg").style.display = "none";}
function sll(){$("#submitButton").click(function(){authenticate({"userName":document.getElementById("userNick").value,"userPass":document.getElementById("userPass").value});});}
function sla(){$("#logOut").click(function(){lo();});}
function ready(){
	identifier[current]();
}
function authenticate(oauth){
	showLoading();
	fetch("/login/auth",{
		method : 'POST',
		headers : new Headers({
			'Content-Type': 'application/json'
		}),
		body : JSON.stringify(oauth)
	}).then(function(response){
		return response.json();
	}).then(function (data){
		if (data.respCode == "202"){
			document.getElementById("errorMsg").style.display = "none";
			localStorage.setItem(ap,data.token);
			localStorage.setItem("currentUser",data.userName);
			//window.location.href = data.url;
			loadWith(data.url);
		}else if (data.respCode == "401"){
			hideLoading();
			document.getElementById("errorMsg").style.display = "initial";
		}else{
			hideLoading();
			alert("Ocurrio un error, intentelo nuevamente");
		}}).catch(function (error){
			hideLoading();
			alert("no se pudo completar la operacion: "+error);
		});
		document.getElementById("userNick").value = "";
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

function testTwo(){
	$.ajax("/administrador",{
		type : 'GET'
	}).done(function(newUrl){
		$("#rootContent").html(newUrl);
	});
}

