/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */

// Não precisa implementar getBonificacao: já herda a versão concreta do Gerente
public class Diretor extends Funcionario implements Autenticavel {

    private int senha;

    // Obrigatório por causa do "implements Autenticavel"
    @Override
    public boolean autentica(int senha) {
        return this.senha == senha;
    }

    @Override
    public double getBonificacao() {
        return this.salario * 2;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
