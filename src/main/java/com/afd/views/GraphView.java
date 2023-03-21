package com.afd.views;

import com.afd.afdcheck.AFD;
import com.afd.afdcheck.State;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javafx.util.Pair;

public class GraphView {
    
    final int triangle = 10;
    final int TRIANGLE_RADIUS = triangle / 2;
    final int NODE_SIZE = 40;
    private AFD afd;
    
    public GraphView(AFD afd) {
        this.afd = afd;
    }
    
    public mxGraphComponent initGraph(){
        HashMap<State, Boolean> visited = new HashMap<>();
        Queue<State> q = new LinkedList<>();
        HashMap<State, Object> nodes = new HashMap<>();
        ArrayList<Edge> edges = new ArrayList<>(); 
                
        mxGraph graph = new mxGraph() {	
            // Removes the folding icon and disables any folding
            public boolean isCellFoldable(Object cell, boolean collapse){
                    return false;
            }
        };
        
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try{
            /*
            *
            * Estado inicial
            * 
            */
            State initialState = afd.getInitialState();
            mxCell v1 = (mxCell) graph.insertVertex(parent, null, initialState.getName(), 20, 20, NODE_SIZE, NODE_SIZE, shape(initialState.isIsAceptingState()));
            v1.setConnectable(false);
            //triangulo
            mxGeometry geo1 = new mxGeometry(0, 0.5, triangle, triangle);
            //movemos el triangulo al centro
            geo1.setOffset(new mxPoint(-TRIANGLE_RADIUS, -TRIANGLE_RADIUS));
            geo1.setRelative(true);
            mxCell triangle = new mxCell(null, geo1, "shape=triangle;perimter=trianglePerimeter");
            triangle.setVertex(true);
            triangle.setConnectable(false);
            graph.addCell(triangle, v1);
            //fin del diseño estado inicial
            //Recorremos por primera vez para iniciar el recorrido
            for(Map.Entry entry : initialState.getTransitions().entrySet()){
                State s = (State)entry.getValue();
                String simbol = (String)entry.getKey();
                q.add(s);
                edges.add(new Edge(initialState, s, simbol));
            }
            nodes.put(initialState, v1);
            visited.put(initialState, Boolean.TRUE);
            
            /*
            *
            * Iniciamos el recorrido BFS del automata
            *
            */
            int currPos = 60;
            while(!q.isEmpty()){
                State s = q.poll();
                if(visited.get(s) == Boolean.TRUE) continue;
                
                Object v = graph.insertVertex(parent, null, s.getName(), currPos + 20, currPos, NODE_SIZE, NODE_SIZE, shape(s.isIsAceptingState()));
                nodes.put(s, v);
                for(Map.Entry entry : s.getTransitions().entrySet()){
                    State st = (State)entry.getValue();
                    String simbol = (String)entry.getKey();
                    q.add(st);
                    edges.add(new Edge(s, st, simbol));
                }
                visited.put(s, Boolean.TRUE);
                currPos += 40;
            }
            
            /*
            *
            * Inciamos a crear las conexiones
            *
            */
            for(Edge e : edges){
                graph.insertEdge(parent, null, e.simbol, nodes.get(e.v1), nodes.get(e.v2));
            }
            
        } finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        
        return graphComponent;
    }
    
    String shape(boolean isAcceptingState){
        return (isAcceptingState) ? "shape=doubleEllipse;perimeter=ellipsePerimeter;indent=4" : "shape=ellipse;perimeter=ellipsePerimeter" ;
    }
    
    class Edge{
        State v1,v2;
        String simbol;
        public Edge(State v1, State v2, String simbol) {
            this.v1 = v1;
            this.v2 = v2;
            this.simbol = simbol;
        }
    }
}