package com.eric.tree;

public class AVLTree extends BinarySearchTree
{
	@Override
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
			root.leftChild.parent = root;
		}
		else
		{
			root.rightChild = insert(root.rightChild, v);
			root.rightChild.parent = root;
		}

		return root;
	}
	
	public TreeNode insertAndBalance(TreeNode root, int v)
	{
		TreeNode rc = insert(root,v);
		balance(find(this.root, v));
		return rc;
	}
	
	/**
	 * Balancer the tree after inserting the newNode.
	 * 
	 * @param newNode The newly inserted node
	 */
	public void balance(TreeNode newNode)
	{
		
		TreeNode currNode = newNode;
		
		while ( currNode.parent != null )
		{
			
			int balanceFactor = getBalanceFactor(currNode.parent);
			
			if ( balanceFactor > 1 )
			{
				// balance the tree
				TreeNode unbalancedRoot = currNode.parent;
				if ( unbalancedRoot.leftChild != null && unbalancedRoot.leftChild.leftChild != null )
				{
					// Left left case.  Right rotate unbalanced root.
					rightRotate(unbalancedRoot);
					break;
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
		
		// updated the node's parent before rotating it to the right.
		//replaceNodeInParent(node, originalLeftChild);
		originalLeftChild.parent = node.parent;
		
		node.leftChild = originalLeftChild.rightChild;
		
		if ( originalLeftChild.rightChild != null )
		{
			originalLeftChild.rightChild.parent = node;
		}
		
		originalLeftChild.rightChild = node;
		node.parent = originalLeftChild;
		
		if (this.root == node)
		{
			this.root = originalLeftChild;
		}
	}
	
	public static int getBalanceFactor(TreeNode node)
	{
		return Math.abs(AVLTree.height(node.leftChild) - AVLTree.height(node.rightChild) );
	}
}
