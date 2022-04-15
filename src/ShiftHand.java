public class ShiftHand implements Mutation{

    @Override
    public Riff Mutate(Riff riff) {
        //clone riff
        Riff clone = new Riff(riff);
        //find index to shift
        int i = (int) (Math.random() * clone.beats * clone.measures * clone.bars);
        //shift chord
        if(Math.random() > 0.5){
            for(int j = 0; j < riff.strings; j++){
                if(clone.notes[i][j] > 0){
                    clone.notes[i][j]++;
                    if(clone.notes[i][j] > riff.frets){
                        clone.notes[i][j] = -1;
                    }
                }
            }

        } else {
            for(int j = 0; j < riff.strings; j++){
                if(clone.notes[i][j] > 0){
                    clone.notes[i][j]--;
                }
            }
        }
        return clone;



    }

}
