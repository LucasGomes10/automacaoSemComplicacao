package br.com.chronosacademy.steps;
import br.com.chronosacademy.core.Driver;
import br.com.chronosacademy.enums.Browser;
import br.com.chronosacademy.pages.LoginPage;
import br.com.chronosacademy.pages.NewAccountPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.util.Map;

public class LoginSteps {

    LoginPage loginPage;

    @Before
    public void iniciaNavegador(){
        new Driver(Browser.CHROME);
    }

    @After
    public void fecharNavegadir(){
        Driver.getDriver().quit();
    }
    @Dado("que o modal esteja sendo exibida")
    public void queOModalEstejaSendoExibida() {
        Driver.getDriver().get("https://advantageonlineshopping.com/");
        loginPage = new LoginPage();
        loginPage.clickBtnLogin();
        loginPage.visibilityOfBtnFechar();
        loginPage.aguardaLoader();
    }
    @Quando("for realizado um clique fora da modal")
    public void foraRealizadoUmCliqueForaDaModal() {

        loginPage.clickDivFechaModal();
    }
    @Entao("a janela modal deve ser fechada")
    public void aJanelaModalDeveSerFechada() throws Exception {
        try{
            loginPage.invisibilityOfBtnFechar();
        }catch(Exception e){
            throw new Exception("Ajanela modal não foi fechada");
        }
    }

    @Quando("for realizado um clique no icone de fechar modal")
    public void forRealizadoUmCliqueNoIconeDeFecharModal() {
        loginPage.clickBtnFechar();
    }

    @Quando("for realizado um clique no link Create New Account")
    public void forRealizadoUmCliqueNoLinkCreateNewAccount() {
        loginPage.clickLinkCreateAccount();
    }

    @Entao("a janela deve ser fechada")
    public void aJanelaDeveSerFechada() {
    }

    @Entao("a pagina Create Create New Account deve ser exibida")
    public void aPaginaCreateCreateNewAccountDeveSerExibida() {
        NewAccountPage newAccountPage = new NewAccountPage();
        Assert.assertEquals("CREATE ACCOUNT", newAccountPage.getTextNewAccount());
    }

    @Quando("os campos de login forem preenchidos da seguinte forma")
    public void osCamposDeLoginForemPreenchidosDaSeguinteForma(Map<String, String> map) {
        String username = map.get("login");
        String password = map.get("passsword");
        boolean remember = Boolean.parseBoolean(map.get("remember"));

        loginPage.setInpUserName(username);
        loginPage.setInpPassword(password);
        if(remember == true) loginPage.clickInpRemember();
    }

    @Quando("for realizado o clique no botao sign in")
    public void forRealizadoOCliqueNoBotaoSignIn() {
        loginPage.clickBtnSignIn();
    }

    @Entao("deve ser possivel logar no sistema")
    public void deveSerPossivelLogarNoSistema() {
        
    }

    @Entao("o sistema devera exibir uma mensagem de erro")
    public void oSistemaDeveraExibirUmaMensagemDeErro() {
        
    }

    @Entao("o botao sign in deve permanecer desabilitado")
    public void oBotaoSignInDevePermanecerDesabilitado() {
        boolean enabled = loginPage.isBtnSignIn();
        Assert.assertFalse(enabled);
    }
}
