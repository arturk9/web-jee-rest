package org.rest.test.messenger.client;

import org.junit.Assert;
import org.junit.Test;
import org.rest.test.messenger.model.Message;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Artur on 2017-04-17.
 */
public class RestApiClient {

    public static void main (String[] args){

        Client client = ClientBuilder.newClient();

        WebTarget baseTarget = client.target("http://localhost:8080/webapi/");
        WebTarget messagesTarget = baseTarget.path("messages");
        WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
        WebTarget responseTarget = messagesTarget.path("/response");

        Message message = singleMessageTarget
                .resolveTemplate("messageId", "2")
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);

        Message newMessage = new Message(4, "My new message", "Koushik");

        Response postResponse = messagesTarget
                .request()
                .post(Entity.json(newMessage));

        if(postResponse.getStatus() != 201){
            System.out.println("Error");
        }
        Message createdMessage = postResponse.readEntity(Message.class);

        Response anotherPostResponse = responseTarget
                .request()
                .post(Entity.json("Error"));

        System.out.println(createdMessage.getMessage());

        System.out.println(anotherPostResponse.getStatus());
    }
}
