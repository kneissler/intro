package com.kneissler.zoomui.interfaces;

import com.kneissler.zoomui.elements.Polygon;

import java.util.List;

public interface Layouter {
    public Polygon determineWrappingPolygon(List<Polygon> elementPolygons, LayoutHints layoutHnts);

    public List<Polygon> determineElementPolygons(Polygon boundaryPolygon, LayoutHints layoutHnts);

}
