package com.iesvn.yte.paging;

import java.util.Random;

	
	
	
	public class Entry implements java.io.Serializable {
		
		private Random random = new Random();
		private int number1;
		private int number2;
		private int number3;
		private Entry entry;
		
		Entry (Entry entry,int a){
			this.entry = entry;
			this.number1 = a*10+1;
			this.number2 = a*10+2;
			this.number3 = a*10+3;
		}
		public Entry getEntry() {
			return entry;
		}
		public void setEntry(Entry entry) {
			this.entry = entry;
		}
		public int getNumber1() {
			return number1;
		}
		public void setNumber1(int number1) {
			this.number1 = number1;
		}
		public int getNumber2() {
			return number2;
		}
		public void setNumber2(int number2) {
			this.number2 = number2;
		}
		public int getNumber3() {
			return number3;
		}
		public void setNumber3(int number3) {
			this.number3 = number3;
		}		
	}





