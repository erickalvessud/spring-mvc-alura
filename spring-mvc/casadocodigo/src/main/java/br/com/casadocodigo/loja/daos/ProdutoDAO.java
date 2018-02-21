package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public List<Produto> listar() {
		TypedQuery<Produto> query = this.entityManager.createQuery("SELECT P FROM Produto P", Produto.class);
		List<Produto> produtos = query.getResultList();
		return produtos;
	}

	public Produto find(Integer id) {
		return this.entityManager.createQuery("SELECT DISTINCT(P) FROM Produto P "
				+ "JOIN FETCH P.precos WHERE P.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
