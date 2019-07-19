package pers.lance.platform.base.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.util.Objects;

/**
 * Gson工具类
 *
 * @author lance
 * @date 2017-10-09
 */
public class GsonUtils {
    private static Gson gson = null;
    private static Gson nullAndDateHandlerGosn = null;
    private static Gson longTransferDateGson = null;

    /**
     * 获取普通的gson
     *
     * @return
     */
    public static Gson getGson() {
        if (Objects.isNull(gson)) {
            synchronized (GsonUtils.class) {
                if (Objects.isNull(gson)) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    /**
     * null值属性也需要序列化\设置日期转换
     *
     * @return
     */
    public static Gson getNullAndDateHandlerGosn() {
        if (Objects.isNull(nullAndDateHandlerGosn)) {
            synchronized (GsonUtils.class) {
                if (Objects.isNull(nullAndDateHandlerGosn)) {
                    nullAndDateHandlerGosn = new GsonBuilder()
                            //null值属性也需要序列化
                            .serializeNulls()
                            //设置日期转换
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();
                }
            }
        }
        return nullAndDateHandlerGosn;
    }

    /**
     * json串中Long型日期转Date
     *
     * @return
     */
    public static Gson getLongTransferDateGson() {
        if (Objects.isNull(longTransferDateGson)) {
            synchronized (GsonUtils.class) {
                if (Objects.isNull(longTransferDateGson)) {
                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(java.util.Date.class, new JsonDeserializer() {
                        @Override
                        public java.util.Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
                        }
                    });
                    longTransferDateGson = builder.create();
                }
            }
        }
        return longTransferDateGson;
    }


}


