package com.organizamoney.backend.springmonolith.teste;

import com.organizamoney.backend.springmonolith.teste2.ClasseForaDoPacote;

public abstract class MinhaClasseSelada {
    public abstract Number getNumero();
}

sealed class Snake permits Snake.Cobra {
    private int length = 5;
    final class Cobra extends Snake {
        private int length = 8;

        public void method() {
            System.out.println(this.length);
            System.out.println(Snake.this.length);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Snake.Cobra cobra = new Snake().new Cobra();
        cobra.method();
    }
}