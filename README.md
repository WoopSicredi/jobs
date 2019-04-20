# poll-service
Um serviço REST simples para criar votações a respeito de pautas.

## Como rodar a aplicação
### Subir serviços com o Docker Swarm:

$ ./run.sh deploy

Pré-requisitos: Linux, Docker

Irá:
- Fazer build da aplicação com o gradle;
- Criar uma imagem Docker do serviço;
- Inicializar o Docker swarm na máquina;
- Fazer o deploy de uma instância do Postgres (porta 5432) e do serviço (porta 8501).

### Parar e remover a aplicação

$ ./run.sh rm

Irá:
- Parar e remover os containers;
- Remover a máquina do Docker swarm.

### Alternativa: Rodar a aplicação em ambiente de desenvolvimento

- Rodar com o profile dev através de parâmetro da JVM: "-Dspring.profiles.active=dev"

- Será preciso ter um Postgres com as configuração do arquivo application-dev.yml, o que pode ser feito subindo um container Docker do Postgres.
  - Porta: 5432
  - usuário/senha: postgres/postgres.

## Como usar o serviço

Acessar a documentação Swagger do serviço em:
http://localhost:8501/swagger-ui.html

- Criar uma pauta (topic-rest-controller)
  - POST /topic
    - Informar uma descrição para a pauta.
    - Irá retornar o objeto Topic com seu ID.

- Criar uma votação (topic-rest-controller)
  - POST /topics/{topicId}/polls
    - Informar o ID do tópico, a data de fim e uma descrição para a votação. O exemplo mostra como passar a data com o Timezone.
    - Irá retornar o objeto Poll com seu ID.

- Votar (poll-rest-controller)
  - POST /polls/{pollId}/votes
    - Informar O ID da votação, um ID do votante e a opção (Sim/Não).
    - Irá retornar o objeto Vote ou um erro se a votação estiver encerrada.

- Obter os resultados das votações em um tópico
GET /topics/{topicId}/polls
  - Informar o ID do tópico.
  - Irá retornar os dados do tópico com suas votações, incluindo a contagem dos votos para cada uma.

## Decisões tomadas durante o desenvolvimento:
- Fiz o serviço usando Spring boot e Java, pois são as tecnologias que tenho mais familiaridade.

- Conforme o requisito de não perder os dados da aplicação, utilizei o PostgreSQL ao invés de um h2 em memória.
  Escolhi a base relacional pois achei que era mais adequada pro problema, onde tem que ser criadas as relações entre tópico, votações e votos.
  Pensei em usar o MongoDB, mas imaginei que poderia ter algum problema na modelagem, visto que sempre o usei para armazenar documentos monolíticos.

- Usei o Flyway para versionar a base de dados, o que possibilita evoluir seu schema.

- Como a descrição dizia que era pra ser executado na nuvem, possibilitei rodar a aplicação com o Docker Swarm.
  A configuração disponibilizada no projeto vai rodar duas instâncias do serviço e uma instância do Postgresql.

- Achei que seria interessante receber e armazenar as datas de fim de votação no timezone do cliente do serviço, assim ele não precisa fazer transformar de UTC pra sua hora local.
  Usei o tipo ZonedDateTime pra receber o fim da votação, o que exigiu parametrizar o Jackson na configuração do Spring para incluir o timezone na serialização/deserialização.

- Não fiz uma separação clara das classes de domínio para as camadas REST, negócio e persistência, acabei  usando o mesmo objeto de ponta a ponta.
  Achei que separar iria trazer mais custo (boilerplate) do que benefício nesse caso, onde o serviço é pequeno.
  Se necessário modificar a API ou o modelo de dados futuramente, é possível tentar fazer mudanças que não causem incompatibilidades, como criação de novos campos e "deprecação" dos antigos.
  Também não seria difícil versionar a API e criar uma camada de classes para persistência, sem prejuízo ao cliente do serviço.

- Fiz testes unitários e de integração. Os testes de integração irão subir uma instância do Postgres no Docker e fazer as chamadas por HTTP de fato, com o Rest Assured. Se usasse o H2 poderia passar algum problema de integração com o banco real.

- O código foi escrito todo em inglês. Chamei as entidades de Topic (pauta), Poll (votação) e Vote.
  Achei que o domínio era genérico o suficiente para não usar os termos em português (Pauta, Votação) no código.

- A configuração do Docker Swarm não deve ser usada em produção, o ideal seria ter um Postgres instalado em um servidor dedicado, sem usar o Docker.

## Problemas/melhorias:

- Uma coisa que me incomodou foi a quantidade de código (contabilizando testes) que precisei escrever para disponibilizar um conjunto bem pequeno de funcionalidades.
  Se tivesse que refazer, veria se é possível fazer o serviço usando o spring-data-rest e inserir pontualmente a lógica específica exigida em alguns endpoints, como na criação de voto, que precisa verificar se a votação está aberta.

 - A falta de separação das classes de domínio de acordo com a camada da aplicação, apesar de reduzir o código boilerplate, acaba causando uma mistura de anotações do hibernate com as de validação que acaba ficando um pouco estranha. Talvez fosse melhor separar desde o início :)

 - Me incomodou um pouco a forma como ficou a validação dos campos, algumas validações ficaram em anotações nas classes de domínio outras no serviço.
   Numa próxima versão iria analisar como deixar isso de uma forma centralizada sem replicar muito código de validação que o Spring já provê.


