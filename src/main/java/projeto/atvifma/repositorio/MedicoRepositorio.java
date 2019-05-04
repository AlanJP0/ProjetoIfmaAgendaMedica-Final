package projeto.atvifma.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.atvifma.modelo.Medico;

public class MedicoRepositorio extends JpaRepository<Medico, Integer> {
}
