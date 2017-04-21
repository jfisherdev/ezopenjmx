package com.jfisherdev.ezopenjmx.openmbean;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to build a {@link TabularData} instance.
 *
 * @author Josh Fisher
 */
public final class TabularDataBuilder {

    public static TabularDataBuilder newInstance(CompositeType rowType, Set<String> indexNames) {
        return newInstance(TabularTypes.createTabularType(rowType, indexNames));
    }

    public static TabularDataBuilder newInstance(TabularType tabularType) {
        return new TabularDataBuilder(tabularType);
    }

    private final TabularType tabularType;
    private final TabularData tabularData;
    private final Map<Integer, CompositeDataBuilder> rowBuilders = new LinkedHashMap<>();
    private Integer rowIndex = -1;

    private TabularDataBuilder(TabularType tabularType) {
        this.tabularType = tabularType;
        tabularData = new TabularDataSupport(tabularType);
    }

    /**
     * Creates a new row.
     *
     * @return this builder instance with a new row added
     */
    public TabularDataBuilder addRow() {
        rowIndex++;
        rowBuilders.put(rowIndex, CompositeDataBuilder.newInstance(tabularType.getRowType()));
        return this;
    }

    /**
     * Adds a new row and sets the row data values from {@code rowData}.
     *
     * @param rowData row data
     * @return this builder instance with a new row added and values set
     */
    public TabularDataBuilder addRow(CompositeData rowData) {
        addRow();
        CompositeDataBuilder rowBuilder = getCurrentRowBuilder();
        for (String attribute : tabularType.getRowType().keySet()) {
            rowBuilder.setValue(attribute, rowData.get(attribute));
        }
        return this;
    }

    /**
     * Sets the value of {@code column} to {@code value} in the current row. If there isn't a current row, an exception will
     * be thrown.
     *
     * @param column the attribute/column whose value to set
     * @param value  the value to set the attribute/column to
     * @return this builder instance after setting the column value in the current row
     */
    public TabularDataBuilder setValue(String column, Object value) {
        if (rowIndex < 0) {
            throw new RuntimeException("addRow(...) must be invoked before values can be set.");
        }
        getCurrentRowBuilder().setValue(column, value);
        return this;
    }

    private CompositeDataBuilder getCurrentRowBuilder() {
        return rowBuilders.get(rowIndex);
    }

    public TabularData build() {
        if (rowIndex > -1) {
            final int rowCount = rowIndex - 1;
            for (int row = 0; row < rowCount; row++) {
                tabularData.put(rowBuilders.get(row).build());
            }
        }
        return tabularData;
    }

}
