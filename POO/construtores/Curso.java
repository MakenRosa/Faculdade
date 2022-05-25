package construtores;
public class Curso {
    private String nome;
    private String descricao;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String descricao, String codigo, int cargaHoraria){
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
