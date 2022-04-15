public class StringSwapping implements Mutation {

    @Override
    public Riff Mutate(Riff riff) {
        //clone riff
        Riff clone = new Riff(riff);
        //find index to swap
        int i = (int) (Math.random() * clone.beats * clone.measures * clone.bars);
        int j = (int) (Math.random() * clone.strings);
        //swap note with string above
        int temp;
        if(j == 0){
            //swap with bottom string
            temp = clone.notes[i][clone.strings - 1];
            clone.notes[i][clone.strings - 1] = clone.notes[i][j];
        } else {
            temp = clone.notes[i][j - 1];
            clone.notes[i][j - 1] = clone.notes[i][j];
        }
        clone.notes[i][j] = temp;
        return clone;


    }
}