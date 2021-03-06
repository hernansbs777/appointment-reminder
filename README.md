# Appointment Reminder

### Las herramientas, librerías y frameworks utilizados para este proyecto son las siguientes:: 

* [NetBeans 11.3](https://netbeans.apache.org/download/nb113/nb113.html)
* [JDK 11.0.12](https://www.oracle.com/ar/java/technologies/javase/jdk11-archive-downloads.html)
* [Spring 2.6.1](https://spring.io/blog/2021/11/29/spring-boot-2-6-1-is-now-available)
* [GlassFish 5.0 con JDK 1.8.151](https://javaee.github.io/glassfish/download)
* [Hibernate 5](https://hibernate.org/orm/documentation/5.0)
* [SendGrid API V3](https://sendgrid.api-docs.io/v3.0/how-to-use-the-sendgrid-v3-api/api-authentication)
* [Maven](https://maven.apache.org/)
* [MySQL 8](https://dev.mysql.com/downloads/mysql/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Primeros Pasos -->
## Primeros Pasos

Para poder instalar el projecto en principio se debe instalar las librerias con Maven en la carpeta raiz del proyecto
* mvn
  ```sh
  mvn install
  ```
### Instalacion

Como servidor se utilizo GlassFish 5.0 el cual hay que cambiarle la version del JDK a 1.8.151, esto es porque luego surgio un problema de SSL que no se arreglo hasta Payara.

Se recomienda utilizar la herramienta PostMan para poder utilizar los EndPoint.
* [Postman](https://www.postman.com/)

Antes de iniciar el proyecto, deberia modificarse el archivo application.properties de esta manera

  ```sh
    spring.datasource.url=jdbc:mysql://localhost:%PUERTO_CONFIGURADO%/ms?useSSL=false
    spring.datasource.username= REEMPLAZAR CON USERNAME DE BASE DE DATOS
    spring.datasource.password= REEMPLAZAR CON PASSWORD DE BASE DE DATOS
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl-auto=update
    app.sendgrid.key=REEMPLAZAR CON APIKEY DE SENDGRID
    app.sendgrid.templateId=REEMPLAZAR CON TEMPLATE ID DE SENDGRID
    app.sendgrid.email=REEMPLAZAR CON EMAIL CONFIGURADO PARA EL ENVIO
  ```

### Ejemplos de uso de la aplicacion

- Crear Turno
* Metodo: POST
* URL: http://%URLSERVIDOR%:%PUERTO%/appointment-reminder/turnos/
* Ejemplo de formato: 
   ```js
    {
      "fecha":"2022-10-15"
      "hora":"15:00:00"
      "institucion":"Hosp. Municipal de Quemados" 
      "consultorio":"15"
      "direccion":"Av. Pedro Goyena 369, C1424 BSD, Buenos Aires"
    }
   ```
- Enviar Email
* Metodo: POST
* URL: http://%URLSERVIDOR%:%PUERTO%/appointment-reminder/enviar/
* Ejemplo de formato:
   ```js
    {
      "email":"ejemplo@ejemplo.com"
      "nombre":"Pepe" 
      "apellido":"Gonzales"
      "idturno":"170"
    }
   ```   
