package com.eric.tree;

public interface Tree
{
    public TreeNode insert(TreeNode root, int v);
    
    public void delete(TreeNode root, int v);
    
    public TreeNode find(TreeNode root, int v);
    
}
