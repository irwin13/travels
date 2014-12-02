package travels.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

/**
 * Created by irwin on 12/3/14.
 */
@Provider
public class ObjectMapperProvider
        extends AbstractHttpContextInjectable<ObjectMapper>
        implements InjectableProvider<Context, Type> {

    @Override
    public ComponentScope getScope() {
        return ComponentScope.Singleton;
    }

    @Override
    public Injectable getInjectable(ComponentContext componentContext,
                                    Context context,
                                    Type type) {

        if (type.equals(ObjectMapper.class)) {
            return this;
        }
        return null;
    }

    @Override
    public ObjectMapper getValue(HttpContext httpContext) {
        return new ObjectMapper();
    }
}
