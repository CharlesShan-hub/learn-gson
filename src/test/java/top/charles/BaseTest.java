package top.charles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

public class BaseTest {
    Gson gson = new Gson();
    Gson prettyGson = new GsonBuilder()
            .setPrettyPrinting()  // 这里！
            .create();
    Gson gsonWithNull = new GsonBuilder()
            .serializeNulls()   // 多了这一行
            .setPrettyPrinting()
            .create();
    Gson prettyDataGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setPrettyPrinting()
            .create();
    Gson exposeGson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
}
