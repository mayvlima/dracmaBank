package dracmabank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Conta {

    protected double saldo = 0;
    private int numeroDaConta;
    private boolean statusDaConta = true;
    protected List<String> extrato = new ArrayList<>();

    public DecimalFormat fmt = new DecimalFormat("0.00");

    private Cliente cliente;

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Conta(double saldo, Cliente cliente) {
        this.saldo = saldo;
        this.numeroDaConta = (int) ((Math.random() * (9999 - 1000)) + 1000);
        this.cliente = cliente;
    }

    public void mostrarExtrato() {
        System.out.println("----Extrato----");
        System.out.println("DRACMA BANK");
        System.out.printf("Cliente: %s\n", cliente.getNome());
        System.out.printf("Número da conta: %d\n", numeroDaConta);

        for (int i = 0; i < extrato.size(); i++) {
            System.out.println("0" + (i + 1) + ") " + extrato.get(i));
        }


        System.out.printf("Seu saldo atual: R$ %.2f \n", saldo);
    }

    public void sacar(double valorDoSaque) {

        if (saldo >= valorDoSaque) {
            metodoDoSaque(valorDoSaque);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    protected void metodoDoSaque(double valorDoSaque) {
        this.saldo -= valorDoSaque;
        System.out.println("Saque realizado com sucesso");
        System.out.printf("Saldo atual: R$ %.2f \n", saldo);

        String auxiliar = "Saque: R$ " + fmt.format(valorDoSaque);
        extrato.add(auxiliar);
    }

    public void depositar(double valorDoDeposito) {
        this.saldo += valorDoDeposito;
        System.out.println("Depósito realizado com sucesso");
        System.out.printf("Saldo atual: R$ %.2f \n", saldo);
        String auxiliar = "Deposito: R$ " + fmt.format(valorDoDeposito);
        extrato.add(auxiliar);
    }

    public void encerrarConta() {
        if (statusDaConta) {
            if (saldo != 0) {
                System.out.println("Para encerrar a conta, seu saldo deve ser R$ 0.00");
                System.out.printf("Seu saldo atual é: R$ %.2f\n", saldo);
            } else {
                statusDaConta = false;
                System.out.println("Conta encerrada");
                System.exit(1);
            }
        }
    }

    public void adicionarLimiteChequeEspecial(double valorChequeEspecial, int senhaDigitada) {

    }

    public void informacoesConta() {
        System.out.println("Nome do cliente: " + this.cliente.getNome());
        System.out.println("Número da conta: " + this.numeroDaConta);
        if (this.statusDaConta) {
            System.out.println("Status da conta: ativa");
        } else {
            System.out.println("Status da conta: desativada");
        }
    }

    public void trasfere(double valor, Conta conta) {
        if (saldo >= valor) {
            metodoDoSaque(valor);
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso");
        } else {
            System.out.println("Saldo insuficiente");
        }

    }


}
