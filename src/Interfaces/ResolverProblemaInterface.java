package Interfaces;

import java.util.ArrayList;

import Api.LetraResultado;

public interface ResolverProblemaInterface {
    ArrayList<LetraResultado> obtenerResultado(String palabraUno, String palabraDos, String palabraResultado);
}
