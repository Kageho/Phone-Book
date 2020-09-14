package phonebook;

public final class UnderTheHood {
    public UnderTheHood() {
//        it runs each algorithm and print information about searching and sorting
        SortingAndSearching sortingAndSearching = new SortingAndSearching();
        System.out.println("Start searching (linear search)...");
        sortingAndSearching.doLinearSearch();
        sortingAndSearching.printTimeWithMatches(sortingAndSearching.getLinearSearchTime());

        System.out.println("\n\nStart searching (bubble sort + jump search)...");
        boolean isBubbleSortOk = true;
        long time;
        if (sortingAndSearching.jumpSearch()) {
            time = sortingAndSearching.getBubbleSortTime() + sortingAndSearching.getJumpSearchTime();
        } else {
            time = sortingAndSearching.getBubbleSortTime() + sortingAndSearching.getLinearSearchTime();
            isBubbleSortOk = false;
        }
        sortingAndSearching.printTimeWithMatches(time);
        System.out.print("\nSorting time: ");
        sortingAndSearching.printTime(sortingAndSearching.getBubbleSortTime());
        System.out.print(isBubbleSortOk ? "" : " - STOPPED, moved to linear search");
        System.out.println();
        System.out.print("Searching time: ");
        sortingAndSearching.printTime(sortingAndSearching.getLinearSearchTime() + sortingAndSearching.getJumpSearchTime());

        System.out.println("\n\nStart searching (quick sort + binary search)...");
        sortingAndSearching.doQSAndBS();
        sortingAndSearching.printTimeWithMatches(sortingAndSearching.getBinarySearchTime() + sortingAndSearching.getQsSearchTime());
        System.out.print("\nSorting time: ");
        sortingAndSearching.printTime(sortingAndSearching.getQsSearchTime());
        System.out.print("\nSearching time: ");
        sortingAndSearching.printTime(sortingAndSearching.getBinarySearchTime());

        System.out.println("\n\nStart searching (hash table)...");
        sortingAndSearching.hashing();
        sortingAndSearching.printTimeWithMatches(sortingAndSearching.getCreatingHashTime() + sortingAndSearching.getFindingHashTime());
        System.out.print("\nCreating time: ");
        sortingAndSearching.printTime(sortingAndSearching.getCreatingHashTime());
        System.out.print("\nSearching time: ");
        sortingAndSearching.printTime(sortingAndSearching.getFindingHashTime());
    }
}
