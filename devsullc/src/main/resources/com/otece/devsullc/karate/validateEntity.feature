Feature: Cliente Service Testing

Background:
  * url "http://cliente:8087/clientes"
  * header Content-Type = "application/json"
  
  
Scenario: Crear un nuevo cliente
  Given request 
  """
  {
    "contraseña": "54698",
    "estado": "Activo",
    "nombre": "Andres",
    "genero": "Masculino",
    "edad": "25",
    "identificacion": "52278656",
    "direccion": "13 junio y Equinoccial",
    "telefono": "098874587"
  }
  """
  When method POST
  Then status 201
  And match response.nombre == "Andres"
  And match response.contraseña == "54698"
  And match response.direccion == "13 junio y Equinoccial"
  And match response.telefono == "098874587"
  And match response.estado == "Activo"

Scenario: Obtener detalles de un cliente con id especifico
  * def clientId = "52278656"
  Given path clientId
  When method GET
  Then status 200
  And match response.nombre == "Andres"
  And match response.contraseña == "54698"
  And match response.direccion == "13 junio y Equinoccial"
  And match response.telefono == "098874587"
  And match response.estado == "Activo"

Scenario: Actualizar detalles de un cliente existente
  * def id = "52278656"
  Given path id
  And request 
  """
  {
    "contraseña": "nuevaContraseña",
    "estado": "Inactivo",
    "nombre": "Andres Modificado",
    "genero": "Masculino",
    "edad": "26",
    "direccion": "Nueva Dirección 456",
    "telefono": "0999887766"
  }
  """
  When method PUT
  Then status 200
  And match response.nombre == "Andres Modificado"
  And match response.contraseña == "nuevaContraseña"
  And match response.direccion == "Nueva Dirección 456"
  And match response.telefono == "0999887766"
  And match response.estado == "Inactivo"