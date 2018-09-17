package com.company;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

public class BinaryTreeRaina {
    private class Node {
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
        if (node == null) { // if there are no children, return
            return;
        }

        String outputString = "";
        outputString += node.left != null ? node.left.data : "."; // if node.left is not null, add data to string, else add full stop
        outputString += " -> " + node.data + " <- ";
        outputString += node.right != null ? node.right.data : "."; // if node.left is not null, add data to string, else add full stop

        System.out.println(outputString);

        this.display(node.left); // faith in left child
        this.display(node.right); // faith in right child
    }

    public int getSize() { // method to calculate size, if we don't have a size variable
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
    public int height() {
        return this.height(this.root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }

        int leftSubtreeHeight = this.height(node.left);
        int rightSubtreeHeight = this.height(node.right);
        return (Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1);

    }


    //==========================================================================================================================

    //======================================================maximum of the binary tree============================================
    public int max(){
        return this.max(this.root);
    }

    private int max(Node node) {

        if(node==null){

            return Integer.MIN_VALUE;
        }
        int leftSubtreeMax = this.max(node.left);
        int rightSubtreeMax = this.max(node.right);
        return(Math.max(node.data,Math.max(leftSubtreeMax,rightSubtreeMax)));
    }

    //=============================================================================================================================
 //======================================================maximum of the binary tree============================================
    public int min(){
        return this.min(this.root);
    }

    private int min(Node node) {

        if(node==null){

            return Integer.MAX_VALUE;
        }
        int leftSubtreeMin = this.min(node.left);
        int rightSubtreeMin = this.min(node.right);
        return(Math.min(node.data,Math.min(leftSubtreeMin,rightSubtreeMin)));
    }

    //=============================================================================================================================



    //=======================================================find in binary tree===============================================
    public boolean findele(int key) {
        return this.findele(this.root, key);
    }

    private boolean findele(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.data == key) {
            return true;
        }

        boolean foundInLeftChild = findele(node.left, key);
        if (foundInLeftChild) {
            return true;
        }
        boolean foundInRightChild = findele(node.right, key);
        if (foundInRightChild) {
            return true;
        }


        return false;

    }
    //=============================================================================================================================

    //==============================traversals======================================================================================

    public void preOrder() {
        this.preOrder(this.root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + "->");
        preOrder(node.left);
        preOrder(node.right);
    }

    //==============================================================================================================================
    public void levelOrder() {
        this.levelOrder(this.root);
    }

    private void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(node);
        while (!queue.isEmpty()) {
            Node removed = queue.removeFirst();
            if (removed.left != null) {
                queue.addLast(removed.left);
            }
            if (removed.right != null) {
                queue.addLast(removed.right);
            }
            System.out.print(removed.data + "->");
        }
        System.out.println("\n");


    }

    //=================================================print single child======================================================
    public void singleChild() {
        this.singleChild(this.root, this.root.left);
        this.singleChild(this.root, this.root.right);
    }

    private void singleChild(Node parent, Node child) {
        if (child == null) {
            return;
        }

        if ((parent.left != null && parent.right == null)) {
            System.out.print(parent.left.data);
        }


        if ((parent.left == null) && (parent.right != null)) {
            System.out.print(parent.right.data);
        }


        this.singleChild(child, child.left);
        this.singleChild(child, child.right);
    }

    //=========================================================================================================
    //========================================remove Leaf Nodes================================================
    public void removeLeafNodes() {
        this.removeLeafNodes(this.root, this.root.left);
        this.removeLeafNodes(this.root, this.root.right);

    }

    private void removeLeafNodes(Node parent, Node child) {
        if (child == null) {
            return;
        }
        if (child.left == null && child.right == null) {
            if (parent.left == child) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        removeLeafNodes(child, child.left);
        removeLeafNodes(child, child.right);
    }
    //=========================================================================================================

    //======================================root To Node path==================================================
    public ArrayList<Integer> rootToNodePath(int data) {
        return this.rootToNodePath(this.root, data);
    }

    private ArrayList<Integer> rootToNodePath(Node node, int data) {
        if (node == null) {
            ArrayList<Integer> path = new ArrayList<Integer>();
            return path;
        }

        //if the path is from root to root
        if (node.data == data) {
            ArrayList<Integer> newList = new ArrayList<>();
            newList.add(node.data);
            return newList;
        }

        //if path is from a node in the left subtree
        ArrayList<Integer> pathFromLeftSubtree = this.rootToNodePath(node.left, data);
        if (pathFromLeftSubtree.size() != 0) {
            pathFromLeftSubtree.add(node.data);
            return pathFromLeftSubtree;
        }

        ArrayList<Integer> pathFromrightSubtree = this.rootToNodePath(node.right, data);
        if (pathFromrightSubtree.size() != 0) {
            pathFromrightSubtree.add(node.data);
            return pathFromrightSubtree;
        }


        return new ArrayList<>();

    }
    //==================================root To Node Paths with target sum=========================================
   /* public void targetSum(int target){
         this.targetSum(this.root,0,target);
    }

    private void targetSum(Node node,int ssf,int target){
        if(node==null){
            new ArrayList<Integer>();
        }
        if(node.data==target){
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            System.out.println(path);
        }

        ArrayList<Integer> pathFromLeftSubtree = new ArrayList<>();
        if(ssf==target){
            pathFromLeftSubtree.add(node.data);
            System.out.println(pathFromLeftSubtree);
        }
        targetSum(node.left,ssf+node.data,target);

        ArrayList<Integer> pathFromRightSubtree = new ArrayList<>();
        if(ssf==target){
            pathFromRightSubtree.add(node.data);
            System.out.println(pathFromRightSubtree);
        }
        targetSum(node.right,ssf+node.data,target);




    }*/
    //=============================================================================================================

    public void printRootToNodePaths(int target) {
        this.printRootToNodePaths(root, 0, "", target);
    }

    private void printRootToNodePaths(Node node, int ssf, String psf, int target) {
        //base case
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            if (ssf + node.data < target) {
                psf = psf + "-" + node.data;
                System.out.println(psf);
            }

        }

        printRootToNodePaths(node.left, ssf + node.data, psf + "-" + node.data, target);
        printRootToNodePaths(node.right, ssf + node.data, psf + "-" + node.data, target);
    }

    //==============================================sum of nodes at each level=====================================
    public void SumOfElementsAtAGivenLevel() {
        this.SumOfElementsAtAGivenLevel(this.root);
    }


    private void SumOfElementsAtAGivenLevel(Node node) {
        if (node == null) {
            return;
        }


        LinkedList<Node> queue = new LinkedList<>();  //created an empty queue
        queue.addFirst(node);

        while (!queue.isEmpty()) {
            int count = queue.size();
            int sum = 0;
            while (count != 0) {
                Node removed = queue.removeFirst();
                sum += removed.data;
                if (removed.left != null) {
                    queue.addLast(removed.left);
                }
                if (removed.right != null) {
                    queue.addLast(removed.right);
                }
            }
            System.out.println(sum);
        }
    }


    //==================================================finding maximum sum root to leaf path in a binary tree============================
    public void MaxRootToNodePaths() {
        this.MaxRootToNodePaths(this.root, 0);
    }

    private void MaxRootToNodePaths(Node node, int ssf) {
        if (node == null) {
            return;
        }


        int branch = 1;
        if (node.left == null && node.right == null) {
            System.out.println("Branch " + branch + " sum is - " + ssf + node.data);
            branch++;
        }
        /*max = fmax.removeFirst();
        while(!fmax.isEmpty()){
            int pop = fmax.removeFirst();
            if(pop>max){
                max = pop;
            }

        }
*/
        //System.out.println(max);


        MaxRootToNodePaths(node.left, ssf + node.data);
        MaxRootToNodePaths(node.right, ssf + node.data);
    }

    //=====================================================================================================================================
    //==================================max level sum===========================================================//
    //helu boiz
    public void LevelOrderRecursive() {
        ArrayList<Node> currLevel = new ArrayList<>();
        currLevel.add(this.root);
        this.LevelOrderRecursive(currLevel);

    }


    private void LevelOrderRecursive(ArrayList<Node> currLevel) {
        ArrayList<Node> nextLevel = new ArrayList<>();
        for (Node n : currLevel) {
            System.out.print(n.data + "-");
            if (n.left != null) {
                nextLevel.add(n.left);
            }
            if (n.right != null) {
                nextLevel.add(n.right);
            }
        }
        System.out.println();


        if (!nextLevel.isEmpty()) {
            LevelOrderRecursive(nextLevel);
        }

    }

    //=====================================max Level Sum=========================================
    public class HeapMover {
        int max;
        int level;
    }

    public void MaxLevelSum() {
        ArrayList<Node> currLevel = new ArrayList<>();
        currLevel.add(this.root);
        HeapMover maxLevelWrapper = new HeapMover();
        this.MaxLevelSum(currLevel, 1, maxLevelWrapper);
        System.out.println(maxLevelWrapper.max + " is the max sum at level " + maxLevelWrapper.level);
    }

    private void MaxLevelSum(ArrayList<Node> currLevel, int i, HeapMover maxLevelWrapper) {
        ArrayList<Node> nextLevel = new ArrayList<>();
        int sum = 0;
        for (Node n : currLevel) {
            //SUM MATA
            sum += n.data;

            if (n.left != null) {
                nextLevel.add(n.left);
            }
            if (n.right != null) {
                nextLevel.add(n.right);
            }
        }
        if (sum > maxLevelWrapper.max) {
            maxLevelWrapper.max = sum;
            maxLevelWrapper.level = i;
        }//MAX MATA


        if (!nextLevel.isEmpty())
            MaxLevelSum(nextLevel, i + 1, maxLevelWrapper);

    }

    //===============================kth level Sum ===================================

    public void kLevelSum(int k) {
        ArrayList<Node> currLevel = new ArrayList<Node>();
        currLevel.add(this.root);
        this.kLevelSum(currLevel, k, 1);
    }

    private void kLevelSum(ArrayList<Node> currLevel, int k, int i) {
        ArrayList<Node> nextLevel = new ArrayList<>();
        int sum = 0;
        for (Node n : currLevel) {
            sum += n.data;
            if (n.left != null) {
                nextLevel.add(n.left);
            }
            if (n.right != null) {
                nextLevel.add(n.right);
            }

        }
        if (i == k) {
            System.out.println("Sum at level " + k + " is" + sum);
        }
        if (!nextLevel.isEmpty())
            kLevelSum(nextLevel, k, i + 1);

    }

    //====================================================================================================
    //=======================Lowest Common Ancestor===================================================
    public int LowestCommonAncestor(int n1, int n2) {
        return this.LowestCommonAncestor(this.root, n1, n2);
    }

    private int LowestCommonAncestor(Node node, int n1, int n2) {
        int rv = 0;
        ArrayList<Node> pathTon1 = rootToNodePathsAsNodes(n1);
        ArrayList<Node> pathTon2 = rootToNodePathsAsNodes(n2);
        for (int i = 0; i < Math.min(pathTon1.size(), pathTon2.size()); i++) {
            if (pathTon1.get(pathTon1.size() - i - 1).equals(pathTon2.get(pathTon2.size() - i - 1))) {
                rv = pathTon1.get(pathTon1.size() - i - 1).data;
            }
        }

        return rv;
    }
    //Helper Function


    //==============================root To Node Path An Nodes===============================================
    public ArrayList<Node> rootToNodePathsAsNodes(int data) {
        return this.rootToNodePathsAsNodes(this.root, data);
    }

    private ArrayList<Node> rootToNodePathsAsNodes(Node node, int data) {
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.data == data) {
            ArrayList<Node> newList = new ArrayList<>();
            newList.add(node);
            return newList;
        }


        ArrayList<Node> leftSubtreePath = rootToNodePathsAsNodes(node.left, data);
        //left ki bkchodi
        if (leftSubtreePath.size() != 0) {
            leftSubtreePath.add(node);
            return leftSubtreePath;
        }
        ArrayList<Node> rightSubtreePath = rootToNodePathsAsNodes(node.right, data);
        //right ki bkloli
        if (rightSubtreePath.size() != 0) {
            rightSubtreePath.add(node);
            return rightSubtreePath;
        }

        return new ArrayList<>();
    }


    //==================================count Subtrees with a given Sum Value===================
    public class Heapmover {
        int count = 0;
    }

    public void CountSubtreesWithAGivenSumValue(int targetSum) {
        Heapmover treeCounter = new Heapmover();
        this.CountSubtreesWithAGivenSumValue(this.root, 0, targetSum, treeCounter);
        System.out.println(treeCounter.count);
    }

    private void CountSubtreesWithAGivenSumValue(Node node, int ssf, int targetSum, Heapmover treeCounter) {
        int rv = 0;

        if (node == null) {
            return;
        }


        if (ssf == targetSum) {
            treeCounter.count++;
        }

        //size type ki 2 calls maarenge
        CountSubtreesWithAGivenSumValue(node.left, ssf + node.data, targetSum, treeCounter);
        CountSubtreesWithAGivenSumValue(node.right, ssf + node.data, targetSum, treeCounter);
        return;
    }

    //===========================================================================================

   /*
   cousin sum

   print the diagonal sums
   diameter of a Binary Tree
   ---BST---
   given a BST, return the root of Largest BST Subtree
   mushkil bc - aalas bc - connect all nodes at same level
           50 ---- NULL
       /      \
     25-------75-- NULL
    /  \     /
  12----37-62------NULL
   \   /
   20 30-----------NULL


    */

    //========================subtreeOfAGivenTree======================================
    public boolean hasSubtree(BinaryTreeRaina childTree) {
        return this.hasSubtree(this.root, childTree.root);
    }

    private boolean hasSubtree(Node node, Node childTreeNode) {
        //base case bkchodi
        if (childTreeNode == null) {
            return true;
        }
        if (node == null) {
            return false;
        }


        //check section
        if (areIdentical(node, childTreeNode))
            return true;


        //recursive calls
        boolean hasChildRootLeft = hasSubtree(node.left, childTreeNode);
        boolean hasChildRootRight = hasSubtree(node.right, childTreeNode);

        return hasChildRootLeft || hasChildRootRight;


    }

    //helper function
    boolean areIdentical(Node nodeT, Node nodeS) {
        //base case
        if (nodeT == null && nodeS == null) {
            return true;
        }
        if (nodeT == null || nodeS == null) {
            return false;
        }
        //check section
        boolean isDataSame = nodeT.data == nodeS.data;


        //recursive calls
        boolean isLeftIdentical = areIdentical(nodeT.left, nodeS.left);
        boolean isRightIdentical = areIdentical(nodeT.right, nodeS.right);

        return (isDataSame && isLeftIdentical && isRightIdentical);
    }

//======================================================================================================================
    //==============================================left View===========================================================

    public void printLeftView(){
        ArrayList<Node> currLevel = new ArrayList<>();
        currLevel.add(this.root);
        printLeftView(currLevel);
    }

    private void printLeftView(ArrayList<Node> currLevel){
        ArrayList<Node> nextLevel = new ArrayList<>();
        System.out.println(currLevel.get(0).data);
        for(Node n:currLevel){
            if(n.left!=null){
                nextLevel.add(n.left);
            }
            if(n.right!=null){
                nextLevel.add(n.right);
            }
        }

        if(!nextLevel.isEmpty())
        printLeftView(nextLevel);


    }
    //==============================================================================================================

    //=====================================checking if a tree is BST================================================
    public boolean isBST(){
        return this.isBST(this.root);
    }

    private boolean isBST(Node node) {
    if(node==null){
        return true;
    }

        int maxOfLeft = max(node.left);
        int minOfRight = min(node.right);//recursion calls
        boolean isBSTLeft = isBST(node.left);
        boolean isBSTRight = isBST(node.right);

        //check

        return((maxOfLeft<node.data&&node.data>minOfRight)&&isBSTLeft&&isBSTRight);


    }

    //======================================================================================================================================
    //======================================isBST balanced==================================================================================

    /*
    conditions to check for height balanced
    1 - an empty tree is height balanced
    2 - Left subtree of T is balanced
    3 - Right subtree of T is balanced
    4 - The difference between heights of left subtree and right subtree is not more than 1.
     */

    public boolean isTreeBalanced(){
        return isTreeBalanced(this.root);
    }

    private boolean isTreeBalanced(Node node) {

        if(node==null){
            return true;
        }

        //recursive calls
        boolean isLeftBalanced = isTreeBalanced(node.left);
        boolean isRightBalanced = isTreeBalanced(node.right);
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);


        int diff = Math.abs(leftHeight-rightHeight);
        return((diff<=1)&&isLeftBalanced&&isRightBalanced);
    }

//=========================================================================================================================
//=================================diameter of a Tree======================================================================
    public int getDiameter(){
        return this.getDiameter(this.root);
    }

    private int getDiameter(Node node) {

        if(node==null){
            return 0;
        }


        //recursive calls
        int LeftDiameter = getDiameter(node.left);
        int RightDiameter = getDiameter(node.right);
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return(Math.max(RightDiameter,Math.max(leftHeight+rightHeight+2,LeftDiameter)));


    }
    //====================================================================================================================
    //==============================getDiameterOptimised===================================================================
    private class DiaPair{
        int diameter;
        int height;
    }

    public int getDiameterOpt(){
        DiaPair pair =  getDiamterOpt(this.root);
        return pair.diameter;

    }

    private DiaPair getDiamterOpt(Node node){

        //Base Case
        if(node==null){
            DiaPair basePair = new DiaPair();
            basePair.height = -1;
            basePair.diameter = 0;
            return basePair;
        }


        DiaPair leftPair = getDiamterOpt(node.left);
        DiaPair rightPair = getDiamterOpt(node.right);


        DiaPair returnPair = new DiaPair();
        returnPair.height = Math.max(leftPair.height,rightPair.height)+1;
        returnPair.diameter = Math.max(Math.max(leftPair.diameter,rightPair.diameter),leftPair.height+rightPair.height+2);

        return returnPair;

    }

    //======================================================================================================================
    //========================checking if 2 nodes are cousins of each other=================================================
   /* public boolean areCousins(int a,int b){
        return areCousins(this.root,root.left,a,b);

    }

    private boolean areCousins(Node node, int a, int b) {
        if(node==null){
            return false;
        }
        //recursion calls

        boolean left = areCousins(node.left,a,b);
        boolean right = areCousins(node.right,a,b);

        //check
        int leftLevel = height(node.left);
        int rightLevel = height(node.right);


        if(leftLevel==rightLevel){

        }
    }
*/

   //======================optimised solution to check BST================================================
    public class BSTPair{
        int min;
        int max;
        boolean isBST;

    }
    public boolean isBSTOpt(){
        BSTPair bp = isBSTOpt(this.root);
        return bp.isBST;
    }

    private BSTPair isBSTOpt(Node node) {
        //base case
        if(node==null){
            BSTPair  bp = new BSTPair();
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;
            bp.isBST = true;
        }

        BSTPair leftPair = isBSTOpt(node.left);
        BSTPair rightPair = isBSTOpt(node.right);


        BSTPair returnPair = new BSTPair();
        returnPair.min = Math.min(node.data,Math.min(leftPair.min,rightPair.min));
        returnPair.max = Math.max(node.data,Math.max(leftPair.max,rightPair.max));
        returnPair.isBST = leftPair.isBST&&rightPair.isBST&&(leftPair.max<node.data)&&(node.data<rightPair.min);
        return returnPair;
    }
    //==================================================================================================================

    //find the largest subtree inside a tree============================================================================



}
