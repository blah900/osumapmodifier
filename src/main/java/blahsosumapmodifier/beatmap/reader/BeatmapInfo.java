package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;

public class BeatmapInfo {
  List<String> preCursorLines;
  List<BeatmapInfoFragment> fragments = new ArrayList<>();
  BeatmapInfoHitObjects hitObjects;
  BeatmapInfoMetadata metadata;
  BeatmapInfoDifficulty difficulty;

  public BeatmapInfoDifficulty getDifficulty() {
    return difficulty;
  }

  public BeatmapInfoHitObjects getHitObjects() {
    return hitObjects;
  }

  public BeatmapInfoMetadata getMetadata() {
    return metadata;
  }

  public static class Builder {
    BeatmapInfo info = new BeatmapInfo();

    public Builder addLines(List<String> lines) {
      String header = lines.get(0);
      List<String>subList  = lines.subList(1, lines.size());

      switch (header) {
      case "[General]":
        info.fragments.add(new BeatmapInfoGeneral(subList));
        break;
      case "[Editor]":
        info.fragments.add(new BeatmapInfoEditor(subList));
        break;
      case "[Metadata]":
        info.metadata = new BeatmapInfoMetadata(subList);
        info.fragments.add(info.metadata);
        break;
      case "[Difficulty]":
        info.difficulty = new BeatmapInfoDifficulty(subList);
        info.fragments.add(info.difficulty);
        break;
      case "[Events]":
        info.fragments.add(new BeatmapInfoEvents(subList));
        break;
      case "[TimingPoints]":
        info.fragments.add(new BeatmapInfoTimingPoints(subList));
        break;
      case "[Colours]":
        info.fragments.add(new BeatmapInfoColours(subList));
        break;
      case "[HitObjects]":
        info.hitObjects = new BeatmapInfoHitObjects(subList);
        System.out.println("Size: " + subList.get(0));
        System.out.println("Size: " + subList.get(subList.size() - 1));
        info.fragments.add(info.hitObjects);
        break;
      default:
        info.preCursorLines = lines;
        break;
      }
      return this;
    }

    public BeatmapInfo build() {
      return info;
    }
  }
  
  public void writeTo(String fileName) {
    try {
      BufferedWriter writer = Files.newWriter(new File(fileName), Charsets.UTF_8);
      for(String line : preCursorLines) {
        writer.write(line + "\n");
      }
      writer.write("\n");
      
      for (BeatmapInfoFragment fragment : fragments) {
        fragment.output(writer);
      }
      writer.flush();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
