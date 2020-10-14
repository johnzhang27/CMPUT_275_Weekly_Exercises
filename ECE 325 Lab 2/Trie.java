
/**
 * Lab 2: Debugging with an IDE and Prefix Tree)
 * The {@code Trie} class uses multiple TrieNode structures to represent a prefix tree data structure
 * @author YongQuan Zhang 1515873
 */

class Trie {
	
	static final int ALPHABET_SIZE = 26;
	
	static class TrieNode {
  
	        TrieNode[] child = new TrieNode[ALPHABET_SIZE];
	        boolean isEndOfWord;
	        
	        TrieNode(){ 
				isEndOfWord = false; 
				for (int i = 0; i < ALPHABET_SIZE; i++) 
					child[i] = null; 
			}
	        
	    }
	
    /**
     * Root node of the Prefix Tree
     */	 
	static TrieNode root; 
    /**
     * Insert a string parameter to the prefix tree data structure
     * @param word   {@code String} The string to be inserted
     */
    public static void insert(String word) {
		// TODO: Insert a new element to the Prefix Tree
		// This is the index number that would be used to represent a-z ASCII value.
		// Start from 0 to 25.
		int index = 0;
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++){
			index = word.charAt(i) - 'a';
			if(node.child[index] == null){
				node.child[index] = new TrieNode();
			}
			node = node.child[index];
		}
		node.isEndOfWord = true;
    }
    
    /**
     * Search a string parameter to see if it existed in the prefix tree data structure
	 * isEndOfWord = true is required
     * @param word   {@code String} The string to be searched
     */
    public static boolean search(String word) {
		// TODO: Returns if the word is in the Prefix Tree
		int index = 0;
		TrieNode node = root;
		for(int i = 0;i < word.length(); i++){
			index = word.charAt(i)-'a';
			if(node.child[index] != null){
				node = node.child[index];
			}
			else{
				return false;
			}
		}
		if(node.isEndOfWord == false){
			return false;
		}
    	return true;
    }
    /**
     * Search a string parameter to see if it is a prefix in the prefix tree data structure
	 * isEndOfWord = true is not required
     * @param prefix   {@code String} The string to be searched
     */
    public static boolean startWith(String prefix) {
		// TODO: Returns if there is any word in the Prefix Tree that starts with the given prefix.
		int index = 0;
		TrieNode node = root;
		for(int i = 0;i < prefix.length(); i++){
			index = prefix.charAt(i)-'a';
			if(node.child[index] != null){
				node = node.child[index];
			}
			else{
				return false;
			}
		}
    	return true;
    }

   
    public static void main(String args[]) 
	{ 
		
		String words[] = {"ece", "lab", "java", "jar", "car", 
						"cat", "care", "laboratory", "ebook"}; 
	
		String output[] = {"is NOT in the prefix tree", "is in the prefix tree"}; 
	
	
		root = new TrieNode(); 
	
		// Construct trie 
		int i; 
		for (i = 0; i < words.length ; i++) 
			insert(words[i]); 
	
		// Search for different keys 
		if(search("lab") == true) 
			System.out.println("lab --- " + output[1]); 
		else System.out.println("lab --- " + output[0]); 
		
		if(search("java") == true) 
			System.out.println("java --- " + output[1]); 
		else System.out.println("java --- " + output[0]); 
		
		if(startWith("eced") == true) 
			System.out.println("eced --- " + output[1]); 
		else System.out.println("eced --- " + output[0]); 
		
		if(startWith("ca") == true) 
			System.out.println("ca --- " + output[1]); 
		else System.out.println("ca --- " + output[0]); 
		
		if(search("book") == true) 
			System.out.println("book --- " + output[1]); 
		else System.out.println("book --- " + output[0]); 
		// I added one more test case.
		if(search("ca") == true) 
			System.out.println("ca --- " + output[1]); 
		else System.out.println("ca --- " + output[0]); 
	} 


}


