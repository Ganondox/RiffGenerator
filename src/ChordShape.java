public class ChordShape implements Aesthetic{

    enum Shape {UPSLANT, DOWNSLANT, BARFRET, SOFTMUTE, HARDMUTE, BARMUTE, OPEN, SILENT} //measures how each finger is positioned on each string

    //Upslant / string above is fretted higher - 2
    //Downslant / string above is fretted lower - hardest 7
    //Barfret / finger is also used to fret string above - 5
    //Softmute / finger used to fret and mute strong above - 3
    //Hardmute / finger used to mute string - 6
    //Barmute / finger used to mute string and string above - 4
    //Open / string is not fretted but played - 1
    //Silent / string is not played - easiest 0

    double weight;

    public ChordShape(double weight) {
        this.weight = weight;
    }

    @Override
    public double rate(Riff riff) {
        double penalty = 0;
        for(int i = 0; i < riff.beats * riff.measures * riff.bars; i++){
            Shape[] chordshape = new Shape[riff.strings];
            //first pass, coming down
            if(riff.notes[i][0] == -1) chordshape[0] = Shape.SILENT;
            else if(riff.notes[i][0] == 0) chordshape[0] = Shape.OPEN;
            else chordshape[0] = Shape.UPSLANT;
            for(int j = 1; j < riff.strings; j++){
                if(riff.notes[i][j] == 0) chordshape[j] = Shape.OPEN; else{
                    if(chordshape[j-1] == Shape.SILENT){
                        if(riff.notes[i][j] == -1) chordshape[j] = Shape.SILENT;
                        else chordshape[j] = Shape.UPSLANT;
                    } else if(riff.notes[i][j-1] == -1){
                        if(riff.notes[i][j] == -1) chordshape[j] = Shape.BARMUTE;
                        else chordshape[j] = Shape.SOFTMUTE;
                    } else if(riff.notes[i][j-1] == 0){
                        if(riff.notes[i][j] == -1) chordshape[j] = Shape.HARDMUTE;
                        else chordshape[j] = Shape.DOWNSLANT;
                    } else {
                        if(riff.notes[i][j] == -1) chordshape[j] = Shape.HARDMUTE;
                        else {

                            if(riff.notes[i][j-1] > riff.notes[i][j]) chordshape[j] = Shape.UPSLANT;
                            else if(riff.notes[i][j-1] == riff.notes[i][j]) chordshape[j] = Shape.BARMUTE;
                            else chordshape[j] = Shape.DOWNSLANT;
                        }
                    }
                }


            }
            //second pass, coming up - extend silence from below, if notes are softmuted below than they can be open
            if(chordshape[riff.strings - 1] != Shape.SILENT) { //check that chord wasn't already silent to save time
                if (riff.notes[i][riff.strings - 1] == -1) chordshape[riff.strings - 1] = Shape.SILENT;
                for(int j = riff.strings - 2; j > -1; j--){
                    if(riff.notes[i][j] == - 1){
                        if(chordshape[j+1] == Shape.SILENT) chordshape[j] = Shape.SILENT;
                        else if (chordshape[j+1] == Shape.SOFTMUTE) chordshape[j] = Shape.OPEN;
                    }
                }
            }

            //calculate penalty for chord shape
            for(int j = 0; j < riff.strings; j++){
                switch (chordshape[j]){
                    case DOWNSLANT:
                        penalty++;
                    case HARDMUTE:
                        penalty++;
                    case BARFRET:;
                        penalty++;
                    case BARMUTE:
                        penalty++;
                    case SOFTMUTE:
                        penalty++;
                    case UPSLANT:
                        penalty++;
                    case OPEN:
                        penalty++;
                }
            }


        }
        return penalty * weight;
    }

}
