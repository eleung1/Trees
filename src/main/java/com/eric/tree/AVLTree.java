package com.eric.tree;

/**
 * AVL tree.  Self-balancing binary search tree.
 * 
 * The tree is named after the inventors: Georgy Adelson-Velsky and Evgenii Landis.
 * 
 * Complexity:
 * 
 * Average              Worst
 * Search: O(log n)     O(log n)
 * Insert: O(log n)     O(log n)
 * Delete: O(log n)     O(log n)
 * Space:  O(n)         O(n)
 * 
 * @author Eric Leung
 *
 */
public class AVLTree extends BinarySearchTree
{
	public TreeNode insertAndBalance(TreeNode root, int v)
	{
		TreeNode rc = insert(root,v);
		balance(find(this.root, v));
		return rc;
	}
	
	public void deleteAndBalance(TreeNode root, int v)
	{
	  TreeNode deletedNode = find(root, v);
	  
	  // Delete the node
	  delete(root, v);
	  
	  // Back-trace from deletedNode all the way up to the first unbalanced node and balance if needed
	  if ( deletedNode != null )
	  {
	    balance(deletedNode.parent);
	  }
	  
	}
	
	/**
	 * Balancer the tree after inserting the newNode.
	 * 
	 * @param newNode The newly inserted node
	 */
	public void balance(TreeNode newNode)
	{
		
		TreeNode currNode = newNode;
		
		while ( currNode != null )
		{
			
			int balanceFactor = getBalanceFactor(currNode);
			
			if ( balanceFactor > 1 )
			{
				// balance the tree
				TreeNode unbalancedRoot = currNode;
				if ( unbalancedRoot.leftChild != null && unbalancedRoot.leftChild.leftChild != null )
				{
					// Left left case.  Right rotate unbalanced root.
					rightRotate(unbalancedRoot);
					break;
				}
				else if ( unbalancedRoot.leftChild != null && unbalancedRoot.leftChild.rightChild != null )
				{
					// Left right case
					leftRotate(unbalancedRoot.leftChild);
					rightRotate(unbalancedRoot);
					break;
				}
				else if ( unbalancedRoot.rightChild != null && unbalancedRoot.rightChild.rightChild != null )
				{
					// Right right case
					leftRotate(unbalancedRoot);
					break;
				}
				else if ( unbalancedRoot.rightChild != null && unbalancedRoot.rightChild.leftChild != null )
				{
					// Right left case
					rightRotate(unbalancedRoot.rightChild);
					leftRotate(unbalancedRoot);
					break;
				}
				else
				{
					// should not get to here...
					throw new IllegalStateException();
				}
			}
			else
			{
				currNode = currNode.parent;
			}
		}
	}
	
	public void rightRotate(TreeNode node)
	{
		TreeNode originalLeftChild = node.leftChild;
		TreeNode originalLeftRightGrandChild = originalLeftChild.rightChild;
		
		replaceNodeInParent(node, originalLeftChild);
		
		originalLeftChild.rightChild = node;
		node.parent = originalLeftChild;
		
		node.leftChild = originalLeftRightGrandChild;
		if ( originalLeftRightGrandChild != null )
		{
			originalLeftRightGrandChild.parent = node;
		}
		
	}
	
	public void leftRotate(TreeNode node)
	{
		TreeNode originalRightChild = node.rightChild;
		TreeNode originalRightLeftGrandChild = originalRightChild.leftChild;
		
		replaceNodeInParent(node, originalRightChild);
		
		originalRightChild.leftChild = node;
		node.parent = originalRightChild;
		
		node.rightChild = originalRightLeftGrandChild;
		if ( originalRightLeftGrandChild != null )
		{
			originalRightLeftGrandChild.parent = node;
		}
	}
	
	public static int getBalanceFactor(TreeNode node)
	{
		return Math.abs(AVLTree.height(node.leftChild) - AVLTree.height(node.rightChild) );
	}
}
