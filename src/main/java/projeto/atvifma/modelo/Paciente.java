package projeto.atvifma.modelo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paciente")
public class Paciente {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_crianca")
    private String nomeCrianca;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    private char sexo;

    @OneToMany
    @JoinColumn(name = "paciente_id") // coluna na tabela telefone
    private List<Telefone> telefones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "plano_saude_id")
    private PlanoSaude planoSaude;

    public Paciente() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }
    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getnomeResponsavel() {
        return nomeResponsavel;
    }
    public void setnomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public PlanoSaude getPlanoSaude() {	return planoSaude; }
    public void setPlanoSaude(PlanoSaude planoSaude) { this.planoSaude = planoSaude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return sexo == paciente.sexo &&
                Objects.equals(id, paciente.id) &&
                Objects.equals(nomeCrianca, paciente.nomeCrianca) &&
                Objects.equals(nomeResponsavel, paciente.nomeResponsavel) &&
                Objects.equals(dataNascimento, paciente.dataNascimento) &&
                Objects.equals(telefones, paciente.telefones) &&
                Objects.equals(endereco, paciente.endereco) &&
                Objects.equals(planoSaude, paciente.planoSaude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCrianca, nomeResponsavel, dataNascimento, sexo, telefones, endereco, planoSaude);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nomeCrianca='" + nomeCrianca + '\'' +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", telefones=" + telefones +
                ", endereco=" + endereco +
                ", planoSaude=" + planoSaude +
                '}';
    }
}
