package projeto.atvifma.controle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.atvifma.modelo.Endereco;
import projeto.atvifma.servico.EnderecoServico;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    private final EnderecoServico enderecoServico;

    public EnderecoControle(EnderecoServico enderecoServico) {
        this.enderecoServico = enderecoServico;
    }

    @GetMapping
    public List<Endereco> todos() {
        return enderecoServico.todos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salva(@RequestBody Endereco endereco) {
        return enderecoServico.salva(endereco);
    }

    @GetMapping("/{id}")
    public Endereco buscaPor(@PathVariable Integer id) {
        return enderecoServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        enderecoServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public Endereco altera(@PathVariable  Integer id, @RequestBody Endereco endereco) {
        return  enderecoServico.atualiza(id, endereco );
    }
}


