package ex01;

/**
 *
 * @author Jv Loreti
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

public class JanelaGrafica extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    private ManipuladorDeNumerosAleatorios numerosAleatorios;

    private JPanel panel;
    
    private JTextArea area;
    private JScrollPane scroll;
    
    private JButton btnGerar;
    private JButton btnImprimir;
    
    ManipuladorDeArquivo mda = new ManipuladorDeArquivo();
    String endereco = "/Users/User/Downloads/Arquivo.txt";
    
    public JanelaGrafica(){
        componentesJanela();
        propriedadesJanela();
    }
    
    private void componentesJanela(){
        panel = new JPanel();
        
        area = new JTextArea(30, 30); //Linhas, colunas
        area.setFont(new Font("Arial", Font.BOLD, 15));
        area.setEditable(false);
        
        scroll = new JScrollPane();
        scroll.setViewportView(area);
        
        btnGerar = new JButton("Gerar números aleatórios");
        btnGerar.addActionListener(this);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.addActionListener(this);
        btnImprimir.setEnabled(false);
        
        adicionarComponentes();
    }
    
    private void adicionarComponentes(){
        add(panel);
        
        panel.add(btnGerar);
        panel.add(btnImprimir);
        panel.add(scroll);
    }
    
    private void propriedadesJanela(){
        setVisible(true);
        setTitle("Números aleatórios");
        pack(); //Redimensionamento preferencial da janela de acordo com seus sub-componentes
        setLocationRelativeTo(null); //Inicializa a janela ao centro da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void gerar(){
        numerosAleatorios = new ManipuladorDeNumerosAleatorios();
        numerosAleatorios.gerarNumeros();
        
        btnImprimir.setEnabled(true);
    }
    
     private void imprimir() throws IOException{
         area.append(numerosAleatorios.imprimirInvertido() + "\n");
     }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnGerar){ //Gerar
            gerar();
        }else if(e.getSource() == btnImprimir){ //Imprimir
            try{
                imprimir();
                ManipuladorDeArquivo.ler(endereco); //Imprime os dados armazenados no arquivo, para testes
            }catch(IOException | ValorInvalidoException ex){
                Logger.getLogger(JanelaGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
