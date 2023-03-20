package com.afd.views;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class GraphView {
    mxGraphComponent initGraph(){
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try{
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 40, 40, "shape=ellipse;perimeter=ellipsePerimeter;shadow=1");
            Object v2 = graph.insertVertex(parent, null, "World!", 40, 60, 40, 40, "shape=doubleEllipse;perimeter=ellipsePerimeter;indent=4");
            Object v3 = graph.insertVertex(parent, null, "World!", 60, 100, 40, 40, "shape=ellipse;perimeter=ellipsePerimeter");
            Object v4 = graph.insertVertex(parent, null, "World!", 80, 140, 40, 40, "shape=ellipse;perimeter=ellipsePerimeter");
            Object v5 = graph.insertVertex(parent, null, "World!", 100, 180, 40, 40, "shape=ellipse;perimeter=ellipsePerimeter");
            Object v6 = graph.insertVertex(parent, null, "World!", 400, 200, 40, 40, "shape=ellipse;perimeter=ellipsePerimeter");
            graph.insertEdge(parent, null, "Edge", v1, v2);
            graph.insertEdge(parent, null, "Edge1", v3, v2);
            graph.insertEdge(parent, null, "Edge2", v3, v4);
        }
        finally{
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        
        return graphComponent;
    }
}
