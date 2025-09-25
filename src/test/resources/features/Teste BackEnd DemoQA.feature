# language: en
Feature: Teste BackEnd DemoQA

  Scenario: Criar usuário, autenticar, alugar livros e consultar dados
    Given que eu crio um usuário com nome e senha válidos
    When gero um token de acesso para o usuário criado
    And verifico se o usuário está autorizado
    And eu consulto a lista de livros disponíveis
    And seleciono dois livros e associo ao usuário
    Then os dados do usuário devem conter os dois livros escolhidos

