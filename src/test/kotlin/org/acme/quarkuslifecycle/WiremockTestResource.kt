package org.acme.quarkuslifecycle

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ClasspathFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource
import com.github.tomakehurst.wiremock.stubbing.InMemoryStubMappings
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager
import java.util.concurrent.atomic.AtomicReference

class WiremockServerTestResourceSlow: QuarkusTestResourceConfigurableLifecycleManager<WithWiremockedRemoteApis> {

    val server = WireMockServer(WireMockConfiguration.options().dynamicPort())
    val name = AtomicReference<String>()

    override fun init(annotation: WithWiremockedRemoteApis) {
        name.set(annotation.name)
    }

    override fun start(): MutableMap<String, String> {
        val mappingSource = JsonFileMappingsSource(ClasspathFileSource("wiremock"))
        mappingSource.loadMappingsInto(InMemoryStubMappings())

        server.loadMappingsUsing(mappingSource)
        server.start()

        return mutableMapOf(
            "org.acme.UserClient/mp-rest/url" to server.baseUrl()
        )
    }

    @Synchronized
    override fun stop() {
        server.stop()
    }
}


@QuarkusTestResource(WiremockServerTestResourceSlow::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class WithWiremockedRemoteApis(
    val name: String = "Frufru"
)

