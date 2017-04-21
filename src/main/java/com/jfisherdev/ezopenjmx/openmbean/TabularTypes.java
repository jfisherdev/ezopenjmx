package com.jfisherdev.ezopenjmx.openmbean;

import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.TabularType;
import java.util.Set;

/**
 * This class contains some convenience methods for creating and working with {@link TabularType} instances.
 *
 * @author Josh Fisher
 */
public final class TabularTypes {

    private TabularTypes() {
    }

    /**
     * Creates a {@link TabularType} instance for the given row type and index names. The row type name and description are
     * used as the type name and description for the tabular type.
     *
     * @param rowType    the row type
     * @param indexNames set of key/index attribute names
     * @return a {@link TabularType} instance for the given row type and index names
     */
    public static TabularType createTabularType(CompositeType rowType, Set<String> indexNames) {
        return createTabularType(rowType.getTypeName(), rowType.getDescription(), rowType, indexNames);
    }

    /**
     * Convenience method for creating a {@link TabularType} instance that allows the index names to be specified by a set
     * and allows callers to not have to implement checked exception exception handling.
     *
     * @param typeName    tabular data type name
     * @param description tabular data type description
     * @param rowType     the row type
     * @param indexNames  set of key/index attribute names
     * @return a {@link TabularType} instance
     */
    public static TabularType createTabularType(String typeName, String description, CompositeType rowType, Set<String> indexNames) {
        try {
            return new TabularType(typeName, description, rowType, indexNames.toArray(new String[indexNames.size()]));
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }

}
