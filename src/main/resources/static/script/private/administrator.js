var currentMenu;
$(document).ready(function(){
	$("#cu").click(function(){
		toggle("cu");
		
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
function toggle(newId){
	if (currentMenu!=null){
		document.getElementById(currentMenu).classList.remove="select";
	}
	currentMenu = newId;
	document.getElementById(newId).classList.add="select";
}