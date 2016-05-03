package com.eric.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest
{
	private AVLTree avl;
	
	private AVLTree avl2;
	
	private AVLTree avl3;
	
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
    avl2.insertAndBalance(avl2.root, 5);
    avl2.insertAndBalance(avl2.root, 4);
    avl2.insertAndBalance(avl2.root, 3);
    
    avl3 = new AVLTree();
    
	}
	
	@Test
	public void testGetBalanceFactor()
	{
		assertEquals(0, AVLTree.getBalanceFactor(avl.root));
		
		assertEquals(1, AVLTree.getBalanceFactor(avl.root.rightChild));
		
		assertEquals(0, AVLTree.getBalanceFactor(avl2.root));
	}
	
	@Test
	public void testInsertWithBalance()
	{
		avl3.insertAndBalance(avl3.root, 5);
    avl3.insertAndBalance(avl3.root, 3);
    avl3.insertAndBalance(avl3.root, 7);
    avl3.insertAndBalance(avl3.root, 2);
    avl3.insertAndBalance(avl3.root, 4);
    
    System.out.println("PreOrder:");
    avl3.preOrderTraversal(avl3.root);
    
    System.out.println("\nInserting 1:");
    avl3.insertAndBalance(avl3.root, 1);
    
    System.out.println("PreOrder:");
    avl3.preOrderTraversal(avl3.root);
	}
}
