package org.acme

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam


@Path("/api/v3/user/{userId}")
@RegisterRestClient(configKey = "user-api")
interface UserClient {

    @GET
    suspend fun getUser(@PathParam("userId") userId: Int): User
}