package messages.model;

public interface MessagesInterface {

	public Integer doAddOneMsg(MessagesVO oneMessagesVO);

	public MessagesVO doGetOneMsgById(Integer oneMsgId);

}
