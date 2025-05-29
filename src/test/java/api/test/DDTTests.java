package api.test;

import api.endpoints.userEndpoints;
import api.payload.user;
import api.utilities.dataProvider;



import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class DDTTests {

    user userPayload;

    @Test(priority = 1,dataProvider = "allData",dataProviderClass = dataProvider.class)
    public void createUserTest(String userID,String userName,String fn, String ln,String email, String pwd, String ph){

        userPayload=new user();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(fn);
        userPayload.setLastname(ln);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);


        Response response= userEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }



    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = dataProvider.class)
    public void deleteUserTest(String userName){

        Response response= userEndpoints.deleteUser(userName);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
