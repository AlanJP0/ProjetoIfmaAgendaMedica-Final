package projeto.atvifma.controle.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import projeto.atvifma.modelo.Paciente;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PacienteDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nomeCrianca;

    @NotNull
    @Size(min = 2, max = 50)
    private String nomeResponsavel;

    @NotNull
    private Date dataNascimento;

    @NotNull
    @Size(min = 2)
    private char sexo;

    private DTO<Paciente, PacienteDTO> dto = new DTO<>(this);

    public PacienteDTO() {  }

    public PacienteDTO(Paciente paciente) {
        this.comDadosDe(paciente );
    }

    public PacienteDTO comDadosDe(Paciente paciente) {
        return dto.comDadosDe(paciente );
    }

    public Paciente atualizaIgnorandoNuloA(Paciente paciente) {
        return dto.mergeIgnorandoNulo(paciente );
    }

    @JsonIgnore
    public Paciente getPaciente() {
        return dto.getEntity(new Paciente() );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "id=" + id +
                ", nomeCrianca='" + nomeCrianca + '\'' +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                ", dto=" + dto +
                '}';
    }
}
