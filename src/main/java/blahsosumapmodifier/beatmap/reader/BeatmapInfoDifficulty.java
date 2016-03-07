package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class BeatmapInfoDifficulty extends BeatmapInfoFragment{
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(".#");
  public static enum Setting {
    HPDrainRate, CircleSize, OverallDifficulty, ApproachRate, SliderMultiplier, SliderTickRate;
  }

  public BeatmapInfoDifficulty(List<String> lines) {
    super(lines, false);
  }

  public double get(Setting setting) {
    return Double.valueOf(lines.get(setting.ordinal()));
  }

  public void set(Setting setting, double value) {
    lines.set(setting.ordinal(), setting.name() + ":" + DECIMAL_FORMAT.format(value));
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Difficulty]", writer);
  }

}
