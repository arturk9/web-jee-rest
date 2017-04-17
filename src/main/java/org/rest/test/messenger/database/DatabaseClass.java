package org.rest.test.messenger.database;

import org.rest.test.messenger.model.Message;
import org.rest.test.messenger.model.Profile;
import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<Long, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages(){
        return messages;
    }

    public static Map<Long, Profile> getProfiles(){
        return profiles;
    }
}
