package ua.com.juja.core.week6.part1.lab37TryWithResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 С выходом  Java 7 появились дополнительные инструменты для работы с ресурсами в блоке  try.

 try-with-resources механизм, который позволяет закрывать все ресурсы открытые вначале блока.

 Для понимания работы новых функций и поддержки старых прооектов рассмотрим следующую схему:

 Интерфейс AutoCloseableFactory:

 public AutoCloseable create() throws Throwable; - "эмулятор" работы конструктора некоторого экземпляра AutoCloseable.

 Интерфейс TryBody:

 public void runBody() throws Throwable; –  “эмулятор” “тела блока try”

 Пример реализации для одного ресурса:

 Решение на Java 7 с использованием try-with-resources

 public class AnswerOneResourceJava7 {

 public static void call(AutoCloseableFactory factory, TryBody body) throws Throwable {

 try (AutoCloseable resource = factory.create()) {

 body.runBody();

 }

 }

 }

 Решение на Java 6 БЕЗ использования try-with-resources.

 public class AnswerOneResourceJava6 {

 public static void call(AutoCloseableFactory factory, TryBody body) throws Throwable {

 // --- create A ---

 AutoCloseable resource = factory.create();

 // --- run body ---

 try {

 body.runBody();

 } catch (Throwable bodyEx) {

 try {

 resource.close();

 } catch (Throwable closeEx) {

 bodyEx.addSuppressed(closeEx);

 }

 throw bodyEx;

 }

 // --- close ---

 resource.close();

 }

 }

 Задание:

 Для работы с двумя ресурами на Java7 реализован такой код:

 public class AnswerTwoResourcesJava7 {

 public static void call(

 AutoCloseableFactory factoryA,

 AutoCloseableFactory factoryB,

 TryBody body) throws Throwable {

 try (AutoCloseable a = factoryA.create();

 AutoCloseable b = factoryB.create()

 ) {

 body.runBody();

 }

 }

 }

 Необходимо реализовать эту логику БЕЗ использования try-with-resources.
 */
public class TryWithResource {
    public static void main(String[] args) {
        //prepare
        final List actualHistoryCall = new ArrayList();
        final List expectedExceptionTextAndOrder = new ArrayList();
        expectedExceptionTextAndOrder.add("Error factoryB.createB");
        expectedExceptionTextAndOrder.add("Error closeA");
        List expectedHistoryCall = Arrays.asList("factoryA.createA", "factoryB.createB", "A.close");

        final AutoCloseable resourceA = new AutoCloseable() {
            @Override
            public void close() throws Exception {
                actualHistoryCall.add("A.close");
                throw new Error((String) expectedExceptionTextAndOrder.get(1));
            }
        };

        final AutoCloseable resourceB = new AutoCloseable() {
            @Override
            public void close() throws Exception {
                actualHistoryCall.add("B.close");
            }
        };

        AutoCloseableFactory factoryA = new AutoCloseableFactory() {
            @Override
            public AutoCloseable create() throws Throwable {
                actualHistoryCall.add("factoryA.createA");
                return resourceA;
            }


        };

        AutoCloseableFactory factoryB = new AutoCloseableFactory() {
            @Override
            public AutoCloseable create() {
                actualHistoryCall.add("factoryB.createB");
                throw new Error((String) expectedExceptionTextAndOrder.get(0));
            }


        };

        TryBody tryBody = new TryBody() {
            @Override
            public void runBody() {
                actualHistoryCall.add("TryBody.runBody");
                //NOP
            }
        };

        //call and  check
        try {
            TryWithResource.twoResource(factoryA, factoryB, tryBody);
        } catch (Throwable e) {

            if (!expectedExceptionTextAndOrder.get(0).equals(e.getMessage()))
                throw new AssertionError("Not correct main exception");

            if (e.getSuppressed().length == 0)
                throw new AssertionError("Should be suppressed exceptions  ");

            if(!expectedExceptionTextAndOrder.get(1).equals(e.getSuppressed()[0].getMessage()))
                throw new AssertionError("Not correct suppressed exception should to be "+expectedExceptionTextAndOrder.get(1) +" but found " + e.getSuppressed()[0].getMessage());
        }


        if (!actualHistoryCall.equals(expectedHistoryCall))
            throw new AssertionError("Not correct order call should be " + expectedHistoryCall.toString() + " but found " + actualHistoryCall.toString());

        System.out.print("OK");
    }

    public static void twoResource(AutoCloseableFactory factoryA, AutoCloseableFactory factoryB, TryBody body) throws Throwable {
        AutoCloseable resourceA = factoryA.create();
        AutoCloseable resourceB = factoryB.create();

        try {
            body.runBody();
        } catch (Throwable bodyEx) {
            try {
                resourceA.close();
            } catch (Throwable closeExA) {
                try {
                    resourceB.close();
                } catch (Throwable closeExB) {
                    bodyEx.addSuppressed(closeExB);
                }

                bodyEx.addSuppressed(closeExA);
            }
            try {
                resourceB.close();
            } catch (Throwable closeExB) {
                bodyEx.addSuppressed(closeExB);
            }

            throw bodyEx;
        }

        resourceA.close();
        resourceB.close();
    }
}

interface AutoCloseableFactory {
    public AutoCloseable create() throws Throwable;
}

interface TryBody {
    public void runBody() throws Throwable;
}