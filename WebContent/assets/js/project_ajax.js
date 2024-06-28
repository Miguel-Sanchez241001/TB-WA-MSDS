
String.prototype.trim = function(){
	var s = this.toString();
	s = s.replace(/^\s+|\s+$/gi, ''); //sacar espacios blanco principio y final
	return s;
};

function SoloNumeros(e){
	var charCode;
	charCode = e.keyCode; 

	if ((charCode < 45 || charCode >45 )&&(charCode < 48 || charCode > 57)&& charCode != 46 || e.keyCode ==45 )
	return false;
}

function stopKey(evt) {
	var evt = (evt) ? evt : ((event) ? event : null);
	var node = (evt.target) ? evt.target
			: ((evt.srcElement) ? evt.srcElement : null);
	if ((evt.keyCode == 8)
			&& (node.type == "textarea" && node.readOnly == false)) {
		return true;
	}
	if ((evt.keyCode == 8)
			&& (node.type == "text" && node.readOnly == false)) {
		return true;
	}
	if ((evt.keyCode == 8)) {
		return false;
	}
}

function verMensajeError(div){
	
	var divElem =document.getElementById(div);
	if(divElem!=null){
		divElem.style.display = 'block';
		divElem.style.visibility = 'visible';
	}
	
}


function resetForm(form){
    var forma = document.forms[form];
    if(forma!=null){
    	forma.reset();
    }
	
}

function submitsAction(form,action,option,navegar){
	
	if(navegar==null || !navegar) return false;
	newAction = action + document.getElementById("idSession").value;
	document.forms[form].action = newAction;
	if(option!=null && option!= '') document.forms[form].option.value=option;
	disabledContolsNavigate();
	document.forms[form].submit();
	
}

function submitsAction2(form,action,navegar,disabled){
	if(navegar==null || !navegar) return false;
	document.forms[form].action=action;
	if(disabled) disabledContolsNavigate();
	document.forms[form].submit();
}


function submitsActionComplex(form,action,optionName,option,navegar,disabled){
	
	if(navegar==null || !navegar) return false;
	document.forms[form].action=action;
	if(optionName!=null && optionName!='' && option!=null && option!= ''){
		
		if(validNotExistInputForm(form,optionName)){
			var y = document.createElement("INPUT");
			y.setAttribute("type", "hidden");
			y.setAttribute("name",optionName);
			y.setAttribute("id",optionName);
			y.setAttribute("value",option);
			document.forms[form].appendChild(y);
		}else{
			var ctl = document.getElementById(optionName);
			if(ctl!=null && typeof(ctl)=='object' && ctl.type=='hidden'){
				ctl.value = option;
			}
		}

	}
	if(disabled) disabledContolsNavigate();
	document.forms[form].submit();
	
}

function validNotExistInputForm(form,optionName){
	var myForm = document.forms[form];
	if(myForm==null) return false;
    var elemtForm = myForm.elements;
    if(elemtForm==null) return false;
    if(elemtForm.length==0) return true;
	 for (var i = 0; i < elemtForm.length; i++) { 
		 var myEleId = elemtForm[i];
		 var myIdName = myEleId.id;
		 var myLbl = myIdName.toLowerCase().trim();
		 if(myLbl == optionName.toLowerCase().trim()){
			 return false;
		 }
	 }
	
	return true;
}


function disabledContolsNavigate(){

    var inputs = document.getElementsByTagName("input");
    if(inputs!=null){
	    for (var i = 0; i < inputs.length; i++) { 
	        
	        if(typeof(inputs[i])=='object' && (inputs[i].type=='button' || inputs[i].type=='image' || inputs[i].type=='submit')){
	        	inputs[i].disabled = true;
	        }
	        
	    }
    }

    var frames = window.top.document.getElementsByTagName("iframe");
    if(frames!=null){
    	for (var f = 0; f < frames.length; f++) { 
    		
        	var doc = frames[f].contentDocument? frames[f].contentDocument: frames[f].contentWindow.document;
        	var inputFrame = doc.getElementsByTagName("input");
        	if(inputFrame!=null){
        		for (var y = 0; y < inputFrame.length; y++) {
        			if(typeof(inputFrame[y])=='object' && (inputFrame[y].type=='button' || inputFrame[y].type=='image' || inputFrame[y].type=='submit')){
        				inputFrame[y].disabled = true;
        			}
        		}
        	}
    	}
    }


}



function validateReqControl(arrayVal){	
	 
	for	(var index = 0; index < arrayVal.length; index++) {	
		if(document.getElementById(arrayVal[index][0])==null || typeof(document.getElementById(arrayVal[index][0]))!='object'){
			continue;
		}
		if(document.getElementById(arrayVal[index][0]).value.trim()!=''){
			return true;
		}
	}
	alert('seleccione por lo menos un filtro de busqueda');
	return false;
}

function compareToDateHour(ctlDateMin,ctlHourMin,nameCtlMin, ctlDateMax,ctlHourMax,nameCtlMax){
	
	//fecha  dd/MM/yyyy/HH:mi
	if(ctlDateMin==null || ctlDateMin=='' || ctlDateMax==null || ctlDateMax==''){
		alert('el campo debe ser de tipo fecha');
		return false;
	}
	if(ctlHourMin==null || ctlHourMin=='' || ctlHourMax==null || ctlHourMax==''){
		alert('el campo debe ser de tipo fecha');
		return false;
	}
	
	 var dateMin = document.getElementById(ctlDateMin);
	 var hourMin = document.getElementById(ctlHourMin);
	 var dateMax = document.getElementById(ctlDateMax);
	 var hourMax = document.getElementById(ctlHourMax);
	
	if(dateMin==null || dateMin.value.trim().length!=10){
		alert('Fecha no v�lida dateMin1 (' + ctlDateMin +  ')');
		dateMin.focus();
		return false;
	}
	if(hourMin==null || hourMin.value.trim().length!=5){
		alert('Hora no v�lida hourMin1 (' + ctlHourMin +  ')');
		hourMin.focus();
		return false;
	}	
	
	if(dateMax==null || dateMax.value.trim().length!=10){
		alert('Fecha no v�lida dateMax1 (' + ctlDateMax +  ')');
		dateMax.focus();
		return false;
	}
	if(hourMax==null || hourMax.value.trim().length!=5){
		alert('Hora no v�lida hourMax (' + ctlHourMax +  ')');
		hourMax.focus();
		return false;
	}
	
	var fechaMin = dateMin.value.trim().split("/");
	var horaMin = hourMin.value.trim().split(":");
	var fechaMax = dateMax.value.trim().split("/");
	var horaMax = hourMax.value.trim().split(":");
	
	if(fechaMin==null || !is_array(fechaMin) || fechaMin.length!=3){
		alert('Fecha no v�lida fechaMin2');
		dateMin.focus();
		return false;
	}
	if(horaMin==null || !is_array(horaMin) || horaMin.length!=2){
		alert('Hora minuto no valido horaMin');
		hourMin.focus();
		return false;		
	}
	if(fechaMax==null || !is_array(fechaMax) || fechaMax.length!=3){
		alert('Fecha no v�lida fechaMax2');
		dateMax.focus();
		return false;
	}
	if(horaMax==null || !is_array(horaMax) || horaMax.length!=2){
		alert('Hora minuto no valido horaMax');
		hourMax.focus();
		return false;		
	}
	
	var daymin = new Date(fechaMin[2],fechaMin[1]-1, fechaMin[0],horaMin[0],horaMin[1],0);
	var daymax = new Date(fechaMax[2],fechaMax[1]-1, fechaMax[0],horaMax[0],horaMax[1],0);
	if (daymax >= daymin) {
		return true;

	} else {
		alert('la fecha ' + nameCtlMax  + ' (' + dateMax.value + ' ' + hourMax.value + ') ' 
				+ ' debe ser mayor o igual a ' + nameCtlMin + ' (' + dateMin.value + ' ' + hourMin.value + ') ');
		dateMax.focus();
		return false;
	}
	dateMax.focus();
	return false;
	
}


function compareToDate(ctlMin, ctlMax){
	
	if(ctlMin==null || ctlMax==null){
		alert('el campo debe ser de tipo fecha');
		return false;
	}
	
	 var dateMin = document.getElementById(ctlMin);
	 var dateMax = document.getElementById(ctlMax);
	
	if(dateMin==null || dateMin.value.trim().length!=10){
		alert('Fecha no v�lida dateMin2');
		dateMin.focus();
		return false;
	}
	
	if(dateMax==null || dateMax.value.trim().length!=10){
		alert('Fecha no v�lida dateMax2');
		dateMax.focus();
		return false;
	}
	
	var fechaMin = dateMin.value.trim().split("/");
	var fechaMax = dateMax.value.trim().split("/");

	if(fechaMin==null || !is_array(fechaMin) || fechaMin.length!=3){
		alert('Fecha no v�lida fechaMin1');
		dateMin.focus();
		return false;
	}
	if(fechaMax==null || !is_array(fechaMax) || fechaMax.length!=3){
		alert('Fecha no v�lida fechaMax1');
		dateMax.focus();
		return false;
	}
	
	var daymin = new Date();
	daymin.setFullYear(fechaMin[2],fechaMin[1]-1, fechaMin[0]);
	
	var daymax = new Date();
	daymax.setFullYear(fechaMax[2],fechaMax[1]-1, fechaMax[0]);

	if (daymax >= daymin) {
	    return true;
	} else {
		alert('la fecha ' + dateMax.value + ' debe ser mayor o igual a ' + dateMin.value);
	    return false;
	}
	
}

function limpiarControles(controls){
	if(controls!=null && controls!=''){
	   var arrControl= controls.split("|");
	   if(arrControl!=null && is_array(arrControl) && arrControl.length>0){
		   for (var i = 0; i < arrControl.length; i++) {
			   var ctl = document.getElementById(arrControl[i]);
			   if(ctl!=null && typeof(ctl)=='object' && ctl.type=='text'){
				   ctl.value = '';
			   }
			   if(ctl!=null && typeof(ctl)=='object' && ctl.type=='select-one'){
				   ctl.selectedIndex = 0;
			   }
		   }
	   }
	}
	
}


function nuevoAjax()
{ 

	var xmlhttp=false;
	try{
		xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");

	}catch(e){
		try{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

		}catch(E){
			if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
				xmlhttp=new XMLHttpRequest();

			}
		}
	}
	return xmlhttp; 
}  

/******							******/
function FAjax2(url,valores,method,param1,param2,param3)
{   	  
		 
          var ajax=nuevoAjax();
          ajax.open ('POST', url, true);  
      	  
          ajax.onreadystatechange = function() {
	          if (ajax.readyState==1) {
	   
	          }else{
	              if (ajax.readyState==4){
	                  if(ajax.status==200){

	               	   var valoresReturn = ajax.responseText;
	                      if(valoresReturn==null || valoresReturn.length==0){
	           				 alert('Servicio no disponible');
	           			    return;
	           			 }
	                    if(param3!=null && param3!=''){
	                    	window[method](valoresReturn,param1,param2,param3);
	                    }else{
	                    	window[method](valoresReturn,param1,param2);
	                    }  
	           		    
	                  }else{
	               	   if(ajax.status==404){
	                   	   alert('Pagina no disponible');
	                      }else{
	                   	   alert('Error pagina:' + ajax.status);
	                      }   
	                  }
	               }	        	  
	          }

         }
         ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=iso-8859-1');
         ajax.send(valores);
         return;
} 


/******							******/
function FAjax3(url,valores,method,param1,param2)
{   	  
		 
          var ajax=nuevoAjax();
          ajax.open ('POST', url, false);  
          ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=iso-8859-1');
          ajax.send(valores);
          var valoresReturn = ajax.responseText;
          return valoresReturn;
          
} 

function imposeMaxLength(Event, Object, MaxLen,tipo,method,redirect,objRedirect)
{

    if(((Object.value.length+1) <= MaxLen)||(Event.keyCode == 8 || Event.keyCode == 9 || Event.keyCode == 13 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))){
    	if(tipo==1){
    		return numberKey(Event);
    	}else if(tipo==2){
    		return alphanumeric(Event,Object);
    	}else if(tipo==3){
    		return alphanumericClave(Event,Object);
    	}else if(tipo==4){
    		return alphanumericNamefile(Event,Object);
    	}else if(tipo==5){
    		return alpha(Event,Object);
    	}else if(tipo==6){
    		return alphaWithoutSpace(Event,Object);
    	}else if(tipo==7){
    	  return hora(Event);
    	}else if(tipo==8){
    		return alphanumericEnter(Event,Object);
    	}else if(tipo==9){
    		return alphanumericCorreo(Event,Object);
    	}else if(tipo==10){
    		return numberTelefono(Event,Object);   //celular
    	}else if(tipo==11){
    		return numberMonto(Event,Object,method,redirect,objRedirect);  
    	}else if(tipo==12){
    		return numberKeyEnter(Event,Object,method,redirect,objRedirect);   //Numero con enter
    	}else if(tipo==13){
    		return dateEnter(Event,Object,method,redirect,objRedirect);   //fecha
    		
    	}else{
    		return true;
    	}
    	return false;
    }
    return false;
}
 
//Allow only alpha charecter a-z A-Z  Delete BackSpace Without Space

function alphaWithoutSpace(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
 
    if ((charCode > 64 && charCode < 91)  //letras mayuscula
    		|| (charCode > 96 && charCode < 123)  //letras minusculas
    		|| charCode == 127 //delete
    		|| charCode == 8 //backspace
    	) {
        return true;
    }
    else {
        return false;
    }
}
//Allow only alpha charecter a-z A-Z Space Delete BackSpace

function alpha(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    	
    if ((charCode > 64 && charCode < 91) //letras mayuscula
    		|| (charCode > 96 && charCode < 123) //letras minusculas
    		|| charCode == 127 //delete
    		|| charCode == 8 //backspace 
    		|| charCode == 32 //espacio en blanco
    	) {
        return true;
    }
    else {
        return false;
    }
}
 
//Allow only alpha and Numeric charecter a-z A-Z 0-9 Space Delete BackSpace
function alphanumeric(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    	   
    if (charCode > 64 && charCode < 91 //letras mayuscula
    		|| charCode > 96 && charCode < 123 //letras minusculas
    		|| charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8  //backspace 
    		|| charCode == 32 //espacio en blanco
    		|| charCode == 241 //�
    		|| charCode == 209 //�
    		) {
        return true;
    }
    else {
        return false;
    }
}


function alphanumericEnter(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
 	 
    if (charCode > 64 && charCode < 91 //letras mayuscula
    		|| charCode > 96 && charCode < 123 //letras minusculas
    		|| charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8  //backspace 
    		|| charCode == 32 //espacio en blanco
    		|| charCode == 241 //�
    		|| charCode == 209 //�
    		|| charCode == 13 //Enter
    		|| charCode == 46 //pnto
    		|| charCode == 95 //underline
    		
    		) {
        return true;
    }
    else {
        return false;
    }
}


function alphanumericCorreo(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
 	 
    if (charCode > 64 && charCode < 91 //letras mayuscula
    		|| charCode > 96 && charCode < 123 //letras minusculas
    		|| charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8  //backspace 
    		|| charCode == 32 //espacio en blanco
    		|| charCode == 241 //�
    		|| charCode == 209 //�
    		|| charCode == 46 //pnto
    		|| charCode == 95 //underline
    		|| charCode == 64 //	@
    		|| charCode == 36 //	$
    		//|| charCode == 59 //	;
    		|| charCode == 45 //	-
    		|| charCode == 44 //	,
    		) {
        return true;
    }
    else {
        return false;
    }
}




//To Clave
function alphanumericClave(evt,Object) {
	
/*
    var charCode = (evt.which) ? evt.which : event.keyCode
    
    if (charCode > 64 && charCode < 91 //letras mayuscula
    		|| charCode > 96 && charCode < 123  //letras minusculas
    		|| charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8  //backspace
    		|| charCode == 64 //	@
    		|| charCode == 36 //	$
    		|| charCode == 45 //	-
    		|| charCode == 46 //pnto
    		|| charCode == 95 //underline
    		|| charCode == 42	//*
    		|| charCode == 35	//#    		
    		
    ) {
        return true;
    }
    else {
        return false;
    }*/
	return true;
}
//To Namefile
function alphanumericNamefile(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode

    if (charCode > 64 && charCode < 91 //letras mayuscula
    		|| charCode > 96 && charCode < 123 //letras minusculas
    		|| charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8 	//backspace
    		|| charCode == 95  //linea abajo
    ) {
        return true;
    }
    else {
        return false;
    }
}
//Allow only numeric charecters 0-9 and  backspace delete

function numberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8	//backspace
    	) {
        return true;
    }
    else {
        return false;
    }
}


function dateEnter(evt,Object,method,redirect,objRedirect) {
    var charCode = (evt.which) ? evt.which : event.keyCode
		
    if (charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8	//backspace
    		|| charCode == 47	//
    	) {
        return true;
    }
    else {
    	
    	if (charCode == 13 //Enter
    	){
    		if(redirect){
    			var oRedirect = document.getElementById(objRedirect);
    			if(oRedirect!=null){
    				oRedirect.focus();
    			}
    		}
    		if(method!=null && method!=''){
        		if (typeof (window[method]) == 'function') {
        			window[method](Object);
        			return false;
        		}
    		}
    	}
        return false;
    }
}

function numberKeyEnter(evt,Object,method,redirect,objRedirect) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8	//backspace
    	) {
        return true;
    }
    else {

    	if (charCode == 13 //Enter
    		
    	){
    		if(redirect){
    			var oRedirect = document.getElementById(objRedirect);
    			if(oRedirect!=null){
    				oRedirect.focus();
    			}
    		}
    		if(method!=null && method!=''){
        		if (typeof (window[method]) == 'function') {
        			window[method](Object);
        			return false;
        		}
    		}
    	}
        return false;
    }
}

function numberMonto(evt,Object,method,redirect,objRedirect) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8	//backspace
    		|| charCode == 46 //pnto
    	) {
        return true;
    }
    else {
    	if (charCode == 13 //Enter
    	){
    		if(redirect){
    			var oRedirect = document.getElementById(objRedirect);
    			if(oRedirect!=null){
    				oRedirect.focus();
    			}
    		}
    		if(method!=null && method!=''){
        		if (typeof (window[method]) == 'function') {
        			window[method]();
        			return false;
        		}
    		}
    	}
        return false;
    }
}

function numberTelefono(evt,Object) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 47 && charCode < 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8	//backspace
    		|| charCode == 42	//*
    		|| charCode == 35	//#
    		|| charCode == 45	//-
    	) {
        return true;
    }
    else {
        return false;
    }
}


function hora(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 47 && charCode <= 58 //numeros
    		|| charCode == 127 //delete
    		|| charCode == 8  //backspace
    		|| charCode == 58  //dos puntos
    ) {
        return true;
    }
    else {
        return false;
    }
}

function validateFile() {
    var fileName = document.getElementById("CVS").value
    if (fileName == "") {
        alert("Subir un archivo Valido con extension .CSV");
        return false;
    }
    else if (fileName.split(".")[1].toUpperCase() == "CSV")
        return true;
    else {
        alert("Archivo Invalido , Favor ingresar archivo CSV ");
        return false;
    }
    return true;
}


function validateArrForm(arrayVal){	
 
	try {
		for	(var index = 0; index < arrayVal.length; index++) {	
			
			if(arrayVal[index][0]==null || arrayVal[index][0] == '') return false;
			var obj1 = document.getElementById(arrayVal[index][0]);
			
			if(obj1==null || typeof(obj1)!='object') continue;
			
			if(obj1.value.trim()==''){
				alert(arrayVal[index][1]);
				document.getElementById(arrayVal[index][0]).focus();
				return false;
			}
			if(arrayVal[index][3]!='-1'){
				if(document.getElementById(arrayVal[index][0]).value.length>arrayVal[index][3]){
					alert('La longitud del campo es mayor a ' + arrayVal[index][3] + ':');
					document.getElementById(arrayVal[index][0]).focus();
					return false;
				}
			}
			if(arrayVal[index][2]!=''){
				var pattern  = getPattern(arrayVal[index][2]);
				var valor = document.getElementById(arrayVal[index][0]).value;

				if(pattern!=null && valor!=null && pattern!='' && valor!=''){
					//verificando si es una lista o un elemento
					if(arrayVal[index].length <=4){
						var isValid = pattern.test(valor);
						if(!isValid){
							alert('Formato Incorrecto:' + valor);
							document.getElementById(arrayVal[index][0]).focus();
							return false;
						}
						if(arrayVal[index][2]=='MONTO2'){
							var numero = getStringToNumber(valor,'10','2');
							if(numero<=0){
								alert('El campo debe ser mayor a 0');
								document.getElementById(arrayVal[index][0]).focus();
								return false;
							}
						}
						
					}else{
						var charVal = arrayVal[index][5];
						var listaSplit = valor.split(charVal);
						if(!(listaSplit!=null && is_array(listaSplit) && listaSplit.length>0)){
							alert('Lista no disponible');
							return false;
						}

						for (var i = 0; i < listaSplit.length; i++) {
							var item1 = listaSplit[i];
							if(item1!=null & item1.trim()!=''){
								item1 = item1.trim();
								var isValid = pattern.test(item1);
								if(!isValid){
									alert('Formato Incorrecto:' + item1);
									document.getElementById(arrayVal[index][0]).focus();
									return false;
								}						
							}
							
						}
					}
				}
			}
		}
		return true;	
	} catch (e) {
		alert(e);
		return false;
	}
	
	
}

function getStringToNumber(numero,parteEntera,parteDecimal){

	var num = parseFloat(numero,parteEntera).toFixed(parteDecimal);
	return num;
}


function getPattern(formato){
	
	if(formato=='CORREO'){
		return /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/; // /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	}
	if(formato=='HORA'){
		return /^(0[1-9]|1\d|2[0-3]):([0-5]\d):([0-5]\d)$/;
	}if(formato=='HORA2'){
		return /^(0[1-9]|1\d|2[0-3]):([0-5]\d)$/;
	}if(formato=='MONTO2'){
		return /^\d+\.\d{0,2}$/;
	}
	return '';
}
	
//Validacion de los radioBttns
function ValidarRadio(optSeleccion){
	
	//obteniendo el objeto radioBttns.
	var radioBttn = document.getElementsByName(optSeleccion);
 
	if (radioBttn == null){
		alert("No existe elementos en la grilla");
		return false;
	}

	var cadena = "" + radioBttn.length;
	if (cadena == "undefined"){
	   
		//existe un solo elemento
		if (radioBttn.checked==true){
			return true;
		}else{	
		alert("Seleccione una sola opcion");		
		return false;}
	}
	else{
		//Esta en una coleccion
		if (radioBttn.length == 0){
			alert("No existe elementos en la grilla");
			return false;}
		var icont = 0;	

		//verificando que por lo menos exiete un radioBttn habilitado
		for (i=0;i<radioBttn.length;i++)
			if (radioBttn[i].checked==true) 
					icont++;
		if(icont == 1){
			return true;
		}

		if(icont == 0)
			alert("Seleccione un elemento de la grilla");
		else
			alert("Seleccione una sola opcion");
	} 
	return false;
} 


function is_array(input){
	return typeof(input)=='object'&&(input instanceof Array);
}


function selectedCodCombo(objeto,textToFind){
	try {
		var dd = document.getElementById(objeto);
		if(dd!=null){
			for (var i = 0; i < dd.options.length; i++) {
			    if (dd.options[i].value == textToFind) {
			        dd.selectedIndex = i;
			        break;
			    }
			}
		}
	} catch (e) {
		alert(e);
	}
}


function selectedCodComboDis(objeto,textToFind){
	try {
		var dd = document.getElementById(objeto);
		if(dd!=null){
			for (var i = 0; i < dd.options.length; i++) {
				dd.options[i].disabled = true;
			    if (dd.options[i].value == textToFind) {
			        dd.selectedIndex = i;
			        dd.options[i].disabled = false;
			    }
			}
		}
	} catch (e) {
		alert(e);
	}
}

function selectedCombo(objeto,textToFind){
	try {
		var dd = document.getElementById(objeto);
		if(dd!=null){
			for (var i = 0; i < dd.options.length; i++) {
			    if (dd.options[i].text == textToFind) {
			        dd.selectedIndex = i;
			        break;
			    }
			}
		}		
	} catch (e) {
		alert(e);
	}
}


function cargaComboCodMultiple(cadena,param1,autoSel){
	
	var infoCombo=cadena+'';
	try{
		var selectDestino=document.getElementById(param1);
		selectDestino.parentNode.innerHTML=infoCombo;
		selectedCodCombo(param1,autoSel);
	}catch(e){
		alert(e);
	}
	
}

function eliminarComboMultipleGrupo(param1,disabled){
	try{
		var selectDestino=document.getElementById(param1);
		while (selectDestino.firstChild) {
			selectDestino.removeChild(selectDestino.firstChild);
		}

		selectDestino.length = 0;
		
		if(disabled!=null && disabled=='T'){
			var combo = document.getElementById(param1);
			combo.disabled = true;
		}
	}catch(e){
		alert(e);
	}
}

function eliminarComboMultipleValue(param1,disabled){
	try{
		var selectDestino=document.getElementById(param1);
		selectDestino.length = 0;
		if(disabled!=null && disabled=='T'){
			var combo = document.getElementById(param1);
			combo.disabled = true;
		}
	}catch(e){
		alert(e);
	}
}

function cargaComboMultipleValueDis(cadena,param1,param2,param3){

	var infoCombo=cadena+'';
	try{
		var selectDestino=document.getElementById(param1);
		selectDestino.parentNode.innerHTML=infoCombo;
		if(param2!=null && param2=='T'){
			var combo = document.getElementById(param1);
			combo.disabled = true;
		}
		if(param3!=null && param3!=''){
			var combo = document.getElementById(param1);
			if(combo.length>1){
				combo.selectedIndex = param3;
			}
		}
	}catch(e){
		alert(e);
	}
	
}

function cargaComboMultipleValueExec(cadena,param1,param2,param3){

	var infoCombo=cadena+'';
	try{
		var selectDestino=document.getElementById(param1);
		selectDestino.parentNode.innerHTML=infoCombo;
		if(param2!=null && param2!='' && param3!=null && param3!=''){
			window[param2](param3);
		}
	}catch(e){
		alert(e);
	}
	
}

function cargaComboMultipleValue(cadena,param1,disabled){

	var infoCombo=cadena+'';
	try{
		var selectDestino=document.getElementById(param1);
		selectDestino.parentNode.innerHTML=infoCombo;
		if(disabled!=null && disabled=='T'){
			var combo = document.getElementById(param1);
			combo.disabled = true;
		}
	}catch(e){
		alert(e);
	}
	
}

function buscarEnArray(array, dato){
	var x=0;
	while(x<array.length){
		if(array[x]==dato) return x;
		x++;
	}
	return null;
}


function compress(data) {
    data = data.replace(/([^&=]+=)([^&]*)(.*?)&\1([^&]*)/g, "$1$2,$4$3");
    return /([^&=]+=).*?&\1/.test(data) ? compress(data) : data;
}


function getValueRadio(optSeleccion){
	
	var radioBttn = document.getElementsByName(optSeleccion);
	if (radioBttn == null){
		return '';
	}

	var cadena = "" + radioBttn.length;
	if (cadena == "undefined"){
		if (radioBttn.checked==true){
			return radioBttn.value;
		}else{	
		return '';
		}
	}
	else{
		//Esta en una coleccion
		if (radioBttn.length == 0){
			return '';
		}
		for (var i=0;i<radioBttn.length;i++){
			if (radioBttn[i].checked==true){
				return radioBttn[i].value;
			} 
		}
	} 
	return '';
} 


function getEtiquetaRadio(optSeleccion,etiqueta){
	
	var radioBttn = document.getElementsByName(optSeleccion);
	if (radioBttn == null){
		return '';
	}

	var cadena = "" + radioBttn.length;
	if (cadena == "undefined"){
		if (radioBttn.checked==true){
			var datos = radioBttn.getAttribute(etiqueta);
			return datos;
		}else{	
		return '';
		}
	}
	else{
		//Esta en una coleccion
		if (radioBttn.length == 0){
			return '';
		}
		for (var i=0;i<radioBttn.length;i++){
			if (radioBttn[i].checked==true){
				var datos = radioBttn[i].getAttribute(etiqueta);
				return datos;
			} 
		}
	} 
	return '';
} 


function mostrarMotivoEliminacion(combo){
	
	try {
		
		var f01Observa = document.getElementById('f01Observa');
		var ctl = combo.options[combo.selectedIndex];
		if(ctl.value == 'SEEL'){
			if(f01Observa!=null) {f01Observa.readOnly = false;f01Observa.disabled = false;};
		}else{
			if(f01Observa!=null) {f01Observa.readOnly = true;f01Observa.disabled = true;f01Observa.value='';}
		}
	} catch (e) {
		alert(e);
	}
}

function disabledControles(controls,estado,borrar){
	if(controls!=null && controls!=''){
	   var arrControl= controls.split("|");
	   if(arrControl!=null && is_array(arrControl) && arrControl.length>0){
		   for (var i = 0; i < arrControl.length; i++) {
			   var ctl = document.getElementById(arrControl[i]);
			   if(ctl!=null && typeof(ctl)=='object' && ctl.type=='text'){
				   ctl.disabled = estado;
				   var nameCtl = ctl.id.toLowerCase().trim();
				   if(nameCtl!=null && nameCtl!=''){
					   var n = nameCtl.search("fecha");
					   if(n<=0){
						   ctl.readOnly = estado;    
					   }
				   }else{
					   ctl.readOnly = estado; 
				   }
				   
				   
				   if(borrar){
					   ctl.value = '';   
				   }
			   }
			   if(ctl!=null && typeof(ctl)=='object' && ctl.type=='select-one'){
				   ctl.disabled = estado;
				   ctl.readOnly = estado;
				   if(borrar){
					   ctl.selectedIndex = 0; 
				   }
			   }
			   	if(ctl!=null && typeof(ctl)=='object' && ctl.type=='button'){
					   ctl.disabled = estado;
			   }
			   	if(ctl!=null && typeof(ctl)=='object' && ctl.tagName.toLowerCase() =='textarea'){
					ctl.disabled = estado;
					ctl.readOnly = estado;
					if(borrar){
						   ctl.value = '';   
					   }
			   }
		   }
	   }
	}
}


function changeCheckedControl(objeto,div){
	
	var divElem =document.getElementById(div);
	if(objeto!=null &&  divElem!=null){
		if (objeto.checked==true){
			divElem.style.display = "block";
		}else{
			divElem.style.display = "none";
		}
	}
	
}


function ajustar(num,tam) {
	if (num.toString().length < tam) 
		return ajustar("0" + num,tam);
	else return num;
}


function ImprimirDoc(marco){
	
	var pantalla = document.getElementById(marco);
	if(!(pantalla!=null && typeof(pantalla)=='object')){
		alert('No se puede imprimir la pantalla');
		return;
	}
	//Ocultar todos los objetos
	var objeto = document.getElementsByTagName('TABLE');
	for(i = 0; i < objeto.length; i++){
       objeto[i].style.visibility = 'hidden';
   }
	 
   pantalla.style.position='absolute';
   pantalla.style.visibility = 'visible';
   pantalla.style.top = '0px' ;
   pantalla.style.left = '0px' ;
   
   var hijos = pantalla.getElementsByTagName('TABLE');
	for(i = 0; i < hijos.length; i++){
       hijos[i].style.visibility = 'visible';
   }

	window.print();
   
   //Volver al estado Normal
	var objeto = document.getElementsByTagName('TABLE');
	for(i = 0; i < objeto.length; i++){
       objeto[i].style.visibility = 'visible';
   }
   pantalla.style.position='static';
} 


function printContent(div_id)
{
var DocumentContainer = document.getElementById(div_id);

var link1 = '<link rel="stylesheet" href="assets/css/fontTableroPic1.css" type="text/css"></link>';
var link2 = '<link rel="stylesheet" href="assets/css/fontTableroPic2.css" type="text/css"></link>';
var link3 = '<link rel="stylesheet" href="assets/css/stylesTable.css" type="text/css"></link>';
var link4 = '<link rel="stylesheet" href="assets/css/styles.css" type="text/css" />';
var link5 = '<link rel="stylesheet" href="assets/css/displaytag.css" type="text/css"></link>';

var html = '<html><head>'+
				link1+link2+link3+link4+link5+
               '</head><body style="background:#ffffff;">'+
               DocumentContainer.innerHTML+
               '</body></html>';
 
    var WindowObject = window.open("", "PrintWindow","width=1000,height=600,top=0,left=0,toolbar=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes");
    WindowObject.document.writeln(html);
    WindowObject.document.close();
    WindowObject.focus();
   // WindowObject.print();
    //WindowObject.close();

}



///////////////////////////////////////////////////////////////////


/*   FUNCTION TABLERO */
/* ******************** */


function cargaContenidoInfoGrupo(object,divPersonalizar){
	
	if(object!=null && typeof(object)=='object'  && object.type=='select-one'){
		var ctl = object.options[object.selectedIndex];
	   var f06CodGrupoText = document.getElementById('f06CodGrupoText');
		if(f06CodGrupoText!=null && typeof(f06CodGrupoText)=='object'){
			f06CodGrupoText.value =  ajustar(ctl.value,4);
	   }
		selectedCodCombo('f06GrupoSelect',ctl.infoMacroRegion);
		if(divPersonalizar!=''){
			iniciarControlDiv('divInfoContenidoGestionComercial');
			paintContenidoInfoAgencia(f01CodGrupoText.value);
		} 
	}
	
}


function cargaContenidoInfoAgencia(object,divPersonalizar){
	
	if(object!=null && typeof(object)=='object'  && object.type=='select-one'){
		var ctl = object.options[object.selectedIndex];
	   var f01CodOficinaText = document.getElementById('f01CodOficinaText');
		if(f01CodOficinaText!=null && typeof(f01CodOficinaText)=='object'){
			f01CodOficinaText.value =  ajustar(ctl.value,4);
	   }
		selectedCodCombo('f01MacroRegionSelect',ctl.infoMacroRegion);
		if(divPersonalizar!=''){
			iniciarControlDiv('divInfoContenidoGestionComercial');
			paintContenidoInfoAgencia(f01CodOficinaText.value);
		} 
	}
	
}

function paintContenidoInfoAgencia(f01CodOficina){
	
	var methodCallBack = 'getInfoPaintDivMain';
	var objeto = 'divcontenidoInfoAgencia';
	var data = "f01CodOficina="+f01CodOficina; 
	FAjax2("gcmGestionComercialBusquedaContenidoAgenciaAjax",data,methodCallBack,objeto,'','');	
	
}


function cargaContenidoRegion(combo,cbodependiente,opcion){
	
	try {
		iniciarControlDiv('divInfoContenidoGestionComercial');
		limpiarControles('f01CodOficinaText|f01CodOficinaSelect');
		eliminarComboMultipleGrupo(cbodependiente,'F');
		var ctl = combo.options[combo.selectedIndex];
		var data = 'f01MacroRegion='+ctl.value+'&opcion='+opcion;
		FAjax2('comComunBusquedaAgenciasAjax',data,'cargaComboMultipleValue',cbodependiente,'','');
	} catch (e) {
		alert(e);
	}
	
}

function cargaContenidoGrupo(combo,cbodependiente,opcion){
	
	try {
		iniciarControlDiv('divInfoContenidoGestionComercial');
		limpiarControles('f01CodItemText|f01CodItemSelect');
		eliminarComboMultipleGrupo(cbodependiente,'F');
		var ctl = combo.options[combo.selectedIndex];
		var data = 'f06Grupo='+ctl.value+'&opcion='+opcion;
		FAjax2('comComunBusquedaItemAjax',data,'cargaComboMultipleValue',cbodependiente,'','');
	} catch (e) {
		alert(e);
	}
	
}

function execEnterControlAgenciaMetasLoad(Object){
	Object.value =ajustar(Object.value,4);
	execGetInfoMacroRegion(Object.value,'A');
	execGetInfoAgenciaSelect(Object.value,'A');

}

function execEnterControlAgenciaTableroLoad(Object){
	
	Object.value =ajustar(Object.value,4);
	iniciarControlDiv('divInfoContenidoGestionComercial');
	execGetInfoMacroRegion(Object.value,'T');
	execGetInfoAgenciaSelect(Object.value,'T');
	paintContenidoInfoAgencia(Object.value);
	
}

function execGetInfoMacroRegion(f01CodOficina,opcion){

	var methodCallBack = 'getInfoPaintDivMain';
	var objeto = 'divListaMacroRegionSelect';
	var data = "f01CodOficina="+f01CodOficina+'&opcion='+opcion; 
	FAjax2("comComunBusquedaMacroRegionAjax",data,methodCallBack,objeto,'','');	
	
}

function execGetInfoAgenciaSelect(f01CodOficina,opcion){

	var methodCallBack = 'getInfoPaintDivMain';
	var objeto = 'divListaAgenciasSelect';
	var data = "f01CodOficina="+f01CodOficina+'&opcion='+opcion;
	FAjax2("comComunBusquedaAgenciasAjax",data,methodCallBack,objeto,'','');	
	
}

function getInfoPaintDivMain(cadena,param1,autoSel){
	
	var infoDiv=cadena+'';
	try{
		var divDestino=document.getElementById(param1);
		divDestino.innerHTML=infoDiv;
		
	}catch(e){
		alert(e);
	}
}

//Formulario Item

function valFrmConfigItem(){

	var f06GrupoSelect = document.getElementById('f06GrupoSelect');
	var f06CodItemText = document.getElementById('f06CodItemText');
	
	if(f06GrupoSelect!=null && typeof(f06GrupoSelect)=='object' && 
			f06ItemText!=null && typeof(f06ItemText)=='object'){
		
		if(f06GrupoSelect.value=='' && f06CodItemText.value==''){
			alert('Seleccione Item o Grupo');
			return false;
		}
		if(f06CodItemText.value!=''){
			var arrItems = [
			                ['f06CodItemText','Ingrese Item','','15']
			        	];
			
			if (!validateArrForm(arrItems)) return false;
		}
		return true;
	}
	return false;
	
}



//----------------------------------------
//Formulario Agencia
//----------------------------------------

function execEnterControlComboAgenciaLoad(Object){
	Object.value =ajustar(Object.value,4);
	execGetInfoMacroRegion(Object.value,'A');
	execGetInfoAgenciaSelect(Object.value,'A');

}

function valFrmConfigAgencia(){

	var f01MacroRegionSelect = document.getElementById('f01MacroRegionSelect');
	var f01CodOficinaText = document.getElementById('f01CodOficinaText');
	
	if(f01MacroRegionSelect!=null && typeof(f01MacroRegionSelect)=='object' && 
			f01CodOficinaText!=null && typeof(f01CodOficinaText)=='object'){
		
		if(f01MacroRegionSelect.value=='' && f01CodOficinaText.value==''){
			alert('Seleccione la Agencia o Macro Regi�n');
			return false;
		}
		if(f01CodOficinaText.value!=''){
			var arrItems = [
			                ['f01CodOficinaText','Ingrese la agencia','','4']
			        	];
			
			if (!validateArrForm(arrItems)) return false;
		}
		return true;
	}
	return false;
	
}

function buscarConfigAgencia(form,action,opcion,navegar){
	
	try {
		
		var opcion = document.getElementById('opcion');
		if(opcion==null) return false;
		opcion.value = opcion;
		
		var f01CodOficina = document.getElementById('f01CodOficina');
		var f01CodOficinaText = document.getElementById('f01CodOficinaText');
		f01CodOficina.value = f01CodOficinaText.value;

		var f01MacroRegion = document.getElementById('f01MacroRegion');
		var f01MacroRegionSelect = document.getElementById('f01MacroRegionSelect');
		f01MacroRegion.value = f01MacroRegionSelect.value;
		
		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
}

function buscarConfigGrupo(form,action,opcion,navegar){
	
	try {
		
		var opcion = document.getElementById('opcion');
		if(opcion==null) return false;
		opcion.value = opcion;
		
		var f06CodItem = document.getElementById('f06CodItem');
		var f06CodItemText = document.getElementById('f06CodItemText');
		f06CodItem.value = f06CodItemText.value;

		var f06Grupo = document.getElementById('f06Grupo');
		var f06GrupoSelect = document.getElementById('f06GrupoSelect');
		f06Grupo.value = f06GrupoSelect.value;
		
		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
}


function valFrmUploadFileAgencia(){
	
	var ctlDiv=document.getElementById('f02ContenidoFileAgencia');
	if(ctlDiv!=null && typeof(ctlDiv)=='object'){
		if(ctlDiv.value == ''){
			alert('Seleccione documento');
			ctlDiv.focus();
			return false;
		}
		return true;
	}
	return false;
	
}


//----------------------------------------
// Formulario Metas
//----------------------------------------

function buscarMetaAgencia(form,action,opcion,navegar){
	
	try {
		
		var opcion = document.getElementById('opcion');
		if(opcion==null) return false;
		opcion.value = opcion;
		
		var f01CodOficina = document.getElementById('f01CodOficina');
		var f01CodOficinaText = document.getElementById('f01CodOficinaText');
		f01CodOficina.value = f01CodOficinaText.value;

		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
}

function valFrmMetaAgencia(){

	var arrItems = [
	                ['f01CodOficinaText','Ingrese la agencia','','4'],
	                ['f01FeEvalEstadisticAnio','Ingrese el a�o a consultar','','4']
	        	];
	
	if (!validateArrForm(arrItems)) return false;
	return true;
	
}

function valFrmUploadFileMeta(){
	
	var ctlDiv=document.getElementById('f02ContenidoFileMeta');
	if(ctlDiv!=null && typeof(ctlDiv)=='object'){
		if(ctlDiv.value == ''){
			alert('Seleccione documento');
			ctlDiv.focus();
			return false;
		}
		return true;
	}
	return false;
	
}

function valFrmUploadFileCargaInicial(){
	
	var ctlDiv=document.getElementById('f12ContenidoFileCodigo');
	if(ctlDiv!=null && typeof(ctlDiv)=='object'){
		if(ctlDiv.value == ''){
			alert('Seleccione documento');
			ctlDiv.focus();
			return false;
		}
		return true;
	}
	return false;
	
}


//Carga de codigo de agencia

function valFrmUploadFileCodigo(){
	
	var ctlDiv=document.getElementById('f11ContenidoFileCodigo');
	if(ctlDiv!=null && typeof(ctlDiv)=='object'){
		if(ctlDiv.value == ''){
			alert('Seleccione documento');
			ctlDiv.focus();
			return false;
		}
		return true;
	}
	return false;
	
}

//----------------------------------------
//Formulario Estadistico Main
//----------------------------------------

function valFrmEstadisticoTabConsulta(){
	
	return true;
}

function buscarEstadisticoMain(form,action,option,navegar){
	
	try {
		
		var optionAmbito = document.getElementById('optionAmbito');
		if(optionAmbito==null) return false;
		optionAmbito.value = option;
		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
}

//----------------------------------------
//Formulario Estadistico Macro Region
//----------------------------------------

function valFrmEstadisticoMacro(){
	
	var arrItems = [
	                ['f01MacroRegionSelect','Seleccione Macro Region','','-1'],
	        		['f01FeEvalEstadisticMes','Ingrese el mes a consultar','','-1'],
	        		['f01FeEvalEstadisticAnio','Ingrese el a�o a consultar','','4']
	        	];
	
	if (!validateArrForm(arrItems)) return false;
	return true;
	
}

function buscarEstadisticoMacro(form,action,option,navegar){
	
	try {
		
		var optionEstadistico = document.getElementById('optionEstadistico');
		if(optionEstadistico==null) return false;
		optionEstadistico.value = option;
		
		var f01MacroRegion = document.getElementById('f01MacroRegion');
		if(f01MacroRegion==null) return false;
		var f01MacroRegionSelect = document.getElementById('f01MacroRegionSelect');
		if(f01MacroRegionSelect==null) return false;
		f01MacroRegion.value = f01MacroRegionSelect.value;
		submitsAction2(form,action,navegar,true);		
	} catch (e) {
		alert(e);
	}
}

//----------------------------------------
//Formulario Estadistico Agencia
//----------------------------------------

function valFrmEstadisticoAgencia(){
	
	var arrItems = [
	                ['f01CodOficinaText','Ingrese C�digo de la Agencia','','4'],
	        		['f01FeEvalEstadisticMes','Ingrese el mes a consultar','','-1'],
	        		['f01FeEvalEstadisticAnio','Ingrese el a�o a consultar','','4']
	        	];
	
	if (!validateArrForm(arrItems)) return false;
	return true;
	
}

function buscarEstadisticoAgencia(form,action,option,navegar){
	
	try {
		
		var optionEstadistico = document.getElementById('optionEstadistico');
		if(optionEstadistico==null) return false;
		optionEstadistico.value = option;
		
		var f01CodOficina = document.getElementById('f01CodOficina');
		if(f01CodOficina==null) return false;
		var f01CodOficinaText = document.getElementById('f01CodOficinaText');
		if(f01CodOficinaText==null) return false;
		f01CodOficina.value = f01CodOficinaText.value;
		
		var f01MacroRegion = document.getElementById('f01MacroRegion');
		if(f01MacroRegion==null) return false;
		var f01MacroRegionSelect = document.getElementById('f01MacroRegionSelect');
		if(f01MacroRegionSelect==null) return false;
		f01MacroRegion.value = f01MacroRegionSelect.value;
		
		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
}

function execEnterCerrarVentanaPopop(){
	var evt = (evt) ? evt : ((event) ? event : null);
	var node = (evt.target) ? evt.target
			: ((evt.srcElement) ? evt.srcElement : null);
	
	if (evt.keyCode >=0){
		gfPop.fHideCal();
	}
	return true;
}

function iniciarControlDiv(param1){
	
	var ctlDiv=document.getElementById(param1);
	if(ctlDiv!=null && typeof(ctlDiv)=='object'){
		ctlDiv.innerHTML='';
	}
	
}


//Configuraciones para ITEMs

function cargaContenidoGrupo(combo){
	
	try {

		var ctl = combo.options[combo.selectedIndex];
		var data = 'f05Grupo='+ctl.value;
		var methodCallBack = 'getInfoPaintDivMain';
		var objeto = 'divcontenidoInfoItem';
		FAjax2("admAdministracionBusquedaItemAjax",data,methodCallBack,objeto,'','');	
		
	} catch (e) {
		alert(e);
	}
	
}

function modificarItems(form,action,opcion,navegar){
	
	try {
		
		var crt = document.getElementById('opcion2');
		if(crt==null) return false;
		crt.value = opcion;
		
		submitsAction2(form,action,navegar,true);
		
	} catch (e) {
		alert(e);
	}
	
}


function valFrmItems(){

	return true;
	
}


