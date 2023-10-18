package utils;


public class Utiles {

    public static int[] ValidarEntradaInt(String[] entrada)
    {

        try {
            int salidaD[] = new int[entrada.length];

            for (int i = 0; i < entrada.length; i++) {
                salidaD[i] = Integer.parseInt(entrada[i]);
            }
            return salidaD;
        } catch (Exception ex) {
            System.out.println("Entrada Inválida");
            return null;
        }

    }

    public static Integer[] ValidarEntradaInteger(String[] entrada)
    {


        try {
            Integer salidaD[] = new Integer[entrada.length];

            for (int i = 0; i < entrada.length; i++) {
                salidaD[i] = Integer.parseInt(entrada[i]);
            }
            return salidaD;
        } catch (Exception ex) {
            System.out.println("Entrada Inválida");
            return null;
        }

    }

    public static void imprimir(int[] miArreglo)
    {

        for(int i = 0; i<miArreglo.length; i++)
        {
            System.out.println("Elemento : "+i + " es :" + miArreglo[i]);
        }

    }

}

