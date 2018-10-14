package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreeRaina {
    private class Node{
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    private Node root;
    private int size;

    public BinaryTreeRaina() {
        Scanner scn = new Scanner(System.in);
        this.root = this.takeInput(scn, null, false);
    }
    
    public BinaryTreeRaina(int[] inOrder, int[] postOrder) {
        this.root = this.construct(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
        this.size = postOrder.length;
    }

    private Node construct(int[] inOrder, int inStartIdx, int inLastIdx, int[] postOrder, int postStartIdx, int postLastIdx) {
        if (inStartIdx > inLastIdx || postStartIdx > postLastIdx) {
            return null;
        }

        Node node = new Node(0, null, null);
        node.data = postOrder[postLastIdx];  // root is the last element of postOrder array

        int rootIdx = -1;       // we will keep the index of root node in inOrder array here
        for (rootIdx = inStartIdx; rootIdx <= inLastIdx; rootIdx++) { // loop over inOrder array
            if (postOrder[postLastIdx] == inOrder[rootIdx]) { // and search for the root node
                break; // when found, break to store its index in rootIdx
            }
        }

        // inside the inOrder array
        // left side will start at inStartIdx and end at rootIdx - 1
        // right side will start at rootIdx + 1 and end at inLastIdx

        // inside the postOrder Array
        // left side will start at postStartIdx and end at postLastIdx - numElemRight - 1
        // right side will start at postLastIdx - numElemRight and end at postLastIdx - 1
        int numRightElem = inLastIdx - rootIdx; // calculate the number of elements in right subtree
        node.left = this.construct(inOrder, inStartIdx, rootIdx - 1,
                postOrder, postStartIdx,postLastIdx - numRightElem - 1);
        node.right = this.construct(inOrder, rootIdx + 1, inLastIdx,
                postOrder, postLastIdx - numRightElem, postLastIdx - 1);

        return node;
    }

    private Node takeInput(Scanner scn, Node parent, boolean isLeftChild) {
        if (parent == null) {
            System.out.print("Enter the data for root: ");
        } else {
            if (isLeftChild) {
                System.out.print("Enter the data for left child of " + parent.data + ": ");
            } else {
                System.out.print("Enter the data for right child of " + parent.data + ": ");
            }
        }

        int childData = scn.nextInt();
        Node child = new Node(childData, null, null);
        size++;

        System.out.print("Do you have a left child for " + child.data + ": "); // answer in true or false
        boolean hasLeftChild = scn.nextBoolean();

        if (hasLeftChild) {
            child.left = this.takeInput(scn, child, true);
        }

        System.out.print("Do you have a right child for " + child.data + ": ");
        boolean hasRightChild = scn.nextBoolean();
        if (hasRightChild) {
            child.right = this.takeInput(scn, child, false);
        }

        return child;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public void display() {
        this.display(this.root);
    }

    private void display(Node node) { // expectation is that this function will display the whole binary tree as: left child -> node <- right child
        if(node == null) { // if there are no children, return
            return;
        }

        String outputString = "";
        outputString += node.left != null ? node.left.data:"."; // if node.left is not null, add data to string, else add full stop
        outputString += " -> " + node.data + " <- ";
        outputString += node.right != null ? node.right.data:"."; // if node.left is not null, add data to string, else add full stop

        System.out.println(outputString);

        this.display(node.left); // faith in left child
        this.display(node.right); // faith in right child
    }

    public int getSize(){ // method to calculate size, if we don't have a size variable
        return this.getSize(this.root);
    }

    private int getSize(Node node) {  //  expectation is that the method will count and return the size of the tree
        if (node == null) { // base case, if node is null, its size is 0
            return 0;
        }

        int leftSize = this.getSize(node.left); // faith that we will get size of left subtree
        int rightSize = this.getSize(node.right); // faith that we will get size of right subtree

        return (leftSize + rightSize + 1); // so actual size will be leftSize + rightSize + 1 (for the root node itself)
    }
//=================================================height of the binary tree===================================================
    public int height(){
        return this.height(this.root);
    }

    private int height(Node node){
        if(node==null){
            return 0;
        }

        int leftSubtreeHeight = this.height(node.left);
        int rightSubtreeHeight = this.height(node.right);
        return(Math.max(leftSubtreeHeight,rightSubtreeHeight)+1);

    }
    //==========================================================================================================================

    //======================================================maximum of the binary tree============================================
    //=============================================================================================================================


    //=======================================================find in binary tree===============================================
    public boolean findele(int key){
        return this.findele(this.root,key);
    }

    private boolean findele(Node node, int key){
      if(node==null){
          return false;
      }
      if(node.data==key){
          return true;
      }

      boolean foundInLeftChild = findele(node.left,key);
      if(foundInLeftChild){
          return true;
      }
      boolean foundInRightChild = findele(node.right,key);
      if(foundInRightChild){
          return true;
      }


        return false;

    }
    //=============================================================================================================================

    //==============================traversals======================================================================================

    public void preOrder(){
        this.preOrder(this.root);
    }

    private void preOrder(Node node){
        if(node==null){
            return;
        }

        System.out.print(node.data+"->");
        preOrder(node.left);
        preOrder(node.right);
    }
    //==============================================================================================================================
    public void levelOrder(){
        this.levelOrder(this.root);
    }

    private void levelOrder(Node node){
        if(node==null){
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(node);
        while(!queue.isEmpty()){
            Node removed = queue.removeFirst();
            if(removed.left!=null){
                queue.addLast(removed.left);
            }
            if(removed.right!=null){
                queue.addLast(removed.right);
            }
            System.out.print(removed.data+"->");
        }System.out.println("\n");


    }

    //=================================================print single child======================================================
    public void singleChild(){
        this.singleChild(this.root,this.root.left);
        this.singleChild(this.root,this.root.right);
    }

    private void singleChild(Node parent,Node child){
        if(child==null){
            return;
        }

        if((parent.left!=null && parent.right==null)){   System.out.print(parent.left.data);     }


        if((parent.left==null)&&(parent.right!=null)){
            System.out.print(parent.right.data);
        }


        this.singleChild(child,child.left);
        this.singleChild(child,child.right);
    }
    //=========================================================================================================
    //========================================remove Leaf Nodes================================================
    public void removeLeafNodes(){
        this.removeLeafNodes(this.root,this.root.left);
        this.removeLeafNodes(this.root,this.root.right);

    }

    private void removeLeafNodes(Node parent,Node child){
        if(child==null){
            return;
        }
        if(child.left==null&&child.right==null){
            if(parent.left==child){
                parent.left=null;
            }else{
                parent.right=null;
            }
        }
        removeLeafNodes(child,child.left);
        removeLeafNodes(child,child.right);
    }
    //=========================================================================================================

    //======================================root To Node path==================================================
    public ArrayList<Integer> rootToNodePath(int data){
        return this.rootToNodePath(this.root,data);
    }

    private ArrayList<Integer> rootToNodePath(Node node,int data){
        if(node==null){
            ArrayList<Integer> path = new ArrayList<Integer>();
            return path;
        }

        //if the path is from root to root
        if(node.data==data){
            ArrayList<Integer> newList = new ArrayList<>();
            newList.add(node.data);
            return newList;
        }

        //if path is from a node in the left subtree
        ArrayList<Integer> pathFromLeftSubtree = this.rootToNodePath(node.left,data);
        if(pathFromLeftSubtree.size()!=0){
            pathFromLeftSubtree.add(node.data);
            return pathFromLeftSubtree;
        }

        ArrayList<Integer> pathFromrightSubtree = this.rootToNodePath(node.right,data);
        if(pathFromrightSubtree.size()!=0){
            pathFromrightSubtree.add(node.data);
            return pathFromrightSubtree;
        }


        return new ArrayList<>();

    }
    //==================================root To Node Paths with target sum=========================================
    public ArrayList<Integer> targetSum(int target){
        return this.targetSum(this.root,0,target);
    }

    private ArrayList<Integer> targetSum(Node node,int ssf,int target){
        if(node==null){
            return new ArrayList<Integer>();
        }
        if(node.data==target){
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }

        ArrayList<Integer> pathFromLeftSubtree = targetSum(node.left,ssf+node.data,target);
        if(ssf==target){
            pathFromLeftSubtree.add(node.data);
            return pathFromLeftSubtree;
        }

        ArrayList<Integer> pathFromRightSubtree = targetSum(node.right,ssf+node.data,target);
        if(ssf==target){
            pathFromRightSubtree.add(node.data);
            return pathFromRightSubtree;
        }


        return new ArrayList<Integer>();

    }
    //=============================================================================================================

    

}
