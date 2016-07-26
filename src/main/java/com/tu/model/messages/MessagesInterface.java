package com.tu.model.messages;

public interface MessagesInterface {

	Integer doAddOneMsg(MessagesVO oneMessagesVO);

	MessagesVO doGetOneMsgById(Integer oneMsgId);

}
