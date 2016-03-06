package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.util.List;

public class BeatmapInfoMetadata extends BeatmapInfoFragment{

  public BeatmapInfoMetadata(List<String> lines) {
    super(lines, false);
    Setup();
  }
  
  private void Setup() {
    setField("Version", getField("Version") + "_Spiced");
    setField("BeatmapID", "0");
    setField("BeatmapSetID", "-1");
  }

  @Override
  public void output(BufferedWriter writer) {
    defaultOutput("[Metadata]", writer);
  }
  
  public String getVersion() {
    return getField("Version");
  }
 
}
