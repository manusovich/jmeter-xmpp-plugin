package com.manusovich.jmeter.controller;

import com.manusovich.jmeter.XmppClient;
import com.manusovich.jmeter.XmppControllerHolder;
import org.apache.jmeter.control.GenericController;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.TestStateListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Manusovich on 9/9/14.
 */
public class XmppController extends GenericController implements TestBean, TestStateListener {
    private static Map<String, XmppClient> clients = new HashMap<String, XmppClient>();

    @Override
    public Sampler next() {
        Sampler next = super.next();
        if (next instanceof XmppControllerHolder) {
            ((XmppControllerHolder) next).initXmppController(this);
        }
        return next;
    }

    @Override
    public void testStarted() {
    }

    @Override
    public void testStarted(String s) {
    }

    @Override
    public void testEnded() {
        testEnded("");
    }

    @Override
    public void testEnded(String s) {
        System.out.println(this + " testEnded " + clients.size());
        while (clients.size() > 0) {
            disconnectClient(clients.keySet().iterator().next());
        }
    }

    public void disconnectClient(String cs) {
        clients.remove(cs).disconnect();
    }


    public XmppClient getClient(String jid) {
        return clients.get(jid);
    }

    public void initClient(String jid, XmppClient client) {
        System.out.println(this + " client (" + jid + ") setup " + client);
        clients.put(jid, client);
    }
}
