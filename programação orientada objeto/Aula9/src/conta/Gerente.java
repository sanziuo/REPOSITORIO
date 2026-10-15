/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class Gerente extends Funcionario implements Autenticavel {

    private int senha;
    private String departamento;
    private boolean departamentoTemAcesso = true;
    
    // Obrigatório implementar, senão não compila
    @Override
    public double getBonificacao() {
        return this.salario * 1.4 + 1000;
    }


   @Override
    public boolean autentica(int senha) {
        if (this.senha != senha) {
            return false;
        }
        // Verificação extra do gerente: o departamento dele tem acesso?
        return this.departamentoTemAcesso;
    }
    
    // setter da senha (omitido no slide)
     public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public boolean isDepartamentoTemAcesso() {
        return departamentoTemAcesso;
    }

    public void setDepartamentoTemAcesso(boolean departamentoTemAcesso) {
        this.departamentoTemAcesso = departamentoTemAcesso;
    }
}