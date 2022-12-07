package samourail;


import Model.SudokuModel;
import Model.SudokuPositionException;
import Model.SudokuVue;

public class VueSamourail implements SudokuVue {
    private static String format = """
            +-------+-------+-------+       +-------+-------+-------+
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            +-------+-------+-------+       +-------+-------+-------+
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            +-------+-------+-------+-------+-------+-------+-------+
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            +-------+-------+-------+-------+-------+-------+-------+
                            | . . . | . . . | . . . |
                            | . . . | . . . | . . . |
                            | . . . | . . . | . . . |
            +-------+-------+-------+-------+-------+-------+-------+
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            | . . . | . . . | . . . | . . . | . . . | . . . | . . . |
            +-------+-------+-------+-------+-------+-------+-------+
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            +-------+-------+-------+       +-------+-------+-------+
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            | . . . | . . . | . . . |       | . . . | . . . | . . . |
            +-------+-------+-------+       +-------+-------+-------+
            """.replaceAll("\\.", "%s");

    public VueSamourail(SudokuModelSamourail model) {
        this.model = model;
    }

    SudokuModelSamourail model;

    public void afficherGrilleVide() {
        afficherGrille(new SudokuModelSamourail());
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

    public void afficherGrille(SudokuModelSamourail values) {
        Character[] vect = new Character[21 * 21 - 4 * 3 * 6];
        int pos=0;
        for (int lig = 0; lig < 21; lig++) {
            for (int col = 0; col < 21; col++) {
                if (values.isPositionValid(lig, col)) {
                    try {
                        if (values.getValue(lig, col) == SudokuModel.EMPTY) {
                            vect[pos++] = '.';
                        } else {
                            vect[pos++] = values.getValue(lig, col);
                        }
                    } catch (SudokuPositionException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        afficherVecteur(vect);
    }

    private static void afficherVecteur(Character[] vect) {
        System.out.printf(format, (Object[]) vect);
    }
}
