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
  
  /**
   * Case1: no children.
   * 
   * The node to be deleted is the root of the tree.
   * 
   */
  @Test
  public void testDelete_noChildrenIsRoot_emptyTree()
  {
  	bst.insert(bst.root, 5);
  	assertEquals(5, bst.root.value);
  	bst.delete(bst.root, 5);
  	assertNull(bst.root);
  }
  
  /**
   * Case1: no children. 
   * 
   * The node to be deleted is NOT the root of the tree.  Simply severe the link between its parent and the node.
   * 
   */
  @Test
  public void testDelete_noChildrenIsNotRoot_deleteTheNode()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 3);
  	
  	assertEquals(3, bst.root.leftChild.value);
  	
  	bst.delete(bst.root, 3);
  	assertNull(bst.root.leftChild);
  }
  
  /**
   * Case2: 1 children.
   * 
   * The node to be deleted is the root of the tree.
   * 
   */
  @Test
  public void testDelete_1LeftChildIsRoot_replaceRootWithChild()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 3);
  	
  	// delete the root
  	bst.delete(bst.root, 5);
  	
  	assertEquals(3, bst.root.value);
  	assertNull(bst.root.leftChild);
  	assertNull(bst.root.rightChild);
  }
  
  /**
   * Case2: 1 children.
   * 
   * The node to be deleted is the root of the tree.
   * 
   */
  @Test
  public void testDelete_1RightChildIsRoot_replaceRootWithChild()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 8);
  	
  	// delete the root
  	bst.delete(bst.root, 5);
  	
  	assertEquals(8, bst.root.value);
  	assertNull(bst.root.leftChild);
  	assertNull(bst.root.rightChild);
  }
  
  /**
   * Case2: 1 left children.
   * 
   * The node to be deleted is not the root of the tree.
   * 
   */
  @Test
  public void testDelete_1LeftChildIsNotRoot_replaceNodeWithChild()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 3);
  	bst.insert(bst.root, 1);
  	
  	bst.delete(bst.root, 3);
  	
  	assertEquals(5, bst.root.value);
  	assertEquals(1, bst.root.leftChild.value);
  	assertEquals(bst.root, bst.root.leftChild.parent);
  	assertNull(bst.root.rightChild);
  }
  
  /**
   * Case2: 1 right children.
   * 
   * The node to be deleted is not the root of the tree.
   * 
   */
  @Test
  public void testDelete_1RightChildIsNotRoot_replaceNodeWithChild()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 8);
  	bst.insert(bst.root, 10);
  	
  	bst.delete(bst.root, 8);
  	
  	assertEquals(5, bst.root.value);
  	assertEquals(10, bst.root.rightChild.value);
  	assertEquals(bst.root, bst.root.rightChild.parent);
  	assertNull(bst.root.leftChild);
  }
  
  @Test
  public void testDelete_2ChildrenIsRoot_replaceRootWithMinNodeInRightSubtree()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 3);
  	bst.insert(bst.root, 8);
  	
  	// delete root
  	bst.delete(bst.root, 5);
  	
  	assertEquals(8, bst.root.value);
  	assertNull(bst.root.rightChild);
  	assertEquals(3, bst.root.leftChild.value);
  	assertEquals(8, bst.root.leftChild.parent.value);
  }
  
  @Test
  public void testDelete_2ChildrenIsNotRoot_replaceNodeWithMinNodeInRightSubtree()
  {
  	bst.insert(bst.root, 5);
  	bst.insert(bst.root, 3);
  	bst.insert(bst.root, 8);
  	bst.insert(bst.root, 1);
  	bst.insert(bst.root, 4);
  	
  	// delete node
  	bst.delete(bst.root, 3);
  	
  	assertEquals(5, bst.root.value);
  	assertEquals(4, bst.root.leftChild.value);
  	assertEquals(bst.root, bst.root.leftChild.parent);
  	assertEquals(1, bst.root.leftChild.leftChild.value);
  	assertEquals(4, bst.root.leftChild.leftChild.parent.value);
  	assertEquals(8, bst.root.rightChild.value);
  }
  
  @Test
  public void testDelete_general()
  {
  	bst.insert(bst.root, 5);
    bst.insert(bst.root, 2);
    bst.insert(bst.root, 7);
    bst.insert(bst.root, 1);
    bst.insert(bst.root, 3);
    bst.insert(bst.root, 6);
    
    System.out.println("\nBefore deletion:");
    bst.preOrderTraversal(bst.root);
    
    bst.delete(bst.root, 5);
    
    System.out.println("\nAfter deletion:");
    bst.preOrderTraversal(bst.root);
    
    assertEquals(6, bst.root.value);
    assertEquals(7, bst.root.rightChild.value);
    assertNull(bst.root.rightChild.leftChild);
    assertNull(bst.root.rightChild.rightChild);
    
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
    
    System.out.println("\nPre Order: ");
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
