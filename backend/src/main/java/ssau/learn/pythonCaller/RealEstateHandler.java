package ssau.learn.pythonCaller;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ssau.learn.dto.RealEstateDto;

import javax.script.*;
import java.io.*;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class RealEstateHandler {


    public static RealEstateDto eval(RealEstateDto initValue){
        System.out.println(initValue.getAddress());
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec("x = 10+10");
            PyObject x = pyInterp.get("x");
        }
        return initValue;
    }
    private static String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }

    public static void runPyFile() throws IOException, ScriptException {
        try{
            String pythonPath = "C:/Users/Alex/Desktop/06022023/web/backend/src/main/resources/python/hello.py";
            //String pythonExe = "C:/Users/AppData/Local/Continuum/Anaconda/python.exe";
            ProcessBuilder pb = new ProcessBuilder("python", pythonPath);
            Process p = pb.start();

            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            System.out.println("Running Python starts: " + line);
            line = bfr.readLine();
            System.out.println("First Line: " + line);
            while ((line = bfr.readLine()) != null){
                System.out.println("Python Output: " + line);
            }

        }catch(Exception e){System.out.println(e);}
    }

    public static double getCost(RealEstateDto dto){
        String city = dto.getAddress().split("/")[0];
        String address = dto.getAddress().split("/")[1];
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        UriComponents uriComponents = UriComponentsBuilder.fromUri(URI.create("http://127.0.0.1:8000/realEstate"))
                        .queryParam("city",city)
                        .queryParam("address",address).build();
        URI uri = uriComponents.toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch
        (Exception e){
            e.printStackTrace();
        }
        EstateFromPython est = new Gson().fromJson(response.body(), EstateFromPython.class);
        return est.cost;
    }

    public static void main(String[] args) throws ScriptException, IOException, InterruptedException {

    }
}
