package com.manusovich.jmeter;

import org.apache.jmeter.testbeans.BeanInfoSupport;

import java.beans.PropertyDescriptor;

/**
 * Created by Alex Manusovich on 9/9/14.
 */
public class XmppControllerBeanInfo extends BeanInfoSupport {
    private static final String XMPP_SERVER = "xmppServer";
    private static final String XMPP_SERVER_PORT = "xmppServerPort";

    private static final String RECEIVER_JID = "receiverJid";
    private static final String SENDER_JID = "senderJid";
    private static final String SENDER_PASSWORD = "senderPassword";

    private static final String GROUP = "xmpp-plugin-controller";

    public XmppControllerBeanInfo() {
        super(XmppController.class);

        getBeanDescriptor().getValue(RESOURCE_BUNDLE);

        createPropertyGroup(GROUP, new String[]{XMPP_SERVER});
        createPropertyGroup(GROUP, new String[]{XMPP_SERVER_PORT});
        createPropertyGroup(GROUP, new String[]{RECEIVER_JID});
        createPropertyGroup(GROUP, new String[]{SENDER_JID});
        createPropertyGroup(GROUP, new String[]{SENDER_PASSWORD});

        setupProperty(XMPP_SERVER);
        setupProperty(XMPP_SERVER_PORT);
        setupProperty(RECEIVER_JID);
        setupProperty(SENDER_JID);
        setupProperty(SENDER_PASSWORD);
    }

    private void setupProperty(String propertyIdentifier) {
        PropertyDescriptor p = property(propertyIdentifier);
        p.setValue(DEFAULT, "");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
    }

}