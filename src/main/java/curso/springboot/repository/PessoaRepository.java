package curso.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Pessoa;

//Atenção com o importe do Transaction, deve ser o do spring...
@Repository
@Transactional  
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

}
