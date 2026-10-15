/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
// NÃO herda de Funcionario: só assina o contrato Autenticavel
public class Cliente implements Autenticavel {

    private String nome;
    private int senha;

    @Override
    public boolean autentica(int senha) {
        return this.senha == senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
