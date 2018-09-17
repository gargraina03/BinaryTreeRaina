package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        //50 true 25 true 12 false true 20 false false true 37 true 30 false false false true 75 true 62 false false true 87 false false
        //5 true -10 true 9 false false true 8 false false true 3 true -4 false false true 7 false false
        //1 true 2 true 3 true 4 false false true 5 true 6 false true 7 false false true 8 true 9 true 10 false false true 11 true 12 true 14 false false true 15 false false true 13 false false true 16 true 17 false false true 18 false false true 19 false false true 20 false false

        /*
        sample tree with some state of the art skills
           50
       /      \
     25       75
    /  \     /
  12    37 62
   \   /
   20 30
         */
        BinaryTreeRaina btr = new BinaryTreeRaina();
        btr.display();
        System.out.println("Size Of The Tree Is - " + btr.getSize());
        System.out.println("Height Of The Tree Is - " + btr.height());
        // System.out.println("Enter the element you want to search for?");
        //int key = scn.nextInt();
        //System.out.println(btr.findele(key));
        System.out.println("Pre Order Traversal - ");
        btr.preOrder();
        System.out.println("Level Order Traversal - ");
        btr.levelOrder();
        System.out.println("Single Children - ");
        btr.singleChild();
        System.out.println();
        //btr.removeLeafNodes();
        //System.out.println("Tree after leaf nodes removed - ");
        //btr.display();
        System.out.println("Root To Node paths - ");
        System.out.println(btr.rootToNodePath(30));
        System.out.println("target sum path - ");
        btr.rootToNodePath(100);
        System.out.println("Sum of elements at each level - ");
        //btr.SumOfElementsAtAGivenLevel();
        System.out.println("Max sum root to node paths- ");
        btr.MaxRootToNodePaths();
        System.out.println();
        System.out.println("level Order Recursive - ");
        System.out.println();
        btr.LevelOrderRecursive();
        System.out.println();
        //btr.MaxLevelSum();
        System.out.println();
        btr.kLevelSum(3);

        System.out.println("Lowest Common Ancestor - ");
        System.out.println(btr.LowestCommonAncestor(20, 87));
        System.out.println(btr.LowestCommonAncestor(20, 30));
        System.out.println("Subtrees with a given sum value -");
        btr.CountSubtreesWithAGivenSumValue(7);
        //System.out.println("checking for presence for subtree in a given tree -");
        //BinaryTreeRaina btr2 = new BinaryTreeRaina();
        //25 true 12 false true 20 false false true 37 true 30 false false false
        //System.out.println(btr.hasSubtree(btr2));
        System.out.println("Print Left View - ");
        btr.printLeftView();
        System.out.println("Checking For BST -");
        System.out.println(btr.isBST());
        System.out.println("checking fo rif the input Tree is balanced - ");
        System.out.println(btr.isTreeBalanced());
        System.out.println("Diameter of a tree - ");
        System.out.println(btr.getDiameter());
        System.out.println("get Diameter optimised tareeke se -");
        System.out.println(btr.getDiameterOpt());

    }
}
