
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> first;	//Premier element de la liste. (Ajouté pour avoir O(1))
	private Node<AnyType> last;		//Dernier element de la liste.
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		if (empty())
			return null;
		
		//FIFO: tête de file = le premier entré qui serait le prochain à sortir
		return first.getData();
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if (empty())
			throw new EmptyQueueException(); //Est-ce légal en Java? Ou juste throw EmptyQueueException(); ?
		
		first = first.getNext(); //Le premier est remplacé par le deuxième, qui devient le premier (FIFO)
		size--;
	}
	
	//Ajoute un element a la fin de la file
	//complexité asymptotique: O(1)
	public void push(AnyType item)
	{		
		Node<AnyType> newItem = new Node<AnyType>(item, null);
		if (empty()) {
			first = newItem;
			last = first;
		}
		else {
			/*if (size == 1) 
				first.setNext(newItem);  //On crée le Link avec l'item suivant
			else 
				last.setNext(newItem);*/
			last.setNext(newItem);		// Si c'est de size 1, le dernier est le premier
			last = newItem;
		}
		size++;					 //On incrémente la taille de la list
		
		//Est-ce que newItem est dead après la fin de la méthode push? 
		//Parce qu'on veut vraiment pas ça, mais on peut pas le stocker ailleurs
	}  
}
