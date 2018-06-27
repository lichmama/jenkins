package com.lichmama.demo.common.constant;

public class ActionStatus {

	public static final Message SUCCESS = new Message(0, "success");

	public static final Message ERROR = new Message(500, "internal server error");

	public static ActionMessage success() {
		return new ActionMessage(SUCCESS);
	}

	public static ActionMessage success(Object data) {
		ActionMessage actionMsg = new ActionMessage(SUCCESS);
		actionMsg.setData(data);
		return actionMsg;
	}

	public static ActionMessage error() {
		return new ActionMessage(ERROR);
	}
}
