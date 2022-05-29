package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	Airport partenza;
	Airport arrivo;
	Integer peso;
	public Rotta(Airport partenza, Airport arrivo, Integer peso) {
		super();
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.peso = peso;
	}
	public Airport getPartenza() {
		return partenza;
	}
	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}
	public Airport getArrivo() {
		return arrivo;
	}
	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Rotta [partenza=" + partenza + ", arrivo=" + arrivo + ", peso=" + peso + "]";
	}
	
	
	
	

}
