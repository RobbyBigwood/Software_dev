public interface Observer {
  

    public void subscribe(boolean checked);
  /** 
  * New information is available to observe.
  * @param newData Data Structure holding information.
  */
  void update(Object newData);
  
}
