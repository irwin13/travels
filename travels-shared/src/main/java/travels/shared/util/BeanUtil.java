package travels.shared.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by irwin on 11/22/14.
 */
public final class BeanUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    public static final String objectsToString(Object bean, boolean includeSuperClass) {
        StringBuilder sb = new StringBuilder();

        Class<? extends Object> clazz = bean.getClass();

        sb.append("\n==== " + bean.getClass().getName() + " ======\n");
        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if ((method.getName().startsWith("get") ||
                    method.getName().startsWith("is")) &&
                    !method.getName().equalsIgnoreCase("getClass") &&
                    !method.getName().contains("Password")) {

                if (!Collection.class.isAssignableFrom(method.getReturnType())) {
                    try {
                        sb.append(fieldNameFromGetMethod(method.getName()) + " = " + method.invoke(bean, ((Object[])null)) + "\n");
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    } catch (InvocationTargetException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    }
                }
            }
        }

        sb.append("=============================================\n");
        return sb.toString();

    }

    public static String fieldNameFromGetMethod(String methodName) {
        String field;
        if (methodName.startsWith("is")) {
            field = methodName.substring(2, methodName.length());
        } else {
            field = methodName.substring(3, methodName.length());
        }
        return (field.substring(0, 1).toLowerCase() + field.substring(1, field.length()));
    }

}
