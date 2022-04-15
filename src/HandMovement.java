public class HandMovement implements Aesthetic{

    double weight;

    public HandMovement(double weight) {
        this.weight = weight;
    }

    @Override
    public double rate(Riff riff) {
        double penalty = 0;

        HandPoint lastChord = new HandPoint(riff.notes[0]);

        for(int i = 1; i < riff.beats * riff.measures * riff.bars; i++){
           HandPoint curChord = new HandPoint(riff.notes[i]);
           if(!curChord.empty && !lastChord.empty) {
               double distance = Math.sqrt((lastChord.x - curChord.x) * (lastChord.x - curChord.x) + (lastChord.y - curChord.y) * (lastChord.y - curChord.y));
               penalty += distance;
           }
           lastChord = curChord;
        }
        return penalty * weight;

    }

}
