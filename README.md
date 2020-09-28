### Informações gerais sobre o Projeto
-  Suporte a mudanças de orientação das telas sem perder estado
-  O projeto foi elaborado utilizando o padrão arquitetural MVI (Model-View-Intent).
-  O projeto possui testes unitários.
-  O projeto possui testes de interface.
-  O projeto do teste foi criado com base em um template feito por mim no meu Github, a intenção do template é deixar toda a estrutura pronta para ganhar tempo na criação de um novo projeto e evitar o processo demorado e repetitivo quando tiver que criar um do zero.

### Motivos para escolher este padrão arquitetural
 - Fluxo de dados unidirecional e cíclico.
 - Um estado consistente durante o ciclo de vida das Views.
 - Modelos imutáveis ​​que fornecem comportamento confiável e segurança de threads em aplicativos grandes.


### Linguagem utilizada:
   - Kotlin


###  Bibliotecas utilizadas na implementação:
   - Mobius Spotify (Uma biblioteca criada pela equipe do Spoty com uma estrutura reativa funcional para gerenciar a evolução do estado e efeitos colaterais, com complementos para conectar-se às UIs do Android e ao RxJava Observables. Ele enfatiza a separação de preocupações, a testabilidade e o isolamento de partes com estado do código.)
   - rxbinding3 (Para converter os widgets em observables)
   - Dagger 2 (Para injentar as dependências)
   - ViewModels (Trabalhar a lógica de negócio)
   - Jetpack navigation
   - Room database
   - Databinding (Para evitar boilerplate e manter um código mais limpo)
   - Safe Args
   - Retrofit2: moshi-converter, adapter-rxjava2
   - RxJava2
   - Glide
   - AndroidX
   - RxJava (RxKotlin)
   - Material Design 2.0

### Bibliotecas utilizadas para testes:
   - Espresso
   - JUnit
   - MockWebServer (Para simular a api criando um servidor local)

### Como rodar os testes de interface
   - Para rodar os testes de interface, mude a build variants para intrumented.

### O que gostaria de ter feito mais e não fiz por causa do tempo
  - Gostaria de ter trabalhado mais nos testes das views, criado as ações.