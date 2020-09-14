package phonebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SortingAndSearching {

    private long bubbleSortTime;
    private long linearSearchTime;
    private long jumpSearchTime;
    private long binarySearchTime;
    private long qsSearchTime;
    private long creatingHashTime;
    private long findingHashTime;
    private final Files files;
    //    number of matches in every searching algorithm
    private int matches;
    private List<Contact> bufferPhoneBook;

    public SortingAndSearching() {
        this.files = new Files();
        this.bufferPhoneBook = new ArrayList<>();
    }

    // just linear search
    void doLinearSearch() {
        matches = 0;
        long startingTime = System.currentTimeMillis();
        for (var i : files.getSearchList()) {
            for (var j : files.getPhoneBook()) {
                if (Objects.equals(j.getName(), i.getName())) {
                    matches++;
                    break;
                }
            }
        }
        linearSearchTime = System.currentTimeMillis() - startingTime;
    }

    private boolean bubbleSort() {
        long value = 0L;
        long startingTime = System.currentTimeMillis();
        bufferPhoneBook = new ArrayList<>(files.getPhoneBook());
        for (int i = 0; i < bufferPhoneBook.size() - 1; i++) {
            for (int j = 0; j < bufferPhoneBook.size() - 1 - i; j++) {
                if (bufferPhoneBook.get(j + 1).compareTo(bufferPhoneBook.get(j)) > 0) {
                    Collections.swap(bufferPhoneBook, j, j + 1);
                }
            }
            value = System.currentTimeMillis() - startingTime;
            if (value / 10 > linearSearchTime) {
                bubbleSortTime = value;
                return false;
            }
        }
        bubbleSortTime = value;
        return true;
    }

    public long getCreatingHashTime() {
        return creatingHashTime;
    }

    public long getFindingHashTime() {
        return findingHashTime;
    }

    boolean jumpSearch() {
        if (!bubbleSort()) {
            doLinearSearch();
            return false;
        }
        matches = 0;
        long startingTime = System.currentTimeMillis();
        int step = (int) Math.sqrt(files.getSearchList().size());
        for (var contact : files.getSearchList()) {
            int previousStep = 0;
            int currentStep = 0;
            if (contact.compareTo(bufferPhoneBook.get(currentStep)) == 0) {
                matches++;
                continue;
            }
            while (currentStep < bufferPhoneBook.size() - 1) {
                currentStep = Math.min(bufferPhoneBook.size() - 1, currentStep + step);

                if (bufferPhoneBook.get(currentStep).compareTo(contact) >= 0) {
                    break;
                }
                previousStep = currentStep;
            }
            if ((currentStep == bufferPhoneBook.size() - 1) && contact.compareTo(bufferPhoneBook.get(currentStep)) > 0) {
                continue;
            }
            for (int j = currentStep; j > previousStep; j--) {
                if (contact.compareTo(bufferPhoneBook.get(j)) == 0) {
                    matches++;
                    break;
                }
            }
        }
        jumpSearchTime = System.currentTimeMillis() - startingTime;
        return true;
    }

    void doQSAndBS() {
        matches = 0;
        bufferPhoneBook.clear();
        bufferPhoneBook = files.getPhoneBook();
        long startTime = System.currentTimeMillis();
        quickSort(bufferPhoneBook, 0, bufferPhoneBook.size() - 1);
        qsSearchTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        for (var i : files.getSearchList()) {
            if (binarySearch(bufferPhoneBook, i) != -1) {
                matches++;
            }
        }
        binarySearchTime = System.currentTimeMillis() - startTime;
    }

    private int binarySearch(List<Contact> book, Contact target) {
        int left = 0;
        int right = book.size() - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) >>> 1;
            if (book.get(middle).compareTo(target) > 0) {
                left = middle + 1;
            } else if (book.get(middle).compareTo(target) < 0) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private void quickSort(List<Contact> book, int left, int right) {
        if (left < right) {
            int index = getIndex(book, left, right);
            quickSort(book, left, index - 1);
            quickSort(book, index + 1, right);
        }
    }

    // method for quick sort
    private int getIndex(List<Contact> book, int left, int right) {
        Contact pivot = book.get(right);
        int index = left;
        for (int i = left; i < right; i++) {
            if (book.get(i).compareTo(pivot) > 0) {
                Collections.swap(book, i, index);
                index++;
            }
        }
        Collections.swap(book, right, index);
        return index;
    }

    void hashing() {
        HashTable hashTable = new HashTable(files.getPhoneBook().size());
        long startT = System.currentTimeMillis();
        for (var i : files.getPhoneBook()) {
            hashTable.put(i);
        }
        creatingHashTime = System.currentTimeMillis() - startT;
        startT = System.currentTimeMillis();
        matches = 0;
        for (var i : files.getSearchList()) {
            if (hashTable.doTablesContain(i.getName())) {
                matches++;
            }
        }
        findingHashTime = System.currentTimeMillis() - startT;
    }

    void printTimeWithMatches(Long time) {
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                matches, files.getSearchList().size(), time / 60000, time % 60000 / 1000, time % 60000 % 1000);
    }

    void printTime(Long time) {
        System.out.printf("%d min. %d sec. %d ms.", time / 60000, time % 60000 / 1000,
                time % 60000 % 1000);
    }

    public long getBubbleSortTime() {
        return bubbleSortTime;
    }

    public long getLinearSearchTime() {
        return linearSearchTime;
    }

    public long getJumpSearchTime() {
        return jumpSearchTime;
    }

    public long getBinarySearchTime() {
        return binarySearchTime;
    }

    public long getQsSearchTime() {
        return qsSearchTime;
    }
}
