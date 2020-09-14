package phonebook;

import java.util.Objects;

/*
 * This class is my hash table representation for String
 * The key is main thing, number can be anything(I mean null or String)
 * Hashing = adding every char in some string,modulo dividing by size of Table
 * */
public class HashTable {
    static class Table {
        private final String number;
        private final String key;

        Table(String key, String number) {
            this.key = key;
            this.number = number;
        }

        public String getKey() {
            return key;
        }
    }

    private final Table[] tables;
    private final int size;

    public HashTable(int size) {
//        I use the closest power of two as the hash table size
        int s = (int) Math.pow(2, Math.ceil(Math.log(size) / Math.log(2)));
        tables = new Table[s];
        this.size = s;
    }

    public void put(Contact contact) {
        int ind = find(contact.getName());
        if (ind == -1) {
            return;
        }
        tables[ind] = new Table(contact.getName(), contact.getNumber());
    }

    public boolean doTablesContain(String key) {
        int idx = find(key);
        return idx != -1 && tables[idx] != null;
    }

    private int find(String key) {
        int firstThreeLettersInAscii = key.hashCode();
        if (firstThreeLettersInAscii < 0) {
            firstThreeLettersInAscii *= -1;
        }
        int hash = firstThreeLettersInAscii % size;
        boolean b = true;
        while (tables[hash] != null && b) {
            if (Objects.equals(key, tables[hash].getKey())) {
                b = false;
            } else {
                hash = (hash + 1) % size;
                if (hash == firstThreeLettersInAscii % size) {
                    return -1;
                }
            }
        }
        return hash;
    }
}
