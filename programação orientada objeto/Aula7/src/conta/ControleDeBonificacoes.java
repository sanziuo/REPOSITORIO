/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class ControleDeBonificacoes {
    private double totalDeBonificacoes = 0;
    
    // POLIMORFISMO: recebe qualquer Funcionario (inclusive Gerente)
    public void registra(Funcionario funcionario) {
        this.totalDeBonificacoes += funcionario.getBonificacao();
    }
    
    public double getTotalDeBonificacoes() {
        return this.totalDeBonificacoes;
    }
}
