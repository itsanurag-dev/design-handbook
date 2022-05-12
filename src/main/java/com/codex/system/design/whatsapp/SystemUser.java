package com.codex.system.design.whatsapp;

import java.util.Queue;

public class SystemUser {
	int userId;
	String name;
	public Queue<MessagingQueue> getActiveQueue() {
		// TODO Auto-generated method stub
		return new MessagingQueue(null, name);
	}

}
