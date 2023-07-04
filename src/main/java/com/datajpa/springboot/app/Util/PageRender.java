package com.datajpa.springboot.app.Util;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender <T>{
    private String url;
    private Page<T> page;

    private int paginaActual;
    private int totalPaginas;
    private int cantElementosPorPagina;

    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<>();
        this.cantElementosPorPagina = page.getSize();
        this.totalPaginas = page.getTotalPages();
        this.paginaActual = page.getNumber();

        int desde, hasta;
        if (totalPaginas <= cantElementosPorPagina){
            desde=1;
            hasta=totalPaginas;
        }else {
            if (paginaActual <= cantElementosPorPagina/2){
                desde =1;
                hasta = cantElementosPorPagina;
            }else if (paginaActual >= totalPaginas - cantElementosPorPagina/2){
                desde=totalPaginas - cantElementosPorPagina;
                hasta = cantElementosPorPagina;
            }else {
                desde = paginaActual - cantElementosPorPagina/2;
                hasta = cantElementosPorPagina;
            }
        }

        for (int i=0; i<hasta; i++){
            paginas.add(new PageItem(desde+i,paginaActual == desde+i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst(){
        return page.isFirst();
    }
    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
}
