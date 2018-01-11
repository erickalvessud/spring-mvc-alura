package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		TipoPreco[] tipoPrecos = TipoPreco.values();
		modelAndView.addObject("tipoPrecos", tipoPrecos);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		this.produtoDAO.gravar(produto);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Produto "+produto.getDescricao()+" cadastrado com sucesso!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = this.produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
