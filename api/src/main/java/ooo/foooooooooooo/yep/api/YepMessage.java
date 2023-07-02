package ooo.foooooooooooo.yep.api;

public abstract class YepMessage {
  public final String player;

  protected YepMessage(String player) { this.player = player; }

  public abstract MessageType getType();

  @Override
  public abstract String toString();
}
