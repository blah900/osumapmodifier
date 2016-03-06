package blahsosumapmodifier.beatmap.reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class BeatmapInfoFragment {
   protected List<String> lines;
   private String separator = ":";
   
   protected BeatmapInfoFragment(List<String> lines, boolean separatorContainsSpace) {
     this.lines = lines;
     if (separatorContainsSpace) {
       separator += " ";
     }
   }
   
   protected int indexOf(String field) {
     for (int i = 0; i < lines.size(); i++) {
       if (lines.get(i).startsWith(field)) {
         return i;
       }
     }
     return -1;
   }
   
   protected void setField(String field, String value) {
     int index = indexOf(field);
     if (index >= 0) {
       lines.set(index, field + separator + value);
     }
   }
   
   protected String getField(String field) {
     int index = indexOf(field);
     if (index >= 0) {
       return lines.get(index).split(":")[1].trim();
     }
     return "";
   }
   
   public abstract void output(BufferedWriter writer);
   
   protected void defaultOutput(String header, BufferedWriter writer) {
     try {
       writer.write(header + "\n");
       for (String line : lines) {
         writer.write(line + "\n");
       }
       writer.write("\n");
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
   
}
