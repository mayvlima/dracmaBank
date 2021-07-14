package dracmabank;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in).useDelimiter("\n");

        System.out.println("-------DRACMA BANK-------");

        System.out.println("Informe o completo:");
        String nome = in.next();
        System.out.println("Informe o CPF:");
        String cpf = in.next();

        Cliente novoCliente = new Cliente(nome, cpf);

        Conta novaConta = null;

        int tipoDeConta = receberTipoDeConta();

        System.out.printf("Informe o saldo inicial: ");
        validaSystemDouble(in);
        double saldoInicial = validaValorPositivo(in);

        switch (tipoDeConta) {

            case 1:
                novaConta = new ContaCorrente(saldoInicial, novoCliente);
                break;
            case 2:
                novaConta = new ContaPoupanca(saldoInicial, novoCliente);
                break;

        }

        boolean realizarOperacao = true;

        System.out.printf("\nOlá, %s", nome);
        while (realizarOperacao) {
            System.out.println("\nQual operação você deseja realizar?");
            System.out.println("[1] - Sacar");
            System.out.println("[2] - Depositar");
            System.out.println("[3] - Extrato");
            System.out.println("[4] - Encerrar Conta");
            if (novaConta instanceof ContaCorrente) {
                System.out.println("[5] - Solicitar Cheque Especial");
            }
            System.out.println("[0] - Sair");
            validaSystemInteiro(in);
            int opcaoSelecionada = in.nextInt();
            switch (opcaoSelecionada) {
                case 1:
                    System.out.print("Informe o valor a sacar: ");
                    validaSystemDouble(in);
                    double saque = validaValorPositivo(in);
                    novaConta.sacar(saque);
                    break;
                case 2:
                    System.out.print("Informe o valor a depositar: ");
                    validaSystemDouble(in);
                    double deposito = validaValorPositivo(in);
                    novaConta.depositar(deposito);
                    break;
                case 3:
                    novaConta.mostrarExtrato();
                    break;
                case 4:
                    novaConta.encerrarConta();
                    break;
                case 5:
                    if (novaConta instanceof ContaCorrente) {
                        System.out.print("Informe o limite do cheque especial: ");
                        validaSystemDouble(in);
                        double limiteChequeEspecial = validaValorPositivo(in);
                        System.out.print("\nSenha do Gerente: ");
                        validaSystemInteiro(in);
                        int senhaInformada = in.nextInt();
                        novaConta.adicionarLimiteChequeEspecial(limiteChequeEspecial, senhaInformada);
                    } else {
                        System.out.println("Essa opção não é válida para Conta Poupança");
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    realizarOperacao = false;
                    break;
                default:
                    System.out.println("Você selecionou uma opção inválida");

            }
        }

        in.close();

    }

    public static int receberTipoDeConta(){
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        int tipoDeConta = 0;
        while(tipoDeConta != 1 && tipoDeConta != 2){
            System.out.println("Escolha um tipo de conta: [1] - Conta Corrente [2] - Conta Poupança");
            validaSystemInteiro(in);
            tipoDeConta = in.nextInt();
        }
        return tipoDeConta;
    }

    public static void validaSystemInteiro(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Você precisa digitar uma número inteiro");
            sc.next();
            System.out.println("Digite novamente:");
        }
    }

    public static void validaSystemDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Você precisa digitar uma número");
            sc.next();
            System.out.println("Digite novamente:");
        }
    }

    public static double validaValorPositivo(Scanner sc){

        double numero = sc.nextDouble();
        while(numero < 0){
            System.out.println("Você deve digitar uma valor maior do que zero");
            System.out.println("Digite novamente: ");
            validaSystemDouble(sc);
            numero = sc.nextDouble();
        }

        return numero;
    }


}
