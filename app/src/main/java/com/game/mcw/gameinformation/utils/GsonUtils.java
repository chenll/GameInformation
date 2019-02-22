package com.game.mcw.gameinformation.utils;

//import com.fossil20.suso56.util.log.LogUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by louis on 2015/9/23.
 */
public class GsonUtils {


    private static final String TAG = "JSON ERROR!";

    public static String EMPTY_JSON_OBJECT = "{}";

    public static String EMPTY_JSON_ARRAY = "[]";

    private static GsonBuilder builder;

    private static Gson gson;

    private static GsonBuilder builderExclude;

    private static Gson gsonExclude;
    ;

    static {
        builder = new GsonBuilder();
        gson = builder.create();

        builderExclude = new GsonBuilder();
        gsonExclude = builderExclude.excludeFieldsWithoutExposeAnnotation().create();
    }


    /**
     * json序列化
     *
     * @param target 对象
     * @return 失败会返回{}或者[]
     */
    public static String toJson(Object target) {
        String result = EMPTY_JSON_OBJECT;
        try {
            if (target != null) {
                result = getGson(target.getClass()).toJson(target);
            }
        } catch (Exception e) {
            //LoggerUtil.e(TAG, e.getMessage());
            if (target instanceof Collection<?>
                    || target instanceof Iterator<?>
                    || target instanceof Enumeration<?>
                    || target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            } else {
                result = EMPTY_JSON_OBJECT;
            }

        }
        return result;
    }

    /**
     * json反序列化
     *
     * @param source
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String source, Class<T> cls) {
        try {
            return (T) getGson().fromJson(new StringReader(source), cls);
        } catch (Exception e) {
            e.printStackTrace();
            //LogUtil.e(e.getMessage());
        }

        return null;
    }

    /**
     * json反序列化
     *
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String source, TypeToken<T> type) {
        try {
            return (T) getGson().fromJson(new StringReader(source), type.getType());
        } catch (Exception e) {
            e.printStackTrace();
            //LogUtil.e(e.getMessage());
        }

        return null;
    }

    /**
     * json反序列化
     *
     * @param reader
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(Reader reader, TypeToken<T> type) {
        try {
            return (T) getGson().fromJson(reader, type.getType());
        } catch (Exception e) {
            e.printStackTrace();
            //LogUtil.e(e.getMessage());
        }
        return null;
    }

    private static Gson getGson() {
        return gson;
    }

    private static Gson getGson(Class<?> clazz) {
        if (clazz.getAnnotation(GsonExclude.class) != null) {
            return gsonExclude;
        } else {
            return gson;
        }
    }

}
