//Función que permite que sólo se ingresen letras si el campo es de tipo caracter
var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO"; 


function stop(event) {
    if (event.preventDefault) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      event.returnValue = false;
      event.cancelBubble = true;
    }
}
 
function esCaracter(event) {
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key <= 90 && key >= 65)|| (key <= 122 && key >= 97) || (key>=0 && key<=8)) 
	 	event.returnValue=true;
	 else
		stop(event);
   		
}

function esCaracterConEspacios(event) {
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }
	
	 if ((key <= 90 && key >= 65)|| (key <= 122 && key >= 97) || (key>=0 && key<=8) || (key==32)
	 	|| (key==241) || (key==209) || (key==225) || (key==233) || (key==237) || (key==243) || (key==250)) 
	 	event.returnValue=true;
	 else
		stop(event);
   		
}

function esAlfaNumerico(event){
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==241) || (key==209)) 
	 	event.returnValue=true;
	 else
		stop(event);
}

function esAlfaNumericoConEspacios(event){
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==32)) 
	 	event.returnValue=true;
	 else
		stop(event);
}

function esAlfaNumericoConPuntos(event){
	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==46)) 
	 	event.returnValue=true;
	 else
		stop(event);
}

function esAlfaNumericoConPuntosYGuionBajo(event){
	var ok;
 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}
	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}

	if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==46) || (key==95))
		event.returnValue=true;
	else
		stop(event);
}

function esAlfaNumericoConPuntosYGuionMedio(event){
	var ok;
 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}
	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}

	if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==46) || (key==45))
		event.returnValue=true;
	else
		stop(event);
}

function esAlfaNumericoConGuionMedio(event){
	var ok;
 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}
	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}

	if ((key >=48 && key <= 57) || (key >=65 && key <=90) || (key >=97 && key <=122) || (key>=0 && key<=8) || (key==45))
		event.returnValue=true;
	else
		stop(event);
}

//permite numeros y S/N (s/n)
function esNumericoConSNoSlash(event) {
 	var ok;
 	var key;
	 if (type=="IE") { 
		key = event.keyCode;
	}
	 if (type=="MO" || type=="OP") {
		key = event.which;		  
	 }

	 if ((key >8 && key < 47) || (key > 57 && key != 115 && key != 83 && key != 110 && key != 78) ) {
	 	stop(event);
	 }
	 else{
		event.returnValue = true;}
}

//permite numeros, espacios y S/N (s/n)
function esNumericoConSNoSlashEspacios(event) {
 	var ok;
 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}
	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}

	if ((key >8 && key < 32) || (key >32 && key < 47) || (key > 57 && key != 115 && key != 83 && key != 110 && key != 78)) {
		stop(event);
	}else{
		event.returnValue = true;
	}
}

function validarNumeroRomano(event){

	//      Min  May
	// I	105	 73
	// V	118	 86
	// X	120	 88
	// L	108	 76
	// C	99	 67
	// D	100	 68
	// M	109	 77

 	var key;
	if (type=="IE") { 
		key = event.keyCode;
	}
	if (type=="MO" || type=="OP") {
		key = event.which;		  
	}
	
	if ((key == 105) || (key == 73) || (key == 118) || (key == 86) || (key == 120) || (key == 88) || (key == 108) ||
		(key == 76) || (key == 99) || (key == 67) || (key == 100) || (key == 68) || (key == 109) || (key == 77) ||
		(key == 0) || (key == 8))
	{
		event.returnValue = true;	 	
	}
	else{
		stop(event);
	}
}

function pasarAMayuscula(id){
	var num = $('#'+id).val();
	$('#'+id).val(num.toUpperCase());
}