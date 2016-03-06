package blahsosumapmodifier.beatmap.mutator;

import org.ejml.simple.SimpleMatrix;import com.sun.webkit.dom.MutationEventImpl;

public class MutateValidator {
  private int xMin;
  private int xMax;
  private int yMin;
  private int yMax;
  
  public MutateValidator(int minX, int maxX, int minY, int maxY) {
    this.xMin = minX;
    this.xMax = maxX;
    this.yMin = minY;
    this.yMax = maxY;
  }
  
  private boolean validate(double x, int min, int max) {
    return x >= min && x <= max;
  }
  
  public boolean validate(SimpleMatrix position) {
    return validate(position.get(0), xMin, xMax) && validate(position.get(1), yMin, yMax);
  }
}
