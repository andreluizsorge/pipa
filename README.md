# Java backend developer test - Pipa

Este projeto foi desenvolvido com o intuito de atender os requisitos passados.

"Write a HTTP-based mini game back-end in Java which registers score points for different
users, with the capability to return the current user position and high score list." 

## Getting Started

### Prerequisites


```
Java 8
Maven 3.6.1
```

### Installing

```
..\..\pipa-parent\mvn clean install
```

```
..\..\pipa-parent\Api\pipa-integration-api\target\java -jar pipa-integration-api-1.0.0.jar
```

b.c.p.i.IntegrationServiceMain           : Started IntegrationServiceMain in 2.558 seconds (JVM running for 2.898)

## Running the tests

https://documenter.getpostman.com/view/788715/Szf6XodK?version=latest#418914d2-25d1-4b5c-aa45-986268b1ff7f

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **André Sorge** - *Initial work* - [andreluizsorge](https://github.com/andreluizsorge)

## Comments

* Em um primeiro momento eu pensei em utilizar a arquitetura ZUP (Orientação a Eventos) 
mas tive muitos problemas, pois eu nao queria utilziar nenhum framework de FIFO (ActiveMQ ou Camel) pois
um dos requisitos era a performace.
Eu tomei bastante cuidado em manter o codigo bem organizado e bem orientado a objetos, pois dessa maneira 
é possivel mexer no projeto sem causar muito impacto.
