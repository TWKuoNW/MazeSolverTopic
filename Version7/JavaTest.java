import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaTest {
    public static void main(String[] args) {
        try {
            // 建立 ProcessBuilder 物件
            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\s1042\\Desktop\\Version7\\PCode\\go.py");
            
            // 啟動子進程
            Process process = pb.start();
            
            // 獲取子進程的輸出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            // 讀取輸出
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // 等待子進程結束
            int exitCode = process.waitFor();
            
            // 檢查結束狀態
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
            } else {
                System.out.println("Python script execution failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
