package tests.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {

        // Configurar os dados da Pesso API
        RestAssured.baseURI = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api";
        //RestAssured.port = 39000;
        //RestAssured.basePath = "/vemser/dbc-pessoa-api";

        // Habilitando o log da requisição e resposta se a validação
        // do teste Rest Assured falhar.
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
