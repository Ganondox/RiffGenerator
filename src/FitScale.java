import java.util.HashSet;
import java.util.Set;

public class FitScale implements Aesthetic {

    Set<Integer> scale;

    double weight;

    public FitScale(double weight) {
        this.weight = weight;

        //default scale is c major
        scale = new HashSet<>();
        scale.add(0);
        scale.add(2);
        scale.add(4);
        scale.add(5);
        scale.add(7);
        scale.add(9);
        scale.add(11);

    }

    @Override
    public double rate(Riff riff) {
        double penalty = 0;

        for(int i = 1; i < riff.beats * riff.measures * riff.bars; i++){
            for(int j = 0; j < riff.strings; j++){
                if(!scale.contains((riff.notes[i][j] + riff.tuning[j]) % 12)) penalty += 1;
            }
        }
        return penalty * weight;

    }

}
