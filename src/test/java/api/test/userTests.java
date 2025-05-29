package api.test;

import api.endpoints.userEndpoints;
import api.payload.user;
import api.utilities.extentReportManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class userTests {

    Faker faker;
    user userPayload;

    private static final Logger logger = LogManager.getLogger(userTests.class);

    @BeforeClass
    public void setupData(){

        faker=new Faker();
        userPayload=new user();

        userPayload.setId(faker.number().numberBetween(10,50));
        userPayload.setUsername(faker.regexify("[a-zA-Z]{8}"));
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void createUserTest() throws JsonProcessingException {

        logger.info("\n   **Executing Create User Scenario...................\n ");

 /* <To check the payload data>

        ObjectMapper om=new ObjectMapper();
        String payload= om.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
        System.out.println(payload);
*/
       Response response=userEndpoints.createUser(userPayload);
       response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void readUserByNameTest() throws InterruptedException {

        Thread.sleep(5000);
        logger.info("\n   **Executing Get User Data Scenario...................\n ");

        Response response=userEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 3)
    public void updateUserByNameTest() throws InterruptedException {

        logger.info("\n   **Executing Update User Data Scenario...................\n ");

        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());


        Response response=userEndpoints.updateUser(this.userPayload.getUsername(),this.userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

        readUserByNameTest();
    }

  @Test(priority = 4)
    public void deleteUserTest() throws InterruptedException {

        Thread.sleep(3000);
      logger.info("\n   **Executing Delete User Scenario...................\n ");

        Response response=userEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();


        Assert.assertEquals(response.getStatusCode(),200);

    }

}
