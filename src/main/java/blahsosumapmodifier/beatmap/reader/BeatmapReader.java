package blahsosumapmodifier.beatmap.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public class BeatmapReader {

  public static BeatmapInfo getOsuPositions(String fileName) {
    List<OsuPositionMutableString> positionMutableStrings = new ArrayList<>();
    try {
      List<String> beatsLines = Files.readLines(new File(fileName), Charsets.UTF_8);
      System.out.println(beatsLines.size());
      List<String> subSet = new ArrayList<>();
      BeatmapInfo.Builder builder = new BeatmapInfo.Builder();
      for (String line : beatsLines) {
        line = line.trim();
        if (line.startsWith("[") && line.endsWith("]")) {
          builder.addLines(subSet);
          System.out.println("Got " + line);
          subSet = new ArrayList<>();
        }

        if (!line.isEmpty()) {
          subSet.add(line);
        }
      }
      builder.addLines(subSet);
      System.out.println("Found hitobjects:" + subSet.size());
      System.out.println("Finished reading all files.");
      return builder.build();
      // int beatLineStart = beatsLines.indexOf("[HitObjects]");
      // for (int i = beatLineStart + 1; i < beatsLines.size(); i++) {
      // positionMutableStrings.add(OsuPositionMutableString.fromString(beatsLines.get(i)));
      // }
    } catch (IOException e) {
      throw new RuntimeException("Failed to read the file: " + fileName);
    }
  }
}
