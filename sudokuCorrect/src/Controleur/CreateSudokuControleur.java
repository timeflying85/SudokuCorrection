package Controleur;



import Main.Input;
import Main.ScannerInput;
import Model.SudokuException;
import Model.SudokuModel;
import Model.SudokuVue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSudokuControleur implements SudokuControleur {
    private final SudokuModel sudoku;
    private final SudokuVue vue;

    private final Input input;

    private Pattern pattern = Pattern.compile("([1-9][0-9]*)\\.([1-9][0-9]*)(\\..)?");
    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue, Input input) {
        this.sudoku = sudoku;
        this.vue = vue;
        this.input = input;
    }
    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue) {
        this(sudoku, vue,new ScannerInput());
    }

    @Override
    public void sart() {
        vue.afficherGrille();
        String request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        while(!request.equalsIgnoreCase("q")){
            Matcher matcher = pattern.matcher(request);
            if(matcher.matches()){
                int lig = Integer.parseInt(matcher.group(1))-1;
                int col = Integer.parseInt(matcher.group(2))-1;
                String value = matcher.group(3);
                try {
                    if (value != null) {
                        char val = value.charAt(1);
                        sudoku.setValue(lig, col, val);
                    } else {
                        sudoku.deleteValue(lig, col);
                    }
                }catch(SudokuException e){
                    vue.setMessage(e.getMessage());
                }

            }else{
                vue.setMessage("entrée non valide");
            }
            vue.afficherGrille();
            request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        }
    }
}
