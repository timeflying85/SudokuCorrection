package Main;

import Controleur.CreateSudokuControleur;
import Controleur.SudokuControleur;
import samourail.SudokuModelSamourail;
import samourail.VueSamourail;
import sudo4x4.SudokuModel4x4;
import sudo4x4.Vue4x4;
import sudo9x9.SudokuModel9x9;
import sudo9x9.Vue9x9;

public class Main {
    private static ScannerInput input = new ScannerInput();
    public static void main(String[] args) {
        SudokuControleur controleur =
                switch (input.read("1 Sudoku 4 x 4\n2 Sudoku 9 x 9\n3 Sudoku Samourail\n")){
                    case "1" -> getCreateSudokuControleur(new SudokuModel4x4());
                    case "2" -> getCreateSudokuControleur(new SudokuModel9x9());
                    case "3" -> getCreateSudokuControleur(new SudokuModelSamourail());
                    default -> null;
                };
        if(controleur != null) {
            controleur.sart();
        }

    }

    private static SudokuControleur getCreateSudokuControleur(SudokuModel9x9 sudoku) {
        Vue9x9 vue = new Vue9x9(sudoku);
        return new CreateSudokuControleur(sudoku,vue);
    }

    private static SudokuControleur getCreateSudokuControleur(SudokuModel4x4 sudoku) {
        Vue4x4 vue = new Vue4x4(sudoku);
        return new CreateSudokuControleur(sudoku,vue);
    }

    private static SudokuControleur getCreateSudokuControleur(SudokuModelSamourail sudoku) {
        VueSamourail vue = new VueSamourail(sudoku);
        return new CreateSudokuControleur(sudoku,vue);
    }
}