package com.mdimension.jchronic.tags;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Token;

public class SeparatorIn extends Separator {
  private static final Pattern IN_PATTERN = Pattern.compile("^in$");

  public SeparatorIn(Separator.SeparatorType type) {
    super(type);
  }

  @Override
  public String toString() {
    return super.toString() + "-in";
  }

  public static SeparatorIn scan(Token token, Options options) {
    Map<Pattern, Separator.SeparatorType> scanner = new HashMap<Pattern, Separator.SeparatorType>();
    scanner.put(SeparatorIn.IN_PATTERN, Separator.SeparatorType.IN);
    for (Pattern scannerItem : scanner.keySet()) {
      if (scannerItem.matcher(token.getWord()).matches()) {
        return new SeparatorIn(scanner.get(scannerItem));
      }
    }
    return null;
  }
}
