package com.br.matheusEgabriel.naonda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.matheusEgabriel.naonda.dao.DaoCliente;
import com.br.matheusEgabriel.naonda.model.Cliente;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {

		System.out.println("Passou no index");
		return "index";
	}

	@RequestMapping(value = "formCliente", method = RequestMethod.POST)
	public String salvar(Cliente cliente) {
		System.out.println(cliente.getNome());
		System.out.println(cliente.getDataNascimento());
		System.out.println(cliente.getTelefone());
		System.out.println(cliente.getEmail());
		System.out.println(cliente.getGenero());
		System.out.println(cliente.getProdutoInteresse());

		System.out.println("Idade:" + cliente.getIdade());

		System.out.println("Passou no salvar");

		DaoCliente daoCliente = new DaoCliente();

		if (cliente.getId() == null) {
			daoCliente.inserir(cliente);
		} else {
			daoCliente.atualizar(cliente);
		}

		return "redirect:listacliente";
	}

	@RequestMapping("listacliente")
	public String listar(Model model) {

		DaoCliente dao = new DaoCliente();
		List<Cliente> lista = new ArrayList<Cliente>(new DaoCliente().listar());

		int jovem = 0, adulto = 0, idoso = 0;
		int seg = 0, terca = 0, quarta = 0, quinta = 0, sexta = 0, sabado = 0, domingo = 0;
		int manha = 0, tarde = 0, noite = 0;
		for (Cliente cliente : lista) {

			// listando idade
			if (cliente.getIdade() < 18) {
				jovem++;
			} else if (cliente.getIdade() < 60) {
				adulto++;
			} else {
				idoso++;
			}

			// listando o dia da semana
			if (new DaoCliente().dia(cliente.getId()) == 2) {
				seg++;
			} else if (new DaoCliente().dia(cliente.getId()) == 3) {
				terca++;
			} else if (new DaoCliente().dia(cliente.getId()) == 4) {
				quarta++;
			} else if (new DaoCliente().dia(cliente.getId()) == 5) {
				quinta++;
			} else if (new DaoCliente().dia(cliente.getId()) == 6) {
				sexta++;
			} else if (new DaoCliente().dia(cliente.getId()) == 7) {
				sabado++;
			} else if (new DaoCliente().dia(cliente.getId()) == 1) {
				domingo++;
			}
			
			// listando horarios
			
			if(new DaoCliente().hora(cliente.getId()) <= 12) {
				manha++;
			}else if(new DaoCliente().hora(cliente.getId()) <=18) {
				tarde++;
			}else {
				noite++;
			}
		}

		model.addAttribute("jovem", jovem);
		model.addAttribute("adulto", adulto);
		model.addAttribute("idoso", idoso);

		model.addAttribute("segunda", seg);
		model.addAttribute("terca", terca);
		model.addAttribute("quarta", quarta);
		model.addAttribute("quinta", quinta);
		model.addAttribute("sexta", sexta);
		model.addAttribute("sabado", sabado);
		model.addAttribute("domingo", domingo);
		
		model.addAttribute("manha", manha);
		model.addAttribute("tarde", tarde);
		model.addAttribute("noite", noite);

		model.addAttribute("cliente", lista);
		model.addAttribute("masculino", new DaoCliente().contar("M"));
		model.addAttribute("feminino", new DaoCliente().contar("F"));

		return "listacliente";// NOME DO ARQUIVO
	}

	@RequestMapping("excluirCliente")
	public String excluir(Long idCliente) {

		DaoCliente dao = new DaoCliente();
		dao.excluir(idCliente);
		return "redirect:listacliente";
	}

	@RequestMapping("alterarCliente") // model VAI TER O MESMO PAPEL DO req VAMOS "PERDURAR AS COISA"
	public String alterar(Long idCliente, Model model) {

		DaoCliente dao = new DaoCliente();
		model.addAttribute("cliente", dao.buscar(idCliente));

		return "forward:/";
	}

}
