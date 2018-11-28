package TPSP16S2;

import java.util.*;
public class History {

private String attractname;
private ArrayList<DateFormat> visitdate;


public History(String n){//divide name and dates
	
	attractname=n;
	visitdate=new ArrayList<DateFormat>();
	
}
public History(){
	attractname=null;
	visitdate.add(new DateFormat());
}
public void changetonull(){
	attractname=null;
	for(int i=0;i<visitdate.size();i++)
	visitdate.remove(i);
}
public void addDate(String s){// just append a valid date in the date part of a visit history
	
	DateFormat date=new DateFormat(s);
	if(date.isValid())
	visitdate.add(date);
}
public void delete(String s){
	visitdate.remove(new DateFormat(s));
}
public void modifydate(String s){// for update cardinfo
	int i=0;
	if(i<visitdate.size()&&i>=0)
	visitdate.set(i, new DateFormat(s));
	else
		System.out.println("no date can be modified in this position");
}

public String getattractname(){
	return attractname;
}

public ArrayList<DateFormat> getvisitdate(){//for writing to file. including sorting.
	DateFormat temp=new DateFormat ();
	if(visitdate.size()>1){
		
	for(int j=0;j<visitdate.size();j++){
	for(int i=visitdate.size()-1;i>j;i--){
	if(visitdate.get(i-1).getdate().after(visitdate.get(i).getdate())){
		
						temp = visitdate.get(i);
						visitdate.set(i, visitdate.get(i - 1));
		visitdate.set(i-1, temp);
		
	}}
	}
	
	}
	
	return visitdate;
	
}
}