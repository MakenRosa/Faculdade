package br.com.senac.gym_manager.main;

import br.com.senac.gym_manager.enums.DiasDaSemana;
import br.com.senac.gym_manager.enums.StatusPagamento;
import br.com.senac.gym_manager.entidades.Cliente;
import br.com.senac.gym_manager.entidades.ClienteExperimental;
import br.com.senac.gym_manager.entidades.CartaoPasseCliente;
import br.com.senac.gym_manager.entidades.CatracaAcademia;
import br.com.senac.gym_manager.entidades.Exercicio;
import br.com.senac.gym_manager.entidades.Mensalidade;
import br.com.senac.gym_manager.entidades.Treinamento;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        // Instanciando a catraca da academia
        CatracaAcademia catraca = new CatracaAcademia();
        
        
        // Instanciando Exercicios
        Exercicio supino = new Exercicio("Supino declinado", 10, 3, "Halteres");
        Exercicio pushUps = new Exercicio("Flexão centrada", 12, 3);
        Exercicio oneArmRow = new Exercicio("One Arm Row", 8, 3, "Halteres");
        Exercicio bentOverRow = new Exercicio("Bent Over Row", 8, 3, "Barra");
        Exercicio curlBarra = new Exercicio("Bícep Curl", 10, 3, "Barra W");
        Exercicio curlHalter = new Exercicio("Bícep Curl", 8, 3);
        Exercicio tricepExtension = new Exercicio("Trícep Extension", 8, 2, "Barra");
        Exercicio tricepDeep = new Exercicio("Trícep Deep", 8, 3, "Banco");
        Exercicio reverseFly = new Exercicio("Reverse Fly (deitado)", 8, 3, "Halteres");
        Exercicio lateralRaise = new Exercicio("Lateral Raise", 8, 3, "Polia");
        Exercicio agachamentoBarra = new Exercicio("Agachamento", 8, 3, "Barra");
        Exercicio abdominal1 = new Exercicio("Abdominal (Oblíquo)", 12, 3);
        Exercicio abdominal2 = new Exercicio("Abdominal (Reto)", 12, 3);
        
        
        // Criando e Instanciando Treinamentos
        List<Exercicio> listTreino1 = new ArrayList();
        listTreino1.add(pushUps);
        listTreino1.add(supino);
        listTreino1.add(curlBarra);
        listTreino1.add(curlHalter);
        List<Exercicio> listTreino2 = new ArrayList();
        listTreino2.add(agachamentoBarra);
        listTreino2.add(reverseFly);
        listTreino2.add(lateralRaise);
        listTreino2.add(abdominal2);
        List<Exercicio> listTreino3 = new ArrayList();
        listTreino3.add(abdominal1);
        listTreino3.add(tricepDeep);
        listTreino3.add(tricepExtension);
        listTreino3.add(bentOverRow);
        listTreino3.add(oneArmRow);
        List<Exercicio> listTreino4 = new ArrayList();
        listTreino4.add(tricepDeep);
        listTreino4.add(curlBarra);
        listTreino4.add(abdominal2);
        listTreino4.add(tricepExtension);
        
        Treinamento makenSegunda = new Treinamento("Peitoral e bíceps", 
                listTreino1, DiasDaSemana.SEGUNDA_FEIRA);
        Treinamento makenQuarta = new Treinamento("Deltoide, quadriceps e abdomen",
                listTreino2, DiasDaSemana.QUARTA_FEIRA);
        Treinamento makenSexta = new Treinamento("Abdomen, triceps e dorsal", 
                listTreino3, DiasDaSemana.SEXTA_FEIRA);
        Treinamento experimental = new Treinamento("Triceps, biceps e abdomen", 
                listTreino4);
        
        // Instanciando e Adicionando a mensalidade do cliente
        Mensalidade julho = new Mensalidade("Julho", 125.00, StatusPagamento.PAGAMENTO_EM_ESPERA);
        
        // Instanciando pessoas

        Cliente maken = new Cliente(02101, "Maken da Rosa", "15421547881", 19, julho);

        ClienteExperimental leonardo = new ClienteExperimental("Leonardo Fulano",
                "78933245888", 17);
        leonardo.setDataAulaExperimental("07/07/2022");
        leonardo.setTreinamento(experimental);
        
        // Adicionando os Treinamentos as Pessoas
        maken.adicionarTreinamento(makenSegunda);
        maken.adicionarTreinamento(makenQuarta);
        maken.adicionarTreinamento(makenSexta);
        
        // Instanciando e Adicionando Cartao de Passe do Cliente na catraca
        CartaoPasseCliente cartaoMaken = new CartaoPasseCliente("451A32X5", catraca);
        catraca.adicionarCartao(cartaoMaken);
        maken.setCartao(cartaoMaken);
        
        
        // Utilizando o sistema
        maken.pagarMensalidade();
        maken.entrarNaAcademia();
    }
}
