package travels.web.util;

import com.google.common.base.Strings;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.NumberTool;
import travels.web.WebConfiguration;

import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;

/**
 * Created by irwin on 12/2/14.
 */
public class VelocityUtil {

    private final WebConfiguration config;

    public VelocityUtil(WebConfiguration config) {
        this.config = config;
    }

    public static Properties classpathProperties(String activateCache,
                                                 String modificationCheckInterval) {
        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("resource.manager.logwhenfound", "false");

        velocityProperties.put("resource.loader", "class");
        velocityProperties.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityProperties.put("class.resource.loader.cache", activateCache);
        velocityProperties.put("class.resource.loader.modificationCheckInterval", modificationCheckInterval);

        return velocityProperties;
    }

    public static Properties urlProperties(String activateCache,
                                           String modificationCheckInterval,
                                           String rootUrl) {

        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("url.resource.manager.logwhenfound", "false");

        velocityProperties.put("resource.loader", "url");
        velocityProperties.put("url.resource.loader.class", "org.apache.velocity.runtime.resource.loader.URLResourceLoader");

        velocityProperties.put("url.resource.loader.cache", activateCache);
        velocityProperties.put("url.resource.loader.modificationCheckInterval", modificationCheckInterval);

        velocityProperties.put("url.resource.loader.root", rootUrl);

        return velocityProperties;
    }

    public String stringFromVm(String vmName, Map<String, Object> objectMap) {

        VelocityContext context = commonVelocityContext();
        Writer writer = new StringWriter();

        if (objectMap != null && !objectMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        Velocity.getTemplate(vmName).merge(context, writer);
        return writer.toString();
    }

    private VelocityContext commonVelocityContext() {
        VelocityContext context = new VelocityContext();
        context.put("dateTool", new DateTool());
        context.put("displayTool", new DisplayTool());
        context.put("escapeTool", new EscapeTool());
        context.put("numberTool", new NumberTool());
        context.put("velocityUtil", this);
        context.put("assetsUrl", config.getAssetsUrl());
        context.put("modelUrl", config.getModelUrl());
        return context;
    }

    // TODO for temporary, please replace it with utility class from velocity
    public String formatNumericLong(String pattern, Long value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    // TODO for temporary, please replace it with utility class from velocity
    public String formatNumericInteger(String pattern, Integer value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    // TODO for temporary, please replace it with utility class from velocity
    public int multiplyInt(int a, int b) {
        int result = a * b;
        return result;
    }

    public String formatNumericDouble(String pattern, Double value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    public boolean startWith(String value, String prefix){
        return value.toUpperCase().startsWith(prefix.toUpperCase());
    }

    public String trimStringTo(String value, int trimSize, String trailing) {
        if (Strings.isNullOrEmpty(value)) {
            return "";
        } else {
            if (value.length() <= trimSize) {
                return value;
            } else {
                return value.substring(0, trimSize) + trailing;
            }
        }
    }

}
