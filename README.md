# Como rodar a aplicação

É possível rodar a aplicação utilizando o maven ou diretamente via jar.
Para rodar com maven basta utilizar o plugin do spring-boot através do seguinte comando:

	- mvn spring-boot:run

Também é possível criar um jar executável e executá-lo diretamente com a JVM atráves dos comandos:

	- mvn install
	- java -jar target/test-sicredi.jar

Optei por essas duas maneira pela simplicidade e agilidade, tendo em vista que não tinha conhecimento de como o teste seria rodado e o tempo que eu tinha disponível para o desenvolvimento do teste.
Após a rodar a aplicação, ela estárá disponível atrávés na URL base http://localhost:8080.
Os seguintes métodos HTTP estarão disponíveis:
* GET /topics
	- Retorna todas as pautas criadas e suas respectivas votações, sem os resultados.
* POST /topics
	- Cria um nova pauta para votação. Um nome deve ser passado como parâmetro no corpo da mensagem. Ex: {"name" : "topic"}. O ID do tópico é retornado como resposta
* DELETE /topics/{id}
	- Deleta uma pauta baseada no ID.
* GET /topics/{id}
	- Retorna um pauta baseada no ID.
* POST /topics/{id}/poll 
	- Abre uma votação de uma determinada pauta.
	- Uma duração pode ser passada no corpo da mensagem para definir por quanto tempo a pauta pode receber votos. Ex {"durationInMinutes": "10"}
* GET /topics/{id}/poll
	- Retorna o resultado de uma votação, caso ela esteja disponível
* POST /topics/{id}/vote
        - Processa um voto em uma pauta baseada no seru ID.
	- Nessa versão, eu incluí o usuário que está votando no corpo da mensagem. Isso não deveria ser feito um produção, uma vez que o usuário estaria autenticado. Ex: {"username": "joao", "voteOption": "YES"}

É possível ver a listagem completa da interface utilizando a interface do Swagger em http://localhost:8080/swagger-ui.html#/topic-controller

# Interpretação dos requisitos.

As serguintes interpretações foram base para desenvolvimento do teste:

* Uma pauta pode receber apenas uma sessão de votação. Ao tentar reabrir a votação de uma pauta que kjá foi encerrada, um erro é retornado. Isso poderia ser facilmente extensível para permitir múltiplas votações alterando o relacionamento entre as entidades.
* Ao abrir uma votação, a API permite definir uma duração em minutos. Optei por minutos para simplificar o desenvolvimento, mas a alteração para permitir definir a unidade e o tempo, ou mesmo uma data de fim seria simples. Optei por definir um período pois isso evita ter que tratar diferentes fusos horários, uma vez que a aplicação sempre trabalho no seu fuso horário. Isso também, deixa o serviço stateless, pois basta somar a duração e a data de criação para saber se o votação expirou ou não.
* Na API de votação, o usuário está presente simplesmente por falta de tempo para ter a infra que possibilitasse obter o usuário logado. Inclui para possibilitar o teste e simular o comportamento real da aplicação.
* Com relação aos resultados, optei por modelar através de um contador de votos, que não associa um voto a um usuário, pois assumi que um dos requisitos poderia querer que o voto fosse totalmente secreto e que não fosse possível recuperar o voto de um usuário. Para evitar um voto duplicado, o registro do voto é salvo sem a opção escolhida.

# Decisões técnicas

As seguintes decisões técnicas foram tomadas:

* Separação da camada de persistência com a camada da API. Mesmo que isso gere conversões aparentemente desnecessárias, isso permite a separação dos domínios e permite uma um maior controle sobre a API REST, evitando quebras desnecessárias e acoplamentos com outras partes do software. Para tomar uma decisão mais embasada, precisaria conhecer melhor o sistema e como ele poderia evoluir.

* Optei por utilizar o banco de dados H2 embarcado, para facilitar o teste e evitar longas instruções de montagem de ambiente. O mesmo motivo também me fez optar por utilizar o Spring-Data para modelagem do banco. Em produção, um versionamento utilizando Flyway ou Liquibase provavelmente seria necessário, porém não utilizei pela simplicidade do teste e falta de tempo.

* Na parte de validação, utilizei anotações javax.validation para validar algumas entradas na API. Validações mais complexas foram separadas em validadores.

* Na parte de Error Handling, utilizei ExceptionHandlers do Spring para capturar exceptions e transformá-las em Response HTTP e seus devidos códigos de erro.


# Melhorias

A maior parte das melhorias listadas abaixo não foram implementadas em virtude do tempo que tive para dedicar ao desenvolvimento que pelos meus cálculos, ficaram em torno de 6 horas não corridas. A maior parte delas foi postergada para possibilitar a implementação do requisito.

* Melhorias de logs: praticamente não incluí logs na aplicação.
* Comentários nas classes: poucos comentários forma incluídos.
* Testes unitarios e testes de integração não foram feitos.
* Possiblidade de geração automática de documentação não foi utilizada e seria um acréscimo a documentação da interface REST.
* Utilização de cache em chamadas que possívelmente possam ser repetitivas, como no caso dos votos de usuário que recuperam a informação da pauta.


