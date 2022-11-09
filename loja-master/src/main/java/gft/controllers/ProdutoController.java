package gft.controllers;

import gft.dto.produto.ConsultaProdutoDTO;
import gft.dto.produto.ProdutoMapper;
import gft.dto.produto.RegistroProdutoDTO;
import gft.entities.Produto;
import gft.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaProdutoDTO>> buscarTodosOsProdutos(@PageableDefault Pageable pageable) {

        return ResponseEntity.ok(produtoService.listarTodosOsProdutos(pageable).map(ProdutoMapper::fromEntity));


    }

    @PostMapping
    public ResponseEntity<ConsultaProdutoDTO> salvarProduto(@RequestBody RegistroProdutoDTO dto) {

        Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDTO(dto));

        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));

    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> buscarProduto(@PathVariable Long id) {

        Produto produto = produtoService.buscarProduto(id);

        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));


    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> alterarProduto(@RequestBody RegistroProdutoDTO dto,
                                                             @PathVariable Long id) {

        try {

            Produto produto = produtoService.atualizarProduto(ProdutoMapper.fromDTO(dto), id);

            return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaProdutoDTO> excluirProduto(@PathVariable Long id) {

        try {
            produtoService.excluirProduto(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("usuario")
    @PreAuthorize("hasAuthority('Usuario')")
    public ResponseEntity<String> metodoGetUsuario() {
        return ResponseEntity.ok("Usuário chegou aqui!");
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> metodoGetAdmin() {
        return ResponseEntity.ok("Admin chegou aqui!");
    }
}
