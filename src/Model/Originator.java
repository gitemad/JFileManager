package Model;

public class Originator {
    private String state;
    
    /**
     * set state
     * @param state state to set
     */
    public void set(String state) {
        this.state = state;
    }
 
    /**
     * save state to memento
     * @return the Memento
     */
    public Memento saveToMemento() {
        return new Memento(this.state);
    }
 
    /**
     * restore from memento
     * @param memento memento to restore
     */
    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
    }
    
    /**
     * get the current state
     * @return state
     */
    public String get() {
    	return state;
    }

    public static class Memento {
        private final String state;
        
        /**
         * Only constructor of class with following parameters
         * @param stateToSave state to save to memento
         */
        public Memento(String stateToSave) {
            state = stateToSave;
        }
 
        // accessible by outer class only
        private String getSavedState() {
            return state;
        }
    }
}
