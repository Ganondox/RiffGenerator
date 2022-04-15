public class HandPoint {

    boolean empty;
    double x;
    double y;

    HandPoint(int[] chord){
        //x is average of frets
        //y is average of top and bottom fretted strings
        double fretCount = 0;
        double fretSum = 0;
        int topString = 0;
        int bottomString = 0;
        for(int i = 0; i < chord.length; i++){
            if(chord[i] > 0){
                if(fretCount == 0) topString = i;
                fretCount++;
                fretSum += chord[i];
                bottomString = i;
            }
        }
        x = fretSum/fretCount;
        y = (topString+bottomString)/2.0;
        if(fretCount == 0) empty = true;
    }

}
