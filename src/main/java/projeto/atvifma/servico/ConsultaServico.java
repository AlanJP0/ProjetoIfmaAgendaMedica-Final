package projeto.atvifma.servico;



import projeto.atvifma.modelo.Consulta;
import projeto.atvifma.repositorio.ConsultaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaServico {

    private final ConsultaRepositorio consultaRepositorio;

    @Autowired
    public ConsultaServico(ConsultaRepositorio consultaRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Consulta> todos() {
        return consultaRepositorio.findAll();
    }

    @Transactional
    public Consulta salva(Consulta consulta) {
        return consultaRepositorio.save(consulta);

    }

    @Transactional(readOnly = true)
    public Consulta buscaPor(Integer id) {
        return consultaRepositorio.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        consultaRepositorio.deleteById(id);
    }

    @Transactional
    public Consulta atualiza(Integer id, Consulta consulta) {

        Consulta consultaSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(consulta, consultaSalvo, "id");

        return consultaSalvo;
    }
}
