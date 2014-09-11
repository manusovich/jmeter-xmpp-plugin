package com.manusovich.jmeter;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import java.util.UUID;

/**
 * Created by Alex Manusovich on 9/9/14.
 */
public class XmppClient {
    private XMPPConnection connection;

    public void connect(String xmppServerAddress, final String jabberUsername, String jabberPassword) {
        SmackConfiguration.setPacketReplyTimeout(5000);
        ConnectionConfiguration xmppConfig = new ConnectionConfiguration(xmppServerAddress, 5222);
        xmppConfig.setSASLAuthenticationEnabled(false);
        xmppConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);

        connection = new XMPPConnection(xmppConfig);
        try {
            connection.connect();
            System.out.println("Signon " + jabberUsername + " " + jabberPassword);
            connection.login(jabberUsername, jabberPassword);
            System.out.println("Signon " + jabberUsername + " " + jabberPassword + " - Success");
        } catch (XMPPException e) {
            System.out.println("e = " + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(final String contact, String message) {
        try {
            MessageListener listener = new MessageListener() {
                public void processMessage(Chat chat, Message message) {
                    System.out.println("XmppClient message (" + contact + "):" + message.toString());
                }
            };
            Chat chat = connection.getChatManager().createChat(contact, listener);
            int min = (int) (Math.random() * 60);
            chat.sendMessage("{\n" +
                    "    \"message\": {\n" +
                    "        \"formatVersion\": {\n" +
                    "            \"version\": \"2\"\n" +
                    "        },\n" +
                    "        \"id\": \"" + UUID.randomUUID().toString() + "\",\n" +
                    "        \"senderTime\": " + System.currentTimeMillis() + ",\n" +
                    "        \"expirationPeriodOffline\": " + (min * 60 * 1000) + ",\n" +
                    "        \"text\": {\n" +
                    "            \"plain\": \"" + message + "\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
            chat.removeMessageListener(listener);
        } catch (XMPPException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        String user = connection.getUser();
        System.out.println("Disconnect = " + user);
        try {
            connection.sendPacket(new Presence(Presence.Type.unavailable));
            connection.disconnect();
        } catch (Throwable th) {
            System.out.println("Disconnect = " + user + " - failed");
        }
    }
}
