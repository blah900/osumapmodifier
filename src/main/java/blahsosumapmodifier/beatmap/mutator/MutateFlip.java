package blahsosumapmodifier.beatmap.mutator;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public class MutateFlip implements Mutator {
  SimpleMatrix mutator;

  private MutateFlip(boolean overXAxis) {
    this.mutator = new SimpleMatrix(new double[][]
      {
          { overXAxis ? 1 : -1},
          { overXAxis ? -1 : 1}});
  }
  
  public static MutateFlip overXAxis() {
    return new MutateFlip(true);
  }
  
  public static MutateFlip overYAxis() {
    return new MutateFlip(false);
  }

  @Override
  public void mutate(List<OsuPositionMutableString> input, MutateInfo mutateInfo,
      MutateValidator validator) {
    for (OsuPositionMutableString elem : input) {
      List<SimpleMatrix> mutatedPositions = new ArrayList<>();
      for (SimpleMatrix matrix : elem.getPositions()) {
        SimpleMatrix mutatedMatrix = mutator.elementMult(matrix.minus(mutateInfo.centerTranslator))
            .plus(mutateInfo.centerTranslator);
        if (validator.validate(mutatedMatrix)) {
          mutatedPositions.add(mutatedMatrix);
        } else {
          throw new RuntimeException("Not valid! - " + mutatedMatrix);
        }
      }
      elem.setPositions(mutatedPositions);
    }
  }

}
