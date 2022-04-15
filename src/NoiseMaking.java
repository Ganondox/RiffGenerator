public class NoiseMaking implements Aesthetic{

    double weight;

    double notePenalty = 1;
    double chordPenalty = 10;

    public NoiseMaking(double weight) {
        this.weight = weight;
    }

    @Override
    public double rate(Riff riff) {
        double score = 0;
        for(int i = 0; i < riff.beats * riff.measures * riff.bars; i++){
            boolean silent = true;
            for(int j = 0; j < riff.strings; j++){
                if(riff.notes[i][j] == -1){
                    score += notePenalty;
                } else silent = false;
            }
            if(silent) score += chordPenalty;
        }
        return score * weight;

    }
}
