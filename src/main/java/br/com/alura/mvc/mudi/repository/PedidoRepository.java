package br.com.alura.mvc.mudi.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StausPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, BigDecimal>{
	
	@Query("SELECT p FROM Pedido p WHERE p.status = :status and p.user.username = :username")
	public List<Pedido> findByStatusAndUsername(StausPedido status, String username, Pageable page);

	@Query("SELECT p FROM Pedido p WHERE p.user.username = :nameUser")
	public List<Pedido> findAllByUsuario(String nameUser);
	
	@Cacheable("books")
	public List<Pedido> findByStatus(StausPedido status, Pageable page);
	
	public Pedido findById(Long id);
}
