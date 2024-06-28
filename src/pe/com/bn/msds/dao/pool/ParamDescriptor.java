package pe.com.bn.msds.dao.pool;

import java.sql.Array;
import java.sql.Timestamp;
import java.sql.Types;

public class ParamDescriptor {

    private int     _dataType;
    private Object  _value;
    
    private ParamDescriptor(int dataType, Object value) {
        _dataType = dataType;
        _value = value;
    }

    // Factory methods for actual instantiation
    public static ParamDescriptor forInt (int paramVal) {
        return new ParamDescriptor (Types.INTEGER, paramVal);
    }
    public static ParamDescriptor forShort (short paramVal) {
        return new ParamDescriptor (Types.INTEGER, paramVal);
    }
    public static ParamDescriptor forShortArray (Array paramVal) {
    	
        return new ParamDescriptor (Types.ARRAY, paramVal);
    }
    public static ParamDescriptor forString (String paramVal) {
        return new ParamDescriptor (Types.VARCHAR, paramVal);
    }

    public static ParamDescriptor forDate (Timestamp paramVal) {
        return new ParamDescriptor (Types.TIMESTAMP, paramVal);
    }
    // Add more here to support more data types . . . .    


    public int getDataType() {
        return _dataType;
    }

    public Object getValue() {
        return _value;
    }


}
