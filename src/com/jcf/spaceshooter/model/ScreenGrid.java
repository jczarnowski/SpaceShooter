package com.jcf.spaceshooter.model;

import java.util.ArrayList;

import android.util.Log;

public class ScreenGrid {
	ArrayList<ArrayList<ArrayList<InteractiveSpaceObject>>> grid;

	float verRes, horRes;
	int verSharNum, horSharNum;

	public ScreenGrid(int width, int height, int horizontalSharesNumber, int verticalSharesNumber)
	{
		verRes = height/(float)verticalSharesNumber;
		horRes = width/(float)horizontalSharesNumber;
		verSharNum = verticalSharesNumber;
		horSharNum = horizontalSharesNumber;

		
		//initialization
		grid = new ArrayList<ArrayList<ArrayList<InteractiveSpaceObject>>>();
		for(int i = 0; i < verticalSharesNumber; i++)
		{
			ArrayList<ArrayList<InteractiveSpaceObject>> tmpHorizontalList = new ArrayList<ArrayList<InteractiveSpaceObject>>();
			for(int j = 0; j < horizontalSharesNumber; j++)
			{
				ArrayList<InteractiveSpaceObject> tmpSOList = new ArrayList<InteractiveSpaceObject>();
				tmpHorizontalList.add(tmpSOList);
			}
			grid.add(tmpHorizontalList);			
		}
	}

	public ArrayList<InteractiveSpaceObject> getObjectList(int x, int y)
	{
		return grid.get(y).get(x);
	}	

	public void clear ()
	{
		for(int i = 0; i < verSharNum; i++)
		{
			for(int j = 0; j < horSharNum; j++)
			{				
				Log.d("grid","n: "+grid.get(i).get(j).size() + " com: " + i +","+j);
				grid.get(i).get(j).clear();
			}
		}
	}

	public float getVerRes() {
		return verRes;
	}

	public float getHorRes() {
		return horRes;
	}

	public int getVerSharNum() {
		return verSharNum;
	}

	public int getHorSharNum() {
		return horSharNum;
	}

	public void registerSpaceObject(InteractiveSpaceObject obj)
	{
		Log.d("grid", "registerd");
		int a = (int)(obj.getY()/verRes);
		int b = (int)(obj.getX()/horRes);
		if( a >= 0 && b >= 0 && a < verSharNum && b < horSharNum)
			grid.get(a).get(b).add(obj);
	}

}
