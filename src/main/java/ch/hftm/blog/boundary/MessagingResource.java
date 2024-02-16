package ch.hftm.blog.boundary;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/messaging")
public class MessagingResource {

    private final MessagingTesting messagingTesting;

    public MessagingResource(MessagingTesting messagingTesting) {
        this.messagingTesting = messagingTesting;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSourceData() {
        return messagingTesting.source().collectItems().asList().await().indefinitely().toString();
    }
}
