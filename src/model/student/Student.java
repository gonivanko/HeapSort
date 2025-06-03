package model.student;

public class Student {
    private final String name;
    private final String surname;
    private final String groupName;

    public Student(String name, String surname, String groupName) {
        this.name = name;
        this.surname = surname;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getGroupName() {
        return groupName;
    }

    public String toString() {
        return String.format("%8s | %s | %s", name, surname, groupName);
    }
    public static Student createRandom() {
        String[] studentNames = {"Ivan", "Alex", "Mike", "Jack", "John"};
        String[] studentSurnames = {"Honcharenko", "Petrenko", "Ivanenko", "Shevchenko"};
        String[] studentGroupNames = {"IP-21", "IP-22", "IP-23", "IP-24"};

        String randomName = studentNames[(int) (Math.random() * studentNames.length)];
        String randomSurname = studentSurnames[(int) (Math.random() * studentSurnames.length)];
        String randomGroupName = studentGroupNames[(int) (Math.random() * studentGroupNames.length)];

        return new Student(randomName, randomSurname, randomGroupName);
    }
}
