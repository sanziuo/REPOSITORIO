/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class Funcionario {
    
    // PROTECTED: visível na própria classe, nas subclasses e no mesmo pacote
    protected String nome;
    protected String cpf;
    protected double salario;
    
    // Bonificação padrão: 10% do salário
    public double getBonificacao() {
        return this.salario * 0.10;
    }
    
    // Getters e Setters
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public double getSalario(){
        return salario;
    }
    
    public void setSalario(double salario){
        this.salario = salario;
    }
}
