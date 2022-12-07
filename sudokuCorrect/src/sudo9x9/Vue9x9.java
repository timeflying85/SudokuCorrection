package sudo9x9;

import Model.SudokuModel;
import Model.SudokuPositionException;
import Model.SudokuVue;

public class Vue9x9 implements SudokuVue {
    private static String format = """
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                """.replaceAll("\\.","%s");

    public Vue9x9(SudokuModel9x9 model) {
        this.model = model;
    }

    SudokuModel9x9 model;
    public void afficherGrilleVide() {
        afficherGrille(new SudokuModel9x9());
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

    public void afficherGrille(SudokuModel9x9 values) {
        Character[] vect = new Character[9*9];
        for (int lig = 0; lig < 9; lig++) {
            for (int col = 0; col < 9; col++) {
                try {
                    if(values.getValue(lig, col)== SudokuModel.EMPTY){
                        vect[lig*9+col]='.';
                    }else{
                        vect[lig*9+col]=values.getValue(lig, col);
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
