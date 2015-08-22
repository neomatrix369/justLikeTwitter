package command;

import domain.CommandType;
import domain.MessagePosted;
import domain.User;
import domain.UserTypedCommand;

import java.util.List;

import static helper.ImplHelper.USER_FIELD;

public class ReadPostCommand extends CommandExecutorImpl {

    public ReadPostCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        User user = createNewUserFrom(commandTokens, USER_FIELD);
        return getFormattedMessageFor(user);
    }

    private String getFormattedMessageFor(User user) {
        StringBuilder result = new StringBuilder();

        List<MessagePosted> messagesPosted = messageStore.getMessagesFor(user);

        for (MessagePosted messagePosted : messagesPosted) {
            buildTimeLine(result, messagePosted);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, MessagePosted messagePosted) {
        result.append(getFormattedMessage(messagePosted))
              .append(System.lineSeparator());
    }
}
