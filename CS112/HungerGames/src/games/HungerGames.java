package games;

import java.util.ArrayList;

/**
 * This class contains methods to represent the Hunger Games using BSTs.
 * Moves people from input files to districts, eliminates people from the game,
 * and determines a possible winner.
 * 
 * @author Pranay Roni
 * @author Maksims Kurjanovics Kravcenko
 * @author Kal Pandit
 */
public class HungerGames {

    private ArrayList<District> districts;  // all districts in Panem.
    private TreeNode            game;       // root of the BST. The BST contains districts that are still in the game.

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * Default constructor, initializes a list of districts.
     */
    public HungerGames() {
        districts = new ArrayList<>();
        game = null;
        StdRandom.setSeed(2023);
    }

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * Sets up Panem, the universe in which the Hunger Games takes place.
     * Reads districts and people from the input file.
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupPanem(String filename) { 
        StdIn.setFile(filename);  // open the file - happens only once here
        setupDistricts(filename); 
        setupPeople(filename);
    }

    /**
     * Reads the following from input file:
     * - Number of districts
     * - District ID's (insert in order of insertion)
     * Insert districts into the districts ArrayList in order of appearance.
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupDistricts (String filename) {
        // WRITE YOUR CODE HERE
        int numDistricts = StdIn.readInt();
        for (int i = 0; i < numDistricts; i++) {
            districts.add(new District(StdIn.readInt()));
        }
    }

    /**
     * Reads the following from input file (continues to read from the SAME input file as setupDistricts()):
     * Number of people
     * Space-separated: first name, last name, birth month (1-12), age, district id, effectiveness
     * Districts will be initialized to the instance variable districts
     * 
     * Persons will be added to corresponding district in districts defined by districtID
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupPeople (String filename) {
        // WRITE YOUR CODE HERE
        int numPeople = StdIn.readInt();
        for (int i = 0; i < numPeople; i++) {
            String firstName = StdIn.readString();
            String lastName = StdIn.readString();
            int birthMonth = StdIn.readInt();
            int age = StdIn.readInt();
            int districtID = StdIn.readInt();
            int effectiveness = StdIn.readInt();
            Person p = new Person(birthMonth, firstName, lastName, age, districtID, effectiveness);
            p.setTessera(p.getAge() >= 12 && p.getAge() < 18);
            int index = 0;
            while (index < districts.size() && districts.get(index).getDistrictID() != p.getDistrictID()) {
                index++;
            }
            if (index < districts.size()) {
                addPerson(index, p);
            }
        }
    }
    private void addPerson(int index, Person p)
    {
        if (p.getBirthMonth() % 2 == 0) 
        {
            districts.get(index).addEvenPerson(p);
        } else {
            districts.get(index).addOddPerson(p);
        }
    }

    /**
     * Adds a district to the game BST.
     * If the district is already added, do nothing
     * 
     * @param root        the TreeNode root which we access all the added districts
     * @param newDistrict the district we wish to add
     */
    public void addDistrictToGame(TreeNode root, District newDistrict) 
    {

        // WRITE YOUR CODE HERE
        if (game == null) // if the game is empty
        {
            game = new TreeNode(newDistrict, null, null);
            districts.remove(newDistrict); // remove the district from the districts list
            //System.out.println(districts.size());
            return;
        }
        else if (root.getDistrict().getDistrictID() == newDistrict.getDistrictID()) // if the district is already added
        {
            //System.out.println(districts.size());
            return;
        } 
        else if (root.getDistrict().getDistrictID() > newDistrict.getDistrictID()) // if the district is less than the root
        {
            if (root.getLeft() == null) // if empty leaf found
            {
                root.setLeft(new TreeNode(newDistrict, null, null)); // add the district to the left
                districts.remove(newDistrict); // remove the district from the districts list
                //System.out.println(districts.size());
                return;
            }
            addDistrictToGame(root.getLeft(), newDistrict); // search for leaf on left
        } 
        else if (root.getDistrict().getDistrictID() < newDistrict.getDistrictID()) // if the district is greater than the root
        {
            if (root.getRight() == null) // if empty leaf found
            {
                root.setRight(new TreeNode(newDistrict, null, null)); // add the district to the right
                districts.remove(newDistrict); // remove the district from the districts list
                //System.out.println(districts.size());
                return;
            }
            addDistrictToGame(root.getRight(), newDistrict); // search for leaf on right
        }
    }

    /**
     * Searches for a district inside of the BST given the district id.
     * 
     * @param id the district to search
     * @return the district if found, null if not found
     */
    public District findDistrict(int id) {
        // WRITE YOUR CODE HERE
        TreeNode d = finder(game, id);
        //System.out.println("find district " + d.getDistrictID());
        if (d == null) // if the district is not found
        {
            return null;
        }
        return d.getDistrict();
    }
    private TreeNode finder(TreeNode root, int id)
    {
        //System.out.println("finder " + root.getDistrict().getDistrictID());
        if (root == null || root.getDistrict().getDistrictID() == id) // if the district is found
        {
            //System.out.println("found");
            if (root == null) // if the district is not found
            {
                return null;
            }
            return root; // return the district
        }
        else if (root.getDistrict().getDistrictID() > id) // if the district is less than the root
        {
            //System.out.println("left");
            if (root.getLeft() == null) // if empty leaf found
            {
                //System.out.println("not found");
                root = null; // if the district is not found
            }
            else
                root = root.getLeft(); // search for leaf on left
        } 
        else if (root.getDistrict().getDistrictID() < id) // if the district is greater than the root
        {
            //System.out.println("right");
            if (root.getRight() == null) // if empty leaf found
            {
                //System.out.println("not found");
                root = null; // if the district is not found
            }
            else
                root = root.getRight(); // search for leaf on right
        }
        return finder(root, id); // return the district if found
    }

    /**
     * Selects two duelers from the tree, following these rules:
     * - One odd person and one even person should be in the pair.
     * - Dueler with Tessera (age 12-18, use tessera instance variable) must be
     * retrieved first.
     * - Find the first odd person and even person (separately) with Tessera if they
     * exist.
     * - If you can't find a person, use StdRandom.uniform(x) where x is the respective 
     * population size to obtain a dueler.
     * - Add odd person dueler to person1 of new DuelerPair and even person dueler to
     * person2.
     * - People from the same district cannot fight against each other.
     * 
     * @return the pair of dueler retrieved from this method.
     */
    public DuelPair selectDuelers() {
        // WRITE YOUR CODE HERE
        DuelPair pair = new DuelPair();
        ArrayList<Person> odd = new ArrayList<Person>();
        ArrayList<Person> even = new ArrayList<Person>();
        oddtessduel(game, pair); // find the first odd tessera person
        eventessduel(game, pair); // find the first even tessera person
        if (pair.getPerson1() == null) // if the first person is not found
        {
            savePersonsOdd(game, odd);
            randomduel(odd, pair, true); // find random odd person
        }
        if (pair.getPerson2() == null) // if the second person is not found
        {
            savePersonsEven(game, even);
            randomduel(even, pair, false); // find random even person
        }
        return pair;
    }
    private void oddtessduel(TreeNode root, DuelPair pair)
    {
        if (root == null || pair.getPerson1() != null) // if the pair is full or the district is not found
        {
            return;
        }
        //System.out.println("oddtessduel");
        ArrayList<Person> odd = root.getDistrict().getOddPopulation();
        if (pair.getPerson1() == null) // if the first person is not found
        {
            for (int i = 0; i < odd.size(); i++) // search for the first person
            {
                //System.out.println(odd.get(i) + " odd " + odd.get(i).getTessera() + " dist " + odd.get(i).getDistrictID());
                if (odd.get(i).getTessera())
                {
                    pair.setPerson1(odd.get(i)); // set the first person (odd)
                    //System.out.println(pair.getPerson1());
                    odd.remove(i); // remove the person from the list
                    root.getDistrict().setOddPopulation(odd); // update the district
                    //System.out.println(root.getDistrict().getOddPopulation());
                    //System.out.println(odd);
                    break;
                }
            }
        }
        oddtessduel(root.getLeft(), pair); // search for leaf on left
        oddtessduel(root.getRight(), pair); // search for leaf on right
    }
    private void eventessduel(TreeNode root, DuelPair pair)
    {
        if (root == null || pair.getPerson2() != null) // if the pair is full or the district is not found
        {
            return;
        }
        //System.out.println("eventessduel");
        District d = root.getDistrict();
        ArrayList<Person> even = d.getEvenPopulation();
        if (pair.getPerson2() == null) // if the second person is not found
        {
            for (int i = 0; i < even.size(); i++) // search for the second person
            {
                //System.out.println(even.get(i) + " even " + even.get(i).getTessera() + " dist " + even.get(i).getDistrictID());
                if (even.get(i).getTessera() && (pair.getPerson1() == null || pair.getPerson1().getDistrictID() != even.get(i).getDistrictID()))
                {
                    pair.setPerson2(even.get(i)); // set the second person (even)
                    //System.out.println(pair.getPerson2());
                    even.remove(i); // remove the person from the list
                    root.getDistrict().setEvenPopulation(even); // update the district
                    //System.out.println(root.getDistrict().getEvenPopulation());
                    //System.out.println(even);
                    break;
                }
            }
        }
        eventessduel(root.getLeft(), pair); // search for leaf on left
        eventessduel(root.getRight(), pair); // search for leaf on right
    }
    private void savePersonsOdd(TreeNode root, ArrayList<Person> odd)
    {
        if (root == null) // if the district is not found
        {
            return;
        }
        District d = root.getDistrict(); // get the district
        ArrayList<Person> oddp = d.getOddPopulation(); // get the odd population
        for (Person p: oddp) // add all the odd people to the odd list
        {
            odd.add(p);
        }
        savePersonsOdd(root.getLeft(), odd); // search for leaf on left
        savePersonsOdd(root.getRight(), odd); // search for leaf on right
    }
    private void savePersonsEven(TreeNode root, ArrayList<Person> even)
    {
        if (root == null) // if the district is not found
        {
            return;
        }
        District d = root.getDistrict(); // get the district
        ArrayList<Person> evenp = d.getEvenPopulation(); // get the even population
        for (Person p: evenp) // add all the even people to the even list
        {
            even.add(p);
        }
        savePersonsEven(root.getLeft(), even); // search for leaf on left
        savePersonsEven(root.getRight(), even); // search for leaf on right
    }
    private void randomduel(ArrayList<Person> pop, DuelPair pair, boolean isOdd)
    {
        if (pop == null) // if the pair is full or the district is not found
        {
            return;
        }
        //System.out.println("randomduel");
        int r = StdRandom.uniform(pop.size()-1);
        if (isOdd && pair.getPerson1() == null)
        {
            pair.setPerson1(pop.get(r)); // set the first person (odd)
            //System.out.println(pair.getPerson1());
            removePerson(pop.get(r)); // remove the person from the district
        }
        if (!isOdd && pair.getPerson2() == null)
        {
            pair.setPerson2(pop.get(r)); // set the second person (even)
            //System.out.println(pair.getPerson2());
            removePerson(pop.get(r)); // remove the person from the district
        }
    }
    private void removePerson(Person p)
    {
        District d = findDistrict(p.getDistrictID()); // find the district
        if (p.getBirthMonth() % 2 == 0) 
        {
            d.getEvenPopulation().remove(p);
        } else {
            d.getOddPopulation().remove(p);
        }
    }


    /**
     * Deletes a district from the BST when they are eliminated from the game.
     * Districts are identified by id's.
     * If district does not exist, do nothing.
     * 
     * This is similar to the BST delete we have seen in class.
     * 
     * @param id the ID of the district to eliminate
     */
    public void eliminateDistrict(int id) {
        // WRITE YOUR CODE HERE
        District d = findDistrict(id);
        if (d == null) // if the district is not found
        {
            return;
        }
        if (d == game.getDistrict())
        {
            if (game.getLeft() != null && game.getRight() != null) // if the district has two children
            {
                TreeNode successor = findInOrderSuccessor(game.getRight(), game); // find and copy the successor of the district
                game.setDistrict(successor.getDistrict()); // replace the district with the successor
                TreeNode succParent = findParent(game, game, successor.getDistrict()); // find the parent of the successor
                setChild(succParent, successor, null); // remove the successor
            }
            else if (game.getLeft() == null) // the district has a right child
            {
                TreeNode child = game.getRight(); // copy the child
                game = child; // set the child in place of the district
            }
            else if (game.getRight() == null) // the district has a left child
            {
                TreeNode child = game.getLeft(); // copy the child
                game = child; // set the child in place of the district
            }
            else // if the district has no children
            {
                game = null;
            }
            return;
        }
        TreeNode dist = finder(game, id); // find the district
        if (dist.getLeft() != null && dist.getRight() != null) // if the district has two children
        {
            TreeNode successor = findInOrderSuccessor(dist.getRight(), dist); // find and copy the successor of the district
            dist.setDistrict(successor.getDistrict()); // replace the district with the successor
            TreeNode succParent = findParent(game, game, successor.getDistrict()); // find the parent of the successor
            setChild(succParent, successor, null); // remove the successor
        }
        else if (dist.getLeft() == null) // the district has a right child
        {
            TreeNode child = dist.getRight(); // copy the child
            TreeNode parent = findParent(game, game, d); // find the parent of the district
            setChild(parent, dist, child); // set the child in place of the district
        }
        else if (dist.getRight() == null) // the district has a left child
        {
            TreeNode child = dist.getLeft(); // copy the child
            TreeNode parent = findParent(game, game, d); // find the parent of the district
            setChild(parent, dist, child); // set the child in place of the district
        }
        else // if the district has no children
        {
            TreeNode parent = findParent(game, game, d); // find the parent of the district
            setChild(parent, dist, null);
        }

    }
    private TreeNode findParent(TreeNode root, TreeNode parent, District child)
    {
        //System.out.println("finder " + root.getDistrict().getDistrictID());
        if (root == null || root.getDistrict().getDistrictID() == child.getDistrictID()) // if the district is found
        {
            //System.out.println("found");
            if (root == null) // if the district is not found
            {
                return null;
            }
            return parent; // return the district
        }
        else if (root.getDistrict().getDistrictID() > child.getDistrictID()) // if the district is less than the root
        {
            //System.out.println("left");
            if (root.getLeft() == null) // if empty leaf found
            {
                //System.out.println("not found");
                root = null; // if the district is not found
                parent = null;
            }
            else
            {
                parent = root; // save previous root
                root = root.getLeft(); // search for leaf on left
            }
        } 
        else if (root.getDistrict().getDistrictID() < child.getDistrictID()) // if the district is greater than the root
        {
            //System.out.println("right");
            if (root.getRight() == null) // if empty leaf found
            {
                //System.out.println("not found");
                root = null; // if the district is not found
                parent = null;
            }
            else
            {
                parent = root; // save previous root
                root = root.getRight(); // search for leaf on right
            }
        }
        return findParent(root, parent, child); // return the district if found
    }
    private TreeNode findInOrderSuccessor(TreeNode root, TreeNode delete)
    {
        if (delete.getRight() != null) // if the district has a right child
            return findMin(delete.getRight()); // the in order successor is it's right child or the smallest left child of it's right child
        TreeNode parent = findParent(root, root, delete.getDistrict()); // find the parent of the district
        while (parent != null && delete == parent.getRight()) // while the district is the right child of the parent
        {
            parent = findParent(root, root, parent.getDistrict()); // find the parent of the parent
        }
        return parent; // return the parent
        
    }
    private TreeNode findMin(TreeNode root)
    {
        if (root.getLeft() == null)
            return root;
        return findMin(root.getLeft());
    }
    private void setChild(TreeNode parent, TreeNode dist, TreeNode child)
    {
        if (parent.getLeft() == dist) // if the district is the left child
        {
            parent.setLeft(child); // paste the child in place of the district or remove the district
        }
        else if (parent.getRight() == dist) // if the district is the right child
        {
            parent.setRight(child); // paste the child in place of the district or remove the district
        }
    }

    /**
     * Eliminates a dueler from a pair of duelers.
     * - Both duelers in the DuelPair argument given will duel
     * - Winner gets returned to their District
     * - Eliminate a District if it only contains a odd person population or even
     * person population
     * 
     * @param pair of persons to fight each other.
     */
    public void eliminateDueler(DuelPair pair) {
        // WRITE YOUR CODE HERE
        Person winner = null;
        Person loser = null;
        if (pair.getPerson1() == null || pair.getPerson2() == null) // if incomplete pair
        {
            if (pair.getPerson1() != null) // if the first person is not null
            {
                winner = pair.getPerson1(); // the winner is the first person
                loser = pair.getPerson2(); // the loser is the second person
            }
            if (pair.getPerson2() != null)
            {
                winner = pair.getPerson2(); // the winner is the second person
                loser = pair.getPerson1(); // the loser is the first person
            }
            if (winner != null)
            {
                addPersonBST(winner); // add the person to the district
            }
        }
        else
        {
            winner = pair.getPerson1().duel(pair.getPerson2()); // duel the two duelers
            addPersonBST(winner); // add the person to the district
            if (winner == pair.getPerson1()) // if the first person wins
            {
                loser = pair.getPerson2(); // the loser is the second person
                //System.out.println(winner + " winner1 " + winner.getDistrictID());
                //System.out.println(loser + " loser1 " + loser.getDistrictID());
            }
            else if (winner == pair.getPerson2()) // if the second person wins
            {
                loser = pair.getPerson1(); // the loser is the first person
                //System.out.println(winner + " winner2 " + winner.getDistrictID());
                //System.out.println(loser + " loser2 " + loser.getDistrictID());
            }
        }
        District l = findDistrict(loser.getDistrictID()); // find the district of loser
        if (l != null && (l.getOddPopulation().size() == 0 || l.getEvenPopulation().size() == 0)) // if the district has only odd or even people
        {
            //System.out.println("eliminate " + l.getDistrictID());
            eliminateDistrict(l.getDistrictID()); // eliminate the district
        }
        District w = findDistrict(winner.getDistrictID()); // find the district of winner
        if (w != null && (w.getOddPopulation().size() == 0 || w.getEvenPopulation().size() == 0)) // if the district has only odd or even people
        {
            //System.out.println("eliminate " + w.getDistrictID());
            eliminateDistrict(w.getDistrictID()); // eliminate the district
        }
    }
    private void addPersonBST(Person p)
    {
        District d = findDistrict(p.getDistrictID()); // find the district
        if (p.getBirthMonth() % 2 == 0) 
        {
            d.addEvenPerson(p);
        } 
        else 
        {
            d.addOddPerson(p);
        }
    }

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * 
     * Obtains the list of districts for the Driver.
     * 
     * @return the ArrayList of districts for selection
     */
    public ArrayList<District> getDistricts() {
        return this.districts;
    }

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * 
     * Returns the root of the BST
     */
    public TreeNode getRoot() {
        return game;
    }
}
