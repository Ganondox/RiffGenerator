public class SingleNotePerturbation implements Mutation{
    @Override
    public Riff Mutate(Riff riff) {
        //clone riff
        Riff clone = new Riff(riff);
        //find index to perturb
        int i = (int) (Math.random() * clone.beats * clone.measures * clone.bars);
        int j = (int) (Math.random() * clone.strings);
        //perturb note
        if(Math.random() > 0.5) clone.notes[i][j]++; else clone.notes[i][j]--; //direction of perturbation
        if(clone.notes[i][j] < -1) clone.notes[i][j] = clone.frets; else if (clone.notes[i][j] > clone.frets) clone.notes[i][j] = -1;  //fix range
        return clone;



    }
}
