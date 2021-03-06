package org.acme.quarkuslifecycle

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

@QuarkusTest
@WithWiremockedRemoteApis
class XxxxxxIT {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
    }

    @Test
    fun testHelloEndpointAgain() {
        given()
            .`when`().get("/hello")
            .then()
            .statusCode(200)
    }
}