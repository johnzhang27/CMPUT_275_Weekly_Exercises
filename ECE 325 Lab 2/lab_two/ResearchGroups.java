/**
 * Lab 2: Debugging with an IDE and Prefix Tree) <br />
 * The {@code ResearchGroup} class uses a 2D array to store the names of group members
 * @author YongQuan Zhang 1515873
 */

package lab_two;
import java.util.*;
public class ResearchGroups {
    /**
     * Search a person to check whether he/she is in the groups
     * @param groups    {@code String[]} The 2D array of groups to be searched
     * @param name      {@code String} name of the person
     */
    public static void searchMember(String[][] groups, String name) {
        // TODO: Lab 2 Part 1-1 -- search and print the results here
        String groupList = "";
        boolean inList = false;
        boolean groupLeader = false;
        int index = 0;
        for (String[] group : groups) {
            for (int i = 0; i < group.length; i++) {
                if (group[i].equals(name)) {
                    inList = true;
                    if (i == 0) {
                        groupLeader = true;
                    }
                    groupList += index;
                    groupList += " ";
                }
            }
            index++;
        }
        if (inList) {
            System.out.println(name + " is in list.");
            System.out.println("Geoup numbers: " + groupList);
            if (groupLeader) {
                System.out.println(name + " is group leader.");
            }
        } else {
            System.out.println(name + " is not in list.");
        }	

    }

    /**
     * Sort groups by number of members <br />
     * @param groups    (<code>String[][]</code>) The 2D array of groups to be sorted
     */
    public static void sortGroups(String[][] groups) {
        // TODO: Lab 2 Part 1-2 -- sort and print the results here. Reuse your heapsort
        HashMap<Integer, String[]> groupsMap = new HashMap<>();
        int[] groupNumbers = new int[groups.length];
        for(int i = 0; i < groups.length; i++){
            // When sorting the 2d array, if group 2 and group 3 have same length then group 2 should
            // be in front of group 3.
            // Mutiply by 10 to make sure the hash number is unique.
            int hashNumber = (groups[i].length)*10+i;
            groupNumbers[i] = hashNumber;
            groupsMap.put(hashNumber, groups[i]);
        }
        groupNumbers = MergeSort.sort(groupNumbers);
        for(int j = 0;j < groups.length; j++){
            groups[j] = groupsMap.get(groupNumbers[j]);
            System.out.println(Arrays.toString(groups[j]));
            // This is another way to print out the sorted group list.
            // System.out.println(Arrays.toString(groupsMap.get(groupNumbers[j])));
        }
    }
    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[][] groups = { {"Bob", "Carol", "Eric", "Matt"},             // 0
                              {"Jim", "Lucy", "Terry", "Brenda", "Ben"},    // 1
                              {"Susan", "Brad", "Jim"},                     // 2
                              {"Sue", "Wendy", "Sam"},                      // 3
                              {"Kate", "Jack", "James", "Sydney"},          // 4
                              {"Mohammad", "Tim", "Kian"},                  // 5
                              {"Emma", "Carol"},                            // 6
                              {"Nick", "Osama", "Harry", "Ben"},            // 7
                              {"Mary", "John", "Ricky"} };                  // 8

        ResearchGroups.searchMember(groups, "Jim");
        ResearchGroups.searchMember(groups, "Lucy");
        ResearchGroups.searchMember(groups, "John Doe");
        ResearchGroups.sortGroups(groups);
    }
}