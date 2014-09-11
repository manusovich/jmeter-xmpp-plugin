package com.manusovich.jmeter.sampler.connect;

import com.manusovich.jmeter.XmppClient;
import com.manusovich.jmeter.XmppControllerHolder;
import com.manusovich.jmeter.controller.XmppController;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;

/**
 * Created by Alex Manusovich on 9/10/14.
 */
public class XmppConnectSampler extends AbstractSampler implements TestBean, XmppControllerHolder {
    private String xmppServer;
    private String xmppServerPort;
    private String senderJid;
    private String senderPassword;
    private XmppController xmppController;

    @Override
    public SampleResult sample(Entry entry) {
        xmppInit();
        return new SampleResult();
    }

    private void xmppInit() {
        try {
            System.out.println(this + " xmpp connection - new connection (controller " + xmppController + ")");
            if (xmppController.getClient(senderJid) != null) {
                System.out.println("xmpp connection exist - disconnect");
                xmppController.disconnectClient(senderJid);
            }
            xmppController.initClient(senderJid, new XmppClient());
            System.out.println("xmpp connection - new connection");
            xmppController.getClient(senderJid)
                    .connect(getXmppServer(), getSenderJid(), getSenderPassword());
        } catch (Throwable th) {
            System.out.println(this + "xmpp connection - failed");
        }
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

    public void initXmppController(XmppController xmppController) {
        this.xmppController = xmppController;
    }
}
