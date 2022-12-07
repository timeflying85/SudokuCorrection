package sudo4x4;

import Model.SudokuModel;
import Model.SudokuPositionException;
import Model.SudokuVue;

public class Vue4x4 implements SudokuVue {

    private static String format = """
                +-----+-----+
                | . . | . . |
                | . . | . . |
                +-----+-----+
                | . . | . . |
                | . . | . . |
                +-----+-----+
                """.replaceAll("\\.","%s");

    public Vue4x4(SudokuModel4x4 model) {
        this.model = model;
    }

    SudokuModel4x4 model;
    public void afficherGrilleVide() {
        afficherGrille(new SudokuModel4x4());
    }

    public void afficherGrille() {
        afficherGrille(model);
        if(message != null){
            System.out.println(message);
            message = null;
        }
    }

    private String message;
    @Override
    public void setMessage(String message) {
        this.message = message;
    }
    public void afficherGrille(SudokuModel4x4 values) {
        Character[] vect = new Character[4*4];
        for (int lig = 0; lig < 4; lig++) {
            for (int col = 0; col < 4; col++) {
                try {
                    if(values.getValue(lig, col)== SudokuModel.EMPTY){
                        vect[lig*4+col]='.';
                    }else{
                        vect[lig*4+col]=values.getValue(lig, col);
                    }
                } catch (SudokuPositionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        afficherVecteur( vect);
    }

    private static void afficherVecteur(Character[] vect) {
        System.out.printf(format, (Object[])vect);
    }
}
