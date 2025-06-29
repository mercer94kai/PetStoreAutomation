package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class userEndpoints2 {

    static ResourceBundle getURL(){

        ResourceBundle rb= ResourceBundle.getBundle("routes");
        return rb;
    }

    public static Response createUser(user payload){

        String post_url=getURL().getString("post_url");

        Response response=given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    public static Response readUser(String userName){

        String get_url=getURL().getString("get_url");

        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(String userName, user payload){

        String update_url=getURL().getString("update_url");

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(String userName){

        String delete_url=getURL().getString("delete_url");

        Response response=given()
                .pathParam("username",userName)
                .when()
                .delete(delete_url);

        return response;
    }
}
