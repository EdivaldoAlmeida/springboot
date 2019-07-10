package curso.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}

	
	/*Resumo do método salvar: Sempre que o o botão "salvarpessoa" for clicado o método intercepta a requisição, 
	 * pega os dados do formulátio (por isso ele recebe um parâmetro pessoa) e envia esses dados ao DB, sempre
	 * utilizando a Interface PessoaRepository. Como retorno, ele redireciona o fluxo para a página "cadastro/cadastropessoa"
	 * 
	 * */
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa") /*Os dois ** ignoram tudo que vem antes de salvarpessoa*/
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		/*Já irá retornar a lista de pessas após salvar*/
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		/*A linha acima arrega um objeto vazio para, pois a linha 15 do form. cadastro de pessoas 
		 * sempre espera um objeto pessoa ao ser carregado se não houver dará erro. Esse procedimento
		 * é utilizado todas as vezes que se precisa exibir essa tela*/

		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa()); 
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Pessoa pessoa = pessoaRepository.findOne(idpessoa);
		modelAndView.addObject("pessoaobj", pessoa);
		
		return modelAndView;

	}
	
	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {
		
		pessoaRepository.delete(idpessoa);
		
		/*Após deletar ele retorna para a mesma tela de cadastro e carrega todas as pessoas*/
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;

	}
	

}
