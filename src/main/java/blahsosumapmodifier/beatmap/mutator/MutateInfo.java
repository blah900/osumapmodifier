package blahsosumapmodifier.beatmap.mutator;

import org.ejml.simple.SimpleMatrix;

public class MutateInfo {
  SimpleMatrix centerTranslator;

  public SimpleMatrix getCenterTranslator() {
    return centerTranslator;
  }

  private MutateInfo() {
  };

  public static class Builder {
    private MutateInfo info = new MutateInfo();

    public Builder setCenterTranslator(SimpleMatrix translator) {
      info.centerTranslator = translator;
      return this;
    }

    public MutateInfo build() {
      return info;
    }
  }
}
