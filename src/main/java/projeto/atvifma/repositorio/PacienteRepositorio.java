package projeto.atvifma.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.atvifma.modelo.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Integer> {
}
