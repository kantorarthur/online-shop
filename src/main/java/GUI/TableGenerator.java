package GUI;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class TableGenerator {

    public static <T> JTable createTable(List<T> objects)
    {

        if (objects == null || objects.isEmpty())
        {
            return new JTable();
        }

        Class<?> class1 = objects.get(0).getClass();


        Field[] fields = class1.getDeclaredFields();


        List<Field> accessibleFields = List.of(fields)
                .stream()
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toList());


        String[] columnNames = accessibleFields.stream()
                .map(Field::getName)
                .toArray(String[]::new);


        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        objects.stream()
                .map(object ->
                        accessibleFields.stream()
                                .map(field -> {
                                    try
                                    {
                                        return field.get(object);
                                    }
                                    catch (IllegalAccessException e)
                                    {
                                        return "Error";
                                    }
                                })
                                .toArray(Object[]::new)
                )
                .forEach(model::addRow);

        return new JTable(model);
    }
}