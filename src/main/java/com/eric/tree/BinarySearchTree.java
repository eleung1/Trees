package com.eric.tree;

public class BinarySearchTree implements Tree
{
  protected TreeNode root;

  public TreeNode insert(TreeNode root, int v)
  {
    if (root == null)
    {
      root = new TreeNode(v);
      if (this.root == null)
      {
        // brand new tree, set this.root to root
        this.root = root;
      }
    }

    if (root.value == v)
    {
      // value already in tree
    }
    else if (root.value > v)
    {
      root.leftChild = insert(root.leftChild, v);
    }
    else
    {
      root.rightChild = insert(root.rightChild, v);
    }

    return root;
  }

  public void delete(TreeNode root, int v)
  {
    // TODO Auto-generated method stub

  }

}
