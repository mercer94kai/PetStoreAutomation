package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class userEndpoints {

    public static Response createUser(user payload){

        Response response=given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                .when()
                .post(routes.post_url);

        return response;
    }

    public static Response readUser(String userName){

        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(routes.get_url);

        return response;
    }

    public static Response updateUser(String userName, user payload){

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(routes.update_url);

        return response;
    }

    public static Response deleteUser(String userName){

        Response response=given()
                .pathParam("username",userName)
                .when()
                .delete(routes.delete_url);

        return response;
    }
}
