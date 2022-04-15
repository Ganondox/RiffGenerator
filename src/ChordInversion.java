public class ChordInversion implements Mutation{

        @Override
        public Riff Mutate(Riff riff) {
            //clone riff
            Riff clone = new Riff(riff);
            //find index to invert
            int i = (int) (Math.random() * clone.beats * clone.measures * clone.bars);
            //invert chord
            for(int j = 0; j < riff.strings; j++){
                clone.notes[i][j] = riff.notes[i][clone.strings - 1 - j];
            }
           return clone;



        }


}
