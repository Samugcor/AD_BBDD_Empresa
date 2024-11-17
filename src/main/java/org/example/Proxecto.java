package org.example;

public class Proxecto {
    private int numProxecto;
    private String nomeProxecto;
    private String lugar;
    private int numDepartamento;

    // CONSTRUCTOR
    public Proxecto(int numProxecto, String nomeProxecto, String lugar, int numDepartamento) {
        this.numProxecto = numProxecto;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
        this.numDepartamento = numDepartamento;
    }

    public Proxecto() {
    }

    // GETTERS Y SETTERS
    public int getNumProxecto() {
        return numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    public String getNomeProxecto() {
        return nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    @Override
    public String toString() {
        return "Numero de proxecto:" + numProxecto +
                "\nNome do proxecto:'" + nomeProxecto +
                "\nLugar:'" + lugar +
                "\nNumero de departamento:" + numDepartamento;
    }
}

