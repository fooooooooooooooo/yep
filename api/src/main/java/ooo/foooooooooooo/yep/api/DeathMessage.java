package ooo.foooooooooooo.yep.api;

public class DeathMessage extends YepMessage {
  private final String message;

  public DeathMessage(String player, String message) {
    super(player);

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

  public static DeathMessage fromString(String player, String str) {
    return new DeathMessage(player, str);
  }
}
