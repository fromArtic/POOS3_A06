package ex02;

/**
 *
 * @author Jv Loreti
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class JanelaGrafica extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    private JogoDeDados jogo;

    private JPanel panel;
    
    private JTextArea areaJogo;
    private JScrollPane scrollJogo;
    
    private JLabel lblAposta;
    private JTextField txtAposta;
    private JButton btnNovoJogo;
    private JButton btnJogar;
    private JButton btnLimpar;
    private JButton btnSave;
    private JButton btnLoad;
    
    public JanelaGrafica(){
        componentesJanela();
        propriedadesJanela();
    }
    
    private void componentesJanela(){
        panel = new JPanel();
        
        areaJogo = new JTextArea(30, 30); //Linhas, colunas
        areaJogo.setFont(new Font("Arial", Font.BOLD, 15));
        areaJogo.setEditable(false);
        
        scrollJogo = new JScrollPane();
        scrollJogo.setViewportView(areaJogo);
        
        lblAposta = new JLabel("Aposta:");
        
        txtAposta = new JTextField(4);
        txtAposta.setEditable(false);
        
        btnNovoJogo = new JButton("Novo jogo");
        btnNovoJogo.addActionListener(this);
        
        btnJogar = new JButton("Jogar");
        btnJogar.addActionListener(this);
        btnJogar.setEnabled(false);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(this);
        btnLimpar.setEnabled(false);
        
        btnSave = new JButton("Salvar partida");
        btnSave.addActionListener(this);
        btnSave.setEnabled(false);
        
        btnLoad = new JButton("Carregar partida");
        btnLoad.addActionListener(this);
        
        adicionarComponentes();
    }
    
    private void adicionarComponentes(){
        add(panel);
        
        panel.add(lblAposta);
        panel.add(txtAposta);
        panel.add(btnNovoJogo);
        panel.add(btnJogar);
        panel.add(btnLimpar);
        panel.add(scrollJogo);
        panel.add(btnSave);
        panel.add(btnLoad);
    }
    
    private void propriedadesJanela(){
        setVisible(true);
        setTitle("Jogo de dados");
        pack(); //Redimensionamento preferencial da janela de acordo com seus sub-componentes
        setLocationRelativeTo(null); //Inicializa a janela ao centro da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarPartida(){
        jogo = new JogoDeDados();
        jogo.inicializarJogo();
        
        txtAposta.setEditable(true);
        txtAposta.setText(null);
        btnNovoJogo.setEnabled(false);
        btnJogar.setEnabled(true);
        btnLimpar.setEnabled(false);
        btnSave.setEnabled(true);
        
        areaJogo.append("- INÍCIO DA PARTIDA -\n");
    }
    
    private void jogar(){
    	String strAposta = txtAposta.getText();
    	if(strAposta.matches("[0-9]+")){ //Somente aceita dígitos como entrada para a aposta
	    int aposta = Integer.parseInt(strAposta);
	    boolean win = jogo.checkAposta(aposta);
	    if(win){
	        areaJogo.append(String.format("%d -> Você acertou! :)\n", aposta));
                encerrarPartida();
	    }else{
                int tentativas = jogo.getTentativas();
	        if(tentativas > 0){
	            areaJogo.append(String.format("%d -> Você errou [%d tentativa(s) restante(s)]\n", aposta, tentativas));
                    txtAposta.setText(null);
	        }else{
	            areaJogo.append("Acabaram suas tentativas, você perdeu :(\n");
	            encerrarPartida();
                }
	    }
    	}
    }
    
    private void encerrarPartida(){
        areaJogo.append("- PARTIDA ENCERRADA -\n\n");
        jogo = null;
        
        txtAposta.setEditable(false);
        btnNovoJogo.setEnabled(true);
        btnJogar.setEnabled(false);
        btnLimpar.setEnabled(true);
        btnSave.setEnabled(false);
    }
    
    private void limparAreaJogo(){
        areaJogo.setText(null);
        btnLimpar.setEnabled(false);
    }
        
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnNovoJogo){ //Novo jogo
            inicializarPartida();
        }else if(e.getSource() == btnJogar){ //Jogar
            jogar();
        }else if(e.getSource() == btnSave){ //Salvar
            try{
                FileOutputStream fos = new FileOutputStream("fos.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(jogo);
                oos.close();
                areaJogo.append("Jogo salvo!\n");
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else if(e.getSource() == btnLoad){ //Carregar
            try{
                FileInputStream fis = new FileInputStream("fos.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                jogo = (JogoDeDados) ois.readObject();
                ois.close();
                txtAposta.setEditable(true);
                btnJogar.setEnabled(true);
                int tentativas = jogo.getTentativas();
                if(!"".equals(areaJogo.getText())){
                    areaJogo.append(String.format("\n- Carregamento concluído! -\n[%d tentativa(s) restante(s)]\n", tentativas));
                }else{
                    areaJogo.append(String.format("- Carregamento concluído! -\n[%d tentativa(s) restante(s)]\n", tentativas));
                }
            }catch(IOException | ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{ //Limpar
            limparAreaJogo();
        }
    }
}
