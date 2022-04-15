public class FingerChange implements Aesthetic{

    double weight;

    public FingerChange(double weight) {
        this.weight = weight;
    }

    @Override
    public double rate(Riff riff) {
        double penalty = 0;

        double[] lastChord =  normalizeChord(riff.notes[0]);

        for(int i = 1; i < riff.beats * riff.measures * riff.bars; i++){
            double[] curChord = normalizeChord(riff.notes[i]);
            double distance = 0;
            for(int j = 0; j < riff.strings; j++){
                distance += (curChord[j] - lastChord[j]) * (curChord[j] - lastChord[j]);
            }
            penalty += Math.sqrt(distance);
            lastChord = curChord;
        }
        return penalty * weight;

    }

    private double[] normalizeChord(int[] chord){
        double[] normalized = new double[chord.length];
        HandPoint offset = new HandPoint(chord);
        for(int i = 0; i < chord.length; i++){
            int index = (int)(offset.y - (chord.length/2.0)) + i;
            if(index < chord.length && index > -1){
                normalized[i] = chord[index] - offset.x;
            }
        }
        return normalized;
    }

}
