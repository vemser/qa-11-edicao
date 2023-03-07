package bookstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BookStoreTest {

    @Test
    public void testBuscarTodosLivros() {

            given()
            .when()
                    .get("https://demoqa.com/BookStore/v1/Books")
            .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("books[0].isbn", equalTo("9781449325862"))
                    .body("books[-1].title", equalTo("Understanding ECMAScript 6"))
            ;
    }

    @Test
    public void testBuscarLivroPorISBN() {

        String ISBN = "9781449325862";
        var response =
                given()
                        .queryParam("ISBN", ISBN)
                .when()
                        .get("https://demoqa.com/BookStore/v1/Book")
                .then()
                        .statusCode(200)
                        .body("isbn", equalTo("9781449325862"))
                ;

        response.log().body();
    }

    @Test
    public void testCadastroLivroComSucesso() {

        var response =
                given()
                        .auth().preemptive().basic("alyson", "VemserQA11@")
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "userId": "2de5645b-cb37-4367-8a3c-76904b542011",
                                  "collectionOfIsbns": [
                                    {
                                      "isbn": "9781449325862"
                                    }
                                  ]
                                }
                                """)
                .when()
                        .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                        .statusCode(201)
                        .body("books[0].isbn", equalTo("9781449325862"))
                ;

        response.log().body();
    }

    @Test
    public void testAtualizarLivroComSucesso() {

        String ISBNUpdate = "9781449325862";

        var response =
                given()
                        .auth().preemptive().basic("alyson", "VemserQA11@")
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "userId": "2de5645b-cb37-4367-8a3c-76904b542011",
                                  "isbn": "9781449331818"
                                }
                                """)
                        .pathParam("ISBN", ISBNUpdate)
                .when()
                        .put("https://demoqa.com/BookStore/v1/Books/{ISBN}")
                .then()
                        .statusCode(200)
                        .body("books[0].isbn", equalTo("9781449331818"))
                ;

        response.log().body();
    }

    @Test
    public void testExcluirTodosLivrosComSucesso() {
        String userId = "2de5645b-cb37-4367-8a3c-76904b542011";

        var response =
                given()
                        .auth().preemptive().basic("alyson", "VemserQA11@")
                        .queryParam("UserId", userId)
                .when()
                        .delete("https://demoqa.com/BookStore/v1/Books")
                .then()
                        .statusCode(204)
                ;

        response.log().body();
    }
}
