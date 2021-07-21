package defaultPc;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String,String> map;

    public SymbolTable(){
        map = new HashMap<>();
    }

    public void addEntry(String symbol,int address){
        map.put(symbol,Integer.toString(address));
    }

    public boolean contains(String symbol){
        return map.containsKey(symbol);
    }

    public String getAddress(String symbol){
        return map.get(symbol);
    }
}
