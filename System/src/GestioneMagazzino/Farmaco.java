package GestioneMagazzino;

public class Farmaco {
    private String nome;
    private String principioAttivo;
    private String data;
    private String daBanco;
    private int quantità;
    private int ID;
    public Farmaco(String nome, String principioAttivo, String data, String daBanco, int quantità) {
        this.nome = nome;
        this.principioAttivo = principioAttivo;
        this.data = data;
        this.daBanco = daBanco;
        this.quantità = quantità;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipioAttivo() {
        return principioAttivo;
    }

    public void setPrincipioAttivo(String principioAttivo) {
        this.principioAttivo = principioAttivo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDaBanco() {
        return daBanco;
    }

    public void setDaBanco(String daBanco) {
        this.daBanco = daBanco;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public String toString() {
        return "Farmaco{" +
                "nome='" + nome + '\'' +
                ", principioAttivo='" + principioAttivo + '\'' +
                ", data='" + data + '\'' +
                ", daBanco='" + daBanco + '\'' +
                ", quantità=" + quantità +
                ", ID="+ID+
                '}';
    }
}
