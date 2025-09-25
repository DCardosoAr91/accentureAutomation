package Steps.FrontEnd;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class frontEndSteps {

    private WebDriver driver;
    private frontEndPage page;

    @Given("que acesse o site {string}")
    public void que_eu_acesse_o_site(String url) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new frontEndPage(driver);
        page.open(url);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");

    }

    @Given("escolha a opção Forms na página inicial")
    public void eu_escolha_a_opcao_Forms_na_pagina_inicial() throws InterruptedException {
        page.clickMenuForms();

    }

    @Given("clique no submenu Practice Form")
    public void eu_clique_no_submenu_Pratice_Form() throws InterruptedException {
        page.clickSubmenuPracticeForm();

    }

    @Given("preencha todo o formulário com valores aleatórios")
    public void eu_preencha_todo_o_formulario_com_valores_aleatorios() throws InterruptedException {
        page.preencherValoresFormulario();
    }

    @When("submeter o formulário")
    public void eu_submeter_o_formulario() {
        page.submitForm();
    }

    @Then("popup deve ser exibido após o submit")
    public void um_popup_deve_ser_exibido_apos_o_submit() {
        page.verifyPopupDisplayed();
    }

    @Then("fecho o popup")
    public void eu_fecho_o_popup() throws InterruptedException {
        page.closePopup();
    }
    //2
    @Given("escolha a opção Alerts, Frame & Windows na página inicial")
    public void escolha_a_opção_alerts_frame_windows_na_página_inicial() throws InterruptedException {
        page.clickMenuAlertsFrameWindows();

    }
    @Given("clique no submenu Browser Windows")
    public void clique_no_submenu() throws InterruptedException {
        page.clickMenuBrowserWindows();

    }
    @When("clico no botão New Window")
    public void clico_no_botão() throws InterruptedException {
        page.clickBtnNewWindow();

    }
    @Then("uma nova janela deve ser aberta a mensagem This is a sample page deve estar visível na nova janela e fechar a nova janela")
    public void uma_nova_janela_deve_ser_aberta_validar_msg() throws InterruptedException {
        page.validarTelaEMsg();

    }

    //3
    @Given("escolha a opção Elements na página inicial")
    public void escolha_a_opção_elements_na_página_inicial() throws InterruptedException {
        page.clickMenuElements();

    }
    @Given("clique no submenu Web Tables")
    public void clique_no_submenu_web_tables() {
        page.clickMenuWebTables();

    }
    @When("crio um novo registro")
    public void crio_um_novo_registro() {
        page.criarNovoRegistro();

    }

    @When("crio {int} novos registros aleatórios")
    public void crio_varios_registros(int quantidade) {
        page.criarVariosRegistrosAleatorios(quantidade);

    }

    @Then("deleto todos os registros criados")
    public void deletar_todos_registros() {
        page.deletarTodosRegistrosCriados();
    }

    @When("edito o novo registro criado")
    public void edito_o_novo_registro_criado() {
        page.editarRegistroCriado();

    }
    @Then("deleto o novo registro criado")
    public void deleto_o_novo_registro_criado() throws InterruptedException {
        page.deletarRegistroCriado();

    }

    @When("clique no menu Widgets")
    public void clique_no_menu_Widgets() {
        page.clickMenuWidgets();

    }

    @When("seleciona o submenu Progress Bar")
    public void seleciona_o_submenu_Progress_Bar(){
        page.clickMenuProgressBar();

    }

    @When("clica no botão Start para iniciar a barra de progresso")
    public void clica_no_botão_para_iniciar_a_barra_de_progresso() {

        page.clickBtnStart();

    }

    @Then("a barra de progresso deve atingir menos de {int}%")
    public void a_barra_de_progresso_deve_atingir_menos_de(int percent) throws InterruptedException {
        page.barraDeProgressoAte25(percent);


    }

    @Then("o valor da barra de progresso deve ser menor ou igual a {int}%")
    public void o_valor_da_barra_de_progresso_deve_ser_menor_ou_igual_a(int percent) {
        page.barraDeProgressoIgual25(percent);
        page.clickBtnStart();

    }

    @When("o usuário clica em Start novamente")
    public void o_usuário_clica_em_novamente() {

        page.clickBtnStart();

    }

    @When("a barra de progresso atinge {int}%")
    public void a_barra_de_progresso_atinge(int percent) {
        page.barraDeProgressoIgual100(percent);

    }

    @Then("o usuário clica no botão Reset")
    public void o_usuário_clica_no_botão() {
        page.clickBtnReset();

    }

    @Then("a barra de progresso volta para {int}%")
    public void a_barra_de_progresso_é_resetada_para(int percent) throws InterruptedException {
        page.barraDeProgressoIgual0(percent);

    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }

    }
}