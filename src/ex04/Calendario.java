package ex04;

/**
 *
 * @author Jv Loreti
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public abstract class Calendario{
    private static LocalDate dtAtual = LocalDate.now();
    private static DateTimeFormatter dtFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static String checarData(int dias){
        LocalDate dtFutura = dtAtual.plusDays(dias);
        String strData = dtFutura.format(dtFormato);
        
        DayOfWeek diaSemana = dtFutura.getDayOfWeek();
        String strDiaSemana = null;
        switch(diaSemana.getValue()){
            case 1:
                strDiaSemana = "Segunda-feira";
                break;
            case 2:
                strDiaSemana = "Terça-feira";
                break;
            case 3:
                strDiaSemana = "Quarta-feira";
                break;
            case 4:
                strDiaSemana = "Quinta-feira";
                break;
            case 5:
                strDiaSemana = "Sexta-feira";
                break;
            case 6:
                strDiaSemana = "Sábado";
                break;
            case 7:
                strDiaSemana = "Domingo";
                break;
        }
        
        return "- " + strData
                + "\n- " + strDiaSemana;
    }
}
