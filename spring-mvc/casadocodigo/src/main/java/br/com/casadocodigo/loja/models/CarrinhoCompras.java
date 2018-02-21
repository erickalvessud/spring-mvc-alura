package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();
	
	public Collection<CarrinhoItem> getItens(){
		return itens.keySet();
	}
	
	public void add(CarrinhoItem item) {
		this.itens.put(item, getQuantidade(item) + 1);
	}

	private Integer getQuantidade(CarrinhoItem item) {
		if ( !this.itens.containsKey(item)) {
			this.itens.put(item, 0);
		}
		return this.itens.get(item);
	}
	
	public Integer getQuantidade() {
		return this.itens.size();
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
}
