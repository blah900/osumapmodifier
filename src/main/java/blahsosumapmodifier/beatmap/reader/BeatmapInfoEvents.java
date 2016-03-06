package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoEvents extends BeatmapInfoFragment{

  public BeatmapInfoEvents(List<String> lines) {
    super(lines, false);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Events]", writer);
  }
 
}
