package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoGeneral extends BeatmapInfoFragment{

  public BeatmapInfoGeneral(List<String> lines) {
    super(lines, false);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[General]", writer);
  }
 
}
