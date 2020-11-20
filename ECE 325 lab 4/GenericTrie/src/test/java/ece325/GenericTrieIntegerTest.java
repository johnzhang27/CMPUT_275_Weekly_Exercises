package ece325;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for GenericTrie
 * The {@code GenericTrieIntegerTest} class
 * @author Yongquan Zhang 1515873
 */
public class GenericTrieIntegerTest 
{

    static String[] words = {"ece", "lab", "java", "jar", "car", "cat", "care", "laboratory", "ebook"};
    static Integer[] values = {10, 20, 30, 35, 40, 42, 60, 102, 45}; 
    
    static GenericTrie<String, Integer> trie = new GenericTrie<String, Integer>();

    @BeforeClass
    public static void initTrie() 
    {
        for (int i = 0; i < words.length ; i++) {
            trie.insert(words[i], values[i]); 
        }
    }

    @Test
    public void searchLab()
    {   
        Integer res = trie.search("lab");
        assertNotNull("'lab' should be in trie", res);  
        assertEquals(20, res, 0);
    }

    @Test
    public void searchJava()
    {   
        Integer res = trie.search("java");
        assertNotNull("'java' should be in trie", res);  
        assertEquals(30, res, 0);
    }

    @Test
    public void startWidthFalse()
    {   
        assertFalse(trie.startWith("eced"));
    }

    @Test
    public void startWidthTrue()
    {   
        assertTrue(trie.startWith("ca"));
    }
				
    @Test
    public void searchBook()
    {   
        Integer res = trie.search("book");
        assertNull("'book' is no in trie", res);  
    }

    @Test
    public void removeInTrie()
    {   
        trie.remove("jar");
        Integer res = trie.search("jar");
        assertNull("'jar' should not be in trie", res);
    }
    @Test
    public void removeNotInTrie()
    {   
        trie.remove("capitan");
        Integer res = trie.search("capitan");
        assertNull("'capitan' is not in trie", res);
    }

}
