package priv.onerice.ricenote.utils;

import com.alibaba.fastjson.JSONObject;
import priv.onerice.ricenote.base.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author onerice
 * @date 2023/4/10
 * @apiNote
 */
public class ServletUtil {
    /**
     * 渲染到客户端
     *
     * @param object 待渲染的实体类，会自动转为json
     */
    public static void render(HttpServletRequest request, HttpServletResponse response, Result object) throws IOException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        JSONObject.writeJSONString(response.getOutputStream(), object);
        //response.getWriter().print(JSONUtil.toJsonStr(object));
    }
}
