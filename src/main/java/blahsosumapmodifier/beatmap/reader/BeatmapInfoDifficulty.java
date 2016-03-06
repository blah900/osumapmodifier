package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class BeatmapInfoDifficulty extends BeatmapInfoFragment{
  public static enum Setting {
    HPDrainRate, CircleSize, OverallDifficulty, ApproachRate, SliderMultiplier, SliderTickRate;
  }

  public BeatmapInfoDifficulty(List<String> lines) {
    super(lines, false);
  }

  public String get(Setting setting) {
    return lines.get(setting.ordinal());
  }

  public void set(Setting setting, String value) {
    lines.set(setting.ordinal(), setting.name() + ":" + value);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Difficulty]", writer);
  }

}
