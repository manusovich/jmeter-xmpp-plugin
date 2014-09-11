package com.manusovich.jmeter.sampler.message;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TextAreaEditor;

import java.beans.PropertyDescriptor;

/**
 * Created by Alex Manusovich on 9/9/14.
 */
public class XmppMessageSamplerBeanInfo extends BeanInfoSupport {
    private static final String RECEIVER_JID = "receiverJid";
    private static final String SENDER_JID = "senderJid";
    private static final String MESSAGE = "message";

    private static final String GROUP = "xmpp-plugin-message-sampler";

    public XmppMessageSamplerBeanInfo() {
        super(XmppMessageSampler.class);

        getBeanDescriptor().getValue(RESOURCE_BUNDLE);

        createPropertyGroup(GROUP, new String[]{SENDER_JID, RECEIVER_JID, MESSAGE});

        setupProperty(SENDER_JID);
        setupProperty(RECEIVER_JID);
//        setupProperty(MESSAGE);

        PropertyDescriptor p = property(MESSAGE);
        p.setValue(DEFAULT, "");
        p.setPropertyEditorClass(TextAreaEditor.class);
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
    }

    private void setupProperty(String propertyIdentifier) {
        PropertyDescriptor p = property(propertyIdentifier);
        p.setValue(DEFAULT, "");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(NOT_EXPRESSION, Boolean.TRUE);
    }

}