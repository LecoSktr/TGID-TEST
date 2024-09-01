public abstract class Usuario {
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract boolean validarDocumento(String documento);
}
public class Cliente extends Usuario {
    private String cpf;

    public Cliente(String nome, String cpf) {
        super(nome);
        if (validarDocumento(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    @Override
    public boolean validarDocumento(String cpf) {
        // Aqui deve ser implementada a lógica de validação de CPF
        return cpf.matches("\\d{11}");
    }

    public String getCpf() {
        return cpf;
    }
}
import java.util.ArrayList;
import java.util.List;

public class Empresa extends Usuario {
    private String cnpj;
    private double saldo;
    private double taxaSistema;
    private List<Transacao> transacoes;

    public Empresa(String nome, String cnpj, double taxaSistema) {
        super(nome);
        if (validarDocumento(cnpj)) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.taxaSistema = taxaSistema;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    @Override
    public boolean validarDocumento(String cnpj) {
        // Aqui deve ser implementada a lógica de validação de CNPJ
        return cnpj.matches("\\d{14}");
    }

    public void realizarDeposito(Cliente cliente, double valor) {
        double valorComTaxa = valor - (valor * taxaSistema);
        saldo += valorComTaxa;
        transacoes.add(new Transacao(cliente, this, valor, "Depósito"));
    }

    public void realizarSaque(Cliente cliente, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            transacoes.add(new Transacao(cliente, this, valor, "Saque"));
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCnpj() {
        return cnpj;
    }
}
public class Transacao {
    private Cliente cliente;
    private Empresa empresa;
    private double valor;
    private String tipo;

    public Transacao(Cliente cliente, Empresa empresa, double valor, String tipo) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getDetalhes() {
        return String.format("Cliente: %s, Empresa: %s, Tipo: %s, Valor: %.2f",
                cliente.getNome(), empresa.getNome(), tipo, valor);
    }
}
public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João", "12345678901");
        Empresa empresa1 = new Empresa("Empresa X", "12345678000199", 0.02);

        empresa1.realizarDeposito(cliente1, 1000.0);
        System.out.println("Saldo após depósito: " + empresa1.getSaldo());

        empresa1.realizarSaque(cliente1, 500.0);
        System.out.println("Saldo após saque: " + empresa1.getSaldo());
    }
}
