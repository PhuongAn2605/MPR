package edu.hanu.nfrs.tictactoe;

public enum Symbol {
    X("X"),
    O("O");

    private final String text;

    Symbol(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
