package projeto.atvifma.servico;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.atvifma.modelo.Medico;
import projeto.atvifma.repositorio.MedicoRepositorio;



public class MedicoServico {

    private final MedicoRepositorio medicoRepository;

    @Autowired
    public MedicoServico(MedicoRepositorio medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Transactional(readOnly = true)
    public List<Medico> todos() {
        return medicoRepository.findAll();
    }

    @Transactional
    public Medico salva(Medico medico) {
        return medicoRepository.save(medico);

    }

    @Transactional(readOnly = true)
    public Medico buscaPor(Integer id) {
        return medicoRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        medicoRepository.deleteById(id);
    }

    @Transactional
    public Medico atualiza(Integer id, Medico medico) {

        Medico medicoSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(medico, medicoSalvo, "id");

        return  medicoSalvo;
    }
}
