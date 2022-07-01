package br.com.senac.gym_manager.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.senac.gym_manager.enums.StatusPagamento;
import br.com.senac.gym_manager.exceptions.CartaoDesativadoException;
import br.com.senac.gym_manager.exceptions.ExcedeuLimiteDeEntradaException;
import br.com.senac.gym_manager.exceptions.SemMensalidadeException;
import br.com.senac.gym_manager.exceptions.NaoCadastradoException;
import br.com.senac.gym_manager.exceptions.SemCartaoException;
import br.com.senac.gym_manager.exceptions.SemCatracaException;

public class Cliente extends Pessoa {

    private int codInscricao;
    private List<Treinamento> treinamentos;
    private Mensalidade mensalidade;
    private CartaoPasseCliente cartao;

    public Cliente(int codInscricao, String nome, String cpf, int idade, Mensalidade mensalidade) {
        super(nome, cpf, idade);
        this.codInscricao = codInscricao;
        this.treinamentos = new ArrayList();
        this.mensalidade = mensalidade;
        System.out.println("Cliente criado");
    }

    public Cliente(int codInscricao, CartaoPasseCliente cartao, String nome, String cpf, int idade, Mensalidade mensalidade) {
        super(nome, cpf, idade);
        this.codInscricao = codInscricao;
        this.cartao = cartao;
        this.treinamentos = new ArrayList();
        this.mensalidade = mensalidade;
        System.out.println("Cliente criado");
    }

    private void atualizarCartao() throws SemMensalidadeException {
        if (mensalidade.getStatus().equals(StatusPagamento.PAGAMENTO_REALIZADO.getStatus())) {
            this.cartao.setAtivo(true);
            System.out.println("Cartão atualizado! (Ativado)");
        } else if (this.getMensalidade() == null) {
            throw new SemMensalidadeException("Esse cliente não tem mensalidade! ");
        } else {
            this.cartao.setAtivo(false);
            System.out.println("Cartão atualiazado! (Desativado)");
        }
    }

    private void inserirCartao() throws NullPointerException, NaoCadastradoException, ExcedeuLimiteDeEntradaException, CartaoDesativadoException, SemCartaoException, SemMensalidadeException, SemCatracaException {
        if (this.getCartao() == null) {
            throw new SemCartaoException("Esse cliente não tem cartão! ");
        } else if (this.cartao.getCatraca() == null) {
            throw new SemCatracaException("O cartão não tem catraca! ");
        } else if (this.getMensalidade() == null){
            throw new SemMensalidadeException("Esse cliente não tem mensalidade! ");
        } else {
            System.out.println("Inseriu o cartão!");
            this.atualizarCartao();
            this.cartao.getCatraca().passarCartao(cartao.getCodCartao());
        }

    }

    public void entrarNaAcademia() {
        try {
            this.inserirCartao();
            System.out.println("Passou na catraca!");
            this.cartao.getCatraca().fecharCatraca();
        } catch (NaoCadastradoException | ExcedeuLimiteDeEntradaException | CartaoDesativadoException | SemCartaoException | SemMensalidadeException ex) {
            System.out.println("Cartão inválido! " + ex.getMessage());
        } catch (SemCatracaException ex) {
            System.out.println("Catraca inválida! " + ex.getMessage());
        }
    }

    public void adicionarTreinamento(Treinamento treinamento) {
        try {
            this.treinamentos.add(treinamento);
            System.out.println("Treinamento adicionado ao cliente!");
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro!" + ex.getMessage());
        }
    }

    public void removerTreinamento(String nomeTreinamento) {
        boolean isInLista = false;
        for (Treinamento treinamento : getTreinamentos()) {
            if (treinamento.getNome().equalsIgnoreCase(nomeTreinamento)) {
                this.treinamentos.remove(treinamento);
                isInLista = true;
                System.out.println("Treinamento removido!");
                break;
            }
        }
        if (isInLista == false) {
            System.out.println("O treinamento não está na lista!");
        }
    }

    public void mostrarTreinos() {
        this.treinamentos.forEach((treino) -> {
            System.out.println(treino.getNome());
        });
    }

    public void pagarMensalidade() {
        try {
            if (this.getMensalidade().getStatus().equalsIgnoreCase("Realizado")) {
                System.out.println("Não possível realizar o pagamento"
                        + "!\nMensalidade já foi paga anteriormente.");
                System.out.println(this.mensalidade.getDataDoPagamentoSTR());
            } else {
                this.getMensalidade().setStatus(StatusPagamento.PAGAMENTO_REALIZADO);
                LocalDate dataPagamento = LocalDate.now();
                this.mensalidade.setDataDoPagamento(dataPagamento);
                System.out.println("Pagou a mensalidade!");
            }
        } catch (NullPointerException ex) {
            System.out.println("Você não possui mensalidades! ");
        }
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
        System.out.println("Código de inscrição atribuída ao cliente!");
    }

    public List<Treinamento> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<Treinamento> treinamentos) {
        this.treinamentos = treinamentos;
        System.out.println("lista de treinamentos atribuída! (cliente)");
    }

    public CartaoPasseCliente getCartao() {
        return cartao;
    }

    public void setCartao(CartaoPasseCliente cartao) {
        this.cartao = cartao;
        System.out.println("Cartão atribuído ao cliente!");
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
        System.out.println("Mensalidade atribuída ao cliente!");
    }

}
