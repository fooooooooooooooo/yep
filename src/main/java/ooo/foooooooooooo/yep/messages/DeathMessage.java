package ooo.foooooooooooo.yep.messages;

public class DeathMessage implements IYepMessage {
    private final String message;

    public DeathMessage(String message) {
        this.message = message;
    }

    @Override
    public MessageType getType() {
        return MessageType.DEATH;
    }

    @Override
    public String toString() {
        return message;
    }
}
