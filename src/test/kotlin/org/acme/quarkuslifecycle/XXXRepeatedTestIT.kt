package org.acme.quarkuslifecycle

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.RepeatedTest

@QuarkusTest
@WithWiremockedRemoteApis
class XXXRepeatedTestIT {

    @RepeatedTest(2)
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
    }

    @RepeatedTest(6)
    fun testHelloEndpointAgain() {
        given()
            .`when`().get("/hello")
            .then()
            .statusCode(200)
    }
}