package com.zxq.iov.cloud.sp.vp.api.util.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tokxp
 * Date: 14-12-12
 * Time: 上午10:46
 */
public class FileTypeHelper {

    public static final String FT_SP = "SP";
    private static final List<String> FILE_TYPES = new ArrayList<String>();

    static {
        Field[] fields = FileTypeHelper.class.getDeclaredFields();
        for(Field field : fields) {
            if(Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())
                    && Modifier.isFinal(field.getModifiers()) && field.getType().equals(String.class)) {
                try {
                    FILE_TYPES.add(field.get(null).toString());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean isValid(String fileType) {
        return FILE_TYPES.contains(fileType);
    }

}
