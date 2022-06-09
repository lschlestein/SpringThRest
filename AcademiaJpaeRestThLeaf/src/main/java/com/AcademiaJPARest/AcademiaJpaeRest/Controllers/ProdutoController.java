package com.AcademiaJPARest.AcademiaJpaeRest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaJPARest.AcademiaJpaeRest.Models.Produto;
import com.AcademiaJPARest.AcademiaJpaeRest.Repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> getprodutos() {
		return produtoRepository.findAll();
	}

	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@GetMapping("/{id}")
	Produto getproduto(@PathVariable Long id) {

		return produtoRepository.findById(id).get();

	}

	@DeleteMapping("/excluir/{id}")
	@ResponseStatus(HttpStatus.OK)
	void deleteproduto(@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}

	@PutMapping("/alterar/{id}")
	Produto updateproduto(@RequestBody Produto novoProduto, @PathVariable Long id) {
		Produto p = produtoRepository.findById(id).get();
		if ((novoProduto.getCodigo() != 0)) {
			p.setCodigo(novoProduto.getCodigo());
		}
		if (novoProduto.getNome() != null) {
			p.setNome(novoProduto.getNome());
		}
		p.setCategoria(novoProduto.getCategoria());
		p.setValor(novoProduto.getValor());
		p.setQuantidade(novoProduto.getQuantidade());
		return produtoRepository.save(p);

	}

}
