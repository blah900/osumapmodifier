package blahsosumapmodifier.beatmap;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import java.util.List;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

import org.ejml.simple.SimpleMatrix;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'yongjin' at '3/5/16 6:20 PM' with Gradle 2.6
 *
 * @author yongjin, @date 3/5/16 6:20 PM
 */
public class OsuPositionMutableStringTest {
  private final double TOLERANCE = 0.00001;
  private void assertPosition(SimpleMatrix actual, double expectedX, double expectedY) {
    assertThat(actual.get(0)).isWithin(TOLERANCE).of(expectedX);
    assertThat(actual.get(1)).isWithin(TOLERANCE).of(expectedY);
  }

  @Test
  public void testGetSinglePositionType() {
    OsuPositionMutableString mutableString = OsuPositionMutableString
        .fromString("100,200,300,400,500");
    List<SimpleMatrix> positions = mutableString.getPositions();
    assertWithMessage("There should only be 1 position!").that(positions.size()).isEqualTo(1);
    assertPosition(positions.get(0), 100, 200);
    assertWithMessage("Time should be third element").that(mutableString.getTime()).isEqualTo(300);
  }

  @Test
  public void testGetMultiplePositionType() {
    OsuPositionMutableString mutableString = OsuPositionMutableString
        .fromString("100,200,300,100|10:20|20:30,1,2|2,2:20|99:99");
    List<SimpleMatrix> positions = mutableString.getPositions();
    assertWithMessage("There should only be 3 positions!").that(positions.size()).isEqualTo(3);
    assertPosition(positions.get(0), 100, 200);
    assertPosition(positions.get(1), 10, 20);
    assertPosition(positions.get(2), 20, 30);
  }
  
  @Test
  public void testSetMultiplePositionType() {
    OsuPositionMutableString mutableString = OsuPositionMutableString
        .fromString("100,200,300,100|10:20|20:30|2,2:20|99:99");
    mutableString.setPositions(ImmutableList.<SimpleMatrix>of(
        new SimpleMatrix(new double[][] {{1},{2}}),
        new SimpleMatrix(new double[][] {{2},{3}}),
        new SimpleMatrix(new double[][] {{3},{4}})
        ));
    List<SimpleMatrix> positions = mutableString.getPositions();
    assertWithMessage("There should only be 3 positions!").that(positions.size()).isEqualTo(3);
    assertPosition(positions.get(0), 1, 2);
    assertPosition(positions.get(1), 2, 3);
    assertPosition(positions.get(2), 3, 4);
    assertThat(mutableString.getString()).isEqualTo("1,2,300,100|2:3|3:4|2,2:20|99:99");
  }
}
