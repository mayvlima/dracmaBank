package dracmabank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Banco {
    private String nome;
    private int codigoBanco;
    private List<Conta> listaDeContas;

    public Banco(String nome, int codigoBanco) {
        this.nome = nome;
        this.codigoBanco = codigoBanco;
        this.listaDeContas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void adicionarConta(Conta conta) {
        if (!listaDeContas.contains(conta)) {
            listaDeContas.add(conta);
            System.out.println("Conta cadastrada no banco com sucesso");
        } else {
            System.out.println("Conta já está cadastrada nesse banco");
        }
    }

    public boolean procurarConta(Conta conta) {
        if (listaDeContas.contains(conta)) {
            return true;
        }
        return false;
    }

    public void listarContas() {
        for (Conta c : listaDeContas) {
            c.informacoesConta();
        }
    }

}
