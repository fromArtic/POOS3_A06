package ex01;

/**
 *
 * @author Jv Loreti
 */

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.lang.reflect.InvocationTargetException;

public class Main{
    public static void main(String[] args){
        try{
            SwingUtilities.invokeAndWait(() -> new JanelaGrafica());
        }catch(InterruptedException | InvocationTargetException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
