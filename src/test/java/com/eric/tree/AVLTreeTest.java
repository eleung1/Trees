package com.eric.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the AVLTree.
 * 
 * @author Eric Leung
 *
 */
public class AVLTreeTest
{
	private AVLTree avl;
	
	private AVLTree avl2;
	
	@Before
	public void setup()
	{
		avl = new AVLTree();
		avl.insertAndBalance(avl.root, 5);
    avl.insertAndBalance(avl.root, 2);
    avl.insertAndBalance(avl.root, 7);
    avl.insertAndBalance(avl.root, 1);
    avl.insertAndBalance(avl.root, 3);
    avl.insertAndBalance(avl.root, 6);
    
    avl2 = new AVLTree();
	}
	
	@Test
	public void testGetBalanceFactor()
	{
		assertEquals(0, AVLTree.getBalanceFactor(avl.root));
		
		assertEquals(1, AVLTree.getBalanceFactor(avl.root.rightChild));
	}
	
	@Test
	public void testRightRotate_isRoot_leftChildBecomesRoot()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 3);
		avl2.insert(avl2.root, 1);
		
		avl2.rightRotate(avl2.root);
		
		assertEquals(3, avl2.root.value);
		assertNull(avl2.root.parent);
		assertEquals(1, avl2.root.leftChild.value);
		assertEquals(5, avl2.root.rightChild.value);
		assertEquals(avl2.root, avl2.root.leftChild.parent);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
		
	}
	
	@Test
	public void testRightRotate_isNotRoot_leftChildBecomesRotatedNode()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 4);
		avl2.insert(avl2.root, 3);
		avl2.insert(avl2.root, 2);
		avl2.insert(avl2.root, 6);
		
		assertEquals(5, avl2.root.value);
		assertEquals(4, avl2.root.leftChild.value);
		assertEquals(3, avl2.root.leftChild.leftChild.value);
		assertEquals(2, avl2.root.leftChild.leftChild.leftChild.value);
		assertEquals(6, avl2.root.rightChild.value);
		
		avl2.rightRotate(avl2.find(avl2.root, 4));
		
		assertEquals(5, avl2.root.value);
		assertEquals(3, avl2.root.leftChild.value);
		assertEquals(2, avl2.root.leftChild.leftChild.value);
		assertEquals(4, avl2.root.leftChild.rightChild.value);
		assertEquals(6, avl2.root.rightChild.value);
	}
	
	@Test
	public void testLeftRotate_isRoot_rightChildBecomesRoot()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 6);
		avl2.insert(avl2.root, 7);
		
		avl2.leftRotate(avl2.root);
		
		assertEquals(6, avl2.root.value);
		assertNull(avl2.root.parent);
		assertEquals(5, avl2.root.leftChild.value);
		assertEquals(7, avl2.root.rightChild.value);
		assertEquals(avl2.root, avl2.root.leftChild.parent);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
	}
	
	@Test
	public void testLeftRotate_isNotRoot_rightChildBecomesRotatedNode()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 6);
		avl2.insert(avl2.root, 7);
		avl2.insert(avl2.root, 8);
		avl2.insert(avl2.root, 4);
		
		avl2.leftRotate(avl2.find(avl2.root, 6));
		
		assertEquals(5, avl2.root.value);
		assertEquals(7, avl2.root.rightChild.value);
		assertEquals(6, avl2.root.rightChild.leftChild.value);
		assertEquals(8, avl2.root.rightChild.rightChild.value);
		assertEquals(4, avl2.root.leftChild.value);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
	}
	
	@Test
	public void testLeftRightCase_isRoot()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 1);
		avl2.insert(avl2.root, 3);
		
		avl2.leftRotate(avl2.root.leftChild);
		avl2.rightRotate(avl2.root);
		
		assertEquals(3, avl2.root.value);
		assertEquals(1, avl2.root.leftChild.value);
		assertEquals(5, avl2.root.rightChild.value);
	}
	
	@Test
	public void testLeftRightCase_isNotRoot()
	{
		avl2.insert(avl2.root, 5);
		avl2.insert(avl2.root, 4);
		avl2.insert(avl2.root, 2);
		avl2.insert(avl2.root, 3);
		avl2.insert(avl2.root, 6);
		
		avl2.leftRotate(avl2.find(avl2.root, 2));
		avl2.rightRotate(avl2.find(avl2.root, 4));
		
		assertEquals(3, avl2.root.leftChild.value);
		assertEquals(2, avl2.root.leftChild.leftChild.value);
		assertEquals(4, avl2.root.leftChild.rightChild.value);
	}
	
	
	@Test
	public void testInsertWithBalance_LeftLeftCase()
	{
		avl2.insertAndBalance(avl2.root, 5);
    avl2.insertAndBalance(avl2.root, 3);
    avl2.insertAndBalance(avl2.root, 7);
    avl2.insertAndBalance(avl2.root, 2);
    avl2.insertAndBalance(avl2.root, 4);
    
    System.out.println("PreOrder:");
    avl2.preOrderTraversal(avl2.root);
    
    System.out.println("\nInserting 1:");
    avl2.insertAndBalance(avl2.root, 1);
    
    System.out.println("PreOrder:");
    avl2.preOrderTraversal(avl2.root);
	}
	
	@Test
	public void testInsertWithBalance_LeftRightCase()
	{
		avl2.insertAndBalance(avl2.root, 5);
    avl2.insertAndBalance(avl2.root, 1);
    avl2.insertAndBalance(avl2.root, 3);
    
    assertEquals(3, avl2.root.value);
		assertNull(avl2.root.parent);
		assertEquals(1, avl2.root.leftChild.value);
		assertEquals(5, avl2.root.rightChild.value);
		assertEquals(avl2.root, avl2.root.leftChild.parent);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
    
	}
	
	@Test
	public void testInsertWithBalance_RightRightCase()
	{
		avl2.insertAndBalance(avl2.root, 5);
		avl2.insertAndBalance(avl2.root, 6);
		avl2.insertAndBalance(avl2.root, 7);
		
		assertEquals(6, avl2.root.value);
		assertNull(avl2.root.parent);
		assertEquals(5, avl2.root.leftChild.value);
		assertEquals(7, avl2.root.rightChild.value);
		assertEquals(avl2.root, avl2.root.leftChild.parent);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
	}
	
	@Test
	public void testInsertWithBalance_RightLeftCase()
	{
		avl2.insertAndBalance(avl2.root, 5);
		avl2.insertAndBalance(avl2.root, 7);
		avl2.insertAndBalance(avl2.root, 6);
		
		assertEquals(6, avl2.root.value);
		assertNull(avl2.root.parent);
		assertEquals(5, avl2.root.leftChild.value);
		assertEquals(7, avl2.root.rightChild.value);
		assertEquals(avl2.root, avl2.root.leftChild.parent);
		assertEquals(avl2.root, avl2.root.rightChild.parent);
	}
	
}
