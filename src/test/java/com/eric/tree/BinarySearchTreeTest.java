package com.eric.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest
{
  private BinarySearchTree bst;
  private BinarySearchTree prepopulatedTree;

  @Before
  public void setup()
  {
    bst = new BinarySearchTree();
    prepopulatedTree = new BinarySearchTree();
    
    prepopulatedTree.insert(prepopulatedTree.root, 5);
    prepopulatedTree.insert(prepopulatedTree.root, 2);
    prepopulatedTree.insert(prepopulatedTree.root, 7);
    prepopulatedTree.insert(prepopulatedTree.root, 1);
    prepopulatedTree.insert(prepopulatedTree.root, 3);
    prepopulatedTree.insert(prepopulatedTree.root, 6);
  }
  
  @Test
  public void testInsert_emptyTree()
  {
    bst.insert(bst.root, 5);
    
    assertEquals(5, bst.root.value);
  }
  
  @Test
  public void testInsert()
  {
    bst.insert(bst.root, 5);
    bst.insert(bst.root, 3);
    bst.insert(bst.root, 7);
    
    assertEquals(5, bst.root.value);
    assertEquals(3, bst.root.leftChild.value);
    assertEquals(7, bst.root.rightChild.value);
    
    
    assertEquals(bst.root, bst.root.leftChild.parent);
    assertEquals(bst.root, bst.root.rightChild.parent);
  }
  
  @Test
  public void testFind_emptyTree()
  {
  	TreeNode result = bst.find(bst.root, 5);
	  assertNull(result);
  }
  
  @Test
  public void testFind()
  {
  	bst.insert(bst.root, 5);
    bst.insert(bst.root, 3);
    bst.insert(bst.root, 7);
    
    TreeNode node5 = bst.find(bst.root, 5);
    TreeNode node3 = bst.find(bst.root, 3);
    TreeNode node7 = bst.find(bst.root, 7);
    
    assertEquals(5, node5.value);
    assertEquals(3, node3.value);
    assertEquals(7, node7.value);
  }
  
  @Test
  public void testDelete()
  {
  	bst.insert(bst.root, 5);
    bst.insert(bst.root, 2);
    bst.insert(bst.root, 7);
    bst.insert(bst.root, 1);
    bst.insert(bst.root, 3);
    bst.insert(bst.root, 6);
    
    System.out.println("Before deletion:");
    bst.preOrderTraversal(bst.root);
    
    bst.delete(bst.root, 5);
    
    System.out.println("After deletion:");
    bst.preOrderTraversal(bst.root);
    
  }
  
  @Test
  public void testTraversal()
  {
  	bst.insert(bst.root, 5);
    bst.insert(bst.root, 2);
    bst.insert(bst.root, 7);
    bst.insert(bst.root, 1);
    bst.insert(bst.root, 3);
    bst.insert(bst.root, 6);
    
    System.out.println("Pre Order: ");
    bst.preOrderTraversal(bst.root);
    System.out.println("\nIn Order: ");
    bst.inOrderTraversal(bst.root);
    System.out.println("\nPost Order: ");
    bst.postOrderTraversal(bst.root);
  }
  
  @Test
  public void testHeight()
  {
  	int height = BinarySearchTree.height(prepopulatedTree.root);
  	assertEquals(3, height);
  	
  	bst.insert(bst.root, 5);
  	assertEquals(1, BinarySearchTree.height(bst.root));
  	
  	bst.insert(bst.root, 2);
  	assertEquals(2, BinarySearchTree.height(bst.root));
  	
  	bst.insert(bst.root, 8);
  	assertEquals(2, BinarySearchTree.height(bst.root));
  	
  	bst.insert(bst.root, 9);
  	assertEquals(3, BinarySearchTree.height(bst.root));
  }
}
