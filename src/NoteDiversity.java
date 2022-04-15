public class NoteDiversity implements Aesthetic{

    double weight;

    public NoteDiversity(double weight) {
        this.weight = weight;
    }

    double unhitPenality = 10;

    @Override
    public double rate(Riff riff) {
        int penality = riff.beats * riff.measures * riff.bars * riff.strings;
        int buff = penality / (riff.frets * riff.strings);
        int[][] hitCount = new int[riff.strings][riff.frets + 1];
        for(int i = 0; i < riff.beats * riff.measures * riff.bars; i++){
            for(int j = 0; j < riff.strings; j++){
                if(riff.notes[i][j] == - 1) penality--;
                else if(hitCount[j][riff.notes[i][j]]++ < buff) penality--;
            }
        }
        for(int i = 0; i < riff.strings; i++){
            for(int j = 0; j < riff.frets; j++){
                if(hitCount[i][j] == 0) penality += unhitPenality;
            }
        }

        return penality * weight;

    }

}
