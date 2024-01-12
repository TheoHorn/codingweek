package eu.telecomnancy.directdealing.model.messaging;

import java.sql.SQLException;
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
             if (message.getSender().equals(app.getCurrentUser().getEmail()) && message.getReceiver().equals(app.getLastAccount().getEmail()) || message.getSender().equals(app.getLastAccount().getEmail()) && message.getReceiver().equals(app.getCurrentUser().getEmail())) {
                 messages.add(message);
             }
         }
         return messages;
    }

    public List<Messaging> getConversationMessage(String converser) throws SQLException {
        List<Messaging> all_messages = app.getMessagingDAO().get();
        List<Messaging> messages = new ArrayList<>();
        for (Messaging message : all_messages) {
            if (message.getSender().equals(app.getCurrentUser().getEmail()) && message.getReceiver().equals(converser) || message.getSender().equals(converser) && message.getReceiver().equals(app.getCurrentUser().getEmail())) {
                messages.add(message);
            }
        }
        return messages;

    }


    public List<String> getConversers(String me) {
        List<Messaging> messagingList = app.getMessagingDAO().get();
        List<String> conversers = new ArrayList<String>();
        for (Messaging messaging : messagingList) {
            if ((messaging.getSender().equals(me)) && (!conversers.contains(messaging.getReceiver())) && (!messaging.getReceiver().equals(me))) {
                conversers.add(messaging.getReceiver());
            } else if ((messaging.getReceiver().equals(me)) && (!conversers.contains(messaging.getSender()))  && (!messaging.getSender().equals(me))) {
                conversers.add(messaging.getSender());
            }
        }
        return conversers;
    }

    public void delete(Messaging messaging) throws SQLException {
        // Delete the messaging
        app.getMessagingDAO().delete(messaging.getIdMsg());
    }
}
