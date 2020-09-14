package phonebook;

/*
 *   class contains information about some person
 */
public class Contact implements Comparable<Contact> {
    private String number;
    private String name;

    private void setName(String[] array, int start) {
//      name of some people does not include number
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < array.length; i++) {
            stringBuilder.append(array[i] + " ");
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ' ') {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        this.name = stringBuilder.toString();
    }

    public Contact(String info, boolean doesItIncludeNumber) {
        //        information about a person contains in an array below
        String[] array = info.split("\\s");
        int start;
        if (doesItIncludeNumber) {
            this.number = array[0];
            start = 1;
        } else {
            this.number = null;
            start = 0;
        }
        setName(array, start);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Contact contact) {
        return this.getName().compareTo(contact.getName());
    }
}
