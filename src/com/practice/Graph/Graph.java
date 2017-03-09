package com.practice.Graph;

import java.util.LinkedList;

public class Graph {
	public int data;
	public LinkedList<Integer> list[];
	public Graph(int data){
		this.data=data;
		list = new LinkedList[this.data];
		for(int i=0;i<data;i++)
			list[i]=new LinkedList();
	}
}
