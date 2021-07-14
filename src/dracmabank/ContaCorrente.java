package dracmabank;

public class ContaCorrente extends Conta{
    private double chequeEspecialDisponivel = 0;
    private double chequeEspecialLimite = 0;
    private int senhaGerente = 1010;

    public ContaCorrente(double saldo, Cliente cliente) {
        super(saldo, cliente);
    }


    public void adicionarLimiteChequeEspecial(double valorChequeEspecial, int senhaDigitada) {
        if (this.senhaGerente == senhaDigitada) {
            this.chequeEspecialLimite = valorChequeEspecial;
            this.chequeEspecialDisponivel = valorChequeEspecial;
            System.out.printf("Limite de R$ %.2f liberado\n", valorChequeEspecial);
        } else {
            System.out.println("Senha incorreta");
        }
    }

    @Override
    public void mostrarExtrato() {
        super.mostrarExtrato();
        System.out.printf("Limite disponivel do Cheque Especial: %.2f\n", chequeEspecialDisponivel);
    }

    @Override
    public void sacar(double valorDoSaque) {
        if (saldo >= valorDoSaque) {
            metodoDoSaque(valorDoSaque);
        } else {
            usarChequeEspecial(valorDoSaque);
        }
    }



    /*@Override
    public void depositar(double valorDoDeposito) {
        super.depositar(valorDoDeposito);
        if (saldo < 0) {
            pagarChequeEspecial(valorDoDeposito);
        }

    }*/

    @Override
    public void depositar(double valorDoDeposito) {
        super.depositar(valorDoDeposito);
        if (saldo >= 0) {
            pagarChequeEspecial(valorDoDeposito);
        }

    }

    public void usarChequeEspecial(double valorDoSaque) {

        if(saldo <= 0){
            if(chequeEspecialDisponivel >= valorDoSaque){
                chequeEspecialDisponivel -= valorDoSaque;
                saldo -= valorDoSaque;
                String auxiliar = "Saque cheque especial: R$ " + fmt.format(valorDoSaque);
                extrato.add(auxiliar);
                System.out.printf("Seu saldo atual é R$ %.2f e seu limite de cheque especial R$ %.2f\n", saldo, chequeEspecialDisponivel);
            }else{
                System.out.println("Você não possui limite suficiente para realizar este saque");
            }
        }else{
            if (saldo + chequeEspecialDisponivel >= valorDoSaque) { // saldo -25, limitedisponivel 475, e tento dacar 475 não deixa
                chequeEspecialDisponivel -= (valorDoSaque - saldo);
                saldo -= valorDoSaque;
                String auxiliar = "Saque cheque especial: R$ " + fmt.format(valorDoSaque);
                extrato.add(auxiliar);
                System.out.printf("Seu saldo atual é R$ %.2f e seu limite de cheque especial R$ %.2f\n", saldo, chequeEspecialDisponivel);
            } else {
                System.out.println("Você não possui limite suficiente para realizar este saque");
            }
        }

    }

    public void pagarChequeEspecial(double valorDepositado) {
        double saldoAntigo = saldo;
        chequeEspecialDisponivel += valorDepositado;
        double diferencaDoPagamento = chequeEspecialDisponivel - chequeEspecialLimite;
        if (diferencaDoPagamento >= 0) {
            chequeEspecialDisponivel = chequeEspecialLimite;
        }

    }

    @Override
    public void informacoesConta() {
        super.informacoesConta();
        System.out.println("Tipo: Conta Corrente");
        System.out.println("-----------------------");
    }
}
