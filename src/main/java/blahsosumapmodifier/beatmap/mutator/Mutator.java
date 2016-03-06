package blahsosumapmodifier.beatmap.mutator;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public interface Mutator {
  void mutate(List<OsuPositionMutableString> input, MutateInfo mutateInfo, MutateValidator validator);
}
