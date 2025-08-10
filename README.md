# PROYECTO SOAP_v2

## Detalle
En este proyecto trabajamos con SOAP para la comunicación entre nuestra aplicacion y una externa (http://www.dataaccess.com/webservicesserver/).
Esta aplicación externa es una Web que publica gratis llamadas SOAP para developers.

## Que diferencia a este proyecto SOAP_v2 con el SOAP?
En este proyecto vamos a contar con el wsdl y utilizaremos el generador de codigo cxf para los servicios.

## Pasos

1. Descargamos el wsdl: para esto ejecutamos el siguiente curl desde una consola bash en la carpeta destino, en nuestro caso C:\Users\mjhernando\Documents\proyectos\_propios\soap_v2\src\main\resources\wsdl
````CURL
curl -k -o soap.wsdl https://www.dataaccess.com/webservicesserver/NumberConversion.wso?wsdl
````
NumberConversion.wsdl: nombre que le asignaremos al wsdl
luego la url **https://www.dataaccess.com/webservicesserver/NumberConversion.wso** + ?wsdl

Recordar que si esto lo ponemos en un navegador debera mostrar el XML completo.

2. Agregar al **pom.xml**:
   1. La dependencia del generador de codigo al importar el WSDL: **cxf-spring-boot-starter-jaxws**
   Se utiliza este generador de codigo porque:
   - Genera clases con paquetes jakarta.xml.ws.* compatibles con Spring Boot 3.
   - Mejor integración con Java 17.
   - Comunidad activa y soporte actualizado.

   2. La dependencia que permite la configuracion de TLS (Transport Layer Security), interceptores HTTP etc para CXF: **cxf-rt-transports-http**
   
   3. El plugin para la generacion de codigo donde solo se especifica url del sdl en local.

3. Hacer un mvn clean install para generar dichas clases. Se crearan en el target/generated-sources/cxf/...
4. En la clase de configuracion crear un bean que devuelva **NumberConversionSoapType** usando el servicio autogenerado **NumberConversion** y lo utilice sin hacer la validacion de certificados (util solo para local o entornos de desarrollo)
5. En el controlador inyectar el servicio y llamar al metodo que queremos en este caso