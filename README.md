# Roulette Masivian Api

API Desarrollada en SpringBoot para prueba técnica de Masivian, presentada y desarrollada por Juan Sebastian Munar Aldana

## Comenzando 🚀

_Estas instrucciones te permitirán ejecutar la API, clona la aplicación con el siguiente enlace https://github.com/msebastian02/Roulette-API.git_

Mira **Release** para encontrar los archivos necesarios para su ejecución.

Mira **Source** para encontrar las fuentes asociadas al desarrollo.

### Pre-requisitos 📋

_Estos son los componentes necesarios para la ejecución de la API:_

```
*Java 
*Docker
```

### Instalación 🔧

_Estas son las instrucciones que deberán ser ejecutadas para la instalación de la API:_

_En la carpeta release, donde se encuentra el archivo "docker-compose.yml" ejecuta el siguiente comando en el CMD_

```
docker-compose up -d
```

_Es importante tener disponible el puerto 3306 para la ejecución de la base de datos MySQL_

_Luego para ejecutar la API ejecuta el siguiente comando en el CMD_

```
java -jar roulette-api-1.jar
```

_A continuación podrás ejecutar pruebas de la API expuesta en localhost:8080_

## Ejecutando las pruebas ⚙️

_Para ejecutar pruebas a la API se debe usar cualquier programa para ejecución de peticiones (Postman, SoapUI, API Tester, etc.)_

_NOTA: En la carpeta Release del repositorio se encuentra un proyecto SoapUI ya configurado con las diferentes peticiones (Roulette-soapui-project.xml)_

_Crear Ruleta, Se deberá enviar una petición tipo POST con un body vacío y retornará el id de la ruleta creada:_

```
ENDPOINT:
http://localhost:8080/create

RS

1
```

_Crear Usuario, Se deberá enviar una petición tipo POST con un body como el siguiente y retornará el objeto del usuario creado:_

```
ENDPOINT:
http://localhost:8080/createuser

RQ

{
  "credit":10000
}

RS

{
	"id": 9,
	"credit": 10000.0
}
```


_Abrir Ruleta, Se deberá enviar una petición tipo PUT con un body como el siguiente y retornará el estado de la petición:_

```
ENDPOINT:
http://localhost:8080/open

RQ

{
  "id":1
}

RS

"Successful operation"
```

_Realizar Apuesta, Se deberá enviar una petición tipo POST con el userId diligenciado en el HEADER, un body como el siguiente y retornará el objeto de la apuesta creada:_

```
ENDPOINT:
http://localhost:8080/makebet

Example 1

RQ

HEADER userId : 1
{
  "userBet":15,
  "betAmount":100,
  "rouletteId":1
}

RS

{
	"id": 1,
	"userBet": "15",
	"status": null,
	"betAmount": 100,
	"userId": 1,
	"rouletteId": 1
}

Example 2

RQ

HEADER userId : 1
{
  "userBet":"red",
  "betAmount":500,
  "rouletteId":1
}

RS

{
	"id": 3,
	"userBet": "red",
	"status": null,
	"betAmount": 500,
	"userId": 1,
	"rouletteId": 1
}
```

_Cerrar Ruleta, Se deberá enviar una petición tipo PUT con un body como el siguiente y retornará el resultado de las apuestas realizadas:_

```
ENDPOINT:
http://localhost:8080/close

RQ

{
  "id":1
}

RS

[
    {
        "id": 1,
        "userBet": "15",
        "status": "LOSE",
        "betAmount": 100,
        "userId": 1,
        "rouletteId": 1
    },
    {
        "id": 2,
        "userBet": "red",
        "status": "COLOR WIN",
        "betAmount": 100,
        "userId": 1,
        "rouletteId": 1
    },
    {
        "id": 3,
        "userBet": "red",
        "status": "COLOR WIN",
        "betAmount": 500,
        "userId": 1,
        "rouletteId": 1
    }
]
```

_Listado Ruletas, Se deberá enviar una petición tipo GET y retornará el listado de ruletas junto con sus estados:_

```
ENDPOINT:
http://localhost:8080/list

RS

[
    {
        "id": 1,
        "status": false
    },
    {
        "id": 2,
        "status": false
    },
    {
        "id": 3,
        "status": false
    },
    {
        "id": 4,
        "status": false
    },
    {
        "id": 5,
        "status": false
    },
    {
        "id": 6,
        "status": false
    }
]
```


## Construido con 🛠️

* [SpringBoot](https://spring.io/projects/spring-boot) - Java Framework
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Docker](https://docs.docker.com/) - Usado para ejecutar MySQL

## Autores ✒️


* **Juan Sebastian Munar Aldana** - *Desarrollo* - [msebastian02](https://github.com/msebastian02)

---
