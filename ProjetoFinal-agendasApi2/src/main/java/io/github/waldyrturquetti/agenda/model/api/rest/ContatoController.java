package io.github.waldyrturquetti.agenda.model.api.rest;

import io.github.waldyrturquetti.agenda.model.entity.Contato;
import io.github.waldyrturquetti.agenda.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

    private final ContatoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save( @RequestBody Contato contato ){
        return repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        repository.deleteById(id);
    }

//    @GetMapping
//    public List<Contato> list(){
//        return repository.findAll();
//    }

    @GetMapping
    public Page<Contato> list(
            @RequestParam(value = "page", defaultValue = "0")   Integer pagina,
            @RequestParam(value = "size", defaultValue = "10")  Integer tamanhoPagina
    ){
        //Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
        return repository.findAll(pageRequest);
    }


    //É utilizado o Patch pois é só um atributo que iremos atualizar,
    //se fosse tudo ou mais de um, usariamos o PUT.
//    @PatchMapping("{id}/favorito")
//    public void favorite( @PathVariable Integer id, @RequestBody Boolean favorite ){
//        Optional<Contato> contato = repository.findById(id);
//        contato.ifPresent( c -> {           //O ifPresent é executado apenas se o objeto existir
//            c.setFavorito(favorite);
//            repository.save(c);
//        });
//    }

    @PatchMapping("{id}/favorito")
    public void favorite( @PathVariable Integer id ){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
             repository.save(c);
        });
    }

    // dominio/api/contatos/1/foto
    @PutMapping("{id}/foto")
    public byte[] addPhoto(@PathVariable Integer id,
                           @RequestParam("foto") Part arquivo) {
        Optional<Contato> contato = repository.findById(id);
        return contato.map(c -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                c.setFoto(bytes);
                repository.save(c);
                is.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }
}
