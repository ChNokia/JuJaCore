package ua.com.juja.core.SQLCmd;

import java.util.Arrays;

/**
 * Created by Anka on 30.08.2015.
 */
public class DataSet {
    static class Data {
        private String name;
        private  Object value;

        public Data (String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public Data[] data = new Data[100]; //TODO: remove magig 100
    public int currentIndex = 0;

    public String[] getNames() {
        String[] names = new String[currentIndex];

        for ( int i = 0; i < currentIndex; i++ ) {
            names[i] = data[i].getName();
        }

        return  names;
    }

    public Object[] getValues() {
        Object[] values = new Object[currentIndex];

        for ( int i = 0; i < currentIndex; i++ ) {
            values[i] = data[i].getValue();
        }

        return  values;
    }

    public void put(String name, Object value) {
        data[currentIndex] = new Data(name, value);
        currentIndex += 1;
    }

    @Override
    public String toString() {
        return "DataSet{\n" +
                "names:" + Arrays.toString(getNames()) + "\n" +
                "values:" + Arrays.toString(getValues()) + "\n" +
                "}";
    }
}
