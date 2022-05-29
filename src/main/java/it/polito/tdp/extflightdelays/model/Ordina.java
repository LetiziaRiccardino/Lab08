package it.polito.tdp.extflightdelays.model;

import java.util.Comparator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Ordina implements Comparator <DefaultWeightedEdge> {
	
	Graph<Airport, DefaultWeightedEdge> grafo;

	public Ordina(Graph<Airport, DefaultWeightedEdge> grafo) {
		super();
		this.grafo = grafo;
	}

	@Override
	public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
		// TODO Auto-generated method stub
		if(this.grafo.getEdgeSource(o1).getId()==this.grafo.getEdgeSource(o2).getId()) {
			return this.grafo.getEdgeTarget(o1).getId()-this.grafo.getEdgeTarget(o2).getId();
		}
		else
		return this.grafo.getEdgeSource(o1).getId()-this.grafo.getEdgeSource(o2).getId();
	}

	
	

}
