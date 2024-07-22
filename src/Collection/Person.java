package Collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


	
	class Person {
	    private int id;
	    private String name;
	    private int age;

	    public Person(int id, String name, int age) {
	        this.id = id;
	        this.name = name;
	        this.age = age;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getAge() {
	        return age;
	    }

	    @Override
	    public int hashCode() {
	        return Integer.hashCode(id);
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Person person = (Person) obj;
	        return id == person.id;
	    }

	    @Override
	    public String toString() {
	        return "Person{id=" + id + ", name='" + name + "', age=" + age + '}';
	    }
	}

	class PersonSetOperations {
	    public static Set<Person> union(Set<Person> set1, Set<Person> set2) {
	        Set<Person> resultSet = new HashSet<>(set1);
	        resultSet.addAll(set2);
	        return resultSet;
	    }

	    public static Set<Person> intersection(Set<Person> set1, Set<Person> set2) {
	        Set<Person> resultSet = new HashSet<>(set1);
	        resultSet.retainAll(set2);
	        return resultSet;
	    }

	    public static Set<Person> difference(Set<Person> set1, Set<Person> set2) {
	        Set<Person> resultSet = new HashSet<>(set1);
	        resultSet.removeAll(set2);
	        return resultSet;
	    }

	    public static Person findMaxAge(Set<Person> set) {
	        return set.stream().max(Comparator.comparingInt(Person::getAge)).orElse(null);
	    }

	    public static Person findMinAge(Set<Person> set) {
	        return set.stream().min(Comparator.comparingInt(Person::getAge)).orElse(null);
	    }

	    public static List<Person> sortByAge(Set<Person> set) {
	        List<Person> sortedList = new ArrayList<>(set);
	        sortedList.sort(Comparator.comparingInt(Person::getAge));
	        return sortedList;
	    }

	    public static Set<Person> filterByAge(Set<Person> set, int ageThreshold) {
	        Set<Person> filteredSet = new HashSet<>();
	        for (Person person : set) {
	            if (person.getAge() > ageThreshold) {
	                filteredSet.add(person);
	            }
	        }
	        return filteredSet;
	    }
	}

	class Conference {
	    private Set<Person> attendees = new HashSet<>();

	    public void addAttendee(Person person) {
	        attendees.add(person);
	    }

	    public void removeAttendee(Person person) {
	        attendees.remove(person);
	    }

	    public Set<Person> findDuplicateAttendees() {
	        Set<Person> duplicates = new HashSet<>();
	        Set<Integer> ids = new HashSet<>();
	        for (Person attendee : attendees) {
	            if (!ids.add(attendee.getId())) {
	                duplicates.add(attendee);
	            }
	        }
	        return duplicates;
	    }

	    public Set<Person> listUniqueAttendees() {
	        return new HashSet<>(attendees);
	    }

	    public Set<Person> findAttendeesInAgeRange(int minAge, int maxAge) {
	        Set<Person> inRange = new HashSet<>();
	        for (Person attendee : attendees) {
	            if (attendee.getAge() >= minAge && attendee.getAge() <= maxAge) {
	                inRange.add(attendee);
	            }
	        }
	        return inRange;
	    }
	}

	 class Main {
	    public static void main(String[] args) {
	        // Task 1: Create Person objects
	        Set<Person> hashSet = new HashSet<>();
	        Set<Person> linkedHashSet = new LinkedHashSet<>();

	        // Adding Persons
	        hashSet.add(new Person(1, "shubhu", 30));
	        hashSet.add(new Person(2, "sarvesh", 25));
	        hashSet.add(new Person(1, "mau", 30)); // Duplicate
	        linkedHashSet.add(new Person(3, "trisha", 35));
	        linkedHashSet.add(new Person(4, "mai", 28));
	        linkedHashSet.add(new Person(3, "shobha", 35)); // Duplicate

	        // Task 2: Set Operations
	        Set<Person> unionSet = PersonSetOperations.union(hashSet, linkedHashSet);
	        Set<Person> intersectionSet = PersonSetOperations.intersection(hashSet, linkedHashSet);
	        Set<Person> differenceSet1 = PersonSetOperations.difference(hashSet, linkedHashSet);
	        Set<Person> differenceSet2 = PersonSetOperations.difference(linkedHashSet, hashSet);

	        System.out.println("Union: " + unionSet);
	        System.out.println("Intersection: " + intersectionSet);
	        System.out.println("Difference (HashSet - LinkedHashSet): " + differenceSet1);
	        System.out.println("Difference (LinkedHashSet - HashSet): " + differenceSet2);

	        // Task 3: Complex Operations
	        System.out.println("Max Age in HashSet: " + PersonSetOperations.findMaxAge(hashSet));
	        System.out.println("Min Age in HashSet: " + PersonSetOperations.findMinAge(hashSet));
	        System.out.println("Sorted by Age (HashSet): " + PersonSetOperations.sortByAge(hashSet));
	        System.out.println("Filtered by Age > 28 (HashSet): " + PersonSetOperations.filterByAge(hashSet, 28));

	        // Task 5: Conference
	        Conference conference = new Conference();
	        conference.addAttendee(new Person(5, "Eve", 22));
	        conference.addAttendee(new Person(6, "Frank", 40));
	        conference.addAttendee(new Person(5, "Eve", 22)); // Duplicate
	        System.out.println("Unique Attendees: " + conference.listUniqueAttendees());
	        System.out.println("Duplicate Attendees: " + conference.findDuplicateAttendees());
	        System.out.println("Attendees aged 20-30: " + conference.findAttendeesInAgeRange(20, 30));
	    }
	}

	