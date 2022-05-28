package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		idMap = new HashMap<Integer,Airport>();
		dao = new ExtFlightDelaysDAO();
		dao.loadAllAirports();
	}
	
	public String creaGrafo(int distanzaMedia) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		//Aggiungere i vertici
		Graphs.addAllVertices(grafo, idMap.values());
		
		for(Rotta rotta : this.getRotte()) {
			//controllo se esiste già un arco
			//se esiste, aggiorno il peso
			DefaultWeightedEdge edge = grafo.getEdge(rotta.getPartenza(), rotta.getArrivo());
			if(edge == null) {
				Graphs.addEdge(grafo, rotta.getPartenza(), rotta.getArrivo(), rotta.getPeso());
			} else {
				double peso = grafo.getEdgeWeight(edge);
				double newPeso = (peso + rotta.getPeso())/2;
				grafo.setEdgeWeight(edge, newPeso);
			}
						
		}
		return "numero vertici: "+grafo.vertexSet().size()+ "numero archi: "+this.grafo.edgeSet().size();
	}
	
	public List<Rotta> getRotte(){
		//uso la classe Rotta per salvare gli archi del grafo con il relativo peso
		List<Rotta> rotte = new ArrayList<Rotta>();
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			rotte.add(new Rotta(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), this.grafo.getEdgeWeight(e)));
		}
		return rotte;
	}
	

}
