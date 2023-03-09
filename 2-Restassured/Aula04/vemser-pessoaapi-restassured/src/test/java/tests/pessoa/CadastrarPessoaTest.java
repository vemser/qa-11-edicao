package tests.pessoa;

import dataFactory.PessoaDataFactory;
import model.JSONFailureResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class CadastrarPessoaTest extends BaseTest {

    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Deve cadastrar pessoa com sucesso")
    public void testDeveCadastrarPessoaComSucesso() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();

        PessoaModel pessoaCadastrada = pessoaService.cadastrarPessoa(pessoaValida)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                        .as(PessoaModel.class)
                ;

        idPessoa = pessoaCadastrada.getIdPessoa();

        Assertions.assertNotNull(pessoaCadastrada.getIdPessoa());
        Assertions.assertEquals(pessoaValida.getNome(), pessoaCadastrada.getNome());
        Assertions.assertEquals(pessoaValida.getCpf(), pessoaCadastrada.getCpf());
        Assertions.assertEquals(pessoaValida.getDataNascimento(), pessoaCadastrada.getDataNascimento());

        pessoaService.deleterPessoa(idPessoa).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Deve cadastrar pessoa com nome em branco")
    public void testDeveCadastrarPessoaComNomeEmBranco() {
        PessoaModel pessoaComNomeEmBranco = PessoaDataFactory.pessoaComNomeEmBranco();

        JSONFailureResponse response = pessoaService.cadastrarPessoa(pessoaComNomeEmBranco)
                .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                            .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("nome: must not be blank", response.getErrors().get(0));
    }
}
