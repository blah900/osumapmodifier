package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;
import blahsosumapmodifier.beatmap.mutator.MutateInfo;
import blahsosumapmodifier.beatmap.mutator.MutateValidator;
import blahsosumapmodifier.beatmap.mutator.Mutator;

public class BeatmapInfoHitObjects extends BeatmapInfoFragment {
  List<OsuPositionMutableString> mutableStrings;

  public BeatmapInfoHitObjects(List<String> lines) {
    super(lines, false);
    mutableStrings = OsuPositionMutableString.fromStrings(lines);
  }

  @Override
  public void output(BufferedWriter writer) {
    try {
      writer.write("[HitObjects]" + "\n");
      for (OsuPositionMutableString line : mutableStrings) {
        writer.write(line.getString() + "\n");
      }
      writer.write("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void applyMutators(Mutator mutator, MutateInfo mutateInfo, MutateValidator validator) {
    mutator.mutate(mutableStrings, mutateInfo, validator);
  }

}
