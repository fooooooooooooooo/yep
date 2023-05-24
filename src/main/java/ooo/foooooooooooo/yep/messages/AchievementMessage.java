package ooo.foooooooooooo.yep.messages;

public class AchievementMessage implements IYepMessage {
    private final String title;
    private final String description;

    public AchievementMessage(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public MessageType getType() {
        return MessageType.ADVANCEMENT;
    }

    @Override
    public String toString() {
        return title + "|" + description;
    }
}
