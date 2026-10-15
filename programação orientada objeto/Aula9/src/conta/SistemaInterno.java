/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class SistemaInterno {

    // Aceita QUALQUER Autenticavel: Gerente, Diretor, Cliente...
    public void login(Autenticavel a, int senha) {
        boolean ok = a.autentica(senha);

        if (ok) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Login negado!");
        }
    }
}
