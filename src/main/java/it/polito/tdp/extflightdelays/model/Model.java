package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		for(Airport a :dao.loadAllAirports())
			idMap.put(a.getId(), a);
		Graphs.addAllVertices(grafo, idMap.values());
		
		for(Rotta rotta : this.getRotte(distanzaMedia)) {
			//controllo se esiste già un arco
			//se esiste, aggiorno il peso
			DefaultWeightedEdge arco = grafo.getEdge(rotta.getPartenza(), rotta.getArrivo());
			if(arco == null) {
				Graphs.addEdge(grafo, rotta.getPartenza(), rotta.getArrivo(), rotta.getPeso());
			} else {
				double peso = grafo.getEdgeWeight(arco);
				double newPeso = (peso + rotta.getPeso())/2;
				grafo.setEdgeWeight(arco, newPeso);
			}
						
		}
		
		String msg="\n";
		Set<DefaultWeightedEdge>set= grafo.edgeSet();
		List<DefaultWeightedEdge>archiOrdinati=new ArrayList<DefaultWeightedEdge>(set);
		Collections.sort(archiOrdinati, new Ordina(this.grafo));
		for(DefaultWeightedEdge r: archiOrdinati) {
			msg+=grafo.getEdgeSource(r).getId()+" "+grafo.getEdgeTarget(r).getId()+" "+grafo.getEdgeWeight(r)+"\n";//r.toString()
		}
		return "numero vertici: "+grafo.vertexSet().size()+ "\nnumero archi:"+this.grafo.edgeSet().size()+msg;
	}
	
	public List<Rotta> getRotte(int dMin){
		//uso la classe Rotta per salvare gli archi del grafo con il relativo peso
		List<Rotta> rotte = new ArrayList<Rotta>();
		for(Flight e : this.dao.loadAllFlights()) {
			if(e.getDistance()>=dMin) {
				rotte.add(new Rotta(idMap.get(e.getOriginAirportId()), idMap.get(e.getDestinationAirportId()), e.getDistance()));
			}
			
		}
		return rotte;
	}
	

}
