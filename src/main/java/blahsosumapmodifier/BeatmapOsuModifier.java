package blahsosumapmodifier;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import blahsosumapmodifier.beatmap.hitobject.OsuPositionMutableString;
import blahsosumapmodifier.beatmap.mutator.MutateConstrain;
import blahsosumapmodifier.beatmap.mutator.MutateFlip;
import blahsosumapmodifier.beatmap.mutator.MutateInfo;
import blahsosumapmodifier.beatmap.mutator.MutateRotate;
import blahsosumapmodifier.beatmap.mutator.MutateScale;
import blahsosumapmodifier.beatmap.mutator.MutateValidator;
import blahsosumapmodifier.beatmap.reader.BeatmapInfo;
import blahsosumapmodifier.beatmap.reader.BeatmapInfoDifficulty;
import blahsosumapmodifier.beatmap.reader.BeatmapReader;

public class BeatmapOsuModifier {

  public static void main(String[] args) {
    String fileName = "";
    BeatmapInfo info = BeatmapReader.getOsuPositions(fileName);
    MutateInfo mutateInfo = new MutateInfo.Builder().setCenterTranslator(new SimpleMatrix(new double[][]
      {
          { 512 / 2 },
          { 384 / 2 } }))
        .build();
    MutateValidator validator = new MutateValidator(0, 512, 0, 384);
    MutateValidator nonValidator = new MutateValidator(-1000, 1000, -500, 500);
    info.getDifficulty().set(BeatmapInfoDifficulty.Setting.CircleSize, "7");
    info.getDifficulty().set(BeatmapInfoDifficulty.Setting.ApproachRate, "9");
    info.getHitObjects().applyMutators(new MutateScale(0.67, 1), mutateInfo, nonValidator);
    info.getHitObjects().applyMutators(new MutateRotate(Math.PI / 4.0), mutateInfo, nonValidator);
    info.getHitObjects().applyMutators(new MutateConstrain(0, 512, 0, 384), mutateInfo, validator);
    info.getHitObjects().applyMutators(MutateFlip.overXAxis(), mutateInfo, validator);
    info.getHitObjects().applyMutators(MutateFlip.overYAxis(), mutateInfo, validator);
    info.writeTo(
        fileName.replaceFirst("\\[.*\\].osu", "[" + info.getMetadata().getVersion() + "].osu"));
  }

}
