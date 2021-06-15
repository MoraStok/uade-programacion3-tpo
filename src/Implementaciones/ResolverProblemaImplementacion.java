package Implementaciones;

import Api.LetraResultado;
import Interfaces.ResolverProblemaInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

public class ResolverProblemaImplementacion implements ResolverProblemaInterface {

    /**
     * @Metodo: ObtenerResultado
     * @Parametros: String palabraUno, String palabraDos, String palabraResultado.
     * @Tarea: Genera el array de letra únicas, llama al método Criptoarimetica, y luego corrobora si tiene o no solucion.
     * @Return: ArrayList<LetraResultado> Resultado.
     * @Complejidad_temporal: O(U ^ N)
     */
    public ArrayList<LetraResultado> obtenerResultado(String palabraUno, String palabraDos, String palabraResultado) {

        String mayusPalabraUno = palabraUno.toUpperCase();
        String mayusPalabraDos = palabraDos.toUpperCase();
        String mayusPalabraResultado = palabraResultado.toUpperCase();

        ArrayList<Character> letrasUnicas = ExtraerUnicas(mayusPalabraUno, mayusPalabraDos, mayusPalabraResultado);
        ArrayList<LetraResultado> Resultado = new ArrayList<LetraResultado>();

        if (Criptoaritmetica(mayusPalabraUno, mayusPalabraDos, mayusPalabraResultado, letrasUnicas, Resultado, 0)) {
            System.out.println("Éxito!, el código ha sido descifrado");
        } else {
            System.out.println("Lamentablemente, el problema no tiene solución");
        }

        return Resultado;
    }


    /**
     * @Metodo: Criptoaritmética
     * @Parametros: String palabraUno, String palabraDos, String palabraResultado, ArrayList<Character> letrasUnicas, ArrayList<LetraResultado> Resultado, int E
     * @Tarea: Resolver un problema de criptoaritmética pasado por parámetro.
     * @Return: Boolean - Devuelve si es que hay solución o no.
     * @Complejidad_temporal: O(U ^ N)
     */
    public boolean Criptoaritmetica(String palabraUno, String palabraDos, String palabraResultado, ArrayList<Character> letrasUnicas, ArrayList<LetraResultado> Resultado, int E) {

        if (E == letrasUnicas.size()) {
            return Resuelto(palabraUno, palabraDos, palabraResultado, Resultado);
        } else {
            Boolean solucion;
            char Letra = letrasUnicas.get(E);
            for (int i = 0; i <= 9; i++) {
                Boolean valido = DigitoValido(Letra, i, palabraUno, palabraDos, palabraResultado, Resultado);
                if (valido) {
                    LetraResultado tupla = new LetraResultado();
                    tupla.letra = Letra;
                    tupla.valor = i;
                    Resultado.add(tupla);
                    solucion = Criptoaritmetica(palabraUno, palabraDos, palabraResultado, letrasUnicas, Resultado, E + 1);
                    if (!solucion) {
                        Resultado.remove(tupla);
                    } else {
                        return true;
                    }
                }
            }

            return false;
        }
    }


    /**
     * @Metodo: Resuelto
     * @Parametros: String palabraUno, String palabraDos, String palabraResultado, ArrayList<LetraResultado> Codigo
     * @Tarea: Responsablede convertir los strings a numeros utilizando el código obtenido.
     * @Return: Devuelve si la suma es correcta o no.
     * @Complejidad_Temporal: O(N * M)
     */
    public Boolean Resuelto(String palabraUno, String palabraDos, String palabraResultado, ArrayList<LetraResultado> Codigo) {

        int P1 = ConvertirStringAnumero(palabraUno, Codigo);
        int P2 = ConvertirStringAnumero(palabraDos, Codigo);
        int PR = ConvertirStringAnumero(palabraResultado, Codigo);
        return (P1 + P2 == PR);
    }


    /**
     * @Metodo: ConvertirStringAnumero
     * @Parametros: String palabra, ArrayList<LetraResultado> Codigo
     * @Tarea: Toma un string y lo convierte en número tomando como guia el codigo pasado por parametro.
     * @Return: Palabra codificada.
     * @Complejidad_Temporal: O(N * M)
     */
    public int ConvertirStringAnumero(String palabra, ArrayList<LetraResultado> Codigo) {

        ArrayList<Integer> palabraArrayInt = new ArrayList<Integer>();
        String numeroString = "";
        char[] palabraArrayChar = palabra.toCharArray();

        for (char letra :
                palabraArrayChar) {
            for (LetraResultado cod :
                    Codigo) {
                if (cod.letra == letra) {
                    palabraArrayInt.add(cod.valor);
                    break;
                }
            }
        }

        for (int num :
                palabraArrayInt) {
            numeroString += Integer.toString(num);
        }

        return Integer.parseInt(numeroString);
    }


    /**
     * @Metodo: DigitoValido
     * @Parametros: char Letra, int i, String palabraUno, String palabraDos, String palabraResultado, ArrayList<LetraResultado> Codigo
     * @Tarea: Corroborar validez del digito.
     * @Return: Boolean. True si es válido, False inválido.
     * @Complejidad_Temporal: O(N)
     */
    public Boolean DigitoValido(char Letra, int i, String palabraUno, String palabraDos, String palabraResultado, ArrayList<LetraResultado> Codigo) {

        if (i == 0) {
            if ((palabraUno.length() > 1 && Letra == palabraUno.charAt(0)) ||
                    (palabraDos.length() > 1 && Letra == palabraDos.charAt(0)) ||
                    (palabraResultado.length() > 1 && Letra == palabraResultado.charAt(0))) {
                return false;
            }
        }

        for (LetraResultado cod : Codigo
        ) {
            if (cod.valor == i) {
                return false;
            }
        }

        return true;
    }


    /**
     * @Metodo: ExtraerUnicas
     * @Parametros: String palabraUno, String palabraDos, String palabraResultado
     * @Tarea: Dadas 3 palabras pasadas por parámetro, obtiene un array de caracteres únicos
     * @Return: ArrayList<Character> arrLetrasUnicas. Array de letras unicas.
     * @Complejidad_Temporal: O(N)
     */
    public ArrayList<Character> ExtraerUnicas(String palabraUno, String palabraDos, String palabraResultado) {

        HashSet conjUnicas = new HashSet<Character>();
        ArrayList<Character> arrLetrasUnicas = new ArrayList<Character>();

        for (char letra : palabraUno.toCharArray()
        ) {
            conjUnicas.add(letra);
        }

        for (char letra : palabraDos.toCharArray()
        ) {
            conjUnicas.add(letra);
        }

        for (char letra : palabraResultado.toCharArray()
        ) {
            conjUnicas.add(letra);
        }


        // Crear iterador
        Iterator<Character> iter = conjUnicas.iterator();

        // Iterar valores del conjunto
        while (iter.hasNext()) {
            char value = iter.next();
            arrLetrasUnicas.add(value);
        }

        return arrLetrasUnicas;
    }
}