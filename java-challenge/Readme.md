Este arquivo README.md fornece informações sobre o projeto Java Challenge, incluindo sua finalidade, como executar os testes, a estrutura do código e como contribuir para o projeto.
Visão Geral

O Java Challenge é um projeto que visa demonstrar o uso de testes unitários em Java com JUnit e Mockito, além de apresentar um exemplo de arquitetura para uma API REST. O projeto consiste em um serviço de pagamentos com funcionalidades para processar pagamentos, estornar pagamentos, consultar pagamentos por ID e consultar todos os pagamentos.
Funcionalidades

    Processar Pagamento: Permite processar pagamentos, registrando as informações da transação, descrição e forma de pagamento.
    Estornar Pagamento: Permite estornar pagamentos, alterando o status da transação para cancelado.
    Consultar Pagamento por ID: Permite consultar um pagamento específico pelo seu ID.
    Consultar Todos os Pagamentos: Permite consultar todos os pagamentos registrados.

Tecnologias

    Java: Linguagem de programação utilizada.
    JUnit: Framework de testes unitários.
    Mockito: Framework de mock para testes unitários.
    Spring Boot: Framework para criação de aplicações Java Spring.
    Maven: Ferramenta de gerenciamento de dependências.

Como Executar os Testes

    Clone o repositório:
    Bash

git clone https://github.com/ThiagoDevRoot/ToolsChallenge-.git

Acesse o diretório do projeto:
Bash

cd java-challenge

Execute os testes com Maven:
Bash

    mvn test

    Este comando irá compilar o código, executar os testes unitários e gerar um relatório de testes.

Estrutura do Código

O projeto está estruturado da seguinte forma:

    src/main/java: Contém o código fonte principal da aplicação.
        com.tools.java_challenge.controller: Contém os controladores REST da API.
        com.tools.java_challenge.model: Contém as classes de modelo (entidades) da aplicação.
        com.tools.java_challenge.service: Contém as classes de serviço com a lógica de negócios.
        com.tools.java_challenge.exception: Contém as classes de exceção personalizadas.
        com.tools.java_challenge.mapper: Contém os mappers para conversão entre objetos.
        com.tools.java_challenge.response: Contém as classes de resposta da API.
    src/test/java: Contém os testes unitários da aplicação.
        com.tools.java_challenge.controllerTest: Contém os testes unitários dos controladores.
        com.tools.java_challenge.serviceTest: Contém os testes unitários dos serviços.
    pom.xml: Arquivo de configuração do Maven com as dependências do projeto.

Como Contribuir

    Fork o repositório.
    Crie sua branch de feature (git checkout -b feature/nome-da-feature).
    Faça commit das suas mudanças (git commit -am 'Adiciona nova feature').
    Faça push para a branch (git push origin feature/nome-da-feature).
    Crie um Pull Request.

Licença

Este projeto está sob a licença [Tools/Thiago].

Autor

[Thiago Claro]