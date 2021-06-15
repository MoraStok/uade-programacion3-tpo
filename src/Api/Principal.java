package Api;

import java.util.ArrayList;

import Implementaciones.ResolverProblemaImplementacion;

public class Principal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ResolverProblemaImplementacion problema = new ResolverProblemaImplementacion();

        //Completar aca el uso uso de la clase ResolverProblemaImplementacion
        ArrayList<LetraResultado> Resultado = problema.obtenerResultado("DOS", "DOS", "X");
        for (int i = 0; i < Resultado.size(); i++) {
            System.out.print(Resultado.get(i).letra);
            System.out.print("-");
            System.out.println(Resultado.get(i).valor);
        }
    }
}
