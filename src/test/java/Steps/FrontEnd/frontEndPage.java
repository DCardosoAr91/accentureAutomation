package Steps.FrontEnd;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class frontEndPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public frontEndPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String url) {
        driver.get(url);
        maximizeWindow();
        removeAds(); // Limpa anúncios ao abrir a página
        pause();

    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Remove banners, iframes e anúncios comuns
    private void removeAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.querySelectorAll('#fixedban, iframe, .banner, .ads, .ad').forEach(e => e.remove());"
        );
        pause(); // pausa de 2s

    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void clickMenuForms() {
        removeAds();
        WebElement selecionarBtnForms = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app']/div/div/div[2]/div/div[2]")));
        scrollIntoView(selecionarBtnForms);
        selecionarBtnForms.click();
        zoomPage(50);
        pause();

    }

    public void clickSubmenuPracticeForm() {
        removeAds();
        WebElement selecionarBtnPraticeForms = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Practice Form']")));
        scrollIntoView(selecionarBtnPraticeForms);
        selecionarBtnPraticeForms.click();
        pause();
    }

    public void preencherValoresFormulario() {
        removeAds();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        firstName.sendKeys("Denis");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Cardoso");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("denis.cardoso@teste.com");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.SPACE).perform();

        WebElement mobile = driver.findElement(By.id("userNumber"));
        mobile.sendKeys("11912438342");

        WebElement uploadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadPicture")));
        String filePath = "C:\\Users\\dcard\\Downloads\\AutomationAccenture\\src\\test\\resources\\fileTesting.txt";
        uploadButton.sendKeys(filePath);
        pause();
    }

    public void submitForm() {
        removeAds();
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        pause();
    }


    public void verifyPopupDisplayed() {
        removeAds();
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
        assert popup.isDisplayed() : "Popup não está visível.";
        pause();
    }

    public void closePopup() {
        removeAds();
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("closeLargeModal")));
        scrollIntoView(closeButton);
        closeButton.click();
        pause();
    }

    public void clickMenuAlertsFrameWindows() {
        removeAds();
        WebElement selecionarBtnAlertsFrameWindows = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app']/div/div/div[2]/div/div[3]")));
        scrollIntoView(selecionarBtnAlertsFrameWindows);
        selecionarBtnAlertsFrameWindows.click();
        zoomPage(50);
        pause();
    }

    public void clickMenuBrowserWindows() {
        removeAds();
        WebElement selecionarBtnBrowserWindows = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='text' and text()='Browser Windows']")));
        scrollIntoView(selecionarBtnBrowserWindows);
        selecionarBtnBrowserWindows.click();
        pause();
    }

    public void clickBtnNewWindow() {
        removeAds();
        WebElement selecionarBtnNewWindow = wait.until(ExpectedConditions.elementToBeClickable(By.id("windowButton")));
        scrollIntoView(selecionarBtnNewWindow);
        selecionarBtnNewWindow.click();
        pause();
    }

    public void validarTelaEMsg() {
        removeAds();
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
                String expectedMessage = "This is a sample page";
                assert message.getText().equals(expectedMessage) : "Mensagem incorreta na nova janela.";
                driver.close();
                driver.switchTo().window(mainWindow);
                break;
            }
            pause();
        }
    }

    public void clickMenuElements() {
        removeAds();
        WebElement selecionarBtnElements = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='app']/div/div/div[2]/div/div[1]")));
        scrollIntoView(selecionarBtnElements);
        selecionarBtnElements.click();
        zoomPage(50);
        pause();
    }

    public void clickMenuWebTables() {
        removeAds();
        WebElement selecionarBtnWebTables = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='text' and text()='Web Tables']")));
        scrollIntoView(selecionarBtnWebTables);
        selecionarBtnWebTables.click();
        pause();
    }

    public void criarNovoRegistro() {
        removeAds();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
        scrollIntoView(addButton);
        addButton.click();

        driver.findElement(By.id("firstName")).sendKeys("Teste");
        driver.findElement(By.id("lastName")).sendKeys("Automation");
        driver.findElement(By.id("userEmail")).sendKeys("teste@teste.com");
        driver.findElement(By.id("age")).sendKeys("36");
        driver.findElement(By.id("salary")).sendKeys("9000");
        driver.findElement(By.id("department")).sendKeys("QA");
        driver.findElement(By.id("submit")).click();
        pause();
    }

    public void criarVariosRegistrosAleatorios(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
            addButton.click();

            driver.findElement(By.id("firstName")).sendKeys("User" + i);
            driver.findElement(By.id("lastName")).sendKeys("Test" + i);
            driver.findElement(By.id("userEmail")).sendKeys("user" + i + "@teste.com");
            driver.findElement(By.id("age")).sendKeys(String.valueOf(20 + i));
            driver.findElement(By.id("salary")).sendKeys(String.valueOf(2000 + i * 100));
            driver.findElement(By.id("department")).sendKeys("QA" + i);
            driver.findElement(By.id("submit")).click();

            pause();
        }
    }

    public void deletarTodosRegistrosCriados() {
        while (true) {
            try {
                WebElement deleteButton = driver.findElement(By.cssSelector("span[title='Delete']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
                deleteButton.click();
                pause();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                break;
            }
        }
    }



    public void editarRegistroCriado() {
        removeAds();
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@id,'edit-record-')]")));
        scrollIntoView(editButton);
        editButton.click();

        WebElement ageField = driver.findElement(By.id("age"));
        ageField.clear();
        ageField.sendKeys("35");
        driver.findElement(By.id("submit")).click();
        pause();
    }

    public void deletarRegistroCriado() {
        removeAds();
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@id,'delete-record-4')]")));
        scrollIntoView(deleteButton);
        deleteButton.click();
    }

    public void clickMenuWidgets() {
        removeAds();
        WebElement widgetsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Widgets']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetsLink);
        widgetsLink.click();

    }

    public void clickMenuProgressBar() {
        removeAds();
        WebElement progressBarLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Progress Bar']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBarLink);
        progressBarLink.click();

    }

    public void clickBtnStart() {
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("startStopButton")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", startButton);
        startButton.click();
    }

    public void barraDeProgressoAte25(int percent) throws InterruptedException {
        while (true) {
            WebElement progressBar = driver.findElement(By.className("progress-bar"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBar);

            String progressText = progressBar.getText();

            // Verifica se progressText não está vazio antes de convertê-lo
            if (!progressText.isEmpty()) {
                int progressValue = Integer.parseInt(progressText.replace("%", ""));

                // Se o valor atingir o percentual especificado, interrompe o loop
                if (progressValue >= percent) {
                    break;
                }
            }

            Thread.sleep(300); // Evita execução muito rápida
        }
    }



    public void barraDeProgressoIgual25(int percent) {
        WebElement progressBar = driver.findElement(By.className("progress-bar"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBar);
        int finalProgress = Integer.parseInt(progressBar.getText().replace("%", ""));
        assert finalProgress <= percent : "Erro: A barra de progresso está acima de " + percent + "%";


    }


    public void barraDeProgressoIgual100(int percent) {
        WebElement progressBar = driver.findElement(By.className("progress-bar"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBar);
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, percent + "%"));
    }

    public void clickBtnReset() {
        WebElement resetButton = driver.findElement(By.id("resetButton"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetButton);
        resetButton.click();
    }


    public void barraDeProgressoIgual0(int percent) throws InterruptedException {
        Thread.sleep(2000);
        WebElement progressBar = driver.findElement(By.className("progress-bar"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBar);
        wait.until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "0"));
    }


    private void zoomPage(int percent) {
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='" + percent + "%'");
    }

    public void pause() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
