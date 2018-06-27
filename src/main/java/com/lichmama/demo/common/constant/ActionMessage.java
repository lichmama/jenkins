package com.lichmama.demo.common.constant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActionMessage {
	private Message status;

	private Object data;

	public ActionMessage(Message status) {
		this.status = status;
	}
}
