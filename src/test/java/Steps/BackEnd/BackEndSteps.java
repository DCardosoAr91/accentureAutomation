package Steps.BackEnd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.UUID;
import java.util.List;
import java.util.Random;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BackEndSteps {

    private String userId;
    private String token;
    private String username;
    private final String password = "Brasil@12345";
    private Response response;

    private enum Endpoints {
        CREATE_USER("/Account/v1/User"),
        GENERATE_TOKEN("/Account/v1/GenerateToken"),
        AUTHORIZED("/Account/v1/Authorized"),
        USER_DETAILS("/Account/v1/User/"),
        BOOKS("/BookStore/v1/Books");

        private final String path;
        Endpoints(String path) { this.path = path; }
        public String getPath() { return path; }
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "https://demoqa.com";
        username = "usuario_" + UUID.randomUUID().toString().substring(0, 6);
    }

    private String buildUserPayload() {
        return """
                {
                    "userName": "%s",
                    "password": "%s"
                }
                """
                .formatted(username, password);
    }

    @Dado("que eu crio um usuário com nome e senha válidos")
    public void createValidUserPwd() {
                response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(buildUserPayload())
                .when()
                .post(Endpoints.CREATE_USER.getPath())
                .then()
                .log().all()
                .statusCode(201)
                .body("username", equalTo(username))
                .extract().response();

        userId = response.jsonPath().getString("userID");
        System.out.println("\n🙋‍♂️ Usuário criado: " + username + " | ID: " + userId + "\n");
    }

    @Dado("gero um token de acesso para o usuário criado")
    public void genTokenAccess() {
                response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(buildUserPayload())
                .when()
                .post(Endpoints.GENERATE_TOKEN.getPath())
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        token = response.jsonPath().getString("token");
        System.out.println("🔑 Token gerado com sucesso!" + "\n");
    }

    @Dado("verifico se o usuário está autorizado")
    public void verifyAuthUser() {
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(buildUserPayload())
                .when()
                .post(Endpoints.AUTHORIZED.getPath())
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("✅ Usuário autorizado com sucesso." + "\n");
    }

    @Quando("eu consulto a lista de livros disponíveis")
    public void searchAvaliableBooks() {
                response = given()
                .log().all()
                .when()
                .get(Endpoints.BOOKS.getPath())
                .then()
                .log().all()
                .statusCode(200)
                .body("books.size()", greaterThan(0))
                .extract().response();

        System.out.println("📚 Lista de livros disponíveis retornada. \n");
    }

    @Quando("seleciono dois livros e associo ao usuário")
    public void selectAssociateBooks() {
        Response livrosResponse = given()
                .header("accept", "application/json")
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<String> isbns = livrosResponse.jsonPath().getList("books.isbn");

        Random random = new Random();
        int index1 = random.nextInt(isbns.size());
        int index2;
        do {
            index2 = random.nextInt(isbns.size());
        } while (index1 == index2);

        String isbn1 = isbns.get(index1);
        String isbn2 = isbns.get(index2);

        String payload = String.format(
        """
        {
          "userId": "%s",
          "collectionOfIsbns": [
            {"isbn": "%s"},
            {"isbn": "%s"}
          ]
        }
        """
                , userId, isbn1, isbn2);

        given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);

        System.out.printf("\n📚🙋‍♂️ Livros (ISBN) [%s, %s] associados ao usuário com sucesso.\n", isbn1, isbn2);

    }

    @Então("os dados do usuário devem conter os dois livros escolhidos")
    public void choosedUserBooks() {
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(Endpoints.USER_DETAILS.getPath() + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo(username))
                .body("books.size()", is(2))
                .extract().response();

        System.out.println("📚 Usuário contém os livros alugados:\n" + response.getBody().asPrettyString());
    }

}
