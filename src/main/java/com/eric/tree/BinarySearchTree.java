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
			root.leftChild.parent = root;
		}
		else
		{
			root.rightChild = insert(root.rightChild, v);
			root.rightChild.parent = root;
		}

		return root;
	}

	public TreeNode find(TreeNode root, int v)
	{
		if ( root == null )
		{
			// not found
			return null;
		}
		else if ( root.value == v)
		{
			return root;
		}
		else if ( root.value > v)
		{
			return find(root.leftChild, v);
		}
		else
		{
			return find(root.rightChild, v);
		}
		
	}
	
	/**
	 * Deletion has 3 cases:
	 * 
	 * 1.  Node to be deleted has no child.
	 * 2.  Node to be deleted has one child.
	 * 3.  Node to be deleted has 2 children.
	 * 
	 */
	public void delete(TreeNode root, int v)
	{
		TreeNode nodeToDelete = find(root, v);
		
		if ( nodeToDelete == null )
		{
			return; // node does not exist.  No need to delete
		}
		
		if ( nodeToDelete.leftChild == null && nodeToDelete.rightChild == null )
		{
			// Case 1:  no children.  Simply delete the node
			if ( nodeToDelete == this.root )
			{
				this.root = null;
			}
			else 
			{
				replaceNodeInParent(nodeToDelete, null);
			}
		}
		else if ( nodeToDelete.leftChild != null && nodeToDelete.rightChild == null )
		{
			// Case 2: one left child
			replaceNodeInParent(nodeToDelete, nodeToDelete.leftChild);
		}
		else if ( nodeToDelete.leftChild == null && nodeToDelete.rightChild != null )
		{
			// Case 2: one right child
			replaceNodeInParent(nodeToDelete, nodeToDelete.rightChild);
		}
		else if ( nodeToDelete.leftChild != null && nodeToDelete.rightChild != null )
		{
			// Case 3: 2 children.  Replace nodeToDelete with the min child in its right subtree.
			// case3a: We are deleting the root
			TreeNode replacementNode = findMin(nodeToDelete.rightChild);
			
			// delete the replacement node, which will be a case 1 or case 2;
			this.delete(this.root, replacementNode.value); 
			
			nodeToDelete.value = replacementNode.value;
		}
	}
	
	/**
	 * Helper method to replace nodeToDelete's parent's child node.
	 * 
	 */
	private void replaceNodeInParent(TreeNode nodeToDelete, TreeNode replacementNode)
	{
		if ( nodeToDelete.parent.leftChild == nodeToDelete )
		{
			nodeToDelete.parent.leftChild = replacementNode;
		}
		else if ( nodeToDelete.parent.rightChild == nodeToDelete )
		{
			nodeToDelete.parent.rightChild = replacementNode;
		}
		
		if ( replacementNode != null )
		{
			//replacementNode.leftChild = nodeToDelete.leftChild;
			replacementNode.parent = nodeToDelete.parent;
		}
	}
	
	/**
	 * Helper method
	 * 
	 * @return min node in the given tree rooted at the given root.
	 */
	private TreeNode findMin(TreeNode root)
	{
		TreeNode minNode = root;
		
		while ( minNode.leftChild != null )
		{
			minNode = minNode.leftChild;
		}
		
		return minNode;
	}
	
	public void preOrderTraversal(TreeNode root)
	{
		if ( root != null )
		{
			visit(root);
			preOrderTraversal(root.leftChild);
			preOrderTraversal(root.rightChild);
		}
	}
	
	public void postOrderTraversal(TreeNode root)
	{
		if ( root != null )
		{
			postOrderTraversal(root.leftChild);
			postOrderTraversal(root.rightChild);
			visit(root);
		}
	}
	
	public void inOrderTraversal(TreeNode root)
	{
		if ( root != null )
		{
			inOrderTraversal(root.leftChild);
			visit(root);
			inOrderTraversal(root.rightChild);
		}
	}
	
	/**
	 * Visiting the node. Simply print out the value
	 * 
	 * @param node
	 */
	public void visit(TreeNode node)
	{
		System.out.print(node.value + " ");
	}

	public static int height(TreeNode root)
  {
		if ( root == null )
		{
			return 0;
		}
		
		return Math.max(height(root.leftChild), height(root.rightChild)) + 1;
  }
}
