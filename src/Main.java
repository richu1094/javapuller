import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        //https://api.themoviedb.org/3/movie/{movie_id}/images?api_key=cdbce95d5a79f025bdb488749921105f&language=es
        String endpoint = "https://api.themoviedb.org/3/tv/";
        String apiKey = "cdbce95d5a79f025bdb488749921105f";



        try {
            File archivo = new File("series_imagenes.txt");
            FileWriter escritorArchivo = new FileWriter(archivo);
            BufferedWriter bwEscritorArchivo = new BufferedWriter(escritorArchivo);

            for (int id = 1; id <= 10; id++) {
                URL url = new URL(endpoint + id + "/images?api_key=" + apiKey + "&language=es");
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

                if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conexion.getInputStream();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        bwEscritorArchivo.write(linea);
                    }
                    bwEscritorArchivo.newLine();
                    bwEscritorArchivo.newLine();

                    lector.close();
                } else {
                    System.out.println("Error en la solicitud con ID " + id);
                }
                bwEscritorArchivo.flush();
                bwEscritorArchivo.close();
                conexion.disconnect();
            }
            System.out.println("He acabado");
        } catch (IOException e) {
            System.out.println("Error en la creación o escritura del archivo: " + e.getMessage());
        }
    }
}