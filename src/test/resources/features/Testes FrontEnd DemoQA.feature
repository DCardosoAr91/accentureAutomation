Feature: Testes FrontEnd DemoQA

  Scenario: CT01 - Preencher e Submeter o Formulário de Prática com Valores Aleatórios
    Given que acesse o site "https://demoqa.com/"
    And escolha a opção Forms na página inicial
    And clique no submenu Practice Form
    And preencha todo o formulário com valores aleatórios
    When submeter o formulário
    Then popup deve ser exibido após o submit
    And fecho o popup

  Scenario: CT02 - Abrir nova janela e validar mensagem "This is a sample page"
    Given que acesse o site "https://demoqa.com/"
    And escolha a opção Alerts, Frame & Windows na página inicial
    And clique no submenu Browser Windows
    When clico no botão New Window
    Then uma nova janela deve ser aberta a mensagem This is a sample page deve estar visível na nova janela e fechar a nova janela

  Scenario: CT03 - Realizar operações de CRUD na tabela do site DemoQA
    Given que acesse o site "https://demoqa.com/"
    And escolha a opção Elements na página inicial
    And clique no submenu Web Tables
    When crio um novo registro
    And edito o novo registro criado
    Then deleto o novo registro criado
    And crio 12 novos registros aleatórios
    And deleto todos os registros criados
    
  Scenario: CT04 - Controle de Progress Bar e reset ao atingir percentual máximo
    Given que acesse o site "https://demoqa.com/"
    When clique no menu Widgets
    And seleciona o submenu Progress Bar
    And clica no botão Start para iniciar a barra de progresso
    Then a barra de progresso deve atingir menos de 25%
    And o valor da barra de progresso deve ser menor ou igual a 25%
    When o usuário clica em Start novamente
    And a barra de progresso atinge 100%
    Then o usuário clica no botão Reset
    And a barra de progresso é volta para 0%