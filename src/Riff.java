public class Riff {


    int beats = 4;
    int measures = 4;
    int bars = 12;
    int strings = 6;
    int frets = 24;

    int[][] notes; // 48*6, values range from -1 to 24
    int[] tuning; //EADGBe

    public Riff() {
        //tuning = new char[]{'e', 'B', 'G', 'D', 'A', 'E'}; //todo: find a way to generalize to arbitrary number of strings
        tuning = new int[]{4, 11, 7, 2, 9, 4};
        notes = new int[beats*measures*bars][strings];
        for(int i = 0; i < beats*measures*bars; i++){
            for(int j = 0; j < strings; j++){
                notes[i][j] = ((int)(Math.random() * frets)) - 1;
            }
        }
    }

    public Riff(Riff ref) {

        beats = ref.beats;
        measures = ref.measures;
        bars = ref.bars;
        strings = ref.strings;
        frets = ref.frets;

        tuning = new int[ref.tuning.length];
        for(int i = 0; i < tuning.length ; i++){
            tuning[i] = ref.tuning[i];
        }
        notes = new int[ref.notes.length][ref.notes[0].length];
        for(int i = 0; i < notes.length; i++){
            for(int j = 0; j < notes[0].length; j++){
                notes[i][j] = ref.notes[i][j];
            }
        }



    }

    @Override
    public String toString(){
        String tab = "";
        for(int i = 0; i < bars; i++){
            for(int j = 0; j < strings; j++){
                tab += toneLabel(tuning[j]);
                for(int k = 0; k < measures; k++){
                    tab += '|';
                    for(int l = 0; l < beats; l++){
                        int note = notes[i*measures*beats + k*beats + l][j];
                        if(note < 10){ //pad single digit numbers
                            tab += "-";
                        }
                        if (note < 0) {//not play string
                            tab += "-";
                        } else {
                            tab += note;
                        }
                        tab += "-"; //for legibility
                    }
                }
                tab += "|\n";
            }
            tab += "\n";
        }
        return tab;
    }

    static String toneLabel(int tone){
        switch (tone % 12){
            case 0:
                return "C_";
            case 1:
                return "C#";
            case 2:
                return "D_";
            case 3:
                return "D#";
            case 4:
                return "E_";
            case 5:
                return "F_";
            case 6:
                return "F#";
            case 7:
                return "G_";
            case 8:
                return "G#";
            case 9:
                return "A_";
            case 10:
                return "A#";
            case 11:
                return "B_";

        }
        return null;
    }

}



