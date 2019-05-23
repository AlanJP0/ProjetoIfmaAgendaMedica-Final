package projeto.atvifma.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.atvifma.modelo.Consulta;


public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {
}
