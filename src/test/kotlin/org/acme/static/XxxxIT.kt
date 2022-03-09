package org.acme.static

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ClasspathFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource
import com.github.tomakehurst.wiremock.stubbing.InMemoryStubMappings
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

@QuarkusTest
class XxxxIT {

    companion object Setup {
        private val server = WireMockServer(WireMockConfiguration.options().port(8082))

        @BeforeAll
        @JvmStatic
        fun initRemoteEndpoints() {
            val mappingSource = JsonFileMappingsSource(ClasspathFileSource("wiremock"))
            mappingSource.loadMappingsInto(InMemoryStubMappings())

            server.loadMappingsUsing(mappingSource)
            server.start()
        }

        @AfterAll
        @JvmStatic
        fun tearDownRemoteEndpoints() {
            server.stop()
        }
    }

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