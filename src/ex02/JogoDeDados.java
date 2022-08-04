package ex02;

/**
 *
 * @author Jv Loreti
 */

import java.io.Serializable;

public class JogoDeDados implements Serializable{
    private int dado1;
    private int dado2;
    private int somatoria;
    private int tentativas = 3;
	
    public void inicializarJogo(){
	dado1 = lancarDado(1, 6);
	dado2 = lancarDado(1, 6);
	somatoria = dado1 + dado2;
	System.out.println(somatoria); //Exibição da somatória, para testes 
    }
	
    public boolean checkAposta(int palpite){
	if(tentativas > 0){
            tentativas--;
            return palpite == somatoria; //Retorna verdadeiro caso o palpite esteja correto
	}
	return false;
    }
    
    private int lancarDado(int min, int max) {
	return (int) (Math.random()*(max+1-min)) + min;
    }
	
    public int getTentativas(){
        return tentativas;
    }
}
