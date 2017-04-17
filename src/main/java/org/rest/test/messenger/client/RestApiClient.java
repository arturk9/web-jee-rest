package org.rest.test.messenger.client;

import org.junit.Assert;
import org.junit.Test;
import org.rest.test.messenger.model.Message;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Artur on 2017-04-17.
 */
public class RestApiClient {

    public static void main (String[] args){

        Client client = ClientBuilder.newClient();

        String response = client
                .target("http://localhost:8080/webapi/messages/1")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println(response);
    }
}
