package ooo.foooooooooooo.yep.api;

import java.util.regex.Pattern;

import static ooo.foooooooooooo.yep.api.YepApi.UNIT_SEPARATOR;

public class AdvancementMessage extends YepMessage {
  private final String title;
  private final String description;

  public AdvancementMessage(String player, String title, String description) {
    super(player);

    this.title = title;
    this.description = description;
  }

  @Override
  public MessageType getType() {
    return MessageType.ADVANCEMENT;
  }


  @Override
  public String toString() {
    return title + UNIT_SEPARATOR + description;
  }

  private static final String SPLIT = Pattern.quote(String.valueOf(UNIT_SEPARATOR));

  public static AdvancementMessage fromString(String player, String str) {
    var parts = str.split(SPLIT);

    return new AdvancementMessage(player, parts[0], parts[1]);
  }
}
