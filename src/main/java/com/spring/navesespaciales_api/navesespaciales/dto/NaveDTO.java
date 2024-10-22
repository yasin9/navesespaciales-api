package com.spring.navesespaciales_api.navesespaciales.dto;

public class NaveDTO {
    private Long id;
    private String name;
    private String series;
    private String tipo;

    // Constructor vacío
    public NaveDTO() {
    }
        
    // Constructor completo
    public NaveDTO(Long id, String name, String series, String tipo) {
        this.id = id;
        this.name = name;
        this.series = series;
        this.tipo = tipo;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
