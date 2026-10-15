/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package media;
import java.util.Scanner; // Importação necessária para usar a classe Scanner
/**
 *
 * @author sanzi
 */
public class Media {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //variaveis do tipo real
        float nota1, nota2, nota3, nota4, mediaAritmetica;
        //endrada de dados
        Scanner entrada = new Scanner (System.in);
        System.out.println("Entre com a nota 1: ");
        nota1= entrada.nextFloat ();
        System.out.println("Entre com a nota 2: ");
        nota2= entrada.nextFloat ();
        System.out.println("Entre com a nota 3: ");
        nota3= entrada.nextFloat ();
        System.out.println("Entre com a nota 4: ");
        nota4= entrada.nextFloat ();
        //processamento
        mediaAritmetica = (nota1+nota2+nota3+nota4)/4;
        //resultados
        System.out.printf ("\nA média aritmética: %.2f",mediaAritmetica);
        if(mediaAritmetica >=7.0){
            System.out.printf("\nAluno Aprovado!");
        }//fim do if
    }//fim do método main
}//fim da classe média
