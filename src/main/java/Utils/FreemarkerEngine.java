package Utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FreemarkerEngine {


    public FreemarkerEngine() {

    }

    public void render(HttpServletResponse rs, String tmplName, HashMap<String, Object> data) throws IOException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_30);
        conf.setDefaultEncoding(String.valueOf((StandardCharsets.UTF_8)));
        conf.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        try(PrintWriter w = rs.getWriter()){
            conf.getTemplate(tmplName).process(data ,w);

        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
