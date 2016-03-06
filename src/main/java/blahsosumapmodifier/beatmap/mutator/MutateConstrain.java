package blahsosumapmodifier.beatmap.mutator;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public class MutateConstrain implements Mutator {
  SimpleMatrix mutator;
  int xMin;
  int xMax;
  int yMin;
  int yMax;

  public MutateConstrain(int minX, int maxX, int minY, int maxY) {
    xMin = minX;
    xMax = maxX;
    yMin = minY;
    yMax = maxY;
  }
  
  private double constrain(double input, int min, int max) {
    return Math.max(min, Math.min(max, input));
  }

  @Override
  public void mutate(List<OsuPositionMutableString> input, MutateInfo mutateInfo,
      MutateValidator validator) {
    for (OsuPositionMutableString elem : input) {
      List<SimpleMatrix> mutatedPositions = new ArrayList<>();
      for (SimpleMatrix matrix : elem.getPositions()) {
        matrix.set(0, constrain(matrix.get(0), xMin, xMax));
        matrix.set(1, constrain(matrix.get(1), yMin, yMax));
        if (validator.validate(matrix)) {
          mutatedPositions.add(matrix);
        } else {
          throw new RuntimeException("Not valid! - " + matrix);
        }
      }
      elem.setPositions(mutatedPositions);
    }
  }

}
