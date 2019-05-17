package projeto.atvifma.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.atvifma.modelo.Telefone;
import projeto.atvifma.servico.TelefoneServico;

import java.util.List;


@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    private final TelefoneServico telefoneServico;

    @Autowired
    public TelefoneControle(TelefoneServico telefoneServico) {
        this.telefoneServico = telefoneServico;
    }

    @GetMapping
    public List<Telefone> todos() {
        return telefoneServico.todos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telefone salva(@RequestBody Telefone telefone) {
        return telefoneServico.salva(telefone);
    }

    @GetMapping("/{id}")
    public Telefone buscaPor(@PathVariable Integer id) {
        return telefoneServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        telefoneServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public Telefone altera(@PathVariable  Integer id, @RequestBody Telefone telefone) {
        return  telefoneServico.atualiza(id, telefone );
    }
}
