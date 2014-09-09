package com.manusovich.jmeter;

import org.apache.jmeter.control.GenericController;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.TestStateListener;

/**
 * Created by Alex Manusovich on 9/9/14.
 */
public class XmppController extends GenericController implements TestBean, TestStateListener, SampleListener {
    private String xmppServer;
    private String xmppServerPort;
    private String senderJid;
    private String senderPassword;
    private String receiverJid;

    @Override
    public void sampleOccurred(SampleEvent sampleEvent) {
        XmppClient client = new XmppClient();
        client.connect(getXmppServer(), getSenderJid(), getSenderPassword());
        System.out.println("XMPP msg to " + getReceiverJid());
        client.sendMessage(getReceiverJid(), "Hello from jmeter");
        client.disconnect();
    }

    @Override
    public void sampleStarted(SampleEvent sampleEvent) {

    }

    @Override
    public void sampleStopped(SampleEvent sampleEvent) {

    }

    @Override
    public void testStarted() {

    }

    @Override
    public void testStarted(String s) {

    }

    @Override
    public void testEnded() {

    }

    @Override
    public void testEnded(String s) {

    }


    public String getXmppServer() {
        return xmppServer;
    }

    public void setXmppServer(String xmppServer) {
        this.xmppServer = xmppServer;
    }

    public String getXmppServerPort() {
        return xmppServerPort;
    }

    public void setXmppServerPort(String xmppServerPort) {
        this.xmppServerPort = xmppServerPort;
    }

    public String getSenderJid() {
        return senderJid;
    }

    public void setSenderJid(String senderJid) {
        this.senderJid = senderJid;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public String getReceiverJid() {
        return receiverJid;
    }

    public void setReceiverJid(String receiverJid) {
        this.receiverJid = receiverJid;
    }
}
