package curso.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}

	
	/*Resumo do método salvar: Sempre que o o botão "salvarpessoa" for clicado o método intercepta a requisição, 
	 * pega os dados do formulátio (por isso ele recebe um parâmetro pessoa) e envia esses dados ao DB, sempre
	 * utilizando a Interface PessoaRepository. Como retorno, ele redireciona o fluxo para a página "cadastro/cadastropessoa"
	 * 
	 * */
	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public String salvar(Pessoa pessoa) {

		pessoaRepository.save(pessoa);

		return "cadastro/cadastropessoa";
	}

}
