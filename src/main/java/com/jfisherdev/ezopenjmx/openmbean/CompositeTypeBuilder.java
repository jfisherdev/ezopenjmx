package com.jfisherdev.ezopenjmx.openmbean;

import com.jfisherdev.ezopenjmx.util.EZOpenJMXStringUtils;

import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class is used to build a {@link CompositeType} instance.
 *
 * @author Josh Fisher
 */
public final class CompositeTypeBuilder {

    public static CompositeTypeBuilder newInstance(String typeName) {
        return newInstance(typeName, typeName);
    }

    public static CompositeTypeBuilder newInstance(String typeName, String typeDescription) {
        return new CompositeTypeBuilder(typeName, typeDescription);
    }

    private final String typeName;
    private final String description;
    private final Set<CompositeTypeAttribute> attributes = new LinkedHashSet<>();

    private CompositeTypeBuilder(String typeName, String description) {
        this.typeName = EZOpenJMXStringUtils.checkNotNullOrEmpty(typeName);
        this.description = EZOpenJMXStringUtils.checkNotNullOrEmpty(description);
    }

    public CompositeTypeBuilder addAttribute(OpenType<?> type, String name) {
        return addAttribute(type, name, name);
    }

    public CompositeTypeBuilder addAttribute(OpenType<?> type, String name, String description) {
        attributes.add(new CompositeTypeAttribute(name, description, type));
        return this;
    }

    public CompositeType build() {
        final int attributesSize = attributes.size();
        if (attributesSize < 1) {
            throw new RuntimeException("At least one attribute must be specified.");
        }
        try {
            final String[] names = new String[attributesSize];
            final String[] descriptions = new String[attributesSize];
            final OpenType<?>[] types = new OpenType<?>[attributesSize];
            int index = 0;
            for (CompositeTypeAttribute attribute : attributes) {
                names[index] = attribute.name;
                descriptions[index] = attribute.description;
                types[index] = attribute.type;
                index++;
            }
            return new CompositeType(typeName, description, names, descriptions, types);
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }

    private static class CompositeTypeAttribute {

        private final String name;
        private final String description;
        private final OpenType<?> type;

        CompositeTypeAttribute(String name, String description, OpenType<?> type) {
            this.name = name;
            this.description = description;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            CompositeTypeAttribute that = (CompositeTypeAttribute) o;

            return name.equals(that.name) &&
                    description.equals(that.description) &&
                    type.equals(that.type);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + description.hashCode();
            result = 31 * result + type.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "CompositeTypeAttribute{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", type=" + type +
                    '}';
        }

    }

}
