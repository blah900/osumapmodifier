package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoColours extends BeatmapInfoFragment{

  public BeatmapInfoColours(List<String> lines) {
    super(lines, false);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Colours]", writer);
  }
 
}
