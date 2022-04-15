import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){
        Riff riff = new Riff();
        List<Mutation> mutations = new ArrayList<>();
        mutations.add(new SingleNotePerturbation());
        mutations.add(new ToggleString());
        mutations.add(new StringSwapping());
        mutations.add(new ChordInversion());
        mutations.add(new ShiftHand());
        List<Aesthetic> aesthetics = new ArrayList<>();
        aesthetics.add(new ChordDistance(0.25));
        aesthetics.add(new ChordShape(1));
        aesthetics.add(new NoiseMaking(5));
        aesthetics.add(new NoteDiversity(3));
        aesthetics.add(new HandMovement(1));
        aesthetics.add(new FingerChange(1));
        aesthetics.add(new FitScale(15));
        for(int i = 0; i < 1000000; i++){
            Riff contentor = new Riff(riff);
            for(Mutation m: mutations){
                contentor = m.Mutate(contentor);
            }
            double scoreRiff = 0;
            double scoreContentor = 0;
            for(Aesthetic a: aesthetics){
                scoreRiff += a.rate(riff);
                scoreContentor += a.rate(contentor);
            }
            if(scoreContentor <= scoreRiff) riff = contentor;
        }
        System.out.print(riff);
    }

}
