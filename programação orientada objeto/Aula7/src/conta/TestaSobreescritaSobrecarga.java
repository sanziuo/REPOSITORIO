/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaSobreescritaSobrecarga {
    public static void main(String[] args){
        //Sobrescrita
        Animal animal = new Animal();
        Animal cao = new Cao(); //polimorfismo: referência Animal, objeto Cao
        System.out.println(animal.emitirSom()); //Som genérico de animal
        System.out.println(cao.emitirSom()); //Latido
        
        // Sobrecarga
        Calculadora calc = new Calculadora();
        System.out.println(calc.somar(2,3));    //5
        System.out.println(calc.somar(2,3,4));
    }
}
