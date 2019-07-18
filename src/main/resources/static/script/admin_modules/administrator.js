var currentMenu;var collectionUsers;var currentEditingUser; var collectionCustomers;
$(document).ready(function(){
	document.getElementById("userName").innerHTML = localStorage.getItem("currentUser");
	$("#cu").click(function(){
		showLoading();
		toggle("cu");
		fetch("/administrador/getAllUsers",{
			method : 'GET',
			headers : new Headers({
				'Authorization': tp + localStorage.getItem(ap)
			})					
		}).then(function(list){
			return list.json();
		}).then(function(listUsers){
			$.ajax({url:'/controlUsuarios',
				type : 'GET',
				headers : {'Authorization' : tp + localStorage.getItem(ap)}
			}).done(function (ans){
				$("#MainDashBoard").html(ans);
				setListenerAddUserButton();
				//console.log(listUsers);
				poblateTable(listUsers, getRowControlUsers, collectionUsers, "controlUsuariosTable");
			}).fail(function(error){
				console.log(error);
			});
		}).catch(function(error){
			hideLoading();
			alert("error: "+error);
		});
	});
	$("#pl").click(function(){
		showLoading();
		toggle("pl");
		fetch("/administrador/getCustomers",{
			method : 'GET',
			headers : new Headers({
				'Authorization' : tp + localStorage.getItem(ap)
			})
		}).then(function(list){
			return list.json();
		}).then(function(listCustomers){
			console.log(listCustomers[0].contacts);
			$.ajax({
				url : '/clientes',
				type : 'GET',
				headers : {'Authorization' : tp + localStorage.getItem(ap)}
			}).done(function(ans){
				$("#MainDashBoard").html(ans);
				//poblateTable(listCustomers, getRowCustomer, collectionCustomers, "clientesTable");
				
			})
		})
	});
	$("#tr").click(function(){
		toggle("tr");
	});
	$("#ca").click(function(){
		toggle("ca");
	});
	$("#ga").click(function(){
		toggle("ga");
	});
});
function toggle(newId){
	if (currentMenu!=null){
		document.getElementById(currentMenu).classList.remove("select");
	}
	currentMenu = newId;
	document.getElementById(newId).classList.add("select");
}

function poblateTable(list, rowCallBack, storeVar, idTable){
	storeVar = {};
	var table = document.getElementById(idTable);
	for(i=0; i<list.length; i++){
		table.appendChild(rowCallBack(list[i], i, storeVar, document.createElement("tr")));
	}
	hideLoading();
}

function getRowControlUsers(user, ind, storeVar, newRow){
	storeVar[user.id] = user;
	var fedit = "editUser('"+user.id+"')";
	var fdelete = "deleteUser('"+user.id+"')";
	newRow.className = (ind)%2!=0 ? "darkRow" : "lightRow";
	newRow.id = user.id;
	newRow.innerHTML = "<td class='colNum numData'>"+ (ind+1)+"</td><td class='nameData'>"+user.userName+"</td><td class='idData'>"+user.id+"</td><td class='nickData'>"+user.nickName+"</td><td class='rolData'>"+user.roleName+"</td><td class='editOpt' onclick="+fedit+">Editar</td><td class='deleteOpt' onclick="+fdelete+">Eliminar</td>";
	return newRow;
}

function getRowCustomer(customer, ind, storeVar, newRow){
	storeVar[customer.nit] = customer;
	var row = "<td class='colNum numData'>"+(int+1)+"</td><td class='nameData'>"+customer.customerName+"</td><td class='nitData'>"+customer.nit+"</td><td class= statusActive "+customer.status_active ? "'activeData'> <b>►</b> Activo" : "'inactiveData'><b>►</b> Inactivo"+"</td><td class='detailsData'><p>▼</p></td>"
	var details = document.createElement("tr");
	details.className = "detailsRow";
	details.id = customer.nit+"Details";
	var detailsContent = "<h1>Contactos:</h1>";
	/**for (i=0; i<customer.contacts.length; i++){
		var contactInfo = document.createElement("div");
		contactInfo.className="contactInfo";
		contactInfo.innerHTML = "<h2>"+customer.contacts[i].contact_name+"</h2>+<ul>";
		for (i=0; i<customer.contacts[i].)
	}**/
	
	
}

function setListenerAddCustomer(){
	$("#addButton").click(function(){
		
	});
}
function setListenerAddUserButton(){
	$("#addButton").click(function(){
		showLoading();
		$.ajax({
			url : '/registro',
			type : 'GET',
			headers : {'Authorization' : tp + localStorage.getItem(ap)}
		}).done(function(resp){
			$("#MainDashBoard").html(resp);
			$("#submit").click(function(){
				if (confirmPass()){
					var setterRole = document.getElementById("rolSetter");
					var user = {'userName' : document.getElementById("userNameInput").value,'nickName' : document.getElementById("userNickInput").value,'userPass' : document.getElementById("userPassInput").value,'id' : document.getElementById("userIdInput").value,'roleName' : setterRole.options[setterRole.selectedIndex].value};
					fetch('/administrador/addUser',{
						method : 'POST',
						headers : new Headers({
							'Content-Type' : 'application/json',
							'Authorization' : tp + localStorage.getItem(ap)
						}),
						body : JSON.stringify(user)
					}).then(function(resp){
						return resp.text();
					}).then(function(resp){
						var mesage = document.getElementById("mesagge");
						mesage.style.display="block";
						if (resp=="202"){
							mesage.style.color = "green";
							mesage.innerHTML = "El usuario "+user.userName+" ha sido guardado exitosamente";
						}else{
							mesage.style.color = "red";
							mesage.innerHTML = "El usuario "+user.userName+" no pudo ser guardado";
						}
						setTimeout(function(){
							$("#cu").click();
						},1000);
					}).catch(function(error){
						alert("ocurrio un error, intentelo más tarde");
						console.log(error);
					});
				}else{
					alert("las contraseñas no coinciden!");
				}
			});
			$("#cancel").click(function(){
				$("#cu").click();
			});
			hideLoading();
		}).fail(function(error){
			alert("Error accediendo al recurso, comunique al administrador del sistema");
			hideLoading();
			$("#cu").click();
		});			
	});
}
function confirmPass(){
	var ans = document.getElementById("userPassInput").value == document.getElementById("confirmPassInput").value && document.getElementById("userPassInput").value.length>0;
	var msg = document.getElementById("mesagge");msg.style.display = "block";
	if (ans){
		msg.style.color = "green";
		msg.innerHTML = "las contraseñas coniciden";
	}else{
		msg.style.color = "red";
		msg.innerHTML = "las contraseñas no coniciden";
	}
	return ans;
}

function editUser(iu){
	showLoading();
	$.ajax({
		url : '/editUser',
		type : 'GET',
		headers : {
			'Authorization' : tp + localStorage.getItem(ap)
		}
	}).done(function(urlView){
		$("#MainDashBoard").html(urlView);
		currentEditingUser = collectionUsers[iu];
		setViewEdit(currentEditingUser);
	}).fail(function (error){
		alert("ocurrio un error accediendo al recurso");
		hideLoading();
		$("#cu").click();
	});
}
function setViewEdit(user){
	$("#title").html("Editar el usuario: "+user.userName);
	$("#actualName").html("Actual: "+user.userName);
	$("#actualNick").html("Actual: "+user.nickName);
	$("#actualId").html("Actual: "+user.id);
	$("#actualRol").html("Actual: "+user.roleName);
	$("#actualEmail").html("Actual: "+user.email);
	hideLoading();
}

function updateName(){
	var object = document.getElementById("newName");
	var entry = object.value;
	object.value="";
	if(entry.length>5){
		fetchUpdate('/administrador/updateName?id='+currentEditingUser.id+'&userName='+entry, setName, entry);
	}else{
		alert("El valor ingresado no parece valido, Verifiquelo");
	}
}

function setName(entry){
	currentEditingUser.userName = entry;
	setViewEdit(currentEditingUser);
}

function updateNick(){
	var object = document.getElementById("newNick");
	var entry = object.value;
	object.value="";
	if(entry.length>5){
		fetchUpdate('/administrador/updateNick?id='+currentEditingUser.id+'&nickName='+entry, setNick, entry);
	}else{
		alert("El valor ingresado no parece valido, Verifiquelo");
	}
}

function setNick(entry){
	currentEditingUser.nickName = entry;
	setViewEdit(currentEditingUser);
}

function updateId(){
	var object = document.getElementById("newId");
	var entry = object.value;
	object.value="";
	if(entry.length>5){
		fetchUpdate('/administrador/updateId?id='+currentEditingUser.id+'&newId='+entry, setCurrentId, entry);
	}else{
		alert("El valor ingresado no parece valido, Verifiquelo");
	}
}

function setCurrentId(entry){
	delete collectionUsers[currentEditingUser.id];
	currentEditingUser.id = entry;
	collectionUsers[entry] = currentEditingUser;
	setViewEdit(currentEditingUser);
}

function updateRol(){
	var entry = document.getElementById("rolSetter").value;
	fetchUpdate('/administrador/updateRol?id='+currentEditingUser.id+'&roleName='+entry.options[entry.selectedIndex].value, setRol, entry);
}

function setRol(entry){
	currentEditingUser.rol = entry;
	setViewEdit(currentEditingUser);
}

function updateEmail(){
	var object = document.getElementById("newEmail");
	var entry = object.value;
	object.value="";
	if(entry.length>5 && entry.includes("@") && entry.includes(".com")){
			fetchUpdate('/administrador/updateEmail?id='+currentEditingUser.id+'&email='+entry, setEmail, entry);
	}else{
		alert("El valor ingresado no parece valido, Verifiquelo");
	}
}

function setEmail(entry){
	currentEditingUser.email = entry;
	setViewEdit(currentEditingUser);
}

function fetchUpdate(url, callBack, param){
	showLoading();
	fetch(
		url,{
			method : 'POST',
			headers : new Headers({
				'Authorization' : tp + localStorage.getItem(ap)
			}),
		}
	).then(function(response){
		return response.text();
	}).then(function(code){
		if (code=="202"){
			callBack(param);
		}else if (code=="300"){
			hideLoading();
			alert("Para este usuario debe dirigirse a la configuracion de la cuenta");
		}else{
			hideLoading();
			alert("No se pudo actualizar el campo requerido, intentelo de nuevo");
		}
	}).catch(function(error){
		hideLoading();
		alert("Error, no se pudo completar la operacion");
		console.log(error);
	});
}

function goBack(){
	$("#cu").click();
}

function deleteUser(idUser){
	alert("eliminar usuario: "+idUser);
}