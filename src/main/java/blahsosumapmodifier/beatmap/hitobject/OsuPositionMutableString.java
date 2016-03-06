package blahsosumapmodifier.beatmap.hitobject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import autovalue.shaded.com.google.common.common.collect.Lists;

public class OsuPositionMutableString {
  // Position can be separated by a "," or ":" given the fact that there is only
  // position in a string fragment.
  private final String POSITION_SEPARATOR = "[,:]";
  private final Joiner STRING_JOINER = Joiner.on("");
  List<String> stringPieces = new ArrayList<>();
  List<Integer> mutableIndices = new ArrayList<>();
  int timingIndex = -1;

  private OsuPositionMutableString() {
  };
  
  public static List<OsuPositionMutableString> fromStrings (List<String>lines ) {
    List<OsuPositionMutableString> mutableStrings = new ArrayList<>();
     for (String line : lines) {
       mutableStrings.add(fromString(line));
     }
     return mutableStrings;
  }

  public static OsuPositionMutableString fromString(String line) {
    OsuPositionMutableString mutableString = new OsuPositionMutableString();
    List<String> lineFragments = Lists.newArrayList(line.split("\\|"));

    String uniformFirstPosition = lineFragments.get(0);
    // The first piece is of the format "x,y,time..." so find the occurrence of
    // the second comma.
    int indexOfFirstPositionEnd = uniformFirstPosition.indexOf(",",
        uniformFirstPosition.indexOf(",") + 1);
    int indexOfTimeEnd = uniformFirstPosition.indexOf(",", indexOfFirstPositionEnd + 1);
    mutableString.stringPieces.add(uniformFirstPosition.substring(0, indexOfFirstPositionEnd));
    mutableString.stringPieces.add(uniformFirstPosition.substring(indexOfFirstPositionEnd, indexOfFirstPositionEnd + 1));
    mutableString.stringPieces.add(uniformFirstPosition.substring(indexOfFirstPositionEnd + 1, indexOfTimeEnd ));
    mutableString.stringPieces.add(uniformFirstPosition.substring(indexOfTimeEnd));
    mutableString.mutableIndices.add(0);
    mutableString.timingIndex = 2;

    boolean positionsOver = false;
    for (String otherPosition : lineFragments.subList(1, lineFragments.size())) {
      // The subsequent pieces in the case of a slider uses the syntax of
      // curveX:curveY.
      // In the case it is not of the form curveX:curveY, the additional
      // locations have been exhausted so we break;
      mutableString.stringPieces.add("|");
      if (!positionsOver && otherPosition.matches("\\d+:\\d+,?.*")) {
        // Small hack to get the proper index.
        mutableString.mutableIndices.add(mutableString.stringPieces.size());
        List<String> splitPositionStrings = Lists.newArrayList(otherPosition.split(",", 2)); 
        if (splitPositionStrings.size() == 1) {
          mutableString.stringPieces.add(otherPosition);
        } else {
          mutableString.stringPieces.add(splitPositionStrings.get(0));
          mutableString.stringPieces.add(",");
          mutableString.stringPieces.add(splitPositionStrings.get(1));
        }

      } else {
        positionsOver = true;
        mutableString.stringPieces.add(otherPosition);
      }
    }

    return mutableString;
  }

  public List<SimpleMatrix> getPositions() {
    List<SimpleMatrix> positions = new ArrayList<>();
    for (int positionIndex : mutableIndices) {
      String[] xyStrings = stringPieces.get(positionIndex).split(POSITION_SEPARATOR);
      positions.add(new SimpleMatrix(new double[][]
        {
            { Integer.valueOf(xyStrings[0].trim()) },
            { Integer.valueOf(xyStrings[1].trim()) } }));
    }
    return positions;
  }

  public void setPositions(List<SimpleMatrix> newPositions) {
    Preconditions.checkArgument(newPositions.size() == mutableIndices.size(),
        "The new positions size does not match the mutable position count! %s != %s",
        newPositions.size(), mutableIndices.size());
    for (int i = 0; i < mutableIndices.size(); i++) {
      if (i == 0) {
        stringPieces.set(mutableIndices.get(i), (int)newPositions.get(i).get(0) + "," + (int)newPositions.get(i).get(1));
      } else {
        stringPieces.set(mutableIndices.get(i), (int)newPositions.get(i).get(0) + ":" + (int)newPositions.get(i).get(1));
      }
    }
  }
  
  public String getString() {
    return STRING_JOINER.join(stringPieces);
  }

  public long getTime() {
    return Long.valueOf(stringPieces.get(timingIndex));
  }
}
