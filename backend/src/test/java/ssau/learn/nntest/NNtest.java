package ssau.learn.nntest;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.python.util.PythonInterpreter;
import ssau.learn.dto.RealEstateDto;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class NNtest {

    @Test
    public void testPython(){
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            RealEstateDto realEstateDto = new RealEstateDto();
            realEstateDto.setId(1488L);
            realEstateDto.setAddress("ULITSA ZERO");

            pyInterp.exec("print('"+realEstateDto.getAddress()+"')");

        }
    }

    private String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }

    @Test
    public void testPyFile() throws FileNotFoundException, ScriptException {
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader(resolvePythonScriptPath("src/main/resources/python/hello.py")), context);
        System.out.println(writer);
    }


    public double getCost(){
        Response response =
        given()
                .accept("application/json")
                .when()
                .param("city","Курская область")
                .param("address","S6983")
                .log().all()
                .get("http://127.0.0.1:8000/realEstate")
                .then().log().all().extract().response();
        return Double.parseDouble(response.getBody().path("cost"));
    }
}
