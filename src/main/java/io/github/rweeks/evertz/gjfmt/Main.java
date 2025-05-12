package io.github.rweeks.evertz.gjfmt;

public class Main {

    public sealed interface Causes {
        static StringCause stringCause(String cause) {
            return new StringCause(cause);
        }

        static ObjectCause objectCause(Object cause) { return new ObjectCause(cause); }
        static OtherStringCause otherStringCause(String profileId) {
            return new OtherStringCause(profileId);
        }
    }

    public record StringCause(String cause) implements Causes {}

    public record ObjectCause(Object object) implements Causes {}

    public record OtherStringCause(String cause) implements Causes {}


    public static void handleCause(Causes ex) {
        switch(ex) {
            case StringCause(String cause) -> System.out.println(cause);
            case OtherStringCause(String otherCause) -> System.out.println(otherCause);
            case ObjectCause(Object objectCause) -> System.out.println(objectCause);
            default -> throw new IllegalStateException("Unexpected value: " + ex);
        }
    }

    public static void main(String[] args) {
        handleCause(new OtherStringCause("formatting"));
        handleCause(new ObjectCause("works"));
        handleCause(new StringCause("testing"));
    }
}
