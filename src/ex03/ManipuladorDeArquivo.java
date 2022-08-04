package ex03;

/**
 *
 * @author Jv Loreti
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ManipuladorDeArquivo{
    String endereco = "/Users/User/Downloads/Arquivo.txt";
    String linha = null;
    
    public static void ler(String endereco) throws IOException{
        FileReader arquivo = new FileReader(endereco);
        BufferedReader bufferedReader = new BufferedReader(arquivo);
        String linha;
        
        while((linha = bufferedReader.readLine()) != null){
            System.out.println(linha);
        }
        
        bufferedReader.close();
    }
    
    public static void escrever(String endereco, String linha) throws IOException{
        FileWriter arquivo = new FileWriter(endereco, false);
        BufferedWriter bufferedWriter = new BufferedWriter(arquivo);
        bufferedWriter.append(linha + "\n");
        bufferedWriter.close();
    }
    
    public void salvar(String str){
        try{
            ManipuladorDeArquivo.escrever(endereco, str);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        }catch(IOException ex){
            System.out.println("ERRO na leitura ou escrita do arquivo: " + ex.getMessage());
        }
    }
}
