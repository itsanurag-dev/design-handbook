package com.codex.system.design.whatsapp;

import java.util.Queue;

public class Messagenger {
	// Send Msg Flow
		public void sendMsg(String msg, Sender sender, Receiver receiver){
			pushMsgToUserQueue(sender, true, msg);
			pushMsgToUserQueue(receiver, receiver.isReceiverAvailable(receiver, msg), msg);	
		}
		public void sendMsg(String msg, Sender sender, Group group){
			pushMsgToUserQueue(sender, true, msg);
			pushMsgToUserQueue(group.getReceiver(),group.getReceiver(). isReceiverAvailable(group.getReceiver(), msg), msg);
		}
		private void pushMsgToUserQueue(SystemUser user, boolean online, String msg){
			Queue<MessagingQueue> userQueue = getUserQueueById(user);
			userQueue.offer(new MessagingQueue(user, msg));
		}
		
		private Queue<MessagingQueue> getUserQueueById(SystemUser user){
			if(user.getActiveQueue() != null){
				return user.getActiveQueue();
			}
			return new MessagingQueue(user, null);
		}
		private boolean isReceiverAvailable(int userId){
			// Check WebSocket Connection with the user based on userID
			if(SystemUtil.socketConnection() != null){
				return true;
			}
			
			return true ? SystemUtil.dbConnection.UpdateUserData(userId)  : false;
		}
		
		public void getHeartbeatRequest(Sender user){
			if(SystemUtil.socketConnection !=null){
				Queue<MessagingQueue> userQueue = getUserQueueById(user);
				broadcastMsg(userQueue);
			}
		}
		private void broadcastMsg(Queue<MessagingQueue> userQueue) {
			// TODO Auto-generated method stub
			
		}
	}

