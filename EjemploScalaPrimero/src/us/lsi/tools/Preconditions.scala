package us.lsi.tools

object Preconditions {
  
  /**
	 * Checks that the boolean is true. Use for validating arguments to methods.
	 * @param condition A condition
	 */
	def checkArgument(condition: Boolean) = {
		if(!condition){
			throw new IllegalArgumentException()
		}
	}
	
	/**
	 * Checks that the boolean is true. Use for validating arguments to methods.
	 * @param message A message
	 * @param condition A condition
	 */
	def checkArgument(condition: Boolean, message: String){
		if(!condition){
			throw new IllegalArgumentException(message)
		}
	}

	/**
	 * Checks some state of the object, not dependent on the method arguments.  
	 * @param condition A condition
	 */
	def checkState(condition: Boolean){
		if(!condition){
			throw new IllegalArgumentException()
		}
	}
	/**
	 * Checks some state of the object, not dependent on the method arguments. 
	 * @param message Mensaje a imprimir
	 * @param condition A condition
	 */
	def checkState(condition: Boolean, message: String) {
		if(!condition){
			throw new IllegalArgumentException(message)
		}
	}
	
	/**
	 * Comprueba que los parámetros son no null
	 * @param <T> El tipo del elemento	
	 * @param reference Parámetros a comprobar
	 */
	def checkNotNull[T](reference: T*) {
		if(reference.exists(x=>x == null)){
		  val ind = (0 to reference.length)
					.find(i=>reference(i)==null)
					.get
			throw new NullPointerException(f"Es nulo el elemento $ind%d")		
		}
	}
		
	/**
	 * Checks that index is a valid element index into a list, string, or array with the specified size. 
	 * An element index may range from 0 inclusive to size exclusive. 
	 * You don't pass the list, string, or array directly; you just pass its size. 
	 * @param index Un índice 
	 * @param size El tamaño de la lista
	 * @return Index El índice del elemento
	 */
	def checkElementIndex(index: Int,size: Int): Int = {
		if(!(index>=0 && index<size)){
			throw new IndexOutOfBoundsException(f"Index = $index%d, size = $size%d")
		}
		index
	}
	
	/**
	 * Checks that index is a valid position index into a list, string, or array with the specified size. 
	 * A position index may range from 0 inclusive to size inclusive. 
	 * You don't pass the list, string, or array directly; you just pass its size. Returns index.
	 * @param index El índice del elemento
	 * @param size El tamaño de la lista
	 * @return Index El índice del elemento
	 */
	def checkPositionIndex(index: Int, size: Int): Int = {
		if(!(index>=0 && index<=size)){
			throw new IndexOutOfBoundsException("Index = %index%d, size = $size%d")
		}
		index
	}
	
}