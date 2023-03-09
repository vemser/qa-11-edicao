package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PessoaModel;
import utils.Autenticacao;

import static io.restassured.RestAssured.*;

public class PessoaService {

    public Response cadastrarPessoa(PessoaModel pessoa) {

        Response response =
            given()
                    .header("Authorization", Autenticacao.token())
                    .contentType(ContentType.JSON)
                    .body(pessoa)
            .when()
                    .post("/pessoa")
            ;

        return response;
    }

    public Response deleterPessoa(Integer idPessoa) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .pathParam("idPessoa", idPessoa)
                .when()
                        .delete("/pessoa/{idPessoa}")
                ;

        return response;
    }
}
