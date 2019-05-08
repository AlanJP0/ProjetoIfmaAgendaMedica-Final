package projeto.atvifma.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.atvifma.modelo.Paciente;
import projeto.atvifma.repositorio.PacienteRepositorio;


import java.util.List;

@Service
public class PacienteServico {

    private final PacienteRepositorio pacienteRepositorio;

    @Autowired
    public PacienteServico(PacienteRepositorio pacienteRepository) {
        this.pacienteRepositorio = pacienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Paciente> todos() {
        return pacienteRepositorio.findAll();
    }

    @Transactional
    public Paciente salva(Paciente paciente) {
        return pacienteRepositorio.save(paciente);

    }

    @Transactional(readOnly = true)
    public Paciente buscaPor(Integer id) {
        return pacienteRepositorio.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        pacienteRepositorio.deleteById(id);
    }

    @Transactional
    public Paciente atualiza(Integer id, Paciente paciente) {

        Paciente pacienteSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(paciente, pacienteSalvo, "id");

        return  pacienteSalvo;
    }
}