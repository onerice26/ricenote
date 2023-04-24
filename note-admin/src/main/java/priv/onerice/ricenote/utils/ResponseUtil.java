package priv.onerice.ricenote.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import priv.onerice.ricenote.base.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author onerice
 * @date 2023/4/16
 * @apiNote
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, Result result) {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = null;
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            writer = response.getWriter();
            mapper.writeValue(writer, result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
