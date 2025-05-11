# Sistema de Colas Municipal

Sistema de gestión de colas para servicios municipales desarrollado con Java Spring Boot.

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- NetBeans IDE (recomendado)

## Configuración del Proyecto

1. Clonar el repositorio
2. Configurar la base de datos MySQL:
   - Crear una base de datos llamada `municipalidad_db`
   - El usuario y contraseña por defecto son `root/root` (modificar en `application.properties` si es necesario)

3. Configurar el proyecto en NetBeans:
   - Abrir NetBeans
   - Seleccionar File -> Open Project
   - Navegar hasta la carpeta del proyecto y seleccionarla
   - Esperar a que Maven descargue las dependencias

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── municipalidad/
│   │           ├── controller/
│   │           ├── model/
│   │           ├── repository/
│   │           └── service/
│   └── resources/
│       └── application.properties
```

## Servicios Disponibles

El sistema maneja los siguientes servicios:
- Pago de IUSI
- Pago de Servicio de Agua
- Servicios de Catastro
- Servicios de Licencias de Construcción

## Endpoints API

- `POST /api/tickets/crear` - Crear nuevo ticket
- `PUT /api/tickets/{id}/atender` - Atender ticket
- `PUT /api/tickets/{id}/finalizar` - Finalizar ticket
- `GET /api/tickets/pendientes` - Obtener tickets pendientes
- `GET /api/tickets/tipo/{tipoServicio}` - Obtener tickets por tipo de servicio

## Ejecutar el Proyecto

1. En NetBeans:
   - Click derecho en el proyecto
   - Seleccionar "Clean and Build"
   - Click derecho en el proyecto
   - Seleccionar "Run"

2. O desde la línea de comandos:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Configuración de Red

El sistema está diseñado para funcionar en una red con las siguientes máquinas:
1. Servidor de Base de datos (MySQL)
2. Máquina Cliente
3. Servidor de Aplicación (Spring Boot)
4. Servidor de visualización del Dashboard

## Seguridad

- Implementación de autenticación JWT
- Roles de usuario (ADMIN, OPERADOR)
- Endpoints protegidos

## Reportes

El sistema genera los siguientes reportes:
- Tickets atendidos agrupados por colas de trabajo
- Dashboard de tickets atendidos por tipos de tickets 