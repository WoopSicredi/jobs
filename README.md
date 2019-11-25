# votacao-api

Serviço Rest para realizar gerenciar a votação de pautas

## Rodar a aplicação

É possível rodar a aplicação utilizando o maven ou via jar. Das duas formas é necessário ter o java 8 e maven instalados na máquina

Para rodar via maven, no diretório raiz do projeto execute o comando abaixo:

+ mvn spring-boot:run

ou

+ mvn install
+ java -jar target/votacao-api.jar

### Banco de Dados

Foi utlizado o serviço gratuito de banco de dados MySQL da https://db4free.net/.
Como o próprio site friza, o propósito é fornecer um serviço de testes, o que cabe examente neste desafio.
E por também não ser necessário nenhuma configuração a respeito do banco.

Criei uma base de dados para este teste, onde poderá visualizar os dados no link e credenciais abaixo:
+ *https://db4free.net/phpMyAdmin/*
+ Utilizador : *dbsicredi*
+ Palavra-passe: *dbsicredi*

### Variáveis de Ambiente

Algumas variáveis do sistema podem ter seus valores alterados ao exec

+ SERVER_PORT: Porta do Tomcat (Embarcado no Spring Boot) - Padrão **8080**
+ DB_HOST: Host do banco de dados - Padrão **db4free.net**
+ DB_PORT: Porta do banco de dados - Padrão **3306**
+ DB_DATABASE: Nome do banco de dados - Padrão **dbsicredi**
+ DB_USERNAME: Usuário do banco de dados - Padrão **dbsicredi**
+ DB_PASSWORD: Senha do usuário do banco de dados - Padrão **dbsicredi**
+ URL_CHECAR_ASSOCIADO: URL para checar o status do CPF do associado - Padrão **https://user-info.herokuapp.com**

Para alterar alguma variável, basta seguir o exemplo abaixo:
- java -jar -DSERVER_PORT=**8081** target/votacao-api.jar

Ao executar o jar, estaremos iniciando o serviço na porta **8081**

Um ponto importante é que foi utilizado o Flyway no projeto, ou seja, caso queira utilizar um banco próprio o sistema irá gerar automaticamente a estrutura de dados. Ponto importante é que deverá ser **MySQL8**.

## Estrutura do projeto

O projeto contem está organizado em dois principais pacotes:
+ application: 
    <p>Tudo que engloba toda aplicação (segurança[nesse caso não foi preciso], recursos compartilhados).</p>
+ domains:
    Nesse pacote será realmente onde nossos serviços são desenvolvidos, abaixo deste pacote são colocados os dominíos chaves digamos assim.
    Em nosso caso, o sistema de votação.
    Vamos analisar os requisitos:
    - 1º Cadastrar uma nova pauta: 
      - Dominío forte: **pauta**
    - 2º Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default):
      - Vamos analisar: Temos que abrir uma sessão, mas a sessão está relacionada a pauta. Então não existe sessão sem pauta. Portando nosso dominío forte é: **pauta**
    - 3º Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta):
      - Vamos analisar: O voto será de acordo com a pauta em questão. Portando nosso dominío forte é: **pauta**
    - 4º Contabilizar os votos e dar o resultado da votação na pauta: 
      - Dominío forte: **pauta**
    
    Então em nosso projeto, teremos somente um ponto focal, que será /pauta/** e abaixo desse domínío teremos todas as regras de negócio.
    
    Abaixo como ficou a estrutura do projeto:

![github-large](https://i.ibb.co/XS9QWbp/estrutura-do-projeto.png)

## Usando o serviço

Inicie o serviço e a documentação do Swagger com a lista de serviços encontra-se no link: http://localhost:8080/swagger-ui.html

+ Criar pauta
    - /pauta/criar - POST
        - Requesição: JSON contendo o nome da pauta
        `{
	"nome": "Pauta Teste"
}`
        - Resposta: JSON contendo o id gerado da pauta e o nome da mesma
        `{
    "topicId": 1,
    "name": "Pauta Teste"
}`

+ Abrir Sessão
    - /pauta/abrirSessao - PUT
        - Requisição: JSON contendo o id da pauta e a duração em minutos da sessão(opcional)
        `{
  "duracaoEmMinutos": 10,
  "pautaId": 1
}`
        - Resposta: JSON contendo o id da pauta, o nome da pauta e horário de início e fim da sessão no formato: 'yyyy-MM-dd HH:mm:ss'
        `{
    "pautaId": 1,
    "nome": "Pauta Teste",
    "inicioVotacao": "2019-11-25 07:51:20",
    "fimVotacao": "2019-11-25 08:01:20"
}`

+ Votar
    - /pauta/votar - POST
        - Requisição: JSON contendo a decisão do voto (true é SIM / false é NÃO), o identificador da pauta e objeto do associado contendo o identificador e o cpf(opcional)
        `{
  "associado": {
    "cpf": "91509896007",
    "id": 1
  },
  "decisao": true,
  "pautaId": 1
}`
        - Resposta: JSON contendo todas as informações da pauta e o voto do associado
        `{
  "pautaDTO": {
    "pautaId": 1,
    "nome": "Pauta Teste",
    "inicioVotacao": "2019-11-25 07:51:20",
    "fimVotacao": "2019-11-25 08:01:20"
  },
  "associado": {
    "id": 1,
    "cpf": "91509896007"
  },
  "decision": true
}`

+ Apuração 
    - /pauta/apuracao/{pautaId} - GET
        - Requisição: Enviar o código da pauta no endereço do serviço
            `/pauta/apuracao/1`
        - Resposta: JSON contendo as informações da pauta e o resultado da votação
        `{
  "pauta": {
    "pautaId": 1,
    "nome": "Pauta Teste",
    "inicioVotacao": "2019-11-25 07:51:20",
    "fimVotacao": "2019-11-25 08:01:20"
  },
  "votingResult": "SIM"
}`

