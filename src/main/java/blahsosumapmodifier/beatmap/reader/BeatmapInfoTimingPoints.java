package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoTimingPoints extends BeatmapInfoFragment{

  public BeatmapInfoTimingPoints(List<String> lines) {
    super(lines, false);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[TimingPoints]", writer);
  }
 
}
