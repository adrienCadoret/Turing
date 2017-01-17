package data;

/**
 * @author MOSER Meven, ROBIN Alexis
 *
 */
public class Ruban {

	// ---------- ATTRIBUTE
	
	/**
	 * The sentinel's element of the list.
	 */
	private CaseRuban sentinel ;
	
	/**
	 * The current's element of the list.
	 */
	private CaseRuban current ;
	
	/**
	 * The size's list.
	 */
	private int size ; 
	
	
	// ---------- CONSTRUCTOR
	/**
	 * The LinkedList's constructor.
	 */
	public Ruban() {
		this.sentinel = new CaseRuban();
		this.current = this.sentinel;
		this.size = 0;
	}

	
	// ---------- METHODS
	// ----- ACCESSORS
	
	/** 
	 * Return the value of the current element.
	 *
	 * @return The value of the current element.
	 * @see datastruct.List#getValue()
	 */
	public Object getValue() {
		Object ret = null;
		
		if(!isEmpty()){
			ret = (this.current).symbol;
		}
		else{
			System.out.println("The list is empty.");
		}
		
		return ret;
	}

	/** 
	 * Set a value to the current element.
	 * 
	 * @param newData The Object to set as the value of the current element.
	 * @see datastruct.List#setValue(java.lang.Object)
	 */
	public void setValue(char newSymbol) {
		if(!isEmpty()){
			this.current.symbol = newSymbol;
		}
		else{
			System.out.println("The list is empty.");
		}
	}
	
	/** 
	 * Return the size of the LinkedList.
	 * 
	 * @return The size of the LinkedList.
	 * @see datastruct.List#getSize()
	 */
	public int getSize() {
		return this.size;
	}
	
	/** 
	 * Return the value of the element at the index position.
	 *
	 * @param index The position of the element required.
	 * @return The value of the element at the index position.
	 * @see datastruct.List#getValue()
	 */
	public char getValueAt(int index){
		char ret = ' ';
		
		if ((index >= size) || (index < 0)){
			System.out.println("The value required is not valid.");
		}
		else{
			CaseRuban tmp = sentinel.next;
			
			for(int i = 0 ; i < index ; i++){
				tmp = tmp.next;
			}
			
			ret = tmp.symbol;
		}
		
		return ret;
	}
	
	/**
	 * Method used to get the reference to the current element.
	 * @return the current element.
	 */
	public CaseRuban getCurrent(){
		return this.current;
	}
	
	
	// ----- OTHER METHODS
	
	/**
	 * Set the current element as the head's list.
	 */
	public void goToHead() {
		if(!isEmpty()){
			this.current = sentinel.next;
		}
		else{ System.out.println("The list is empty"); }
	}
	
	/**
	 * Set the current element as the end's list.
	 */
	public void goToEnd() {
		if(!isEmpty()){
			this.current = sentinel.prev;
		}
		else{ System.out.println("The list is empty"); }
	}
	
	/**
	 * Set the current element as the next element.
	 */
	public boolean next() {
		boolean ret = false;
		
		if (this.hasNext()){
			this.current = (this.current).next;
			ret = true;
		}
		
		return ret;
	}
	
	/**
	 * Set the current element as the previous element.
	 */
	public boolean previous() {
		boolean ret = false;
		
		if (this.hasPrevious()){
			this.current = (this.current).prev;
			ret = true;
		}
		
		return ret;
	}
	
	/**
	 * Return a String which contains the content of the LinkedList.
	 *
	 * @return The String which contains the content of the LinkedList.
	 */
	public String toString() {
		String ret = "";
		
		if(!isEmpty()){
			CaseRuban tmp = (this.sentinel).next;
			char tmpSymbol;
			int i = 1;
			
			do{
				tmpSymbol = tmp.symbol; 
				ret += i + ". " + tmpSymbol + "\n";
				
				i++;
				tmp = tmp.next;
			} while (tmp != sentinel);
		}
		else{ ret = "The list is empty"; }
		
		return ret;
	}
	
	/**
	 * Return true if the current previous element isn't null.
	 * 
	 * @return True if the current previous element isn't null. False otherwise.
	 */
	public boolean hasPrevious() {
		boolean ret = true;
		
		if ((this.current).prev == sentinel){
			ret = false;
		}
		
		return ret;
	}

	/**
	 * Return true if the current next element isn't null.
	 * 
	 * @return True if the current next element isn't null. False otherwise.
	 */
	public boolean hasNext() {
		boolean ret = true;
		
		if ((this.current).next == sentinel){
			ret = false;
		}
		
		return ret;
	}
	
	
	/**
	 * Insert a new element on the LinkedList before the current element.
	 *
	 * @param data The value of the new element.
	 * @see datastruct.List#insert(java.lang.Object)
	 */
	public void insert(char symbol) {
		CaseRuban theElem = new CaseRuban(null, null, symbol);
		
		theElem.prev = this.current.prev;
		theElem.next = this.current;
		(this.current).prev.next = theElem;
		(this.current).prev = theElem;
		this.current = theElem;
		
		this.size++;
	}

	/**
	 * Delete the current element.
	 * 
	 * @see datastruct.List#delete()
	 */
	public void delete() {
		if(!isEmpty()){
			(this.current).prev.next = (this.current).next;
			(this.current).next.prev = (this.current).prev;
			
			if(hasPrevious()){
				this.current = (this.current).prev;
			}
			else{
				this.current = (this.current).next;
			}
			
			this.size--;
		}
		else{ System.out.println("The list is already empty"); }
	}

	/** 
	 * Return true if data exists on the LinkedList.
	 * 
	 * @return True if data exists on the LinkedList. False otherwise. 
	 * @see datastruct.List#contains(java.lang.Object)
	 */
	public boolean contains(Object data) {
		boolean ret = false;
		
		if (isEmpty()){
			System.out.println("The list is empty"); 
		}
		else { 
			CaseRuban tmp = sentinel;
			Object currentData;
			
			do{
				tmp = tmp.next;
				currentData = tmp.symbol;
				
				if (currentData.equals(data)){
					ret = true;
				}
			} while (!ret && (tmp.next != sentinel));
		}
		
		if(!ret){
			System.out.println("Object not found");
		}
		
		return ret;
	}


	/**
	 * Return true if the list is empty.
	 * 
	 * @return True if the list is empty. False otherwise. 
	 * @see datastruct.List#isEmpty()
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}
	
	// ---------- INTERNAL CLASS
	
	public class CaseRuban {
		
		/**
		 * The link to the previous element.
		 */
		private CaseRuban prev ;
		
		/**
		 * The link to the next element.
		 */
		private CaseRuban next ;
		
		/**
		 * The value stored on the element.
		 */
		private char symbol ;
		
		/**
		 * CaseRuban's constructor for an empty list.
		 */
		CaseRuban() {
			this.prev = this;
			this.next = this;
			this.symbol = ' ';
		}
		
		/**
		 * CaseRuban's constructor for a double chained list.
		 * 
		 * @param prev The link to the previous element.
		 * @param next The link to the next element.
		 * @param data The values stored on the element.
		 */
		CaseRuban ( CaseRuban prev, CaseRuban next, char symbol ) {
			this.prev = prev;
			this.next = next;
			this.symbol = symbol;
		}

		/**
		 * @return the prev
		 */
		public CaseRuban getPrev() {
			return prev;
		}

		/**
		 * @return the next
		 */
		public CaseRuban getNext() {
			return next;
		}

		/**
		 * @return the symbol
		 */
		public char getSymbol() {
			return symbol;
		}
	}
}
