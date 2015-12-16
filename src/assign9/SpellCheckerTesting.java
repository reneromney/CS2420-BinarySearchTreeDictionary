package assign9;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
/**
 * Tests for SpellChecker
 * @author Hanna Larsen & Romney Doria
 *uID: 		u0741837		u0592859
 *CADE:		hannal			doria
 *Date Modified: 11/04/15
 */
public class SpellCheckerTesting {
	//Dictionary file to compare with
	File f = new File("dictionary.txt");
	SpellChecker dictionary = new SpellChecker(f);
	// misspelled word list
	List<String> misspelled;
	
	/**
	 * Tests for when there are some misspelled words
	 */
	@Test
	public void testMisspelledWords() {
		File test1 = new File("test1.txt");
		misspelled = dictionary.spellCheck(test1);
		assertTrue(misspelled.contains("theef"));
		assertTrue(misspelled.contains("macrosimmulation"));
		assertEquals(2, misspelled.size());
	}

	/**
	 * Tests for when there are no misspelled words
	 */
	@Test
	public void testNoMisspelledWords(){
		File helloworld = new File("hello_world.txt");
		misspelled = dictionary.spellCheck(helloworld);
		assertEquals(0, misspelled.size());
		assertTrue(misspelled.isEmpty());
	}
	
	/**
	 * Tests for when the entire document is misspelled words
	 */
	@Test
	public void testAllMisspelled(){
		File test2 = new File("test2.txt");
		misspelled = dictionary.spellCheck(test2);
		assertEquals(3, misspelled.size());
		assertTrue(misspelled.contains("ahffs"));
		assertTrue(misspelled.contains("adjfaklj"));
		assertTrue(misspelled.contains("fjdsklfja"));
	}
	
	/**
	 * Tests for when the document is empty
	 */
	@Test
	public void testEmptyDoc(){
		File test3 = new File("test3.txt");
		misspelled = dictionary.spellCheck(test3);
		assertEquals(0, misspelled.size());
		assertTrue(misspelled.isEmpty());
	}
	
	
	/**
	 * Tests adding a new word
	 */
	@Test
	public void testAddWord(){
		File f2 = new File("reallySmallDictionary.txt");
		SpellChecker dictionary2 = new SpellChecker(f2);
		dictionary2.addToDictionary("Hanna");
		File test4 = new File("test4.txt");
		misspelled = dictionary2.spellCheck(test4);
		assertFalse(misspelled.contains("hanna"));
		assertEquals(0, misspelled.size());
	}
	
	/**
	 * Tests adding a word that is already in the dictionary
	 * (must make dictionary member variable in SpellChecker public for this to work)
	 */
//	@Test
//	public void testAddDuplicateWord(){
//		dictionary.addToDictionary("dagger");
//		assertTrue(dictionary.dictionary.contains("dagger"));
//	}
	
	/**
	 * Tests removing a word in the dictionary
	 */
	@Test
	public void testRemoveWord(){
		dictionary.removeFromDictionary("luck");
		File test5 = new File("test5.txt");
		misspelled = dictionary.spellCheck(test5);
		assertTrue(misspelled.contains("luck"));
	}
	
	/**
	 * Tests for when there are duplicate misspelled words in
	 * the file to check
	 */
	@Test
	public void testMisspelledDuplicates(){
		File test6 = new File("test6.txt");
		misspelled = dictionary.spellCheck(test6);
		assertEquals("grapee", misspelled.get(0));
		assertEquals("grapee", misspelled.get(1));
		assertEquals(2, misspelled.size());
	}
	
	/**
	 * Tests when the file to check does not exist
	 */
	@Test//(expected = FileNotFoundException.class)
	public void testNoFile(){
		File testNoFile = new File("testNoFile.txt");
		dictionary.spellCheck(testNoFile);
	}
}
