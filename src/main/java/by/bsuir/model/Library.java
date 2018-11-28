package by.bsuir.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty first;
    private SimpleStringProperty second;
    private SimpleStringProperty third;

    public Library() {
    }

    public Library(SimpleStringProperty first, SimpleStringProperty second, SimpleStringProperty third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Library(String first, String second, String third) {
        this.first = new SimpleStringProperty(first);
        this.second = new SimpleStringProperty(second);
        this.third = new SimpleStringProperty(third);
    }

    public String getFirst() {
        return first.get();
    }

    public SimpleStringProperty firstProperty() {
        return first;
    }

    public void setFirst(String first) {
        this.first.set(first);
    }

    public String getSecond() {
        return second.get();
    }

    public SimpleStringProperty secondProperty() {
        return second;
    }

    public void setSecond(String second) {
        this.second.set(second);
    }

    public String getThird() {
        return third.get();
    }

    public SimpleStringProperty thirdProperty() {
        return third;
    }

    public void setThird(String third) {
        this.third.set(third);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(first, library.first) &&
                Objects.equals(second, library.second) &&
                Objects.equals(third, library.third);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "Library{" +
                "russian word=" + first +
                ", foreign word=" + second +
                ", language of tr=" + third +
                '}';
    }
}
