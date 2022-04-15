public class ChordDistance implements Aesthetic{

    double weight;

    public ChordDistance(double weight) {
        this.weight = weight;
    }

    @Override
    public double rate(Riff riff) {
        double penality = 0;
        for(int i = 0; i < riff.beats * riff.measures * riff.bars; i++){
            int max = 0;
            int min = riff.frets;
            double distance = 0;
            for(int j = 0; j < riff.strings; j++){
                if(riff.notes[i][j] > max) max = riff.notes[i][j];
                if(riff.notes[i][j] < min && riff.notes[i][j] > 0 ) min = riff.notes[i][j]; //open string and empty string are not counted
                if(max > 0){
                    distance += (max - min)*(max - min);
                }
            }
            //penality += Math.sqrt(distance);
            penality += distance; //square it because large deviations are impossible to play
        }
        return penality * weight;

    }
}
