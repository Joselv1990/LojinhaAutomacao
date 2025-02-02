package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage (WebDriver navegador){
        this.navegador = navegador;

    }

    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;

    }

    public FormularioDeAdicaoDeProdutoPage informarValorProduto(String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;

    }

    public FormularioDeAdicaoDeProdutoPage informarCoresDoProduto(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;

    }

    public ListaDeProdutosPage submeterFormularioComErro(){

        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);

    }

}
