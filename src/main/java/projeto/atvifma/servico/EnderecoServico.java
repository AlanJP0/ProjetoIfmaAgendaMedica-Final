package projeto.atvifma.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.atvifma.modelo.Endereco;
import projeto.atvifma.repositorio.EnderecoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoServico {
    private final EnderecoRepositorio enderecoRepositorio;

    @Autowired
    public EnderecoServico(EnderecoRepositorio enderecoRepositorio) {
        this.enderecoRepositorio = enderecoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Endereco> todos() {
        return enderecoRepositorio.findAll();
    }

    @Transactional
    public Endereco salva(Endereco endereco) {
        return enderecoRepositorio.save(endereco);

    }

    @Transactional(readOnly = true)
    public Endereco buscaPor(Integer id) {
        return enderecoRepositorio.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
        enderecoRepositorio.deleteById(id);
    }

    @Transactional
    public Endereco atualiza(Integer id, Endereco endereco) {

        Endereco enderecoSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(endereco, enderecoSalvo, "id");

        return  enderecoSalvo;
    }
}
