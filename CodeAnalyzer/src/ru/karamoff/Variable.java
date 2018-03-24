package ru.karamoff;

public class Variable {
    private String id;
    private int value;

    public Variable(String id, int value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;

        Variable var = (Variable) other;

        return this.id.equals(var.id);
    }

    public boolean equalsBoth(Variable other) {
        return this.id.equals(other.id) && this.value == other.value;
    }

    @Override
    public String toString() {
        return id + " = " + value;
    }
}
