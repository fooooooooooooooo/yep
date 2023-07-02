package ooo.foooooooooooo.yep.api;

import java.util.regex.Pattern;

public class YepApi {
  static final char UNIT_SEPARATOR = '␟';
  static final char RECORD_SEPARATOR = '␞';
  private static final Pattern SPLITTER = Pattern.compile(Pattern.quote(String.valueOf(RECORD_SEPARATOR)));

  // todo: tests
  public static String serializeMessage(YepMessage message) {
    return message.player + RECORD_SEPARATOR + message.getType().value + RECORD_SEPARATOR + message;
  }

  // todo: tests
  public static YepMessage deserializeMessage(String msg) {
    var parts = SPLITTER.split(msg);

    if (parts.length != 3) {
      throw new RuntimeException("wrong number of parts in yep message");
    }

    var playerName = parts[0];
    var messageType = parts[1];
    var message = parts[2];

    return switch (messageType) {
      case MessageType.MessageTypeValue.DEATH -> DeathMessage.fromString(playerName, message);
      case MessageType.MessageTypeValue.ADVANCEMENT -> AdvancementMessage.fromString(playerName, message);
      default -> throw new RuntimeException("invalid message type `%s`".formatted(messageType));
    };
  }

  public static final String NAMESPACE = "velocity";
  public static final String PATH = "yep";
}
