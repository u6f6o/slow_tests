package org.acme

import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlin.random.Random

@Path("/hello")
class UserGreetingResource(
    @RestClient
    val userClient: UserClient
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun hello(): String {
        val user = userClient.getUser(Random.nextInt(0, 1000))
        return "Hello ${user.firstName} !"
    }
}