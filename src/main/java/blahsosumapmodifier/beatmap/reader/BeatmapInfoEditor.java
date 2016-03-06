package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoEditor extends BeatmapInfoFragment{

  public BeatmapInfoEditor(List<String> lines) {
    super(lines, false);
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Editor]", writer);
  }
 
}
