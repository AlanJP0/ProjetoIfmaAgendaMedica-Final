package projeto.atvifma.servico;

import projeto.atvifma.modelo.Telefone;
import projeto.atvifma.repositorio.TelefoneRepositorio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class TelefoneServico {

    private final TelefoneRepositorio telefoneRepositorio;

    @Autowired
    public TelefoneServico(TelefoneRepositorio telefoneRepository) {
        this.telefoneRepositorio = telefoneRepository;
    }

    @Transactional(readOnly = true)
    public List<Telefone> todos() {
        return telefoneRepositorio.findAll();
    }

    @Transactional
    public Telefone salva(Telefone telefone) {
        return telefoneRepositorio.save(telefone);

    }

    @Transactional(readOnly = true)
    public Telefone buscaPor(Integer id) {
        return telefoneRepositorio.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        telefoneRepositorio.deleteById(id);
    }

    @Transactional
    public Telefone atualiza(Integer id, Telefone telefone) {

        Telefone telefoneSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(telefone, telefoneSalvo, "id");

        return  telefoneSalvo;
    }
}
