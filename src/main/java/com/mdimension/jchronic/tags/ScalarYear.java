package com.mdimension.jchronic.tags;

import java.util.regex.Pattern;

import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.utils.Token;

public class ScalarYear extends Scalar {
  public static final Pattern YEAR_PATTERN = Pattern.compile("^([1-9]\\d)?\\d\\d?$");

  public ScalarYear(Integer type) {
    super(type);
  }

  @Override
  public String toString() {
    return super.toString() + "-year-" + getType();
  }

  public static ScalarYear scan(Token token, Token postToken, Options options) {
    if (ScalarYear.YEAR_PATTERN.matcher(token.getWord()).matches()) {
      int scalarValue = Integer.parseInt(token.getWord());
      if (!(postToken != null && Scalar.TIMES.contains(postToken.getWord()))) {
        Pointer.PointerType context = options.getContext();
        switch (context) {
          case NONE:
            // If the context indicates no bias,
            // treat anything up to 37 as 20xx, anything from 69-99 as 19xx,
            // anything from 100-137 as 20xx, and leave 38-68 alone (treating
            // that range as ambiguous, I guess).
            // This used to be the behavior regardless of the context.
            if (scalarValue <= 37) {
              scalarValue += 2000;
            }
            else if (scalarValue <= 137 && scalarValue >= 69) {
              scalarValue += 1900;
            }
            break;

          case PAST:
            // If the context indicates a bias to past dates,
            // treat anything from 20-99 as 19xx, and otherwise
            // match the NONE behavior.
            if (scalarValue <= 19) {
              scalarValue += 2000;
            }
            else if (scalarValue <= 99) {
              scalarValue += 1900;
            }
            else if (scalarValue <= 137) {
              scalarValue += 1900;
            }
            break;

          case FUTURE:
            // If the context indicates a bias to future dates,
            // treat anything up to 68 as 20xx, and otherwise
            // match the NONE behavior.
            if (scalarValue <= 68) {
              scalarValue += 2000;
            }
            else if (scalarValue <= 137) {
              scalarValue += 1900;
            }
            break;
        }
        return new ScalarYear(Integer.valueOf(scalarValue));
      }
    }
    return null;
  }
}
