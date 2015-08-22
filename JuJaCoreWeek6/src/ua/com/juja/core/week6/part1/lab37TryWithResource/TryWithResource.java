package ua.com.juja.core.week6.part1.lab37TryWithResource;

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
    public static void twoResource(AutoCloseableFactory factoryA, AutoCloseableFactory factoryB, TryBody body) throws Throwable {
        AutoCloseable resourceA = factoryA.create();
        AutoCloseable resourceB = null;

        try {
            resourceB = factoryB.create();
        } catch (Throwable bodyEx) {
            resourceA.close();
        }

        try {
            body.runBody();
        } catch (Throwable bodyEx) {
            try {
                resourceA.close();
                resourceB.close();
            } catch (Throwable closeEx) {
                bodyEx.addSuppressed(closeEx);
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
