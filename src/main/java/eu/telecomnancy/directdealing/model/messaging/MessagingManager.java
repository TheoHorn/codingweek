package eu.telecomnancy.directdealing.model.messaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class MessagingManager {

    public void sendMessage(String content, String receiver) throws Exception {
        app.getMessagingDAO().save(new Messaging(content, app.getCurrentUser().getEmail(), receiver, new Date()));
        app.notifyObservers();
    }

    public List<Messaging> getMessaging() throws Exception {
         List<Messaging> all_messages = app.getMessagingDAO().get();
         List<Messaging> messages = new ArrayList<>();
         for (Messaging message : all_messages) {
             if (app.getAccountDAO().get(message.getSender()).getEmail().equals(app.getCurrentUser().getEmail()) || app.getAccountDAO().get(message.getReceiver()).getEmail().equals(app.getCurrentUser().getEmail())) {
                 messages.add(message);
             }
         }
         return messages;
    }
}
