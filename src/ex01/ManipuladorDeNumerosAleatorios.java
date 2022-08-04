package ex01;

/**
 *
 * @author Jv Loreti
 */

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;

public class ManipuladorDeNumerosAleatorios implements Serializable{
    int numerosAleatorios[] = new int[10];
    ManipuladorDeArquivo mda = new ManipuladorDeArquivo();
    
    public void gerarNumeros(){
        for(int i = 0; i < numerosAleatorios.length; i++){
            numerosAleatorios[i] = new Random().nextInt(30) + 20; //Random().nextInt(numMaximo-numMinimo) + numMinimo
            System.out.println(numerosAleatorios[i]); //Imprime a sequência de números aleatórios, para testes
        }
    }
    
    public String imprimirInvertido() throws IOException{
        int[] aleatoriosInvertidos = new int[numerosAleatorios.length];
        int j = numerosAleatorios.length - 1;
        for(int i = 0; i < numerosAleatorios.length; i++){
            aleatoriosInvertidos[j] = numerosAleatorios[i];
            j--;
        }
        
        String arrayToString = Arrays.toString(aleatoriosInvertidos);
        mda.salvar(arrayToString); //Salva no arquivo txt
        return arrayToString;
    }
}
