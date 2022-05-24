# Fuego de Quasar

El proyecto está desarrollado bajo el framework Spring Boot.
El proyecto cuenta con swagger para facilitar documentación de su API:
Para ver la documentación se debe ingresar al siguiente enlace: http://ip_aplicacion:puerto/swagger-ui/index.html#
El proyecto está estructurado de la siguiente manera:

Config: En este paquete se encontrarán todas las configuraciones necesarias para la aplicación. (Manejo de mensajes a travez de properties y YML)

Controller: En este paquete se encuentran las clases(Controller) de las cuales recibiremos las peticiones. Aquí encontraremos los end-point.

Exception: En esta carpeta encontraremos las excepciones personalizadas, así como el Handle de excepciones que se lanzaran si existe una excepción en la petición Rest (Utilizamos RestControllerAdvice).

Facade: En este paquete encontraremos fachadas para llamar a los servicios de obtener mensajes y localización.

Service: En este paquete encontramos los servicios principales de nuestra aplicación, los cuales son obtener el mensaje y localizar el origen del mensaje.

Model: En este paquete encontraremos los objetos que representan las solicitudes y las respuestas de la aplicación. Estas clases representan los modelos de nuestra aplicación.

# Diagrama de clases

Nuestro diagrama de clases es el siguiente:


Librerías utilizadas: Spring Boot(We, Exception advice, etc), Lombok, Swagger
La aplicación será desplegada en los servicios GCP, específicamente en App Engine
