package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        //50 true 25 true 12 false true 20 false false true 37 true 30 false false false true 75 true 62 false false true 87 false false
	BinaryTreeRaina btr = new BinaryTreeRaina();
    btr.display();
    System.out.println("Size Of The Tree Is - "+btr.getSize());
    System.out.println("Height Of The Tree Is - "+btr.height());
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
    System.out.println(btr.targetSum(75));

    }
}
