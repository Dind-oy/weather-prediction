import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunWeatherScript {
    public static void main(String[] args) {
        try {
            // Command untuk menjalankan script Python dengan argument
            String[] command = {
                "python", 
                "C:\\PythonProject\\weather-predict\\weather_predict_cmd.py", 
                "4.1",   // precipitation
                "4.4",   // temp_max
                "0.6",   // temp_min
                "5.3"    // wind
            };

            // Eksekusi perintah
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // Gabungkan output error dengan output standar
            Process process = processBuilder.start();

            // Membaca output dari script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String prediction = ""; // Variabel untuk menyimpan prediksi cuaca
            while ((line = reader.readLine()) != null) {
                prediction = line; // Simpan output terakhir sebagai prediksi
            }

            // Menunggu proses selesai dan mendapatkan kode keluaran
            int exitCode = process.waitFor();

            // Menampilkan hasil dengan format yang diinginkan
            System.out.println("Prediction Weather: " + prediction);
            System.out.println("Proses selesai dengan kode: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 