package fileparser;

/**
 * @author Voki
 */
public class DataPackage <Data> {
    public final Data data;
    public final DataAction dataAction;

    public DataPackage(Data data, DataAction dataAction) {
        this.data = data;
        this.dataAction = dataAction;
    }
    
}