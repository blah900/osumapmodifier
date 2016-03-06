package blahsosumapmodifier.beatmap.mutator;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public class MutateRotate implements Mutator {
  SimpleMatrix mutator;

  public MutateRotate(double angle) {
    this.mutator = new SimpleMatrix(new double[][]
      {
          { Math.cos(angle), -1 * Math.sin(angle) },
          { Math.sin(angle), Math.cos(angle) } });
  }

  @Override
  public void mutate(List<OsuPositionMutableString> input, MutateInfo mutateInfo,
      MutateValidator validator) {
    for (OsuPositionMutableString elem : input) {
      List<SimpleMatrix> mutatedPositions = new ArrayList<>();
      for (SimpleMatrix matrix : elem.getPositions()) {
        SimpleMatrix mutatedMatrix = mutator.mult(matrix.minus(mutateInfo.centerTranslator))
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
