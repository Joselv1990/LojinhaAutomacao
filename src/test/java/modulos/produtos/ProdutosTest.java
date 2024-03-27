package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        // abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // vou maximizar a tela
        this.navegador.manage().window().maximize();

        // vou definir um tempo de espera padrão 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // navegar para a pagina da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @Test
    @DisplayName("Não e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorZerado() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("playstation")
                .informarValorProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor acima de 7.000,00")
    public void testeNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorProduto("700001")
                .informarCoresDoProduto("preto,azul")
                .submeterFormularioComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorProduto("001")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);



    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Cabo flex")
                .informarValorProduto("700000")
                .informarCoresDoProduto("preto, azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }



    @AfterEach
    public void afterEach() {

        //fechar o navegador
        navegador.quit();
    }

}

