package org.rest.test.messenger.resources;

import org.rest.test.messenger.model.Message;
import org.rest.test.messenger.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Path("/messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    private PrintWriter writer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @POST
    @Path("/response")
    public Response response() {
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message addMessages(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message updateMessages(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMessage(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long messageId) {

        return messageService.getMessage(messageId);
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String exportCube(String cube) throws IOException {

        BufferedReader bufReader = new BufferedReader(new StringReader(cube));

        ArrayList<String> text = new ArrayList<>();

        String line;

        System.out.println(cube);

        while((line = bufReader.readLine()) != null)
            text.add(line);

        return text.get(1);
    }

    @GET
    @Path("/testget")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCube() throws IOException {
        File file = new File("file.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String headers = reader.readLine();

        return headers;
    }
}
