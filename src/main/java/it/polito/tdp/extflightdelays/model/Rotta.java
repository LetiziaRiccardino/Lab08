package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	Airport partenza;
	Airport arrivo;
	Double peso;
	public Rotta(Airport partenza, Airport arrivo, Double peso) {
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
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	

}
