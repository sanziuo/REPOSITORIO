/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaPessoa {
    public static void main(String[] args) {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Maria");
        pf.setCpf("123.456.789-00");

        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Restaurante Bom Sabor");
        pj.setCnpj("12.345.678/0001-99");

        // Relatório de clientes: array de Pessoa (polimorfismo)
        Pessoa[] clientes = { pf, pj };
        for (Pessoa p : clientes) {
            System.out.println(p.getNome() + " - " + p.getDocumento());
        }
    }
}
