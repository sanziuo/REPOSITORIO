package conta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabrielsanzio
 */
public class TestaSistemaInterno {
        public static void main(String[] args) {
        SistemaInterno sistema = new SistemaInterno();

        Gerente gerente = new Gerente();
        gerente.setNome("João");
        gerente.setSenha(1234);

        Diretor diretor = new Diretor();
        diretor.setNome("Ana");
        diretor.setSenha(5678);

        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setSenha(1111);

        // Polimorfismo pela interface
        Autenticavel a = new Gerente();      // referência do tipo da interface
        Autenticavel d = diretor;

        System.out.print("Gerente (senha certa): ");
        sistema.login(gerente, 1234);

        System.out.print("Gerente (senha errada): ");
        sistema.login(gerente, 9999);

        System.out.print("Diretor: ");
        sistema.login(d, 5678);

        System.out.print("Cliente: ");
        sistema.login(cliente, 1111);

        // Gerente com departamento sem acesso
        gerente.setDepartamentoTemAcesso(false);
        System.out.print("Gerente (departamento sem acesso): ");
        sistema.login(gerente, 1234);

        // NÃO COMPILA: Secretario não implementa Autenticavel
        // sistema.login(new Secretario(), 1234);
    }
}
