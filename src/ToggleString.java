public class ToggleString implements Mutation{

    @Override
    public Riff Mutate(Riff riff) {
        //clone riff
        Riff clone = new Riff(riff);
        //find index to perturb
        int i = (int) (Math.random() * clone.beats * clone.measures * clone.bars);
        int j = (int) (Math.random() * clone.strings);
        //toggle string
        if(clone.notes[i][j] != -1){
            clone.notes[i][j] = -1;
        } else {
           clone.notes[i][j] = (int) (Math.random() * (clone.frets + 1));
        }
        return clone;
    }
}
