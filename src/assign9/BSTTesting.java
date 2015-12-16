package assign9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests methods in BinarySearchTree
 * @author Hanna Larsen & Romney Doria
 *uID: 		u0741837		u0592859
 *CADE:		hannal			doria
 *Date Modified: 11/04/15
 */
public class BSTTesting {
	
	// tester BST
	BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();

	/**
	 * Tests for trying to add an item that is null
	 */
	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		Integer i = null;
		test.add(null);
		test.add(i);
	}
	
	/**
	 * Tests to make sure add is correct
	 */
	@Test
	public void testAddDefault()
	{
		assertTrue(test.isEmpty());
		assertTrue(test.add(50));
		assertEquals(1, test.size());
		assertTrue(test.add(2));
	}
	
	/**
	 * Tests to make sure duplicates can't be added
	 */
	@Test
	public void testAddNoDuplicates(){
		test.add(7);
		test.add(8);
		test.add(6);
		assertFalse(test.add(7));
		assertEquals(3, test.size());
	}
	
	/**
	 * Tests add all when entire collection to add is null
	 */
	@Test(expected = NullPointerException.class)
	public void testAddAllNullCollection(){
		ArrayList<Integer> testList = null;
		test.addAll(testList);
	}
	
	/**
	 * Tests add all when an item in the collection is null
	 */
	@Test(expected = NullPointerException.class)
	public void testAddAllNullItem(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(6, null, 100));
		test.addAll(testList);
	}
	
	/**
	 * Tests to makes sure a collection can be added correctly
	 */
	@Test
	public void testAddAllDefault(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100));
		assertTrue(test.addAll(testList));
		assertEquals(3, test.size());
		assertTrue(test.contains(1));		
		assertTrue(test.contains(30));
		assertTrue(test.contains(100));
	}
	
	/**
	 * Tests addAll when there are duplicates
	 */
	@Test
	public void testAddAllDuplicates(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100, 30));
		assertTrue(test.addAll(testList));
		assertEquals(3, test.size());
		assertTrue(test.contains(1));		
		assertTrue(test.contains(30));
		assertTrue(test.contains(100));
	}
	
	
	/**
	 * Tests to make sure clear functions correctly
	 */
	@Test
	public void testClear(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100, 98, 4, 25));
		test.addAll(testList);
		assertEquals(6, test.size());
		test.clear();
		assertEquals(0, test.size());
	}
	
	/**
	 * Tests contains for when the item in question is null
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNull(){
		test.add(5);
		test.add(9);
		test.contains(null);	
	}
	
	/**
	 * Tests to make sure contains functions correctly
	 */
	@Test
	public void testContainsDefault(){
		test.add(76);
		test.add(34);
		test.add(87);
		assertTrue(test.contains(34));
		assertTrue(test.contains(76));
		assertTrue(test.contains(87));
	}
	
	/**
	 * Tests containsAll when the entire collection it is comparing to is null
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllNullCollection(){
		ArrayList<Integer> testList = null;
		test.containsAll(testList);
	}
	
	/**
	 * Tests containsAll when an item in the collection it is comparing to is null
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllNullItem(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(6, null, 100));
		test.add(6);
		test.add(100);
		test.containsAll(testList);
	}
	/**
	 * Tests to make sure containsAll functions correctly
	 */
	@Test
	public void testContainsAllDefault(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100, 67));
		assertTrue(test.addAll(testList));
		assertTrue(test.containsAll(testList));
	}
	
	/**
	 * Tests containsAll for when it doesn't contain everything
	 */
	@Test
	public void testContainsAllFalse(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100, 7));
		test.add(1);
		test.add(30);
		test.add(100);
		assertEquals(3, test.size());
		assertFalse(test.containsAll(testList));
	}
	
	/**
	 * Tests containsAll for when there are duplicates in the collection it is comparing to
	 */
	@Test
	public void testContainsAllDuplicates(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(1, 30, 100, 7, 30));
		test.addAll(testList);
		assertEquals(4, test.size());
		assertTrue(test.containsAll(testList));
	}
	
	/**
	 * Tests isEmpty
	 */
	@Test
	public void testIsEmpty(){
		assertTrue(test.isEmpty());
		test.add(7);
		assertFalse(test.isEmpty());
	}
	
	/**
	 * Tests first (i.e. getting the smallest node)
	 */
	@Test
	public void testFirst(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(23);
		assertEquals((Integer)2, test.first());
	}
	
	/**
	 * Tests first when the BST is empty
	 */
	@Test(expected = NoSuchElementException.class)
	public void testFirstNoElement(){
		test.first();
	}
	
	/**
	 * Tests last(i.e. getting the largest node)
	 */
	@Test
	public void testLast(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(23);
		assertEquals((Integer)27, test.last());
	}
	
	/**
	 * Tests last when the BST is empty
	 */
	@Test(expected = NoSuchElementException.class)
	public void testLastNoElement(){
		test.last();
	}
	
	/**
	 * Tests remove when item to remove is null
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNull(){
		test.remove(null);
	}
	
	/**
	 * Tests remove when item to remove is not in BST
	 */
	@Test
	public void testRemoveNoItem(){
		test.add(10);
		test.add(87);
		test.add(43);
		test.add(23);
		assertEquals(4, test.size());
		assertFalse(test.remove(6));
	}
	
	/**
	 * Tests remove when node is a leaf
	 */
	@Test
	public void testRemoveLeaf()
	{
		test.add(10);
		test.add(2);
		test.add(12);
		assertTrue(test.remove(2));
		assertTrue(test.remove(12));
		assertEquals(1, test.size());
	}

	/**
	 * Tests remove when node has a left child
	 */
	@Test
	public void testRemoveOneLeftChild(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(23);
		assertTrue(test.remove(5));
		assertEquals(8, test.size());
	}
	
	/**
	 * Tests remove when node has a right child
	 */
	@Test
	public void testRemoveOneRightChild(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(29);
		assertTrue(test.remove(27));
		assertEquals(8, test.size());
	}
	
	/**
	 * Tests remove when node has 2 children
	 * & successor has no child
	 */
	@Test
	public void testRemoveTwoChildren1(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(23);
		assertTrue(test.remove(9));
		assertEquals(8, test.size());
	}
	
	/**
	 * Tests remove when node has 2 children
	 * & successor has a right child
	 */
	@Test
	public void testRemoveTwoChildren2(){
		test.add(20);
		test.add(9);
		test.add(5);
		test.add(16);
		test.add(2);
		test.add(27);
		test.add(11);
		test.add(19);
		test.add(23);
		test.add(12);
		assertTrue(test.remove(9));
		assertEquals(9, test.size());
	}
	
	/**
	 * Tests removeAll to make sure it functions correctly
	 */
	@Test
	public void testRemoveAllDefault(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(65, 30, 100, 67));
		test.addAll(testList);
		assertTrue(test.containsAll(testList));
		assertTrue(test.removeAll(testList));
	}
	
	/**
	 * Tests removeAll when the collection is null
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullCollection(){
		ArrayList<Integer> testList = null;
		test.removeAll(testList);
	}
	
	/**
	 * Tests removeAll when an item in the collection is null
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullItem(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(65, 30, null, 67));
		test.removeAll(testList);
	}
	
	/**
	 * Tests toArrayList to make sure that an inorder sort is done
	 */
	@Test
	public void testToArrayListDefault(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(65, 30, 100, 67));
		test.addAll(testList);
		ArrayList<Integer> result = test.toArrayList();
		assertEquals((Integer)30, result.get(0));
		assertEquals((Integer)65, result.get(1));
		assertEquals((Integer)67, result.get(2));
		assertEquals((Integer)100, result.get(3));
	}
	
	/**
	 * Tests toArrayList when there is only one item in the BST
	 */
	@Test
	public void testToArrayListOne(){
		ArrayList<Integer> testList = new ArrayList<Integer>(Arrays.asList(65));
		test.addAll(testList);
		ArrayList<Integer> result = test.toArrayList();
		assertEquals((Integer)65, result.get(0));
	}
	
	/**
	 * Tests toArrayList when BST has nothing in it
	 */
	@Test
	public void testToArrayListEmptyBST(){
		ArrayList<Integer> result = test.toArrayList();
		assertEquals(0, result.size());
	}
}
