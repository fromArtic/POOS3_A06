package ex03;

/**
 *
 * @author Jv Loreti
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class JanelaBotao extends JFrame{
    private JPanel panel;
    private JButton btn;
    
    public JanelaBotao(){
        componentesJanela();
        propriedadesJanela();
    }
    
    private void componentesJanela(){
        panel = new JPanel();
        btn = new JButton("UAU");
        
        add(panel);
        panel.add(btn);
    }
    
    public void propriedadesBotao(String nome, int estilo, int tamanho, String cor){
        Font fonte = new Font(nome, estilo, tamanho);
        btn.setFont(fonte);
        if("Azul".equals(cor)){
            btn.setBackground(Color.blue);
            btn.setForeground(Color.white);
        }else if("Magenta".equals(cor)){
            btn.setBackground(Color.magenta);
            btn.setForeground(Color.yellow);
        }else{
            btn.setBackground(Color.red);
            btn.setForeground(Color.cyan);
        }
        pack();
    }
    
    private void propriedadesJanela(){
        setVisible(true);
        setLocationRelativeTo(null); //Inicializa a janela ao centro da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
