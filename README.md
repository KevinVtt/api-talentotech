# Proyecto Final - API RESTful para Talento Tech

Este proyecto es una **API RESTful** desarrollada con **Spring Boot** como trabajo práctico final para el curso de Talento Tech. Implementa un sistema de carrito de compras con buenas prácticas de desarrollo, incluyendo:

- **DTOs**: Uso de Data Transfer Objects para separar la lógica de negocio de la presentación.
- **Excepciones personalizadas**: Manejo robusto de errores para una mejor experiencia del usuario.
- **Lógica de negocio separada**: Servicios encapsulados para una arquitectura limpia.
- **Mappers**: Transformación entre entidades y DTOs (request/response) para una comunicación eficiente.
- **Controllers**: Devolución de respuestas claras y optimizadas para el usuario.
- **Variables de entorno**: Configuración segura de la base de datos.
- **Swagger**: Utilice swagger para la documentacion de las rutas, pagina para probar: http://localhost:8080/swagger-ui/index.html#/

## Configuración de la base de datos

El proyecto utiliza MySQL como base de datos. Configura las siguientes propiedades en el archivo `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
# Configuración de variables de entorno en IntelliJ IDEA

Para configurar las variables de entorno en IntelliJ IDEA, sigue estos pasos:

1. Haz clic en los tres puntos (`More Actions`) en la parte superior derecha de IntelliJ IDEA y selecciona **Edit Configurations**.
2. Luego, haz clic en el botón `+` y selecciona **Application**.
3. Asigna un nombre a la configuración, por ejemplo: `Spring Boot App`.
4. En la sección **Build and run**, selecciona la clase principal de tu aplicación Spring Boot, como por ejemplo: `ProyectoFinalApplication`.
5. En el apartado **Environment variables**, ingresa las variables de entorno en el siguiente formato:
```
1. DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos?createDatabaseIfNotExist=true&serverTimezone=UTC;
2. DB_USER=tu_usuario;
3. DB_PASSWORD=tu_contraseña
```
    Aclaracion: Copiar uno por uno y luego reemplazarlo no olvidarse los punto y coma al final de cada variable de entorno

6. Reemplaza:
- `tu_base_de_datos` por el nombre de tu base de datos.
- `tu_usuario` por tu usuario de MySQL.
- `tu_contraseña` por tu contraseña de MySQL.

7. Finalmente, haz clic en **Apply** y luego en **OK** para guardar la configuración.
8. Ejecuta la aplicación desde IntelliJ IDEA usando la configuración creada.

---
## 1. Crear una persona

**Ruta:** `/api/user`  
**Método:** `POST`  
**Cuerpo del request:**
```json
{
  "name": "jose",
  "email": "jose@jose.com"
}
```
✅ Al crear una persona (usuario/cliente), automáticamente se crea un carrito de compras asociado.

## Endpoints de la API

## 2. Crear una categoría de productos
**Ruta:** `/api/categoria`  
**Método:** `POST`  
**Cuerpo del request:**
```json
{
  "name": "nombre de la categoria"
}
```
## 3. Crear un producto
**Ruta:** `/api/producto`  
**Método:** `POST`  
**Cuerpo del request:**
```json
{
  "name": "producto",
  "price": 130,
  "stock": 100,
  "categoryId": 1
}
```
## 4. Crear una orden (Order)
**Ruta:** `/api/order`  
**Método:** `POST`  
**Cuerpo del request:**
```json
{
  "orderItemRequests": [
    {
      "productId": 1,
      "quantity": 5
    },
    {
      "productId": 2,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 5
    }
  ],
  "cartId": 1
}
```

