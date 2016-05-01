package com.eric.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest
{
  private BinarySearchTree bst;

  @Before
  public void setup()
  {
    bst = new BinarySearchTree();
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
  }
}
