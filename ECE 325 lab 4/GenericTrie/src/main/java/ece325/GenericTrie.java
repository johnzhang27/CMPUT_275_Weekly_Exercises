package ece325;

/**
 * Lab 4: Generics <br />
 * The {@code GenericTrie} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Trie">
 *              https://en.wikipedia.org/wiki/Trie
 *            </a>
 * @author Yongquan Zhang 1515873
 */
public class GenericTrie<K extends CharSequence, V> 
{
    /**
     * Root node of the Prefix Tree
	 * We have to assign it to a new TrieNode, otherwise it will be null
     */	 
	TrieNode<V> root = new TrieNode();

    /**
     * Insert a key and associated value to the trie
     * @param word      {@code K} String
	 * @param value      {@code V} Number
     */
    public void insert(K word, V value) {
    	// TODO: Insert a new element to the Prefix Tree
        int index = 0;
		TrieNode<V> node = root;
		for(int i = 0; i < word.length(); i++){
			index = word.charAt(i) - 'a';
			if(node.child[index] == null){
				node.child[index] = new TrieNode(value);
			}
			node = node.child[index];
		}
		node = new TrieNode(value);
    }
    
    /**
     * Search a word to see if it is in the trie
     * @param word      {@code K} String
	 * @return 			{@code V} value
     */
    public V search(K word) {
		// TODO: Returns the value associated with the word is in the Prefix Tree
        int index = 0;
		TrieNode<V> node = root;
		for(int i = 0;i < word.length(); i++){
			index = word.charAt(i)-'a';
			if(node.child[index] != null){
				node = node.child[index];
			}
			else{
				return null;
			}
		}
		if(node.isEndOfWord == false){
			return null;
		}
		return node.value;
    }
    
    /**
     * Check prefix
     * @param prefix      {@code K} String
	 * @return 			  {@code boolean} true or false
     */
    public boolean startWith(K prefix) {
        // TODO: Returns if there is any word in the Prefix Tree that starts with the given prefix.
        int index = 0;
		TrieNode<V> node = root;
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
    /**
     * Remove a key and associated value to the trie
     * @param word      {@code K} String
	 * @return 			{@code V} value removed
     */
    public V remove(K word) {
    	// TODO: Removes an element from the Prefix Tree, returning its associated value
		int index = 0;
		V temp;
        TrieNode<V> node = root;
		for(int i = 0;i < word.length(); i++){
			index = word.charAt(i)-'a';
			if(node.child[index] != null){
				node = node.child[index];
			}
			else{
				return null;
			}
		}
		if(node.isEndOfWord == false){
			return null;
		}
		temp = node.value;
		node.value = null;
		node = null;
    	return temp;
    }

}


