package projeto.atvifma.modelo;

import javax.persistence.*;
import projeto.atvifma.modelo.Semana.Dias;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class agendaM {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Dias diaDaSemana;

    @ElementCollection
    @CollectionTable(name = "agenda_horarios", joinColumns = @JoinColumn(name = "agenda_id"))
    @Column(name = "horario")
    private List<LocalTime> horarios = new ArrayList();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dias getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(Dias diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public List<LocalTime> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<LocalTime> horarios) {
        this.horarios = horarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        agendaM agendaM = (agendaM) o;
        return Objects.equals(id, agendaM.id) &&
                diaDaSemana == agendaM.diaDaSemana &&
                Objects.equals(horarios, agendaM.horarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diaDaSemana, horarios);
    }
}
