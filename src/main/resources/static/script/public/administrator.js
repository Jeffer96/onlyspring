var currentMenu;
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
				setListenerAddButton();
				poblateTable(listUsers);
			}).fail(function(error){
				console.log(error);
			});
		}).catch(function(error){
			hideLoading();
			alert("error: "+error);
		});
	});
	$("#pl").click(function(){
		toggle("pl");
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
function setListenerAddButton(){
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
					var user = {
						'userName' : document.getElementById("userNameInput").value,
						'nickName' : document.getElementById("userNickInput").value,
						'userPass' : document.getElementById("userPassInput").value,
						'id' : document.getElementById("userIdInput").value,
						'roleName' : setterRole.options[setterRole.selectedIndex].value
					};
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
						},2000);
					}).catch(function(error){
						console.log(error);
					});
				}
			});
			$("#cancel").click(function(){
				$("#cu").click();
			});
			hideLoading();
		}).fail(function(error){
			hideLoading();
			$("#cu").click();
			console.log(error);
		});			
	});
}
function confirmPass(){
	var ans = document.getElementById("userPassInput").value == document.getElementById("confirmPassInput").value;
	var msg = document.getElementById("mesagge");
	msg.style.display = "block";
	if (ans){
		msg.style.color = "green";
		msg.innerHTML = "las contraseñas coniciden";
	}else{
		msg.style.color = "red";
		msg.innerHTML = "las contraseñas no coniciden";
	}
	return ans;
}
function poblateTable(listUsers){
	var table = document.getElementById("controlUsuariosTable");
	for(i=0; i<listUsers.length; i++){
		var user = listUsers[i];
		var row = table.insertRow(i+1);
		row.id = user.id;
		var cell = row.insertCell(0);
		cell.className = "colNum numData";
		cell.innerHTML = i+1;
		cell = row.insertCell(1);
		cell.className = "nameData";
		cell.innerHTML = user.userName;
		cell = row.insertCell(2);
		cell.className = "idData";
		cell.innerHTML = user.id;
		cell = row.insertCell(3);
		cell.className = "nickData";
		cell.innerHTML = user.nickName;
		cell = row.insertCell(4);
		cell.className = "rolData";
		cell.innerHTML = user.roleName;
		cell = row.insertCell(5);
		cell.setAttribute("onclick","editUser("+user.id+")"); 
		cell.className = "editOpt";
		cell.innerHTML = "Editar";
		cell = row.insertCell(6);
		cell.setAttribute("onclick","deleteUser("+user.id+")"); 
		cell.className = "deleteOpt";
		cell.innerHTML = "Eliminar";
	}
	hideLoading();
}

function editUser(idUser){
	alert("editar usuario: "+idUser);
}

function deleteUser(idUser){
	alert("eliminar usuario: "+idUser);
}
function toggle(newId){
	if (currentMenu!=null){
		document.getElementById(currentMenu).classList.remove("select");
	}
	currentMenu = newId;
	document.getElementById(newId).classList.add("select");
}