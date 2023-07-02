package ooo.foooooooooooo.yep.api;

public enum MessageType {
  DEATH(MessageTypeValue.DEATH),
  ADVANCEMENT(MessageTypeValue.ADVANCEMENT);

  public final String value;

  MessageType(String value) {
    this.value = value;
  }

  public static class MessageTypeValue {
    public static final String DEATH = "death";
    public static final String ADVANCEMENT = "advancement";
  }
}
