package dracmabank;

public class ContaPoupanca extends Conta{


    public ContaPoupanca(double saldo, Cliente cliente) {
        super(saldo, cliente);
    }

    @Override
    public void informacoesConta() {
        super.informacoesConta();
        System.out.println("Tipo: Conta Poupança");
        System.out.println("-----------------------");
    }


}
