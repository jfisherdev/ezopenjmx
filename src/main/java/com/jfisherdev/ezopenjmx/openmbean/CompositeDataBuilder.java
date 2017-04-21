package com.jfisherdev.ezopenjmx.openmbean;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is used to build a {@link CompositeData} instance.
 *
 * @author Josh Fisher
 */
public final class CompositeDataBuilder {

    public static CompositeDataBuilder newInstance(CompositeType type) {
        return new CompositeDataBuilder(type);
    }

    private final CompositeType type;
    private final Map<String, Object> data;

    private CompositeDataBuilder(CompositeType type) {
        this.type = type;
        data = new LinkedHashMap<>();
    }

    public CompositeDataBuilder setValue(String attribute, Object value) {
        data.put(attribute, value);
        return this;
    }

    public CompositeData build() {
        try {
            return new CompositeDataSupport(type, data);
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }

}
