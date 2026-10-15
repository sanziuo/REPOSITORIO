/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class Calculadora {
    //Somar dois valores (a e b)
    // (int, int)
    public int somar(int a, int b) {
        return a + b;
    }

    // (int, int, int): muda a QUANTIDADE de parâmetros
    public int somar(int a, int b, int c) {
        return a + b + c;
    }

    // (float, float): muda o TIPO dos parâmetros
    public float somar(float a, float b) {
        return a + b;
    }

    // (double, double): muda o TIPO dos parâmetros
    public double somar(double a, double b) {
        return a + b;
    }
    // ERRO se descomentar: mesma assinatura somar(int, int),
    // só o retorno é diferente, e isso NÃO é sobrecarga
    // public void somar(int x, int y) { }
}
