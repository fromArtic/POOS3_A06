package ex04;

/**
 *
 * @author Jv Loreti
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class JanelaGrafica extends JFrame implements ActionListener{
    private JPanel panel;
    private JTextArea area;
    private JLabel lbl;
    private JTextField txt;
    private JButton btn;
    
    public JanelaGrafica(){
        componentesJanela();
        propriedadesJanela();
    }
    
    private void componentesJanela(){
        panel = new JPanel();
        
        area = new JTextArea(6, 20); //Linhas, colunas
        area.setFont(new Font("Arial", Font.BOLD, 17));
        area.setEditable(false);
        
        lbl = new JLabel("Dias:");
        txt = new JTextField(4);
        btn = new JButton("Checar calendário");
        btn.addActionListener(this);
        
        adicionarComponentes();
    }
    
    private void adicionarComponentes(){
        add(panel);
        
        panel.add(lbl);
        panel.add(txt);
        panel.add(btn);
        panel.add(area);
    }

    private void propriedadesJanela(){
        setVisible(true);
        setTitle("Checagem calendário");
        pack(); //Redimensionamento preferencial da janela de acordo com seus sub-componentes
        setLocationRelativeTo(null); //Inicializa a janela ao centro da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void checarCalendario(){
        area.setText(null);
        int x = Integer.parseInt(txt.getText());
        area.append("Daqui a " + x + " dia(s), será:\n" + Calendario.checarData(x));
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        checarCalendario();
    }
}
