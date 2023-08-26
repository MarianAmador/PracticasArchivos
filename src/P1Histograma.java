import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class P1Histograma {
    public static void main(String[] args) {

        BufferedReader bLeeAr = null;
        try {

            HashMap<Integer, Integer> histogramaAlma = new HashMap<>();
            bLeeAr = new BufferedReader(new FileReader("src/divina_comedia_sp.txt"));
            String palabra = bLeeAr.readLine();



            while (palabra != null) {

                String[] espaciosSepara= palabra.split("\\s+");

                for (String noLet : espaciosSepara) {

                    noLet = noLet.replaceAll("[^a-zA-Z]", "");

                    int longitud = noLet.length();

                    if (noLet.length() >= 2 && noLet.length() <= 10) {

                        histogramaAlma.put(longitud, histogramaAlma.getOrDefault(longitud, 0) + 1);

                    }
                }
                palabra = bLeeAr.readLine();
            }


            for (int longitud : histogramaAlma.keySet()) {
                int frecuencia = histogramaAlma.get(longitud);
                System.out.println("Longitud: " + longitud + "\n" +  "Las palabras de esta longitud aparecen: " + frecuencia + " veces." + "\n" + "-----------------------------------------------------" + "\n");
            }

        } catch (IOException excepción) {
            excepción.printStackTrace();
        } finally {
            try {
                if (bLeeAr != null) {
                    bLeeAr.close();  // Cerrar el archivo al finalizar, en caso de éxito o error
                }
            } catch (IOException exception2) {
                exception2.printStackTrace();
            }
        }
    }
}
