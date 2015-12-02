<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta Persona</title>
<sx:head />
</head>
<body>
	<s:form action="realizarAlta">
		<s:push value="#session.persona">
			<s:hidden name="id" value="%{id}"/>
			<s:label value="Ingrese Persona"></s:label>
			<s:textfield label="Nombre" name="nombre"></s:textfield>
			<s:textfield label="Apellido" name="apellido"></s:textfield>
			<sx:datetimepicker name="fechaDate" label="Fecha nacimiento"
				displayFormat="dd-MMM-yyyy" value="todayDate" />
			<s:submit value="Guardar"></s:submit>
		</s:push>
	</s:form>
--------------------------------------------------------------------<br>
	<s:label value="Lista de Personas"/>
	<s:form action="realizarAlta">
		<s:select headerKey="-1" headerValue="Seleccione" list="#session.personas"  listValue="nombre" listKey="id" name="idFilter" label="seleccione responsable" />
		<s:submit name="submit" value="Filtrar"/>
	</s:form>

	<ol>
		<s:iterator value="#session.personasFiltradas" >
			<li>
				<s:property value="nombre" />, 
				<s:property value="apellido" />,
				<s:property value="fechaDate" />,
				
				<s:if test="responsable==null">
					<s:label value="sin responsable"/>
				</s:if><s:else>
					<s:property value="responsable.nombre" />
				</s:else>
				<s:url id="editURL" action="editarPersona">
					<s:param name="idPerEdit" value="%{id}" />
				</s:url>
				<s:a href="%{editURL}">Editar</s:a>
				<s:url id="deleteURL" action="borrarPersona">
					<s:param name="idPerRemove" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteURL}">Eliminar</s:a>
			</li>
		</s:iterator>
	</ol>

--------------------------------------------------------------------<br>
		<s:label value="Asignar responsable a una persona:"></s:label>	
		<s:form action="realizarAlta">
			<s:select headerKey="-1" headerValue="Seleccione" label="seleccione persona" list="#session.perSinResponsables" name="idPersona" 
				 listKey="id"  listValue="nombre" value="opcionVacia" />
			<s:select headerKey="-1" headerValue="Seleccione" label="seleccione Su responsable" list="#session.personas" name="idResponsable" 
				 listKey="id"  listValue="nombre" value="opcionVacia" />
			<s:submit name="submit" value="Guardar"></s:submit> 
		</s:form>
---------------------------------------------------------------------		
		<s:form action="realizarAlta">
			<s:label value="Ingrese Contacto"></s:label>
			<s:textfield label="Descripción" name="descripcion"></s:textfield>
			<s:checkbox label="Disponible" type="checkbox" name="disponible" />
			<s:select headerKey="-1" headerValue="Seleccione" label="seleccione a la persona que contiene este contacto" list="#session.personas" name="idPersonaContacto" 
				 listKey="id"  listValue="nombre" value="opcionVasia" />
			<s:submit value="Gardar"></s:submit>
		</s:form>
			<s:label value="Contactos:"></s:label>
		<ol>
			<s:iterator value="#session.contactos">
				<li>
					<s:property value="persona.nombre" />,
					<s:property value="descripcion" />, 
					<s:property value="disponible" />
				</li>
			</s:iterator>
		</ol>
</body>
</html>