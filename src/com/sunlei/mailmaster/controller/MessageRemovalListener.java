package com.sunlei.mailmaster.controller;

import com.sunlei.mailmaster.mail.Message;

public interface MessageRemovalListener {
    public void messageRemoved(Message message);
}
