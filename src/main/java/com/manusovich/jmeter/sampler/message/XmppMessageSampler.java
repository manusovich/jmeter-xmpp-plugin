package com.manusovich.jmeter.sampler.message;

import com.manusovich.jmeter.XmppControllerHolder;
import com.manusovich.jmeter.controller.XmppController;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;

/**
 * Created by Alex Manusovich on 9/10/14.
 */
public class XmppMessageSampler extends AbstractSampler implements TestBean, XmppControllerHolder {
    private String message;
    private String senderJid;
    private String receiverJid;
    private XmppController xmppController;

    @Override
    public SampleResult sample(Entry entry) {
        System.out.println(this + " message from (" + senderJid + ") to (" + receiverJid + "): " + getMessage());
        xmppController.getClient(senderJid).sendMessage(getReceiverJid(), getMessage());
        SampleResult sampleResult = new SampleResult();
        sampleResult.setSampleLabel("XmppMessageSampler");
        sampleResult.setResponseMessageOK();
        sampleResult.setResponseCodeOK();
        sampleResult.setResponseOK();
        return sampleResult;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverJid() {
        return receiverJid;
    }

    public void setReceiverJid(String receiverJid) {
        this.receiverJid = receiverJid;
    }

    public void initXmppController(XmppController xmppController) {
        this.xmppController = xmppController;
    }


    public String getSenderJid() {
        return senderJid;
    }

    public void setSenderJid(String senderJid) {
        this.senderJid = senderJid;
    }
}
