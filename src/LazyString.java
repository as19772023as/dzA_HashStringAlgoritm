public class LazyString {
    private String source; // ссылка на исходную строку
    private int start, end; // границы нашей подстроки
    private int hash; // запоминаем хеш чтобы не пересчитывать

    private LazyString() {
    }

    public LazyString(String source, int start, int end) {
        this.source = source;
        this.start = start;
        this.end = end;

        // ВАШ КОД а тут нужно посчитать hash
        // просто сложите все коды символов нашей подстроки
        // и сохраните в поле hash из-за этого, создание LazyString через конструктор будет линейным
        StringBuilder pattern = new StringBuilder();
        int hash = 0;
        for (int i = 0; i < length(); i++) {
            hash += source.charAt(i);
            pattern.append(source.charAt(i));
        }
        this.hash = hash;
    }

    public LazyString shiftRight() {
        // Это способ создания новой LazyString через предыдущую, работает за О(1)
        LazyString shifted = new LazyString();
        shifted.source = source;
        shifted.start = start + 1;
        shifted.end = end + 1;

        // ВАШ КОД Вычислите хеш для shifted из хеша для исходной строки
        // и заполните его в shifted.hash.
        // Заметьте, что достаточно просто вычесть код того
        // символа, что исчез и прибавить код того символа, что появился
        if (end <= shifted.source.length()) {
            shifted.hash = hash - shifted.source.charAt(start) + shifted.source.charAt(end);
        }
        return shifted;
    }

    public int length() {
        return end - start;
    }

    public boolean equals(LazyString that) {
        // если не равны по длине, то не равны и вовсе
        if (length() != that.length()) {
            return false;
        }
        // перебираем и сравниваем на равенство все символы
        for (int i = 0; i < length(); i++) {
            char myChar = source.charAt(start + i);
            char thatChar = source.charAt(that.start + i);
            if (myChar != thatChar) { // если хотя бы один не совпал, то не равны
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash; // хеш у нас всегда предпосчитан для каждого объекта, чтобы не тратить на это время
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyString that = (LazyString) o;
        return this.equals(that);
    }
}


