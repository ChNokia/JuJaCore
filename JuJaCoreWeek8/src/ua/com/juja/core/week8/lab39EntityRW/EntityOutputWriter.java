package ua.com.juja.core.week8.lab39EntityRW;
/*
В системе есть два класса сущностей class Point class Person.
Для работы с этими сущностями было создано два интерфейса EntityOutput EntityInput, которые декларируют методы записи и считывания сущностей.
Интерфейс EntityOutput уже реалиован в классе EntityOutputWriter.
По образу и подобию нужно реализовать интерфейс EntityInput в классе EntityInputReader.
Для класса EntityInputReader необходимо создать конструктор со следующей сигнатурой:
public EntityInputReader(Reader in)
 */
import java.io.*;

public class EntityOutputWriter implements EntityOutput {
    private final Writer out;

    public static void main(String[] args) throws IOException {
        Person expectedPerson = new Person("John", 32);

        StringWriter outStringWriter = new StringWriter();
        EntityOutputWriter writer = new EntityOutputWriter(outStringWriter);

        writer.writePerson(expectedPerson);
        String rawString = outStringWriter.toString();


        //call
        StringReader inStringReader = new StringReader(rawString);

        EntityInputReader reader = new EntityInputReader(inStringReader);

        Person actualPerson = reader.readPerson();

        //check

        if (!actualPerson.getName().equals(expectedPerson.getName()))
            throw new AssertionError("Expected Person name equals " + expectedPerson.getName() + " but found " + actualPerson.getName());

        if (actualPerson.getAge() != expectedPerson.getAge())
            throw new AssertionError("Expected Person age equals " + expectedPerson.getAge() + " but found " + actualPerson.getAge());


        System.out.print("OK");
    }

    public EntityOutputWriter(Writer out) {
        this.out = out;
    }

    public void writePerson(Person person) throws IOException {
        int age = person.getAge();
        String name = person.getName();
        out.write("<person>n");
        out.write("    <age>" + age + "</age>n");
        out.write("    <name>" + name + "</name>n");
        out.write("</person>n");
        out.flush();
    }

    @Override
    public void writePoint(Point point) throws IOException {
        out.write("<point x=" + point.getX() + " y=" + point.getY() + "></point>n");
        out.flush();
    }
}

interface EntityInput {
    public Person readPerson() throws IOException;

    public Point readPoint() throws IOException;
}

interface EntityOutput {
    public void writePerson(Person person) throws IOException;

    public void writePoint(Point point) throws IOException;
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name=" + name  + ", age=" + age + "}";
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        if (x < 0 || 15 < x) {
            throw new IllegalArgumentException();
        }
        if (y < 0 || 15 < y) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" + x + ", " + y + "}";
    }
}

class EntityInputReader implements EntityInput {
    private Reader in;

    /*BODY*/
    public EntityInputReader(Reader in) {
        this.in = in;
    }

    @Override
    public Person readPerson() throws IOException {
        BufferedReader bufRead = new BufferedReader(in);
        String dataLine = bufRead.readLine();

        int indexBegin = dataLine.indexOf("<age>");
        int indexEnd = dataLine.indexOf("</age>");
        String age = dataLine.substring(indexBegin + "<age>".length(), indexEnd);

        indexBegin = dataLine.indexOf("<name>");
        indexEnd = dataLine.indexOf("</name>");
        String name = dataLine.substring(indexBegin + "<name>".length(), indexEnd);

        return new Person(name, Integer.parseInt(age));
    }

    @Override
    public Point readPoint() throws IOException {
        BufferedReader bufRead = new BufferedReader(in);
        String dataLine = bufRead.readLine();

        String age = dataLine.substring("<point x=".length(), dataLine.length() - "></point>n".length());
        String[] data = age.split(" y=");

        return new Point(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
    }
}


