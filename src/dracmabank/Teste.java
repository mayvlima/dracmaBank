package dracmabank;

public class Teste {

    public static void main(String[] args) {
        Banco banco = new Banco("Dracma", 01);

        Cliente cliente1 = new Cliente("Mayara Lima", "1234567899" );
        Cliente cliente2 = new Cliente("Luna Lima", "1234567899" );

        Conta conta1 = new ContaPoupanca(1500, cliente1);
        Conta conta2 = new ContaPoupanca(1500, cliente2);

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);



        conta1.mostrarExtrato();
        conta2.mostrarExtrato();






    }
}
