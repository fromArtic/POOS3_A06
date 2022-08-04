package ex03;

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
            SwingUtilities.invokeAndWait(() -> new JanelaFormulario());
        }catch(InterruptedException | InvocationTargetException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
