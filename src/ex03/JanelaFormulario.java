package ex03;

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
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JanelaFormulario extends JFrame implements ActionListener{
    private JanelaBotao jb;
    
    private JPanel panel;
    private JLabel lblFonte;
    private JComboBox comboFonte;
    private JLabel lblEstilo;
    private JCheckBox checkEstilo;
    private JLabel lblTamanho;
    private JTextField txtTamanho;
    private JLabel lblCor;
    private JComboBox comboCor;
    private JButton btnAbrirJanela;
    private JButton btnGravar;
    
    ManipuladorDeArquivo mda = new ManipuladorDeArquivo();
    String endereco = "/Users/User/Downloads/Arquivo.txt";
    
    public JanelaFormulario(){
        componentesJanela();
        propriedadesJanela();
    }
    
    private void componentesJanela(){
        panel = new JPanel();
        
        lblFonte = new JLabel("Fonte:");
        comboFonte = new JComboBox();
        DefaultComboBoxModel modeloNomeFonte = new DefaultComboBoxModel();
                modeloNomeFonte.addElement("Courier New");
                modeloNomeFonte.addElement("Serif");
                modeloNomeFonte.addElement("TimesRoman");
        comboFonte.setModel(modeloNomeFonte);
        
        lblEstilo = new JLabel("Negrito");
        checkEstilo = new JCheckBox();
        
        lblTamanho = new JLabel("Tamanho:");
        txtTamanho = new JTextField(5);
        
        lblCor = new JLabel("Cor do botão:");
        comboCor = new JComboBox();
        DefaultComboBoxModel modeloCorBotao = new DefaultComboBoxModel();
                modeloCorBotao.addElement("Azul");
                modeloCorBotao.addElement("Magenta");
                modeloCorBotao.addElement("Vermelho");
        comboCor.setModel(modeloCorBotao);
        
        btnAbrirJanela = new JButton("Abrir nova janela");
        btnAbrirJanela.addActionListener(this);
        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        
        adicionarComponentes();
    }
    
    private void adicionarComponentes(){
        add(panel);
        
        panel.add(lblFonte);
        panel.add(comboFonte);
        panel.add(lblEstilo);
        panel.add(checkEstilo);
        panel.add(lblTamanho);
        panel.add(txtTamanho);
        panel.add(lblCor);
        panel.add(comboCor);
        panel.add(btnAbrirJanela);
        panel.add(btnGravar);
    }
    
    private void propriedadesJanela(){
        setVisible(true);
        setTitle("Personalize seu próprio JButton");
        pack(); //Redimensionamento preferencial da janela de acordo com seus sub-componentes
        setLocationRelativeTo(null); //Inicializa a janela ao centro da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String fonte = (String) this.comboFonte.getSelectedItem();
        boolean estilo = this.checkEstilo.isSelected();
        int tamanho = Integer.parseInt(this.txtTamanho.getText());
        String cor = (String) this.comboCor.getSelectedItem();
        
        if(tamanho > 0){
            if(e.getSource() == btnAbrirJanela){
                jb = new JanelaBotao();
                if(estilo == true){
                    jb.propriedadesBotao(fonte, 1, tamanho, cor);
                }else{
                    jb.propriedadesBotao(fonte, 0, tamanho, cor);
                }
            }else{
                try{
                    String str = "Fonte: " + fonte + "\nNegrito: " + estilo + "\nTamanho: " + tamanho + "\nCor: " + cor;
                    mda.salvar(str);
                    ManipuladorDeArquivo.ler(endereco); //Exibe os dados armazenados no arquivo, para testes
                }catch(IOException ex){
                    Logger.getLogger(JanelaFormulario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Tamanho de fonte inválido.");
        }
    }
}
