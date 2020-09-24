# Framework e tecnologia utilizadas no projeto

Neste projeto utilizei as seguintes tecnologias:

* React
* Typescript
* Webpack
* JJS

## React
React é uma biblioteca JavaScript que facilita o desenvolvimento da interface de usuário. A grande vantagem de usar este tipo de tecnologia é a sua facilidade para criar componentes reutilizáveis e velocidade de desenvolvimento. 

## Typescript
Typescript estende a linguagem Javascript adicionando tipagem para manipulação das estruturas de dados e introduzindo uma etapa de compilação/transpilação de código. A vantagem de utilizar typescript é que ele permite detecção de erros de programação através da etapa de compilação e isso facilita muito a gestão de grandes projetos. Outra utilidade para o Typescript é que ele tem recurso nativo para transpilar expressões JSX para código javascript. (JSX são maneiras de descrever elementos de interface de usuário através de código javascript ao invés de html). Mais uma vantagem desta tecnologia é que ela é capas de transpilar código javascript de versões mais recentes como por exemplo EcmaScript 6 para versões antigas como EcmaScript 5 (Compatível com Navegadores antigos).

## Webpack
Webpack é um empacotador de código ou apenas "build system". Através de um arquivo de configuração, esta tecnológica analisa e empacota o código fonte do projeto em um formato pronto para aplicação em produção. 

## JJS
JJS é uma tecnologia para estilizar componentes HTML através de JavaScript. Esta tecnologia resolve automaticamente conflitos de nomenclatura de classes CSS e permite descrever e reutilizar estilos usando JavaScript. É muito semelhante a tecnologias como Sass, a diferença é que ao invés de utilizar uma linguagem diferentes é possível fazer tudo usando JavaScript. 

# Project setup, compile e run
Os passos para baixar e executar o projeto são os seguintes: 

0. Instalar [NodeJS e NPM](https://nodejs.org/en/download/)
1. Clonar o projeto usando o comando ```git clone "url do projeto"```
2. Abrir um terminal no diretório do projeto e instalar os pacotes node ``npm install``
3. Para rodar o ambiente de desenvolvimento:
  - ``npm run dev``
4. Para criar um pacote de produção:
  - ``npm run prod``

# Estrutura do projeto

O projeto tem a seguinte estrutura:

```
| Assets (Diretório para arquivos estáticos como HTML, SVG, Imagens, etc.)
| dist (Diretório onde é gerado os arquivos de compilação do Webpack)
| docs (Usado apenas para o GitHub Pages)
| src (Diretório com todo o código fonte)
 \
  | components (Componentes reutilizáveis do React)
  | services (código fonte referente a integração como por exemplo, chamadas API para um servidor Backend)
  | util (Códigos comuns reutilizáveis)
  | view (Cada arquivo é uma tala diferente do sistema)
 /
| Raiz do projeto (Arquivos de configuração e README)
```

# Funcionalidades 
- Login (Fake) 
- Tela de listagem de dragões (Tela principal) 
- Edição de dragões é feita na própria tela de listagem 
- Tela de criação de dragões 
- Tela de detalhe dos dragões 
- Botão para remover dragões na própria listagem 
- Menu global de fácil acesso no header 
